package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPredictionManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void createPredictionSession(AppPredictionContext paramAppPredictionContext, AppPredictionSessionId paramAppPredictionSessionId, IBinder paramIBinder) throws RemoteException {}
  
  public void notifyAppTargetEvent(AppPredictionSessionId paramAppPredictionSessionId, AppTargetEvent paramAppTargetEvent) throws RemoteException {}
  
  public void notifyLaunchLocationShown(AppPredictionSessionId paramAppPredictionSessionId, String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {}
  
  public void onDestroyPredictionSession(AppPredictionSessionId paramAppPredictionSessionId) throws RemoteException {}
  
  public void registerPredictionUpdates(AppPredictionSessionId paramAppPredictionSessionId, IPredictionCallback paramIPredictionCallback) throws RemoteException {}
  
  public void requestPredictionUpdate(AppPredictionSessionId paramAppPredictionSessionId) throws RemoteException {}
  
  public void sortAppTargets(AppPredictionSessionId paramAppPredictionSessionId, ParceledListSlice paramParceledListSlice, IPredictionCallback paramIPredictionCallback) throws RemoteException {}
  
  public void unregisterPredictionUpdates(AppPredictionSessionId paramAppPredictionSessionId, IPredictionCallback paramIPredictionCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */