package android.hardware;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ICameraServiceListener {
  public static ICameraServiceListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.ICameraServiceListener";
  }
  
  public void onCameraAccessPrioritiesChanged() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
      if (!this.mRemote.transact(4, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
        ICameraServiceListener.Stub.getDefaultImpl().onCameraAccessPrioritiesChanged();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onCameraClosed(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(6, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
        ICameraServiceListener.Stub.getDefaultImpl().onCameraClosed(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onCameraOpened(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      if (!this.mRemote.transact(5, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
        ICameraServiceListener.Stub.getDefaultImpl().onCameraOpened(paramString1, paramString2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPhysicalCameraStatusChanged(int paramInt, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
        ICameraServiceListener.Stub.getDefaultImpl().onPhysicalCameraStatusChanged(paramInt, paramString1, paramString2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onStatusChanged(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
        ICameraServiceListener.Stub.getDefaultImpl().onStatusChanged(paramInt, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTorchStatusChanged(int paramInt, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(3, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
        ICameraServiceListener.Stub.getDefaultImpl().onTorchStatusChanged(paramInt, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */