package android.app.role;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public final class RoleManager {
  public static final String ACTION_REQUEST_ROLE = "android.app.role.action.REQUEST_ROLE";
  
  private static final String LOG_TAG = RoleManager.class.getSimpleName();
  
  @SystemApi
  public static final int MANAGE_HOLDERS_FLAG_DONT_KILL_APP = 1;
  
  public static final String PERMISSION_MANAGE_ROLES_FROM_CONTROLLER = "com.android.permissioncontroller.permission.MANAGE_ROLES_FROM_CONTROLLER";
  
  public static final String ROLE_ASSISTANT = "android.app.role.ASSISTANT";
  
  public static final String ROLE_BROWSER = "android.app.role.BROWSER";
  
  public static final String ROLE_CALL_REDIRECTION = "android.app.role.CALL_REDIRECTION";
  
  public static final String ROLE_CALL_SCREENING = "android.app.role.CALL_SCREENING";
  
  public static final String ROLE_DIALER = "android.app.role.DIALER";
  
  public static final String ROLE_EMERGENCY = "android.app.role.EMERGENCY";
  
  public static final String ROLE_HOME = "android.app.role.HOME";
  
  public static final String ROLE_SMS = "android.app.role.SMS";
  
  private final Context mContext;
  
  private final SparseArray<ArrayMap<OnRoleHoldersChangedListener, OnRoleHoldersChangedListenerDelegate>> mListeners = new SparseArray();
  
  private final Object mListenersLock = new Object();
  
  private final IRoleManager mService;
  
  public RoleManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
    this.mService = IRoleManager.Stub.asInterface(ServiceManager.getServiceOrThrow("role"));
  }
  
  private static RemoteCallback createRemoteCallback(Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    return new RemoteCallback(new _$$Lambda$RoleManager$m9y_ZqrQy4gHK3mGDXvG129sdjU(paramExecutor, paramConsumer));
  }
  
  @SystemApi
  public void addOnRoleHoldersChangedListenerAsUser(Executor paramExecutor, OnRoleHoldersChangedListener paramOnRoleHoldersChangedListener, UserHandle paramUserHandle) {
    Objects.requireNonNull(paramExecutor, "executor cannot be null");
    Objects.requireNonNull(paramOnRoleHoldersChangedListener, "listener cannot be null");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    int i = paramUserHandle.getIdentifier();
    synchronized (this.mListenersLock) {
      ArrayMap arrayMap1;
      ArrayMap arrayMap2 = (ArrayMap)this.mListeners.get(i);
      if (arrayMap2 == null) {
        arrayMap1 = new ArrayMap();
        this();
        this.mListeners.put(i, arrayMap1);
      } else {
        arrayMap1 = arrayMap2;
        if (arrayMap2.containsKey(paramOnRoleHoldersChangedListener))
          return; 
      } 
      OnRoleHoldersChangedListenerDelegate onRoleHoldersChangedListenerDelegate = new OnRoleHoldersChangedListenerDelegate();
      this(paramExecutor, paramOnRoleHoldersChangedListener);
      try {
        this.mService.addOnRoleHoldersChangedListenerAsUser(onRoleHoldersChangedListenerDelegate, i);
        arrayMap1.put(paramOnRoleHoldersChangedListener, onRoleHoldersChangedListenerDelegate);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  @SystemApi
  public void addRoleHolderAsUser(String paramString1, String paramString2, int paramInt, UserHandle paramUserHandle, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    Objects.requireNonNull(paramExecutor, "executor cannot be null");
    Objects.requireNonNull(paramConsumer, "callback cannot be null");
    try {
      this.mService.addRoleHolderAsUser(paramString1, paramString2, paramInt, paramUserHandle.getIdentifier(), createRemoteCallback(paramExecutor, paramConsumer));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean addRoleHolderFromController(String paramString1, String paramString2) {
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    try {
      return this.mService.addRoleHolderFromController(paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void clearRoleHoldersAsUser(String paramString, int paramInt, UserHandle paramUserHandle, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    Objects.requireNonNull(paramExecutor, "executor cannot be null");
    Objects.requireNonNull(paramConsumer, "callback cannot be null");
    try {
      this.mService.clearRoleHoldersAsUser(paramString, paramInt, paramUserHandle.getIdentifier(), createRemoteCallback(paramExecutor, paramConsumer));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Intent createRequestRoleIntent(String paramString) {
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    Intent intent = new Intent("android.app.role.action.REQUEST_ROLE");
    intent.setPackage(this.mContext.getPackageManager().getPermissionControllerPackageName());
    intent.putExtra("android.intent.extra.ROLE_NAME", paramString);
    return intent;
  }
  
  public String getDefaultSmsPackage(int paramInt) {
    try {
      return this.mService.getDefaultSmsPackage(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<String> getHeldRolesFromController(String paramString) {
    Preconditions.checkStringNotEmpty(paramString, "packageName cannot be null or empty");
    try {
      return this.mService.getHeldRolesFromController(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<String> getRoleHolders(String paramString) {
    return getRoleHoldersAsUser(paramString, Process.myUserHandle());
  }
  
  @SystemApi
  public List<String> getRoleHoldersAsUser(String paramString, UserHandle paramUserHandle) {
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    try {
      return this.mService.getRoleHoldersAsUser(paramString, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isRoleAvailable(String paramString) {
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    try {
      return this.mService.isRoleAvailable(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isRoleHeld(String paramString) {
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    try {
      return this.mService.isRoleHeld(paramString, this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void removeOnRoleHoldersChangedListenerAsUser(OnRoleHoldersChangedListener paramOnRoleHoldersChangedListener, UserHandle paramUserHandle) {
    Objects.requireNonNull(paramOnRoleHoldersChangedListener, "listener cannot be null");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    int i = paramUserHandle.getIdentifier();
    synchronized (this.mListenersLock) {
      ArrayMap arrayMap = (ArrayMap)this.mListeners.get(i);
      if (arrayMap == null)
        return; 
      OnRoleHoldersChangedListenerDelegate onRoleHoldersChangedListenerDelegate = (OnRoleHoldersChangedListenerDelegate)arrayMap.get(paramOnRoleHoldersChangedListener);
      if (onRoleHoldersChangedListenerDelegate == null)
        return; 
      try {
        this.mService.removeOnRoleHoldersChangedListenerAsUser(onRoleHoldersChangedListenerDelegate, paramUserHandle.getIdentifier());
        arrayMap.remove(paramOnRoleHoldersChangedListener);
        if (arrayMap.isEmpty())
          this.mListeners.remove(i); 
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  @SystemApi
  public void removeRoleHolderAsUser(String paramString1, String paramString2, int paramInt, UserHandle paramUserHandle, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    Objects.requireNonNull(paramExecutor, "executor cannot be null");
    Objects.requireNonNull(paramConsumer, "callback cannot be null");
    try {
      this.mService.removeRoleHolderAsUser(paramString1, paramString2, paramInt, paramUserHandle.getIdentifier(), createRemoteCallback(paramExecutor, paramConsumer));
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean removeRoleHolderFromController(String paramString1, String paramString2) {
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    try {
      return this.mService.removeRoleHolderFromController(paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setRoleNamesFromController(List<String> paramList) {
    Objects.requireNonNull(paramList, "roleNames cannot be null");
    try {
      this.mService.setRoleNamesFromController(paramList);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static @interface ManageHoldersFlags {}
  
  private static class OnRoleHoldersChangedListenerDelegate extends IOnRoleHoldersChangedListener.Stub {
    private final Executor mExecutor;
    
    private final OnRoleHoldersChangedListener mListener;
    
    OnRoleHoldersChangedListenerDelegate(Executor param1Executor, OnRoleHoldersChangedListener param1OnRoleHoldersChangedListener) {
      this.mExecutor = param1Executor;
      this.mListener = param1OnRoleHoldersChangedListener;
    }
    
    public void onRoleHoldersChanged(String param1String, int param1Int) {
      long l = Binder.clearCallingIdentity();
      try {
        this.mExecutor.execute((Runnable)PooledLambda.obtainRunnable((TriConsumer)_$$Lambda$o94o2jK_ei_IVw_3oY_QJ49zpAA.INSTANCE, this.mListener, param1String, UserHandle.of(param1Int)));
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/RoleManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */