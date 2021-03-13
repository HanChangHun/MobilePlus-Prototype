package android.accounts;

import android.annotation.SystemApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.collect.Maps;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AccountManager {
  public static final String ACCOUNT_ACCESS_TOKEN_TYPE = "com.android.AccountManager.ACCOUNT_ACCESS_TOKEN_TYPE";
  
  public static final String ACTION_ACCOUNT_REMOVED = "android.accounts.action.ACCOUNT_REMOVED";
  
  public static final String ACTION_AUTHENTICATOR_INTENT = "android.accounts.AccountAuthenticator";
  
  public static final String ACTION_VISIBLE_ACCOUNTS_CHANGED = "android.accounts.action.VISIBLE_ACCOUNTS_CHANGED";
  
  public static final String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";
  
  public static final String AUTHENTICATOR_META_DATA_NAME = "android.accounts.AccountAuthenticator";
  
  public static final int ERROR_CODE_BAD_ARGUMENTS = 7;
  
  public static final int ERROR_CODE_BAD_AUTHENTICATION = 9;
  
  public static final int ERROR_CODE_BAD_REQUEST = 8;
  
  public static final int ERROR_CODE_CANCELED = 4;
  
  public static final int ERROR_CODE_INVALID_RESPONSE = 5;
  
  public static final int ERROR_CODE_MANAGEMENT_DISABLED_FOR_ACCOUNT_TYPE = 101;
  
  public static final int ERROR_CODE_NETWORK_ERROR = 3;
  
  public static final int ERROR_CODE_REMOTE_EXCEPTION = 1;
  
  public static final int ERROR_CODE_UNSUPPORTED_OPERATION = 6;
  
  public static final int ERROR_CODE_USER_RESTRICTED = 100;
  
  public static final String KEY_ACCOUNTS = "accounts";
  
  public static final String KEY_ACCOUNT_ACCESS_ID = "accountAccessId";
  
  public static final String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE = "accountAuthenticatorResponse";
  
  public static final String KEY_ACCOUNT_MANAGER_RESPONSE = "accountManagerResponse";
  
  public static final String KEY_ACCOUNT_NAME = "authAccount";
  
  public static final String KEY_ACCOUNT_SESSION_BUNDLE = "accountSessionBundle";
  
  public static final String KEY_ACCOUNT_STATUS_TOKEN = "accountStatusToken";
  
  public static final String KEY_ACCOUNT_TYPE = "accountType";
  
  public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
  
  public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
  
  public static final String KEY_AUTHTOKEN = "authtoken";
  
  public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
  
  public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
  
  public static final String KEY_BOOLEAN_RESULT = "booleanResult";
  
  public static final String KEY_CALLER_PID = "callerPid";
  
  public static final String KEY_CALLER_UID = "callerUid";
  
  public static final String KEY_ERROR_CODE = "errorCode";
  
  public static final String KEY_ERROR_MESSAGE = "errorMessage";
  
  public static final String KEY_INTENT = "intent";
  
  public static final String KEY_LAST_AUTHENTICATED_TIME = "lastAuthenticatedTime";
  
  public static final String KEY_NOTIFY_ON_FAILURE = "notifyOnAuthFailure";
  
  public static final String KEY_PASSWORD = "password";
  
  public static final String KEY_USERDATA = "userdata";
  
  public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_CHANGED";
  
  public static final String PACKAGE_NAME_KEY_LEGACY_NOT_VISIBLE = "android:accounts:key_legacy_not_visible";
  
  public static final String PACKAGE_NAME_KEY_LEGACY_VISIBLE = "android:accounts:key_legacy_visible";
  
  private static final String TAG = "AccountManager";
  
  public static final int VISIBILITY_NOT_VISIBLE = 3;
  
  public static final int VISIBILITY_UNDEFINED = 0;
  
  public static final int VISIBILITY_USER_MANAGED_NOT_VISIBLE = 4;
  
  public static final int VISIBILITY_USER_MANAGED_VISIBLE = 2;
  
  public static final int VISIBILITY_VISIBLE = 1;
  
  private final BroadcastReceiver mAccountsChangedBroadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        null = AccountManager.this.getAccounts();
        synchronized (AccountManager.this.mAccountsUpdatedListeners) {
          for (Map.Entry entry : AccountManager.this.mAccountsUpdatedListeners.entrySet())
            AccountManager.this.postToHandler((Handler)entry.getValue(), (OnAccountsUpdateListener)entry.getKey(), null); 
          return;
        } 
      }
    };
  
  private final HashMap<OnAccountsUpdateListener, Handler> mAccountsUpdatedListeners = Maps.newHashMap();
  
  private final HashMap<OnAccountsUpdateListener, Set<String>> mAccountsUpdatedListenersTypes = Maps.newHashMap();
  
  private final Context mContext;
  
  private final Handler mMainHandler;
  
  private final IAccountManager mService;
  
  public AccountManager(Context paramContext, IAccountManager paramIAccountManager) {
    this.mContext = paramContext;
    this.mService = paramIAccountManager;
    this.mMainHandler = new Handler(this.mContext.getMainLooper());
  }
  
  public AccountManager(Context paramContext, IAccountManager paramIAccountManager, Handler paramHandler) {
    this.mContext = paramContext;
    this.mService = paramIAccountManager;
    this.mMainHandler = paramHandler;
  }
  
  private Exception convertErrorToException(int paramInt, String paramString) {
    return (Exception)((paramInt == 3) ? new IOException(paramString) : ((paramInt == 6) ? new UnsupportedOperationException(paramString) : ((paramInt == 5) ? new AuthenticatorException(paramString) : ((paramInt == 7) ? new IllegalArgumentException(paramString) : new AuthenticatorException(paramString)))));
  }
  
  private void ensureNotOnMainThread() {
    Looper looper = Looper.myLooper();
    if (looper != null && looper == this.mContext.getMainLooper()) {
      IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
      Log.e("AccountManager", "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
      if ((this.mContext.getApplicationInfo()).targetSdkVersion >= 8)
        throw illegalStateException; 
    } 
  }
  
  public static AccountManager get(Context paramContext) {
    if (paramContext != null)
      return (AccountManager)paramContext.getSystemService("account"); 
    throw new IllegalArgumentException("context is null");
  }
  
  private void getAccountByTypeAndFeatures(final String accountType, final String[] features, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    (new AmsTask(null, paramHandler, paramAccountManagerCallback) {
        public void doWork() throws RemoteException {
          AccountManager.this.mService.getAccountByTypeAndFeatures(this.mResponse, accountType, features, AccountManager.this.mContext.getOpPackageName());
        }
      }).start();
  }
  
  @Deprecated
  public static Intent newChooseAccountIntent(Account paramAccount, ArrayList<Account> paramArrayList, String[] paramArrayOfString1, boolean paramBoolean, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle) {
    return newChooseAccountIntent(paramAccount, paramArrayList, paramArrayOfString1, paramString1, paramString2, paramArrayOfString2, paramBundle);
  }
  
  public static Intent newChooseAccountIntent(Account paramAccount, List<Account> paramList, String[] paramArrayOfString1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle) {
    Intent intent = new Intent();
    ComponentName componentName = ComponentName.unflattenFromString(Resources.getSystem().getString(17039838));
    intent.setClassName(componentName.getPackageName(), componentName.getClassName());
    if (paramList == null) {
      paramList = null;
    } else {
      paramList = new ArrayList<>(paramList);
    } 
    intent.putExtra("allowableAccounts", (Serializable)paramList);
    intent.putExtra("allowableAccountTypes", paramArrayOfString1);
    intent.putExtra("addAccountOptions", paramBundle);
    intent.putExtra("selectedAccount", paramAccount);
    intent.putExtra("descriptionTextOverride", paramString1);
    intent.putExtra("authTokenType", paramString2);
    intent.putExtra("addAccountRequiredFeatures", paramArrayOfString2);
    return intent;
  }
  
  private void postToHandler(Handler paramHandler, final AccountManagerCallback<Bundle> callback, final AccountManagerFuture<Bundle> future) {
    if (paramHandler == null)
      paramHandler = this.mMainHandler; 
    paramHandler.post(new Runnable() {
          public void run() {
            callback.run(future);
          }
        });
  }
  
  private void postToHandler(Handler paramHandler, final OnAccountsUpdateListener listener, Account[] paramArrayOfAccount) {
    final Account[] accountsCopy = new Account[paramArrayOfAccount.length];
    System.arraycopy(paramArrayOfAccount, 0, arrayOfAccount, 0, arrayOfAccount.length);
    if (paramHandler == null)
      paramHandler = this.mMainHandler; 
    paramHandler.post(new Runnable() {
          public void run() {
            HashMap hashMap = AccountManager.this.mAccountsUpdatedListeners;
            /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/HashMap}, name=null} */
            try {
              if (AccountManager.this.mAccountsUpdatedListeners.containsKey(listener)) {
                Set set = (Set)AccountManager.this.mAccountsUpdatedListenersTypes.get(listener);
                if (set != null) {
                  ArrayList<Account> arrayList = new ArrayList();
                  this();
                  for (Account account : accountsCopy) {
                    if (set.contains(account.type))
                      arrayList.add(account); 
                  } 
                  listener.onAccountsUpdated(arrayList.<Account>toArray(new Account[arrayList.size()]));
                } else {
                  listener.onAccountsUpdated(accountsCopy);
                } 
              } 
            } catch (SQLException sQLException) {
              Log.e("AccountManager", "Can't update accounts", (Throwable)sQLException);
            } finally {
              Exception exception;
            } 
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/HashMap}, name=null} */
          }
        });
  }
  
  public static Bundle sanitizeResult(Bundle paramBundle) {
    if (paramBundle != null && paramBundle.containsKey("authtoken") && !TextUtils.isEmpty(paramBundle.getString("authtoken"))) {
      paramBundle = new Bundle(paramBundle);
      paramBundle.putString("authtoken", "<omitted for logging purposes>");
      return paramBundle;
    } 
    return paramBundle;
  }
  
  public AccountManagerFuture<Bundle> addAccount(final String accountType, final String authTokenType, final String[] requiredFeatures, Bundle paramBundle, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (Process.myUserHandle().equals(this.mContext.getUser())) {
      if (accountType != null) {
        final Bundle optionsIn = new Bundle();
        if (paramBundle != null)
          bundle.putAll(paramBundle); 
        bundle.putString("androidPackageName", this.mContext.getPackageName());
        return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
            public void doWork() throws RemoteException {
              boolean bool;
              IAccountManager iAccountManager = AccountManager.this.mService;
              IAccountManagerResponse iAccountManagerResponse = this.mResponse;
              String str1 = accountType;
              String str2 = authTokenType;
              String[] arrayOfString = requiredFeatures;
              if (activity != null) {
                bool = true;
              } else {
                bool = false;
              } 
              iAccountManager.addAccount(iAccountManagerResponse, str1, str2, arrayOfString, bool, optionsIn);
            }
          }).start();
      } 
      throw new IllegalArgumentException("accountType is null");
    } 
    return addAccountAsUser(accountType, authTokenType, requiredFeatures, paramBundle, activity, paramAccountManagerCallback, paramHandler, this.mContext.getUser());
  }
  
  public AccountManagerFuture<Bundle> addAccountAsUser(final String accountType, final String authTokenType, final String[] requiredFeatures, Bundle paramBundle, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler, final UserHandle userHandle) {
    if (accountType != null) {
      if (userHandle != null) {
        final Bundle optionsIn = new Bundle();
        if (paramBundle != null)
          bundle.putAll(paramBundle); 
        bundle.putString("androidPackageName", this.mContext.getPackageName());
        return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
            public void doWork() throws RemoteException {
              boolean bool;
              IAccountManager iAccountManager = AccountManager.this.mService;
              IAccountManagerResponse iAccountManagerResponse = this.mResponse;
              String str1 = accountType;
              String str2 = authTokenType;
              String[] arrayOfString = requiredFeatures;
              if (activity != null) {
                bool = true;
              } else {
                bool = false;
              } 
              iAccountManager.addAccountAsUser(iAccountManagerResponse, str1, str2, arrayOfString, bool, optionsIn, userHandle.getIdentifier());
            }
          }).start();
      } 
      throw new IllegalArgumentException("userHandle is null");
    } 
    throw new IllegalArgumentException("accountType is null");
  }
  
  public boolean addAccountExplicitly(Account paramAccount, String paramString, Bundle paramBundle) {
    if (paramAccount != null)
      try {
        return this.mService.addAccountExplicitly(paramAccount, paramString, paramBundle);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public boolean addAccountExplicitly(Account paramAccount, String paramString, Bundle paramBundle, Map<String, Integer> paramMap) {
    if (paramAccount != null)
      try {
        return this.mService.addAccountExplicitlyWithVisibility(paramAccount, paramString, paramBundle, paramMap);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public void addOnAccountsUpdatedListener(OnAccountsUpdateListener paramOnAccountsUpdateListener, Handler paramHandler, boolean paramBoolean) {
    addOnAccountsUpdatedListener(paramOnAccountsUpdateListener, paramHandler, paramBoolean, null);
  }
  
  public void addOnAccountsUpdatedListener(OnAccountsUpdateListener paramOnAccountsUpdateListener, Handler paramHandler, boolean paramBoolean, String[] paramArrayOfString) {
    if (paramOnAccountsUpdateListener != null)
      synchronized (this.mAccountsUpdatedListeners) {
        if (!this.mAccountsUpdatedListeners.containsKey(paramOnAccountsUpdateListener)) {
          boolean bool = this.mAccountsUpdatedListeners.isEmpty();
          this.mAccountsUpdatedListeners.put(paramOnAccountsUpdateListener, paramHandler);
          if (paramArrayOfString != null) {
            HashMap<OnAccountsUpdateListener, Set<String>> hashMap = this.mAccountsUpdatedListenersTypes;
            HashSet<String> hashSet = new HashSet();
            this(Arrays.asList((E[])paramArrayOfString));
            hashMap.put(paramOnAccountsUpdateListener, hashSet);
          } else {
            this.mAccountsUpdatedListenersTypes.put(paramOnAccountsUpdateListener, null);
          } 
          if (bool) {
            IntentFilter intentFilter = new IntentFilter();
            this();
            intentFilter.addAction("android.accounts.action.VISIBLE_ACCOUNTS_CHANGED");
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
            this.mContext.registerReceiver(this.mAccountsChangedBroadcastReceiver, intentFilter);
          } 
          try {
            this.mService.registerAccountListener(paramArrayOfString, this.mContext.getOpPackageName());
            if (paramBoolean)
              postToHandler(paramHandler, paramOnAccountsUpdateListener, getAccounts()); 
            return;
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          } 
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        this("this listener is already added");
        throw illegalStateException;
      }  
    throw new IllegalArgumentException("the listener is null");
  }
  
  public void addSharedAccountsFromParentUser(UserHandle paramUserHandle1, UserHandle paramUserHandle2) {
    try {
      this.mService.addSharedAccountsFromParentUser(paramUserHandle1.getIdentifier(), paramUserHandle2.getIdentifier(), this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String blockingGetAuthToken(Account paramAccount, String paramString, boolean paramBoolean) throws OperationCanceledException, IOException, AuthenticatorException {
    if (paramAccount != null) {
      if (paramString != null) {
        StringBuilder stringBuilder;
        Bundle bundle = getAuthToken(paramAccount, paramString, paramBoolean, null, null).getResult();
        if (bundle == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("blockingGetAuthToken: null was returned from getResult() for ");
          stringBuilder.append(paramAccount);
          stringBuilder.append(", authTokenType ");
          stringBuilder.append(paramString);
          Log.e("AccountManager", stringBuilder.toString());
          return null;
        } 
        return stringBuilder.getString("authtoken");
      } 
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public void clearPassword(Account paramAccount) {
    if (paramAccount != null)
      try {
        this.mService.clearPassword(paramAccount);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public AccountManagerFuture<Bundle> confirmCredentials(Account paramAccount, Bundle paramBundle, Activity paramActivity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    return confirmCredentialsAsUser(paramAccount, paramBundle, paramActivity, paramAccountManagerCallback, paramHandler, this.mContext.getUser());
  }
  
  public AccountManagerFuture<Bundle> confirmCredentialsAsUser(final Account account, final Bundle options, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler, UserHandle paramUserHandle) {
    if (account != null)
      return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
          public void doWork() throws RemoteException {
            boolean bool;
            IAccountManager iAccountManager = AccountManager.this.mService;
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            Account account = account;
            Bundle bundle = options;
            if (activity != null) {
              bool = true;
            } else {
              bool = false;
            } 
            iAccountManager.confirmCredentialsAsUser(iAccountManagerResponse, account, bundle, bool, userId);
          }
        }).start(); 
    throw new IllegalArgumentException("account is null");
  }
  
  public AccountManagerFuture<Boolean> copyAccountToUser(final Account account, final UserHandle fromUser, final UserHandle toUser, AccountManagerCallback<Boolean> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      if (toUser != null && fromUser != null)
        return (new Future2Task<Boolean>(paramHandler, paramAccountManagerCallback) {
            public Boolean bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
              if (param1Bundle.containsKey("booleanResult"))
                return Boolean.valueOf(param1Bundle.getBoolean("booleanResult")); 
              throw new AuthenticatorException("no result in response");
            }
            
            public void doWork() throws RemoteException {
              AccountManager.this.mService.copyAccountToUser(this.mResponse, account, fromUser.getIdentifier(), toUser.getIdentifier());
            }
          }).start(); 
      throw new IllegalArgumentException("fromUser and toUser cannot be null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public IntentSender createRequestAccountAccessIntentSenderAsUser(Account paramAccount, String paramString, UserHandle paramUserHandle) {
    try {
      return this.mService.createRequestAccountAccessIntentSenderAsUser(paramAccount, paramString, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AccountManagerFuture<Bundle> editProperties(final String accountType, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (accountType != null)
      return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
          public void doWork() throws RemoteException {
            boolean bool;
            IAccountManager iAccountManager = AccountManager.this.mService;
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            String str = accountType;
            if (activity != null) {
              bool = true;
            } else {
              bool = false;
            } 
            iAccountManager.editProperties(iAccountManagerResponse, str, bool);
          }
        }).start(); 
    throw new IllegalArgumentException("accountType is null");
  }
  
  public AccountManagerFuture<Bundle> finishSession(Bundle paramBundle, Activity paramActivity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    return finishSessionAsUser(paramBundle, paramActivity, this.mContext.getUser(), paramAccountManagerCallback, paramHandler);
  }
  
  @SystemApi
  public AccountManagerFuture<Bundle> finishSessionAsUser(final Bundle sessionBundle, final Activity activity, final UserHandle userHandle, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (sessionBundle != null) {
      final Bundle appInfo = new Bundle();
      bundle.putString("androidPackageName", this.mContext.getPackageName());
      return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
          public void doWork() throws RemoteException {
            boolean bool;
            IAccountManager iAccountManager = AccountManager.this.mService;
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            Bundle bundle = sessionBundle;
            if (activity != null) {
              bool = true;
            } else {
              bool = false;
            } 
            iAccountManager.finishSessionAsUser(iAccountManagerResponse, bundle, bool, appInfo, userHandle.getIdentifier());
          }
        }).start();
    } 
    throw new IllegalArgumentException("sessionBundle is null");
  }
  
  public int getAccountVisibility(Account paramAccount, String paramString) {
    if (paramAccount != null)
      try {
        return this.mService.getAccountVisibility(paramAccount, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public Account[] getAccounts() {
    return getAccountsAsUser(this.mContext.getUserId());
  }
  
  public Map<Account, Integer> getAccountsAndVisibilityForPackage(String paramString1, String paramString2) {
    try {
      return this.mService.getAccountsAndVisibilityForPackage(paramString1, paramString2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Account[] getAccountsAsUser(int paramInt) {
    try {
      return this.mService.getAccountsAsUser(null, paramInt, this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Account[] getAccountsByType(String paramString) {
    return getAccountsByTypeAsUser(paramString, this.mContext.getUser());
  }
  
  public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(final String type, final String[] features, AccountManagerCallback<Account[]> paramAccountManagerCallback, Handler paramHandler) {
    if (type != null)
      return (AccountManagerFuture)(new Future2Task<Account[]>(paramHandler, paramAccountManagerCallback) {
          public Account[] bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
            if (param1Bundle.containsKey("accounts")) {
              Parcelable[] arrayOfParcelable = param1Bundle.getParcelableArray("accounts");
              Account[] arrayOfAccount = new Account[arrayOfParcelable.length];
              for (byte b = 0; b < arrayOfParcelable.length; b++)
                arrayOfAccount[b] = (Account)arrayOfParcelable[b]; 
              return arrayOfAccount;
            } 
            throw new AuthenticatorException("no result in response");
          }
          
          public void doWork() throws RemoteException {
            AccountManager.this.mService.getAccountsByFeatures(this.mResponse, type, features, AccountManager.this.mContext.getOpPackageName());
          }
        }).start(); 
    throw new IllegalArgumentException("type is null");
  }
  
  public Account[] getAccountsByTypeAsUser(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mService.getAccountsAsUser(paramString, paramUserHandle.getIdentifier(), this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Account[] getAccountsByTypeForPackage(String paramString1, String paramString2) {
    try {
      return this.mService.getAccountsByTypeForPackage(paramString1, paramString2, this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Account[] getAccountsForPackage(String paramString, int paramInt) {
    try {
      return this.mService.getAccountsForPackage(paramString, paramInt, this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AccountManagerFuture<Bundle> getAuthToken(final Account account, final String authTokenType, Bundle paramBundle, Activity paramActivity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      if (authTokenType != null) {
        final Bundle optionsIn = new Bundle();
        if (paramBundle != null)
          bundle.putAll(paramBundle); 
        bundle.putString("androidPackageName", this.mContext.getPackageName());
        return (new AmsTask(paramActivity, paramHandler, paramAccountManagerCallback) {
            public void doWork() throws RemoteException {
              AccountManager.this.mService.getAuthToken(this.mResponse, account, authTokenType, false, true, optionsIn);
            }
          }).start();
      } 
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public AccountManagerFuture<Bundle> getAuthToken(final Account account, final String authTokenType, Bundle paramBundle, final boolean notifyAuthFailure, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      if (authTokenType != null) {
        final Bundle optionsIn = new Bundle();
        if (paramBundle != null)
          bundle.putAll(paramBundle); 
        bundle.putString("androidPackageName", this.mContext.getPackageName());
        return (new AmsTask(null, paramHandler, paramAccountManagerCallback) {
            public void doWork() throws RemoteException {
              AccountManager.this.mService.getAuthToken(this.mResponse, account, authTokenType, notifyAuthFailure, false, optionsIn);
            }
          }).start();
      } 
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  @Deprecated
  public AccountManagerFuture<Bundle> getAuthToken(Account paramAccount, String paramString, boolean paramBoolean, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    return getAuthToken(paramAccount, paramString, (Bundle)null, paramBoolean, paramAccountManagerCallback, paramHandler);
  }
  
  public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String paramString1, String paramString2, String[] paramArrayOfString, Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (paramString1 != null) {
      if (paramString2 != null) {
        GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = new GetAuthTokenByTypeAndFeaturesTask(paramString1, paramString2, paramArrayOfString, paramActivity, paramBundle1, paramBundle2, paramAccountManagerCallback, paramHandler);
        getAuthTokenByTypeAndFeaturesTask.start();
        return getAuthTokenByTypeAndFeaturesTask;
      } 
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("account type is null");
  }
  
  public AccountManagerFuture<String> getAuthTokenLabel(final String accountType, final String authTokenType, AccountManagerCallback<String> paramAccountManagerCallback, Handler paramHandler) {
    if (accountType != null) {
      if (authTokenType != null)
        return (new Future2Task<String>(paramHandler, paramAccountManagerCallback) {
            public String bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
              if (param1Bundle.containsKey("authTokenLabelKey"))
                return param1Bundle.getString("authTokenLabelKey"); 
              throw new AuthenticatorException("no result in response");
            }
            
            public void doWork() throws RemoteException {
              AccountManager.this.mService.getAuthTokenLabel(this.mResponse, accountType, authTokenType);
            }
          }).start(); 
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("accountType is null");
  }
  
  public AuthenticatorDescription[] getAuthenticatorTypes() {
    return getAuthenticatorTypesAsUser(this.mContext.getUserId());
  }
  
  public AuthenticatorDescription[] getAuthenticatorTypesAsUser(int paramInt) {
    try {
      return this.mService.getAuthenticatorTypes(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Map<String, Integer> getPackagesAndVisibilityForAccount(Account paramAccount) {
    if (paramAccount != null)
      try {
        return this.mService.getPackagesAndVisibilityForAccount(paramAccount);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
    this("account is null");
    throw illegalArgumentException;
  }
  
  public String getPassword(Account paramAccount) {
    if (paramAccount != null)
      try {
        return this.mService.getPassword(paramAccount);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public String getPreviousName(Account paramAccount) {
    if (paramAccount != null)
      try {
        return this.mService.getPreviousName(paramAccount);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public String getUserData(Account paramAccount, String paramString) {
    if (paramAccount != null) {
      if (paramString != null)
        try {
          return this.mService.getUserData(paramAccount, paramString);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("key is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public boolean hasAccountAccess(Account paramAccount, String paramString, UserHandle paramUserHandle) {
    try {
      return this.mService.hasAccountAccess(paramAccount, paramString, paramUserHandle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AccountManagerFuture<Boolean> hasFeatures(final Account account, final String[] features, AccountManagerCallback<Boolean> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      if (features != null)
        return (new Future2Task<Boolean>(paramHandler, paramAccountManagerCallback) {
            public Boolean bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
              if (param1Bundle.containsKey("booleanResult"))
                return Boolean.valueOf(param1Bundle.getBoolean("booleanResult")); 
              throw new AuthenticatorException("no result in response");
            }
            
            public void doWork() throws RemoteException {
              AccountManager.this.mService.hasFeatures(this.mResponse, account, features, AccountManager.this.mContext.getOpPackageName());
            }
          }).start(); 
      throw new IllegalArgumentException("features is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public void invalidateAuthToken(String paramString1, String paramString2) {
    if (paramString1 != null) {
      if (paramString2 != null)
        try {
          this.mService.invalidateAuthToken(paramString1, paramString2);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
    throw new IllegalArgumentException("accountType is null");
  }
  
  public AccountManagerFuture<Boolean> isCredentialsUpdateSuggested(final Account account, final String statusToken, AccountManagerCallback<Boolean> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      if (!TextUtils.isEmpty(statusToken))
        return (new Future2Task<Boolean>(paramHandler, paramAccountManagerCallback) {
            public Boolean bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
              if (param1Bundle.containsKey("booleanResult"))
                return Boolean.valueOf(param1Bundle.getBoolean("booleanResult")); 
              throw new AuthenticatorException("no result in response");
            }
            
            public void doWork() throws RemoteException {
              AccountManager.this.mService.isCredentialsUpdateSuggested(this.mResponse, account, statusToken);
            }
          }).start(); 
      throw new IllegalArgumentException("status token is empty");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public boolean notifyAccountAuthenticated(Account paramAccount) {
    if (paramAccount != null)
      try {
        return this.mService.accountAuthenticated(paramAccount);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public String peekAuthToken(Account paramAccount, String paramString) {
    if (paramAccount != null) {
      if (paramString != null)
        try {
          return this.mService.peekAuthToken(paramAccount, paramString);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  @Deprecated
  public AccountManagerFuture<Boolean> removeAccount(Account paramAccount, AccountManagerCallback<Boolean> paramAccountManagerCallback, Handler paramHandler) {
    return removeAccountAsUser(paramAccount, paramAccountManagerCallback, paramHandler, this.mContext.getUser());
  }
  
  public AccountManagerFuture<Bundle> removeAccount(Account paramAccount, Activity paramActivity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    return removeAccountAsUser(paramAccount, paramActivity, paramAccountManagerCallback, paramHandler, this.mContext.getUser());
  }
  
  @Deprecated
  public AccountManagerFuture<Boolean> removeAccountAsUser(final Account account, AccountManagerCallback<Boolean> paramAccountManagerCallback, Handler paramHandler, final UserHandle userHandle) {
    if (account != null) {
      if (userHandle != null)
        return (new Future2Task<Boolean>(paramHandler, paramAccountManagerCallback) {
            public Boolean bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
              if (param1Bundle.containsKey("booleanResult"))
                return Boolean.valueOf(param1Bundle.getBoolean("booleanResult")); 
              throw new AuthenticatorException("no result in response");
            }
            
            public void doWork() throws RemoteException {
              AccountManager.this.mService.removeAccountAsUser(this.mResponse, account, false, userHandle.getIdentifier());
            }
          }).start(); 
      throw new IllegalArgumentException("userHandle is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public AccountManagerFuture<Bundle> removeAccountAsUser(final Account account, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler, final UserHandle userHandle) {
    if (account != null) {
      if (userHandle != null)
        return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
            public void doWork() throws RemoteException {
              boolean bool;
              IAccountManager iAccountManager = AccountManager.this.mService;
              IAccountManagerResponse iAccountManagerResponse = this.mResponse;
              Account account = account;
              if (activity != null) {
                bool = true;
              } else {
                bool = false;
              } 
              iAccountManager.removeAccountAsUser(iAccountManagerResponse, account, bool, userHandle.getIdentifier());
            }
          }).start(); 
      throw new IllegalArgumentException("userHandle is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public boolean removeAccountExplicitly(Account paramAccount) {
    if (paramAccount != null)
      try {
        return this.mService.removeAccountExplicitly(paramAccount);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener paramOnAccountsUpdateListener) {
    if (paramOnAccountsUpdateListener != null)
      synchronized (this.mAccountsUpdatedListeners) {
        if (!this.mAccountsUpdatedListeners.containsKey(paramOnAccountsUpdateListener)) {
          Log.e("AccountManager", "Listener was not previously added");
          return;
        } 
        Set set = this.mAccountsUpdatedListenersTypes.get(paramOnAccountsUpdateListener);
        if (set != null) {
          String[] arrayOfString = (String[])set.toArray((Object[])new String[set.size()]);
        } else {
          set = null;
        } 
        this.mAccountsUpdatedListeners.remove(paramOnAccountsUpdateListener);
        this.mAccountsUpdatedListenersTypes.remove(paramOnAccountsUpdateListener);
        if (this.mAccountsUpdatedListeners.isEmpty())
          this.mContext.unregisterReceiver(this.mAccountsChangedBroadcastReceiver); 
        try {
          this.mService.unregisterAccountListener((String[])set, this.mContext.getOpPackageName());
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      }  
    throw new IllegalArgumentException("listener is null");
  }
  
  public AccountManagerFuture<Account> renameAccount(final Account account, final String newName, AccountManagerCallback<Account> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      if (!TextUtils.isEmpty(newName))
        return (new Future2Task<Account>(paramHandler, paramAccountManagerCallback) {
            public Account bundleToResult(Bundle param1Bundle) throws AuthenticatorException {
              return new Account(param1Bundle.getString("authAccount"), param1Bundle.getString("accountType"), param1Bundle.getString("accountAccessId"));
            }
            
            public void doWork() throws RemoteException {
              AccountManager.this.mService.renameAccount(this.mResponse, account, newName);
            }
          }).start(); 
      throw new IllegalArgumentException("newName is empty or null.");
    } 
    throw new IllegalArgumentException("account is null.");
  }
  
  public boolean setAccountVisibility(Account paramAccount, String paramString, int paramInt) {
    if (paramAccount != null)
      try {
        return this.mService.setAccountVisibility(paramAccount, paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public void setAuthToken(Account paramAccount, String paramString1, String paramString2) {
    if (paramAccount != null) {
      if (paramString1 != null)
        try {
          this.mService.setAuthToken(paramAccount, paramString1, paramString2);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("authTokenType is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public void setPassword(Account paramAccount, String paramString) {
    if (paramAccount != null)
      try {
        this.mService.setPassword(paramAccount, paramString);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("account is null");
  }
  
  public void setUserData(Account paramAccount, String paramString1, String paramString2) {
    if (paramAccount != null) {
      if (paramString1 != null)
        try {
          this.mService.setUserData(paramAccount, paramString1, paramString2);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("key is null");
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public boolean someUserHasAccount(Account paramAccount) {
    try {
      return this.mService.someUserHasAccount(paramAccount);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AccountManagerFuture<Bundle> startAddAccountSession(final String accountType, final String authTokenType, final String[] requiredFeatures, Bundle paramBundle, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (accountType != null) {
      final Bundle optionsIn = new Bundle();
      if (paramBundle != null)
        bundle.putAll(paramBundle); 
      bundle.putString("androidPackageName", this.mContext.getPackageName());
      return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
          public void doWork() throws RemoteException {
            boolean bool;
            IAccountManager iAccountManager = AccountManager.this.mService;
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            String str1 = accountType;
            String str2 = authTokenType;
            String[] arrayOfString = requiredFeatures;
            if (activity != null) {
              bool = true;
            } else {
              bool = false;
            } 
            iAccountManager.startAddAccountSession(iAccountManagerResponse, str1, str2, arrayOfString, bool, optionsIn);
          }
        }).start();
    } 
    throw new IllegalArgumentException("accountType is null");
  }
  
  public AccountManagerFuture<Bundle> startUpdateCredentialsSession(final Account account, final String authTokenType, Bundle paramBundle, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null) {
      final Bundle optionsIn = new Bundle();
      if (paramBundle != null)
        bundle.putAll(paramBundle); 
      bundle.putString("androidPackageName", this.mContext.getPackageName());
      return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
          public void doWork() throws RemoteException {
            boolean bool;
            IAccountManager iAccountManager = AccountManager.this.mService;
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            Account account = account;
            String str = authTokenType;
            if (activity != null) {
              bool = true;
            } else {
              bool = false;
            } 
            iAccountManager.startUpdateCredentialsSession(iAccountManagerResponse, account, str, bool, optionsIn);
          }
        }).start();
    } 
    throw new IllegalArgumentException("account is null");
  }
  
  public void updateAppPermission(Account paramAccount, String paramString, int paramInt, boolean paramBoolean) {
    try {
      this.mService.updateAppPermission(paramAccount, paramString, paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AccountManagerFuture<Bundle> updateCredentials(final Account account, final String authTokenType, final Bundle options, final Activity activity, AccountManagerCallback<Bundle> paramAccountManagerCallback, Handler paramHandler) {
    if (account != null)
      return (new AmsTask(activity, paramHandler, paramAccountManagerCallback) {
          public void doWork() throws RemoteException {
            boolean bool;
            IAccountManager iAccountManager = AccountManager.this.mService;
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            Account account = account;
            String str = authTokenType;
            if (activity != null) {
              bool = true;
            } else {
              bool = false;
            } 
            iAccountManager.updateCredentials(iAccountManagerResponse, account, str, bool, options);
          }
        }).start(); 
    throw new IllegalArgumentException("account is null");
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AccountVisibility {}
  
  private abstract class AmsTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
    final Activity mActivity;
    
    final AccountManagerCallback<Bundle> mCallback;
    
    final Handler mHandler;
    
    final IAccountManagerResponse mResponse;
    
    public AmsTask(Activity param1Activity, Handler param1Handler, AccountManagerCallback<Bundle> param1AccountManagerCallback) {
      super(new Callable<Bundle>(AccountManager.this) {
            public Bundle call() throws Exception {
              throw new IllegalStateException("this should never be called");
            }
          });
      this.mHandler = param1Handler;
      this.mCallback = param1AccountManagerCallback;
      this.mActivity = param1Activity;
      this.mResponse = new Response();
    }
    
    private Bundle internalGetResult(Long param1Long, TimeUnit param1TimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
      Bundle bundle;
      AuthenticatorException authenticatorException;
      if (!isDone())
        AccountManager.this.ensureNotOnMainThread(); 
      if (param1Long == null) {
        try {
          bundle = get();
          cancel(true);
          return bundle;
        } catch (CancellationException cancellationException) {
          OperationCanceledException operationCanceledException = new OperationCanceledException();
          this();
          throw operationCanceledException;
        } catch (TimeoutException timeoutException) {
          cancel(true);
          throw new OperationCanceledException();
        } catch (InterruptedException interruptedException) {
          cancel(true);
          throw new OperationCanceledException();
        } catch (ExecutionException executionException) {
          Throwable throwable = executionException.getCause();
          if (!(throwable instanceof IOException)) {
            if (!(throwable instanceof UnsupportedOperationException)) {
              if (!(throwable instanceof AuthenticatorException)) {
                if (!(throwable instanceof RuntimeException)) {
                  if (throwable instanceof Error)
                    throw (Error)throwable; 
                  IllegalStateException illegalStateException = new IllegalStateException();
                  this(throwable);
                  throw illegalStateException;
                } 
                throw (RuntimeException)throwable;
              } 
              throw (AuthenticatorException)throwable;
            } 
            authenticatorException = new AuthenticatorException();
            this(throwable);
            throw authenticatorException;
          } 
          throw (IOException)throwable;
        } finally {}
      } else {
        bundle = get(param1Long.longValue(), (TimeUnit)authenticatorException);
        cancel(true);
        return bundle;
      } 
      cancel(true);
      throw bundle;
    }
    
    public abstract void doWork() throws RemoteException;
    
    protected void done() {
      AccountManagerCallback<Bundle> accountManagerCallback = this.mCallback;
      if (accountManagerCallback != null)
        AccountManager.this.postToHandler(this.mHandler, accountManagerCallback, this); 
    }
    
    public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
      return internalGetResult((Long)null, (TimeUnit)null);
    }
    
    public Bundle getResult(long param1Long, TimeUnit param1TimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
      return internalGetResult(Long.valueOf(param1Long), param1TimeUnit);
    }
    
    protected void set(Bundle param1Bundle) {
      if (param1Bundle == null)
        Log.e("AccountManager", "the bundle must not be null", new Exception()); 
      super.set(param1Bundle);
    }
    
    public final AccountManagerFuture<Bundle> start() {
      try {
        doWork();
      } catch (RemoteException remoteException) {
        setException((Throwable)remoteException);
      } 
      return this;
    }
    
    private class Response extends IAccountManagerResponse.Stub {
      private Response() {}
      
      public void onError(int param2Int, String param2String) {
        if (param2Int == 4 || param2Int == 100 || param2Int == 101) {
          AccountManager.AmsTask.this.cancel(true);
          return;
        } 
        AccountManager.AmsTask amsTask = AccountManager.AmsTask.this;
        amsTask.setException(AccountManager.this.convertErrorToException(param2Int, param2String));
      }
      
      public void onResult(Bundle param2Bundle) {
        if (param2Bundle == null) {
          onError(5, "null bundle returned");
          return;
        } 
        Intent intent = (Intent)param2Bundle.getParcelable("intent");
        if (intent != null && AccountManager.AmsTask.this.mActivity != null) {
          AccountManager.AmsTask.this.mActivity.startActivity(intent);
        } else if (param2Bundle.getBoolean("retry")) {
          try {
            AccountManager.AmsTask.this.doWork();
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          } 
        } else {
          AccountManager.AmsTask.this.set((Bundle)remoteException);
        } 
      }
    }
  }
  
  class null implements Callable<Bundle> {
    public Bundle call() throws Exception {
      throw new IllegalStateException("this should never be called");
    }
  }
  
  private class Response extends IAccountManagerResponse.Stub {
    private Response() {}
    
    public void onError(int param1Int, String param1String) {
      if (param1Int == 4 || param1Int == 100 || param1Int == 101) {
        AccountManager.AmsTask.this.cancel(true);
        return;
      } 
      AccountManager.AmsTask amsTask = AccountManager.AmsTask.this;
      amsTask.setException(AccountManager.this.convertErrorToException(param1Int, param1String));
    }
    
    public void onResult(Bundle param1Bundle) {
      if (param1Bundle == null) {
        onError(5, "null bundle returned");
        return;
      } 
      Intent intent = (Intent)param1Bundle.getParcelable("intent");
      if (intent != null && AccountManager.AmsTask.this.mActivity != null) {
        AccountManager.AmsTask.this.mActivity.startActivity(intent);
      } else if (param1Bundle.getBoolean("retry")) {
        try {
          AccountManager.AmsTask.this.doWork();
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        } 
      } else {
        AccountManager.AmsTask.this.set((Bundle)remoteException);
      } 
    }
  }
  
  private abstract class BaseFutureTask<T> extends FutureTask<T> {
    final Handler mHandler;
    
    public final IAccountManagerResponse mResponse;
    
    public BaseFutureTask(Handler param1Handler) {
      super(new Callable<T>(AccountManager.this) {
            public T call() throws Exception {
              throw new IllegalStateException("this should never be called");
            }
          });
      this.mHandler = param1Handler;
      this.mResponse = new Response();
    }
    
    public abstract T bundleToResult(Bundle param1Bundle) throws AuthenticatorException;
    
    public abstract void doWork() throws RemoteException;
    
    protected void postRunnableToHandler(Runnable param1Runnable) {
      Handler handler1 = this.mHandler;
      Handler handler2 = handler1;
      if (handler1 == null)
        handler2 = AccountManager.this.mMainHandler; 
      handler2.post(param1Runnable);
    }
    
    protected void startTask() {
      try {
        doWork();
      } catch (RemoteException remoteException) {
        setException((Throwable)remoteException);
      } 
    }
    
    protected class Response extends IAccountManagerResponse.Stub {
      public void onError(int param2Int, String param2String) {
        if (param2Int == 4 || param2Int == 100 || param2Int == 101) {
          AccountManager.BaseFutureTask.this.cancel(true);
          return;
        } 
        AccountManager.BaseFutureTask baseFutureTask = AccountManager.BaseFutureTask.this;
        baseFutureTask.setException(AccountManager.this.convertErrorToException(param2Int, param2String));
      }
      
      public void onResult(Bundle param2Bundle) {
        try {
          param2Bundle = AccountManager.BaseFutureTask.this.bundleToResult(param2Bundle);
          if (param2Bundle == null)
            return; 
          AccountManager.BaseFutureTask.this.set((T)param2Bundle);
          return;
        } catch (ClassCastException classCastException) {
        
        } catch (AuthenticatorException authenticatorException) {}
        onError(5, "no result in response");
      }
    }
  }
  
  class null implements Callable<T> {
    public T call() throws Exception {
      throw new IllegalStateException("this should never be called");
    }
  }
  
  protected class Response extends IAccountManagerResponse.Stub {
    public void onError(int param1Int, String param1String) {
      if (param1Int == 4 || param1Int == 100 || param1Int == 101) {
        this.this$1.cancel(true);
        return;
      } 
      AccountManager.BaseFutureTask baseFutureTask = this.this$1;
      baseFutureTask.setException(AccountManager.this.convertErrorToException(param1Int, param1String));
    }
    
    public void onResult(Bundle param1Bundle) {
      try {
        param1Bundle = this.this$1.bundleToResult(param1Bundle);
        if (param1Bundle == null)
          return; 
        this.this$1.set((T)param1Bundle);
        return;
      } catch (ClassCastException classCastException) {
      
      } catch (AuthenticatorException authenticatorException) {}
      onError(5, "no result in response");
    }
  }
  
  private abstract class Future2Task<T> extends BaseFutureTask<T> implements AccountManagerFuture<T> {
    final AccountManagerCallback<T> mCallback;
    
    public Future2Task(Handler param1Handler, AccountManagerCallback<T> param1AccountManagerCallback) {
      super(param1Handler);
      this.mCallback = param1AccountManagerCallback;
    }
    
    private T internalGetResult(Long param1Long, TimeUnit param1TimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
      AuthenticatorException authenticatorException;
      if (!isDone())
        AccountManager.this.ensureNotOnMainThread(); 
      if (param1Long == null) {
        try {
          param1Long = (Long)get();
          return (T)param1Long;
        } catch (InterruptedException interruptedException) {
        
        } catch (TimeoutException timeoutException) {
        
        } catch (CancellationException cancellationException) {
        
        } catch (ExecutionException executionException) {
          Throwable throwable = executionException.getCause();
          if (!(throwable instanceof IOException)) {
            if (!(throwable instanceof UnsupportedOperationException)) {
              if (!(throwable instanceof AuthenticatorException)) {
                if (!(throwable instanceof RuntimeException)) {
                  if (throwable instanceof Error)
                    throw (Error)throwable; 
                  IllegalStateException illegalStateException = new IllegalStateException();
                  this(throwable);
                  throw illegalStateException;
                } 
                throw (RuntimeException)throwable;
              } 
              throw (AuthenticatorException)throwable;
            } 
            authenticatorException = new AuthenticatorException();
            this(throwable);
            throw authenticatorException;
          } 
          throw (IOException)throwable;
        } finally {
          cancel(true);
        } 
      } else {
        param1Long = (Long)get(param1Long.longValue(), (TimeUnit)authenticatorException);
        cancel(true);
        return (T)param1Long;
      } 
      cancel(true);
      throw new OperationCanceledException();
    }
    
    protected void done() {
      if (this.mCallback != null)
        postRunnableToHandler(new Runnable() {
              public void run() {
                AccountManager.Future2Task.this.mCallback.run(AccountManager.Future2Task.this);
              }
            }); 
    }
    
    public T getResult() throws OperationCanceledException, IOException, AuthenticatorException {
      return internalGetResult((Long)null, (TimeUnit)null);
    }
    
    public T getResult(long param1Long, TimeUnit param1TimeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
      return internalGetResult(Long.valueOf(param1Long), param1TimeUnit);
    }
    
    public Future2Task<T> start() {
      startTask();
      return this;
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.mCallback.run(this.this$1);
    }
  }
  
  private class GetAuthTokenByTypeAndFeaturesTask extends AmsTask implements AccountManagerCallback<Bundle> {
    final String mAccountType;
    
    final Bundle mAddAccountOptions;
    
    final String mAuthTokenType;
    
    final String[] mFeatures;
    
    volatile AccountManagerFuture<Bundle> mFuture = null;
    
    final Bundle mLoginOptions;
    
    final AccountManagerCallback<Bundle> mMyCallback;
    
    private volatile int mNumAccounts = 0;
    
    GetAuthTokenByTypeAndFeaturesTask(String param1String1, String param1String2, String[] param1ArrayOfString, Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2, AccountManagerCallback<Bundle> param1AccountManagerCallback, Handler param1Handler) {
      super(param1Activity, param1Handler, param1AccountManagerCallback);
      if (param1String1 != null) {
        this.mAccountType = param1String1;
        this.mAuthTokenType = param1String2;
        this.mFeatures = param1ArrayOfString;
        this.mAddAccountOptions = param1Bundle1;
        this.mLoginOptions = param1Bundle2;
        this.mMyCallback = this;
        return;
      } 
      throw new IllegalArgumentException("account type is null");
    }
    
    public void doWork() throws RemoteException {
      AccountManager.this.getAccountByTypeAndFeatures(this.mAccountType, this.mFeatures, new AccountManagerCallback<Bundle>() {
            public void run(AccountManagerFuture<Bundle> param2AccountManagerFuture) {
              try {
                Bundle bundle = param2AccountManagerFuture.getResult();
                String str1 = bundle.getString("authAccount");
                String str2 = bundle.getString("accountType");
                if (str1 == null) {
                  if (AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity != null) {
                    AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
                    getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.addAccount(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAccountType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mFeatures, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAddAccountOptions, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                  } else {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("authAccount", null);
                    bundle1.putString("accountType", null);
                    bundle1.putString("authtoken", null);
                    bundle1.putBinder("accountAccessId", null);
                    try {
                      AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onResult(bundle1);
                    } catch (RemoteException remoteException) {}
                  } 
                } else {
                  AccountManager.GetAuthTokenByTypeAndFeaturesTask.access$1502(AccountManager.GetAuthTokenByTypeAndFeaturesTask.this, 1);
                  Account account = new Account((String)remoteException, str2);
                  if (AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity == null) {
                    AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
                    getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.getAuthToken(account, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, false, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                  } else {
                    AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = AccountManager.GetAuthTokenByTypeAndFeaturesTask.this;
                    getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.getAuthToken(account, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mActivity, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                  } 
                } 
                return;
              } catch (OperationCanceledException operationCanceledException) {
                AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.setException(operationCanceledException);
                return;
              } catch (IOException iOException) {
                AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.setException(iOException);
                return;
              } catch (AuthenticatorException authenticatorException) {
                AccountManager.GetAuthTokenByTypeAndFeaturesTask.this.setException(authenticatorException);
                return;
              } 
            }
          }this.mHandler);
    }
    
    public void run(AccountManagerFuture<Bundle> param1AccountManagerFuture) {
      try {
        String str;
        Bundle bundle = param1AccountManagerFuture.getResult();
        if (this.mNumAccounts == 0) {
          AuthenticatorException authenticatorException;
          String str2 = bundle.getString("authAccount");
          String str1 = bundle.getString("accountType");
          if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str1)) {
            authenticatorException = new AuthenticatorException();
            this("account not in result");
            setException(authenticatorException);
            return;
          } 
          str = bundle.getString("accountAccessId");
          Account account = new Account();
          this(str2, (String)authenticatorException, str);
          this.mNumAccounts = 1;
          AccountManager.this.getAuthToken(account, this.mAuthTokenType, (Bundle)null, this.mActivity, this.mMyCallback, this.mHandler);
          return;
        } 
        set((Bundle)str);
      } catch (OperationCanceledException operationCanceledException) {
        cancel(true);
      } catch (IOException iOException) {
        setException(iOException);
      } catch (AuthenticatorException authenticatorException) {
        setException(authenticatorException);
      } 
    }
  }
  
  class null implements AccountManagerCallback<Bundle> {
    public void run(AccountManagerFuture<Bundle> param1AccountManagerFuture) {
      try {
        Bundle bundle = param1AccountManagerFuture.getResult();
        String str1 = bundle.getString("authAccount");
        String str2 = bundle.getString("accountType");
        if (str1 == null) {
          if (this.this$1.mActivity != null) {
            AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = this.this$1;
            getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.addAccount(this.this$1.mAccountType, this.this$1.mAuthTokenType, this.this$1.mFeatures, this.this$1.mAddAccountOptions, this.this$1.mActivity, this.this$1.mMyCallback, this.this$1.mHandler);
          } else {
            Bundle bundle1 = new Bundle();
            bundle1.putString("authAccount", null);
            bundle1.putString("accountType", null);
            bundle1.putString("authtoken", null);
            bundle1.putBinder("accountAccessId", null);
            try {
              this.this$1.mResponse.onResult(bundle1);
            } catch (RemoteException remoteException) {}
          } 
        } else {
          AccountManager.GetAuthTokenByTypeAndFeaturesTask.access$1502(this.this$1, 1);
          Account account = new Account((String)remoteException, str2);
          if (this.this$1.mActivity == null) {
            AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = this.this$1;
            getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.getAuthToken(account, this.this$1.mAuthTokenType, false, this.this$1.mMyCallback, this.this$1.mHandler);
          } else {
            AccountManager.GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = this.this$1;
            getAuthTokenByTypeAndFeaturesTask.mFuture = AccountManager.this.getAuthToken(account, this.this$1.mAuthTokenType, this.this$1.mLoginOptions, this.this$1.mActivity, this.this$1.mMyCallback, this.this$1.mHandler);
          } 
        } 
        return;
      } catch (OperationCanceledException operationCanceledException) {
        this.this$1.setException(operationCanceledException);
        return;
      } catch (IOException iOException) {
        this.this$1.setException(iOException);
        return;
      } catch (AuthenticatorException authenticatorException) {
        this.this$1.setException(authenticatorException);
        return;
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */