package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiDeviceEventListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiDeviceEventListener";
  
  static final int TRANSACTION_onStatusChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiDeviceEventListener");
  }
  
  public static IHdmiDeviceEventListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiDeviceEventListener");
    return (iInterface != null && iInterface instanceof IHdmiDeviceEventListener) ? (IHdmiDeviceEventListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiDeviceEventListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onStatusChanged";
  }
  
  public static boolean setDefaultImpl(IHdmiDeviceEventListener paramIHdmiDeviceEventListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiDeviceEventListener != null) {
        Proxy.sDefaultImpl = paramIHdmiDeviceEventListener;
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
      paramParcel2.writeString("android.hardware.hdmi.IHdmiDeviceEventListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiDeviceEventListener");
    if (paramParcel1.readInt() != 0) {
      HdmiDeviceInfo hdmiDeviceInfo = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onStatusChanged((HdmiDeviceInfo)paramParcel2, paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IHdmiDeviceEventListener {
    public static IHdmiDeviceEventListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiDeviceEventListener";
    }
    
    public void onStatusChanged(HdmiDeviceInfo param2HdmiDeviceInfo, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiDeviceEventListener");
        if (param2HdmiDeviceInfo != null) {
          parcel.writeInt(1);
          param2HdmiDeviceInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiDeviceEventListener.Stub.getDefaultImpl() != null) {
          IHdmiDeviceEventListener.Stub.getDefaultImpl().onStatusChanged(param2HdmiDeviceInfo, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiDeviceEventListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */