package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothAvrcpController {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothAvrcpController";
  
  static final int TRANSACTION_getConnectedDevices = 1;
  
  static final int TRANSACTION_getConnectionState = 3;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 2;
  
  static final int TRANSACTION_getPlayerSettings = 4;
  
  static final int TRANSACTION_sendGroupNavigationCmd = 6;
  
  static final int TRANSACTION_setPlayerApplicationSetting = 5;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothAvrcpController");
  }
  
  public static IBluetoothAvrcpController asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothAvrcpController");
    return (iInterface != null && iInterface instanceof IBluetoothAvrcpController) ? (IBluetoothAvrcpController)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothAvrcpController getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 6:
        return "sendGroupNavigationCmd";
      case 5:
        return "setPlayerApplicationSetting";
      case 4:
        return "getPlayerSettings";
      case 3:
        return "getConnectionState";
      case 2:
        return "getDevicesMatchingConnectionStates";
      case 1:
        break;
    } 
    return "getConnectedDevices";
  }
  
  public static boolean setDefaultImpl(IBluetoothAvrcpController paramIBluetoothAvrcpController) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothAvrcpController != null) {
        Proxy.sDefaultImpl = paramIBluetoothAvrcpController;
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
      BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings;
      BluetoothDevice bluetoothDevice;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
          if (paramParcel1.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            bluetoothDevice = null;
          } 
          sendGroupNavigationCmd(bluetoothDevice, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
          if (paramParcel1.readInt() != 0) {
            BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings1 = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bool = setPlayerApplicationSetting((BluetoothAvrcpPlayerSettings)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
          if (paramParcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bluetoothAvrcpPlayerSettings = getPlayerSettings((BluetoothDevice)paramParcel1);
          paramParcel2.writeNoException();
          if (bluetoothAvrcpPlayerSettings != null) {
            paramParcel2.writeInt(1);
            bluetoothAvrcpPlayerSettings.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          bluetoothAvrcpPlayerSettings.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
          if (bluetoothAvrcpPlayerSettings.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothAvrcpPlayerSettings);
          } else {
            bluetoothAvrcpPlayerSettings = null;
          } 
          i = getConnectionState((BluetoothDevice)bluetoothAvrcpPlayerSettings);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 2:
          bluetoothAvrcpPlayerSettings.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
          list = getDevicesMatchingConnectionStates(bluetoothAvrcpPlayerSettings.createIntArray());
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 1:
          break;
      } 
      list.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
      List<BluetoothDevice> list = getConnectedDevices();
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(list);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothAvrcpController");
    return true;
  }
  
  private static class Proxy implements IBluetoothAvrcpController {
    public static IBluetoothAvrcpController sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getConnectedDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothAvrcpController";
    }
    
    public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getPlayerSettings(param2BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(parcel2);
        } else {
          param2BluetoothDevice = null;
        } 
        return (BluetoothAvrcpPlayerSettings)param2BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendGroupNavigationCmd(BluetoothDevice param2BluetoothDevice, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null) {
          IBluetoothAvrcpController.Stub.getDefaultImpl().sendGroupNavigationCmd(param2BluetoothDevice, param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings param2BluetoothAvrcpPlayerSettings) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        boolean bool = true;
        if (param2BluetoothAvrcpPlayerSettings != null) {
          parcel1.writeInt(1);
          param2BluetoothAvrcpPlayerSettings.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null) {
          bool = IBluetoothAvrcpController.Stub.getDefaultImpl().setPlayerApplicationSetting(param2BluetoothAvrcpPlayerSettings);
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpController$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */