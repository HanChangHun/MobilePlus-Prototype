package android.bluetooth;

import java.io.IOException;
import java.io.InputStream;

final class BluetoothInputStream extends InputStream {
  private BluetoothSocket mSocket;
  
  BluetoothInputStream(BluetoothSocket paramBluetoothSocket) {
    this.mSocket = paramBluetoothSocket;
  }
  
  public int available() throws IOException {
    return this.mSocket.available();
  }
  
  public void close() throws IOException {
    this.mSocket.close();
  }
  
  public int read() throws IOException {
    byte[] arrayOfByte = new byte[1];
    return (this.mSocket.read(arrayOfByte, 0, 1) == 1) ? (arrayOfByte[0] & 0xFF) : -1;
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramArrayOfbyte != null) {
      if ((paramInt1 | paramInt2) >= 0 && paramInt2 <= paramArrayOfbyte.length - paramInt1)
        return this.mSocket.read(paramArrayOfbyte, paramInt1, paramInt2); 
      throw new ArrayIndexOutOfBoundsException("invalid offset or length");
    } 
    throw new NullPointerException("byte array is null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */