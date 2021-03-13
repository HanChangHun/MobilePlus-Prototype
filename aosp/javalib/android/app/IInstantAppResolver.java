package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public interface IInstantAppResolver extends IInterface {
  void getInstantAppIntentFilterList(InstantAppRequestInfo paramInstantAppRequestInfo, IRemoteCallback paramIRemoteCallback) throws RemoteException;
  
  void getInstantAppResolveInfoList(InstantAppRequestInfo paramInstantAppRequestInfo, int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException;
  
  public static class Default implements IInstantAppResolver {
    public IBinder asBinder() {
      return null;
    }
    
    public void getInstantAppIntentFilterList(InstantAppRequestInfo param1InstantAppRequestInfo, IRemoteCallback param1IRemoteCallback) throws RemoteException {}
    
    public void getInstantAppResolveInfoList(InstantAppRequestInfo param1InstantAppRequestInfo, int param1Int, IRemoteCallback param1IRemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IInstantAppResolver {
    private static final String DESCRIPTOR = "android.app.IInstantAppResolver";
    
    static final int TRANSACTION_getInstantAppIntentFilterList = 2;
    
    static final int TRANSACTION_getInstantAppResolveInfoList = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IInstantAppResolver");
    }
    
    public static IInstantAppResolver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IInstantAppResolver");
      return (iInterface != null && iInterface instanceof IInstantAppResolver) ? (IInstantAppResolver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IInstantAppResolver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "getInstantAppIntentFilterList") : "getInstantAppResolveInfoList";
    }
    
    public static boolean setDefaultImpl(IInstantAppResolver param1IInstantAppResolver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IInstantAppResolver != null) {
          Proxy.sDefaultImpl = param1IInstantAppResolver;
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
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.IInstantAppResolver");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IInstantAppResolver");
        if (param1Parcel1.readInt() != 0) {
          InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)InstantAppRequestInfo.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel2 = null;
        } 
        getInstantAppIntentFilterList((InstantAppRequestInfo)param1Parcel2, IRemoteCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IInstantAppResolver");
      if (param1Parcel1.readInt() != 0) {
        InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)InstantAppRequestInfo.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      getInstantAppResolveInfoList((InstantAppRequestInfo)param1Parcel2, param1Parcel1.readInt(), IRemoteCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
      return true;
    }
    
    private static class Proxy implements IInstantAppResolver {
      public static IInstantAppResolver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void getInstantAppIntentFilterList(InstantAppRequestInfo param2InstantAppRequestInfo, IRemoteCallback param2IRemoteCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IInstantAppResolver");
          if (param2InstantAppRequestInfo != null) {
            parcel.writeInt(1);
            param2InstantAppRequestInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2IRemoteCallback != null) {
            iBinder = param2IRemoteCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(2, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
            IInstantAppResolver.Stub.getDefaultImpl().getInstantAppIntentFilterList(param2InstantAppRequestInfo, param2IRemoteCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void getInstantAppResolveInfoList(InstantAppRequestInfo param2InstantAppRequestInfo, int param2Int, IRemoteCallback param2IRemoteCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IInstantAppResolver");
          if (param2InstantAppRequestInfo != null) {
            parcel.writeInt(1);
            param2InstantAppRequestInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (param2IRemoteCallback != null) {
            iBinder = param2IRemoteCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
            IInstantAppResolver.Stub.getDefaultImpl().getInstantAppResolveInfoList(param2InstantAppRequestInfo, param2Int, param2IRemoteCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IInstantAppResolver";
      }
    }
  }
  
  private static class Proxy implements IInstantAppResolver {
    public static IInstantAppResolver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void getInstantAppIntentFilterList(InstantAppRequestInfo param1InstantAppRequestInfo, IRemoteCallback param1IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IInstantAppResolver");
        if (param1InstantAppRequestInfo != null) {
          parcel.writeInt(1);
          param1InstantAppRequestInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1IRemoteCallback != null) {
          iBinder = param1IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
          IInstantAppResolver.Stub.getDefaultImpl().getInstantAppIntentFilterList(param1InstantAppRequestInfo, param1IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getInstantAppResolveInfoList(InstantAppRequestInfo param1InstantAppRequestInfo, int param1Int, IRemoteCallback param1IRemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IInstantAppResolver");
        if (param1InstantAppRequestInfo != null) {
          parcel.writeInt(1);
          param1InstantAppRequestInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (param1IRemoteCallback != null) {
          iBinder = param1IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IInstantAppResolver.Stub.getDefaultImpl() != null) {
          IInstantAppResolver.Stub.getDefaultImpl().getInstantAppResolveInfoList(param1InstantAppRequestInfo, param1Int, param1IRemoteCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IInstantAppResolver";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstantAppResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */