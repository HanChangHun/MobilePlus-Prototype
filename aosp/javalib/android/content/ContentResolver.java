package android.content;

import android.accounts.Account;
import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.Application;
import android.app.IActivityManager;
import android.app.UriGrantsManager;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.CrossProcessCursorWrapper;
import android.database.Cursor;
import android.database.IContentObserver;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.DeadObjectException;
import android.os.ICancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.system.Int32Ref;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.util.SparseArray;
import com.android.internal.util.MimeIconUtils;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ContentResolver implements ContentInterface {
  public static final Intent ACTION_SYNC_CONN_STATUS_CHANGED;
  
  public static final String ANY_CURSOR_ITEM_TYPE = "vnd.android.cursor.item/*";
  
  public static final int CONTENT_PROVIDER_PUBLISH_TIMEOUT_MILLIS = 10000;
  
  public static final int CONTENT_PROVIDER_READY_TIMEOUT_MILLIS = 20000;
  
  private static final int CONTENT_PROVIDER_TIMEOUT_MILLIS = 3000;
  
  public static final String CONTENT_SERVICE_NAME = "content";
  
  public static final String CURSOR_DIR_BASE_TYPE = "vnd.android.cursor.dir";
  
  public static final String CURSOR_ITEM_BASE_TYPE = "vnd.android.cursor.item";
  
  public static final boolean DEPRECATE_DATA_COLUMNS = StorageManager.hasIsolatedStorage();
  
  public static final String DEPRECATE_DATA_PREFIX = "/mnt/content/";
  
  private static final boolean ENABLE_CONTENT_SAMPLE = false;
  
  public static final String EXTRA_HONORED_ARGS = "android.content.extra.HONORED_ARGS";
  
  public static final String EXTRA_REFRESH_SUPPORTED = "android.content.extra.REFRESH_SUPPORTED";
  
  public static final String EXTRA_SIZE = "android.content.extra.SIZE";
  
  public static final String EXTRA_TOTAL_COUNT = "android.content.extra.TOTAL_COUNT";
  
  @Deprecated
  public static final String MIME_TYPE_DEFAULT = "application/octet-stream";
  
  public static final int NOTIFY_DELETE = 16;
  
  public static final int NOTIFY_INSERT = 4;
  
  public static final int NOTIFY_NO_DELAY = 32768;
  
  public static final int NOTIFY_SKIP_NOTIFY_FOR_DESCENDANTS = 2;
  
  public static final int NOTIFY_SYNC_TO_NETWORK = 1;
  
  public static final int NOTIFY_UPDATE = 8;
  
  public static final String QUERY_ARG_GROUP_COLUMNS = "android:query-arg-group-columns";
  
  public static final String QUERY_ARG_LIMIT = "android:query-arg-limit";
  
  public static final String QUERY_ARG_OFFSET = "android:query-arg-offset";
  
  public static final String QUERY_ARG_SORT_COLLATION = "android:query-arg-sort-collation";
  
  public static final String QUERY_ARG_SORT_COLUMNS = "android:query-arg-sort-columns";
  
  public static final String QUERY_ARG_SORT_DIRECTION = "android:query-arg-sort-direction";
  
  public static final String QUERY_ARG_SORT_LOCALE = "android:query-arg-sort-locale";
  
  public static final String QUERY_ARG_SQL_GROUP_BY = "android:query-arg-sql-group-by";
  
  public static final String QUERY_ARG_SQL_HAVING = "android:query-arg-sql-having";
  
  public static final String QUERY_ARG_SQL_LIMIT = "android:query-arg-sql-limit";
  
  public static final String QUERY_ARG_SQL_SELECTION = "android:query-arg-sql-selection";
  
  public static final String QUERY_ARG_SQL_SELECTION_ARGS = "android:query-arg-sql-selection-args";
  
  public static final String QUERY_ARG_SQL_SORT_ORDER = "android:query-arg-sql-sort-order";
  
  public static final int QUERY_SORT_DIRECTION_ASCENDING = 0;
  
  public static final int QUERY_SORT_DIRECTION_DESCENDING = 1;
  
  public static final String REMOTE_CALLBACK_ERROR = "error";
  
  public static final String REMOTE_CALLBACK_RESULT = "result";
  
  private static final int REMOTE_CONTENT_PROVIDER_TIMEOUT_MILLIS = 23000;
  
  public static final String SCHEME_ANDROID_RESOURCE = "android.resource";
  
  public static final String SCHEME_CONTENT = "content";
  
  public static final String SCHEME_FILE = "file";
  
  private static final int SLOW_THRESHOLD_MILLIS = 500;
  
  public static final int SYNC_ERROR_AUTHENTICATION = 2;
  
  public static final int SYNC_ERROR_CONFLICT = 5;
  
  public static final int SYNC_ERROR_INTERNAL = 8;
  
  public static final int SYNC_ERROR_IO = 3;
  
  private static final String[] SYNC_ERROR_NAMES;
  
  public static final int SYNC_ERROR_PARSE = 4;
  
  public static final int SYNC_ERROR_SYNC_ALREADY_IN_PROGRESS = 1;
  
  public static final int SYNC_ERROR_TOO_MANY_DELETIONS = 6;
  
  public static final int SYNC_ERROR_TOO_MANY_RETRIES = 7;
  
  public static final int SYNC_EXEMPTION_NONE = 0;
  
  public static final int SYNC_EXEMPTION_PROMOTE_BUCKET = 1;
  
  public static final int SYNC_EXEMPTION_PROMOTE_BUCKET_WITH_TEMP = 2;
  
  @Deprecated
  public static final String SYNC_EXTRAS_ACCOUNT = "account";
  
  public static final String SYNC_EXTRAS_DISALLOW_METERED = "allow_metered";
  
  public static final String SYNC_EXTRAS_DISCARD_LOCAL_DELETIONS = "discard_deletions";
  
  public static final String SYNC_EXTRAS_DO_NOT_RETRY = "do_not_retry";
  
  public static final String SYNC_EXTRAS_EXPECTED_DOWNLOAD = "expected_download";
  
  public static final String SYNC_EXTRAS_EXPECTED_UPLOAD = "expected_upload";
  
  public static final String SYNC_EXTRAS_EXPEDITED = "expedited";
  
  @Deprecated
  public static final String SYNC_EXTRAS_FORCE = "force";
  
  public static final String SYNC_EXTRAS_IGNORE_BACKOFF = "ignore_backoff";
  
  public static final String SYNC_EXTRAS_IGNORE_SETTINGS = "ignore_settings";
  
  public static final String SYNC_EXTRAS_INITIALIZE = "initialize";
  
  public static final String SYNC_EXTRAS_MANUAL = "force";
  
  public static final String SYNC_EXTRAS_OVERRIDE_TOO_MANY_DELETIONS = "deletions_override";
  
  public static final String SYNC_EXTRAS_PRIORITY = "sync_priority";
  
  public static final String SYNC_EXTRAS_REQUIRE_CHARGING = "require_charging";
  
  public static final String SYNC_EXTRAS_UPLOAD = "upload";
  
  public static final int SYNC_OBSERVER_TYPE_ACTIVE = 4;
  
  public static final int SYNC_OBSERVER_TYPE_ALL = 2147483647;
  
  public static final int SYNC_OBSERVER_TYPE_PENDING = 2;
  
  public static final int SYNC_OBSERVER_TYPE_SETTINGS = 1;
  
  public static final int SYNC_OBSERVER_TYPE_STATUS = 8;
  
  public static final String SYNC_VIRTUAL_EXTRAS_EXEMPTION_FLAG = "v_exemption";
  
  private static final String TAG = "ContentResolver";
  
  private static volatile IContentService sContentService;
  
  final String mAttributionTag;
  
  private final Context mContext;
  
  final String mPackageName;
  
  private final Random mRandom;
  
  final int mTargetSdkVersion;
  
  final ContentInterface mWrapped;
  
  static {
    ACTION_SYNC_CONN_STATUS_CHANGED = new Intent("com.android.sync.SYNC_CONN_STATUS_CHANGED");
    SYNC_ERROR_NAMES = new String[] { "already-in-progress", "authentication-error", "io-error", "parse-error", "conflict", "too-many-deletions", "too-many-retries", "internal-error" };
  }
  
  public ContentResolver(Context paramContext) {
    this(paramContext, null);
  }
  
  public ContentResolver(Context paramContext, ContentInterface paramContentInterface) {
    Application application;
    this.mRandom = new Random();
    if (paramContext == null)
      application = ActivityThread.currentApplication(); 
    this.mContext = (Context)application;
    this.mPackageName = application.getOpPackageName();
    this.mAttributionTag = this.mContext.getAttributionTag();
    this.mTargetSdkVersion = (this.mContext.getApplicationInfo()).targetSdkVersion;
    this.mWrapped = paramContentInterface;
  }
  
  public static void addPeriodicSync(Account paramAccount, String paramString, Bundle paramBundle, long paramLong) {
    validateSyncExtrasBundle(paramBundle);
    if (!invalidPeriodicExtras(paramBundle))
      try {
        getContentService().addPeriodicSync(paramAccount, paramString, paramBundle, paramLong);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("illegal extras were set");
  }
  
  public static Object addStatusChangeListener(int paramInt, SyncStatusObserver paramSyncStatusObserver) {
    if (paramSyncStatusObserver != null)
      try {
        ISyncStatusObserver.Stub stub = new ISyncStatusObserver.Stub() {
            public void onStatusChanged(int param1Int) throws RemoteException {
              callback.onStatusChanged(param1Int);
            }
          };
        super(paramSyncStatusObserver);
        getContentService().addStatusChangeListener(paramInt, stub);
        return stub;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("you passed in a null callback");
  }
  
  public static void cancelSync(Account paramAccount, String paramString) {
    try {
      getContentService().cancelSync(paramAccount, paramString, null);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void cancelSync(SyncRequest paramSyncRequest) {
    if (paramSyncRequest != null)
      try {
        getContentService().cancelRequest(paramSyncRequest);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("request cannot be null");
  }
  
  public static void cancelSyncAsUser(Account paramAccount, String paramString, int paramInt) {
    try {
      getContentService().cancelSyncAsUser(paramAccount, paramString, null, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static Bundle createSqlQueryBundle(String paramString, String[] paramArrayOfString) {
    return createSqlQueryBundle(paramString, paramArrayOfString, null);
  }
  
  public static Bundle createSqlQueryBundle(String paramString1, String[] paramArrayOfString, String paramString2) {
    if (paramString1 == null && paramArrayOfString == null && paramString2 == null)
      return null; 
    Bundle bundle = new Bundle();
    if (paramString1 != null)
      bundle.putString("android:query-arg-sql-selection", paramString1); 
    if (paramArrayOfString != null)
      bundle.putStringArray("android:query-arg-sql-selection-args", paramArrayOfString); 
    if (paramString2 != null)
      bundle.putString("android:query-arg-sql-sort-order", paramString2); 
    return bundle;
  }
  
  public static String createSqlSortClause(Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'android:query-arg-sort-columns'
    //   3: invokevirtual getStringArray : (Ljava/lang/String;)[Ljava/lang/String;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 168
    //   11: aload_1
    //   12: arraylength
    //   13: ifeq -> 168
    //   16: ldc_w ', '
    //   19: aload_1
    //   20: invokestatic join : (Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
    //   23: astore_2
    //   24: aload_0
    //   25: ldc 'android:query-arg-sort-collation'
    //   27: iconst_3
    //   28: invokevirtual getInt : (Ljava/lang/String;I)I
    //   31: istore_3
    //   32: iload_3
    //   33: ifeq -> 43
    //   36: aload_2
    //   37: astore_1
    //   38: iload_3
    //   39: iconst_1
    //   40: if_icmpne -> 70
    //   43: new java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial <init> : ()V
    //   50: astore_1
    //   51: aload_1
    //   52: aload_2
    //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload_1
    //   58: ldc_w ' COLLATE NOCASE'
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload_1
    //   66: invokevirtual toString : ()Ljava/lang/String;
    //   69: astore_1
    //   70: aload_0
    //   71: ldc 'android:query-arg-sort-direction'
    //   73: ldc_w -2147483648
    //   76: invokevirtual getInt : (Ljava/lang/String;I)I
    //   79: istore_3
    //   80: aload_1
    //   81: astore_0
    //   82: iload_3
    //   83: ldc_w -2147483648
    //   86: if_icmpeq -> 166
    //   89: iload_3
    //   90: ifeq -> 139
    //   93: iload_3
    //   94: iconst_1
    //   95: if_icmpne -> 128
    //   98: new java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial <init> : ()V
    //   105: astore_0
    //   106: aload_0
    //   107: aload_1
    //   108: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_0
    //   113: ldc_w ' DESC'
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload_0
    //   121: invokevirtual toString : ()Ljava/lang/String;
    //   124: astore_0
    //   125: goto -> 166
    //   128: new java/lang/IllegalArgumentException
    //   131: dup
    //   132: ldc_w 'Unsupported sort direction value. See ContentResolver documentation for details.'
    //   135: invokespecial <init> : (Ljava/lang/String;)V
    //   138: athrow
    //   139: new java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial <init> : ()V
    //   146: astore_0
    //   147: aload_0
    //   148: aload_1
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: aload_0
    //   154: ldc_w ' ASC'
    //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload_0
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: astore_0
    //   166: aload_0
    //   167: areturn
    //   168: new java/lang/IllegalArgumentException
    //   171: dup
    //   172: ldc_w 'Can't create sort clause without columns.'
    //   175: invokespecial <init> : (Ljava/lang/String;)V
    //   178: athrow
  }
  
  @SystemApi
  public static Uri decodeFromFile(File paramFile) {
    return translateDeprecatedDataPath(paramFile.getAbsolutePath());
  }
  
  @SystemApi
  public static File encodeToFile(Uri paramUri) {
    return new File(translateDeprecatedDataPath(paramUri));
  }
  
  public static IContentService getContentService() {
    if (sContentService != null)
      return sContentService; 
    sContentService = IContentService.Stub.asInterface(ServiceManager.getService("content"));
    return sContentService;
  }
  
  @Deprecated
  public static SyncInfo getCurrentSync() {
    try {
      List<SyncInfo> list = getContentService().getCurrentSyncs();
      return list.isEmpty() ? null : list.get(0);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static List<SyncInfo> getCurrentSyncs() {
    try {
      return getContentService().getCurrentSyncs();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static List<SyncInfo> getCurrentSyncsAsUser(int paramInt) {
    try {
      return getContentService().getCurrentSyncsAsUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static int getIsSyncable(Account paramAccount, String paramString) {
    try {
      return getContentService().getIsSyncable(paramAccount, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static int getIsSyncableAsUser(Account paramAccount, String paramString, int paramInt) {
    try {
      return getContentService().getIsSyncableAsUser(paramAccount, paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static boolean getMasterSyncAutomatically() {
    try {
      return getContentService().getMasterSyncAutomatically();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static boolean getMasterSyncAutomaticallyAsUser(int paramInt) {
    try {
      return getContentService().getMasterSyncAutomaticallyAsUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static List<PeriodicSync> getPeriodicSyncs(Account paramAccount, String paramString) {
    try {
      return getContentService().getPeriodicSyncs(paramAccount, paramString, null);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static String[] getSyncAdapterPackagesForAuthorityAsUser(String paramString, int paramInt) {
    try {
      return getContentService().getSyncAdapterPackagesForAuthorityAsUser(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static SyncAdapterType[] getSyncAdapterTypes() {
    try {
      return getContentService().getSyncAdapterTypes();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static SyncAdapterType[] getSyncAdapterTypesAsUser(int paramInt) {
    try {
      return getContentService().getSyncAdapterTypesAsUser(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static boolean getSyncAutomatically(Account paramAccount, String paramString) {
    try {
      return getContentService().getSyncAutomatically(paramAccount, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static boolean getSyncAutomaticallyAsUser(Account paramAccount, String paramString, int paramInt) {
    try {
      return getContentService().getSyncAutomaticallyAsUser(paramAccount, paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static SyncStatusInfo getSyncStatus(Account paramAccount, String paramString) {
    try {
      return getContentService().getSyncStatus(paramAccount, paramString, null);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static SyncStatusInfo getSyncStatusAsUser(Account paramAccount, String paramString, int paramInt) {
    try {
      return getContentService().getSyncStatusAsUser(paramAccount, paramString, null, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static Bundle includeSqlSelectionArgs(Bundle paramBundle, String paramString, String[] paramArrayOfString) {
    if (paramString != null)
      paramBundle.putString("android:query-arg-sql-selection", paramString); 
    if (paramArrayOfString != null)
      paramBundle.putStringArray("android:query-arg-sql-selection-args", paramArrayOfString); 
    return paramBundle;
  }
  
  public static boolean invalidPeriodicExtras(Bundle paramBundle) {
    return (paramBundle.getBoolean("force", false) || paramBundle.getBoolean("do_not_retry", false) || paramBundle.getBoolean("ignore_backoff", false) || paramBundle.getBoolean("ignore_settings", false) || paramBundle.getBoolean("initialize", false) || paramBundle.getBoolean("force", false) || paramBundle.getBoolean("expedited", false));
  }
  
  public static boolean isSyncActive(Account paramAccount, String paramString) {
    if (paramAccount != null) {
      if (paramString != null)
        try {
          return getContentService().isSyncActive(paramAccount, paramString, null);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("authority must not be null");
    } 
    throw new IllegalArgumentException("account must not be null");
  }
  
  public static boolean isSyncPending(Account paramAccount, String paramString) {
    return isSyncPendingAsUser(paramAccount, paramString, UserHandle.myUserId());
  }
  
  public static boolean isSyncPendingAsUser(Account paramAccount, String paramString, int paramInt) {
    try {
      return getContentService().isSyncPendingAsUser(paramAccount, paramString, null, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static Bitmap loadThumbnail(ContentInterface paramContentInterface, Uri paramUri, Size paramSize, CancellationSignal paramCancellationSignal, int paramInt) throws IOException {
    Objects.requireNonNull(paramContentInterface);
    Objects.requireNonNull(paramUri);
    Objects.requireNonNull(paramSize);
    Bundle bundle = new Bundle();
    bundle.putParcelable("android.content.extra.SIZE", (Parcelable)Point.convert(paramSize));
    Int32Ref int32Ref = new Int32Ref(0);
    Bitmap bitmap2 = ImageDecoder.decodeBitmap(ImageDecoder.createSource(new _$$Lambda$ContentResolver$7ILY1SWNxC2xhk_fQUG6tAXW9Ik(paramContentInterface, paramUri, bundle, paramCancellationSignal, int32Ref)), new _$$Lambda$ContentResolver$RVw7W0M7r0cGmbYi8rAG5GKxq4M(paramInt, paramCancellationSignal, paramSize));
    Bitmap bitmap1 = bitmap2;
    if (int32Ref.value != 0) {
      paramInt = bitmap2.getWidth();
      int i = bitmap2.getHeight();
      Matrix matrix = new Matrix();
      matrix.setRotate(int32Ref.value, (paramInt / 2), (i / 2));
      bitmap1 = Bitmap.createBitmap(bitmap2, 0, 0, paramInt, i, matrix, false);
    } 
    return bitmap1;
  }
  
  private void maybeLogQueryToEventLog(long paramLong, Uri paramUri, String[] paramArrayOfString, Bundle paramBundle) {}
  
  private void maybeLogUpdateToEventLog(long paramLong, Uri paramUri, String paramString1, String paramString2) {}
  
  public static void onDbCorruption(String paramString1, String paramString2, Throwable paramThrowable) {
    try {
      getContentService().onDbCorruption(paramString1, paramString2, Log.getStackTraceString(paramThrowable));
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void removePeriodicSync(Account paramAccount, String paramString, Bundle paramBundle) {
    validateSyncExtrasBundle(paramBundle);
    try {
      getContentService().removePeriodicSync(paramAccount, paramString, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void removeStatusChangeListener(Object paramObject) {
    if (paramObject != null)
      try {
        getContentService().removeStatusChangeListener((ISyncStatusObserver.Stub)paramObject);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("you passed in a null handle");
  }
  
  public static void requestSync(Account paramAccount, String paramString, Bundle paramBundle) {
    requestSyncAsUser(paramAccount, paramString, UserHandle.myUserId(), paramBundle);
  }
  
  public static void requestSync(SyncRequest paramSyncRequest) {
    try {
      getContentService().sync(paramSyncRequest, ActivityThread.currentPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void requestSyncAsUser(Account paramAccount, String paramString, int paramInt, Bundle paramBundle) {
    if (paramBundle != null) {
      SyncRequest syncRequest = (new SyncRequest.Builder()).setSyncAdapter(paramAccount, paramString).setExtras(paramBundle).syncOnce().build();
      try {
        getContentService().syncAsUser(syncRequest, paramInt, ActivityThread.currentPackageName());
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    throw new IllegalArgumentException("Must specify extras.");
  }
  
  private int samplePercentForDuration(long paramLong) {
    return (paramLong >= 500L) ? 100 : ((int)(100L * paramLong / 500L) + 1);
  }
  
  public static void setIsSyncable(Account paramAccount, String paramString, int paramInt) {
    try {
      getContentService().setIsSyncable(paramAccount, paramString, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void setIsSyncableAsUser(Account paramAccount, String paramString, int paramInt1, int paramInt2) {
    try {
      getContentService().setIsSyncableAsUser(paramAccount, paramString, paramInt1, paramInt2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void setMasterSyncAutomatically(boolean paramBoolean) {
    setMasterSyncAutomaticallyAsUser(paramBoolean, UserHandle.myUserId());
  }
  
  public static void setMasterSyncAutomaticallyAsUser(boolean paramBoolean, int paramInt) {
    try {
      getContentService().setMasterSyncAutomaticallyAsUser(paramBoolean, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static void setSyncAutomatically(Account paramAccount, String paramString, boolean paramBoolean) {
    setSyncAutomaticallyAsUser(paramAccount, paramString, paramBoolean, UserHandle.myUserId());
  }
  
  public static void setSyncAutomaticallyAsUser(Account paramAccount, String paramString, boolean paramBoolean, int paramInt) {
    try {
      getContentService().setSyncAutomaticallyAsUser(paramAccount, paramString, paramBoolean, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static int syncErrorStringToInt(String paramString) {
    int i = 0;
    int j = SYNC_ERROR_NAMES.length;
    while (i < j) {
      if (SYNC_ERROR_NAMES[i].equals(paramString))
        return i + 1; 
      i++;
    } 
    if (paramString != null)
      try {
        return Integer.parseInt(paramString);
      } catch (NumberFormatException numberFormatException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("error parsing sync error: ");
        stringBuilder.append(paramString);
        Log.d("ContentResolver", stringBuilder.toString());
      }  
    return 0;
  }
  
  public static String syncErrorToString(int paramInt) {
    if (paramInt >= 1) {
      String[] arrayOfString = SYNC_ERROR_NAMES;
      if (paramInt <= arrayOfString.length)
        return arrayOfString[paramInt - 1]; 
    } 
    return String.valueOf(paramInt);
  }
  
  public static Uri translateDeprecatedDataPath(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("//");
    stringBuilder.append(paramString.substring("/mnt/content/".length()));
    paramString = stringBuilder.toString();
    return Uri.parse((new Uri.Builder()).scheme("content").encodedOpaquePart(paramString).build().toString());
  }
  
  public static String translateDeprecatedDataPath(Uri paramUri) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("/mnt/content/");
    stringBuilder.append(paramUri.getEncodedSchemeSpecificPart().substring(2));
    return stringBuilder.toString();
  }
  
  public static void validateSyncExtrasBundle(Bundle paramBundle) {
    try {
      Iterator<String> iterator = paramBundle.keySet().iterator();
      while (iterator.hasNext()) {
        Object object = paramBundle.get(iterator.next());
        if (object == null || object instanceof Long || object instanceof Integer || object instanceof Boolean || object instanceof Float || object instanceof Double || object instanceof String || object instanceof Account)
          continue; 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("unexpected value type: ");
        stringBuilder.append(object.getClass().getName());
        this(stringBuilder.toString());
        throw illegalArgumentException;
      } 
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw illegalArgumentException;
    } catch (RuntimeException runtimeException) {
      throw new IllegalArgumentException("error unparceling Bundle", runtimeException);
    } 
  }
  
  public static ContentResolver wrap(ContentInterface paramContentInterface) {
    Objects.requireNonNull(paramContentInterface);
    return new ContentResolver(null, paramContentInterface) {
        protected IContentProvider acquireProvider(Context param1Context, String param1String) {
          throw new UnsupportedOperationException();
        }
        
        protected IContentProvider acquireUnstableProvider(Context param1Context, String param1String) {
          throw new UnsupportedOperationException();
        }
        
        public boolean releaseProvider(IContentProvider param1IContentProvider) {
          throw new UnsupportedOperationException();
        }
        
        public boolean releaseUnstableProvider(IContentProvider param1IContentProvider) {
          throw new UnsupportedOperationException();
        }
        
        public void unstableProviderDied(IContentProvider param1IContentProvider) {
          throw new UnsupportedOperationException();
        }
      };
  }
  
  public static ContentResolver wrap(ContentProvider paramContentProvider) {
    return wrap(paramContentProvider);
  }
  
  public static ContentResolver wrap(ContentProviderClient paramContentProviderClient) {
    return wrap(paramContentProviderClient);
  }
  
  public final ContentProviderClient acquireContentProviderClient(Uri paramUri) {
    Objects.requireNonNull(paramUri, "uri");
    IContentProvider iContentProvider = acquireProvider(paramUri);
    return (iContentProvider != null) ? new ContentProviderClient(this, iContentProvider, paramUri.getAuthority(), true) : null;
  }
  
  public final ContentProviderClient acquireContentProviderClient(String paramString) {
    Objects.requireNonNull(paramString, "name");
    IContentProvider iContentProvider = acquireProvider(paramString);
    return (iContentProvider != null) ? new ContentProviderClient(this, iContentProvider, paramString, true) : null;
  }
  
  protected IContentProvider acquireExistingProvider(Context paramContext, String paramString) {
    return acquireProvider(paramContext, paramString);
  }
  
  public final IContentProvider acquireExistingProvider(Uri paramUri) {
    if (!"content".equals(paramUri.getScheme()))
      return null; 
    String str = paramUri.getAuthority();
    return (str != null) ? acquireExistingProvider(this.mContext, str) : null;
  }
  
  protected abstract IContentProvider acquireProvider(Context paramContext, String paramString);
  
  public final IContentProvider acquireProvider(Uri paramUri) {
    if (!"content".equals(paramUri.getScheme()))
      return null; 
    String str = paramUri.getAuthority();
    return (str != null) ? acquireProvider(this.mContext, str) : null;
  }
  
  public final IContentProvider acquireProvider(String paramString) {
    return (paramString == null) ? null : acquireProvider(this.mContext, paramString);
  }
  
  public final ContentProviderClient acquireUnstableContentProviderClient(Uri paramUri) {
    Objects.requireNonNull(paramUri, "uri");
    IContentProvider iContentProvider = acquireUnstableProvider(paramUri);
    return (iContentProvider != null) ? new ContentProviderClient(this, iContentProvider, paramUri.getAuthority(), false) : null;
  }
  
  public final ContentProviderClient acquireUnstableContentProviderClient(String paramString) {
    Objects.requireNonNull(paramString, "name");
    IContentProvider iContentProvider = acquireUnstableProvider(paramString);
    return (iContentProvider != null) ? new ContentProviderClient(this, iContentProvider, paramString, false) : null;
  }
  
  protected abstract IContentProvider acquireUnstableProvider(Context paramContext, String paramString);
  
  public final IContentProvider acquireUnstableProvider(Uri paramUri) {
    return !"content".equals(paramUri.getScheme()) ? null : ((paramUri.getAuthority() != null) ? acquireUnstableProvider(this.mContext, paramUri.getAuthority()) : null);
  }
  
  public final IContentProvider acquireUnstableProvider(String paramString) {
    return (paramString == null) ? null : acquireUnstableProvider(this.mContext, paramString);
  }
  
  public void appNotRespondingViaProvider(IContentProvider paramIContentProvider) {
    throw new UnsupportedOperationException("appNotRespondingViaProvider");
  }
  
  public ContentProviderResult[] applyBatch(String paramString, ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException {
    Objects.requireNonNull(paramString, "authority");
    Objects.requireNonNull(paramArrayList, "operations");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.applyBatch(paramString, paramArrayList); 
      ContentProviderClient contentProviderClient = acquireContentProviderClient(paramString);
      if (contentProviderClient != null)
        try {
          return contentProviderClient.applyBatch(paramArrayList);
        } finally {
          contentProviderClient.release();
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown authority ");
      stringBuilder.append(paramString);
      throw new IllegalArgumentException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final int bulkInsert(Uri paramUri, ContentValues[] paramArrayOfContentValues) {
    Objects.requireNonNull(paramUri, "url");
    Objects.requireNonNull(paramArrayOfContentValues, "values");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.bulkInsert(paramUri, paramArrayOfContentValues); 
      IContentProvider iContentProvider = acquireProvider(paramUri);
      if (iContentProvider != null)
        try {
          long l = SystemClock.uptimeMillis();
          int i = iContentProvider.bulkInsert(this.mPackageName, this.mAttributionTag, paramUri, paramArrayOfContentValues);
          maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, paramUri, "bulkinsert", null);
          return i;
        } catch (RemoteException remoteException) {
          return 0;
        } finally {
          releaseProvider(iContentProvider);
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown URL ");
      stringBuilder.append(paramUri);
      throw new IllegalArgumentException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      return 0;
    } 
  }
  
  public final Bundle call(Uri paramUri, String paramString1, String paramString2, Bundle paramBundle) {
    return call(paramUri.getAuthority(), paramString1, paramString2, paramBundle);
  }
  
  public final Bundle call(String paramString1, String paramString2, String paramString3, Bundle paramBundle) {
    Objects.requireNonNull(paramString1, "authority");
    Objects.requireNonNull(paramString2, "method");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.call(paramString1, paramString2, paramString3, paramBundle); 
      IContentProvider iContentProvider = acquireProvider(paramString1);
      if (iContentProvider != null)
        try {
          Bundle bundle = iContentProvider.call(this.mPackageName, this.mAttributionTag, paramString1, paramString2, paramString3, paramBundle);
          Bundle.setDefusable(bundle, true);
          return bundle;
        } catch (RemoteException remoteException) {
          return null;
        } finally {
          releaseProvider(iContentProvider);
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown authority ");
      stringBuilder.append(paramString1);
      throw new IllegalArgumentException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  @Deprecated
  public void cancelSync(Uri paramUri) {
    if (paramUri != null) {
      String str = paramUri.getAuthority();
    } else {
      paramUri = null;
    } 
    cancelSync(null, (String)paramUri);
  }
  
  public final Uri canonicalize(Uri paramUri) {
    Objects.requireNonNull(paramUri, "url");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.canonicalize(paramUri); 
      IContentProvider iContentProvider = acquireProvider(paramUri);
      if (iContentProvider == null)
        return null; 
      try {
        UriResultListener uriResultListener = new UriResultListener();
        this();
        String str1 = this.mPackageName;
        String str2 = this.mAttributionTag;
        RemoteCallback remoteCallback = new RemoteCallback();
        this(uriResultListener);
        iContentProvider.canonicalizeAsync(str1, str2, paramUri, remoteCallback);
        uriResultListener.waitForResult(3000L);
        if (uriResultListener.exception == null) {
          paramUri = uriResultListener.result;
          return paramUri;
        } 
        throw uriResultListener.exception;
      } catch (RemoteException remoteException) {
        return null;
      } finally {
        releaseProvider(iContentProvider);
      } 
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final Uri canonicalizeOrElse(Uri paramUri) {
    Uri uri = canonicalize(paramUri);
    if (uri != null)
      paramUri = uri; 
    return paramUri;
  }
  
  @SystemApi
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2) {
    Objects.requireNonNull(paramUri, "uri");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.checkUriPermission(paramUri, paramInt1, paramInt2); 
      try {
        ContentProviderClient contentProviderClient = acquireUnstableContentProviderClient(paramUri);
        try {
          paramInt1 = contentProviderClient.checkUriPermission(paramUri, paramInt1, paramInt2);
          return paramInt1;
        } finally {
          if (contentProviderClient != null)
            try {
              contentProviderClient.close();
            } finally {
              contentProviderClient = null;
            }  
        } 
      } catch (RemoteException remoteException) {
        return -1;
      } 
    } catch (RemoteException remoteException) {
      return -1;
    } 
  }
  
  public final int delete(Uri paramUri, Bundle paramBundle) {
    Objects.requireNonNull(paramUri, "url");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.delete(paramUri, paramBundle); 
      IContentProvider iContentProvider = acquireProvider(paramUri);
      if (iContentProvider != null)
        try {
          long l = SystemClock.uptimeMillis();
          int i = iContentProvider.delete(this.mPackageName, this.mAttributionTag, paramUri, paramBundle);
          maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, paramUri, "delete", null);
          return i;
        } catch (RemoteException remoteException) {
          return -1;
        } finally {
          releaseProvider(iContentProvider);
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown URL ");
      stringBuilder.append(paramUri);
      throw new IllegalArgumentException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      return 0;
    } 
  }
  
  public final int delete(Uri paramUri, String paramString, String[] paramArrayOfString) {
    return delete(paramUri, createSqlQueryBundle(paramString, paramArrayOfString));
  }
  
  public String getAttributionTag() {
    return this.mAttributionTag;
  }
  
  @SystemApi
  public Bundle getCache(Uri paramUri) {
    try {
      Bundle bundle = getContentService().getCache(this.mContext.getPackageName(), paramUri, this.mContext.getUserId());
      if (bundle != null)
        bundle.setClassLoader(this.mContext.getClassLoader()); 
      return bundle;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<UriPermission> getOutgoingPersistedUriPermissions() {
    try {
      return UriGrantsManager.getService().getUriPermissions(this.mPackageName, false, true).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<UriPermission> getOutgoingUriPermissions() {
    try {
      return UriGrantsManager.getService().getUriPermissions(this.mPackageName, false, false).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public List<UriPermission> getPersistedUriPermissions() {
    try {
      return UriGrantsManager.getService().getUriPermissions(this.mPackageName, true, true).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public OpenResourceIdResult getResourceId(Uri paramUri) throws FileNotFoundException {
    OpenResourceIdResult openResourceIdResult;
    String str = paramUri.getAuthority();
    if (!TextUtils.isEmpty(str))
      try {
        Resources resources = this.mContext.getPackageManager().getResourcesForApplication(str);
        List<String> list = paramUri.getPathSegments();
        if (list != null) {
          int i = list.size();
          if (i == 1) {
            try {
              i = Integer.parseInt(list.get(0));
            } catch (NumberFormatException numberFormatException) {
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("Single path segment is not a resource ID: ");
              stringBuilder2.append(paramUri);
              throw new FileNotFoundException(stringBuilder2.toString());
            } 
          } else if (i == 2) {
            i = stringBuilder2.getIdentifier(list.get(1), list.get(0), str);
          } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("More than two path segments: ");
            stringBuilder2.append(paramUri);
            throw new FileNotFoundException(stringBuilder2.toString());
          } 
          if (i != 0) {
            openResourceIdResult = new OpenResourceIdResult();
            openResourceIdResult.r = (Resources)stringBuilder2;
            openResourceIdResult.id = i;
            return openResourceIdResult;
          } 
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("No resource found for: ");
          stringBuilder2.append(openResourceIdResult);
          throw new FileNotFoundException(stringBuilder2.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("No path: ");
        stringBuilder1.append(openResourceIdResult);
        throw new FileNotFoundException(stringBuilder1.toString());
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("No package found for authority: ");
        stringBuilder1.append(openResourceIdResult);
        throw new FileNotFoundException(stringBuilder1.toString());
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No authority: ");
    stringBuilder.append(openResourceIdResult);
    throw new FileNotFoundException(stringBuilder.toString());
  }
  
  public String[] getStreamTypes(Uri paramUri, String paramString) {
    Objects.requireNonNull(paramUri, "url");
    Objects.requireNonNull(paramString, "mimeTypeFilter");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.getStreamTypes(paramUri, paramString); 
      IContentProvider iContentProvider = acquireProvider(paramUri);
      if (iContentProvider == null)
        return null; 
      try {
        return iContentProvider.getStreamTypes(paramUri, paramString);
      } catch (RemoteException remoteException) {
        return null;
      } finally {
        releaseProvider(iContentProvider);
      } 
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public int getTargetSdkVersion() {
    return this.mTargetSdkVersion;
  }
  
  public final String getType(Uri paramUri) {
    Objects.requireNonNull(paramUri, "url");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.getType(paramUri); 
      IContentProvider iContentProvider = acquireExistingProvider(paramUri);
      if (iContentProvider != null)
        try {
          String str;
          StringResultListener stringResultListener = new StringResultListener();
          this();
          RemoteCallback remoteCallback = new RemoteCallback();
          this(stringResultListener);
          iContentProvider.getTypeAsync(paramUri, remoteCallback);
          stringResultListener.waitForResult(3000L);
          if (stringResultListener.exception == null) {
            str = stringResultListener.result;
            return str;
          } 
          throw str.exception;
        } catch (RemoteException null) {
          return null;
        } catch (Exception exception) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Failed to get type for: ");
          stringBuilder.append(null);
          stringBuilder.append(" (");
          stringBuilder.append(exception.getMessage());
          stringBuilder.append(")");
          Log.w("ContentResolver", stringBuilder.toString());
          return null;
        } finally {
          releaseProvider(iContentProvider);
        }  
      if (!"content".equals(paramUri.getScheme()))
        return null; 
      try {
        StringResultListener stringResultListener = new StringResultListener();
        this();
        IActivityManager iActivityManager = ActivityManager.getService();
        Uri uri = ContentProvider.getUriWithoutUserId(paramUri);
        int i = resolveUserId(paramUri);
        RemoteCallback remoteCallback = new RemoteCallback();
        this(stringResultListener);
        iActivityManager.getProviderMimeTypeAsync(uri, i, remoteCallback);
        stringResultListener.waitForResult(23000L);
        if (stringResultListener.exception == null)
          return stringResultListener.result; 
        throw stringResultListener.exception;
      } catch (RemoteException remoteException) {
        return null;
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to get type for: ");
        stringBuilder.append(remoteException);
        stringBuilder.append(" (");
        stringBuilder.append(exception.getMessage());
        stringBuilder.append(")");
        Log.w("ContentResolver", stringBuilder.toString());
        return null;
      } 
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  @Deprecated
  public Drawable getTypeDrawable(String paramString) {
    return getTypeInfo(paramString).getIcon().loadDrawable(this.mContext);
  }
  
  public final MimeTypeInfo getTypeInfo(String paramString) {
    Objects.requireNonNull(paramString);
    return MimeIconUtils.getTypeInfo(paramString);
  }
  
  public int getUserId() {
    return this.mContext.getUserId();
  }
  
  public final Uri insert(Uri paramUri, ContentValues paramContentValues) {
    return insert(paramUri, paramContentValues, null);
  }
  
  public final Uri insert(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) {
    Objects.requireNonNull(paramUri, "url");
    try {
      if (this.mWrapped != null) {
        ContentInterface contentInterface = this.mWrapped;
        try {
          return contentInterface.insert(paramUri, paramContentValues, paramBundle);
        } catch (RemoteException remoteException) {}
      } else {
        IContentProvider iContentProvider = acquireProvider((Uri)remoteException);
        if (iContentProvider != null)
          try {
            long l = SystemClock.uptimeMillis();
            Uri uri = iContentProvider.insert(this.mPackageName, this.mAttributionTag, (Uri)remoteException, paramContentValues, paramBundle);
            maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, (Uri)remoteException, "insert", null);
            return uri;
          } catch (RemoteException remoteException1) {
            return null;
          } finally {
            releaseProvider(iContentProvider);
          }  
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown URL ");
        stringBuilder.append(remoteException);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  public Bitmap loadThumbnail(Uri paramUri, Size paramSize, CancellationSignal paramCancellationSignal) throws IOException {
    return loadThumbnail(this, paramUri, paramSize, paramCancellationSignal, 1);
  }
  
  public void notifyChange(Uri paramUri, ContentObserver paramContentObserver) {
    notifyChange(paramUri, paramContentObserver, true);
  }
  
  public void notifyChange(Uri paramUri, ContentObserver paramContentObserver, int paramInt) {
    Objects.requireNonNull(paramUri, "uri");
    notifyChange(ContentProvider.getUriWithoutUserId(paramUri), paramContentObserver, paramInt, ContentProvider.getUserIdFromUri(paramUri, this.mContext.getUserId()));
  }
  
  public void notifyChange(Uri paramUri, ContentObserver paramContentObserver, int paramInt1, int paramInt2) {
    notifyChange(new Uri[] { paramUri }, paramContentObserver, paramInt1, paramInt2);
  }
  
  @Deprecated
  public void notifyChange(Uri paramUri, ContentObserver paramContentObserver, boolean paramBoolean) {
    notifyChange(paramUri, paramContentObserver, paramBoolean);
  }
  
  @Deprecated
  public void notifyChange(Uri paramUri, ContentObserver paramContentObserver, boolean paramBoolean, int paramInt) {
    notifyChange(paramUri, paramContentObserver, paramBoolean, paramInt);
  }
  
  @Deprecated
  public void notifyChange(Iterable<Uri> paramIterable, ContentObserver paramContentObserver, int paramInt) {
    ArrayList<Uri> arrayList = new ArrayList();
    Objects.requireNonNull(arrayList);
    paramIterable.forEach(new _$$Lambda$TxJXFacN6KB_OTXom31IyFcSl48(arrayList));
    notifyChange(arrayList, paramContentObserver, paramInt);
  }
  
  public void notifyChange(Collection<Uri> paramCollection, ContentObserver paramContentObserver, int paramInt) {
    Objects.requireNonNull(paramCollection, "uris");
    SparseArray sparseArray = new SparseArray();
    for (Uri uri : paramCollection) {
      int i = ContentProvider.getUserIdFromUri(uri, this.mContext.getUserId());
      ArrayList<Uri> arrayList = (ArrayList)sparseArray.get(i);
      paramCollection = arrayList;
      if (arrayList == null) {
        paramCollection = new ArrayList<>();
        sparseArray.put(i, paramCollection);
      } 
      paramCollection.add(ContentProvider.getUriWithoutUserId(uri));
    } 
    for (byte b = 0; b < sparseArray.size(); b++) {
      int i = sparseArray.keyAt(b);
      paramCollection = (ArrayList)sparseArray.valueAt(b);
      notifyChange(paramCollection.<Uri>toArray(new Uri[paramCollection.size()]), paramContentObserver, paramInt, i);
    } 
  }
  
  public void notifyChange(Uri[] paramArrayOfUri, ContentObserver paramContentObserver, int paramInt1, int paramInt2) {
    try {
      IContentObserver iContentObserver;
      boolean bool;
      IContentService iContentService = getContentService();
      if (paramContentObserver == null) {
        iContentObserver = null;
      } else {
        iContentObserver = paramContentObserver.getContentObserver();
      } 
      if (paramContentObserver != null && paramContentObserver.deliverSelfNotifications()) {
        bool = true;
      } else {
        bool = false;
      } 
      iContentService.notifyChange(paramArrayOfUri, iContentObserver, bool, paramInt1, paramInt2, this.mTargetSdkVersion, this.mContext.getPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public final AssetFileDescriptor openAssetFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    try {
      return (this.mWrapped != null) ? this.mWrapped.openAssetFile(paramUri, paramString, paramCancellationSignal) : openAssetFileDescriptor(paramUri, paramString, paramCancellationSignal);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final AssetFileDescriptor openAssetFileDescriptor(Uri paramUri, String paramString) throws FileNotFoundException {
    return openAssetFileDescriptor(paramUri, paramString, null);
  }
  
  public final AssetFileDescriptor openAssetFileDescriptor(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    Objects.requireNonNull(paramUri, "uri");
    Objects.requireNonNull(paramString, "mode");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.openAssetFile(paramUri, paramString, paramCancellationSignal); 
      String str = paramUri.getScheme();
      if ("android.resource".equals(str)) {
        if ("r".equals(paramString)) {
          OpenResourceIdResult openResourceIdResult = getResourceId(paramUri);
          try {
            return openResourceIdResult.r.openRawResourceFd(openResourceIdResult.id);
          } catch (android.content.res.Resources.NotFoundException notFoundException) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Resource does not exist: ");
            stringBuilder1.append(paramUri);
            throw new FileNotFoundException(stringBuilder1.toString());
          } 
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Can't write resources: ");
        stringBuilder.append(paramUri);
        throw new FileNotFoundException(stringBuilder.toString());
      } 
      if ("file".equals(str))
        return new AssetFileDescriptor(ParcelFileDescriptor.open(new File(paramUri.getPath()), ParcelFileDescriptor.parseMode((String)stringBuilder)), 0L, -1L); 
      if ("r".equals(stringBuilder))
        return openTypedAssetFileDescriptor(paramUri, "*/*", null, paramCancellationSignal); 
      IContentProvider iContentProvider = acquireUnstableProvider(paramUri);
      if (iContentProvider != null) {
        AssetFileDescriptor assetFileDescriptor;
        IContentProvider iContentProvider1 = null;
        IContentProvider iContentProvider2 = null;
        Object object = null;
        String str1 = null;
        if (paramCancellationSignal != null) {
          IContentProvider iContentProvider9 = iContentProvider;
          str = str1;
          IContentProvider iContentProvider10 = iContentProvider;
          IContentProvider iContentProvider11 = iContentProvider1;
          IContentProvider iContentProvider12 = iContentProvider;
          IContentProvider iContentProvider13 = iContentProvider2;
          try {
            paramCancellationSignal.throwIfCanceled();
            iContentProvider9 = iContentProvider;
            str = str1;
            iContentProvider10 = iContentProvider;
            iContentProvider11 = iContentProvider1;
            iContentProvider12 = iContentProvider;
            iContentProvider13 = iContentProvider2;
            assetFileDescriptor = (AssetFileDescriptor)iContentProvider.createCancellationSignal();
            iContentProvider9 = iContentProvider;
            str = str1;
            iContentProvider10 = iContentProvider;
            iContentProvider11 = iContentProvider1;
            iContentProvider12 = iContentProvider;
            iContentProvider13 = iContentProvider2;
            paramCancellationSignal.setRemote((ICancellationSignal)assetFileDescriptor);
            iContentProvider9 = iContentProvider;
            str = str1;
            iContentProvider10 = iContentProvider;
            iContentProvider11 = iContentProvider1;
            iContentProvider12 = iContentProvider;
            iContentProvider13 = iContentProvider2;
          } catch (RemoteException remoteException) {
          
          } catch (FileNotFoundException fileNotFoundException) {
          
          } finally {}
        } else {
          assetFileDescriptor = null;
          IContentProvider iContentProvider9 = iContentProvider;
          str = str1;
          IContentProvider iContentProvider10 = iContentProvider;
          IContentProvider iContentProvider11 = iContentProvider1;
          IContentProvider iContentProvider12 = iContentProvider;
          IContentProvider iContentProvider13 = iContentProvider2;
        } 
        unstableProviderDied(iContentProvider);
        IContentProvider iContentProvider4 = iContentProvider;
        str = str1;
        IContentProvider iContentProvider5 = iContentProvider;
        IContentProvider iContentProvider6 = iContentProvider1;
        IContentProvider iContentProvider7 = iContentProvider;
        IContentProvider iContentProvider8 = iContentProvider2;
        IContentProvider iContentProvider3 = acquireProvider(paramUri);
        if (iContentProvider3 != null) {
          iContentProvider4 = iContentProvider;
          IContentProvider iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          assetFileDescriptor = iContentProvider3.openAssetFile(this.mPackageName, this.mAttributionTag, paramUri, (String)remoteException, (ICancellationSignal)assetFileDescriptor);
          if (assetFileDescriptor == null) {
            if (paramCancellationSignal != null)
              paramCancellationSignal.setRemote(null); 
            if (iContentProvider3 != null)
              releaseProvider(iContentProvider3); 
            if (iContentProvider != null)
              releaseUnstableProvider(iContentProvider); 
            return null;
          } 
          iContentProvider8 = iContentProvider3;
        } else {
          iContentProvider4 = iContentProvider;
          IContentProvider iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          FileNotFoundException fileNotFoundException = new FileNotFoundException();
          iContentProvider4 = iContentProvider;
          iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          StringBuilder stringBuilder1 = new StringBuilder();
          iContentProvider4 = iContentProvider;
          iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          this();
          iContentProvider4 = iContentProvider;
          iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          stringBuilder1.append("No content provider: ");
          iContentProvider4 = iContentProvider;
          iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          stringBuilder1.append(paramUri);
          iContentProvider4 = iContentProvider;
          iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          this(stringBuilder1.toString());
          iContentProvider4 = iContentProvider;
          iContentProvider9 = iContentProvider3;
          iContentProvider5 = iContentProvider;
          iContentProvider6 = iContentProvider3;
          iContentProvider7 = iContentProvider;
          iContentProvider8 = iContentProvider3;
          throw fileNotFoundException;
        } 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No content provider: ");
      stringBuilder.append(paramUri);
      throw new FileNotFoundException(stringBuilder.toString());
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final ParcelFileDescriptor openFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    try {
      return (this.mWrapped != null) ? this.mWrapped.openFile(paramUri, paramString, paramCancellationSignal) : openFileDescriptor(paramUri, paramString, paramCancellationSignal);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final ParcelFileDescriptor openFileDescriptor(Uri paramUri, String paramString) throws FileNotFoundException {
    return openFileDescriptor(paramUri, paramString, null);
  }
  
  public final ParcelFileDescriptor openFileDescriptor(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    try {
      if (this.mWrapped != null)
        return this.mWrapped.openFile(paramUri, paramString, paramCancellationSignal); 
      AssetFileDescriptor assetFileDescriptor = openAssetFileDescriptor(paramUri, paramString, paramCancellationSignal);
      if (assetFileDescriptor == null)
        return null; 
      if (assetFileDescriptor.getDeclaredLength() < 0L)
        return assetFileDescriptor.getParcelFileDescriptor(); 
      try {
        assetFileDescriptor.close();
      } catch (IOException iOException) {}
      throw new FileNotFoundException("Not a whole file");
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final InputStream openInputStream(Uri paramUri) throws FileNotFoundException {
    Objects.requireNonNull(paramUri, "uri");
    String str = paramUri.getScheme();
    if ("android.resource".equals(str)) {
      OpenResourceIdResult openResourceIdResult = getResourceId(paramUri);
      try {
        return openResourceIdResult.r.openRawResource(openResourceIdResult.id);
      } catch (android.content.res.Resources.NotFoundException notFoundException) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Resource does not exist: ");
        stringBuilder2.append(paramUri);
        throw new FileNotFoundException(stringBuilder2.toString());
      } 
    } 
    if ("file".equals(stringBuilder2))
      return new FileInputStream(paramUri.getPath()); 
    StringBuilder stringBuilder2 = null;
    AssetFileDescriptor assetFileDescriptor = openAssetFileDescriptor(paramUri, "r", null);
    StringBuilder stringBuilder1 = stringBuilder2;
    if (assetFileDescriptor != null)
      try {
        FileInputStream fileInputStream = assetFileDescriptor.createInputStream();
      } catch (IOException iOException) {
        throw new FileNotFoundException("Unable to create stream");
      }  
    return (InputStream)iOException;
  }
  
  public final OutputStream openOutputStream(Uri paramUri) throws FileNotFoundException {
    return openOutputStream(paramUri, "w");
  }
  
  public final OutputStream openOutputStream(Uri paramUri, String paramString) throws FileNotFoundException {
    Uri uri = null;
    AssetFileDescriptor assetFileDescriptor = openAssetFileDescriptor(paramUri, paramString, null);
    paramUri = uri;
    if (assetFileDescriptor != null)
      try {
        FileOutputStream fileOutputStream = assetFileDescriptor.createOutputStream();
      } catch (IOException iOException) {
        throw new FileNotFoundException("Unable to create stream");
      }  
    return (OutputStream)iOException;
  }
  
  public final AssetFileDescriptor openTypedAssetFile(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    try {
      return (this.mWrapped != null) ? this.mWrapped.openTypedAssetFile(paramUri, paramString, paramBundle, paramCancellationSignal) : openTypedAssetFileDescriptor(paramUri, paramString, paramBundle, paramCancellationSignal);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri paramUri, String paramString, Bundle paramBundle) throws FileNotFoundException {
    return openTypedAssetFileDescriptor(paramUri, paramString, paramBundle, null);
  }
  
  public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws FileNotFoundException {
    Objects.requireNonNull(paramUri, "uri");
    Objects.requireNonNull(paramString, "mimeType");
    try {
      if (this.mWrapped != null) {
        ContentInterface contentInterface = this.mWrapped;
        try {
          return contentInterface.openTypedAssetFile(paramUri, paramString, paramBundle, paramCancellationSignal);
        } catch (RemoteException remoteException) {}
      } else {
        IContentProvider iContentProvider = acquireUnstableProvider((Uri)remoteException);
        if (iContentProvider != null) {
          ICancellationSignal iCancellationSignal;
          Object object7 = null;
          RemoteException remoteException2 = null;
          AssetFileDescriptor assetFileDescriptor = null;
          RemoteException remoteException3 = null;
          Object object8 = null;
          Object object9 = null;
          if (paramCancellationSignal != null) {
            Object object10 = object9;
            IContentProvider iContentProvider1 = iContentProvider;
            Object object11 = object7;
            IContentProvider iContentProvider2 = iContentProvider;
            RemoteException remoteException4 = remoteException2;
            IContentProvider iContentProvider3 = iContentProvider;
            try {
              paramCancellationSignal.throwIfCanceled();
              object10 = object9;
              iContentProvider1 = iContentProvider;
              object11 = object7;
              iContentProvider2 = iContentProvider;
              remoteException4 = remoteException2;
              iContentProvider3 = iContentProvider;
              ICancellationSignal iCancellationSignal1 = iContentProvider.createCancellationSignal();
              object10 = object9;
              iContentProvider1 = iContentProvider;
              object11 = object7;
              iContentProvider2 = iContentProvider;
              remoteException4 = remoteException2;
              iContentProvider3 = iContentProvider;
              paramCancellationSignal.setRemote(iCancellationSignal1);
            } catch (RemoteException remoteException1) {
              remoteException1 = remoteException4;
            } catch (FileNotFoundException fileNotFoundException) {
              object10 = object11;
            } finally {
              remoteException = null;
            } 
          } else {
            iCancellationSignal = null;
          } 
          try {
            String str2 = this.mPackageName;
            String str1 = this.mAttributionTag;
          } catch (DeadObjectException deadObjectException) {
          
          } catch (RemoteException remoteException4) {
          
          } catch (FileNotFoundException fileNotFoundException) {
          
          } finally {
            Object object;
            remoteException = null;
          } 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No content provider: ");
          stringBuilder.append(remoteException);
          throw new FileNotFoundException(stringBuilder.toString());
        } 
        Object object6 = null;
        Object object2 = null;
        Object object1 = null;
        Object object4 = object1;
        Object object3 = object6;
        Object object5 = object2;
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  @SystemApi
  public void putCache(Uri paramUri, Bundle paramBundle) {
    try {
      getContentService().putCache(this.mContext.getPackageName(), paramUri, paramBundle, this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public final Cursor query(Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, CancellationSignal paramCancellationSignal) {
    Objects.requireNonNull(paramUri, "uri");
    try {
      ContentInterface contentInterface = this.mWrapped;
      if (contentInterface != null) {
        try {
          contentInterface = this.mWrapped;
          return contentInterface.query(paramUri, paramArrayOfString, paramBundle, paramCancellationSignal);
        } catch (RemoteException remoteException) {}
      } else {
        Cursor cursor1;
        IContentProvider iContentProvider1 = acquireUnstableProvider((Uri)remoteException);
        if (iContentProvider1 == null)
          return null; 
        contentInterface = null;
        IContentProvider iContentProvider2 = null;
        RemoteException remoteException1 = null;
        Cursor cursor2 = null;
        String[] arrayOfString = null;
        Bundle bundle = null;
        Cursor cursor3 = cursor2;
        IContentProvider iContentProvider3 = iContentProvider2;
        try {
          DeadObjectException deadObjectException;
          ICancellationSignal iCancellationSignal;
          long l = SystemClock.uptimeMillis();
          if (paramCancellationSignal != null) {
            try {
              paramCancellationSignal.throwIfCanceled();
              iCancellationSignal = iContentProvider1.createCancellationSignal();
              paramCancellationSignal.setRemote(iCancellationSignal);
              try {
                cursor3 = iContentProvider1.query(this.mPackageName, this.mAttributionTag, (Uri)remoteException, paramArrayOfString, paramBundle, iCancellationSignal);
                contentInterface = null;
              } catch (DeadObjectException deadObjectException1) {
                cursor3 = cursor2;
                iContentProvider3 = iContentProvider2;
              } 
            } catch (RemoteException remoteException2) {
            
            } finally {
              remoteException = null;
              deadObjectException = deadObjectException1;
            } 
          } else {
            iCancellationSignal = null;
            try {
              cursor3 = iContentProvider1.query(this.mPackageName, this.mAttributionTag, (Uri)remoteException, (String[])deadObjectException, paramBundle, iCancellationSignal);
              contentInterface = null;
            } catch (DeadObjectException deadObjectException1) {
              cursor3 = cursor2;
              iContentProvider3 = iContentProvider2;
            } 
          } 
          unstableProviderDied(iContentProvider1);
          cursor3 = cursor2;
          iContentProvider3 = iContentProvider2;
        } catch (RemoteException remoteException2) {
        
        } finally {
          paramBundle = bundle;
          cursor1 = cursor3;
          if (paramBundle != null)
            paramBundle.close(); 
          if (paramCancellationSignal != null)
            paramCancellationSignal.setRemote(null); 
          if (iContentProvider1 != null)
            releaseUnstableProvider(iContentProvider1); 
          if (cursor1 != null)
            releaseProvider((IContentProvider)cursor1); 
        } 
        if (cursor1 != null)
          cursor1.close(); 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  public final Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    return query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, null);
  }
  
  public final Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal) {
    return query(paramUri, paramArrayOfString1, createSqlQueryBundle(paramString1, paramArrayOfString2, paramString2), paramCancellationSignal);
  }
  
  public final boolean refresh(Uri paramUri, Bundle paramBundle, CancellationSignal paramCancellationSignal) {
    Objects.requireNonNull(paramUri, "url");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.refresh(paramUri, paramBundle, paramCancellationSignal); 
      IContentProvider iContentProvider = acquireProvider(paramUri);
      if (iContentProvider == null)
        return false; 
      if (paramCancellationSignal != null)
        try {
          paramCancellationSignal.throwIfCanceled();
          ICancellationSignal iCancellationSignal2 = iContentProvider.createCancellationSignal();
          paramCancellationSignal.setRemote(iCancellationSignal2);
          ICancellationSignal iCancellationSignal1 = iCancellationSignal2;
          return iContentProvider.refresh(this.mPackageName, this.mAttributionTag, paramUri, paramBundle, iCancellationSignal1);
        } catch (RemoteException remoteException) {
          return false;
        } finally {
          releaseProvider(iContentProvider);
        }  
      paramCancellationSignal = null;
      boolean bool = iContentProvider.refresh(this.mPackageName, this.mAttributionTag, paramUri, paramBundle, (ICancellationSignal)paramCancellationSignal);
      releaseProvider(iContentProvider);
      return bool;
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public final void registerContentObserver(Uri paramUri, boolean paramBoolean, ContentObserver paramContentObserver) {
    Objects.requireNonNull(paramUri, "uri");
    Objects.requireNonNull(paramContentObserver, "observer");
    registerContentObserver(ContentProvider.getUriWithoutUserId(paramUri), paramBoolean, paramContentObserver, ContentProvider.getUserIdFromUri(paramUri, this.mContext.getUserId()));
  }
  
  public final void registerContentObserver(Uri paramUri, boolean paramBoolean, ContentObserver paramContentObserver, int paramInt) {
    try {
      getContentService().registerContentObserver(paramUri, paramBoolean, paramContentObserver.getContentObserver(), paramInt, this.mTargetSdkVersion);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void releasePersistableUriPermission(Uri paramUri, int paramInt) {
    Objects.requireNonNull(paramUri, "uri");
    try {
      UriGrantsManager.getService().releasePersistableUriPermission(ContentProvider.getUriWithoutUserId(paramUri), paramInt, null, resolveUserId(paramUri));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public abstract boolean releaseProvider(IContentProvider paramIContentProvider);
  
  public abstract boolean releaseUnstableProvider(IContentProvider paramIContentProvider);
  
  public int resolveUserId(Uri paramUri) {
    return ContentProvider.getUserIdFromUri(paramUri, this.mContext.getUserId());
  }
  
  @Deprecated
  public void startSync(Uri paramUri, Bundle paramBundle) {
    Account account1 = null;
    Account account2 = null;
    if (paramBundle != null) {
      String str = paramBundle.getString("account");
      account1 = account2;
      if (!TextUtils.isEmpty(str))
        account1 = new Account(str, "com.google"); 
      paramBundle.remove("account");
    } 
    if (paramUri != null) {
      String str = paramUri.getAuthority();
    } else {
      paramUri = null;
    } 
    requestSync(account1, (String)paramUri, paramBundle);
  }
  
  public void takePersistableUriPermission(Uri paramUri, int paramInt) {
    Objects.requireNonNull(paramUri, "uri");
    try {
      UriGrantsManager.getService().takePersistableUriPermission(ContentProvider.getUriWithoutUserId(paramUri), paramInt, null, resolveUserId(paramUri));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void takePersistableUriPermission(String paramString, Uri paramUri, int paramInt) {
    Objects.requireNonNull(paramString, "toPackage");
    Objects.requireNonNull(paramUri, "uri");
    try {
      UriGrantsManager.getService().takePersistableUriPermission(ContentProvider.getUriWithoutUserId(paramUri), paramInt, paramString, resolveUserId(paramUri));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public final Uri uncanonicalize(Uri paramUri) {
    Objects.requireNonNull(paramUri, "url");
    try {
      if (this.mWrapped != null)
        return this.mWrapped.uncanonicalize(paramUri); 
      IContentProvider iContentProvider = acquireProvider(paramUri);
      if (iContentProvider == null)
        return null; 
      try {
        paramUri = iContentProvider.uncanonicalize(this.mPackageName, this.mAttributionTag, paramUri);
        return paramUri;
      } catch (RemoteException remoteException) {
        return null;
      } finally {
        releaseProvider(iContentProvider);
      } 
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final void unregisterContentObserver(ContentObserver paramContentObserver) {
    Objects.requireNonNull(paramContentObserver, "observer");
    try {
      IContentObserver iContentObserver = paramContentObserver.releaseContentObserver();
      if (iContentObserver != null)
        getContentService().unregisterContentObserver(iContentObserver); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public abstract void unstableProviderDied(IContentProvider paramIContentProvider);
  
  public final int update(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) {
    Objects.requireNonNull(paramUri, "uri");
    try {
      if (this.mWrapped != null) {
        ContentInterface contentInterface = this.mWrapped;
        try {
          return contentInterface.update(paramUri, paramContentValues, paramBundle);
        } catch (RemoteException remoteException) {}
      } else {
        IContentProvider iContentProvider = acquireProvider((Uri)remoteException);
        if (iContentProvider != null)
          try {
            long l = SystemClock.uptimeMillis();
            int i = iContentProvider.update(this.mPackageName, this.mAttributionTag, (Uri)remoteException, paramContentValues, paramBundle);
            maybeLogUpdateToEventLog(SystemClock.uptimeMillis() - l, (Uri)remoteException, "update", null);
            return i;
          } catch (RemoteException remoteException1) {
            return -1;
          } finally {
            releaseProvider(iContentProvider);
          }  
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown URI ");
        stringBuilder.append(remoteException);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } catch (RemoteException remoteException) {}
    return 0;
  }
  
  public final int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    return update(paramUri, paramContentValues, createSqlQueryBundle(paramString, paramArrayOfString));
  }
  
  private final class CursorWrapperInner extends CrossProcessCursorWrapper {
    private final CloseGuard mCloseGuard;
    
    private final IContentProvider mContentProvider;
    
    private final AtomicBoolean mProviderReleased = new AtomicBoolean();
    
    CursorWrapperInner(Cursor param1Cursor, IContentProvider param1IContentProvider) {
      super(param1Cursor);
      CloseGuard closeGuard = CloseGuard.get();
      this.mCloseGuard = closeGuard;
      this.mContentProvider = param1IContentProvider;
      closeGuard.open("close");
    }
    
    public void close() {
      this.mCloseGuard.close();
      super.close();
      if (this.mProviderReleased.compareAndSet(false, true))
        ContentResolver.this.releaseProvider(this.mContentProvider); 
    }
    
    protected void finalize() throws Throwable {
      try {
        if (this.mCloseGuard != null)
          this.mCloseGuard.warnIfOpen(); 
        close();
        return;
      } finally {
        super.finalize();
      } 
    }
  }
  
  public static final class MimeTypeInfo {
    private final CharSequence mContentDescription;
    
    private final Icon mIcon;
    
    private final CharSequence mLabel;
    
    public MimeTypeInfo(Icon param1Icon, CharSequence param1CharSequence1, CharSequence param1CharSequence2) {
      Objects.requireNonNull(param1Icon);
      this.mIcon = param1Icon;
      Objects.requireNonNull(param1CharSequence1);
      this.mLabel = param1CharSequence1;
      Objects.requireNonNull(param1CharSequence2);
      this.mContentDescription = param1CharSequence2;
    }
    
    public CharSequence getContentDescription() {
      return this.mContentDescription;
    }
    
    public Icon getIcon() {
      return this.mIcon;
    }
    
    public CharSequence getLabel() {
      return this.mLabel;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface NotifyFlags {}
  
  public class OpenResourceIdResult {
    public int id;
    
    public Resources r;
  }
  
  private final class ParcelFileDescriptorInner extends ParcelFileDescriptor {
    private final IContentProvider mContentProvider;
    
    private final AtomicBoolean mProviderReleased = new AtomicBoolean();
    
    ParcelFileDescriptorInner(ParcelFileDescriptor param1ParcelFileDescriptor, IContentProvider param1IContentProvider) {
      super(param1ParcelFileDescriptor);
      this.mContentProvider = param1IContentProvider;
    }
    
    public void releaseResources() {
      if (this.mProviderReleased.compareAndSet(false, true))
        ContentResolver.this.releaseProvider(this.mContentProvider); 
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface QueryCollator {}
  
  private static abstract class ResultListener<T> implements RemoteCallback.OnResultListener {
    public boolean done;
    
    public RuntimeException exception;
    
    public T result;
    
    private ResultListener() {}
    
    protected abstract T getResultFromBundle(Bundle param1Bundle);
    
    public void onResult(Bundle param1Bundle) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_1
      //   3: ldc 'error'
      //   5: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
      //   8: checkcast android/os/ParcelableException
      //   11: astore_2
      //   12: aload_2
      //   13: ifnull -> 56
      //   16: aload_2
      //   17: invokevirtual getCause : ()Ljava/lang/Throwable;
      //   20: astore_1
      //   21: aload_1
      //   22: instanceof java/lang/RuntimeException
      //   25: ifeq -> 39
      //   28: aload_0
      //   29: aload_1
      //   30: checkcast java/lang/RuntimeException
      //   33: putfield exception : Ljava/lang/RuntimeException;
      //   36: goto -> 53
      //   39: new java/lang/RuntimeException
      //   42: astore_2
      //   43: aload_2
      //   44: aload_1
      //   45: invokespecial <init> : (Ljava/lang/Throwable;)V
      //   48: aload_0
      //   49: aload_2
      //   50: putfield exception : Ljava/lang/RuntimeException;
      //   53: goto -> 65
      //   56: aload_0
      //   57: aload_0
      //   58: aload_1
      //   59: invokevirtual getResultFromBundle : (Landroid/os/Bundle;)Ljava/lang/Object;
      //   62: putfield result : Ljava/lang/Object;
      //   65: aload_0
      //   66: iconst_1
      //   67: putfield done : Z
      //   70: aload_0
      //   71: invokevirtual notifyAll : ()V
      //   74: aload_0
      //   75: monitorexit
      //   76: return
      //   77: astore_1
      //   78: aload_0
      //   79: monitorexit
      //   80: aload_1
      //   81: athrow
      // Exception table:
      //   from	to	target	type
      //   2	12	77	finally
      //   16	36	77	finally
      //   39	53	77	finally
      //   56	65	77	finally
      //   65	76	77	finally
      //   78	80	77	finally
    }
    
    public void waitForResult(long param1Long) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield done : Z
      //   6: istore_3
      //   7: iload_3
      //   8: ifne -> 21
      //   11: aload_0
      //   12: lload_1
      //   13: invokevirtual wait : (J)V
      //   16: goto -> 21
      //   19: astore #4
      //   21: aload_0
      //   22: monitorexit
      //   23: return
      //   24: astore #4
      //   26: aload_0
      //   27: monitorexit
      //   28: aload #4
      //   30: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	24	finally
      //   11	16	19	java/lang/InterruptedException
      //   11	16	24	finally
      //   21	23	24	finally
      //   26	28	24	finally
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SortDirection {}
  
  private static class StringResultListener extends ResultListener<String> {
    private StringResultListener() {}
    
    protected String getResultFromBundle(Bundle param1Bundle) {
      return param1Bundle.getString("result");
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SyncExemption {}
  
  private static class UriResultListener extends ResultListener<Uri> {
    private UriResultListener() {}
    
    protected Uri getResultFromBundle(Bundle param1Bundle) {
      return (Uri)param1Bundle.getParcelable("result");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */