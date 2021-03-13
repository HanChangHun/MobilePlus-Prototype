package android.companion;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

public abstract class Stub extends Binder implements IFindDeviceCallback {
  private static final String DESCRIPTOR = "android.companion.IFindDeviceCallback";
  
  static final int TRANSACTION_onFailure = 2;
  
  static final int TRANSACTION_onSuccess = 1;
  
  public Stub() {
    attachInterface(this, "android.companion.IFindDeviceCallback");
  }
  
  public static IFindDeviceCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.companion.IFindDeviceCallback");
    return (iInterface != null && iInterface instanceof IFindDeviceCallback) ? (IFindDeviceCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFindDeviceCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onFailure") : "onSuccess";
  }
  
  public static boolean setDefaultImpl(IFindDeviceCallback paramIFindDeviceCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFindDeviceCallback != null) {
        Proxy.sDefaultImpl = paramIFindDeviceCallback;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.companion.IFindDeviceCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.companion.IFindDeviceCallback");
      if (paramParcel1.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onFailure((CharSequence)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.companion.IFindDeviceCallback");
    if (paramParcel1.readInt() != 0) {
      PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onSuccess((PendingIntent)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/IFindDeviceCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */