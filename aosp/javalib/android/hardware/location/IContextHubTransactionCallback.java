package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IContextHubTransactionCallback extends IInterface {
  void onQueryResponse(int paramInt, List<NanoAppState> paramList) throws RemoteException;
  
  void onTransactionComplete(int paramInt) throws RemoteException;
  
  public static class Default implements IContextHubTransactionCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onQueryResponse(int param1Int, List<NanoAppState> param1List) throws RemoteException {}
    
    public void onTransactionComplete(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IContextHubTransactionCallback {
    private static final String DESCRIPTOR = "android.hardware.location.IContextHubTransactionCallback";
    
    static final int TRANSACTION_onQueryResponse = 1;
    
    static final int TRANSACTION_onTransactionComplete = 2;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IContextHubTransactionCallback");
    }
    
    public static IContextHubTransactionCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IContextHubTransactionCallback");
      return (iInterface != null && iInterface instanceof IContextHubTransactionCallback) ? (IContextHubTransactionCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContextHubTransactionCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onTransactionComplete") : "onQueryResponse";
    }
    
    public static boolean setDefaultImpl(IContextHubTransactionCallback param1IContextHubTransactionCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContextHubTransactionCallback != null) {
          Proxy.sDefaultImpl = param1IContextHubTransactionCallback;
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
          param1Parcel2.writeString("android.hardware.location.IContextHubTransactionCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.location.IContextHubTransactionCallback");
        onTransactionComplete(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IContextHubTransactionCallback");
      onQueryResponse(param1Parcel1.readInt(), param1Parcel1.createTypedArrayList(NanoAppState.CREATOR));
      return true;
    }
    
    private static class Proxy implements IContextHubTransactionCallback {
      public static IContextHubTransactionCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IContextHubTransactionCallback";
      }
      
      public void onQueryResponse(int param2Int, List<NanoAppState> param2List) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
          parcel.writeInt(param2Int);
          parcel.writeTypedList(param2List);
          if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
            IContextHubTransactionCallback.Stub.getDefaultImpl().onQueryResponse(param2Int, param2List);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onTransactionComplete(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
            IContextHubTransactionCallback.Stub.getDefaultImpl().onTransactionComplete(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IContextHubTransactionCallback {
    public static IContextHubTransactionCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubTransactionCallback";
    }
    
    public void onQueryResponse(int param1Int, List<NanoAppState> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
        parcel.writeInt(param1Int);
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
          IContextHubTransactionCallback.Stub.getDefaultImpl().onQueryResponse(param1Int, param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTransactionComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
          IContextHubTransactionCallback.Stub.getDefaultImpl().onTransactionComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubTransactionCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */