package android.app;

import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;

public abstract class Style {
  static final int MAX_REMOTE_INPUT_HISTORY_LINES = 3;
  
  private CharSequence mBigContentTitle;
  
  protected Notification.Builder mBuilder;
  
  protected CharSequence mSummaryText = null;
  
  protected boolean mSummaryTextSet = false;
  
  public void addExtras(Bundle paramBundle) {
    if (this.mSummaryTextSet)
      paramBundle.putCharSequence("android.summaryText", this.mSummaryText); 
    CharSequence charSequence = this.mBigContentTitle;
    if (charSequence != null)
      paramBundle.putCharSequence("android.title.big", charSequence); 
    paramBundle.putString("android.template", getClass().getName());
  }
  
  public abstract boolean areNotificationsVisiblyDifferent(Style paramStyle);
  
  public Notification build() {
    checkBuilder();
    return this.mBuilder.build();
  }
  
  public Notification buildStyled(Notification paramNotification) {
    addExtras(paramNotification.extras);
    return paramNotification;
  }
  
  protected void checkBuilder() {
    if (this.mBuilder != null)
      return; 
    throw new IllegalArgumentException("Style requires a valid Builder object");
  }
  
  public boolean displayCustomViewInline() {
    return false;
  }
  
  public CharSequence getHeadsUpStatusBarText() {
    return null;
  }
  
  protected RemoteViews getStandardView(int paramInt) {
    return getStandardView(paramInt, this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder), null);
  }
  
  protected RemoteViews getStandardView(int paramInt, Notification.StandardTemplateParams paramStandardTemplateParams, Notification.TemplateBindResult paramTemplateBindResult) {
    checkBuilder();
    CharSequence charSequence2 = this.mBigContentTitle;
    if (charSequence2 != null)
      paramStandardTemplateParams.title = charSequence2; 
    RemoteViews remoteViews = Notification.Builder.access$2400(this.mBuilder, paramInt, paramStandardTemplateParams, paramTemplateBindResult);
    CharSequence charSequence1 = this.mBigContentTitle;
    if (charSequence1 != null && charSequence1.equals("")) {
      remoteViews.setViewVisibility(16909124, 8);
    } else {
      remoteViews.setViewVisibility(16909124, 0);
    } 
    return remoteViews;
  }
  
  protected boolean hasProgress() {
    return true;
  }
  
  public boolean hasSummaryInHeader() {
    return true;
  }
  
  protected void internalSetBigContentTitle(CharSequence paramCharSequence) {
    this.mBigContentTitle = paramCharSequence;
  }
  
  protected void internalSetSummaryText(CharSequence paramCharSequence) {
    this.mSummaryText = paramCharSequence;
    this.mSummaryTextSet = true;
  }
  
  public RemoteViews makeBigContentView() {
    return null;
  }
  
  public RemoteViews makeContentView(boolean paramBoolean) {
    return null;
  }
  
  public RemoteViews makeHeadsUpContentView(boolean paramBoolean) {
    return null;
  }
  
  public void purgeResources() {}
  
  public void reduceImageSizes(Context paramContext) {}
  
  protected void restoreFromExtras(Bundle paramBundle) {
    if (paramBundle.containsKey("android.summaryText")) {
      this.mSummaryText = paramBundle.getCharSequence("android.summaryText");
      this.mSummaryTextSet = true;
    } 
    if (paramBundle.containsKey("android.title.big"))
      this.mBigContentTitle = paramBundle.getCharSequence("android.title.big"); 
  }
  
  public void setBuilder(Notification.Builder paramBuilder) {
    if (this.mBuilder != paramBuilder) {
      this.mBuilder = paramBuilder;
      if (paramBuilder != null)
        paramBuilder.setStyle(this); 
    } 
  }
  
  public void validate(Context paramContext) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$Style.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */