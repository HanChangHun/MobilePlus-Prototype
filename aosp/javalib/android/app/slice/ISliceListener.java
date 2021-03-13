package android.app.slice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISliceListener extends IInterface {
  void onSliceUpdated(Slice paramSlice) throws RemoteException;
  
  public static class Default implements ISliceListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onSliceUpdated(Slice param1Slice) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISliceListener {
    private static final String DESCRIPTOR = "android.app.slice.ISliceListener";
    
    static final int TRANSACTION_onSliceUpdated = 1;
    
    public Stub() {
      attachInterface(this, "android.app.slice.ISliceListener");
    }
    
    public static ISliceListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.slice.ISliceListener");
      return (iInterface != null && iInterface instanceof ISliceListener) ? (ISliceListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISliceListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onSliceUpdated";
    }
    
    public static boolean setDefaultImpl(ISliceListener param1ISliceListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISliceListener != null) {
          Proxy.sDefaultImpl = param1ISliceListener;
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
        param1Parcel2.writeString("android.app.slice.ISliceListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.slice.ISliceListener");
      if (param1Parcel1.readInt() != 0) {
        Slice slice = (Slice)Slice.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onSliceUpdated((Slice)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements ISliceListener {
      public static ISliceListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.slice.ISliceListener";
      }
      
      public void onSliceUpdated(Slice param2Slice) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.slice.ISliceListener");
          if (param2Slice != null) {
            parcel.writeInt(1);
            param2Slice.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && ISliceListener.Stub.getDefaultImpl() != null) {
            ISliceListener.Stub.getDefaultImpl().onSliceUpdated(param2Slice);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISliceListener {
    public static ISliceListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.slice.ISliceListener";
    }
    
    public void onSliceUpdated(Slice param1Slice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.slice.ISliceListener");
        if (param1Slice != null) {
          parcel.writeInt(1);
          param1Slice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ISliceListener.Stub.getDefaultImpl() != null) {
          ISliceListener.Stub.getDefaultImpl().onSliceUpdated(param1Slice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */