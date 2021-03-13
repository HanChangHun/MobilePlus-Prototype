package android.appwidget;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.RemoteViewsAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AppWidgetHostView extends FrameLayout {
  private static final LayoutInflater.Filter INFLATER_FILTER = (LayoutInflater.Filter)_$$Lambda$AppWidgetHostView$AzPWN1sIsRb7M_0Ss1rK2mksT_o.INSTANCE;
  
  private static final String KEY_JAILED_ARRAY = "jail";
  
  static final boolean LOGD = false;
  
  static final String TAG = "AppWidgetHostView";
  
  static final int VIEW_MODE_CONTENT = 1;
  
  static final int VIEW_MODE_DEFAULT = 3;
  
  static final int VIEW_MODE_ERROR = 2;
  
  static final int VIEW_MODE_NOINIT = 0;
  
  int mAppWidgetId;
  
  private Executor mAsyncExecutor;
  
  Context mContext;
  
  AppWidgetProviderInfo mInfo;
  
  private CancellationSignal mLastExecutionSignal;
  
  int mLayoutId = -1;
  
  private RemoteViews.OnClickHandler mOnClickHandler;
  
  private boolean mOnLightBackground;
  
  Context mRemoteContext;
  
  View mView;
  
  int mViewMode = 0;
  
  public AppWidgetHostView(Context paramContext) {
    this(paramContext, 17432576, 17432577);
  }
  
  public AppWidgetHostView(Context paramContext, int paramInt1, int paramInt2) {
    super(paramContext);
    this.mContext = paramContext;
    setIsRootNamespace(true);
  }
  
  public AppWidgetHostView(Context paramContext, RemoteViews.OnClickHandler paramOnClickHandler) {
    this(paramContext, 17432576, 17432577);
    this.mOnClickHandler = getHandler(paramOnClickHandler);
  }
  
  private void applyContent(View paramView, boolean paramBoolean, Exception paramException) {
    View view = paramView;
    if (paramView == null) {
      if (this.mViewMode == 2)
        return; 
      if (paramException != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error inflating RemoteViews : ");
        stringBuilder.append(paramException.toString());
        Log.w("AppWidgetHostView", stringBuilder.toString());
      } 
      view = getErrorView();
      this.mViewMode = 2;
    } 
    if (!paramBoolean) {
      prepareView(view);
      addView(view);
    } 
    paramView = this.mView;
    if (paramView != view) {
      removeView(paramView);
      this.mView = view;
    } 
  }
  
  private int generateId() {
    int i = getId();
    if (i == -1)
      i = this.mAppWidgetId; 
    return i;
  }
  
  private Rect getDefaultPadding() {
    return getDefaultPaddingForWidget(this.mContext, (Rect)null);
  }
  
  public static Rect getDefaultPaddingForWidget(Context paramContext, ComponentName paramComponentName, Rect paramRect) {
    return getDefaultPaddingForWidget(paramContext, paramRect);
  }
  
  private static Rect getDefaultPaddingForWidget(Context paramContext, Rect paramRect) {
    if (paramRect == null) {
      paramRect = new Rect(0, 0, 0, 0);
    } else {
      paramRect.set(0, 0, 0, 0);
    } 
    Resources resources = paramContext.getResources();
    paramRect.left = resources.getDimensionPixelSize(17105143);
    paramRect.right = resources.getDimensionPixelSize(17105144);
    paramRect.top = resources.getDimensionPixelSize(17105145);
    paramRect.bottom = resources.getDimensionPixelSize(17105142);
    return paramRect;
  }
  
  private RemoteViews.OnClickHandler getHandler(RemoteViews.OnClickHandler paramOnClickHandler) {
    return new _$$Lambda$AppWidgetHostView$D007jt6fiFenWYtpza3A8OuYZ4w(this, paramOnClickHandler);
  }
  
  private void inflateAsync(RemoteViews paramRemoteViews) {
    this.mRemoteContext = getRemoteContext();
    int i = paramRemoteViews.getLayoutId();
    if (i == this.mLayoutId) {
      View view = this.mView;
      if (view != null)
        try {
          Context context = this.mContext;
          Executor executor = this.mAsyncExecutor;
          ViewApplyListener viewApplyListener = new ViewApplyListener();
          this(this, paramRemoteViews, i, true);
          this.mLastExecutionSignal = paramRemoteViews.reapplyAsync(context, view, executor, viewApplyListener, this.mOnClickHandler);
        } catch (Exception exception) {} 
    } 
    if (this.mLastExecutionSignal == null)
      this.mLastExecutionSignal = paramRemoteViews.applyAsync(this.mContext, (ViewGroup)this, this.mAsyncExecutor, new ViewApplyListener(paramRemoteViews, i, false), this.mOnClickHandler); 
  }
  
  private void onDefaultViewClicked(View paramView) {
    if (this.mInfo != null) {
      LauncherApps launcherApps = (LauncherApps)getContext().getSystemService(LauncherApps.class);
      List<LauncherActivityInfo> list = launcherApps.getActivityList(this.mInfo.provider.getPackageName(), this.mInfo.getProfile());
      if (!list.isEmpty()) {
        LauncherActivityInfo launcherActivityInfo = list.get(0);
        launcherApps.startMainActivity(launcherActivityInfo.getComponentName(), launcherActivityInfo.getUser(), RemoteViews.getSourceBounds(paramView), null);
      } 
    } 
  }
  
  protected void applyRemoteViews(RemoteViews paramRemoteViews, boolean paramBoolean) {
    boolean bool1 = false;
    boolean bool2 = false;
    View view = null;
    RuntimeException runtimeException1 = null;
    runtimeException2 = null;
    CancellationSignal cancellationSignal = this.mLastExecutionSignal;
    if (cancellationSignal != null) {
      cancellationSignal.cancel();
      this.mLastExecutionSignal = null;
    } 
    if (paramRemoteViews == null) {
      if (this.mViewMode == 3)
        return; 
      view = getDefaultView();
      this.mLayoutId = -1;
      this.mViewMode = 3;
      paramBoolean = bool1;
      runtimeException2 = runtimeException1;
    } else {
      RemoteViews remoteViews = paramRemoteViews;
      if (this.mOnLightBackground)
        remoteViews = paramRemoteViews.getDarkTextViews(); 
      if (this.mAsyncExecutor != null && paramBoolean) {
        inflateAsync(remoteViews);
        return;
      } 
      this.mRemoteContext = getRemoteContext();
      int i = remoteViews.getLayoutId();
      paramBoolean = bool2;
      View view1 = view;
      runtimeException1 = runtimeException2;
      if (!false) {
        paramBoolean = bool2;
        view1 = view;
        runtimeException1 = runtimeException2;
        if (i == this.mLayoutId)
          try {
            remoteViews.reapply(this.mContext, this.mView, this.mOnClickHandler);
            view1 = this.mView;
            paramBoolean = true;
            runtimeException1 = runtimeException2;
          } catch (RuntimeException runtimeException) {
            view1 = view;
            paramBoolean = bool2;
          }  
      } 
      view = view1;
      runtimeException2 = runtimeException;
      if (view1 == null)
        try {
          view = remoteViews.apply(this.mContext, (ViewGroup)this, this.mOnClickHandler);
          runtimeException2 = runtimeException;
        } catch (RuntimeException runtimeException2) {
          view = view1;
        }  
      this.mLayoutId = i;
      this.mViewMode = 1;
    } 
    applyContent(view, paramBoolean, runtimeException2);
  }
  
  public ActivityOptions createSharedElementActivityOptions(int[] paramArrayOfint, String[] paramArrayOfString, Intent paramIntent) {
    Context context;
    for (context = getContext(); context instanceof ContextWrapper && !(context instanceof Activity); context = ((ContextWrapper)context).getBaseContext());
    if (!(context instanceof Activity))
      return null; 
    ArrayList<Pair> arrayList = new ArrayList();
    Bundle bundle = new Bundle();
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      View view = findViewById(paramArrayOfint[b]);
      if (view != null) {
        arrayList.add(Pair.create(view, paramArrayOfString[b]));
        bundle.putParcelable(paramArrayOfString[b], (Parcelable)RemoteViews.getSourceBounds(view));
      } 
    } 
    if (!arrayList.isEmpty()) {
      paramIntent.putExtra("android.widget.extra.SHARED_ELEMENT_BOUNDS", bundle);
      ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity)context, arrayList.<Pair>toArray(new Pair[arrayList.size()]));
      activityOptions.setPendingIntentLaunchFlags(268435456);
      return activityOptions;
    } 
    return null;
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray) {
    Parcelable parcelable = (Parcelable)paramSparseArray.get(generateId());
    paramSparseArray = null;
    if (parcelable instanceof Bundle)
      paramSparseArray = ((Bundle)parcelable).getSparseParcelableArray("jail"); 
    SparseArray<Parcelable> sparseArray = paramSparseArray;
    if (paramSparseArray == null)
      sparseArray = new SparseArray(); 
    try {
      super.dispatchRestoreInstanceState(sparseArray);
    } catch (Exception exception) {
      String str;
      ComponentName componentName;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("failed to restoreInstanceState for widget id: ");
      stringBuilder.append(this.mAppWidgetId);
      stringBuilder.append(", ");
      AppWidgetProviderInfo appWidgetProviderInfo = this.mInfo;
      if (appWidgetProviderInfo == null) {
        str = "null";
      } else {
        componentName = ((AppWidgetProviderInfo)str).provider;
      } 
      stringBuilder.append(componentName);
      Log.e("AppWidgetHostView", stringBuilder.toString(), exception);
    } 
  }
  
  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray) {
    SparseArray sparseArray = new SparseArray();
    super.dispatchSaveInstanceState(sparseArray);
    Bundle bundle = new Bundle();
    bundle.putSparseParcelableArray("jail", sparseArray);
    paramSparseArray.put(generateId(), bundle);
  }
  
  public FrameLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    Context context = this.mRemoteContext;
    if (context == null)
      context = this.mContext; 
    return new FrameLayout.LayoutParams(context, paramAttributeSet);
  }
  
  public int getAppWidgetId() {
    return this.mAppWidgetId;
  }
  
  public AppWidgetProviderInfo getAppWidgetInfo() {
    return this.mInfo;
  }
  
  protected View getDefaultView() {
    View view1;
    _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ2;
    View view2 = null;
    Context context = null;
    View view3 = null;
    View view4 = view2;
    try {
      _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ;
      if (this.mInfo != null) {
        view4 = view2;
        context = getRemoteContext();
        view4 = view2;
        this.mRemoteContext = context;
        view4 = view2;
        LayoutInflater layoutInflater = ((LayoutInflater)context.getSystemService("layout_inflater")).cloneInContext(context);
        view4 = view2;
        layoutInflater.setFilter(INFLATER_FILTER);
        view4 = view2;
        Bundle bundle = AppWidgetManager.getInstance(this.mContext).getAppWidgetOptions(this.mAppWidgetId);
        view4 = view2;
        int i = this.mInfo.initialLayout;
        int j = i;
        view4 = view2;
        if (bundle.containsKey("appWidgetCategory")) {
          j = i;
          view4 = view2;
          if (bundle.getInt("appWidgetCategory") == 2) {
            view4 = view2;
            j = this.mInfo.initialKeyguardLayout;
            if (j != 0)
              i = j; 
            j = i;
          } 
        } 
        view4 = view2;
        view2 = layoutInflater.inflate(j, (ViewGroup)this, false);
        view4 = view2;
        if (!(view2 instanceof AdapterView)) {
          view4 = view2;
          _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ = new _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ();
          view4 = view2;
          this(this);
          view4 = view2;
          view2.setOnClickListener(_$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ);
        } 
        view4 = view2;
      } else {
        view4 = view2;
        Log.w("AppWidgetHostView", "can't inflate defaultView because mInfo is missing");
        _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ2 = _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ;
      } 
      view2 = view3;
    } catch (RuntimeException runtimeException) {}
    if (runtimeException != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error inflating AppWidget ");
      stringBuilder.append(this.mInfo);
      stringBuilder.append(": ");
      stringBuilder.append(runtimeException.toString());
      Log.w("AppWidgetHostView", stringBuilder.toString());
    } 
    _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ1 = _$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ2;
    if (_$$Lambda$AppWidgetHostView$ab7zr5jJn3_7TaWMNA8VPkK4SdQ2 == null)
      view1 = getErrorView(); 
    return view1;
  }
  
  protected View getErrorView() {
    TextView textView = new TextView(this.mContext);
    textView.setText(17040236);
    textView.setBackgroundColor(Color.argb(127, 0, 0, 0));
    return (View)textView;
  }
  
  protected Context getRemoteContext() {
    try {
      return this.mContext.createApplicationContext(this.mInfo.providerInfo.applicationInfo, 4);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Package name ");
      stringBuilder.append(this.mInfo.providerInfo.packageName);
      stringBuilder.append(" not found");
      Log.e("AppWidgetHostView", stringBuilder.toString());
      return this.mContext;
    } 
  }
  
  public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    super.onInitializeAccessibilityNodeInfoInternal(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(AppWidgetHostView.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    try {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    } catch (RuntimeException runtimeException) {
      Log.e("AppWidgetHostView", "Remote provider threw runtime exception, using error view instead.", runtimeException);
      removeViewInLayout(this.mView);
      View view = getErrorView();
      prepareView(view);
      addViewInLayout(view, 0, view.getLayoutParams());
      measureChild(view, View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
      view.layout(0, 0, view.getMeasuredWidth() + this.mPaddingLeft + this.mPaddingRight, view.getMeasuredHeight() + this.mPaddingTop + this.mPaddingBottom);
      this.mView = view;
      this.mViewMode = 2;
    } 
  }
  
  protected void prepareView(View paramView) {
    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams)paramView.getLayoutParams();
    FrameLayout.LayoutParams layoutParams2 = layoutParams1;
    if (layoutParams1 == null)
      layoutParams2 = new FrameLayout.LayoutParams(-1, -1); 
    layoutParams2.gravity = 17;
    paramView.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
  }
  
  void resetAppWidget(AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    setAppWidget(this.mAppWidgetId, paramAppWidgetProviderInfo);
    this.mViewMode = 0;
    updateAppWidget((RemoteViews)null);
  }
  
  public void setAppWidget(int paramInt, AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    this.mAppWidgetId = paramInt;
    this.mInfo = paramAppWidgetProviderInfo;
    Rect rect = getDefaultPadding();
    setPadding(rect.left, rect.top, rect.right, rect.bottom);
    if (paramAppWidgetProviderInfo != null) {
      String str2 = paramAppWidgetProviderInfo.loadLabel(getContext().getPackageManager());
      String str1 = str2;
      if ((paramAppWidgetProviderInfo.providerInfo.applicationInfo.flags & 0x40000000) != 0)
        str1 = Resources.getSystem().getString(17041332, new Object[] { str2 }); 
      setContentDescription(str1);
    } 
  }
  
  public void setExecutor(Executor paramExecutor) {
    CancellationSignal cancellationSignal = this.mLastExecutionSignal;
    if (cancellationSignal != null) {
      cancellationSignal.cancel();
      this.mLastExecutionSignal = null;
    } 
    this.mAsyncExecutor = paramExecutor;
  }
  
  public void setOnClickHandler(RemoteViews.OnClickHandler paramOnClickHandler) {
    this.mOnClickHandler = getHandler(paramOnClickHandler);
  }
  
  public void setOnLightBackground(boolean paramBoolean) {
    this.mOnLightBackground = paramBoolean;
  }
  
  public void updateAppWidget(RemoteViews paramRemoteViews) {
    applyRemoteViews(paramRemoteViews, true);
  }
  
  public void updateAppWidgetOptions(Bundle paramBundle) {
    AppWidgetManager.getInstance(this.mContext).updateAppWidgetOptions(this.mAppWidgetId, paramBundle);
  }
  
  public void updateAppWidgetSize(Bundle paramBundle, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    updateAppWidgetSize(paramBundle, paramInt1, paramInt2, paramInt3, paramInt4, false);
  }
  
  public void updateAppWidgetSize(Bundle paramBundle, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    if (paramBundle == null)
      paramBundle = new Bundle(); 
    Rect rect = getDefaultPadding();
    float f = (getResources().getDisplayMetrics()).density;
    int i = (int)((rect.left + rect.right) / f);
    int j = (int)((rect.top + rect.bottom) / f);
    boolean bool = false;
    if (paramBoolean) {
      k = 0;
    } else {
      k = i;
    } 
    int k = paramInt1 - k;
    if (paramBoolean) {
      paramInt1 = 0;
    } else {
      paramInt1 = j;
    } 
    paramInt2 -= paramInt1;
    if (paramBoolean) {
      paramInt1 = 0;
    } else {
      paramInt1 = i;
    } 
    paramInt3 -= paramInt1;
    if (paramBoolean)
      j = bool; 
    paramInt4 -= j;
    Bundle bundle = AppWidgetManager.getInstance(this.mContext).getAppWidgetOptions(this.mAppWidgetId);
    paramInt1 = 0;
    if (k != bundle.getInt("appWidgetMinWidth") || paramInt2 != bundle.getInt("appWidgetMinHeight") || paramInt3 != bundle.getInt("appWidgetMaxWidth") || paramInt4 != bundle.getInt("appWidgetMaxHeight"))
      paramInt1 = 1; 
    if (paramInt1 != 0) {
      paramBundle.putInt("appWidgetMinWidth", k);
      paramBundle.putInt("appWidgetMinHeight", paramInt2);
      paramBundle.putInt("appWidgetMaxWidth", paramInt3);
      paramBundle.putInt("appWidgetMaxHeight", paramInt4);
      updateAppWidgetOptions(paramBundle);
    } 
  }
  
  void viewDataChanged(int paramInt) {
    View view = findViewById(paramInt);
    if (view != null && view instanceof AdapterView) {
      AdapterView adapterView = (AdapterView)view;
      Adapter adapter = adapterView.getAdapter();
      if (adapter instanceof BaseAdapter) {
        ((BaseAdapter)adapter).notifyDataSetChanged();
      } else if (adapter == null && adapterView instanceof RemoteViewsAdapter.RemoteAdapterConnectionCallback) {
        ((RemoteViewsAdapter.RemoteAdapterConnectionCallback)adapterView).deferNotifyDataSetChanged();
      } 
    } 
  }
  
  private class ViewApplyListener implements RemoteViews.OnViewAppliedListener {
    private final boolean mIsReapply;
    
    private final int mLayoutId;
    
    private final RemoteViews mViews;
    
    public ViewApplyListener(RemoteViews param1RemoteViews, int param1Int, boolean param1Boolean) {
      this.mViews = param1RemoteViews;
      this.mLayoutId = param1Int;
      this.mIsReapply = param1Boolean;
    }
    
    public void onError(Exception param1Exception) {
      RemoteViews remoteViews;
      if (this.mIsReapply) {
        AppWidgetHostView appWidgetHostView1 = AppWidgetHostView.this;
        remoteViews = this.mViews;
        Context context = appWidgetHostView1.mContext;
        AppWidgetHostView appWidgetHostView2 = AppWidgetHostView.this;
        AppWidgetHostView.access$102(appWidgetHostView1, remoteViews.applyAsync(context, (ViewGroup)appWidgetHostView2, appWidgetHostView2.mAsyncExecutor, new ViewApplyListener(this.mViews, this.mLayoutId, false), AppWidgetHostView.this.mOnClickHandler));
      } else {
        AppWidgetHostView.this.applyContent((View)null, false, (Exception)remoteViews);
      } 
    }
    
    public void onViewApplied(View param1View) {
      AppWidgetHostView.this.mLayoutId = this.mLayoutId;
      AppWidgetHostView.this.mViewMode = 1;
      AppWidgetHostView.this.applyContent(param1View, this.mIsReapply, (Exception)null);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetHostView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */