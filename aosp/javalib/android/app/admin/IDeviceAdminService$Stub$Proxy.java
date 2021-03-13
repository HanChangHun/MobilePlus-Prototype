package android.app.admin;

import android.os.IBinder;

class Proxy implements IDeviceAdminService {
  public static IDeviceAdminService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.admin.IDeviceAdminService";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IDeviceAdminService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */