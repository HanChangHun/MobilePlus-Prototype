package android.bluetooth;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothMapClient extends IInterface {
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  int getSupportedFeatures(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean getUnreadMessages(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean isConnected(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean sendMessage(BluetoothDevice paramBluetoothDevice, Uri[] paramArrayOfUri, String paramString, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  public static class Default implements IBluetoothMapClient {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
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
    
    public int getSupportedFeatures(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return 0;
    }
    
    public boolean getUnreadMessages(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean isConnected(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean sendMessage(BluetoothDevice param1BluetoothDevice, Uri[] param1ArrayOfUri, String param1String, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2) throws RemoteException {
      return false;
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothMapClient {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothMapClient";
    
    static final int TRANSACTION_connect = 1;
    
    static final int TRANSACTION_disconnect = 2;
    
    static final int TRANSACTION_getConnectedDevices = 4;
    
    static final int TRANSACTION_getConnectionPolicy = 8;
    
    static final int TRANSACTION_getConnectionState = 6;
    
    static final int TRANSACTION_getDevicesMatchingConnectionStates = 5;
    
    static final int TRANSACTION_getSupportedFeatures = 11;
    
    static final int TRANSACTION_getUnreadMessages = 10;
    
    static final int TRANSACTION_isConnected = 3;
    
    static final int TRANSACTION_sendMessage = 9;
    
    static final int TRANSACTION_setConnectionPolicy = 7;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothMapClient");
    }
    
    public static IBluetoothMapClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothMapClient");
      return (iInterface != null && iInterface instanceof IBluetoothMapClient) ? (IBluetoothMapClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothMapClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 11:
          return "getSupportedFeatures";
        case 10:
          return "getUnreadMessages";
        case 9:
          return "sendMessage";
        case 8:
          return "getConnectionPolicy";
        case 7:
          return "setConnectionPolicy";
        case 6:
          return "getConnectionState";
        case 5:
          return "getDevicesMatchingConnectionStates";
        case 4:
          return "getConnectedDevices";
        case 3:
          return "isConnected";
        case 2:
          return "disconnect";
        case 1:
          break;
      } 
      return "connect";
    }
    
    public static boolean setDefaultImpl(IBluetoothMapClient param1IBluetoothMapClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothMapClient != null) {
          Proxy.sDefaultImpl = param1IBluetoothMapClient;
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
        int j;
        boolean bool2;
        int i;
        List<BluetoothDevice> list;
        BluetoothDevice bluetoothDevice;
        Uri[] arrayOfUri;
        String str;
        PendingIntent pendingIntent;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 11:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            param1Int1 = getSupportedFeatures((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bool3 = getUnreadMessages((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            arrayOfUri = (Uri[])param1Parcel1.createTypedArray(Uri.CREATOR);
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              pendingIntent = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              PendingIntent pendingIntent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bool3 = sendMessage(bluetoothDevice, arrayOfUri, str, pendingIntent, (PendingIntent)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            j = getConnectionPolicy((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (param1Parcel1.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bluetoothDevice = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            i = getConnectionState((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMapClient");
            list = getDevicesMatchingConnectionStates(param1Parcel1.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 4:
            list.enforceInterface("android.bluetooth.IBluetoothMapClient");
            list = getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = isConnected((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothMapClient");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool1 = disconnect((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.bluetooth.IBluetoothMapClient");
        if (list.readInt() != 0) {
          BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
        } else {
          list = null;
        } 
        boolean bool1 = connect((BluetoothDevice)list);
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothMapClient");
      return true;
    }
    
    private static class Proxy implements IBluetoothMapClient {
      public static IBluetoothMapClient sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public boolean connect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothMapClient.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
      
      public boolean disconnect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothMapClient.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
      
      public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
            return IBluetoothMapClient.Stub.getDefaultImpl().getConnectedDevices(); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
            return IBluetoothMapClient.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
            return IBluetoothMapClient.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
            return IBluetoothMapClient.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothMapClient";
      }
      
      public int getSupportedFeatures(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
            return IBluetoothMapClient.Stub.getDefaultImpl().getSupportedFeatures(param2BluetoothDevice); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean getUnreadMessages(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothMapClient.Stub.getDefaultImpl().getUnreadMessages(param2BluetoothDevice);
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
      
      public boolean isConnected(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothMapClient.Stub.getDefaultImpl().isConnected(param2BluetoothDevice);
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
      
      public boolean sendMessage(BluetoothDevice param2BluetoothDevice, Uri[] param2ArrayOfUri, String param2String, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeTypedArray((Parcelable[])param2ArrayOfUri, 0);
            try {
              parcel1.writeString(param2String);
              if (param2PendingIntent1 != null) {
                parcel1.writeInt(1);
                param2PendingIntent1.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (param2PendingIntent2 != null) {
                parcel1.writeInt(1);
                param2PendingIntent2.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
                  bool = IBluetoothMapClient.Stub.getDefaultImpl().sendMessage(param2BluetoothDevice, param2ArrayOfUri, param2String, param2PendingIntent1, param2PendingIntent2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return bool;
                } 
                parcel2.readException();
                int i = parcel2.readInt();
                if (i == 0)
                  bool = false; 
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2BluetoothDevice;
      }
      
      public boolean setConnectionPolicy(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothMapClient.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
    }
  }
  
  private static class Proxy implements IBluetoothMapClient {
    public static IBluetoothMapClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothMapClient.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothMapClient.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
          return IBluetoothMapClient.Stub.getDefaultImpl().getConnectedDevices(); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
          return IBluetoothMapClient.Stub.getDefaultImpl().getConnectionPolicy(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
          return IBluetoothMapClient.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
          return IBluetoothMapClient.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothMapClient";
    }
    
    public int getSupportedFeatures(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null)
          return IBluetoothMapClient.Stub.getDefaultImpl().getSupportedFeatures(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getUnreadMessages(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothMapClient.Stub.getDefaultImpl().getUnreadMessages(param1BluetoothDevice);
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
    
    public boolean isConnected(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothMapClient.Stub.getDefaultImpl().isConnected(param1BluetoothDevice);
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
    
    public boolean sendMessage(BluetoothDevice param1BluetoothDevice, Uri[] param1ArrayOfUri, String param1String, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeTypedArray((Parcelable[])param1ArrayOfUri, 0);
          try {
            parcel1.writeString(param1String);
            if (param1PendingIntent1 != null) {
              parcel1.writeInt(1);
              param1PendingIntent1.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (param1PendingIntent2 != null) {
              parcel1.writeInt(1);
              param1PendingIntent2.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
                bool = IBluetoothMapClient.Stub.getDefaultImpl().sendMessage(param1BluetoothDevice, param1ArrayOfUri, param1String, param1PendingIntent1, param1PendingIntent2);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              int i = parcel2.readInt();
              if (i == 0)
                bool = false; 
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1BluetoothDevice;
    }
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothMapClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothMapClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothMapClient.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothMapClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */