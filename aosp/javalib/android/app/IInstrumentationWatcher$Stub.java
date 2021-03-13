package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IInstrumentationWatcher {
  private static final String DESCRIPTOR = "android.app.IInstrumentationWatcher";
  
  static final int TRANSACTION_instrumentationFinished = 2;
  
  static final int TRANSACTION_instrumentationStatus = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IInstrumentationWatcher");
  }
  
  public static IInstrumentationWatcher asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IInstrumentationWatcher");
    return (iInterface != null && iInterface instanceof IInstrumentationWatcher) ? (IInstrumentationWatcher)iInterface : new Proxy(paramIBinder);
  }
  
  public static IInstrumentationWatcher getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "instrumentationFinished") : "instrumentationStatus";
  }
  
  public static boolean setDefaultImpl(IInstrumentationWatcher paramIInstrumentationWatcher) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIInstrumentationWatcher != null) {
        Proxy.sDefaultImpl = paramIInstrumentationWatcher;
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
    ComponentName componentName;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.IInstrumentationWatcher");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IInstrumentationWatcher");
      if (paramParcel1.readInt() != 0) {
        componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
      } else {
        componentName = null;
      } 
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      instrumentationFinished(componentName, paramInt1, (Bundle)paramParcel1);
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IInstrumentationWatcher");
    if (paramParcel1.readInt() != 0) {
      componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      componentName = null;
    } 
    paramInt1 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    instrumentationStatus(componentName, paramInt1, (Bundle)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IInstrumentationWatcher {
    public static IInstrumentationWatcher sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IInstrumentationWatcher";
    }
    
    public void instrumentationFinished(ComponentName param2ComponentName, int param2Int, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IInstrumentationWatcher");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IInstrumentationWatcher.Stub.getDefaultImpl() != null) {
          IInstrumentationWatcher.Stub.getDefaultImpl().instrumentationFinished(param2ComponentName, param2Int, param2Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void instrumentationStatus(ComponentName param2ComponentName, int param2Int, Bundle param2Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IInstrumentationWatcher");
        if (param2ComponentName != null) {
          parcel1.writeInt(1);
          param2ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2Bundle != null) {
          parcel1.writeInt(1);
          param2Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IInstrumentationWatcher.Stub.getDefaultImpl() != null) {
          IInstrumentationWatcher.Stub.getDefaultImpl().instrumentationStatus(param2ComponentName, param2Int, param2Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstrumentationWatcher$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */