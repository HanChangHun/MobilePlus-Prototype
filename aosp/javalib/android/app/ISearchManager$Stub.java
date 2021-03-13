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

public abstract class Stub extends Binder implements ISearchManager {
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
  
  public static ISearchManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.ISearchManager");
    return (iInterface != null && iInterface instanceof ISearchManager) ? (ISearchManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISearchManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(ISearchManager paramISearchManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISearchManager != null) {
        Proxy.sDefaultImpl = paramISearchManager;
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
      ComponentName componentName;
      List<ResolveInfo> list;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.app.ISearchManager");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          launchAssist(paramInt1, (Bundle)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.ISearchManager");
          componentName = getWebSearchActivity();
          paramParcel2.writeNoException();
          if (componentName != null) {
            paramParcel2.writeInt(1);
            componentName.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 4:
          componentName.enforceInterface("android.app.ISearchManager");
          componentName = getGlobalSearchActivity();
          paramParcel2.writeNoException();
          if (componentName != null) {
            paramParcel2.writeInt(1);
            componentName.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          componentName.enforceInterface("android.app.ISearchManager");
          list = getGlobalSearchActivities();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 2:
          list.enforceInterface("android.app.ISearchManager");
          list = (List)getSearchablesInGlobalSearch();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
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
      paramParcel2.writeNoException();
      if (searchableInfo != null) {
        paramParcel2.writeInt(1);
        searchableInfo.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    paramParcel2.writeString("android.app.ISearchManager");
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


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */