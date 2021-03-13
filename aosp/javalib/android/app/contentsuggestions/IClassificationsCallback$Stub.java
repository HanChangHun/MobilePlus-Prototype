package android.app.contentsuggestions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IClassificationsCallback {
  private static final String DESCRIPTOR = "android.app.contentsuggestions.IClassificationsCallback";
  
  static final int TRANSACTION_onContentClassificationsAvailable = 1;
  
  public Stub() {
    attachInterface(this, "android.app.contentsuggestions.IClassificationsCallback");
  }
  
  public static IClassificationsCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.contentsuggestions.IClassificationsCallback");
    return (iInterface != null && iInterface instanceof IClassificationsCallback) ? (IClassificationsCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IClassificationsCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onContentClassificationsAvailable";
  }
  
  public static boolean setDefaultImpl(IClassificationsCallback paramIClassificationsCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIClassificationsCallback != null) {
        Proxy.sDefaultImpl = paramIClassificationsCallback;
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
      paramParcel2.writeString("android.app.contentsuggestions.IClassificationsCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.contentsuggestions.IClassificationsCallback");
    onContentClassificationsAvailable(paramParcel1.readInt(), paramParcel1.createTypedArrayList(ContentClassification.CREATOR));
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


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IClassificationsCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */