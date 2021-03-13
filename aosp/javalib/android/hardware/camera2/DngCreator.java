package android.hardware.camera2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.location.Location;
import android.media.Image;
import android.os.SystemClock;
import android.util.Log;
import android.util.Size;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public final class DngCreator implements AutoCloseable {
  private static final int BYTES_PER_RGB_PIX = 3;
  
  private static final int DEFAULT_PIXEL_STRIDE = 2;
  
  private static final String GPS_DATE_FORMAT_STR = "yyyy:MM:dd";
  
  private static final String GPS_LAT_REF_NORTH = "N";
  
  private static final String GPS_LAT_REF_SOUTH = "S";
  
  private static final String GPS_LONG_REF_EAST = "E";
  
  private static final String GPS_LONG_REF_WEST = "W";
  
  public static final int MAX_THUMBNAIL_DIMENSION = 256;
  
  private static final String TAG = "DngCreator";
  
  private static final int TAG_ORIENTATION_UNKNOWN = 9;
  
  private static final String TIFF_DATETIME_FORMAT = "yyyy:MM:dd HH:mm:ss";
  
  private static final DateFormat sExifGPSDateStamp;
  
  private final Calendar mGPSTimeStampCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
  
  private long mNativeContext;
  
  static {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd", Locale.US);
    sExifGPSDateStamp = simpleDateFormat;
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    nativeClassInit();
  }
  
  public DngCreator(CameraCharacteristics paramCameraCharacteristics, CaptureResult paramCaptureResult) {
    if (paramCameraCharacteristics != null && paramCaptureResult != null) {
      long l2;
      long l1 = System.currentTimeMillis();
      int i = ((Integer)paramCameraCharacteristics.<Integer>get(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE)).intValue();
      if (i == 1) {
        l2 = l1 - SystemClock.elapsedRealtime();
      } else if (i == 0) {
        l2 = l1 - SystemClock.uptimeMillis();
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sensor timestamp source is unexpected: ");
        stringBuilder.append(i);
        Log.w("DngCreator", stringBuilder.toString());
        l2 = l1 - SystemClock.uptimeMillis();
      } 
      Long long_ = paramCaptureResult.<Long>get(CaptureResult.SENSOR_TIMESTAMP);
      if (long_ != null)
        l1 = long_.longValue() / 1000000L + l2; 
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
      simpleDateFormat.setTimeZone(TimeZone.getDefault());
      String str = simpleDateFormat.format(Long.valueOf(l1));
      nativeInit(paramCameraCharacteristics.getNativeCopy(), paramCaptureResult.getNativeCopy(), str);
      return;
    } 
    throw new IllegalArgumentException("Null argument to DngCreator constructor");
  }
  
  private static void colorToRgb(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    paramArrayOfbyte[paramInt2] = (byte)(byte)Color.red(paramInt1);
    paramArrayOfbyte[paramInt2 + 1] = (byte)(byte)Color.green(paramInt1);
    paramArrayOfbyte[paramInt2 + 2] = (byte)(byte)Color.blue(paramInt1);
  }
  
  private static ByteBuffer convertToRGB(Bitmap paramBitmap) {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(i * 3 * j);
    int[] arrayOfInt = new int[i];
    byte[] arrayOfByte = new byte[i * 3];
    for (byte b = 0; b < j; b++) {
      paramBitmap.getPixels(arrayOfInt, 0, i, 0, b, i, 1);
      for (byte b1 = 0; b1 < i; b1++)
        colorToRgb(arrayOfInt[b1], b1 * 3, arrayOfByte); 
      byteBuffer.put(arrayOfByte);
    } 
    byteBuffer.rewind();
    return byteBuffer;
  }
  
  private static ByteBuffer convertToRGB(Image paramImage) {
    int i = paramImage.getWidth();
    int j = paramImage.getHeight();
    ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(i * 3 * j);
    Image.Plane plane1 = paramImage.getPlanes()[0];
    Image.Plane plane2 = paramImage.getPlanes()[1];
    Image.Plane plane3 = paramImage.getPlanes()[2];
    ByteBuffer byteBuffer3 = plane1.getBuffer();
    ByteBuffer byteBuffer1 = plane2.getBuffer();
    ByteBuffer byteBuffer4 = plane3.getBuffer();
    byteBuffer3.rewind();
    byteBuffer1.rewind();
    byteBuffer4.rewind();
    int k = plane1.getRowStride();
    int m = plane3.getRowStride();
    int n = plane2.getRowStride();
    int i1 = plane1.getPixelStride();
    int i2 = plane3.getPixelStride();
    int i3 = plane2.getPixelStride();
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 0;
    arrayOfByte1[2] = 0;
    byte[] arrayOfByte4 = new byte[(i - 1) * i1 + 1];
    byte[] arrayOfByte3 = new byte[(i / 2 - 1) * i3 + 1];
    byte[] arrayOfByte5 = new byte[(i / 2 - 1) * i2 + 1];
    byte[] arrayOfByte2 = new byte[i * 3];
    for (byte b = 0; b < j; b++) {
      int i4 = b / 2;
      byteBuffer3.position(k * b);
      byteBuffer3.get(arrayOfByte4);
      byteBuffer1.position(n * i4);
      byteBuffer1.get(arrayOfByte3);
      byteBuffer4.position(m * i4);
      byteBuffer4.get(arrayOfByte5);
      for (i4 = 0; i4 < i; i4++) {
        int i5 = i4 / 2;
        arrayOfByte1[0] = (byte)arrayOfByte4[i1 * i4];
        arrayOfByte1[1] = (byte)arrayOfByte3[i3 * i5];
        arrayOfByte1[2] = (byte)arrayOfByte5[i2 * i5];
        yuvToRgb(arrayOfByte1, i4 * 3, arrayOfByte2);
      } 
      byteBuffer2.put(arrayOfByte2);
    } 
    byteBuffer3.rewind();
    byteBuffer1.rewind();
    byteBuffer4.rewind();
    byteBuffer2.rewind();
    return byteBuffer2;
  }
  
  private static native void nativeClassInit();
  
  private synchronized native void nativeDestroy();
  
  private synchronized native void nativeInit(CameraMetadataNative paramCameraMetadataNative1, CameraMetadataNative paramCameraMetadataNative2, String paramString);
  
  private synchronized native void nativeSetDescription(String paramString);
  
  private synchronized native void nativeSetGpsTags(int[] paramArrayOfint1, String paramString1, int[] paramArrayOfint2, String paramString2, String paramString3, int[] paramArrayOfint3);
  
  private synchronized native void nativeSetOrientation(int paramInt);
  
  private synchronized native void nativeSetThumbnail(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  private synchronized native void nativeWriteImage(OutputStream paramOutputStream, int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, int paramInt3, int paramInt4, long paramLong, boolean paramBoolean) throws IOException;
  
  private synchronized native void nativeWriteInputStream(OutputStream paramOutputStream, InputStream paramInputStream, int paramInt1, int paramInt2, long paramLong) throws IOException;
  
  private static int[] toExifLatLong(double paramDouble) {
    paramDouble = Math.abs(paramDouble);
    int i = (int)paramDouble;
    paramDouble = (paramDouble - i) * 60.0D;
    int j = (int)paramDouble;
    return new int[] { i, 1, j, 1, (int)((paramDouble - j) * 6000.0D), 100 };
  }
  
  private void writeByteBuffer(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, OutputStream paramOutputStream, int paramInt3, int paramInt4, long paramLong) throws IOException {
    if (paramInt1 > 0 && paramInt2 > 0) {
      long l1 = paramByteBuffer.capacity();
      long l2 = paramInt4 * paramInt2 + paramLong;
      if (l1 >= l2) {
        int i = paramInt3 * paramInt1;
        if (i <= paramInt4) {
          paramByteBuffer.clear();
          nativeWriteImage(paramOutputStream, paramInt1, paramInt2, paramByteBuffer, paramInt4, paramInt3, paramLong, paramByteBuffer.isDirect());
          paramByteBuffer.clear();
          return;
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid image pixel stride, row byte width ");
        stringBuilder2.append(i);
        stringBuilder2.append(" is too large, expecting ");
        stringBuilder2.append(paramInt4);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Image size ");
      stringBuilder1.append(l1);
      stringBuilder1.append(" is too small (must be larger than ");
      stringBuilder1.append(l2);
      stringBuilder1.append(")");
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Image with invalid width, height: (");
    stringBuilder.append(paramInt1);
    stringBuilder.append(",");
    stringBuilder.append(paramInt2);
    stringBuilder.append(") passed to write");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static void yuvToRgb(byte[] paramArrayOfbyte1, int paramInt, byte[] paramArrayOfbyte2) {
    float f1 = (paramArrayOfbyte1[0] & 0xFF);
    float f2 = (paramArrayOfbyte1[1] & 0xFF);
    float f3 = (paramArrayOfbyte1[2] & 0xFF);
    paramArrayOfbyte2[paramInt] = (byte)(byte)(int)Math.max(0.0F, Math.min(255.0F, (f3 - 128.0F) * 1.402F + f1));
    paramArrayOfbyte2[paramInt + 1] = (byte)(byte)(int)Math.max(0.0F, Math.min(255.0F, f1 - (f2 - 128.0F) * 0.34414F - (f3 - 128.0F) * 0.71414F));
    paramArrayOfbyte2[paramInt + 2] = (byte)(byte)(int)Math.max(0.0F, Math.min(255.0F, (f2 - 128.0F) * 1.772F + f1));
  }
  
  public void close() {
    nativeDestroy();
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public DngCreator setDescription(String paramString) {
    if (paramString != null) {
      nativeSetDescription(paramString);
      return this;
    } 
    throw new IllegalArgumentException("Null description passed to setDescription.");
  }
  
  public DngCreator setLocation(Location paramLocation) {
    if (paramLocation != null) {
      String str1;
      String str2;
      double d1 = paramLocation.getLatitude();
      double d2 = paramLocation.getLongitude();
      long l = paramLocation.getTime();
      int[] arrayOfInt1 = toExifLatLong(d1);
      int[] arrayOfInt2 = toExifLatLong(d2);
      if (d1 >= 0.0D) {
        str1 = "N";
      } else {
        str1 = "S";
      } 
      if (d2 >= 0.0D) {
        str2 = "E";
      } else {
        str2 = "W";
      } 
      String str3 = sExifGPSDateStamp.format(Long.valueOf(l));
      this.mGPSTimeStampCalendar.setTimeInMillis(l);
      nativeSetGpsTags(arrayOfInt1, str1, arrayOfInt2, str2, str3, new int[] { this.mGPSTimeStampCalendar.get(11), 1, this.mGPSTimeStampCalendar.get(12), 1, this.mGPSTimeStampCalendar.get(13), 1 });
      return this;
    } 
    throw new IllegalArgumentException("Null location passed to setLocation");
  }
  
  public DngCreator setOrientation(int paramInt) {
    if (paramInt >= 0 && paramInt <= 8) {
      int i = paramInt;
      if (paramInt == 0)
        i = 9; 
      nativeSetOrientation(i);
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Orientation ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is not a valid EXIF orientation value");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public DngCreator setThumbnail(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      if (i <= 256 && j <= 256) {
        nativeSetThumbnail(convertToRGB(paramBitmap), i, j);
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Thumbnail dimensions width,height (");
      stringBuilder.append(i);
      stringBuilder.append(",");
      stringBuilder.append(j);
      stringBuilder.append(") too large, dimensions must be smaller than ");
      stringBuilder.append(256);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("Null argument to setThumbnail");
  }
  
  public DngCreator setThumbnail(Image paramImage) {
    if (paramImage != null) {
      int i = paramImage.getFormat();
      if (i == 35) {
        int j = paramImage.getWidth();
        i = paramImage.getHeight();
        if (j <= 256 && i <= 256) {
          nativeSetThumbnail(convertToRGB(paramImage), j, i);
          return this;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Thumbnail dimensions width,height (");
        stringBuilder1.append(j);
        stringBuilder1.append(",");
        stringBuilder1.append(i);
        stringBuilder1.append(") too large, dimensions must be smaller than ");
        stringBuilder1.append(256);
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported Image format ");
      stringBuilder.append(i);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("Null argument to setThumbnail");
  }
  
  public void writeByteBuffer(OutputStream paramOutputStream, Size paramSize, ByteBuffer paramByteBuffer, long paramLong) throws IOException {
    if (paramOutputStream != null) {
      if (paramSize != null) {
        if (paramByteBuffer != null) {
          if (paramLong >= 0L) {
            int i = paramSize.getWidth();
            writeByteBuffer(i, paramSize.getHeight(), paramByteBuffer, paramOutputStream, 2, i * 2, paramLong);
            return;
          } 
          throw new IllegalArgumentException("Negative offset passed to writeByteBuffer");
        } 
        throw new IllegalArgumentException("Null pixels passed to writeByteBuffer");
      } 
      throw new IllegalArgumentException("Null size passed to writeByteBuffer");
    } 
    throw new IllegalArgumentException("Null dngOutput passed to writeByteBuffer");
  }
  
  public void writeImage(OutputStream paramOutputStream, Image paramImage) throws IOException {
    if (paramOutputStream != null) {
      if (paramImage != null) {
        int i = paramImage.getFormat();
        if (i == 32) {
          Image.Plane[] arrayOfPlane = paramImage.getPlanes();
          if (arrayOfPlane != null && arrayOfPlane.length > 0) {
            ByteBuffer byteBuffer = arrayOfPlane[0].getBuffer();
            writeByteBuffer(paramImage.getWidth(), paramImage.getHeight(), byteBuffer, paramOutputStream, arrayOfPlane[0].getPixelStride(), arrayOfPlane[0].getRowStride(), 0L);
            return;
          } 
          throw new IllegalArgumentException("Image with no planes passed to writeImage");
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported image format ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      throw new IllegalArgumentException("Null pixels to writeImage");
    } 
    throw new IllegalArgumentException("Null dngOutput to writeImage");
  }
  
  public void writeInputStream(OutputStream paramOutputStream, Size paramSize, InputStream paramInputStream, long paramLong) throws IOException {
    if (paramOutputStream != null) {
      if (paramSize != null) {
        if (paramInputStream != null) {
          if (paramLong >= 0L) {
            int i = paramSize.getWidth();
            int j = paramSize.getHeight();
            if (i > 0 && j > 0) {
              nativeWriteInputStream(paramOutputStream, paramInputStream, i, j, paramLong);
              return;
            } 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Size with invalid width, height: (");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(j);
            stringBuilder.append(") passed to writeInputStream");
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
          throw new IllegalArgumentException("Negative offset passed to writeInputStream");
        } 
        throw new IllegalArgumentException("Null pixels passed to writeInputStream");
      } 
      throw new IllegalArgumentException("Null size passed to writeInputStream");
    } 
    throw new IllegalArgumentException("Null dngOutput passed to writeInputStream");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/DngCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */