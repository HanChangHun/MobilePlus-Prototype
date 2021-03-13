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

public interface ICameraDeviceUser extends IInterface {
  public static final int AUDIO_RESTRICTION_NONE = 0;
  
  public static final int AUDIO_RESTRICTION_VIBRATION = 1;
  
  public static final int AUDIO_RESTRICTION_VIBRATION_SOUND = 3;
  
  public static final int CONSTRAINED_HIGH_SPEED_MODE = 1;
  
  public static final int NORMAL_MODE = 0;
  
  public static final int NO_IN_FLIGHT_REPEATING_FRAMES = -1;
  
  public static final int TEMPLATE_MANUAL = 6;
  
  public static final int TEMPLATE_PREVIEW = 1;
  
  public static final int TEMPLATE_RECORD = 3;
  
  public static final int TEMPLATE_STILL_CAPTURE = 2;
  
  public static final int TEMPLATE_VIDEO_SNAPSHOT = 4;
  
  public static final int TEMPLATE_ZERO_SHUTTER_LAG = 5;
  
  public static final int VENDOR_MODE_START = 32768;
  
  void beginConfigure() throws RemoteException;
  
  long cancelRequest(int paramInt) throws RemoteException;
  
  CameraMetadataNative createDefaultRequest(int paramInt) throws RemoteException;
  
  int createInputStream(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  int createStream(OutputConfiguration paramOutputConfiguration) throws RemoteException;
  
  void deleteStream(int paramInt) throws RemoteException;
  
  void disconnect() throws RemoteException;
  
  int[] endConfigure(int paramInt, CameraMetadataNative paramCameraMetadataNative) throws RemoteException;
  
  void finalizeOutputConfigurations(int paramInt, OutputConfiguration paramOutputConfiguration) throws RemoteException;
  
  long flush() throws RemoteException;
  
  CameraMetadataNative getCameraInfo() throws RemoteException;
  
  int getGlobalAudioRestriction() throws RemoteException;
  
  Surface getInputSurface() throws RemoteException;
  
  boolean isSessionConfigurationSupported(SessionConfiguration paramSessionConfiguration) throws RemoteException;
  
  void prepare(int paramInt) throws RemoteException;
  
  void prepare2(int paramInt1, int paramInt2) throws RemoteException;
  
  void setCameraAudioRestriction(int paramInt) throws RemoteException;
  
  SubmitInfo submitRequest(CaptureRequest paramCaptureRequest, boolean paramBoolean) throws RemoteException;
  
  SubmitInfo submitRequestList(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) throws RemoteException;
  
  ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks paramICameraDeviceCallbacks, int[] paramArrayOfint) throws RemoteException;
  
  void tearDown(int paramInt) throws RemoteException;
  
  void updateOutputConfiguration(int paramInt, OutputConfiguration paramOutputConfiguration) throws RemoteException;
  
  void waitUntilIdle() throws RemoteException;
  
  public static class Default implements ICameraDeviceUser {
    public IBinder asBinder() {
      return null;
    }
    
    public void beginConfigure() throws RemoteException {}
    
    public long cancelRequest(int param1Int) throws RemoteException {
      return 0L;
    }
    
    public CameraMetadataNative createDefaultRequest(int param1Int) throws RemoteException {
      return null;
    }
    
    public int createInputStream(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int createStream(OutputConfiguration param1OutputConfiguration) throws RemoteException {
      return 0;
    }
    
    public void deleteStream(int param1Int) throws RemoteException {}
    
    public void disconnect() throws RemoteException {}
    
    public int[] endConfigure(int param1Int, CameraMetadataNative param1CameraMetadataNative) throws RemoteException {
      return null;
    }
    
    public void finalizeOutputConfigurations(int param1Int, OutputConfiguration param1OutputConfiguration) throws RemoteException {}
    
    public long flush() throws RemoteException {
      return 0L;
    }
    
    public CameraMetadataNative getCameraInfo() throws RemoteException {
      return null;
    }
    
    public int getGlobalAudioRestriction() throws RemoteException {
      return 0;
    }
    
    public Surface getInputSurface() throws RemoteException {
      return null;
    }
    
    public boolean isSessionConfigurationSupported(SessionConfiguration param1SessionConfiguration) throws RemoteException {
      return false;
    }
    
    public void prepare(int param1Int) throws RemoteException {}
    
    public void prepare2(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void setCameraAudioRestriction(int param1Int) throws RemoteException {}
    
    public SubmitInfo submitRequest(CaptureRequest param1CaptureRequest, boolean param1Boolean) throws RemoteException {
      return null;
    }
    
    public SubmitInfo submitRequestList(CaptureRequest[] param1ArrayOfCaptureRequest, boolean param1Boolean) throws RemoteException {
      return null;
    }
    
    public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks param1ICameraDeviceCallbacks, int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public void tearDown(int param1Int) throws RemoteException {}
    
    public void updateOutputConfiguration(int param1Int, OutputConfiguration param1OutputConfiguration) throws RemoteException {}
    
    public void waitUntilIdle() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICameraDeviceUser {
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
    
    public static ICameraDeviceUser asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.camera2.ICameraDeviceUser");
      return (iInterface != null && iInterface instanceof ICameraDeviceUser) ? (ICameraDeviceUser)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraDeviceUser getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(ICameraDeviceUser param1ICameraDeviceUser) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraDeviceUser != null) {
          Proxy.sDefaultImpl = param1ICameraDeviceUser;
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
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 23:
            param1Parcel1.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            iCameraOfflineSession = switchToOffline(ICameraDeviceCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.createIntArray());
            param1Parcel2.writeNoException();
            if (iCameraOfflineSession != null) {
              IBinder iBinder = iCameraOfflineSession.asBinder();
            } else {
              iCameraOfflineSession = null;
            } 
            param1Parcel2.writeStrongBinder((IBinder)iCameraOfflineSession);
            return true;
          case 22:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            param1Int1 = getGlobalAudioRestriction();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 21:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            setCameraAudioRestriction(iCameraOfflineSession.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 20:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            param1Int1 = iCameraOfflineSession.readInt();
            if (iCameraOfflineSession.readInt() != 0) {
              OutputConfiguration outputConfiguration = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel((Parcel)iCameraOfflineSession);
            } else {
              iCameraOfflineSession = null;
            } 
            finalizeOutputConfigurations(param1Int1, (OutputConfiguration)iCameraOfflineSession);
            param1Parcel2.writeNoException();
            return true;
          case 19:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            param1Int1 = iCameraOfflineSession.readInt();
            if (iCameraOfflineSession.readInt() != 0) {
              OutputConfiguration outputConfiguration = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel((Parcel)iCameraOfflineSession);
            } else {
              iCameraOfflineSession = null;
            } 
            updateOutputConfiguration(param1Int1, (OutputConfiguration)iCameraOfflineSession);
            param1Parcel2.writeNoException();
            return true;
          case 18:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            prepare2(iCameraOfflineSession.readInt(), iCameraOfflineSession.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 17:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            tearDown(iCameraOfflineSession.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 16:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            prepare(iCameraOfflineSession.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 15:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            l = flush();
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l);
            return true;
          case 14:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            waitUntilIdle();
            param1Parcel2.writeNoException();
            return true;
          case 13:
            iCameraOfflineSession.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            cameraMetadataNative = getCameraInfo();
            param1Parcel2.writeNoException();
            if (cameraMetadataNative != null) {
              param1Parcel2.writeInt(1);
              cameraMetadataNative.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 12:
            cameraMetadataNative.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            cameraMetadataNative = createDefaultRequest(cameraMetadataNative.readInt());
            param1Parcel2.writeNoException();
            if (cameraMetadataNative != null) {
              param1Parcel2.writeInt(1);
              cameraMetadataNative.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 11:
            cameraMetadataNative.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            surface = getInputSurface();
            param1Parcel2.writeNoException();
            if (surface != null) {
              param1Parcel2.writeInt(1);
              surface.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 10:
            surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            param1Int1 = createInputStream(surface.readInt(), surface.readInt(), surface.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 9:
            surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            if (surface.readInt() != 0) {
              OutputConfiguration outputConfiguration = (OutputConfiguration)OutputConfiguration.CREATOR.createFromParcel((Parcel)surface);
            } else {
              surface = null;
            } 
            param1Int1 = createStream((OutputConfiguration)surface);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 8:
            surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            deleteStream(surface.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 7:
            surface.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            if (surface.readInt() != 0) {
              SessionConfiguration sessionConfiguration = (SessionConfiguration)SessionConfiguration.CREATOR.createFromParcel((Parcel)surface);
            } else {
              surface = null;
            } 
            bool = isSessionConfigurationSupported((SessionConfiguration)surface);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
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
            param1Parcel2.writeNoException();
            param1Parcel2.writeIntArray(arrayOfInt);
            return true;
          case 5:
            arrayOfInt.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            beginConfigure();
            param1Parcel2.writeNoException();
            return true;
          case 4:
            arrayOfInt.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
            l = cancelRequest(arrayOfInt.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeLong(l);
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
            param1Parcel2.writeNoException();
            if (submitInfo != null) {
              param1Parcel2.writeInt(1);
              submitInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
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
            param1Parcel2.writeNoException();
            if (submitInfo != null) {
              param1Parcel2.writeInt(1);
              submitInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 1:
            break;
        } 
        submitInfo.enforceInterface("android.hardware.camera2.ICameraDeviceUser");
        disconnect();
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.hardware.camera2.ICameraDeviceUser");
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
  
  private static class Proxy implements ICameraDeviceUser {
    public static ICameraDeviceUser sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public long cancelRequest(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().cancelRequest(param1Int); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CameraMetadataNative createDefaultRequest(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CameraMetadataNative cameraMetadataNative;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          cameraMetadataNative = ICameraDeviceUser.Stub.getDefaultImpl().createDefaultRequest(param1Int);
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
    
    public int createInputStream(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          param1Int1 = ICameraDeviceUser.Stub.getDefaultImpl().createInputStream(param1Int1, param1Int2, param1Int3);
          return param1Int1;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        return param1Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createStream(OutputConfiguration param1OutputConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (param1OutputConfiguration != null) {
          parcel1.writeInt(1);
          param1OutputConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().createStream(param1OutputConfiguration); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteStream(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().deleteStream(param1Int);
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
    
    public int[] endConfigure(int param1Int, CameraMetadataNative param1CameraMetadataNative) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (param1CameraMetadataNative != null) {
          parcel1.writeInt(1);
          param1CameraMetadataNative.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().endConfigure(param1Int, param1CameraMetadataNative); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finalizeOutputConfigurations(int param1Int, OutputConfiguration param1OutputConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (param1OutputConfiguration != null) {
          parcel1.writeInt(1);
          param1OutputConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().finalizeOutputConfigurations(param1Int, param1OutputConfiguration);
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
    
    public boolean isSessionConfigurationSupported(SessionConfiguration param1SessionConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        boolean bool = true;
        if (param1SessionConfiguration != null) {
          parcel1.writeInt(1);
          param1SessionConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          bool = ICameraDeviceUser.Stub.getDefaultImpl().isSessionConfigurationSupported(param1SessionConfiguration);
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
    
    public void prepare(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().prepare(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void prepare2(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().prepare2(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCameraAudioRestriction(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().setCameraAudioRestriction(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SubmitInfo submitRequest(CaptureRequest param1CaptureRequest, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        boolean bool = true;
        if (param1CaptureRequest != null) {
          parcel1.writeInt(1);
          param1CaptureRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param1Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().submitRequest(param1CaptureRequest, param1Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SubmitInfo submitInfo = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param1CaptureRequest = null;
        } 
        return (SubmitInfo)param1CaptureRequest;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public SubmitInfo submitRequestList(CaptureRequest[] param1ArrayOfCaptureRequest, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeTypedArray((Parcelable[])param1ArrayOfCaptureRequest, 0);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().submitRequestList(param1ArrayOfCaptureRequest, param1Boolean); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SubmitInfo submitInfo = (SubmitInfo)SubmitInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param1ArrayOfCaptureRequest = null;
        } 
        return (SubmitInfo)param1ArrayOfCaptureRequest;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks param1ICameraDeviceCallbacks, int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        if (param1ICameraDeviceCallbacks != null) {
          iBinder = param1ICameraDeviceCallbacks.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null)
          return ICameraDeviceUser.Stub.getDefaultImpl().switchToOffline(param1ICameraDeviceCallbacks, param1ArrayOfint); 
        parcel2.readException();
        return ICameraOfflineSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void tearDown(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().tearDown(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateOutputConfiguration(int param1Int, OutputConfiguration param1OutputConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraDeviceUser");
        parcel1.writeInt(param1Int);
        if (param1OutputConfiguration != null) {
          parcel1.writeInt(1);
          param1OutputConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ICameraDeviceUser.Stub.getDefaultImpl() != null) {
          ICameraDeviceUser.Stub.getDefaultImpl().updateOutputConfiguration(param1Int, param1OutputConfiguration);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraDeviceUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */