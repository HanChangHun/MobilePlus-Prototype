package android.content;

public class MutableContextWrapper extends ContextWrapper {
  public MutableContextWrapper(Context paramContext) {
    super(paramContext);
  }
  
  public void setBaseContext(Context paramContext) {
    this.mBase = paramContext;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/MutableContextWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */