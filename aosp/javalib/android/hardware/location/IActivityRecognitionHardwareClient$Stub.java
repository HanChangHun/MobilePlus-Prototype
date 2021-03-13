package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IActivityRecognitionHardwareClient {
  private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareClient";
  
  static final int TRANSACTION_onAvailabilityChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareClient");
  }
  
  public static IActivityRecognitionHardwareClient asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareClient");
    return (iInterface != null && iInterface instanceof IActivityRecognitionHardwareClient) ? (IActivityRecognitionHardwareClient)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityRecognitionHardwareClient getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onAvailabilityChanged";
  }
  
  public static boolean setDefaultImpl(IActivityRecognitionHardwareClient paramIActivityRecognitionHardwareClient) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityRecognitionHardwareClient != null) {
        Proxy.sDefaultImpl = paramIActivityRecognitionHardwareClient;
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
      paramParcel2.writeString("android.hardware.location.IActivityRecognitionHardwareClient");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardwareClient");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onAvailabilityChanged(bool, IActivityRecognitionHardware.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IActivityRecognitionHardwareClient {
    public static IActivityRecognitionHardwareClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardwareClient";
    }
    
    public void onAvailabilityChanged(boolean param2Boolean, IActivityRecognitionHardware param2IActivityRecognitionHardware) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareClient");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param2IActivityRecognitionHardware != null) {
          iBinder = param2IActivityRecognitionHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IActivityRecognitionHardwareClient.Stub.getDefaultImpl() != null) {
          IActivityRecognitionHardwareClient.Stub.getDefaultImpl().onAvailabilityChanged(param2Boolean, param2IActivityRecognitionHardware);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareClient$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */