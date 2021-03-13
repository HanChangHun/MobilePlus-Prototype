package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IQualifiedNetworksServiceCallback extends IInterface {
  void onQualifiedNetworkTypesChanged(int paramInt, int[] paramArrayOfint) throws RemoteException;
  
  public static class Default implements IQualifiedNetworksServiceCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onQualifiedNetworkTypesChanged(int param1Int, int[] param1ArrayOfint) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IQualifiedNetworksServiceCallback {
    private static final String DESCRIPTOR = "android.telephony.data.IQualifiedNetworksServiceCallback";
    
    static final int TRANSACTION_onQualifiedNetworkTypesChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.telephony.data.IQualifiedNetworksServiceCallback");
    }
    
    public static IQualifiedNetworksServiceCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.telephony.data.IQualifiedNetworksServiceCallback");
      return (iInterface != null && iInterface instanceof IQualifiedNetworksServiceCallback) ? (IQualifiedNetworksServiceCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IQualifiedNetworksServiceCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onQualifiedNetworkTypesChanged";
    }
    
    public static boolean setDefaultImpl(IQualifiedNetworksServiceCallback param1IQualifiedNetworksServiceCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IQualifiedNetworksServiceCallback != null) {
          Proxy.sDefaultImpl = param1IQualifiedNetworksServiceCallback;
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
        param1Parcel2.writeString("android.telephony.data.IQualifiedNetworksServiceCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.telephony.data.IQualifiedNetworksServiceCallback");
      onQualifiedNetworkTypesChanged(param1Parcel1.readInt(), param1Parcel1.createIntArray());
      return true;
    }
    
    private static class Proxy implements IQualifiedNetworksServiceCallback {
      public static IQualifiedNetworksServiceCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.telephony.data.IQualifiedNetworksServiceCallback";
      }
      
      public void onQualifiedNetworkTypesChanged(int param2Int, int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksServiceCallback");
          parcel.writeInt(param2Int);
          parcel.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksServiceCallback.Stub.getDefaultImpl() != null) {
            IQualifiedNetworksServiceCallback.Stub.getDefaultImpl().onQualifiedNetworkTypesChanged(param2Int, param2ArrayOfint);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IQualifiedNetworksServiceCallback {
    public static IQualifiedNetworksServiceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.telephony.data.IQualifiedNetworksServiceCallback";
    }
    
    public void onQualifiedNetworkTypesChanged(int param1Int, int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksServiceCallback");
        parcel.writeInt(param1Int);
        parcel.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksServiceCallback.Stub.getDefaultImpl() != null) {
          IQualifiedNetworksServiceCallback.Stub.getDefaultImpl().onQualifiedNetworkTypesChanged(param1Int, param1ArrayOfint);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksServiceCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */