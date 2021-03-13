package android.app;

import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface ISearchManager extends IInterface {
  List<ResolveInfo> getGlobalSearchActivities() throws RemoteException;
  
  ComponentName getGlobalSearchActivity() throws RemoteException;
  
  SearchableInfo getSearchableInfo(ComponentName paramComponentName) throws RemoteException;
  
  List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException;
  
  ComponentName getWebSearchActivity() throws RemoteException;
  
  void launchAssist(int paramInt, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements ISearchManager {
    public IBinder asBinder() {
      return null;
    }
    
    public List<ResolveInfo> getGlobalSearchActivities() throws RemoteException {
      return null;
    }
    
    public ComponentName getGlobalSearchActivity() throws RemoteException {
      return null;
    }
    
    public SearchableInfo getSearchableInfo(ComponentName param1ComponentName) throws RemoteException {
      return null;
    }
    
    public List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException {
      return null;
    }
    
    public ComponentName getWebSearchActivity() throws RemoteException {
      return null;
    }
    
    public void launchAssist(int param1Int, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISearchManager {
    private static final String DESCRIPTOR = "android.app.ISearchManager";
    
    static final int TRANSACTION_getGlobalSearchActivities = 3;
    
    static final int TRANSACTION_getGlobalSearchActivity = 4;
    
    static final int TRANSACTION_getSearchableInfo = 1;
    
    static final int TRANSACTION_getSearchablesInGlobalSearch = 2;
    
    static final int TRANSACTION_getWebSearchActivity = 5;
    
    static final int TRANSACTION_launchAssist = 6;
    
    public Stub() {
      attachInterface(this, "android.app.ISearchManager");
    }
    
    public static ISearchManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.ISearchManager");
      return (iInterface != null && iInterface instanceof ISearchManager) ? (ISearchManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISearchManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 6:
          return "launchAssist";
        case 5:
          return "getWebSearchActivity";
        case 4:
          return "getGlobalSearchActivity";
        case 3:
          return "getGlobalSearchActivities";
        case 2:
          return "getSearchablesInGlobalSearch";
        case 1:
          break;
      } 
      return "getSearchableInfo";
    }
    
    public static boolean setDefaultImpl(ISearchManager param1ISearchManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISearchManager != null) {
          Proxy.sDefaultImpl = param1ISearchManager;
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
        ComponentName componentName;
        List<ResolveInfo> list;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.app.ISearchManager");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            launchAssist(param1Int1, (Bundle)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.ISearchManager");
            componentName = getWebSearchActivity();
            param1Parcel2.writeNoException();
            if (componentName != null) {
              param1Parcel2.writeInt(1);
              componentName.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 4:
            componentName.enforceInterface("android.app.ISearchManager");
            componentName = getGlobalSearchActivity();
            param1Parcel2.writeNoException();
            if (componentName != null) {
              param1Parcel2.writeInt(1);
              componentName.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 3:
            componentName.enforceInterface("android.app.ISearchManager");
            list = getGlobalSearchActivities();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 2:
            list.enforceInterface("android.app.ISearchManager");
            list = (List)getSearchablesInGlobalSearch();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.app.ISearchManager");
        if (list.readInt() != 0) {
          ComponentName componentName1 = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
        } else {
          list = null;
        } 
        SearchableInfo searchableInfo = getSearchableInfo((ComponentName)list);
        param1Parcel2.writeNoException();
        if (searchableInfo != null) {
          param1Parcel2.writeInt(1);
          searchableInfo.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        return true;
      } 
      param1Parcel2.writeString("android.app.ISearchManager");
      return true;
    }
    
    private static class Proxy implements ISearchManager {
      public static ISearchManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public List<ResolveInfo> getGlobalSearchActivities() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.ISearchManager");
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
            return ISearchManager.Stub.getDefaultImpl().getGlobalSearchActivities(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ResolveInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ComponentName getGlobalSearchActivity() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ComponentName componentName;
          parcel1.writeInterfaceToken("android.app.ISearchManager");
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
            componentName = ISearchManager.Stub.getDefaultImpl().getGlobalSearchActivity();
            return componentName;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
          } else {
            componentName = null;
          } 
          return componentName;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.ISearchManager";
      }
      
      public SearchableInfo getSearchableInfo(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.ISearchManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
            return ISearchManager.Stub.getDefaultImpl().getSearchableInfo(param2ComponentName); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            SearchableInfo searchableInfo = (SearchableInfo)SearchableInfo.CREATOR.createFromParcel(parcel2);
          } else {
            param2ComponentName = null;
          } 
          return (SearchableInfo)param2ComponentName;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.ISearchManager");
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
            return ISearchManager.Stub.getDefaultImpl().getSearchablesInGlobalSearch(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(SearchableInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ComponentName getWebSearchActivity() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ComponentName componentName;
          parcel1.writeInterfaceToken("android.app.ISearchManager");
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
            componentName = ISearchManager.Stub.getDefaultImpl().getWebSearchActivity();
            return componentName;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
          } else {
            componentName = null;
          } 
          return componentName;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void launchAssist(int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.ISearchManager");
          parcel1.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
            ISearchManager.Stub.getDefaultImpl().launchAssist(param2Int, param2Bundle);
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
  
  private static class Proxy implements ISearchManager {
    public static ISearchManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public List<ResolveInfo> getGlobalSearchActivities() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.ISearchManager");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
          return ISearchManager.Stub.getDefaultImpl().getGlobalSearchActivities(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ResolveInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getGlobalSearchActivity() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.ISearchManager");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
          componentName = ISearchManager.Stub.getDefaultImpl().getGlobalSearchActivity();
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ISearchManager";
    }
    
    public SearchableInfo getSearchableInfo(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.ISearchManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
          return ISearchManager.Stub.getDefaultImpl().getSearchableInfo(param1ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          SearchableInfo searchableInfo = (SearchableInfo)SearchableInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param1ComponentName = null;
        } 
        return (SearchableInfo)param1ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.ISearchManager");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
          return ISearchManager.Stub.getDefaultImpl().getSearchablesInGlobalSearch(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(SearchableInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getWebSearchActivity() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ComponentName componentName;
        parcel1.writeInterfaceToken("android.app.ISearchManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
          componentName = ISearchManager.Stub.getDefaultImpl().getWebSearchActivity();
          return componentName;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          componentName = null;
        } 
        return componentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void launchAssist(int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.ISearchManager");
        parcel1.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
          ISearchManager.Stub.getDefaultImpl().launchAssist(param1Int, param1Bundle);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */