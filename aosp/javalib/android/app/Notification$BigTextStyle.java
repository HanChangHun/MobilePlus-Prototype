package android.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Objects;

public class BigTextStyle extends Notification.Style {
  private CharSequence mBigText;
  
  public BigTextStyle() {}
  
  @Deprecated
  public BigTextStyle(Notification.Builder paramBuilder) {
    setBuilder(paramBuilder);
  }
  
  public void addExtras(Bundle paramBundle) {
    super.addExtras(paramBundle);
    paramBundle.putCharSequence("android.bigText", this.mBigText);
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    if (paramStyle == null || getClass() != paramStyle.getClass())
      return true; 
    paramStyle = paramStyle;
    return true ^ Objects.equals(String.valueOf(getBigText()), String.valueOf(paramStyle.getBigText()));
  }
  
  public BigTextStyle bigText(CharSequence paramCharSequence) {
    this.mBigText = Notification.safeCharSequence(paramCharSequence);
    return this;
  }
  
  public CharSequence getBigText() {
    return this.mBigText;
  }
  
  public RemoteViews makeBigContentView() {
    boolean bool;
    Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder).text(null);
    Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult(null);
    RemoteViews remoteViews = getStandardView(Notification.Builder.access$3200(this.mBuilder), standardTemplateParams, templateBindResult);
    remoteViews.setInt(16908791, "setImageEndMargin", templateBindResult.getIconMarginEnd());
    CharSequence charSequence1 = Notification.Builder.access$2600(this.mBuilder, this.mBigText);
    CharSequence charSequence2 = charSequence1;
    if (TextUtils.isEmpty(charSequence1))
      charSequence2 = Notification.Builder.access$2600(this.mBuilder, Notification.Builder.access$3300(this.mBuilder).getCharSequence("android.text")); 
    remoteViews.setTextViewText(16908791, Notification.Builder.access$2700(this.mBuilder, charSequence2));
    Notification.Builder.access$2800(this.mBuilder, remoteViews, 16908791, standardTemplateParams);
    if (TextUtils.isEmpty(charSequence2)) {
      bool = true;
    } else {
      bool = false;
    } 
    remoteViews.setViewVisibility(16908791, bool);
    remoteViews.setBoolean(16908791, "setHasImage", templateBindResult.isRightIconContainerVisible());
    return remoteViews;
  }
  
  public RemoteViews makeContentView(boolean paramBoolean) {
    if (paramBoolean) {
      Notification.Builder.access$2902(this.mBuilder, Notification.Builder.access$3000(this.mBuilder));
      Notification.Builder.access$3002(this.mBuilder, new ArrayList());
      RemoteViews remoteViews = makeBigContentView();
      Notification.Builder.access$3002(this.mBuilder, Notification.Builder.access$2900(this.mBuilder));
      Notification.Builder.access$2902(this.mBuilder, null);
      return remoteViews;
    } 
    return super.makeContentView(paramBoolean);
  }
  
  public RemoteViews makeHeadsUpContentView(boolean paramBoolean) {
    return (paramBoolean && Notification.Builder.access$3000(this.mBuilder).size() > 0) ? makeBigContentView() : super.makeHeadsUpContentView(paramBoolean);
  }
  
  protected void restoreFromExtras(Bundle paramBundle) {
    super.restoreFromExtras(paramBundle);
    this.mBigText = paramBundle.getCharSequence("android.bigText");
  }
  
  public BigTextStyle setBigContentTitle(CharSequence paramCharSequence) {
    internalSetBigContentTitle(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public BigTextStyle setSummaryText(CharSequence paramCharSequence) {
    internalSetSummaryText(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$BigTextStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */