package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiMhlVendorCommandListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
  
  static final int TRANSACTION_onReceived = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiMhlVendorCommandListener");
  }
  
  public static IHdmiMhlVendorCommandListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
    return (iInterface != null && iInterface instanceof IHdmiMhlVendorCommandListener) ? (IHdmiMhlVendorCommandListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiMhlVendorCommandListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onReceived";
  }
  
  public static boolean setDefaultImpl(IHdmiMhlVendorCommandListener paramIHdmiMhlVendorCommandListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiMhlVendorCommandListener != null) {
        Proxy.sDefaultImpl = paramIHdmiMhlVendorCommandListener;
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
      paramParcel2.writeString("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
    onReceived(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
    return true;
  }
  
  private static class Proxy implements IHdmiMhlVendorCommandListener {
    public static IHdmiMhlVendorCommandListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
    }
    
    public void onReceived(int param2Int1, int param2Int2, int param2Int3, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        parcel.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiMhlVendorCommandListener.Stub.getDefaultImpl() != null) {
          IHdmiMhlVendorCommandListener.Stub.getDefaultImpl().onReceived(param2Int1, param2Int2, param2Int3, param2ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiMhlVendorCommandListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */