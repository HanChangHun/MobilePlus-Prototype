package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiHotplugEventListener extends IInterface {
  void onReceived(HdmiHotplugEvent paramHdmiHotplugEvent) throws RemoteException;
  
  public static class Default implements IHdmiHotplugEventListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onReceived(HdmiHotplugEvent param1HdmiHotplugEvent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiHotplugEventListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiHotplugEventListener";
    
    static final int TRANSACTION_onReceived = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiHotplugEventListener");
    }
    
    public static IHdmiHotplugEventListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiHotplugEventListener");
      return (iInterface != null && iInterface instanceof IHdmiHotplugEventListener) ? (IHdmiHotplugEventListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiHotplugEventListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onReceived";
    }
    
    public static boolean setDefaultImpl(IHdmiHotplugEventListener param1IHdmiHotplugEventListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiHotplugEventListener != null) {
          Proxy.sDefaultImpl = param1IHdmiHotplugEventListener;
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
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiHotplugEventListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiHotplugEventListener");
      if (param1Parcel1.readInt() != 0) {
        HdmiHotplugEvent hdmiHotplugEvent = (HdmiHotplugEvent)HdmiHotplugEvent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onReceived((HdmiHotplugEvent)param1Parcel1);
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
  
  private static class Proxy implements IHdmiHotplugEventListener {
    public static IHdmiHotplugEventListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiHotplugEventListener";
    }
    
    public void onReceived(HdmiHotplugEvent param1HdmiHotplugEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiHotplugEventListener");
        if (param1HdmiHotplugEvent != null) {
          parcel.writeInt(1);
          param1HdmiHotplugEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiHotplugEventListener.Stub.getDefaultImpl() != null) {
          IHdmiHotplugEventListener.Stub.getDefaultImpl().onReceived(param1HdmiHotplugEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiHotplugEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */