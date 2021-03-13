package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IQualifiedNetworksServiceCallback {
  private static final String DESCRIPTOR = "android.telephony.data.IQualifiedNetworksServiceCallback";
  
  static final int TRANSACTION_onQualifiedNetworkTypesChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.telephony.data.IQualifiedNetworksServiceCallback");
  }
  
  public static IQualifiedNetworksServiceCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.telephony.data.IQualifiedNetworksServiceCallback");
    return (iInterface != null && iInterface instanceof IQualifiedNetworksServiceCallback) ? (IQualifiedNetworksServiceCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IQualifiedNetworksServiceCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onQualifiedNetworkTypesChanged";
  }
  
  public static boolean setDefaultImpl(IQualifiedNetworksServiceCallback paramIQualifiedNetworksServiceCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIQualifiedNetworksServiceCallback != null) {
        Proxy.sDefaultImpl = paramIQualifiedNetworksServiceCallback;
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
      paramParcel2.writeString("android.telephony.data.IQualifiedNetworksServiceCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.telephony.data.IQualifiedNetworksServiceCallback");
    onQualifiedNetworkTypesChanged(paramParcel1.readInt(), paramParcel1.createIntArray());
    return true;
  }
  
  private static class Proxy implements IQualifiedNetworksServiceCallback {
    public static IQualifiedNetworksServiceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.telephony.data.IQualifiedNetworksServiceCallback";
    }
    
    public void onQualifiedNetworkTypesChanged(int param2Int, int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IQualifiedNetworksServiceCallback");
        parcel.writeInt(param2Int);
        parcel.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(1, parcel, null, 1) && IQualifiedNetworksServiceCallback.Stub.getDefaultImpl() != null) {
          IQualifiedNetworksServiceCallback.Stub.getDefaultImpl().onQualifiedNetworkTypesChanged(param2Int, param2ArrayOfint);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksServiceCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */