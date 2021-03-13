package android.content.pm;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;

class Proxy implements IShortcutService {
  public static IShortcutService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public boolean addDynamicShortcuts(String paramString, ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        bool = IShortcutService.Stub.getDefaultImpl().addDynamicShortcuts(paramString, paramParceledListSlice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void applyRestore(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeByteArray(paramArrayOfbyte);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().applyRestore(paramArrayOfbyte, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public Intent createShortcutResultIntent(String paramString, ShortcutInfo paramShortcutInfo, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      if (paramShortcutInfo != null) {
        parcel1.writeInt(1);
        paramShortcutInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
        return IShortcutService.Stub.getDefaultImpl().createShortcutResultIntent(paramString, paramShortcutInfo, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (Intent)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disableShortcuts(String paramString, List paramList, CharSequence paramCharSequence, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeList(paramList);
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().disableShortcuts(paramString, paramList, paramCharSequence, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enableShortcuts(String paramString, List paramList, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeList(paramList);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().enableShortcuts(paramString, paramList, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getBackupPayload(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
        return IShortcutService.Stub.getDefaultImpl().getBackupPayload(paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getIconMaxDimensions(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        paramInt = IShortcutService.Stub.getDefaultImpl().getIconMaxDimensions(paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IShortcutService";
  }
  
  public int getMaxShortcutCountPerActivity(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        paramInt = IShortcutService.Stub.getDefaultImpl().getMaxShortcutCountPerActivity(paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getRateLimitResetTime(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
        return IShortcutService.Stub.getDefaultImpl().getRateLimitResetTime(paramString, paramInt); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getRemainingCallCount(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        paramInt = IShortcutService.Stub.getDefaultImpl().getRemainingCallCount(paramString, paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getShareTargets(String paramString, IntentFilter paramIntentFilter, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      if (paramIntentFilter != null) {
        parcel1.writeInt(1);
        paramIntentFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
        return IShortcutService.Stub.getDefaultImpl().getShareTargets(paramString, paramIntentFilter, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getShortcuts(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null)
        return IShortcutService.Stub.getDefaultImpl().getShortcuts(paramString, paramInt1, paramInt2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasShareTargets(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(21, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        bool = IShortcutService.Stub.getDefaultImpl().hasShareTargets(paramString1, paramString2, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isRequestPinItemSupported(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(19, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        bool = IShortcutService.Stub.getDefaultImpl().isRequestPinItemSupported(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onApplicationActive(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().onApplicationActive(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void pushDynamicShortcut(String paramString, ShortcutInfo paramShortcutInfo, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      if (paramShortcutInfo != null) {
        parcel1.writeInt(1);
        paramShortcutInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().pushDynamicShortcut(paramString, paramShortcutInfo, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeAllDynamicShortcuts(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().removeAllDynamicShortcuts(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeDynamicShortcuts(String paramString, List paramList, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeList(paramList);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().removeDynamicShortcuts(paramString, paramList, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeLongLivedShortcuts(String paramString, List paramList, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      parcel1.writeList(paramList);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().removeLongLivedShortcuts(paramString, paramList, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportShortcutUsed(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().reportShortcutUsed(paramString1, paramString2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean requestPinShortcut(String paramString, ShortcutInfo paramShortcutInfo, IntentSender paramIntentSender, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramShortcutInfo != null) {
        parcel1.writeInt(1);
        paramShortcutInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        bool = IShortcutService.Stub.getDefaultImpl().requestPinShortcut(paramString, paramShortcutInfo, paramIntentSender, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resetThrottling() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        IShortcutService.Stub.getDefaultImpl().resetThrottling();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setDynamicShortcuts(String paramString, ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        bool = IShortcutService.Stub.getDefaultImpl().setDynamicShortcuts(paramString, paramParceledListSlice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean updateShortcuts(String paramString, ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IShortcutService");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IShortcutService.Stub.getDefaultImpl() != null) {
        bool = IShortcutService.Stub.getDefaultImpl().updateShortcuts(paramString, paramParceledListSlice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IShortcutService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */