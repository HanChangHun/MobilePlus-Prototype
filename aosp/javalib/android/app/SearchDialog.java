package android.app;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchDialog extends Dialog {
  private static final boolean DBG = false;
  
  private static final String IME_OPTION_NO_MICROPHONE = "nm";
  
  private static final String INSTANCE_KEY_APPDATA = "data";
  
  private static final String INSTANCE_KEY_COMPONENT = "comp";
  
  private static final String INSTANCE_KEY_USER_QUERY = "uQry";
  
  private static final String LOG_TAG = "SearchDialog";
  
  private static final int SEARCH_PLATE_LEFT_PADDING_NON_GLOBAL = 7;
  
  private Context mActivityContext;
  
  private ImageView mAppIcon;
  
  private Bundle mAppSearchData;
  
  private TextView mBadgeLabel;
  
  private View mCloseSearch;
  
  private BroadcastReceiver mConfChangeListener = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED"))
          SearchDialog.this.onConfigurationChanged(); 
      }
    };
  
  private ComponentName mLaunchComponent;
  
  private final SearchView.OnCloseListener mOnCloseListener = new SearchView.OnCloseListener() {
      public boolean onClose() {
        return SearchDialog.this.onClosePressed();
      }
    };
  
  private final SearchView.OnQueryTextListener mOnQueryChangeListener = new SearchView.OnQueryTextListener() {
      public boolean onQueryTextChange(String param1String) {
        return false;
      }
      
      public boolean onQueryTextSubmit(String param1String) {
        SearchDialog.this.dismiss();
        return false;
      }
    };
  
  private final SearchView.OnSuggestionListener mOnSuggestionSelectionListener = new SearchView.OnSuggestionListener() {
      public boolean onSuggestionClick(int param1Int) {
        SearchDialog.this.dismiss();
        return false;
      }
      
      public boolean onSuggestionSelect(int param1Int) {
        return false;
      }
    };
  
  private AutoCompleteTextView mSearchAutoComplete;
  
  private int mSearchAutoCompleteImeOptions;
  
  private View mSearchPlate;
  
  private SearchView mSearchView;
  
  private SearchableInfo mSearchable;
  
  private String mUserQuery;
  
  private final Intent mVoiceAppSearchIntent;
  
  private final Intent mVoiceWebSearchIntent;
  
  private Drawable mWorkingSpinner;
  
  public SearchDialog(Context paramContext, SearchManager paramSearchManager) {
    super(paramContext, resolveDialogTheme(paramContext));
    Intent intent = new Intent("android.speech.action.WEB_SEARCH");
    this.mVoiceWebSearchIntent = intent;
    intent.addFlags(268435456);
    this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
    intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    this.mVoiceAppSearchIntent = intent;
    intent.addFlags(268435456);
  }
  
  private void createContentView() {
    setContentView(17367289);
    SearchView searchView = findViewById(16909396);
    this.mSearchView = searchView;
    searchView.setIconified(false);
    this.mSearchView.setOnCloseListener(this.mOnCloseListener);
    this.mSearchView.setOnQueryTextListener(this.mOnQueryChangeListener);
    this.mSearchView.setOnSuggestionListener(this.mOnSuggestionSelectionListener);
    this.mSearchView.onActionViewExpanded();
    searchView = findViewById(16908327);
    this.mCloseSearch = (View)searchView;
    searchView.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            SearchDialog.this.dismiss();
          }
        });
    this.mBadgeLabel = (TextView)this.mSearchView.findViewById(16909387);
    this.mSearchAutoComplete = (AutoCompleteTextView)this.mSearchView.findViewById(16909395);
    this.mAppIcon = findViewById(16909386);
    this.mSearchPlate = this.mSearchView.findViewById(16909394);
    this.mWorkingSpinner = getContext().getDrawable(17303447);
    setWorking(false);
    this.mBadgeLabel.setVisibility(8);
    this.mSearchAutoCompleteImeOptions = this.mSearchAutoComplete.getImeOptions();
  }
  
  private Intent createIntent(String paramString1, Uri paramUri, String paramString2, String paramString3, int paramInt, String paramString4) {
    Intent intent = new Intent(paramString1);
    intent.addFlags(268435456);
    if (paramUri != null)
      intent.setData(paramUri); 
    intent.putExtra("user_query", this.mUserQuery);
    if (paramString3 != null)
      intent.putExtra("query", paramString3); 
    if (paramString2 != null)
      intent.putExtra("intent_extra_data_key", paramString2); 
    Bundle bundle = this.mAppSearchData;
    if (bundle != null)
      intent.putExtra("app_data", bundle); 
    if (paramInt != 0) {
      intent.putExtra("action_key", paramInt);
      intent.putExtra("action_msg", paramString4);
    } 
    intent.setComponent(this.mSearchable.getSearchActivity());
    return intent;
  }
  
  private boolean doShow(String paramString, boolean paramBoolean, ComponentName paramComponentName, Bundle paramBundle) {
    if (!show(paramComponentName, paramBundle))
      return false; 
    setUserQuery(paramString);
    if (paramBoolean)
      this.mSearchAutoComplete.selectAll(); 
    return true;
  }
  
  private boolean enoughToFilter() {
    Filterable filterable = (Filterable)this.mSearchAutoComplete.getAdapter();
    return (filterable == null || filterable.getFilter() == null) ? false : this.mSearchAutoComplete.enoughToFilter();
  }
  
  private boolean isEmpty(AutoCompleteTextView paramAutoCompleteTextView) {
    boolean bool;
    if (TextUtils.getTrimmedLength((CharSequence)paramAutoCompleteTextView.getText()) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  static boolean isLandscapeMode(Context paramContext) {
    boolean bool;
    if ((paramContext.getResources().getConfiguration()).orientation == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isOutOfBounds(View paramView, MotionEvent paramMotionEvent) {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    int k = ViewConfiguration.get(this.mContext).getScaledWindowTouchSlop();
    return (i < -k || j < -k || i > paramView.getWidth() + k || j > paramView.getHeight() + k);
  }
  
  private void launchIntent(Intent paramIntent) {
    if (paramIntent == null)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("launching ");
    stringBuilder.append(paramIntent);
    Log.d("SearchDialog", stringBuilder.toString());
    try {
      getContext().startActivity(paramIntent);
      dismiss();
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Failed launch activity: ");
      stringBuilder1.append(paramIntent);
      Log.e("SearchDialog", stringBuilder1.toString(), runtimeException);
    } 
  }
  
  private boolean onClosePressed() {
    if (isEmpty(this.mSearchAutoComplete)) {
      dismiss();
      return true;
    } 
    return false;
  }
  
  static int resolveDialogTheme(Context paramContext) {
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(17957067, typedValue, true);
    return typedValue.resourceId;
  }
  
  private void setUserQuery(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    this.mUserQuery = str;
    this.mSearchAutoComplete.setText(str);
    this.mSearchAutoComplete.setSelection(str.length());
  }
  
  private boolean show(ComponentName paramComponentName, Bundle paramBundle) {
    SearchableInfo searchableInfo = ((SearchManager)this.mContext.getSystemService("search")).getSearchableInfo(paramComponentName);
    this.mSearchable = searchableInfo;
    if (searchableInfo == null)
      return false; 
    this.mLaunchComponent = paramComponentName;
    this.mAppSearchData = paramBundle;
    this.mActivityContext = searchableInfo.getActivityContext(getContext());
    if (!isShowing()) {
      createContentView();
      this.mSearchView.setSearchableInfo(this.mSearchable);
      this.mSearchView.setAppSearchData(this.mAppSearchData);
      show();
    } 
    updateUI();
    return true;
  }
  
  private void updateSearchAppIcon() {
    Drawable drawable;
    PackageManager packageManager = getContext().getPackageManager();
    try {
      drawable = packageManager.getApplicationIcon((packageManager.getActivityInfo(this.mLaunchComponent, 0)).applicationInfo);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      drawable = packageManager.getDefaultActivityIcon();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.mLaunchComponent);
      stringBuilder.append(" not found, using generic app icon");
      Log.w("SearchDialog", stringBuilder.toString());
    } 
    this.mAppIcon.setImageDrawable(drawable);
    this.mAppIcon.setVisibility(0);
    View view = this.mSearchPlate;
    view.setPadding(7, view.getPaddingTop(), this.mSearchPlate.getPaddingRight(), this.mSearchPlate.getPaddingBottom());
  }
  
  private void updateSearchAutoComplete() {
    this.mSearchAutoComplete.setDropDownDismissedOnCompletion(false);
    this.mSearchAutoComplete.setForceIgnoreOutsideTouch(false);
  }
  
  private void updateSearchBadge() {
    Drawable drawable2;
    byte b = 8;
    Drawable drawable1 = null;
    String str = null;
    if (this.mSearchable.useBadgeIcon()) {
      drawable2 = this.mActivityContext.getDrawable(this.mSearchable.getIconId());
      b = 0;
    } else {
      drawable2 = drawable1;
      if (this.mSearchable.useBadgeLabel()) {
        str = this.mActivityContext.getResources().getText(this.mSearchable.getLabelId()).toString();
        b = 0;
        drawable2 = drawable1;
      } 
    } 
    this.mBadgeLabel.setCompoundDrawablesWithIntrinsicBounds(drawable2, null, null, null);
    this.mBadgeLabel.setText(str);
    this.mBadgeLabel.setVisibility(b);
  }
  
  private void updateUI() {
    if (this.mSearchable != null) {
      this.mDecor.setVisibility(0);
      updateSearchAutoComplete();
      updateSearchAppIcon();
      updateSearchBadge();
      int i = this.mSearchable.getInputType();
      int j = i;
      if ((i & 0xF) == 1) {
        i &= 0xFFFEFFFF;
        j = i;
        if (this.mSearchable.getSuggestAuthority() != null)
          j = i | 0x10000; 
      } 
      this.mSearchAutoComplete.setInputType(j);
      j = this.mSearchable.getImeOptions();
      this.mSearchAutoCompleteImeOptions = j;
      this.mSearchAutoComplete.setImeOptions(j);
      if (this.mSearchable.getVoiceSearchEnabled()) {
        this.mSearchAutoComplete.setPrivateImeOptions("nm");
      } else {
        this.mSearchAutoComplete.setPrivateImeOptions(null);
      } 
    } 
  }
  
  public void hide() {
    if (!isShowing())
      return; 
    InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(InputMethodManager.class);
    if (inputMethodManager != null)
      inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0); 
    super.hide();
  }
  
  public void launchQuerySearch() {
    launchQuerySearch(0, (String)null);
  }
  
  protected void launchQuerySearch(int paramInt, String paramString) {
    launchIntent(createIntent("android.intent.action.SEARCH", (Uri)null, (String)null, this.mSearchAutoComplete.getText().toString(), paramInt, paramString));
  }
  
  public void onBackPressed() {
    InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(InputMethodManager.class);
    if (inputMethodManager != null && inputMethodManager.isFullscreenMode() && inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0))
      return; 
    cancel();
  }
  
  public void onConfigurationChanged() {
    if (this.mSearchable != null && isShowing()) {
      updateSearchAppIcon();
      updateSearchBadge();
      if (isLandscapeMode(getContext())) {
        this.mSearchAutoComplete.setInputMethodMode(1);
        if (this.mSearchAutoComplete.isDropDownAlwaysVisible() || enoughToFilter())
          this.mSearchAutoComplete.showDropDown(); 
      } 
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    Window window = getWindow();
    WindowManager.LayoutParams layoutParams = window.getAttributes();
    layoutParams.width = -1;
    layoutParams.height = -1;
    layoutParams.gravity = 55;
    layoutParams.softInputMode = 16;
    window.setAttributes(layoutParams);
    setCanceledOnTouchOutside(true);
  }
  
  public void onRestoreInstanceState(Bundle paramBundle) {
    if (paramBundle == null)
      return; 
    ComponentName componentName = (ComponentName)paramBundle.getParcelable("comp");
    Bundle bundle = paramBundle.getBundle("data");
    if (!doShow(paramBundle.getString("uQry"), false, componentName, bundle))
      return; 
  }
  
  public Bundle onSaveInstanceState() {
    if (!isShowing())
      return null; 
    Bundle bundle = new Bundle();
    bundle.putParcelable("comp", (Parcelable)this.mLaunchComponent);
    bundle.putBundle("data", this.mAppSearchData);
    bundle.putString("uQry", this.mUserQuery);
    return bundle;
  }
  
  public void onStart() {
    super.onStart();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
    getContext().registerReceiver(this.mConfChangeListener, intentFilter);
  }
  
  public void onStop() {
    super.onStop();
    getContext().unregisterReceiver(this.mConfChangeListener);
    this.mLaunchComponent = null;
    this.mAppSearchData = null;
    this.mSearchable = null;
    this.mUserQuery = null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    if (!this.mSearchAutoComplete.isPopupShowing() && isOutOfBounds(this.mSearchPlate, paramMotionEvent)) {
      cancel();
      return true;
    } 
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setListSelection(int paramInt) {
    this.mSearchAutoComplete.setListSelection(paramInt);
  }
  
  public void setWorking(boolean paramBoolean) {
    boolean bool;
    Drawable drawable = this.mWorkingSpinner;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    drawable.setAlpha(bool);
    this.mWorkingSpinner.setVisible(paramBoolean, false);
    this.mWorkingSpinner.invalidateSelf();
  }
  
  public boolean show(String paramString, boolean paramBoolean, ComponentName paramComponentName, Bundle paramBundle) {
    paramBoolean = doShow(paramString, paramBoolean, paramComponentName, paramBundle);
    if (paramBoolean)
      this.mSearchAutoComplete.showDropDownAfterLayout(); 
    return paramBoolean;
  }
  
  public static class SearchBar extends LinearLayout {
    public SearchBar(Context param1Context) {
      super(param1Context);
    }
    
    public SearchBar(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public ActionMode startActionModeForChild(View param1View, ActionMode.Callback param1Callback, int param1Int) {
      return (param1Int != 0) ? super.startActionModeForChild(param1View, param1Callback, param1Int) : null;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */