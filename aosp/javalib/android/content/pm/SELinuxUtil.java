package android.content.pm;

public final class SELinuxUtil {
  public static final String COMPLETE_STR = ":complete";
  
  private static final String INSTANT_APP_STR = ":ephemeralapp";
  
  public static String assignSeinfoUser(PackageUserState paramPackageUserState) {
    return paramPackageUserState.instantApp ? ":ephemeralapp:complete" : ":complete";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SELinuxUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */