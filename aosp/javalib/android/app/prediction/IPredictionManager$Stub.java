package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPredictionManager {
  private static final String DESCRIPTOR = "android.app.prediction.IPredictionManager";
  
  static final int TRANSACTION_createPredictionSession = 1;
  
  static final int TRANSACTION_notifyAppTargetEvent = 2;
  
  static final int TRANSACTION_notifyLaunchLocationShown = 3;
  
  static final int TRANSACTION_onDestroyPredictionSession = 8;
  
  static final int TRANSACTION_registerPredictionUpdates = 5;
  
  static final int TRANSACTION_requestPredictionUpdate = 7;
  
  static final int TRANSACTION_sortAppTargets = 4;
  
  static final int TRANSACTION_unregisterPredictionUpdates = 6;
  
  public Stub() {
    attachInterface(this, "android.app.prediction.IPredictionManager");
  }
  
  public static IPredictionManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.prediction.IPredictionManager");
    return (iInterface != null && iInterface instanceof IPredictionManager) ? (IPredictionManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPredictionManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 8:
        return "onDestroyPredictionSession";
      case 7:
        return "requestPredictionUpdate";
      case 6:
        return "unregisterPredictionUpdates";
      case 5:
        return "registerPredictionUpdates";
      case 4:
        return "sortAppTargets";
      case 3:
        return "notifyLaunchLocationShown";
      case 2:
        return "notifyAppTargetEvent";
      case 1:
        break;
    } 
    return "createPredictionSession";
  }
  
  public static boolean setDefaultImpl(IPredictionManager paramIPredictionManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPredictionManager != null) {
        Proxy.sDefaultImpl = paramIPredictionManager;
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
    if (paramInt1 != 1598968902) {
      AppPredictionSessionId appPredictionSessionId;
      ParceledListSlice parceledListSlice;
      String str;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 8:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            AppPredictionSessionId appPredictionSessionId1 = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onDestroyPredictionSession((AppPredictionSessionId)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            AppPredictionSessionId appPredictionSessionId1 = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          requestPredictionUpdate((AppPredictionSessionId)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            appPredictionSessionId = null;
          } 
          unregisterPredictionUpdates(appPredictionSessionId, IPredictionCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            appPredictionSessionId = null;
          } 
          registerPredictionUpdates(appPredictionSessionId, IPredictionCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            appPredictionSessionId = null;
          } 
          if (paramParcel1.readInt() != 0) {
            parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
          } else {
            parceledListSlice = null;
          } 
          sortAppTargets(appPredictionSessionId, parceledListSlice, IPredictionCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            appPredictionSessionId = null;
          } 
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            ParceledListSlice parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          notifyLaunchLocationShown(appPredictionSessionId, str, (ParceledListSlice)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
          if (paramParcel1.readInt() != 0) {
            appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
          } else {
            appPredictionSessionId = null;
          } 
          if (paramParcel1.readInt() != 0) {
            AppTargetEvent appTargetEvent = (AppTargetEvent)AppTargetEvent.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          notifyAppTargetEvent(appPredictionSessionId, (AppTargetEvent)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.prediction.IPredictionManager");
      if (paramParcel1.readInt() != 0) {
        AppPredictionContext appPredictionContext = (AppPredictionContext)AppPredictionContext.CREATOR.createFromParcel(paramParcel1);
      } else {
        appPredictionSessionId = null;
      } 
      if (paramParcel1.readInt() != 0) {
        AppPredictionSessionId appPredictionSessionId1 = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(paramParcel1);
      } else {
        str = null;
      } 
      createPredictionSession((AppPredictionContext)appPredictionSessionId, (AppPredictionSessionId)str, paramParcel1.readStrongBinder());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.prediction.IPredictionManager");
    return true;
  }
  
  private static class Proxy implements IPredictionManager {
    public static IPredictionManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void createPredictionSession(AppPredictionContext param2AppPredictionContext, AppPredictionSessionId param2AppPredictionSessionId, IBinder param2IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionContext != null) {
          parcel1.writeInt(1);
          param2AppPredictionContext.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().createPredictionSession(param2AppPredictionContext, param2AppPredictionSessionId, param2IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.prediction.IPredictionManager";
    }
    
    public void notifyAppTargetEvent(AppPredictionSessionId param2AppPredictionSessionId, AppTargetEvent param2AppTargetEvent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2AppTargetEvent != null) {
          parcel1.writeInt(1);
          param2AppTargetEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().notifyAppTargetEvent(param2AppPredictionSessionId, param2AppTargetEvent);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyLaunchLocationShown(AppPredictionSessionId param2AppPredictionSessionId, String param2String, ParceledListSlice param2ParceledListSlice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().notifyLaunchLocationShown(param2AppPredictionSessionId, param2String, param2ParceledListSlice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onDestroyPredictionSession(AppPredictionSessionId param2AppPredictionSessionId) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().onDestroyPredictionSession(param2AppPredictionSessionId);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerPredictionUpdates(AppPredictionSessionId param2AppPredictionSessionId, IPredictionCallback param2IPredictionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IPredictionCallback != null) {
          iBinder = param2IPredictionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().registerPredictionUpdates(param2AppPredictionSessionId, param2IPredictionCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestPredictionUpdate(AppPredictionSessionId param2AppPredictionSessionId) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().requestPredictionUpdate(param2AppPredictionSessionId);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sortAppTargets(AppPredictionSessionId param2AppPredictionSessionId, ParceledListSlice param2ParceledListSlice, IPredictionCallback param2IPredictionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IPredictionCallback != null) {
          iBinder = param2IPredictionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().sortAppTargets(param2AppPredictionSessionId, param2ParceledListSlice, param2IPredictionCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterPredictionUpdates(AppPredictionSessionId param2AppPredictionSessionId, IPredictionCallback param2IPredictionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param2AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param2AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IPredictionCallback != null) {
          iBinder = param2IPredictionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().unregisterPredictionUpdates(param2AppPredictionSessionId, param2IPredictionCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */