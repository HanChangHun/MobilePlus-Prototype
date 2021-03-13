package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

class MarshalerString extends Marshaler<String> {
  protected MarshalerString(TypeReference<String> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableString, paramTypeReference, paramInt);
  }
  
  public int calculateMarshalSize(String paramString) {
    return (paramString.getBytes(MarshalQueryableString.PreloadHolder.UTF8_CHARSET)).length + 1;
  }
  
  public int getNativeSize() {
    return NATIVE_SIZE_DYNAMIC;
  }
  
  public void marshal(String paramString, ByteBuffer paramByteBuffer) {
    paramByteBuffer.put(paramString.getBytes(MarshalQueryableString.PreloadHolder.UTF8_CHARSET));
    paramByteBuffer.put((byte)0);
  }
  
  public String unmarshal(ByteBuffer paramByteBuffer) {
    boolean bool2;
    paramByteBuffer.mark();
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (paramByteBuffer.hasRemaining()) {
        if (paramByteBuffer.get() == 0) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    if (bool2) {
      paramByteBuffer.reset();
      byte[] arrayOfByte = new byte[b + 1];
      paramByteBuffer.get(arrayOfByte, 0, b + 1);
      return new String(arrayOfByte, 0, b, MarshalQueryableString.PreloadHolder.UTF8_CHARSET);
    } 
    throw new UnsupportedOperationException("Strings must be null-terminated");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableString$MarshalerString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */