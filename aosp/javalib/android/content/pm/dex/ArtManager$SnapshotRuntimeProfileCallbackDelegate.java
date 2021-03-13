package android.content.pm.dex;

import android.os.ParcelFileDescriptor;
import java.util.concurrent.Executor;

class SnapshotRuntimeProfileCallbackDelegate extends ISnapshotRuntimeProfileCallback.Stub {
  private final ArtManager.SnapshotRuntimeProfileCallback mCallback;
  
  private final Executor mExecutor;
  
  private SnapshotRuntimeProfileCallbackDelegate(ArtManager.SnapshotRuntimeProfileCallback paramSnapshotRuntimeProfileCallback, Executor paramExecutor) {
    this.mCallback = paramSnapshotRuntimeProfileCallback;
    this.mExecutor = paramExecutor;
  }
  
  public void onError(int paramInt) {
    this.mExecutor.execute(new _$$Lambda$ArtManager$SnapshotRuntimeProfileCallbackDelegate$m2Wpsf6LxhWt_1tS6tQt3B8QcGo(this, paramInt));
  }
  
  public void onSuccess(ParcelFileDescriptor paramParcelFileDescriptor) {
    this.mExecutor.execute(new _$$Lambda$ArtManager$SnapshotRuntimeProfileCallbackDelegate$OOdGv4iFxuVpH2kzFMr8KwX3X8s(this, paramParcelFileDescriptor));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ArtManager$SnapshotRuntimeProfileCallbackDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */