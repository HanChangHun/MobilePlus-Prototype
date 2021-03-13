package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothHeadsetPhone {
  public static IBluetoothHeadsetPhone sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean answerCall() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(1, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().answerCall();
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
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cdmaSetSecondCallState(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        IBluetoothHeadsetPhone.Stub.getDefaultImpl().cdmaSetSecondCallState(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cdmaSwapSecondCallState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        IBluetoothHeadsetPhone.Stub.getDefaultImpl().cdmaSwapSecondCallState();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothHeadsetPhone";
  }
  
  public String getNetworkOperator() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null)
        return IBluetoothHeadsetPhone.Stub.getDefaultImpl().getNetworkOperator(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getSubscriberNumber() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null)
        return IBluetoothHeadsetPhone.Stub.getDefaultImpl().getSubscriberNumber(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hangupCall() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().hangupCall();
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
  
  public boolean listCurrentCalls() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().listCurrentCalls();
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
  
  public boolean processChld(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().processChld(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean queryPhoneState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().queryPhoneState();
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
  
  public boolean sendDtmf(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().sendDtmf(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
        IBluetoothHeadsetPhone.Stub.getDefaultImpl().updateBtHandsfreeAfterRadioTechnologyChange();
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadsetPhone$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */