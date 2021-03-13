package android.filterfw.core;

import java.util.Arrays;
import java.util.Map;

public class FrameFormat {
  public static final int BYTES_PER_SAMPLE_UNSPECIFIED = 1;
  
  protected static final int SIZE_UNKNOWN = -1;
  
  public static final int SIZE_UNSPECIFIED = 0;
  
  public static final int TARGET_GPU = 3;
  
  public static final int TARGET_NATIVE = 2;
  
  public static final int TARGET_RS = 5;
  
  public static final int TARGET_SIMPLE = 1;
  
  public static final int TARGET_UNSPECIFIED = 0;
  
  public static final int TARGET_VERTEXBUFFER = 4;
  
  public static final int TYPE_BIT = 1;
  
  public static final int TYPE_BYTE = 2;
  
  public static final int TYPE_DOUBLE = 6;
  
  public static final int TYPE_FLOAT = 5;
  
  public static final int TYPE_INT16 = 3;
  
  public static final int TYPE_INT32 = 4;
  
  public static final int TYPE_OBJECT = 8;
  
  public static final int TYPE_POINTER = 7;
  
  public static final int TYPE_UNSPECIFIED = 0;
  
  protected int mBaseType = 0;
  
  protected int mBytesPerSample = 1;
  
  protected int[] mDimensions;
  
  protected KeyValueMap mMetaData;
  
  protected Class mObjectClass;
  
  protected int mSize = -1;
  
  protected int mTarget = 0;
  
  protected FrameFormat() {}
  
  public FrameFormat(int paramInt1, int paramInt2) {
    this.mBaseType = paramInt1;
    this.mTarget = paramInt2;
    initDefaults();
  }
  
  public static String baseTypeToString(int paramInt) {
    switch (paramInt) {
      default:
        return "unknown";
      case 8:
        return "object";
      case 7:
        return "pointer";
      case 6:
        return "double";
      case 5:
        return "float";
      case 4:
        return "int";
      case 3:
        return "int";
      case 2:
        return "byte";
      case 1:
        return "bit";
      case 0:
        break;
    } 
    return "unspecified";
  }
  
  public static int bytesPerSampleOf(int paramInt) {
    if (paramInt != 3) {
      if (paramInt != 4 && paramInt != 5)
        if (paramInt != 6) {
          if (paramInt != 7)
            return 1; 
        } else {
          return 8;
        }  
      return 4;
    } 
    return 2;
  }
  
  public static String dimensionsToString(int[] paramArrayOfint) {
    StringBuffer stringBuffer = new StringBuffer();
    if (paramArrayOfint != null) {
      int i = paramArrayOfint.length;
      for (byte b = 0; b < i; b++) {
        if (paramArrayOfint[b] == 0) {
          stringBuffer.append("[]");
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("[");
          stringBuilder.append(String.valueOf(paramArrayOfint[b]));
          stringBuilder.append("]");
          stringBuffer.append(stringBuilder.toString());
        } 
      } 
    } 
    return stringBuffer.toString();
  }
  
  private void initDefaults() {
    this.mBytesPerSample = bytesPerSampleOf(this.mBaseType);
  }
  
  public static String metaDataToString(KeyValueMap paramKeyValueMap) {
    if (paramKeyValueMap == null)
      return ""; 
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("{ ");
    for (Map.Entry<String, Object> entry : paramKeyValueMap.entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append((String)entry.getKey());
      stringBuilder.append(": ");
      stringBuilder.append(entry.getValue());
      stringBuilder.append(" ");
      stringBuffer.append(stringBuilder.toString());
    } 
    stringBuffer.append("}");
    return stringBuffer.toString();
  }
  
  public static int readTargetString(String paramString) {
    if (paramString.equalsIgnoreCase("CPU") || paramString.equalsIgnoreCase("NATIVE"))
      return 2; 
    if (paramString.equalsIgnoreCase("GPU"))
      return 3; 
    if (paramString.equalsIgnoreCase("SIMPLE"))
      return 1; 
    if (paramString.equalsIgnoreCase("VERTEXBUFFER"))
      return 4; 
    if (paramString.equalsIgnoreCase("UNSPECIFIED"))
      return 0; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown target type '");
    stringBuilder.append(paramString);
    stringBuilder.append("'!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public static String targetToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? "unknown" : "renderscript") : "vbo") : "gpu") : "native") : "simple") : "unspecified";
  }
  
  public static FrameFormat unspecified() {
    return new FrameFormat(0, 0);
  }
  
  int calcSize(int[] paramArrayOfint) {
    byte b = 0;
    if (paramArrayOfint != null && paramArrayOfint.length > 0) {
      int i = getBytesPerSample();
      int j = paramArrayOfint.length;
      while (b < j) {
        i *= paramArrayOfint[b];
        b++;
      } 
      return i;
    } 
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof FrameFormat))
      return false; 
    paramObject = paramObject;
    if (((FrameFormat)paramObject).mBaseType != this.mBaseType || ((FrameFormat)paramObject).mTarget != this.mTarget || ((FrameFormat)paramObject).mBytesPerSample != this.mBytesPerSample || !Arrays.equals(((FrameFormat)paramObject).mDimensions, this.mDimensions) || !((FrameFormat)paramObject).mMetaData.equals(this.mMetaData))
      bool = false; 
    return bool;
  }
  
  public int getBaseType() {
    return this.mBaseType;
  }
  
  public int getBytesPerSample() {
    return this.mBytesPerSample;
  }
  
  public int getDepth() {
    byte b;
    int[] arrayOfInt = this.mDimensions;
    if (arrayOfInt != null && arrayOfInt.length >= 3) {
      b = arrayOfInt[2];
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getDimension(int paramInt) {
    return this.mDimensions[paramInt];
  }
  
  public int getDimensionCount() {
    int i;
    int[] arrayOfInt = this.mDimensions;
    if (arrayOfInt == null) {
      i = 0;
    } else {
      i = arrayOfInt.length;
    } 
    return i;
  }
  
  public int[] getDimensions() {
    return this.mDimensions;
  }
  
  public int getHeight() {
    byte b;
    int[] arrayOfInt = this.mDimensions;
    if (arrayOfInt != null && arrayOfInt.length >= 2) {
      b = arrayOfInt[1];
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getLength() {
    byte b;
    int[] arrayOfInt = this.mDimensions;
    if (arrayOfInt != null && arrayOfInt.length >= 1) {
      b = arrayOfInt[0];
    } else {
      b = -1;
    } 
    return b;
  }
  
  public Object getMetaValue(String paramString) {
    KeyValueMap keyValueMap = this.mMetaData;
    if (keyValueMap != null) {
      Object object = keyValueMap.get(paramString);
    } else {
      paramString = null;
    } 
    return paramString;
  }
  
  public int getNumberOfDimensions() {
    boolean bool;
    int[] arrayOfInt = this.mDimensions;
    if (arrayOfInt != null) {
      bool = arrayOfInt.length;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Class getObjectClass() {
    return this.mObjectClass;
  }
  
  public int getSize() {
    if (this.mSize == -1)
      this.mSize = calcSize(this.mDimensions); 
    return this.mSize;
  }
  
  public int getTarget() {
    return this.mTarget;
  }
  
  public int getValuesPerSample() {
    return this.mBytesPerSample / bytesPerSampleOf(this.mBaseType);
  }
  
  public int getWidth() {
    return getLength();
  }
  
  public boolean hasMetaKey(String paramString) {
    boolean bool;
    KeyValueMap keyValueMap = this.mMetaData;
    if (keyValueMap != null) {
      bool = keyValueMap.containsKey(paramString);
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasMetaKey(String paramString, Class paramClass) {
    KeyValueMap keyValueMap = this.mMetaData;
    if (keyValueMap != null && keyValueMap.containsKey(paramString)) {
      if (paramClass.isAssignableFrom(this.mMetaData.get(paramString).getClass()))
        return true; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FrameFormat meta-key '");
      stringBuilder.append(paramString);
      stringBuilder.append("' is of type ");
      stringBuilder.append(this.mMetaData.get(paramString).getClass());
      stringBuilder.append(" but expected to be of type ");
      stringBuilder.append(paramClass);
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString());
    } 
    return false;
  }
  
  public int hashCode() {
    return this.mBaseType ^ 0x1073 ^ this.mBytesPerSample ^ getSize();
  }
  
  public boolean isBinaryDataType() {
    int i = this.mBaseType;
    boolean bool = true;
    if (i < 1 || i > 6)
      bool = false; 
    return bool;
  }
  
  public boolean isCompatibleWith(FrameFormat paramFrameFormat) {
    if (paramFrameFormat.getBaseType() != 0 && getBaseType() != paramFrameFormat.getBaseType())
      return false; 
    if (paramFrameFormat.getTarget() != 0 && getTarget() != paramFrameFormat.getTarget())
      return false; 
    if (paramFrameFormat.getBytesPerSample() != 1 && getBytesPerSample() != paramFrameFormat.getBytesPerSample())
      return false; 
    if (paramFrameFormat.getDimensionCount() > 0 && getDimensionCount() != paramFrameFormat.getDimensionCount())
      return false; 
    for (byte b = 0; b < paramFrameFormat.getDimensionCount(); b++) {
      int i = paramFrameFormat.getDimension(b);
      if (i != 0 && getDimension(b) != i)
        return false; 
    } 
    if (paramFrameFormat.getObjectClass() != null && (getObjectClass() == null || !paramFrameFormat.getObjectClass().isAssignableFrom(getObjectClass())))
      return false; 
    KeyValueMap keyValueMap = paramFrameFormat.mMetaData;
    if (keyValueMap != null)
      for (String str : keyValueMap.keySet()) {
        KeyValueMap keyValueMap1 = this.mMetaData;
        if (keyValueMap1 == null || !keyValueMap1.containsKey(str) || !this.mMetaData.get(str).equals(paramFrameFormat.mMetaData.get(str)))
          return false; 
      }  
    return true;
  }
  
  boolean isReplaceableBy(FrameFormat paramFrameFormat) {
    boolean bool;
    if (this.mTarget == paramFrameFormat.mTarget && getSize() == paramFrameFormat.getSize() && Arrays.equals(paramFrameFormat.mDimensions, this.mDimensions)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean mayBeCompatibleWith(FrameFormat paramFrameFormat) {
    if (paramFrameFormat.getBaseType() != 0 && getBaseType() != 0 && getBaseType() != paramFrameFormat.getBaseType())
      return false; 
    if (paramFrameFormat.getTarget() != 0 && getTarget() != 0 && getTarget() != paramFrameFormat.getTarget())
      return false; 
    if (paramFrameFormat.getBytesPerSample() != 1 && getBytesPerSample() != 1 && getBytesPerSample() != paramFrameFormat.getBytesPerSample())
      return false; 
    if (paramFrameFormat.getDimensionCount() > 0 && getDimensionCount() > 0 && getDimensionCount() != paramFrameFormat.getDimensionCount())
      return false; 
    for (byte b = 0; b < paramFrameFormat.getDimensionCount(); b++) {
      int i = paramFrameFormat.getDimension(b);
      if (i != 0 && getDimension(b) != 0 && getDimension(b) != i)
        return false; 
    } 
    if (paramFrameFormat.getObjectClass() != null && getObjectClass() != null && !paramFrameFormat.getObjectClass().isAssignableFrom(getObjectClass()))
      return false; 
    KeyValueMap keyValueMap = paramFrameFormat.mMetaData;
    if (keyValueMap != null && this.mMetaData != null)
      for (String str : keyValueMap.keySet()) {
        if (this.mMetaData.containsKey(str) && !this.mMetaData.get(str).equals(paramFrameFormat.mMetaData.get(str)))
          return false; 
      }  
    return true;
  }
  
  public MutableFrameFormat mutableCopy() {
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat();
    mutableFrameFormat.setBaseType(getBaseType());
    mutableFrameFormat.setTarget(getTarget());
    mutableFrameFormat.setBytesPerSample(getBytesPerSample());
    mutableFrameFormat.setDimensions(getDimensions());
    mutableFrameFormat.setObjectClass(getObjectClass());
    KeyValueMap keyValueMap = this.mMetaData;
    if (keyValueMap == null) {
      keyValueMap = null;
    } else {
      keyValueMap = (KeyValueMap)keyValueMap.clone();
    } 
    mutableFrameFormat.mMetaData = keyValueMap;
    return mutableFrameFormat;
  }
  
  public String toString() {
    String str2;
    String str3;
    int i = getValuesPerSample();
    String str1 = "";
    if (i == 1) {
      str2 = "";
    } else {
      str2 = String.valueOf(i);
    } 
    if (this.mTarget == 0) {
      str3 = "";
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(targetToString(this.mTarget));
      stringBuilder1.append(" ");
      str3 = stringBuilder1.toString();
    } 
    if (this.mObjectClass != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(" class(");
      stringBuilder1.append(this.mObjectClass.getSimpleName());
      stringBuilder1.append(") ");
      str1 = stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str3);
    stringBuilder.append(baseTypeToString(this.mBaseType));
    stringBuilder.append(str2);
    stringBuilder.append(dimensionsToString(this.mDimensions));
    stringBuilder.append(str1);
    stringBuilder.append(metaDataToString(this.mMetaData));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FrameFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */