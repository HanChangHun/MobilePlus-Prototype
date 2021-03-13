package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAppTraceRetriever {
  private static final String DESCRIPTOR = "android.app.IAppTraceRetriever";
  
  static final int TRANSACTION_getTraceFileDescriptor = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IAppTraceRetriever");
  }
  
  public static IAppTraceRetriever asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IAppTraceRetriever");
    return (iInterface != null && iInterface instanceof IAppTraceRetriever) ? (IAppTraceRetriever)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAppTraceRetriever getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "getTraceFileDescriptor";
  }
  
  public static boolean setDefaultImpl(IAppTraceRetriever paramIAppTraceRetriever) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAppTraceRetriever != null) {
        Proxy.sDefaultImpl = paramIAppTraceRetriever;
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
      paramParcel2.writeString("android.app.IAppTraceRetriever");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IAppTraceRetriever");
    ParcelFileDescriptor parcelFileDescriptor = getTraceFileDescriptor(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
    paramParcel2.writeNoException();
    if (parcelFileDescriptor != null) {
      paramParcel2.writeInt(1);
      parcelFileDescriptor.writeToParcel(paramParcel2, 1);
    } else {
      paramParcel2.writeInt(0);
    } 
    return true;
  }
  
  private static class Proxy implements IAppTraceRetriever {
    public static IAppTraceRetriever sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IAppTraceRetriever";
    }
    
    public ParcelFileDescriptor getTraceFileDescriptor(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAppTraceRetriever");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppTraceRetriever.Stub.getDefaultImpl() != null)
          return IAppTraceRetriever.Stub.getDefaultImpl().getTraceFileDescriptor(param2String, param2Int1, param2Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParcelFileDescriptor)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTraceRetriever$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */