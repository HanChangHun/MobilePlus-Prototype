package android.app;

class TemplateBindResult {
  int mIconMarginEnd;
  
  boolean mRightIconContainerVisible;
  
  private TemplateBindResult() {}
  
  public int getIconMarginEnd() {
    return this.mIconMarginEnd;
  }
  
  public boolean isRightIconContainerVisible() {
    return this.mRightIconContainerVisible;
  }
  
  public void setIconMarginEnd(int paramInt) {
    this.mIconMarginEnd = paramInt;
  }
  
  public void setRightIconContainerVisible(boolean paramBoolean) {
    this.mRightIconContainerVisible = paramBoolean;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$TemplateBindResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */