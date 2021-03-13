package android.graphics.text;

public class ParagraphConstraints {
  private float mDefaultTabStop = 0.0F;
  
  private float mFirstWidth = 0.0F;
  
  private int mFirstWidthLineCount = 0;
  
  private float[] mVariableTabStops = null;
  
  private float mWidth = 0.0F;
  
  public float getDefaultTabStop() {
    return this.mDefaultTabStop;
  }
  
  public float getFirstWidth() {
    return this.mFirstWidth;
  }
  
  public int getFirstWidthLineCount() {
    return this.mFirstWidthLineCount;
  }
  
  public float[] getTabStops() {
    return this.mVariableTabStops;
  }
  
  public float getWidth() {
    return this.mWidth;
  }
  
  public void setIndent(float paramFloat, int paramInt) {
    this.mFirstWidth = paramFloat;
    this.mFirstWidthLineCount = paramInt;
  }
  
  public void setTabStops(float[] paramArrayOffloat, float paramFloat) {
    this.mVariableTabStops = paramArrayOffloat;
    this.mDefaultTabStop = paramFloat;
  }
  
  public void setWidth(float paramFloat) {
    this.mWidth = paramFloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/text/LineBreaker$ParagraphConstraints.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */