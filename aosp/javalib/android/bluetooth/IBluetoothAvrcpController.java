package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothAvrcpController extends IInterface {
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  void sendGroupNavigationCmd(BluetoothDevice paramBluetoothDevice, int paramInt1, int paramInt2) throws RemoteException;
  
  boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings paramBluetoothAvrcpPlayerSettings) throws RemoteException;
  
  public static class Default implements IBluetoothAvrcpController {
    public IBinder asBinder() {
      return null;
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      return null;
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return null;
    }
    
    public void sendGroupNavigationCmd(BluetoothDevice param1BluetoothDevice, int param1Int1, int param1Int2) throws RemoteException {}
    
    public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings param1BluetoothAvrcpPlayerSettings) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothAvrcpController {
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
    
    public static IBluetoothAvrcpController asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothAvrcpController");
      return (iInterface != null && iInterface instanceof IBluetoothAvrcpController) ? (IBluetoothAvrcpController)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothAvrcpController getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBluetoothAvrcpController param1IBluetoothAvrcpController) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothAvrcpController != null) {
          Proxy.sDefaultImpl = param1IBluetoothAvrcpController;
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
        BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings;
        BluetoothDevice bluetoothDevice;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            sendGroupNavigationCmd(bluetoothDevice, param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
            if (param1Parcel1.readInt() != 0) {
              BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings1 = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bool = setPlayerApplicationSetting((BluetoothAvrcpPlayerSettings)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bluetoothAvrcpPlayerSettings = getPlayerSettings((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            if (bluetoothAvrcpPlayerSettings != null) {
              param1Parcel2.writeInt(1);
              bluetoothAvrcpPlayerSettings.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
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
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 2:
            bluetoothAvrcpPlayerSettings.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
            list = getDevicesMatchingConnectionStates(bluetoothAvrcpPlayerSettings.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.bluetooth.IBluetoothAvrcpController");
        List<BluetoothDevice> list = getConnectedDevices();
        param1Parcel2.writeNoException();
        param1Parcel2.writeTypedList(list);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothAvrcpController");
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
  
  private static class Proxy implements IBluetoothAvrcpController {
    public static IBluetoothAvrcpController sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
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
    
    public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null)
          return IBluetoothAvrcpController.Stub.getDefaultImpl().getPlayerSettings(param1BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          BluetoothAvrcpPlayerSettings bluetoothAvrcpPlayerSettings = (BluetoothAvrcpPlayerSettings)BluetoothAvrcpPlayerSettings.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (BluetoothAvrcpPlayerSettings)param1BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendGroupNavigationCmd(BluetoothDevice param1BluetoothDevice, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null) {
          IBluetoothAvrcpController.Stub.getDefaultImpl().sendGroupNavigationCmd(param1BluetoothDevice, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings param1BluetoothAvrcpPlayerSettings) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpController");
        boolean bool = true;
        if (param1BluetoothAvrcpPlayerSettings != null) {
          parcel1.writeInt(1);
          param1BluetoothAvrcpPlayerSettings.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothAvrcpController.Stub.getDefaultImpl() != null) {
          bool = IBluetoothAvrcpController.Stub.getDefaultImpl().setPlayerApplicationSetting(param1BluetoothAvrcpPlayerSettings);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */