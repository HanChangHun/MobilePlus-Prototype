package android.app.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

public interface ITrustListener extends IInterface {
  void onTrustChanged(boolean paramBoolean, int paramInt1, int paramInt2) throws RemoteException;
  
  void onTrustError(CharSequence paramCharSequence) throws RemoteException;
  
  void onTrustManagedChanged(boolean paramBoolean, int paramInt) throws RemoteException;
  
  public static class Default implements ITrustListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onTrustChanged(boolean param1Boolean, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onTrustError(CharSequence param1CharSequence) throws RemoteException {}
    
    public void onTrustManagedChanged(boolean param1Boolean, int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITrustListener {
    private static final String DESCRIPTOR = "android.app.trust.ITrustListener";
    
    static final int TRANSACTION_onTrustChanged = 1;
    
    static final int TRANSACTION_onTrustError = 3;
    
    static final int TRANSACTION_onTrustManagedChanged = 2;
    
    public Stub() {
      attachInterface(this, "android.app.trust.ITrustListener");
    }
    
    public static ITrustListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.trust.ITrustListener");
      return (iInterface != null && iInterface instanceof ITrustListener) ? (ITrustListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITrustListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "onTrustError") : "onTrustManagedChanged") : "onTrustChanged";
    }
    
    public static boolean setDefaultImpl(ITrustListener param1ITrustListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITrustListener != null) {
          Proxy.sDefaultImpl = param1ITrustListener;
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
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.app.trust.ITrustListener");
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.trust.ITrustListener");
          if (param1Parcel1.readInt() != 0) {
            CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel1);
          } else {
            param1Parcel1 = null;
          } 
          onTrustError((CharSequence)param1Parcel1);
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.trust.ITrustListener");
        if (param1Parcel1.readInt() != 0)
          bool2 = true; 
        onTrustManagedChanged(bool2, param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.trust.ITrustListener");
      bool2 = bool1;
      if (param1Parcel1.readInt() != 0)
        bool2 = true; 
      onTrustChanged(bool2, param1Parcel1.readInt(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements ITrustListener {
      public static ITrustListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.trust.ITrustListener";
      }
      
      public void onTrustChanged(boolean param2Boolean, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.trust.ITrustListener");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
            ITrustListener.Stub.getDefaultImpl().onTrustChanged(param2Boolean, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onTrustError(CharSequence param2CharSequence) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.trust.ITrustListener");
          if (param2CharSequence != null) {
            parcel.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
            ITrustListener.Stub.getDefaultImpl().onTrustError(param2CharSequence);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onTrustManagedChanged(boolean param2Boolean, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.trust.ITrustListener");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
            ITrustListener.Stub.getDefaultImpl().onTrustManagedChanged(param2Boolean, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ITrustListener {
    public static ITrustListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.trust.ITrustListener";
    }
    
    public void onTrustChanged(boolean param1Boolean, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.trust.ITrustListener");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
          ITrustListener.Stub.getDefaultImpl().onTrustChanged(param1Boolean, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTrustError(CharSequence param1CharSequence) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.trust.ITrustListener");
        if (param1CharSequence != null) {
          parcel.writeInt(1);
          TextUtils.writeToParcel(param1CharSequence, parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
          ITrustListener.Stub.getDefaultImpl().onTrustError(param1CharSequence);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTrustManagedChanged(boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.trust.ITrustListener");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && ITrustListener.Stub.getDefaultImpl() != null) {
          ITrustListener.Stub.getDefaultImpl().onTrustManagedChanged(param1Boolean, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */