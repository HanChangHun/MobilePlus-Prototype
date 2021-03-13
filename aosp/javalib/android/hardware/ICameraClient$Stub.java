package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICameraClient {
  private static final String DESCRIPTOR = "android.hardware.ICameraClient";
  
  public Stub() {
    attachInterface(this, "android.hardware.ICameraClient");
  }
  
  public static ICameraClient asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ICameraClient");
    return (iInterface != null && iInterface instanceof ICameraClient) ? (ICameraClient)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICameraClient getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return null;
  }
  
  public static boolean setDefaultImpl(ICameraClient paramICameraClient) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICameraClient != null) {
        Proxy.sDefaultImpl = paramICameraClient;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902)
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
    paramParcel2.writeString("android.hardware.ICameraClient");
    return true;
  }
  
  private static class Proxy implements ICameraClient {
    public static ICameraClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ICameraClient";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraClient$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */