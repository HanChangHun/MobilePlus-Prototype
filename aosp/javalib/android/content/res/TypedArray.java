package android.content.res;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.android.internal.util.XmlUtils;
import dalvik.system.VMRuntime;
import java.util.Arrays;

public class TypedArray {
  static final int STYLE_ASSET_COOKIE = 2;
  
  static final int STYLE_CHANGING_CONFIGURATIONS = 4;
  
  static final int STYLE_DATA = 1;
  
  static final int STYLE_DENSITY = 5;
  
  static final int STYLE_NUM_ENTRIES = 7;
  
  static final int STYLE_RESOURCE_ID = 3;
  
  static final int STYLE_SOURCE_RESOURCE_ID = 6;
  
  static final int STYLE_TYPE = 0;
  
  private AssetManager mAssets;
  
  int[] mData;
  
  long mDataAddress;
  
  int[] mIndices;
  
  long mIndicesAddress;
  
  int mLength;
  
  private DisplayMetrics mMetrics;
  
  private boolean mRecycled;
  
  private final Resources mResources;
  
  Resources.Theme mTheme;
  
  TypedValue mValue = new TypedValue();
  
  XmlBlock.Parser mXml;
  
  protected TypedArray(Resources paramResources) {
    this.mResources = paramResources;
    this.mMetrics = paramResources.getDisplayMetrics();
    this.mAssets = this.mResources.getAssets();
  }
  
  private boolean getValueAt(int paramInt, TypedValue paramTypedValue) {
    CharSequence charSequence;
    int[] arrayOfInt = this.mData;
    int i = arrayOfInt[paramInt + 0];
    if (i == 0)
      return false; 
    paramTypedValue.type = i;
    paramTypedValue.data = arrayOfInt[paramInt + 1];
    paramTypedValue.assetCookie = arrayOfInt[paramInt + 2];
    paramTypedValue.resourceId = arrayOfInt[paramInt + 3];
    paramTypedValue.changingConfigurations = ActivityInfo.activityInfoConfigNativeToJava(arrayOfInt[paramInt + 4]);
    paramTypedValue.density = arrayOfInt[paramInt + 5];
    if (i == 3) {
      charSequence = loadStringValueAt(paramInt);
    } else {
      charSequence = null;
    } 
    paramTypedValue.string = charSequence;
    paramTypedValue.sourceResourceId = arrayOfInt[paramInt + 6];
    return true;
  }
  
  private CharSequence loadStringValueAt(int paramInt) {
    int[] arrayOfInt = this.mData;
    int i = arrayOfInt[paramInt + 2];
    if (i < 0) {
      XmlBlock.Parser parser = this.mXml;
      return (parser != null) ? parser.getPooledString(arrayOfInt[paramInt + 1]) : null;
    } 
    return this.mAssets.getPooledStringForCookie(i, arrayOfInt[paramInt + 1]);
  }
  
  static TypedArray obtain(Resources paramResources, int paramInt) {
    TypedArray typedArray1 = (TypedArray)paramResources.mTypedArrayPool.acquire();
    TypedArray typedArray2 = typedArray1;
    if (typedArray1 == null)
      typedArray2 = new TypedArray(paramResources); 
    typedArray2.mRecycled = false;
    typedArray2.mAssets = paramResources.getAssets();
    typedArray2.mMetrics = paramResources.getDisplayMetrics();
    typedArray2.resize(paramInt);
    return typedArray2;
  }
  
  private void resize(int paramInt) {
    this.mLength = paramInt;
    int i = paramInt * 7;
    VMRuntime vMRuntime = VMRuntime.getRuntime();
    if (this.mDataAddress == 0L || this.mData.length < i) {
      int[] arrayOfInt = (int[])vMRuntime.newNonMovableArray(int.class, i);
      this.mData = arrayOfInt;
      this.mDataAddress = vMRuntime.addressOf(arrayOfInt);
      arrayOfInt = (int[])vMRuntime.newNonMovableArray(int.class, paramInt + 1);
      this.mIndices = arrayOfInt;
      this.mIndicesAddress = vMRuntime.addressOf(arrayOfInt);
    } 
  }
  
  public int[] extractThemeAttrs() {
    return extractThemeAttrs(null);
  }
  
  public int[] extractThemeAttrs(int[] paramArrayOfint) {
    if (!this.mRecycled) {
      int[] arrayOfInt1 = null;
      int[] arrayOfInt2 = this.mData;
      int i = length();
      byte b = 0;
      while (b < i) {
        int[] arrayOfInt;
        int j = b * 7;
        if (arrayOfInt2[j + 0] != 2) {
          arrayOfInt = arrayOfInt1;
        } else {
          arrayOfInt2[j + 0] = 0;
          j = arrayOfInt2[j + 1];
          if (j == 0) {
            arrayOfInt = arrayOfInt1;
          } else {
            arrayOfInt = arrayOfInt1;
            if (arrayOfInt1 == null)
              if (paramArrayOfint != null && paramArrayOfint.length == i) {
                arrayOfInt = paramArrayOfint;
                Arrays.fill(arrayOfInt, 0);
              } else {
                arrayOfInt = new int[i];
              }  
            arrayOfInt[b] = j;
          } 
        } 
        b++;
        arrayOfInt1 = arrayOfInt;
      } 
      return arrayOfInt1;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public boolean getBoolean(int paramInt, boolean paramBoolean) {
    if (!this.mRecycled) {
      int i = paramInt * 7;
      int[] arrayOfInt = this.mData;
      paramInt = arrayOfInt[i + 0];
      if (paramInt == 0)
        return paramBoolean; 
      if (paramInt >= 16 && paramInt <= 31) {
        if (arrayOfInt[i + 1] != 0) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        } 
        return paramBoolean;
      } 
      TypedValue typedValue = this.mValue;
      if (getValueAt(i, typedValue)) {
        StrictMode.noteResourceMismatch(typedValue);
        return XmlUtils.convertValueToBoolean(typedValue.coerceToString(), paramBoolean);
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getBoolean of bad type: 0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getChangingConfigurations() {
    if (!this.mRecycled) {
      int i = 0;
      int[] arrayOfInt = this.mData;
      int j = length();
      for (byte b = 0; b < j; b++) {
        int k = b * 7;
        if (arrayOfInt[k + 0] != 0)
          i |= ActivityInfo.activityInfoConfigNativeToJava(arrayOfInt[k + 4]); 
      } 
      return i;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getColor(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      int i = paramInt1 * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j == 0)
        return paramInt2; 
      if (j >= 16 && j <= 31)
        return arrayOfInt[i + 1]; 
      if (j == 3) {
        TypedValue typedValue = this.mValue;
        return getValueAt(i, typedValue) ? this.mResources.loadColorStateList(typedValue, typedValue.resourceId, this.mTheme).getDefaultColor() : paramInt2;
      } 
      if (j == 2) {
        TypedValue typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't convert value at index ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" to color: type=0x");
      stringBuilder.append(Integer.toHexString(j));
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public ColorStateList getColorStateList(int paramInt) {
    if (!this.mRecycled) {
      TypedValue typedValue = this.mValue;
      if (getValueAt(paramInt * 7, typedValue)) {
        if (typedValue.type != 2)
          return this.mResources.loadColorStateList(typedValue, typedValue.resourceId, this.mTheme); 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to resolve attribute at index ");
        stringBuilder.append(paramInt);
        stringBuilder.append(": ");
        stringBuilder.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
      return null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public ComplexColor getComplexColor(int paramInt) {
    if (!this.mRecycled) {
      TypedValue typedValue = this.mValue;
      if (getValueAt(paramInt * 7, typedValue)) {
        if (typedValue.type != 2)
          return this.mResources.loadComplexColor(typedValue, typedValue.resourceId, this.mTheme); 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to resolve attribute at index ");
        stringBuilder.append(paramInt);
        stringBuilder.append(": ");
        stringBuilder.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
      return null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public float getDimension(int paramInt, float paramFloat) {
    if (!this.mRecycled) {
      int i = paramInt * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j == 0)
        return paramFloat; 
      if (j == 5)
        return TypedValue.complexToDimension(arrayOfInt[i + 1], this.mMetrics); 
      if (j == 2) {
        TypedValue typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't convert value at index ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" to dimension: type=0x");
      stringBuilder.append(Integer.toHexString(j));
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getDimensionPixelOffset(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      int i = paramInt1 * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j == 0)
        return paramInt2; 
      if (j == 5)
        return TypedValue.complexToDimensionPixelOffset(arrayOfInt[i + 1], this.mMetrics); 
      if (j == 2) {
        TypedValue typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't convert value at index ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" to dimension: type=0x");
      stringBuilder.append(Integer.toHexString(j));
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getDimensionPixelSize(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      int i = paramInt1 * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j == 0)
        return paramInt2; 
      if (j == 5)
        return TypedValue.complexToDimensionPixelSize(arrayOfInt[i + 1], this.mMetrics); 
      if (j == 2) {
        TypedValue typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't convert value at index ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" to dimension: type=0x");
      stringBuilder.append(Integer.toHexString(j));
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public Drawable getDrawable(int paramInt) {
    return getDrawableForDensity(paramInt, 0);
  }
  
  public Drawable getDrawableForDensity(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      TypedValue typedValue = this.mValue;
      if (getValueAt(paramInt1 * 7, typedValue)) {
        if (typedValue.type != 2) {
          if (paramInt2 > 0)
            this.mResources.getValueForDensity(typedValue.resourceId, paramInt2, typedValue, true); 
          return this.mResources.loadDrawable(typedValue, typedValue.resourceId, paramInt2, this.mTheme);
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to resolve attribute at index ");
        stringBuilder.append(paramInt1);
        stringBuilder.append(": ");
        stringBuilder.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
      return null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public float getFloat(int paramInt, float paramFloat) {
    if (!this.mRecycled) {
      int i = paramInt * 7;
      int[] arrayOfInt = this.mData;
      paramInt = arrayOfInt[i + 0];
      if (paramInt == 0)
        return paramFloat; 
      if (paramInt == 4)
        return Float.intBitsToFloat(arrayOfInt[i + 1]); 
      if (paramInt >= 16 && paramInt <= 31)
        return arrayOfInt[i + 1]; 
      TypedValue typedValue = this.mValue;
      if (getValueAt(i, typedValue)) {
        CharSequence charSequence = typedValue.coerceToString();
        if (charSequence != null) {
          StrictMode.noteResourceMismatch(typedValue);
          return Float.parseFloat(charSequence.toString());
        } 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getFloat of bad type: 0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public Typeface getFont(int paramInt) {
    if (!this.mRecycled) {
      TypedValue typedValue = this.mValue;
      if (getValueAt(paramInt * 7, typedValue)) {
        if (typedValue.type != 2)
          return this.mResources.getFont(typedValue, typedValue.resourceId); 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to resolve attribute at index ");
        stringBuilder.append(paramInt);
        stringBuilder.append(": ");
        stringBuilder.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
      return null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public float getFraction(int paramInt1, int paramInt2, int paramInt3, float paramFloat) {
    if (!this.mRecycled) {
      int i = paramInt1 * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j == 0)
        return paramFloat; 
      if (j == 6)
        return TypedValue.complexToFraction(arrayOfInt[i + 1], paramInt2, paramInt3); 
      if (j == 2) {
        TypedValue typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't convert value at index ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" to fraction: type=0x");
      stringBuilder.append(Integer.toHexString(j));
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getIndex(int paramInt) {
    if (!this.mRecycled)
      return this.mIndices[paramInt + 1]; 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getIndexCount() {
    if (!this.mRecycled)
      return this.mIndices[0]; 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getInt(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      int i = paramInt1 * 7;
      int[] arrayOfInt = this.mData;
      paramInt1 = arrayOfInt[i + 0];
      if (paramInt1 == 0)
        return paramInt2; 
      if (paramInt1 >= 16 && paramInt1 <= 31)
        return arrayOfInt[i + 1]; 
      TypedValue typedValue = this.mValue;
      if (getValueAt(i, typedValue)) {
        StrictMode.noteResourceMismatch(typedValue);
        return XmlUtils.convertValueToInt(typedValue.coerceToString(), paramInt2);
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getInt of bad type: 0x");
      stringBuilder.append(Integer.toHexString(paramInt1));
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getInteger(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      int i = paramInt1 * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j == 0)
        return paramInt2; 
      if (j >= 16 && j <= 31)
        return arrayOfInt[i + 1]; 
      if (j == 2) {
        TypedValue typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt1);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't convert value at index ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" to integer: type=0x");
      stringBuilder.append(Integer.toHexString(j));
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getLayoutDimension(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      paramInt1 *= 7;
      int[] arrayOfInt = this.mData;
      int i = arrayOfInt[paramInt1 + 0];
      return (i >= 16 && i <= 31) ? arrayOfInt[paramInt1 + 1] : ((i == 5) ? TypedValue.complexToDimensionPixelSize(arrayOfInt[paramInt1 + 1], this.mMetrics) : paramInt2);
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getLayoutDimension(int paramInt, String paramString) {
    if (!this.mRecycled) {
      TypedValue typedValue;
      int i = paramInt * 7;
      int[] arrayOfInt = this.mData;
      int j = arrayOfInt[i + 0];
      if (j >= 16 && j <= 31)
        return arrayOfInt[i + 1]; 
      if (j == 5)
        return TypedValue.complexToDimensionPixelSize(arrayOfInt[i + 1], this.mMetrics); 
      if (j == 2) {
        typedValue = this.mValue;
        getValueAt(i, typedValue);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to resolve attribute at index ");
        stringBuilder1.append(paramInt);
        stringBuilder1.append(": ");
        stringBuilder1.append(typedValue);
        throw new UnsupportedOperationException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(getPositionDescription());
      stringBuilder.append(": You must supply a ");
      stringBuilder.append((String)typedValue);
      stringBuilder.append(" attribute.");
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public String getNonConfigurationString(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      paramInt1 *= 7;
      int[] arrayOfInt = this.mData;
      int i = arrayOfInt[paramInt1 + 0];
      int j = ActivityInfo.activityInfoConfigNativeToJava(arrayOfInt[paramInt1 + 4]);
      arrayOfInt = null;
      if ((paramInt2 & j) != 0)
        return null; 
      if (i == 0)
        return null; 
      if (i == 3)
        return loadStringValueAt(paramInt1).toString(); 
      TypedValue typedValue = this.mValue;
      if (getValueAt(paramInt1, typedValue)) {
        String str;
        CharSequence charSequence = typedValue.coerceToString();
        if (charSequence != null)
          str = charSequence.toString(); 
        return str;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getNonConfigurationString of bad type: 0x");
      stringBuilder.append(Integer.toHexString(i));
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public String getNonResourceString(int paramInt) {
    if (!this.mRecycled) {
      paramInt *= 7;
      int[] arrayOfInt = this.mData;
      return (arrayOfInt[paramInt + 0] == 3 && arrayOfInt[paramInt + 2] < 0) ? this.mXml.getPooledString(arrayOfInt[paramInt + 1]).toString() : null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public String getPositionDescription() {
    if (!this.mRecycled) {
      String str;
      XmlBlock.Parser parser = this.mXml;
      if (parser != null) {
        str = parser.getPositionDescription();
      } else {
        str = "<internal>";
      } 
      return str;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getResourceId(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      paramInt1 *= 7;
      int[] arrayOfInt = this.mData;
      if (arrayOfInt[paramInt1 + 0] != 0) {
        paramInt1 = arrayOfInt[paramInt1 + 3];
        if (paramInt1 != 0)
          return paramInt1; 
      } 
      return paramInt2;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public Resources getResources() {
    if (!this.mRecycled)
      return this.mResources; 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getSourceResourceId(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      paramInt1 = this.mData[paramInt1 * 7 + 6];
      return (paramInt1 != 0) ? paramInt1 : paramInt2;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public String getString(int paramInt) {
    if (!this.mRecycled) {
      int i = paramInt * 7;
      paramInt = this.mData[i + 0];
      String str = null;
      if (paramInt == 0)
        return null; 
      if (paramInt == 3)
        return loadStringValueAt(i).toString(); 
      TypedValue typedValue = this.mValue;
      if (getValueAt(i, typedValue)) {
        CharSequence charSequence = typedValue.coerceToString();
        if (charSequence != null)
          str = charSequence.toString(); 
        return str;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getString of bad type: 0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public CharSequence getText(int paramInt) {
    if (!this.mRecycled) {
      int i = paramInt * 7;
      paramInt = this.mData[i + 0];
      if (paramInt == 0)
        return null; 
      if (paramInt == 3)
        return loadStringValueAt(i); 
      TypedValue typedValue = this.mValue;
      if (getValueAt(i, typedValue))
        return typedValue.coerceToString(); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getText of bad type: 0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public CharSequence[] getTextArray(int paramInt) {
    if (!this.mRecycled) {
      TypedValue typedValue = this.mValue;
      return getValueAt(paramInt * 7, typedValue) ? this.mResources.getTextArray(typedValue.resourceId) : null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getThemeAttributeId(int paramInt1, int paramInt2) {
    if (!this.mRecycled) {
      paramInt1 *= 7;
      int[] arrayOfInt = this.mData;
      return (arrayOfInt[paramInt1 + 0] == 2) ? arrayOfInt[paramInt1 + 1] : paramInt2;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int getType(int paramInt) {
    if (!this.mRecycled)
      return this.mData[paramInt * 7 + 0]; 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public boolean getValue(int paramInt, TypedValue paramTypedValue) {
    if (!this.mRecycled)
      return getValueAt(paramInt * 7, paramTypedValue); 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public boolean hasValue(int paramInt) {
    if (!this.mRecycled) {
      boolean bool;
      if (this.mData[paramInt * 7 + 0] != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public boolean hasValueOrEmpty(int paramInt) {
    if (!this.mRecycled) {
      paramInt *= 7;
      int[] arrayOfInt = this.mData;
      int i = arrayOfInt[paramInt + 0];
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (i == 0)
        if (arrayOfInt[paramInt + 1] == 1) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      return bool2;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public int length() {
    if (!this.mRecycled)
      return this.mLength; 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public TypedValue peekValue(int paramInt) {
    if (!this.mRecycled) {
      TypedValue typedValue = this.mValue;
      return getValueAt(paramInt * 7, typedValue) ? typedValue : null;
    } 
    throw new RuntimeException("Cannot make calls to a recycled instance!");
  }
  
  public void recycle() {
    if (!this.mRecycled) {
      this.mRecycled = true;
      this.mXml = null;
      this.mTheme = null;
      this.mAssets = null;
      this.mResources.mTypedArrayPool.release(this);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(toString());
    stringBuilder.append(" recycled twice!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public String toString() {
    return Arrays.toString(this.mData);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/TypedArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */