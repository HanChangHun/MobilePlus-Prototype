package android.graphics;

import java.io.IOException;
import java.nio.ByteBuffer;

class ByteBufferSource extends ImageDecoder.Source {
  private final ByteBuffer mBuffer;
  
  ByteBufferSource(ByteBuffer paramByteBuffer) {
    this.mBuffer = paramByteBuffer;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    if (!this.mBuffer.isDirect() && this.mBuffer.hasArray()) {
      int i = this.mBuffer.arrayOffset();
      int j = this.mBuffer.position();
      int k = this.mBuffer.limit();
      int m = this.mBuffer.position();
      return ImageDecoder.access$100(this.mBuffer.array(), i + j, k - m, paramBoolean, this);
    } 
    ByteBuffer byteBuffer = this.mBuffer.slice();
    return ImageDecoder.access$200(byteBuffer, byteBuffer.position(), byteBuffer.limit(), paramBoolean, this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$ByteBufferSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */