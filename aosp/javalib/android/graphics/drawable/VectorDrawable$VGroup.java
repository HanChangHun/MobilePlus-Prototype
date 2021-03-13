package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.Property;
import com.android.internal.R;
import com.android.internal.util.VirtualRefBasePtr;
import java.util.ArrayList;
import java.util.HashMap;

class VGroup extends VectorDrawable.VObject {
  private static final int NATIVE_ALLOCATION_SIZE = 100;
  
  private static final Property<VGroup, Float> PIVOT_X;
  
  private static final int PIVOT_X_INDEX = 1;
  
  private static final Property<VGroup, Float> PIVOT_Y;
  
  private static final int PIVOT_Y_INDEX = 2;
  
  private static final Property<VGroup, Float> ROTATION;
  
  private static final int ROTATION_INDEX = 0;
  
  private static final Property<VGroup, Float> SCALE_X;
  
  private static final int SCALE_X_INDEX = 3;
  
  private static final Property<VGroup, Float> SCALE_Y;
  
  private static final int SCALE_Y_INDEX = 4;
  
  private static final int TRANSFORM_PROPERTY_COUNT = 7;
  
  private static final Property<VGroup, Float> TRANSLATE_X;
  
  private static final int TRANSLATE_X_INDEX = 5;
  
  private static final Property<VGroup, Float> TRANSLATE_Y;
  
  private static final int TRANSLATE_Y_INDEX = 6;
  
  private static final HashMap<String, Integer> sPropertyIndexMap = new HashMap<String, Integer>() {
    
    };
  
  private static final HashMap<String, Property> sPropertyMap;
  
  private int mChangingConfigurations;
  
  private final ArrayList<VectorDrawable.VObject> mChildren = new ArrayList<>();
  
  private String mGroupName = null;
  
  private boolean mIsStateful;
  
  private final long mNativePtr;
  
  private int[] mThemeAttrs;
  
  private float[] mTransform;
  
  static {
    TRANSLATE_X = (Property)new FloatProperty<VGroup>("translateX") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getTranslateX());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setTranslateX(param2Float);
        }
      };
    TRANSLATE_Y = (Property)new FloatProperty<VGroup>("translateY") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getTranslateY());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setTranslateY(param2Float);
        }
      };
    SCALE_X = (Property)new FloatProperty<VGroup>("scaleX") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getScaleX());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setScaleX(param2Float);
        }
      };
    SCALE_Y = (Property)new FloatProperty<VGroup>("scaleY") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getScaleY());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setScaleY(param2Float);
        }
      };
    PIVOT_X = (Property)new FloatProperty<VGroup>("pivotX") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getPivotX());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setPivotX(param2Float);
        }
      };
    PIVOT_Y = (Property)new FloatProperty<VGroup>("pivotY") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getPivotY());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setPivotY(param2Float);
        }
      };
    ROTATION = (Property)new FloatProperty<VGroup>("rotation") {
        public Float get(VectorDrawable.VGroup param2VGroup) {
          return Float.valueOf(param2VGroup.getRotation());
        }
        
        public void setValue(VectorDrawable.VGroup param2VGroup, float param2Float) {
          param2VGroup.setRotation(param2Float);
        }
      };
    sPropertyMap = new HashMap<String, Property>() {
      
      };
  }
  
  public VGroup() {
    this.mNativePtr = VectorDrawable.access$1700();
  }
  
  public VGroup(VGroup paramVGroup, ArrayMap<String, Object> paramArrayMap) {
    this.mIsStateful = paramVGroup.mIsStateful;
    this.mThemeAttrs = paramVGroup.mThemeAttrs;
    String str = paramVGroup.mGroupName;
    this.mGroupName = str;
    this.mChangingConfigurations = paramVGroup.mChangingConfigurations;
    if (str != null)
      paramArrayMap.put(str, this); 
    this.mNativePtr = VectorDrawable.access$1600(paramVGroup.mNativePtr);
    ArrayList<VectorDrawable.VObject> arrayList = paramVGroup.mChildren;
    for (byte b = 0; b < arrayList.size(); b++) {
      VectorDrawable.VObject vObject = arrayList.get(b);
      if (vObject instanceof VGroup) {
        addChild(new VGroup((VGroup)vObject, paramArrayMap));
      } else {
        if (vObject instanceof VectorDrawable.VFullPath) {
          vObject = new VectorDrawable.VFullPath((VectorDrawable.VFullPath)vObject);
        } else if (vObject instanceof VectorDrawable.VClipPath) {
          vObject = new VectorDrawable.VClipPath((VectorDrawable.VClipPath)vObject);
        } else {
          throw new IllegalStateException("Unknown object in the tree!");
        } 
        addChild(vObject);
        if (((VectorDrawable.VPath)vObject).mPathName != null)
          paramArrayMap.put(((VectorDrawable.VPath)vObject).mPathName, vObject); 
      } 
    } 
  }
  
  static int getPropertyIndex(String paramString) {
    return sPropertyIndexMap.containsKey(paramString) ? ((Integer)sPropertyIndexMap.get(paramString)).intValue() : -1;
  }
  
  public void addChild(VectorDrawable.VObject paramVObject) {
    VectorDrawable.access$1800(this.mNativePtr, paramVObject.getNativePtr());
    this.mChildren.add(paramVObject);
    this.mIsStateful |= paramVObject.isStateful();
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    int[] arrayOfInt = this.mThemeAttrs;
    if (arrayOfInt != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(arrayOfInt, R.styleable.VectorDrawableGroup);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
    ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
    byte b = 0;
    int i = arrayList.size();
    while (b < i) {
      VectorDrawable.VObject vObject = arrayList.get(b);
      if (vObject.canApplyTheme()) {
        vObject.applyTheme(paramTheme);
        this.mIsStateful |= vObject.isStateful();
      } 
      b++;
    } 
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs != null)
      return true; 
    ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
    byte b = 0;
    int i = arrayList.size();
    while (b < i) {
      if (((VectorDrawable.VObject)arrayList.get(b)).canApplyTheme())
        return true; 
      b++;
    } 
    return false;
  }
  
  public String getGroupName() {
    return this.mGroupName;
  }
  
  public long getNativePtr() {
    return this.mNativePtr;
  }
  
  int getNativeSize() {
    int i = 100;
    for (byte b = 0; b < this.mChildren.size(); b++)
      i += ((VectorDrawable.VObject)this.mChildren.get(b)).getNativeSize(); 
    return i;
  }
  
  public float getPivotX() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$2400(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getPivotY() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$2600(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  Property getProperty(String paramString) {
    return sPropertyMap.containsKey(paramString) ? sPropertyMap.get(paramString) : null;
  }
  
  public float getRotation() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$2200(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getScaleX() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$2800(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getScaleY() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$3000(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getTranslateX() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$3200(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public float getTranslateY() {
    float f;
    if (isTreeValid()) {
      f = VectorDrawable.access$3400(this.mNativePtr);
    } else {
      f = 0.0F;
    } 
    return f;
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool = false;
    ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
    byte b = 0;
    int i = arrayList.size();
    while (b < i) {
      VectorDrawable.VObject vObject = arrayList.get(b);
      boolean bool1 = bool;
      if (vObject.isStateful())
        bool1 = bool | vObject.hasFocusStateSpecified(); 
      b++;
      bool = bool1;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme) {
    TypedArray typedArray = Drawable.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.VectorDrawableGroup);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
  }
  
  public boolean isStateful() {
    return this.mIsStateful;
  }
  
  public boolean onStateChange(int[] paramArrayOfint) {
    boolean bool = false;
    ArrayList<VectorDrawable.VObject> arrayList = this.mChildren;
    byte b = 0;
    int i = arrayList.size();
    while (b < i) {
      VectorDrawable.VObject vObject = arrayList.get(b);
      boolean bool1 = bool;
      if (vObject.isStateful())
        bool1 = bool | vObject.onStateChange(paramArrayOfint); 
      b++;
      bool = bool1;
    } 
    return bool;
  }
  
  public void setPivotX(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$2500(this.mNativePtr, paramFloat); 
  }
  
  public void setPivotY(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$2700(this.mNativePtr, paramFloat); 
  }
  
  public void setRotation(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$2300(this.mNativePtr, paramFloat); 
  }
  
  public void setScaleX(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$2900(this.mNativePtr, paramFloat); 
  }
  
  public void setScaleY(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$3100(this.mNativePtr, paramFloat); 
  }
  
  public void setTranslateX(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$3300(this.mNativePtr, paramFloat); 
  }
  
  public void setTranslateY(float paramFloat) {
    if (isTreeValid())
      VectorDrawable.access$3500(this.mNativePtr, paramFloat); 
  }
  
  public void setTree(VirtualRefBasePtr paramVirtualRefBasePtr) {
    super.setTree(paramVirtualRefBasePtr);
    for (byte b = 0; b < this.mChildren.size(); b++)
      ((VectorDrawable.VObject)this.mChildren.get(b)).setTree(paramVirtualRefBasePtr); 
  }
  
  void updateStateFromTypedArray(TypedArray paramTypedArray) {
    this.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    this.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    if (this.mTransform == null)
      this.mTransform = new float[7]; 
    if (VectorDrawable.access$1900(this.mNativePtr, this.mTransform, 7)) {
      float f1 = paramTypedArray.getFloat(5, this.mTransform[0]);
      float f2 = paramTypedArray.getFloat(1, this.mTransform[1]);
      float f3 = paramTypedArray.getFloat(2, this.mTransform[2]);
      float f4 = paramTypedArray.getFloat(3, this.mTransform[3]);
      float f5 = paramTypedArray.getFloat(4, this.mTransform[4]);
      float f6 = paramTypedArray.getFloat(6, this.mTransform[5]);
      float f7 = paramTypedArray.getFloat(7, this.mTransform[6]);
      String str = paramTypedArray.getString(0);
      if (str != null) {
        this.mGroupName = str;
        VectorDrawable.access$2000(this.mNativePtr, str);
      } 
      VectorDrawable.access$2100(this.mNativePtr, f1, f2, f3, f4, f5, f6, f7);
      return;
    } 
    throw new RuntimeException("Error: inconsistent property count");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */