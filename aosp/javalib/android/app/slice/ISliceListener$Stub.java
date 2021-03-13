package android.app.slice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISliceListener {
  private static final String DESCRIPTOR = "android.app.slice.ISliceListener";
  
  static final int TRANSACTION_onSliceUpdated = 1;
  
  public Stub() {
    attachInterface(this, "android.app.slice.ISliceListener");
  }
  
  public static ISliceListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.slice.ISliceListener");
    return (iInterface != null && iInterface instanceof ISliceListener) ? (ISliceListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISliceListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onSliceUpdated";
  }
  
  public static boolean setDefaultImpl(ISliceListener paramISliceListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISliceListener != null) {
        Proxy.sDefaultImpl = paramISliceListener;
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
      paramParcel2.writeString("android.app.slice.ISliceListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.slice.ISliceListener");
    if (paramParcel1.readInt() != 0) {
      Slice slice = (Slice)Slice.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onSliceUpdated((Slice)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */