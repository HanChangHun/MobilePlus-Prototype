package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraServiceListener extends IInterface {
  public static final int STATUS_ENUMERATING = 2;
  
  public static final int STATUS_NOT_AVAILABLE = -2;
  
  public static final int STATUS_NOT_PRESENT = 0;
  
  public static final int STATUS_PRESENT = 1;
  
  public static final int STATUS_UNKNOWN = -1;
  
  public static final int TORCH_STATUS_AVAILABLE_OFF = 1;
  
  public static final int TORCH_STATUS_AVAILABLE_ON = 2;
  
  public static final int TORCH_STATUS_NOT_AVAILABLE = 0;
  
  public static final int TORCH_STATUS_UNKNOWN = -1;
  
  void onCameraAccessPrioritiesChanged() throws RemoteException;
  
  void onCameraClosed(String paramString) throws RemoteException;
  
  void onCameraOpened(String paramString1, String paramString2) throws RemoteException;
  
  void onPhysicalCameraStatusChanged(int paramInt, String paramString1, String paramString2) throws RemoteException;
  
  void onStatusChanged(int paramInt, String paramString) throws RemoteException;
  
  void onTorchStatusChanged(int paramInt, String paramString) throws RemoteException;
  
  public static class Default implements ICameraServiceListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCameraAccessPrioritiesChanged() throws RemoteException {}
    
    public void onCameraClosed(String param1String) throws RemoteException {}
    
    public void onCameraOpened(String param1String1, String param1String2) throws RemoteException {}
    
    public void onPhysicalCameraStatusChanged(int param1Int, String param1String1, String param1String2) throws RemoteException {}
    
    public void onStatusChanged(int param1Int, String param1String) throws RemoteException {}
    
    public void onTorchStatusChanged(int param1Int, String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICameraServiceListener {
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
    
    public static ICameraServiceListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ICameraServiceListener");
      return (iInterface != null && iInterface instanceof ICameraServiceListener) ? (ICameraServiceListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraServiceListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(ICameraServiceListener param1ICameraServiceListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraServiceListener != null) {
          Proxy.sDefaultImpl = param1ICameraServiceListener;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.hardware.ICameraServiceListener");
            onCameraClosed(param1Parcel1.readString());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.ICameraServiceListener");
            onCameraOpened(param1Parcel1.readString(), param1Parcel1.readString());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.ICameraServiceListener");
            onCameraAccessPrioritiesChanged();
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.ICameraServiceListener");
            onTorchStatusChanged(param1Parcel1.readInt(), param1Parcel1.readString());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.ICameraServiceListener");
            onPhysicalCameraStatusChanged(param1Parcel1.readInt(), param1Parcel1.readString(), param1Parcel1.readString());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.ICameraServiceListener");
        onStatusChanged(param1Parcel1.readInt(), param1Parcel1.readString());
        return true;
      } 
      param1Parcel2.writeString("android.hardware.ICameraServiceListener");
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
  
  private static class Proxy implements ICameraServiceListener {
    public static ICameraServiceListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void onCameraClosed(String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeString(param1String);
        if (!this.mRemote.transact(6, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onCameraClosed(param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCameraOpened(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        if (!this.mRemote.transact(5, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onCameraOpened(param1String1, param1String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPhysicalCameraStatusChanged(int param1Int, String param1String1, String param1String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onPhysicalCameraStatusChanged(param1Int, param1String1, param1String2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStatusChanged(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onStatusChanged(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTorchStatusChanged(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceListener");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(3, parcel, null, 1) && ICameraServiceListener.Stub.getDefaultImpl() != null) {
          ICameraServiceListener.Stub.getDefaultImpl().onTorchStatusChanged(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */