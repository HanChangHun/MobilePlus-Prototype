package android.accounts;

import android.app.Activity;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ChooseTypeAndAccountActivity extends Activity implements AccountManagerCallback<Bundle> {
  public static final String EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING = "authTokenType";
  
  public static final String EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE = "addAccountOptions";
  
  public static final String EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY = "addAccountRequiredFeatures";
  
  public static final String EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST = "allowableAccounts";
  
  public static final String EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY = "allowableAccountTypes";
  
  @Deprecated
  public static final String EXTRA_ALWAYS_PROMPT_FOR_ACCOUNT = "alwaysPromptForAccount";
  
  public static final String EXTRA_DESCRIPTION_TEXT_OVERRIDE = "descriptionTextOverride";
  
  public static final String EXTRA_SELECTED_ACCOUNT = "selectedAccount";
  
  private static final String KEY_INSTANCE_STATE_ACCOUNTS_LIST = "accountsList";
  
  private static final String KEY_INSTANCE_STATE_EXISTING_ACCOUNTS = "existingAccounts";
  
  private static final String KEY_INSTANCE_STATE_PENDING_REQUEST = "pendingRequest";
  
  private static final String KEY_INSTANCE_STATE_SELECTED_ACCOUNT_NAME = "selectedAccountName";
  
  private static final String KEY_INSTANCE_STATE_SELECTED_ADD_ACCOUNT = "selectedAddAccount";
  
  private static final String KEY_INSTANCE_STATE_VISIBILITY_LIST = "visibilityList";
  
  public static final int REQUEST_ADD_ACCOUNT = 2;
  
  public static final int REQUEST_CHOOSE_TYPE = 1;
  
  public static final int REQUEST_NULL = 0;
  
  private static final int SELECTED_ITEM_NONE = -1;
  
  private static final String TAG = "AccountChooser";
  
  private LinkedHashMap<Account, Integer> mAccounts;
  
  private String mCallingPackage;
  
  private int mCallingUid;
  
  private String mDescriptionOverride;
  
  private boolean mDisallowAddAccounts;
  
  private boolean mDontShowPicker;
  
  private Parcelable[] mExistingAccounts = null;
  
  private Button mOkButton;
  
  private int mPendingRequest = 0;
  
  private ArrayList<Account> mPossiblyVisibleAccounts;
  
  private String mSelectedAccountName = null;
  
  private boolean mSelectedAddNewAccount = false;
  
  private int mSelectedItemIndex;
  
  private Set<Account> mSetOfAllowableAccounts;
  
  private Set<String> mSetOfRelevantAccountTypes;
  
  private LinkedHashMap<Account, Integer> getAcceptableAccountChoices(AccountManager paramAccountManager) {
    Map<Account, Integer> map = paramAccountManager.getAccountsAndVisibilityForPackage(this.mCallingPackage, null);
    Account[] arrayOfAccount = paramAccountManager.getAccounts();
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>(map.size());
    int i = arrayOfAccount.length;
    for (byte b = 0; b < i; b++) {
      Account account = arrayOfAccount[b];
      Set<Account> set = this.mSetOfAllowableAccounts;
      if (set == null || set.contains(account)) {
        Set<String> set1 = this.mSetOfRelevantAccountTypes;
        if ((set1 == null || set1.contains(account.type)) && map.get(account) != null)
          linkedHashMap.put(account, map.get(account)); 
      } 
    } 
    return (LinkedHashMap)linkedHashMap;
  }
  
  private Set<Account> getAllowableAccountSet(Intent paramIntent) {
    HashSet<Account> hashSet;
    Intent intent = null;
    ArrayList arrayList = paramIntent.getParcelableArrayListExtra("allowableAccounts");
    paramIntent = intent;
    if (arrayList != null) {
      HashSet<Account> hashSet1 = new HashSet(arrayList.size());
      Iterator<Account> iterator = arrayList.iterator();
      while (true) {
        hashSet = hashSet1;
        if (iterator.hasNext()) {
          hashSet1.add(iterator.next());
          continue;
        } 
        break;
      } 
    } 
    return hashSet;
  }
  
  private int getItemIndexToSelect(ArrayList<Account> paramArrayList, String paramString, boolean paramBoolean) {
    if (paramBoolean)
      return paramArrayList.size(); 
    for (byte b = 0; b < paramArrayList.size(); b++) {
      if (((Account)paramArrayList.get(b)).name.equals(paramString))
        return b; 
    } 
    return -1;
  }
  
  private String[] getListOfDisplayableOptions(ArrayList<Account> paramArrayList) {
    String[] arrayOfString = new String[paramArrayList.size() + (this.mDisallowAddAccounts ^ true)];
    for (byte b = 0; b < paramArrayList.size(); b++)
      arrayOfString[b] = ((Account)paramArrayList.get(b)).name; 
    if (!this.mDisallowAddAccounts)
      arrayOfString[paramArrayList.size()] = getResources().getString(17039609); 
    return arrayOfString;
  }
  
  private Set<String> getReleventAccountTypes(Intent paramIntent) {
    String[] arrayOfString = paramIntent.getStringArrayExtra("allowableAccountTypes");
    AuthenticatorDescription[] arrayOfAuthenticatorDescription = AccountManager.get((Context)this).getAuthenticatorTypes();
    HashSet<String> hashSet = new HashSet(arrayOfAuthenticatorDescription.length);
    int i = arrayOfAuthenticatorDescription.length;
    for (byte b = 0; b < i; b++)
      hashSet.add((arrayOfAuthenticatorDescription[b]).type); 
    if (arrayOfString != null) {
      HashSet<String> hashSet1 = Sets.newHashSet((Object[])arrayOfString);
      hashSet1.retainAll(hashSet);
      hashSet = hashSet1;
    } 
    return hashSet;
  }
  
  private void onAccountSelected(Account paramAccount) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("selected account ");
    stringBuilder.append(paramAccount);
    Log.d("AccountChooser", stringBuilder.toString());
    setResultAndFinish(paramAccount.name, paramAccount.type);
  }
  
  private void overrideDescriptionIfSupplied(String paramString) {
    TextView textView = (TextView)findViewById(16908926);
    if (!TextUtils.isEmpty(paramString)) {
      textView.setText(paramString);
    } else {
      textView.setVisibility(8);
    } 
  }
  
  private final void populateUIAccountList(String[] paramArrayOfString) {
    ListView listView = (ListView)findViewById(16908298);
    listView.setAdapter((ListAdapter)new ArrayAdapter((Context)this, 17367055, (Object[])paramArrayOfString));
    listView.setChoiceMode(1);
    listView.setItemsCanFocus(false);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            ChooseTypeAndAccountActivity.access$002(ChooseTypeAndAccountActivity.this, param1Int);
            ChooseTypeAndAccountActivity.this.mOkButton.setEnabled(true);
          }
        });
    int i = this.mSelectedItemIndex;
    if (i != -1) {
      listView.setItemChecked(i, true);
      if (Log.isLoggable("AccountChooser", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("List item ");
        stringBuilder.append(this.mSelectedItemIndex);
        stringBuilder.append(" should be selected");
        Log.v("AccountChooser", stringBuilder.toString());
      } 
    } 
  }
  
  private void setNonLabelThemeAndCallSuperCreate(Bundle paramBundle) {
    setTheme(16974132);
    super.onCreate(paramBundle);
  }
  
  private void setResultAndFinish(String paramString1, String paramString2) {
    Account account = new Account(paramString1, paramString2);
    Integer integer = Integer.valueOf(AccountManager.get((Context)this).getAccountVisibility(account, this.mCallingPackage));
    if (integer != null && integer.intValue() == 4)
      AccountManager.get((Context)this).setAccountVisibility(account, this.mCallingPackage, 2); 
    if (integer != null && integer.intValue() == 3) {
      setResult(0);
      finish();
      return;
    } 
    Bundle bundle = new Bundle();
    bundle.putString("authAccount", paramString1);
    bundle.putString("accountType", paramString2);
    setResult(-1, (new Intent()).putExtras(bundle));
    if (Log.isLoggable("AccountChooser", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ChooseTypeAndAccountActivity.setResultAndFinish: selected account ");
      stringBuilder.append(paramString1);
      stringBuilder.append(", ");
      stringBuilder.append(paramString2);
      Log.v("AccountChooser", stringBuilder.toString());
    } 
    finish();
  }
  
  private void startChooseAccountTypeActivity() {
    if (Log.isLoggable("AccountChooser", 2))
      Log.v("AccountChooser", "ChooseAccountTypeActivity.startChooseAccountTypeActivity()"); 
    Intent intent = new Intent((Context)this, ChooseAccountTypeActivity.class);
    intent.setFlags(524288);
    intent.putExtra("allowableAccountTypes", getIntent().getStringArrayExtra("allowableAccountTypes"));
    intent.putExtra("addAccountOptions", getIntent().getBundleExtra("addAccountOptions"));
    intent.putExtra("addAccountRequiredFeatures", getIntent().getStringArrayExtra("addAccountRequiredFeatures"));
    intent.putExtra("authTokenType", getIntent().getStringExtra("authTokenType"));
    startActivityForResult(intent, 1);
    this.mPendingRequest = 1;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    // Byte code:
    //   0: ldc 'AccountChooser'
    //   2: iconst_2
    //   3: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   6: ifeq -> 122
    //   9: aload_3
    //   10: ifnull -> 28
    //   13: aload_3
    //   14: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   17: ifnull -> 28
    //   20: aload_3
    //   21: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   24: invokevirtual keySet : ()Ljava/util/Set;
    //   27: pop
    //   28: aload_3
    //   29: ifnull -> 41
    //   32: aload_3
    //   33: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   36: astore #4
    //   38: goto -> 44
    //   41: aconst_null
    //   42: astore #4
    //   44: new java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial <init> : ()V
    //   51: astore #5
    //   53: aload #5
    //   55: ldc_w 'ChooseTypeAndAccountActivity.onActivityResult(reqCode='
    //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload #5
    //   64: iload_1
    //   65: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload #5
    //   71: ldc_w ', resCode='
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload #5
    //   80: iload_2
    //   81: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload #5
    //   87: ldc_w ', extras='
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload #5
    //   96: aload #4
    //   98: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload #5
    //   104: ldc_w ')'
    //   107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: ldc 'AccountChooser'
    //   113: aload #5
    //   115: invokevirtual toString : ()Ljava/lang/String;
    //   118: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   121: pop
    //   122: aload_0
    //   123: iconst_0
    //   124: putfield mPendingRequest : I
    //   127: iload_2
    //   128: ifne -> 151
    //   131: aload_0
    //   132: getfield mPossiblyVisibleAccounts : Ljava/util/ArrayList;
    //   135: invokevirtual isEmpty : ()Z
    //   138: ifeq -> 150
    //   141: aload_0
    //   142: iconst_0
    //   143: invokevirtual setResult : (I)V
    //   146: aload_0
    //   147: invokevirtual finish : ()V
    //   150: return
    //   151: iload_2
    //   152: iconst_m1
    //   153: if_icmpne -> 390
    //   156: iload_1
    //   157: iconst_1
    //   158: if_icmpne -> 195
    //   161: aload_3
    //   162: ifnull -> 183
    //   165: aload_3
    //   166: ldc_w 'accountType'
    //   169: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
    //   172: astore_3
    //   173: aload_3
    //   174: ifnull -> 183
    //   177: aload_0
    //   178: aload_3
    //   179: invokevirtual runAddAccountForAuthenticator : (Ljava/lang/String;)V
    //   182: return
    //   183: ldc 'AccountChooser'
    //   185: ldc_w 'ChooseTypeAndAccountActivity.onActivityResult: unable to find account type, pretending the request was canceled'
    //   188: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   191: pop
    //   192: goto -> 381
    //   195: iload_1
    //   196: iconst_2
    //   197: if_icmpne -> 381
    //   200: aconst_null
    //   201: astore #5
    //   203: aconst_null
    //   204: astore #4
    //   206: aload_3
    //   207: ifnull -> 228
    //   210: aload_3
    //   211: ldc_w 'authAccount'
    //   214: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
    //   217: astore #5
    //   219: aload_3
    //   220: ldc_w 'accountType'
    //   223: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
    //   226: astore #4
    //   228: aload #5
    //   230: ifnull -> 245
    //   233: aload #5
    //   235: astore_3
    //   236: aload #4
    //   238: astore #6
    //   240: aload #4
    //   242: ifnonnull -> 364
    //   245: aload_0
    //   246: invokestatic get : (Landroid/content/Context;)Landroid/accounts/AccountManager;
    //   249: aload_0
    //   250: getfield mCallingPackage : Ljava/lang/String;
    //   253: aload_0
    //   254: getfield mCallingUid : I
    //   257: invokevirtual getAccountsForPackage : (Ljava/lang/String;I)[Landroid/accounts/Account;
    //   260: astore #7
    //   262: new java/util/HashSet
    //   265: dup
    //   266: invokespecial <init> : ()V
    //   269: astore #8
    //   271: aload_0
    //   272: getfield mExistingAccounts : [Landroid/os/Parcelable;
    //   275: astore_3
    //   276: aload_3
    //   277: arraylength
    //   278: istore_2
    //   279: iconst_0
    //   280: istore_1
    //   281: iload_1
    //   282: iload_2
    //   283: if_icmpge -> 306
    //   286: aload #8
    //   288: aload_3
    //   289: iload_1
    //   290: aaload
    //   291: checkcast android/accounts/Account
    //   294: invokeinterface add : (Ljava/lang/Object;)Z
    //   299: pop
    //   300: iinc #1, 1
    //   303: goto -> 281
    //   306: aload #7
    //   308: arraylength
    //   309: istore_2
    //   310: iconst_0
    //   311: istore_1
    //   312: aload #5
    //   314: astore_3
    //   315: aload #4
    //   317: astore #6
    //   319: iload_1
    //   320: iload_2
    //   321: if_icmpge -> 364
    //   324: aload #7
    //   326: iload_1
    //   327: aaload
    //   328: astore #6
    //   330: aload #8
    //   332: aload #6
    //   334: invokeinterface contains : (Ljava/lang/Object;)Z
    //   339: ifne -> 358
    //   342: aload #6
    //   344: getfield name : Ljava/lang/String;
    //   347: astore_3
    //   348: aload #6
    //   350: getfield type : Ljava/lang/String;
    //   353: astore #6
    //   355: goto -> 364
    //   358: iinc #1, 1
    //   361: goto -> 312
    //   364: aload_3
    //   365: ifnonnull -> 373
    //   368: aload #6
    //   370: ifnull -> 381
    //   373: aload_0
    //   374: aload_3
    //   375: aload #6
    //   377: invokespecial setResultAndFinish : (Ljava/lang/String;Ljava/lang/String;)V
    //   380: return
    //   381: ldc 'AccountChooser'
    //   383: ldc_w 'ChooseTypeAndAccountActivity.onActivityResult: unable to find added account, pretending the request was canceled'
    //   386: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   389: pop
    //   390: ldc 'AccountChooser'
    //   392: iconst_2
    //   393: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   396: ifeq -> 408
    //   399: ldc 'AccountChooser'
    //   401: ldc_w 'ChooseTypeAndAccountActivity.onActivityResult: canceled'
    //   404: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   407: pop
    //   408: aload_0
    //   409: iconst_0
    //   410: invokevirtual setResult : (I)V
    //   413: aload_0
    //   414: invokevirtual finish : ()V
    //   417: return
  }
  
  public void onCancelButtonClicked(View paramView) {
    onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle) {
    Parcelable[] arrayOfParcelable;
    if (Log.isLoggable("AccountChooser", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ChooseTypeAndAccountActivity.onCreate(savedInstanceState=");
      stringBuilder.append(paramBundle);
      stringBuilder.append(")");
      Log.v("AccountChooser", stringBuilder.toString());
    } 
    boolean bool = false;
    try {
      IBinder iBinder = getActivityToken();
      this.mCallingUid = ActivityTaskManager.getService().getLaunchedFromUid(iBinder);
      String str = ActivityTaskManager.getService().getLaunchedFromPackage(iBinder);
      this.mCallingPackage = str;
      if (this.mCallingUid != 0 && str != null) {
        UserManager userManager = UserManager.get((Context)this);
        UserHandle userHandle = new UserHandle();
        this(UserHandle.getUserId(this.mCallingUid));
        this.mDisallowAddAccounts = userManager.getUserRestrictions(userHandle).getBoolean("no_modify_accounts", false);
      } 
    } catch (RemoteException remoteException) {
      String str = getClass().getSimpleName();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to get caller identity \n");
      stringBuilder.append(remoteException);
      Log.w(str, stringBuilder.toString());
    } 
    Intent intent = getIntent();
    this.mSetOfAllowableAccounts = getAllowableAccountSet(intent);
    this.mSetOfRelevantAccountTypes = getReleventAccountTypes(intent);
    this.mDescriptionOverride = intent.getStringExtra("descriptionTextOverride");
    if (paramBundle != null) {
      this.mPendingRequest = paramBundle.getInt("pendingRequest");
      this.mExistingAccounts = paramBundle.getParcelableArray("existingAccounts");
      this.mSelectedAccountName = paramBundle.getString("selectedAccountName");
      this.mSelectedAddNewAccount = paramBundle.getBoolean("selectedAddAccount", false);
      arrayOfParcelable = paramBundle.getParcelableArray("accountsList");
      ArrayList<Integer> arrayList = paramBundle.getIntegerArrayList("visibilityList");
      this.mAccounts = new LinkedHashMap<>();
      for (byte b = 0; b < arrayOfParcelable.length; b++)
        this.mAccounts.put((Account)arrayOfParcelable[b], arrayList.get(b)); 
    } else {
      this.mPendingRequest = 0;
      this.mExistingAccounts = null;
      Account account = (Account)arrayOfParcelable.getParcelableExtra("selectedAccount");
      if (account != null)
        this.mSelectedAccountName = account.name; 
      this.mAccounts = getAcceptableAccountChoices(AccountManager.get((Context)this));
    } 
    if (Log.isLoggable("AccountChooser", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("selected account name is ");
      stringBuilder.append(this.mSelectedAccountName);
      Log.v("AccountChooser", stringBuilder.toString());
    } 
    this.mPossiblyVisibleAccounts = new ArrayList<>(this.mAccounts.size());
    for (Map.Entry<Account, Integer> entry : this.mAccounts.entrySet()) {
      if (3 != ((Integer)entry.getValue()).intValue())
        this.mPossiblyVisibleAccounts.add((Account)entry.getKey()); 
    } 
    if (this.mPossiblyVisibleAccounts.isEmpty() && this.mDisallowAddAccounts) {
      requestWindowFeature(1);
      setContentView(17367096);
      this.mDontShowPicker = true;
    } 
    if (this.mDontShowPicker) {
      super.onCreate(paramBundle);
      return;
    } 
    if (this.mPendingRequest == 0 && this.mPossiblyVisibleAccounts.isEmpty()) {
      setNonLabelThemeAndCallSuperCreate(paramBundle);
      if (this.mSetOfRelevantAccountTypes.size() == 1) {
        runAddAccountForAuthenticator(this.mSetOfRelevantAccountTypes.iterator().next());
      } else {
        startChooseAccountTypeActivity();
      } 
    } 
    String[] arrayOfString = getListOfDisplayableOptions(this.mPossiblyVisibleAccounts);
    this.mSelectedItemIndex = getItemIndexToSelect(this.mPossiblyVisibleAccounts, this.mSelectedAccountName, this.mSelectedAddNewAccount);
    super.onCreate(paramBundle);
    setContentView(17367122);
    overrideDescriptionIfSupplied(this.mDescriptionOverride);
    populateUIAccountList(arrayOfString);
    Button button = (Button)findViewById(16908314);
    this.mOkButton = button;
    if (this.mSelectedItemIndex != -1)
      bool = true; 
    button.setEnabled(bool);
  }
  
  protected void onDestroy() {
    if (Log.isLoggable("AccountChooser", 2))
      Log.v("AccountChooser", "ChooseTypeAndAccountActivity.onDestroy()"); 
    super.onDestroy();
  }
  
  public void onOkButtonClicked(View paramView) {
    if (this.mSelectedItemIndex == this.mPossiblyVisibleAccounts.size()) {
      startChooseAccountTypeActivity();
    } else {
      int i = this.mSelectedItemIndex;
      if (i != -1)
        onAccountSelected(this.mPossiblyVisibleAccounts.get(i)); 
    } 
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("pendingRequest", this.mPendingRequest);
    if (this.mPendingRequest == 2)
      paramBundle.putParcelableArray("existingAccounts", this.mExistingAccounts); 
    int i = this.mSelectedItemIndex;
    if (i != -1)
      if (i == this.mPossiblyVisibleAccounts.size()) {
        paramBundle.putBoolean("selectedAddAccount", true);
      } else {
        paramBundle.putBoolean("selectedAddAccount", false);
        paramBundle.putString("selectedAccountName", ((Account)this.mPossiblyVisibleAccounts.get(this.mSelectedItemIndex)).name);
      }  
    Parcelable[] arrayOfParcelable = new Parcelable[this.mAccounts.size()];
    ArrayList<Integer> arrayList = new ArrayList(this.mAccounts.size());
    i = 0;
    for (Map.Entry<Account, Integer> entry : this.mAccounts.entrySet()) {
      arrayOfParcelable[i] = (Parcelable)entry.getKey();
      arrayList.add((Integer)entry.getValue());
      i++;
    } 
    paramBundle.putParcelableArray("accountsList", arrayOfParcelable);
    paramBundle.putIntegerArrayList("visibilityList", arrayList);
  }
  
  public void run(AccountManagerFuture<Bundle> paramAccountManagerFuture) {
    try {
      Intent intent = (Intent)((Bundle)paramAccountManagerFuture.getResult()).getParcelable("intent");
      if (intent != null) {
        this.mPendingRequest = 2;
        this.mExistingAccounts = (Parcelable[])AccountManager.get((Context)this).getAccountsForPackage(this.mCallingPackage, this.mCallingUid);
        intent.setFlags(intent.getFlags() & 0xEFFFFFFF);
        startActivityForResult(intent, 2);
        return;
      } 
      Bundle bundle = new Bundle();
      bundle.putString("errorMessage", "error communicating with server");
      setResult(-1, (new Intent()).putExtras(bundle));
      finish();
      return;
    } catch (OperationCanceledException operationCanceledException) {
      setResult(0);
      finish();
      return;
    } catch (IOException iOException) {
      Bundle bundle = new Bundle();
      bundle.putString("errorMessage", "error communicating with server");
      setResult(-1, (new Intent()).putExtras(bundle));
      finish();
      return;
    } catch (AuthenticatorException authenticatorException) {
      Bundle bundle = new Bundle();
      bundle.putString("errorMessage", "error communicating with server");
      setResult(-1, (new Intent()).putExtras(bundle));
      finish();
      return;
    } 
  }
  
  protected void runAddAccountForAuthenticator(String paramString) {
    if (Log.isLoggable("AccountChooser", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("runAddAccountForAuthenticator: ");
      stringBuilder.append(paramString);
      Log.v("AccountChooser", stringBuilder.toString());
    } 
    Bundle bundle = getIntent().getBundleExtra("addAccountOptions");
    String[] arrayOfString = getIntent().getStringArrayExtra("addAccountRequiredFeatures");
    String str = getIntent().getStringExtra("authTokenType");
    AccountManager.get((Context)this).addAccount(paramString, str, arrayOfString, bundle, null, this, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseTypeAndAccountActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */