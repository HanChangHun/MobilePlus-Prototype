package android.hardware.display;

import com.android.internal.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Builder {
  private static final int MAX_CORRECTIONS_BY_CATEGORY = 20;
  
  private static final int MAX_CORRECTIONS_BY_PACKAGE_NAME = 20;
  
  private Map<Integer, BrightnessCorrection> mCorrectionsByCategory;
  
  private Map<String, BrightnessCorrection> mCorrectionsByPackageName;
  
  private float[] mCurveLux;
  
  private float[] mCurveNits;
  
  private String mDescription;
  
  private float mShortTermModelLowerLuxMultiplier = Float.NaN;
  
  private long mShortTermModelTimeout = -1L;
  
  private float mShortTermModelUpperLuxMultiplier = Float.NaN;
  
  private boolean mShouldCollectColorSamples;
  
  public Builder(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    Objects.requireNonNull(paramArrayOffloat1);
    Objects.requireNonNull(paramArrayOffloat2);
    if (paramArrayOffloat1.length != 0 && paramArrayOffloat2.length != 0) {
      if (paramArrayOffloat1.length == paramArrayOffloat2.length) {
        if (paramArrayOffloat1[0] == 0.0F) {
          Preconditions.checkArrayElementsInRange(paramArrayOffloat1, 0.0F, Float.MAX_VALUE, "lux");
          Preconditions.checkArrayElementsInRange(paramArrayOffloat2, 0.0F, Float.MAX_VALUE, "nits");
          checkMonotonic(paramArrayOffloat1, true, "lux");
          checkMonotonic(paramArrayOffloat2, false, "nits");
          this.mCurveLux = paramArrayOffloat1;
          this.mCurveNits = paramArrayOffloat2;
          this.mCorrectionsByPackageName = new HashMap<>();
          this.mCorrectionsByCategory = new HashMap<>();
          return;
        } 
        throw new IllegalArgumentException("Initial control point must be for 0 lux");
      } 
      throw new IllegalArgumentException("Lux and nits arrays must be the same length");
    } 
    throw new IllegalArgumentException("Lux and nits arrays must not be empty");
  }
  
  private static void checkMonotonic(float[] paramArrayOffloat, boolean paramBoolean, String paramString) {
    if (paramArrayOffloat.length <= 1)
      return; 
    float f = paramArrayOffloat[0];
    for (byte b = 1; b < paramArrayOffloat.length; b++) {
      String str1;
      if (f > paramArrayOffloat[b] || (f == paramArrayOffloat[b] && paramBoolean)) {
        if (paramBoolean) {
          str1 = "strictly increasing";
        } else {
          str1 = "monotonic";
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" values must be ");
        stringBuilder.append(str1);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      String str2 = str1[b];
    } 
  }
  
  public Builder addCorrectionByCategory(int paramInt, BrightnessCorrection paramBrightnessCorrection) {
    Objects.requireNonNull(paramBrightnessCorrection, "correction must not be null");
    if (this.mCorrectionsByCategory.size() < getMaxCorrectionsByCategory()) {
      this.mCorrectionsByCategory.put(Integer.valueOf(paramInt), paramBrightnessCorrection);
      return this;
    } 
    throw new IllegalArgumentException("Too many corrections by category");
  }
  
  public Builder addCorrectionByPackageName(String paramString, BrightnessCorrection paramBrightnessCorrection) {
    Objects.requireNonNull(paramString, "packageName must not be null");
    Objects.requireNonNull(paramBrightnessCorrection, "correction must not be null");
    if (this.mCorrectionsByPackageName.size() < getMaxCorrectionsByPackageName()) {
      this.mCorrectionsByPackageName.put(paramString, paramBrightnessCorrection);
      return this;
    } 
    throw new IllegalArgumentException("Too many corrections by package name");
  }
  
  public BrightnessConfiguration build() {
    if (this.mCurveLux != null && this.mCurveNits != null)
      return new BrightnessConfiguration(this.mCurveLux, this.mCurveNits, this.mCorrectionsByPackageName, this.mCorrectionsByCategory, this.mDescription, this.mShouldCollectColorSamples, this.mShortTermModelTimeout, this.mShortTermModelLowerLuxMultiplier, this.mShortTermModelUpperLuxMultiplier, null); 
    throw new IllegalStateException("A curve must be set!");
  }
  
  public int getMaxCorrectionsByCategory() {
    return 20;
  }
  
  public int getMaxCorrectionsByPackageName() {
    return 20;
  }
  
  public Builder setDescription(String paramString) {
    this.mDescription = paramString;
    return this;
  }
  
  public Builder setShortTermModelLowerLuxMultiplier(float paramFloat) {
    if (paramFloat >= 0.0F) {
      this.mShortTermModelLowerLuxMultiplier = paramFloat;
      return this;
    } 
    throw new IllegalArgumentException("Negative lux multiplier");
  }
  
  public Builder setShortTermModelTimeoutMillis(long paramLong) {
    this.mShortTermModelTimeout = paramLong;
    return this;
  }
  
  public Builder setShortTermModelUpperLuxMultiplier(float paramFloat) {
    if (paramFloat >= 0.0F) {
      this.mShortTermModelUpperLuxMultiplier = paramFloat;
      return this;
    } 
    throw new IllegalArgumentException("Negative lux multiplier");
  }
  
  public Builder setShouldCollectColorSamples(boolean paramBoolean) {
    this.mShouldCollectColorSamples = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessConfiguration$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */