package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPackageChangeObserver extends IInterface {
  void onPackageChanged(PackageChangeEvent paramPackageChangeEvent) throws RemoteException;
  
  public static class Default implements IPackageChangeObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onPackageChanged(PackageChangeEvent param1PackageChangeEvent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageChangeObserver {
    private static final String DESCRIPTOR = "android.content.pm.IPackageChangeObserver";
    
    static final int TRANSACTION_onPackageChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPackageChangeObserver");
    }
    
    public static IPackageChangeObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageChangeObserver");
      return (iInterface != null && iInterface instanceof IPackageChangeObserver) ? (IPackageChangeObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageChangeObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onPackageChanged";
    }
    
    public static boolean setDefaultImpl(IPackageChangeObserver param1IPackageChangeObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageChangeObserver != null) {
          Proxy.sDefaultImpl = param1IPackageChangeObserver;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.pm.IPackageChangeObserver");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IPackageChangeObserver");
      if (param1Parcel1.readInt() != 0) {
        PackageChangeEvent packageChangeEvent = (PackageChangeEvent)PackageChangeEvent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onPackageChanged((PackageChangeEvent)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IPackageChangeObserver {
      public static IPackageChangeObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IPackageChangeObserver";
      }
      
      public void onPackageChanged(PackageChangeEvent param2PackageChangeEvent) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageChangeObserver");
          if (param2PackageChangeEvent != null) {
            parcel.writeInt(1);
            param2PackageChangeEvent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IPackageChangeObserver.Stub.getDefaultImpl() != null) {
            IPackageChangeObserver.Stub.getDefaultImpl().onPackageChanged(param2PackageChangeEvent);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPackageChangeObserver {
    public static IPackageChangeObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageChangeObserver";
    }
    
    public void onPackageChanged(PackageChangeEvent param1PackageChangeEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageChangeObserver");
        if (param1PackageChangeEvent != null) {
          parcel.writeInt(1);
          param1PackageChangeEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageChangeObserver.Stub.getDefaultImpl() != null) {
          IPackageChangeObserver.Stub.getDefaultImpl().onPackageChanged(param1PackageChangeEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageChangeObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */