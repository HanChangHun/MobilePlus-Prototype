package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiMhlVendorCommandListener extends IInterface {
  void onReceived(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException;
  
  public static class Default implements IHdmiMhlVendorCommandListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onReceived(int param1Int1, int param1Int2, int param1Int3, byte[] param1ArrayOfbyte) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiMhlVendorCommandListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
    
    static final int TRANSACTION_onReceived = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiMhlVendorCommandListener");
    }
    
    public static IHdmiMhlVendorCommandListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
      return (iInterface != null && iInterface instanceof IHdmiMhlVendorCommandListener) ? (IHdmiMhlVendorCommandListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiMhlVendorCommandListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onReceived";
    }
    
    public static boolean setDefaultImpl(IHdmiMhlVendorCommandListener param1IHdmiMhlVendorCommandListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiMhlVendorCommandListener != null) {
          Proxy.sDefaultImpl = param1IHdmiMhlVendorCommandListener;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
      onReceived(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.createByteArray());
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
  
  private static class Proxy implements IHdmiMhlVendorCommandListener {
    public static IHdmiMhlVendorCommandListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiMhlVendorCommandListener";
    }
    
    public void onReceived(int param1Int1, int param1Int2, int param1Int3, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiMhlVendorCommandListener");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiMhlVendorCommandListener.Stub.getDefaultImpl() != null) {
          IHdmiMhlVendorCommandListener.Stub.getDefaultImpl().onReceived(param1Int1, param1Int2, param1Int3, param1ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiMhlVendorCommandListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */