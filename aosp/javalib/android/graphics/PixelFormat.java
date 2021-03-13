package android.graphics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PixelFormat {
  @Deprecated
  public static final int A_8 = 8;
  
  public static final int HSV_888 = 55;
  
  @Deprecated
  public static final int JPEG = 256;
  
  @Deprecated
  public static final int LA_88 = 10;
  
  @Deprecated
  public static final int L_8 = 9;
  
  public static final int OPAQUE = -1;
  
  public static final int RGBA_1010102 = 43;
  
  @Deprecated
  public static final int RGBA_4444 = 7;
  
  @Deprecated
  public static final int RGBA_5551 = 6;
  
  public static final int RGBA_8888 = 1;
  
  public static final int RGBA_F16 = 22;
  
  public static final int RGBX_8888 = 2;
  
  @Deprecated
  public static final int RGB_332 = 11;
  
  public static final int RGB_565 = 4;
  
  public static final int RGB_888 = 3;
  
  public static final int TRANSLUCENT = -3;
  
  public static final int TRANSPARENT = -2;
  
  public static final int UNKNOWN = 0;
  
  @Deprecated
  public static final int YCbCr_420_SP = 17;
  
  @Deprecated
  public static final int YCbCr_422_I = 20;
  
  @Deprecated
  public static final int YCbCr_422_SP = 16;
  
  public int bitsPerPixel;
  
  public int bytesPerPixel;
  
  public static boolean formatHasAlpha(int paramInt) {
    return !(paramInt != -3 && paramInt != -2 && paramInt != 1 && paramInt != 10 && paramInt != 22 && paramInt != 43 && paramInt != 6 && paramInt != 7 && paramInt != 8);
  }
  
  public static String formatToString(int paramInt) {
    if (paramInt != -3) {
      if (paramInt != -2) {
        if (paramInt != 0) {
          if (paramInt != 1) {
            if (paramInt != 2) {
              if (paramInt != 3) {
                if (paramInt != 4) {
                  if (paramInt != 16) {
                    if (paramInt != 17) {
                      if (paramInt != 20) {
                        if (paramInt != 22) {
                          if (paramInt != 43) {
                            if (paramInt != 55) {
                              if (paramInt != 256) {
                                switch (paramInt) {
                                  default:
                                    return Integer.toString(paramInt);
                                  case 11:
                                    return "RGB_332";
                                  case 10:
                                    return "LA_88";
                                  case 9:
                                    return "L_8";
                                  case 8:
                                    return "A_8";
                                  case 7:
                                    return "RGBA_4444";
                                  case 6:
                                    break;
                                } 
                                return "RGBA_5551";
                              } 
                              return "JPEG";
                            } 
                            return "HSV_888";
                          } 
                          return "RGBA_1010102";
                        } 
                        return "RGBA_F16";
                      } 
                      return "YCbCr_422_I";
                    } 
                    return "YCbCr_420_SP";
                  } 
                  return "YCbCr_422_SP";
                } 
                return "RGB_565";
              } 
              return "RGB_888";
            } 
            return "RGBX_8888";
          } 
          return "RGBA_8888";
        } 
        return "UNKNOWN";
      } 
      return "TRANSPARENT";
    } 
    return "TRANSLUCENT";
  }
  
  public static void getPixelFormatInfo(int paramInt, PixelFormat paramPixelFormat) {
    // Byte code:
    //   0: iload_0
    //   1: iconst_1
    //   2: if_icmpeq -> 214
    //   5: iload_0
    //   6: iconst_2
    //   7: if_icmpeq -> 214
    //   10: iload_0
    //   11: iconst_3
    //   12: if_icmpeq -> 200
    //   15: iload_0
    //   16: iconst_4
    //   17: if_icmpeq -> 186
    //   20: iload_0
    //   21: bipush #16
    //   23: if_icmpeq -> 172
    //   26: iload_0
    //   27: bipush #17
    //   29: if_icmpeq -> 158
    //   32: iload_0
    //   33: bipush #20
    //   35: if_icmpeq -> 172
    //   38: iload_0
    //   39: bipush #22
    //   41: if_icmpeq -> 143
    //   44: iload_0
    //   45: bipush #43
    //   47: if_icmpeq -> 214
    //   50: iload_0
    //   51: bipush #55
    //   53: if_icmpeq -> 200
    //   56: iload_0
    //   57: tableswitch default -> 96, 6 -> 186, 7 -> 186, 8 -> 129, 9 -> 129, 10 -> 186, 11 -> 129
    //   96: new java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial <init> : ()V
    //   103: astore_1
    //   104: aload_1
    //   105: ldc 'unknown pixel format '
    //   107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload_1
    //   112: iload_0
    //   113: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: new java/lang/IllegalArgumentException
    //   120: dup
    //   121: aload_1
    //   122: invokevirtual toString : ()Ljava/lang/String;
    //   125: invokespecial <init> : (Ljava/lang/String;)V
    //   128: athrow
    //   129: aload_1
    //   130: bipush #8
    //   132: putfield bitsPerPixel : I
    //   135: aload_1
    //   136: iconst_1
    //   137: putfield bytesPerPixel : I
    //   140: goto -> 225
    //   143: aload_1
    //   144: bipush #64
    //   146: putfield bitsPerPixel : I
    //   149: aload_1
    //   150: bipush #8
    //   152: putfield bytesPerPixel : I
    //   155: goto -> 225
    //   158: aload_1
    //   159: bipush #12
    //   161: putfield bitsPerPixel : I
    //   164: aload_1
    //   165: iconst_1
    //   166: putfield bytesPerPixel : I
    //   169: goto -> 225
    //   172: aload_1
    //   173: bipush #16
    //   175: putfield bitsPerPixel : I
    //   178: aload_1
    //   179: iconst_1
    //   180: putfield bytesPerPixel : I
    //   183: goto -> 225
    //   186: aload_1
    //   187: bipush #16
    //   189: putfield bitsPerPixel : I
    //   192: aload_1
    //   193: iconst_2
    //   194: putfield bytesPerPixel : I
    //   197: goto -> 225
    //   200: aload_1
    //   201: bipush #24
    //   203: putfield bitsPerPixel : I
    //   206: aload_1
    //   207: iconst_3
    //   208: putfield bytesPerPixel : I
    //   211: goto -> 225
    //   214: aload_1
    //   215: bipush #32
    //   217: putfield bitsPerPixel : I
    //   220: aload_1
    //   221: iconst_4
    //   222: putfield bytesPerPixel : I
    //   225: return
  }
  
  public static boolean isPublicFormat(int paramInt) {
    return !(paramInt != 1 && paramInt != 2 && paramInt != 3 && paramInt != 4 && paramInt != 22 && paramInt != 43);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Format {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Opacity {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PixelFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */