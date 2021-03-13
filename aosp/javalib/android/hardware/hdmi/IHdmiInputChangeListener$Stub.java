package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiInputChangeListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiInputChangeListener";
  
  static final int TRANSACTION_onChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiInputChangeListener");
  }
  
  public static IHdmiInputChangeListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiInputChangeListener");
    return (iInterface != null && iInterface instanceof IHdmiInputChangeListener) ? (IHdmiInputChangeListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiInputChangeListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onChanged";
  }
  
  public static boolean setDefaultImpl(IHdmiInputChangeListener paramIHdmiInputChangeListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiInputChangeListener != null) {
        Proxy.sDefaultImpl = paramIHdmiInputChangeListener;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.hdmi.IHdmiInputChangeListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiInputChangeListener");
    if (paramParcel1.readInt() != 0) {
      HdmiDeviceInfo hdmiDeviceInfo = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onChanged((HdmiDeviceInfo)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IHdmiInputChangeListener {
    public static IHdmiInputChangeListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiInputChangeListener";
    }
    
    public void onChanged(HdmiDeviceInfo param2HdmiDeviceInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiInputChangeListener");
        if (param2HdmiDeviceInfo != null) {
          parcel.writeInt(1);
          param2HdmiDeviceInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiInputChangeListener.Stub.getDefaultImpl() != null) {
          IHdmiInputChangeListener.Stub.getDefaultImpl().onChanged(param2HdmiDeviceInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiInputChangeListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */