package android.app;

import android.annotation.SystemApi;
import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.app.trust.ITrustManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.persistentdata.IPersistentDataBlockService;
import android.util.Log;
import android.view.IOnKeyguardExitResult;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class KeyguardManager {
  public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL = "android.app.action.CONFIRM_DEVICE_CREDENTIAL";
  
  public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL_WITH_USER = "android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER";
  
  public static final String ACTION_CONFIRM_FRP_CREDENTIAL = "android.app.action.CONFIRM_FRP_CREDENTIAL";
  
  public static final String EXTRA_ALTERNATE_BUTTON_LABEL = "android.app.extra.ALTERNATE_BUTTON_LABEL";
  
  public static final String EXTRA_DESCRIPTION = "android.app.extra.DESCRIPTION";
  
  public static final String EXTRA_DISALLOW_BIOMETRICS_IF_POLICY_EXISTS = "check_dpm";
  
  public static final String EXTRA_TITLE = "android.app.extra.TITLE";
  
  public static final int RESULT_ALTERNATE = 1;
  
  private static final String TAG = "KeyguardManager";
  
  private final IActivityManager mAm;
  
  private final Context mContext;
  
  private final INotificationManager mNotificationManager;
  
  private final ITrustManager mTrustManager;
  
  private final IWindowManager mWM;
  
  KeyguardManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
    this.mWM = WindowManagerGlobal.getWindowManagerService();
    this.mAm = ActivityManager.getService();
    this.mTrustManager = ITrustManager.Stub.asInterface(ServiceManager.getServiceOrThrow("trust"));
    this.mNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("notification"));
  }
  
  private boolean checkInitialLockMethodUsage() {
    if (this.mContext.checkCallingOrSelfPermission("android.permission.SET_INITIAL_LOCK") == 0)
      return !!this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive"); 
    throw new SecurityException("Requires SET_INITIAL_LOCK permission.");
  }
  
  private String getSettingsPackageForIntent(Intent paramIntent) {
    List list = this.mContext.getPackageManager().queryIntentActivities(paramIntent, 1048576);
    return (list.size() < 0) ? ((ResolveInfo)list.get(0)).activityInfo.packageName : "com.android.settings";
  }
  
  @Deprecated
  public Intent createConfirmDeviceCredentialIntent(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    if (!isDeviceSecure())
      return null; 
    Intent intent = new Intent("android.app.action.CONFIRM_DEVICE_CREDENTIAL");
    intent.putExtra("android.app.extra.TITLE", paramCharSequence1);
    intent.putExtra("android.app.extra.DESCRIPTION", paramCharSequence2);
    intent.setPackage(getSettingsPackageForIntent(intent));
    return intent;
  }
  
  public Intent createConfirmDeviceCredentialIntent(CharSequence paramCharSequence1, CharSequence paramCharSequence2, int paramInt) {
    if (!isDeviceSecure(paramInt))
      return null; 
    Intent intent = new Intent("android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER");
    intent.putExtra("android.app.extra.TITLE", paramCharSequence1);
    intent.putExtra("android.app.extra.DESCRIPTION", paramCharSequence2);
    intent.putExtra("android.intent.extra.USER_ID", paramInt);
    intent.setPackage(getSettingsPackageForIntent(intent));
    return intent;
  }
  
  public Intent createConfirmDeviceCredentialIntent(CharSequence paramCharSequence1, CharSequence paramCharSequence2, int paramInt, boolean paramBoolean) {
    Intent intent = createConfirmDeviceCredentialIntent(paramCharSequence1, paramCharSequence2, paramInt);
    intent.putExtra("check_dpm", paramBoolean);
    return intent;
  }
  
  @SystemApi
  public Intent createConfirmFactoryResetCredentialIntent(CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3) {
    if (LockPatternUtils.frpCredentialEnabled(this.mContext)) {
      if (Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0) == 0)
        try {
          IPersistentDataBlockService iPersistentDataBlockService = IPersistentDataBlockService.Stub.asInterface(ServiceManager.getService("persistent_data_block"));
          if (iPersistentDataBlockService != null) {
            if (!iPersistentDataBlockService.hasFrpCredentialHandle()) {
              Log.i("KeyguardManager", "The persistent data block does not have a factory reset credential.");
              return null;
            } 
            Intent intent = new Intent("android.app.action.CONFIRM_FRP_CREDENTIAL");
            intent.putExtra("android.app.extra.TITLE", paramCharSequence1);
            intent.putExtra("android.app.extra.DESCRIPTION", paramCharSequence2);
            intent.putExtra("android.app.extra.ALTERNATE_BUTTON_LABEL", paramCharSequence3);
            intent.setPackage(getSettingsPackageForIntent(intent));
            return intent;
          } 
          Log.e("KeyguardManager", "No persistent data block service");
          UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
          this("not supported on this device");
          throw unsupportedOperationException;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      Log.e("KeyguardManager", "Factory reset credential cannot be verified after provisioning.");
      throw new IllegalStateException("must not be provisioned yet");
    } 
    Log.w("KeyguardManager", "Factory reset credentials not supported.");
    throw new UnsupportedOperationException("not supported on this device");
  }
  
  @Deprecated
  public void exitKeyguardSecurely(OnKeyguardExitResult paramOnKeyguardExitResult) {
    try {
      IWindowManager iWindowManager = this.mWM;
      IOnKeyguardExitResult.Stub stub = new IOnKeyguardExitResult.Stub() {
          public void onKeyguardExitResult(boolean param1Boolean) throws RemoteException {
            KeyguardManager.OnKeyguardExitResult onKeyguardExitResult = callback;
            if (onKeyguardExitResult != null)
              onKeyguardExitResult.onKeyguardExitResult(param1Boolean); 
          }
        };
      super(this, paramOnKeyguardExitResult);
      iWindowManager.exitKeyguardSecurely((IOnKeyguardExitResult)stub);
    } catch (RemoteException remoteException) {}
  }
  
  @SystemApi
  public int getMinLockLength(boolean paramBoolean, int paramInt) {
    if (!checkInitialLockMethodUsage())
      return -1; 
    paramInt = PasswordMetrics.sanitizeComplexityLevel(paramInt);
    PasswordMetrics passwordMetrics = ((DevicePolicyManager)this.mContext.getSystemService("device_policy")).getPasswordMinimumMetrics(this.mContext.getUserId());
    return (PasswordMetrics.applyComplexity(passwordMetrics, paramBoolean, paramInt)).length;
  }
  
  @SystemApi
  public boolean getPrivateNotificationsAllowed() {
    try {
      return this.mNotificationManager.getPrivateNotificationsAllowed();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean inKeyguardRestrictedInputMode() {
    return isKeyguardLocked();
  }
  
  public boolean isDeviceLocked() {
    return isDeviceLocked(this.mContext.getUserId());
  }
  
  public boolean isDeviceLocked(int paramInt) {
    try {
      return this.mTrustManager.isDeviceLocked(paramInt);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isDeviceSecure() {
    return isDeviceSecure(this.mContext.getUserId());
  }
  
  public boolean isDeviceSecure(int paramInt) {
    try {
      return this.mTrustManager.isDeviceSecure(paramInt);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isKeyguardLocked() {
    try {
      return this.mWM.isKeyguardLocked();
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isKeyguardSecure() {
    try {
      return this.mWM.isKeyguardSecure(this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  @SystemApi
  public boolean isValidLockPasswordComplexity(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    boolean bool = checkInitialLockMethodUsage();
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramInt2 = PasswordMetrics.sanitizeComplexityLevel(paramInt2);
    PasswordMetrics passwordMetrics = ((DevicePolicyManager)this.mContext.getSystemService("device_policy")).getPasswordMinimumMetrics(this.mContext.getUserId());
    if (paramInt1 != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (PasswordMetrics.validatePassword(passwordMetrics, paramInt2, bool, paramArrayOfbyte).size() == 0)
      bool1 = true; 
    return bool1;
  }
  
  @Deprecated
  public KeyguardLock newKeyguardLock(String paramString) {
    return new KeyguardLock(paramString);
  }
  
  public void requestDismissKeyguard(Activity paramActivity, KeyguardDismissCallback paramKeyguardDismissCallback) {
    requestDismissKeyguard(paramActivity, null, paramKeyguardDismissCallback);
  }
  
  @SystemApi
  public void requestDismissKeyguard(Activity paramActivity, CharSequence paramCharSequence, KeyguardDismissCallback paramKeyguardDismissCallback) {
    try {
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      IBinder iBinder = paramActivity.getActivityToken();
      IKeyguardDismissCallback.Stub stub = new IKeyguardDismissCallback.Stub() {
          public void onDismissCancelled() throws RemoteException {
            if (callback != null && !activity.isDestroyed()) {
              Handler handler = activity.mHandler;
              KeyguardManager.KeyguardDismissCallback keyguardDismissCallback = callback;
              Objects.requireNonNull(keyguardDismissCallback);
              handler.post(new _$$Lambda$KlsE01yvVI54Xvdo0TIjyhUKWHQ(keyguardDismissCallback));
            } 
          }
          
          public void onDismissError() throws RemoteException {
            if (callback != null && !activity.isDestroyed()) {
              Handler handler = activity.mHandler;
              KeyguardManager.KeyguardDismissCallback keyguardDismissCallback = callback;
              Objects.requireNonNull(keyguardDismissCallback);
              handler.post(new _$$Lambda$rztNj2LGZZegxvT34NFbOqZrZHM(keyguardDismissCallback));
            } 
          }
          
          public void onDismissSucceeded() throws RemoteException {
            if (callback != null && !activity.isDestroyed()) {
              Handler handler = activity.mHandler;
              KeyguardManager.KeyguardDismissCallback keyguardDismissCallback = callback;
              Objects.requireNonNull(keyguardDismissCallback);
              handler.post(new _$$Lambda$YTMEV7TmbMrzjIag59qAffcsEUw(keyguardDismissCallback));
            } 
          }
        };
      super(this, paramKeyguardDismissCallback, paramActivity);
      iActivityTaskManager.dismissKeyguard(iBinder, (IKeyguardDismissCallback)stub, paramCharSequence);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean setLock(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    if (!checkInitialLockMethodUsage())
      return false; 
    LockPatternUtils lockPatternUtils = new LockPatternUtils(this.mContext);
    int i = this.mContext.getUserId();
    if (isDeviceSecure(i)) {
      Log.e("KeyguardManager", "Password already set, rejecting call to setLock");
      return false;
    } 
    if (!isValidLockPasswordComplexity(paramInt1, paramArrayOfbyte, paramInt2)) {
      Log.e("KeyguardManager", "Password is not valid, rejecting call to setLock");
      return false;
    } 
    boolean bool = false;
    if (paramInt1 != 0) {
      if (paramInt1 != 1) {
        if (paramInt1 != 2) {
          try {
            Log.e("KeyguardManager", "Unknown lock type, returning a failure");
            Arrays.fill(paramArrayOfbyte, (byte)0);
          } catch (Exception exception) {
            Log.e("KeyguardManager", "Save lock exception", exception);
            bool = false;
            Arrays.fill(paramArrayOfbyte, (byte)0);
          } finally {}
        } else {
          List list = LockPatternUtils.byteArrayToPattern(paramArrayOfbyte);
          lockPatternUtils.setLockCredential(LockscreenCredential.createPattern(list), LockscreenCredential.createNone(), i);
          list.clear();
          bool = true;
          Arrays.fill(paramArrayOfbyte, (byte)0);
        } 
      } else {
        String str = new String();
        this(paramArrayOfbyte);
        lockPatternUtils.setLockCredential(LockscreenCredential.createPin(str), LockscreenCredential.createNone(), i);
        bool = true;
        Arrays.fill(paramArrayOfbyte, (byte)0);
      } 
    } else {
      String str = new String();
      this(paramArrayOfbyte, Charset.forName("UTF-8"));
      lockPatternUtils.setLockCredential(LockscreenCredential.createPassword(str), LockscreenCredential.createNone(), i);
      bool = true;
      Arrays.fill(paramArrayOfbyte, (byte)0);
    } 
    return bool;
  }
  
  @SystemApi
  public void setPrivateNotificationsAllowed(boolean paramBoolean) {
    try {
      this.mNotificationManager.setPrivateNotificationsAllowed(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static abstract class KeyguardDismissCallback {
    public void onDismissCancelled() {}
    
    public void onDismissError() {}
    
    public void onDismissSucceeded() {}
  }
  
  @Deprecated
  public class KeyguardLock {
    private final String mTag;
    
    private final IBinder mToken = (IBinder)new Binder();
    
    KeyguardLock(String param1String) {
      this.mTag = param1String;
    }
    
    public void disableKeyguard() {
      try {
        KeyguardManager.this.mWM.disableKeyguard(this.mToken, this.mTag, KeyguardManager.this.mContext.getUserId());
      } catch (RemoteException remoteException) {}
    }
    
    public void reenableKeyguard() {
      try {
        KeyguardManager.this.mWM.reenableKeyguard(this.mToken, KeyguardManager.this.mContext.getUserId());
      } catch (RemoteException remoteException) {}
    }
  }
  
  static @interface LockTypes {
    public static final int PASSWORD = 0;
    
    public static final int PATTERN = 2;
    
    public static final int PIN = 1;
  }
  
  @Deprecated
  public static interface OnKeyguardExitResult {
    void onKeyguardExitResult(boolean param1Boolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/KeyguardManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */