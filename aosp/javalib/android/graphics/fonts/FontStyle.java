package android.graphics.fonts;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public final class FontStyle {
  public static final int FONT_SLANT_ITALIC = 1;
  
  public static final int FONT_SLANT_UPRIGHT = 0;
  
  public static final int FONT_WEIGHT_BLACK = 900;
  
  public static final int FONT_WEIGHT_BOLD = 700;
  
  public static final int FONT_WEIGHT_EXTRA_BOLD = 800;
  
  public static final int FONT_WEIGHT_EXTRA_LIGHT = 200;
  
  public static final int FONT_WEIGHT_LIGHT = 300;
  
  public static final int FONT_WEIGHT_MAX = 1000;
  
  public static final int FONT_WEIGHT_MEDIUM = 500;
  
  public static final int FONT_WEIGHT_MIN = 1;
  
  public static final int FONT_WEIGHT_NORMAL = 400;
  
  public static final int FONT_WEIGHT_SEMI_BOLD = 600;
  
  public static final int FONT_WEIGHT_THIN = 100;
  
  private static final String TAG = "FontStyle";
  
  private final int mSlant = 0;
  
  private final int mWeight = 400;
  
  public FontStyle() {}
  
  public FontStyle(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: iconst_0
    //   5: istore_3
    //   6: iconst_1
    //   7: iload_1
    //   8: if_icmpgt -> 24
    //   11: iload_1
    //   12: sipush #1000
    //   15: if_icmpgt -> 24
    //   18: iconst_1
    //   19: istore #4
    //   21: goto -> 27
    //   24: iconst_0
    //   25: istore #4
    //   27: iload #4
    //   29: ldc 'weight value must be [1, 1000]'
    //   31: invokestatic checkArgument : (ZLjava/lang/Object;)V
    //   34: iload_2
    //   35: ifeq -> 46
    //   38: iload_3
    //   39: istore #4
    //   41: iload_2
    //   42: iconst_1
    //   43: if_icmpne -> 49
    //   46: iconst_1
    //   47: istore #4
    //   49: iload #4
    //   51: ldc 'slant value must be FONT_SLANT_UPRIGHT or FONT_SLANT_UPRIGHT'
    //   53: invokestatic checkArgument : (ZLjava/lang/Object;)V
    //   56: aload_0
    //   57: iload_1
    //   58: putfield mWeight : I
    //   61: aload_0
    //   62: iload_2
    //   63: putfield mSlant : I
    //   66: return
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (paramObject == null || !(paramObject instanceof FontStyle))
      return false; 
    paramObject = paramObject;
    if (((FontStyle)paramObject).mWeight != this.mWeight || ((FontStyle)paramObject).mSlant != this.mSlant)
      bool = false; 
    return bool;
  }
  
  public int getMatchScore(FontStyle paramFontStyle) {
    byte b;
    int i = Math.abs(getWeight() - paramFontStyle.getWeight()) / 100;
    if (getSlant() == paramFontStyle.getSlant()) {
      b = 0;
    } else {
      b = 2;
    } 
    return i + b;
  }
  
  public int getSlant() {
    return this.mSlant;
  }
  
  public int getWeight() {
    return this.mWeight;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mWeight), Integer.valueOf(this.mSlant) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FontStyle { weight=");
    stringBuilder.append(this.mWeight);
    stringBuilder.append(", slant=");
    stringBuilder.append(this.mSlant);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FontSlant {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/FontStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */