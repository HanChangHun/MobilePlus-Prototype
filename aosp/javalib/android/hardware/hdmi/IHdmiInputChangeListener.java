package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiInputChangeListener extends IInterface {
  void onChanged(HdmiDeviceInfo paramHdmiDeviceInfo) throws RemoteException;
  
  public static class Default implements IHdmiInputChangeListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onChanged(HdmiDeviceInfo param1HdmiDeviceInfo) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiInputChangeListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiInputChangeListener";
    
    static final int TRANSACTION_onChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiInputChangeListener");
    }
    
    public static IHdmiInputChangeListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiInputChangeListener");
      return (iInterface != null && iInterface instanceof IHdmiInputChangeListener) ? (IHdmiInputChangeListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiInputChangeListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onChanged";
    }
    
    public static boolean setDefaultImpl(IHdmiInputChangeListener param1IHdmiInputChangeListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiInputChangeListener != null) {
          Proxy.sDefaultImpl = param1IHdmiInputChangeListener;
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
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiInputChangeListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiInputChangeListener");
      if (param1Parcel1.readInt() != 0) {
        HdmiDeviceInfo hdmiDeviceInfo = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onChanged((HdmiDeviceInfo)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IHdmiInputChangeListener {
      public static IHdmiInputChangeListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.hdmi.IHdmiInputChangeListener";
      }
      
      public void onChanged(HdmiDeviceInfo param2HdmiDeviceInfo) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiInputChangeListener");
          if (param2HdmiDeviceInfo != null) {
            parcel.writeInt(1);
            param2HdmiDeviceInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiInputChangeListener.Stub.getDefaultImpl() != null) {
            IHdmiInputChangeListener.Stub.getDefaultImpl().onChanged(param2HdmiDeviceInfo);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IHdmiInputChangeListener {
    public static IHdmiInputChangeListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiInputChangeListener";
    }
    
    public void onChanged(HdmiDeviceInfo param1HdmiDeviceInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiInputChangeListener");
        if (param1HdmiDeviceInfo != null) {
          parcel.writeInt(1);
          param1HdmiDeviceInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiInputChangeListener.Stub.getDefaultImpl() != null) {
          IHdmiInputChangeListener.Stub.getDefaultImpl().onChanged(param1HdmiDeviceInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiInputChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */