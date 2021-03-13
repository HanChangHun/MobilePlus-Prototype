package android.app.timezone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRulesManager {
  private static final String DESCRIPTOR = "android.app.timezone.IRulesManager";
  
  static final int TRANSACTION_getRulesState = 1;
  
  static final int TRANSACTION_requestInstall = 2;
  
  static final int TRANSACTION_requestNothing = 4;
  
  static final int TRANSACTION_requestUninstall = 3;
  
  public Stub() {
    attachInterface(this, "android.app.timezone.IRulesManager");
  }
  
  public static IRulesManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.timezone.IRulesManager");
    return (iInterface != null && iInterface instanceof IRulesManager) ? (IRulesManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRulesManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : "requestNothing") : "requestUninstall") : "requestInstall") : "getRulesState";
  }
  
  public static boolean setDefaultImpl(IRulesManager paramIRulesManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRulesManager != null) {
        Proxy.sDefaultImpl = paramIRulesManager;
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
    boolean bool = false;
    if (paramInt1 != 1) {
      ParcelFileDescriptor parcelFileDescriptor;
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 1598968902)
              return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
            paramParcel2.writeString("android.app.timezone.IRulesManager");
            return true;
          } 
          paramParcel1.enforceInterface("android.app.timezone.IRulesManager");
          parcelFileDescriptor = (ParcelFileDescriptor)paramParcel1.createByteArray();
          if (paramParcel1.readInt() != 0)
            bool = true; 
          requestNothing((byte[])parcelFileDescriptor, bool);
          paramParcel2.writeNoException();
          return true;
        } 
        paramParcel1.enforceInterface("android.app.timezone.IRulesManager");
        paramInt1 = requestUninstall(paramParcel1.createByteArray(), ICallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      } 
      paramParcel1.enforceInterface("android.app.timezone.IRulesManager");
      if (paramParcel1.readInt() != 0) {
        parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
      } else {
        parcelFileDescriptor = null;
      } 
      paramInt1 = requestInstall(parcelFileDescriptor, paramParcel1.createByteArray(), ICallback.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    } 
    paramParcel1.enforceInterface("android.app.timezone.IRulesManager");
    RulesState rulesState = getRulesState();
    paramParcel2.writeNoException();
    if (rulesState != null) {
      paramParcel2.writeInt(1);
      rulesState.writeToParcel(paramParcel2, 1);
    } else {
      paramParcel2.writeInt(0);
    } 
    return true;
  }
  
  private static class Proxy implements IRulesManager {
    public static IRulesManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timezone.IRulesManager";
    }
    
    public RulesState getRulesState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        RulesState rulesState;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
          rulesState = IRulesManager.Stub.getDefaultImpl().getRulesState();
          return rulesState;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          rulesState = (RulesState)RulesState.CREATOR.createFromParcel(parcel2);
        } else {
          rulesState = null;
        } 
        return rulesState;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int requestInstall(ParcelFileDescriptor param2ParcelFileDescriptor, byte[] param2ArrayOfbyte, ICallback param2ICallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        if (param2ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (param2ICallback != null) {
          iBinder = param2ICallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
          return IRulesManager.Stub.getDefaultImpl().requestInstall(param2ParcelFileDescriptor, param2ArrayOfbyte, param2ICallback); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestNothing(byte[] param2ArrayOfbyte, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
          IRulesManager.Stub.getDefaultImpl().requestNothing(param2ArrayOfbyte, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int requestUninstall(byte[] param2ArrayOfbyte, ICallback param2ICallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (param2ICallback != null) {
          iBinder = param2ICallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
          return IRulesManager.Stub.getDefaultImpl().requestUninstall(param2ArrayOfbyte, param2ICallback); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/IRulesManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */