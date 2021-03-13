package android.filterfw.core;

public class NativeBuffer {
  private Frame mAttachedFrame;
  
  private long mDataPointer = 0L;
  
  private boolean mOwnsData = false;
  
  private int mRefCount = 1;
  
  private int mSize = 0;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  public NativeBuffer() {}
  
  public NativeBuffer(int paramInt) {
    allocate(getElementSize() * paramInt);
    this.mOwnsData = true;
  }
  
  private native boolean allocate(int paramInt);
  
  private native boolean deallocate(boolean paramBoolean);
  
  private native boolean nativeCopyTo(NativeBuffer paramNativeBuffer);
  
  protected void assertReadable() {
    if (this.mDataPointer != 0L && this.mSize != 0) {
      Frame frame = this.mAttachedFrame;
      if (frame == null || frame.hasNativeAllocation())
        return; 
    } 
    throw new NullPointerException("Attempting to read from null data frame!");
  }
  
  protected void assertWritable() {
    if (!isReadOnly())
      return; 
    throw new RuntimeException("Attempting to modify read-only native (structured) data!");
  }
  
  void attachToFrame(Frame paramFrame) {
    this.mAttachedFrame = paramFrame;
  }
  
  public int count() {
    boolean bool;
    if (this.mDataPointer != 0L) {
      bool = this.mSize / getElementSize();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getElementSize() {
    return 1;
  }
  
  public boolean isReadOnly() {
    boolean bool;
    Frame frame = this.mAttachedFrame;
    if (frame != null) {
      bool = frame.isReadOnly();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public NativeBuffer mutableCopy() {
    try {
      NativeBuffer nativeBuffer = (NativeBuffer)getClass().newInstance();
      if (this.mSize <= 0 || nativeCopyTo(nativeBuffer))
        return nativeBuffer; 
      throw new RuntimeException("Failed to copy NativeBuffer to mutable instance!");
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to allocate a copy of ");
      stringBuilder.append(getClass());
      stringBuilder.append("! Make sure the class has a default constructor!");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  public NativeBuffer release() {
    int i = 0;
    Frame frame = this.mAttachedFrame;
    boolean bool = false;
    int j = 0;
    if (frame != null) {
      i = j;
      if (frame.release() == null)
        i = 1; 
    } else if (this.mOwnsData) {
      j = this.mRefCount - 1;
      this.mRefCount = j;
      i = bool;
      if (j == 0)
        i = 1; 
    } 
    if (i != 0) {
      deallocate(this.mOwnsData);
      return null;
    } 
    return this;
  }
  
  public NativeBuffer retain() {
    Frame frame = this.mAttachedFrame;
    if (frame != null) {
      frame.retain();
    } else if (this.mOwnsData) {
      this.mRefCount++;
    } 
    return this;
  }
  
  public int size() {
    return this.mSize;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/NativeBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */