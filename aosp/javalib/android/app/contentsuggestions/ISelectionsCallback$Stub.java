package android.app.contentsuggestions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements ISelectionsCallback {
  private static final String DESCRIPTOR = "android.app.contentsuggestions.ISelectionsCallback";
  
  static final int TRANSACTION_onContentSelectionsAvailable = 1;
  
  public Stub() {
    attachInterface(this, "android.app.contentsuggestions.ISelectionsCallback");
  }
  
  public static ISelectionsCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.contentsuggestions.ISelectionsCallback");
    return (iInterface != null && iInterface instanceof ISelectionsCallback) ? (ISelectionsCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISelectionsCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onContentSelectionsAvailable";
  }
  
  public static boolean setDefaultImpl(ISelectionsCallback paramISelectionsCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISelectionsCallback != null) {
        Proxy.sDefaultImpl = paramISelectionsCallback;
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
      paramParcel2.writeString("android.app.contentsuggestions.ISelectionsCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.contentsuggestions.ISelectionsCallback");
    onContentSelectionsAvailable(paramParcel1.readInt(), paramParcel1.createTypedArrayList(ContentSelection.CREATOR));
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


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ISelectionsCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */