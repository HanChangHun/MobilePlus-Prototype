package android.appwidget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

class ViewApplyListener implements RemoteViews.OnViewAppliedListener {
  private final boolean mIsReapply;
  
  private final int mLayoutId;
  
  private final RemoteViews mViews;
  
  public ViewApplyListener(RemoteViews paramRemoteViews, int paramInt, boolean paramBoolean) {
    this.mViews = paramRemoteViews;
    this.mLayoutId = paramInt;
    this.mIsReapply = paramBoolean;
  }
  
  public void onError(Exception paramException) {
    RemoteViews remoteViews;
    if (this.mIsReapply) {
      AppWidgetHostView appWidgetHostView1 = AppWidgetHostView.this;
      remoteViews = this.mViews;
      Context context = appWidgetHostView1.mContext;
      AppWidgetHostView appWidgetHostView2 = AppWidgetHostView.this;
      AppWidgetHostView.access$102(appWidgetHostView1, remoteViews.applyAsync(context, (ViewGroup)appWidgetHostView2, AppWidgetHostView.access$200(appWidgetHostView2), new ViewApplyListener(this.mViews, this.mLayoutId, false), AppWidgetHostView.access$300(AppWidgetHostView.this)));
    } else {
      AppWidgetHostView.access$000(AppWidgetHostView.this, null, false, (Exception)remoteViews);
    } 
  }
  
  public void onViewApplied(View paramView) {
    AppWidgetHostView.this.mLayoutId = this.mLayoutId;
    AppWidgetHostView.this.mViewMode = 1;
    AppWidgetHostView.access$000(AppWidgetHostView.this, paramView, this.mIsReapply, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetHostView$ViewApplyListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */