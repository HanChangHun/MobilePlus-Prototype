package android.bluetooth;

import java.io.IOException;
import java.io.OutputStream;

final class BluetoothOutputStream extends OutputStream {
  private BluetoothSocket mSocket;
  
  BluetoothOutputStream(BluetoothSocket paramBluetoothSocket) {
    this.mSocket = paramBluetoothSocket;
  }
  
  public void close() throws IOException {
    this.mSocket.close();
  }
  
  public void write(int paramInt) throws IOException {
    byte b = (byte)paramInt;
    this.mSocket.write(new byte[] { b }, 0, 1);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramArrayOfbyte != null) {
      if ((paramInt1 | paramInt2) >= 0 && paramInt2 <= paramArrayOfbyte.length - paramInt1) {
        this.mSocket.write(paramArrayOfbyte, paramInt1, paramInt2);
        return;
      } 
      throw new IndexOutOfBoundsException("invalid offset or length");
    } 
    throw new NullPointerException("buffer is null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */