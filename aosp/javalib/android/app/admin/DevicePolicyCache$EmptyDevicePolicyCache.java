package android.app.admin;

class EmptyDevicePolicyCache extends DevicePolicyCache {
  private static final EmptyDevicePolicyCache INSTANCE = new EmptyDevicePolicyCache();
  
  public int getPasswordQuality(int paramInt) {
    return 0;
  }
  
  public boolean isScreenCaptureAllowed(int paramInt, boolean paramBoolean) {
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyCache$EmptyDevicePolicyCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */