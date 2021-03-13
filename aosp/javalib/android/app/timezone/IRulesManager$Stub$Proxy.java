package android.app.timezone;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements IRulesManager {
  public static IRulesManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.timezone.IRulesManager";
  }
  
  public RulesState getRulesState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      RulesState rulesState;
      parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
        rulesState = IRulesManager.Stub.getDefaultImpl().getRulesState();
        return rulesState;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        rulesState = (RulesState)RulesState.CREATOR.createFromParcel(parcel2);
      } else {
        rulesState = null;
      } 
      return rulesState;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int requestInstall(ParcelFileDescriptor paramParcelFileDescriptor, byte[] paramArrayOfbyte, ICallback paramICallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeByteArray(paramArrayOfbyte);
      if (paramICallback != null) {
        iBinder = paramICallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
        return IRulesManager.Stub.getDefaultImpl().requestInstall(paramParcelFileDescriptor, paramArrayOfbyte, paramICallback); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestNothing(byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
        IRulesManager.Stub.getDefaultImpl().requestNothing(paramArrayOfbyte, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int requestUninstall(byte[] paramArrayOfbyte, ICallback paramICallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (paramICallback != null) {
        iBinder = paramICallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
        return IRulesManager.Stub.getDefaultImpl().requestUninstall(paramArrayOfbyte, paramICallback); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/IRulesManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */