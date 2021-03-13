package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IDexModuleRegisterCallback {
  public static IDexModuleRegisterCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IDexModuleRegisterCallback";
  }
  
  public void onDexModuleRegistered(String paramString1, boolean paramBoolean, String paramString2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.content.pm.IDexModuleRegisterCallback");
      parcel.writeString(paramString1);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeString(paramString2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IDexModuleRegisterCallback.Stub.getDefaultImpl() != null) {
        IDexModuleRegisterCallback.Stub.getDefaultImpl().onDexModuleRegistered(paramString1, paramBoolean, paramString2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDexModuleRegisterCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */