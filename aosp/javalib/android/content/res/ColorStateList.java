package android.content.res;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MathUtils;
import android.util.SparseArray;
import android.util.StateSet;
import android.util.Xml;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ColorStateList extends ComplexColor implements Parcelable {
  public static final Parcelable.Creator<ColorStateList> CREATOR;
  
  private static final int DEFAULT_COLOR = -65536;
  
  private static final int[][] EMPTY = new int[][] { {} };
  
  private static final String TAG = "ColorStateList";
  
  private static final SparseArray<WeakReference<ColorStateList>> sCache = new SparseArray();
  
  private int mChangingConfigurations;
  
  private int[] mColors;
  
  private int mDefaultColor;
  
  private ColorStateListFactory mFactory;
  
  private boolean mIsOpaque;
  
  private int[][] mStateSpecs;
  
  private int[][] mThemeAttrs;
  
  static {
    CREATOR = new Parcelable.Creator<ColorStateList>() {
        public ColorStateList createFromParcel(Parcel param1Parcel) {
          int i = param1Parcel.readInt();
          int[][] arrayOfInt = new int[i][];
          for (byte b = 0; b < i; b++)
            arrayOfInt[b] = param1Parcel.createIntArray(); 
          return new ColorStateList(arrayOfInt, param1Parcel.createIntArray());
        }
        
        public ColorStateList[] newArray(int param1Int) {
          return new ColorStateList[param1Int];
        }
      };
  }
  
  private ColorStateList() {}
  
  private ColorStateList(ColorStateList paramColorStateList) {
    if (paramColorStateList != null) {
      this.mChangingConfigurations = paramColorStateList.mChangingConfigurations;
      this.mStateSpecs = paramColorStateList.mStateSpecs;
      this.mDefaultColor = paramColorStateList.mDefaultColor;
      this.mIsOpaque = paramColorStateList.mIsOpaque;
      this.mThemeAttrs = (int[][])paramColorStateList.mThemeAttrs.clone();
      this.mColors = (int[])paramColorStateList.mColors.clone();
    } 
  }
  
  public ColorStateList(int[][] paramArrayOfint, int[] paramArrayOfint1) {
    this.mStateSpecs = paramArrayOfint;
    this.mColors = paramArrayOfint1;
    onColorsChanged();
  }
  
  private void applyTheme(Resources.Theme paramTheme) {
    if (this.mThemeAttrs == null)
      return; 
    int i = 0;
    int[][] arrayOfInt = this.mThemeAttrs;
    int j = arrayOfInt.length;
    byte b = 0;
    while (b < j) {
      int k = i;
      if (arrayOfInt[b] != null) {
        TypedArray typedArray = paramTheme.resolveAttributes(arrayOfInt[b], R.styleable.ColorStateListItem);
        if (arrayOfInt[b][0] != 0) {
          f = Color.alpha(this.mColors[b]) / 255.0F;
        } else {
          f = 1.0F;
        } 
        arrayOfInt[b] = typedArray.extractThemeAttrs(arrayOfInt[b]);
        if (arrayOfInt[b] != null)
          i = 1; 
        k = typedArray.getColor(0, this.mColors[b]);
        float f = typedArray.getFloat(1, f);
        this.mColors[b] = modulateColorAlpha(k, f);
        this.mChangingConfigurations |= typedArray.getChangingConfigurations();
        typedArray.recycle();
        k = i;
      } 
      b++;
      i = k;
    } 
    if (i == 0)
      this.mThemeAttrs = null; 
    onColorsChanged();
  }
  
  @Deprecated
  public static ColorStateList createFromXml(Resources paramResources, XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    return createFromXml(paramResources, paramXmlPullParser, null);
  }
  
  public static ColorStateList createFromXml(Resources paramResources, XmlPullParser paramXmlPullParser, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2)
      return createFromXmlInner(paramResources, paramXmlPullParser, attributeSet, paramTheme); 
    throw new XmlPullParserException("No start tag found");
  }
  
  static ColorStateList createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    ColorStateList colorStateList;
    String str = paramXmlPullParser.getName();
    if (str.equals("selector")) {
      colorStateList = new ColorStateList();
      colorStateList.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return colorStateList;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramXmlPullParser.getPositionDescription());
    stringBuilder.append(": invalid color state list tag ");
    stringBuilder.append((String)colorStateList);
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  private void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    int j = 0;
    int k = -65536;
    boolean bool = false;
    int[][] arrayOfInt2 = (int[][])ArrayUtils.newUnpaddedArray(int[].class, 20);
    int[][] arrayOfInt3 = new int[arrayOfInt2.length][];
    int[] arrayOfInt4 = new int[arrayOfInt2.length];
    byte b = 0;
    while (true) {
      int m = paramXmlPullParser.next();
      if (m != 1) {
        int n = paramXmlPullParser.getDepth();
        if (n >= i || m != 3) {
          if (m != 2 || n > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = Resources.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ColorStateListItem);
          int[] arrayOfInt6 = typedArray.extractThemeAttrs();
          int i1 = typedArray.getColor(0, -65281);
          float f = typedArray.getFloat(1, 1.0F);
          int i2 = typedArray.getChangingConfigurations();
          typedArray.recycle();
          n = paramAttributeSet.getAttributeCount();
          int[] arrayOfInt5 = new int[n];
          byte b1 = 0;
          for (m = 0; m < n; m++) {
            int i3 = paramAttributeSet.getAttributeNameResource(m);
            if (i3 != 16843173 && i3 != 16843551) {
              if (!paramAttributeSet.getAttributeBooleanValue(m, false))
                i3 = -i3; 
              arrayOfInt5[b1] = i3;
              b1++;
            } 
          } 
          arrayOfInt5 = StateSet.trimStateSet(arrayOfInt5, b1);
          m = modulateColorAlpha(i1, f);
          if (!b || arrayOfInt5.length == 0)
            k = m; 
          if (arrayOfInt6 != null)
            bool = true; 
          arrayOfInt4 = GrowingArrayUtils.append(arrayOfInt4, b, m);
          arrayOfInt3 = (int[][])GrowingArrayUtils.append((Object[])arrayOfInt3, b, arrayOfInt6);
          arrayOfInt2 = (int[][])GrowingArrayUtils.append((Object[])arrayOfInt2, b, arrayOfInt5);
          b++;
          j |= i2;
          continue;
        } 
      } 
      break;
    } 
    this.mChangingConfigurations = j;
    this.mDefaultColor = k;
    if (bool) {
      int[][] arrayOfInt = new int[b][];
      this.mThemeAttrs = arrayOfInt;
      System.arraycopy(arrayOfInt3, 0, arrayOfInt, 0, b);
    } else {
      this.mThemeAttrs = null;
    } 
    int[] arrayOfInt1 = new int[b];
    this.mColors = arrayOfInt1;
    this.mStateSpecs = new int[b][];
    System.arraycopy(arrayOfInt4, 0, arrayOfInt1, 0, b);
    System.arraycopy(arrayOfInt2, 0, this.mStateSpecs, 0, b);
    onColorsChanged();
  }
  
  private int modulateColorAlpha(int paramInt, float paramFloat) {
    return (paramFloat == 1.0F) ? paramInt : (0xFFFFFF & paramInt | MathUtils.constrain((int)(Color.alpha(paramInt) * paramFloat + 0.5F), 0, 255) << 24);
  }
  
  private void onColorsChanged() {
    int i = -65536;
    boolean bool1 = true;
    int[][] arrayOfInt = this.mStateSpecs;
    int[] arrayOfInt1 = this.mColors;
    int j = arrayOfInt.length;
    boolean bool2 = bool1;
    if (j > 0) {
      int m;
      int k = arrayOfInt1[0];
      i = j - 1;
      while (true) {
        m = k;
        if (i > 0) {
          if ((arrayOfInt[i]).length == 0) {
            m = arrayOfInt1[i];
            break;
          } 
          i--;
          continue;
        } 
        break;
      } 
      k = 0;
      while (true) {
        i = m;
        bool2 = bool1;
        if (k < j) {
          if (Color.alpha(arrayOfInt1[k]) != 255) {
            bool2 = false;
            i = m;
            break;
          } 
          k++;
          continue;
        } 
        break;
      } 
    } 
    this.mDefaultColor = i;
    this.mIsOpaque = bool2;
  }
  
  public static ColorStateList valueOf(int paramInt) {
    synchronized (sCache) {
      int i = sCache.indexOfKey(paramInt);
      if (i >= 0) {
        ColorStateList colorStateList1 = ((WeakReference<ColorStateList>)sCache.valueAt(i)).get();
        if (colorStateList1 != null)
          return colorStateList1; 
        sCache.removeAt(i);
      } 
      for (i = sCache.size() - 1; i >= 0; i--) {
        if (((WeakReference)sCache.valueAt(i)).get() == null)
          sCache.removeAt(i); 
      } 
      ColorStateList colorStateList = new ColorStateList();
      this(EMPTY, new int[] { paramInt });
      SparseArray<WeakReference<ColorStateList>> sparseArray = sCache;
      WeakReference weakReference = new WeakReference();
      this((T)colorStateList);
      sparseArray.put(paramInt, weakReference);
      return colorStateList;
    } 
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    if (this.mThemeAttrs != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mChangingConfigurations;
  }
  
  public int getColorForState(int[] paramArrayOfint, int paramInt) {
    int i = this.mStateSpecs.length;
    for (byte b = 0; b < i; b++) {
      if (StateSet.stateSetMatches(this.mStateSpecs[b], paramArrayOfint))
        return this.mColors[b]; 
    } 
    return paramInt;
  }
  
  public int[] getColors() {
    return this.mColors;
  }
  
  public ConstantState<ComplexColor> getConstantState() {
    if (this.mFactory == null)
      this.mFactory = new ColorStateListFactory(this); 
    return this.mFactory;
  }
  
  public int getDefaultColor() {
    return this.mDefaultColor;
  }
  
  public int[][] getStates() {
    return this.mStateSpecs;
  }
  
  public boolean hasFocusStateSpecified() {
    return StateSet.containsAttribute(this.mStateSpecs, 16842908);
  }
  
  public boolean hasState(int paramInt) {
    for (int[] arrayOfInt : this.mStateSpecs) {
      int i = arrayOfInt.length;
      for (byte b = 0; b < i; b++) {
        if (arrayOfInt[b] == paramInt || arrayOfInt[b] == paramInt)
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean isOpaque() {
    return this.mIsOpaque;
  }
  
  public boolean isStateful() {
    int[][] arrayOfInt = this.mStateSpecs;
    int i = arrayOfInt.length;
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (i >= 1) {
      bool2 = bool1;
      if ((arrayOfInt[0]).length > 0)
        bool2 = true; 
    } 
    return bool2;
  }
  
  public ColorStateList obtainForTheme(Resources.Theme paramTheme) {
    if (paramTheme == null || !canApplyTheme())
      return this; 
    ColorStateList colorStateList = new ColorStateList(this);
    colorStateList.applyTheme(paramTheme);
    return colorStateList;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ColorStateList{mThemeAttrs=");
    stringBuilder.append(Arrays.deepToString((Object[])this.mThemeAttrs));
    stringBuilder.append("mChangingConfigurations=");
    stringBuilder.append(this.mChangingConfigurations);
    stringBuilder.append("mStateSpecs=");
    stringBuilder.append(Arrays.deepToString((Object[])this.mStateSpecs));
    stringBuilder.append("mColors=");
    stringBuilder.append(Arrays.toString(this.mColors));
    stringBuilder.append("mDefaultColor=");
    stringBuilder.append(this.mDefaultColor);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public ColorStateList withAlpha(int paramInt) {
    int[] arrayOfInt = new int[this.mColors.length];
    int i = arrayOfInt.length;
    for (byte b = 0; b < i; b++)
      arrayOfInt[b] = this.mColors[b] & 0xFFFFFF | paramInt << 24; 
    return new ColorStateList(this.mStateSpecs, arrayOfInt);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (canApplyTheme())
      Log.w("ColorStateList", "Wrote partially-resolved ColorStateList to parcel!"); 
    int i = this.mStateSpecs.length;
    paramParcel.writeInt(i);
    for (paramInt = 0; paramInt < i; paramInt++)
      paramParcel.writeIntArray(this.mStateSpecs[paramInt]); 
    paramParcel.writeIntArray(this.mColors);
  }
  
  private static class ColorStateListFactory extends ConstantState<ComplexColor> {
    private final ColorStateList mSrc;
    
    public ColorStateListFactory(ColorStateList param1ColorStateList) {
      this.mSrc = param1ColorStateList;
    }
    
    public int getChangingConfigurations() {
      return this.mSrc.mChangingConfigurations;
    }
    
    public ColorStateList newInstance() {
      return this.mSrc;
    }
    
    public ColorStateList newInstance(Resources param1Resources, Resources.Theme param1Theme) {
      return this.mSrc.obtainForTheme(param1Theme);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ColorStateList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */