package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

public interface ICrossProfileApps extends IInterface {
  boolean canConfigureInteractAcrossProfiles(String paramString) throws RemoteException;
  
  boolean canInteractAcrossProfiles(String paramString) throws RemoteException;
  
  boolean canRequestInteractAcrossProfiles(String paramString) throws RemoteException;
  
  boolean canUserAttemptToConfigureInteractAcrossProfiles(String paramString) throws RemoteException;
  
  void clearInteractAcrossProfilesAppOps() throws RemoteException;
  
  List<UserHandle> getTargetUserProfiles(String paramString) throws RemoteException;
  
  void resetInteractAcrossProfilesAppOps(List<String> paramList) throws RemoteException;
  
  void setInteractAcrossProfilesAppOp(String paramString, int paramInt) throws RemoteException;
  
  void startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException;
  
  void startActivityAsUserByIntent(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, int paramInt, IBinder paramIBinder, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements ICrossProfileApps {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean canConfigureInteractAcrossProfiles(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean canInteractAcrossProfiles(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean canRequestInteractAcrossProfiles(String param1String) throws RemoteException {
      return false;
    }
    
    public boolean canUserAttemptToConfigureInteractAcrossProfiles(String param1String) throws RemoteException {
      return false;
    }
    
    public void clearInteractAcrossProfilesAppOps() throws RemoteException {}
    
    public List<UserHandle> getTargetUserProfiles(String param1String) throws RemoteException {
      return null;
    }
    
    public void resetInteractAcrossProfilesAppOps(List<String> param1List) throws RemoteException {}
    
    public void setInteractAcrossProfilesAppOp(String param1String, int param1Int) throws RemoteException {}
    
    public void startActivityAsUser(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, ComponentName param1ComponentName, int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void startActivityAsUserByIntent(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, int param1Int, IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICrossProfileApps {
    private static final String DESCRIPTOR = "android.content.pm.ICrossProfileApps";
    
    static final int TRANSACTION_canConfigureInteractAcrossProfiles = 7;
    
    static final int TRANSACTION_canInteractAcrossProfiles = 4;
    
    static final int TRANSACTION_canRequestInteractAcrossProfiles = 5;
    
    static final int TRANSACTION_canUserAttemptToConfigureInteractAcrossProfiles = 8;
    
    static final int TRANSACTION_clearInteractAcrossProfilesAppOps = 10;
    
    static final int TRANSACTION_getTargetUserProfiles = 3;
    
    static final int TRANSACTION_resetInteractAcrossProfilesAppOps = 9;
    
    static final int TRANSACTION_setInteractAcrossProfilesAppOp = 6;
    
    static final int TRANSACTION_startActivityAsUser = 1;
    
    static final int TRANSACTION_startActivityAsUserByIntent = 2;
    
    public Stub() {
      attachInterface(this, "android.content.pm.ICrossProfileApps");
    }
    
    public static ICrossProfileApps asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.ICrossProfileApps");
      return (iInterface != null && iInterface instanceof ICrossProfileApps) ? (ICrossProfileApps)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICrossProfileApps getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 10:
          return "clearInteractAcrossProfilesAppOps";
        case 9:
          return "resetInteractAcrossProfilesAppOps";
        case 8:
          return "canUserAttemptToConfigureInteractAcrossProfiles";
        case 7:
          return "canConfigureInteractAcrossProfiles";
        case 6:
          return "setInteractAcrossProfilesAppOp";
        case 5:
          return "canRequestInteractAcrossProfiles";
        case 4:
          return "canInteractAcrossProfiles";
        case 3:
          return "getTargetUserProfiles";
        case 2:
          return "startActivityAsUserByIntent";
        case 1:
          break;
      } 
      return "startActivityAsUser";
    }
    
    public static boolean setDefaultImpl(ICrossProfileApps param1ICrossProfileApps) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICrossProfileApps != null) {
          Proxy.sDefaultImpl = param1ICrossProfileApps;
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
        boolean bool;
        List<UserHandle> list;
        IApplicationThread iApplicationThread1;
        String str2;
        Intent intent;
        IBinder iBinder;
        boolean bool1;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 10:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            clearInteractAcrossProfilesAppOps();
            param1Parcel2.writeNoException();
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            resetInteractAcrossProfilesAppOps(param1Parcel1.createStringArrayList());
            param1Parcel2.writeNoException();
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            bool = canUserAttemptToConfigureInteractAcrossProfiles(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            bool = canConfigureInteractAcrossProfiles(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            setInteractAcrossProfilesAppOp(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            bool = canRequestInteractAcrossProfiles(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            bool = canInteractAcrossProfiles(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.content.pm.ICrossProfileApps");
            list = getTargetUserProfiles(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 2:
            list.enforceInterface("android.content.pm.ICrossProfileApps");
            iApplicationThread1 = IApplicationThread.Stub.asInterface(list.readStrongBinder());
            str2 = list.readString();
            str3 = list.readString();
            if (list.readInt() != 0) {
              intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)list);
            } else {
              intent = null;
            } 
            i = list.readInt();
            iBinder = list.readStrongBinder();
            if (list.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)list);
            } else {
              list = null;
            } 
            startActivityAsUserByIntent(iApplicationThread1, str2, str3, intent, i, iBinder, (Bundle)list);
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.content.pm.ICrossProfileApps");
        IApplicationThread iApplicationThread2 = IApplicationThread.Stub.asInterface(list.readStrongBinder());
        String str3 = list.readString();
        String str1 = list.readString();
        if (list.readInt() != 0) {
          ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)list);
        } else {
          intent = null;
        } 
        int i = list.readInt();
        if (list.readInt() != 0) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        startActivityAsUser(iApplicationThread2, str3, str1, (ComponentName)intent, i, bool1);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.content.pm.ICrossProfileApps");
      return true;
    }
    
    private static class Proxy implements ICrossProfileApps {
      public static ICrossProfileApps sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public boolean canConfigureInteractAcrossProfiles(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(7, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            bool = ICrossProfileApps.Stub.getDefaultImpl().canConfigureInteractAcrossProfiles(param2String);
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
      
      public boolean canInteractAcrossProfiles(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            bool = ICrossProfileApps.Stub.getDefaultImpl().canInteractAcrossProfiles(param2String);
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
      
      public boolean canRequestInteractAcrossProfiles(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(5, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            bool = ICrossProfileApps.Stub.getDefaultImpl().canRequestInteractAcrossProfiles(param2String);
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
      
      public boolean canUserAttemptToConfigureInteractAcrossProfiles(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(8, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            bool = ICrossProfileApps.Stub.getDefaultImpl().canUserAttemptToConfigureInteractAcrossProfiles(param2String);
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
      
      public void clearInteractAcrossProfilesAppOps() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            ICrossProfileApps.Stub.getDefaultImpl().clearInteractAcrossProfilesAppOps();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.ICrossProfileApps";
      }
      
      public List<UserHandle> getTargetUserProfiles(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null)
            return ICrossProfileApps.Stub.getDefaultImpl().getTargetUserProfiles(param2String); 
          parcel2.readException();
          return parcel2.createTypedArrayList(UserHandle.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void resetInteractAcrossProfilesAppOps(List<String> param2List) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeStringList(param2List);
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            ICrossProfileApps.Stub.getDefaultImpl().resetInteractAcrossProfilesAppOps(param2List);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setInteractAcrossProfilesAppOp(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
            ICrossProfileApps.Stub.getDefaultImpl().setInteractAcrossProfilesAppOp(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startActivityAsUser(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, ComponentName param2ComponentName, int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeString(param2String2);
              if (param2ComponentName != null) {
                parcel1.writeInt(1);
                param2ComponentName.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                boolean bool;
                parcel1.writeInt(param2Int);
                if (param2Boolean) {
                  bool = true;
                } else {
                  bool = false;
                } 
                parcel1.writeInt(bool);
                try {
                  if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
                    ICrossProfileApps.Stub.getDefaultImpl().startActivityAsUser(param2IApplicationThread, param2String1, param2String2, param2ComponentName, param2Int, param2Boolean);
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
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public void startActivityAsUserByIntent(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, int param2Int, IBinder param2IBinder, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeString(param2String2);
              if (param2Intent != null) {
                parcel1.writeInt(1);
                param2Intent.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                parcel1.writeInt(param2Int);
                parcel1.writeStrongBinder(param2IBinder);
                if (param2Bundle != null) {
                  parcel1.writeInt(1);
                  param2Bundle.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
                  ICrossProfileApps.Stub.getDefaultImpl().startActivityAsUserByIntent(param2IApplicationThread, param2String1, param2String2, param2Intent, param2Int, param2IBinder, param2Bundle);
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
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
    }
  }
  
  private static class Proxy implements ICrossProfileApps {
    public static ICrossProfileApps sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean canConfigureInteractAcrossProfiles(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          bool = ICrossProfileApps.Stub.getDefaultImpl().canConfigureInteractAcrossProfiles(param1String);
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
    
    public boolean canInteractAcrossProfiles(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          bool = ICrossProfileApps.Stub.getDefaultImpl().canInteractAcrossProfiles(param1String);
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
    
    public boolean canRequestInteractAcrossProfiles(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          bool = ICrossProfileApps.Stub.getDefaultImpl().canRequestInteractAcrossProfiles(param1String);
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
    
    public boolean canUserAttemptToConfigureInteractAcrossProfiles(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          bool = ICrossProfileApps.Stub.getDefaultImpl().canUserAttemptToConfigureInteractAcrossProfiles(param1String);
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
    
    public void clearInteractAcrossProfilesAppOps() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          ICrossProfileApps.Stub.getDefaultImpl().clearInteractAcrossProfilesAppOps();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.ICrossProfileApps";
    }
    
    public List<UserHandle> getTargetUserProfiles(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null)
          return ICrossProfileApps.Stub.getDefaultImpl().getTargetUserProfiles(param1String); 
        parcel2.readException();
        return parcel2.createTypedArrayList(UserHandle.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resetInteractAcrossProfilesAppOps(List<String> param1List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeStringList(param1List);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          ICrossProfileApps.Stub.getDefaultImpl().resetInteractAcrossProfilesAppOps(param1List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setInteractAcrossProfilesAppOp(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
          ICrossProfileApps.Stub.getDefaultImpl().setInteractAcrossProfilesAppOp(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startActivityAsUser(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, ComponentName param1ComponentName, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeString(param1String2);
            if (param1ComponentName != null) {
              parcel1.writeInt(1);
              param1ComponentName.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              boolean bool;
              parcel1.writeInt(param1Int);
              if (param1Boolean) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel1.writeInt(bool);
              try {
                if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
                  ICrossProfileApps.Stub.getDefaultImpl().startActivityAsUser(param1IApplicationThread, param1String1, param1String2, param1ComponentName, param1Int, param1Boolean);
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
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public void startActivityAsUserByIntent(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, int param1Int, IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeString(param1String2);
            if (param1Intent != null) {
              parcel1.writeInt(1);
              param1Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param1Int);
              parcel1.writeStrongBinder(param1IBinder);
              if (param1Bundle != null) {
                parcel1.writeInt(1);
                param1Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
                ICrossProfileApps.Stub.getDefaultImpl().startActivityAsUserByIntent(param1IApplicationThread, param1String1, param1String2, param1Intent, param1Int, param1IBinder, param1Bundle);
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
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ICrossProfileApps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */