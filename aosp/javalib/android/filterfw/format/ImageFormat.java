package android.filterfw.format;

import android.filterfw.core.MutableFrameFormat;
import android.graphics.Bitmap;

public class ImageFormat {
  public static final int COLORSPACE_GRAY = 1;
  
  public static final String COLORSPACE_KEY = "colorspace";
  
  public static final int COLORSPACE_RGB = 2;
  
  public static final int COLORSPACE_RGBA = 3;
  
  public static final int COLORSPACE_YUV = 4;
  
  public static int bytesPerSampleForColorspace(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt == 4)
            return 3; 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown colorspace id ");
          stringBuilder.append(paramInt);
          stringBuilder.append("!");
          throw new RuntimeException(stringBuilder.toString());
        } 
        return 4;
      } 
      return 3;
    } 
    return 1;
  }
  
  public static MutableFrameFormat create(int paramInt) {
    return create(0, 0, paramInt, bytesPerSampleForColorspace(paramInt), 0);
  }
  
  public static MutableFrameFormat create(int paramInt1, int paramInt2) {
    return create(0, 0, paramInt1, bytesPerSampleForColorspace(paramInt1), paramInt2);
  }
  
  public static MutableFrameFormat create(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return create(paramInt1, paramInt2, paramInt3, bytesPerSampleForColorspace(paramInt3), paramInt4);
  }
  
  public static MutableFrameFormat create(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(2, paramInt5);
    mutableFrameFormat.setDimensions(paramInt1, paramInt2);
    mutableFrameFormat.setBytesPerSample(paramInt4);
    mutableFrameFormat.setMetaValue("colorspace", Integer.valueOf(paramInt3));
    if (paramInt5 == 1)
      mutableFrameFormat.setObjectClass(Bitmap.class); 
    return mutableFrameFormat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/format/ImageFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */