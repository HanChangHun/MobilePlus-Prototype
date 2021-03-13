package android.graphics;

import android.hardware.HardwareBuffer;
import android.os.Parcel;
import android.os.Parcelable;

public class GraphicBuffer implements Parcelable {
  public static final Parcelable.Creator<GraphicBuffer> CREATOR = new Parcelable.Creator<GraphicBuffer>() {
      public GraphicBuffer createFromParcel(Parcel param1Parcel) {
        int i = param1Parcel.readInt();
        int j = param1Parcel.readInt();
        int k = param1Parcel.readInt();
        int m = param1Parcel.readInt();
        long l = GraphicBuffer.nReadGraphicBufferFromParcel(param1Parcel);
        return (l != 0L) ? new GraphicBuffer(i, j, k, m, l) : null;
      }
      
      public GraphicBuffer[] newArray(int param1Int) {
        return new GraphicBuffer[param1Int];
      }
    };
  
  public static final int USAGE_HW_2D = 1024;
  
  public static final int USAGE_HW_COMPOSER = 2048;
  
  public static final int USAGE_HW_MASK = 466688;
  
  public static final int USAGE_HW_RENDER = 512;
  
  public static final int USAGE_HW_TEXTURE = 256;
  
  public static final int USAGE_HW_VIDEO_ENCODER = 65536;
  
  public static final int USAGE_PROTECTED = 16384;
  
  public static final int USAGE_SOFTWARE_MASK = 255;
  
  public static final int USAGE_SW_READ_MASK = 15;
  
  public static final int USAGE_SW_READ_NEVER = 0;
  
  public static final int USAGE_SW_READ_OFTEN = 3;
  
  public static final int USAGE_SW_READ_RARELY = 2;
  
  public static final int USAGE_SW_WRITE_MASK = 240;
  
  public static final int USAGE_SW_WRITE_NEVER = 0;
  
  public static final int USAGE_SW_WRITE_OFTEN = 48;
  
  public static final int USAGE_SW_WRITE_RARELY = 32;
  
  private Canvas mCanvas;
  
  private boolean mDestroyed;
  
  private final int mFormat;
  
  private final int mHeight;
  
  private final long mNativeObject;
  
  private int mSaveCount;
  
  private final int mUsage;
  
  private final int mWidth;
  
  private GraphicBuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mFormat = paramInt3;
    this.mUsage = paramInt4;
    this.mNativeObject = paramLong;
  }
  
  public static GraphicBuffer create(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    long l = nCreateGraphicBuffer(paramInt1, paramInt2, paramInt3, paramInt4);
    return (l != 0L) ? new GraphicBuffer(paramInt1, paramInt2, paramInt3, paramInt4, l) : null;
  }
  
  public static GraphicBuffer createFromExisting(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong) {
    paramLong = nWrapGraphicBuffer(paramLong);
    return (paramLong != 0L) ? new GraphicBuffer(paramInt1, paramInt2, paramInt3, paramInt4, paramLong) : null;
  }
  
  public static final GraphicBuffer createFromHardwareBuffer(HardwareBuffer paramHardwareBuffer) {
    return nCreateFromHardwareBuffer(paramHardwareBuffer);
  }
  
  private static native GraphicBuffer nCreateFromHardwareBuffer(HardwareBuffer paramHardwareBuffer);
  
  private static native long nCreateGraphicBuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  private static native void nDestroyGraphicBuffer(long paramLong);
  
  private static native boolean nLockCanvas(long paramLong, Canvas paramCanvas, Rect paramRect);
  
  private static native long nReadGraphicBufferFromParcel(Parcel paramParcel);
  
  private static native boolean nUnlockCanvasAndPost(long paramLong, Canvas paramCanvas);
  
  private static native long nWrapGraphicBuffer(long paramLong);
  
  private static native void nWriteGraphicBufferToParcel(long paramLong, Parcel paramParcel);
  
  public int describeContents() {
    return 0;
  }
  
  public void destroy() {
    if (!this.mDestroyed) {
      this.mDestroyed = true;
      nDestroyGraphicBuffer(this.mNativeObject);
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      if (!this.mDestroyed)
        nDestroyGraphicBuffer(this.mNativeObject); 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getFormat() {
    return this.mFormat;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int getUsage() {
    return this.mUsage;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public boolean isDestroyed() {
    return this.mDestroyed;
  }
  
  public Canvas lockCanvas() {
    return lockCanvas(null);
  }
  
  public Canvas lockCanvas(Rect paramRect) {
    if (this.mDestroyed)
      return null; 
    if (this.mCanvas == null)
      this.mCanvas = new Canvas(); 
    if (nLockCanvas(this.mNativeObject, this.mCanvas, paramRect)) {
      this.mSaveCount = this.mCanvas.save();
      return this.mCanvas;
    } 
    return null;
  }
  
  public void unlockCanvasAndPost(Canvas paramCanvas) {
    if (!this.mDestroyed) {
      Canvas canvas = this.mCanvas;
      if (canvas != null && paramCanvas == canvas) {
        paramCanvas.restoreToCount(this.mSaveCount);
        this.mSaveCount = 0;
        nUnlockCanvasAndPost(this.mNativeObject, this.mCanvas);
      } 
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (!this.mDestroyed) {
      paramParcel.writeInt(this.mWidth);
      paramParcel.writeInt(this.mHeight);
      paramParcel.writeInt(this.mFormat);
      paramParcel.writeInt(this.mUsage);
      nWriteGraphicBufferToParcel(this.mNativeObject, paramParcel);
      return;
    } 
    throw new IllegalStateException("This GraphicBuffer has been destroyed and cannot be written to a parcel.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */