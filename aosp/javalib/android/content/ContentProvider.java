package android.content;

import android.annotation.SystemApi;
import android.app.AppOpsManager;
import android.content.pm.PathPermission;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ParcelableException;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class ContentProvider implements ContentInterface, ComponentCallbacks2 {
  private static final String TAG = "ContentProvider";
  
  private String[] mAuthorities;
  
  private String mAuthority;
  
  private ThreadLocal<Pair<String, String>> mCallingPackage;
  
  private Context mContext = null;
  
  private boolean mExported;
  
  private int mMyUid;
  
  private boolean mNoPerms;
  
  private PathPermission[] mPathPermissions;
  
  private String mReadPermission;
  
  private boolean mSingleUser;
  
  private Transport mTransport = new Transport();
  
  private String mWritePermission;
  
  public ContentProvider() {}
  
  public ContentProvider(Context paramContext, String paramString1, String paramString2, PathPermission[] paramArrayOfPathPermission) {
    this.mContext = paramContext;
    this.mReadPermission = paramString1;
    this.mWritePermission = paramString2;
    this.mPathPermissions = paramArrayOfPathPermission;
  }
  
  private void attachInfo(Context paramContext, ProviderInfo paramProviderInfo, boolean paramBoolean) {
    this.mNoPerms = paramBoolean;
    this.mCallingPackage = new ThreadLocal<>();
    if (this.mContext == null) {
      this.mContext = paramContext;
      if (paramContext != null) {
        Transport transport = this.mTransport;
        if (transport != null)
          transport.mAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops"); 
      } 
      this.mMyUid = Process.myUid();
      if (paramProviderInfo != null) {
        setReadPermission(paramProviderInfo.readPermission);
        setWritePermission(paramProviderInfo.writePermission);
        setPathPermissions(paramProviderInfo.pathPermissions);
        this.mExported = paramProviderInfo.exported;
        if ((paramProviderInfo.flags & 0x40000000) != 0) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        } 
        this.mSingleUser = paramBoolean;
        setAuthorities(paramProviderInfo.authority);
      } 
      if (Build.IS_DEBUGGABLE)
        setTransportLoggingEnabled(Log.isLoggable(getClass().getSimpleName(), 2)); 
      onCreate();
    } 
  }
  
  private int checkPermissionAndAppOp(String paramString1, String paramString2, String paramString3, IBinder paramIBinder) {
    return (getContext().checkPermission(paramString1, Binder.getCallingPid(), Binder.getCallingUid(), paramIBinder) != 0) ? 2 : this.mTransport.noteProxyOp(paramString2, paramString3, AppOpsManager.permissionToOpCode(paramString1));
  }
  
  public static ContentProvider coerceToLocalContentProvider(IContentProvider paramIContentProvider) {
    return (paramIContentProvider instanceof Transport) ? ((Transport)paramIContentProvider).getContentProvider() : null;
  }
  
  public static String getAuthorityWithoutUserId(String paramString) {
    return (paramString == null) ? null : paramString.substring(paramString.lastIndexOf('@') + 1);
  }
  
  public static Uri getUriWithoutUserId(Uri paramUri) {
    if (paramUri == null)
      return null; 
    Uri.Builder builder = paramUri.buildUpon();
    builder.authority(getAuthorityWithoutUserId(paramUri.getAuthority()));
    return builder.build();
  }
  
  public static UserHandle getUserHandleFromUri(Uri paramUri) {
    return UserHandle.of(getUserIdFromUri(paramUri, Process.myUserHandle().getIdentifier()));
  }
  
  public static int getUserIdFromAuthority(String paramString) {
    return getUserIdFromAuthority(paramString, -2);
  }
  
  public static int getUserIdFromAuthority(String paramString, int paramInt) {
    if (paramString == null)
      return paramInt; 
    int i = paramString.lastIndexOf('@');
    if (i == -1)
      return paramInt; 
    paramString = paramString.substring(0, i);
    try {
      return Integer.parseInt(paramString);
    } catch (NumberFormatException numberFormatException) {
      Log.w("ContentProvider", "Error parsing userId.", numberFormatException);
      return -10000;
    } 
  }
  
  public static int getUserIdFromUri(Uri paramUri) {
    return getUserIdFromUri(paramUri, -2);
  }
  
  public static int getUserIdFromUri(Uri paramUri, int paramInt) {
    return (paramUri == null) ? paramInt : getUserIdFromAuthority(paramUri.getAuthority(), paramInt);
  }
  
  public static Uri maybeAddUserId(Uri paramUri, int paramInt) {
    if (paramUri == null)
      return null; 
    if (paramInt != -2 && "content".equals(paramUri.getScheme()) && !uriHasUserId(paramUri)) {
      Uri.Builder builder = paramUri.buildUpon();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(paramInt);
      stringBuilder.append("@");
      stringBuilder.append(paramUri.getEncodedAuthority());
      builder.encodedAuthority(stringBuilder.toString());
      return builder.build();
    } 
    return paramUri;
  }
  
  private Uri maybeGetUriWithoutUserId(Uri paramUri) {
    return this.mSingleUser ? paramUri : getUriWithoutUserId(paramUri);
  }
  
  private Pair<String, String> setCallingPackage(Pair<String, String> paramPair) {
    Pair<String, String> pair = this.mCallingPackage.get();
    this.mCallingPackage.set(paramPair);
    onCallingPackageChanged();
    return pair;
  }
  
  public static boolean uriHasUserId(Uri paramUri) {
    return (paramUri == null) ? false : (TextUtils.isEmpty(paramUri.getUserInfo()) ^ true);
  }
  
  private void validateIncomingAuthority(String paramString) throws SecurityException {
    if (!matchesOurAuthorities(getAuthorityWithoutUserId(paramString))) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("The authority ");
      stringBuilder.append(paramString);
      stringBuilder.append(" does not match the one of the contentProvider: ");
      paramString = stringBuilder.toString();
      if (this.mAuthority != null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(this.mAuthority);
        paramString = stringBuilder.toString();
      } else {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(Arrays.toString((Object[])this.mAuthorities));
        paramString = stringBuilder.toString();
      } 
      throw new SecurityException(paramString);
    } 
  }
  
  public ContentProviderResult[] applyBatch(String paramString, ArrayList<ContentProviderOperation> paramArrayList) throws OperationApplicationException {
    return applyBatch(paramArrayList);
  }
  
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> paramArrayList) throws OperationApplicationException {
    int i = paramArrayList.size();
    ContentProviderResult[] arrayOfContentProviderResult = new ContentProviderResult[i];
    for (byte b = 0; b < i; b++)
      arrayOfContentProviderResult[b] = ((ContentProviderOperation)paramArrayList.get(b)).apply(this, arrayOfContentProviderResult, b); 
    return arrayOfContentProviderResult;
  }
  
  public void attachInfo(Context paramContext, ProviderInfo paramProviderInfo) {
    attachInfo(paramContext, paramProviderInfo, false);
  }
  
  public void attachInfoForTesting(Context paramContext, ProviderInfo paramProviderInfo) {
    attachInfo(paramContext, paramProviderInfo, true);
  }
  
  public int bulkInsert(Uri paramUri, ContentValues[] paramArrayOfContentValues) {
    int i = paramArrayOfContentValues.length;
    for (byte b = 0; b < i; b++)
      insert(paramUri, paramArrayOfContentValues[b]); 
    return i;
  }
  
  public Bundle call(String paramString1, String paramString2, Bundle paramBundle) {
    return null;
  }
  
  public Bundle call(String paramString1, String paramString2, String paramString3, Bundle paramBundle) {
    return call(paramString2, paramString3, paramBundle);
  }
  
  public Uri canonicalize(Uri paramUri) {
    return null;
  }
  
  @SystemApi
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2) {
    return -1;
  }
  
  boolean checkUser(int paramInt1, int paramInt2, Context paramContext) {
    int i = UserHandle.getUserId(paramInt2);
    int j = paramContext.getUserId();
    boolean bool = true;
    if (i == j || this.mSingleUser)
      return true; 
    if (paramContext.checkPermission("android.permission.INTERACT_ACROSS_USERS", paramInt1, paramInt2) != 0 && paramContext.checkPermission("android.permission.INTERACT_ACROSS_USERS_FULL", paramInt1, paramInt2) != 0)
      bool = false; 
    return bool;
  }
  
  public final CallingIdentity clearCallingIdentity() {
    return new CallingIdentity(Binder.clearCallingIdentity(), setCallingPackage(null));
  }
  
  public int delete(Uri paramUri, Bundle paramBundle) {
    if (paramBundle == null)
      paramBundle = Bundle.EMPTY; 
    return delete(paramUri, paramBundle.getString("android:query-arg-sql-selection"), paramBundle.getStringArray("android:query-arg-sql-selection-args"));
  }
  
  public abstract int delete(Uri paramUri, String paramString, String[] paramArrayOfString);
  
  public void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.println("nothing to dump");
  }
  
  protected int enforceReadPermissionInner(Uri paramUri, String paramString1, String paramString2, IBinder paramIBinder) throws SecurityException {
    Uri uri;
    Context context = getContext();
    int i = Binder.getCallingPid();
    int j = Binder.getCallingUid();
    String str = null;
    int k = 0;
    boolean bool = UserHandle.isSameApp(j, this.mMyUid);
    byte b = 0;
    if (bool)
      return 0; 
    if (this.mExported && checkUser(i, j, context)) {
      boolean bool1;
      String str1 = getReadPermission();
      if (str1 != null) {
        k = checkPermissionAndAppOp(str1, paramString1, paramString2, paramIBinder);
        if (k == 0)
          return 0; 
        str = str1;
        k = Math.max(0, k);
      } 
      if (str1 == null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      PathPermission[] arrayOfPathPermission = getPathPermissions();
      if (arrayOfPathPermission != null) {
        String str2 = paramUri.getPath();
        int n = arrayOfPathPermission.length;
        while (b < n) {
          PathPermission pathPermission = arrayOfPathPermission[b];
          String str3 = pathPermission.getReadPermission();
          if (str3 != null && pathPermission.match(str2)) {
            int i1 = checkPermissionAndAppOp(str3, paramString1, paramString2, paramIBinder);
            if (i1 == 0)
              return 0; 
            bool1 = false;
            k = Math.max(k, i1);
            str = str3;
          } 
          b++;
        } 
      } 
      if (bool1)
        return 0; 
      paramString1 = str;
    } else {
      paramString1 = null;
      k = 0;
    } 
    int m = UserHandle.getUserId(j);
    if (this.mSingleUser && !UserHandle.isSameUser(this.mMyUid, j)) {
      uri = maybeAddUserId(paramUri, m);
    } else {
      uri = paramUri;
    } 
    if (context.checkUriPermission(uri, i, j, 1, paramIBinder) == 0)
      return 0; 
    if (k == 1)
      return 1; 
    if (!"android.permission.MANAGE_DOCUMENTS".equals(this.mReadPermission)) {
      if (this.mExported) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(" requires ");
        stringBuilder1.append(paramString1);
        stringBuilder1.append(", or grantUriPermission()");
        paramString1 = stringBuilder1.toString();
      } else {
        paramString1 = " requires the provider be exported, or grantUriPermission()";
      } 
    } else {
      paramString1 = " requires that you obtain access using ACTION_OPEN_DOCUMENT or related APIs";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Permission Denial: reading ");
    stringBuilder.append(getClass().getName());
    stringBuilder.append(" uri ");
    stringBuilder.append(paramUri);
    stringBuilder.append(" from pid=");
    stringBuilder.append(i);
    stringBuilder.append(", uid=");
    stringBuilder.append(j);
    stringBuilder.append(paramString1);
    throw new SecurityException(stringBuilder.toString());
  }
  
  protected int enforceWritePermissionInner(Uri paramUri, String paramString1, String paramString2, IBinder paramIBinder) throws SecurityException {
    Context context = getContext();
    int i = Binder.getCallingPid();
    int j = Binder.getCallingUid();
    String str = null;
    int k = 0;
    if (UserHandle.isSameApp(j, this.mMyUid))
      return 0; 
    if (this.mExported && checkUser(i, j, context)) {
      boolean bool;
      String str1 = getWritePermission();
      if (str1 != null) {
        k = checkPermissionAndAppOp(str1, paramString1, paramString2, paramIBinder);
        if (k == 0)
          return 0; 
        str = str1;
        k = Math.max(0, k);
      } 
      if (str1 == null) {
        bool = true;
      } else {
        bool = false;
      } 
      PathPermission[] arrayOfPathPermission = getPathPermissions();
      if (arrayOfPathPermission != null) {
        String str2 = paramUri.getPath();
        int m = arrayOfPathPermission.length;
        for (byte b = 0; b < m; b++) {
          PathPermission pathPermission = arrayOfPathPermission[b];
          String str3 = pathPermission.getWritePermission();
          if (str3 != null && pathPermission.match(str2)) {
            int n = checkPermissionAndAppOp(str3, paramString1, paramString2, paramIBinder);
            if (n == 0)
              return 0; 
            bool = false;
            k = Math.max(k, n);
            str = str3;
          } 
        } 
      } 
      if (bool)
        return 0; 
      paramString1 = str;
    } else {
      paramString1 = null;
      k = 0;
    } 
    if (context.checkUriPermission(paramUri, i, j, 2, paramIBinder) == 0)
      return 0; 
    if (k == 1)
      return 1; 
    if (this.mExported) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(" requires ");
      stringBuilder1.append(paramString1);
      stringBuilder1.append(", or grantUriPermission()");
      paramString1 = stringBuilder1.toString();
    } else {
      paramString1 = " requires the provider be exported, or grantUriPermission()";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Permission Denial: writing ");
    stringBuilder.append(getClass().getName());
    stringBuilder.append(" uri ");
    stringBuilder.append(paramUri);
    stringBuilder.append(" from pid=");
    stringBuilder.append(i);
    stringBuilder.append(", uid=");
    stringBuilder.append(j);
    stringBuilder.append(paramString1);
    throw new SecurityException(stringBuilder.toString());
  }
  
  public AppOpsManager getAppOpsManager() {
    return this.mTransport.mAppOpsManager;
  }
  
  public final String getCallingAttributionTag() {
    Pair pair = this.mCallingPackage.get();
    return (pair != null) ? (String)pair.second : null;
  }
  
  @Deprecated
  public final String getCallingFeatureId() {
    return getCallingAttributionTag();
  }
  
  public final String getCallingPackage() {
    Pair pair = this.mCallingPackage.get();
    if (pair != null) {
      this.mTransport.mAppOpsManager.checkPackage(Binder.getCallingUid(), (String)pair.first);
      return (String)pair.first;
    } 
    return null;
  }
  
  public final String getCallingPackageUnchecked() {
    Pair pair = this.mCallingPackage.get();
    return (pair != null) ? (String)pair.first : null;
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public IContentProvider getIContentProvider() {
    return this.mTransport;
  }
  
  public final PathPermission[] getPathPermissions() {
    return this.mPathPermissions;
  }
  
  public final String getReadPermission() {
    return this.mReadPermission;
  }
  
  public String[] getStreamTypes(Uri paramUri, String paramString) {
    return null;
  }
  
  public abstract String getType(Uri paramUri);
  
  public final String getWritePermission() {
    return this.mWritePermission;
  }
  
  public abstract Uri insert(Uri paramUri, ContentValues paramContentValues);
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) {
    return insert(paramUri, paramContentValues);
  }
  
  protected boolean isTemporary() {
    return false;
  }
  
  protected final boolean matchesOurAuthorities(String paramString) {
    String str = this.mAuthority;
    if (str != null)
      return str.equals(paramString); 
    String[] arrayOfString = this.mAuthorities;
    if (arrayOfString != null) {
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++) {
        if (this.mAuthorities[b].equals(paramString))
          return true; 
      } 
    } 
    return false;
  }
  
  public void onCallingPackageChanged() {}
  
  public void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public abstract boolean onCreate();
  
  public void onLowMemory() {}
  
  public void onTrimMemory(int paramInt) {}
  
  public AssetFileDescriptor openAssetFile(Uri paramUri, String paramString) throws FileNotFoundException {
    ParcelFileDescriptor parcelFileDescriptor = openFile(paramUri, paramString);
    if (parcelFileDescriptor != null) {
      AssetFileDescriptor assetFileDescriptor = new AssetFileDescriptor(parcelFileDescriptor, 0L, -1L);
    } else {
      parcelFileDescriptor = null;
    } 
    return (AssetFileDescriptor)parcelFileDescriptor;
  }
  
  public AssetFileDescriptor openAssetFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    return openAssetFile(paramUri, paramString);
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString) throws FileNotFoundException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No files supported by provider at ");
    stringBuilder.append(paramUri);
    throw new FileNotFoundException(stringBuilder.toString());
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    return openFile(paramUri, paramString);
  }
  
  protected final ParcelFileDescriptor openFileHelper(Uri paramUri, String paramString) throws FileNotFoundException {
    StringBuilder stringBuilder;
    Cursor cursor = query(paramUri, new String[] { "_data" }, null, null, null);
    if (cursor != null) {
      i = cursor.getCount();
    } else {
      i = 0;
    } 
    if (i != 1) {
      if (cursor != null)
        cursor.close(); 
      if (!i) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("No entry for ");
        stringBuilder1.append(paramUri);
        throw new FileNotFoundException(stringBuilder1.toString());
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Multiple items at ");
      stringBuilder.append(paramUri);
      throw new FileNotFoundException(stringBuilder.toString());
    } 
    cursor.moveToFirst();
    int i = cursor.getColumnIndex("_data");
    if (i >= 0) {
      String str = cursor.getString(i);
    } else {
      paramUri = null;
    } 
    cursor.close();
    if (paramUri != null) {
      i = ParcelFileDescriptor.parseMode((String)stringBuilder);
      return ParcelFileDescriptor.open(new File((String)paramUri), i);
    } 
    throw new FileNotFoundException("Column _data not found.");
  }
  
  public <T> ParcelFileDescriptor openPipeHelper(Uri paramUri, String paramString, Bundle paramBundle, T paramT, PipeDataWriter<T> paramPipeDataWriter) throws FileNotFoundException {
    try {
      ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
      AsyncTask<Object, Object, Object> asyncTask = new AsyncTask<Object, Object, Object>() {
          protected Object doInBackground(Object... param1VarArgs) {
            func.writeDataToPipe(fds[1], uri, mimeType, opts, args);
            try {
              fds[1].close();
            } catch (IOException iOException) {
              Log.w("ContentProvider", "Failure closing pipe", iOException);
            } 
            return null;
          }
        };
      super(this, paramPipeDataWriter, arrayOfParcelFileDescriptor, paramUri, paramString, paramBundle, paramT);
      asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])null);
      return arrayOfParcelFileDescriptor[0];
    } catch (IOException iOException) {
      throw new FileNotFoundException("failure making pipe");
    } 
  }
  
  public AssetFileDescriptor openTypedAssetFile(Uri paramUri, String paramString, Bundle paramBundle) throws FileNotFoundException {
    if ("*/*".equals(paramString))
      return openAssetFile(paramUri, "r"); 
    String str = getType(paramUri);
    if (str != null && ClipDescription.compareMimeTypes(str, paramString))
      return openAssetFile(paramUri, "r"); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't open ");
    stringBuilder.append(paramUri);
    stringBuilder.append(" as type ");
    stringBuilder.append(paramString);
    throw new FileNotFoundException(stringBuilder.toString());
  }
  
  public AssetFileDescriptor openTypedAssetFile(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    return openTypedAssetFile(paramUri, paramString, paramBundle);
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, CancellationSignal paramCancellationSignal) {
    if (paramBundle == null)
      paramBundle = Bundle.EMPTY; 
    String str1 = paramBundle.getString("android:query-arg-sql-sort-order");
    String str2 = str1;
    if (str1 == null) {
      str2 = str1;
      if (paramBundle.containsKey("android:query-arg-sort-columns"))
        str2 = ContentResolver.createSqlSortClause(paramBundle); 
    } 
    return query(paramUri, paramArrayOfString, paramBundle.getString("android:query-arg-sql-selection"), paramBundle.getStringArray("android:query-arg-sql-selection-args"), str2, paramCancellationSignal);
  }
  
  public abstract Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2);
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal) {
    return query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
  }
  
  public boolean refresh(Uri paramUri, Bundle paramBundle, CancellationSignal paramCancellationSignal) {
    return false;
  }
  
  public Uri rejectInsert(Uri paramUri, ContentValues paramContentValues) {
    return paramUri.buildUpon().appendPath("0").build();
  }
  
  public final Context requireContext() {
    Context context = getContext();
    if (context != null)
      return context; 
    throw new IllegalStateException("Cannot find context from the provider.");
  }
  
  public final void restoreCallingIdentity(CallingIdentity paramCallingIdentity) {
    Binder.restoreCallingIdentity(paramCallingIdentity.binderToken);
    this.mCallingPackage.set(paramCallingIdentity.callingPackage);
  }
  
  public final void setAppOps(int paramInt1, int paramInt2) {
    if (!this.mNoPerms) {
      this.mTransport.mReadOp = paramInt1;
      this.mTransport.mWriteOp = paramInt2;
    } 
  }
  
  protected final void setAuthorities(String paramString) {
    if (paramString != null)
      if (paramString.indexOf(';') == -1) {
        this.mAuthority = paramString;
        this.mAuthorities = null;
      } else {
        this.mAuthority = null;
        this.mAuthorities = paramString.split(";");
      }  
  }
  
  protected final void setPathPermissions(PathPermission[] paramArrayOfPathPermission) {
    this.mPathPermissions = paramArrayOfPathPermission;
  }
  
  protected final void setReadPermission(String paramString) {
    this.mReadPermission = paramString;
  }
  
  public final void setTransportLoggingEnabled(boolean paramBoolean) {
    Transport transport = this.mTransport;
    if (transport == null)
      return; 
    if (paramBoolean) {
      transport.mInterface = new LoggingContentInterface(getClass().getSimpleName(), this);
    } else {
      transport.mInterface = this;
    } 
  }
  
  protected final void setWritePermission(String paramString) {
    this.mWritePermission = paramString;
  }
  
  public void shutdown() {
    Log.w("ContentProvider", "implement ContentProvider shutdown() to make sure all database connections are gracefully shutdown");
  }
  
  public Uri uncanonicalize(Uri paramUri) {
    return paramUri;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) {
    if (paramBundle == null)
      paramBundle = Bundle.EMPTY; 
    return update(paramUri, paramContentValues, paramBundle.getString("android:query-arg-sql-selection"), paramBundle.getStringArray("android:query-arg-sql-selection-args"));
  }
  
  public abstract int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString);
  
  public Uri validateIncomingUri(Uri paramUri) throws SecurityException {
    StringBuilder stringBuilder;
    String str = paramUri.getAuthority();
    if (!this.mSingleUser) {
      int i = getUserIdFromAuthority(str, -2);
      if (i != -2 && i != this.mContext.getUserId()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("trying to query a ContentProvider in user ");
        stringBuilder.append(this.mContext.getUserId());
        stringBuilder.append(" with a uri belonging to user ");
        stringBuilder.append(i);
        throw new SecurityException(stringBuilder.toString());
      } 
    } 
    validateIncomingAuthority(str);
    str = stringBuilder.getEncodedPath();
    if (str != null && str.indexOf("//") != -1) {
      Uri uri = stringBuilder.buildUpon().encodedPath(str.replaceAll("//+", "/")).build();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Normalized ");
      stringBuilder1.append(stringBuilder);
      stringBuilder1.append(" to ");
      stringBuilder1.append(uri);
      stringBuilder1.append(" to avoid possible security issues");
      Log.w("ContentProvider", stringBuilder1.toString());
      return uri;
    } 
    return (Uri)stringBuilder;
  }
  
  public final class CallingIdentity {
    public final long binderToken;
    
    public final Pair<String, String> callingPackage;
    
    public CallingIdentity(long param1Long, Pair<String, String> param1Pair) {
      this.binderToken = param1Long;
      this.callingPackage = param1Pair;
    }
  }
  
  public static interface PipeDataWriter<T> {
    void writeDataToPipe(ParcelFileDescriptor param1ParcelFileDescriptor, Uri param1Uri, String param1String, Bundle param1Bundle, T param1T);
  }
  
  class Transport extends ContentProviderNative {
    volatile AppOpsManager mAppOpsManager = null;
    
    volatile ContentInterface mInterface = ContentProvider.this;
    
    volatile int mReadOp = -1;
    
    volatile int mWriteOp = -1;
    
    private void enforceFilePermission(String param1String1, String param1String2, Uri param1Uri, String param1String3, IBinder param1IBinder) throws FileNotFoundException, SecurityException {
      if (param1String3 != null && param1String3.indexOf('w') != -1) {
        if (enforceWritePermission(param1String1, param1String2, param1Uri, param1IBinder) != 0)
          throw new FileNotFoundException("App op not allowed"); 
      } else if (enforceReadPermission(param1String1, param1String2, param1Uri, param1IBinder) != 0) {
        throw new FileNotFoundException("App op not allowed");
      } 
    }
    
    private int enforceReadPermission(String param1String1, String param1String2, Uri param1Uri, IBinder param1IBinder) throws SecurityException {
      int i = ContentProvider.this.enforceReadPermissionInner(param1Uri, param1String1, param1String2, param1IBinder);
      return (i != 0) ? i : noteProxyOp(param1String1, param1String2, this.mReadOp);
    }
    
    private int enforceWritePermission(String param1String1, String param1String2, Uri param1Uri, IBinder param1IBinder) throws SecurityException {
      int i = ContentProvider.this.enforceWritePermissionInner(param1Uri, param1String1, param1String2, param1IBinder);
      return (i != 0) ? i : noteProxyOp(param1String1, param1String2, this.mWriteOp);
    }
    
    private int noteProxyOp(String param1String1, String param1String2, int param1Int) {
      if (param1Int != -1) {
        param1Int = this.mAppOpsManager.noteProxyOp(param1Int, param1String1, Binder.getCallingUid(), param1String2, null);
        if (param1Int == 3)
          param1Int = 1; 
        return param1Int;
      } 
      return 0;
    }
    
    public ContentProviderResult[] applyBatch(String param1String1, String param1String2, String param1String3, ArrayList<ContentProviderOperation> param1ArrayList) throws OperationApplicationException {
      ContentProvider.this.validateIncomingAuthority(param1String3);
      int i = param1ArrayList.size();
      int[] arrayOfInt = new int[i];
      byte b = 0;
      while (b < i) {
        ContentProviderOperation contentProviderOperation1 = param1ArrayList.get(b);
        Uri uri1 = contentProviderOperation1.getUri();
        arrayOfInt[b] = ContentProvider.getUserIdFromUri(uri1);
        uri1 = ContentProvider.this.validateIncomingUri(uri1);
        Uri uri2 = ContentProvider.this.maybeGetUriWithoutUserId(uri1);
        ContentProviderOperation contentProviderOperation2 = contentProviderOperation1;
        if (!Objects.equals(contentProviderOperation1.getUri(), uri2)) {
          contentProviderOperation2 = new ContentProviderOperation(contentProviderOperation1, uri2);
          param1ArrayList.set(b, contentProviderOperation2);
        } 
        if (!contentProviderOperation2.isReadOperation() || enforceReadPermission(param1String1, param1String2, uri2, null) == 0) {
          if (!contentProviderOperation2.isWriteOperation() || enforceWritePermission(param1String1, param1String2, uri2, null) == 0) {
            b++;
            continue;
          } 
          throw new OperationApplicationException("App op not allowed", 0);
        } 
        throw new OperationApplicationException("App op not allowed", 0);
      } 
      Trace.traceBegin(1048576L, "applyBatch");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        ContentProviderResult[] arrayOfContentProviderResult = this.mInterface.applyBatch(param1String3, param1ArrayList);
        if (arrayOfContentProviderResult != null)
          for (b = 0; b < arrayOfContentProviderResult.length; b++) {
            if (arrayOfInt[b] != -2)
              arrayOfContentProviderResult[b] = new ContentProviderResult(arrayOfContentProviderResult[b], arrayOfInt[b]); 
          }  
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return arrayOfContentProviderResult;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public int bulkInsert(String param1String1, String param1String2, Uri param1Uri, ContentValues[] param1ArrayOfContentValues) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      if (enforceWritePermission(param1String1, param1String2, param1Uri, null) != 0)
        return 0; 
      Trace.traceBegin(1048576L, "bulkInsert");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        int i = this.mInterface.bulkInsert(param1Uri, param1ArrayOfContentValues);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return i;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public Bundle call(String param1String1, String param1String2, String param1String3, String param1String4, String param1String5, Bundle param1Bundle) {
      ContentProvider.this.validateIncomingAuthority(param1String3);
      Bundle.setDefusable(param1Bundle, true);
      Trace.traceBegin(1048576L, "call");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        Bundle bundle = this.mInterface.call(param1String3, param1String4, param1String5, param1Bundle);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return bundle;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public Uri canonicalize(String param1String1, String param1String2, Uri param1Uri) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      int i = ContentProvider.getUserIdFromUri(param1Uri);
      param1Uri = ContentProvider.getUriWithoutUserId(param1Uri);
      if (enforceReadPermission(param1String1, param1String2, param1Uri, null) != 0)
        return null; 
      Trace.traceBegin(1048576L, "canonicalize");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        Uri uri = ContentProvider.maybeAddUserId(this.mInterface.canonicalize(param1Uri), i);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return uri;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public void canonicalizeAsync(String param1String1, String param1String2, Uri param1Uri, RemoteCallback param1RemoteCallback) {
      Bundle bundle = new Bundle();
      try {
        bundle.putParcelable("result", (Parcelable)canonicalize(param1String1, param1String2, param1Uri));
      } catch (Exception exception) {
        bundle.putParcelable("error", (Parcelable)new ParcelableException(exception));
      } 
      param1RemoteCallback.sendResult(bundle);
    }
    
    public int checkUriPermission(String param1String1, String param1String2, Uri param1Uri, int param1Int1, int param1Int2) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      Trace.traceBegin(1048576L, "checkUriPermission");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        param1Int1 = this.mInterface.checkUriPermission(param1Uri, param1Int1, param1Int2);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return param1Int1;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public ICancellationSignal createCancellationSignal() {
      return CancellationSignal.createTransport();
    }
    
    public int delete(String param1String1, String param1String2, Uri param1Uri, Bundle param1Bundle) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      if (enforceWritePermission(param1String1, param1String2, param1Uri, null) != 0)
        return 0; 
      Trace.traceBegin(1048576L, "delete");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        int i = this.mInterface.delete(param1Uri, param1Bundle);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return i;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    ContentProvider getContentProvider() {
      return ContentProvider.this;
    }
    
    public String getProviderName() {
      return getContentProvider().getClass().getName();
    }
    
    public String[] getStreamTypes(Uri param1Uri, String param1String) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      Trace.traceBegin(1048576L, "getStreamTypes");
      try {
        String[] arrayOfString = this.mInterface.getStreamTypes(param1Uri, param1String);
        Trace.traceEnd(1048576L);
        return arrayOfString;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      Trace.traceEnd(1048576L);
      throw param1Uri;
    }
    
    public String getType(Uri param1Uri) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      Trace.traceBegin(1048576L, "getType");
      try {
        String str = this.mInterface.getType(param1Uri);
        Trace.traceEnd(1048576L);
        return str;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      Trace.traceEnd(1048576L);
      throw param1Uri;
    }
    
    public void getTypeAsync(Uri param1Uri, RemoteCallback param1RemoteCallback) {
      Bundle bundle = new Bundle();
      try {
        bundle.putString("result", getType(param1Uri));
      } catch (Exception exception) {
        bundle.putParcelable("error", (Parcelable)new ParcelableException(exception));
      } 
      param1RemoteCallback.sendResult(bundle);
    }
    
    public Uri insert(String param1String1, String param1String2, Uri param1Uri, ContentValues param1ContentValues, Bundle param1Bundle) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      int i = ContentProvider.getUserIdFromUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      if (enforceWritePermission(param1String1, param1String2, param1Uri, null) != 0) {
        pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
        try {
          return ContentProvider.this.rejectInsert(param1Uri, param1ContentValues);
        } finally {
          ContentProvider.this.setCallingPackage(pair);
        } 
      } 
      Trace.traceBegin(1048576L, "insert");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(pair, param1String2));
      try {
        Uri uri = ContentProvider.maybeAddUserId(this.mInterface.insert(param1Uri, param1ContentValues, param1Bundle), i);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return uri;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public AssetFileDescriptor openAssetFile(String param1String1, String param1String2, Uri param1Uri, String param1String3, ICancellationSignal param1ICancellationSignal) throws FileNotFoundException {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      enforceFilePermission(param1String1, param1String2, param1Uri, param1String3, null);
      Trace.traceBegin(1048576L, "openAssetFile");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        AssetFileDescriptor assetFileDescriptor = this.mInterface.openAssetFile(param1Uri, param1String3, CancellationSignal.fromTransport(param1ICancellationSignal));
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return assetFileDescriptor;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public ParcelFileDescriptor openFile(String param1String1, String param1String2, Uri param1Uri, String param1String3, ICancellationSignal param1ICancellationSignal, IBinder param1IBinder) throws FileNotFoundException {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      enforceFilePermission(param1String1, param1String2, param1Uri, param1String3, param1IBinder);
      Trace.traceBegin(1048576L, "openFile");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        ParcelFileDescriptor parcelFileDescriptor = this.mInterface.openFile(param1Uri, param1String3, CancellationSignal.fromTransport(param1ICancellationSignal));
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return parcelFileDescriptor;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public AssetFileDescriptor openTypedAssetFile(String param1String1, String param1String2, Uri param1Uri, String param1String3, Bundle param1Bundle, ICancellationSignal param1ICancellationSignal) throws FileNotFoundException {
      Bundle.setDefusable(param1Bundle, true);
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      enforceFilePermission(param1String1, param1String2, param1Uri, "r", null);
      Trace.traceBegin(1048576L, "openTypedAssetFile");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        AssetFileDescriptor assetFileDescriptor = this.mInterface.openTypedAssetFile(param1Uri, param1String3, param1Bundle, CancellationSignal.fromTransport(param1ICancellationSignal));
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return assetFileDescriptor;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public Cursor query(String param1String1, String param1String2, Uri param1Uri, String[] param1ArrayOfString, Bundle param1Bundle, ICancellationSignal param1ICancellationSignal) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      if (enforceReadPermission(param1String1, param1String2, param1Uri, null) != 0) {
        if (param1ArrayOfString != null)
          return (Cursor)new MatrixCursor(param1ArrayOfString, 0); 
        pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
        try {
          Cursor cursor = this.mInterface.query(param1Uri, param1ArrayOfString, param1Bundle, CancellationSignal.fromTransport(param1ICancellationSignal));
          ContentProvider.this.setCallingPackage(pair);
          return (Cursor)((cursor == null) ? null : new MatrixCursor(cursor.getColumnNames(), 0));
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowAsRuntimeException();
        } finally {}
        ContentProvider.this.setCallingPackage(pair);
        throw param1String2;
      } 
      Trace.traceBegin(1048576L, "query");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(pair, param1String2));
      try {
        Cursor cursor = this.mInterface.query(param1Uri, param1ArrayOfString, param1Bundle, CancellationSignal.fromTransport(param1ICancellationSignal));
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return cursor;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public boolean refresh(String param1String1, String param1String2, Uri param1Uri, Bundle param1Bundle, ICancellationSignal param1ICancellationSignal) throws RemoteException {
      param1Uri = ContentProvider.getUriWithoutUserId(ContentProvider.this.validateIncomingUri(param1Uri));
      if (enforceReadPermission(param1String1, param1String2, param1Uri, null) != 0)
        return false; 
      Trace.traceBegin(1048576L, "refresh");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        return this.mInterface.refresh(param1Uri, param1Bundle, CancellationSignal.fromTransport(param1ICancellationSignal));
      } finally {
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
      } 
    }
    
    public Uri uncanonicalize(String param1String1, String param1String2, Uri param1Uri) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      int i = ContentProvider.getUserIdFromUri(param1Uri);
      param1Uri = ContentProvider.getUriWithoutUserId(param1Uri);
      if (enforceReadPermission(param1String1, param1String2, param1Uri, null) != 0)
        return null; 
      Trace.traceBegin(1048576L, "uncanonicalize");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        Uri uri = ContentProvider.maybeAddUserId(this.mInterface.uncanonicalize(param1Uri), i);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return uri;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
    
    public int update(String param1String1, String param1String2, Uri param1Uri, ContentValues param1ContentValues, Bundle param1Bundle) {
      param1Uri = ContentProvider.this.validateIncomingUri(param1Uri);
      param1Uri = ContentProvider.this.maybeGetUriWithoutUserId(param1Uri);
      if (enforceWritePermission(param1String1, param1String2, param1Uri, null) != 0)
        return 0; 
      Trace.traceBegin(1048576L, "update");
      Pair pair = ContentProvider.this.setCallingPackage(new Pair(param1String1, param1String2));
      try {
        int i = this.mInterface.update(param1Uri, param1ContentValues, param1Bundle);
        ContentProvider.this.setCallingPackage(pair);
        Trace.traceEnd(1048576L);
        return i;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.this.setCallingPackage(pair);
      Trace.traceEnd(1048576L);
      throw param1String2;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */