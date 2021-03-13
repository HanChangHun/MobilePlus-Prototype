package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.ComplexColor;
import android.content.res.GradientColor;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.IntProperty;
import android.util.PathParser;
import android.util.Property;
import com.android.internal.R;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

class VFullPath extends VectorDrawable.VPath {
  private static final Property<VFullPath, Float> FILL_ALPHA;
  
  private static final int FILL_ALPHA_INDEX = 4;
  
  private static final Property<VFullPath, Integer> FILL_COLOR;
  
  private static final int FILL_COLOR_INDEX = 3;
  
  private static final int FILL_TYPE_INDEX = 11;
  
  private static final int NATIVE_ALLOCATION_SIZE = 264;
  
  private static final Property<VFullPath, Float> STROKE_ALPHA;
  
  private static final int STROKE_ALPHA_INDEX = 2;
  
  private static final Property<VFullPath, Integer> STROKE_COLOR;
  
  private static final int STROKE_COLOR_INDEX = 1;
  
  private static final int STROKE_LINE_CAP_INDEX = 8;
  
  private static final int STROKE_LINE_JOIN_INDEX = 9;
  
  private static final int STROKE_MITER_LIMIT_INDEX = 10;
  
  private static final Property<VFullPath, Float> STROKE_WIDTH;
  
  private static final int STROKE_WIDTH_INDEX = 0;
  
  private static final int TOTAL_PROPERTY_COUNT = 12;
  
  private static final Property<VFullPath, Float> TRIM_PATH_END;
  
  private static final int TRIM_PATH_END_INDEX = 6;
  
  private static final Property<VFullPath, Float> TRIM_PATH_OFFSET;
  
  private static final int TRIM_PATH_OFFSET_INDEX = 7;
  
  private static final Property<VFullPath, Float> TRIM_PATH_START;
  
  private static final int TRIM_PATH_START_INDEX = 5;
  
  private static final HashMap<String, Integer> sPropertyIndexMap = new HashMap<String, Integer>() {
    
    };
  
  private static final HashMap<String, Property> sPropertyMap;
  
  ComplexColor mFillColors = null;
  
  private final long mNativePtr = VectorDrawable.access$4800();
  
  private byte[] mPropertyData;
  
  ComplexColor mStrokeColors = null;
  
  private int[] mThemeAttrs;
  
  static {
    STROKE_WIDTH = (Property)new FloatProperty<VFullPath>("strokeWidth") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getStrokeWidth());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setStrokeWidth(param2Float);
        }
      };
    STROKE_COLOR = (Property)new IntProperty<VFullPath>("strokeColor") {
        public Integer get(VectorDrawable.VFullPath param2VFullPath) {
          return Integer.valueOf(param2VFullPath.getStrokeColor());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, int param2Int) {
          param2VFullPath.setStrokeColor(param2Int);
        }
      };
    STROKE_ALPHA = (Property)new FloatProperty<VFullPath>("strokeAlpha") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getStrokeAlpha());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setStrokeAlpha(param2Float);
        }
      };
    FILL_COLOR = (Property)new IntProperty<VFullPath>("fillColor") {
        public Integer get(VectorDrawable.VFullPath param2VFullPath) {
          return Integer.valueOf(param2VFullPath.getFillColor());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, int param2Int) {
          param2VFullPath.setFillColor(param2Int);
        }
      };
    FILL_ALPHA = (Property)new FloatProperty<VFullPath>("fillAlpha") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getFillAlpha());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setFillAlpha(param2Float);
        }
      };
    TRIM_PATH_START = (Property)new FloatProperty<VFullPath>("trimPathStart") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getTrimPathStart());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setTrimPathStart(param2Float);
        }
      };
    TRIM_PATH_END = (Property)new FloatProperty<VFullPath>("trimPathEnd") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getTrimPathEnd());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setTrimPathEnd(param2Float);
        }
      };
    TRIM_PATH_OFFSET = (Property)new FloatProperty<VFullPath>("trimPathOffset") {
        public Float get(VectorDrawable.VFullPath param2VFullPath) {
          return Float.valueOf(param2VFullPath.getTrimPathOffset());
        }
        
        public void setValue(VectorDrawable.VFullPath param2VFullPath, float param2Float) {
          param2VFullPath.setTrimPathOffset(param2Float);
        }
      };
    sPropertyMap = new HashMap<String, Property>() {
      
      };
  }
  
  public VFullPath() {}
  
  public VFullPath(VFullPath paramVFullPath) {
    super(paramVFullPath);
    this.mThemeAttrs = paramVFullPath.mThemeAttrs;
    this.mStrokeColors = paramVFullPath.mStrokeColors;
    this.mFillColors = paramVFullPath.mFillColors;
  }
  
  private boolean canComplexColorApplyTheme(ComplexColor paramComplexColor) {
    boolean bool;
    if (paramComplexColor != null && paramComplexColor.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    if (this.mPropertyData == null)
      this.mPropertyData = new byte[48]; 
    if (VectorDrawable.access$5200(this.mNativePtr, this.mPropertyData, 48)) {
      ByteBuffer byteBuffer1 = ByteBuffer.wrap(this.mPropertyData);
      byteBuffer1.order(ByteOrder.nativeOrder());
      float f1 = byteBuffer1.getFloat(0);
      int i = byteBuffer1.getInt(4);
      float f2 = byteBuffer1.getFloat(8);
      int j = byteBuffer1.getInt(12);
      float f3 = byteBuffer1.getFloat(16);
      float f4 = byteBuffer1.getFloat(20);
      float f5 = byteBuffer1.getFloat(24);
      float f6 = byteBuffer1.getFloat(28);
      int k = byteBuffer1.getInt(32);
      int m = byteBuffer1.getInt(36);
      float f7 = byteBuffer1.getFloat(40);
      int n = byteBuffer1.getInt(44);
      Shader shader = null;
      byteBuffer1 = null;
      ByteBuffer byteBuffer2 = null;
      ByteBuffer byteBuffer3 = null;
      this.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
      this.mThemeAttrs = paramTypedArray.extractThemeAttrs();
      String str = paramTypedArray.getString(0);
      if (str != null) {
        this.mPathName = str;
        VectorDrawable.access$2000(this.mNativePtr, this.mPathName);
      } 
      str = paramTypedArray.getString(2);
      if (str != null) {
        this.mPathData = new PathParser.PathData(str);
        VectorDrawable.access$3900(this.mNativePtr, str, str.length());
      } 
      ComplexColor complexColor = paramTypedArray.getComplexColor(1);
      if (complexColor != null) {
        Shader shader1;
        if (complexColor instanceof GradientColor) {
          this.mFillColors = complexColor;
          shader1 = ((GradientColor)complexColor).getShader();
        } else if (complexColor.isStateful() || complexColor.canApplyTheme()) {
          this.mFillColors = complexColor;
        } else {
          this.mFillColors = null;
        } 
        j = complexColor.getDefaultColor();
        shader = shader1;
      } 
      complexColor = paramTypedArray.getComplexColor(3);
      if (complexColor != null) {
        if (complexColor instanceof GradientColor) {
          this.mStrokeColors = complexColor;
          Shader shader1 = ((GradientColor)complexColor).getShader();
        } else if (complexColor.isStateful() || complexColor.canApplyTheme()) {
          this.mStrokeColors = complexColor;
          byteBuffer1 = byteBuffer3;
        } else {
          this.mStrokeColors = null;
          byteBuffer1 = byteBuffer3;
        } 
        i = complexColor.getDefaultColor();
      } else {
        byteBuffer1 = byteBuffer2;
      } 
      long l1 = this.mNativePtr;
      long l2 = 0L;
      if (shader != null) {
        l3 = shader.getNativeInstance();
      } else {
        l3 = 0L;
      } 
      VectorDrawable.access$5300(l1, l3);
      l1 = this.mNativePtr;
      long l3 = l2;
      if (byteBuffer1 != null)
        l3 = byteBuffer1.getNativeInstance(); 
      VectorDrawable.access$5400(l1, l3);
      f3 = paramTypedArray.getFloat(12, f3);
      k = paramTypedArray.getInt(8, k);
      m = paramTypedArray.getInt(9, m);
      f7 = paramTypedArray.getFloat(10, f7);
      f2 = paramTypedArray.getFloat(11, f2);
      f1 = paramTypedArray.getFloat(4, f1);
      f5 = paramTypedArray.getFloat(6, f5);
      f6 = paramTypedArray.getFloat(7, f6);
      f4 = paramTypedArray.getFloat(5, f4);
      n = paramTypedArray.getInt(13, n);
      VectorDrawable.access$5500(this.mNativePtr, f1, i, f2, j, f3, f4, f5, f6, f7, k, m, n);
      return;
    } 
    throw new RuntimeException("Error: inconsistent property count");
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    int[] arrayOfInt = this.mThemeAttrs;
    if (arrayOfInt != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(arrayOfInt, R.styleable.VectorDrawablePath);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
    boolean bool1 = canComplexColorApplyTheme(this.mFillColors);
    boolean bool2 = canComplexColorApplyTheme(this.mStrokeColors);
    if (bool1) {
      ComplexColor complexColor = this.mFillColors.obtainForTheme(paramTheme);
      this.mFillColors = complexColor;
      if (complexColor instanceof GradientColor) {
        VectorDrawable.access$5300(this.mNativePtr, ((GradientColor)complexColor).getShader().getNativeInstance());
      } else if (complexColor instanceof ColorStateList) {
        VectorDrawable.access$5100(this.mNativePtr, complexColor.getDefaultColor());
      } 
    } 
    if (bool2) {
      ComplexColor complexColor = this.mStrokeColors.obtainForTheme(paramTheme);
      this.mStrokeColors = complexColor;
      if (complexColor instanceof GradientColor) {
        VectorDrawable.access$5400(this.mNativePtr, ((GradientColor)complexColor).getShader().getNativeInstance());
      } else if (complexColor instanceof ColorStateList) {
        VectorDrawable.access$5000(this.mNativePtr, complexColor.getDefaultColor());
      } 
    } 
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs != null)
      return true; 
    boolean bool1 = canComplexColorApplyTheme(this.mFillColors);
    boolean bool2 = canComplexColorApplyTheme(this.mStrokeColors);
    return (bool1 || bool2);
  }
  
  float getFillAlpha() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$6200(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  int getFillColor() {
    boolean bool;
    if (isTreeValid()) {
      bool = VectorDrawable.access$6100(this.mNativePtr);
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public long getNativePtr() {
    return this.mNativePtr;
  }
  
  int getNativeSize() {
    return 264;
  }
  
  Property getProperty(String paramString) {
    Property property = super.getProperty(paramString);
    return (property != null) ? property : (sPropertyMap.containsKey(paramString) ? sPropertyMap.get(paramString) : null);
  }
  
  int getPropertyIndex(String paramString) {
    return !sPropertyIndexMap.containsKey(paramString) ? -1 : ((Integer)sPropertyIndexMap.get(paramString)).intValue();
  }
  
  float getStrokeAlpha() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$5900(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  int getStrokeColor() {
    boolean bool;
    if (isTreeValid()) {
      bool = VectorDrawable.access$5600(this.mNativePtr);
    } else {
      bool = false;
    } 
    return bool;
  }
  
  float getStrokeWidth() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$5700(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  float getTrimPathEnd() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$6600(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  float getTrimPathOffset() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$6800(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  float getTrimPathStart() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$6400(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public boolean hasFocusStateSpecified() {
    ComplexColor complexColor = this.mStrokeColors;
    if (complexColor != null && complexColor instanceof ColorStateList && ((ColorStateList)complexColor).hasFocusStateSpecified()) {
      complexColor = this.mFillColors;
      if (complexColor != null && complexColor instanceof ColorStateList && ((ColorStateList)complexColor).hasFocusStateSpecified())
        return true; 
    } 
    return false;
  }
  
  public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme) {
    TypedArray typedArray = Drawable.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.VectorDrawablePath);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
  }
  
  public boolean isStateful() {
    return (this.mStrokeColors != null || this.mFillColors != null);
  }
  
  public boolean onStateChange(int[] paramArrayOfint) {
    int i = 0;
    ComplexColor complexColor = this.mStrokeColors;
    boolean bool = true;
    int j = i;
    if (complexColor != null) {
      j = i;
      if (complexColor instanceof ColorStateList) {
        boolean bool1;
        int k = getStrokeColor();
        int m = ((ColorStateList)this.mStrokeColors).getColorForState(paramArrayOfint, k);
        if (k != m) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        i = false | bool1;
        j = i;
        if (k != m) {
          VectorDrawable.access$5000(this.mNativePtr, m);
          j = i;
        } 
      } 
    } 
    complexColor = this.mFillColors;
    i = j;
    if (complexColor != null) {
      i = j;
      if (complexColor instanceof ColorStateList) {
        byte b;
        int k = getFillColor();
        int m = ((ColorStateList)this.mFillColors).getColorForState(paramArrayOfint, k);
        if (k != m) {
          b = bool;
        } else {
          b = 0;
        } 
        j |= b;
        i = j;
        if (k != m) {
          VectorDrawable.access$5100(this.mNativePtr, m);
          i = j;
        } 
      } 
    } 
    return i;
  }
  
  void setFillAlpha(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$6300(this.mNativePtr, paramFloat); 
  }
  
  void setFillColor(int paramInt) {
    this.mFillColors = null;
    if (isTreeValid())
      VectorDrawable.access$5100(this.mNativePtr, paramInt); 
  }
  
  void setStrokeAlpha(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$6000(this.mNativePtr, paramFloat); 
  }
  
  void setStrokeColor(int paramInt) {
    this.mStrokeColors = null;
    if (isTreeValid())
      VectorDrawable.access$5000(this.mNativePtr, paramInt); 
  }
  
  void setStrokeWidth(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$5800(this.mNativePtr, paramFloat); 
  }
  
  void setTrimPathEnd(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$6700(this.mNativePtr, paramFloat); 
  }
  
  void setTrimPathOffset(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$6900(this.mNativePtr, paramFloat); 
  }
  
  void setTrimPathStart(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$6500(this.mNativePtr, paramFloat); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VFullPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */