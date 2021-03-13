package android.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IRadioService {
  private static final String DESCRIPTOR = "android.hardware.radio.IRadioService";
  
  static final int TRANSACTION_addAnnouncementListener = 3;
  
  static final int TRANSACTION_listModules = 1;
  
  static final int TRANSACTION_openTuner = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.radio.IRadioService");
  }
  
  public static IRadioService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.radio.IRadioService");
    return (iInterface != null && iInterface instanceof IRadioService) ? (IRadioService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRadioService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "addAnnouncementListener") : "openTuner") : "listModules";
  }
  
  public static boolean setDefaultImpl(IRadioService paramIRadioService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRadioService != null) {
        Proxy.sDefaultImpl = paramIRadioService;
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
    IBinder iBinder;
    if (paramInt1 != 1) {
      IBinder iBinder1;
      boolean bool;
      ICloseHandle iCloseHandle2 = null;
      Parcel parcel = null;
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.hardware.radio.IRadioService");
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.radio.IRadioService");
        iCloseHandle2 = addAnnouncementListener(paramParcel1.createIntArray(), IAnnouncementListener.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel1 = parcel;
        if (iCloseHandle2 != null)
          iBinder1 = iCloseHandle2.asBinder(); 
        paramParcel2.writeStrongBinder(iBinder1);
        return true;
      } 
      iBinder1.enforceInterface("android.hardware.radio.IRadioService");
      paramInt1 = iBinder1.readInt();
      if (iBinder1.readInt() != 0) {
        RadioManager.BandConfig bandConfig = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel((Parcel)iBinder1);
      } else {
        parcel = null;
      } 
      if (iBinder1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      ITuner iTuner = openTuner(paramInt1, (RadioManager.BandConfig)parcel, bool, ITunerCallback.Stub.asInterface(iBinder1.readStrongBinder()));
      paramParcel2.writeNoException();
      ICloseHandle iCloseHandle1 = iCloseHandle2;
      if (iTuner != null)
        iBinder = iTuner.asBinder(); 
      paramParcel2.writeStrongBinder(iBinder);
      return true;
    } 
    iBinder.enforceInterface("android.hardware.radio.IRadioService");
    List<RadioManager.ModuleProperties> list = listModules();
    paramParcel2.writeNoException();
    paramParcel2.writeTypedList(list);
    return true;
  }
  
  private static class Proxy implements IRadioService {
    public static IRadioService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public ICloseHandle addAnnouncementListener(int[] param2ArrayOfint, IAnnouncementListener param2IAnnouncementListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.radio.IRadioService");
        parcel1.writeIntArray(param2ArrayOfint);
        if (param2IAnnouncementListener != null) {
          iBinder = param2IAnnouncementListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRadioService.Stub.getDefaultImpl() != null)
          return IRadioService.Stub.getDefaultImpl().addAnnouncementListener(param2ArrayOfint, param2IAnnouncementListener); 
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
    
    public ITuner openTuner(int param2Int, RadioManager.BandConfig param2BandConfig, boolean param2Boolean, ITunerCallback param2ITunerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.radio.IRadioService");
        parcel1.writeInt(param2Int);
        boolean bool = true;
        if (param2BandConfig != null) {
          parcel1.writeInt(1);
          param2BandConfig.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (param2ITunerCallback != null) {
          iBinder = param2ITunerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRadioService.Stub.getDefaultImpl() != null)
          return IRadioService.Stub.getDefaultImpl().openTuner(param2Int, param2BandConfig, param2Boolean, param2ITunerCallback); 
        parcel2.readException();
        return ITuner.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IRadioService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */