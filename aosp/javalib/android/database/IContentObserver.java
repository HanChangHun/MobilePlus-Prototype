package android.database;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface IContentObserver extends IInterface {
  void onChange(boolean paramBoolean, Uri paramUri, int paramInt) throws RemoteException;
  
  void onChangeEtc(boolean paramBoolean, Uri[] paramArrayOfUri, int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IContentObserver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onChange(boolean param1Boolean, Uri param1Uri, int param1Int) throws RemoteException {}
    
    public void onChangeEtc(boolean param1Boolean, Uri[] param1ArrayOfUri, int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IContentObserver {
    private static final String DESCRIPTOR = "android.database.IContentObserver";
    
    static final int TRANSACTION_onChange = 1;
    
    static final int TRANSACTION_onChangeEtc = 2;
    
    public Stub() {
      attachInterface(this, "android.database.IContentObserver");
    }
    
    public static IContentObserver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.database.IContentObserver");
      return (iInterface != null && iInterface instanceof IContentObserver) ? (IContentObserver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContentObserver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onChangeEtc") : "onChange";
    }
    
    public static boolean setDefaultImpl(IContentObserver param1IContentObserver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContentObserver != null) {
          Proxy.sDefaultImpl = param1IContentObserver;
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
      boolean bool1 = false;
      boolean bool2 = false;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.database.IContentObserver");
          return true;
        } 
        param1Parcel1.enforceInterface("android.database.IContentObserver");
        if (param1Parcel1.readInt() != 0)
          bool2 = true; 
        onChangeEtc(bool2, (Uri[])param1Parcel1.createTypedArray(Uri.CREATOR), param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.database.IContentObserver");
      bool2 = bool1;
      if (param1Parcel1.readInt() != 0)
        bool2 = true; 
      if (param1Parcel1.readInt() != 0) {
        Uri uri = (Uri)Uri.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onChange(bool2, (Uri)param1Parcel2, param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IContentObserver {
      public static IContentObserver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.database.IContentObserver";
      }
      
      public void onChange(boolean param2Boolean, Uri param2Uri, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.database.IContentObserver");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Uri != null) {
            parcel.writeInt(1);
            param2Uri.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IContentObserver.Stub.getDefaultImpl() != null) {
            IContentObserver.Stub.getDefaultImpl().onChange(param2Boolean, param2Uri, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onChangeEtc(boolean param2Boolean, Uri[] param2ArrayOfUri, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.database.IContentObserver");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeTypedArray((Parcelable[])param2ArrayOfUri, 0);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(2, parcel, null, 1) && IContentObserver.Stub.getDefaultImpl() != null) {
            IContentObserver.Stub.getDefaultImpl().onChangeEtc(param2Boolean, param2ArrayOfUri, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IContentObserver {
    public static IContentObserver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.database.IContentObserver";
    }
    
    public void onChange(boolean param1Boolean, Uri param1Uri, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.database.IContentObserver");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Uri != null) {
          parcel.writeInt(1);
          param1Uri.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IContentObserver.Stub.getDefaultImpl() != null) {
          IContentObserver.Stub.getDefaultImpl().onChange(param1Boolean, param1Uri, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onChangeEtc(boolean param1Boolean, Uri[] param1ArrayOfUri, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.database.IContentObserver");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeTypedArray((Parcelable[])param1ArrayOfUri, 0);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IContentObserver.Stub.getDefaultImpl() != null) {
          IContentObserver.Stub.getDefaultImpl().onChangeEtc(param1Boolean, param1ArrayOfUri, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/IContentObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */