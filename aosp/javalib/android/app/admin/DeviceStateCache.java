package android.app.admin;

import com.android.server.LocalServices;

public abstract class DeviceStateCache {
  public static DeviceStateCache getInstance() {
    DeviceStateCache deviceStateCache;
    DevicePolicyManagerInternal devicePolicyManagerInternal = (DevicePolicyManagerInternal)LocalServices.getService(DevicePolicyManagerInternal.class);
    if (devicePolicyManagerInternal != null) {
      deviceStateCache = devicePolicyManagerInternal.getDeviceStateCache();
    } else {
      deviceStateCache = EmptyDeviceStateCache.INSTANCE;
    } 
    return deviceStateCache;
  }
  
  public abstract boolean isDeviceProvisioned();
  
  private static class EmptyDeviceStateCache extends DeviceStateCache {
    private static final EmptyDeviceStateCache INSTANCE = new EmptyDeviceStateCache();
    
    public boolean isDeviceProvisioned() {
      return false;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DeviceStateCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */