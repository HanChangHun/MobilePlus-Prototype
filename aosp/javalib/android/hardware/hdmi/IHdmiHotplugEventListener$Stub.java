package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiHotplugEventListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiHotplugEventListener";
  
  static final int TRANSACTION_onReceived = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiHotplugEventListener");
  }
  
  public static IHdmiHotplugEventListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiHotplugEventListener");
    return (iInterface != null && iInterface instanceof IHdmiHotplugEventListener) ? (IHdmiHotplugEventListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiHotplugEventListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onReceived";
  }
  
  public static boolean setDefaultImpl(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiHotplugEventListener != null) {
        Proxy.sDefaultImpl = paramIHdmiHotplugEventListener;
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
      paramParcel2.writeString("android.hardware.hdmi.IHdmiHotplugEventListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiHotplugEventListener");
    if (paramParcel1.readInt() != 0) {
      HdmiHotplugEvent hdmiHotplugEvent = (HdmiHotplugEvent)HdmiHotplugEvent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onReceived((HdmiHotplugEvent)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IHdmiHotplugEventListener {
    public static IHdmiHotplugEventListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiHotplugEventListener";
    }
    
    public void onReceived(HdmiHotplugEvent param2HdmiHotplugEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiHotplugEventListener");
        if (param2HdmiHotplugEvent != null) {
          parcel.writeInt(1);
          param2HdmiHotplugEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiHotplugEventListener.Stub.getDefaultImpl() != null) {
          IHdmiHotplugEventListener.Stub.getDefaultImpl().onReceived(param2HdmiHotplugEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiHotplugEventListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */