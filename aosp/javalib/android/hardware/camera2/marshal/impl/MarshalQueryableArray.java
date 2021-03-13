package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Log;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class MarshalQueryableArray<T> implements MarshalQueryable<T> {
  private static final boolean DEBUG = false;
  
  private static final String TAG = MarshalQueryableArray.class.getSimpleName();
  
  public Marshaler<T> createMarshaler(TypeReference<T> paramTypeReference, int paramInt) {
    return new MarshalerArray(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<T> paramTypeReference, int paramInt) {
    return paramTypeReference.getRawType().isArray();
  }
  
  private class MarshalerArray extends Marshaler<T> {
    private final Class<T> mClass;
    
    private final Class<?> mComponentClass;
    
    private final Marshaler<?> mComponentMarshaler;
    
    protected MarshalerArray(TypeReference<T> param1TypeReference, int param1Int) {
      super(MarshalQueryableArray.this, param1TypeReference, param1Int);
      this.mClass = param1TypeReference.getRawType();
      TypeReference typeReference = param1TypeReference.getComponentType();
      this.mComponentMarshaler = MarshalRegistry.getMarshaler(typeReference, this.mNativeType);
      this.mComponentClass = typeReference.getRawType();
    }
    
    private <TElem> int calculateElementMarshalSize(Marshaler<TElem> param1Marshaler, Object param1Object, int param1Int) {
      return param1Marshaler.calculateMarshalSize(Array.get(param1Object, param1Int));
    }
    
    private Object copyListToArray(ArrayList<?> param1ArrayList, Object param1Object) {
      return param1ArrayList.toArray((Object[])param1Object);
    }
    
    private <TElem> void marshalArrayElement(Marshaler<TElem> param1Marshaler, ByteBuffer param1ByteBuffer, Object param1Object, int param1Int) {
      param1Marshaler.marshal(Array.get(param1Object, param1Int), param1ByteBuffer);
    }
    
    public int calculateMarshalSize(T param1T) {
      int i = this.mComponentMarshaler.getNativeSize();
      int j = Array.getLength(param1T);
      if (i != Marshaler.NATIVE_SIZE_DYNAMIC)
        return i * j; 
      int k = 0;
      for (i = 0; i < j; i++)
        k += calculateElementMarshalSize(this.mComponentMarshaler, param1T, i); 
      return k;
    }
    
    public int getNativeSize() {
      // Byte code:
      //   0: getstatic android/hardware/camera2/marshal/impl/MarshalQueryableArray$MarshalerArray.NATIVE_SIZE_DYNAMIC : I
      //   3: ireturn
    }
    
    public void marshal(T param1T, ByteBuffer param1ByteBuffer) {
      int i = Array.getLength(param1T);
      for (byte b = 0; b < i; b++)
        marshalArrayElement(this.mComponentMarshaler, param1ByteBuffer, param1T, b); 
    }
    
    public T unmarshal(ByteBuffer param1ByteBuffer) {
      StringBuilder stringBuilder;
      Object object;
      int i = this.mComponentMarshaler.getNativeSize();
      if (i != Marshaler.NATIVE_SIZE_DYNAMIC) {
        int j = param1ByteBuffer.remaining();
        int k = j / i;
        if (j % i == 0) {
          object = Array.newInstance(this.mComponentClass, k);
          for (i = 0; i < k; i++)
            Array.set(object, i, this.mComponentMarshaler.unmarshal(param1ByteBuffer)); 
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Arrays for ");
          stringBuilder.append(this.mTypeReference);
          stringBuilder.append(" must be packed tighly into a multiple of ");
          stringBuilder.append(i);
          stringBuilder.append("; but there are ");
          stringBuilder.append(j % i);
          stringBuilder.append(" left over bytes");
          throw new UnsupportedOperationException(stringBuilder.toString());
        } 
      } else {
        ArrayList<Object> arrayList = new ArrayList();
        while (stringBuilder.hasRemaining())
          arrayList.add(this.mComponentMarshaler.unmarshal((ByteBuffer)stringBuilder)); 
        i = arrayList.size();
        object = copyListToArray(arrayList, Array.newInstance(this.mComponentClass, i));
      } 
      if (stringBuilder.remaining() != 0) {
        String str = MarshalQueryableArray.TAG;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Trailing bytes (");
        stringBuilder1.append(stringBuilder.remaining());
        stringBuilder1.append(") left over after unpacking ");
        stringBuilder1.append(this.mClass);
        Log.e(str, stringBuilder1.toString());
      } 
      return this.mClass.cast(object);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */