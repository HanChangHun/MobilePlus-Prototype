package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalHelpers;
import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class MarshalQueryableEnum<T extends Enum<T>> implements MarshalQueryable<T> {
  private static final boolean DEBUG = false;
  
  private static final String TAG = MarshalQueryableEnum.class.getSimpleName();
  
  private static final int UINT8_MASK = 255;
  
  private static final int UINT8_MAX = 255;
  
  private static final int UINT8_MIN = 0;
  
  private static final HashMap<Class<? extends Enum>, int[]> sEnumValues = (HashMap)new HashMap<>();
  
  private static <T extends Enum<T>> T getEnumFromValue(Class<T> paramClass, int paramInt) {
    int i;
    int[] arrayOfInt = sEnumValues.get(paramClass);
    if (arrayOfInt != null) {
      byte b = -1;
      byte b1 = 0;
      while (true) {
        i = b;
        if (b1 < arrayOfInt.length) {
          if (arrayOfInt[b1] == paramInt) {
            i = b1;
            break;
          } 
          b1++;
          continue;
        } 
        break;
      } 
    } else {
      i = paramInt;
    } 
    Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
    if (i < 0 || i >= arrayOfEnum.length) {
      boolean bool = false;
      if (arrayOfInt != null)
        bool = true; 
      throw new IllegalArgumentException(String.format("Argument 'value' (%d) was not a valid enum value for type %s (registered? %b)", new Object[] { Integer.valueOf(paramInt), paramClass, Boolean.valueOf(bool) }));
    } 
    return (T)arrayOfEnum[i];
  }
  
  private static <T extends Enum<T>> int getEnumValue(T paramT) {
    int[] arrayOfInt = sEnumValues.get(paramT.getClass());
    int i = paramT.ordinal();
    return (arrayOfInt != null) ? arrayOfInt[i] : i;
  }
  
  public static <T extends Enum<T>> void registerEnumValues(Class<T> paramClass, int[] paramArrayOfint) {
    if (((Enum[])paramClass.getEnumConstants()).length == paramArrayOfint.length) {
      sEnumValues.put(paramClass, paramArrayOfint);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Expected values array to be the same size as the enumTypes values ");
    stringBuilder.append(paramArrayOfint.length);
    stringBuilder.append(" for type ");
    stringBuilder.append(paramClass);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Marshaler<T> createMarshaler(TypeReference<T> paramTypeReference, int paramInt) {
    return new MarshalerEnum(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<T> paramTypeReference, int paramInt) {
    if ((paramInt == 1 || paramInt == 0) && paramTypeReference.getType() instanceof Class) {
      Class clazz = (Class)paramTypeReference.getType();
      if (clazz.isEnum())
        try {
          clazz.getDeclaredConstructor(new Class[] { String.class, int.class });
          return true;
        } catch (NoSuchMethodException noSuchMethodException) {
          String str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Can't marshal class ");
          stringBuilder.append(clazz);
          stringBuilder.append("; no default constructor");
          Log.e(str, stringBuilder.toString());
        } catch (SecurityException securityException) {
          String str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Can't marshal class ");
          stringBuilder.append(clazz);
          stringBuilder.append("; not accessible");
          Log.e(str, stringBuilder.toString());
        }  
    } 
    return false;
  }
  
  private class MarshalerEnum extends Marshaler<T> {
    private final Class<T> mClass;
    
    protected MarshalerEnum(TypeReference<T> param1TypeReference, int param1Int) {
      super(MarshalQueryableEnum.this, param1TypeReference, param1Int);
      this.mClass = param1TypeReference.getRawType();
    }
    
    public int getNativeSize() {
      return MarshalHelpers.getPrimitiveTypeSize(this.mNativeType);
    }
    
    public void marshal(T param1T, ByteBuffer param1ByteBuffer) {
      int i = MarshalQueryableEnum.getEnumValue(param1T);
      if (this.mNativeType == 1) {
        param1ByteBuffer.putInt(i);
      } else {
        if (this.mNativeType == 0) {
          if (i >= 0 && i <= 255) {
            param1ByteBuffer.put((byte)i);
            return;
          } 
          throw new UnsupportedOperationException(String.format("Enum value %x too large to fit into unsigned byte", new Object[] { Integer.valueOf(i) }));
        } 
        throw new AssertionError();
      } 
    }
    
    public T unmarshal(ByteBuffer param1ByteBuffer) {
      int i = this.mNativeType;
      if (i != 0) {
        if (i == 1) {
          i = param1ByteBuffer.getInt();
        } else {
          throw new AssertionError("Unexpected native type; impossible since its not supported");
        } 
      } else {
        i = param1ByteBuffer.get() & 0xFF;
      } 
      return MarshalQueryableEnum.getEnumFromValue(this.mClass, i);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */