package android.app;

import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Singleton;

public class UriGrantsManager {
  private static final Singleton<IUriGrantsManager> IUriGrantsManagerSingleton = new Singleton<IUriGrantsManager>() {
      protected IUriGrantsManager create() {
        return IUriGrantsManager.Stub.asInterface(ServiceManager.getService("uri_grants"));
      }
    };
  
  private final Context mContext;
  
  UriGrantsManager(Context paramContext, Handler paramHandler) {
    this.mContext = paramContext;
  }
  
  public static IUriGrantsManager getService() {
    return (IUriGrantsManager)IUriGrantsManagerSingleton.get();
  }
  
  public void clearGrantedUriPermissions(String paramString) {
    try {
      getService().clearGrantedUriPermissions(paramString, this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ParceledListSlice<GrantedUriPermission> getGrantedUriPermissions(String paramString) {
    try {
      return getService().getGrantedUriPermissions(paramString, this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UriGrantsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */