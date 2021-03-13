package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraServiceProxy extends IInterface {
  public static final int CAMERA_API_LEVEL_1 = 1;
  
  public static final int CAMERA_API_LEVEL_2 = 2;
  
  public static final int CAMERA_FACING_BACK = 0;
  
  public static final int CAMERA_FACING_EXTERNAL = 2;
  
  public static final int CAMERA_FACING_FRONT = 1;
  
  public static final int CAMERA_STATE_ACTIVE = 1;
  
  public static final int CAMERA_STATE_CLOSED = 3;
  
  public static final int CAMERA_STATE_IDLE = 2;
  
  public static final int CAMERA_STATE_OPEN = 0;
  
  void notifyCameraState(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3) throws RemoteException;
  
  void pingForUserUpdate() throws RemoteException;
  
  public static class Default implements ICameraServiceProxy {
    public IBinder asBinder() {
      return null;
    }
    
    public void notifyCameraState(String param1String1, int param1Int1, int param1Int2, String param1String2, int param1Int3) throws RemoteException {}
    
    public void pingForUserUpdate() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICameraServiceProxy {
    private static final String DESCRIPTOR = "android.hardware.ICameraServiceProxy";
    
    static final int TRANSACTION_notifyCameraState = 2;
    
    static final int TRANSACTION_pingForUserUpdate = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.ICameraServiceProxy");
    }
    
    public static ICameraServiceProxy asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ICameraServiceProxy");
      return (iInterface != null && iInterface instanceof ICameraServiceProxy) ? (ICameraServiceProxy)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraServiceProxy getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "notifyCameraState") : "pingForUserUpdate";
    }
    
    public static boolean setDefaultImpl(ICameraServiceProxy param1ICameraServiceProxy) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraServiceProxy != null) {
          Proxy.sDefaultImpl = param1ICameraServiceProxy;
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
          param1Parcel2.writeString("android.hardware.ICameraServiceProxy");
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.ICameraServiceProxy");
        notifyCameraState(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readString(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.ICameraServiceProxy");
      pingForUserUpdate();
      return true;
    }
    
    private static class Proxy implements ICameraServiceProxy {
      public static ICameraServiceProxy sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.ICameraServiceProxy";
      }
      
      public void notifyCameraState(String param2String1, int param2Int1, int param2Int2, String param2String2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
          parcel.writeString(param2String1);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeString(param2String2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
            ICameraServiceProxy.Stub.getDefaultImpl().notifyCameraState(param2String1, param2Int1, param2Int2, param2String2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void pingForUserUpdate() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
          if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
            ICameraServiceProxy.Stub.getDefaultImpl().pingForUserUpdate();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ICameraServiceProxy {
    public static ICameraServiceProxy sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ICameraServiceProxy";
    }
    
    public void notifyCameraState(String param1String1, int param1Int1, int param1Int2, String param1String2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
        parcel.writeString(param1String1);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeString(param1String2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(2, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
          ICameraServiceProxy.Stub.getDefaultImpl().notifyCameraState(param1String1, param1Int1, param1Int2, param1String2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void pingForUserUpdate() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.ICameraServiceProxy");
        if (!this.mRemote.transact(1, parcel, null, 1) && ICameraServiceProxy.Stub.getDefaultImpl() != null) {
          ICameraServiceProxy.Stub.getDefaultImpl().pingForUserUpdate();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */