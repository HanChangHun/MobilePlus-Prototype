package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothHeadset {
  public static IBluetoothHeadset sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clccResponse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, String paramString, int paramInt5) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeInt(paramInt2);
          try {
            parcel1.writeInt(paramInt3);
            try {
              boolean bool;
              parcel1.writeInt(paramInt4);
              if (paramBoolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              try {
                parcel1.writeString(paramString);
                parcel1.writeInt(paramInt5);
                if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
                  IBluetoothHeadset.Stub.getDefaultImpl().clccResponse(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, paramString, paramInt5);
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
    throw paramString;
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().connect(paramBluetoothDevice);
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
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().disconnect(paramBluetoothDevice);
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
  
  public int getAudioState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
        return IBluetoothHeadset.Stub.getDefaultImpl().getAudioState(paramBluetoothDevice); 
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
  
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
        return IBluetoothHeadset.Stub.getDefaultImpl().getConnectionPolicy(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
        return IBluetoothHeadset.Stub.getDefaultImpl().getConnectionState(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
        return IBluetoothHeadset.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(paramArrayOfint); 
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
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null)
        return IBluetoothHeadset.Stub.getDefaultImpl().getPriority(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAudioConnected(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().isAudioConnected(paramBluetoothDevice);
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
  
  public void phoneStateChanged(int paramInt1, int paramInt2, int paramInt3, String paramString1, int paramInt4, String paramString2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      try {
        parcel.writeInt(paramInt1);
        try {
          parcel.writeInt(paramInt2);
          try {
            parcel.writeInt(paramInt3);
            try {
              parcel.writeString(paramString1);
              try {
                parcel.writeInt(paramInt4);
                try {
                  parcel.writeString(paramString2);
                  try {
                    if (!this.mRemote.transact(21, parcel, null, 1) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
                      IBluetoothHeadset.Stub.getDefaultImpl().phoneStateChanged(paramInt1, paramInt2, paramInt3, paramString1, paramInt4, paramString2);
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
    throw paramString1;
  }
  
  public boolean sendVendorSpecificResultCode(BluetoothDevice paramBluetoothDevice, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().sendVendorSpecificResultCode(paramBluetoothDevice, paramString1, paramString2);
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
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().setActiveDevice(paramBluetoothDevice);
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
  
  public void setAudioRouteAllowed(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        IBluetoothHeadset.Stub.getDefaultImpl().setAudioRouteAllowed(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().setConnectionPolicy(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setForceScoAudio(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        IBluetoothHeadset.Stub.getDefaultImpl().setForceScoAudio(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().setPriority(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
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
  
  public boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().startVoiceRecognition(paramBluetoothDevice);
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
  
  public boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadset.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadset.Stub.getDefaultImpl().stopVoiceRecognition(paramBluetoothDevice);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadset$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */