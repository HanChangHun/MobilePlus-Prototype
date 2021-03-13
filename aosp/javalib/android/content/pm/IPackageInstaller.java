package android.content.pm;

import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IPackageInstaller extends IInterface {
  void abandonSession(int paramInt) throws RemoteException;
  
  void bypassNextStagedInstallerCheck(boolean paramBoolean) throws RemoteException;
  
  int createSession(PackageInstaller.SessionParams paramSessionParams, String paramString, int paramInt) throws RemoteException;
  
  ParceledListSlice getAllSessions(int paramInt) throws RemoteException;
  
  ParceledListSlice getMySessions(String paramString, int paramInt) throws RemoteException;
  
  PackageInstaller.SessionInfo getSessionInfo(int paramInt) throws RemoteException;
  
  ParceledListSlice getStagedSessions() throws RemoteException;
  
  void installExistingPackage(String paramString, int paramInt1, int paramInt2, IntentSender paramIntentSender, int paramInt3, List<String> paramList) throws RemoteException;
  
  IPackageInstallerSession openSession(int paramInt) throws RemoteException;
  
  void registerCallback(IPackageInstallerCallback paramIPackageInstallerCallback, int paramInt) throws RemoteException;
  
  void setPermissionsResult(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void uninstall(VersionedPackage paramVersionedPackage, String paramString, int paramInt1, IntentSender paramIntentSender, int paramInt2) throws RemoteException;
  
  void uninstallExistingPackage(VersionedPackage paramVersionedPackage, String paramString, IntentSender paramIntentSender, int paramInt) throws RemoteException;
  
  void unregisterCallback(IPackageInstallerCallback paramIPackageInstallerCallback) throws RemoteException;
  
  void updateSessionAppIcon(int paramInt, Bitmap paramBitmap) throws RemoteException;
  
  void updateSessionAppLabel(int paramInt, String paramString) throws RemoteException;
  
  public static class Default implements IPackageInstaller {
    public void abandonSession(int param1Int) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void bypassNextStagedInstallerCheck(boolean param1Boolean) throws RemoteException {}
    
    public int createSession(PackageInstaller.SessionParams param1SessionParams, String param1String, int param1Int) throws RemoteException {
      return 0;
    }
    
    public ParceledListSlice getAllSessions(int param1Int) throws RemoteException {
      return null;
    }
    
    public ParceledListSlice getMySessions(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public PackageInstaller.SessionInfo getSessionInfo(int param1Int) throws RemoteException {
      return null;
    }
    
    public ParceledListSlice getStagedSessions() throws RemoteException {
      return null;
    }
    
    public void installExistingPackage(String param1String, int param1Int1, int param1Int2, IntentSender param1IntentSender, int param1Int3, List<String> param1List) throws RemoteException {}
    
    public IPackageInstallerSession openSession(int param1Int) throws RemoteException {
      return null;
    }
    
    public void registerCallback(IPackageInstallerCallback param1IPackageInstallerCallback, int param1Int) throws RemoteException {}
    
    public void setPermissionsResult(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void uninstall(VersionedPackage param1VersionedPackage, String param1String, int param1Int1, IntentSender param1IntentSender, int param1Int2) throws RemoteException {}
    
    public void uninstallExistingPackage(VersionedPackage param1VersionedPackage, String param1String, IntentSender param1IntentSender, int param1Int) throws RemoteException {}
    
    public void unregisterCallback(IPackageInstallerCallback param1IPackageInstallerCallback) throws RemoteException {}
    
    public void updateSessionAppIcon(int param1Int, Bitmap param1Bitmap) throws RemoteException {}
    
    public void updateSessionAppLabel(int param1Int, String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPackageInstaller {
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
    
    public static IPackageInstaller asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IPackageInstaller");
      return (iInterface != null && iInterface instanceof IPackageInstaller) ? (IPackageInstaller)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPackageInstaller getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IPackageInstaller param1IPackageInstaller) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPackageInstaller != null) {
          Proxy.sDefaultImpl = param1IPackageInstaller;
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
      if (param1Int1 != 1598968902) {
        ParceledListSlice parceledListSlice;
        PackageInstaller.SessionInfo sessionInfo;
        IPackageInstallerSession iPackageInstallerSession;
        String str1;
        IntentSender intentSender;
        String str2;
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 16:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            bypassNextStagedInstallerCheck(bool2);
            param1Parcel2.writeNoException();
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            param1Int1 = param1Parcel1.readInt();
            bool2 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            setPermissionsResult(param1Int1, bool2);
            param1Parcel2.writeNoException();
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            str1 = param1Parcel1.readString();
            param1Int2 = param1Parcel1.readInt();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(param1Parcel1);
            } else {
              intentSender = null;
            } 
            installExistingPackage(str1, param1Int2, param1Int1, intentSender, param1Parcel1.readInt(), param1Parcel1.createStringArrayList());
            param1Parcel2.writeNoException();
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            if (param1Parcel1.readInt() != 0) {
              VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(param1Parcel1);
            } else {
              intentSender = null;
            } 
            str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              IntentSender intentSender1 = (IntentSender)IntentSender.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str1 = null;
            } 
            uninstallExistingPackage((VersionedPackage)intentSender, str2, (IntentSender)str1, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            if (param1Parcel1.readInt() != 0) {
              VersionedPackage versionedPackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(param1Parcel1);
            } else {
              intentSender = null;
            } 
            str2 = param1Parcel1.readString();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              IntentSender intentSender1 = (IntentSender)IntentSender.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str1 = null;
            } 
            uninstall((VersionedPackage)intentSender, str2, param1Int1, (IntentSender)str1, param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            unregisterCallback(IPackageInstallerCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            registerCallback(IPackageInstallerCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.content.pm.IPackageInstaller");
            parceledListSlice = getStagedSessions();
            param1Parcel2.writeNoException();
            if (parceledListSlice != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 8:
            parceledListSlice.enforceInterface("android.content.pm.IPackageInstaller");
            parceledListSlice = getMySessions(parceledListSlice.readString(), parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            if (parceledListSlice != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 7:
            parceledListSlice.enforceInterface("android.content.pm.IPackageInstaller");
            parceledListSlice = getAllSessions(parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            if (parceledListSlice != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 6:
            parceledListSlice.enforceInterface("android.content.pm.IPackageInstaller");
            sessionInfo = getSessionInfo(parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            if (sessionInfo != null) {
              param1Parcel2.writeInt(1);
              sessionInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            sessionInfo.enforceInterface("android.content.pm.IPackageInstaller");
            iPackageInstallerSession = openSession(sessionInfo.readInt());
            param1Parcel2.writeNoException();
            if (iPackageInstallerSession != null) {
              IBinder iBinder = iPackageInstallerSession.asBinder();
            } else {
              iPackageInstallerSession = null;
            } 
            param1Parcel2.writeStrongBinder((IBinder)iPackageInstallerSession);
            return true;
          case 4:
            iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
            abandonSession(iPackageInstallerSession.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
            updateSessionAppLabel(iPackageInstallerSession.readInt(), iPackageInstallerSession.readString());
            param1Parcel2.writeNoException();
            return true;
          case 2:
            iPackageInstallerSession.enforceInterface("android.content.pm.IPackageInstaller");
            param1Int1 = iPackageInstallerSession.readInt();
            if (iPackageInstallerSession.readInt() != 0) {
              Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel((Parcel)iPackageInstallerSession);
            } else {
              iPackageInstallerSession = null;
            } 
            updateSessionAppIcon(param1Int1, (Bitmap)iPackageInstallerSession);
            param1Parcel2.writeNoException();
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
        param1Int1 = createSession((PackageInstaller.SessionParams)intentSender, iPackageInstallerSession.readString(), iPackageInstallerSession.readInt());
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(param1Int1);
        return true;
      } 
      param1Parcel2.writeString("android.content.pm.IPackageInstaller");
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
  
  private static class Proxy implements IPackageInstaller {
    public static IPackageInstaller sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void abandonSession(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().abandonSession(param1Int);
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
    
    public void bypassNextStagedInstallerCheck(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().bypassNextStagedInstallerCheck(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createSession(PackageInstaller.SessionParams param1SessionParams, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param1SessionParams != null) {
          parcel1.writeInt(1);
          param1SessionParams.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          param1Int = IPackageInstaller.Stub.getDefaultImpl().createSession(param1SessionParams, param1String, param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAllSessions(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          parceledListSlice = IPackageInstaller.Stub.getDefaultImpl().getAllSessions(param1Int);
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
    
    public ParceledListSlice getMySessions(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null)
          return IPackageInstaller.Stub.getDefaultImpl().getMySessions(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParceledListSlice)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public PackageInstaller.SessionInfo getSessionInfo(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        PackageInstaller.SessionInfo sessionInfo;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          sessionInfo = IPackageInstaller.Stub.getDefaultImpl().getSessionInfo(param1Int);
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
    
    public void installExistingPackage(String param1String, int param1Int1, int param1Int2, IntentSender param1IntentSender, int param1Int3, List<String> param1List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        try {
          parcel1.writeString(param1String);
          try {
            parcel1.writeInt(param1Int1);
            try {
              parcel1.writeInt(param1Int2);
              if (param1IntentSender != null) {
                parcel1.writeInt(1);
                param1IntentSender.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param1Int3);
                try {
                  parcel1.writeStringList(param1List);
                  if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
                    IPackageInstaller.Stub.getDefaultImpl().installExistingPackage(param1String, param1Int1, param1Int2, param1IntentSender, param1Int3, param1List);
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
      throw param1String;
    }
    
    public IPackageInstallerSession openSession(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null)
          return IPackageInstaller.Stub.getDefaultImpl().openSession(param1Int); 
        parcel2.readException();
        return IPackageInstallerSession.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerCallback(IPackageInstallerCallback param1IPackageInstallerCallback, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param1IPackageInstallerCallback != null) {
          iBinder = param1IPackageInstallerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().registerCallback(param1IPackageInstallerCallback, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPermissionsResult(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().setPermissionsResult(param1Int, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void uninstall(VersionedPackage param1VersionedPackage, String param1String, int param1Int1, IntentSender param1IntentSender, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param1VersionedPackage != null) {
          parcel1.writeInt(1);
          param1VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        if (param1IntentSender != null) {
          parcel1.writeInt(1);
          param1IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().uninstall(param1VersionedPackage, param1String, param1Int1, param1IntentSender, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void uninstallExistingPackage(VersionedPackage param1VersionedPackage, String param1String, IntentSender param1IntentSender, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param1VersionedPackage != null) {
          parcel1.writeInt(1);
          param1VersionedPackage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (param1IntentSender != null) {
          parcel1.writeInt(1);
          param1IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().uninstallExistingPackage(param1VersionedPackage, param1String, param1IntentSender, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterCallback(IPackageInstallerCallback param1IPackageInstallerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        if (param1IPackageInstallerCallback != null) {
          iBinder = param1IPackageInstallerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().unregisterCallback(param1IPackageInstallerCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateSessionAppIcon(int param1Int, Bitmap param1Bitmap) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        if (param1Bitmap != null) {
          parcel1.writeInt(1);
          param1Bitmap.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().updateSessionAppIcon(param1Int, param1Bitmap);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateSessionAppLabel(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstaller");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageInstaller.Stub.getDefaultImpl() != null) {
          IPackageInstaller.Stub.getDefaultImpl().updateSessionAppLabel(param1Int, param1String);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstaller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */