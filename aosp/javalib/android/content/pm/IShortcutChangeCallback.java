package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.ArrayList;
import java.util.List;

public interface IShortcutChangeCallback extends IInterface {
  void onShortcutsAddedOrUpdated(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) throws RemoteException;
  
  void onShortcutsRemoved(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) throws RemoteException;
  
  public static class Default implements IShortcutChangeCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onShortcutsAddedOrUpdated(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) throws RemoteException {}
    
    public void onShortcutsRemoved(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IShortcutChangeCallback {
    private static final String DESCRIPTOR = "android.content.pm.IShortcutChangeCallback";
    
    static final int TRANSACTION_onShortcutsAddedOrUpdated = 1;
    
    static final int TRANSACTION_onShortcutsRemoved = 2;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IShortcutChangeCallback");
    }
    
    public static IShortcutChangeCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IShortcutChangeCallback");
      return (iInterface != null && iInterface instanceof IShortcutChangeCallback) ? (IShortcutChangeCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IShortcutChangeCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onShortcutsRemoved") : "onShortcutsAddedOrUpdated";
    }
    
    public static boolean setDefaultImpl(IShortcutChangeCallback param1IShortcutChangeCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IShortcutChangeCallback != null) {
          Proxy.sDefaultImpl = param1IShortcutChangeCallback;
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
          param1Parcel2.writeString("android.content.pm.IShortcutChangeCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IShortcutChangeCallback");
        String str1 = param1Parcel1.readString();
        ArrayList<ShortcutInfo> arrayList1 = param1Parcel1.createTypedArrayList(ShortcutInfo.CREATOR);
        if (param1Parcel1.readInt() != 0) {
          UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onShortcutsRemoved(str1, arrayList1, (UserHandle)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IShortcutChangeCallback");
      String str = param1Parcel1.readString();
      ArrayList<ShortcutInfo> arrayList = param1Parcel1.createTypedArrayList(ShortcutInfo.CREATOR);
      if (param1Parcel1.readInt() != 0) {
        UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onShortcutsAddedOrUpdated(str, arrayList, (UserHandle)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IShortcutChangeCallback {
      public static IShortcutChangeCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.IShortcutChangeCallback";
      }
      
      public void onShortcutsAddedOrUpdated(String param2String, List<ShortcutInfo> param2List, UserHandle param2UserHandle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IShortcutChangeCallback");
          parcel.writeString(param2String);
          parcel.writeTypedList(param2List);
          if (param2UserHandle != null) {
            parcel.writeInt(1);
            param2UserHandle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IShortcutChangeCallback.Stub.getDefaultImpl() != null) {
            IShortcutChangeCallback.Stub.getDefaultImpl().onShortcutsAddedOrUpdated(param2String, param2List, param2UserHandle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onShortcutsRemoved(String param2String, List<ShortcutInfo> param2List, UserHandle param2UserHandle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.content.pm.IShortcutChangeCallback");
          parcel.writeString(param2String);
          parcel.writeTypedList(param2List);
          if (param2UserHandle != null) {
            parcel.writeInt(1);
            param2UserHandle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IShortcutChangeCallback.Stub.getDefaultImpl() != null) {
            IShortcutChangeCallback.Stub.getDefaultImpl().onShortcutsRemoved(param2String, param2List, param2UserHandle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IShortcutChangeCallback {
    public static IShortcutChangeCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IShortcutChangeCallback";
    }
    
    public void onShortcutsAddedOrUpdated(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IShortcutChangeCallback");
        parcel.writeString(param1String);
        parcel.writeTypedList(param1List);
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IShortcutChangeCallback.Stub.getDefaultImpl() != null) {
          IShortcutChangeCallback.Stub.getDefaultImpl().onShortcutsAddedOrUpdated(param1String, param1List, param1UserHandle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onShortcutsRemoved(String param1String, List<ShortcutInfo> param1List, UserHandle param1UserHandle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IShortcutChangeCallback");
        parcel.writeString(param1String);
        parcel.writeTypedList(param1List);
        if (param1UserHandle != null) {
          parcel.writeInt(1);
          param1UserHandle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IShortcutChangeCallback.Stub.getDefaultImpl() != null) {
          IShortcutChangeCallback.Stub.getDefaultImpl().onShortcutsRemoved(param1String, param1List, param1UserHandle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutChangeCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */