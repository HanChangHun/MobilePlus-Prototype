package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBiometricEnabledOnKeyguardCallback {
  private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback";
  
  static final int TRANSACTION_onChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback");
  }
  
  public static IBiometricEnabledOnKeyguardCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback");
    return (iInterface != null && iInterface instanceof IBiometricEnabledOnKeyguardCallback) ? (IBiometricEnabledOnKeyguardCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBiometricEnabledOnKeyguardCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onChanged";
  }
  
  public static boolean setDefaultImpl(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBiometricEnabledOnKeyguardCallback != null) {
        Proxy.sDefaultImpl = paramIBiometricEnabledOnKeyguardCallback;
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
      paramParcel2.writeString("android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback");
    if (paramParcel1.readInt() != 0) {
      BiometricSourceType biometricSourceType = (BiometricSourceType)BiometricSourceType.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onChanged((BiometricSourceType)paramParcel2, bool, paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IBiometricEnabledOnKeyguardCallback {
    public static IBiometricEnabledOnKeyguardCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback";
    }
    
    public void onChanged(BiometricSourceType param2BiometricSourceType, boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback");
        boolean bool = false;
        if (param2BiometricSourceType != null) {
          parcel.writeInt(1);
          param2BiometricSourceType.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricEnabledOnKeyguardCallback.Stub.getDefaultImpl() != null) {
          IBiometricEnabledOnKeyguardCallback.Stub.getDefaultImpl().onChanged(param2BiometricSourceType, param2Boolean, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricEnabledOnKeyguardCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */