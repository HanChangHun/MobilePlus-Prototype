package android.database;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IContentObserver {
  private static final String DESCRIPTOR = "android.database.IContentObserver";
  
  static final int TRANSACTION_onChange = 1;
  
  static final int TRANSACTION_onChangeEtc = 2;
  
  public Stub() {
    attachInterface(this, "android.database.IContentObserver");
  }
  
  public static IContentObserver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.database.IContentObserver");
    return (iInterface != null && iInterface instanceof IContentObserver) ? (IContentObserver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContentObserver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onChangeEtc") : "onChange";
  }
  
  public static boolean setDefaultImpl(IContentObserver paramIContentObserver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContentObserver != null) {
        Proxy.sDefaultImpl = paramIContentObserver;
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
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.database.IContentObserver");
        return true;
      } 
      paramParcel1.enforceInterface("android.database.IContentObserver");
      if (paramParcel1.readInt() != 0)
        bool2 = true; 
      onChangeEtc(bool2, (Uri[])paramParcel1.createTypedArray(Uri.CREATOR), paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.database.IContentObserver");
    bool2 = bool1;
    if (paramParcel1.readInt() != 0)
      bool2 = true; 
    if (paramParcel1.readInt() != 0) {
      Uri uri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onChange(bool2, (Uri)paramParcel2, paramParcel1.readInt());
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


/* Location:              /home/chun/Desktop/temp/!/android/database/IContentObserver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */