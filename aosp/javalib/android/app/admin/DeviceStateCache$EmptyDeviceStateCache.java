package android.app.admin;

class EmptyDeviceStateCache extends DeviceStateCache {
  private static final EmptyDeviceStateCache INSTANCE = new EmptyDeviceStateCache();
  
  public boolean isDeviceProvisioned() {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DeviceStateCache$EmptyDeviceStateCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */