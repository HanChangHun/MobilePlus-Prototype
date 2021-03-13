package android.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IActivityPendingResult {
  private static final String DESCRIPTOR = "android.app.IActivityPendingResult";
  
  static final int TRANSACTION_sendResult = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IActivityPendingResult");
  }
  
  public static IActivityPendingResult asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IActivityPendingResult");
    return (iInterface != null && iInterface instanceof IActivityPendingResult) ? (IActivityPendingResult)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityPendingResult getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "sendResult";
  }
  
  public static boolean setDefaultImpl(IActivityPendingResult paramIActivityPendingResult) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityPendingResult != null) {
        Proxy.sDefaultImpl = paramIActivityPendingResult;
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
      paramParcel2.writeString("android.app.IActivityPendingResult");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IActivityPendingResult");
    paramInt1 = paramParcel1.readInt();
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    boolean bool = sendResult(paramInt1, str, (Bundle)paramParcel1);
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private static class Proxy implements IActivityPendingResult {
    public static IActivityPendingResult sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IActivityPendingResult";
    }
    
    public boolean sendResult(int param2Int, String param2String, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityPendingResult");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityPendingResult.Stub.getDefaultImpl() != null) {
          bool = IActivityPendingResult.Stub.getDefaultImpl().sendResult(param2Int, param2String, param2Bundle);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityPendingResult$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */