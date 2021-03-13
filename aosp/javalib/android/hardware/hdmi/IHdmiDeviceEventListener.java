package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiDeviceEventListener extends IInterface {
  void onStatusChanged(HdmiDeviceInfo paramHdmiDeviceInfo, int paramInt) throws RemoteException;
  
  public static class Default implements IHdmiDeviceEventListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onStatusChanged(HdmiDeviceInfo param1HdmiDeviceInfo, int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiDeviceEventListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiDeviceEventListener";
    
    static final int TRANSACTION_onStatusChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiDeviceEventListener");
    }
    
    public static IHdmiDeviceEventListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiDeviceEventListener");
      return (iInterface != null && iInterface instanceof IHdmiDeviceEventListener) ? (IHdmiDeviceEventListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiDeviceEventListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onStatusChanged";
    }
    
    public static boolean setDefaultImpl(IHdmiDeviceEventListener param1IHdmiDeviceEventListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiDeviceEventListener != null) {
          Proxy.sDefaultImpl = param1IHdmiDeviceEventListener;
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
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiDeviceEventListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiDeviceEventListener");
      if (param1Parcel1.readInt() != 0) {
        HdmiDeviceInfo hdmiDeviceInfo = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onStatusChanged((HdmiDeviceInfo)param1Parcel2, param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IHdmiDeviceEventListener {
      public static IHdmiDeviceEventListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.hdmi.IHdmiDeviceEventListener";
      }
      
      public void onStatusChanged(HdmiDeviceInfo param2HdmiDeviceInfo, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiDeviceEventListener");
          if (param2HdmiDeviceInfo != null) {
            parcel.writeInt(1);
            param2HdmiDeviceInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiDeviceEventListener.Stub.getDefaultImpl() != null) {
            IHdmiDeviceEventListener.Stub.getDefaultImpl().onStatusChanged(param2HdmiDeviceInfo, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IHdmiDeviceEventListener {
    public static IHdmiDeviceEventListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiDeviceEventListener";
    }
    
    public void onStatusChanged(HdmiDeviceInfo param1HdmiDeviceInfo, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiDeviceEventListener");
        if (param1HdmiDeviceInfo != null) {
          parcel.writeInt(1);
          param1HdmiDeviceInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiDeviceEventListener.Stub.getDefaultImpl() != null) {
          IHdmiDeviceEventListener.Stub.getDefaultImpl().onStatusChanged(param1HdmiDeviceInfo, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiDeviceEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */