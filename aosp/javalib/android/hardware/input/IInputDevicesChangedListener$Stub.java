package android.hardware.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IInputDevicesChangedListener {
  private static final String DESCRIPTOR = "android.hardware.input.IInputDevicesChangedListener";
  
  static final int TRANSACTION_onInputDevicesChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.input.IInputDevicesChangedListener");
  }
  
  public static IInputDevicesChangedListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.input.IInputDevicesChangedListener");
    return (iInterface != null && iInterface instanceof IInputDevicesChangedListener) ? (IInputDevicesChangedListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IInputDevicesChangedListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onInputDevicesChanged";
  }
  
  public static boolean setDefaultImpl(IInputDevicesChangedListener paramIInputDevicesChangedListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIInputDevicesChangedListener != null) {
        Proxy.sDefaultImpl = paramIInputDevicesChangedListener;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.input.IInputDevicesChangedListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.input.IInputDevicesChangedListener");
    onInputDevicesChanged(paramParcel1.createIntArray());
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputDevicesChangedListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */