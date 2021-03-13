package android.content.pm;

import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IPackageInstaller {
  private static final String DESCRIPTOR = "android.content.pm.IPackageInstaller";
  
  static final int TRANSACTION_abandonSession = 4;
  
  static final int TRANSACTION_bypassNextStagedInstallerCheck = 16;
  
  static final int TRANSACTION_createSession = 1;
  
  static final int TRANSACTION_getAllSessions = 7;
  
  static final int TRANSACTION_getMySessions = 8;
  
  static final int TRANSACTION_getSessionInfo = 6;
  
  static final int TRANSACTION_getStagedSessions = 9;
  
  static final int TRANSACTION_installExistingPackage = 14;
  
  static final int TRANSACTION_openSession = 5;
  
  static final int TRANSACTION_registerCallback = 10;
  
  static final int TRANSACTION_setPermissionsResult = 15;
  
  static final int TRANSACTION_uninstall = 12;
  
  static final int TRANSACTION_uninstallExistingPackage = 13;
  
  static final int TRANSACTION_unregisterCallback = 11;
  
  static final int TRANSACTION_updateSessionAppIcon = 2;
  
  static final int TRANSACTION_updateSessionAppLabel = 3;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageInstaller");
  }
  
  public static IPackageInstaller asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstaller");
    return (iInterface != null && iInterface instanceof IPackageInstaller) ? (IPackageInstaller)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageInstaller getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 16:
        return "bypassNextStagedInstallerCheck";
      case 15:
        return "setPermissionsResult";
      case 14:
        return "installExistingPackage";
      case 13:
        return "uninstallExistingPackage";
      case 12:
        return "uninstall";
      case 11:
        return "unregisterCallback";
      case 10:
        return "registerCallback";
      case 9:
        return "getStagedSessions";
      case 8:
        return "getMySessions";
      case 7:
        return "getAllSessions";
      case 6:
        return "getSessionInfo";
      case 5:
        return "openSession";
      case 4:
        return "abandonSession";
      case 3:
        return "updateSessionAppLabel";
      case 2:
        return "updateSessionAppIcon";
      case 1:
        break;
    } 
    return "createSession";
  }
  
  public static boolean setDefaultImpl(IPackageInstaller paramIPackageInstaller) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageInstaller != null) {
        Proxy.sDefaultImpl = paramIPackageInstaller;
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
      ParceledListSlice parceledListSlice;
      PackageInstaller.SessionInfo sessionInfo;
      IPackageInstallerSession iPackageInstallerSession;
      String str1;
      IntentSender intentSender;
      String str2;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 16:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          bypassNextStagedInstallerCheck(bool2);
          paramParcel2.writeNoException();
          return true;
        case 15:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          paramInt1 = paramParcel1.readInt();
          bool2 = bool1;
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          setPermissionsResult(paramInt1, bool2);
          paramParcel2.writeNoException();
          return true;
        case 14:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          str1 = paramParcel1.readString();
          paramInt2 = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(paramParcel1);
          } else {
            intentSender = null;
          } 
          installExistingPackage(str1, paramInt2, paramInt1, intentSender, paramParcel1.readInt(), paramParcel1.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 13:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          if (paramParcel1.readInt() != 0) {
            VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(paramParcel1);
          } else {
            intentSender = null;
          } 
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            IntentSender intentSender1 = (IntentSender)IntentSender.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          uninstallExistingPackage((VersionedPackage)intentSender, str2, (IntentSender)str1, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 12:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          if (paramParcel1.readInt() != 0) {
            VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(paramParcel1);
          } else {
            intentSender = null;
          } 
          str2 = paramParcel1.readString();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            IntentSender intentSender1 = (IntentSender)IntentSender.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          uninstall((VersionedPackage)intentSender, str2, paramInt1, (IntentSender)str1, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 11:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          unregisterCallback(IPackageInstallerCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          registerCallback(IPackageInstallerCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 9:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstaller");
          parceledListSlice = getStagedSessions();
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 8:
          parceledListSlice.enforceInterface("android.content.pm.IPackageInstaller");
          parceledListSlice = getMySessions(parceledListSlice.readString(), parceledListSlice.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 7:
          parceledListSlice.enforceInterface("android.content.pm.IPackageInstaller");
          parceledListSlice = getAllSessions(parceledListSlice.readInt());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 6:
          parceledListSlice.enforceInterface("android.content.pm.IPackageInstaller");
          sessionInfo = getSessionInfo(parceledListSlice.readInt());
          paramParcel2.writeNoException();
          if (sessionInfo != null) {
            paramParcel2.writeInt(1);
            sessionInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 5:
          sessionInfo.enforceInterface("android.content.pm.IPackageInstaller");
          iPackageInstallerSession = openSession(sessionInfo.readInt());
          paramParcel2.writeNoException();
          if (iPackageInstallerSession != null) {
            IBinder iBinder = iPackageInstallerSession.asBinder();
          } else {
            iPackageInstallerSession = null;
          } 
          paramParcel2.writeStrongBinder((IBinder)iPackageInstallerSession);
          return true;
        case 4:
          iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
          abandonSession(iPackageInstallerSession.readInt());
          paramParcel2.writeNoException();
          return true;
        case 3:
          iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
          updateSessionAppLabel(iPackageInstallerSession.readInt(), iPackageInstallerSession.readString());
          paramParcel2.writeNoException();
          return true;
        case 2:
          iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
          paramInt1 = iPackageInstallerSession.readInt();
          if (iPackageInstallerSession.readInt() != 0) {
            Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel((Parcel)iPackageInstallerSession);
          } else {
            iPackageInstallerSession = null;
          } 
          updateSessionAppIcon(paramInt1, (Bitmap)iPackageInstallerSession);
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
      if (iPackageInstallerSession.readInt() != 0) {
        PackageInstaller.SessionParams sessionParams = (PackageInstaller.SessionParams)PackageInstaller.SessionParams.CREATOR.createFromParcel((Parcel)iPackageInstallerSession);
      } else {
        intentSender = null;
      } 
      paramInt1 = createSession((PackageInstaller.SessionParams)intentSender, iPackageInstallerSession.readString(), iPackageInstallerSession.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    } 
    paramParcel2.writeString("android.content.pm.IPackageInstaller");
    return true;
  }
  
  private static class Proxy implements IPackageInstaller {
    public static IPackageInstaller sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void abandonSession(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().abandonSession(param2Int);
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
    
    public void bypassNextStagedInstallerCheck(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().bypassNextStagedInstallerCheck(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createSession(PackageInstaller.SessionParams param2SessionParams, String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param2SessionParams != null) {
          parcel1.writeInt(1);
          param2SessionParams.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          param2Int = IPackageInstaller.Stub.getDefaultImpl().createSession(param2SessionParams, param2String, param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAllSessions(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageInstaller.Stub.getDefaultImpl().getAllSessions(param2Int);
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
    
    public ParceledListSlice getMySessions(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null)
          return IPackageInstaller.Stub.getDefaultImpl().getMySessions(param2String, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PackageInstaller.SessionInfo getSessionInfo(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        PackageInstaller.SessionInfo sessionInfo;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          sessionInfo = IPackageInstaller.Stub.getDefaultImpl().getSessionInfo(param2Int);
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
    
    public void installExistingPackage(String param2String, int param2Int1, int param2Int2, IntentSender param2IntentSender, int param2Int3, List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        try {
          parcel1.writeString(param2String);
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeInt(param2Int2);
              if (param2IntentSender != null) {
                parcel1.writeInt(1);
                param2IntentSender.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param2Int3);
                try {
                  parcel1.writeStringList(param2List);
                  if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
                    IPackageInstaller.Stub.getDefaultImpl().installExistingPackage(param2String, param2Int1, param2Int2, param2IntentSender, param2Int3, param2List);
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
      throw param2String;
    }
    
    public IPackageInstallerSession openSession(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null)
          return IPackageInstaller.Stub.getDefaultImpl().openSession(param2Int); 
        parcel2.readException();
        return IPackageInstallerSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerCallback(IPackageInstallerCallback param2IPackageInstallerCallback, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param2IPackageInstallerCallback != null) {
          iBinder = param2IPackageInstallerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().registerCallback(param2IPackageInstallerCallback, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPermissionsResult(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().setPermissionsResult(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void uninstall(VersionedPackage param2VersionedPackage, String param2String, int param2Int1, IntentSender param2IntentSender, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param2VersionedPackage != null) {
          parcel1.writeInt(1);
          param2VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int1);
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().uninstall(param2VersionedPackage, param2String, param2Int1, param2IntentSender, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void uninstallExistingPackage(VersionedPackage param2VersionedPackage, String param2String, IntentSender param2IntentSender, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param2VersionedPackage != null) {
          parcel1.writeInt(1);
          param2VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().uninstallExistingPackage(param2VersionedPackage, param2String, param2IntentSender, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterCallback(IPackageInstallerCallback param2IPackageInstallerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param2IPackageInstallerCallback != null) {
          iBinder = param2IPackageInstallerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().unregisterCallback(param2IPackageInstallerCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateSessionAppIcon(int param2Int, Bitmap param2Bitmap) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        if (param2Bitmap != null) {
          parcel1.writeInt(1);
          param2Bitmap.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().updateSessionAppIcon(param2Int, param2Bitmap);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateSessionAppLabel(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().updateSessionAppLabel(param2Int, param2String);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstaller$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */