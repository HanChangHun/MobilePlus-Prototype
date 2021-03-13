package android.content.pm;

public abstract class SessionCallback {
  public abstract void onActiveChanged(int paramInt, boolean paramBoolean);
  
  public abstract void onBadgingChanged(int paramInt);
  
  public abstract void onCreated(int paramInt);
  
  public abstract void onFinished(int paramInt, boolean paramBoolean);
  
  public abstract void onProgressChanged(int paramInt, float paramFloat);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$SessionCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */