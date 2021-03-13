package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IHdmiRecordListener {
  public static IHdmiRecordListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.hdmi.IHdmiRecordListener";
  }
  
  public byte[] getOneTouchRecordSource(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null)
        return IHdmiRecordListener.Stub.getDefaultImpl().getOneTouchRecordSource(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onClearTimerRecordingResult(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
        IHdmiRecordListener.Stub.getDefaultImpl().onClearTimerRecordingResult(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onOneTouchRecordResult(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
        IHdmiRecordListener.Stub.getDefaultImpl().onOneTouchRecordResult(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onTimerRecordingResult(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
        IHdmiRecordListener.Stub.getDefaultImpl().onTimerRecordingResult(paramInt1, paramInt2);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiRecordListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */