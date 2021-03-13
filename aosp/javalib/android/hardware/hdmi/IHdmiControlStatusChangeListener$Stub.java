package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiControlStatusChangeListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiControlStatusChangeListener";
  
  static final int TRANSACTION_onStatusChange = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiControlStatusChangeListener");
  }
  
  public static IHdmiControlStatusChangeListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiControlStatusChangeListener");
    return (iInterface != null && iInterface instanceof IHdmiControlStatusChangeListener) ? (IHdmiControlStatusChangeListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiControlStatusChangeListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onStatusChange";
  }
  
  public static boolean setDefaultImpl(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiControlStatusChangeListener != null) {
        Proxy.sDefaultImpl = paramIHdmiControlStatusChangeListener;
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
    boolean bool2;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.hdmi.IHdmiControlStatusChangeListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlStatusChangeListener");
    paramInt1 = paramParcel1.readInt();
    boolean bool1 = false;
    if (paramInt1 != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel1.readInt() != 0)
      bool1 = true; 
    onStatusChange(bool2, bool1);
    return true;
  }
  
  private static class Proxy implements IHdmiControlStatusChangeListener {
    public static IHdmiControlStatusChangeListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiControlStatusChangeListener";
    }
    
    public void onStatusChange(boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlStatusChangeListener");
        boolean bool1 = false;
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        boolean bool2 = bool1;
        if (param2Boolean2)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiControlStatusChangeListener.Stub.getDefaultImpl() != null) {
          IHdmiControlStatusChangeListener.Stub.getDefaultImpl().onStatusChange(param2Boolean1, param2Boolean2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlStatusChangeListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */