package android.graphics;

import java.io.IOException;

class ByteArraySource extends ImageDecoder.Source {
  private final byte[] mData;
  
  private final int mLength;
  
  private final int mOffset;
  
  ByteArraySource(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.mData = paramArrayOfbyte;
    this.mOffset = paramInt1;
    this.mLength = paramInt2;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
    return ImageDecoder.access$100(this.mData, this.mOffset, this.mLength, paramBoolean, this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$ByteArraySource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */