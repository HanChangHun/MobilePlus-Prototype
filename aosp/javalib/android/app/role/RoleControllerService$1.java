package android.app.role;

import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteCallback;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.QuintConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.Objects;
import java.util.function.BiConsumer;

class null extends IRoleController.Stub {
  private void enforceCallerSystemUid(String paramString) {
    if (Binder.getCallingUid() == 1000)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Only the system process can call ");
    stringBuilder.append(paramString);
    stringBuilder.append("()");
    throw new SecurityException(stringBuilder.toString());
  }
  
  public void grantDefaultRoles(RemoteCallback paramRemoteCallback) {
    enforceCallerSystemUid("grantDefaultRoles");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    RoleControllerService.access$000(RoleControllerService.this).sendMessage(PooledLambda.obtainMessage((BiConsumer)_$$Lambda$RoleControllerService$1$_fmj7uDKaG3BoLl6bhtrA675gRI.INSTANCE, RoleControllerService.this, paramRemoteCallback));
  }
  
  public void isApplicationQualifiedForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) {
    RoleControllerService roleControllerService = RoleControllerService.this;
    Bundle bundle = null;
    roleControllerService.enforceCallingPermission("android.permission.MANAGE_ROLE_HOLDERS", null);
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    if (RoleControllerService.this.onIsApplicationQualifiedForRole(paramString1, paramString2))
      bundle = Bundle.EMPTY; 
    paramRemoteCallback.sendResult(bundle);
  }
  
  public void isApplicationVisibleForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) {
    RoleControllerService roleControllerService = RoleControllerService.this;
    Bundle bundle = null;
    roleControllerService.enforceCallingPermission("android.permission.MANAGE_ROLE_HOLDERS", null);
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    if (RoleControllerService.this.onIsApplicationVisibleForRole(paramString1, paramString2))
      bundle = Bundle.EMPTY; 
    paramRemoteCallback.sendResult(bundle);
  }
  
  public void isRoleVisible(String paramString, RemoteCallback paramRemoteCallback) {
    RoleControllerService roleControllerService = RoleControllerService.this;
    Bundle bundle = null;
    roleControllerService.enforceCallingPermission("android.permission.MANAGE_ROLE_HOLDERS", null);
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    if (RoleControllerService.this.onIsRoleVisible(paramString))
      bundle = Bundle.EMPTY; 
    paramRemoteCallback.sendResult(bundle);
  }
  
  public void onAddRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) {
    enforceCallerSystemUid("onAddRoleHolder");
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    RoleControllerService.access$000(RoleControllerService.this).sendMessage(PooledLambda.obtainMessage((QuintConsumer)_$$Lambda$RoleControllerService$1$UVI1sAWAcBnt3Enqn2IT_Lirwtk.INSTANCE, RoleControllerService.this, paramString1, paramString2, Integer.valueOf(paramInt), paramRemoteCallback));
  }
  
  public void onClearRoleHolders(String paramString, int paramInt, RemoteCallback paramRemoteCallback) {
    enforceCallerSystemUid("onClearRoleHolders");
    Preconditions.checkStringNotEmpty(paramString, "roleName cannot be null or empty");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    RoleControllerService.access$000(RoleControllerService.this).sendMessage(PooledLambda.obtainMessage((QuadConsumer)_$$Lambda$RoleControllerService$1$dBm1t_MGyEA9yMAxoOUMOhYVmPo.INSTANCE, RoleControllerService.this, paramString, Integer.valueOf(paramInt), paramRemoteCallback));
  }
  
  public void onRemoveRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) {
    enforceCallerSystemUid("onRemoveRoleHolder");
    Preconditions.checkStringNotEmpty(paramString1, "roleName cannot be null or empty");
    Preconditions.checkStringNotEmpty(paramString2, "packageName cannot be null or empty");
    Objects.requireNonNull(paramRemoteCallback, "callback cannot be null");
    RoleControllerService.access$000(RoleControllerService.this).sendMessage(PooledLambda.obtainMessage((QuintConsumer)_$$Lambda$RoleControllerService$1$PB6H1df6VvLzUJ3hhB_75mN3u7s.INSTANCE, RoleControllerService.this, paramString1, paramString2, Integer.valueOf(paramInt), paramRemoteCallback));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/RoleControllerService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */