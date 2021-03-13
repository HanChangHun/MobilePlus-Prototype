package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.app.WindowDecorActionBar;
import com.android.internal.policy.PhoneWindow;
import java.lang.ref.WeakReference;

public class Dialog implements DialogInterface, Window.Callback, KeyEvent.Callback, View.OnCreateContextMenuListener, Window.OnWindowDismissedCallback {
  private static final int CANCEL = 68;
  
  private static final String DIALOG_HIERARCHY_TAG = "android:dialogHierarchy";
  
  private static final String DIALOG_SHOWING_TAG = "android:dialogShowing";
  
  private static final int DISMISS = 67;
  
  private static final int SHOW = 69;
  
  private static final String TAG = "Dialog";
  
  private ActionBar mActionBar;
  
  private ActionMode mActionMode;
  
  private int mActionModeTypeStarting = 0;
  
  private String mCancelAndDismissTaken;
  
  private Message mCancelMessage;
  
  protected boolean mCancelable = true;
  
  private boolean mCanceled = false;
  
  final Context mContext;
  
  private boolean mCreated = false;
  
  View mDecor;
  
  private final Runnable mDismissAction = new _$$Lambda$oslF4K8Uk6v_6nTRoaEpCmfAptE(this);
  
  private Message mDismissMessage;
  
  private final Handler mHandler = new Handler();
  
  private final Handler mListenersHandler;
  
  private DialogInterface.OnKeyListener mOnKeyListener;
  
  private Activity mOwnerActivity;
  
  private SearchEvent mSearchEvent;
  
  private Message mShowMessage;
  
  private boolean mShowing = false;
  
  final Window mWindow;
  
  private final WindowManager mWindowManager;
  
  public Dialog(Context paramContext) {
    this(paramContext, 0, true);
  }
  
  public Dialog(Context paramContext, int paramInt) {
    this(paramContext, paramInt, true);
  }
  
  Dialog(Context paramContext, int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      int i = paramInt;
      if (paramInt == 0) {
        TypedValue typedValue = new TypedValue();
        paramContext.getTheme().resolveAttribute(16843528, typedValue, true);
        i = typedValue.resourceId;
      } 
      this.mContext = (Context)new ContextThemeWrapper(paramContext, i);
    } else {
      this.mContext = paramContext;
    } 
    this.mWindowManager = (WindowManager)paramContext.getSystemService("window");
    PhoneWindow phoneWindow = new PhoneWindow(this.mContext);
    this.mWindow = (Window)phoneWindow;
    phoneWindow.setCallback(this);
    phoneWindow.setOnWindowDismissedCallback(this);
    phoneWindow.setOnWindowSwipeDismissedCallback(new _$$Lambda$Dialog$zXRzrq3I7H1_zmZ8d_W7t2CQN0I(this));
    phoneWindow.setWindowManager(this.mWindowManager, null, null);
    phoneWindow.setGravity(17);
    this.mListenersHandler = new ListenersHandler(this);
  }
  
  protected Dialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener) {
    this(paramContext);
    this.mCancelable = paramBoolean;
    setOnCancelListener(paramOnCancelListener);
  }
  
  @Deprecated
  protected Dialog(Context paramContext, boolean paramBoolean, Message paramMessage) {
    this(paramContext);
    this.mCancelable = paramBoolean;
    this.mCancelMessage = paramMessage;
  }
  
  private ComponentName getAssociatedActivity() {
    ComponentName componentName;
    Context context2;
    Activity activity = this.mOwnerActivity;
    Context context1 = getContext();
    while (true) {
      context2 = null;
      Context context = null;
      if (activity == null && context1 != null) {
        if (context1 instanceof Activity) {
          activity = (Activity)context1;
          continue;
        } 
        if (context1 instanceof ContextWrapper) {
          context1 = ((ContextWrapper)context1).getBaseContext();
          continue;
        } 
        context1 = context;
        continue;
      } 
      break;
    } 
    if (activity == null) {
      context1 = context2;
    } else {
      componentName = activity.getComponentName();
    } 
    return componentName;
  }
  
  private void sendDismissMessage() {
    Message message = this.mDismissMessage;
    if (message != null)
      Message.obtain(message).sendToTarget(); 
  }
  
  private void sendShowMessage() {
    Message message = this.mShowMessage;
    if (message != null)
      Message.obtain(message).sendToTarget(); 
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    this.mWindow.addContentView(paramView, paramLayoutParams);
  }
  
  public void cancel() {
    if (!this.mCanceled) {
      Message message = this.mCancelMessage;
      if (message != null) {
        this.mCanceled = true;
        Message.obtain(message).sendToTarget();
      } 
    } 
    dismiss();
  }
  
  public void closeOptionsMenu() {
    if (this.mWindow.hasFeature(0))
      this.mWindow.closePanel(0); 
  }
  
  public void create() {
    if (!this.mCreated)
      dispatchOnCreate(null); 
  }
  
  public void dismiss() {
    if (Looper.myLooper() == this.mHandler.getLooper()) {
      dismissDialog();
    } else {
      this.mHandler.post(this.mDismissAction);
    } 
  }
  
  void dismissDialog() {
    if (this.mDecor == null || !this.mShowing)
      return; 
    if (this.mWindow.isDestroyed()) {
      Log.e("Dialog", "Tried to dismissDialog() but the Dialog's window was already destroyed!");
      return;
    } 
    try {
      this.mWindowManager.removeViewImmediate(this.mDecor);
      return;
    } finally {
      ActionMode actionMode = this.mActionMode;
      if (actionMode != null)
        actionMode.finish(); 
      this.mDecor = null;
      this.mWindow.closeAllPanels();
      onStop();
      this.mShowing = false;
      sendDismissMessage();
    } 
  }
  
  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent) {
    return this.mWindow.superDispatchGenericMotionEvent(paramMotionEvent) ? true : onGenericMotionEvent(paramMotionEvent);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    DialogInterface.OnKeyListener onKeyListener = this.mOnKeyListener;
    if (onKeyListener != null && onKeyListener.onKey(this, paramKeyEvent.getKeyCode(), paramKeyEvent))
      return true; 
    if (this.mWindow.superDispatchKeyEvent(paramKeyEvent))
      return true; 
    View view = this.mDecor;
    if (view != null) {
      KeyEvent.DispatcherState dispatcherState = view.getKeyDispatcherState();
    } else {
      view = null;
    } 
    return paramKeyEvent.dispatch(this, (KeyEvent.DispatcherState)view, this);
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent) {
    return this.mWindow.superDispatchKeyShortcutEvent(paramKeyEvent) ? true : onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent);
  }
  
  void dispatchOnCreate(Bundle paramBundle) {
    if (!this.mCreated) {
      onCreate(paramBundle);
      this.mCreated = true;
    } 
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    boolean bool;
    paramAccessibilityEvent.setClassName(getClass().getName());
    paramAccessibilityEvent.setPackageName(this.mContext.getPackageName());
    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
    if (((ViewGroup.LayoutParams)layoutParams).width == -1 && ((ViewGroup.LayoutParams)layoutParams).height == -1) {
      bool = true;
    } else {
      bool = false;
    } 
    paramAccessibilityEvent.setFullScreen(bool);
    return false;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    return this.mWindow.superDispatchTouchEvent(paramMotionEvent) ? true : onTouchEvent(paramMotionEvent);
  }
  
  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent) {
    return this.mWindow.superDispatchTrackballEvent(paramMotionEvent) ? true : onTrackballEvent(paramMotionEvent);
  }
  
  public <T extends View> T findViewById(int paramInt) {
    return (T)this.mWindow.findViewById(paramInt);
  }
  
  public ActionBar getActionBar() {
    return this.mActionBar;
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public View getCurrentFocus() {
    Window window = this.mWindow;
    if (window != null) {
      View view = window.getCurrentFocus();
    } else {
      window = null;
    } 
    return (View)window;
  }
  
  public LayoutInflater getLayoutInflater() {
    return getWindow().getLayoutInflater();
  }
  
  public final Activity getOwnerActivity() {
    return this.mOwnerActivity;
  }
  
  public final SearchEvent getSearchEvent() {
    return this.mSearchEvent;
  }
  
  public final int getVolumeControlStream() {
    return getWindow().getVolumeControlStream();
  }
  
  public Window getWindow() {
    return this.mWindow;
  }
  
  public void hide() {
    View view = this.mDecor;
    if (view != null)
      view.setVisibility(8); 
  }
  
  public void invalidateOptionsMenu() {
    if (this.mWindow.hasFeature(0))
      this.mWindow.invalidatePanelMenu(0); 
  }
  
  public boolean isShowing() {
    View view = this.mDecor;
    boolean bool = false;
    if (view != null && view.getVisibility() == 0)
      bool = true; 
    return bool;
  }
  
  public void onActionModeFinished(ActionMode paramActionMode) {
    if (paramActionMode == this.mActionMode)
      this.mActionMode = null; 
  }
  
  public void onActionModeStarted(ActionMode paramActionMode) {
    this.mActionMode = paramActionMode;
  }
  
  public void onAttachedToWindow() {}
  
  public void onBackPressed() {
    if (this.mCancelable)
      cancel(); 
  }
  
  public void onContentChanged() {}
  
  public boolean onContextItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onContextMenuClosed(Menu paramMenu) {}
  
  protected void onCreate(Bundle paramBundle) {}
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {}
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    return true;
  }
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu) {
    return (paramInt == 0) ? onCreateOptionsMenu(paramMenu) : false;
  }
  
  public View onCreatePanelView(int paramInt) {
    return null;
  }
  
  public void onDetachedFromWindow() {}
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent) {
    return false;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4 || paramInt == 111) {
      paramKeyEvent.startTracking();
      return true;
    } 
    return false;
  }
  
  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent) {
    return false;
  }
  
  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent) {
    return false;
  }
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent) {
    return false;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    if ((paramInt == 4 || paramInt == 111) && paramKeyEvent.isTracking() && !paramKeyEvent.isCanceled()) {
      onBackPressed();
      return true;
    } 
    return false;
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem) {
    return false;
  }
  
  public boolean onMenuOpened(int paramInt, Menu paramMenu) {
    if (paramInt == 8)
      this.mActionBar.dispatchMenuVisibilityChanged(true); 
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {}
  
  public void onPanelClosed(int paramInt, Menu paramMenu) {
    if (paramInt == 8)
      this.mActionBar.dispatchMenuVisibilityChanged(false); 
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu) {
    return true;
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu) {
    boolean bool = true;
    if (paramInt == 0) {
      if (!onPrepareOptionsMenu(paramMenu) || !paramMenu.hasVisibleItems())
        bool = false; 
      return bool;
    } 
    return true;
  }
  
  public void onRestoreInstanceState(Bundle paramBundle) {
    Bundle bundle = paramBundle.getBundle("android:dialogHierarchy");
    if (bundle == null)
      return; 
    dispatchOnCreate(paramBundle);
    this.mWindow.restoreHierarchyState(bundle);
    if (paramBundle.getBoolean("android:dialogShowing"))
      show(); 
  }
  
  public Bundle onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putBoolean("android:dialogShowing", this.mShowing);
    if (this.mCreated)
      bundle.putBundle("android:dialogHierarchy", this.mWindow.saveHierarchyState()); 
    return bundle;
  }
  
  public boolean onSearchRequested() {
    SearchManager searchManager = (SearchManager)this.mContext.getSystemService("search");
    ComponentName componentName = getAssociatedActivity();
    if (componentName != null && searchManager.getSearchableInfo(componentName) != null) {
      searchManager.startSearch(null, false, componentName, null, false);
      dismiss();
      return true;
    } 
    return false;
  }
  
  public boolean onSearchRequested(SearchEvent paramSearchEvent) {
    this.mSearchEvent = paramSearchEvent;
    return onSearchRequested();
  }
  
  protected void onStart() {
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(true); 
  }
  
  protected void onStop() {
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(false); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    if (this.mCancelable && this.mShowing && this.mWindow.shouldCloseOnTouch(this.mContext, paramMotionEvent)) {
      cancel();
      return true;
    } 
    return false;
  }
  
  public boolean onTrackballEvent(MotionEvent paramMotionEvent) {
    return false;
  }
  
  public void onWindowAttributesChanged(WindowManager.LayoutParams paramLayoutParams) {
    View view = this.mDecor;
    if (view != null)
      this.mWindowManager.updateViewLayout(view, (ViewGroup.LayoutParams)paramLayoutParams); 
  }
  
  public void onWindowDismissed(boolean paramBoolean1, boolean paramBoolean2) {
    dismiss();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {}
  
  public ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback) {
    ActionBar actionBar = this.mActionBar;
    return (actionBar != null && this.mActionModeTypeStarting == 0) ? actionBar.startActionMode(paramCallback) : null;
  }
  
  public ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback, int paramInt) {
    try {
      this.mActionModeTypeStarting = paramInt;
      return onWindowStartingActionMode(paramCallback);
    } finally {
      this.mActionModeTypeStarting = 0;
    } 
  }
  
  public void openContextMenu(View paramView) {
    paramView.showContextMenu();
  }
  
  public void openOptionsMenu() {
    if (this.mWindow.hasFeature(0))
      this.mWindow.openPanel(0, null); 
  }
  
  public void registerForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(this);
  }
  
  public final boolean requestWindowFeature(int paramInt) {
    return getWindow().requestFeature(paramInt);
  }
  
  public final <T extends View> T requireViewById(int paramInt) {
    T t = (T)findViewById(paramInt);
    if (t != null)
      return t; 
    throw new IllegalArgumentException("ID does not reference a View inside this Dialog");
  }
  
  public void setCancelMessage(Message paramMessage) {
    this.mCancelMessage = paramMessage;
  }
  
  public void setCancelable(boolean paramBoolean) {
    this.mCancelable = paramBoolean;
  }
  
  public void setCanceledOnTouchOutside(boolean paramBoolean) {
    if (paramBoolean && !this.mCancelable)
      this.mCancelable = true; 
    this.mWindow.setCloseOnTouchOutside(paramBoolean);
  }
  
  public void setContentView(int paramInt) {
    this.mWindow.setContentView(paramInt);
  }
  
  public void setContentView(View paramView) {
    this.mWindow.setContentView(paramView);
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    this.mWindow.setContentView(paramView, paramLayoutParams);
  }
  
  public void setDismissMessage(Message paramMessage) {
    this.mDismissMessage = paramMessage;
  }
  
  public final void setFeatureDrawable(int paramInt, Drawable paramDrawable) {
    getWindow().setFeatureDrawable(paramInt, paramDrawable);
  }
  
  public final void setFeatureDrawableAlpha(int paramInt1, int paramInt2) {
    getWindow().setFeatureDrawableAlpha(paramInt1, paramInt2);
  }
  
  public final void setFeatureDrawableResource(int paramInt1, int paramInt2) {
    getWindow().setFeatureDrawableResource(paramInt1, paramInt2);
  }
  
  public final void setFeatureDrawableUri(int paramInt, Uri paramUri) {
    getWindow().setFeatureDrawableUri(paramInt, paramUri);
  }
  
  public void setOnCancelListener(DialogInterface.OnCancelListener paramOnCancelListener) {
    if (this.mCancelAndDismissTaken == null) {
      if (paramOnCancelListener != null) {
        this.mCancelMessage = this.mListenersHandler.obtainMessage(68, paramOnCancelListener);
      } else {
        this.mCancelMessage = null;
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("OnCancelListener is already taken by ");
    stringBuilder.append(this.mCancelAndDismissTaken);
    stringBuilder.append(" and can not be replaced.");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void setOnDismissListener(DialogInterface.OnDismissListener paramOnDismissListener) {
    if (this.mCancelAndDismissTaken == null) {
      if (paramOnDismissListener != null) {
        this.mDismissMessage = this.mListenersHandler.obtainMessage(67, paramOnDismissListener);
      } else {
        this.mDismissMessage = null;
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("OnDismissListener is already taken by ");
    stringBuilder.append(this.mCancelAndDismissTaken);
    stringBuilder.append(" and can not be replaced.");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void setOnKeyListener(DialogInterface.OnKeyListener paramOnKeyListener) {
    this.mOnKeyListener = paramOnKeyListener;
  }
  
  public void setOnShowListener(DialogInterface.OnShowListener paramOnShowListener) {
    if (paramOnShowListener != null) {
      this.mShowMessage = this.mListenersHandler.obtainMessage(69, paramOnShowListener);
    } else {
      this.mShowMessage = null;
    } 
  }
  
  public final void setOwnerActivity(Activity paramActivity) {
    this.mOwnerActivity = paramActivity;
    getWindow().setVolumeControlStream(this.mOwnerActivity.getVolumeControlStream());
  }
  
  public void setTitle(int paramInt) {
    setTitle(this.mContext.getText(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    this.mWindow.setTitle(paramCharSequence);
    this.mWindow.getAttributes().setTitle(paramCharSequence);
  }
  
  public final void setVolumeControlStream(int paramInt) {
    getWindow().setVolumeControlStream(paramInt);
  }
  
  public void show() {
    if (this.mShowing) {
      if (this.mDecor != null) {
        if (this.mWindow.hasFeature(8))
          this.mWindow.invalidatePanelMenu(8); 
        this.mDecor.setVisibility(0);
      } 
      return;
    } 
    this.mCanceled = false;
    if (!this.mCreated) {
      dispatchOnCreate(null);
    } else {
      Configuration configuration = this.mContext.getResources().getConfiguration();
      this.mWindow.getDecorView().dispatchConfigurationChanged(configuration);
    } 
    onStart();
    this.mDecor = this.mWindow.getDecorView();
    if (this.mActionBar == null && this.mWindow.hasFeature(8)) {
      ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
      this.mWindow.setDefaultIcon(applicationInfo.icon);
      this.mWindow.setDefaultLogo(applicationInfo.logo);
      this.mActionBar = (ActionBar)new WindowDecorActionBar(this);
    } 
    WindowManager.LayoutParams layoutParams = this.mWindow.getAttributes();
    boolean bool = false;
    if ((layoutParams.softInputMode & 0x100) == 0) {
      layoutParams.softInputMode |= 0x100;
      bool = true;
    } 
    this.mWindowManager.addView(this.mDecor, (ViewGroup.LayoutParams)layoutParams);
    if (bool)
      layoutParams.softInputMode &= 0xFFFFFEFF; 
    this.mShowing = true;
    sendShowMessage();
  }
  
  public boolean takeCancelAndDismissListeners(String paramString, DialogInterface.OnCancelListener paramOnCancelListener, DialogInterface.OnDismissListener paramOnDismissListener) {
    if (this.mCancelAndDismissTaken != null) {
      this.mCancelAndDismissTaken = null;
    } else if (this.mCancelMessage != null || this.mDismissMessage != null) {
      return false;
    } 
    setOnCancelListener(paramOnCancelListener);
    setOnDismissListener(paramOnDismissListener);
    this.mCancelAndDismissTaken = paramString;
    return true;
  }
  
  public void takeKeyEvents(boolean paramBoolean) {
    this.mWindow.takeKeyEvents(paramBoolean);
  }
  
  public void unregisterForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(null);
  }
  
  private static final class ListenersHandler extends Handler {
    private final WeakReference<DialogInterface> mDialog;
    
    public ListenersHandler(Dialog param1Dialog) {
      this.mDialog = new WeakReference<>(param1Dialog);
    }
    
    public void handleMessage(Message param1Message) {
      switch (param1Message.what) {
        default:
          return;
        case 69:
          ((DialogInterface.OnShowListener)param1Message.obj).onShow(this.mDialog.get());
        case 68:
          ((DialogInterface.OnCancelListener)param1Message.obj).onCancel(this.mDialog.get());
        case 67:
          break;
      } 
      ((DialogInterface.OnDismissListener)param1Message.obj).onDismiss(this.mDialog.get());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Dialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */