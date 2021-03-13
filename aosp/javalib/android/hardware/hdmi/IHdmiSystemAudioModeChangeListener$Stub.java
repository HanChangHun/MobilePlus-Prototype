package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiSystemAudioModeChangeListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiSystemAudioModeChangeListener";
  
  static final int TRANSACTION_onStatusChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
  }
  
  public static IHdmiSystemAudioModeChangeListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
    return (iInterface != null && iInterface instanceof IHdmiSystemAudioModeChangeListener) ? (IHdmiSystemAudioModeChangeListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiSystemAudioModeChangeListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onStatusChanged";
  }
  
  public static boolean setDefaultImpl(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiSystemAudioModeChangeListener != null) {
        Proxy.sDefaultImpl = paramIHdmiSystemAudioModeChangeListener;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onStatusChanged(bool);
    return true;
  }
  
  private static class Proxy implements IHdmiSystemAudioModeChangeListener {
    public static IHdmiSystemAudioModeChangeListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiSystemAudioModeChangeListener";
    }
    
    public void onStatusChanged(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiSystemAudioModeChangeListener.Stub.getDefaultImpl() != null) {
          IHdmiSystemAudioModeChangeListener.Stub.getDefaultImpl().onStatusChanged(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiSystemAudioModeChangeListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */