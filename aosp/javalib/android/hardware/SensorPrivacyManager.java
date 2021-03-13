package android.hardware;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;

public final class SensorPrivacyManager {
  private static SensorPrivacyManager sInstance;
  
  private static final Object sInstanceLock = new Object();
  
  private final Context mContext;
  
  private final ArrayMap<OnSensorPrivacyChangedListener, ISensorPrivacyListener> mListeners;
  
  private final ISensorPrivacyManager mService;
  
  private SensorPrivacyManager(Context paramContext, ISensorPrivacyManager paramISensorPrivacyManager) {
    this.mContext = paramContext;
    this.mService = paramISensorPrivacyManager;
    this.mListeners = new ArrayMap();
  }
  
  public static SensorPrivacyManager getInstance(Context paramContext) {
    synchronized (sInstanceLock) {
      SensorPrivacyManager sensorPrivacyManager = sInstance;
      if (sensorPrivacyManager == null)
        try {
          ISensorPrivacyManager iSensorPrivacyManager = ISensorPrivacyManager.Stub.asInterface(ServiceManager.getServiceOrThrow("sensor_privacy"));
          SensorPrivacyManager sensorPrivacyManager1 = new SensorPrivacyManager();
          this(paramContext, iSensorPrivacyManager);
          sInstance = sensorPrivacyManager1;
        } catch (android.os.ServiceManager.ServiceNotFoundException serviceNotFoundException) {
          IllegalStateException illegalStateException = new IllegalStateException();
          this((Throwable)serviceNotFoundException);
          throw illegalStateException;
        }  
      return sInstance;
    } 
  }
  
  public void addSensorPrivacyListener(OnSensorPrivacyChangedListener paramOnSensorPrivacyChangedListener) {
    synchronized (this.mListeners) {
      ISensorPrivacyListener iSensorPrivacyListener1 = (ISensorPrivacyListener)this.mListeners.get(paramOnSensorPrivacyChangedListener);
      ISensorPrivacyListener iSensorPrivacyListener2 = iSensorPrivacyListener1;
      if (iSensorPrivacyListener1 == null) {
        iSensorPrivacyListener2 = new ISensorPrivacyListener.Stub() {
            public void onSensorPrivacyChanged(boolean param1Boolean) {
              listener.onSensorPrivacyChanged(param1Boolean);
            }
          };
        super(this, paramOnSensorPrivacyChangedListener);
        this.mListeners.put(paramOnSensorPrivacyChangedListener, iSensorPrivacyListener2);
      } 
      try {
        this.mService.addSensorPrivacyListener(iSensorPrivacyListener2);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public boolean isSensorPrivacyEnabled() {
    try {
      return this.mService.isSensorPrivacyEnabled();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeSensorPrivacyListener(OnSensorPrivacyChangedListener paramOnSensorPrivacyChangedListener) {
    synchronized (this.mListeners) {
      ISensorPrivacyListener iSensorPrivacyListener = (ISensorPrivacyListener)this.mListeners.get(paramOnSensorPrivacyChangedListener);
      if (iSensorPrivacyListener != null) {
        this.mListeners.remove(iSensorPrivacyListener);
        try {
          this.mService.removeSensorPrivacyListener(iSensorPrivacyListener);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } 
      return;
    } 
  }
  
  public void setSensorPrivacy(boolean paramBoolean) {
    try {
      this.mService.setSensorPrivacy(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static interface OnSensorPrivacyChangedListener {
    void onSensorPrivacyChanged(boolean param1Boolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SensorPrivacyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */