package android.app.backup;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import android.util.Pair;
import java.util.List;

public class BackupManager {
  @SystemApi
  public static final int ERROR_AGENT_FAILURE = -1003;
  
  @SystemApi
  public static final int ERROR_BACKUP_CANCELLED = -2003;
  
  @SystemApi
  public static final int ERROR_BACKUP_NOT_ALLOWED = -2001;
  
  @SystemApi
  public static final int ERROR_PACKAGE_NOT_FOUND = -2002;
  
  @SystemApi
  public static final int ERROR_TRANSPORT_ABORTED = -1000;
  
  @SystemApi
  public static final int ERROR_TRANSPORT_INVALID = -2;
  
  @SystemApi
  public static final int ERROR_TRANSPORT_PACKAGE_REJECTED = -1002;
  
  @SystemApi
  public static final int ERROR_TRANSPORT_QUOTA_EXCEEDED = -1005;
  
  @SystemApi
  public static final int ERROR_TRANSPORT_UNAVAILABLE = -1;
  
  public static final String EXTRA_BACKUP_SERVICES_AVAILABLE = "backup_services_available";
  
  @SystemApi
  public static final int FLAG_NON_INCREMENTAL_BACKUP = 1;
  
  @SystemApi
  public static final String PACKAGE_MANAGER_SENTINEL = "@pm@";
  
  @SystemApi
  public static final int SUCCESS = 0;
  
  private static final String TAG = "BackupManager";
  
  private static IBackupManager sService;
  
  private Context mContext;
  
  public BackupManager(Context paramContext) {
    this.mContext = paramContext;
  }
  
  private static void checkServiceBinder() {
    if (sService == null)
      sService = IBackupManager.Stub.asInterface(ServiceManager.getService("backup")); 
  }
  
  public static void dataChanged(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.dataChanged(paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "dataChanged(pkg) couldn't connect");
      }  
  }
  
  @SystemApi
  public void backupNow() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.backupNow();
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "backupNow() couldn't connect");
      }  
  }
  
  @SystemApi
  public RestoreSession beginRestoreSession() {
    RestoreSession restoreSession1 = null;
    RestoreSession restoreSession2 = null;
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    RestoreSession restoreSession3 = restoreSession1;
    if (iBackupManager != null)
      try {
        IRestoreSession iRestoreSession = iBackupManager.beginRestoreSessionForUser(this.mContext.getUserId(), null, null);
        restoreSession3 = restoreSession2;
        if (iRestoreSession != null) {
          restoreSession3 = new RestoreSession();
          this(this.mContext, iRestoreSession);
        } 
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "beginRestoreSession() couldn't connect");
        restoreSession3 = restoreSession1;
      }  
    return restoreSession3;
  }
  
  @SystemApi
  public void cancelBackups() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.cancelBackups();
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "cancelBackups() couldn't connect.");
      }  
  }
  
  public void dataChanged() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.dataChanged(this.mContext.getPackageName());
      } catch (RemoteException remoteException) {
        Log.d("BackupManager", "dataChanged() couldn't connect");
      }  
  }
  
  @SystemApi
  public void excludeKeysFromRestore(String paramString, List<String> paramList) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.excludeKeysFromRestore(paramString, paramList);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "excludeKeysFromRestore() couldn't connect");
      }  
  }
  
  @SystemApi
  public long getAvailableRestoreToken(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getAvailableRestoreTokenForUser(this.mContext.getUserId(), paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getAvailableRestoreToken() couldn't connect");
      }  
    return 0L;
  }
  
  @SystemApi
  public Intent getConfigurationIntent(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getConfigurationIntentForUser(this.mContext.getUserId(), paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getConfigurationIntent() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public String getCurrentTransport() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getCurrentTransport();
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getCurrentTransport() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public ComponentName getCurrentTransportComponent() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getCurrentTransportComponentForUser(this.mContext.getUserId());
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getCurrentTransportComponent() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public Intent getDataManagementIntent(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getDataManagementIntentForUser(this.mContext.getUserId(), paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getDataManagementIntent() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public CharSequence getDataManagementIntentLabel(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getDataManagementLabelForUser(this.mContext.getUserId(), paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getDataManagementIntentLabel() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  @Deprecated
  public String getDataManagementLabel(String paramString) {
    CharSequence charSequence = getDataManagementIntentLabel(paramString);
    if (charSequence == null) {
      charSequence = null;
    } else {
      charSequence = charSequence.toString();
    } 
    return (String)charSequence;
  }
  
  @SystemApi
  public String getDestinationString(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getDestinationStringForUser(this.mContext.getUserId(), paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getDestinationString() couldn't connect");
      }  
    return null;
  }
  
  public UserHandle getUserForAncestralSerialNumber(long paramLong) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.getUserForAncestralSerialNumber(paramLong);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "getUserForAncestralSerialNumber() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public boolean isAppEligibleForBackup(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.isAppEligibleForBackupForUser(this.mContext.getUserId(), paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "isAppEligibleForBackup(pkg) couldn't connect");
      }  
    return false;
  }
  
  @SystemApi
  public boolean isBackupEnabled() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.isBackupEnabled();
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "isBackupEnabled() couldn't connect");
      }  
    return false;
  }
  
  @SystemApi
  public boolean isBackupServiceActive(UserHandle paramUserHandle) {
    this.mContext.enforceCallingOrSelfPermission("android.permission.BACKUP", "isBackupServiceActive");
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.isBackupServiceActive(paramUserHandle.getIdentifier());
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "isBackupEnabled() couldn't connect");
      }  
    return false;
  }
  
  @SystemApi
  public String[] listAllTransports() {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.listAllTransports();
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "listAllTransports() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public int requestBackup(String[] paramArrayOfString, BackupObserver paramBackupObserver) {
    return requestBackup(paramArrayOfString, paramBackupObserver, null, 0);
  }
  
  @SystemApi
  public int requestBackup(String[] paramArrayOfString, BackupObserver paramBackupObserver, BackupManagerMonitor paramBackupManagerMonitor, int paramInt) {
    // Byte code:
    //   0: invokestatic checkServiceBinder : ()V
    //   3: getstatic android/app/backup/BackupManager.sService : Landroid/app/backup/IBackupManager;
    //   6: ifnull -> 83
    //   9: aconst_null
    //   10: astore #5
    //   12: aload_2
    //   13: ifnonnull -> 21
    //   16: aconst_null
    //   17: astore_2
    //   18: goto -> 35
    //   21: new android/app/backup/BackupManager$BackupObserverWrapper
    //   24: dup
    //   25: aload_0
    //   26: aload_0
    //   27: getfield mContext : Landroid/content/Context;
    //   30: aload_2
    //   31: invokespecial <init> : (Landroid/app/backup/BackupManager;Landroid/content/Context;Landroid/app/backup/BackupObserver;)V
    //   34: astore_2
    //   35: aload_3
    //   36: ifnonnull -> 45
    //   39: aload #5
    //   41: astore_3
    //   42: goto -> 55
    //   45: new android/app/backup/BackupManager$BackupManagerMonitorWrapper
    //   48: dup
    //   49: aload_0
    //   50: aload_3
    //   51: invokespecial <init> : (Landroid/app/backup/BackupManager;Landroid/app/backup/BackupManagerMonitor;)V
    //   54: astore_3
    //   55: getstatic android/app/backup/BackupManager.sService : Landroid/app/backup/IBackupManager;
    //   58: aload_1
    //   59: aload_2
    //   60: aload_3
    //   61: iload #4
    //   63: invokeinterface requestBackup : ([Ljava/lang/String;Landroid/app/backup/IBackupObserver;Landroid/app/backup/IBackupManagerMonitor;I)I
    //   68: istore #4
    //   70: iload #4
    //   72: ireturn
    //   73: astore_1
    //   74: ldc 'BackupManager'
    //   76: ldc_w 'requestBackup() couldn't connect'
    //   79: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   82: pop
    //   83: iconst_m1
    //   84: ireturn
    // Exception table:
    //   from	to	target	type
    //   21	35	73	android/os/RemoteException
    //   45	55	73	android/os/RemoteException
    //   55	70	73	android/os/RemoteException
  }
  
  @Deprecated
  public int requestRestore(RestoreObserver paramRestoreObserver) {
    return requestRestore(paramRestoreObserver, null);
  }
  
  @SystemApi
  @Deprecated
  public int requestRestore(RestoreObserver paramRestoreObserver, BackupManagerMonitor paramBackupManagerMonitor) {
    Log.w("BackupManager", "requestRestore(): Since Android P app can no longer request restoring of its backup.");
    return -1;
  }
  
  @SystemApi
  @Deprecated
  public String selectBackupTransport(String paramString) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        return iBackupManager.selectBackupTransport(paramString);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "selectBackupTransport() couldn't connect");
      }  
    return null;
  }
  
  @SystemApi
  public void selectBackupTransport(ComponentName paramComponentName, SelectBackupTransportCallback paramSelectBackupTransportCallback) {
    SelectTransportListenerWrapper selectTransportListenerWrapper;
    checkServiceBinder();
    if (sService != null) {
      if (paramSelectBackupTransportCallback == null) {
        paramSelectBackupTransportCallback = null;
      } else {
        try {
          selectTransportListenerWrapper = new SelectTransportListenerWrapper(this.mContext, paramSelectBackupTransportCallback);
          sService.selectBackupTransportAsyncForUser(this.mContext.getUserId(), paramComponentName, selectTransportListenerWrapper);
        } catch (RemoteException remoteException) {
          Log.e("BackupManager", "selectBackupTransportAsync() couldn't connect");
        } 
        return;
      } 
    } else {
      return;
    } 
    sService.selectBackupTransportAsyncForUser(this.mContext.getUserId(), (ComponentName)remoteException, selectTransportListenerWrapper);
  }
  
  @SystemApi
  public void setAncestralSerialNumber(long paramLong) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.setAncestralSerialNumber(paramLong);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "setAncestralSerialNumber() couldn't connect");
      }  
  }
  
  @SystemApi
  public void setAutoRestore(boolean paramBoolean) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.setAutoRestore(paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "setAutoRestore() couldn't connect");
      }  
  }
  
  @SystemApi
  public void setBackupEnabled(boolean paramBoolean) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.setBackupEnabled(paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "setBackupEnabled() couldn't connect");
      }  
  }
  
  @SystemApi
  public void updateTransportAttributes(ComponentName paramComponentName, String paramString1, Intent paramIntent1, String paramString2, Intent paramIntent2, CharSequence paramCharSequence) {
    checkServiceBinder();
    IBackupManager iBackupManager = sService;
    if (iBackupManager != null)
      try {
        iBackupManager.updateTransportAttributesForUser(this.mContext.getUserId(), paramComponentName, paramString1, paramIntent1, paramString2, paramIntent2, paramCharSequence);
      } catch (RemoteException remoteException) {
        Log.e("BackupManager", "describeTransport() couldn't connect");
      }  
  }
  
  @SystemApi
  @Deprecated
  public void updateTransportAttributes(ComponentName paramComponentName, String paramString1, Intent paramIntent1, String paramString2, Intent paramIntent2, String paramString3) {
    updateTransportAttributes(paramComponentName, paramString1, paramIntent1, paramString2, paramIntent2, paramString3);
  }
  
  private class BackupManagerMonitorWrapper extends IBackupManagerMonitor.Stub {
    final BackupManagerMonitor mMonitor;
    
    BackupManagerMonitorWrapper(BackupManagerMonitor param1BackupManagerMonitor) {
      this.mMonitor = param1BackupManagerMonitor;
    }
    
    public void onEvent(Bundle param1Bundle) throws RemoteException {
      this.mMonitor.onEvent(param1Bundle);
    }
  }
  
  private class BackupObserverWrapper extends IBackupObserver.Stub {
    static final int MSG_FINISHED = 3;
    
    static final int MSG_RESULT = 2;
    
    static final int MSG_UPDATE = 1;
    
    final Handler mHandler;
    
    final BackupObserver mObserver;
    
    BackupObserverWrapper(Context param1Context, BackupObserver param1BackupObserver) {
      this.mHandler = new Handler(param1Context.getMainLooper()) {
          public void handleMessage(Message param2Message) {
            int i = param2Message.what;
            if (i != 1) {
              if (i != 2) {
                if (i != 3) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("Unknown message: ");
                  stringBuilder.append(param2Message);
                  Log.w("BackupManager", stringBuilder.toString());
                } else {
                  BackupManager.BackupObserverWrapper.this.mObserver.backupFinished(param2Message.arg1);
                } 
              } else {
                BackupManager.BackupObserverWrapper.this.mObserver.onResult((String)param2Message.obj, param2Message.arg1);
              } 
            } else {
              Pair pair = (Pair)param2Message.obj;
              BackupManager.BackupObserverWrapper.this.mObserver.onUpdate((String)pair.first, (BackupProgress)pair.second);
            } 
          }
        };
      this.mObserver = param1BackupObserver;
    }
    
    public void backupFinished(int param1Int) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(3, param1Int, 0));
    }
    
    public void onResult(String param1String, int param1Int) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(2, param1Int, 0, param1String));
    }
    
    public void onUpdate(String param1String, BackupProgress param1BackupProgress) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(1, Pair.create(param1String, param1BackupProgress)));
    }
  }
  
  class null extends Handler {
    null(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown message: ");
            stringBuilder.append(param1Message);
            Log.w("BackupManager", stringBuilder.toString());
          } else {
            this.this$1.mObserver.backupFinished(param1Message.arg1);
          } 
        } else {
          this.this$1.mObserver.onResult((String)param1Message.obj, param1Message.arg1);
        } 
      } else {
        Pair pair = (Pair)param1Message.obj;
        this.this$1.mObserver.onUpdate((String)pair.first, (BackupProgress)pair.second);
      } 
    }
  }
  
  private class SelectTransportListenerWrapper extends ISelectBackupTransportCallback.Stub {
    private final Handler mHandler;
    
    private final SelectBackupTransportCallback mListener;
    
    SelectTransportListenerWrapper(Context param1Context, SelectBackupTransportCallback param1SelectBackupTransportCallback) {
      this.mHandler = new Handler(param1Context.getMainLooper());
      this.mListener = param1SelectBackupTransportCallback;
    }
    
    public void onFailure(final int reason) {
      this.mHandler.post(new Runnable() {
            public void run() {
              BackupManager.SelectTransportListenerWrapper.this.mListener.onFailure(reason);
            }
          });
    }
    
    public void onSuccess(final String transportName) {
      this.mHandler.post(new Runnable() {
            public void run() {
              BackupManager.SelectTransportListenerWrapper.this.mListener.onSuccess(transportName);
            }
          });
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.mListener.onSuccess(transportName);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.mListener.onFailure(reason);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */