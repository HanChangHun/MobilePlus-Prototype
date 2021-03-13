package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalHelpers;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerEnum extends Marshaler<T> {
  private final Class<T> mClass;
  
  protected MarshalerEnum(TypeReference<T> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableEnum, paramTypeReference, paramInt);
    this.mClass = paramTypeReference.getRawType();
  }
  
  public int getNativeSize() {
    return MarshalHelpers.getPrimitiveTypeSize(this.mNativeType);
  }
  
  public void marshal(T paramT, ByteBuffer paramByteBuffer) {
    int i = MarshalQueryableEnum.access$000((Enum)paramT);
    if (this.mNativeType == 1) {
      paramByteBuffer.putInt(i);
    } else {
      if (this.mNativeType == 0) {
        if (i >= 0 && i <= 255) {
          paramByteBuffer.put((byte)i);
          return;
        } 
        throw new UnsupportedOperationException(String.format("Enum value %x too large to fit into unsigned byte", new Object[] { Integer.valueOf(i) }));
      } 
      throw new AssertionError();
    } 
  }
  
  public T unmarshal(ByteBuffer paramByteBuffer) {
    int i = this.mNativeType;
    if (i != 0) {
      if (i == 1) {
        i = paramByteBuffer.getInt();
      } else {
        throw new AssertionError("Unexpected native type; impossible since its not supported");
      } 
    } else {
      i = paramByteBuffer.get() & 0xFF;
    } 
    return (T)MarshalQueryableEnum.access$100(this.mClass, i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableEnum$MarshalerEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */