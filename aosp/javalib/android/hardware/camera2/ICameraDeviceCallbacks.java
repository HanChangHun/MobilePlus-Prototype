package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface ICameraDeviceCallbacks extends IInterface {
  public static final int ERROR_CAMERA_BUFFER = 5;
  
  public static final int ERROR_CAMERA_DEVICE = 1;
  
  public static final int ERROR_CAMERA_DISABLED = 6;
  
  public static final int ERROR_CAMERA_DISCONNECTED = 0;
  
  public static final int ERROR_CAMERA_INVALID_ERROR = -1;
  
  public static final int ERROR_CAMERA_REQUEST = 3;
  
  public static final int ERROR_CAMERA_RESULT = 4;
  
  public static final int ERROR_CAMERA_SERVICE = 2;
  
  void onCaptureStarted(CaptureResultExtras paramCaptureResultExtras, long paramLong) throws RemoteException;
  
  void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) throws RemoteException;
  
  void onDeviceIdle() throws RemoteException;
  
  void onPrepared(int paramInt) throws RemoteException;
  
  void onRepeatingRequestError(long paramLong, int paramInt) throws RemoteException;
  
  void onRequestQueueEmpty() throws RemoteException;
  
  void onResultReceived(CameraMetadataNative paramCameraMetadataNative, CaptureResultExtras paramCaptureResultExtras, PhysicalCaptureResultInfo[] paramArrayOfPhysicalCaptureResultInfo) throws RemoteException;
  
  public static class Default implements ICameraDeviceCallbacks {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCaptureStarted(CaptureResultExtras param1CaptureResultExtras, long param1Long) throws RemoteException {}
    
    public void onDeviceError(int param1Int, CaptureResultExtras param1CaptureResultExtras) throws RemoteException {}
    
    public void onDeviceIdle() throws RemoteException {}
    
    public void onPrepared(int param1Int) throws RemoteException {}
    
    public void onRepeatingRequestError(long param1Long, int param1Int) throws RemoteException {}
    
    public void onRequestQueueEmpty() throws RemoteException {}
    
    public void onResultReceived(CameraMetadataNative param1CameraMetadataNative, CaptureResultExtras param1CaptureResultExtras, PhysicalCaptureResultInfo[] param1ArrayOfPhysicalCaptureResultInfo) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICameraDeviceCallbacks {
    private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceCallbacks";
    
    static final int TRANSACTION_onCaptureStarted = 3;
    
    static final int TRANSACTION_onDeviceError = 1;
    
    static final int TRANSACTION_onDeviceIdle = 2;
    
    static final int TRANSACTION_onPrepared = 5;
    
    static final int TRANSACTION_onRepeatingRequestError = 6;
    
    static final int TRANSACTION_onRequestQueueEmpty = 7;
    
    static final int TRANSACTION_onResultReceived = 4;
    
    public Stub() {
      attachInterface(this, "android.hardware.camera2.ICameraDeviceCallbacks");
    }
    
    public static ICameraDeviceCallbacks asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.camera2.ICameraDeviceCallbacks");
      return (iInterface != null && iInterface instanceof ICameraDeviceCallbacks) ? (ICameraDeviceCallbacks)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraDeviceCallbacks getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 7:
          return "onRequestQueueEmpty";
        case 6:
          return "onRepeatingRequestError";
        case 5:
          return "onPrepared";
        case 4:
          return "onResultReceived";
        case 3:
          return "onCaptureStarted";
        case 2:
          return "onDeviceIdle";
        case 1:
          break;
      } 
      return "onDeviceError";
    }
    
    public static boolean setDefaultImpl(ICameraDeviceCallbacks param1ICameraDeviceCallbacks) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraDeviceCallbacks != null) {
          Proxy.sDefaultImpl = param1ICameraDeviceCallbacks;
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
        CaptureResultExtras captureResultExtras;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            onRequestQueueEmpty();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            onRepeatingRequestError(param1Parcel1.readLong(), param1Parcel1.readInt());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            onPrepared(param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            if (param1Parcel1.readInt() != 0) {
              CameraMetadataNative cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              captureResultExtras = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(param1Parcel1);
            } else {
              captureResultExtras = null;
            } 
            onResultReceived((CameraMetadataNative)param1Parcel2, captureResultExtras, (PhysicalCaptureResultInfo[])param1Parcel1.createTypedArray(PhysicalCaptureResultInfo.CREATOR));
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            if (param1Parcel1.readInt() != 0) {
              CaptureResultExtras captureResultExtras1 = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onCaptureStarted((CaptureResultExtras)param1Parcel2, param1Parcel1.readLong());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
            onDeviceIdle();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceCallbacks");
        param1Int1 = param1Parcel1.readInt();
        if (param1Parcel1.readInt() != 0) {
          CaptureResultExtras captureResultExtras1 = (CaptureResultExtras)CaptureResultExtras.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onDeviceError(param1Int1, (CaptureResultExtras)param1Parcel1);
        return true;
      } 
      param1Parcel2.writeString("android.hardware.camera2.ICameraDeviceCallbacks");
      return true;
    }
    
    private static class Proxy implements ICameraDeviceCallbacks {
      public static ICameraDeviceCallbacks sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.camera2.ICameraDeviceCallbacks";
      }
      
      public void onCaptureStarted(CaptureResultExtras param2CaptureResultExtras, long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          if (param2CaptureResultExtras != null) {
            parcel.writeInt(1);
            param2CaptureResultExtras.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(3, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onCaptureStarted(param2CaptureResultExtras, param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onDeviceError(int param2Int, CaptureResultExtras param2CaptureResultExtras) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          parcel.writeInt(param2Int);
          if (param2CaptureResultExtras != null) {
            parcel.writeInt(1);
            param2CaptureResultExtras.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceError(param2Int, param2CaptureResultExtras);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onDeviceIdle() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          if (!this.mRemote.transact(2, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceIdle();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onPrepared(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onPrepared(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onRepeatingRequestError(long param2Long, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onRepeatingRequestError(param2Long, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onRequestQueueEmpty() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          if (!this.mRemote.transact(7, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onRequestQueueEmpty();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onResultReceived(CameraMetadataNative param2CameraMetadataNative, CaptureResultExtras param2CaptureResultExtras, PhysicalCaptureResultInfo[] param2ArrayOfPhysicalCaptureResultInfo) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
          if (param2CameraMetadataNative != null) {
            parcel.writeInt(1);
            param2CameraMetadataNative.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2CaptureResultExtras != null) {
            parcel.writeInt(1);
            param2CaptureResultExtras.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeTypedArray((Parcelable[])param2ArrayOfPhysicalCaptureResultInfo, 0);
          if (!this.mRemote.transact(4, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
            ICameraDeviceCallbacks.Stub.getDefaultImpl().onResultReceived(param2CameraMetadataNative, param2CaptureResultExtras, param2ArrayOfPhysicalCaptureResultInfo);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ICameraDeviceCallbacks {
    public static ICameraDeviceCallbacks sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.camera2.ICameraDeviceCallbacks";
    }
    
    public void onCaptureStarted(CaptureResultExtras param1CaptureResultExtras, long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (param1CaptureResultExtras != null) {
          parcel.writeInt(1);
          param1CaptureResultExtras.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(3, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onCaptureStarted(param1CaptureResultExtras, param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeviceError(int param1Int, CaptureResultExtras param1CaptureResultExtras) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        parcel.writeInt(param1Int);
        if (param1CaptureResultExtras != null) {
          parcel.writeInt(1);
          param1CaptureResultExtras.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceError(param1Int, param1CaptureResultExtras);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeviceIdle() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (!this.mRemote.transact(2, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onDeviceIdle();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPrepared(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onPrepared(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRepeatingRequestError(long param1Long, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onRepeatingRequestError(param1Long, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRequestQueueEmpty() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (!this.mRemote.transact(7, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onRequestQueueEmpty();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onResultReceived(CameraMetadataNative param1CameraMetadataNative, CaptureResultExtras param1CaptureResultExtras, PhysicalCaptureResultInfo[] param1ArrayOfPhysicalCaptureResultInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.camera2.ICameraDeviceCallbacks");
        if (param1CameraMetadataNative != null) {
          parcel.writeInt(1);
          param1CameraMetadataNative.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1CaptureResultExtras != null) {
          parcel.writeInt(1);
          param1CaptureResultExtras.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeTypedArray((Parcelable[])param1ArrayOfPhysicalCaptureResultInfo, 0);
        if (!this.mRemote.transact(4, parcel, null, 1) && ICameraDeviceCallbacks.Stub.getDefaultImpl() != null) {
          ICameraDeviceCallbacks.Stub.getDefaultImpl().onResultReceived(param1CameraMetadataNative, param1CaptureResultExtras, param1ArrayOfPhysicalCaptureResultInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */