package android.content.res;

class ColorStateListFactory extends ConstantState<ComplexColor> {
  private final ColorStateList mSrc;
  
  public ColorStateListFactory(ColorStateList paramColorStateList) {
    this.mSrc = paramColorStateList;
  }
  
  public int getChangingConfigurations() {
    return ColorStateList.access$000(this.mSrc);
  }
  
  public ColorStateList newInstance() {
    return this.mSrc;
  }
  
  public ColorStateList newInstance(Resources paramResources, Resources.Theme paramTheme) {
    return this.mSrc.obtainForTheme(paramTheme);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ColorStateList$ColorStateListFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */