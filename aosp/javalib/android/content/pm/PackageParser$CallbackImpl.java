package android.content.pm;

public final class CallbackImpl implements PackageParser.Callback {
  private final PackageManager mPm;
  
  public CallbackImpl(PackageManager paramPackageManager) {
    this.mPm = paramPackageManager;
  }
  
  public boolean hasFeature(String paramString) {
    return this.mPm.hasSystemFeature(paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$CallbackImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */