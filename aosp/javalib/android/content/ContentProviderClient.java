package android.content;

import android.annotation.SystemApi;
import android.content.res.AssetFileDescriptor;
import android.database.CrossProcessCursorWrapper;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import libcore.io.IoUtils;

public class ContentProviderClient implements ContentInterface, AutoCloseable {
  private static final String TAG = "ContentProviderClient";
  
  private static Handler sAnrHandler;
  
  private NotRespondingRunnable mAnrRunnable;
  
  private long mAnrTimeout;
  
  private final String mAttributionTag;
  
  private final String mAuthority;
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final AtomicBoolean mClosed = new AtomicBoolean();
  
  private final IContentProvider mContentProvider;
  
  private final ContentResolver mContentResolver;
  
  private final String mPackageName;
  
  private final boolean mStable;
  
  public ContentProviderClient(ContentResolver paramContentResolver, IContentProvider paramIContentProvider, String paramString, boolean paramBoolean) {
    this.mContentResolver = paramContentResolver;
    this.mContentProvider = paramIContentProvider;
    this.mPackageName = paramContentResolver.mPackageName;
    this.mAttributionTag = paramContentResolver.mAttributionTag;
    this.mAuthority = paramString;
    this.mStable = paramBoolean;
    this.mCloseGuard.open("close");
  }
  
  public ContentProviderClient(ContentResolver paramContentResolver, IContentProvider paramIContentProvider, boolean paramBoolean) {
    this(paramContentResolver, paramIContentProvider, "unknown", paramBoolean);
  }
  
  private void afterRemote() {
    NotRespondingRunnable notRespondingRunnable = this.mAnrRunnable;
    if (notRespondingRunnable != null)
      sAnrHandler.removeCallbacks(notRespondingRunnable); 
  }
  
  private void beforeRemote() {
    NotRespondingRunnable notRespondingRunnable = this.mAnrRunnable;
    if (notRespondingRunnable != null)
      sAnrHandler.postDelayed(notRespondingRunnable, this.mAnrTimeout); 
  }
  
  private boolean closeInternal() {
    this.mCloseGuard.close();
    if (this.mClosed.compareAndSet(false, true)) {
      setDetectNotResponding(0L);
      return this.mStable ? this.mContentResolver.releaseProvider(this.mContentProvider) : this.mContentResolver.releaseUnstableProvider(this.mContentProvider);
    } 
    return false;
  }
  
  @Deprecated
  public static void closeQuietly(ContentProviderClient paramContentProviderClient) {
    IoUtils.closeQuietly(paramContentProviderClient);
  }
  
  @Deprecated
  public static void releaseQuietly(ContentProviderClient paramContentProviderClient) {
    IoUtils.closeQuietly(paramContentProviderClient);
  }
  
  public ContentProviderResult[] applyBatch(String paramString, ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException {
    Objects.requireNonNull(paramArrayList, "operations");
    beforeRemote();
    try {
      ContentProviderResult[] arrayOfContentProviderResult = this.mContentProvider.applyBatch(this.mPackageName, this.mAttributionTag, paramString, paramArrayList);
      afterRemote();
      return arrayOfContentProviderResult;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramString;
  }
  
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException {
    return applyBatch(this.mAuthority, paramArrayList);
  }
  
  public int bulkInsert(Uri paramUri, ContentValues[] paramArrayOfContentValues) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    Objects.requireNonNull(paramArrayOfContentValues, "initialValues");
    beforeRemote();
    try {
      int i = this.mContentProvider.bulkInsert(this.mPackageName, this.mAttributionTag, paramUri, paramArrayOfContentValues);
      afterRemote();
      return i;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public Bundle call(String paramString1, String paramString2, Bundle paramBundle) throws RemoteException {
    return call(this.mAuthority, paramString1, paramString2, paramBundle);
  }
  
  public Bundle call(String paramString1, String paramString2, String paramString3, Bundle paramBundle) throws RemoteException {
    Objects.requireNonNull(paramString1, "authority");
    Objects.requireNonNull(paramString2, "method");
    beforeRemote();
    try {
      Bundle bundle = this.mContentProvider.call(this.mPackageName, this.mAttributionTag, paramString1, paramString2, paramString3, paramBundle);
      afterRemote();
      return bundle;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramString1;
  }
  
  public final Uri canonicalize(Uri paramUri) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    try {
      paramUri = this.mContentProvider.canonicalize(this.mPackageName, this.mAttributionTag, paramUri);
      afterRemote();
      return paramUri;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {
    Objects.requireNonNull(paramUri, "uri");
    beforeRemote();
    try {
      paramInt1 = this.mContentProvider.checkUriPermission(this.mPackageName, this.mAttributionTag, paramUri, paramInt1, paramInt2);
      afterRemote();
      return paramInt1;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public void close() {
    closeInternal();
  }
  
  public int delete(Uri paramUri, Bundle paramBundle) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    try {
      int i = this.mContentProvider.delete(this.mPackageName, this.mAttributionTag, paramUri, paramBundle);
      afterRemote();
      return i;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString) throws RemoteException {
    return delete(paramUri, ContentResolver.createSqlQueryBundle(paramString, paramArrayOfString));
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
  
  public ContentProvider getLocalContentProvider() {
    return ContentProvider.coerceToLocalContentProvider(this.mContentProvider);
  }
  
  public String[] getStreamTypes(Uri paramUri, String paramString) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    Objects.requireNonNull(paramString, "mimeTypeFilter");
    beforeRemote();
    try {
      String[] arrayOfString = this.mContentProvider.getStreamTypes(paramUri, paramString);
      afterRemote();
      return arrayOfString;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public String getType(Uri paramUri) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    try {
      String str = this.mContentProvider.getType(paramUri);
      afterRemote();
      return str;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues) throws RemoteException {
    return insert(paramUri, paramContentValues, null);
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    try {
      paramUri = this.mContentProvider.insert(this.mPackageName, this.mAttributionTag, paramUri, paramContentValues, paramBundle);
      afterRemote();
      return paramUri;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public AssetFileDescriptor openAssetFile(Uri paramUri, String paramString) throws RemoteException, FileNotFoundException {
    return openAssetFile(paramUri, paramString, null);
  }
  
  public AssetFileDescriptor openAssetFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    AssetFileDescriptor assetFileDescriptor;
    Objects.requireNonNull(paramUri, "url");
    Objects.requireNonNull(paramString, "mode");
    beforeRemote();
    ICancellationSignal iCancellationSignal = null;
    if (paramCancellationSignal != null) {
      try {
        paramCancellationSignal.throwIfCanceled();
        iCancellationSignal = this.mContentProvider.createCancellationSignal();
        paramCancellationSignal.setRemote(iCancellationSignal);
        assetFileDescriptor = this.mContentProvider.openAssetFile(this.mPackageName, this.mAttributionTag, paramUri, paramString, iCancellationSignal);
        afterRemote();
        return assetFileDescriptor;
      } catch (DeadObjectException deadObjectException) {
        if (!this.mStable)
          this.mContentResolver.unstableProviderDied(this.mContentProvider); 
        throw deadObjectException;
      } finally {}
    } else {
      assetFileDescriptor = this.mContentProvider.openAssetFile(this.mPackageName, this.mAttributionTag, paramUri, paramString, iCancellationSignal);
      afterRemote();
      return assetFileDescriptor;
    } 
    afterRemote();
    throw assetFileDescriptor;
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString) throws RemoteException, FileNotFoundException {
    return openFile(paramUri, paramString, null);
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    ParcelFileDescriptor parcelFileDescriptor;
    Objects.requireNonNull(paramUri, "url");
    Objects.requireNonNull(paramString, "mode");
    beforeRemote();
    ICancellationSignal iCancellationSignal = null;
    if (paramCancellationSignal != null) {
      try {
        paramCancellationSignal.throwIfCanceled();
        iCancellationSignal = this.mContentProvider.createCancellationSignal();
        paramCancellationSignal.setRemote(iCancellationSignal);
        parcelFileDescriptor = this.mContentProvider.openFile(this.mPackageName, this.mAttributionTag, paramUri, paramString, iCancellationSignal, (IBinder)null);
        afterRemote();
        return parcelFileDescriptor;
      } catch (DeadObjectException deadObjectException) {
        if (!this.mStable)
          this.mContentResolver.unstableProviderDied(this.mContentProvider); 
        throw deadObjectException;
      } finally {}
    } else {
      parcelFileDescriptor = this.mContentProvider.openFile(this.mPackageName, this.mAttributionTag, paramUri, paramString, iCancellationSignal, (IBinder)null);
      afterRemote();
      return parcelFileDescriptor;
    } 
    afterRemote();
    throw parcelFileDescriptor;
  }
  
  public final AssetFileDescriptor openTypedAssetFile(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    AssetFileDescriptor assetFileDescriptor;
    Objects.requireNonNull(paramUri, "uri");
    Objects.requireNonNull(paramString, "mimeTypeFilter");
    beforeRemote();
    ICancellationSignal iCancellationSignal = null;
    if (paramCancellationSignal != null) {
      try {
        paramCancellationSignal.throwIfCanceled();
        iCancellationSignal = this.mContentProvider.createCancellationSignal();
        paramCancellationSignal.setRemote(iCancellationSignal);
        assetFileDescriptor = this.mContentProvider.openTypedAssetFile(this.mPackageName, this.mAttributionTag, paramUri, paramString, paramBundle, iCancellationSignal);
        afterRemote();
        return assetFileDescriptor;
      } catch (DeadObjectException deadObjectException) {
        if (!this.mStable)
          this.mContentResolver.unstableProviderDied(this.mContentProvider); 
        throw deadObjectException;
      } finally {}
    } else {
      assetFileDescriptor = this.mContentProvider.openTypedAssetFile(this.mPackageName, this.mAttributionTag, paramUri, paramString, paramBundle, iCancellationSignal);
      afterRemote();
      return assetFileDescriptor;
    } 
    afterRemote();
    throw assetFileDescriptor;
  }
  
  public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri paramUri, String paramString, Bundle paramBundle) throws RemoteException, FileNotFoundException {
    return openTypedAssetFileDescriptor(paramUri, paramString, paramBundle, null);
  }
  
  public final AssetFileDescriptor openTypedAssetFileDescriptor(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    return openTypedAssetFile(paramUri, paramString, paramBundle, paramCancellationSignal);
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'url'
    //   3: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_0
    //   8: invokespecial beforeRemote : ()V
    //   11: aconst_null
    //   12: astore #5
    //   14: aload #4
    //   16: ifnull -> 42
    //   19: aload #4
    //   21: invokevirtual throwIfCanceled : ()V
    //   24: aload_0
    //   25: getfield mContentProvider : Landroid/content/IContentProvider;
    //   28: invokeinterface createCancellationSignal : ()Landroid/os/ICancellationSignal;
    //   33: astore #5
    //   35: aload #4
    //   37: aload #5
    //   39: invokevirtual setRemote : (Landroid/os/ICancellationSignal;)V
    //   42: aload_0
    //   43: getfield mContentProvider : Landroid/content/IContentProvider;
    //   46: aload_0
    //   47: getfield mPackageName : Ljava/lang/String;
    //   50: aload_0
    //   51: getfield mAttributionTag : Ljava/lang/String;
    //   54: aload_1
    //   55: aload_2
    //   56: aload_3
    //   57: aload #5
    //   59: invokeinterface query : (Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;[Ljava/lang/String;Landroid/os/Bundle;Landroid/os/ICancellationSignal;)Landroid/database/Cursor;
    //   64: astore_1
    //   65: aload_1
    //   66: ifnonnull -> 75
    //   69: aload_0
    //   70: invokespecial afterRemote : ()V
    //   73: aconst_null
    //   74: areturn
    //   75: new android/content/ContentProviderClient$CursorWrapperInner
    //   78: dup
    //   79: aload_0
    //   80: aload_1
    //   81: invokespecial <init> : (Landroid/content/ContentProviderClient;Landroid/database/Cursor;)V
    //   84: astore_1
    //   85: aload_0
    //   86: invokespecial afterRemote : ()V
    //   89: aload_1
    //   90: areturn
    //   91: astore_1
    //   92: goto -> 116
    //   95: astore_1
    //   96: aload_0
    //   97: getfield mStable : Z
    //   100: ifne -> 114
    //   103: aload_0
    //   104: getfield mContentResolver : Landroid/content/ContentResolver;
    //   107: aload_0
    //   108: getfield mContentProvider : Landroid/content/IContentProvider;
    //   111: invokevirtual unstableProviderDied : (Landroid/content/IContentProvider;)V
    //   114: aload_1
    //   115: athrow
    //   116: aload_0
    //   117: invokespecial afterRemote : ()V
    //   120: aload_1
    //   121: athrow
    // Exception table:
    //   from	to	target	type
    //   19	42	95	android/os/DeadObjectException
    //   19	42	91	finally
    //   42	65	95	android/os/DeadObjectException
    //   42	65	91	finally
    //   75	85	95	android/os/DeadObjectException
    //   75	85	91	finally
    //   96	114	91	finally
    //   114	116	91	finally
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) throws RemoteException {
    return query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, null);
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal) throws RemoteException {
    return query(paramUri, paramArrayOfString1, ContentResolver.createSqlQueryBundle(paramString1, paramArrayOfString2, paramString2), paramCancellationSignal);
  }
  
  public boolean refresh(Uri paramUri, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    ICancellationSignal iCancellationSignal = null;
    if (paramCancellationSignal != null) {
      try {
        paramCancellationSignal.throwIfCanceled();
        iCancellationSignal = this.mContentProvider.createCancellationSignal();
        paramCancellationSignal.setRemote(iCancellationSignal);
        boolean bool = this.mContentProvider.refresh(this.mPackageName, this.mAttributionTag, paramUri, paramBundle, iCancellationSignal);
        afterRemote();
        return bool;
      } catch (DeadObjectException deadObjectException) {
        if (!this.mStable)
          this.mContentResolver.unstableProviderDied(this.mContentProvider); 
        throw deadObjectException;
      } finally {}
    } else {
      boolean bool = this.mContentProvider.refresh(this.mPackageName, this.mAttributionTag, paramUri, paramBundle, iCancellationSignal);
      afterRemote();
      return bool;
    } 
    afterRemote();
    throw paramUri;
  }
  
  @Deprecated
  public boolean release() {
    return closeInternal();
  }
  
  @SystemApi
  public void setDetectNotResponding(long paramLong) {
    // Byte code:
    //   0: ldc android/content/ContentProviderClient
    //   2: monitorenter
    //   3: aload_0
    //   4: lload_1
    //   5: putfield mAnrTimeout : J
    //   8: lload_1
    //   9: lconst_0
    //   10: lcmp
    //   11: ifle -> 75
    //   14: aload_0
    //   15: getfield mAnrRunnable : Landroid/content/ContentProviderClient$NotRespondingRunnable;
    //   18: ifnonnull -> 36
    //   21: new android/content/ContentProviderClient$NotRespondingRunnable
    //   24: astore_3
    //   25: aload_3
    //   26: aload_0
    //   27: aconst_null
    //   28: invokespecial <init> : (Landroid/content/ContentProviderClient;Landroid/content/ContentProviderClient$1;)V
    //   31: aload_0
    //   32: aload_3
    //   33: putfield mAnrRunnable : Landroid/content/ContentProviderClient$NotRespondingRunnable;
    //   36: getstatic android/content/ContentProviderClient.sAnrHandler : Landroid/os/Handler;
    //   39: ifnonnull -> 59
    //   42: new android/os/Handler
    //   45: astore_3
    //   46: aload_3
    //   47: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   50: aconst_null
    //   51: iconst_1
    //   52: invokespecial <init> : (Landroid/os/Looper;Landroid/os/Handler$Callback;Z)V
    //   55: aload_3
    //   56: putstatic android/content/ContentProviderClient.sAnrHandler : Landroid/os/Handler;
    //   59: aload_0
    //   60: getfield mContentProvider : Landroid/content/IContentProvider;
    //   63: invokeinterface asBinder : ()Landroid/os/IBinder;
    //   68: invokestatic allowBlocking : (Landroid/os/IBinder;)Landroid/os/IBinder;
    //   71: pop
    //   72: goto -> 93
    //   75: aload_0
    //   76: aconst_null
    //   77: putfield mAnrRunnable : Landroid/content/ContentProviderClient$NotRespondingRunnable;
    //   80: aload_0
    //   81: getfield mContentProvider : Landroid/content/IContentProvider;
    //   84: invokeinterface asBinder : ()Landroid/os/IBinder;
    //   89: invokestatic defaultBlocking : (Landroid/os/IBinder;)Landroid/os/IBinder;
    //   92: pop
    //   93: ldc android/content/ContentProviderClient
    //   95: monitorexit
    //   96: return
    //   97: astore_3
    //   98: ldc android/content/ContentProviderClient
    //   100: monitorexit
    //   101: aload_3
    //   102: athrow
    // Exception table:
    //   from	to	target	type
    //   3	8	97	finally
    //   14	36	97	finally
    //   36	59	97	finally
    //   59	72	97	finally
    //   75	93	97	finally
    //   93	96	97	finally
    //   98	101	97	finally
  }
  
  public final Uri uncanonicalize(Uri paramUri) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    try {
      paramUri = this.mContentProvider.uncanonicalize(this.mPackageName, this.mAttributionTag, paramUri);
      afterRemote();
      return paramUri;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException {
    Objects.requireNonNull(paramUri, "url");
    beforeRemote();
    try {
      int i = this.mContentProvider.update(this.mPackageName, this.mAttributionTag, paramUri, paramContentValues, paramBundle);
      afterRemote();
      return i;
    } catch (DeadObjectException deadObjectException) {
      if (!this.mStable)
        this.mContentResolver.unstableProviderDied(this.mContentProvider); 
      throw deadObjectException;
    } finally {}
    afterRemote();
    throw paramUri;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) throws RemoteException {
    return update(paramUri, paramContentValues, ContentResolver.createSqlQueryBundle(paramString, paramArrayOfString));
  }
  
  private final class CursorWrapperInner extends CrossProcessCursorWrapper {
    private final CloseGuard mCloseGuard;
    
    CursorWrapperInner(Cursor param1Cursor) {
      super(param1Cursor);
      CloseGuard closeGuard = CloseGuard.get();
      this.mCloseGuard = closeGuard;
      closeGuard.open("close");
    }
    
    public void close() {
      this.mCloseGuard.close();
      super.close();
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
  
  private class NotRespondingRunnable implements Runnable {
    private NotRespondingRunnable() {}
    
    public void run() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Detected provider not responding: ");
      stringBuilder.append(ContentProviderClient.this.mContentProvider);
      Log.w("ContentProviderClient", stringBuilder.toString());
      ContentProviderClient.this.mContentResolver.appNotRespondingViaProvider(ContentProviderClient.this.mContentProvider);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */