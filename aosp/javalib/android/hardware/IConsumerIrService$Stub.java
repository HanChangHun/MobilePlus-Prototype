package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IConsumerIrService {
  private static final String DESCRIPTOR = "android.hardware.IConsumerIrService";
  
  static final int TRANSACTION_getCarrierFrequencies = 3;
  
  static final int TRANSACTION_hasIrEmitter = 1;
  
  static final int TRANSACTION_transmit = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.IConsumerIrService");
  }
  
  public static IConsumerIrService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.IConsumerIrService");
    return (iInterface != null && iInterface instanceof IConsumerIrService) ? (IConsumerIrService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IConsumerIrService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "getCarrierFrequencies") : "transmit") : "hasIrEmitter";
  }
  
  public static boolean setDefaultImpl(IConsumerIrService paramIConsumerIrService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIConsumerIrService != null) {
        Proxy.sDefaultImpl = paramIConsumerIrService;
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
    int[] arrayOfInt;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.hardware.IConsumerIrService");
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.IConsumerIrService");
        arrayOfInt = getCarrierFrequencies();
        paramParcel2.writeNoException();
        paramParcel2.writeIntArray(arrayOfInt);
        return true;
      } 
      arrayOfInt.enforceInterface("android.hardware.IConsumerIrService");
      transmit(arrayOfInt.readString(), arrayOfInt.readInt(), arrayOfInt.createIntArray());
      paramParcel2.writeNoException();
      return true;
    } 
    arrayOfInt.enforceInterface("android.hardware.IConsumerIrService");
    boolean bool = hasIrEmitter();
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private static class Proxy implements IConsumerIrService {
    public static IConsumerIrService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public int[] getCarrierFrequencies() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null)
          return IConsumerIrService.Stub.getDefaultImpl().getCarrierFrequencies(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.IConsumerIrService";
    }
    
    public boolean hasIrEmitter() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
          bool = IConsumerIrService.Stub.getDefaultImpl().hasIrEmitter();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void transmit(String param2String, int param2Int, int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
          IConsumerIrService.Stub.getDefaultImpl().transmit(param2String, param2Int, param2ArrayOfint);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/IConsumerIrService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */