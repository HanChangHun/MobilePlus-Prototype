package android.app;

import android.os.ServiceManager;
import android.view.autofill.AutofillManager;
import android.view.autofill.IAutoFillManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AutofillManager> {
  public AutofillManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IAutoFillManager iAutoFillManager = IAutoFillManager.Stub.asInterface(ServiceManager.getService("autofill"));
    return new AutofillManager(paramContextImpl.getOuterContext(), iAutoFillManager);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$95.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */