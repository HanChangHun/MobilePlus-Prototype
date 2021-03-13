package android.content.res;

import com.android.internal.util.GrowingArrayUtils;

class ThemeKey implements Cloneable {
  int mCount;
  
  boolean[] mForce;
  
  private int mHashCode = 0;
  
  int[] mResId;
  
  public void append(int paramInt, boolean paramBoolean) {
    if (this.mResId == null)
      this.mResId = new int[4]; 
    if (this.mForce == null)
      this.mForce = new boolean[4]; 
    this.mResId = GrowingArrayUtils.append(this.mResId, this.mCount, paramInt);
    this.mForce = GrowingArrayUtils.append(this.mForce, this.mCount, paramBoolean);
    this.mCount++;
    this.mHashCode = (this.mHashCode * 31 + paramInt) * 31 + paramBoolean;
  }
  
  public ThemeKey clone() {
    ThemeKey themeKey = new ThemeKey();
    themeKey.mResId = this.mResId;
    themeKey.mForce = this.mForce;
    themeKey.mCount = this.mCount;
    themeKey.mHashCode = this.mHashCode;
    return themeKey;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass() || hashCode() != paramObject.hashCode())
      return false; 
    paramObject = paramObject;
    if (this.mCount != ((ThemeKey)paramObject).mCount)
      return false; 
    int i = this.mCount;
    for (byte b = 0; b < i; b++) {
      if (this.mResId[b] != ((ThemeKey)paramObject).mResId[b] || this.mForce[b] != ((ThemeKey)paramObject).mForce[b])
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    return this.mHashCode;
  }
  
  public void setTo(ThemeKey paramThemeKey) {
    int[] arrayOfInt = paramThemeKey.mResId;
    boolean[] arrayOfBoolean2 = null;
    if (arrayOfInt == null) {
      arrayOfInt = null;
    } else {
      arrayOfInt = (int[])arrayOfInt.clone();
    } 
    this.mResId = arrayOfInt;
    boolean[] arrayOfBoolean1 = paramThemeKey.mForce;
    if (arrayOfBoolean1 == null) {
      arrayOfBoolean1 = arrayOfBoolean2;
    } else {
      arrayOfBoolean1 = (boolean[])arrayOfBoolean1.clone();
    } 
    this.mForce = arrayOfBoolean1;
    this.mCount = paramThemeKey.mCount;
    this.mHashCode = paramThemeKey.mHashCode;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Resources$ThemeKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */