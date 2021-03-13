package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

public interface IEphemeralResolver extends IInterface {
  void getEphemeralIntentFilterList(IRemoteCallback paramIRemoteCallback, String paramString, int paramInt) throws RemoteException;
  
  void getEphemeralResolveInfoList(IRemoteCallback paramIRemoteCallback, int[] paramArrayOfint, int paramInt) throws RemoteException;
  
  public static class Default implements IEphemeralResolver {
    public IBinder asBinder() {
      return null;
    }
    
    public void getEphemeralIntentFilterList(IRemoteCallback param1IRemoteCallback, String param1String, int param1Int) throws RemoteException {}
    
    public void getEphemeralResolveInfoList(IRemoteCallback param1IRemoteCallback, int[] param1ArrayOfint, int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IEphemeralResolver {
    private static final String DESCRIPTOR = "android.app.IEphemeralResolver";
    
    static final int TRANSACTION_getEphemeralIntentFilterList = 2;
    
    static final int TRANSACTION_getEphemeralResolveInfoList = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IEphemeralResolver");
    }
    
    public static IEphemeralResolver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IEphemeralResolver");
      return (iInterface != null && iInterface instanceof IEphemeralResolver) ? (IEphemeralResolver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IEphemeralResolver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "getEphemeralIntentFilterList") : "getEphemeralResolveInfoList";
    }
    
    public static boolean setDefaultImpl(IEphemeralResolver param1IEphemeralResolver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IEphemeralResolver != null) {
          Proxy.sDefaultImpl = param1IEphemeralResolver;
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
          param1Parcel2.writeString("android.app.IEphemeralResolver");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IEphemeralResolver");
        getEphemeralIntentFilterList(IRemoteCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IEphemeralResolver");
      getEphemeralResolveInfoList(IRemoteCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.createIntArray(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IEphemeralResolver {
      public static IEphemeralResolver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void getEphemeralIntentFilterList(IRemoteCallback param2IRemoteCallback, String param2String, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IEphemeralResolver");
          if (param2IRemoteCallback != null) {
            iBinder = param2IRemoteCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeString(param2String);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
            IEphemeralResolver.Stub.getDefaultImpl().getEphemeralIntentFilterList(param2IRemoteCallback, param2String, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void getEphemeralResolveInfoList(IRemoteCallback param2IRemoteCallback, int[] param2ArrayOfint, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IEphemeralResolver");
          if (param2IRemoteCallback != null) {
            iBinder = param2IRemoteCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeIntArray(param2ArrayOfint);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
            IEphemeralResolver.Stub.getDefaultImpl().getEphemeralResolveInfoList(param2IRemoteCallback, param2ArrayOfint, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IEphemeralResolver";
      }
    }
  }
  
  private static class Proxy implements IEphemeralResolver {
    public static IEphemeralResolver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void getEphemeralIntentFilterList(IRemoteCallback param1IRemoteCallback, String param1String, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IEphemeralResolver");
        if (param1IRemoteCallback != null) {
          iBinder = param1IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
          IEphemeralResolver.Stub.getDefaultImpl().getEphemeralIntentFilterList(param1IRemoteCallback, param1String, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void getEphemeralResolveInfoList(IRemoteCallback param1IRemoteCallback, int[] param1ArrayOfint, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IEphemeralResolver");
        if (param1IRemoteCallback != null) {
          iBinder = param1IRemoteCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeIntArray(param1ArrayOfint);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IEphemeralResolver.Stub.getDefaultImpl() != null) {
          IEphemeralResolver.Stub.getDefaultImpl().getEphemeralResolveInfoList(param1IRemoteCallback, param1ArrayOfint, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IEphemeralResolver";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IEphemeralResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */