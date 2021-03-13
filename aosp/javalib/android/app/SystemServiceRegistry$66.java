package android.app;

import android.os.ServiceManager;
import android.os.UserHandle;
import android.print.IPrintManager;
import android.print.PrintManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<PrintManager> {
  public PrintManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IPrintManager iPrintManager = null;
    if (paramContextImpl.getPackageManager().hasSystemFeature("android.software.print"))
      iPrintManager = IPrintManager.Stub.asInterface(ServiceManager.getServiceOrThrow("print")); 
    int i = paramContextImpl.getUserId();
    int j = UserHandle.getAppId((paramContextImpl.getApplicationInfo()).uid);
    return new PrintManager(paramContextImpl.getOuterContext(), iPrintManager, i, j);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$66.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */