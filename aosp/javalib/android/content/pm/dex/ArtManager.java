package android.content.pm.dex;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Slog;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

@SystemApi
public class ArtManager {
  public static final int PROFILE_APPS = 0;
  
  public static final int PROFILE_BOOT_IMAGE = 1;
  
  public static final int SNAPSHOT_FAILED_CODE_PATH_NOT_FOUND = 1;
  
  public static final int SNAPSHOT_FAILED_INTERNAL_ERROR = 2;
  
  public static final int SNAPSHOT_FAILED_PACKAGE_NOT_FOUND = 0;
  
  private static final String TAG = "ArtManager";
  
  private final IArtManager mArtManager;
  
  private final Context mContext;
  
  public ArtManager(Context paramContext, IArtManager paramIArtManager) {
    this.mContext = paramContext;
    this.mArtManager = paramIArtManager;
  }
  
  public static String getCurrentProfilePath(String paramString1, int paramInt, String paramString2) {
    return (new File(Environment.getDataProfilesDePackageDirectory(paramInt, paramString1), getProfileName(paramString2))).getAbsolutePath();
  }
  
  public static String getProfileName(String paramString) {
    if (paramString == null) {
      paramString = "primary.prof";
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(".split.prof");
      paramString = stringBuilder.toString();
    } 
    return paramString;
  }
  
  public static File getProfileSnapshotFileForName(String paramString1, String paramString2) {
    File file = Environment.getDataRefProfilesDePackageDirectory(paramString1);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString2);
    stringBuilder.append(".snapshot");
    return new File(file, stringBuilder.toString());
  }
  
  public boolean isRuntimeProfilingEnabled(int paramInt) {
    try {
      return this.mArtManager.isRuntimeProfilingEnabled(paramInt, this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public void snapshotRuntimeProfile(int paramInt, String paramString1, String paramString2, Executor paramExecutor, SnapshotRuntimeProfileCallback paramSnapshotRuntimeProfileCallback) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Requesting profile snapshot for ");
    stringBuilder.append(paramString1);
    stringBuilder.append(":");
    stringBuilder.append(paramString2);
    Slog.d("ArtManager", stringBuilder.toString());
    SnapshotRuntimeProfileCallbackDelegate snapshotRuntimeProfileCallbackDelegate = new SnapshotRuntimeProfileCallbackDelegate(paramSnapshotRuntimeProfileCallback, paramExecutor);
    try {
      this.mArtManager.snapshotRuntimeProfile(paramInt, paramString1, paramString2, snapshotRuntimeProfileCallbackDelegate, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProfileType {}
  
  public static abstract class SnapshotRuntimeProfileCallback {
    public abstract void onError(int param1Int);
    
    public abstract void onSuccess(ParcelFileDescriptor param1ParcelFileDescriptor);
  }
  
  private static class SnapshotRuntimeProfileCallbackDelegate extends ISnapshotRuntimeProfileCallback.Stub {
    private final ArtManager.SnapshotRuntimeProfileCallback mCallback;
    
    private final Executor mExecutor;
    
    private SnapshotRuntimeProfileCallbackDelegate(ArtManager.SnapshotRuntimeProfileCallback param1SnapshotRuntimeProfileCallback, Executor param1Executor) {
      this.mCallback = param1SnapshotRuntimeProfileCallback;
      this.mExecutor = param1Executor;
    }
    
    public void onError(int param1Int) {
      this.mExecutor.execute(new _$$Lambda$ArtManager$SnapshotRuntimeProfileCallbackDelegate$m2Wpsf6LxhWt_1tS6tQt3B8QcGo(this, param1Int));
    }
    
    public void onSuccess(ParcelFileDescriptor param1ParcelFileDescriptor) {
      this.mExecutor.execute(new _$$Lambda$ArtManager$SnapshotRuntimeProfileCallbackDelegate$OOdGv4iFxuVpH2kzFMr8KwX3X8s(this, param1ParcelFileDescriptor));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ArtManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */