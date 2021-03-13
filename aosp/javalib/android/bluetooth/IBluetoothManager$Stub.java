package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothManager {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothManager";
  
  static final int TRANSACTION_bindBluetoothProfileService = 11;
  
  static final int TRANSACTION_disable = 8;
  
  static final int TRANSACTION_disableBle = 18;
  
  static final int TRANSACTION_enable = 6;
  
  static final int TRANSACTION_enableBle = 17;
  
  static final int TRANSACTION_enableNoAutoConnect = 7;
  
  static final int TRANSACTION_getAddress = 13;
  
  static final int TRANSACTION_getBluetoothGatt = 10;
  
  static final int TRANSACTION_getName = 14;
  
  static final int TRANSACTION_getState = 9;
  
  static final int TRANSACTION_getSystemConfigEnabledProfilesForPackage = 21;
  
  static final int TRANSACTION_isBleAppPresent = 19;
  
  static final int TRANSACTION_isBleScanAlwaysAvailable = 16;
  
  static final int TRANSACTION_isEnabled = 5;
  
  static final int TRANSACTION_isHearingAidProfileSupported = 20;
  
  static final int TRANSACTION_onFactoryReset = 15;
  
  static final int TRANSACTION_registerAdapter = 1;
  
  static final int TRANSACTION_registerStateChangeCallback = 3;
  
  static final int TRANSACTION_unbindBluetoothProfileService = 12;
  
  static final int TRANSACTION_unregisterAdapter = 2;
  
  static final int TRANSACTION_unregisterStateChangeCallback = 4;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothManager");
  }
  
  public static IBluetoothManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothManager");
    return (iInterface != null && iInterface instanceof IBluetoothManager) ? (IBluetoothManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 21:
        return "getSystemConfigEnabledProfilesForPackage";
      case 20:
        return "isHearingAidProfileSupported";
      case 19:
        return "isBleAppPresent";
      case 18:
        return "disableBle";
      case 17:
        return "enableBle";
      case 16:
        return "isBleScanAlwaysAvailable";
      case 15:
        return "onFactoryReset";
      case 14:
        return "getName";
      case 13:
        return "getAddress";
      case 12:
        return "unbindBluetoothProfileService";
      case 11:
        return "bindBluetoothProfileService";
      case 10:
        return "getBluetoothGatt";
      case 9:
        return "getState";
      case 8:
        return "disable";
      case 7:
        return "enableNoAutoConnect";
      case 6:
        return "enable";
      case 5:
        return "isEnabled";
      case 4:
        return "unregisterStateChangeCallback";
      case 3:
        return "registerStateChangeCallback";
      case 2:
        return "unregisterAdapter";
      case 1:
        break;
    } 
    return "registerAdapter";
  }
  
  public static boolean setDefaultImpl(IBluetoothManager paramIBluetoothManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothManager != null) {
        Proxy.sDefaultImpl = paramIBluetoothManager;
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
      boolean bool2;
      int i;
      boolean bool1;
      List<String> list;
      String str1;
      IBinder iBinder2;
      IBinder iBinder1;
      boolean bool;
      IBluetoothGatt iBluetoothGatt2 = null;
      String str2 = null;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 21:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothManager");
          list = getSystemConfigEnabledProfilesForPackage(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 20:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = isHearingAidProfileSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 19:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = isBleAppPresent();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 18:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = disableBle(list.readString(), list.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 17:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = enableBle(list.readString(), list.readStrongBinder());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 16:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = isBleScanAlwaysAvailable();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 15:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = onFactoryReset();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 14:
          list.enforceInterface("android.bluetooth.IBluetoothManager");
          str1 = getName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 13:
          str1.enforceInterface("android.bluetooth.IBluetoothManager");
          str1 = getAddress();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 12:
          str1.enforceInterface("android.bluetooth.IBluetoothManager");
          unbindBluetoothProfileService(str1.readInt(), IBluetoothProfileServiceConnection.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 11:
          str1.enforceInterface("android.bluetooth.IBluetoothManager");
          bool2 = bindBluetoothProfileService(str1.readInt(), IBluetoothProfileServiceConnection.Stub.asInterface(str1.readStrongBinder()));
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 10:
          str1.enforceInterface("android.bluetooth.IBluetoothManager");
          iBluetoothGatt2 = getBluetoothGatt();
          paramParcel2.writeNoException();
          str1 = str2;
          if (iBluetoothGatt2 != null)
            iBinder2 = iBluetoothGatt2.asBinder(); 
          paramParcel2.writeStrongBinder(iBinder2);
          return true;
        case 9:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          i = getState();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 8:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          str2 = iBinder2.readString();
          if (iBinder2.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          bool1 = disable(str2, bool);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 7:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          bool1 = enableNoAutoConnect(iBinder2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 6:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          bool1 = enable(iBinder2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 5:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          bool1 = isEnabled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 4:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          unregisterStateChangeCallback(IBluetoothStateChangeCallback.Stub.asInterface(iBinder2.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 3:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          registerStateChangeCallback(IBluetoothStateChangeCallback.Stub.asInterface(iBinder2.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 2:
          iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
          unregisterAdapter(IBluetoothManagerCallback.Stub.asInterface(iBinder2.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      iBinder2.enforceInterface("android.bluetooth.IBluetoothManager");
      IBluetooth iBluetooth = registerAdapter(IBluetoothManagerCallback.Stub.asInterface(iBinder2.readStrongBinder()));
      paramParcel2.writeNoException();
      IBluetoothGatt iBluetoothGatt1 = iBluetoothGatt2;
      if (iBluetooth != null)
        iBinder1 = iBluetooth.asBinder(); 
      paramParcel2.writeStrongBinder(iBinder1);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothManager");
    return true;
  }
  
  private static class Proxy implements IBluetoothManager {
    public static IBluetoothManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean bindBluetoothProfileService(int param2Int, IBluetoothProfileServiceConnection param2IBluetoothProfileServiceConnection) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeInt(param2Int);
        if (param2IBluetoothProfileServiceConnection != null) {
          iBinder = param2IBluetoothProfileServiceConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(11, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().bindBluetoothProfileService(param2Int, param2IBluetoothProfileServiceConnection);
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
    
    public boolean disable(String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeString(param2String);
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IBluetoothManager.Stub.getDefaultImpl().disable(param2String, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean disableBle(String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().disableBle(param2String, param2IBinder);
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
    
    public boolean enable(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().enable(param2String);
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
    
    public boolean enableBle(String param2String, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeString(param2String);
        parcel1.writeStrongBinder(param2IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().enableBle(param2String, param2IBinder);
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
    
    public boolean enableNoAutoConnect(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().enableNoAutoConnect(param2String);
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
    
    public String getAddress() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
          return IBluetoothManager.Stub.getDefaultImpl().getAddress(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBluetoothGatt getBluetoothGatt() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
          return IBluetoothManager.Stub.getDefaultImpl().getBluetoothGatt(); 
        parcel2.readException();
        return IBluetoothGatt.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothManager";
    }
    
    public String getName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
          return IBluetoothManager.Stub.getDefaultImpl().getName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
          return IBluetoothManager.Stub.getDefaultImpl().getState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getSystemConfigEnabledProfilesForPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
          return IBluetoothManager.Stub.getDefaultImpl().getSystemConfigEnabledProfilesForPackage(param2String); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBleAppPresent() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().isBleAppPresent();
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
    
    public boolean isBleScanAlwaysAvailable() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(16, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().isBleScanAlwaysAvailable();
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
    
    public boolean isEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().isEnabled();
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
    
    public boolean isHearingAidProfileSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(20, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().isHearingAidProfileSupported();
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
    
    public boolean onFactoryReset() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(15, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          bool = IBluetoothManager.Stub.getDefaultImpl().onFactoryReset();
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
    
    public IBluetooth registerAdapter(IBluetoothManagerCallback param2IBluetoothManagerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (param2IBluetoothManagerCallback != null) {
          iBinder = param2IBluetoothManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
          return IBluetoothManager.Stub.getDefaultImpl().registerAdapter(param2IBluetoothManagerCallback); 
        parcel2.readException();
        return IBluetooth.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerStateChangeCallback(IBluetoothStateChangeCallback param2IBluetoothStateChangeCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (param2IBluetoothStateChangeCallback != null) {
          iBinder = param2IBluetoothStateChangeCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          IBluetoothManager.Stub.getDefaultImpl().registerStateChangeCallback(param2IBluetoothStateChangeCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unbindBluetoothProfileService(int param2Int, IBluetoothProfileServiceConnection param2IBluetoothProfileServiceConnection) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        parcel1.writeInt(param2Int);
        if (param2IBluetoothProfileServiceConnection != null) {
          iBinder = param2IBluetoothProfileServiceConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          IBluetoothManager.Stub.getDefaultImpl().unbindBluetoothProfileService(param2Int, param2IBluetoothProfileServiceConnection);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterAdapter(IBluetoothManagerCallback param2IBluetoothManagerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (param2IBluetoothManagerCallback != null) {
          iBinder = param2IBluetoothManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          IBluetoothManager.Stub.getDefaultImpl().unregisterAdapter(param2IBluetoothManagerCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterStateChangeCallback(IBluetoothStateChangeCallback param2IBluetoothStateChangeCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
        if (param2IBluetoothStateChangeCallback != null) {
          iBinder = param2IBluetoothStateChangeCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
          IBluetoothManager.Stub.getDefaultImpl().unregisterStateChangeCallback(param2IBluetoothStateChangeCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */