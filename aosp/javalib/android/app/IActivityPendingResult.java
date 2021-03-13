package android.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IActivityPendingResult extends IInterface {
  boolean sendResult(int paramInt, String paramString, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements IActivityPendingResult {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean sendResult(int param1Int, String param1String, Bundle param1Bundle) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IActivityPendingResult {
    private static final String DESCRIPTOR = "android.app.IActivityPendingResult";
    
    static final int TRANSACTION_sendResult = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IActivityPendingResult");
    }
    
    public static IActivityPendingResult asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IActivityPendingResult");
      return (iInterface != null && iInterface instanceof IActivityPendingResult) ? (IActivityPendingResult)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityPendingResult getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "sendResult";
    }
    
    public static boolean setDefaultImpl(IActivityPendingResult param1IActivityPendingResult) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityPendingResult != null) {
          Proxy.sDefaultImpl = param1IActivityPendingResult;
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
        param1Parcel2.writeString("android.app.IActivityPendingResult");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IActivityPendingResult");
      param1Int1 = param1Parcel1.readInt();
      String str = param1Parcel1.readString();
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      boolean bool = sendResult(param1Int1, str, (Bundle)param1Parcel1);
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(bool);
      return true;
    }
    
    private static class Proxy implements IActivityPendingResult {
      public static IActivityPendingResult sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IActivityPendingResult";
      }
      
      public boolean sendResult(int param2Int, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityPendingResult");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          boolean bool = true;
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityPendingResult.Stub.getDefaultImpl() != null) {
            bool = IActivityPendingResult.Stub.getDefaultImpl().sendResult(param2Int, param2String, param2Bundle);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IActivityPendingResult {
    public static IActivityPendingResult sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IActivityPendingResult";
    }
    
    public boolean sendResult(int param1Int, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityPendingResult");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        boolean bool = true;
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityPendingResult.Stub.getDefaultImpl() != null) {
          bool = IActivityPendingResult.Stub.getDefaultImpl().sendResult(param1Int, param1String, param1Bundle);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityPendingResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */