package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.dreams.IDreamManager;

public class DreamManager {
  private final Context mContext;
  
  private final IDreamManager mService = IDreamManager.Stub.asInterface(ServiceManager.getServiceOrThrow("dreams"));
  
  public DreamManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
  }
  
  public boolean isDreaming() {
    try {
      return this.mService.isDreaming();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      return false;
    } 
  }
  
  public void setActiveDream(ComponentName paramComponentName) {
    try {
      this.mService.setDreamComponentsForUser(this.mContext.getUserId(), new ComponentName[] { paramComponentName });
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startDream(ComponentName paramComponentName) {
    try {
      this.mService.dream();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void stopDream() {
    try {
      this.mService.awaken();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DreamManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */