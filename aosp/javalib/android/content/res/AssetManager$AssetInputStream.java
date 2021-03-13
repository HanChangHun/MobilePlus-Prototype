package android.content.res;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public final class AssetInputStream extends InputStream {
  private long mAssetNativePtr;
  
  private long mLength;
  
  private long mMarkPos;
  
  private AssetInputStream(long paramLong) {
    this.mAssetNativePtr = paramLong;
    this.mLength = AssetManager.access$600(paramLong);
  }
  
  private void ensureOpen() {
    if (this.mAssetNativePtr != 0L)
      return; 
    throw new IllegalStateException("AssetInputStream is closed");
  }
  
  public final int available() throws IOException {
    int i;
    ensureOpen();
    long l = AssetManager.access$1000(this.mAssetNativePtr);
    if (l > 2147483647L) {
      i = Integer.MAX_VALUE;
    } else {
      i = (int)l;
    } 
    return i;
  }
  
  public final void close() throws IOException {
    long l = this.mAssetNativePtr;
    if (l != 0L) {
      AssetManager.access$1100(l);
      this.mAssetNativePtr = 0L;
      synchronized (AssetManager.this) {
        AssetManager.access$1200(AssetManager.this, hashCode());
      } 
    } 
  }
  
  protected void finalize() throws Throwable {
    close();
  }
  
  public final int getAssetInt() {
    throw new UnsupportedOperationException();
  }
  
  public final long getNativeAsset() {
    return this.mAssetNativePtr;
  }
  
  public final void mark(int paramInt) {
    ensureOpen();
    this.mMarkPos = AssetManager.access$900(this.mAssetNativePtr, 0L, 0);
  }
  
  public final boolean markSupported() {
    return true;
  }
  
  public final int read() throws IOException {
    ensureOpen();
    return AssetManager.access$700(this.mAssetNativePtr);
  }
  
  public final int read(byte[] paramArrayOfbyte) throws IOException {
    ensureOpen();
    Objects.requireNonNull(paramArrayOfbyte, "b");
    return AssetManager.access$800(this.mAssetNativePtr, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public final int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    ensureOpen();
    Objects.requireNonNull(paramArrayOfbyte, "b");
    return AssetManager.access$800(this.mAssetNativePtr, paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public final void reset() throws IOException {
    ensureOpen();
    AssetManager.access$900(this.mAssetNativePtr, this.mMarkPos, -1);
  }
  
  public final long skip(long paramLong) throws IOException {
    ensureOpen();
    long l1 = AssetManager.access$900(this.mAssetNativePtr, 0L, 0);
    long l2 = this.mLength;
    long l3 = paramLong;
    if (l1 + paramLong > l2)
      l3 = l2 - l1; 
    if (l3 > 0L)
      AssetManager.access$900(this.mAssetNativePtr, l3, 0); 
    return l3;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetManager$AssetInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */