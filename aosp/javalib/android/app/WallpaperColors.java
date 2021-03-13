package android.app;

import android.annotation.SystemApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.graphics.palette.Palette;
import com.android.internal.graphics.palette.Quantizer;
import com.android.internal.graphics.palette.VariationalKMeansQuantizer;
import com.android.internal.util.ContrastColorUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class WallpaperColors implements Parcelable {
  private static final float BRIGHT_IMAGE_MEAN_LUMINANCE = 0.75F;
  
  public static final Parcelable.Creator<WallpaperColors> CREATOR = new Parcelable.Creator<WallpaperColors>() {
      public WallpaperColors createFromParcel(Parcel param1Parcel) {
        return new WallpaperColors(param1Parcel);
      }
      
      public WallpaperColors[] newArray(int param1Int) {
        return new WallpaperColors[param1Int];
      }
    };
  
  private static final float DARK_PIXEL_CONTRAST = 6.0F;
  
  private static final float DARK_THEME_MEAN_LUMINANCE = 0.25F;
  
  private static final boolean DEBUG_DARK_PIXELS = false;
  
  public static final int HINT_FROM_BITMAP = 4;
  
  @SystemApi
  public static final int HINT_SUPPORTS_DARK_TEXT = 1;
  
  @SystemApi
  public static final int HINT_SUPPORTS_DARK_THEME = 2;
  
  private static final int MAX_BITMAP_SIZE = 112;
  
  private static final float MAX_DARK_AREA = 0.025F;
  
  private static final int MAX_WALLPAPER_EXTRACTION_AREA = 12544;
  
  private static final float MIN_COLOR_OCCURRENCE = 0.05F;
  
  private int mColorHints;
  
  private final ArrayList<Color> mMainColors;
  
  public WallpaperColors(Color paramColor1, Color paramColor2, Color paramColor3) {
    this(paramColor1, paramColor2, paramColor3, 0);
    float[] arrayOfFloat = new float[3];
    ColorUtils.colorToHSL(paramColor1.toArgb(), arrayOfFloat);
    if (arrayOfFloat[2] < 0.25F)
      this.mColorHints = 0x2 | this.mColorHints; 
  }
  
  @SystemApi
  public WallpaperColors(Color paramColor1, Color paramColor2, Color paramColor3, int paramInt) {
    if (paramColor1 != null) {
      ArrayList<Color> arrayList = new ArrayList(3);
      this.mMainColors = arrayList;
      arrayList.add(paramColor1);
      if (paramColor2 != null)
        this.mMainColors.add(paramColor2); 
      if (paramColor3 != null)
        if (paramColor2 != null) {
          this.mMainColors.add(paramColor3);
        } else {
          throw new IllegalArgumentException("tertiaryColor can't be specified when secondaryColor is null");
        }  
      this.mColorHints = paramInt;
      return;
    } 
    throw new IllegalArgumentException("Primary color should never be null.");
  }
  
  public WallpaperColors(Parcel paramParcel) {
    this.mMainColors = new ArrayList<>();
    int i = paramParcel.readInt();
    for (byte b = 0; b < i; b++) {
      Color color = Color.valueOf(paramParcel.readInt());
      this.mMainColors.add(color);
    } 
    this.mColorHints = paramParcel.readInt();
  }
  
  private static int calculateDarkHints(Bitmap paramBitmap) {
    if (paramBitmap == null)
      return 0; 
    int[] arrayOfInt = new int[paramBitmap.getWidth() * paramBitmap.getHeight()];
    double d = 0.0D;
    int i = (int)(arrayOfInt.length * 0.025F);
    int j = 0;
    paramBitmap.getPixels(arrayOfInt, 0, paramBitmap.getWidth(), 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    float[] arrayOfFloat = new float[3];
    int k = 0;
    while (k < arrayOfInt.length) {
      boolean bool;
      ColorUtils.colorToHSL(arrayOfInt[k], arrayOfFloat);
      float f = arrayOfFloat[2];
      int m = Color.alpha(arrayOfInt[k]);
      if (ContrastColorUtil.calculateContrast(arrayOfInt[k], -16777216) > 6.0D) {
        bool = true;
      } else {
        bool = false;
      } 
      int n = j;
      if (!bool) {
        n = j;
        if (m != 0)
          n = j + 1; 
      } 
      d += f;
      k++;
      j = n;
    } 
    byte b = 0;
    d /= arrayOfInt.length;
    k = b;
    if (d > 0.75D) {
      k = b;
      if (j < i)
        k = false | true; 
    } 
    j = k;
    if (d < 0.25D)
      j = k | 0x2; 
    return j;
  }
  
  private static Size calculateOptimalSize(int paramInt1, int paramInt2) {
    int i = paramInt1 * paramInt2;
    double d = 1.0D;
    if (i > 12544)
      d = Math.sqrt(12544.0D / i); 
    int j = (int)(paramInt1 * d);
    i = (int)(paramInt2 * d);
    paramInt1 = j;
    if (j == 0)
      paramInt1 = 1; 
    paramInt2 = i;
    if (i == 0)
      paramInt2 = 1; 
    return new Size(paramInt1, paramInt2);
  }
  
  public static WallpaperColors fromBitmap(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      boolean bool = false;
      Bitmap bitmap = paramBitmap;
      if (i * j > 12544) {
        bool = true;
        Size size = calculateOptimalSize(paramBitmap.getWidth(), paramBitmap.getHeight());
        bitmap = Bitmap.createScaledBitmap(paramBitmap, size.getWidth(), size.getHeight(), true);
      } 
      ArrayList<Palette.Swatch> arrayList = new ArrayList(Palette.from(bitmap).setQuantizer((Quantizer)new VariationalKMeansQuantizer()).maximumColorCount(5).clearFilters().resizeBitmapArea(12544).generate().getSwatches());
      arrayList.removeIf(new _$$Lambda$WallpaperColors$8R5kfKKLfHjpw_QXmD1mWOKwJxc((bitmap.getWidth() * bitmap.getHeight()) * 0.05F));
      arrayList.sort((Comparator)_$$Lambda$WallpaperColors$MQFGJ9EZ9CDeGbIhMufJKqru3IE.INSTANCE);
      j = arrayList.size();
      Color color1 = null;
      Color color2 = null;
      Color color3 = null;
      for (i = 0; i < j; i++) {
        Color color = Color.valueOf(((Palette.Swatch)arrayList.get(i)).getRgb());
        if (i != 0) {
          if (i != 1) {
            if (i != 2)
              break; 
            color3 = color;
          } else {
            color2 = color;
          } 
        } else {
          color1 = color;
        } 
      } 
      i = calculateDarkHints(bitmap);
      if (bool)
        bitmap.recycle(); 
      return new WallpaperColors(color1, color2, color3, i | 0x4);
    } 
    throw new IllegalArgumentException("Bitmap can't be null");
  }
  
  public static WallpaperColors fromDrawable(Drawable paramDrawable) {
    Rect rect;
    if (paramDrawable != null) {
      rect = paramDrawable.copyBounds();
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (i > 0) {
        int k = j;
        if (j <= 0) {
          i = 112;
          k = 112;
          Size size2 = calculateOptimalSize(i, k);
          Bitmap bitmap2 = Bitmap.createBitmap(size2.getWidth(), size2.getHeight(), Bitmap.Config.ARGB_8888);
          Canvas canvas2 = new Canvas(bitmap2);
          paramDrawable.setBounds(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
          paramDrawable.draw(canvas2);
          WallpaperColors wallpaperColors2 = fromBitmap(bitmap2);
          bitmap2.recycle();
          paramDrawable.setBounds(rect);
          return wallpaperColors2;
        } 
        Size size1 = calculateOptimalSize(i, k);
        Bitmap bitmap1 = Bitmap.createBitmap(size1.getWidth(), size1.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap1);
        paramDrawable.setBounds(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
        paramDrawable.draw(canvas1);
        WallpaperColors wallpaperColors1 = fromBitmap(bitmap1);
        bitmap1.recycle();
        paramDrawable.setBounds(rect);
        return wallpaperColors1;
      } 
    } else {
      throw new IllegalArgumentException("Drawable cannot be null");
    } 
    byte b1 = 112;
    byte b2 = 112;
    Size size = calculateOptimalSize(b1, b2);
    Bitmap bitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    paramDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
    paramDrawable.draw(canvas);
    WallpaperColors wallpaperColors = fromBitmap(bitmap);
    bitmap.recycle();
    paramDrawable.setBounds(rect);
    return wallpaperColors;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1 = false;
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    boolean bool2 = bool1;
    if (this.mMainColors.equals(((WallpaperColors)paramObject).mMainColors)) {
      bool2 = bool1;
      if (this.mColorHints == ((WallpaperColors)paramObject).mColorHints)
        bool2 = true; 
    } 
    return bool2;
  }
  
  @SystemApi
  public int getColorHints() {
    return this.mColorHints;
  }
  
  public List<Color> getMainColors() {
    return Collections.unmodifiableList(this.mMainColors);
  }
  
  public Color getPrimaryColor() {
    return this.mMainColors.get(0);
  }
  
  public Color getSecondaryColor() {
    Color color;
    if (this.mMainColors.size() < 2) {
      color = null;
    } else {
      color = this.mMainColors.get(1);
    } 
    return color;
  }
  
  public Color getTertiaryColor() {
    Color color;
    if (this.mMainColors.size() < 3) {
      color = null;
    } else {
      color = this.mMainColors.get(2);
    } 
    return color;
  }
  
  public int hashCode() {
    return this.mMainColors.hashCode() * 31 + this.mColorHints;
  }
  
  public void setColorHints(int paramInt) {
    this.mColorHints = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    for (byte b = 0; b < this.mMainColors.size(); b++) {
      stringBuilder1.append(Integer.toHexString(((Color)this.mMainColors.get(b)).toArgb()));
      stringBuilder1.append(" ");
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("[WallpaperColors: ");
    stringBuilder2.append(stringBuilder1.toString());
    stringBuilder2.append("h: ");
    stringBuilder2.append(this.mColorHints);
    stringBuilder2.append("]");
    return stringBuilder2.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    List<Color> list = getMainColors();
    int i = list.size();
    paramParcel.writeInt(i);
    for (paramInt = 0; paramInt < i; paramInt++)
      paramParcel.writeInt(((Color)list.get(paramInt)).toArgb()); 
    paramParcel.writeInt(this.mColorHints);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperColors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */