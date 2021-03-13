package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPredictionManager extends IInterface {
  void createPredictionSession(AppPredictionContext paramAppPredictionContext, AppPredictionSessionId paramAppPredictionSessionId, IBinder paramIBinder) throws RemoteException;
  
  void notifyAppTargetEvent(AppPredictionSessionId paramAppPredictionSessionId, AppTargetEvent paramAppTargetEvent) throws RemoteException;
  
  void notifyLaunchLocationShown(AppPredictionSessionId paramAppPredictionSessionId, String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException;
  
  void onDestroyPredictionSession(AppPredictionSessionId paramAppPredictionSessionId) throws RemoteException;
  
  void registerPredictionUpdates(AppPredictionSessionId paramAppPredictionSessionId, IPredictionCallback paramIPredictionCallback) throws RemoteException;
  
  void requestPredictionUpdate(AppPredictionSessionId paramAppPredictionSessionId) throws RemoteException;
  
  void sortAppTargets(AppPredictionSessionId paramAppPredictionSessionId, ParceledListSlice paramParceledListSlice, IPredictionCallback paramIPredictionCallback) throws RemoteException;
  
  void unregisterPredictionUpdates(AppPredictionSessionId paramAppPredictionSessionId, IPredictionCallback paramIPredictionCallback) throws RemoteException;
  
  public static class Default implements IPredictionManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void createPredictionSession(AppPredictionContext param1AppPredictionContext, AppPredictionSessionId param1AppPredictionSessionId, IBinder param1IBinder) throws RemoteException {}
    
    public void notifyAppTargetEvent(AppPredictionSessionId param1AppPredictionSessionId, AppTargetEvent param1AppTargetEvent) throws RemoteException {}
    
    public void notifyLaunchLocationShown(AppPredictionSessionId param1AppPredictionSessionId, String param1String, ParceledListSlice param1ParceledListSlice) throws RemoteException {}
    
    public void onDestroyPredictionSession(AppPredictionSessionId param1AppPredictionSessionId) throws RemoteException {}
    
    public void registerPredictionUpdates(AppPredictionSessionId param1AppPredictionSessionId, IPredictionCallback param1IPredictionCallback) throws RemoteException {}
    
    public void requestPredictionUpdate(AppPredictionSessionId param1AppPredictionSessionId) throws RemoteException {}
    
    public void sortAppTargets(AppPredictionSessionId param1AppPredictionSessionId, ParceledListSlice param1ParceledListSlice, IPredictionCallback param1IPredictionCallback) throws RemoteException {}
    
    public void unregisterPredictionUpdates(AppPredictionSessionId param1AppPredictionSessionId, IPredictionCallback param1IPredictionCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPredictionManager {
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
    
    public static IPredictionManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.prediction.IPredictionManager");
      return (iInterface != null && iInterface instanceof IPredictionManager) ? (IPredictionManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPredictionManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IPredictionManager param1IPredictionManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPredictionManager != null) {
          Proxy.sDefaultImpl = param1IPredictionManager;
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
      if (param1Int1 != 1598968902) {
        AppPredictionSessionId appPredictionSessionId;
        ParceledListSlice parceledListSlice;
        String str;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              AppPredictionSessionId appPredictionSessionId1 = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onDestroyPredictionSession((AppPredictionSessionId)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              AppPredictionSessionId appPredictionSessionId1 = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            requestPredictionUpdate((AppPredictionSessionId)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              appPredictionSessionId = null;
            } 
            unregisterPredictionUpdates(appPredictionSessionId, IPredictionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              appPredictionSessionId = null;
            } 
            registerPredictionUpdates(appPredictionSessionId, IPredictionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              appPredictionSessionId = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              parceledListSlice = null;
            } 
            sortAppTargets(appPredictionSessionId, parceledListSlice, IPredictionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              appPredictionSessionId = null;
            } 
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              ParceledListSlice parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            notifyLaunchLocationShown(appPredictionSessionId, str, (ParceledListSlice)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
            if (param1Parcel1.readInt() != 0) {
              appPredictionSessionId = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
            } else {
              appPredictionSessionId = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              AppTargetEvent appTargetEvent = (AppTargetEvent)AppTargetEvent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            notifyAppTargetEvent(appPredictionSessionId, (AppTargetEvent)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.prediction.IPredictionManager");
        if (param1Parcel1.readInt() != 0) {
          AppPredictionContext appPredictionContext = (AppPredictionContext)AppPredictionContext.CREATOR.createFromParcel(param1Parcel1);
        } else {
          appPredictionSessionId = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          AppPredictionSessionId appPredictionSessionId1 = (AppPredictionSessionId)AppPredictionSessionId.CREATOR.createFromParcel(param1Parcel1);
        } else {
          str = null;
        } 
        createPredictionSession((AppPredictionContext)appPredictionSessionId, (AppPredictionSessionId)str, param1Parcel1.readStrongBinder());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.app.prediction.IPredictionManager");
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
  
  private static class Proxy implements IPredictionManager {
    public static IPredictionManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void createPredictionSession(AppPredictionContext param1AppPredictionContext, AppPredictionSessionId param1AppPredictionSessionId, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionContext != null) {
          parcel1.writeInt(1);
          param1AppPredictionContext.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().createPredictionSession(param1AppPredictionContext, param1AppPredictionSessionId, param1IBinder);
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
    
    public void notifyAppTargetEvent(AppPredictionSessionId param1AppPredictionSessionId, AppTargetEvent param1AppTargetEvent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1AppTargetEvent != null) {
          parcel1.writeInt(1);
          param1AppTargetEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().notifyAppTargetEvent(param1AppPredictionSessionId, param1AppTargetEvent);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyLaunchLocationShown(AppPredictionSessionId param1AppPredictionSessionId, String param1String, ParceledListSlice param1ParceledListSlice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (param1ParceledListSlice != null) {
          parcel1.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().notifyLaunchLocationShown(param1AppPredictionSessionId, param1String, param1ParceledListSlice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onDestroyPredictionSession(AppPredictionSessionId param1AppPredictionSessionId) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().onDestroyPredictionSession(param1AppPredictionSessionId);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerPredictionUpdates(AppPredictionSessionId param1AppPredictionSessionId, IPredictionCallback param1IPredictionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IPredictionCallback != null) {
          iBinder = param1IPredictionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().registerPredictionUpdates(param1AppPredictionSessionId, param1IPredictionCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestPredictionUpdate(AppPredictionSessionId param1AppPredictionSessionId) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().requestPredictionUpdate(param1AppPredictionSessionId);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sortAppTargets(AppPredictionSessionId param1AppPredictionSessionId, ParceledListSlice param1ParceledListSlice, IPredictionCallback param1IPredictionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1ParceledListSlice != null) {
          parcel1.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IPredictionCallback != null) {
          iBinder = param1IPredictionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().sortAppTargets(param1AppPredictionSessionId, param1ParceledListSlice, param1IPredictionCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterPredictionUpdates(AppPredictionSessionId param1AppPredictionSessionId, IPredictionCallback param1IPredictionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
        if (param1AppPredictionSessionId != null) {
          parcel1.writeInt(1);
          param1AppPredictionSessionId.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IPredictionCallback != null) {
          iBinder = param1IPredictionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
          IPredictionManager.Stub.getDefaultImpl().unregisterPredictionUpdates(param1AppPredictionSessionId, param1IPredictionCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */