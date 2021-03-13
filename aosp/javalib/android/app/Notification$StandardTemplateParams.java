package android.app;

import android.os.Bundle;

class StandardTemplateParams {
  boolean allowColorization = true;
  
  boolean forceDefaultColor = false;
  
  boolean hasProgress = true;
  
  CharSequence headerTextSecondary;
  
  boolean hideLargeIcon;
  
  boolean hideReplyIcon;
  
  int maxRemoteInputHistory = 3;
  
  CharSequence summaryText;
  
  CharSequence text;
  
  CharSequence title;
  
  private StandardTemplateParams() {}
  
  final StandardTemplateParams disallowColorization() {
    this.allowColorization = false;
    return this;
  }
  
  final StandardTemplateParams fillTextsFrom(Notification.Builder paramBuilder) {
    Bundle bundle = (Notification.Builder.access$400(paramBuilder)).extras;
    this.title = Notification.Builder.access$2600(paramBuilder, bundle.getCharSequence("android.title"));
    this.text = Notification.Builder.access$2600(paramBuilder, bundle.getCharSequence("android.text"));
    this.summaryText = bundle.getCharSequence("android.subText");
    return this;
  }
  
  final StandardTemplateParams forceDefaultColor() {
    this.forceDefaultColor = true;
    return this;
  }
  
  final StandardTemplateParams hasProgress(boolean paramBoolean) {
    this.hasProgress = paramBoolean;
    return this;
  }
  
  final StandardTemplateParams headerTextSecondary(CharSequence paramCharSequence) {
    this.headerTextSecondary = paramCharSequence;
    return this;
  }
  
  final StandardTemplateParams hideLargeIcon(boolean paramBoolean) {
    this.hideLargeIcon = paramBoolean;
    return this;
  }
  
  final StandardTemplateParams hideReplyIcon(boolean paramBoolean) {
    this.hideReplyIcon = paramBoolean;
    return this;
  }
  
  final StandardTemplateParams reset() {
    this.hasProgress = true;
    this.title = null;
    this.text = null;
    this.summaryText = null;
    this.headerTextSecondary = null;
    this.maxRemoteInputHistory = 3;
    this.allowColorization = true;
    this.forceDefaultColor = false;
    return this;
  }
  
  public StandardTemplateParams setMaxRemoteInputHistory(int paramInt) {
    this.maxRemoteInputHistory = paramInt;
    return this;
  }
  
  final StandardTemplateParams summaryText(CharSequence paramCharSequence) {
    this.summaryText = paramCharSequence;
    return this;
  }
  
  final StandardTemplateParams text(CharSequence paramCharSequence) {
    this.text = paramCharSequence;
    return this;
  }
  
  final StandardTemplateParams title(CharSequence paramCharSequence) {
    this.title = paramCharSequence;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$StandardTemplateParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */