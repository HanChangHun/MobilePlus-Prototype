package android.hardware.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IInputDevicesChangedListener extends IInterface {
  void onInputDevicesChanged(int[] paramArrayOfint) throws RemoteException;
  
  public static class Default implements IInputDevicesChangedListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onInputDevicesChanged(int[] param1ArrayOfint) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IInputDevicesChangedListener {
    private static final String DESCRIPTOR = "android.hardware.input.IInputDevicesChangedListener";
    
    static final int TRANSACTION_onInputDevicesChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.input.IInputDevicesChangedListener");
    }
    
    public static IInputDevicesChangedListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.input.IInputDevicesChangedListener");
      return (iInterface != null && iInterface instanceof IInputDevicesChangedListener) ? (IInputDevicesChangedListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IInputDevicesChangedListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onInputDevicesChanged";
    }
    
    public static boolean setDefaultImpl(IInputDevicesChangedListener param1IInputDevicesChangedListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IInputDevicesChangedListener != null) {
          Proxy.sDefaultImpl = param1IInputDevicesChangedListener;
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
        param1Parcel2.writeString("android.hardware.input.IInputDevicesChangedListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.input.IInputDevicesChangedListener");
      onInputDevicesChanged(param1Parcel1.createIntArray());
      return true;
    }
    
    private static class Proxy implements IInputDevicesChangedListener {
      public static IInputDevicesChangedListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.input.IInputDevicesChangedListener";
      }
      
      public void onInputDevicesChanged(int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.input.IInputDevicesChangedListener");
          parcel.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(1, parcel, null, 1) && IInputDevicesChangedListener.Stub.getDefaultImpl() != null) {
            IInputDevicesChangedListener.Stub.getDefaultImpl().onInputDevicesChanged(param2ArrayOfint);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IInputDevicesChangedListener {
    public static IInputDevicesChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.input.IInputDevicesChangedListener";
    }
    
    public void onInputDevicesChanged(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.input.IInputDevicesChangedListener");
        parcel.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(1, parcel, null, 1) && IInputDevicesChangedListener.Stub.getDefaultImpl() != null) {
          IInputDevicesChangedListener.Stub.getDefaultImpl().onInputDevicesChanged(param1ArrayOfint);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputDevicesChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */