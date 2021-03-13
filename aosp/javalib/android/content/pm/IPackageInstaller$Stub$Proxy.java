package android.content.pm;

import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IPackageInstaller {
  public static IPackageInstaller sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void abandonSession(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().abandonSession(paramInt);
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
  
  public void bypassNextStagedInstallerCheck(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().bypassNextStagedInstallerCheck(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int createSession(PackageInstaller.SessionParams paramSessionParams, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (paramSessionParams != null) {
        parcel1.writeInt(1);
        paramSessionParams.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        paramInt = IPackageInstaller.Stub.getDefaultImpl().createSession(paramSessionParams, paramString, paramInt);
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
  
  public ParceledListSlice getAllSessions(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageInstaller.Stub.getDefaultImpl().getAllSessions(paramInt);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IPackageInstaller";
  }
  
  public ParceledListSlice getMySessions(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null)
        return IPackageInstaller.Stub.getDefaultImpl().getMySessions(paramString, paramInt); 
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
  
  public PackageInstaller.SessionInfo getSessionInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      PackageInstaller.SessionInfo sessionInfo;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        sessionInfo = IPackageInstaller.Stub.getDefaultImpl().getSessionInfo(paramInt);
        return sessionInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        sessionInfo = (PackageInstaller.SessionInfo)PackageInstaller.SessionInfo.CREATOR.createFromParcel(parcel2);
      } else {
        sessionInfo = null;
      } 
      return sessionInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getStagedSessions() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        parceledListSlice = IPackageInstaller.Stub.getDefaultImpl().getStagedSessions();
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void installExistingPackage(String paramString, int paramInt1, int paramInt2, IntentSender paramIntentSender, int paramInt3, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      try {
        parcel1.writeString(paramString);
        try {
          parcel1.writeInt(paramInt1);
          try {
            parcel1.writeInt(paramInt2);
            if (paramIntentSender != null) {
              parcel1.writeInt(1);
              paramIntentSender.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(paramInt3);
              try {
                parcel1.writeStringList(paramList);
                if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
                  IPackageInstaller.Stub.getDefaultImpl().installExistingPackage(paramString, paramInt1, paramInt2, paramIntentSender, paramInt3, paramList);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public IPackageInstallerSession openSession(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null)
        return IPackageInstaller.Stub.getDefaultImpl().openSession(paramInt); 
      parcel2.readException();
      return IPackageInstallerSession.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerCallback(IPackageInstallerCallback paramIPackageInstallerCallback, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (paramIPackageInstallerCallback != null) {
        iBinder = paramIPackageInstallerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().registerCallback(paramIPackageInstallerCallback, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPermissionsResult(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().setPermissionsResult(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void uninstall(VersionedPackage paramVersionedPackage, String paramString, int paramInt1, IntentSender paramIntentSender, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (paramVersionedPackage != null) {
        parcel1.writeInt(1);
        paramVersionedPackage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().uninstall(paramVersionedPackage, paramString, paramInt1, paramIntentSender, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void uninstallExistingPackage(VersionedPackage paramVersionedPackage, String paramString, IntentSender paramIntentSender, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (paramVersionedPackage != null) {
        parcel1.writeInt(1);
        paramVersionedPackage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().uninstallExistingPackage(paramVersionedPackage, paramString, paramIntentSender, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterCallback(IPackageInstallerCallback paramIPackageInstallerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      if (paramIPackageInstallerCallback != null) {
        iBinder = paramIPackageInstallerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().unregisterCallback(paramIPackageInstallerCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateSessionAppIcon(int paramInt, Bitmap paramBitmap) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      if (paramBitmap != null) {
        parcel1.writeInt(1);
        paramBitmap.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().updateSessionAppIcon(paramInt, paramBitmap);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateSessionAppLabel(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
        IPackageInstaller.Stub.getDefaultImpl().updateSessionAppLabel(paramInt, paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstaller$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */