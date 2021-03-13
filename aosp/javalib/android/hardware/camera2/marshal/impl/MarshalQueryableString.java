package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class MarshalQueryableString implements MarshalQueryable<String> {
  private static final boolean DEBUG = false;
  
  private static final byte NUL = 0;
  
  private static final String TAG = MarshalQueryableString.class.getSimpleName();
  
  public Marshaler<String> createMarshaler(TypeReference<String> paramTypeReference, int paramInt) {
    return new MarshalerString(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<String> paramTypeReference, int paramInt) {
    boolean bool;
    if (paramInt == 0 && String.class.equals(paramTypeReference.getType())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private class MarshalerString extends Marshaler<String> {
    protected MarshalerString(TypeReference<String> param1TypeReference, int param1Int) {
      super(MarshalQueryableString.this, param1TypeReference, param1Int);
    }
    
    public int calculateMarshalSize(String param1String) {
      return (param1String.getBytes(MarshalQueryableString.PreloadHolder.UTF8_CHARSET)).length + 1;
    }
    
    public int getNativeSize() {
      return NATIVE_SIZE_DYNAMIC;
    }
    
    public void marshal(String param1String, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.put(param1String.getBytes(MarshalQueryableString.PreloadHolder.UTF8_CHARSET));
      param1ByteBuffer.put((byte)0);
    }
    
    public String unmarshal(ByteBuffer param1ByteBuffer) {
      boolean bool2;
      param1ByteBuffer.mark();
      boolean bool1 = false;
      byte b = 0;
      while (true) {
        bool2 = bool1;
        if (param1ByteBuffer.hasRemaining()) {
          if (param1ByteBuffer.get() == 0) {
            bool2 = true;
            break;
          } 
          b++;
          continue;
        } 
        break;
      } 
      if (bool2) {
        param1ByteBuffer.reset();
        byte[] arrayOfByte = new byte[b + 1];
        param1ByteBuffer.get(arrayOfByte, 0, b + 1);
        return new String(arrayOfByte, 0, b, MarshalQueryableString.PreloadHolder.UTF8_CHARSET);
      } 
      throw new UnsupportedOperationException("Strings must be null-terminated");
    }
  }
  
  private static class PreloadHolder {
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */