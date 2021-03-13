package android.content.res;

import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.android.internal.R;
import com.android.internal.util.GrowingArrayUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class GradientColor extends ComplexColor {
  private static final boolean DBG_GRADIENT = false;
  
  private static final String TAG = "GradientColor";
  
  private static final int TILE_MODE_CLAMP = 0;
  
  private static final int TILE_MODE_MIRROR = 2;
  
  private static final int TILE_MODE_REPEAT = 1;
  
  private int mCenterColor = 0;
  
  private float mCenterX = 0.0F;
  
  private float mCenterY = 0.0F;
  
  private int mChangingConfigurations;
  
  private int mDefaultColor;
  
  private int mEndColor = 0;
  
  private float mEndX = 0.0F;
  
  private float mEndY = 0.0F;
  
  private GradientColorFactory mFactory;
  
  private float mGradientRadius = 0.0F;
  
  private int mGradientType = 0;
  
  private boolean mHasCenterColor = false;
  
  private int[] mItemColors;
  
  private float[] mItemOffsets;
  
  private int[][] mItemsThemeAttrs;
  
  private Shader mShader = null;
  
  private int mStartColor = 0;
  
  private float mStartX = 0.0F;
  
  private float mStartY = 0.0F;
  
  private int[] mThemeAttrs;
  
  private int mTileMode = 0;
  
  private GradientColor() {}
  
  private GradientColor(GradientColor paramGradientColor) {
    if (paramGradientColor != null) {
      this.mChangingConfigurations = paramGradientColor.mChangingConfigurations;
      this.mDefaultColor = paramGradientColor.mDefaultColor;
      this.mShader = paramGradientColor.mShader;
      this.mGradientType = paramGradientColor.mGradientType;
      this.mCenterX = paramGradientColor.mCenterX;
      this.mCenterY = paramGradientColor.mCenterY;
      this.mStartX = paramGradientColor.mStartX;
      this.mStartY = paramGradientColor.mStartY;
      this.mEndX = paramGradientColor.mEndX;
      this.mEndY = paramGradientColor.mEndY;
      this.mStartColor = paramGradientColor.mStartColor;
      this.mCenterColor = paramGradientColor.mCenterColor;
      this.mEndColor = paramGradientColor.mEndColor;
      this.mHasCenterColor = paramGradientColor.mHasCenterColor;
      this.mGradientRadius = paramGradientColor.mGradientRadius;
      this.mTileMode = paramGradientColor.mTileMode;
      int[] arrayOfInt2 = paramGradientColor.mItemColors;
      if (arrayOfInt2 != null)
        this.mItemColors = (int[])arrayOfInt2.clone(); 
      float[] arrayOfFloat = paramGradientColor.mItemOffsets;
      if (arrayOfFloat != null)
        this.mItemOffsets = (float[])arrayOfFloat.clone(); 
      int[] arrayOfInt1 = paramGradientColor.mThemeAttrs;
      if (arrayOfInt1 != null)
        this.mThemeAttrs = (int[])arrayOfInt1.clone(); 
      int[][] arrayOfInt = paramGradientColor.mItemsThemeAttrs;
      if (arrayOfInt != null)
        this.mItemsThemeAttrs = (int[][])arrayOfInt.clone(); 
    } 
  }
  
  private void applyItemsAttrsTheme(Resources.Theme paramTheme) {
    if (this.mItemsThemeAttrs == null)
      return; 
    boolean bool = false;
    int[][] arrayOfInt = this.mItemsThemeAttrs;
    int i = arrayOfInt.length;
    byte b = 0;
    while (b < i) {
      boolean bool1 = bool;
      if (arrayOfInt[b] != null) {
        TypedArray typedArray = paramTheme.resolveAttributes(arrayOfInt[b], R.styleable.GradientColorItem);
        arrayOfInt[b] = typedArray.extractThemeAttrs(arrayOfInt[b]);
        if (arrayOfInt[b] != null)
          bool = true; 
        int[] arrayOfInt1 = this.mItemColors;
        arrayOfInt1[b] = typedArray.getColor(0, arrayOfInt1[b]);
        float[] arrayOfFloat = this.mItemOffsets;
        arrayOfFloat[b] = typedArray.getFloat(1, arrayOfFloat[b]);
        this.mChangingConfigurations |= typedArray.getChangingConfigurations();
        typedArray.recycle();
        bool1 = bool;
      } 
      b++;
      bool = bool1;
    } 
    if (!bool)
      this.mItemsThemeAttrs = null; 
  }
  
  private void applyRootAttrsTheme(Resources.Theme paramTheme) {
    TypedArray typedArray = paramTheme.resolveAttributes(this.mThemeAttrs, R.styleable.GradientColor);
    this.mThemeAttrs = typedArray.extractThemeAttrs(this.mThemeAttrs);
    updateRootElementState(typedArray);
    this.mChangingConfigurations |= typedArray.getChangingConfigurations();
    typedArray.recycle();
  }
  
  private void applyTheme(Resources.Theme paramTheme) {
    if (this.mThemeAttrs != null)
      applyRootAttrsTheme(paramTheme); 
    if (this.mItemsThemeAttrs != null)
      applyItemsAttrsTheme(paramTheme); 
    onColorsChange();
  }
  
  public static GradientColor createFromXml(Resources paramResources, XmlResourceParser paramXmlResourceParser, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlResourceParser);
    while (true) {
      i = paramXmlResourceParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2)
      return createFromXmlInner(paramResources, paramXmlResourceParser, attributeSet, paramTheme); 
    throw new XmlPullParserException("No start tag found");
  }
  
  static GradientColor createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    GradientColor gradientColor;
    String str = paramXmlPullParser.getName();
    if (str.equals("gradient")) {
      gradientColor = new GradientColor();
      gradientColor.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return gradientColor;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramXmlPullParser.getPositionDescription());
    stringBuilder.append(": invalid gradient color tag ");
    stringBuilder.append((String)gradientColor);
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  private void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = Resources.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientColor);
    updateRootElementState(typedArray);
    this.mChangingConfigurations |= typedArray.getChangingConfigurations();
    typedArray.recycle();
    validateXmlContent();
    inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    onColorsChange();
  }
  
  private void inflateChildElements(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    float[] arrayOfFloat = new float[20];
    int[] arrayOfInt = new int[arrayOfFloat.length];
    int[][] arrayOfInt1 = new int[arrayOfFloat.length][];
    byte b = 0;
    boolean bool = false;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = Resources.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientColorItem);
          boolean bool1 = typedArray.hasValue(0);
          boolean bool2 = typedArray.hasValue(1);
          if (bool1 && bool2) {
            int[] arrayOfInt2 = typedArray.extractThemeAttrs();
            k = typedArray.getColor(0, 0);
            float f = typedArray.getFloat(1, 0.0F);
            this.mChangingConfigurations |= typedArray.getChangingConfigurations();
            typedArray.recycle();
            if (arrayOfInt2 != null)
              bool = true; 
            arrayOfInt = GrowingArrayUtils.append(arrayOfInt, b, k);
            arrayOfFloat = GrowingArrayUtils.append(arrayOfFloat, b, f);
            arrayOfInt1 = (int[][])GrowingArrayUtils.append((Object[])arrayOfInt1, b, arrayOfInt2);
            b++;
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramXmlPullParser.getPositionDescription());
          stringBuilder.append(": <item> tag requires a 'color' attribute and a 'offset' attribute!");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
      } 
      break;
    } 
    if (b > 0) {
      if (bool) {
        int[][] arrayOfInt3 = new int[b][];
        this.mItemsThemeAttrs = arrayOfInt3;
        System.arraycopy(arrayOfInt1, 0, arrayOfInt3, 0, b);
      } else {
        this.mItemsThemeAttrs = null;
      } 
      int[] arrayOfInt2 = new int[b];
      this.mItemColors = arrayOfInt2;
      this.mItemOffsets = new float[b];
      System.arraycopy(arrayOfInt, 0, arrayOfInt2, 0, b);
      System.arraycopy(arrayOfFloat, 0, this.mItemOffsets, 0, b);
    } 
  }
  
  private void onColorsChange() {
    float[] arrayOfFloat = null;
    int[] arrayOfInt = this.mItemColors;
    if (arrayOfInt != null) {
      int j = arrayOfInt.length;
      arrayOfInt = new int[j];
      arrayOfFloat = new float[j];
      for (byte b = 0; b < j; b++) {
        arrayOfInt[b] = this.mItemColors[b];
        arrayOfFloat[b] = this.mItemOffsets[b];
      } 
    } else if (this.mHasCenterColor) {
      arrayOfInt = new int[3];
      arrayOfInt[0] = this.mStartColor;
      arrayOfInt[1] = this.mCenterColor;
      arrayOfInt[2] = this.mEndColor;
      arrayOfFloat = new float[3];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.5F;
      arrayOfFloat[2] = 1.0F;
    } else {
      arrayOfInt = new int[2];
      arrayOfInt[0] = this.mStartColor;
      arrayOfInt[1] = this.mEndColor;
    } 
    if (arrayOfInt.length < 2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("<gradient> tag requires 2 color values specified!");
      stringBuilder.append(arrayOfInt.length);
      stringBuilder.append(" ");
      stringBuilder.append(arrayOfInt);
      Log.w("GradientColor", stringBuilder.toString());
    } 
    int i = this.mGradientType;
    if (i == 0) {
      this.mShader = (Shader)new LinearGradient(this.mStartX, this.mStartY, this.mEndX, this.mEndY, arrayOfInt, arrayOfFloat, parseTileMode(this.mTileMode));
    } else if (i == 1) {
      this.mShader = (Shader)new RadialGradient(this.mCenterX, this.mCenterY, this.mGradientRadius, arrayOfInt, arrayOfFloat, parseTileMode(this.mTileMode));
    } else {
      this.mShader = (Shader)new SweepGradient(this.mCenterX, this.mCenterY, arrayOfInt, arrayOfFloat);
    } 
    this.mDefaultColor = arrayOfInt[0];
  }
  
  private static Shader.TileMode parseTileMode(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR) : Shader.TileMode.REPEAT) : Shader.TileMode.CLAMP;
  }
  
  private void updateRootElementState(TypedArray paramTypedArray) {
    this.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    this.mStartX = paramTypedArray.getFloat(8, this.mStartX);
    this.mStartY = paramTypedArray.getFloat(9, this.mStartY);
    this.mEndX = paramTypedArray.getFloat(10, this.mEndX);
    this.mEndY = paramTypedArray.getFloat(11, this.mEndY);
    this.mCenterX = paramTypedArray.getFloat(3, this.mCenterX);
    this.mCenterY = paramTypedArray.getFloat(4, this.mCenterY);
    this.mGradientType = paramTypedArray.getInt(2, this.mGradientType);
    this.mStartColor = paramTypedArray.getColor(0, this.mStartColor);
    this.mHasCenterColor |= paramTypedArray.hasValue(7);
    this.mCenterColor = paramTypedArray.getColor(7, this.mCenterColor);
    this.mEndColor = paramTypedArray.getColor(1, this.mEndColor);
    this.mTileMode = paramTypedArray.getInt(6, this.mTileMode);
    this.mGradientRadius = paramTypedArray.getFloat(5, this.mGradientRadius);
  }
  
  private void validateXmlContent() throws XmlPullParserException {
    if (this.mGradientRadius > 0.0F || this.mGradientType != 1)
      return; 
    throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
  }
  
  public boolean canApplyTheme() {
    return (this.mThemeAttrs != null || this.mItemsThemeAttrs != null);
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mChangingConfigurations;
  }
  
  public ConstantState<ComplexColor> getConstantState() {
    if (this.mFactory == null)
      this.mFactory = new GradientColorFactory(this); 
    return this.mFactory;
  }
  
  public int getDefaultColor() {
    return this.mDefaultColor;
  }
  
  public Shader getShader() {
    return this.mShader;
  }
  
  public GradientColor obtainForTheme(Resources.Theme paramTheme) {
    if (paramTheme == null || !canApplyTheme())
      return this; 
    GradientColor gradientColor = new GradientColor(this);
    gradientColor.applyTheme(paramTheme);
    return gradientColor;
  }
  
  private static class GradientColorFactory extends ConstantState<ComplexColor> {
    private final GradientColor mSrc;
    
    public GradientColorFactory(GradientColor param1GradientColor) {
      this.mSrc = param1GradientColor;
    }
    
    public int getChangingConfigurations() {
      return this.mSrc.mChangingConfigurations;
    }
    
    public GradientColor newInstance() {
      return this.mSrc;
    }
    
    public GradientColor newInstance(Resources param1Resources, Resources.Theme param1Theme) {
      return this.mSrc.obtainForTheme(param1Theme);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface GradientTileMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/GradientColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */