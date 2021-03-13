package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.ArrayList;
import java.util.List;

public abstract class Stub extends Binder implements IShortcutChangeCallback {
  private static final String DESCRIPTOR = "android.content.pm.IShortcutChangeCallback";
  
  static final int TRANSACTION_onShortcutsAddedOrUpdated = 1;
  
  static final int TRANSACTION_onShortcutsRemoved = 2;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IShortcutChangeCallback");
  }
  
  public static IShortcutChangeCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IShortcutChangeCallback");
    return (iInterface != null && iInterface instanceof IShortcutChangeCallback) ? (IShortcutChangeCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IShortcutChangeCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onShortcutsRemoved") : "onShortcutsAddedOrUpdated";
  }
  
  public static boolean setDefaultImpl(IShortcutChangeCallback paramIShortcutChangeCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIShortcutChangeCallback != null) {
        Proxy.sDefaultImpl = paramIShortcutChangeCallback;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.content.pm.IShortcutChangeCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IShortcutChangeCallback");
      String str1 = paramParcel1.readString();
      ArrayList<ShortcutInfo> arrayList1 = paramParcel1.createTypedArrayList(ShortcutInfo.CREATOR);
      if (paramParcel1.readInt() != 0) {
        UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onShortcutsRemoved(str1, arrayList1, (UserHandle)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IShortcutChangeCallback");
    String str = paramParcel1.readString();
    ArrayList<ShortcutInfo> arrayList = paramParcel1.createTypedArrayList(ShortcutInfo.CREATOR);
    if (paramParcel1.readInt() != 0) {
      UserHandle userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onShortcutsAddedOrUpdated(str, arrayList, (UserHandle)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutChangeCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */