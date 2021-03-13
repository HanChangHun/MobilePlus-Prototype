package android.content.res;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewHierarchyEncoder;

public final class Theme {
  private ResourcesImpl.ThemeImpl mThemeImpl;
  
  private Theme() {}
  
  private String getResourceNameFromHexString(String paramString) {
    return Resources.this.getResourceName(Integer.parseInt(paramString, 16));
  }
  
  public void applyStyle(int paramInt, boolean paramBoolean) {
    this.mThemeImpl.applyStyle(paramInt, paramBoolean);
  }
  
  public void dump(int paramInt, String paramString1, String paramString2) {
    this.mThemeImpl.dump(paramInt, paramString1, paramString2);
  }
  
  public void encode(ViewHierarchyEncoder paramViewHierarchyEncoder) {
    paramViewHierarchyEncoder.beginObject(this);
    String[] arrayOfString = getTheme();
    for (byte b = 0; b < arrayOfString.length; b += 2)
      paramViewHierarchyEncoder.addProperty(arrayOfString[b], arrayOfString[b + 1]); 
    paramViewHierarchyEncoder.endObject();
  }
  
  public int[] getAllAttributes() {
    return this.mThemeImpl.getAllAttributes();
  }
  
  int getAppliedStyleResId() {
    return this.mThemeImpl.getAppliedStyleResId();
  }
  
  public int[] getAttributeResolutionStack(int paramInt1, int paramInt2, int paramInt3) {
    int[] arrayOfInt = this.mThemeImpl.getAttributeResolutionStack(paramInt1, paramInt2, paramInt3);
    return (arrayOfInt == null) ? new int[0] : arrayOfInt;
  }
  
  public int getChangingConfigurations() {
    return this.mThemeImpl.getChangingConfigurations();
  }
  
  public Drawable getDrawable(int paramInt) throws Resources.NotFoundException {
    return Resources.this.getDrawable(paramInt, this);
  }
  
  public int getExplicitStyle(AttributeSet paramAttributeSet) {
    TypedValue typedValue;
    if (paramAttributeSet == null)
      return 0; 
    int i = paramAttributeSet.getStyleAttribute();
    if (i == 0)
      return 0; 
    String str = getResources().getResourceTypeName(i);
    if ("attr".equals(str)) {
      typedValue = new TypedValue();
      if (resolveAttribute(i, typedValue, true))
        return typedValue.resourceId; 
    } else if ("style".equals(typedValue)) {
      return i;
    } 
    return 0;
  }
  
  public Resources.ThemeKey getKey() {
    return this.mThemeImpl.getKey();
  }
  
  long getNativeTheme() {
    return this.mThemeImpl.getNativeTheme();
  }
  
  public Resources getResources() {
    return Resources.this;
  }
  
  @ExportedProperty(category = "theme", hasAdjacentMapping = true)
  public String[] getTheme() {
    return this.mThemeImpl.getTheme();
  }
  
  public TypedArray obtainStyledAttributes(int paramInt, int[] paramArrayOfint) throws Resources.NotFoundException {
    return this.mThemeImpl.obtainStyledAttributes(this, null, paramArrayOfint, 0, paramInt);
  }
  
  public TypedArray obtainStyledAttributes(AttributeSet paramAttributeSet, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    return this.mThemeImpl.obtainStyledAttributes(this, paramAttributeSet, paramArrayOfint, paramInt1, paramInt2);
  }
  
  public TypedArray obtainStyledAttributes(int[] paramArrayOfint) {
    return this.mThemeImpl.obtainStyledAttributes(this, null, paramArrayOfint, 0, 0);
  }
  
  public void rebase() {
    this.mThemeImpl.rebase();
  }
  
  public boolean resolveAttribute(int paramInt, TypedValue paramTypedValue, boolean paramBoolean) {
    return this.mThemeImpl.resolveAttribute(paramInt, paramTypedValue, paramBoolean);
  }
  
  public TypedArray resolveAttributes(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    return this.mThemeImpl.resolveAttributes(this, paramArrayOfint1, paramArrayOfint2);
  }
  
  void setImpl(ResourcesImpl.ThemeImpl paramThemeImpl) {
    this.mThemeImpl = paramThemeImpl;
  }
  
  public void setTo(Theme paramTheme) {
    this.mThemeImpl.setTo(paramTheme.mThemeImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Resources$Theme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */