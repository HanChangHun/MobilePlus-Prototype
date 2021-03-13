package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IContextHubCallback extends IInterface {
  void onMessageReceipt(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) throws RemoteException;
  
  public static class Default implements IContextHubCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onMessageReceipt(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IContextHubCallback {
    private static final String DESCRIPTOR = "android.hardware.location.IContextHubCallback";
    
    static final int TRANSACTION_onMessageReceipt = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IContextHubCallback");
    }
    
    public static IContextHubCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IContextHubCallback");
      return (iInterface != null && iInterface instanceof IContextHubCallback) ? (IContextHubCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContextHubCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onMessageReceipt";
    }
    
    public static boolean setDefaultImpl(IContextHubCallback param1IContextHubCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContextHubCallback != null) {
          Proxy.sDefaultImpl = param1IContextHubCallback;
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
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.location.IContextHubCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IContextHubCallback");
      param1Int2 = param1Parcel1.readInt();
      param1Int1 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        ContextHubMessage contextHubMessage = (ContextHubMessage)ContextHubMessage.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onMessageReceipt(param1Int2, param1Int1, (ContextHubMessage)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IContextHubCallback {
      public static IContextHubCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IContextHubCallback";
      }
      
      public void onMessageReceipt(int param2Int1, int param2Int2, ContextHubMessage param2ContextHubMessage) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (param2ContextHubMessage != null) {
            parcel.writeInt(1);
            param2ContextHubMessage.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubCallback.Stub.getDefaultImpl() != null) {
            IContextHubCallback.Stub.getDefaultImpl().onMessageReceipt(param2Int1, param2Int2, param2ContextHubMessage);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IContextHubCallback {
    public static IContextHubCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubCallback";
    }
    
    public void onMessageReceipt(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (param1ContextHubMessage != null) {
          parcel.writeInt(1);
          param1ContextHubMessage.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubCallback.Stub.getDefaultImpl() != null) {
          IContextHubCallback.Stub.getDefaultImpl().onMessageReceipt(param1Int1, param1Int2, param1ContextHubMessage);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */