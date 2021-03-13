package android.app.contentsuggestions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface ISelectionsCallback extends IInterface {
  void onContentSelectionsAvailable(int paramInt, List<ContentSelection> paramList) throws RemoteException;
  
  public static class Default implements ISelectionsCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onContentSelectionsAvailable(int param1Int, List<ContentSelection> param1List) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISelectionsCallback {
    private static final String DESCRIPTOR = "android.app.contentsuggestions.ISelectionsCallback";
    
    static final int TRANSACTION_onContentSelectionsAvailable = 1;
    
    public Stub() {
      attachInterface(this, "android.app.contentsuggestions.ISelectionsCallback");
    }
    
    public static ISelectionsCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.contentsuggestions.ISelectionsCallback");
      return (iInterface != null && iInterface instanceof ISelectionsCallback) ? (ISelectionsCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISelectionsCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onContentSelectionsAvailable";
    }
    
    public static boolean setDefaultImpl(ISelectionsCallback param1ISelectionsCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISelectionsCallback != null) {
          Proxy.sDefaultImpl = param1ISelectionsCallback;
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
        param1Parcel2.writeString("android.app.contentsuggestions.ISelectionsCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.contentsuggestions.ISelectionsCallback");
      onContentSelectionsAvailable(param1Parcel1.readInt(), param1Parcel1.createTypedArrayList(ContentSelection.CREATOR));
      return true;
    }
    
    private static class Proxy implements ISelectionsCallback {
      public static ISelectionsCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.contentsuggestions.ISelectionsCallback";
      }
      
      public void onContentSelectionsAvailable(int param2Int, List<ContentSelection> param2List) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.contentsuggestions.ISelectionsCallback");
          parcel.writeInt(param2Int);
          parcel.writeTypedList(param2List);
          if (!this.mRemote.transact(1, parcel, null, 1) && ISelectionsCallback.Stub.getDefaultImpl() != null) {
            ISelectionsCallback.Stub.getDefaultImpl().onContentSelectionsAvailable(param2Int, param2List);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISelectionsCallback {
    public static ISelectionsCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.contentsuggestions.ISelectionsCallback";
    }
    
    public void onContentSelectionsAvailable(int param1Int, List<ContentSelection> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.contentsuggestions.ISelectionsCallback");
        parcel.writeInt(param1Int);
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISelectionsCallback.Stub.getDefaultImpl() != null) {
          ISelectionsCallback.Stub.getDefaultImpl().onContentSelectionsAvailable(param1Int, param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ISelectionsCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */