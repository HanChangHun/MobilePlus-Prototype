package android.hardware.radio;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IRadioService {
  public static IRadioService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public ICloseHandle addAnnouncementListener(int[] paramArrayOfint, IAnnouncementListener paramIAnnouncementListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.radio.IRadioService");
      parcel1.writeIntArray(paramArrayOfint);
      if (paramIAnnouncementListener != null) {
        iBinder = paramIAnnouncementListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRadioService.Stub.getDefaultImpl() != null)
        return IRadioService.Stub.getDefaultImpl().addAnnouncementListener(paramArrayOfint, paramIAnnouncementListener); 
      parcel2.readException();
      return ICloseHandle.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.radio.IRadioService";
  }
  
  public List<RadioManager.ModuleProperties> listModules() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.IRadioService");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRadioService.Stub.getDefaultImpl() != null)
        return IRadioService.Stub.getDefaultImpl().listModules(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(RadioManager.ModuleProperties.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ITuner openTuner(int paramInt, RadioManager.BandConfig paramBandConfig, boolean paramBoolean, ITunerCallback paramITunerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.radio.IRadioService");
      parcel1.writeInt(paramInt);
      boolean bool = true;
      if (paramBandConfig != null) {
        parcel1.writeInt(1);
        paramBandConfig.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (paramITunerCallback != null) {
        iBinder = paramITunerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRadioService.Stub.getDefaultImpl() != null)
        return IRadioService.Stub.getDefaultImpl().openTuner(paramInt, paramBandConfig, paramBoolean, paramITunerCallback); 
      parcel2.readException();
      return ITuner.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IRadioService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */