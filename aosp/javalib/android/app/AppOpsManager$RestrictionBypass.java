package android.app;

public class RestrictionBypass {
  public static RestrictionBypass UNRESTRICTED = new RestrictionBypass(true, true);
  
  public boolean isPrivileged;
  
  public boolean isRecordAudioRestrictionExcept;
  
  public RestrictionBypass(boolean paramBoolean1, boolean paramBoolean2) {
    this.isPrivileged = paramBoolean1;
    this.isRecordAudioRestrictionExcept = paramBoolean2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$RestrictionBypass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */