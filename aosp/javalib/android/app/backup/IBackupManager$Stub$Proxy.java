package android.app.backup;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import java.util.List;

class Proxy implements IBackupManager {
  public static IBackupManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void acknowledgeFullBackupOrRestore(int paramInt, boolean paramBoolean, String paramString1, String paramString2, IFullBackupRestoreObserver paramIFullBackupRestoreObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramIFullBackupRestoreObserver != null) {
        iBinder = paramIFullBackupRestoreObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().acknowledgeFullBackupOrRestore(paramInt, paramBoolean, paramString1, paramString2, paramIFullBackupRestoreObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void acknowledgeFullBackupOrRestoreForUser(int paramInt1, int paramInt2, boolean paramBoolean, String paramString1, String paramString2, IFullBackupRestoreObserver paramIFullBackupRestoreObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      try {
        parcel1.writeInt(paramInt1);
        try {
          boolean bool;
          parcel1.writeInt(paramInt2);
          if (paramBoolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          try {
            parcel1.writeString(paramString1);
            try {
              IBinder iBinder;
              parcel1.writeString(paramString2);
              if (paramIFullBackupRestoreObserver != null) {
                iBinder = paramIFullBackupRestoreObserver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              try {
                if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
                  IBackupManager.Stub.getDefaultImpl().acknowledgeFullBackupOrRestoreForUser(paramInt1, paramInt2, paramBoolean, paramString1, paramString2, paramIFullBackupRestoreObserver);
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
    throw paramString1;
  }
  
  public void adbBackup(int paramInt, ParcelFileDescriptor paramParcelFileDescriptor, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, boolean paramBoolean8, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      boolean bool1 = true;
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean3) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean4) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean5) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean6) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean7) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean8) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().adbBackup(paramInt, paramParcelFileDescriptor, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramBoolean6, paramBoolean7, paramBoolean8, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void adbRestore(int paramInt, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().adbRestore(paramInt, paramParcelFileDescriptor);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void agentConnected(String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().agentConnected(paramString, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void agentConnectedForUser(int paramInt, String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().agentConnectedForUser(paramInt, paramString, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void agentDisconnected(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().agentDisconnected(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void agentDisconnectedForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().agentDisconnectedForUser(paramInt, paramString);
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
  
  public void backupNow() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().backupNow();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void backupNowForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().backupNowForUser(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IRestoreSession beginRestoreSessionForUser(int paramInt, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().beginRestoreSessionForUser(paramInt, paramString1, paramString2); 
      parcel2.readException();
      return IRestoreSession.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelBackups() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().cancelBackups();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelBackupsForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().cancelBackupsForUser(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearBackupData(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().clearBackupData(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearBackupDataForUser(int paramInt, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().clearBackupDataForUser(paramInt, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dataChanged(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().dataChanged(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dataChangedForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().dataChangedForUser(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void excludeKeysFromRestore(String paramString, List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().excludeKeysFromRestore(paramString, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] filterAppsEligibleForBackupForUser(int paramInt, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramArrayOfString = IBackupManager.Stub.getDefaultImpl().filterAppsEligibleForBackupForUser(paramInt, paramArrayOfString);
        return paramArrayOfString;
      } 
      parcel2.readException();
      paramArrayOfString = parcel2.createStringArray();
      return paramArrayOfString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void fullTransportBackupForUser(int paramInt, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().fullTransportBackupForUser(paramInt, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getAvailableRestoreTokenForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getAvailableRestoreTokenForUser(paramInt, paramString); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Intent getConfigurationIntent(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getConfigurationIntent(paramString); 
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
  
  public Intent getConfigurationIntentForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getConfigurationIntentForUser(paramInt, paramString); 
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
  
  public String getCurrentTransport() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getCurrentTransport(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getCurrentTransportComponentForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ComponentName componentName;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        componentName = IBackupManager.Stub.getDefaultImpl().getCurrentTransportComponentForUser(paramInt);
        return componentName;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        componentName = null;
      } 
      return componentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getCurrentTransportForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getCurrentTransportForUser(paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Intent getDataManagementIntent(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getDataManagementIntent(paramString); 
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
  
  public Intent getDataManagementIntentForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getDataManagementIntentForUser(paramInt, paramString); 
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
  
  public CharSequence getDataManagementLabelForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getDataManagementLabelForUser(paramInt, paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getDestinationString(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramString = IBackupManager.Stub.getDefaultImpl().getDestinationString(paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getDestinationStringForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramString = IBackupManager.Stub.getDefaultImpl().getDestinationStringForUser(paramInt, paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.backup.IBackupManager";
  }
  
  public String[] getTransportWhitelist() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().getTransportWhitelist(); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public UserHandle getUserForAncestralSerialNumber(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      UserHandle userHandle;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        userHandle = IBackupManager.Stub.getDefaultImpl().getUserForAncestralSerialNumber(paramLong);
        return userHandle;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        userHandle = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel2);
      } else {
        userHandle = null;
      } 
      return userHandle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasBackupPassword() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(19, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().hasBackupPassword();
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
  
  public void initializeTransportsForUser(int paramInt, String[] paramArrayOfString, IBackupObserver paramIBackupObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (paramIBackupObserver != null) {
        iBinder = paramIBackupObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().initializeTransportsForUser(paramInt, paramArrayOfString, paramIBackupObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAppEligibleForBackupForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(52, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().isAppEligibleForBackupForUser(paramInt, paramString);
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
  
  public boolean isBackupEnabled() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().isBackupEnabled();
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
  
  public boolean isBackupEnabledForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(16, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().isBackupEnabledForUser(paramInt);
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
  
  public boolean isBackupServiceActive(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(49, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().isBackupServiceActive(paramInt);
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
  
  public boolean isUserReadyForBackup(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(50, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().isUserReadyForBackup(paramInt);
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
  
  public ComponentName[] listAllTransportComponentsForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().listAllTransportComponentsForUser(paramInt); 
      parcel2.readException();
      return (ComponentName[])parcel2.createTypedArray(ComponentName.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] listAllTransports() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().listAllTransports(); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] listAllTransportsForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null)
        return IBackupManager.Stub.getDefaultImpl().listAllTransportsForUser(paramInt); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void opComplete(int paramInt, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().opComplete(paramInt, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void opCompleteForUser(int paramInt1, int paramInt2, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().opCompleteForUser(paramInt1, paramInt2, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int requestBackup(String[] paramArrayOfString, IBackupObserver paramIBackupObserver, IBackupManagerMonitor paramIBackupManagerMonitor, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeStringArray(paramArrayOfString);
      IBinder iBinder1 = null;
      if (paramIBackupObserver != null) {
        iBinder2 = paramIBackupObserver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIBackupManagerMonitor != null)
        iBinder2 = paramIBackupManagerMonitor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramInt = IBackupManager.Stub.getDefaultImpl().requestBackup(paramArrayOfString, paramIBackupObserver, paramIBackupManagerMonitor, paramInt);
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
  
  public int requestBackupForUser(int paramInt1, String[] paramArrayOfString, IBackupObserver paramIBackupObserver, IBackupManagerMonitor paramIBackupManagerMonitor, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeStringArray(paramArrayOfString);
      IBinder iBinder1 = null;
      if (paramIBackupObserver != null) {
        iBinder2 = paramIBackupObserver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIBackupManagerMonitor != null)
        iBinder2 = paramIBackupManagerMonitor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IBackupManager.Stub.getDefaultImpl().requestBackupForUser(paramInt1, paramArrayOfString, paramIBackupObserver, paramIBackupManagerMonitor, paramInt2);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restoreAtInstall(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().restoreAtInstall(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restoreAtInstallForUser(int paramInt1, String paramString, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().restoreAtInstallForUser(paramInt1, paramString, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String selectBackupTransport(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramString = IBackupManager.Stub.getDefaultImpl().selectBackupTransport(paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void selectBackupTransportAsyncForUser(int paramInt, ComponentName paramComponentName, ISelectBackupTransportCallback paramISelectBackupTransportCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramISelectBackupTransportCallback != null) {
        iBinder = paramISelectBackupTransportCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().selectBackupTransportAsyncForUser(paramInt, paramComponentName, paramISelectBackupTransportCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String selectBackupTransportForUser(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        paramString = IBackupManager.Stub.getDefaultImpl().selectBackupTransportForUser(paramInt, paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAncestralSerialNumber(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().setAncestralSerialNumber(paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAutoRestore(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().setAutoRestore(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAutoRestoreForUser(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().setAutoRestoreForUser(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setBackupEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().setBackupEnabled(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setBackupEnabledForUser(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().setBackupEnabledForUser(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setBackupPassword(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(18, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        bool = IBackupManager.Stub.getDefaultImpl().setBackupPassword(paramString1, paramString2);
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
  
  public void setBackupServiceActive(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
        IBackupManager.Stub.getDefaultImpl().setBackupServiceActive(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateTransportAttributesForUser(int paramInt, ComponentName paramComponentName, String paramString1, Intent paramIntent1, String paramString2, Intent paramIntent2, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.backup.IBackupManager");
      try {
        parcel1.writeInt(paramInt);
        if (paramComponentName != null) {
          parcel1.writeInt(1);
          paramComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(paramString1);
        if (paramIntent1 != null) {
          parcel1.writeInt(1);
          paramIntent1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(paramString2);
        if (paramIntent2 != null) {
          parcel1.writeInt(1);
          paramIntent2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (paramCharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBackupManager.Stub.getDefaultImpl() != null) {
          IBackupManager.Stub.getDefaultImpl().updateTransportAttributesForUser(paramInt, paramComponentName, paramString1, paramIntent1, paramString2, paramIntent2, paramCharSequence);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */