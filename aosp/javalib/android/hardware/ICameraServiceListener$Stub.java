package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICameraServiceListener {
  private static final String DESCRIPTOR = "android.hardware.ICameraServiceListener";
  
  static final int TRANSACTION_onCameraAccessPrioritiesChanged = 4;
  
  static final int TRANSACTION_onCameraClosed = 6;
  
  static final int TRANSACTION_onCameraOpened = 5;
  
  static final int TRANSACTION_onPhysicalCameraStatusChanged = 2;
  
  static final int TRANSACTION_onStatusChanged = 1;
  
  static final int TRANSACTION_onTorchStatusChanged = 3;
  
  public Stub() {
    attachInterface(this, "android.hardware.ICameraServiceListener");
  }
  
  public static ICameraServiceListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ICameraServiceListener");
    return (iInterface != null && iInterface instanceof ICameraServiceListener) ? (ICameraServiceListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICameraServiceListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 6:
        return "onCameraClosed";
      case 5:
        return "onCameraOpened";
      case 4:
        return "onCameraAccessPrioritiesChanged";
      case 3:
        return "onTorchStatusChanged";
      case 2:
        return "onPhysicalCameraStatusChanged";
      case 1:
        break;
    } 
    return "onStatusChanged";
  }
  
  public static boolean setDefaultImpl(ICameraServiceListener paramICameraServiceListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICameraServiceListener != null) {
        Proxy.sDefaultImpl = paramICameraServiceListener;
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
    if (paramInt1 != 1598968902) {
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.hardware.ICameraServiceListener");
          onCameraClosed(paramParcel1.readString());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.ICameraServiceListener");
          onCameraOpened(paramParcel1.readString(), paramParcel1.readString());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.ICameraServiceListener");
          onCameraAccessPrioritiesChanged();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.ICameraServiceListener");
          onTorchStatusChanged(paramParcel1.readInt(), paramParcel1.readString());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.ICameraServiceListener");
          onPhysicalCameraStatusChanged(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.ICameraServiceListener");
      onStatusChanged(paramParcel1.readInt(), paramParcel1.readString());
      return true;
    } 
    paramParcel2.writeString("android.hardware.ICameraServiceListener");
    return true;
  }
  
  private static class Proxy implements ICameraServiceListener {
    public static ICameraServiceListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
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
    
    public void onCameraClosed(String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeString(param2String);
        if (!this.mRemote.transact(6, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onCameraClosed(param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCameraOpened(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        if (!this.mRemote.transact(5, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onCameraOpened(param2String1, param2String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPhysicalCameraStatusChanged(int param2Int, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onPhysicalCameraStatusChanged(param2Int, param2String1, param2String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStatusChanged(int param2Int, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onStatusChanged(param2Int, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTorchStatusChanged(int param2Int, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(3, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onTorchStatusChanged(param2Int, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */