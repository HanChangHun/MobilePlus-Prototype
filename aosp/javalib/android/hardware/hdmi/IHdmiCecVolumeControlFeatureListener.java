package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiCecVolumeControlFeatureListener extends IInterface {
  void onHdmiCecVolumeControlFeature(boolean paramBoolean) throws RemoteException;
  
  public static class Default implements IHdmiCecVolumeControlFeatureListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onHdmiCecVolumeControlFeature(boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiCecVolumeControlFeatureListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";
    
    static final int TRANSACTION_onHdmiCecVolumeControlFeature = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
    }
    
    public static IHdmiCecVolumeControlFeatureListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
      return (iInterface != null && iInterface instanceof IHdmiCecVolumeControlFeatureListener) ? (IHdmiCecVolumeControlFeatureListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiCecVolumeControlFeatureListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onHdmiCecVolumeControlFeature";
    }
    
    public static boolean setDefaultImpl(IHdmiCecVolumeControlFeatureListener param1IHdmiCecVolumeControlFeatureListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiCecVolumeControlFeatureListener != null) {
          Proxy.sDefaultImpl = param1IHdmiCecVolumeControlFeatureListener;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
      if (param1Parcel1.readInt() != 0) {
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
  
  private static class Proxy implements IHdmiCecVolumeControlFeatureListener {
    public static IHdmiCecVolumeControlFeatureListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener";
    }
    
    public void onHdmiCecVolumeControlFeature(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IHdmiCecVolumeControlFeatureListener.Stub.getDefaultImpl() != null) {
          IHdmiCecVolumeControlFeatureListener.Stub.getDefaultImpl().onHdmiCecVolumeControlFeature(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiCecVolumeControlFeatureListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */