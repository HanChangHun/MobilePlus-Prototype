package android.graphics.fonts;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FontFileUtil {
  private static final int ANALYZE_ERROR = -1;
  
  private static final int OS2_TABLE_TAG = 1330851634;
  
  private static final int SFNT_VERSION_1 = 65536;
  
  private static final int SFNT_VERSION_OTTO = 1330926671;
  
  private static final int TTC_TAG = 1953784678;
  
  public static final int analyzeStyle(ByteBuffer paramByteBuffer, int paramInt, FontVariationAxis[] paramArrayOfFontVariationAxis) {
    byte b1 = -1;
    byte b2 = -1;
    boolean bool = false;
    if (paramArrayOfFontVariationAxis != null) {
      int j = paramArrayOfFontVariationAxis.length;
      byte b = 0;
      while (b < j) {
        byte b3;
        FontVariationAxis fontVariationAxis = paramArrayOfFontVariationAxis[b];
        if ("wght".equals(fontVariationAxis.getTag())) {
          b3 = (int)fontVariationAxis.getStyleValue();
        } else {
          b3 = b1;
          if ("ital".equals(fontVariationAxis.getTag())) {
            if (fontVariationAxis.getStyleValue() == 1.0F) {
              b2 = 1;
            } else {
              b2 = 0;
            } 
            b3 = b1;
          } 
        } 
        b++;
        b1 = b3;
      } 
    } else {
      b2 = -1;
      b1 = -1;
    } 
    if (b1 != -1 && b2 != -1) {
      if (b2 == 1)
        bool = true; 
      return pack(b1, bool);
    } 
    ByteOrder byteOrder = paramByteBuffer.order();
    paramByteBuffer.order(ByteOrder.BIG_ENDIAN);
    int i = 0;
    try {
      if (paramByteBuffer.getInt(0) == 1953784678) {
        i = paramByteBuffer.getInt(8);
        if (paramInt >= i)
          return -1; 
        i = paramByteBuffer.getInt(paramInt * 4 + 12);
      } 
      paramInt = paramByteBuffer.getInt(i);
      if (paramInt != 65536 && paramInt != 1330926671)
        return -1; 
      short s = paramByteBuffer.getShort(i + 4);
      byte b = -1;
      byte b3 = 0;
      while (true) {
        paramInt = b;
        if (b3 < s) {
          paramInt = i + 12 + b3 * 16;
          if (paramByteBuffer.getInt(paramInt) == 1330851634) {
            paramInt = paramByteBuffer.getInt(paramInt + 8);
            break;
          } 
          b3++;
          continue;
        } 
        break;
      } 
      if (paramInt == -1) {
        paramInt = pack(400, false);
        return paramInt;
      } 
      boolean bool1 = false;
      i = paramByteBuffer.getShort(paramInt + 4);
      if ((paramByteBuffer.getShort(paramInt + 62) & 0x1) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (b1 == -1) {
        paramInt = i;
      } else {
        paramInt = b1;
      } 
      if (b2 != -1) {
        bool = bool1;
        if (b2 == 1)
          bool = true; 
      } 
      paramInt = pack(paramInt, bool);
      return paramInt;
    } finally {
      paramByteBuffer.order(byteOrder);
    } 
  }
  
  public static boolean isSuccess(int paramInt) {
    boolean bool;
    if (paramInt != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static int pack(int paramInt, boolean paramBoolean) {
    int i;
    if (paramBoolean) {
      i = 65536;
    } else {
      i = 0;
    } 
    return i | paramInt;
  }
  
  public static boolean unpackItalic(int paramInt) {
    boolean bool;
    if ((0x10000 & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static int unpackWeight(int paramInt) {
    return 0xFFFF & paramInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/FontFileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */