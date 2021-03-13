package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiCecVolumeControlFeatureListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";
  
  static final int TRANSACTION_onHdmiCecVolumeControlFeature = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
  }
  
  public static IHdmiCecVolumeControlFeatureListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
    return (iInterface != null && iInterface instanceof IHdmiCecVolumeControlFeatureListener) ? (IHdmiCecVolumeControlFeatureListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiCecVolumeControlFeatureListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onHdmiCecVolumeControlFeature";
  }
  
  public static boolean setDefaultImpl(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiCecVolumeControlFeatureListener != null) {
        Proxy.sDefaultImpl = paramIHdmiCecVolumeControlFeatureListener;
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
      paramParcel2.writeString("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onHdmiCecVolumeControlFeature(bool);
    return true;
  }
  
  private static class Proxy implements IHdmiCecVolumeControlFeatureListener {
    public static IHdmiCecVolumeControlFeatureListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";
    }
    
    public void onHdmiCecVolumeControlFeature(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiCecVolumeControlFeatureListener.Stub.getDefaultImpl() != null) {
          IHdmiCecVolumeControlFeatureListener.Stub.getDefaultImpl().onHdmiCecVolumeControlFeature(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiCecVolumeControlFeatureListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */