package android.app;

import android.hardware.face.FaceManager;
import android.hardware.face.IFaceService;
import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<FaceManager> {
  public FaceManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder;
    if ((paramContextImpl.getApplicationInfo()).targetSdkVersion >= 26) {
      iBinder = ServiceManager.getServiceOrThrow("face");
    } else {
      iBinder = ServiceManager.getService("face");
    } 
    IFaceService iFaceService = IFaceService.Stub.asInterface(iBinder);
    return new FaceManager(paramContextImpl.getOuterContext(), iFaceService);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$72.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */