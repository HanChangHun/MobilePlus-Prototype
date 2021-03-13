package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWallpaperManagerCallback extends IInterface {
  void onWallpaperChanged() throws RemoteException;
  
  void onWallpaperColorsChanged(WallpaperColors paramWallpaperColors, int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IWallpaperManagerCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onWallpaperChanged() throws RemoteException {}
    
    public void onWallpaperColorsChanged(WallpaperColors param1WallpaperColors, int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IWallpaperManagerCallback {
    private static final String DESCRIPTOR = "android.app.IWallpaperManagerCallback";
    
    static final int TRANSACTION_onWallpaperChanged = 1;
    
    static final int TRANSACTION_onWallpaperColorsChanged = 2;
    
    public Stub() {
      attachInterface(this, "android.app.IWallpaperManagerCallback");
    }
    
    public static IWallpaperManagerCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IWallpaperManagerCallback");
      return (iInterface != null && iInterface instanceof IWallpaperManagerCallback) ? (IWallpaperManagerCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IWallpaperManagerCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onWallpaperColorsChanged") : "onWallpaperChanged";
    }
    
    public static boolean setDefaultImpl(IWallpaperManagerCallback param1IWallpaperManagerCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IWallpaperManagerCallback != null) {
          Proxy.sDefaultImpl = param1IWallpaperManagerCallback;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.IWallpaperManagerCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IWallpaperManagerCallback");
        if (param1Parcel1.readInt() != 0) {
          WallpaperColors wallpaperColors = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel2 = null;
        } 
        onWallpaperColorsChanged((WallpaperColors)param1Parcel2, param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IWallpaperManagerCallback");
      onWallpaperChanged();
      return true;
    }
    
    private static class Proxy implements IWallpaperManagerCallback {
      public static IWallpaperManagerCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IWallpaperManagerCallback";
      }
      
      public void onWallpaperChanged() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
          if (!this.mRemote.transact(1, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
            IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperChanged();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onWallpaperColorsChanged(WallpaperColors param2WallpaperColors, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
          if (param2WallpaperColors != null) {
            parcel.writeInt(1);
            param2WallpaperColors.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(2, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
            IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperColorsChanged(param2WallpaperColors, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IWallpaperManagerCallback {
    public static IWallpaperManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IWallpaperManagerCallback";
    }
    
    public void onWallpaperChanged() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
          IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperChanged();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onWallpaperColorsChanged(WallpaperColors param1WallpaperColors, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
        if (param1WallpaperColors != null) {
          parcel.writeInt(1);
          param1WallpaperColors.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
          IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperColorsChanged(param1WallpaperColors, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManagerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */