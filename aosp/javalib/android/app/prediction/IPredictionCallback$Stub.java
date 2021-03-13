package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPredictionCallback {
  private static final String DESCRIPTOR = "android.app.prediction.IPredictionCallback";
  
  static final int TRANSACTION_onResult = 1;
  
  public Stub() {
    attachInterface(this, "android.app.prediction.IPredictionCallback");
  }
  
  public static IPredictionCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.prediction.IPredictionCallback");
    return (iInterface != null && iInterface instanceof IPredictionCallback) ? (IPredictionCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPredictionCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onResult";
  }
  
  public static boolean setDefaultImpl(IPredictionCallback paramIPredictionCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPredictionCallback != null) {
        Proxy.sDefaultImpl = paramIPredictionCallback;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.app.prediction.IPredictionCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.prediction.IPredictionCallback");
    if (paramParcel1.readInt() != 0) {
      ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onResult((ParceledListSlice)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */