package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

class Proxy implements IShortcutChangeCallback {
  public static IShortcutChangeCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IShortcutChangeCallback";
  }
  
  public void onShortcutsAddedOrUpdated(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IShortcutChangeCallback");
      parcel.writeString(paramString);
      parcel.writeTypedList(paramList);
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IShortcutChangeCallback.Stub.getDefaultImpl() != null) {
        IShortcutChangeCallback.Stub.getDefaultImpl().onShortcutsAddedOrUpdated(paramString, paramList, paramUserHandle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onShortcutsRemoved(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IShortcutChangeCallback");
      parcel.writeString(paramString);
      parcel.writeTypedList(paramList);
      if (paramUserHandle != null) {
        parcel.writeInt(1);
        paramUserHandle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IShortcutChangeCallback.Stub.getDefaultImpl() != null) {
        IShortcutChangeCallback.Stub.getDefaultImpl().onShortcutsRemoved(paramString, paramList, paramUserHandle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutChangeCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */