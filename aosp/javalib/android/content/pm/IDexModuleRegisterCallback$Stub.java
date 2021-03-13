package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IDexModuleRegisterCallback {
  private static final String DESCRIPTOR = "android.content.pm.IDexModuleRegisterCallback";
  
  static final int TRANSACTION_onDexModuleRegistered = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IDexModuleRegisterCallback");
  }
  
  public static IDexModuleRegisterCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IDexModuleRegisterCallback");
    return (iInterface != null && iInterface instanceof IDexModuleRegisterCallback) ? (IDexModuleRegisterCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDexModuleRegisterCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onDexModuleRegistered";
  }
  
  public static boolean setDefaultImpl(IDexModuleRegisterCallback paramIDexModuleRegisterCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDexModuleRegisterCallback != null) {
        Proxy.sDefaultImpl = paramIDexModuleRegisterCallback;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.pm.IDexModuleRegisterCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IDexModuleRegisterCallback");
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onDexModuleRegistered(str, bool, paramParcel1.readString());
    return true;
  }
  
  private static class Proxy implements IDexModuleRegisterCallback {
    public static IDexModuleRegisterCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IDexModuleRegisterCallback";
    }
    
    public void onDexModuleRegistered(String param2String1, boolean param2Boolean, String param2String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.pm.IDexModuleRegisterCallback");
        parcel.writeString(param2String1);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param2String2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDexModuleRegisterCallback.Stub.getDefaultImpl() != null) {
          IDexModuleRegisterCallback.Stub.getDefaultImpl().onDexModuleRegistered(param2String1, param2Boolean, param2String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDexModuleRegisterCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */