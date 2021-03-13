package android.graphics;

import android.content.res.AssetManager;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.graphics.fonts.FontVariationAxis;
import android.os.ParcelFileDescriptor;
import android.util.SparseArray;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public final class Builder {
  public static final int BOLD_WEIGHT = 700;
  
  public static final int NORMAL_WEIGHT = 400;
  
  private final AssetManager mAssetManager;
  
  private String mFallbackFamilyName;
  
  private final Font.Builder mFontBuilder;
  
  private int mItalic = -1;
  
  private final String mPath;
  
  private int mWeight = -1;
  
  public Builder(AssetManager paramAssetManager, String paramString) {
    this(paramAssetManager, paramString, true, 0);
  }
  
  public Builder(AssetManager paramAssetManager, String paramString, boolean paramBoolean, int paramInt) {
    this.mFontBuilder = new Font.Builder(paramAssetManager, paramString, paramBoolean, paramInt);
    this.mAssetManager = paramAssetManager;
    this.mPath = paramString;
  }
  
  public Builder(File paramFile) {
    this.mFontBuilder = new Font.Builder(paramFile);
    this.mAssetManager = null;
    this.mPath = null;
  }
  
  public Builder(FileDescriptor paramFileDescriptor) {
    try {
      Font.Builder builder2 = new Font.Builder();
      this(ParcelFileDescriptor.dup(paramFileDescriptor));
      Font.Builder builder1 = builder2;
    } catch (IOException iOException) {
      iOException = null;
    } 
    this.mFontBuilder = (Font.Builder)iOException;
    this.mAssetManager = null;
    this.mPath = null;
  }
  
  public Builder(String paramString) {
    this.mFontBuilder = new Font.Builder(new File(paramString));
    this.mAssetManager = null;
    this.mPath = null;
  }
  
  private static String createAssetUid(AssetManager paramAssetManager, String paramString1, int paramInt1, FontVariationAxis[] paramArrayOfFontVariationAxis, int paramInt2, int paramInt3, String paramString2) {
    SparseArray sparseArray = paramAssetManager.getAssignedPackageIdentifiers();
    StringBuilder stringBuilder = new StringBuilder();
    int i = sparseArray.size();
    for (byte b = 0; b < i; b++) {
      stringBuilder.append((String)sparseArray.valueAt(b));
      stringBuilder.append("-");
    } 
    stringBuilder.append(paramString1);
    stringBuilder.append("-");
    stringBuilder.append(Integer.toString(paramInt1));
    stringBuilder.append("-");
    stringBuilder.append(Integer.toString(paramInt2));
    stringBuilder.append("-");
    stringBuilder.append(Integer.toString(paramInt3));
    stringBuilder.append("--");
    stringBuilder.append(paramString2);
    stringBuilder.append("--");
    if (paramArrayOfFontVariationAxis != null) {
      paramInt2 = paramArrayOfFontVariationAxis.length;
      for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++) {
        FontVariationAxis fontVariationAxis = paramArrayOfFontVariationAxis[paramInt1];
        stringBuilder.append(fontVariationAxis.getTag());
        stringBuilder.append("-");
        stringBuilder.append(Float.toString(fontVariationAxis.getStyleValue()));
      } 
    } 
    return stringBuilder.toString();
  }
  
  private Typeface resolveFallbackTypeface() {
    String str = this.mFallbackFamilyName;
    if (str == null)
      return null; 
    Typeface typeface = Typeface.access$100(str);
    if (this.mWeight == -1 && this.mItalic == -1)
      return typeface; 
    int i = this.mWeight;
    int j = i;
    if (i == -1)
      j = Typeface.access$200(typeface); 
    i = this.mItalic;
    boolean bool = false;
    if ((i == -1) ? ((Typeface.access$300(typeface) & 0x2) != 0) : (i == 1))
      bool = true; 
    return Typeface.access$400(typeface, j, bool);
  }
  
  public Typeface build() {
    Font.Builder builder = this.mFontBuilder;
    if (builder == null)
      return resolveFallbackTypeface(); 
    try {
      String str;
      int i;
      int j;
      Font font = builder.build();
      if (this.mAssetManager == null) {
        builder = null;
      } else {
        AssetManager assetManager = this.mAssetManager;
        String str1 = this.mPath;
        i = font.getTtcIndex();
        FontVariationAxis[] arrayOfFontVariationAxis = font.getAxes();
        j = this.mWeight;
        int k = this.mItalic;
        if (this.mFallbackFamilyName == null) {
          str = "sans-serif";
        } else {
          str = this.mFallbackFamilyName;
        } 
        str = createAssetUid(assetManager, str1, i, arrayOfFontVariationAxis, j, k, str);
      } 
      if (str != null)
        synchronized (Typeface.access$500()) {
          Typeface typeface1 = (Typeface)Typeface.access$600().get(str);
          if (typeface1 != null)
            return typeface1; 
        }  
      FontFamily.Builder builder1 = new FontFamily.Builder();
      this(font);
      FontFamily fontFamily = builder1.build();
      if (this.mWeight == -1) {
        i = font.getStyle().getWeight();
      } else {
        i = this.mWeight;
      } 
      if (this.mItalic == -1) {
        j = font.getStyle().getSlant();
      } else {
        j = this.mItalic;
      } 
      Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder();
      this(fontFamily);
      FontStyle fontStyle = new FontStyle();
      this(i, j);
      customFallbackBuilder = customFallbackBuilder.setStyle(fontStyle);
      if (this.mFallbackFamilyName != null)
        customFallbackBuilder.setSystemFallback(this.mFallbackFamilyName); 
      Typeface typeface = customFallbackBuilder.build();
      if (str != null)
        synchronized (Typeface.access$500()) {
          Typeface.access$600().put(str, typeface);
        }  
      return typeface;
    } catch (IOException|IllegalArgumentException iOException) {
      return resolveFallbackTypeface();
    } 
  }
  
  public Builder setFallback(String paramString) {
    this.mFallbackFamilyName = paramString;
    return this;
  }
  
  public Builder setFontVariationSettings(String paramString) {
    this.mFontBuilder.setFontVariationSettings(paramString);
    return this;
  }
  
  public Builder setFontVariationSettings(FontVariationAxis[] paramArrayOfFontVariationAxis) {
    this.mFontBuilder.setFontVariationSettings(paramArrayOfFontVariationAxis);
    return this;
  }
  
  public Builder setItalic(boolean paramBoolean) {
    this.mItalic = paramBoolean;
    this.mFontBuilder.setSlant(paramBoolean);
    return this;
  }
  
  public Builder setTtcIndex(int paramInt) {
    this.mFontBuilder.setTtcIndex(paramInt);
    return this;
  }
  
  public Builder setWeight(int paramInt) {
    this.mWeight = paramInt;
    this.mFontBuilder.setWeight(paramInt);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Typeface$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */