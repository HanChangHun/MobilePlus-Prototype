package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IServiceConnection {
  private static final String DESCRIPTOR = "android.app.IServiceConnection";
  
  static final int TRANSACTION_connected = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IServiceConnection");
  }
  
  public static IServiceConnection asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IServiceConnection");
    return (iInterface != null && iInterface instanceof IServiceConnection) ? (IServiceConnection)iInterface : new Proxy(paramIBinder);
  }
  
  public static IServiceConnection getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "connected";
  }
  
  public static boolean setDefaultImpl(IServiceConnection paramIServiceConnection) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIServiceConnection != null) {
        Proxy.sDefaultImpl = paramIServiceConnection;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.app.IServiceConnection");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IServiceConnection");
    if (paramParcel1.readInt() != 0) {
      ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    IBinder iBinder = paramParcel1.readStrongBinder();
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    connected((ComponentName)paramParcel2, iBinder, bool);
    return true;
  }
  
  private static class Proxy implements IServiceConnection {
    public static IServiceConnection sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void connected(ComponentName param2ComponentName, IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IServiceConnection");
        boolean bool = false;
        if (param2ComponentName != null) {
          parcel.writeInt(1);
          param2ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStrongBinder(param2IBinder);
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IServiceConnection.Stub.getDefaultImpl() != null) {
          IServiceConnection.Stub.getDefaultImpl().connected(param2ComponentName, param2IBinder, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IServiceConnection";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IServiceConnection$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */