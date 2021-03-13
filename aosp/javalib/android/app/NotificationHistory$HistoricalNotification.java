package android.app;

import android.graphics.drawable.Icon;
import java.util.Objects;

public final class HistoricalNotification {
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
  
  private HistoricalNotification() {}
  
  public boolean equals(Object paramObject) {
    boolean bool2;
    boolean bool1 = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if ((getIcon() == null && paramObject.getIcon() == null) || (getIcon() != null && paramObject.getIcon() != null && getIcon().sameAs(paramObject.getIcon()))) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (getUid() != paramObject.getUid() || getUserId() != paramObject.getUserId() || getPostedTimeMs() != paramObject.getPostedTimeMs() || !Objects.equals(getPackage(), paramObject.getPackage()) || !Objects.equals(getChannelName(), paramObject.getChannelName()) || !Objects.equals(getChannelId(), paramObject.getChannelId()) || !Objects.equals(getTitle(), paramObject.getTitle()) || !Objects.equals(getText(), paramObject.getText()) || !Objects.equals(getConversationId(), paramObject.getConversationId()) || !bool2)
      bool1 = false; 
    return bool1;
  }
  
  public String getChannelId() {
    return this.mChannelId;
  }
  
  public String getChannelName() {
    return this.mChannelName;
  }
  
  public String getConversationId() {
    return this.mConversationId;
  }
  
  public Icon getIcon() {
    return this.mIcon;
  }
  
  public String getKey() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mPackage);
    stringBuilder.append("|");
    stringBuilder.append(this.mUid);
    stringBuilder.append("|");
    stringBuilder.append(this.mPostedTimeMs);
    return stringBuilder.toString();
  }
  
  public String getPackage() {
    return this.mPackage;
  }
  
  public long getPostedTimeMs() {
    return this.mPostedTimeMs;
  }
  
  public String getText() {
    return this.mText;
  }
  
  public String getTitle() {
    return this.mTitle;
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  public int getUserId() {
    return this.mUserId;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { getPackage(), getChannelName(), getChannelId(), Integer.valueOf(getUid()), Integer.valueOf(getUserId()), Long.valueOf(getPostedTimeMs()), getTitle(), getText(), getIcon(), getConversationId() });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("HistoricalNotification{key='");
    stringBuilder.append(getKey());
    stringBuilder.append('\'');
    stringBuilder.append(", mChannelName='");
    stringBuilder.append(this.mChannelName);
    stringBuilder.append('\'');
    stringBuilder.append(", mChannelId='");
    stringBuilder.append(this.mChannelId);
    stringBuilder.append('\'');
    stringBuilder.append(", mUserId=");
    stringBuilder.append(this.mUserId);
    stringBuilder.append(", mUid=");
    stringBuilder.append(this.mUid);
    stringBuilder.append(", mTitle='");
    stringBuilder.append(this.mTitle);
    stringBuilder.append('\'');
    stringBuilder.append(", mText='");
    stringBuilder.append(this.mText);
    stringBuilder.append('\'');
    stringBuilder.append(", mIcon=");
    stringBuilder.append(this.mIcon);
    stringBuilder.append(", mPostedTimeMs=");
    stringBuilder.append(this.mPostedTimeMs);
    stringBuilder.append(", mConversationId=");
    stringBuilder.append(this.mConversationId);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public static final class Builder {
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
      NotificationHistory.HistoricalNotification historicalNotification = new NotificationHistory.HistoricalNotification();
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
    
    public Builder setChannelId(String param2String) {
      this.mChannelId = param2String;
      return this;
    }
    
    public Builder setChannelName(String param2String) {
      this.mChannelName = param2String;
      return this;
    }
    
    public Builder setConversationId(String param2String) {
      this.mConversationId = param2String;
      return this;
    }
    
    public Builder setIcon(Icon param2Icon) {
      this.mIcon = param2Icon;
      return this;
    }
    
    public Builder setPackage(String param2String) {
      this.mPackage = param2String;
      return this;
    }
    
    public Builder setPostedTimeMs(long param2Long) {
      this.mPostedTimeMs = param2Long;
      return this;
    }
    
    public Builder setText(String param2String) {
      this.mText = param2String;
      return this;
    }
    
    public Builder setTitle(String param2String) {
      this.mTitle = param2String;
      return this;
    }
    
    public Builder setUid(int param2Int) {
      this.mUid = param2Int;
      return this;
    }
    
    public Builder setUserId(int param2Int) {
      this.mUserId = param2Int;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationHistory$HistoricalNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */