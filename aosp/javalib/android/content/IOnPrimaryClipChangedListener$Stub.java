package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IOnPrimaryClipChangedListener {
  private static final String DESCRIPTOR = "android.content.IOnPrimaryClipChangedListener";
  
  static final int TRANSACTION_dispatchPrimaryClipChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.content.IOnPrimaryClipChangedListener");
  }
  
  public static IOnPrimaryClipChangedListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.IOnPrimaryClipChangedListener");
    return (iInterface != null && iInterface instanceof IOnPrimaryClipChangedListener) ? (IOnPrimaryClipChangedListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IOnPrimaryClipChangedListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "dispatchPrimaryClipChanged";
  }
  
  public static boolean setDefaultImpl(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIOnPrimaryClipChangedListener != null) {
        Proxy.sDefaultImpl = paramIOnPrimaryClipChangedListener;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.IOnPrimaryClipChangedListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.IOnPrimaryClipChangedListener");
    dispatchPrimaryClipChanged();
    return true;
  }
  
  private static class Proxy implements IOnPrimaryClipChangedListener {
    public static IOnPrimaryClipChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void dispatchPrimaryClipChanged() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.IOnPrimaryClipChangedListener");
        if (!this.mRemote.transact(1, parcel, null, 1) && IOnPrimaryClipChangedListener.Stub.getDefaultImpl() != null) {
          IOnPrimaryClipChangedListener.Stub.getDefaultImpl().dispatchPrimaryClipChanged();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IOnPrimaryClipChangedListener";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IOnPrimaryClipChangedListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */