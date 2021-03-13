package android.companion;

import android.annotation.SystemApi;
import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentSender;
import android.net.MacAddress;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class CompanionDeviceManager {
  public static final String COMPANION_DEVICE_DISCOVERY_PACKAGE_NAME = "com.android.companiondevicemanager";
  
  private static final boolean DEBUG = false;
  
  public static final String EXTRA_DEVICE = "android.companion.extra.DEVICE";
  
  private static final String LOG_TAG = "CompanionDeviceManager";
  
  private final Context mContext;
  
  private final ICompanionDeviceManager mService;
  
  public CompanionDeviceManager(ICompanionDeviceManager paramICompanionDeviceManager, Context paramContext) {
    this.mService = paramICompanionDeviceManager;
    this.mContext = paramContext;
  }
  
  private boolean checkFeaturePresent() {
    boolean bool;
    if (this.mService != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private Activity getActivity() {
    return (Activity)this.mContext;
  }
  
  private String getCallingPackage() {
    return this.mContext.getPackageName();
  }
  
  public void associate(AssociationRequest paramAssociationRequest, Callback paramCallback, Handler paramHandler) {
    if (!checkFeaturePresent())
      return; 
    Objects.requireNonNull(paramAssociationRequest, "Request cannot be null");
    Objects.requireNonNull(paramCallback, "Callback cannot be null");
    try {
      ICompanionDeviceManager iCompanionDeviceManager = this.mService;
      CallbackProxy callbackProxy = new CallbackProxy();
      this(this, paramAssociationRequest, paramCallback, Handler.mainIfNull(paramHandler));
      iCompanionDeviceManager.associate(paramAssociationRequest, callbackProxy, getCallingPackage());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disassociate(String paramString) {
    if (!checkFeaturePresent())
      return; 
    try {
      this.mService.disassociate(paramString, getCallingPackage());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<String> getAssociations() {
    if (!checkFeaturePresent())
      return Collections.emptyList(); 
    try {
      return this.mService.getAssociations(getCallingPackage(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasNotificationAccess(ComponentName paramComponentName) {
    if (!checkFeaturePresent())
      return false; 
    try {
      return this.mService.hasNotificationAccess(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean isDeviceAssociatedForWifiConnection(String paramString, MacAddress paramMacAddress, UserHandle paramUserHandle) {
    if (!checkFeaturePresent())
      return false; 
    Objects.requireNonNull(paramString, "package name cannot be null");
    Objects.requireNonNull(paramMacAddress, "mac address cannot be null");
    Objects.requireNonNull(paramUserHandle, "user cannot be null");
    try {
      return this.mService.isDeviceAssociatedForWifiConnection(paramString, paramMacAddress.toString(), paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void requestNotificationAccess(ComponentName paramComponentName) {
    if (!checkFeaturePresent())
      return; 
    try {
      IntentSender intentSender = this.mService.requestNotificationAccess(paramComponentName).getIntentSender();
      this.mContext.startIntentSender(intentSender, null, 0, 0, 0);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (android.content.IntentSender.SendIntentException sendIntentException) {
      throw new RuntimeException(sendIntentException);
    } 
  }
  
  public static abstract class Callback {
    public abstract void onDeviceFound(IntentSender param1IntentSender);
    
    public abstract void onFailure(CharSequence param1CharSequence);
  }
  
  private class CallbackProxy extends IFindDeviceCallback.Stub implements Application.ActivityLifecycleCallbacks {
    private CompanionDeviceManager.Callback mCallback;
    
    private Handler mHandler;
    
    final Object mLock = new Object();
    
    private AssociationRequest mRequest;
    
    private CallbackProxy(AssociationRequest param1AssociationRequest, CompanionDeviceManager.Callback param1Callback, Handler param1Handler) {
      this.mCallback = param1Callback;
      this.mHandler = param1Handler;
      this.mRequest = param1AssociationRequest;
      CompanionDeviceManager.this.getActivity().getApplication().registerActivityLifecycleCallbacks(this);
    }
    
    <T> void lockAndPost(BiConsumer<CompanionDeviceManager.Callback, T> param1BiConsumer, T param1T) {
      synchronized (this.mLock) {
        if (this.mHandler != null) {
          Handler handler = this.mHandler;
          _$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo _$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo = new _$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo();
          this(this, param1BiConsumer, param1T);
          handler.post(_$$Lambda$CompanionDeviceManager$CallbackProxy$gkUVA3m3QgEEk8G84_kcBFARHvo);
        } 
        return;
      } 
    }
    
    public void onActivityCreated(Activity param1Activity, Bundle param1Bundle) {}
    
    public void onActivityDestroyed(Activity param1Activity) {
      synchronized (this.mLock) {
        if (param1Activity != CompanionDeviceManager.this.getActivity())
          return; 
        try {
          CompanionDeviceManager.this.mService.stopScan(this.mRequest, this, CompanionDeviceManager.this.getCallingPackage());
        } catch (RemoteException remoteException) {
          remoteException.rethrowFromSystemServer();
        } 
        CompanionDeviceManager.this.getActivity().getApplication().unregisterActivityLifecycleCallbacks(this);
        this.mCallback = null;
        this.mHandler = null;
        this.mRequest = null;
        return;
      } 
    }
    
    public void onActivityPaused(Activity param1Activity) {}
    
    public void onActivityResumed(Activity param1Activity) {}
    
    public void onActivitySaveInstanceState(Activity param1Activity, Bundle param1Bundle) {}
    
    public void onActivityStarted(Activity param1Activity) {}
    
    public void onActivityStopped(Activity param1Activity) {}
    
    public void onFailure(CharSequence param1CharSequence) {
      lockAndPost((BiConsumer<CompanionDeviceManager.Callback, CharSequence>)_$$Lambda$ZUPGnRMz08ZrG1ogNO_2O5Hso3I.INSTANCE, param1CharSequence);
    }
    
    public void onSuccess(PendingIntent param1PendingIntent) {
      lockAndPost((BiConsumer<CompanionDeviceManager.Callback, IntentSender>)_$$Lambda$OThxsns9MAD5QsKURFQAFbt_3qc.INSTANCE, param1PendingIntent.getIntentSender());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/CompanionDeviceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */