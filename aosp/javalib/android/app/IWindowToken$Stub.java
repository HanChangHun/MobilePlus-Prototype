package android.app;

import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IWindowToken {
  private static final String DESCRIPTOR = "android.app.IWindowToken";
  
  static final int TRANSACTION_onConfigurationChanged = 1;
  
  static final int TRANSACTION_onWindowTokenRemoved = 2;
  
  public Stub() {
    attachInterface(this, "android.app.IWindowToken");
  }
  
  public static IWindowToken asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IWindowToken");
    return (iInterface != null && iInterface instanceof IWindowToken) ? (IWindowToken)iInterface : new Proxy(paramIBinder);
  }
  
  public static IWindowToken getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onWindowTokenRemoved") : "onConfigurationChanged";
  }
  
  public static boolean setDefaultImpl(IWindowToken paramIWindowToken) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIWindowToken != null) {
        Proxy.sDefaultImpl = paramIWindowToken;
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
        paramParcel2.writeString("android.app.IWindowToken");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IWindowToken");
      onWindowTokenRemoved();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IWindowToken");
    if (paramParcel1.readInt() != 0) {
      Configuration configuration = (Configuration)Configuration.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onConfigurationChanged((Configuration)paramParcel2, paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IWindowToken {
    public static IWindowToken sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IWindowToken";
    }
    
    public void onConfigurationChanged(Configuration param2Configuration, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWindowToken");
        if (param2Configuration != null) {
          parcel.writeInt(1);
          param2Configuration.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
          IWindowToken.Stub.getDefaultImpl().onConfigurationChanged(param2Configuration, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onWindowTokenRemoved() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWindowToken");
        if (!this.mRemote.transact(2, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
          IWindowToken.Stub.getDefaultImpl().onWindowTokenRemoved();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWindowToken$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */