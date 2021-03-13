package android.app.contentsuggestions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IClassificationsCallback extends IInterface {
  void onContentClassificationsAvailable(int paramInt, List<ContentClassification> paramList) throws RemoteException;
  
  public static class Default implements IClassificationsCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onContentClassificationsAvailable(int param1Int, List<ContentClassification> param1List) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IClassificationsCallback {
    private static final String DESCRIPTOR = "android.app.contentsuggestions.IClassificationsCallback";
    
    static final int TRANSACTION_onContentClassificationsAvailable = 1;
    
    public Stub() {
      attachInterface(this, "android.app.contentsuggestions.IClassificationsCallback");
    }
    
    public static IClassificationsCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.contentsuggestions.IClassificationsCallback");
      return (iInterface != null && iInterface instanceof IClassificationsCallback) ? (IClassificationsCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IClassificationsCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onContentClassificationsAvailable";
    }
    
    public static boolean setDefaultImpl(IClassificationsCallback param1IClassificationsCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IClassificationsCallback != null) {
          Proxy.sDefaultImpl = param1IClassificationsCallback;
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
        param1Parcel2.writeString("android.app.contentsuggestions.IClassificationsCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.contentsuggestions.IClassificationsCallback");
      onContentClassificationsAvailable(param1Parcel1.readInt(), param1Parcel1.createTypedArrayList(ContentClassification.CREATOR));
      return true;
    }
    
    private static class Proxy implements IClassificationsCallback {
      public static IClassificationsCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.contentsuggestions.IClassificationsCallback";
      }
      
      public void onContentClassificationsAvailable(int param2Int, List<ContentClassification> param2List) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.contentsuggestions.IClassificationsCallback");
          parcel.writeInt(param2Int);
          parcel.writeTypedList(param2List);
          if (!this.mRemote.transact(1, parcel, null, 1) && IClassificationsCallback.Stub.getDefaultImpl() != null) {
            IClassificationsCallback.Stub.getDefaultImpl().onContentClassificationsAvailable(param2Int, param2List);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IClassificationsCallback {
    public static IClassificationsCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.contentsuggestions.IClassificationsCallback";
    }
    
    public void onContentClassificationsAvailable(int param1Int, List<ContentClassification> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.contentsuggestions.IClassificationsCallback");
        parcel.writeInt(param1Int);
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(1, parcel, null, 1) && IClassificationsCallback.Stub.getDefaultImpl() != null) {
          IClassificationsCallback.Stub.getDefaultImpl().onContentClassificationsAvailable(param1Int, param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IClassificationsCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */