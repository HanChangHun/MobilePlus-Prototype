package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IAppTraceRetriever extends IInterface {
  ParcelFileDescriptor getTraceFileDescriptor(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IAppTraceRetriever {
    public IBinder asBinder() {
      return null;
    }
    
    public ParcelFileDescriptor getTraceFileDescriptor(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      return null;
    }
  }
  
  public static abstract class Stub extends Binder implements IAppTraceRetriever {
    private static final String DESCRIPTOR = "android.app.IAppTraceRetriever";
    
    static final int TRANSACTION_getTraceFileDescriptor = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IAppTraceRetriever");
    }
    
    public static IAppTraceRetriever asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IAppTraceRetriever");
      return (iInterface != null && iInterface instanceof IAppTraceRetriever) ? (IAppTraceRetriever)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAppTraceRetriever getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "getTraceFileDescriptor";
    }
    
    public static boolean setDefaultImpl(IAppTraceRetriever param1IAppTraceRetriever) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAppTraceRetriever != null) {
          Proxy.sDefaultImpl = param1IAppTraceRetriever;
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
        param1Parcel2.writeString("android.app.IAppTraceRetriever");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IAppTraceRetriever");
      ParcelFileDescriptor parcelFileDescriptor = getTraceFileDescriptor(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt());
      param1Parcel2.writeNoException();
      if (parcelFileDescriptor != null) {
        param1Parcel2.writeInt(1);
        parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
      } else {
        param1Parcel2.writeInt(0);
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
  
  private static class Proxy implements IAppTraceRetriever {
    public static IAppTraceRetriever sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IAppTraceRetriever";
    }
    
    public ParcelFileDescriptor getTraceFileDescriptor(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IAppTraceRetriever");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppTraceRetriever.Stub.getDefaultImpl() != null)
          return IAppTraceRetriever.Stub.getDefaultImpl().getTraceFileDescriptor(param1String, param1Int1, param1Int2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParcelFileDescriptor)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTraceRetriever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */