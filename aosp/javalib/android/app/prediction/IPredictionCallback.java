package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPredictionCallback extends IInterface {
  void onResult(ParceledListSlice paramParceledListSlice) throws RemoteException;
  
  public static class Default implements IPredictionCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onResult(ParceledListSlice param1ParceledListSlice) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPredictionCallback {
    private static final String DESCRIPTOR = "android.app.prediction.IPredictionCallback";
    
    static final int TRANSACTION_onResult = 1;
    
    public Stub() {
      attachInterface(this, "android.app.prediction.IPredictionCallback");
    }
    
    public static IPredictionCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.prediction.IPredictionCallback");
      return (iInterface != null && iInterface instanceof IPredictionCallback) ? (IPredictionCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPredictionCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onResult";
    }
    
    public static boolean setDefaultImpl(IPredictionCallback param1IPredictionCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPredictionCallback != null) {
          Proxy.sDefaultImpl = param1IPredictionCallback;
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
        param1Parcel2.writeString("android.app.prediction.IPredictionCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.prediction.IPredictionCallback");
      if (param1Parcel1.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onResult((ParceledListSlice)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IPredictionCallback {
      public static IPredictionCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.prediction.IPredictionCallback";
      }
      
      public void onResult(ParceledListSlice param2ParceledListSlice) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.prediction.IPredictionCallback");
          if (param2ParceledListSlice != null) {
            parcel.writeInt(1);
            param2ParceledListSlice.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IPredictionCallback.Stub.getDefaultImpl() != null) {
            IPredictionCallback.Stub.getDefaultImpl().onResult(param2ParceledListSlice);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPredictionCallback {
    public static IPredictionCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.prediction.IPredictionCallback";
    }
    
    public void onResult(ParceledListSlice param1ParceledListSlice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.prediction.IPredictionCallback");
        if (param1ParceledListSlice != null) {
          parcel.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IPredictionCallback.Stub.getDefaultImpl() != null) {
          IPredictionCallback.Stub.getDefaultImpl().onResult(param1ParceledListSlice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */