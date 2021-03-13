package android.graphics.fonts;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public final class FontVariationAxis {
  private static final Pattern STYLE_VALUE_PATTERN;
  
  private static final Pattern TAG_PATTERN = Pattern.compile("[ -~]{4}");
  
  private final float mStyleValue;
  
  private final int mTag;
  
  private final String mTagString;
  
  static {
    STYLE_VALUE_PATTERN = Pattern.compile("-?(([0-9]+(\\.[0-9]+)?)|(\\.[0-9]+))");
  }
  
  public FontVariationAxis(String paramString, float paramFloat) {
    if (isValidTag(paramString)) {
      this.mTag = makeTag(paramString);
      this.mTagString = paramString;
      this.mStyleValue = paramFloat;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Illegal tag pattern: ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static FontVariationAxis[] fromFontVariationSettings(String paramString) {
    if (paramString == null || paramString.isEmpty())
      return null; 
    ArrayList<FontVariationAxis> arrayList = new ArrayList();
    int i = paramString.length();
    for (int j = 0;; j++) {
      if (j < i) {
        char c = paramString.charAt(j);
        if (!Character.isWhitespace(c)) {
          StringBuilder stringBuilder1;
          if ((c == '\'' || c == '"') && i >= j + 6 && paramString.charAt(j + 5) == c) {
            String str = paramString.substring(j + 1, j + 5);
            int k = j + 6;
            int m = paramString.indexOf(',', k);
            j = m;
            if (m == -1)
              j = i; 
            try {
              float f = Float.parseFloat(paramString.substring(k, j));
              arrayList.add(new FontVariationAxis(str, f));
              j++;
            } catch (NumberFormatException numberFormatException) {
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Failed to parse float string: ");
              stringBuilder1.append(numberFormatException.getMessage());
              throw new IllegalArgumentException(stringBuilder1.toString());
            } 
            continue;
          } 
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Tag should be wrapped with double or single quote: ");
          stringBuilder2.append((String)stringBuilder1);
          throw new IllegalArgumentException(stringBuilder2.toString());
        } 
      } else {
        break;
      } 
    } 
    return arrayList.isEmpty() ? null : arrayList.<FontVariationAxis>toArray(new FontVariationAxis[0]);
  }
  
  private static boolean isValidTag(String paramString) {
    boolean bool;
    if (paramString != null && TAG_PATTERN.matcher(paramString).matches()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean isValidValueFormat(String paramString) {
    boolean bool;
    if (paramString != null && STYLE_VALUE_PATTERN.matcher(paramString).matches()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static int makeTag(String paramString) {
    return paramString.charAt(0) << 24 | paramString.charAt(1) << 16 | paramString.charAt(2) << 8 | paramString.charAt(3);
  }
  
  public static String toFontVariationSettings(FontVariationAxis[] paramArrayOfFontVariationAxis) {
    return (paramArrayOfFontVariationAxis == null) ? "" : TextUtils.join(",", (Object[])paramArrayOfFontVariationAxis);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (paramObject == null || !(paramObject instanceof FontVariationAxis))
      return false; 
    paramObject = paramObject;
    if (((FontVariationAxis)paramObject).mTag != this.mTag || ((FontVariationAxis)paramObject).mStyleValue != this.mStyleValue)
      bool = false; 
    return bool;
  }
  
  public int getOpenTypeTagValue() {
    return this.mTag;
  }
  
  public float getStyleValue() {
    return this.mStyleValue;
  }
  
  public String getTag() {
    return this.mTagString;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mTag), Float.valueOf(this.mStyleValue) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("'");
    stringBuilder.append(this.mTagString);
    stringBuilder.append("' ");
    stringBuilder.append(Float.toString(this.mStyleValue));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/FontVariationAxis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */