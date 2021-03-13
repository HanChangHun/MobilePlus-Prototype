package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

class Proxy implements IRestrictionsManager {
  public static IRestrictionsManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public Intent createLocalApprovalIntent() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Intent intent;
      parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
        intent = IRestrictionsManager.Stub.getDefaultImpl().createLocalApprovalIntent();
        return intent;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
      } else {
        intent = null;
      } 
      return intent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getApplicationRestrictions(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null)
        return IRestrictionsManager.Stub.getDefaultImpl().getApplicationRestrictions(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (Bundle)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.IRestrictionsManager";
  }
  
  public boolean hasRestrictionsProvider() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
        bool = IRestrictionsManager.Stub.getDefaultImpl().hasRestrictionsProvider();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyPermissionResponse(String paramString, PersistableBundle paramPersistableBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
      parcel1.writeString(paramString);
      if (paramPersistableBundle != null) {
        parcel1.writeInt(1);
        paramPersistableBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
        IRestrictionsManager.Stub.getDefaultImpl().notifyPermissionResponse(paramString, paramPersistableBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestPermission(String paramString1, String paramString2, String paramString3, PersistableBundle paramPersistableBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      if (paramPersistableBundle != null) {
        parcel1.writeInt(1);
        paramPersistableBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
        IRestrictionsManager.Stub.getDefaultImpl().requestPermission(paramString1, paramString2, paramString3, paramPersistableBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IRestrictionsManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */