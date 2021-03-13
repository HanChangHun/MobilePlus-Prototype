package android.graphics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ImageFormat {
  public static final int DEPTH16 = 1144402265;
  
  public static final int DEPTH_JPEG = 1768253795;
  
  public static final int DEPTH_POINT_CLOUD = 257;
  
  public static final int FLEX_RGBA_8888 = 42;
  
  public static final int FLEX_RGB_888 = 41;
  
  public static final int HEIC = 1212500294;
  
  public static final int JPEG = 256;
  
  public static final int NV16 = 16;
  
  public static final int NV21 = 17;
  
  public static final int PRIVATE = 34;
  
  public static final int RAW10 = 37;
  
  public static final int RAW12 = 38;
  
  public static final int RAW_DEPTH = 4098;
  
  public static final int RAW_PRIVATE = 36;
  
  public static final int RAW_SENSOR = 32;
  
  public static final int RGB_565 = 4;
  
  public static final int UNKNOWN = 0;
  
  public static final int Y16 = 540422489;
  
  public static final int Y8 = 538982489;
  
  public static final int YUV_420_888 = 35;
  
  public static final int YUV_422_888 = 39;
  
  public static final int YUV_444_888 = 40;
  
  public static final int YUY2 = 20;
  
  public static final int YV12 = 842094169;
  
  public static int getBitsPerPixel(int paramInt) {
    if (paramInt != 4) {
      if (paramInt != 20) {
        if (paramInt != 32)
          if (paramInt != 35) {
            if (paramInt != 4098) {
              if (paramInt != 538982489) {
                if (paramInt != 540422489)
                  if (paramInt != 842094169) {
                    if (paramInt != 1144402265) {
                      if (paramInt != 16) {
                        if (paramInt != 17) {
                          switch (paramInt) {
                            default:
                              return -1;
                            case 42:
                              return 32;
                            case 41:
                              return 24;
                            case 40:
                              return 24;
                            case 39:
                              return 16;
                            case 38:
                              return 12;
                            case 37:
                              break;
                          } 
                          return 10;
                        } 
                        return 12;
                      } 
                      return 16;
                    } 
                  } else {
                    return 12;
                  }  
                return 16;
              } 
              return 8;
            } 
          } else {
            return 12;
          }  
        return 16;
      } 
      return 16;
    } 
    return 16;
  }
  
  public static boolean isPublicFormat(int paramInt) {
    if (paramInt != 16 && paramInt != 17 && paramInt != 256 && paramInt != 257)
      switch (paramInt) {
        default:
          switch (paramInt) {
            default:
              return false;
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
              break;
          } 
          break;
        case 4:
        case 20:
        case 32:
        case 4098:
        case 538982489:
        case 842094169:
        case 1144402265:
        case 1212500294:
        case 1768253795:
          break;
      }  
    return true;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Format {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */