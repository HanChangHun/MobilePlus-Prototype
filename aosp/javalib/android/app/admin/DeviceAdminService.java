package android.app.admin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DeviceAdminService extends Service {
  private final IDeviceAdminServiceImpl mImpl = new IDeviceAdminServiceImpl();
  
  public final IBinder onBind(Intent paramIntent) {
    return this.mImpl.asBinder();
  }
  
  private class IDeviceAdminServiceImpl extends IDeviceAdminService.Stub {
    private IDeviceAdminServiceImpl() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DeviceAdminService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */