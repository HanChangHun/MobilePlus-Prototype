package android.app.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;

public abstract class Stub extends Binder implements ITrustListener {
  private static final String DESCRIPTOR = "android.app.trust.ITrustListener";
  
  static final int TRANSACTION_onTrustChanged = 1;
  
  static final int TRANSACTION_onTrustError = 3;
  
  static final int TRANSACTION_onTrustManagedChanged = 2;
  
  public Stub() {
    attachInterface(this, "android.app.trust.ITrustListener");
  }
  
  public static ITrustListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.trust.ITrustListener");
    return (iInterface != null && iInterface instanceof ITrustListener) ? (ITrustListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITrustListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "onTrustError") : "onTrustManagedChanged") : "onTrustChanged";
  }
  
  public static boolean setDefaultImpl(ITrustListener paramITrustListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITrustListener != null) {
        Proxy.sDefaultImpl = paramITrustListener;
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
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.app.trust.ITrustListener");
          return true;
        } 
        paramParcel1.enforceInterface("android.app.trust.ITrustListener");
        if (paramParcel1.readInt() != 0) {
          CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel1);
        } else {
          paramParcel1 = null;
        } 
        onTrustError((CharSequence)paramParcel1);
        return true;
      } 
      paramParcel1.enforceInterface("android.app.trust.ITrustListener");
      if (paramParcel1.readInt() != 0)
        bool2 = true; 
      onTrustManagedChanged(bool2, paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.trust.ITrustListener");
    bool2 = bool1;
    if (paramParcel1.readInt() != 0)
      bool2 = true; 
    onTrustChanged(bool2, paramParcel1.readInt(), paramParcel1.readInt());
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


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */