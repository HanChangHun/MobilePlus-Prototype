package android.app;

import android.graphics.drawable.Icon;

public final class Builder {
  private String mChannelId;
  
  private String mChannelName;
  
  private String mConversationId;
  
  private Icon mIcon;
  
  private String mPackage;
  
  private long mPostedTimeMs;
  
  private String mText;
  
  private String mTitle;
  
  private int mUid;
  
  private int mUserId;
  
  public NotificationHistory.HistoricalNotification build() {
    NotificationHistory.HistoricalNotification historicalNotification = new NotificationHistory.HistoricalNotification(null);
    NotificationHistory.HistoricalNotification.access$102(historicalNotification, this.mPackage);
    NotificationHistory.HistoricalNotification.access$202(historicalNotification, this.mChannelName);
    NotificationHistory.HistoricalNotification.access$302(historicalNotification, this.mChannelId);
    NotificationHistory.HistoricalNotification.access$402(historicalNotification, this.mUid);
    NotificationHistory.HistoricalNotification.access$502(historicalNotification, this.mUserId);
    NotificationHistory.HistoricalNotification.access$602(historicalNotification, this.mPostedTimeMs);
    NotificationHistory.HistoricalNotification.access$702(historicalNotification, this.mTitle);
    NotificationHistory.HistoricalNotification.access$802(historicalNotification, this.mText);
    NotificationHistory.HistoricalNotification.access$902(historicalNotification, this.mIcon);
    NotificationHistory.HistoricalNotification.access$1002(historicalNotification, this.mConversationId);
    return historicalNotification;
  }
  
  public Builder setChannelId(String paramString) {
    this.mChannelId = paramString;
    return this;
  }
  
  public Builder setChannelName(String paramString) {
    this.mChannelName = paramString;
    return this;
  }
  
  public Builder setConversationId(String paramString) {
    this.mConversationId = paramString;
    return this;
  }
  
  public Builder setIcon(Icon paramIcon) {
    this.mIcon = paramIcon;
    return this;
  }
  
  public Builder setPackage(String paramString) {
    this.mPackage = paramString;
    return this;
  }
  
  public Builder setPostedTimeMs(long paramLong) {
    this.mPostedTimeMs = paramLong;
    return this;
  }
  
  public Builder setText(String paramString) {
    this.mText = paramString;
    return this;
  }
  
  public Builder setTitle(String paramString) {
    this.mTitle = paramString;
    return this;
  }
  
  public Builder setUid(int paramInt) {
    this.mUid = paramInt;
    return this;
  }
  
  public Builder setUserId(int paramInt) {
    this.mUserId = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationHistory$HistoricalNotification$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */