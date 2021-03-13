package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Log;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;

class MarshalerArray extends Marshaler<T> {
  private final Class<T> mClass;
  
  private final Class<?> mComponentClass;
  
  private final Marshaler<?> mComponentMarshaler;
  
  protected MarshalerArray(TypeReference<T> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableArray, paramTypeReference, paramInt);
    this.mClass = paramTypeReference.getRawType();
    TypeReference typeReference = paramTypeReference.getComponentType();
    this.mComponentMarshaler = MarshalRegistry.getMarshaler(typeReference, this.mNativeType);
    this.mComponentClass = typeReference.getRawType();
  }
  
  private <TElem> int calculateElementMarshalSize(Marshaler<TElem> paramMarshaler, Object paramObject, int paramInt) {
    return paramMarshaler.calculateMarshalSize(Array.get(paramObject, paramInt));
  }
  
  private Object copyListToArray(ArrayList<?> paramArrayList, Object paramObject) {
    return paramArrayList.toArray((Object[])paramObject);
  }
  
  private <TElem> void marshalArrayElement(Marshaler<TElem> paramMarshaler, ByteBuffer paramByteBuffer, Object paramObject, int paramInt) {
    paramMarshaler.marshal(Array.get(paramObject, paramInt), paramByteBuffer);
  }
  
  public int calculateMarshalSize(T paramT) {
    int i = this.mComponentMarshaler.getNativeSize();
    int j = Array.getLength(paramT);
    if (i != Marshaler.NATIVE_SIZE_DYNAMIC)
      return i * j; 
    int k = 0;
    for (i = 0; i < j; i++)
      k += calculateElementMarshalSize(this.mComponentMarshaler, paramT, i); 
    return k;
  }
  
  public int getNativeSize() {
    // Byte code:
    //   0: getstatic android/hardware/camera2/marshal/impl/MarshalQueryableArray$MarshalerArray.NATIVE_SIZE_DYNAMIC : I
    //   3: ireturn
  }
  
  public void marshal(T paramT, ByteBuffer paramByteBuffer) {
    int i = Array.getLength(paramT);
    for (byte b = 0; b < i; b++)
      marshalArrayElement(this.mComponentMarshaler, paramByteBuffer, paramT, b); 
  }
  
  public T unmarshal(ByteBuffer paramByteBuffer) {
    StringBuilder stringBuilder;
    Object object;
    int i = this.mComponentMarshaler.getNativeSize();
    if (i != Marshaler.NATIVE_SIZE_DYNAMIC) {
      int j = paramByteBuffer.remaining();
      int k = j / i;
      if (j % i == 0) {
        object = Array.newInstance(this.mComponentClass, k);
        for (i = 0; i < k; i++)
          Array.set(object, i, this.mComponentMarshaler.unmarshal(paramByteBuffer)); 
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
      String str = MarshalQueryableArray.access$000();
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableArray$MarshalerArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */