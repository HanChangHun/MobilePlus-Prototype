package android.content.pm;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.crypto.Mac;

public class MacAuthenticatedInputStream extends FilterInputStream {
  private final Mac mMac;
  
  public MacAuthenticatedInputStream(InputStream paramInputStream, Mac paramMac) {
    super(paramInputStream);
    this.mMac = paramMac;
  }
  
  public boolean isTagEqual(byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = this.mMac.doFinal();
    boolean bool = false;
    if (paramArrayOfbyte == null || arrayOfByte == null || paramArrayOfbyte.length != arrayOfByte.length)
      return false; 
    int i = 0;
    for (byte b = 0; b < paramArrayOfbyte.length; b++)
      i |= paramArrayOfbyte[b] ^ arrayOfByte[b]; 
    if (i == 0)
      bool = true; 
    return bool;
  }
  
  public int read() throws IOException {
    int i = super.read();
    if (i >= 0)
      this.mMac.update((byte)i); 
    return i;
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    paramInt2 = super.read(paramArrayOfbyte, paramInt1, paramInt2);
    if (paramInt2 > 0)
      this.mMac.update(paramArrayOfbyte, paramInt1, paramInt2); 
    return paramInt2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/MacAuthenticatedInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */