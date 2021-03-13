package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISerialManager {
  private static final String DESCRIPTOR = "android.hardware.ISerialManager";
  
  static final int TRANSACTION_getSerialPorts = 1;
  
  static final int TRANSACTION_openSerialPort = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.ISerialManager");
  }
  
  public static ISerialManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ISerialManager");
    return (iInterface != null && iInterface instanceof ISerialManager) ? (ISerialManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISerialManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "openSerialPort") : "getSerialPorts";
  }
  
  public static boolean setDefaultImpl(ISerialManager paramISerialManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISerialManager != null) {
        Proxy.sDefaultImpl = paramISerialManager;
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
    ParcelFileDescriptor parcelFileDescriptor;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.hardware.ISerialManager");
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.ISerialManager");
      parcelFileDescriptor = openSerialPort(paramParcel1.readString());
      paramParcel2.writeNoException();
      if (parcelFileDescriptor != null) {
        paramParcel2.writeInt(1);
        parcelFileDescriptor.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    parcelFileDescriptor.enforceInterface("android.hardware.ISerialManager");
    String[] arrayOfString = getSerialPorts();
    paramParcel2.writeNoException();
    paramParcel2.writeStringArray(arrayOfString);
    return true;
  }
  
  private static class Proxy implements ISerialManager {
    public static ISerialManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ISerialManager";
    }
    
    public String[] getSerialPorts() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ISerialManager");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISerialManager.Stub.getDefaultImpl() != null)
          return ISerialManager.Stub.getDefaultImpl().getSerialPorts(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openSerialPort(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ISerialManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISerialManager.Stub.getDefaultImpl() != null)
          return ISerialManager.Stub.getDefaultImpl().openSerialPort(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParcelFileDescriptor)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISerialManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */