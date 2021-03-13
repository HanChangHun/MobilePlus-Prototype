package android.hardware;

import android.graphics.GraphicBuffer;
import android.os.Parcel;
import android.os.Parcelable;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.CloseGuard;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import libcore.util.NativeAllocationRegistry;

public final class HardwareBuffer implements Parcelable, AutoCloseable {
  public static final int BLOB = 33;
  
  public static final Parcelable.Creator<HardwareBuffer> CREATOR = new Parcelable.Creator<HardwareBuffer>() {
      public HardwareBuffer createFromParcel(Parcel param1Parcel) {
        long l = HardwareBuffer.nReadHardwareBufferFromParcel(param1Parcel);
        return (l != 0L) ? new HardwareBuffer(l) : null;
      }
      
      public HardwareBuffer[] newArray(int param1Int) {
        return new HardwareBuffer[param1Int];
      }
    };
  
  public static final int DS_24UI8 = 50;
  
  public static final int DS_FP32UI8 = 52;
  
  public static final int D_16 = 48;
  
  public static final int D_24 = 49;
  
  public static final int D_FP32 = 51;
  
  private static final long NATIVE_HARDWARE_BUFFER_SIZE = 232L;
  
  public static final int RGBA_1010102 = 43;
  
  public static final int RGBA_8888 = 1;
  
  public static final int RGBA_FP16 = 22;
  
  public static final int RGBX_8888 = 2;
  
  public static final int RGB_565 = 4;
  
  public static final int RGB_888 = 3;
  
  public static final int S_UI8 = 53;
  
  public static final long USAGE_CPU_READ_OFTEN = 3L;
  
  public static final long USAGE_CPU_READ_RARELY = 2L;
  
  public static final long USAGE_CPU_WRITE_OFTEN = 48L;
  
  public static final long USAGE_CPU_WRITE_RARELY = 32L;
  
  public static final long USAGE_GPU_COLOR_OUTPUT = 512L;
  
  public static final long USAGE_GPU_CUBE_MAP = 33554432L;
  
  public static final long USAGE_GPU_DATA_BUFFER = 16777216L;
  
  public static final long USAGE_GPU_MIPMAP_COMPLETE = 67108864L;
  
  public static final long USAGE_GPU_SAMPLED_IMAGE = 256L;
  
  public static final long USAGE_PROTECTED_CONTENT = 16384L;
  
  public static final long USAGE_SENSOR_DIRECT_DATA = 8388608L;
  
  public static final long USAGE_VIDEO_ENCODE = 65536L;
  
  public static final int YCBCR_420_888 = 35;
  
  private Runnable mCleaner;
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private long mNativeObject;
  
  private HardwareBuffer(long paramLong) {
    this.mNativeObject = paramLong;
    this.mCleaner = (new NativeAllocationRegistry(HardwareBuffer.class.getClassLoader(), nGetNativeFinalizer(), 232L)).registerNativeAllocation(this, this.mNativeObject);
    this.mCloseGuard.open("close");
  }
  
  public static HardwareBuffer create(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong) {
    if (isSupportedFormat(paramInt3)) {
      if (paramInt1 > 0) {
        if (paramInt2 > 0) {
          if (paramInt4 > 0) {
            if (paramInt3 != 33 || paramInt2 == 1) {
              paramLong = nCreateHardwareBuffer(paramInt1, paramInt2, paramInt3, paramInt4, paramLong);
              if (paramLong != 0L)
                return new HardwareBuffer(paramLong); 
              throw new IllegalArgumentException("Unable to create a HardwareBuffer, either the dimensions passed were too large, too many image layers were requested, or an invalid set of usage flags or invalid format was passed");
            } 
            throw new IllegalArgumentException("Height must be 1 when using the BLOB format");
          } 
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append("Invalid layer count ");
          stringBuilder3.append(paramInt4);
          throw new IllegalArgumentException(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid height ");
        stringBuilder2.append(paramInt2);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid width ");
      stringBuilder1.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid pixel format ");
    stringBuilder.append(paramInt3);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static HardwareBuffer createFromGraphicBuffer(GraphicBuffer paramGraphicBuffer) {
    return new HardwareBuffer(nCreateFromGraphicBuffer(paramGraphicBuffer));
  }
  
  public static boolean isSupported(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong) {
    if (isSupportedFormat(paramInt3)) {
      if (paramInt1 > 0) {
        if (paramInt2 > 0) {
          if (paramInt4 > 0) {
            if (paramInt3 != 33 || paramInt2 == 1)
              return nIsSupported(paramInt1, paramInt2, paramInt3, paramInt4, paramLong); 
            throw new IllegalArgumentException("Height must be 1 when using the BLOB format");
          } 
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append("Invalid layer count ");
          stringBuilder3.append(paramInt4);
          throw new IllegalArgumentException(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid height ");
        stringBuilder2.append(paramInt2);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid width ");
      stringBuilder1.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid pixel format ");
    stringBuilder.append(paramInt3);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static boolean isSupportedFormat(int paramInt) {
    if (paramInt != 1 && paramInt != 2 && paramInt != 3 && paramInt != 4 && paramInt != 22 && paramInt != 33 && paramInt != 35 && paramInt != 43)
      switch (paramInt) {
        default:
          return false;
        case 48:
        case 49:
        case 50:
        case 51:
        case 52:
        case 53:
          break;
      }  
    return true;
  }
  
  private static native long nCreateFromGraphicBuffer(GraphicBuffer paramGraphicBuffer);
  
  private static native long nCreateHardwareBuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
  
  @FastNative
  private static native int nGetFormat(long paramLong);
  
  @FastNative
  private static native int nGetHeight(long paramLong);
  
  @FastNative
  private static native int nGetLayers(long paramLong);
  
  private static native long nGetNativeFinalizer();
  
  @FastNative
  private static native long nGetUsage(long paramLong);
  
  @FastNative
  private static native int nGetWidth(long paramLong);
  
  private static native boolean nIsSupported(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
  
  private static native long nReadHardwareBufferFromParcel(Parcel paramParcel);
  
  private static native void nWriteHardwareBufferToParcel(long paramLong, Parcel paramParcel);
  
  public void close() {
    if (!isClosed()) {
      this.mCloseGuard.close();
      this.mNativeObject = 0L;
      this.mCleaner.run();
      this.mCleaner = null;
    } 
  }
  
  public int describeContents() {
    return 1;
  }
  
  protected void finalize() throws Throwable {
    try {
      this.mCloseGuard.warnIfOpen();
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getFormat() {
    if (!isClosed())
      return nGetFormat(this.mNativeObject); 
    throw new IllegalStateException("This HardwareBuffer has been closed and its format cannot be obtained.");
  }
  
  public int getHeight() {
    if (!isClosed())
      return nGetHeight(this.mNativeObject); 
    throw new IllegalStateException("This HardwareBuffer has been closed and its height cannot be obtained.");
  }
  
  public int getLayers() {
    if (!isClosed())
      return nGetLayers(this.mNativeObject); 
    throw new IllegalStateException("This HardwareBuffer has been closed and its layer count cannot be obtained.");
  }
  
  public long getUsage() {
    if (!isClosed())
      return nGetUsage(this.mNativeObject); 
    throw new IllegalStateException("This HardwareBuffer has been closed and its usage cannot be obtained.");
  }
  
  public int getWidth() {
    if (!isClosed())
      return nGetWidth(this.mNativeObject); 
    throw new IllegalStateException("This HardwareBuffer has been closed and its width cannot be obtained.");
  }
  
  public boolean isClosed() {
    boolean bool;
    if (this.mNativeObject == 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (!isClosed()) {
      nWriteHardwareBufferToParcel(this.mNativeObject, paramParcel);
      return;
    } 
    throw new IllegalStateException("This HardwareBuffer has been closed and cannot be written to a parcel.");
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Format {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Usage {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/HardwareBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */