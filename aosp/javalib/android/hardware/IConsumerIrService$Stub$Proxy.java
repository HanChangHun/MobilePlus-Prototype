package android.hardware;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IConsumerIrService {
  public static IConsumerIrService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public void transmit(String paramString, int paramInt, int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
        IConsumerIrService.Stub.getDefaultImpl().transmit(paramString, paramInt, paramArrayOfint);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/IConsumerIrService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */