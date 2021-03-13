package android.bluetooth;

import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

public final class BluetoothServerSocket implements Closeable {
  private static final boolean DBG = false;
  
  private static final String TAG = "BluetoothServerSocket";
  
  private int mChannel;
  
  private Handler mHandler;
  
  private int mMessage;
  
  final BluetoothSocket mSocket;
  
  BluetoothServerSocket(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) throws IOException {
    this.mChannel = paramInt2;
    BluetoothSocket bluetoothSocket = new BluetoothSocket(paramInt1, -1, paramBoolean1, paramBoolean2, null, paramInt2, null);
    this.mSocket = bluetoothSocket;
    if (paramInt2 == -2)
      bluetoothSocket.setExcludeSdp(true); 
  }
  
  BluetoothServerSocket(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, boolean paramBoolean3, boolean paramBoolean4) throws IOException {
    this.mChannel = paramInt2;
    BluetoothSocket bluetoothSocket = new BluetoothSocket(paramInt1, -1, paramBoolean1, paramBoolean2, null, paramInt2, null, paramBoolean3, paramBoolean4);
    this.mSocket = bluetoothSocket;
    if (paramInt2 == -2)
      bluetoothSocket.setExcludeSdp(true); 
  }
  
  BluetoothServerSocket(int paramInt, boolean paramBoolean1, boolean paramBoolean2, ParcelUuid paramParcelUuid) throws IOException {
    BluetoothSocket bluetoothSocket = new BluetoothSocket(paramInt, -1, paramBoolean1, paramBoolean2, null, -1, paramParcelUuid);
    this.mSocket = bluetoothSocket;
    this.mChannel = bluetoothSocket.getPort();
  }
  
  public BluetoothSocket accept() throws IOException {
    return accept(-1);
  }
  
  public BluetoothSocket accept(int paramInt) throws IOException {
    return this.mSocket.accept(paramInt);
  }
  
  public void close() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mHandler : Landroid/os/Handler;
    //   6: ifnull -> 23
    //   9: aload_0
    //   10: getfield mHandler : Landroid/os/Handler;
    //   13: aload_0
    //   14: getfield mMessage : I
    //   17: invokevirtual obtainMessage : (I)Landroid/os/Message;
    //   20: invokevirtual sendToTarget : ()V
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_0
    //   26: getfield mSocket : Landroid/bluetooth/BluetoothSocket;
    //   29: invokevirtual close : ()V
    //   32: return
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	33	finally
    //   23	25	33	finally
    //   34	36	33	finally
  }
  
  public int getChannel() {
    return this.mChannel;
  }
  
  public int getPsm() {
    return this.mChannel;
  }
  
  void setChannel(int paramInt) {
    BluetoothSocket bluetoothSocket = this.mSocket;
    if (bluetoothSocket != null && bluetoothSocket.getPort() != paramInt) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("The port set is different that the underlying port. mSocket.getPort(): ");
      stringBuilder.append(this.mSocket.getPort());
      stringBuilder.append(" requested newChannel: ");
      stringBuilder.append(paramInt);
      Log.w("BluetoothServerSocket", stringBuilder.toString());
    } 
    this.mChannel = paramInt;
  }
  
  void setCloseHandler(Handler paramHandler, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mHandler : Landroid/os/Handler;
    //   7: aload_0
    //   8: iload_2
    //   9: putfield mMessage : I
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
  
  void setServiceName(String paramString) {
    this.mSocket.setServiceName(paramString);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServerSocket: Type: ");
    int i = this.mSocket.getConnectionType();
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 4)
            stringBuilder.append("TYPE_L2CAP_LE"); 
        } else {
          stringBuilder.append("TYPE_L2CAP");
        } 
      } else {
        stringBuilder.append("TYPE_SCO");
      } 
    } else {
      stringBuilder.append("TYPE_RFCOMM");
    } 
    stringBuilder.append(" Channel: ");
    stringBuilder.append(this.mChannel);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothServerSocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */