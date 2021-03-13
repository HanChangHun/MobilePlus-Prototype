package android.graphics;

import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.graphics.fonts.SystemFonts;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;

public final class CustomFallbackBuilder {
  private static final int MAX_CUSTOM_FALLBACK = 64;
  
  private String mFallbackName = null;
  
  private final ArrayList<FontFamily> mFamilies = new ArrayList<>();
  
  private FontStyle mStyle;
  
  public CustomFallbackBuilder(FontFamily paramFontFamily) {
    Preconditions.checkNotNull(paramFontFamily);
    this.mFamilies.add(paramFontFamily);
  }
  
  public static int getMaxCustomFallbackCount() {
    return 64;
  }
  
  public CustomFallbackBuilder addCustomFallback(FontFamily paramFontFamily) {
    boolean bool;
    Preconditions.checkNotNull(paramFontFamily);
    if (this.mFamilies.size() < getMaxCustomFallbackCount()) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Custom fallback limit exceeded(");
    stringBuilder.append(getMaxCustomFallbackCount());
    stringBuilder.append(")");
    Preconditions.checkArgument(bool, stringBuilder.toString());
    this.mFamilies.add(paramFontFamily);
    return this;
  }
  
  public Typeface build() {
    int i = this.mFamilies.size();
    FontFamily[] arrayOfFontFamily = SystemFonts.getSystemFallback(this.mFallbackName);
    long[] arrayOfLong = new long[arrayOfFontFamily.length + i];
    int j;
    for (j = 0; j < i; j++)
      arrayOfLong[j] = ((FontFamily)this.mFamilies.get(j)).getNativePtr(); 
    for (j = 0; j < arrayOfFontFamily.length; j++)
      arrayOfLong[j + i] = arrayOfFontFamily[j].getNativePtr(); 
    FontStyle fontStyle = this.mStyle;
    if (fontStyle == null) {
      j = 400;
    } else {
      j = fontStyle.getWeight();
    } 
    fontStyle = this.mStyle;
    if (fontStyle == null || fontStyle.getSlant() == 0) {
      i = 0;
      return new Typeface(Typeface.access$700(arrayOfLong, j, i), null);
    } 
    i = 1;
    return new Typeface(Typeface.access$700(arrayOfLong, j, i), null);
  }
  
  public CustomFallbackBuilder setStyle(FontStyle paramFontStyle) {
    this.mStyle = paramFontStyle;
    return this;
  }
  
  public CustomFallbackBuilder setSystemFallback(String paramString) {
    Preconditions.checkNotNull(paramString);
    this.mFallbackName = paramString;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Typeface$CustomFallbackBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */