package android.app.admin;

import com.android.server.LocalServices;

public abstract class DevicePolicyCache {
  public static DevicePolicyCache getInstance() {
    DevicePolicyCache devicePolicyCache;
    DevicePolicyManagerInternal devicePolicyManagerInternal = (DevicePolicyManagerInternal)LocalServices.getService(DevicePolicyManagerInternal.class);
    if (devicePolicyManagerInternal != null) {
      devicePolicyCache = devicePolicyManagerInternal.getDevicePolicyCache();
    } else {
      devicePolicyCache = EmptyDevicePolicyCache.INSTANCE;
    } 
    return devicePolicyCache;
  }
  
  public abstract int getPasswordQuality(int paramInt);
  
  public abstract boolean isScreenCaptureAllowed(int paramInt, boolean paramBoolean);
  
  private static class EmptyDevicePolicyCache extends DevicePolicyCache {
    private static final EmptyDevicePolicyCache INSTANCE = new EmptyDevicePolicyCache();
    
    public int getPasswordQuality(int param1Int) {
      return 0;
    }
    
    public boolean isScreenCaptureAllowed(int param1Int, boolean param1Boolean) {
      return true;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */