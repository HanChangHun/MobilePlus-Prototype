package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRestrictionsManager {
  private static final String DESCRIPTOR = "android.content.IRestrictionsManager";
  
  static final int TRANSACTION_createLocalApprovalIntent = 5;
  
  static final int TRANSACTION_getApplicationRestrictions = 1;
  
  static final int TRANSACTION_hasRestrictionsProvider = 2;
  
  static final int TRANSACTION_notifyPermissionResponse = 4;
  
  static final int TRANSACTION_requestPermission = 3;
  
  public Stub() {
    attachInterface(this, "android.content.IRestrictionsManager");
  }
  
  public static IRestrictionsManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.IRestrictionsManager");
    return (iInterface != null && iInterface instanceof IRestrictionsManager) ? (IRestrictionsManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRestrictionsManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "createLocalApprovalIntent") : "notifyPermissionResponse") : "requestPermission") : "hasRestrictionsProvider") : "getApplicationRestrictions";
  }
  
  public static boolean setDefaultImpl(IRestrictionsManager paramIRestrictionsManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRestrictionsManager != null) {
        Proxy.sDefaultImpl = paramIRestrictionsManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    Intent intent;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.content.IRestrictionsManager");
              return true;
            } 
            paramParcel1.enforceInterface("android.content.IRestrictionsManager");
            intent = createLocalApprovalIntent();
            paramParcel2.writeNoException();
            if (intent != null) {
              paramParcel2.writeInt(1);
              intent.writeToParcel(paramParcel2, 1);
            } else {
              paramParcel2.writeInt(0);
            } 
            return true;
          } 
          intent.enforceInterface("android.content.IRestrictionsManager");
          String str = intent.readString();
          if (intent.readInt() != 0) {
            PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)intent);
          } else {
            intent = null;
          } 
          notifyPermissionResponse(str, (PersistableBundle)intent);
          paramParcel2.writeNoException();
          return true;
        } 
        intent.enforceInterface("android.content.IRestrictionsManager");
        String str2 = intent.readString();
        String str3 = intent.readString();
        String str1 = intent.readString();
        if (intent.readInt() != 0) {
          PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)intent);
        } else {
          intent = null;
        } 
        requestPermission(str2, str3, str1, (PersistableBundle)intent);
        paramParcel2.writeNoException();
        return true;
      } 
      intent.enforceInterface("android.content.IRestrictionsManager");
      boolean bool = hasRestrictionsProvider();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool);
      return true;
    } 
    intent.enforceInterface("android.content.IRestrictionsManager");
    Bundle bundle = getApplicationRestrictions(intent.readString());
    paramParcel2.writeNoException();
    if (bundle != null) {
      paramParcel2.writeInt(1);
      bundle.writeToParcel(paramParcel2, 1);
    } else {
      paramParcel2.writeInt(0);
    } 
    return true;
  }
  
  private static class Proxy implements IRestrictionsManager {
    public static IRestrictionsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public Intent createLocalApprovalIntent() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Intent intent;
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          intent = IRestrictionsManager.Stub.getDefaultImpl().createLocalApprovalIntent();
          return intent;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
        } else {
          intent = null;
        } 
        return intent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getApplicationRestrictions(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null)
          return IRestrictionsManager.Stub.getDefaultImpl().getApplicationRestrictions(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (Bundle)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IRestrictionsManager";
    }
    
    public boolean hasRestrictionsProvider() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          bool = IRestrictionsManager.Stub.getDefaultImpl().hasRestrictionsProvider();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyPermissionResponse(String param2String, PersistableBundle param2PersistableBundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        parcel1.writeString(param2String);
        if (param2PersistableBundle != null) {
          parcel1.writeInt(1);
          param2PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          IRestrictionsManager.Stub.getDefaultImpl().notifyPermissionResponse(param2String, param2PersistableBundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestPermission(String param2String1, String param2String2, String param2String3, PersistableBundle param2PersistableBundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IRestrictionsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeString(param2String3);
        if (param2PersistableBundle != null) {
          parcel1.writeInt(1);
          param2PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRestrictionsManager.Stub.getDefaultImpl() != null) {
          IRestrictionsManager.Stub.getDefaultImpl().requestPermission(param2String1, param2String2, param2String3, param2PersistableBundle);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IRestrictionsManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */