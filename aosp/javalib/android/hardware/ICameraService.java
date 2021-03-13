package android.hardware;

import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.VendorTagDescriptor;
import android.hardware.camera2.params.VendorTagDescriptorCache;
import android.hardware.camera2.utils.CameraIdAndSessionConfiguration;
import android.hardware.camera2.utils.ConcurrentCameraIdCombination;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface ICameraService extends IInterface {
  public static final int API_VERSION_1 = 1;
  
  public static final int API_VERSION_2 = 2;
  
  public static final int CAMERA_HAL_API_VERSION_UNSPECIFIED = -1;
  
  public static final int CAMERA_TYPE_ALL = 1;
  
  public static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
  
  public static final int DEVICE_STATE_BACK_COVERED = 1;
  
  public static final int DEVICE_STATE_FOLDED = 4;
  
  public static final int DEVICE_STATE_FRONT_COVERED = 2;
  
  public static final int DEVICE_STATE_LAST_FRAMEWORK_BIT = -2147483648;
  
  public static final int DEVICE_STATE_NORMAL = 0;
  
  public static final int ERROR_ALREADY_EXISTS = 2;
  
  public static final int ERROR_CAMERA_IN_USE = 7;
  
  public static final int ERROR_DEPRECATED_HAL = 9;
  
  public static final int ERROR_DISABLED = 6;
  
  public static final int ERROR_DISCONNECTED = 4;
  
  public static final int ERROR_ILLEGAL_ARGUMENT = 3;
  
  public static final int ERROR_INVALID_OPERATION = 10;
  
  public static final int ERROR_MAX_CAMERAS_IN_USE = 8;
  
  public static final int ERROR_PERMISSION_DENIED = 1;
  
  public static final int ERROR_TIMED_OUT = 5;
  
  public static final int EVENT_NONE = 0;
  
  public static final int EVENT_USER_SWITCHED = 1;
  
  public static final int USE_CALLING_PID = -1;
  
  public static final int USE_CALLING_UID = -1;
  
  CameraStatus[] addListener(ICameraServiceListener paramICameraServiceListener) throws RemoteException;
  
  ICamera connect(ICameraClient paramICameraClient, int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException;
  
  ICameraDeviceUser connectDevice(ICameraDeviceCallbacks paramICameraDeviceCallbacks, String paramString1, String paramString2, String paramString3, int paramInt) throws RemoteException;
  
  ICamera connectLegacy(ICameraClient paramICameraClient, int paramInt1, int paramInt2, String paramString, int paramInt3) throws RemoteException;
  
  CameraMetadataNative getCameraCharacteristics(String paramString) throws RemoteException;
  
  CameraInfo getCameraInfo(int paramInt) throws RemoteException;
  
  VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException;
  
  VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException;
  
  ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException;
  
  String getLegacyParameters(int paramInt) throws RemoteException;
  
  int getNumberOfCameras(int paramInt) throws RemoteException;
  
  boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] paramArrayOfCameraIdAndSessionConfiguration) throws RemoteException;
  
  boolean isHiddenPhysicalCamera(String paramString) throws RemoteException;
  
  void notifyDeviceStateChange(long paramLong) throws RemoteException;
  
  void notifySystemEvent(int paramInt, int[] paramArrayOfint) throws RemoteException;
  
  void removeListener(ICameraServiceListener paramICameraServiceListener) throws RemoteException;
  
  void setTorchMode(String paramString, boolean paramBoolean, IBinder paramIBinder) throws RemoteException;
  
  boolean supportsCameraApi(String paramString, int paramInt) throws RemoteException;
  
  public static class Default implements ICameraService {
    public CameraStatus[] addListener(ICameraServiceListener param1ICameraServiceListener) throws RemoteException {
      return null;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public ICamera connect(ICameraClient param1ICameraClient, int param1Int1, String param1String, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks param1ICameraDeviceCallbacks, String param1String1, String param1String2, String param1String3, int param1Int) throws RemoteException {
      return null;
    }
    
    public ICamera connectLegacy(ICameraClient param1ICameraClient, int param1Int1, int param1Int2, String param1String, int param1Int3) throws RemoteException {
      return null;
    }
    
    public CameraMetadataNative getCameraCharacteristics(String param1String) throws RemoteException {
      return null;
    }
    
    public CameraInfo getCameraInfo(int param1Int) throws RemoteException {
      return null;
    }
    
    public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
      return null;
    }
    
    public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
      return null;
    }
    
    public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
      return null;
    }
    
    public String getLegacyParameters(int param1Int) throws RemoteException {
      return null;
    }
    
    public int getNumberOfCameras(int param1Int) throws RemoteException {
      return 0;
    }
    
    public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] param1ArrayOfCameraIdAndSessionConfiguration) throws RemoteException {
      return false;
    }
    
    public boolean isHiddenPhysicalCamera(String param1String) throws RemoteException {
      return false;
    }
    
    public void notifyDeviceStateChange(long param1Long) throws RemoteException {}
    
    public void notifySystemEvent(int param1Int, int[] param1ArrayOfint) throws RemoteException {}
    
    public void removeListener(ICameraServiceListener param1ICameraServiceListener) throws RemoteException {}
    
    public void setTorchMode(String param1String, boolean param1Boolean, IBinder param1IBinder) throws RemoteException {}
    
    public boolean supportsCameraApi(String param1String, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements ICameraService {
    private static final String DESCRIPTOR = "android.hardware.ICameraService";
    
    static final int TRANSACTION_addListener = 6;
    
    static final int TRANSACTION_connect = 3;
    
    static final int TRANSACTION_connectDevice = 4;
    
    static final int TRANSACTION_connectLegacy = 5;
    
    static final int TRANSACTION_getCameraCharacteristics = 10;
    
    static final int TRANSACTION_getCameraInfo = 2;
    
    static final int TRANSACTION_getCameraVendorTagCache = 12;
    
    static final int TRANSACTION_getCameraVendorTagDescriptor = 11;
    
    static final int TRANSACTION_getConcurrentCameraIds = 7;
    
    static final int TRANSACTION_getLegacyParameters = 13;
    
    static final int TRANSACTION_getNumberOfCameras = 1;
    
    static final int TRANSACTION_isConcurrentSessionConfigurationSupported = 8;
    
    static final int TRANSACTION_isHiddenPhysicalCamera = 15;
    
    static final int TRANSACTION_notifyDeviceStateChange = 18;
    
    static final int TRANSACTION_notifySystemEvent = 17;
    
    static final int TRANSACTION_removeListener = 9;
    
    static final int TRANSACTION_setTorchMode = 16;
    
    static final int TRANSACTION_supportsCameraApi = 14;
    
    public Stub() {
      attachInterface(this, "android.hardware.ICameraService");
    }
    
    public static ICameraService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ICameraService");
      return (iInterface != null && iInterface instanceof ICameraService) ? (ICameraService)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 18:
          return "notifyDeviceStateChange";
        case 17:
          return "notifySystemEvent";
        case 16:
          return "setTorchMode";
        case 15:
          return "isHiddenPhysicalCamera";
        case 14:
          return "supportsCameraApi";
        case 13:
          return "getLegacyParameters";
        case 12:
          return "getCameraVendorTagCache";
        case 11:
          return "getCameraVendorTagDescriptor";
        case 10:
          return "getCameraCharacteristics";
        case 9:
          return "removeListener";
        case 8:
          return "isConcurrentSessionConfigurationSupported";
        case 7:
          return "getConcurrentCameraIds";
        case 6:
          return "addListener";
        case 5:
          return "connectLegacy";
        case 4:
          return "connectDevice";
        case 3:
          return "connect";
        case 2:
          return "getCameraInfo";
        case 1:
          break;
      } 
      return "getNumberOfCameras";
    }
    
    public static boolean setDefaultImpl(ICameraService param1ICameraService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraService != null) {
          Proxy.sDefaultImpl = param1ICameraService;
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
        String str2;
        VendorTagDescriptorCache vendorTagDescriptorCache;
        VendorTagDescriptor vendorTagDescriptor;
        CameraMetadataNative cameraMetadataNative;
        ConcurrentCameraIdCombination[] arrayOfConcurrentCameraIdCombination;
        CameraStatus[] arrayOfCameraStatus;
        String str1;
        IBinder iBinder2;
        ICamera iCamera1;
        IBinder iBinder1;
        CameraInfo cameraInfo;
        ICameraDeviceUser iCameraDeviceUser;
        ICamera iCamera3;
        ICamera iCamera2 = null;
        IBinder iBinder3 = null;
        String str3 = null;
        boolean bool1 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 18:
            param1Parcel1.enforceInterface("android.hardware.ICameraService");
            notifyDeviceStateChange(param1Parcel1.readLong());
            return true;
          case 17:
            param1Parcel1.enforceInterface("android.hardware.ICameraService");
            notifySystemEvent(param1Parcel1.readInt(), param1Parcel1.createIntArray());
            return true;
          case 16:
            param1Parcel1.enforceInterface("android.hardware.ICameraService");
            str3 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0)
              bool1 = true; 
            setTorchMode(str3, bool1, param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.hardware.ICameraService");
            bool = isHiddenPhysicalCamera(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.hardware.ICameraService");
            bool = supportsCameraApi(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.hardware.ICameraService");
            str2 = getLegacyParameters(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str2);
            return true;
          case 12:
            str2.enforceInterface("android.hardware.ICameraService");
            vendorTagDescriptorCache = getCameraVendorTagCache();
            param1Parcel2.writeNoException();
            if (vendorTagDescriptorCache != null) {
              param1Parcel2.writeInt(1);
              vendorTagDescriptorCache.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 11:
            vendorTagDescriptorCache.enforceInterface("android.hardware.ICameraService");
            vendorTagDescriptor = getCameraVendorTagDescriptor();
            param1Parcel2.writeNoException();
            if (vendorTagDescriptor != null) {
              param1Parcel2.writeInt(1);
              vendorTagDescriptor.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 10:
            vendorTagDescriptor.enforceInterface("android.hardware.ICameraService");
            cameraMetadataNative = getCameraCharacteristics(vendorTagDescriptor.readString());
            param1Parcel2.writeNoException();
            if (cameraMetadataNative != null) {
              param1Parcel2.writeInt(1);
              cameraMetadataNative.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 9:
            cameraMetadataNative.enforceInterface("android.hardware.ICameraService");
            removeListener(ICameraServiceListener.Stub.asInterface(cameraMetadataNative.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 8:
            cameraMetadataNative.enforceInterface("android.hardware.ICameraService");
            bool = isConcurrentSessionConfigurationSupported((CameraIdAndSessionConfiguration[])cameraMetadataNative.createTypedArray(CameraIdAndSessionConfiguration.CREATOR));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 7:
            cameraMetadataNative.enforceInterface("android.hardware.ICameraService");
            arrayOfConcurrentCameraIdCombination = getConcurrentCameraIds();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedArray((Parcelable[])arrayOfConcurrentCameraIdCombination, 1);
            return true;
          case 6:
            arrayOfConcurrentCameraIdCombination.enforceInterface("android.hardware.ICameraService");
            arrayOfCameraStatus = addListener(ICameraServiceListener.Stub.asInterface(arrayOfConcurrentCameraIdCombination.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedArray((Parcelable[])arrayOfCameraStatus, 1);
            return true;
          case 5:
            arrayOfCameraStatus.enforceInterface("android.hardware.ICameraService");
            iCamera2 = connectLegacy(ICameraClient.Stub.asInterface(arrayOfCameraStatus.readStrongBinder()), arrayOfCameraStatus.readInt(), arrayOfCameraStatus.readInt(), arrayOfCameraStatus.readString(), arrayOfCameraStatus.readInt());
            param1Parcel2.writeNoException();
            str1 = str3;
            if (iCamera2 != null)
              iBinder2 = iCamera2.asBinder(); 
            param1Parcel2.writeStrongBinder(iBinder2);
            return true;
          case 4:
            iBinder2.enforceInterface("android.hardware.ICameraService");
            iCameraDeviceUser = connectDevice(ICameraDeviceCallbacks.Stub.asInterface(iBinder2.readStrongBinder()), iBinder2.readString(), iBinder2.readString(), iBinder2.readString(), iBinder2.readInt());
            param1Parcel2.writeNoException();
            iCamera1 = iCamera2;
            if (iCameraDeviceUser != null)
              iBinder1 = iCameraDeviceUser.asBinder(); 
            param1Parcel2.writeStrongBinder(iBinder1);
            return true;
          case 3:
            iBinder1.enforceInterface("android.hardware.ICameraService");
            iCamera3 = connect(ICameraClient.Stub.asInterface(iBinder1.readStrongBinder()), iBinder1.readInt(), iBinder1.readString(), iBinder1.readInt(), iBinder1.readInt());
            param1Parcel2.writeNoException();
            iBinder1 = iBinder3;
            if (iCamera3 != null)
              iBinder1 = iCamera3.asBinder(); 
            param1Parcel2.writeStrongBinder(iBinder1);
            return true;
          case 2:
            iBinder1.enforceInterface("android.hardware.ICameraService");
            cameraInfo = getCameraInfo(iBinder1.readInt());
            param1Parcel2.writeNoException();
            if (cameraInfo != null) {
              param1Parcel2.writeInt(1);
              cameraInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 1:
            break;
        } 
        cameraInfo.enforceInterface("android.hardware.ICameraService");
        int i = getNumberOfCameras(cameraInfo.readInt());
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(i);
        return true;
      } 
      param1Parcel2.writeString("android.hardware.ICameraService");
      return true;
    }
    
    private static class Proxy implements ICameraService {
      public static ICameraService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public CameraStatus[] addListener(ICameraServiceListener param2ICameraServiceListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (param2ICameraServiceListener != null) {
            iBinder = param2ICameraServiceListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().addListener(param2ICameraServiceListener); 
          parcel2.readException();
          return (CameraStatus[])parcel2.createTypedArray(CameraStatus.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public ICamera connect(ICameraClient param2ICameraClient, int param2Int1, String param2String, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (param2ICameraClient != null) {
            iBinder = param2ICameraClient.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().connect(param2ICameraClient, param2Int1, param2String, param2Int2, param2Int3); 
          parcel2.readException();
          return ICamera.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks param2ICameraDeviceCallbacks, String param2String1, String param2String2, String param2String3, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (param2ICameraDeviceCallbacks != null) {
            iBinder = param2ICameraDeviceCallbacks.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeString(param2String3);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().connectDevice(param2ICameraDeviceCallbacks, param2String1, param2String2, param2String3, param2Int); 
          parcel2.readException();
          return ICameraDeviceUser.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ICamera connectLegacy(ICameraClient param2ICameraClient, int param2Int1, int param2Int2, String param2String, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (param2ICameraClient != null) {
            iBinder = param2ICameraClient.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().connectLegacy(param2ICameraClient, param2Int1, param2Int2, param2String, param2Int3); 
          parcel2.readException();
          return ICamera.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public CameraMetadataNative getCameraCharacteristics(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().getCameraCharacteristics(param2String); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            CameraMetadataNative cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (CameraMetadataNative)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public CameraInfo getCameraInfo(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          CameraInfo cameraInfo;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            cameraInfo = ICameraService.Stub.getDefaultImpl().getCameraInfo(param2Int);
            return cameraInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            cameraInfo = (CameraInfo)CameraInfo.CREATOR.createFromParcel(parcel2);
          } else {
            cameraInfo = null;
          } 
          return cameraInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          VendorTagDescriptorCache vendorTagDescriptorCache;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            vendorTagDescriptorCache = ICameraService.Stub.getDefaultImpl().getCameraVendorTagCache();
            return vendorTagDescriptorCache;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            vendorTagDescriptorCache = (VendorTagDescriptorCache)VendorTagDescriptorCache.CREATOR.createFromParcel(parcel2);
          } else {
            vendorTagDescriptorCache = null;
          } 
          return vendorTagDescriptorCache;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          VendorTagDescriptor vendorTagDescriptor;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            vendorTagDescriptor = ICameraService.Stub.getDefaultImpl().getCameraVendorTagDescriptor();
            return vendorTagDescriptor;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            vendorTagDescriptor = (VendorTagDescriptor)VendorTagDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            vendorTagDescriptor = null;
          } 
          return vendorTagDescriptor;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().getConcurrentCameraIds(); 
          parcel2.readException();
          return (ConcurrentCameraIdCombination[])parcel2.createTypedArray(ConcurrentCameraIdCombination.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.ICameraService";
      }
      
      public String getLegacyParameters(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
            return ICameraService.Stub.getDefaultImpl().getLegacyParameters(param2Int); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getNumberOfCameras(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            param2Int = ICameraService.Stub.getDefaultImpl().getNumberOfCameras(param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] param2ArrayOfCameraIdAndSessionConfiguration) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          boolean bool = false;
          parcel1.writeTypedArray((Parcelable[])param2ArrayOfCameraIdAndSessionConfiguration, 0);
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            bool = ICameraService.Stub.getDefaultImpl().isConcurrentSessionConfigurationSupported(param2ArrayOfCameraIdAndSessionConfiguration);
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
      
      public boolean isHiddenPhysicalCamera(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(15, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            bool = ICameraService.Stub.getDefaultImpl().isHiddenPhysicalCamera(param2String);
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
      
      public void notifyDeviceStateChange(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.ICameraService");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(18, parcel, null, 1) && ICameraService.Stub.getDefaultImpl() != null) {
            ICameraService.Stub.getDefaultImpl().notifyDeviceStateChange(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void notifySystemEvent(int param2Int, int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.ICameraService");
          parcel.writeInt(param2Int);
          parcel.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(17, parcel, null, 1) && ICameraService.Stub.getDefaultImpl() != null) {
            ICameraService.Stub.getDefaultImpl().notifySystemEvent(param2Int, param2ArrayOfint);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void removeListener(ICameraServiceListener param2ICameraServiceListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          if (param2ICameraServiceListener != null) {
            iBinder = param2ICameraServiceListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            ICameraService.Stub.getDefaultImpl().removeListener(param2ICameraServiceListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setTorchMode(String param2String, boolean param2Boolean, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeString(param2String);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            ICameraService.Stub.getDefaultImpl().setTorchMode(param2String, param2Boolean, param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean supportsCameraApi(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ICameraService");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(14, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
            bool = ICameraService.Stub.getDefaultImpl().supportsCameraApi(param2String, param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ICameraService {
    public static ICameraService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public CameraStatus[] addListener(ICameraServiceListener param1ICameraServiceListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (param1ICameraServiceListener != null) {
          iBinder = param1ICameraServiceListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().addListener(param1ICameraServiceListener); 
        parcel2.readException();
        return (CameraStatus[])parcel2.createTypedArray(CameraStatus.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public ICamera connect(ICameraClient param1ICameraClient, int param1Int1, String param1String, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (param1ICameraClient != null) {
          iBinder = param1ICameraClient.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().connect(param1ICameraClient, param1Int1, param1String, param1Int2, param1Int3); 
        parcel2.readException();
        return ICamera.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks param1ICameraDeviceCallbacks, String param1String1, String param1String2, String param1String3, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (param1ICameraDeviceCallbacks != null) {
          iBinder = param1ICameraDeviceCallbacks.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeString(param1String3);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().connectDevice(param1ICameraDeviceCallbacks, param1String1, param1String2, param1String3, param1Int); 
        parcel2.readException();
        return ICameraDeviceUser.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ICamera connectLegacy(ICameraClient param1ICameraClient, int param1Int1, int param1Int2, String param1String, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (param1ICameraClient != null) {
          iBinder = param1ICameraClient.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().connectLegacy(param1ICameraClient, param1Int1, param1Int2, param1String, param1Int3); 
        parcel2.readException();
        return ICamera.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CameraMetadataNative getCameraCharacteristics(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().getCameraCharacteristics(param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          CameraMetadataNative cameraMetadataNative = (CameraMetadataNative)CameraMetadataNative.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (CameraMetadataNative)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public CameraInfo getCameraInfo(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        CameraInfo cameraInfo;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          cameraInfo = ICameraService.Stub.getDefaultImpl().getCameraInfo(param1Int);
          return cameraInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          cameraInfo = (CameraInfo)CameraInfo.CREATOR.createFromParcel(parcel2);
        } else {
          cameraInfo = null;
        } 
        return cameraInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        VendorTagDescriptorCache vendorTagDescriptorCache;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          vendorTagDescriptorCache = ICameraService.Stub.getDefaultImpl().getCameraVendorTagCache();
          return vendorTagDescriptorCache;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          vendorTagDescriptorCache = (VendorTagDescriptorCache)VendorTagDescriptorCache.CREATOR.createFromParcel(parcel2);
        } else {
          vendorTagDescriptorCache = null;
        } 
        return vendorTagDescriptorCache;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        VendorTagDescriptor vendorTagDescriptor;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          vendorTagDescriptor = ICameraService.Stub.getDefaultImpl().getCameraVendorTagDescriptor();
          return vendorTagDescriptor;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          vendorTagDescriptor = (VendorTagDescriptor)VendorTagDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          vendorTagDescriptor = null;
        } 
        return vendorTagDescriptor;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().getConcurrentCameraIds(); 
        parcel2.readException();
        return (ConcurrentCameraIdCombination[])parcel2.createTypedArray(ConcurrentCameraIdCombination.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ICameraService";
    }
    
    public String getLegacyParameters(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null)
          return ICameraService.Stub.getDefaultImpl().getLegacyParameters(param1Int); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getNumberOfCameras(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          param1Int = ICameraService.Stub.getDefaultImpl().getNumberOfCameras(param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] param1ArrayOfCameraIdAndSessionConfiguration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        boolean bool = false;
        parcel1.writeTypedArray((Parcelable[])param1ArrayOfCameraIdAndSessionConfiguration, 0);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          bool = ICameraService.Stub.getDefaultImpl().isConcurrentSessionConfigurationSupported(param1ArrayOfCameraIdAndSessionConfiguration);
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
    
    public boolean isHiddenPhysicalCamera(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(15, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          bool = ICameraService.Stub.getDefaultImpl().isHiddenPhysicalCamera(param1String);
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
    
    public void notifyDeviceStateChange(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraService");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(18, parcel, null, 1) && ICameraService.Stub.getDefaultImpl() != null) {
          ICameraService.Stub.getDefaultImpl().notifyDeviceStateChange(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void notifySystemEvent(int param1Int, int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraService");
        parcel.writeInt(param1Int);
        parcel.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(17, parcel, null, 1) && ICameraService.Stub.getDefaultImpl() != null) {
          ICameraService.Stub.getDefaultImpl().notifySystemEvent(param1Int, param1ArrayOfint);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void removeListener(ICameraServiceListener param1ICameraServiceListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        if (param1ICameraServiceListener != null) {
          iBinder = param1ICameraServiceListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          ICameraService.Stub.getDefaultImpl().removeListener(param1ICameraServiceListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTorchMode(String param1String, boolean param1Boolean, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeString(param1String);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          ICameraService.Stub.getDefaultImpl().setTorchMode(param1String, param1Boolean, param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean supportsCameraApi(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICameraService");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(14, parcel1, parcel2, 0) && ICameraService.Stub.getDefaultImpl() != null) {
          bool = ICameraService.Stub.getDefaultImpl().supportsCameraApi(param1String, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */