package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.view.Surface;

public abstract class Stub extends Binder implements ICameraDeviceUser {
  private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceUser";
  
  static final int TRANSACTION_beginConfigure = 5;
  
  static final int TRANSACTION_cancelRequest = 4;
  
  static final int TRANSACTION_createDefaultRequest = 12;
  
  static final int TRANSACTION_createInputStream = 10;
  
  static final int TRANSACTION_createStream = 9;
  
  static final int TRANSACTION_deleteStream = 8;
  
  static final int TRANSACTION_disconnect = 1;
  
  static final int TRANSACTION_endConfigure = 6;
  
  static final int TRANSACTION_finalizeOutputConfigurations = 20;
  
  static final int TRANSACTION_flush = 15;
  
  static final int TRANSACTION_getCameraInfo = 13;
  
  static final int TRANSACTION_getGlobalAudioRestriction = 22;
  
  static final int TRANSACTION_getInputSurface = 11;
  
  static final int TRANSACTION_isSessionConfigurationSupported = 7;
  
  static final int TRANSACTION_prepare = 16;
  
  static final int TRANSACTION_prepare2 = 18;
  
  static final int TRANSACTION_setCameraAudioRestriction = 21;
  
  static final int TRANSACTION_submitRequest = 2;
  
  static final int TRANSACTION_submitRequestList = 3;
  
  static final int TRANSACTION_switchToOffline = 23;
  
  static final int TRANSACTION_tearDown = 17;
  
  static final int TRANSACTION_updateOutputConfiguration = 19;
  
  static final int TRANSACTION_waitUntilIdle = 14;
  
  public Stub() {
    attachInterface(this, "android.hardware.camera2.ICameraDeviceUser");
  }
  
  public static ICameraDeviceUser asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.camera2.ICameraDeviceUser");
    return (iInterface != null && iInterface instanceof ICameraDeviceUser) ? (ICameraDeviceUser)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICameraDeviceUser getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 23:
        return "switchToOffline";
      case 22:
        return "getGlobalAudioRestriction";
      case 21:
        return "setCameraAudioRestriction";
      case 20:
        return "finalizeOutputConfigurations";
      case 19:
        return "updateOutputConfiguration";
      case 18:
        return "prepare2";
      case 17:
        return "tearDown";
      case 16:
        return "prepare";
      case 15:
        return "flush";
      case 14:
        return "waitUntilIdle";
      case 13:
        return "getCameraInfo";
      case 12:
        return "createDefaultRequest";
      case 11:
        return "getInputSurface";
      case 10:
        return "createInputStream";
      case 9:
        return "createStream";
      case 8:
        return "deleteStream";
      case 7:
        return "isSessionConfigurationSupported";
      case 6:
        return "endConfigure";
      case 5:
        return "beginConfigure";
      case 4:
        return "cancelRequest";
      case 3:
        return "submitRequestList";
      case 2:
        return "submitRequest";
      case 1:
        break;
    } 
    return "disconnect";
  }
  
  public static boolean setDefaultImpl(ICameraDeviceUser paramICameraDeviceUser) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICameraDeviceUser != null) {
        Proxy.sDefaultImpl = paramICameraDeviceUser;
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
      boolean bool;
      int i;
      ICameraOfflineSession iCameraOfflineSession;
      CameraMetadataNative cameraMetadataNative;
      Surface surface;
      int[] arrayOfInt;
      SubmitInfo submitInfo;
      long l;
      CaptureRequest[] arrayOfCaptureRequest;
      boolean bool1;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 23:
          paramParcel1.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          iCameraOfflineSession = switchToOffline(ICameraDeviceCallbacks.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.createIntArray());
          paramParcel2.writeNoException();
          if (iCameraOfflineSession != null) {
            IBinder iBinder = iCameraOfflineSession.asBinder();
          } else {
            iCameraOfflineSession = null;
          } 
          paramParcel2.writeStrongBinder((IBinder)iCameraOfflineSession);
          return true;
        case 22:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          paramInt1 = getGlobalAudioRestriction();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 21:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          setCameraAudioRestriction(iCameraOfflineSession.readInt());
          paramParcel2.writeNoException();
          return true;
        case 20:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          paramInt1 = iCameraOfflineSession.readInt();
          if (iCameraOfflineSession.readInt() != 0) {
            OutputConfiguration outputConfiguration = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel((Parcel)iCameraOfflineSession);
          } else {
            iCameraOfflineSession = null;
          } 
          finalizeOutputConfigurations(paramInt1, (OutputConfiguration)iCameraOfflineSession);
          paramParcel2.writeNoException();
          return true;
        case 19:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          paramInt1 = iCameraOfflineSession.readInt();
          if (iCameraOfflineSession.readInt() != 0) {
            OutputConfiguration outputConfiguration = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel((Parcel)iCameraOfflineSession);
          } else {
            iCameraOfflineSession = null;
          } 
          updateOutputConfiguration(paramInt1, (OutputConfiguration)iCameraOfflineSession);
          paramParcel2.writeNoException();
          return true;
        case 18:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          prepare2(iCameraOfflineSession.readInt(), iCameraOfflineSession.readInt());
          paramParcel2.writeNoException();
          return true;
        case 17:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          tearDown(iCameraOfflineSession.readInt());
          paramParcel2.writeNoException();
          return true;
        case 16:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          prepare(iCameraOfflineSession.readInt());
          paramParcel2.writeNoException();
          return true;
        case 15:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          l = flush();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 14:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          waitUntilIdle();
          paramParcel2.writeNoException();
          return true;
        case 13:
          iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          cameraMetadataNative = getCameraInfo();
          paramParcel2.writeNoException();
          if (cameraMetadataNative != null) {
            paramParcel2.writeInt(1);
            cameraMetadataNative.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 12:
          cameraMetadataNative.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          cameraMetadataNative = createDefaultRequest(cameraMetadataNative.readInt());
          paramParcel2.writeNoException();
          if (cameraMetadataNative != null) {
            paramParcel2.writeInt(1);
            cameraMetadataNative.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 11:
          cameraMetadataNative.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          surface = getInputSurface();
          paramParcel2.writeNoException();
          if (surface != null) {
            paramParcel2.writeInt(1);
            surface.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 10:
          surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          paramInt1 = createInputStream(surface.readInt(), surface.readInt(), surface.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 9:
          surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          if (surface.readInt() != 0) {
            OutputConfiguration outputConfiguration = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel((Parcel)surface);
          } else {
            surface = null;
          } 
          paramInt1 = createStream((OutputConfiguration)surface);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 8:
          surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          deleteStream(surface.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          if (surface.readInt() != 0) {
            SessionConfiguration sessionConfiguration = (SessionConfiguration)SessionConfiguration.CREATOR.createFromParcel((Parcel)surface);
          } else {
            surface = null;
          } 
          bool = isSessionConfigurationSupported((SessionConfiguration)surface);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          i = surface.readInt();
          if (surface.readInt() != 0) {
            CameraMetadataNative cameraMetadataNative1 = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel((Parcel)surface);
          } else {
            surface = null;
          } 
          arrayOfInt = endConfigure(i, (CameraMetadataNative)surface);
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt);
          return true;
        case 5:
          arrayOfInt.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          beginConfigure();
          paramParcel2.writeNoException();
          return true;
        case 4:
          arrayOfInt.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          l = cancelRequest(arrayOfInt.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 3:
          arrayOfInt.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          arrayOfCaptureRequest = (CaptureRequest[])arrayOfInt.createTypedArray(CaptureRequest.CREATOR);
          if (arrayOfInt.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          submitInfo = submitRequestList(arrayOfCaptureRequest, bool1);
          paramParcel2.writeNoException();
          if (submitInfo != null) {
            paramParcel2.writeInt(1);
            submitInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          submitInfo.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
          if (submitInfo.readInt() != 0) {
            CaptureRequest captureRequest = (CaptureRequest)CaptureRequest.CREATOR.createFromParcel((Parcel)submitInfo);
          } else {
            arrayOfCaptureRequest = null;
          } 
          if (submitInfo.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          submitInfo = submitRequest((CaptureRequest)arrayOfCaptureRequest, bool1);
          paramParcel2.writeNoException();
          if (submitInfo != null) {
            paramParcel2.writeInt(1);
            submitInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 1:
          break;
      } 
      submitInfo.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
      disconnect();
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.camera2.ICameraDeviceUser");
    return true;
  }
  
  private static class Proxy implements ICameraDeviceUser {
    public static ICameraDeviceUser sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void beginConfigure() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().beginConfigure();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long cancelRequest(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().cancelRequest(param2Int); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CameraMetadataNative createDefaultRequest(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CameraMetadataNative cameraMetadataNative;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          cameraMetadataNative = ICameraDeviceUser.Stub.getDefaultImpl().createDefaultRequest(param2Int);
          return cameraMetadataNative;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
        } else {
          cameraMetadataNative = null;
        } 
        return cameraMetadataNative;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createInputStream(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          param2Int1 = ICameraDeviceUser.Stub.getDefaultImpl().createInputStream(param2Int1, param2Int2, param2Int3);
          return param2Int1;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        return param2Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createStream(OutputConfiguration param2OutputConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (param2OutputConfiguration != null) {
          parcel1.writeInt(1);
          param2OutputConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().createStream(param2OutputConfiguration); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteStream(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().deleteStream(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disconnect() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().disconnect();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] endConfigure(int param2Int, CameraMetadataNative param2CameraMetadataNative) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (param2CameraMetadataNative != null) {
          parcel1.writeInt(1);
          param2CameraMetadataNative.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().endConfigure(param2Int, param2CameraMetadataNative); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finalizeOutputConfigurations(int param2Int, OutputConfiguration param2OutputConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (param2OutputConfiguration != null) {
          parcel1.writeInt(1);
          param2OutputConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().finalizeOutputConfigurations(param2Int, param2OutputConfiguration);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long flush() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().flush(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CameraMetadataNative getCameraInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CameraMetadataNative cameraMetadataNative;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          cameraMetadataNative = ICameraDeviceUser.Stub.getDefaultImpl().getCameraInfo();
          return cameraMetadataNative;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
        } else {
          cameraMetadataNative = null;
        } 
        return cameraMetadataNative;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getGlobalAudioRestriction() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().getGlobalAudioRestriction(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Surface getInputSurface() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Surface surface;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          surface = ICameraDeviceUser.Stub.getDefaultImpl().getInputSurface();
          return surface;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          surface = (Surface)Surface.CREATOR.createFromParcel(parcel2);
        } else {
          surface = null;
        } 
        return surface;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.camera2.ICameraDeviceUser";
    }
    
    public boolean isSessionConfigurationSupported(SessionConfiguration param2SessionConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        boolean bool = true;
        if (param2SessionConfiguration != null) {
          parcel1.writeInt(1);
          param2SessionConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          bool = ICameraDeviceUser.Stub.getDefaultImpl().isSessionConfigurationSupported(param2SessionConfiguration);
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
    
    public void prepare(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().prepare(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void prepare2(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().prepare2(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCameraAudioRestriction(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().setCameraAudioRestriction(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SubmitInfo submitRequest(CaptureRequest param2CaptureRequest, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        boolean bool = true;
        if (param2CaptureRequest != null) {
          parcel1.writeInt(1);
          param2CaptureRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().submitRequest(param2CaptureRequest, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SubmitInfo submitInfo = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2CaptureRequest = null;
        } 
        return (SubmitInfo)param2CaptureRequest;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SubmitInfo submitRequestList(CaptureRequest[] param2ArrayOfCaptureRequest, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeTypedArray((Parcelable[])param2ArrayOfCaptureRequest, 0);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().submitRequestList(param2ArrayOfCaptureRequest, param2Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SubmitInfo submitInfo = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2ArrayOfCaptureRequest = null;
        } 
        return (SubmitInfo)param2ArrayOfCaptureRequest;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks param2ICameraDeviceCallbacks, int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (param2ICameraDeviceCallbacks != null) {
          iBinder = param2ICameraDeviceCallbacks.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().switchToOffline(param2ICameraDeviceCallbacks, param2ArrayOfint); 
        parcel2.readException();
        return ICameraOfflineSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void tearDown(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().tearDown(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateOutputConfiguration(int param2Int, OutputConfiguration param2OutputConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param2Int);
        if (param2OutputConfiguration != null) {
          parcel1.writeInt(1);
          param2OutputConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().updateOutputConfiguration(param2Int, param2OutputConfiguration);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void waitUntilIdle() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().waitUntilIdle();
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceUser$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */