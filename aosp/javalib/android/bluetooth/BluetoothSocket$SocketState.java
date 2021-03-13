package android.bluetooth;

enum SocketState {
  CLOSED, CONNECTED, INIT, LISTENING;
  
  static {
    CONNECTED = new SocketState("CONNECTED", 1);
    LISTENING = new SocketState("LISTENING", 2);
    SocketState socketState = new SocketState("CLOSED", 3);
    CLOSED = socketState;
    $VALUES = new SocketState[] { INIT, CONNECTED, LISTENING, socketState };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothSocket$SocketState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */