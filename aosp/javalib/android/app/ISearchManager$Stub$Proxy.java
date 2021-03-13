package android.app;

import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements ISearchManager {
  public static ISearchManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public SearchableInfo getSearchableInfo(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.ISearchManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null)
        return ISearchManager.Stub.getDefaultImpl().getSearchableInfo(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        SearchableInfo searchableInfo = (SearchableInfo)SearchableInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (SearchableInfo)paramComponentName;
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
  
  public void launchAssist(int paramInt, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.ISearchManager");
      parcel1.writeInt(paramInt);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ISearchManager.Stub.getDefaultImpl() != null) {
        ISearchManager.Stub.getDefaultImpl().launchAssist(paramInt, paramBundle);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */