package android.content.res;

import android.os.ParcelFileDescriptor;
import java.io.IOException;

public class AutoCloseOutputStream extends ParcelFileDescriptor.AutoCloseOutputStream {
  private long mRemaining;
  
  public AutoCloseOutputStream(AssetFileDescriptor paramAssetFileDescriptor) throws IOException {
    super(paramAssetFileDescriptor.getParcelFileDescriptor());
    if (paramAssetFileDescriptor.getParcelFileDescriptor().seekTo(paramAssetFileDescriptor.getStartOffset()) >= 0L) {
      this.mRemaining = (int)paramAssetFileDescriptor.getLength();
      return;
    } 
    throw new IOException("Unable to seek");
  }
  
  public void write(int paramInt) throws IOException {
    long l = this.mRemaining;
    if (l >= 0L) {
      if (l == 0L)
        return; 
      super.write(paramInt);
      this.mRemaining--;
      return;
    } 
    super.write(paramInt);
  }
  
  public void write(byte[] paramArrayOfbyte) throws IOException {
    long l = this.mRemaining;
    if (l >= 0L) {
      if (l == 0L)
        return; 
      int i = paramArrayOfbyte.length;
      int j = i;
      if (i > l)
        j = (int)l; 
      super.write(paramArrayOfbyte);
      this.mRemaining -= j;
      return;
    } 
    super.write(paramArrayOfbyte);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    long l = this.mRemaining;
    if (l >= 0L) {
      if (l == 0L)
        return; 
      int i = paramInt2;
      if (paramInt2 > l)
        i = (int)l; 
      super.write(paramArrayOfbyte, paramInt1, i);
      this.mRemaining -= i;
      return;
    } 
    super.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetFileDescriptor$AutoCloseOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */