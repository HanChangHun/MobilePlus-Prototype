package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.Insets;
import android.util.ArrayMap;
import android.util.FloatProperty;
import android.util.Property;
import com.android.internal.util.VirtualRefBasePtr;
import dalvik.system.VMRuntime;

class VectorDrawableState extends Drawable.ConstantState {
  static final Property<VectorDrawableState, Float> ALPHA = (Property)new FloatProperty<VectorDrawableState>("alpha") {
      public Float get(VectorDrawable.VectorDrawableState param2VectorDrawableState) {
        return Float.valueOf(param2VectorDrawableState.getAlpha());
      }
      
      public void setValue(VectorDrawable.VectorDrawableState param2VectorDrawableState, float param2Float) {
        param2VectorDrawableState.setAlpha(param2Float);
      }
    };
  
  private static final int NATIVE_ALLOCATION_SIZE = 316;
  
  private int mAllocationOfAllNodes = 0;
  
  boolean mAutoMirrored;
  
  int mBaseHeight = 0;
  
  int mBaseWidth = 0;
  
  BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
  
  boolean mCacheDirty;
  
  boolean mCachedAutoMirrored;
  
  BlendMode mCachedBlendMode;
  
  int[] mCachedThemeAttrs;
  
  ColorStateList mCachedTint;
  
  int mChangingConfigurations;
  
  int mDensity = 160;
  
  int mLastHWCachePixelCount = 0;
  
  int mLastSWCachePixelCount = 0;
  
  VirtualRefBasePtr mNativeTree = null;
  
  Insets mOpticalInsets = Insets.NONE;
  
  VectorDrawable.VGroup mRootGroup;
  
  String mRootName = null;
  
  int[] mThemeAttrs;
  
  ColorStateList mTint = null;
  
  final ArrayMap<String, Object> mVGTargetsMap = new ArrayMap();
  
  float mViewportHeight = 0.0F;
  
  float mViewportWidth = 0.0F;
  
  public VectorDrawableState(VectorDrawableState paramVectorDrawableState) {
    if (paramVectorDrawableState != null) {
      this.mThemeAttrs = paramVectorDrawableState.mThemeAttrs;
      this.mChangingConfigurations = paramVectorDrawableState.mChangingConfigurations;
      this.mTint = paramVectorDrawableState.mTint;
      this.mBlendMode = paramVectorDrawableState.mBlendMode;
      this.mAutoMirrored = paramVectorDrawableState.mAutoMirrored;
      VectorDrawable.VGroup vGroup = new VectorDrawable.VGroup(paramVectorDrawableState.mRootGroup, this.mVGTargetsMap);
      this.mRootGroup = vGroup;
      createNativeTreeFromCopy(paramVectorDrawableState, vGroup);
      this.mBaseWidth = paramVectorDrawableState.mBaseWidth;
      this.mBaseHeight = paramVectorDrawableState.mBaseHeight;
      setViewportSize(paramVectorDrawableState.mViewportWidth, paramVectorDrawableState.mViewportHeight);
      this.mOpticalInsets = paramVectorDrawableState.mOpticalInsets;
      this.mRootName = paramVectorDrawableState.mRootName;
      this.mDensity = paramVectorDrawableState.mDensity;
      String str = paramVectorDrawableState.mRootName;
      if (str != null)
        this.mVGTargetsMap.put(str, this); 
    } else {
      VectorDrawable.VGroup vGroup = new VectorDrawable.VGroup();
      this.mRootGroup = vGroup;
      createNativeTree(vGroup);
    } 
    onTreeConstructionFinished();
  }
  
  private void applyDensityScaling(int paramInt1, int paramInt2) {
    this.mBaseWidth = Drawable.scaleFromDensity(this.mBaseWidth, paramInt1, paramInt2, true);
    this.mBaseHeight = Drawable.scaleFromDensity(this.mBaseHeight, paramInt1, paramInt2, true);
    this.mOpticalInsets = Insets.of(Drawable.scaleFromDensity(this.mOpticalInsets.left, paramInt1, paramInt2, false), Drawable.scaleFromDensity(this.mOpticalInsets.top, paramInt1, paramInt2, false), Drawable.scaleFromDensity(this.mOpticalInsets.right, paramInt1, paramInt2, false), Drawable.scaleFromDensity(this.mOpticalInsets.bottom, paramInt1, paramInt2, false));
  }
  
  private void createNativeTree(VectorDrawable.VGroup paramVGroup) {
    this.mNativeTree = new VirtualRefBasePtr(VectorDrawable.access$300(VectorDrawable.VGroup.access$200(paramVGroup)));
    VMRuntime.getRuntime().registerNativeAllocation(316);
  }
  
  private void createNativeTreeFromCopy(VectorDrawableState paramVectorDrawableState, VectorDrawable.VGroup paramVGroup) {
    this.mNativeTree = new VirtualRefBasePtr(VectorDrawable.access$400(paramVectorDrawableState.mNativeTree.get(), VectorDrawable.VGroup.access$200(paramVGroup)));
    VMRuntime.getRuntime().registerNativeAllocation(316);
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    this.mRootGroup.applyTheme(paramTheme);
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      VectorDrawable.VGroup vGroup = this.mRootGroup;
      if (vGroup == null || !vGroup.canApplyTheme()) {
        ColorStateList colorStateList = this.mTint;
        return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
      } 
    } 
    return true;
  }
  
  public boolean canReuseCache() {
    if (!this.mCacheDirty && this.mCachedThemeAttrs == this.mThemeAttrs && this.mCachedTint == this.mTint && this.mCachedBlendMode == this.mBlendMode && this.mCachedAutoMirrored == this.mAutoMirrored)
      return true; 
    updateCacheStates();
    return false;
  }
  
  public void finalize() throws Throwable {
    super.finalize();
    int i = this.mLastHWCachePixelCount;
    int j = this.mLastSWCachePixelCount;
    VMRuntime.getRuntime().registerNativeFree(this.mAllocationOfAllNodes + 316 + i * 4 + j * 4);
  }
  
  public float getAlpha() {
    return VectorDrawable.access$800(this.mNativeTree.get());
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
  
  long getNativeRenderer() {
    VirtualRefBasePtr virtualRefBasePtr = this.mNativeTree;
    return (virtualRefBasePtr == null) ? 0L : virtualRefBasePtr.get();
  }
  
  Property getProperty(String paramString) {
    return ALPHA.getName().equals(paramString) ? ALPHA : null;
  }
  
  public boolean hasFocusStateSpecified() {
    ColorStateList colorStateList = this.mTint;
    if (colorStateList == null || !colorStateList.hasFocusStateSpecified()) {
      VectorDrawable.VGroup vGroup = this.mRootGroup;
      return (vGroup != null && vGroup.hasFocusStateSpecified());
    } 
    return true;
  }
  
  public boolean isStateful() {
    ColorStateList colorStateList = this.mTint;
    if (colorStateList == null || !colorStateList.isStateful()) {
      VectorDrawable.VGroup vGroup = this.mRootGroup;
      return (vGroup != null && vGroup.isStateful());
    } 
    return true;
  }
  
  public Drawable newDrawable() {
    return new VectorDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new VectorDrawable(this, paramResources, null);
  }
  
  public boolean onStateChange(int[] paramArrayOfint) {
    return this.mRootGroup.onStateChange(paramArrayOfint);
  }
  
  void onTreeConstructionFinished() {
    this.mRootGroup.setTree(this.mNativeTree);
    this.mAllocationOfAllNodes = this.mRootGroup.getNativeSize();
    VMRuntime.getRuntime().registerNativeAllocation(this.mAllocationOfAllNodes);
  }
  
  public boolean setAlpha(float paramFloat) {
    return VectorDrawable.access$700(this.mNativeTree.get(), paramFloat);
  }
  
  public final boolean setDensity(int paramInt) {
    if (this.mDensity != paramInt) {
      int i = this.mDensity;
      this.mDensity = paramInt;
      applyDensityScaling(i, paramInt);
      return true;
    } 
    return false;
  }
  
  void setViewportSize(float paramFloat1, float paramFloat2) {
    this.mViewportWidth = paramFloat1;
    this.mViewportHeight = paramFloat2;
    VectorDrawable.access$600(getNativeRenderer(), paramFloat1, paramFloat2);
  }
  
  public void updateCacheStates() {
    this.mCachedThemeAttrs = this.mThemeAttrs;
    this.mCachedTint = this.mTint;
    this.mCachedBlendMode = this.mBlendMode;
    this.mCachedAutoMirrored = this.mAutoMirrored;
    this.mCacheDirty = false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VectorDrawableState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */