package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPackageStatsObserver extends IInterface {
  void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean) throws RemoteException;
  
  public static class Default implements IPackageStatsObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onGetStatsCompleted(PackageStats param1PackageStats, boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageStatsObserver {
    private static final String DESCRIPTOR = "android.content.pm.IPackageStatsObserver";
    
    static final int TRANSACTION_onGetStatsCompleted = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPackageStatsObserver");
    }
    
    public static IPackageStatsObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageStatsObserver");
      return (iInterface != null && iInterface instanceof IPackageStatsObserver) ? (IPackageStatsObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageStatsObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onGetStatsCompleted";
    }
    
    public static boolean setDefaultImpl(IPackageStatsObserver param1IPackageStatsObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageStatsObserver != null) {
          Proxy.sDefaultImpl = param1IPackageStatsObserver;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.pm.IPackageStatsObserver");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IPackageStatsObserver");
      if (param1Parcel1.readInt() != 0) {
        PackageStats packageStats = (PackageStats)PackageStats.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onGetStatsCompleted((PackageStats)param1Parcel2, bool);
      return true;
    }
    
    private static class Proxy implements IPackageStatsObserver {
      public static IPackageStatsObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IPackageStatsObserver";
      }
      
      public void onGetStatsCompleted(PackageStats param2PackageStats, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
          boolean bool = false;
          if (param2PackageStats != null) {
            parcel.writeInt(1);
            param2PackageStats.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && IPackageStatsObserver.Stub.getDefaultImpl() != null) {
            IPackageStatsObserver.Stub.getDefaultImpl().onGetStatsCompleted(param2PackageStats, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPackageStatsObserver {
    public static IPackageStatsObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageStatsObserver";
    }
    
    public void onGetStatsCompleted(PackageStats param1PackageStats, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
        boolean bool = false;
        if (param1PackageStats != null) {
          parcel.writeInt(1);
          param1PackageStats.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IPackageStatsObserver.Stub.getDefaultImpl() != null) {
          IPackageStatsObserver.Stub.getDefaultImpl().onGetStatsCompleted(param1PackageStats, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageStatsObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */