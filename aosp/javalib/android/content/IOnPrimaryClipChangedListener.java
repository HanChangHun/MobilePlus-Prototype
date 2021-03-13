package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOnPrimaryClipChangedListener extends IInterface {
  void dispatchPrimaryClipChanged() throws RemoteException;
  
  public static class Default implements IOnPrimaryClipChangedListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void dispatchPrimaryClipChanged() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IOnPrimaryClipChangedListener {
    private static final String DESCRIPTOR = "android.content.IOnPrimaryClipChangedListener";
    
    static final int TRANSACTION_dispatchPrimaryClipChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.content.IOnPrimaryClipChangedListener");
    }
    
    public static IOnPrimaryClipChangedListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.IOnPrimaryClipChangedListener");
      return (iInterface != null && iInterface instanceof IOnPrimaryClipChangedListener) ? (IOnPrimaryClipChangedListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IOnPrimaryClipChangedListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "dispatchPrimaryClipChanged";
    }
    
    public static boolean setDefaultImpl(IOnPrimaryClipChangedListener param1IOnPrimaryClipChangedListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IOnPrimaryClipChangedListener != null) {
          Proxy.sDefaultImpl = param1IOnPrimaryClipChangedListener;
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
        param1Parcel2.writeString("android.content.IOnPrimaryClipChangedListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.IOnPrimaryClipChangedListener");
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
  
  private static class Proxy implements IOnPrimaryClipChangedListener {
    public static IOnPrimaryClipChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IOnPrimaryClipChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */