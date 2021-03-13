package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IContextHubClientCallback extends IInterface {
  void onHubReset() throws RemoteException;
  
  void onMessageFromNanoApp(NanoAppMessage paramNanoAppMessage) throws RemoteException;
  
  void onNanoAppAborted(long paramLong, int paramInt) throws RemoteException;
  
  void onNanoAppDisabled(long paramLong) throws RemoteException;
  
  void onNanoAppEnabled(long paramLong) throws RemoteException;
  
  void onNanoAppLoaded(long paramLong) throws RemoteException;
  
  void onNanoAppUnloaded(long paramLong) throws RemoteException;
  
  public static class Default implements IContextHubClientCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onHubReset() throws RemoteException {}
    
    public void onMessageFromNanoApp(NanoAppMessage param1NanoAppMessage) throws RemoteException {}
    
    public void onNanoAppAborted(long param1Long, int param1Int) throws RemoteException {}
    
    public void onNanoAppDisabled(long param1Long) throws RemoteException {}
    
    public void onNanoAppEnabled(long param1Long) throws RemoteException {}
    
    public void onNanoAppLoaded(long param1Long) throws RemoteException {}
    
    public void onNanoAppUnloaded(long param1Long) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IContextHubClientCallback {
    private static final String DESCRIPTOR = "android.hardware.location.IContextHubClientCallback";
    
    static final int TRANSACTION_onHubReset = 2;
    
    static final int TRANSACTION_onMessageFromNanoApp = 1;
    
    static final int TRANSACTION_onNanoAppAborted = 3;
    
    static final int TRANSACTION_onNanoAppDisabled = 7;
    
    static final int TRANSACTION_onNanoAppEnabled = 6;
    
    static final int TRANSACTION_onNanoAppLoaded = 4;
    
    static final int TRANSACTION_onNanoAppUnloaded = 5;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IContextHubClientCallback");
    }
    
    public static IContextHubClientCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IContextHubClientCallback");
      return (iInterface != null && iInterface instanceof IContextHubClientCallback) ? (IContextHubClientCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContextHubClientCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 7:
          return "onNanoAppDisabled";
        case 6:
          return "onNanoAppEnabled";
        case 5:
          return "onNanoAppUnloaded";
        case 4:
          return "onNanoAppLoaded";
        case 3:
          return "onNanoAppAborted";
        case 2:
          return "onHubReset";
        case 1:
          break;
      } 
      return "onMessageFromNanoApp";
    }
    
    public static boolean setDefaultImpl(IContextHubClientCallback param1IContextHubClientCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContextHubClientCallback != null) {
          Proxy.sDefaultImpl = param1IContextHubClientCallback;
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
      if (param1Int1 != 1598968902) {
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
            onNanoAppDisabled(param1Parcel1.readLong());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
            onNanoAppEnabled(param1Parcel1.readLong());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
            onNanoAppUnloaded(param1Parcel1.readLong());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
            onNanoAppLoaded(param1Parcel1.readLong());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
            onNanoAppAborted(param1Parcel1.readLong(), param1Parcel1.readInt());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
            onHubReset();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.location.IContextHubClientCallback");
        if (param1Parcel1.readInt() != 0) {
          NanoAppMessage nanoAppMessage = (NanoAppMessage)NanoAppMessage.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onMessageFromNanoApp((NanoAppMessage)param1Parcel1);
        return true;
      } 
      param1Parcel2.writeString("android.hardware.location.IContextHubClientCallback");
      return true;
    }
    
    private static class Proxy implements IContextHubClientCallback {
      public static IContextHubClientCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IContextHubClientCallback";
      }
      
      public void onHubReset() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onHubReset();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onMessageFromNanoApp(NanoAppMessage param2NanoAppMessage) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          if (param2NanoAppMessage != null) {
            parcel.writeInt(1);
            param2NanoAppMessage.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onMessageFromNanoApp(param2NanoAppMessage);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onNanoAppAborted(long param2Long, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppAborted(param2Long, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onNanoAppDisabled(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(7, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppDisabled(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onNanoAppEnabled(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(6, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppEnabled(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onNanoAppLoaded(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(4, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppLoaded(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onNanoAppUnloaded(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(5, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
            IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppUnloaded(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IContextHubClientCallback {
    public static IContextHubClientCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubClientCallback";
    }
    
    public void onHubReset() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onHubReset();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onMessageFromNanoApp(NanoAppMessage param1NanoAppMessage) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        if (param1NanoAppMessage != null) {
          parcel.writeInt(1);
          param1NanoAppMessage.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onMessageFromNanoApp(param1NanoAppMessage);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppAborted(long param1Long, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppAborted(param1Long, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppDisabled(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(7, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppDisabled(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppEnabled(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(6, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppEnabled(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppLoaded(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(4, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppLoaded(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onNanoAppUnloaded(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubClientCallback");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(5, parcel, null, 1) && IContextHubClientCallback.Stub.getDefaultImpl() != null) {
          IContextHubClientCallback.Stub.getDefaultImpl().onNanoAppUnloaded(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClientCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */