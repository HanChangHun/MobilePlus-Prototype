package android.companion;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements ICompanionDeviceManager {
  public static ICompanionDeviceManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void associate(AssociationRequest paramAssociationRequest, IFindDeviceCallback paramIFindDeviceCallback, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      if (paramAssociationRequest != null) {
        parcel1.writeInt(1);
        paramAssociationRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIFindDeviceCallback != null) {
        iBinder = paramIFindDeviceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
        ICompanionDeviceManager.Stub.getDefaultImpl().associate(paramAssociationRequest, paramIFindDeviceCallback, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disassociate(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
        ICompanionDeviceManager.Stub.getDefaultImpl().disassociate(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAssociations(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null)
        return ICompanionDeviceManager.Stub.getDefaultImpl().getAssociations(paramString, paramInt); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.companion.ICompanionDeviceManager";
  }
  
  public boolean hasNotificationAccess(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
        bool = ICompanionDeviceManager.Stub.getDefaultImpl().hasNotificationAccess(paramComponentName);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isDeviceAssociatedForWifiConnection(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
        bool = ICompanionDeviceManager.Stub.getDefaultImpl().isDeviceAssociatedForWifiConnection(paramString1, paramString2, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public PendingIntent requestNotificationAccess(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null)
        return ICompanionDeviceManager.Stub.getDefaultImpl().requestNotificationAccess(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (PendingIntent)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopScan(AssociationRequest paramAssociationRequest, IFindDeviceCallback paramIFindDeviceCallback, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
      if (paramAssociationRequest != null) {
        parcel1.writeInt(1);
        paramAssociationRequest.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIFindDeviceCallback != null) {
        iBinder = paramIFindDeviceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
        ICompanionDeviceManager.Stub.getDefaultImpl().stopScan(paramAssociationRequest, paramIFindDeviceCallback, paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */