package android.filterfw.core;

import java.util.Arrays;

public class MutableFrameFormat extends FrameFormat {
  public MutableFrameFormat() {}
  
  public MutableFrameFormat(int paramInt1, int paramInt2) {
    super(paramInt1, paramInt2);
  }
  
  public void setBaseType(int paramInt) {
    this.mBaseType = paramInt;
    this.mBytesPerSample = bytesPerSampleOf(paramInt);
  }
  
  public void setBytesPerSample(int paramInt) {
    this.mBytesPerSample = paramInt;
    this.mSize = -1;
  }
  
  public void setDimensionCount(int paramInt) {
    this.mDimensions = new int[paramInt];
  }
  
  public void setDimensions(int paramInt) {
    this.mDimensions = new int[] { paramInt };
    this.mSize = -1;
  }
  
  public void setDimensions(int paramInt1, int paramInt2) {
    this.mDimensions = new int[] { paramInt1, paramInt2 };
    this.mSize = -1;
  }
  
  public void setDimensions(int paramInt1, int paramInt2, int paramInt3) {
    this.mDimensions = new int[] { paramInt1, paramInt2, paramInt3 };
    this.mSize = -1;
  }
  
  public void setDimensions(int[] paramArrayOfint) {
    if (paramArrayOfint == null) {
      paramArrayOfint = null;
    } else {
      paramArrayOfint = Arrays.copyOf(paramArrayOfint, paramArrayOfint.length);
    } 
    this.mDimensions = paramArrayOfint;
    this.mSize = -1;
  }
  
  public void setMetaValue(String paramString, Object paramObject) {
    if (this.mMetaData == null)
      this.mMetaData = new KeyValueMap(); 
    this.mMetaData.put(paramString, paramObject);
  }
  
  public void setObjectClass(Class paramClass) {
    this.mObjectClass = paramClass;
  }
  
  public void setTarget(int paramInt) {
    this.mTarget = paramInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/MutableFrameFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */