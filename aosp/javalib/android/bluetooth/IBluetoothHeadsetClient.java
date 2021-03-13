package android.bluetooth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IBluetoothHeadsetClient extends IInterface {
  boolean acceptCall(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean connectAudio(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  BluetoothHeadsetClientCall dial(BluetoothDevice paramBluetoothDevice, String paramString) throws RemoteException;
  
  boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean disconnectAudio(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean enterPrivateMode(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  boolean explicitCallTransfer(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean getAudioRouteAllowed(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getAudioState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getConnectedDevices() throws RemoteException;
  
  int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  Bundle getCurrentAgEvents(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  Bundle getCurrentAgFeatures(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException;
  
  boolean getLastVoiceTagNumber(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean holdCall(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean rejectCall(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean sendDTMF(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException;
  
  boolean sendVendorAtCommand(BluetoothDevice paramBluetoothDevice, int paramInt, String paramString) throws RemoteException;
  
  void setAudioRouteAllowed(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException;
  
  boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException;
  
  boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  boolean terminateCall(BluetoothDevice paramBluetoothDevice, BluetoothHeadsetClientCall paramBluetoothHeadsetClientCall) throws RemoteException;
  
  public static class Default implements IBluetoothHeadsetClient {
    public boolean acceptCall(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean connectAudio(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public BluetoothHeadsetClientCall dial(BluetoothDevice param1BluetoothDevice, String param1String) throws RemoteException {
      return null;
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean disconnectAudio(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean enterPrivateMode(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean explicitCallTransfer(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean getAudioRouteAllowed(BluetoothDevice param1BluetoothDevice) throws RemoteException {
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
    
    public Bundle getCurrentAgEvents(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return null;
    }
    
    public Bundle getCurrentAgFeatures(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return null;
    }
    
    public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return null;
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      return null;
    }
    
    public boolean getLastVoiceTagNumber(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean holdCall(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean rejectCall(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean sendDTMF(BluetoothDevice param1BluetoothDevice, byte param1Byte) throws RemoteException {
      return false;
    }
    
    public boolean sendVendorAtCommand(BluetoothDevice param1BluetoothDevice, int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public void setAudioRouteAllowed(BluetoothDevice param1BluetoothDevice, boolean param1Boolean) throws RemoteException {}
    
    public boolean setConnectionPolicy(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean startVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean stopVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      return false;
    }
    
    public boolean terminateCall(BluetoothDevice param1BluetoothDevice, BluetoothHeadsetClientCall param1BluetoothHeadsetClientCall) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IBluetoothHeadsetClient {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadsetClient";
    
    static final int TRANSACTION_acceptCall = 12;
    
    static final int TRANSACTION_connect = 1;
    
    static final int TRANSACTION_connectAudio = 22;
    
    static final int TRANSACTION_dial = 18;
    
    static final int TRANSACTION_disconnect = 2;
    
    static final int TRANSACTION_disconnectAudio = 23;
    
    static final int TRANSACTION_enterPrivateMode = 16;
    
    static final int TRANSACTION_explicitCallTransfer = 17;
    
    static final int TRANSACTION_getAudioRouteAllowed = 25;
    
    static final int TRANSACTION_getAudioState = 21;
    
    static final int TRANSACTION_getConnectedDevices = 3;
    
    static final int TRANSACTION_getConnectionPolicy = 7;
    
    static final int TRANSACTION_getConnectionState = 5;
    
    static final int TRANSACTION_getCurrentAgEvents = 11;
    
    static final int TRANSACTION_getCurrentAgFeatures = 27;
    
    static final int TRANSACTION_getCurrentCalls = 10;
    
    static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
    
    static final int TRANSACTION_getLastVoiceTagNumber = 20;
    
    static final int TRANSACTION_holdCall = 13;
    
    static final int TRANSACTION_rejectCall = 14;
    
    static final int TRANSACTION_sendDTMF = 19;
    
    static final int TRANSACTION_sendVendorAtCommand = 26;
    
    static final int TRANSACTION_setAudioRouteAllowed = 24;
    
    static final int TRANSACTION_setConnectionPolicy = 6;
    
    static final int TRANSACTION_startVoiceRecognition = 8;
    
    static final int TRANSACTION_stopVoiceRecognition = 9;
    
    static final int TRANSACTION_terminateCall = 15;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothHeadsetClient");
    }
    
    public static IBluetoothHeadsetClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothHeadsetClient");
      return (iInterface != null && iInterface instanceof IBluetoothHeadsetClient) ? (IBluetoothHeadsetClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothHeadsetClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 27:
          return "getCurrentAgFeatures";
        case 26:
          return "sendVendorAtCommand";
        case 25:
          return "getAudioRouteAllowed";
        case 24:
          return "setAudioRouteAllowed";
        case 23:
          return "disconnectAudio";
        case 22:
          return "connectAudio";
        case 21:
          return "getAudioState";
        case 20:
          return "getLastVoiceTagNumber";
        case 19:
          return "sendDTMF";
        case 18:
          return "dial";
        case 17:
          return "explicitCallTransfer";
        case 16:
          return "enterPrivateMode";
        case 15:
          return "terminateCall";
        case 14:
          return "rejectCall";
        case 13:
          return "holdCall";
        case 12:
          return "acceptCall";
        case 11:
          return "getCurrentAgEvents";
        case 10:
          return "getCurrentCalls";
        case 9:
          return "stopVoiceRecognition";
        case 8:
          return "startVoiceRecognition";
        case 7:
          return "getConnectionPolicy";
        case 6:
          return "setConnectionPolicy";
        case 5:
          return "getConnectionState";
        case 4:
          return "getDevicesMatchingConnectionStates";
        case 3:
          return "getConnectedDevices";
        case 2:
          return "disconnect";
        case 1:
          break;
      } 
      return "connect";
    }
    
    public static boolean setDefaultImpl(IBluetoothHeadsetClient param1IBluetoothHeadsetClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothHeadsetClient != null) {
          Proxy.sDefaultImpl = param1IBluetoothHeadsetClient;
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
        boolean bool4;
        int k;
        boolean bool3;
        int j;
        boolean bool2;
        int i;
        Bundle bundle2;
        BluetoothHeadsetClientCall bluetoothHeadsetClientCall;
        Bundle bundle1;
        List<BluetoothHeadsetClientCall> list;
        BluetoothDevice bluetoothDevice;
        boolean bool = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 27:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (param1Parcel1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bundle2 = getCurrentAgFeatures((BluetoothDevice)param1Parcel1);
            param1Parcel2.writeNoException();
            if (bundle2 != null) {
              param1Parcel2.writeInt(1);
              bundle2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 26:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bluetoothDevice = null;
            } 
            bool4 = sendVendorAtCommand(bluetoothDevice, bundle2.readInt(), bundle2.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool4);
            return true;
          case 25:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bundle2 = null;
            } 
            bool4 = getAudioRouteAllowed((BluetoothDevice)bundle2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool4);
            return true;
          case 24:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bluetoothDevice = null;
            } 
            if (bundle2.readInt() != 0)
              bool = true; 
            setAudioRouteAllowed(bluetoothDevice, bool);
            param1Parcel2.writeNoException();
            return true;
          case 23:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bundle2 = null;
            } 
            bool4 = disconnectAudio((BluetoothDevice)bundle2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool4);
            return true;
          case 22:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bundle2 = null;
            } 
            bool4 = connectAudio((BluetoothDevice)bundle2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool4);
            return true;
          case 21:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bundle2 = null;
            } 
            k = getAudioState((BluetoothDevice)bundle2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(k);
            return true;
          case 20:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bundle2 = null;
            } 
            bool3 = getLastVoiceTagNumber((BluetoothDevice)bundle2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 19:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bluetoothDevice = null;
            } 
            bool3 = sendDTMF(bluetoothDevice, bundle2.readByte());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 18:
            bundle2.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle2.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle2);
            } else {
              bluetoothDevice = null;
            } 
            bluetoothHeadsetClientCall = dial(bluetoothDevice, bundle2.readString());
            param1Parcel2.writeNoException();
            if (bluetoothHeadsetClientCall != null) {
              param1Parcel2.writeInt(1);
              bluetoothHeadsetClientCall.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 17:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothHeadsetClientCall = null;
            } 
            bool3 = explicitCallTransfer((BluetoothDevice)bluetoothHeadsetClientCall);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 16:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothDevice = null;
            } 
            bool3 = enterPrivateMode(bluetoothDevice, bluetoothHeadsetClientCall.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 15:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothDevice = null;
            } 
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall)BluetoothHeadsetClientCall.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothHeadsetClientCall = null;
            } 
            bool3 = terminateCall(bluetoothDevice, bluetoothHeadsetClientCall);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 14:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothHeadsetClientCall = null;
            } 
            bool3 = rejectCall((BluetoothDevice)bluetoothHeadsetClientCall);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 13:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothHeadsetClientCall = null;
            } 
            bool3 = holdCall((BluetoothDevice)bluetoothHeadsetClientCall);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 12:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothDevice = null;
            } 
            bool3 = acceptCall(bluetoothDevice, bluetoothHeadsetClientCall.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 11:
            bluetoothHeadsetClientCall.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bluetoothHeadsetClientCall.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bluetoothHeadsetClientCall);
            } else {
              bluetoothHeadsetClientCall = null;
            } 
            bundle1 = getCurrentAgEvents((BluetoothDevice)bluetoothHeadsetClientCall);
            param1Parcel2.writeNoException();
            if (bundle1 != null) {
              param1Parcel2.writeInt(1);
              bundle1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 10:
            bundle1.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (bundle1.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)bundle1);
            } else {
              bundle1 = null;
            } 
            list = getCurrentCalls((BluetoothDevice)bundle1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 9:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool3 = stopVoiceRecognition((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 8:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            bool3 = startVoiceRecognition((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 7:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            j = getConnectionPolicy((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 6:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (list.readInt() != 0) {
              bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              bluetoothDevice = null;
            } 
            bool2 = setConnectionPolicy(bluetoothDevice, list.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 5:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            if (list.readInt() != 0) {
              BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            i = getConnectionState((BluetoothDevice)list);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 4:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            list = (List)getDevicesMatchingConnectionStates(list.createIntArray());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 3:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
            list = (List)getConnectedDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 2:
            list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
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
        list.enforceInterface("android.bluetooth.IBluetoothHeadsetClient");
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
      param1Parcel2.writeString("android.bluetooth.IBluetoothHeadsetClient");
      return true;
    }
    
    private static class Proxy implements IBluetoothHeadsetClient {
      public static IBluetoothHeadsetClient sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public boolean acceptCall(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().acceptCall(param2BluetoothDevice, param2Int);
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
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public boolean connect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().connect(param2BluetoothDevice);
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
      
      public boolean connectAudio(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().connectAudio(param2BluetoothDevice);
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
      
      public BluetoothHeadsetClientCall dial(BluetoothDevice param2BluetoothDevice, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().dial(param2BluetoothDevice, param2String); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            BluetoothHeadsetClientCall bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall)BluetoothHeadsetClientCall.CREATOR.createFromParcel(parcel2);
          } else {
            param2BluetoothDevice = null;
          } 
          return (BluetoothHeadsetClientCall)param2BluetoothDevice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean disconnect(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().disconnect(param2BluetoothDevice);
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
      
      public boolean disconnectAudio(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().disconnectAudio(param2BluetoothDevice);
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
      
      public boolean enterPrivateMode(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().enterPrivateMode(param2BluetoothDevice, param2Int);
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
      
      public boolean explicitCallTransfer(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().explicitCallTransfer(param2BluetoothDevice);
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
      
      public boolean getAudioRouteAllowed(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().getAudioRouteAllowed(param2BluetoothDevice);
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
      
      public int getAudioState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getAudioState(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getConnectedDevices(); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getConnectionPolicy(param2BluetoothDevice); 
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Bundle getCurrentAgEvents(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getCurrentAgEvents(param2BluetoothDevice); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
          } else {
            param2BluetoothDevice = null;
          } 
          return (Bundle)param2BluetoothDevice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Bundle getCurrentAgFeatures(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getCurrentAgFeatures(param2BluetoothDevice); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
          } else {
            param2BluetoothDevice = null;
          } 
          return (Bundle)param2BluetoothDevice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getCurrentCalls(param2BluetoothDevice); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothHeadsetClientCall.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetClient.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
          parcel2.readException();
          return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothHeadsetClient";
      }
      
      public boolean getLastVoiceTagNumber(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().getLastVoiceTagNumber(param2BluetoothDevice);
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
      
      public boolean holdCall(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().holdCall(param2BluetoothDevice);
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
      
      public boolean rejectCall(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().rejectCall(param2BluetoothDevice);
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
      
      public boolean sendDTMF(BluetoothDevice param2BluetoothDevice, byte param2Byte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeByte(param2Byte);
          if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().sendDTMF(param2BluetoothDevice, param2Byte);
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
      
      public boolean sendVendorAtCommand(BluetoothDevice param2BluetoothDevice, int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().sendVendorAtCommand(param2BluetoothDevice, param2Int, param2String);
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
      
      public void setAudioRouteAllowed(BluetoothDevice param2BluetoothDevice, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!param2Boolean)
            bool = false; 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            IBluetoothHeadsetClient.Stub.getDefaultImpl().setAudioRouteAllowed(param2BluetoothDevice, param2Boolean);
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
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().setConnectionPolicy(param2BluetoothDevice, param2Int);
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
      
      public boolean startVoiceRecognition(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().startVoiceRecognition(param2BluetoothDevice);
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
      
      public boolean stopVoiceRecognition(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().stopVoiceRecognition(param2BluetoothDevice);
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
      
      public boolean terminateCall(BluetoothDevice param2BluetoothDevice, BluetoothHeadsetClientCall param2BluetoothHeadsetClientCall) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
          boolean bool = true;
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2BluetoothHeadsetClientCall != null) {
            parcel1.writeInt(1);
            param2BluetoothHeadsetClientCall.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().terminateCall(param2BluetoothDevice, param2BluetoothHeadsetClientCall);
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
  
  private static class Proxy implements IBluetoothHeadsetClient {
    public static IBluetoothHeadsetClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public boolean acceptCall(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().acceptCall(param1BluetoothDevice, param1Int);
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
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean connect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().connect(param1BluetoothDevice);
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
    
    public boolean connectAudio(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().connectAudio(param1BluetoothDevice);
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
    
    public BluetoothHeadsetClientCall dial(BluetoothDevice param1BluetoothDevice, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().dial(param1BluetoothDevice, param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          BluetoothHeadsetClientCall bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall)BluetoothHeadsetClientCall.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (BluetoothHeadsetClientCall)param1BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean disconnect(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().disconnect(param1BluetoothDevice);
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
    
    public boolean disconnectAudio(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().disconnectAudio(param1BluetoothDevice);
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
    
    public boolean enterPrivateMode(BluetoothDevice param1BluetoothDevice, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().enterPrivateMode(param1BluetoothDevice, param1Int);
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
    
    public boolean explicitCallTransfer(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().explicitCallTransfer(param1BluetoothDevice);
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
    
    public boolean getAudioRouteAllowed(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().getAudioRouteAllowed(param1BluetoothDevice);
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
    
    public int getAudioState(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getAudioState(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getConnectedDevices(); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getConnectionPolicy(param1BluetoothDevice); 
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getConnectionState(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getCurrentAgEvents(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getCurrentAgEvents(param1BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (Bundle)param1BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getCurrentAgFeatures(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getCurrentAgFeatures(param1BluetoothDevice); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (Bundle)param1BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getCurrentCalls(param1BluetoothDevice); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothHeadsetClientCall.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetClient.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param1ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothHeadsetClient";
    }
    
    public boolean getLastVoiceTagNumber(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().getLastVoiceTagNumber(param1BluetoothDevice);
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
    
    public boolean holdCall(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().holdCall(param1BluetoothDevice);
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
    
    public boolean rejectCall(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().rejectCall(param1BluetoothDevice);
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
    
    public boolean sendDTMF(BluetoothDevice param1BluetoothDevice, byte param1Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByte(param1Byte);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().sendDTMF(param1BluetoothDevice, param1Byte);
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
    
    public boolean sendVendorAtCommand(BluetoothDevice param1BluetoothDevice, int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().sendVendorAtCommand(param1BluetoothDevice, param1Int, param1String);
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
    
    public void setAudioRouteAllowed(BluetoothDevice param1BluetoothDevice, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param1Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          IBluetoothHeadsetClient.Stub.getDefaultImpl().setAudioRouteAllowed(param1BluetoothDevice, param1Boolean);
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
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().setConnectionPolicy(param1BluetoothDevice, param1Int);
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
    
    public boolean startVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().startVoiceRecognition(param1BluetoothDevice);
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
    
    public boolean stopVoiceRecognition(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().stopVoiceRecognition(param1BluetoothDevice);
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
    
    public boolean terminateCall(BluetoothDevice param1BluetoothDevice, BluetoothHeadsetClientCall param1BluetoothHeadsetClientCall) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetClient");
        boolean bool = true;
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1BluetoothHeadsetClientCall != null) {
          parcel1.writeInt(1);
          param1BluetoothHeadsetClientCall.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBluetoothHeadsetClient.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetClient.Stub.getDefaultImpl().terminateCall(param1BluetoothDevice, param1BluetoothHeadsetClientCall);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadsetClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */