package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IContextHubClient extends IInterface {
  void close() throws RemoteException;
  
  int sendMessageToNanoApp(NanoAppMessage paramNanoAppMessage) throws RemoteException;
  
  public static class Default implements IContextHubClient {
    public IBinder asBinder() {
      return null;
    }
    
    public void close() throws RemoteException {}
    
    public int sendMessageToNanoApp(NanoAppMessage param1NanoAppMessage) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IContextHubClient {
    private static final String DESCRIPTOR = "android.hardware.location.IContextHubClient";
    
    static final int TRANSACTION_close = 2;
    
    static final int TRANSACTION_sendMessageToNanoApp = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IContextHubClient");
    }
    
    public static IContextHubClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IContextHubClient");
      return (iInterface != null && iInterface instanceof IContextHubClient) ? (IContextHubClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContextHubClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "close") : "sendMessageToNanoApp";
    }
    
    public static boolean setDefaultImpl(IContextHubClient param1IContextHubClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContextHubClient != null) {
          Proxy.sDefaultImpl = param1IContextHubClient;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.hardware.location.IContextHubClient");
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.location.IContextHubClient");
        close();
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IContextHubClient");
      if (param1Parcel1.readInt() != 0) {
        NanoAppMessage nanoAppMessage = (NanoAppMessage)NanoAppMessage.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      param1Int1 = sendMessageToNanoApp((NanoAppMessage)param1Parcel1);
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(param1Int1);
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
  
  private static class Proxy implements IContextHubClient {
    public static IContextHubClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public int sendMessageToNanoApp(NanoAppMessage param1NanoAppMessage) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubClient");
        if (param1NanoAppMessage != null) {
          parcel1.writeInt(1);
          param1NanoAppMessage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContextHubClient.Stub.getDefaultImpl() != null)
          return IContextHubClient.Stub.getDefaultImpl().sendMessageToNanoApp(param1NanoAppMessage); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */