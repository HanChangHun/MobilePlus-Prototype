package android.app.role;

import android.app.ActivityThread;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteCallback;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.infra.AndroidFuture;
import com.android.internal.infra.ServiceConnector;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class RoleControllerManager {
  private static final String LOG_TAG = RoleControllerManager.class.getSimpleName();
  
  private static final long REQUEST_TIMEOUT_MILLIS = 15000L;
  
  private static volatile ComponentName sRemoteServiceComponentName;
  
  private static final SparseArray<ServiceConnector<IRoleController>> sRemoteServices;
  
  private static final Object sRemoteServicesLock = new Object();
  
  private final ServiceConnector<IRoleController> mRemoteService;
  
  static {
    sRemoteServices = new SparseArray();
  }
  
  private RoleControllerManager(ComponentName paramComponentName, Handler paramHandler, Context paramContext) {
    synchronized (sRemoteServicesLock) {
      ServiceConnector.Impl<IRoleController> impl;
      int i = paramContext.getUserId();
      ServiceConnector serviceConnector2 = (ServiceConnector)sRemoteServices.get(i);
      ServiceConnector serviceConnector1 = serviceConnector2;
      if (serviceConnector2 == null) {
        impl = new ServiceConnector.Impl<IRoleController>() {
            protected Handler getJobHandler() {
              return handler;
            }
          };
        Application application = ActivityThread.currentApplication();
        Intent intent = new Intent();
        this("android.app.role.RoleControllerService");
        super(this, (Context)application, intent.setComponent(paramComponentName), 0, i, (Function)_$$Lambda$Z0BwIRmLFQVA4XrF_I5nxvuecWE.INSTANCE, paramHandler);
        sRemoteServices.put(i, impl);
      } 
      this.mRemoteService = (ServiceConnector<IRoleController>)impl;
      return;
    } 
  }
  
  public RoleControllerManager(Context paramContext) {
    this(getRemoteServiceComponentName(paramContext), paramContext.getMainThreadHandler(), paramContext);
  }
  
  public static RoleControllerManager createWithInitializedRemoteServiceComponentName(Handler paramHandler, Context paramContext) {
    return new RoleControllerManager(sRemoteServiceComponentName, paramHandler, paramContext);
  }
  
  private static ComponentName getRemoteServiceComponentName(Context paramContext) {
    Intent intent = new Intent("android.app.role.RoleControllerService");
    PackageManager packageManager = paramContext.getPackageManager();
    intent.setPackage(packageManager.getPermissionControllerPackageName());
    return packageManager.resolveService(intent, 0).getComponentInfo().getComponentName();
  }
  
  public static void initializeRemoteServiceComponentName(Context paramContext) {
    sRemoteServiceComponentName = getRemoteServiceComponentName(paramContext);
  }
  
  private void propagateCallback(AndroidFuture<Bundle> paramAndroidFuture, String paramString, RemoteCallback paramRemoteCallback) {
    paramAndroidFuture.orTimeout(15000L, TimeUnit.MILLISECONDS).whenComplete(new _$$Lambda$RoleControllerManager$1tkryWxs4VtGp5AmGDyjpvBjC28(paramString, paramRemoteCallback));
  }
  
  private void propagateCallback(AndroidFuture<Bundle> paramAndroidFuture, String paramString, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    paramAndroidFuture.orTimeout(15000L, TimeUnit.MILLISECONDS).whenComplete(new _$$Lambda$RoleControllerManager$hbh627Rh8mtJykW3vb1FWR34mIQ(paramExecutor, paramString, paramConsumer));
  }
  
  public void grantDefaultRoles(Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    propagateCallback(this.mRemoteService.postAsync((ServiceConnector.Job)_$$Lambda$RoleControllerManager$Jsb4ev7pHUqel8_lglNSRLiUzpg.INSTANCE), "grantDefaultRoles", paramExecutor, paramConsumer);
  }
  
  public void isApplicationQualifiedForRole(String paramString1, String paramString2, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    propagateCallback(this.mRemoteService.postAsync(new _$$Lambda$RoleControllerManager$ReKCWj8qlXAul532f7t4g53Ivg0(paramString1, paramString2)), "isApplicationQualifiedForRole", paramExecutor, paramConsumer);
  }
  
  public void isApplicationVisibleForRole(String paramString1, String paramString2, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    propagateCallback(this.mRemoteService.postAsync(new _$$Lambda$RoleControllerManager$aq0cHbejAFqnsX1ZigMc9Dz1D5A(paramString1, paramString2)), "isApplicationVisibleForRole", paramExecutor, paramConsumer);
  }
  
  public void isRoleVisible(String paramString, Executor paramExecutor, Consumer<Boolean> paramConsumer) {
    propagateCallback(this.mRemoteService.postAsync(new _$$Lambda$RoleControllerManager$VsbRaGFueP4e1AEVbN4zwbUmdZU(paramString)), "isRoleVisible", paramExecutor, paramConsumer);
  }
  
  public void onAddRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) {
    propagateCallback(this.mRemoteService.postAsync(new _$$Lambda$RoleControllerManager$GiyGeOpnMIVwipW_81KV8TogKt8(paramString1, paramString2, paramInt)), "onAddRoleHolder", paramRemoteCallback);
  }
  
  public void onClearRoleHolders(String paramString, int paramInt, RemoteCallback paramRemoteCallback) {
    propagateCallback(this.mRemoteService.postAsync(new _$$Lambda$RoleControllerManager$lBbpGLk6PhAvXOnY5bjXhdddZ6Q(paramString, paramInt)), "onClearRoleHolders", paramRemoteCallback);
  }
  
  public void onRemoveRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) {
    propagateCallback(this.mRemoteService.postAsync(new _$$Lambda$RoleControllerManager$_qXKDWwgESwB52qfoJN7JTZsDiU(paramString1, paramString2, paramInt)), "onRemoveRoleHolder", paramRemoteCallback);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/RoleControllerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */