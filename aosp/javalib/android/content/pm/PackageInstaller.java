package android.content.pm;

import android.annotation.SystemApi;
import android.app.AppGlobals;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.FileBridge;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ParcelableException;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.system.ErrnoException;
import android.system.Os;
import android.util.ArraySet;
import android.util.ExceptionUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

public class PackageInstaller {
  public static final String ACTION_CONFIRM_INSTALL = "android.content.pm.action.CONFIRM_INSTALL";
  
  public static final String ACTION_SESSION_COMMITTED = "android.content.pm.action.SESSION_COMMITTED";
  
  public static final String ACTION_SESSION_DETAILS = "android.content.pm.action.SESSION_DETAILS";
  
  public static final String ACTION_SESSION_UPDATED = "android.content.pm.action.SESSION_UPDATED";
  
  @SystemApi
  public static final int DATA_LOADER_TYPE_INCREMENTAL = 2;
  
  @SystemApi
  public static final int DATA_LOADER_TYPE_NONE = 0;
  
  @SystemApi
  public static final int DATA_LOADER_TYPE_STREAMING = 1;
  
  public static final boolean ENABLE_REVOCABLE_FD = SystemProperties.getBoolean("fw.revocable_fd", false);
  
  public static final String EXTRA_CALLBACK = "android.content.pm.extra.CALLBACK";
  
  @SystemApi
  public static final String EXTRA_DATA_LOADER_TYPE = "android.content.pm.extra.DATA_LOADER_TYPE";
  
  public static final String EXTRA_LEGACY_BUNDLE = "android.content.pm.extra.LEGACY_BUNDLE";
  
  public static final String EXTRA_LEGACY_STATUS = "android.content.pm.extra.LEGACY_STATUS";
  
  public static final String EXTRA_OTHER_PACKAGE_NAME = "android.content.pm.extra.OTHER_PACKAGE_NAME";
  
  public static final String EXTRA_PACKAGE_NAME = "android.content.pm.extra.PACKAGE_NAME";
  
  @Deprecated
  public static final String EXTRA_PACKAGE_NAMES = "android.content.pm.extra.PACKAGE_NAMES";
  
  public static final String EXTRA_SESSION = "android.content.pm.extra.SESSION";
  
  public static final String EXTRA_SESSION_ID = "android.content.pm.extra.SESSION_ID";
  
  public static final String EXTRA_STATUS = "android.content.pm.extra.STATUS";
  
  public static final String EXTRA_STATUS_MESSAGE = "android.content.pm.extra.STATUS_MESSAGE";
  
  public static final String EXTRA_STORAGE_PATH = "android.content.pm.extra.STORAGE_PATH";
  
  @SystemApi
  public static final int LOCATION_DATA_APP = 0;
  
  @SystemApi
  public static final int LOCATION_MEDIA_DATA = 2;
  
  @SystemApi
  public static final int LOCATION_MEDIA_OBB = 1;
  
  public static final int STATUS_FAILURE = 1;
  
  public static final int STATUS_FAILURE_ABORTED = 3;
  
  public static final int STATUS_FAILURE_BLOCKED = 2;
  
  public static final int STATUS_FAILURE_CONFLICT = 5;
  
  public static final int STATUS_FAILURE_INCOMPATIBLE = 7;
  
  public static final int STATUS_FAILURE_INVALID = 4;
  
  public static final int STATUS_FAILURE_STORAGE = 6;
  
  public static final int STATUS_PENDING_STREAMING = -2;
  
  public static final int STATUS_PENDING_USER_ACTION = -1;
  
  public static final int STATUS_SUCCESS = 0;
  
  private static final String TAG = "PackageInstaller";
  
  private final ArrayList<SessionCallbackDelegate> mDelegates = new ArrayList<>();
  
  private final IPackageInstaller mInstaller;
  
  private final String mInstallerPackageName;
  
  private final int mUserId;
  
  public PackageInstaller(IPackageInstaller paramIPackageInstaller, String paramString, int paramInt) {
    this.mInstaller = paramIPackageInstaller;
    this.mInstallerPackageName = paramString;
    this.mUserId = paramInt;
  }
  
  public void abandonSession(int paramInt) {
    try {
      this.mInstaller.abandonSession(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void addSessionCallback(SessionCallback paramSessionCallback) {
    registerSessionCallback(paramSessionCallback);
  }
  
  @Deprecated
  public void addSessionCallback(SessionCallback paramSessionCallback, Handler paramHandler) {
    registerSessionCallback(paramSessionCallback, paramHandler);
  }
  
  public int createSession(SessionParams paramSessionParams) throws IOException {
    try {
      return this.mInstaller.createSession(paramSessionParams, this.mInstallerPackageName, this.mUserId);
    } catch (RuntimeException runtimeException) {
      ExceptionUtils.maybeUnwrapIOException(runtimeException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public SessionInfo getActiveStagedSession() {
    SessionInfo sessionInfo;
    List<SessionInfo> list = getActiveStagedSessions();
    if (list.isEmpty()) {
      list = null;
    } else {
      sessionInfo = list.get(0);
    } 
    return sessionInfo;
  }
  
  public List<SessionInfo> getActiveStagedSessions() {
    ArrayList<SessionInfo> arrayList = new ArrayList();
    List<SessionInfo> list = getStagedSessions();
    for (byte b = 0; b < list.size(); b++) {
      SessionInfo sessionInfo = list.get(b);
      if (sessionInfo.isStagedSessionActive())
        arrayList.add(sessionInfo); 
    } 
    return arrayList;
  }
  
  public List<SessionInfo> getAllSessions() {
    try {
      return this.mInstaller.getAllSessions(this.mUserId).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<SessionInfo> getMySessions() {
    try {
      return this.mInstaller.getMySessions(this.mInstallerPackageName, this.mUserId).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public SessionInfo getSessionInfo(int paramInt) {
    try {
      return this.mInstaller.getSessionInfo(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<SessionInfo> getStagedSessions() {
    try {
      return this.mInstaller.getStagedSessions().getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void installExistingPackage(String paramString, int paramInt, IntentSender paramIntentSender) {
    Objects.requireNonNull(paramString, "packageName cannot be null");
    try {
      this.mInstaller.installExistingPackage(paramString, 4194304, paramInt, paramIntentSender, this.mUserId, null);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Session openSession(int paramInt) throws IOException {
    try {
      return new Session(this.mInstaller.openSession(paramInt));
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (RuntimeException runtimeException) {}
    ExceptionUtils.maybeUnwrapIOException(runtimeException);
    throw runtimeException;
  }
  
  public void registerSessionCallback(SessionCallback paramSessionCallback) {
    registerSessionCallback(paramSessionCallback, new Handler());
  }
  
  public void registerSessionCallback(SessionCallback paramSessionCallback, Handler paramHandler) {
    synchronized (this.mDelegates) {
      SessionCallbackDelegate sessionCallbackDelegate = new SessionCallbackDelegate();
      HandlerExecutor handlerExecutor = new HandlerExecutor();
      this(paramHandler);
      this(paramSessionCallback, (Executor)handlerExecutor);
      try {
        this.mInstaller.registerCallback(sessionCallbackDelegate, this.mUserId);
        this.mDelegates.add(sessionCallbackDelegate);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  @Deprecated
  public void removeSessionCallback(SessionCallback paramSessionCallback) {
    unregisterSessionCallback(paramSessionCallback);
  }
  
  @SystemApi
  public void setPermissionsResult(int paramInt, boolean paramBoolean) {
    try {
      this.mInstaller.setPermissionsResult(paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void uninstall(VersionedPackage paramVersionedPackage, int paramInt, IntentSender paramIntentSender) {
    Objects.requireNonNull(paramVersionedPackage, "versionedPackage cannot be null");
    try {
      this.mInstaller.uninstall(paramVersionedPackage, this.mInstallerPackageName, paramInt, paramIntentSender, this.mUserId);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void uninstall(VersionedPackage paramVersionedPackage, IntentSender paramIntentSender) {
    uninstall(paramVersionedPackage, 0, paramIntentSender);
  }
  
  public void uninstall(String paramString, int paramInt, IntentSender paramIntentSender) {
    uninstall(new VersionedPackage(paramString, -1), paramInt, paramIntentSender);
  }
  
  public void uninstall(String paramString, IntentSender paramIntentSender) {
    uninstall(paramString, 0, paramIntentSender);
  }
  
  public void uninstallExistingPackage(String paramString, IntentSender paramIntentSender) {
    Objects.requireNonNull(paramString, "packageName cannot be null");
    try {
      IPackageInstaller iPackageInstaller = this.mInstaller;
      VersionedPackage versionedPackage = new VersionedPackage();
      this(paramString, -1);
      iPackageInstaller.uninstallExistingPackage(versionedPackage, this.mInstallerPackageName, paramIntentSender, this.mUserId);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unregisterSessionCallback(SessionCallback paramSessionCallback) {
    synchronized (this.mDelegates) {
      Iterator<SessionCallbackDelegate> iterator = this.mDelegates.iterator();
      while (iterator.hasNext()) {
        SessionCallbackDelegate sessionCallbackDelegate = iterator.next();
        SessionCallback sessionCallback = sessionCallbackDelegate.mCallback;
        if (sessionCallback == paramSessionCallback)
          try {
            this.mInstaller.unregisterCallback(sessionCallbackDelegate);
            iterator.remove();
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
      } 
      return;
    } 
  }
  
  public void updateSessionAppIcon(int paramInt, Bitmap paramBitmap) {
    try {
      this.mInstaller.updateSessionAppIcon(paramInt, paramBitmap);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void updateSessionAppLabel(int paramInt, CharSequence paramCharSequence) {
    if (paramCharSequence != null) {
      try {
        paramCharSequence = paramCharSequence.toString();
      } catch (RemoteException remoteException) {}
    } else {
      paramCharSequence = null;
    } 
    this.mInstaller.updateSessionAppLabel(paramInt, (String)paramCharSequence);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FileLocation {}
  
  public static class Session implements Closeable {
    protected final IPackageInstallerSession mSession;
    
    public Session(IPackageInstallerSession param1IPackageInstallerSession) {
      this.mSession = param1IPackageInstallerSession;
    }
    
    public void abandon() {
      try {
        this.mSession.abandon();
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void addChildSessionId(int param1Int) {
      try {
        this.mSession.addChildSessionId(param1Int);
      } catch (RemoteException remoteException) {
        remoteException.rethrowFromSystemServer();
      } 
    }
    
    @SystemApi
    public void addFile(int param1Int, String param1String, long param1Long, byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
      try {
        this.mSession.addFile(param1Int, param1String, param1Long, param1ArrayOfbyte1, param1ArrayOfbyte2);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void addProgress(float param1Float) {
      try {
        this.mSession.addClientProgress(param1Float);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void close() {
      try {
        this.mSession.close();
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void commit(IntentSender param1IntentSender) {
      try {
        this.mSession.commit(param1IntentSender, false);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    @SystemApi
    public void commitTransferred(IntentSender param1IntentSender) {
      try {
        this.mSession.commit(param1IntentSender, true);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void fsync(OutputStream param1OutputStream) throws IOException {
      if (PackageInstaller.ENABLE_REVOCABLE_FD) {
        if (param1OutputStream instanceof ParcelFileDescriptor.AutoCloseOutputStream) {
          try {
            Os.fsync(((ParcelFileDescriptor.AutoCloseOutputStream)param1OutputStream).getFD());
          } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
          } 
        } else {
          throw new IllegalArgumentException("Unrecognized stream");
        } 
      } else {
        if (errnoException instanceof FileBridge.FileBridgeOutputStream) {
          ((FileBridge.FileBridgeOutputStream)errnoException).fsync();
          return;
        } 
        throw new IllegalArgumentException("Unrecognized stream");
      } 
    }
    
    public int[] getChildSessionIds() {
      try {
        return this.mSession.getChildSessionIds();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    @SystemApi
    public DataLoaderParams getDataLoaderParams() {
      try {
        DataLoaderParamsParcel dataLoaderParamsParcel = this.mSession.getDataLoaderParams();
        return (dataLoaderParamsParcel == null) ? null : new DataLoaderParams(dataLoaderParamsParcel);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public String[] getNames() throws IOException {
      try {
        return this.mSession.getNames();
      } catch (RuntimeException runtimeException) {
        ExceptionUtils.maybeUnwrapIOException(runtimeException);
        throw runtimeException;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public int getParentSessionId() {
      try {
        return this.mSession.getParentSessionId();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public boolean isMultiPackage() {
      try {
        return this.mSession.isMultiPackage();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public boolean isStaged() {
      try {
        return this.mSession.isStaged();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public InputStream openRead(String param1String) throws IOException {
      try {
        return (InputStream)new ParcelFileDescriptor.AutoCloseInputStream(this.mSession.openRead(param1String));
      } catch (RuntimeException runtimeException) {
        ExceptionUtils.maybeUnwrapIOException(runtimeException);
        throw runtimeException;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public OutputStream openWrite(String param1String, long param1Long1, long param1Long2) throws IOException {
      try {
        return (OutputStream)(PackageInstaller.ENABLE_REVOCABLE_FD ? new ParcelFileDescriptor.AutoCloseOutputStream(this.mSession.openWrite(param1String, param1Long1, param1Long2)) : new FileBridge.FileBridgeOutputStream(this.mSession.openWrite(param1String, param1Long1, param1Long2)));
      } catch (RuntimeException runtimeException) {
        ExceptionUtils.maybeUnwrapIOException(runtimeException);
        throw runtimeException;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void removeChildSessionId(int param1Int) {
      try {
        this.mSession.removeChildSessionId(param1Int);
      } catch (RemoteException remoteException) {
        remoteException.rethrowFromSystemServer();
      } 
    }
    
    @SystemApi
    public void removeFile(int param1Int, String param1String) {
      try {
        this.mSession.removeFile(param1Int, param1String);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void removeSplit(String param1String) throws IOException {
      try {
        this.mSession.removeSplit(param1String);
        return;
      } catch (RuntimeException runtimeException) {
        ExceptionUtils.maybeUnwrapIOException(runtimeException);
        throw runtimeException;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    @Deprecated
    public void setProgress(float param1Float) {
      setStagingProgress(param1Float);
    }
    
    public void setStagingProgress(float param1Float) {
      try {
        this.mSession.setClientProgress(param1Float);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void transfer(String param1String) throws PackageManager.NameNotFoundException {
      Objects.requireNonNull(param1String);
      try {
        this.mSession.transfer(param1String);
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(PackageManager.NameNotFoundException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void write(String param1String, long param1Long1, long param1Long2, ParcelFileDescriptor param1ParcelFileDescriptor) throws IOException {
      try {
        this.mSession.write(param1String, param1Long1, param1Long2, param1ParcelFileDescriptor);
        return;
      } catch (RuntimeException runtimeException) {
        ExceptionUtils.maybeUnwrapIOException(runtimeException);
        throw runtimeException;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
  }
  
  public static abstract class SessionCallback {
    public abstract void onActiveChanged(int param1Int, boolean param1Boolean);
    
    public abstract void onBadgingChanged(int param1Int);
    
    public abstract void onCreated(int param1Int);
    
    public abstract void onFinished(int param1Int, boolean param1Boolean);
    
    public abstract void onProgressChanged(int param1Int, float param1Float);
  }
  
  static class SessionCallbackDelegate extends IPackageInstallerCallback.Stub {
    private static final int MSG_SESSION_ACTIVE_CHANGED = 3;
    
    private static final int MSG_SESSION_BADGING_CHANGED = 2;
    
    private static final int MSG_SESSION_CREATED = 1;
    
    private static final int MSG_SESSION_FINISHED = 5;
    
    private static final int MSG_SESSION_PROGRESS_CHANGED = 4;
    
    final PackageInstaller.SessionCallback mCallback;
    
    final Executor mExecutor;
    
    SessionCallbackDelegate(PackageInstaller.SessionCallback param1SessionCallback, Executor param1Executor) {
      this.mCallback = param1SessionCallback;
      this.mExecutor = param1Executor;
    }
    
    public void onSessionActiveChanged(int param1Int, boolean param1Boolean) {
      this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$T1UQAuePWRRmVQ1KzTyMAktZUPM.INSTANCE, this.mCallback, Integer.valueOf(param1Int), Boolean.valueOf(param1Boolean)).recycleOnUse());
    }
    
    public void onSessionBadgingChanged(int param1Int) {
      this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$B12dZLpdwpXn89QSesmkaZjD72Q.INSTANCE, this.mCallback, Integer.valueOf(param1Int)).recycleOnUse());
    }
    
    public void onSessionCreated(int param1Int) {
      this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$ciir_QAmv6RwJro4I58t77dPnxU.INSTANCE, this.mCallback, Integer.valueOf(param1Int)).recycleOnUse());
    }
    
    public void onSessionFinished(int param1Int, boolean param1Boolean) {
      this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$zO9HBUVgPeroyDQPLJE_MNMvSqc.INSTANCE, this.mCallback, Integer.valueOf(param1Int), Boolean.valueOf(param1Boolean)).recycleOnUse());
    }
    
    public void onSessionProgressChanged(int param1Int, float param1Float) {
      this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$n3uXeb1v_YRmq_BWTfosEqUUr9g.INSTANCE, this.mCallback, Integer.valueOf(param1Int), Float.valueOf(param1Float)).recycleOnUse());
    }
  }
  
  public static class SessionInfo implements Parcelable {
    public static final Parcelable.Creator<SessionInfo> CREATOR = new Parcelable.Creator<SessionInfo>() {
        public PackageInstaller.SessionInfo createFromParcel(Parcel param2Parcel) {
          return new PackageInstaller.SessionInfo(param2Parcel);
        }
        
        public PackageInstaller.SessionInfo[] newArray(int param2Int) {
          return new PackageInstaller.SessionInfo[param2Int];
        }
      };
    
    public static final int INVALID_ID = -1;
    
    private static final int[] NO_SESSIONS = new int[0];
    
    public static final int STAGED_SESSION_ACTIVATION_FAILED = 2;
    
    public static final int STAGED_SESSION_NO_ERROR = 0;
    
    public static final int STAGED_SESSION_UNKNOWN = 3;
    
    public static final int STAGED_SESSION_VERIFICATION_FAILED = 1;
    
    public boolean active;
    
    public Bitmap appIcon;
    
    public CharSequence appLabel;
    
    public String appPackageName;
    
    public int autoRevokePermissionsMode;
    
    public int[] childSessionIds;
    
    public long createdMillis;
    
    public boolean forceQueryable;
    
    public String[] grantedRuntimePermissions;
    
    public int installFlags;
    
    public int installLocation;
    
    public int installReason;
    
    public String installerPackageName;
    
    public boolean isCommitted;
    
    public boolean isMultiPackage;
    
    public boolean isStaged;
    
    public boolean isStagedSessionApplied;
    
    public boolean isStagedSessionFailed;
    
    public boolean isStagedSessionReady;
    
    private int mStagedSessionErrorCode;
    
    private String mStagedSessionErrorMessage;
    
    public int mode;
    
    public int originatingUid;
    
    public Uri originatingUri;
    
    public int parentSessionId;
    
    public float progress;
    
    public Uri referrerUri;
    
    public String resolvedBaseCodePath;
    
    public int rollbackDataPolicy;
    
    public boolean sealed;
    
    public int sessionId;
    
    public long sizeBytes;
    
    public long updatedMillis;
    
    public int userId;
    
    public List<String> whitelistedRestrictedPermissions;
    
    static {
    
    }
    
    public SessionInfo() {
      this.autoRevokePermissionsMode = 3;
      this.parentSessionId = -1;
      this.childSessionIds = NO_SESSIONS;
    }
    
    public SessionInfo(Parcel param1Parcel) {
      boolean bool2;
      this.autoRevokePermissionsMode = 3;
      this.parentSessionId = -1;
      this.childSessionIds = NO_SESSIONS;
      this.sessionId = param1Parcel.readInt();
      this.userId = param1Parcel.readInt();
      this.installerPackageName = param1Parcel.readString();
      this.resolvedBaseCodePath = param1Parcel.readString();
      this.progress = param1Parcel.readFloat();
      int i = param1Parcel.readInt();
      boolean bool1 = true;
      if (i != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.sealed = bool2;
      if (param1Parcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.active = bool2;
      this.mode = param1Parcel.readInt();
      this.installReason = param1Parcel.readInt();
      this.sizeBytes = param1Parcel.readLong();
      this.appPackageName = param1Parcel.readString();
      this.appIcon = (Bitmap)param1Parcel.readParcelable(null);
      this.appLabel = param1Parcel.readString();
      this.installLocation = param1Parcel.readInt();
      this.originatingUri = (Uri)param1Parcel.readParcelable(null);
      this.originatingUid = param1Parcel.readInt();
      this.referrerUri = (Uri)param1Parcel.readParcelable(null);
      this.grantedRuntimePermissions = param1Parcel.readStringArray();
      this.whitelistedRestrictedPermissions = param1Parcel.createStringArrayList();
      this.autoRevokePermissionsMode = param1Parcel.readInt();
      this.installFlags = param1Parcel.readInt();
      this.isMultiPackage = param1Parcel.readBoolean();
      this.isStaged = param1Parcel.readBoolean();
      this.forceQueryable = param1Parcel.readBoolean();
      this.parentSessionId = param1Parcel.readInt();
      int[] arrayOfInt = param1Parcel.createIntArray();
      this.childSessionIds = arrayOfInt;
      if (arrayOfInt == null)
        this.childSessionIds = NO_SESSIONS; 
      this.isStagedSessionApplied = param1Parcel.readBoolean();
      this.isStagedSessionReady = param1Parcel.readBoolean();
      this.isStagedSessionFailed = param1Parcel.readBoolean();
      this.mStagedSessionErrorCode = param1Parcel.readInt();
      this.mStagedSessionErrorMessage = param1Parcel.readString();
      this.isCommitted = param1Parcel.readBoolean();
      this.rollbackDataPolicy = param1Parcel.readInt();
      this.createdMillis = param1Parcel.readLong();
    }
    
    private void checkSessionIsStaged() {
      if (this.isStaged)
        return; 
      throw new IllegalStateException("Session is not marked as staged.");
    }
    
    public Intent createDetailsIntent() {
      Intent intent = new Intent("android.content.pm.action.SESSION_DETAILS");
      intent.putExtra("android.content.pm.extra.SESSION_ID", this.sessionId);
      intent.setPackage(this.installerPackageName);
      intent.setFlags(268435456);
      return intent;
    }
    
    public int describeContents() {
      return 0;
    }
    
    @SystemApi
    public boolean getAllocateAggressive() {
      boolean bool;
      if ((this.installFlags & 0x8000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @SystemApi
    @Deprecated
    public boolean getAllowDowngrade() {
      return getRequestDowngrade();
    }
    
    public Bitmap getAppIcon() {
      if (this.appIcon == null)
        try {
          SessionInfo sessionInfo = AppGlobals.getPackageManager().getPackageInstaller().getSessionInfo(this.sessionId);
          if (sessionInfo != null) {
            Bitmap bitmap = sessionInfo.appIcon;
          } else {
            sessionInfo = null;
          } 
          this.appIcon = (Bitmap)sessionInfo;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return this.appIcon;
    }
    
    public CharSequence getAppLabel() {
      return this.appLabel;
    }
    
    public String getAppPackageName() {
      return this.appPackageName;
    }
    
    @SystemApi
    public int getAutoRevokePermissionsMode() {
      return this.autoRevokePermissionsMode;
    }
    
    public int[] getChildSessionIds() {
      return this.childSessionIds;
    }
    
    public long getCreatedMillis() {
      return this.createdMillis;
    }
    
    @Deprecated
    public Intent getDetailsIntent() {
      return createDetailsIntent();
    }
    
    @SystemApi
    public boolean getDontKillApp() {
      boolean bool;
      if ((this.installFlags & 0x1000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @SystemApi
    public boolean getEnableRollback() {
      boolean bool;
      if ((this.installFlags & 0x40000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @SystemApi
    public String[] getGrantedRuntimePermissions() {
      return this.grantedRuntimePermissions;
    }
    
    @SystemApi
    public boolean getInstallAsFullApp(boolean param1Boolean) {
      if ((this.installFlags & 0x4000) != 0) {
        param1Boolean = true;
      } else {
        param1Boolean = false;
      } 
      return param1Boolean;
    }
    
    @SystemApi
    public boolean getInstallAsInstantApp(boolean param1Boolean) {
      if ((this.installFlags & 0x800) != 0) {
        param1Boolean = true;
      } else {
        param1Boolean = false;
      } 
      return param1Boolean;
    }
    
    @SystemApi
    public boolean getInstallAsVirtualPreload() {
      boolean bool;
      if ((this.installFlags & 0x10000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getInstallLocation() {
      return this.installLocation;
    }
    
    public int getInstallReason() {
      return this.installReason;
    }
    
    public String getInstallerPackageName() {
      return this.installerPackageName;
    }
    
    public int getMode() {
      return this.mode;
    }
    
    public int getOriginatingUid() {
      return this.originatingUid;
    }
    
    public Uri getOriginatingUri() {
      return this.originatingUri;
    }
    
    public int getParentSessionId() {
      return this.parentSessionId;
    }
    
    public float getProgress() {
      return this.progress;
    }
    
    public Uri getReferrerUri() {
      return this.referrerUri;
    }
    
    @SystemApi
    public boolean getRequestDowngrade() {
      boolean bool;
      if ((this.installFlags & 0x80) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @SystemApi
    public int getRollbackDataPolicy() {
      return this.rollbackDataPolicy;
    }
    
    public int getSessionId() {
      return this.sessionId;
    }
    
    public long getSize() {
      return this.sizeBytes;
    }
    
    public int getStagedSessionErrorCode() {
      checkSessionIsStaged();
      return this.mStagedSessionErrorCode;
    }
    
    public String getStagedSessionErrorMessage() {
      checkSessionIsStaged();
      return this.mStagedSessionErrorMessage;
    }
    
    public long getUpdatedMillis() {
      return this.updatedMillis;
    }
    
    public UserHandle getUser() {
      return new UserHandle(this.userId);
    }
    
    @SystemApi
    public Set<String> getWhitelistedRestrictedPermissions() {
      return (Set<String>)(((this.installFlags & 0x400000) != 0) ? PackageInstaller.SessionParams.RESTRICTED_PERMISSIONS_ALL : ((this.whitelistedRestrictedPermissions != null) ? new ArraySet(this.whitelistedRestrictedPermissions) : Collections.emptySet()));
    }
    
    public boolean hasParentSessionId() {
      boolean bool;
      if (this.parentSessionId != -1) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isActive() {
      return this.active;
    }
    
    public boolean isCommitted() {
      return this.isCommitted;
    }
    
    public boolean isForceQueryable() {
      return this.forceQueryable;
    }
    
    public boolean isMultiPackage() {
      return this.isMultiPackage;
    }
    
    @Deprecated
    public boolean isOpen() {
      return isActive();
    }
    
    public boolean isSealed() {
      return this.sealed;
    }
    
    public boolean isStaged() {
      return this.isStaged;
    }
    
    public boolean isStagedSessionActive() {
      boolean bool;
      if (this.isStaged && this.isCommitted && !this.isStagedSessionApplied && !this.isStagedSessionFailed && !hasParentSessionId()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isStagedSessionApplied() {
      checkSessionIsStaged();
      return this.isStagedSessionApplied;
    }
    
    public boolean isStagedSessionFailed() {
      checkSessionIsStaged();
      return this.isStagedSessionFailed;
    }
    
    public boolean isStagedSessionReady() {
      checkSessionIsStaged();
      return this.isStagedSessionReady;
    }
    
    public void setStagedSessionErrorCode(int param1Int, String param1String) {
      this.mStagedSessionErrorCode = param1Int;
      this.mStagedSessionErrorMessage = param1String;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.sessionId);
      param1Parcel.writeInt(this.userId);
      param1Parcel.writeString(this.installerPackageName);
      param1Parcel.writeString(this.resolvedBaseCodePath);
      param1Parcel.writeFloat(this.progress);
      param1Parcel.writeInt(this.sealed);
      param1Parcel.writeInt(this.active);
      param1Parcel.writeInt(this.mode);
      param1Parcel.writeInt(this.installReason);
      param1Parcel.writeLong(this.sizeBytes);
      param1Parcel.writeString(this.appPackageName);
      param1Parcel.writeParcelable((Parcelable)this.appIcon, param1Int);
      CharSequence charSequence = this.appLabel;
      if (charSequence != null) {
        charSequence = charSequence.toString();
      } else {
        charSequence = null;
      } 
      param1Parcel.writeString((String)charSequence);
      param1Parcel.writeInt(this.installLocation);
      param1Parcel.writeParcelable((Parcelable)this.originatingUri, param1Int);
      param1Parcel.writeInt(this.originatingUid);
      param1Parcel.writeParcelable((Parcelable)this.referrerUri, param1Int);
      param1Parcel.writeStringArray(this.grantedRuntimePermissions);
      param1Parcel.writeStringList(this.whitelistedRestrictedPermissions);
      param1Parcel.writeInt(this.autoRevokePermissionsMode);
      param1Parcel.writeInt(this.installFlags);
      param1Parcel.writeBoolean(this.isMultiPackage);
      param1Parcel.writeBoolean(this.isStaged);
      param1Parcel.writeBoolean(this.forceQueryable);
      param1Parcel.writeInt(this.parentSessionId);
      param1Parcel.writeIntArray(this.childSessionIds);
      param1Parcel.writeBoolean(this.isStagedSessionApplied);
      param1Parcel.writeBoolean(this.isStagedSessionReady);
      param1Parcel.writeBoolean(this.isStagedSessionFailed);
      param1Parcel.writeInt(this.mStagedSessionErrorCode);
      param1Parcel.writeString(this.mStagedSessionErrorMessage);
      param1Parcel.writeBoolean(this.isCommitted);
      param1Parcel.writeInt(this.rollbackDataPolicy);
      param1Parcel.writeLong(this.createdMillis);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface StagedSessionErrorCode {}
  }
  
  class null implements Parcelable.Creator<SessionInfo> {
    public PackageInstaller.SessionInfo createFromParcel(Parcel param1Parcel) {
      return new PackageInstaller.SessionInfo(param1Parcel);
    }
    
    public PackageInstaller.SessionInfo[] newArray(int param1Int) {
      return new PackageInstaller.SessionInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StagedSessionErrorCode {}
  
  public static class SessionParams implements Parcelable {
    public static final Parcelable.Creator<SessionParams> CREATOR = new Parcelable.Creator<SessionParams>() {
        public PackageInstaller.SessionParams createFromParcel(Parcel param2Parcel) {
          return new PackageInstaller.SessionParams(param2Parcel);
        }
        
        public PackageInstaller.SessionParams[] newArray(int param2Int) {
          return new PackageInstaller.SessionParams[param2Int];
        }
      };
    
    public static final int MAX_PACKAGE_NAME_LENGTH = 255;
    
    public static final int MODE_FULL_INSTALL = 1;
    
    public static final int MODE_INHERIT_EXISTING = 2;
    
    public static final int MODE_INVALID = -1;
    
    public static final Set<String> RESTRICTED_PERMISSIONS_ALL = (Set<String>)new ArraySet();
    
    public static final int UID_UNKNOWN = -1;
    
    public String abiOverride;
    
    public Bitmap appIcon;
    
    public long appIconLastModified = -1L;
    
    public String appLabel;
    
    public String appPackageName;
    
    public int autoRevokePermissionsMode = 3;
    
    public DataLoaderParams dataLoaderParams;
    
    public boolean forceQueryableOverride;
    
    public String[] grantedRuntimePermissions;
    
    public int installFlags = 4194304;
    
    public int installLocation = 1;
    
    public int installReason = 0;
    
    public String installerPackageName;
    
    public boolean isMultiPackage;
    
    public boolean isStaged;
    
    public int mode = -1;
    
    public int originatingUid = -1;
    
    public Uri originatingUri;
    
    public Uri referrerUri;
    
    public long requiredInstalledVersionCode = -1L;
    
    public int rollbackDataPolicy = 0;
    
    public long sizeBytes = -1L;
    
    public String volumeUuid;
    
    public List<String> whitelistedRestrictedPermissions;
    
    static {
    
    }
    
    public SessionParams(int param1Int) {
      this.mode = param1Int;
    }
    
    public SessionParams(Parcel param1Parcel) {
      this.mode = param1Parcel.readInt();
      this.installFlags = param1Parcel.readInt();
      this.installLocation = param1Parcel.readInt();
      this.installReason = param1Parcel.readInt();
      this.sizeBytes = param1Parcel.readLong();
      this.appPackageName = param1Parcel.readString();
      this.appIcon = (Bitmap)param1Parcel.readParcelable(null);
      this.appLabel = param1Parcel.readString();
      this.originatingUri = (Uri)param1Parcel.readParcelable(null);
      this.originatingUid = param1Parcel.readInt();
      this.referrerUri = (Uri)param1Parcel.readParcelable(null);
      this.abiOverride = param1Parcel.readString();
      this.volumeUuid = param1Parcel.readString();
      this.grantedRuntimePermissions = param1Parcel.readStringArray();
      this.whitelistedRestrictedPermissions = param1Parcel.createStringArrayList();
      this.autoRevokePermissionsMode = param1Parcel.readInt();
      this.installerPackageName = param1Parcel.readString();
      this.isMultiPackage = param1Parcel.readBoolean();
      this.isStaged = param1Parcel.readBoolean();
      this.forceQueryableOverride = param1Parcel.readBoolean();
      this.requiredInstalledVersionCode = param1Parcel.readLong();
      DataLoaderParamsParcel dataLoaderParamsParcel = (DataLoaderParamsParcel)param1Parcel.readParcelable(DataLoaderParamsParcel.class.getClassLoader());
      if (dataLoaderParamsParcel != null)
        this.dataLoaderParams = new DataLoaderParams(dataLoaderParamsParcel); 
      this.rollbackDataPolicy = param1Parcel.readInt();
    }
    
    public boolean areHiddenOptionsSet() {
      int i = this.installFlags;
      return ((0x11D880 & i) != i || this.abiOverride != null || this.volumeUuid != null);
    }
    
    public SessionParams copy() {
      SessionParams sessionParams = new SessionParams(this.mode);
      sessionParams.installFlags = this.installFlags;
      sessionParams.installLocation = this.installLocation;
      sessionParams.installReason = this.installReason;
      sessionParams.sizeBytes = this.sizeBytes;
      sessionParams.appPackageName = this.appPackageName;
      sessionParams.appIcon = this.appIcon;
      sessionParams.appLabel = this.appLabel;
      sessionParams.originatingUri = this.originatingUri;
      sessionParams.originatingUid = this.originatingUid;
      sessionParams.referrerUri = this.referrerUri;
      sessionParams.abiOverride = this.abiOverride;
      sessionParams.volumeUuid = this.volumeUuid;
      sessionParams.grantedRuntimePermissions = this.grantedRuntimePermissions;
      sessionParams.whitelistedRestrictedPermissions = this.whitelistedRestrictedPermissions;
      sessionParams.autoRevokePermissionsMode = this.autoRevokePermissionsMode;
      sessionParams.installerPackageName = this.installerPackageName;
      sessionParams.isMultiPackage = this.isMultiPackage;
      sessionParams.isStaged = this.isStaged;
      sessionParams.forceQueryableOverride = this.forceQueryableOverride;
      sessionParams.requiredInstalledVersionCode = this.requiredInstalledVersionCode;
      sessionParams.dataLoaderParams = this.dataLoaderParams;
      sessionParams.rollbackDataPolicy = this.rollbackDataPolicy;
      return sessionParams;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void dump(IndentingPrintWriter param1IndentingPrintWriter) {
      boolean bool;
      param1IndentingPrintWriter.printPair("mode", Integer.valueOf(this.mode));
      param1IndentingPrintWriter.printHexPair("installFlags", this.installFlags);
      param1IndentingPrintWriter.printPair("installLocation", Integer.valueOf(this.installLocation));
      param1IndentingPrintWriter.printPair("sizeBytes", Long.valueOf(this.sizeBytes));
      param1IndentingPrintWriter.printPair("appPackageName", this.appPackageName);
      if (this.appIcon != null) {
        bool = true;
      } else {
        bool = false;
      } 
      param1IndentingPrintWriter.printPair("appIcon", Boolean.valueOf(bool));
      param1IndentingPrintWriter.printPair("appLabel", this.appLabel);
      param1IndentingPrintWriter.printPair("originatingUri", this.originatingUri);
      param1IndentingPrintWriter.printPair("originatingUid", Integer.valueOf(this.originatingUid));
      param1IndentingPrintWriter.printPair("referrerUri", this.referrerUri);
      param1IndentingPrintWriter.printPair("abiOverride", this.abiOverride);
      param1IndentingPrintWriter.printPair("volumeUuid", this.volumeUuid);
      param1IndentingPrintWriter.printPair("grantedRuntimePermissions", (Object[])this.grantedRuntimePermissions);
      param1IndentingPrintWriter.printPair("whitelistedRestrictedPermissions", this.whitelistedRestrictedPermissions);
      param1IndentingPrintWriter.printPair("autoRevokePermissions", Integer.valueOf(this.autoRevokePermissionsMode));
      param1IndentingPrintWriter.printPair("installerPackageName", this.installerPackageName);
      param1IndentingPrintWriter.printPair("isMultiPackage", Boolean.valueOf(this.isMultiPackage));
      param1IndentingPrintWriter.printPair("isStaged", Boolean.valueOf(this.isStaged));
      param1IndentingPrintWriter.printPair("forceQueryable", Boolean.valueOf(this.forceQueryableOverride));
      param1IndentingPrintWriter.printPair("requiredInstalledVersionCode", Long.valueOf(this.requiredInstalledVersionCode));
      param1IndentingPrintWriter.printPair("dataLoaderParams", this.dataLoaderParams);
      param1IndentingPrintWriter.printPair("rollbackDataPolicy", Integer.valueOf(this.rollbackDataPolicy));
      param1IndentingPrintWriter.println();
    }
    
    public boolean getEnableRollback() {
      boolean bool;
      if ((this.installFlags & 0x40000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @SystemApi
    public void setAllocateAggressive(boolean param1Boolean) {
      if (param1Boolean) {
        this.installFlags |= 0x8000;
      } else {
        this.installFlags &= 0xFFFF7FFF;
      } 
    }
    
    @SystemApi
    @Deprecated
    public void setAllowDowngrade(boolean param1Boolean) {
      setRequestDowngrade(param1Boolean);
    }
    
    public void setAppIcon(Bitmap param1Bitmap) {
      this.appIcon = param1Bitmap;
    }
    
    public void setAppLabel(CharSequence param1CharSequence) {
      if (param1CharSequence != null) {
        param1CharSequence = param1CharSequence.toString();
      } else {
        param1CharSequence = null;
      } 
      this.appLabel = (String)param1CharSequence;
    }
    
    public void setAppPackageName(String param1String) {
      this.appPackageName = param1String;
    }
    
    public void setAutoRevokePermissionsMode(boolean param1Boolean) {
      this.autoRevokePermissionsMode = param1Boolean ^ true;
    }
    
    @SystemApi
    public void setDataLoaderParams(DataLoaderParams param1DataLoaderParams) {
      this.dataLoaderParams = param1DataLoaderParams;
    }
    
    @SystemApi
    public void setDontKillApp(boolean param1Boolean) {
      if (param1Boolean) {
        this.installFlags |= 0x1000;
      } else {
        this.installFlags &= 0xFFFFEFFF;
      } 
    }
    
    @SystemApi
    public void setEnableRollback(boolean param1Boolean) {
      if (param1Boolean) {
        this.installFlags |= 0x40000;
      } else {
        this.installFlags &= 0xFFFBFFFF;
      } 
      this.rollbackDataPolicy = 0;
    }
    
    @SystemApi
    public void setEnableRollback(boolean param1Boolean, int param1Int) {
      if (param1Boolean) {
        this.installFlags |= 0x40000;
      } else {
        this.installFlags &= 0xFFFBFFFF;
      } 
      this.rollbackDataPolicy = param1Int;
    }
    
    public void setForceQueryable() {
      this.forceQueryableOverride = true;
    }
    
    @SystemApi
    public void setGrantedRuntimePermissions(String[] param1ArrayOfString) {
      this.installFlags |= 0x100;
      this.grantedRuntimePermissions = param1ArrayOfString;
    }
    
    @SystemApi
    public void setInstallAsApex() {
      this.installFlags |= 0x20000;
    }
    
    @SystemApi
    public void setInstallAsInstantApp(boolean param1Boolean) {
      if (param1Boolean) {
        int i = this.installFlags | 0x800;
        this.installFlags = i;
        this.installFlags = i & 0xFFFFBFFF;
      } else {
        int i = this.installFlags & 0xFFFFF7FF;
        this.installFlags = i;
        this.installFlags = i | 0x4000;
      } 
    }
    
    @SystemApi
    public void setInstallAsVirtualPreload() {
      this.installFlags |= 0x10000;
    }
    
    public void setInstallFlagsForcePermissionPrompt() {
      this.installFlags |= 0x400;
    }
    
    public void setInstallLocation(int param1Int) {
      this.installLocation = param1Int;
    }
    
    public void setInstallReason(int param1Int) {
      this.installReason = param1Int;
    }
    
    public void setInstallerPackageName(String param1String) {
      this.installerPackageName = param1String;
    }
    
    public void setMultiPackage() {
      this.isMultiPackage = true;
    }
    
    public void setOriginatingUid(int param1Int) {
      this.originatingUid = param1Int;
    }
    
    public void setOriginatingUri(Uri param1Uri) {
      this.originatingUri = param1Uri;
    }
    
    public void setReferrerUri(Uri param1Uri) {
      this.referrerUri = param1Uri;
    }
    
    @SystemApi
    public void setRequestDowngrade(boolean param1Boolean) {
      if (param1Boolean) {
        this.installFlags |= 0x80;
      } else {
        this.installFlags &= 0xFFFFFF7F;
      } 
    }
    
    public void setRequiredInstalledVersionCode(long param1Long) {
      this.requiredInstalledVersionCode = param1Long;
    }
    
    public void setSize(long param1Long) {
      this.sizeBytes = param1Long;
    }
    
    @SystemApi
    public void setStaged() {
      this.isStaged = true;
    }
    
    public void setWhitelistedRestrictedPermissions(Set<String> param1Set) {
      Set<String> set = RESTRICTED_PERMISSIONS_ALL;
      Set set1 = null;
      if (param1Set == set) {
        this.installFlags |= 0x400000;
        this.whitelistedRestrictedPermissions = null;
      } else {
        this.installFlags &= 0xFFBFFFFF;
        if (param1Set != null) {
          ArrayList<String> arrayList = new ArrayList<>(param1Set);
        } else {
          param1Set = set1;
        } 
        this.whitelistedRestrictedPermissions = (List<String>)param1Set;
      } 
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mode);
      param1Parcel.writeInt(this.installFlags);
      param1Parcel.writeInt(this.installLocation);
      param1Parcel.writeInt(this.installReason);
      param1Parcel.writeLong(this.sizeBytes);
      param1Parcel.writeString(this.appPackageName);
      param1Parcel.writeParcelable((Parcelable)this.appIcon, param1Int);
      param1Parcel.writeString(this.appLabel);
      param1Parcel.writeParcelable((Parcelable)this.originatingUri, param1Int);
      param1Parcel.writeInt(this.originatingUid);
      param1Parcel.writeParcelable((Parcelable)this.referrerUri, param1Int);
      param1Parcel.writeString(this.abiOverride);
      param1Parcel.writeString(this.volumeUuid);
      param1Parcel.writeStringArray(this.grantedRuntimePermissions);
      param1Parcel.writeStringList(this.whitelistedRestrictedPermissions);
      param1Parcel.writeInt(this.autoRevokePermissionsMode);
      param1Parcel.writeString(this.installerPackageName);
      param1Parcel.writeBoolean(this.isMultiPackage);
      param1Parcel.writeBoolean(this.isStaged);
      param1Parcel.writeBoolean(this.forceQueryableOverride);
      param1Parcel.writeLong(this.requiredInstalledVersionCode);
      DataLoaderParams dataLoaderParams = this.dataLoaderParams;
      if (dataLoaderParams != null) {
        param1Parcel.writeParcelable(dataLoaderParams.getData(), param1Int);
      } else {
        param1Parcel.writeParcelable(null, param1Int);
      } 
      param1Parcel.writeInt(this.rollbackDataPolicy);
    }
  }
  
  class null implements Parcelable.Creator<SessionParams> {
    public PackageInstaller.SessionParams createFromParcel(Parcel param1Parcel) {
      return new PackageInstaller.SessionParams(param1Parcel);
    }
    
    public PackageInstaller.SessionParams[] newArray(int param1Int) {
      return new PackageInstaller.SessionParams[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */