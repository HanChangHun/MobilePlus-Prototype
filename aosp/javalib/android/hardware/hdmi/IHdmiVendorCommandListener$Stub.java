package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IHdmiVendorCommandListener {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiVendorCommandListener";
  
  static final int TRANSACTION_onControlStateChanged = 2;
  
  static final int TRANSACTION_onReceived = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiVendorCommandListener");
  }
  
  public static IHdmiVendorCommandListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
    return (iInterface != null && iInterface instanceof IHdmiVendorCommandListener) ? (IHdmiVendorCommandListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiVendorCommandListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onControlStateChanged") : "onReceived";
  }
  
  public static boolean setDefaultImpl(IHdmiVendorCommandListener paramIHdmiVendorCommandListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiVendorCommandListener != null) {
        Proxy.sDefaultImpl = paramIHdmiVendorCommandListener;
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
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.hardware.hdmi.IHdmiVendorCommandListener");
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
      if (paramParcel1.readInt() != 0)
        bool2 = true; 
      onControlStateChanged(bool2, paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
    paramInt1 = paramParcel1.readInt();
    paramInt2 = paramParcel1.readInt();
    byte[] arrayOfByte = paramParcel1.createByteArray();
    bool2 = bool1;
    if (paramParcel1.readInt() != 0)
      bool2 = true; 
    onReceived(paramInt1, paramInt2, arrayOfByte, bool2);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IHdmiVendorCommandListener {
    public static IHdmiVendorCommandListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiVendorCommandListener";
    }
    
    public void onControlStateChanged(boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiVendorCommandListener");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiVendorCommandListener.Stub.getDefaultImpl() != null) {
          IHdmiVendorCommandListener.Stub.getDefaultImpl().onControlStateChanged(param2Boolean, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onReceived(int param2Int1, int param2Int2, byte[] param2ArrayOfbyte, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiVendorCommandListener");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiVendorCommandListener.Stub.getDefaultImpl() != null) {
          IHdmiVendorCommandListener.Stub.getDefaultImpl().onReceived(param2Int1, param2Int2, param2ArrayOfbyte, param2Boolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiVendorCommandListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */