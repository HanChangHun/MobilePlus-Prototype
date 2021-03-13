package android.hardware.lights;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.CloseGuard;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.Reference;
import java.util.List;

@SystemApi
public final class LightsManager {
  public static final int LIGHT_TYPE_MICROPHONE = 8;
  
  private static final String TAG = "LightsManager";
  
  private final Context mContext;
  
  private final ILightsManager mService;
  
  public LightsManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this(paramContext, ILightsManager.Stub.asInterface(ServiceManager.getServiceOrThrow("lights")));
  }
  
  public LightsManager(Context paramContext, ILightsManager paramILightsManager) {
    this.mContext = (Context)Preconditions.checkNotNull(paramContext);
    this.mService = (ILightsManager)Preconditions.checkNotNull(paramILightsManager);
  }
  
  public LightState getLightState(Light paramLight) {
    Preconditions.checkNotNull(paramLight);
    try {
      return this.mService.getLightState(paramLight.getId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<Light> getLights() {
    try {
      return this.mService.getLights();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public LightsSession openSession() {
    try {
      LightsSession lightsSession = new LightsSession();
      this(this);
      this.mService.openSession(lightsSession.mToken);
      return lightsSession;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LightType {}
  
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
          LightsManager.this.mService.closeSession(this.mToken);
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
    
    public void requestLights(LightsRequest param1LightsRequest) {
      Preconditions.checkNotNull(param1LightsRequest);
      if (!this.mClosed)
        try {
          LightsManager.this.mService.setLightStates(this.mToken, param1LightsRequest.mLightIds, param1LightsRequest.mLightStates);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/LightsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */