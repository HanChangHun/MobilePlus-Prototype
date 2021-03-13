package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPinItemRequest extends IInterface {
  boolean accept(Bundle paramBundle) throws RemoteException;
  
  AppWidgetProviderInfo getAppWidgetProviderInfo() throws RemoteException;
  
  Bundle getExtras() throws RemoteException;
  
  ShortcutInfo getShortcutInfo() throws RemoteException;
  
  boolean isValid() throws RemoteException;
  
  public static class Default implements IPinItemRequest {
    public boolean accept(Bundle param1Bundle) throws RemoteException {
      return false;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public AppWidgetProviderInfo getAppWidgetProviderInfo() throws RemoteException {
      return null;
    }
    
    public Bundle getExtras() throws RemoteException {
      return null;
    }
    
    public ShortcutInfo getShortcutInfo() throws RemoteException {
      return null;
    }
    
    public boolean isValid() throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IPinItemRequest {
    private static final String DESCRIPTOR = "android.content.pm.IPinItemRequest";
    
    static final int TRANSACTION_accept = 2;
    
    static final int TRANSACTION_getAppWidgetProviderInfo = 4;
    
    static final int TRANSACTION_getExtras = 5;
    
    static final int TRANSACTION_getShortcutInfo = 3;
    
    static final int TRANSACTION_isValid = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IPinItemRequest");
    }
    
    public static IPinItemRequest asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPinItemRequest");
      return (iInterface != null && iInterface instanceof IPinItemRequest) ? (IPinItemRequest)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPinItemRequest getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "getExtras") : "getAppWidgetProviderInfo") : "getShortcutInfo") : "accept") : "isValid";
    }
    
    public static boolean setDefaultImpl(IPinItemRequest param1IPinItemRequest) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPinItemRequest != null) {
          Proxy.sDefaultImpl = param1IPinItemRequest;
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
      ShortcutInfo shortcutInfo;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          AppWidgetProviderInfo appWidgetProviderInfo;
          if (param1Int1 != 3) {
            Bundle bundle;
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.content.pm.IPinItemRequest");
                return true;
              } 
              param1Parcel1.enforceInterface("android.content.pm.IPinItemRequest");
              bundle = getExtras();
              param1Parcel2.writeNoException();
              if (bundle != null) {
                param1Parcel2.writeInt(1);
                bundle.writeToParcel(param1Parcel2, 1);
              } else {
                param1Parcel2.writeInt(0);
              } 
              return true;
            } 
            bundle.enforceInterface("android.content.pm.IPinItemRequest");
            appWidgetProviderInfo = getAppWidgetProviderInfo();
            param1Parcel2.writeNoException();
            if (appWidgetProviderInfo != null) {
              param1Parcel2.writeInt(1);
              appWidgetProviderInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          } 
          appWidgetProviderInfo.enforceInterface("android.content.pm.IPinItemRequest");
          shortcutInfo = getShortcutInfo();
          param1Parcel2.writeNoException();
          if (shortcutInfo != null) {
            param1Parcel2.writeInt(1);
            shortcutInfo.writeToParcel(param1Parcel2, 1);
          } else {
            param1Parcel2.writeInt(0);
          } 
          return true;
        } 
        shortcutInfo.enforceInterface("android.content.pm.IPinItemRequest");
        if (shortcutInfo.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)shortcutInfo);
        } else {
          shortcutInfo = null;
        } 
        boolean bool1 = accept((Bundle)shortcutInfo);
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      shortcutInfo.enforceInterface("android.content.pm.IPinItemRequest");
      boolean bool = isValid();
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(bool);
      return true;
    }
    
    private static class Proxy implements IPinItemRequest {
      public static IPinItemRequest sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public boolean accept(Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
          boolean bool = true;
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
            bool = IPinItemRequest.Stub.getDefaultImpl().accept(param2Bundle);
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
  }
  
  private static class Proxy implements IPinItemRequest {
    public static IPinItemRequest sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public boolean accept(Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPinItemRequest");
        boolean bool = true;
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPinItemRequest.Stub.getDefaultImpl() != null) {
          bool = IPinItemRequest.Stub.getDefaultImpl().accept(param1Bundle);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPinItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */