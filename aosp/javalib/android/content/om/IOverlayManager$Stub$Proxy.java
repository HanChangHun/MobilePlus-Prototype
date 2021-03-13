package android.content.om;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

class Proxy implements IOverlayManager {
  public static IOverlayManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public Map getAllOverlays(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
        return IOverlayManager.Stub.getDefaultImpl().getAllOverlays(paramInt); 
      parcel2.readException();
      return parcel2.readHashMap(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getDefaultOverlayPackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
        return IOverlayManager.Stub.getDefaultImpl().getDefaultOverlayPackages(); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.om.IOverlayManager";
  }
  
  public OverlayInfo getOverlayInfo(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
        return IOverlayManager.Stub.getDefaultImpl().getOverlayInfo(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        OverlayInfo overlayInfo = (OverlayInfo)OverlayInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (OverlayInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List getOverlayInfosForTarget(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null)
        return IOverlayManager.Stub.getDefaultImpl().getOverlayInfosForTarget(paramString, paramInt); 
      parcel2.readException();
      return parcel2.readArrayList(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void invalidateCachesForOverlay(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        IOverlayManager.Stub.getDefaultImpl().invalidateCachesForOverlay(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setEnabled(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IOverlayManager.Stub.getDefaultImpl().setEnabled(paramString, paramBoolean, paramInt);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setEnabledExclusive(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IOverlayManager.Stub.getDefaultImpl().setEnabledExclusive(paramString, paramBoolean, paramInt);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setEnabledExclusiveInCategory(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        bool = IOverlayManager.Stub.getDefaultImpl().setEnabledExclusiveInCategory(paramString, paramInt);
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
  
  public boolean setHighestPriority(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        bool = IOverlayManager.Stub.getDefaultImpl().setHighestPriority(paramString, paramInt);
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
  
  public boolean setLowestPriority(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(9, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        bool = IOverlayManager.Stub.getDefaultImpl().setLowestPriority(paramString, paramInt);
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
  
  public boolean setPriority(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.om.IOverlayManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && IOverlayManager.Stub.getDefaultImpl() != null) {
        bool = IOverlayManager.Stub.getDefaultImpl().setPriority(paramString1, paramString2, paramInt);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/IOverlayManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */