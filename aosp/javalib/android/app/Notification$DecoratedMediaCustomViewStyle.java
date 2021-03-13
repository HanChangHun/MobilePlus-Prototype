package android.app;

import android.widget.RemoteViews;

public class DecoratedMediaCustomViewStyle extends Notification.MediaStyle {
  private RemoteViews buildIntoRemoteView(RemoteViews paramRemoteViews1, int paramInt, RemoteViews paramRemoteViews2) {
    if (paramRemoteViews2 != null) {
      paramRemoteViews2 = paramRemoteViews2.clone();
      paramRemoteViews2.overrideTextColors(this.mBuilder.getPrimaryTextColor(this.mBuilder.mParams));
      paramRemoteViews1.removeAllViews(paramInt);
      paramRemoteViews1.addView(paramInt, paramRemoteViews2);
      paramRemoteViews1.addFlags(1);
    } 
    return paramRemoteViews1;
  }
  
  private RemoteViews makeBigContentViewWithCustomContent(RemoteViews paramRemoteViews) {
    RemoteViews remoteViews = super.makeBigContentView();
    return (remoteViews != null) ? buildIntoRemoteView(remoteViews, 16909225, paramRemoteViews) : ((paramRemoteViews != (Notification.Builder.access$400(this.mBuilder)).contentView) ? buildIntoRemoteView(super.makeContentView(false), 16909222, paramRemoteViews) : null);
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    return (paramStyle == null || getClass() != paramStyle.getClass());
  }
  
  public boolean displayCustomViewInline() {
    return true;
  }
  
  public RemoteViews makeBigContentView() {
    RemoteViews remoteViews;
    if ((Notification.Builder.access$400(this.mBuilder)).bigContentView != null) {
      remoteViews = (Notification.Builder.access$400(this.mBuilder)).bigContentView;
    } else {
      remoteViews = (Notification.Builder.access$400(this.mBuilder)).contentView;
    } 
    return makeBigContentViewWithCustomContent(remoteViews);
  }
  
  public RemoteViews makeContentView(boolean paramBoolean) {
    return buildIntoRemoteView(super.makeContentView(false), 16909222, (Notification.Builder.access$400(this.mBuilder)).contentView);
  }
  
  public RemoteViews makeHeadsUpContentView(boolean paramBoolean) {
    RemoteViews remoteViews;
    if ((Notification.Builder.access$400(this.mBuilder)).headsUpContentView != null) {
      remoteViews = (Notification.Builder.access$400(this.mBuilder)).headsUpContentView;
    } else {
      remoteViews = (Notification.Builder.access$400(this.mBuilder)).contentView;
    } 
    return makeBigContentViewWithCustomContent(remoteViews);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$DecoratedMediaCustomViewStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */