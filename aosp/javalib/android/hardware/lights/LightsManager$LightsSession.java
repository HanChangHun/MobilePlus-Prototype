package android.hardware.lights;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.CloseGuard;
import com.android.internal.util.Preconditions;
import java.lang.ref.Reference;

public final class LightsSession implements AutoCloseable {
  private final CloseGuard mCloseGuard;
  
  private boolean mClosed;
  
  private final IBinder mToken = (IBinder)new Binder();
  
  private LightsSession() {
    CloseGuard closeGuard = new CloseGuard();
    this.mCloseGuard = closeGuard;
    this.mClosed = false;
    closeGuard.open("close");
  }
  
  public void close() {
    if (!this.mClosed)
      try {
        LightsManager.access$200(LightsManager.this).closeSession(this.mToken);
        this.mClosed = true;
        this.mCloseGuard.close();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Reference.reachabilityFence(this);
  }
  
  protected void finalize() throws Throwable {
    try {
      this.mCloseGuard.warnIfOpen();
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public void requestLights(LightsRequest paramLightsRequest) {
    Preconditions.checkNotNull(paramLightsRequest);
    if (!this.mClosed)
      try {
        LightsManager.access$200(LightsManager.this).setLightStates(this.mToken, paramLightsRequest.mLightIds, paramLightsRequest.mLightStates);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/LightsManager$LightsSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */