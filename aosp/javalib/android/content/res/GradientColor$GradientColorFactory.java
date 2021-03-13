package android.content.res;

class GradientColorFactory extends ConstantState<ComplexColor> {
  private final GradientColor mSrc;
  
  public GradientColorFactory(GradientColor paramGradientColor) {
    this.mSrc = paramGradientColor;
  }
  
  public int getChangingConfigurations() {
    return GradientColor.access$000(this.mSrc);
  }
  
  public GradientColor newInstance() {
    return this.mSrc;
  }
  
  public GradientColor newInstance(Resources paramResources, Resources.Theme paramTheme) {
    return this.mSrc.obtainForTheme(paramTheme);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/GradientColor$GradientColorFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */