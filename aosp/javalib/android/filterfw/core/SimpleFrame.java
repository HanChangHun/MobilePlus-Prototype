package android.filterfw.core;

import android.filterfw.format.ObjectFormat;
import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class SimpleFrame extends Frame {
  private Object mObject;
  
  SimpleFrame(FrameFormat paramFrameFormat, FrameManager paramFrameManager) {
    super(paramFrameFormat, paramFrameManager);
    initWithFormat(paramFrameFormat);
    setReusable(false);
  }
  
  private void initWithFormat(FrameFormat paramFrameFormat) {
    int i = paramFrameFormat.getLength();
    int j = paramFrameFormat.getBaseType();
    if (j != 2) {
      if (j != 3) {
        if (j != 4) {
          if (j != 5) {
            if (j != 6) {
              this.mObject = null;
            } else {
              this.mObject = new double[i];
            } 
          } else {
            this.mObject = new float[i];
          } 
        } else {
          this.mObject = new int[i];
        } 
      } else {
        this.mObject = new short[i];
      } 
    } else {
      this.mObject = new byte[i];
    } 
  }
  
  private void setFormatObjectClass(Class paramClass) {
    MutableFrameFormat mutableFrameFormat = getFormat().mutableCopy();
    mutableFrameFormat.setObjectClass(paramClass);
    setFormat(mutableFrameFormat);
  }
  
  static SimpleFrame wrapObject(Object paramObject, FrameManager paramFrameManager) {
    SimpleFrame simpleFrame = new SimpleFrame(ObjectFormat.fromObject(paramObject, 1), paramFrameManager);
    simpleFrame.setObjectValue(paramObject);
    return simpleFrame;
  }
  
  public Bitmap getBitmap() {
    Object object = this.mObject;
    if (object instanceof Bitmap) {
      object = object;
    } else {
      object = null;
    } 
    return (Bitmap)object;
  }
  
  public ByteBuffer getData() {
    Object object = this.mObject;
    if (object instanceof ByteBuffer) {
      object = object;
    } else {
      object = null;
    } 
    return (ByteBuffer)object;
  }
  
  public float[] getFloats() {
    Object object = this.mObject;
    if (object instanceof float[]) {
      object = object;
    } else {
      object = null;
    } 
    return (float[])object;
  }
  
  public int[] getInts() {
    Object object = this.mObject;
    if (object instanceof int[]) {
      object = object;
    } else {
      object = null;
    } 
    return (int[])object;
  }
  
  public Object getObjectValue() {
    return this.mObject;
  }
  
  protected boolean hasNativeAllocation() {
    return false;
  }
  
  protected void releaseNativeAllocation() {}
  
  public void setBitmap(Bitmap paramBitmap) {
    assertFrameMutable();
    setGenericObjectValue(paramBitmap);
  }
  
  public void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) {
    assertFrameMutable();
    setGenericObjectValue(ByteBuffer.wrap(paramByteBuffer.array(), paramInt1, paramInt2));
  }
  
  public void setFloats(float[] paramArrayOffloat) {
    assertFrameMutable();
    setGenericObjectValue(paramArrayOffloat);
  }
  
  protected void setGenericObjectValue(Object paramObject) {
    FrameFormat frameFormat = getFormat();
    if (frameFormat.getObjectClass() == null) {
      setFormatObjectClass(paramObject.getClass());
    } else if (!frameFormat.getObjectClass().isAssignableFrom(paramObject.getClass())) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Attempting to set object value of type '");
      stringBuilder.append(paramObject.getClass());
      stringBuilder.append("' on SimpleFrame of type '");
      stringBuilder.append(frameFormat.getObjectClass());
      stringBuilder.append("'!");
      throw new RuntimeException(stringBuilder.toString());
    } 
    this.mObject = paramObject;
  }
  
  public void setInts(int[] paramArrayOfint) {
    assertFrameMutable();
    setGenericObjectValue(paramArrayOfint);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SimpleFrame (");
    stringBuilder.append(getFormat());
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SimpleFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */