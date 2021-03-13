package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IContextHubCallback {
  private static final String DESCRIPTOR = "android.hardware.location.IContextHubCallback";
  
  static final int TRANSACTION_onMessageReceipt = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IContextHubCallback");
  }
  
  public static IContextHubCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IContextHubCallback");
    return (iInterface != null && iInterface instanceof IContextHubCallback) ? (IContextHubCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContextHubCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onMessageReceipt";
  }
  
  public static boolean setDefaultImpl(IContextHubCallback paramIContextHubCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContextHubCallback != null) {
        Proxy.sDefaultImpl = paramIContextHubCallback;
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
      paramParcel2.writeString("android.hardware.location.IContextHubCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IContextHubCallback");
    paramInt2 = paramParcel1.readInt();
    paramInt1 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      ContextHubMessage contextHubMessage = (ContextHubMessage)ContextHubMessage.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onMessageReceipt(paramInt2, paramInt1, (ContextHubMessage)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IContextHubCallback {
    public static IContextHubCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubCallback";
    }
    
    public void onMessageReceipt(int param2Int1, int param2Int2, ContextHubMessage param2ContextHubMessage) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IContextHubCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (param2ContextHubMessage != null) {
          parcel.writeInt(1);
          param2ContextHubMessage.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IContextHubCallback.Stub.getDefaultImpl() != null) {
          IContextHubCallback.Stub.getDefaultImpl().onMessageReceipt(param2Int1, param2Int2, param2ContextHubMessage);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */