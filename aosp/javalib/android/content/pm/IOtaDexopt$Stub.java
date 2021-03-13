package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IOtaDexopt {
  private static final String DESCRIPTOR = "android.content.pm.IOtaDexopt";
  
  static final int TRANSACTION_cleanup = 2;
  
  static final int TRANSACTION_dexoptNextPackage = 5;
  
  static final int TRANSACTION_getProgress = 4;
  
  static final int TRANSACTION_isDone = 3;
  
  static final int TRANSACTION_nextDexoptCommand = 6;
  
  static final int TRANSACTION_prepare = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IOtaDexopt");
  }
  
  public static IOtaDexopt asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IOtaDexopt");
    return (iInterface != null && iInterface instanceof IOtaDexopt) ? (IOtaDexopt)iInterface : new Proxy(paramIBinder);
  }
  
  public static IOtaDexopt getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 6:
        return "nextDexoptCommand";
      case 5:
        return "dexoptNextPackage";
      case 4:
        return "getProgress";
      case 3:
        return "isDone";
      case 2:
        return "cleanup";
      case 1:
        break;
    } 
    return "prepare";
  }
  
  public static boolean setDefaultImpl(IOtaDexopt paramIOtaDexopt) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIOtaDexopt != null) {
        Proxy.sDefaultImpl = paramIOtaDexopt;
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
    if (paramInt1 != 1598968902) {
      boolean bool;
      String str;
      float f;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.content.pm.IOtaDexopt");
          str = nextDexoptCommand();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str);
          return true;
        case 5:
          str.enforceInterface("android.content.pm.IOtaDexopt");
          dexoptNextPackage();
          paramParcel2.writeNoException();
          return true;
        case 4:
          str.enforceInterface("android.content.pm.IOtaDexopt");
          f = getProgress();
          paramParcel2.writeNoException();
          paramParcel2.writeFloat(f);
          return true;
        case 3:
          str.enforceInterface("android.content.pm.IOtaDexopt");
          bool = isDone();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 2:
          str.enforceInterface("android.content.pm.IOtaDexopt");
          cleanup();
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      str.enforceInterface("android.content.pm.IOtaDexopt");
      prepare();
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.content.pm.IOtaDexopt");
    return true;
  }
  
  private static class Proxy implements IOtaDexopt {
    public static IOtaDexopt sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cleanup() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
          IOtaDexopt.Stub.getDefaultImpl().cleanup();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void dexoptNextPackage() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
          IOtaDexopt.Stub.getDefaultImpl().dexoptNextPackage();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IOtaDexopt";
    }
    
    public float getProgress() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null)
          return IOtaDexopt.Stub.getDefaultImpl().getProgress(); 
        parcel2.readException();
        return parcel2.readFloat();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDone() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
          bool = IOtaDexopt.Stub.getDefaultImpl().isDone();
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
    
    public String nextDexoptCommand() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null)
          return IOtaDexopt.Stub.getDefaultImpl().nextDexoptCommand(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void prepare() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
          IOtaDexopt.Stub.getDefaultImpl().prepare();
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOtaDexopt$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */