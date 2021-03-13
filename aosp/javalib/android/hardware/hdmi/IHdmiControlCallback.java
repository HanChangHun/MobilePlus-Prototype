package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiControlCallback extends IInterface {
  void onComplete(int paramInt) throws RemoteException;
  
  public static class Default implements IHdmiControlCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onComplete(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiControlCallback {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiControlCallback";
    
    static final int TRANSACTION_onComplete = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiControlCallback");
    }
    
    public static IHdmiControlCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiControlCallback");
      return (iInterface != null && iInterface instanceof IHdmiControlCallback) ? (IHdmiControlCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiControlCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onComplete";
    }
    
    public static boolean setDefaultImpl(IHdmiControlCallback param1IHdmiControlCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiControlCallback != null) {
          Proxy.sDefaultImpl = param1IHdmiControlCallback;
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
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiControlCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiControlCallback");
      onComplete(param1Parcel1.readInt());
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
  
  private static class Proxy implements IHdmiControlCallback {
    public static IHdmiControlCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiControlCallback";
    }
    
    public void onComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiControlCallback.Stub.getDefaultImpl() != null) {
          IHdmiControlCallback.Stub.getDefaultImpl().onComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */