package android.content.res;

import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.util.TypedValue;

public class ThemeImpl {
  private final AssetManager mAssets;
  
  private final Resources.ThemeKey mKey = new Resources.ThemeKey();
  
  private final long mTheme;
  
  private int mThemeResId = 0;
  
  ThemeImpl() {
    AssetManager assetManager = paramResourcesImpl.mAssets;
    this.mAssets = assetManager;
    this.mTheme = assetManager.createTheme();
  }
  
  void applyStyle(int paramInt, boolean paramBoolean) {
    synchronized (this.mKey) {
      this.mAssets.applyStyleToTheme(this.mTheme, paramInt, paramBoolean);
      this.mThemeResId = paramInt;
      this.mKey.append(paramInt, paramBoolean);
      return;
    } 
  }
  
  public void dump(int paramInt, String paramString1, String paramString2) {
    synchronized (this.mKey) {
      this.mAssets.dumpTheme(this.mTheme, paramInt, paramString1, paramString2);
      return;
    } 
  }
  
  protected void finalize() throws Throwable {
    super.finalize();
    this.mAssets.releaseTheme(this.mTheme);
  }
  
  int[] getAllAttributes() {
    return this.mAssets.getStyleAttributes(getAppliedStyleResId());
  }
  
  int getAppliedStyleResId() {
    return this.mThemeResId;
  }
  
  public int[] getAttributeResolutionStack(int paramInt1, int paramInt2, int paramInt3) {
    synchronized (this.mKey) {
      return this.mAssets.getAttributeResolutionStack(this.mTheme, paramInt1, paramInt2, paramInt3);
    } 
  }
  
  int getChangingConfigurations() {
    synchronized (this.mKey) {
      return ActivityInfo.activityInfoConfigNativeToJava(AssetManager.nativeThemeGetChangingConfigurations(this.mTheme));
    } 
  }
  
  Resources.ThemeKey getKey() {
    return this.mKey;
  }
  
  long getNativeTheme() {
    return this.mTheme;
  }
  
  String[] getTheme() {
    synchronized (this.mKey) {
      int i = this.mKey.mCount;
      String[] arrayOfString = new String[i * 2];
      byte b = 0;
      i--;
      while (b < arrayOfString.length) {
        String str;
        int j = this.mKey.mResId[i];
        boolean bool = this.mKey.mForce[i];
        try {
          arrayOfString[b] = ResourcesImpl.this.getResourceName(j);
        } catch (NotFoundException notFoundException) {
          arrayOfString[b] = Integer.toHexString(b);
        } 
        if (bool) {
          str = "forced";
        } else {
          str = "not forced";
        } 
        arrayOfString[b + 1] = str;
        b += 2;
        i--;
      } 
      return arrayOfString;
    } 
  }
  
  TypedArray obtainStyledAttributes(Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    Resources.ThemeKey themeKey = this.mKey;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{android/content/res/Resources}.Landroid/content/res/Resources$ThemeKey;}, name=null} */
    try {
      int i = paramArrayOfint.length;
      TypedArray typedArray = TypedArray.obtain(paramTheme.getResources(), i);
      paramAttributeSet = paramAttributeSet;
      this.mAssets.applyStyle(this.mTheme, paramInt1, paramInt2, (XmlBlock.Parser)paramAttributeSet, paramArrayOfint, typedArray.mDataAddress, typedArray.mIndicesAddress);
      try {
        typedArray.mTheme = paramTheme;
        typedArray.mXml = (XmlBlock.Parser)paramAttributeSet;
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{android/content/res/Resources}.Landroid/content/res/Resources$ThemeKey;}, name=null} */
        return typedArray;
      } finally {}
    } finally {}
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=InnerObjectType{ObjectType{android/content/res/Resources}.Landroid/content/res/Resources$ThemeKey;}, name=null} */
    throw paramTheme;
  }
  
  void rebase() {
    synchronized (this.mKey) {
      AssetManager.nativeThemeClear(this.mTheme);
      for (byte b = 0; b < this.mKey.mCount; b++) {
        int i = this.mKey.mResId[b];
        boolean bool = this.mKey.mForce[b];
        this.mAssets.applyStyleToTheme(this.mTheme, i, bool);
      } 
      return;
    } 
  }
  
  boolean resolveAttribute(int paramInt, TypedValue paramTypedValue, boolean paramBoolean) {
    synchronized (this.mKey) {
      paramBoolean = this.mAssets.getThemeValue(this.mTheme, paramInt, paramTypedValue, paramBoolean);
      return paramBoolean;
    } 
  }
  
  TypedArray resolveAttributes(Resources.Theme paramTheme, int[] paramArrayOfint1, int[] paramArrayOfint2) {
    synchronized (this.mKey) {
      int i = paramArrayOfint2.length;
      if (paramArrayOfint1 != null && i == paramArrayOfint1.length) {
        TypedArray typedArray = TypedArray.obtain(paramTheme.getResources(), i);
        this.mAssets.resolveAttrs(this.mTheme, 0, 0, paramArrayOfint1, paramArrayOfint2, typedArray.mData, typedArray.mIndices);
        typedArray.mTheme = paramTheme;
        typedArray.mXml = null;
        return typedArray;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Base attribute values must the same length as attrs");
      throw illegalArgumentException;
    } 
  }
  
  void setTo(ThemeImpl paramThemeImpl) {
    synchronized (this.mKey) {
      synchronized (paramThemeImpl.mKey) {
        this.mAssets.setThemeTo(this.mTheme, paramThemeImpl.mAssets, paramThemeImpl.mTheme);
        this.mThemeResId = paramThemeImpl.mThemeResId;
        this.mKey.setTo(paramThemeImpl.getKey());
        return;
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ResourcesImpl$ThemeImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */