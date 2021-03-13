package android.app;

import android.widget.RemoteViews;

public class DecoratedCustomViewStyle extends Notification.Style {
  private void buildIntoRemoteViewContent(RemoteViews paramRemoteViews1, RemoteViews paramRemoteViews2, Notification.TemplateBindResult paramTemplateBindResult) {
    byte b = -1;
    if (paramRemoteViews2 != null) {
      paramRemoteViews2 = paramRemoteViews2.clone();
      paramRemoteViews1.removeAllViewsExceptId(16909225, 16908301);
      paramRemoteViews1.addView(16909225, paramRemoteViews2, 0);
      paramRemoteViews1.addFlags(1);
      b = 0;
    } 
    paramRemoteViews1.setIntTag(16909225, 16909223, b);
    paramRemoteViews1.setViewLayoutMarginEnd(16909225, Notification.Builder.access$3500(this.mBuilder).getResources().getDimensionPixelSize(17105345) + paramTemplateBindResult.getIconMarginEnd());
  }
  
  private RemoteViews makeDecoratedBigContentView() {
    RemoteViews remoteViews1;
    if ((Notification.Builder.access$400(this.mBuilder)).bigContentView == null) {
      remoteViews1 = (Notification.Builder.access$400(this.mBuilder)).contentView;
    } else {
      remoteViews1 = (Notification.Builder.access$400(this.mBuilder)).bigContentView;
    } 
    if (Notification.Builder.access$3000(this.mBuilder).size() == 0)
      return makeStandardTemplateWithCustomContent(remoteViews1); 
    Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult(null);
    RemoteViews remoteViews2 = Notification.Builder.access$4600(this.mBuilder, Notification.Builder.access$4500(this.mBuilder), templateBindResult);
    buildIntoRemoteViewContent(remoteViews2, remoteViews1, templateBindResult);
    return remoteViews2;
  }
  
  private RemoteViews makeDecoratedHeadsUpContentView() {
    RemoteViews remoteViews1;
    if ((Notification.Builder.access$400(this.mBuilder)).headsUpContentView == null) {
      remoteViews1 = (Notification.Builder.access$400(this.mBuilder)).contentView;
    } else {
      remoteViews1 = (Notification.Builder.access$400(this.mBuilder)).headsUpContentView;
    } 
    if (Notification.Builder.access$3000(this.mBuilder).size() == 0)
      return makeStandardTemplateWithCustomContent(remoteViews1); 
    Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult(null);
    RemoteViews remoteViews2 = Notification.Builder.access$4600(this.mBuilder, Notification.Builder.access$4500(this.mBuilder), templateBindResult);
    buildIntoRemoteViewContent(remoteViews2, remoteViews1, templateBindResult);
    return remoteViews2;
  }
  
  private RemoteViews makeStandardTemplateWithCustomContent(RemoteViews paramRemoteViews) {
    Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult(null);
    RemoteViews remoteViews = Notification.Builder.access$4800(this.mBuilder, Notification.Builder.access$4700(this.mBuilder), templateBindResult);
    buildIntoRemoteViewContent(remoteViews, paramRemoteViews, templateBindResult);
    return remoteViews;
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    return (paramStyle == null || getClass() != paramStyle.getClass());
  }
  
  public boolean displayCustomViewInline() {
    return true;
  }
  
  public RemoteViews makeBigContentView() {
    return makeDecoratedBigContentView();
  }
  
  public RemoteViews makeContentView(boolean paramBoolean) {
    return makeStandardTemplateWithCustomContent((Notification.Builder.access$400(this.mBuilder)).contentView);
  }
  
  public RemoteViews makeHeadsUpContentView(boolean paramBoolean) {
    return makeDecoratedHeadsUpContentView();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$DecoratedCustomViewStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */