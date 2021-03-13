package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISensorPrivacyListener {
  private static final String DESCRIPTOR = "android.hardware.ISensorPrivacyListener";
  
  static final int TRANSACTION_onSensorPrivacyChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.ISensorPrivacyListener");
  }
  
  public static ISensorPrivacyListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ISensorPrivacyListener");
    return (iInterface != null && iInterface instanceof ISensorPrivacyListener) ? (ISensorPrivacyListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISensorPrivacyListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onSensorPrivacyChanged";
  }
  
  public static boolean setDefaultImpl(ISensorPrivacyListener paramISensorPrivacyListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISensorPrivacyListener != null) {
        Proxy.sDefaultImpl = paramISensorPrivacyListener;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.ISensorPrivacyListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.ISensorPrivacyListener");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onSensorPrivacyChanged(bool);
    return true;
  }
  
  private static class Proxy implements ISensorPrivacyListener {
    public static ISensorPrivacyListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ISensorPrivacyListener";
    }
    
    public void onSensorPrivacyChanged(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.ISensorPrivacyListener");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISensorPrivacyListener.Stub.getDefaultImpl() != null) {
          ISensorPrivacyListener.Stub.getDefaultImpl().onSensorPrivacyChanged(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */