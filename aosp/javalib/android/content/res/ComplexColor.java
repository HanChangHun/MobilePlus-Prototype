package android.content.res;

public abstract class ComplexColor {
  private int mChangingConfigurations;
  
  public abstract boolean canApplyTheme();
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public abstract ConstantState<ComplexColor> getConstantState();
  
  public abstract int getDefaultColor();
  
  public boolean isStateful() {
    return false;
  }
  
  public abstract ComplexColor obtainForTheme(Resources.Theme paramTheme);
  
  final void setBaseChangingConfigurations(int paramInt) {
    this.mChangingConfigurations = paramInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ComplexColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */