package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPinItemRequest {
  public static IPinItemRequest sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean accept(Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
      boolean bool = true;
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
        bool = IPinItemRequest.Stub.getDefaultImpl().accept(paramBundle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public AppWidgetProviderInfo getAppWidgetProviderInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AppWidgetProviderInfo appWidgetProviderInfo;
      parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
        appWidgetProviderInfo = IPinItemRequest.Stub.getDefaultImpl().getAppWidgetProviderInfo();
        return appWidgetProviderInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        appWidgetProviderInfo = (AppWidgetProviderInfo)AppWidgetProviderInfo.CREATOR.createFromParcel(parcel2);
      } else {
        appWidgetProviderInfo = null;
      } 
      return appWidgetProviderInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getExtras() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Bundle bundle;
      parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
        bundle = IPinItemRequest.Stub.getDefaultImpl().getExtras();
        return bundle;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        bundle = null;
      } 
      return bundle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPinItemRequest";
  }
  
  public ShortcutInfo getShortcutInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ShortcutInfo shortcutInfo;
      parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
        shortcutInfo = IPinItemRequest.Stub.getDefaultImpl().getShortcutInfo();
        return shortcutInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        shortcutInfo = (ShortcutInfo)ShortcutInfo.CREATOR.createFromParcel(parcel2);
      } else {
        shortcutInfo = null;
      } 
      return shortcutInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isValid() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(1, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
        bool = IPinItemRequest.Stub.getDefaultImpl().isValid();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPinItemRequest$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */