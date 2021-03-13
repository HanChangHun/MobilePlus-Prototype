package android.app;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;
import java.lang.ref.WeakReference;

class InnerConnection extends IServiceConnection.Stub {
  final WeakReference<LoadedApk.ServiceDispatcher> mDispatcher;
  
  InnerConnection(LoadedApk.ServiceDispatcher paramServiceDispatcher) {
    this.mDispatcher = new WeakReference<>(paramServiceDispatcher);
  }
  
  public void connected(ComponentName paramComponentName, IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    LoadedApk.ServiceDispatcher serviceDispatcher = this.mDispatcher.get();
    if (serviceDispatcher != null)
      serviceDispatcher.connected(paramComponentName, paramIBinder, paramBoolean); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ServiceDispatcher$InnerConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */