package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiVendorCommandListener extends IInterface {
  void onControlStateChanged(boolean paramBoolean, int paramInt) throws RemoteException;
  
  void onReceived(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException;
  
  public static class Default implements IHdmiVendorCommandListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onControlStateChanged(boolean param1Boolean, int param1Int) throws RemoteException {}
    
    public void onReceived(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte, boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiVendorCommandListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiVendorCommandListener";
    
    static final int TRANSACTION_onControlStateChanged = 2;
    
    static final int TRANSACTION_onReceived = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiVendorCommandListener");
    }
    
    public static IHdmiVendorCommandListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
      return (iInterface != null && iInterface instanceof IHdmiVendorCommandListener) ? (IHdmiVendorCommandListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiVendorCommandListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onControlStateChanged") : "onReceived";
    }
    
    public static boolean setDefaultImpl(IHdmiVendorCommandListener param1IHdmiVendorCommandListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiVendorCommandListener != null) {
          Proxy.sDefaultImpl = param1IHdmiVendorCommandListener;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      boolean bool1 = false;
      boolean bool2 = false;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.hardware.hdmi.IHdmiVendorCommandListener");
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
        if (param1Parcel1.readInt() != 0)
          bool2 = true; 
        onControlStateChanged(bool2, param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
      param1Int1 = param1Parcel1.readInt();
      param1Int2 = param1Parcel1.readInt();
      byte[] arrayOfByte = param1Parcel1.createByteArray();
      bool2 = bool1;
      if (param1Parcel1.readInt() != 0)
        bool2 = true; 
      onReceived(param1Int1, param1Int2, arrayOfByte, bool2);
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements IHdmiVendorCommandListener {
    public static IHdmiVendorCommandListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiVendorCommandListener";
    }
    
    public void onControlStateChanged(boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiVendorCommandListener");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiVendorCommandListener.Stub.getDefaultImpl() != null) {
          IHdmiVendorCommandListener.Stub.getDefaultImpl().onControlStateChanged(param1Boolean, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onReceived(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiVendorCommandListener");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiVendorCommandListener.Stub.getDefaultImpl() != null) {
          IHdmiVendorCommandListener.Stub.getDefaultImpl().onReceived(param1Int1, param1Int2, param1ArrayOfbyte, param1Boolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiVendorCommandListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */