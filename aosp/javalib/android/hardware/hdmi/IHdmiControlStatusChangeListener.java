package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiControlStatusChangeListener extends IInterface {
  void onStatusChange(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException;
  
  public static class Default implements IHdmiControlStatusChangeListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onStatusChange(boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiControlStatusChangeListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiControlStatusChangeListener";
    
    static final int TRANSACTION_onStatusChange = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiControlStatusChangeListener");
    }
    
    public static IHdmiControlStatusChangeListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiControlStatusChangeListener");
      return (iInterface != null && iInterface instanceof IHdmiControlStatusChangeListener) ? (IHdmiControlStatusChangeListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiControlStatusChangeListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onStatusChange";
    }
    
    public static boolean setDefaultImpl(IHdmiControlStatusChangeListener param1IHdmiControlStatusChangeListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiControlStatusChangeListener != null) {
          Proxy.sDefaultImpl = param1IHdmiControlStatusChangeListener;
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
      boolean bool2;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiControlStatusChangeListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiControlStatusChangeListener");
      param1Int1 = param1Parcel1.readInt();
      boolean bool1 = false;
      if (param1Int1 != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (param1Parcel1.readInt() != 0)
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
  
  private static class Proxy implements IHdmiControlStatusChangeListener {
    public static IHdmiControlStatusChangeListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiControlStatusChangeListener";
    }
    
    public void onStatusChange(boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlStatusChangeListener");
        boolean bool1 = false;
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        boolean bool2 = bool1;
        if (param1Boolean2)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiControlStatusChangeListener.Stub.getDefaultImpl() != null) {
          IHdmiControlStatusChangeListener.Stub.getDefaultImpl().onStatusChange(param1Boolean1, param1Boolean2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlStatusChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */