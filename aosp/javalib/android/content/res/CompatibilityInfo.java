package android.content.res;

import android.content.pm.ApplicationInfo;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

public class CompatibilityInfo implements Parcelable {
  private static final int ALWAYS_NEEDS_COMPAT = 2;
  
  public static final Parcelable.Creator<CompatibilityInfo> CREATOR;
  
  public static final CompatibilityInfo DEFAULT_COMPATIBILITY_INFO = new CompatibilityInfo() {
    
    };
  
  public static final int DEFAULT_NORMAL_SHORT_DIMENSION = 320;
  
  public static final float MAXIMUM_ASPECT_RATIO = 1.7791667F;
  
  private static final int NEEDS_COMPAT_RES = 16;
  
  private static final int NEEDS_SCREEN_COMPAT = 8;
  
  private static final int NEVER_NEEDS_COMPAT = 4;
  
  private static final int SCALING_REQUIRED = 1;
  
  public final int applicationDensity;
  
  public final float applicationInvertedScale;
  
  public final float applicationScale;
  
  private final int mCompatibilityFlags;
  
  static {
    CREATOR = new Parcelable.Creator<CompatibilityInfo>() {
        public CompatibilityInfo createFromParcel(Parcel param1Parcel) {
          return new CompatibilityInfo(param1Parcel);
        }
        
        public CompatibilityInfo[] newArray(int param1Int) {
          return new CompatibilityInfo[param1Int];
        }
      };
  }
  
  private CompatibilityInfo() {
    this(4, DisplayMetrics.DENSITY_DEVICE, 1.0F, 1.0F);
  }
  
  private CompatibilityInfo(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {
    this.mCompatibilityFlags = paramInt1;
    this.applicationDensity = paramInt2;
    this.applicationScale = paramFloat1;
    this.applicationInvertedScale = paramFloat2;
  }
  
  public CompatibilityInfo(ApplicationInfo paramApplicationInfo, int paramInt1, int paramInt2, boolean paramBoolean) {
    int i = 0;
    if (paramApplicationInfo.targetSdkVersion < 26)
      i = 0x0 | 0x10; 
    if (paramApplicationInfo.requiresSmallestWidthDp != 0 || paramApplicationInfo.compatibleWidthLimitDp != 0 || paramApplicationInfo.largestWidthLimitDp != 0) {
      if (paramApplicationInfo.requiresSmallestWidthDp != 0) {
        k = paramApplicationInfo.requiresSmallestWidthDp;
      } else {
        k = paramApplicationInfo.compatibleWidthLimitDp;
      } 
      paramInt1 = k;
      if (k == 0)
        paramInt1 = paramApplicationInfo.largestWidthLimitDp; 
      if (paramApplicationInfo.compatibleWidthLimitDp != 0) {
        k = paramApplicationInfo.compatibleWidthLimitDp;
      } else {
        k = paramInt1;
      } 
      int j = k;
      if (k < paramInt1)
        j = paramInt1; 
      int k = paramApplicationInfo.largestWidthLimitDp;
      if (paramInt1 > 320) {
        paramInt1 = i | 0x4;
      } else if (k != 0 && paramInt2 > k) {
        paramInt1 = i | 0xA;
      } else if (j >= paramInt2) {
        paramInt1 = i | 0x4;
      } else {
        paramInt1 = i;
        if (paramBoolean)
          paramInt1 = i | 0x8; 
      } 
      this.applicationDensity = DisplayMetrics.DENSITY_DEVICE;
      this.applicationScale = 1.0F;
      this.applicationInvertedScale = 1.0F;
    } else {
      paramInt2 = 0;
      boolean bool = false;
      if ((paramApplicationInfo.flags & 0x800) != 0) {
        int k = 0x0 | 0x8;
        boolean bool1 = true;
        paramInt2 = k;
        bool = bool1;
        if (!paramBoolean) {
          paramInt2 = k | 0x22;
          bool = bool1;
        } 
      } 
      int j = paramInt2;
      if ((paramApplicationInfo.flags & 0x80000) != 0) {
        boolean bool1 = true;
        j = paramInt2;
        bool = bool1;
        if (!paramBoolean) {
          j = paramInt2 | 0x22;
          bool = bool1;
        } 
      } 
      paramInt2 = j;
      if ((paramApplicationInfo.flags & 0x1000) != 0) {
        bool = true;
        paramInt2 = j | 0x2;
      } 
      j = paramInt2;
      if (paramBoolean)
        j = paramInt2 & 0xFFFFFFFD; 
      paramInt2 = i | 0x8;
      i = paramInt1 & 0xF;
      if (i != 3) {
        if (i == 4) {
          i = paramInt2;
          if ((j & 0x20) != 0)
            i = paramInt2 & 0xFFFFFFF7; 
          paramInt2 = i;
          if ((paramApplicationInfo.flags & 0x80000) != 0)
            paramInt2 = i | 0x4; 
        } 
      } else {
        i = paramInt2;
        if ((j & 0x8) != 0)
          i = paramInt2 & 0xFFFFFFF7; 
        paramInt2 = i;
        if ((paramApplicationInfo.flags & 0x800) != 0)
          paramInt2 = i | 0x4; 
      } 
      if ((0x10000000 & paramInt1) != 0) {
        if ((j & 0x2) != 0) {
          paramInt1 = paramInt2 & 0xFFFFFFF7;
        } else {
          paramInt1 = paramInt2;
          if (!bool)
            paramInt1 = paramInt2 | 0x2; 
        } 
      } else {
        paramInt1 = paramInt2 & 0xFFFFFFF7 | 0x4;
      } 
      if ((paramApplicationInfo.flags & 0x2000) != 0) {
        this.applicationDensity = DisplayMetrics.DENSITY_DEVICE;
        this.applicationScale = 1.0F;
        this.applicationInvertedScale = 1.0F;
      } else {
        this.applicationDensity = 160;
        float f = DisplayMetrics.DENSITY_DEVICE / 160.0F;
        this.applicationScale = f;
        this.applicationInvertedScale = 1.0F / f;
        paramInt1 |= 0x1;
      } 
    } 
    this.mCompatibilityFlags = paramInt1;
  }
  
  private CompatibilityInfo(Parcel paramParcel) {
    this.mCompatibilityFlags = paramParcel.readInt();
    this.applicationDensity = paramParcel.readInt();
    this.applicationScale = paramParcel.readFloat();
    this.applicationInvertedScale = paramParcel.readFloat();
  }
  
  public static float computeCompatibleScaling(DisplayMetrics paramDisplayMetrics1, DisplayMetrics paramDisplayMetrics2) {
    int m;
    int i = paramDisplayMetrics1.noncompatWidthPixels;
    int j = paramDisplayMetrics1.noncompatHeightPixels;
    if (i < j) {
      k = i;
      m = j;
    } else {
      k = j;
      m = i;
    } 
    int n = (int)(paramDisplayMetrics1.density * 320.0F + 0.5F);
    float f1 = m / k;
    float f2 = f1;
    if (f1 > 1.7791667F)
      f2 = 1.7791667F; 
    int k = (int)(n * f2 + 0.5F);
    if (i < j) {
      m = n;
      n = k;
    } else {
      m = k;
    } 
    f1 = i / m;
    f2 = j / n;
    if (f1 < f2)
      f2 = f1; 
    f1 = f2;
    if (f2 < 1.0F)
      f1 = 1.0F; 
    if (paramDisplayMetrics2 != null) {
      paramDisplayMetrics2.widthPixels = m;
      paramDisplayMetrics2.heightPixels = n;
    } 
    return f1;
  }
  
  public boolean alwaysSupportsScreen() {
    boolean bool;
    if ((this.mCompatibilityFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void applyToConfiguration(int paramInt, Configuration paramConfiguration) {
    if (!supportsScreen()) {
      paramConfiguration.screenLayout = paramConfiguration.screenLayout & 0xFFFFFFF0 | 0x2;
      paramConfiguration.screenWidthDp = paramConfiguration.compatScreenWidthDp;
      paramConfiguration.screenHeightDp = paramConfiguration.compatScreenHeightDp;
      paramConfiguration.smallestScreenWidthDp = paramConfiguration.compatSmallestScreenWidthDp;
    } 
    paramConfiguration.densityDpi = paramInt;
    if (isScalingRequired()) {
      float f = this.applicationInvertedScale;
      paramConfiguration.densityDpi = (int)(paramConfiguration.densityDpi * f + 0.5F);
    } 
  }
  
  public void applyToDisplayMetrics(DisplayMetrics paramDisplayMetrics) {
    if (!supportsScreen()) {
      computeCompatibleScaling(paramDisplayMetrics, paramDisplayMetrics);
    } else {
      paramDisplayMetrics.widthPixels = paramDisplayMetrics.noncompatWidthPixels;
      paramDisplayMetrics.heightPixels = paramDisplayMetrics.noncompatHeightPixels;
    } 
    if (isScalingRequired()) {
      float f = this.applicationInvertedScale;
      paramDisplayMetrics.density = paramDisplayMetrics.noncompatDensity * f;
      paramDisplayMetrics.densityDpi = (int)(paramDisplayMetrics.noncompatDensityDpi * f + 0.5F);
      paramDisplayMetrics.scaledDensity = paramDisplayMetrics.noncompatScaledDensity * f;
      paramDisplayMetrics.xdpi = paramDisplayMetrics.noncompatXdpi * f;
      paramDisplayMetrics.ydpi = paramDisplayMetrics.noncompatYdpi * f;
      paramDisplayMetrics.widthPixels = (int)(paramDisplayMetrics.widthPixels * f + 0.5F);
      paramDisplayMetrics.heightPixels = (int)(paramDisplayMetrics.heightPixels * f + 0.5F);
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    try {
      paramObject = paramObject;
      if (this.mCompatibilityFlags != ((CompatibilityInfo)paramObject).mCompatibilityFlags)
        return false; 
      if (this.applicationDensity != ((CompatibilityInfo)paramObject).applicationDensity)
        return false; 
      if (this.applicationScale != ((CompatibilityInfo)paramObject).applicationScale)
        return false; 
      float f1 = this.applicationInvertedScale;
      float f2 = ((CompatibilityInfo)paramObject).applicationInvertedScale;
      return !(f1 != f2);
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public Translator getTranslator() {
    Translator translator;
    if (isScalingRequired()) {
      translator = new Translator();
    } else {
      translator = null;
    } 
    return translator;
  }
  
  public int hashCode() {
    return (((17 * 31 + this.mCompatibilityFlags) * 31 + this.applicationDensity) * 31 + Float.floatToIntBits(this.applicationScale)) * 31 + Float.floatToIntBits(this.applicationInvertedScale);
  }
  
  public boolean isScalingRequired() {
    int i = this.mCompatibilityFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean needsCompatResources() {
    boolean bool;
    if ((this.mCompatibilityFlags & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean neverSupportsScreen() {
    boolean bool;
    if ((this.mCompatibilityFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean supportsScreen() {
    boolean bool;
    if ((this.mCompatibilityFlags & 0x8) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("{");
    stringBuilder.append(this.applicationDensity);
    stringBuilder.append("dpi");
    if (isScalingRequired()) {
      stringBuilder.append(" ");
      stringBuilder.append(this.applicationScale);
      stringBuilder.append("x");
    } 
    if (!supportsScreen())
      stringBuilder.append(" resizing"); 
    if (neverSupportsScreen())
      stringBuilder.append(" never-compat"); 
    if (alwaysSupportsScreen())
      stringBuilder.append(" always-compat"); 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mCompatibilityFlags);
    paramParcel.writeInt(this.applicationDensity);
    paramParcel.writeFloat(this.applicationScale);
    paramParcel.writeFloat(this.applicationInvertedScale);
  }
  
  public class Translator {
    public final float applicationInvertedScale;
    
    public final float applicationScale;
    
    private Rect mContentInsetsBuffer = null;
    
    private Region mTouchableAreaBuffer = null;
    
    private Rect mVisibleInsetsBuffer = null;
    
    Translator() {
      this(CompatibilityInfo.this.applicationScale, CompatibilityInfo.this.applicationInvertedScale);
    }
    
    Translator(float param1Float1, float param1Float2) {
      this.applicationScale = param1Float1;
      this.applicationInvertedScale = param1Float2;
    }
    
    public Rect getTranslatedContentInsets(Rect param1Rect) {
      if (this.mContentInsetsBuffer == null)
        this.mContentInsetsBuffer = new Rect(); 
      this.mContentInsetsBuffer.set(param1Rect);
      translateRectInAppWindowToScreen(this.mContentInsetsBuffer);
      return this.mContentInsetsBuffer;
    }
    
    public Region getTranslatedTouchableArea(Region param1Region) {
      if (this.mTouchableAreaBuffer == null)
        this.mTouchableAreaBuffer = new Region(); 
      this.mTouchableAreaBuffer.set(param1Region);
      this.mTouchableAreaBuffer.scale(this.applicationScale);
      return this.mTouchableAreaBuffer;
    }
    
    public Rect getTranslatedVisibleInsets(Rect param1Rect) {
      if (this.mVisibleInsetsBuffer == null)
        this.mVisibleInsetsBuffer = new Rect(); 
      this.mVisibleInsetsBuffer.set(param1Rect);
      translateRectInAppWindowToScreen(this.mVisibleInsetsBuffer);
      return this.mVisibleInsetsBuffer;
    }
    
    public void translateCanvas(Canvas param1Canvas) {
      if (this.applicationScale == 1.5F)
        param1Canvas.translate(0.0026143792F, 0.0026143792F); 
      float f = this.applicationScale;
      param1Canvas.scale(f, f);
    }
    
    public void translateEventInScreenToAppWindow(MotionEvent param1MotionEvent) {
      param1MotionEvent.scale(this.applicationInvertedScale);
    }
    
    public void translateLayoutParamsInAppWindowToScreen(WindowManager.LayoutParams param1LayoutParams) {
      param1LayoutParams.scale(this.applicationScale);
    }
    
    public void translatePointInScreenToAppWindow(PointF param1PointF) {
      float f = this.applicationInvertedScale;
      if (f != 1.0F) {
        param1PointF.x *= f;
        param1PointF.y *= f;
      } 
    }
    
    public void translateRectInAppWindowToScreen(Rect param1Rect) {
      param1Rect.scale(this.applicationScale);
    }
    
    public void translateRectInScreenToAppWinFrame(Rect param1Rect) {
      param1Rect.scale(this.applicationInvertedScale);
    }
    
    public void translateRectInScreenToAppWindow(Rect param1Rect) {
      param1Rect.scale(this.applicationInvertedScale);
    }
    
    public void translateRegionInWindowToScreen(Region param1Region) {
      param1Region.scale(this.applicationScale);
    }
    
    public void translateWindowLayout(WindowManager.LayoutParams param1LayoutParams) {
      param1LayoutParams.scale(this.applicationScale);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/CompatibilityInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */