package android.app;

import android.annotation.SystemApi;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

public class SearchManager implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {
  public static final String ACTION_KEY = "action_key";
  
  public static final String ACTION_MSG = "action_msg";
  
  public static final String APP_DATA = "app_data";
  
  public static final String CONTEXT_IS_VOICE = "android.search.CONTEXT_IS_VOICE";
  
  public static final String CURSOR_EXTRA_KEY_IN_PROGRESS = "in_progress";
  
  private static final boolean DBG = false;
  
  public static final String DISABLE_VOICE_SEARCH = "android.search.DISABLE_VOICE_SEARCH";
  
  public static final String EXTRA_DATA_KEY = "intent_extra_data_key";
  
  public static final String EXTRA_NEW_SEARCH = "new_search";
  
  public static final String EXTRA_SELECT_QUERY = "select_query";
  
  public static final String EXTRA_WEB_SEARCH_PENDINGINTENT = "web_search_pendingintent";
  
  public static final int FLAG_QUERY_REFINEMENT = 1;
  
  public static final String INTENT_ACTION_GLOBAL_SEARCH = "android.search.action.GLOBAL_SEARCH";
  
  public static final String INTENT_ACTION_SEARCHABLES_CHANGED = "android.search.action.SEARCHABLES_CHANGED";
  
  public static final String INTENT_ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";
  
  public static final String INTENT_ACTION_SEARCH_SETTINGS_CHANGED = "android.search.action.SETTINGS_CHANGED";
  
  public static final String INTENT_ACTION_WEB_SEARCH_SETTINGS = "android.search.action.WEB_SEARCH_SETTINGS";
  
  public static final String INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED = "android.search.action.GLOBAL_SEARCH_ACTIVITY_CHANGED";
  
  public static final char MENU_KEY = 's';
  
  public static final int MENU_KEYCODE = 47;
  
  public static final String QUERY = "query";
  
  public static final String SEARCH_MODE = "search_mode";
  
  public static final String SHORTCUT_MIME_TYPE = "vnd.android.cursor.item/vnd.android.search.suggest";
  
  public static final String SUGGEST_COLUMN_AUDIO_CHANNEL_CONFIG = "suggest_audio_channel_config";
  
  public static final String SUGGEST_COLUMN_CONTENT_TYPE = "suggest_content_type";
  
  public static final String SUGGEST_COLUMN_DURATION = "suggest_duration";
  
  public static final String SUGGEST_COLUMN_FLAGS = "suggest_flags";
  
  public static final String SUGGEST_COLUMN_FORMAT = "suggest_format";
  
  public static final String SUGGEST_COLUMN_ICON_1 = "suggest_icon_1";
  
  public static final String SUGGEST_COLUMN_ICON_2 = "suggest_icon_2";
  
  public static final String SUGGEST_COLUMN_INTENT_ACTION = "suggest_intent_action";
  
  public static final String SUGGEST_COLUMN_INTENT_DATA = "suggest_intent_data";
  
  public static final String SUGGEST_COLUMN_INTENT_DATA_ID = "suggest_intent_data_id";
  
  public static final String SUGGEST_COLUMN_INTENT_EXTRA_DATA = "suggest_intent_extra_data";
  
  public static final String SUGGEST_COLUMN_IS_LIVE = "suggest_is_live";
  
  public static final String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";
  
  public static final String SUGGEST_COLUMN_PRODUCTION_YEAR = "suggest_production_year";
  
  public static final String SUGGEST_COLUMN_PURCHASE_PRICE = "suggest_purchase_price";
  
  public static final String SUGGEST_COLUMN_QUERY = "suggest_intent_query";
  
  public static final String SUGGEST_COLUMN_RATING_SCORE = "suggest_rating_score";
  
  public static final String SUGGEST_COLUMN_RATING_STYLE = "suggest_rating_style";
  
  public static final String SUGGEST_COLUMN_RENTAL_PRICE = "suggest_rental_price";
  
  public static final String SUGGEST_COLUMN_RESULT_CARD_IMAGE = "suggest_result_card_image";
  
  public static final String SUGGEST_COLUMN_SHORTCUT_ID = "suggest_shortcut_id";
  
  public static final String SUGGEST_COLUMN_SPINNER_WHILE_REFRESHING = "suggest_spinner_while_refreshing";
  
  public static final String SUGGEST_COLUMN_TEXT_1 = "suggest_text_1";
  
  public static final String SUGGEST_COLUMN_TEXT_2 = "suggest_text_2";
  
  public static final String SUGGEST_COLUMN_TEXT_2_URL = "suggest_text_2_url";
  
  public static final String SUGGEST_COLUMN_VIDEO_HEIGHT = "suggest_video_height";
  
  public static final String SUGGEST_COLUMN_VIDEO_WIDTH = "suggest_video_width";
  
  public static final String SUGGEST_MIME_TYPE = "vnd.android.cursor.dir/vnd.android.search.suggest";
  
  public static final String SUGGEST_NEVER_MAKE_SHORTCUT = "_-1";
  
  public static final String SUGGEST_PARAMETER_LIMIT = "limit";
  
  public static final String SUGGEST_URI_PATH_QUERY = "search_suggest_query";
  
  public static final String SUGGEST_URI_PATH_SHORTCUT = "search_suggest_shortcut";
  
  private static final String TAG = "SearchManager";
  
  public static final String USER_QUERY = "user_query";
  
  OnCancelListener mCancelListener = null;
  
  private final Context mContext;
  
  OnDismissListener mDismissListener = null;
  
  final Handler mHandler;
  
  private SearchDialog mSearchDialog;
  
  private final ISearchManager mService;
  
  SearchManager(Context paramContext, Handler paramHandler) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mService = ISearchManager.Stub.asInterface(ServiceManager.getServiceOrThrow("search"));
  }
  
  private void ensureSearchDialog() {
    if (this.mSearchDialog == null) {
      SearchDialog searchDialog = new SearchDialog(this.mContext, this);
      this.mSearchDialog = searchDialog;
      searchDialog.setOnCancelListener(this);
      this.mSearchDialog.setOnDismissListener(this);
    } 
  }
  
  public Intent getAssistIntent(boolean paramBoolean) {
    try {
      Intent intent = new Intent();
      this("android.intent.action.ASSIST");
      if (paramBoolean) {
        Bundle bundle = ActivityTaskManager.getService().getAssistContextExtras(0);
        if (bundle != null)
          intent.replaceExtras(bundle); 
      } 
      return intent;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ResolveInfo> getGlobalSearchActivities() {
    try {
      return this.mService.getGlobalSearchActivities();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ComponentName getGlobalSearchActivity() {
    try {
      return this.mService.getGlobalSearchActivity();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public SearchableInfo getSearchableInfo(ComponentName paramComponentName) {
    try {
      return this.mService.getSearchableInfo(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<SearchableInfo> getSearchablesInGlobalSearch() {
    try {
      return this.mService.getSearchablesInGlobalSearch();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Cursor getSuggestions(SearchableInfo paramSearchableInfo, String paramString) {
    return getSuggestions(paramSearchableInfo, paramString, -1);
  }
  
  public Cursor getSuggestions(SearchableInfo paramSearchableInfo, String paramString, int paramInt) {
    if (paramSearchableInfo == null)
      return null; 
    String str1 = paramSearchableInfo.getSuggestAuthority();
    if (str1 == null)
      return null; 
    Uri.Builder builder = (new Uri.Builder()).scheme("content").authority(str1).query("").fragment("");
    String str2 = paramSearchableInfo.getSuggestPath();
    if (str2 != null)
      builder.appendEncodedPath(str2); 
    builder.appendPath("search_suggest_query");
    str2 = paramSearchableInfo.getSuggestSelection();
    if (str2 != null) {
      String[] arrayOfString = { paramString };
    } else {
      builder.appendPath(paramString);
      paramSearchableInfo = null;
    } 
    if (paramInt > 0)
      builder.appendQueryParameter("limit", String.valueOf(paramInt)); 
    Uri uri = builder.build();
    return this.mContext.getContentResolver().query(uri, null, str2, (String[])paramSearchableInfo, null);
  }
  
  public ComponentName getWebSearchActivity() {
    try {
      return this.mService.getWebSearchActivity();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isVisible() {
    boolean bool;
    SearchDialog searchDialog = this.mSearchDialog;
    if (searchDialog == null) {
      bool = false;
    } else {
      bool = searchDialog.isShowing();
    } 
    return bool;
  }
  
  @SystemApi
  public void launchAssist(Bundle paramBundle) {
    try {
      if (this.mService == null)
        return; 
      this.mService.launchAssist(this.mContext.getUserId(), paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void onCancel(DialogInterface paramDialogInterface) {
    OnCancelListener onCancelListener = this.mCancelListener;
    if (onCancelListener != null)
      onCancelListener.onCancel(); 
  }
  
  @Deprecated
  public void onDismiss(DialogInterface paramDialogInterface) {
    OnDismissListener onDismissListener = this.mDismissListener;
    if (onDismissListener != null)
      onDismissListener.onDismiss(); 
  }
  
  public void setOnCancelListener(OnCancelListener paramOnCancelListener) {
    this.mCancelListener = paramOnCancelListener;
  }
  
  public void setOnDismissListener(OnDismissListener paramOnDismissListener) {
    this.mDismissListener = paramOnDismissListener;
  }
  
  void startGlobalSearch(String paramString, boolean paramBoolean, Bundle paramBundle, Rect paramRect) {
    ComponentName componentName = getGlobalSearchActivity();
    if (componentName == null) {
      Log.w("SearchManager", "No global search activity found.");
      return;
    } 
    Intent intent = new Intent("android.search.action.GLOBAL_SEARCH");
    intent.addFlags(268435456);
    intent.setComponent(componentName);
    if (paramBundle == null) {
      paramBundle = new Bundle();
    } else {
      paramBundle = new Bundle(paramBundle);
    } 
    if (!paramBundle.containsKey("source"))
      paramBundle.putString("source", this.mContext.getPackageName()); 
    intent.putExtra("app_data", paramBundle);
    if (!TextUtils.isEmpty(paramString))
      intent.putExtra("query", paramString); 
    if (paramBoolean)
      intent.putExtra("select_query", paramBoolean); 
    intent.setSourceBounds(paramRect);
    try {
      this.mContext.startActivity(intent);
    } catch (ActivityNotFoundException activityNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Global search activity not found: ");
      stringBuilder.append(componentName);
      Log.e("SearchManager", stringBuilder.toString());
    } 
  }
  
  public void startSearch(String paramString, boolean paramBoolean1, ComponentName paramComponentName, Bundle paramBundle, boolean paramBoolean2) {
    startSearch(paramString, paramBoolean1, paramComponentName, paramBundle, paramBoolean2, null);
  }
  
  public void startSearch(String paramString, boolean paramBoolean1, ComponentName paramComponentName, Bundle paramBundle, boolean paramBoolean2, Rect paramRect) {
    if (paramBoolean2) {
      startGlobalSearch(paramString, paramBoolean1, paramBundle, paramRect);
      return;
    } 
    if (((UiModeManager)this.mContext.getSystemService(UiModeManager.class)).getCurrentModeType() != 4) {
      ensureSearchDialog();
      this.mSearchDialog.show(paramString, paramBoolean1, paramComponentName, paramBundle);
    } 
  }
  
  public void stopSearch() {
    SearchDialog searchDialog = this.mSearchDialog;
    if (searchDialog != null)
      searchDialog.cancel(); 
  }
  
  public void triggerSearch(String paramString, ComponentName paramComponentName, Bundle paramBundle) {
    if (paramString == null || TextUtils.getTrimmedLength(paramString) == 0) {
      Log.w("SearchManager", "triggerSearch called with empty query, ignoring.");
      return;
    } 
    startSearch(paramString, false, paramComponentName, paramBundle, false);
    this.mSearchDialog.launchQuerySearch();
  }
  
  public static interface OnCancelListener {
    void onCancel();
  }
  
  public static interface OnDismissListener {
    void onDismiss();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */