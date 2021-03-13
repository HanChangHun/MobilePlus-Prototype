package android.app.admin;

public abstract class InstallSystemUpdateCallback {
  public static final int UPDATE_ERROR_BATTERY_LOW = 5;
  
  public static final int UPDATE_ERROR_FILE_NOT_FOUND = 4;
  
  public static final int UPDATE_ERROR_INCORRECT_OS_VERSION = 2;
  
  public static final int UPDATE_ERROR_UNKNOWN = 1;
  
  public static final int UPDATE_ERROR_UPDATE_FILE_INVALID = 3;
  
  public void onInstallUpdateError(int paramInt, String paramString) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyManager$InstallSystemUpdateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */