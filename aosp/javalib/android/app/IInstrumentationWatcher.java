package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IInstrumentationWatcher extends IInterface {
  void instrumentationFinished(ComponentName paramComponentName, int paramInt, Bundle paramBundle) throws RemoteException;
  
  void instrumentationStatus(ComponentName paramComponentName, int paramInt, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements IInstrumentationWatcher {
    public IBinder asBinder() {
      return null;
    }
    
    public void instrumentationFinished(ComponentName param1ComponentName, int param1Int, Bundle param1Bundle) throws RemoteException {}
    
    public void instrumentationStatus(ComponentName param1ComponentName, int param1Int, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IInstrumentationWatcher {
    private static final String DESCRIPTOR = "android.app.IInstrumentationWatcher";
    
    static final int TRANSACTION_instrumentationFinished = 2;
    
    static final int TRANSACTION_instrumentationStatus = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IInstrumentationWatcher");
    }
    
    public static IInstrumentationWatcher asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IInstrumentationWatcher");
      return (iInterface != null && iInterface instanceof IInstrumentationWatcher) ? (IInstrumentationWatcher)iInterface : new Proxy(param1IBinder);
    }
    
    public static IInstrumentationWatcher getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "instrumentationFinished") : "instrumentationStatus";
    }
    
    public static boolean setDefaultImpl(IInstrumentationWatcher param1IInstrumentationWatcher) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IInstrumentationWatcher != null) {
          Proxy.sDefaultImpl = param1IInstrumentationWatcher;
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
      ComponentName componentName;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.IInstrumentationWatcher");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IInstrumentationWatcher");
        if (param1Parcel1.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
        } else {
          componentName = null;
        } 
        param1Int1 = param1Parcel1.readInt();
        if (param1Parcel1.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        instrumentationFinished(componentName, param1Int1, (Bundle)param1Parcel1);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IInstrumentationWatcher");
      if (param1Parcel1.readInt() != 0) {
        componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
      } else {
        componentName = null;
      } 
      param1Int1 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      instrumentationStatus(componentName, param1Int1, (Bundle)param1Parcel1);
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IInstrumentationWatcher {
      public static IInstrumentationWatcher sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IInstrumentationWatcher";
      }
      
      public void instrumentationFinished(ComponentName param2ComponentName, int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IInstrumentationWatcher");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IInstrumentationWatcher.Stub.getDefaultImpl() != null) {
            IInstrumentationWatcher.Stub.getDefaultImpl().instrumentationFinished(param2ComponentName, param2Int, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void instrumentationStatus(ComponentName param2ComponentName, int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IInstrumentationWatcher");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IInstrumentationWatcher.Stub.getDefaultImpl() != null) {
            IInstrumentationWatcher.Stub.getDefaultImpl().instrumentationStatus(param2ComponentName, param2Int, param2Bundle);
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
  
  private static class Proxy implements IInstrumentationWatcher {
    public static IInstrumentationWatcher sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IInstrumentationWatcher";
    }
    
    public void instrumentationFinished(ComponentName param1ComponentName, int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IInstrumentationWatcher");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IInstrumentationWatcher.Stub.getDefaultImpl() != null) {
          IInstrumentationWatcher.Stub.getDefaultImpl().instrumentationFinished(param1ComponentName, param1Int, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void instrumentationStatus(ComponentName param1ComponentName, int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IInstrumentationWatcher");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IInstrumentationWatcher.Stub.getDefaultImpl() != null) {
          IInstrumentationWatcher.Stub.getDefaultImpl().instrumentationStatus(param1ComponentName, param1Int, param1Bundle);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstrumentationWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */