package android.hardware;

import android.os.IBinder;

class Proxy implements ICameraClient {
  public static ICameraClient sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.ICameraClient";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraClient$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */