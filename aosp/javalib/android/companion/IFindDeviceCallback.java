package android.companion;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

public interface IFindDeviceCallback extends IInterface {
  void onFailure(CharSequence paramCharSequence) throws RemoteException;
  
  void onSuccess(PendingIntent paramPendingIntent) throws RemoteException;
  
  public static class Default implements IFindDeviceCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onFailure(CharSequence param1CharSequence) throws RemoteException {}
    
    public void onSuccess(PendingIntent param1PendingIntent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IFindDeviceCallback {
    private static final String DESCRIPTOR = "android.companion.IFindDeviceCallback";
    
    static final int TRANSACTION_onFailure = 2;
    
    static final int TRANSACTION_onSuccess = 1;
    
    public Stub() {
      attachInterface(this, "android.companion.IFindDeviceCallback");
    }
    
    public static IFindDeviceCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.companion.IFindDeviceCallback");
      return (iInterface != null && iInterface instanceof IFindDeviceCallback) ? (IFindDeviceCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IFindDeviceCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onFailure") : "onSuccess";
    }
    
    public static boolean setDefaultImpl(IFindDeviceCallback param1IFindDeviceCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IFindDeviceCallback != null) {
          Proxy.sDefaultImpl = param1IFindDeviceCallback;
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
          param1Parcel2.writeString("android.companion.IFindDeviceCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.companion.IFindDeviceCallback");
        if (param1Parcel1.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onFailure((CharSequence)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.companion.IFindDeviceCallback");
      if (param1Parcel1.readInt() != 0) {
        PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onSuccess((PendingIntent)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IFindDeviceCallback {
      public static IFindDeviceCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.companion.IFindDeviceCallback";
      }
      
      public void onFailure(CharSequence param2CharSequence) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
          if (param2CharSequence != null) {
            parcel.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IFindDeviceCallback.Stub.getDefaultImpl() != null) {
            IFindDeviceCallback.Stub.getDefaultImpl().onFailure(param2CharSequence);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onSuccess(PendingIntent param2PendingIntent) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
          if (param2PendingIntent != null) {
            parcel.writeInt(1);
            param2PendingIntent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IFindDeviceCallback.Stub.getDefaultImpl() != null) {
            IFindDeviceCallback.Stub.getDefaultImpl().onSuccess(param2PendingIntent);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IFindDeviceCallback {
    public static IFindDeviceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.companion.IFindDeviceCallback";
    }
    
    public void onFailure(CharSequence param1CharSequence) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
        if (param1CharSequence != null) {
          parcel.writeInt(1);
          TextUtils.writeToParcel(param1CharSequence, parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IFindDeviceCallback.Stub.getDefaultImpl() != null) {
          IFindDeviceCallback.Stub.getDefaultImpl().onFailure(param1CharSequence);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSuccess(PendingIntent param1PendingIntent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.companion.IFindDeviceCallback");
        if (param1PendingIntent != null) {
          parcel.writeInt(1);
          param1PendingIntent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IFindDeviceCallback.Stub.getDefaultImpl() != null) {
          IFindDeviceCallback.Stub.getDefaultImpl().onSuccess(param1PendingIntent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/IFindDeviceCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */