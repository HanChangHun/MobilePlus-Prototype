package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.Shader;

final class BitmapState extends Drawable.ConstantState {
  boolean mAutoMirrored = false;
  
  float mBaseAlpha = 1.0F;
  
  Bitmap mBitmap = null;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  int mChangingConfigurations;
  
  int mGravity = 119;
  
  final Paint mPaint;
  
  boolean mRebuildShader;
  
  int mSrcDensityOverride = 0;
  
  int mTargetDensity = 160;
  
  int[] mThemeAttrs = null;
  
  Shader.TileMode mTileModeX = null;
  
  Shader.TileMode mTileModeY = null;
  
  ColorStateList mTint = null;
  
  BitmapState(Bitmap paramBitmap) {
    this.mBitmap = paramBitmap;
    this.mPaint = new Paint(6);
  }
  
  BitmapState(BitmapState paramBitmapState) {
    this.mBitmap = paramBitmapState.mBitmap;
    this.mTint = paramBitmapState.mTint;
    this.mBlendMode = paramBitmapState.mBlendMode;
    this.mThemeAttrs = paramBitmapState.mThemeAttrs;
    this.mChangingConfigurations = paramBitmapState.mChangingConfigurations;
    this.mGravity = paramBitmapState.mGravity;
    this.mTileModeX = paramBitmapState.mTileModeX;
    this.mTileModeY = paramBitmapState.mTileModeY;
    this.mSrcDensityOverride = paramBitmapState.mSrcDensityOverride;
    this.mTargetDensity = paramBitmapState.mTargetDensity;
    this.mBaseAlpha = paramBitmapState.mBaseAlpha;
    this.mPaint = new Paint(paramBitmapState.mPaint);
    this.mRebuildShader = paramBitmapState.mRebuildShader;
    this.mAutoMirrored = paramBitmapState.mAutoMirrored;
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      ColorStateList colorStateList = this.mTint;
      return (colorStateList != null && colorStateList.canApplyTheme());
    } 
    return true;
  }
  
  public int getChangingConfigurations() {
    byte b;
    int i = this.mChangingConfigurations;
    ColorStateList colorStateList = this.mTint;
    if (colorStateList != null) {
      b = colorStateList.getChangingConfigurations();
    } else {
      b = 0;
    } 
    return i | b;
  }
  
  public Drawable newDrawable() {
    return new BitmapDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new BitmapDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/BitmapDrawable$BitmapState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */