package android.filterfw.core;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public abstract class Frame {
  public static final int NO_BINDING = 0;
  
  public static final long TIMESTAMP_NOT_SET = -2L;
  
  public static final long TIMESTAMP_UNKNOWN = -1L;
  
  private long mBindingId = 0L;
  
  private int mBindingType = 0;
  
  private FrameFormat mFormat;
  
  private FrameManager mFrameManager;
  
  private boolean mReadOnly = false;
  
  private int mRefCount = 1;
  
  private boolean mReusable = false;
  
  private long mTimestamp = -2L;
  
  Frame(FrameFormat paramFrameFormat, FrameManager paramFrameManager) {
    this.mFormat = paramFrameFormat.mutableCopy();
    this.mFrameManager = paramFrameManager;
  }
  
  Frame(FrameFormat paramFrameFormat, FrameManager paramFrameManager, int paramInt, long paramLong) {
    this.mFormat = paramFrameFormat.mutableCopy();
    this.mFrameManager = paramFrameManager;
    this.mBindingType = paramInt;
    this.mBindingId = paramLong;
  }
  
  protected static Bitmap convertBitmapToRGBA(Bitmap paramBitmap) {
    if (paramBitmap.getConfig() == Bitmap.Config.ARGB_8888)
      return paramBitmap; 
    paramBitmap = paramBitmap.copy(Bitmap.Config.ARGB_8888, false);
    if (paramBitmap != null) {
      if (paramBitmap.getRowBytes() == paramBitmap.getWidth() * 4)
        return paramBitmap; 
      throw new RuntimeException("Unsupported row byte count in bitmap!");
    } 
    throw new RuntimeException("Error converting bitmap to RGBA!");
  }
  
  protected void assertFrameMutable() {
    if (!isReadOnly())
      return; 
    throw new RuntimeException("Attempting to modify read-only frame!");
  }
  
  final int decRefCount() {
    int i = this.mRefCount - 1;
    this.mRefCount = i;
    return i;
  }
  
  public long getBindingId() {
    return this.mBindingId;
  }
  
  public int getBindingType() {
    return this.mBindingType;
  }
  
  public abstract Bitmap getBitmap();
  
  public int getCapacity() {
    return getFormat().getSize();
  }
  
  public abstract ByteBuffer getData();
  
  public abstract float[] getFloats();
  
  public FrameFormat getFormat() {
    return this.mFormat;
  }
  
  public FrameManager getFrameManager() {
    return this.mFrameManager;
  }
  
  public abstract int[] getInts();
  
  public abstract Object getObjectValue();
  
  public int getRefCount() {
    return this.mRefCount;
  }
  
  public long getTimestamp() {
    return this.mTimestamp;
  }
  
  protected abstract boolean hasNativeAllocation();
  
  final int incRefCount() {
    int i = this.mRefCount + 1;
    this.mRefCount = i;
    return i;
  }
  
  public boolean isReadOnly() {
    return this.mReadOnly;
  }
  
  final boolean isReusable() {
    return this.mReusable;
  }
  
  final void markReadOnly() {
    this.mReadOnly = true;
  }
  
  protected void onFrameFetch() {}
  
  protected void onFrameStore() {}
  
  public Frame release() {
    FrameManager frameManager = this.mFrameManager;
    return (frameManager != null) ? frameManager.releaseFrame(this) : this;
  }
  
  protected abstract void releaseNativeAllocation();
  
  protected boolean requestResize(int[] paramArrayOfint) {
    return false;
  }
  
  protected void reset(FrameFormat paramFrameFormat) {
    this.mFormat = paramFrameFormat.mutableCopy();
    this.mReadOnly = false;
    this.mRefCount = 1;
  }
  
  public Frame retain() {
    FrameManager frameManager = this.mFrameManager;
    return (frameManager != null) ? frameManager.retainFrame(this) : this;
  }
  
  public abstract void setBitmap(Bitmap paramBitmap);
  
  public void setData(ByteBuffer paramByteBuffer) {
    setData(paramByteBuffer, 0, paramByteBuffer.limit());
  }
  
  public abstract void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  public void setData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    setData(ByteBuffer.wrap(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  public void setDataFromFrame(Frame paramFrame) {
    setData(paramFrame.getData());
  }
  
  public abstract void setFloats(float[] paramArrayOffloat);
  
  protected void setFormat(FrameFormat paramFrameFormat) {
    this.mFormat = paramFrameFormat.mutableCopy();
  }
  
  protected void setGenericObjectValue(Object paramObject) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Cannot set object value of unsupported type: ");
    stringBuilder.append(paramObject.getClass());
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public abstract void setInts(int[] paramArrayOfint);
  
  public void setObjectValue(Object paramObject) {
    assertFrameMutable();
    if (paramObject instanceof int[]) {
      setInts((int[])paramObject);
    } else if (paramObject instanceof float[]) {
      setFloats((float[])paramObject);
    } else if (paramObject instanceof ByteBuffer) {
      setData((ByteBuffer)paramObject);
    } else if (paramObject instanceof Bitmap) {
      setBitmap((Bitmap)paramObject);
    } else {
      setGenericObjectValue(paramObject);
    } 
  }
  
  protected void setReusable(boolean paramBoolean) {
    this.mReusable = paramBoolean;
  }
  
  public void setTimestamp(long paramLong) {
    this.mTimestamp = paramLong;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/Frame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */