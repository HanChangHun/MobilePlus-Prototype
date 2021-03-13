package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiControlCallback {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiControlCallback";
  
  static final int TRANSACTION_onComplete = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiControlCallback");
  }
  
  public static IHdmiControlCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiControlCallback");
    return (iInterface != null && iInterface instanceof IHdmiControlCallback) ? (IHdmiControlCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiControlCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onComplete";
  }
  
  public static boolean setDefaultImpl(IHdmiControlCallback paramIHdmiControlCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiControlCallback != null) {
        Proxy.sDefaultImpl = paramIHdmiControlCallback;
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
      paramParcel2.writeString("android.hardware.hdmi.IHdmiControlCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlCallback");
    onComplete(paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IHdmiControlCallback {
    public static IHdmiControlCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiControlCallback";
    }
    
    public void onComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiControlCallback.Stub.getDefaultImpl() != null) {
          IHdmiControlCallback.Stub.getDefaultImpl().onComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */