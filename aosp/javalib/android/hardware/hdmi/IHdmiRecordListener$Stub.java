package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiRecordListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiRecordListener";
  
  static final int TRANSACTION_getOneTouchRecordSource = 1;
  
  static final int TRANSACTION_onClearTimerRecordingResult = 4;
  
  static final int TRANSACTION_onOneTouchRecordResult = 2;
  
  static final int TRANSACTION_onTimerRecordingResult = 3;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiRecordListener");
  }
  
  public static IHdmiRecordListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiRecordListener");
    return (iInterface != null && iInterface instanceof IHdmiRecordListener) ? (IHdmiRecordListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiRecordListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : "onClearTimerRecordingResult") : "onTimerRecordingResult") : "onOneTouchRecordResult") : "getOneTouchRecordSource";
  }
  
  public static boolean setDefaultImpl(IHdmiRecordListener paramIHdmiRecordListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiRecordListener != null) {
        Proxy.sDefaultImpl = paramIHdmiRecordListener;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 1598968902)
              return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
            paramParcel2.writeString("android.hardware.hdmi.IHdmiRecordListener");
            return true;
          } 
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
          onClearTimerRecordingResult(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
        onTimerRecordingResult(paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
      onOneTouchRecordResult(paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
    byte[] arrayOfByte = getOneTouchRecordSource(paramParcel1.readInt());
    paramParcel2.writeNoException();
    paramParcel2.writeByteArray(arrayOfByte);
    return true;
  }
  
  private static class Proxy implements IHdmiRecordListener {
    public static IHdmiRecordListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiRecordListener";
    }
    
    public byte[] getOneTouchRecordSource(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null)
          return IHdmiRecordListener.Stub.getDefaultImpl().getOneTouchRecordSource(param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onClearTimerRecordingResult(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
          IHdmiRecordListener.Stub.getDefaultImpl().onClearTimerRecordingResult(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onOneTouchRecordResult(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
          IHdmiRecordListener.Stub.getDefaultImpl().onOneTouchRecordResult(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onTimerRecordingResult(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
          IHdmiRecordListener.Stub.getDefaultImpl().onTimerRecordingResult(param2Int1, param2Int2);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiRecordListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */