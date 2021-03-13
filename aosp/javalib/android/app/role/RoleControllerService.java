package android.app.role;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteCallback;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.Objects;
import java.util.function.BiConsumer;

@SystemApi
public abstract class RoleControllerService extends Service {
  public static final String SERVICE_INTERFACE = "android.app.role.RoleControllerService";
  
  private Handler mWorkerHandler;
  
  private HandlerThread mWorkerThread;
  
  private void grantDefaultRoles(RemoteCallback paramRemoteCallback) {
    Bundle bundle;
    if (onGrantDefaultRoles()) {
      bundle = Bundle.EMPTY;
    } else {
      bundle = null;
    } 
    paramRemoteCallback.sendResult(bundle);
  }
  
  private void onAddRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) {
    if (onAddRoleHolder(paramString1, paramString2, paramInt)) {
      Bundle bundle = Bundle.EMPTY;
    } else {
      paramString1 = null;
    } 
    paramRemoteCallback.sendResult((Bundle)paramString1);
  }
  
  private void onClearRoleHolders(String paramString, int paramInt, RemoteCallback paramRemoteCallback) {
    if (onClearRoleHolders(paramString, paramInt)) {
      Bundle bundle = Bundle.EMPTY;
    } else {
      paramString = null;
    } 
    paramRemoteCallback.sendResult((Bundle)paramString);
  }
  
  private void onRemoveRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) {
    if (onRemoveRoleHolder(paramString1, paramString2, paramInt)) {
      Bundle bundle = Bundle.EMPTY;
    } else {
      paramString1 = null;
    } 
    paramRemoteCallback.sendResult((Bundle)paramString1);
  }
  
  public abstract boolean onAddRoleHolder(String paramString1, String paramString2, int paramInt);
  
  public final IBinder onBind(Intent paramIntent) {
    return (IBinder)new IRoleController.Stub() {
        private void enforceCallerSystemUid(String param1String) {
          if (Binder.getCallingUid() == 1000)
            return; 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Only the system process can call ");
          stringBuilder.append(param1String);
          stringBuilder.append("()");
          throw new SecurityException(stringBuilder.toString());
        }
        
        public void grantDefaultRoles(RemoteCallback param1RemoteCallback) {
          enforceCallerSystemUid("grantDefaultRoles");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$RoleControllerService$1$_fmj7uDKaG3BoLl6bhtrA675gRI.INSTANCE, RoleControllerService.this, param1RemoteCallback));
        }
        
        public void isApplicationQualifiedForRole(String param1String1, String param1String2, RemoteCallback param1RemoteCallback) {
          RoleControllerService roleControllerService = RoleControllerService.this;
          Bundle bundle = null;
          roleControllerService.enforceCallingPermission("android.permission.MANAGE_ROLE_HOLDERS", null);
          Preconditions.checkStringNotEmpty(param1String1, "roleName cannot be null or empty");
          Preconditions.checkStringNotEmpty(param1String2, "packageName cannot be null or empty");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          if (RoleControllerService.this.onIsApplicationQualifiedForRole(param1String1, param1String2))
            bundle = Bundle.EMPTY; 
          param1RemoteCallback.sendResult(bundle);
        }
        
        public void isApplicationVisibleForRole(String param1String1, String param1String2, RemoteCallback param1RemoteCallback) {
          RoleControllerService roleControllerService = RoleControllerService.this;
          Bundle bundle = null;
          roleControllerService.enforceCallingPermission("android.permission.MANAGE_ROLE_HOLDERS", null);
          Preconditions.checkStringNotEmpty(param1String1, "roleName cannot be null or empty");
          Preconditions.checkStringNotEmpty(param1String2, "packageName cannot be null or empty");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          if (RoleControllerService.this.onIsApplicationVisibleForRole(param1String1, param1String2))
            bundle = Bundle.EMPTY; 
          param1RemoteCallback.sendResult(bundle);
        }
        
        public void isRoleVisible(String param1String, RemoteCallback param1RemoteCallback) {
          RoleControllerService roleControllerService = RoleControllerService.this;
          Bundle bundle = null;
          roleControllerService.enforceCallingPermission("android.permission.MANAGE_ROLE_HOLDERS", null);
          Preconditions.checkStringNotEmpty(param1String, "roleName cannot be null or empty");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          if (RoleControllerService.this.onIsRoleVisible(param1String))
            bundle = Bundle.EMPTY; 
          param1RemoteCallback.sendResult(bundle);
        }
        
        public void onAddRoleHolder(String param1String1, String param1String2, int param1Int, RemoteCallback param1RemoteCallback) {
          enforceCallerSystemUid("onAddRoleHolder");
          Preconditions.checkStringNotEmpty(param1String1, "roleName cannot be null or empty");
          Preconditions.checkStringNotEmpty(param1String2, "packageName cannot be null or empty");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage((QuintConsumer)_$$Lambda$RoleControllerService$1$UVI1sAWAcBnt3Enqn2IT_Lirwtk.INSTANCE, RoleControllerService.this, param1String1, param1String2, Integer.valueOf(param1Int), param1RemoteCallback));
        }
        
        public void onClearRoleHolders(String param1String, int param1Int, RemoteCallback param1RemoteCallback) {
          enforceCallerSystemUid("onClearRoleHolders");
          Preconditions.checkStringNotEmpty(param1String, "roleName cannot be null or empty");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage((QuadConsumer)_$$Lambda$RoleControllerService$1$dBm1t_MGyEA9yMAxoOUMOhYVmPo.INSTANCE, RoleControllerService.this, param1String, Integer.valueOf(param1Int), param1RemoteCallback));
        }
        
        public void onRemoveRoleHolder(String param1String1, String param1String2, int param1Int, RemoteCallback param1RemoteCallback) {
          enforceCallerSystemUid("onRemoveRoleHolder");
          Preconditions.checkStringNotEmpty(param1String1, "roleName cannot be null or empty");
          Preconditions.checkStringNotEmpty(param1String2, "packageName cannot be null or empty");
          Objects.requireNonNull(param1RemoteCallback, "callback cannot be null");
          RoleControllerService.this.mWorkerHandler.sendMessage(PooledLambda.obtainMessage((QuintConsumer)_$$Lambda$RoleControllerService$1$PB6H1df6VvLzUJ3hhB_75mN3u7s.INSTANCE, RoleControllerService.this, param1String1, param1String2, Integer.valueOf(param1Int), param1RemoteCallback));
        }
      };
  }
  
  public abstract boolean onClearRoleHolders(String paramString, int paramInt);
  
  public void onCreate() {
    super.onCreate();
    HandlerThread handlerThread = new HandlerThread(RoleControllerService.class.getSimpleName());
    this.mWorkerThread = handlerThread;
    handlerThread.start();
    this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
  }
  
  public void onDestroy() {
    super.onDestroy();
    this.mWorkerThread.quitSafely();
  }
  
  public abstract boolean onGrantDefaultRoles();
  
  public abstract boolean onIsApplicationQualifiedForRole(String paramString1, String paramString2);
  
  public boolean onIsApplicationVisibleForRole(String paramString1, String paramString2) {
    return onIsApplicationQualifiedForRole(paramString1, paramString2);
  }
  
  public abstract boolean onIsRoleVisible(String paramString);
  
  public abstract boolean onRemoveRoleHolder(String paramString1, String paramString2, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/RoleControllerService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */