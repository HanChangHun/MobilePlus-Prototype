package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IContextHubClient {
  private static final String DESCRIPTOR = "android.hardware.location.IContextHubClient";
  
  static final int TRANSACTION_close = 2;
  
  static final int TRANSACTION_sendMessageToNanoApp = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IContextHubClient");
  }
  
  public static IContextHubClient asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IContextHubClient");
    return (iInterface != null && iInterface instanceof IContextHubClient) ? (IContextHubClient)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContextHubClient getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "close") : "sendMessageToNanoApp";
  }
  
  public static boolean setDefaultImpl(IContextHubClient paramIContextHubClient) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContextHubClient != null) {
        Proxy.sDefaultImpl = paramIContextHubClient;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.hardware.location.IContextHubClient");
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.location.IContextHubClient");
      close();
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IContextHubClient");
    if (paramParcel1.readInt() != 0) {
      NanoAppMessage nanoAppMessage = (NanoAppMessage)NanoAppMessage.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    paramInt1 = sendMessageToNanoApp((NanoAppMessage)paramParcel1);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(paramInt1);
    return true;
  }
  
  private static class Proxy implements IContextHubClient {
    public static IContextHubClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void close() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubClient");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContextHubClient.Stub.getDefaultImpl() != null) {
          IContextHubClient.Stub.getDefaultImpl().close();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubClient";
    }
    
    public int sendMessageToNanoApp(NanoAppMessage param2NanoAppMessage) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubClient");
        if (param2NanoAppMessage != null) {
          parcel1.writeInt(1);
          param2NanoAppMessage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContextHubClient.Stub.getDefaultImpl() != null)
          return IContextHubClient.Stub.getDefaultImpl().sendMessageToNanoApp(param2NanoAppMessage); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClient$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */