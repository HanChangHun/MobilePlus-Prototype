package android.graphics;

import android.content.res.ResourcesImpl;
import android.hardware.HardwareBuffer;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;
import android.os.Trace;
import android.util.DisplayMetrics;
import android.util.Half;
import android.util.Log;
import android.view.ThreadedRenderer;
import dalvik.annotation.optimization.CriticalNative;
import java.io.OutputStream;
import java.nio.Buffer;
import libcore.util.NativeAllocationRegistry;

public final class Bitmap implements Parcelable {
  public static final Parcelable.Creator<Bitmap> CREATOR;
  
  public static final int DENSITY_NONE = 0;
  
  private static final long NATIVE_ALLOCATION_SIZE = 32L;
  
  private static final String TAG = "Bitmap";
  
  private static final int WORKING_COMPRESS_STORAGE = 4096;
  
  private static volatile int sDefaultDensity = -1;
  
  public static volatile int sPreloadTracingNumInstantiatedBitmaps;
  
  public static volatile long sPreloadTracingTotalBitmapsSize;
  
  private ColorSpace mColorSpace;
  
  public int mDensity = getDefaultDensity();
  
  private int mHeight;
  
  private final long mNativePtr;
  
  private byte[] mNinePatchChunk;
  
  private NinePatch.InsetStruct mNinePatchInsets;
  
  private boolean mRecycled;
  
  private boolean mRequestPremultiplied;
  
  private int mWidth;
  
  static {
    CREATOR = new Parcelable.Creator<Bitmap>() {
        public Bitmap createFromParcel(Parcel param1Parcel) {
          Bitmap bitmap = Bitmap.nativeCreateFromParcel(param1Parcel);
          if (bitmap != null)
            return bitmap; 
          throw new RuntimeException("Failed to unparcel Bitmap");
        }
        
        public Bitmap[] newArray(int param1Int) {
          return new Bitmap[param1Int];
        }
      };
  }
  
  Bitmap(long paramLong, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, byte[] paramArrayOfbyte, NinePatch.InsetStruct paramInsetStruct) {
    this(paramLong, paramInt1, paramInt2, paramInt3, paramBoolean, paramArrayOfbyte, paramInsetStruct, true);
  }
  
  Bitmap(long paramLong, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, byte[] paramArrayOfbyte, NinePatch.InsetStruct paramInsetStruct, boolean paramBoolean2) {
    if (paramLong != 0L) {
      NativeAllocationRegistry nativeAllocationRegistry;
      this.mWidth = paramInt1;
      this.mHeight = paramInt2;
      this.mRequestPremultiplied = paramBoolean1;
      this.mNinePatchChunk = paramArrayOfbyte;
      this.mNinePatchInsets = paramInsetStruct;
      if (paramInt3 >= 0)
        this.mDensity = paramInt3; 
      this.mNativePtr = paramLong;
      paramInt1 = getAllocationByteCount();
      if (paramBoolean2) {
        nativeAllocationRegistry = NativeAllocationRegistry.createMalloced(Bitmap.class.getClassLoader(), nativeGetNativeFinalizer(), paramInt1);
      } else {
        nativeAllocationRegistry = NativeAllocationRegistry.createNonmalloced(Bitmap.class.getClassLoader(), nativeGetNativeFinalizer(), paramInt1);
      } 
      nativeAllocationRegistry.registerNativeAllocation(this, paramLong);
      if (ResourcesImpl.TRACE_FOR_DETAILED_PRELOAD) {
        sPreloadTracingNumInstantiatedBitmaps++;
        paramLong = paramInt1;
        sPreloadTracingTotalBitmapsSize += paramLong + 32L;
      } 
      return;
    } 
    throw new RuntimeException("internal error: native bitmap is 0");
  }
  
  private void checkHardware(String paramString) {
    if (getConfig() != Config.HARDWARE)
      return; 
    throw new IllegalStateException(paramString);
  }
  
  private void checkPixelAccess(int paramInt1, int paramInt2) {
    checkXYSign(paramInt1, paramInt2);
    if (paramInt1 < getWidth()) {
      if (paramInt2 < getHeight())
        return; 
      throw new IllegalArgumentException("y must be < bitmap.height()");
    } 
    throw new IllegalArgumentException("x must be < bitmap.width()");
  }
  
  private void checkPixelsAccess(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfint) {
    checkXYSign(paramInt1, paramInt2);
    if (paramInt3 >= 0) {
      if (paramInt4 >= 0) {
        if (paramInt1 + paramInt3 <= getWidth()) {
          if (paramInt2 + paramInt4 <= getHeight()) {
            if (Math.abs(paramInt6) >= paramInt3) {
              paramInt1 = (paramInt4 - 1) * paramInt6 + paramInt5;
              paramInt2 = paramArrayOfint.length;
              if (paramInt5 >= 0 && paramInt5 + paramInt3 <= paramInt2 && paramInt1 >= 0 && paramInt1 + paramInt3 <= paramInt2)
                return; 
              throw new ArrayIndexOutOfBoundsException();
            } 
            throw new IllegalArgumentException("abs(stride) must be >= width");
          } 
          throw new IllegalArgumentException("y + height must be <= bitmap.height()");
        } 
        throw new IllegalArgumentException("x + width must be <= bitmap.width()");
      } 
      throw new IllegalArgumentException("height must be >= 0");
    } 
    throw new IllegalArgumentException("width must be >= 0");
  }
  
  private void checkRecycled(String paramString) {
    if (!this.mRecycled)
      return; 
    throw new IllegalStateException(paramString);
  }
  
  private static void checkWidthHeight(int paramInt1, int paramInt2) {
    if (paramInt1 > 0) {
      if (paramInt2 > 0)
        return; 
      throw new IllegalArgumentException("height must be > 0");
    } 
    throw new IllegalArgumentException("width must be > 0");
  }
  
  private static void checkXYSign(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0) {
      if (paramInt2 >= 0)
        return; 
      throw new IllegalArgumentException("y must be >= 0");
    } 
    throw new IllegalArgumentException("x must be >= 0");
  }
  
  private static float clamp(float paramFloat, ColorSpace paramColorSpace, int paramInt) {
    return Math.max(Math.min(paramFloat, paramColorSpace.getMaxValue(paramInt)), paramColorSpace.getMinValue(paramInt));
  }
  
  public static Bitmap createBitmap(int paramInt1, int paramInt2, Config paramConfig) {
    return createBitmap(paramInt1, paramInt2, paramConfig, true);
  }
  
  public static Bitmap createBitmap(int paramInt1, int paramInt2, Config paramConfig, boolean paramBoolean) {
    return createBitmap((DisplayMetrics)null, paramInt1, paramInt2, paramConfig, paramBoolean);
  }
  
  public static Bitmap createBitmap(int paramInt1, int paramInt2, Config paramConfig, boolean paramBoolean, ColorSpace paramColorSpace) {
    return createBitmap((DisplayMetrics)null, paramInt1, paramInt2, paramConfig, paramBoolean, paramColorSpace);
  }
  
  public static Bitmap createBitmap(Bitmap paramBitmap) {
    return createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
  }
  
  public static Bitmap createBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return createBitmap(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4, (Matrix)null, false);
  }
  
  public static Bitmap createBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Matrix paramMatrix, boolean paramBoolean) {
    Bitmap bitmap = paramBitmap;
    checkXYSign(paramInt1, paramInt2);
    checkWidthHeight(paramInt3, paramInt4);
    if (paramInt1 + paramInt3 <= paramBitmap.getWidth()) {
      if (paramInt2 + paramInt4 <= paramBitmap.getHeight()) {
        if (!paramBitmap.isRecycled()) {
          Paint paint;
          Bitmap bitmap1;
          boolean bool;
          if (!paramBitmap.isMutable() && paramInt1 == 0 && paramInt2 == 0 && paramInt3 == paramBitmap.getWidth() && paramInt4 == paramBitmap.getHeight() && (paramMatrix == null || paramMatrix.isIdentity()))
            return bitmap; 
          if (paramBitmap.getConfig() == Config.HARDWARE) {
            bool = true;
          } else {
            bool = false;
          } 
          Bitmap bitmap2 = bitmap;
          if (bool) {
            paramBitmap.noteHardwareBitmapSlowCall();
            bitmap2 = nativeCopyPreserveInternalConfig(bitmap.mNativePtr);
          } 
          Rect rect = new Rect(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
          RectF rectF1 = new RectF(0.0F, 0.0F, paramInt3, paramInt4);
          RectF rectF2 = new RectF();
          Config config1 = Config.ARGB_8888;
          Config config2 = bitmap2.getConfig();
          if (config2 != null) {
            paramInt1 = null.$SwitchMap$android$graphics$Bitmap$Config[config2.ordinal()];
            if (paramInt1 != 1) {
              if (paramInt1 != 2) {
                if (paramInt1 != 3) {
                  config1 = Config.ARGB_8888;
                } else {
                  config1 = Config.RGBA_F16;
                } 
              } else {
                config1 = Config.ALPHA_8;
              } 
            } else {
              config1 = Config.RGB_565;
            } 
          } 
          ColorSpace colorSpace = bitmap2.getColorSpace();
          if (paramMatrix == null || paramMatrix.isIdentity()) {
            bitmap1 = createBitmap((DisplayMetrics)null, paramInt3, paramInt4, config1, bitmap2.hasAlpha(), colorSpace);
            config1 = null;
          } else {
            boolean bool1;
            paramInt1 = paramMatrix.rectStaysRect() ^ true;
            paramMatrix.mapRect(rectF2, rectF1);
            paramInt2 = Math.round(rectF2.width());
            paramInt3 = Math.round(rectF2.height());
            Config config = config1;
            config1 = config;
            ColorSpace colorSpace1 = colorSpace;
            if (paramInt1 != 0) {
              config1 = config;
              colorSpace1 = colorSpace;
              if (config != Config.ARGB_8888) {
                config1 = config;
                colorSpace1 = colorSpace;
                if (config != Config.RGBA_F16) {
                  config = Config.ARGB_8888;
                  config1 = config;
                  colorSpace1 = colorSpace;
                  if (colorSpace == null) {
                    colorSpace1 = ColorSpace.get(ColorSpace.Named.SRGB);
                    config1 = config;
                  } 
                } 
              } 
            } 
            if (paramInt1 != 0 || bitmap2.hasAlpha()) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            bitmap1 = createBitmap((DisplayMetrics)null, paramInt2, paramInt3, config1, bool1, colorSpace1);
            paint = new Paint();
            paint.setFilterBitmap(paramBoolean);
            if (paramInt1 != 0)
              paint.setAntiAlias(true); 
          } 
          bitmap1.mDensity = bitmap2.mDensity;
          bitmap1.setHasAlpha(bitmap2.hasAlpha());
          bitmap1.setPremultiplied(bitmap2.mRequestPremultiplied);
          Canvas canvas = new Canvas(bitmap1);
          canvas.translate(-rectF2.left, -rectF2.top);
          canvas.concat(paramMatrix);
          canvas.drawBitmap(bitmap2, rect, rectF1, paint);
          canvas.setBitmap((Bitmap)null);
          return bool ? bitmap1.copy(Config.HARDWARE, false) : bitmap1;
        } 
        throw new IllegalArgumentException("cannot use a recycled source in createBitmap");
      } 
      throw new IllegalArgumentException("y + height must be <= bitmap.height()");
    } 
    throw new IllegalArgumentException("x + width must be <= bitmap.width()");
  }
  
  public static Bitmap createBitmap(Picture paramPicture) {
    return createBitmap(paramPicture, paramPicture.getWidth(), paramPicture.getHeight(), Config.HARDWARE);
  }
  
  public static Bitmap createBitmap(Picture paramPicture, int paramInt1, int paramInt2, Config paramConfig) {
    if (paramInt1 > 0 && paramInt2 > 0) {
      if (paramConfig != null) {
        Bitmap bitmap1;
        paramPicture.endRecording();
        if (paramPicture.requiresHardwareAcceleration() && paramConfig != Config.HARDWARE)
          StrictMode.noteSlowCall("GPU readback"); 
        if (paramConfig == Config.HARDWARE || paramPicture.requiresHardwareAcceleration()) {
          RenderNode renderNode = RenderNode.create("BitmapTemporary", null);
          renderNode.setLeftTopRightBottom(0, 0, paramInt1, paramInt2);
          renderNode.setClipToBounds(false);
          renderNode.setForceDarkAllowed(false);
          RecordingCanvas recordingCanvas = renderNode.beginRecording(paramInt1, paramInt2);
          if (paramPicture.getWidth() != paramInt1 || paramPicture.getHeight() != paramInt2)
            recordingCanvas.scale(paramInt1 / paramPicture.getWidth(), paramInt2 / paramPicture.getHeight()); 
          recordingCanvas.drawPicture(paramPicture);
          renderNode.endRecording();
          Bitmap bitmap = ThreadedRenderer.createHardwareBitmap(renderNode, paramInt1, paramInt2);
          bitmap1 = bitmap;
          if (paramConfig != Config.HARDWARE)
            bitmap1 = bitmap.copy(paramConfig, false); 
          return bitmap1;
        } 
        Bitmap bitmap2 = createBitmap(paramInt1, paramInt2, paramConfig);
        Canvas canvas = new Canvas(bitmap2);
        if (bitmap1.getWidth() != paramInt1 || bitmap1.getHeight() != paramInt2)
          canvas.scale(paramInt1 / bitmap1.getWidth(), paramInt2 / bitmap1.getHeight()); 
        canvas.drawPicture((Picture)bitmap1);
        canvas.setBitmap((Bitmap)null);
        bitmap2.setImmutable();
        return bitmap2;
      } 
      throw new IllegalArgumentException("Config must not be null");
    } 
    throw new IllegalArgumentException("width & height must be > 0");
  }
  
  public static Bitmap createBitmap(DisplayMetrics paramDisplayMetrics, int paramInt1, int paramInt2, Config paramConfig) {
    return createBitmap(paramDisplayMetrics, paramInt1, paramInt2, paramConfig, true);
  }
  
  public static Bitmap createBitmap(DisplayMetrics paramDisplayMetrics, int paramInt1, int paramInt2, Config paramConfig, boolean paramBoolean) {
    return createBitmap(paramDisplayMetrics, paramInt1, paramInt2, paramConfig, paramBoolean, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public static Bitmap createBitmap(DisplayMetrics paramDisplayMetrics, int paramInt1, int paramInt2, Config paramConfig, boolean paramBoolean, ColorSpace paramColorSpace) {
    if (paramInt1 > 0 && paramInt2 > 0) {
      if (paramConfig != Config.HARDWARE) {
        if (paramColorSpace != null || paramConfig == Config.ALPHA_8) {
          long l;
          int i = paramConfig.nativeInt;
          if (paramColorSpace == null) {
            l = 0L;
          } else {
            l = paramColorSpace.getNativeInstance();
          } 
          Bitmap bitmap = nativeCreate(null, 0, paramInt1, paramInt1, paramInt2, i, true, l);
          if (paramDisplayMetrics != null)
            bitmap.mDensity = paramDisplayMetrics.densityDpi; 
          bitmap.setHasAlpha(paramBoolean);
          if ((paramConfig == Config.ARGB_8888 || paramConfig == Config.RGBA_F16) && !paramBoolean)
            nativeErase(bitmap.mNativePtr, -16777216); 
          return bitmap;
        } 
        throw new IllegalArgumentException("can't create bitmap without a color space");
      } 
      throw new IllegalArgumentException("can't create mutable bitmap with Config.HARDWARE");
    } 
    throw new IllegalArgumentException("width and height must be > 0");
  }
  
  public static Bitmap createBitmap(DisplayMetrics paramDisplayMetrics, int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Config paramConfig) {
    checkWidthHeight(paramInt3, paramInt4);
    if (Math.abs(paramInt2) >= paramInt3) {
      int i = paramInt1 + (paramInt4 - 1) * paramInt2;
      int j = paramArrayOfint.length;
      if (paramInt1 >= 0 && paramInt1 + paramInt3 <= j && i >= 0 && i + paramInt3 <= j) {
        if (paramInt3 > 0 && paramInt4 > 0) {
          ColorSpace colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
          Bitmap bitmap = nativeCreate(paramArrayOfint, paramInt1, paramInt2, paramInt3, paramInt4, paramConfig.nativeInt, false, colorSpace.getNativeInstance());
          if (paramDisplayMetrics != null)
            bitmap.mDensity = paramDisplayMetrics.densityDpi; 
          return bitmap;
        } 
        throw new IllegalArgumentException("width and height must be > 0");
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException("abs(stride) must be >= width");
  }
  
  public static Bitmap createBitmap(DisplayMetrics paramDisplayMetrics, int[] paramArrayOfint, int paramInt1, int paramInt2, Config paramConfig) {
    return createBitmap(paramDisplayMetrics, paramArrayOfint, 0, paramInt1, paramInt1, paramInt2, paramConfig);
  }
  
  public static Bitmap createBitmap(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Config paramConfig) {
    return createBitmap((DisplayMetrics)null, paramArrayOfint, paramInt1, paramInt2, paramInt3, paramInt4, paramConfig);
  }
  
  public static Bitmap createBitmap(int[] paramArrayOfint, int paramInt1, int paramInt2, Config paramConfig) {
    return createBitmap((DisplayMetrics)null, paramArrayOfint, 0, paramInt1, paramInt1, paramInt2, paramConfig);
  }
  
  public static Bitmap createScaledBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, boolean paramBoolean) {
    Matrix matrix = new Matrix();
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (i != paramInt1 || j != paramInt2)
      matrix.setScale(paramInt1 / i, paramInt2 / j); 
    return createBitmap(paramBitmap, 0, 0, i, j, matrix, paramBoolean);
  }
  
  static int getDefaultDensity() {
    if (sDefaultDensity >= 0)
      return sDefaultDensity; 
    sDefaultDensity = DisplayMetrics.DENSITY_DEVICE;
    return sDefaultDensity;
  }
  
  private static native boolean nativeCompress(long paramLong, int paramInt1, int paramInt2, OutputStream paramOutputStream, byte[] paramArrayOfbyte);
  
  private static native ColorSpace nativeComputeColorSpace(long paramLong);
  
  private static native int nativeConfig(long paramLong);
  
  private static native Bitmap nativeCopy(long paramLong, int paramInt, boolean paramBoolean);
  
  private static native Bitmap nativeCopyAshmem(long paramLong);
  
  private static native Bitmap nativeCopyAshmemConfig(long paramLong, int paramInt);
  
  private static native void nativeCopyPixelsFromBuffer(long paramLong, Buffer paramBuffer);
  
  private static native void nativeCopyPixelsToBuffer(long paramLong, Buffer paramBuffer);
  
  private static native Bitmap nativeCopyPreserveInternalConfig(long paramLong);
  
  private static native Bitmap nativeCreate(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong);
  
  private static native Bitmap nativeCreateFromParcel(Parcel paramParcel);
  
  private static native void nativeErase(long paramLong, int paramInt);
  
  private static native void nativeErase(long paramLong1, long paramLong2, long paramLong3);
  
  private static native Bitmap nativeExtractAlpha(long paramLong1, long paramLong2, int[] paramArrayOfint);
  
  private static native int nativeGenerationId(long paramLong);
  
  private static native int nativeGetAllocationByteCount(long paramLong);
  
  private static native long nativeGetColor(long paramLong, int paramInt1, int paramInt2);
  
  private static native HardwareBuffer nativeGetHardwareBuffer(long paramLong);
  
  private static native long nativeGetNativeFinalizer();
  
  private static native int nativeGetPixel(long paramLong, int paramInt1, int paramInt2);
  
  private static native void nativeGetPixels(long paramLong, int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  private static native boolean nativeHasAlpha(long paramLong);
  
  private static native boolean nativeHasMipMap(long paramLong);
  
  @CriticalNative
  private static native boolean nativeIsImmutable(long paramLong);
  
  private static native boolean nativeIsPremultiplied(long paramLong);
  
  private static native boolean nativeIsSRGB(long paramLong);
  
  private static native boolean nativeIsSRGBLinear(long paramLong);
  
  private static native void nativePrepareToDraw(long paramLong);
  
  private static native void nativeReconfigure(long paramLong, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
  
  private static native void nativeRecycle(long paramLong);
  
  private static native int nativeRowBytes(long paramLong);
  
  private static native boolean nativeSameAs(long paramLong1, long paramLong2);
  
  private static native void nativeSetColorSpace(long paramLong1, long paramLong2);
  
  private static native void nativeSetHasAlpha(long paramLong, boolean paramBoolean1, boolean paramBoolean2);
  
  private static native void nativeSetHasMipMap(long paramLong, boolean paramBoolean);
  
  private static native void nativeSetImmutable(long paramLong);
  
  private static native void nativeSetPixel(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  private static native void nativeSetPixels(long paramLong, int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  private static native void nativeSetPremultiplied(long paramLong, boolean paramBoolean);
  
  private static native Bitmap nativeWrapHardwareBufferBitmap(HardwareBuffer paramHardwareBuffer, long paramLong);
  
  private static native boolean nativeWriteToParcel(long paramLong, int paramInt, Parcel paramParcel);
  
  private void noteHardwareBitmapSlowCall() {
    if (getConfig() == Config.HARDWARE)
      StrictMode.noteSlowCall("Warning: attempt to read pixels from hardware bitmap, which is very slow operation"); 
  }
  
  public static int scaleFromDensity(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt2 == 0 || paramInt3 == 0 || paramInt2 == paramInt3) ? paramInt1 : ((paramInt1 * paramInt3 + (paramInt2 >> 1)) / paramInt2);
  }
  
  public static void setDefaultDensity(int paramInt) {
    sDefaultDensity = paramInt;
  }
  
  public static Bitmap wrapHardwareBuffer(GraphicBuffer paramGraphicBuffer, ColorSpace paramColorSpace) {
    HardwareBuffer hardwareBuffer = HardwareBuffer.createFromGraphicBuffer(paramGraphicBuffer);
    try {
      return wrapHardwareBuffer(hardwareBuffer, paramColorSpace);
    } finally {
      if (hardwareBuffer != null)
        try {
          hardwareBuffer.close();
        } finally {
          hardwareBuffer = null;
        }  
    } 
  }
  
  public static Bitmap wrapHardwareBuffer(HardwareBuffer paramHardwareBuffer, ColorSpace paramColorSpace) {
    if ((paramHardwareBuffer.getUsage() & 0x100L) != 0L) {
      paramHardwareBuffer.getFormat();
      ColorSpace colorSpace = paramColorSpace;
      if (paramColorSpace == null)
        colorSpace = ColorSpace.get(ColorSpace.Named.SRGB); 
      return nativeWrapHardwareBufferBitmap(paramHardwareBuffer, colorSpace.getNativeInstance());
    } 
    throw new IllegalArgumentException("usage flags must contain USAGE_GPU_SAMPLED_IMAGE.");
  }
  
  public boolean compress(CompressFormat paramCompressFormat, int paramInt, OutputStream paramOutputStream) {
    checkRecycled("Can't compress a recycled bitmap");
    if (paramOutputStream != null) {
      if (paramInt >= 0 && paramInt <= 100) {
        StrictMode.noteSlowCall("Compression of a bitmap is slow");
        Trace.traceBegin(8192L, "Bitmap.compress");
        boolean bool = nativeCompress(this.mNativePtr, paramCompressFormat.nativeInt, paramInt, paramOutputStream, new byte[4096]);
        Trace.traceEnd(8192L);
        return bool;
      } 
      throw new IllegalArgumentException("quality must be 0..100");
    } 
    throw null;
  }
  
  public Bitmap copy(Config paramConfig, boolean paramBoolean) {
    checkRecycled("Can't copy a recycled bitmap");
    if (paramConfig != Config.HARDWARE || !paramBoolean) {
      noteHardwareBitmapSlowCall();
      Bitmap bitmap = nativeCopy(this.mNativePtr, paramConfig.nativeInt, paramBoolean);
      if (bitmap != null) {
        bitmap.setPremultiplied(this.mRequestPremultiplied);
        bitmap.mDensity = this.mDensity;
      } 
      return bitmap;
    } 
    throw new IllegalArgumentException("Hardware bitmaps are always immutable");
  }
  
  public void copyPixelsFromBuffer(Buffer paramBuffer) {
    byte b;
    checkRecycled("copyPixelsFromBuffer called on recycled bitmap");
    checkHardware("unable to copyPixelsFromBuffer, Config#HARDWARE bitmaps are immutable");
    int i = paramBuffer.remaining();
    if (paramBuffer instanceof java.nio.ByteBuffer) {
      b = 0;
    } else if (paramBuffer instanceof java.nio.ShortBuffer) {
      b = 1;
    } else if (paramBuffer instanceof java.nio.IntBuffer) {
      b = 2;
    } else {
      throw new RuntimeException("unsupported Buffer subclass");
    } 
    long l1 = i;
    long l2 = getByteCount();
    if (l1 << b >= l2) {
      nativeCopyPixelsFromBuffer(this.mNativePtr, paramBuffer);
      paramBuffer.position((int)(paramBuffer.position() + (l2 >> b)));
      return;
    } 
    throw new RuntimeException("Buffer not large enough for pixels");
  }
  
  public void copyPixelsToBuffer(Buffer paramBuffer) {
    byte b;
    checkHardware("unable to copyPixelsToBuffer, pixel access is not supported on Config#HARDWARE bitmaps");
    int i = paramBuffer.remaining();
    if (paramBuffer instanceof java.nio.ByteBuffer) {
      b = 0;
    } else if (paramBuffer instanceof java.nio.ShortBuffer) {
      b = 1;
    } else if (paramBuffer instanceof java.nio.IntBuffer) {
      b = 2;
    } else {
      throw new RuntimeException("unsupported Buffer subclass");
    } 
    long l1 = i;
    long l2 = getByteCount();
    if (l1 << b >= l2) {
      nativeCopyPixelsToBuffer(this.mNativePtr, paramBuffer);
      paramBuffer.position((int)(paramBuffer.position() + (l2 >> b)));
      return;
    } 
    throw new RuntimeException("Buffer not large enough for pixels");
  }
  
  public Bitmap createAshmemBitmap() {
    checkRecycled("Can't copy a recycled bitmap");
    noteHardwareBitmapSlowCall();
    Bitmap bitmap = nativeCopyAshmem(this.mNativePtr);
    if (bitmap != null) {
      bitmap.setPremultiplied(this.mRequestPremultiplied);
      bitmap.mDensity = this.mDensity;
    } 
    return bitmap;
  }
  
  public Bitmap createAshmemBitmap(Config paramConfig) {
    checkRecycled("Can't copy a recycled bitmap");
    noteHardwareBitmapSlowCall();
    Bitmap bitmap = nativeCopyAshmemConfig(this.mNativePtr, paramConfig.nativeInt);
    if (bitmap != null) {
      bitmap.setPremultiplied(this.mRequestPremultiplied);
      bitmap.mDensity = this.mDensity;
    } 
    return bitmap;
  }
  
  public GraphicBuffer createGraphicBufferHandle() {
    return GraphicBuffer.createFromHardwareBuffer(getHardwareBuffer());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void eraseColor(int paramInt) {
    checkRecycled("Can't erase a recycled bitmap");
    if (isMutable()) {
      nativeErase(this.mNativePtr, paramInt);
      return;
    } 
    throw new IllegalStateException("cannot erase immutable bitmaps");
  }
  
  public void eraseColor(long paramLong) {
    checkRecycled("Can't erase a recycled bitmap");
    if (isMutable()) {
      ColorSpace colorSpace = Color.colorSpace(paramLong);
      nativeErase(this.mNativePtr, colorSpace.getNativeInstance(), paramLong);
      return;
    } 
    throw new IllegalStateException("cannot erase immutable bitmaps");
  }
  
  public Bitmap extractAlpha() {
    return extractAlpha(null, null);
  }
  
  public Bitmap extractAlpha(Paint paramPaint, int[] paramArrayOfint) {
    long l;
    checkRecycled("Can't extractAlpha on a recycled bitmap");
    if (paramPaint != null) {
      l = paramPaint.getNativeInstance();
    } else {
      l = 0L;
    } 
    noteHardwareBitmapSlowCall();
    Bitmap bitmap = nativeExtractAlpha(this.mNativePtr, l, paramArrayOfint);
    if (bitmap != null) {
      bitmap.mDensity = this.mDensity;
      return bitmap;
    } 
    throw new RuntimeException("Failed to extractAlpha on Bitmap");
  }
  
  public final int getAllocationByteCount() {
    if (this.mRecycled) {
      Log.w("Bitmap", "Called getAllocationByteCount() on a recycle()'d bitmap! This is undefined behavior!");
      return 0;
    } 
    return nativeGetAllocationByteCount(this.mNativePtr);
  }
  
  public final int getByteCount() {
    if (this.mRecycled) {
      Log.w("Bitmap", "Called getByteCount() on a recycle()'d bitmap! This is undefined behavior!");
      return 0;
    } 
    return getRowBytes() * getHeight();
  }
  
  public Color getColor(int paramInt1, int paramInt2) {
    checkRecycled("Can't call getColor() on a recycled bitmap");
    checkHardware("unable to getColor(), pixel access is not supported on Config#HARDWARE bitmaps");
    checkPixelAccess(paramInt1, paramInt2);
    ColorSpace colorSpace = getColorSpace();
    if (colorSpace.equals(ColorSpace.get(ColorSpace.Named.SRGB)))
      return Color.valueOf(nativeGetPixel(this.mNativePtr, paramInt1, paramInt2)); 
    long l = nativeGetColor(this.mNativePtr, paramInt1, paramInt2);
    float f1 = Half.toFloat((short)(int)(l >> 0L & 0xFFFFL));
    float f2 = Half.toFloat((short)(int)(l >> 16L & 0xFFFFL));
    float f3 = Half.toFloat((short)(int)(l >> 32L & 0xFFFFL));
    float f4 = Half.toFloat((short)(int)(0xFFFFL & l >> 48L));
    return Color.valueOf(clamp(f1, colorSpace, 0), clamp(f2, colorSpace, 1), clamp(f3, colorSpace, 2), f4, colorSpace);
  }
  
  public final ColorSpace getColorSpace() {
    checkRecycled("getColorSpace called on a recycled bitmap");
    if (this.mColorSpace == null)
      this.mColorSpace = nativeComputeColorSpace(this.mNativePtr); 
    return this.mColorSpace;
  }
  
  public final Config getConfig() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called getConfig() on a recycle()'d bitmap! This is undefined behavior!"); 
    return Config.nativeToConfig(nativeConfig(this.mNativePtr));
  }
  
  public int getDensity() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called getDensity() on a recycle()'d bitmap! This is undefined behavior!"); 
    return this.mDensity;
  }
  
  public int getGenerationId() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called getGenerationId() on a recycle()'d bitmap! This is undefined behavior!"); 
    return nativeGenerationId(this.mNativePtr);
  }
  
  public HardwareBuffer getHardwareBuffer() {
    return nativeGetHardwareBuffer(this.mNativePtr);
  }
  
  public final int getHeight() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called getHeight() on a recycle()'d bitmap! This is undefined behavior!"); 
    return this.mHeight;
  }
  
  public long getNativeInstance() {
    return this.mNativePtr;
  }
  
  public byte[] getNinePatchChunk() {
    return this.mNinePatchChunk;
  }
  
  public NinePatch.InsetStruct getNinePatchInsets() {
    return this.mNinePatchInsets;
  }
  
  public void getOpticalInsets(Rect paramRect) {
    NinePatch.InsetStruct insetStruct = this.mNinePatchInsets;
    if (insetStruct == null) {
      paramRect.setEmpty();
    } else {
      paramRect.set(insetStruct.opticalRect);
    } 
  }
  
  public int getPixel(int paramInt1, int paramInt2) {
    checkRecycled("Can't call getPixel() on a recycled bitmap");
    checkHardware("unable to getPixel(), pixel access is not supported on Config#HARDWARE bitmaps");
    checkPixelAccess(paramInt1, paramInt2);
    return nativeGetPixel(this.mNativePtr, paramInt1, paramInt2);
  }
  
  public void getPixels(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    checkRecycled("Can't call getPixels() on a recycled bitmap");
    checkHardware("unable to getPixels(), pixel access is not supported on Config#HARDWARE bitmaps");
    if (paramInt5 == 0 || paramInt6 == 0)
      return; 
    checkPixelsAccess(paramInt3, paramInt4, paramInt5, paramInt6, paramInt1, paramInt2, paramArrayOfint);
    nativeGetPixels(this.mNativePtr, paramArrayOfint, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public final int getRowBytes() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called getRowBytes() on a recycle()'d bitmap! This is undefined behavior!"); 
    return nativeRowBytes(this.mNativePtr);
  }
  
  public int getScaledHeight(int paramInt) {
    return scaleFromDensity(getHeight(), this.mDensity, paramInt);
  }
  
  public int getScaledHeight(Canvas paramCanvas) {
    return scaleFromDensity(getHeight(), this.mDensity, paramCanvas.mDensity);
  }
  
  public int getScaledHeight(DisplayMetrics paramDisplayMetrics) {
    return scaleFromDensity(getHeight(), this.mDensity, paramDisplayMetrics.densityDpi);
  }
  
  public int getScaledWidth(int paramInt) {
    return scaleFromDensity(getWidth(), this.mDensity, paramInt);
  }
  
  public int getScaledWidth(Canvas paramCanvas) {
    return scaleFromDensity(getWidth(), this.mDensity, paramCanvas.mDensity);
  }
  
  public int getScaledWidth(DisplayMetrics paramDisplayMetrics) {
    return scaleFromDensity(getWidth(), this.mDensity, paramDisplayMetrics.densityDpi);
  }
  
  public final int getWidth() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called getWidth() on a recycle()'d bitmap! This is undefined behavior!"); 
    return this.mWidth;
  }
  
  public final boolean hasAlpha() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called hasAlpha() on a recycle()'d bitmap! This is undefined behavior!"); 
    return nativeHasAlpha(this.mNativePtr);
  }
  
  public final boolean hasMipMap() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called hasMipMap() on a recycle()'d bitmap! This is undefined behavior!"); 
    return nativeHasMipMap(this.mNativePtr);
  }
  
  public final boolean isMutable() {
    return nativeIsImmutable(this.mNativePtr) ^ true;
  }
  
  public final boolean isPremultiplied() {
    if (this.mRecycled)
      Log.w("Bitmap", "Called isPremultiplied() on a recycle()'d bitmap! This is undefined behavior!"); 
    return nativeIsPremultiplied(this.mNativePtr);
  }
  
  public final boolean isRecycled() {
    return this.mRecycled;
  }
  
  public void prepareToDraw() {
    checkRecycled("Can't prepareToDraw on a recycled bitmap!");
    nativePrepareToDraw(this.mNativePtr);
  }
  
  public void reconfigure(int paramInt1, int paramInt2, Config paramConfig) {
    checkRecycled("Can't call reconfigure() on a recycled bitmap");
    if (paramInt1 > 0 && paramInt2 > 0) {
      if (isMutable()) {
        nativeReconfigure(this.mNativePtr, paramInt1, paramInt2, paramConfig.nativeInt, this.mRequestPremultiplied);
        this.mWidth = paramInt1;
        this.mHeight = paramInt2;
        this.mColorSpace = null;
        return;
      } 
      throw new IllegalStateException("only mutable bitmaps may be reconfigured");
    } 
    throw new IllegalArgumentException("width and height must be > 0");
  }
  
  public void recycle() {
    if (!this.mRecycled) {
      nativeRecycle(this.mNativePtr);
      this.mNinePatchChunk = null;
      this.mRecycled = true;
    } 
  }
  
  void reinit(int paramInt1, int paramInt2, boolean paramBoolean) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mRequestPremultiplied = paramBoolean;
    this.mColorSpace = null;
  }
  
  public boolean sameAs(Bitmap paramBitmap) {
    checkRecycled("Can't call sameAs on a recycled bitmap!");
    noteHardwareBitmapSlowCall();
    if (this == paramBitmap)
      return true; 
    if (paramBitmap == null)
      return false; 
    paramBitmap.noteHardwareBitmapSlowCall();
    if (!paramBitmap.isRecycled())
      return nativeSameAs(this.mNativePtr, paramBitmap.mNativePtr); 
    throw new IllegalArgumentException("Can't compare to a recycled bitmap!");
  }
  
  public void setColorSpace(ColorSpace paramColorSpace) {
    checkRecycled("setColorSpace called on a recycled bitmap");
    if (paramColorSpace != null) {
      if (getConfig() != Config.ALPHA_8) {
        ColorSpace colorSpace = getColorSpace();
        nativeSetColorSpace(this.mNativePtr, paramColorSpace.getNativeInstance());
        this.mColorSpace = null;
        paramColorSpace = getColorSpace();
        try {
          if (colorSpace.getComponentCount() == paramColorSpace.getComponentCount()) {
            byte b = 0;
            while (b < colorSpace.getComponentCount()) {
              if (colorSpace.getMinValue(b) >= paramColorSpace.getMinValue(b)) {
                if (colorSpace.getMaxValue(b) <= paramColorSpace.getMaxValue(b)) {
                  b++;
                  continue;
                } 
                IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException();
                this("The new ColorSpace cannot decrease the maximum value for any of the components compared to the current ColorSpace/ To perform this type of conversion create a new Bitmap in the desired ColorSpace and draw this Bitmap into it.");
                throw illegalArgumentException2;
              } 
              IllegalArgumentException illegalArgumentException1 = new IllegalArgumentException();
              this("The new ColorSpace cannot increase the minimum value for any of the components compared to the current ColorSpace. To perform this type of conversion create a new Bitmap in the desired ColorSpace and draw this Bitmap into it.");
              throw illegalArgumentException1;
            } 
            return;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          this("The new ColorSpace must have the same component count as the current ColorSpace");
          throw illegalArgumentException;
        } catch (IllegalArgumentException illegalArgumentException) {
          this.mColorSpace = colorSpace;
          nativeSetColorSpace(this.mNativePtr, colorSpace.getNativeInstance());
          throw illegalArgumentException;
        } 
      } 
      throw new IllegalArgumentException("Cannot set a ColorSpace on ALPHA_8");
    } 
    throw new IllegalArgumentException("The colorSpace cannot be set to null");
  }
  
  public void setConfig(Config paramConfig) {
    reconfigure(getWidth(), getHeight(), paramConfig);
  }
  
  public void setDensity(int paramInt) {
    this.mDensity = paramInt;
  }
  
  public void setHasAlpha(boolean paramBoolean) {
    checkRecycled("setHasAlpha called on a recycled bitmap");
    nativeSetHasAlpha(this.mNativePtr, paramBoolean, this.mRequestPremultiplied);
  }
  
  public final void setHasMipMap(boolean paramBoolean) {
    checkRecycled("setHasMipMap called on a recycled bitmap");
    nativeSetHasMipMap(this.mNativePtr, paramBoolean);
  }
  
  public void setHeight(int paramInt) {
    reconfigure(getWidth(), paramInt, getConfig());
  }
  
  public void setImmutable() {
    if (isMutable())
      nativeSetImmutable(this.mNativePtr); 
  }
  
  public void setNinePatchChunk(byte[] paramArrayOfbyte) {
    this.mNinePatchChunk = paramArrayOfbyte;
  }
  
  public void setPixel(int paramInt1, int paramInt2, int paramInt3) {
    checkRecycled("Can't call setPixel() on a recycled bitmap");
    if (isMutable()) {
      checkPixelAccess(paramInt1, paramInt2);
      nativeSetPixel(this.mNativePtr, paramInt1, paramInt2, paramInt3);
      return;
    } 
    throw new IllegalStateException();
  }
  
  public void setPixels(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    checkRecycled("Can't call setPixels() on a recycled bitmap");
    if (isMutable()) {
      if (paramInt5 == 0 || paramInt6 == 0)
        return; 
      checkPixelsAccess(paramInt3, paramInt4, paramInt5, paramInt6, paramInt1, paramInt2, paramArrayOfint);
      nativeSetPixels(this.mNativePtr, paramArrayOfint, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
      return;
    } 
    throw new IllegalStateException();
  }
  
  public final void setPremultiplied(boolean paramBoolean) {
    checkRecycled("setPremultiplied called on a recycled bitmap");
    this.mRequestPremultiplied = paramBoolean;
    nativeSetPremultiplied(this.mNativePtr, paramBoolean);
  }
  
  public void setWidth(int paramInt) {
    reconfigure(paramInt, getHeight(), getConfig());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    checkRecycled("Can't parcel a recycled bitmap");
    noteHardwareBitmapSlowCall();
    if (nativeWriteToParcel(this.mNativePtr, this.mDensity, paramParcel))
      return; 
    throw new RuntimeException("native writeToParcel failed");
  }
  
  public enum CompressFormat {
    JPEG(0),
    PNG(1),
    WEBP(2),
    WEBP_LOSSLESS(2),
    WEBP_LOSSY(3);
    
    final int nativeInt;
    
    static {
      CompressFormat compressFormat = new CompressFormat("WEBP_LOSSLESS", 4, 4);
      WEBP_LOSSLESS = compressFormat;
      $VALUES = new CompressFormat[] { JPEG, PNG, WEBP, WEBP_LOSSY, compressFormat };
    }
    
    CompressFormat(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  public enum Config {
    ALPHA_8(1),
    ARGB_4444(1),
    ARGB_8888(1),
    HARDWARE(1),
    RGBA_F16(1),
    RGB_565(3);
    
    private static Config[] sConfigs;
    
    final int nativeInt;
    
    static {
      Config config1 = new Config("HARDWARE", 5, 7);
      HARDWARE = config1;
      Config config2 = ALPHA_8;
      Config config3 = RGB_565;
      Config config4 = ARGB_4444;
      Config config5 = ARGB_8888;
      Config config6 = RGBA_F16;
      $VALUES = new Config[] { config2, config3, config4, config5, config6, config1 };
      sConfigs = new Config[] { null, config2, null, config3, config4, config5, config6, config1 };
    }
    
    Config(int param1Int1) {
      this.nativeInt = param1Int1;
    }
    
    static Config nativeToConfig(int param1Int) {
      return sConfigs[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Bitmap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */