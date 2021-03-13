package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IContextHubTransactionCallback {
  private static final String DESCRIPTOR = "android.hardware.location.IContextHubTransactionCallback";
  
  static final int TRANSACTION_onQueryResponse = 1;
  
  static final int TRANSACTION_onTransactionComplete = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IContextHubTransactionCallback");
  }
  
  public static IContextHubTransactionCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IContextHubTransactionCallback");
    return (iInterface != null && iInterface instanceof IContextHubTransactionCallback) ? (IContextHubTransactionCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContextHubTransactionCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onTransactionComplete") : "onQueryResponse";
  }
  
  public static boolean setDefaultImpl(IContextHubTransactionCallback paramIContextHubTransactionCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContextHubTransactionCallback != null) {
        Proxy.sDefaultImpl = paramIContextHubTransactionCallback;
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
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.hardware.location.IContextHubTransactionCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.location.IContextHubTransactionCallback");
      onTransactionComplete(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IContextHubTransactionCallback");
    onQueryResponse(paramParcel1.readInt(), paramParcel1.createTypedArrayList(NanoAppState.CREATOR));
    return true;
  }
  
  private static class Proxy implements IContextHubTransactionCallback {
    public static IContextHubTransactionCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubTransactionCallback";
    }
    
    public void onQueryResponse(int param2Int, List<NanoAppState> param2List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
        parcel.writeInt(param2Int);
        parcel.writeTypedList(param2List);
        if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
          IContextHubTransactionCallback.Stub.getDefaultImpl().onQueryResponse(param2Int, param2List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTransactionComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubTransactionCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IContextHubTransactionCallback.Stub.getDefaultImpl() != null) {
          IContextHubTransactionCallback.Stub.getDefaultImpl().onTransactionComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubTransactionCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */