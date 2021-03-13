package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothHeadset extends IInterface {
  void clccResponse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, String paramString, int paramInt5) throws RemoteException;
  
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean connectAudio() throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnectAudio() throws RemoteException;
  
  BluetoothDevice getActiveDevice() throws RemoteException;
  
  boolean getAudioRouteAllowed() throws RemoteException;
  
  int getAudioState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  int getPriority(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean isAudioConnected(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean isAudioOn() throws RemoteException;
  
  boolean isInbandRingingEnabled() throws RemoteException;
  
  void phoneStateChanged(int paramInt1, int paramInt2, int paramInt3, String paramString1, int paramInt4, String paramString2) throws RemoteException;
  
  boolean sendVendorSpecificResultCode(BluetoothDevice paramBluetoothDevice, String paramString1, String paramString2) throws RemoteException;
  
  boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  void setAudioRouteAllowed(boolean paramBoolean) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  void setForceScoAudio(boolean paramBoolean) throws RemoteException;
  
  boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  boolean startScoUsingVirtualVoiceCall() throws RemoteException;
  
  boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean stopScoUsingVirtualVoiceCall() throws RemoteException;
  
  boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  public static class Default implements IBluetoothHeadset {
    public IBinder asBinder() {
      return null;
    }
    
    public void clccResponse(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean, String param1String, int param1Int5) throws RemoteException {}
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean connectAudio() throws RemoteException {
      return false;
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean disconnectAudio() throws RemoteException {
      return false;
    }
    
    public BluetoothDevice getActiveDevice() throws RemoteException {
      return null;
    }
    
    public boolean getAudioRouteAllowed() throws RemoteException {
      return false;
    }
    
    public int getAudioState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      return null;
    }
    
    public int getConnectionPolicy(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public int getPriority(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public boolean isAudioConnected(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean isAudioOn() throws RemoteException {
      return false;
    }
    
    public boolean isInbandRingingEnabled() throws RemoteException {
      return false;
    }
    
    public void phoneStateChanged(int param1Int1, int param1Int2, int param1Int3, String param1String1, int param1Int4, String param1String2) throws RemoteException {}
    
    public boolean sendVendorSpecificResultCode(BluetoothDevice param1BluetoothDevice, String param1String1, String param1String2) throws RemoteException {
      return false;
    }
    
    public boolean setActiveDevice(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public void setAudioRouteAllowed(boolean param1Boolean) throws RemoteException {}
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public void setForceScoAudio(boolean param1Boolean) throws RemoteException {}
    
    public boolean setPriority(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean startScoUsingVirtualVoiceCall() throws RemoteException {
      return false;
    }
    
    public boolean startVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean stopScoUsingVirtualVoiceCall() throws RemoteException {
      return false;
    }
    
    public boolean stopVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothHeadset {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadset";
    
    static final int TRANSACTION_clccResponse = 22;
    
    static final int TRANSACTION_connect = 8;
    
    static final int TRANSACTION_connectAudio = 14;
    
    static final int TRANSACTION_disconnect = 9;
    
    static final int TRANSACTION_disconnectAudio = 15;
    
    static final int TRANSACTION_getActiveDevice = 24;
    
    static final int TRANSACTION_getAudioRouteAllowed = 17;
    
    static final int TRANSACTION_getAudioState = 12;
    
    static final int TRANSACTION_getConnectedDevices = 1;
    
    static final int TRANSACTION_getConnectionPolicy = 11;
    
    static final int TRANSACTION_getConnectionState = 3;
    
    static final int TRANSACTION_getDevicesMatchingConnectionStates = 2;
    
    static final int TRANSACTION_getPriority = 27;
    
    static final int TRANSACTION_isAudioConnected = 6;
    
    static final int TRANSACTION_isAudioOn = 13;
    
    static final int TRANSACTION_isInbandRingingEnabled = 25;
    
    static final int TRANSACTION_phoneStateChanged = 21;
    
    static final int TRANSACTION_sendVendorSpecificResultCode = 7;
    
    static final int TRANSACTION_setActiveDevice = 23;
    
    static final int TRANSACTION_setAudioRouteAllowed = 16;
    
    static final int TRANSACTION_setConnectionPolicy = 10;
    
    static final int TRANSACTION_setForceScoAudio = 18;
    
    static final int TRANSACTION_setPriority = 26;
    
    static final int TRANSACTION_startScoUsingVirtualVoiceCall = 19;
    
    static final int TRANSACTION_startVoiceRecognition = 4;
    
    static final int TRANSACTION_stopScoUsingVirtualVoiceCall = 20;
    
    static final int TRANSACTION_stopVoiceRecognition = 5;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothHeadset");
    }
    
    public static IBluetoothHeadset asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothHeadset");
      return (iInterface != null && iInterface instanceof IBluetoothHeadset) ? (IBluetoothHeadset)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothHeadset getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 27:
          return "getPriority";
        case 26:
          return "setPriority";
        case 25:
          return "isInbandRingingEnabled";
        case 24:
          return "getActiveDevice";
        case 23:
          return "setActiveDevice";
        case 22:
          return "clccResponse";
        case 21:
          return "phoneStateChanged";
        case 20:
          return "stopScoUsingVirtualVoiceCall";
        case 19:
          return "startScoUsingVirtualVoiceCall";
        case 18:
          return "setForceScoAudio";
        case 17:
          return "getAudioRouteAllowed";
        case 16:
          return "setAudioRouteAllowed";
        case 15:
          return "disconnectAudio";
        case 14:
          return "connectAudio";
        case 13:
          return "isAudioOn";
        case 12:
          return "getAudioState";
        case 11:
          return "getConnectionPolicy";
        case 10:
          return "setConnectionPolicy";
        case 9:
          return "disconnect";
        case 8:
          return "connect";
        case 7:
          return "sendVendorSpecificResultCode";
        case 6:
          return "isAudioConnected";
        case 5:
          return "stopVoiceRecognition";
        case 4:
          return "startVoiceRecognition";
        case 3:
          return "getConnectionState";
        case 2:
          return "getDevicesMatchingConnectionStates";
        case 1:
          break;
      } 
      return "getConnectedDevices";
    }
    
    public static boolean setDefaultImpl(IBluetoothHeadset param1IBluetoothHeadset) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothHeadset != null) {
          Proxy.sDefaultImpl = param1IBluetoothHeadset;
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
        boolean bool3;
        int k;
        boolean bool2;
        int j;
        boolean bool1;
        int i;
        BluetoothDevice bluetoothDevice1;
        BluetoothDevice bluetoothDevice2;
        int m;
        int n;
        boolean bool4 = false;
        boolean bool5 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 27:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = getPriority((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 26:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice2 = null;
            } 
            bool3 = setPriority(bluetoothDevice2, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 25:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool3 = isInbandRingingEnabled();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 24:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bluetoothDevice1 = getActiveDevice();
            param1Parcel2.writeNoException();
            if (bluetoothDevice1 != null) {
              param1Parcel2.writeInt(1);
              bluetoothDevice1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 23:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            bool3 = setActiveDevice(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 22:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            m = bluetoothDevice1.readInt();
            n = bluetoothDevice1.readInt();
            k = bluetoothDevice1.readInt();
            param1Int2 = bluetoothDevice1.readInt();
            if (bluetoothDevice1.readInt() != 0) {
              bool5 = true;
            } else {
              bool5 = false;
            } 
            clccResponse(m, n, k, param1Int2, bool5, bluetoothDevice1.readString(), bluetoothDevice1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 21:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            phoneStateChanged(bluetoothDevice1.readInt(), bluetoothDevice1.readInt(), bluetoothDevice1.readInt(), bluetoothDevice1.readString(), bluetoothDevice1.readInt(), bluetoothDevice1.readString());
            return true;
          case 20:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool2 = stopScoUsingVirtualVoiceCall();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 19:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool2 = startScoUsingVirtualVoiceCall();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 18:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0)
              bool5 = true; 
            setForceScoAudio(bool5);
            param1Parcel2.writeNoException();
            return true;
          case 17:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool2 = getAudioRouteAllowed();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 16:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool5 = bool4;
            if (bluetoothDevice1.readInt() != 0)
              bool5 = true; 
            setAudioRouteAllowed(bool5);
            param1Parcel2.writeNoException();
            return true;
          case 15:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool2 = disconnectAudio();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 14:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool2 = connectAudio();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 13:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            bool2 = isAudioOn();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 12:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            j = getAudioState(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 11:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            j = getConnectionPolicy(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 10:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice2 = null;
            } 
            bool1 = setConnectionPolicy(bluetoothDevice2, bluetoothDevice1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 9:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            bool1 = disconnect(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 8:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            bool1 = connect(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 7:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice2 = null;
            } 
            bool1 = sendVendorSpecificResultCode(bluetoothDevice2, bluetoothDevice1.readString(), bluetoothDevice1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 6:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            bool1 = isAudioConnected(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 5:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            bool1 = stopVoiceRecognition(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 4:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            bool1 = startVoiceRecognition(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 3:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            if (bluetoothDevice1.readInt() != 0) {
              bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothDevice1);
            } else {
              bluetoothDevice1 = null;
            } 
            i = getConnectionState(bluetoothDevice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 2:
            bluetoothDevice1.enforceInterface("android.bluetooth.IBluetoothHeadset");
            list = getDevicesMatchingConnectionStates(bluetoothDevice1.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.bluetooth.IBluetoothHeadset");
        List<BluetoothDevice> list = getConnectedDevices();
        param1Parcel2.writeNoException();
        param1Parcel2.writeTypedList(list);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothHeadset");
      return true;
    }
    
    private static class Proxy implements IBluetoothHeadset {
      public static IBluetoothHeadset sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void clccResponse(int param2Int1, int param2Int2, int param2Int3, int param2Int4, boolean param2Boolean, String param2String, int param2Int5) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  boolean bool;
                  parcel1.writeInt(param2Int4);
                  if (param2Boolean) {
                    bool = true;
                  } else {
                    bool = false;
                  } 
                  parcel1.writeInt(bool);
                  try {
                    parcel1.writeString(param2String);
                    parcel1.writeInt(param2Int5);
                    if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
                      IBluetoothHeadset.Stub.getDefaultImpl().clccResponse(param2Int1, param2Int2, param2Int3, param2Int4, param2Boolean, param2String, param2Int5);
                      parcel2.recycle();
                      parcel1.recycle();
                      return;
                    } 
                    parcel2.readException();
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String;
      }
      
      public boolean connect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
      
      public boolean connectAudio() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(14, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().connectAudio();
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
      
      public boolean disconnect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
      
      public boolean disconnectAudio() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(15, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().disconnectAudio();
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
      
      public BluetoothDevice getActiveDevice() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          BluetoothDevice bluetoothDevice;
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bluetoothDevice = IBluetoothHeadset.Stub.getDefaultImpl().getActiveDevice();
            return bluetoothDevice;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel2);
          } else {
            bluetoothDevice = null;
          } 
          return bluetoothDevice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean getAudioRouteAllowed() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(17, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().getAudioRouteAllowed();
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
      
      public int getAudioState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
            return IBluetoothHeadset.Stub.getDefaultImpl().getAudioState(param2BluetoothDevice); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
            return IBluetoothHeadset.Stub.getDefaultImpl().getConnectedDevices(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getConnectionPolicy(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
            return IBluetoothHeadset.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getConnectionState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
            return IBluetoothHeadset.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
            return IBluetoothHeadset.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothHeadset";
      }
      
      public int getPriority(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
            return IBluetoothHeadset.Stub.getDefaultImpl().getPriority(param2BluetoothDevice); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isAudioConnected(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().isAudioConnected(param2BluetoothDevice);
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
      
      public boolean isAudioOn() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(13, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().isAudioOn();
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
      
      public boolean isInbandRingingEnabled() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(25, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().isInbandRingingEnabled();
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
      
      public void phoneStateChanged(int param2Int1, int param2Int2, int param2Int3, String param2String1, int param2Int4, String param2String2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          try {
            parcel.writeInt(param2Int1);
            try {
              parcel.writeInt(param2Int2);
              try {
                parcel.writeInt(param2Int3);
                try {
                  parcel.writeString(param2String1);
                  try {
                    parcel.writeInt(param2Int4);
                    try {
                      parcel.writeString(param2String2);
                      try {
                        if (!this.mRemote.transact(21, parcel, null, 1) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
                          IBluetoothHeadset.Stub.getDefaultImpl().phoneStateChanged(param2Int1, param2Int2, param2Int3, param2String1, param2Int4, param2String2);
                          parcel.recycle();
                          return;
                        } 
                        parcel.recycle();
                        return;
                      } finally {}
                    } finally {}
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2String1;
      }
      
      public boolean sendVendorSpecificResultCode(BluetoothDevice param2BluetoothDevice, String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().sendVendorSpecificResultCode(param2BluetoothDevice, param2String1, param2String2);
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
      
      public boolean setActiveDevice(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().setActiveDevice(param2BluetoothDevice);
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
      
      public void setAudioRouteAllowed(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            IBluetoothHeadset.Stub.getDefaultImpl().setAudioRouteAllowed(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setForceScoAudio(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            IBluetoothHeadset.Stub.getDefaultImpl().setForceScoAudio(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setPriority(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().setPriority(param2BluetoothDevice, param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean startScoUsingVirtualVoiceCall() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(19, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().startScoUsingVirtualVoiceCall();
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
      
      public boolean startVoiceRecognition(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().startVoiceRecognition(param2BluetoothDevice);
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
      
      public boolean stopScoUsingVirtualVoiceCall() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(20, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().stopScoUsingVirtualVoiceCall();
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
      
      public boolean stopVoiceRecognition(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadset.Stub.getDefaultImpl().stopVoiceRecognition(param2BluetoothDevice);
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
  
  private static class Proxy implements IBluetoothHeadset {
    public static IBluetoothHeadset sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clccResponse(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean, String param1String, int param1Int5) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeInt(param1Int2);
            try {
              parcel1.writeInt(param1Int3);
              try {
                boolean bool;
                parcel1.writeInt(param1Int4);
                if (param1Boolean) {
                  bool = true;
                } else {
                  bool = false;
                } 
                parcel1.writeInt(bool);
                try {
                  parcel1.writeString(param1String);
                  parcel1.writeInt(param1Int5);
                  if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
                    IBluetoothHeadset.Stub.getDefaultImpl().clccResponse(param1Int1, param1Int2, param1Int3, param1Int4, param1Boolean, param1String, param1Int5);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
    
    public boolean connectAudio() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(14, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().connectAudio();
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
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public boolean disconnectAudio() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(15, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().disconnectAudio();
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
    
    public BluetoothDevice getActiveDevice() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        BluetoothDevice bluetoothDevice;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bluetoothDevice = IBluetoothHeadset.Stub.getDefaultImpl().getActiveDevice();
          return bluetoothDevice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel2);
        } else {
          bluetoothDevice = null;
        } 
        return bluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getAudioRouteAllowed() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().getAudioRouteAllowed();
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
    
    public int getAudioState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
          return IBluetoothHeadset.Stub.getDefaultImpl().getAudioState(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
          return IBluetoothHeadset.Stub.getDefaultImpl().getConnectedDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionPolicy(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
          return IBluetoothHeadset.Stub.getDefaultImpl().getConnectionPolicy(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
          return IBluetoothHeadset.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
          return IBluetoothHeadset.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHeadset";
    }
    
    public int getPriority(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
          return IBluetoothHeadset.Stub.getDefaultImpl().getPriority(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAudioConnected(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().isAudioConnected(param1BluetoothDevice);
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
    
    public boolean isAudioOn() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().isAudioOn();
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
    
    public boolean isInbandRingingEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(25, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().isInbandRingingEnabled();
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
    
    public void phoneStateChanged(int param1Int1, int param1Int2, int param1Int3, String param1String1, int param1Int4, String param1String2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        try {
          parcel.writeInt(param1Int1);
          try {
            parcel.writeInt(param1Int2);
            try {
              parcel.writeInt(param1Int3);
              try {
                parcel.writeString(param1String1);
                try {
                  parcel.writeInt(param1Int4);
                  try {
                    parcel.writeString(param1String2);
                    try {
                      if (!this.mRemote.transact(21, parcel, null, 1) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
                        IBluetoothHeadset.Stub.getDefaultImpl().phoneStateChanged(param1Int1, param1Int2, param1Int3, param1String1, param1Int4, param1String2);
                        parcel.recycle();
                        return;
                      } 
                      parcel.recycle();
                      return;
                    } finally {}
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1String1;
    }
    
    public boolean sendVendorSpecificResultCode(BluetoothDevice param1BluetoothDevice, String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().sendVendorSpecificResultCode(param1BluetoothDevice, param1String1, param1String2);
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
    
    public boolean setActiveDevice(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().setActiveDevice(param1BluetoothDevice);
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
    
    public void setAudioRouteAllowed(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          IBluetoothHeadset.Stub.getDefaultImpl().setAudioRouteAllowed(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setForceScoAudio(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          IBluetoothHeadset.Stub.getDefaultImpl().setForceScoAudio(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPriority(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().setPriority(param1BluetoothDevice, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startScoUsingVirtualVoiceCall() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().startScoUsingVirtualVoiceCall();
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
    
    public boolean startVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().startVoiceRecognition(param1BluetoothDevice);
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
    
    public boolean stopScoUsingVirtualVoiceCall() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(20, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().stopScoUsingVirtualVoiceCall();
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
    
    public boolean stopVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadset.Stub.getDefaultImpl().stopVoiceRecognition(param1BluetoothDevice);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */