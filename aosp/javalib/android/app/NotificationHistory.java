package android.app;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class NotificationHistory implements Parcelable {
  public static final Parcelable.Creator<NotificationHistory> CREATOR = new Parcelable.Creator<NotificationHistory>() {
      public NotificationHistory createFromParcel(Parcel param1Parcel) {
        return new NotificationHistory(param1Parcel);
      }
      
      public NotificationHistory[] newArray(int param1Int) {
        return new NotificationHistory[param1Int];
      }
    };
  
  private int mHistoryCount;
  
  private int mIndex = 0;
  
  private List<HistoricalNotification> mNotificationsToWrite = new ArrayList<>();
  
  private Parcel mParcel = null;
  
  private String[] mStringPool;
  
  private Set<String> mStringsToWrite = new HashSet<>();
  
  public NotificationHistory() {
    this.mHistoryCount = 0;
  }
  
  private NotificationHistory(Parcel paramParcel) {
    byte[] arrayOfByte = paramParcel.readBlob();
    paramParcel = Parcel.obtain();
    paramParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
    paramParcel.setDataPosition(0);
    this.mHistoryCount = paramParcel.readInt();
    this.mIndex = paramParcel.readInt();
    if (this.mHistoryCount > 0) {
      this.mStringPool = paramParcel.createStringArray();
      int i = paramParcel.readInt();
      int j = paramParcel.readInt();
      Parcel parcel = Parcel.obtain();
      this.mParcel = parcel;
      parcel.setDataPosition(0);
      this.mParcel.appendFrom(paramParcel, paramParcel.dataPosition(), i);
      paramParcel = this.mParcel;
      paramParcel.setDataSize(paramParcel.dataPosition());
      this.mParcel.setDataPosition(j);
    } 
  }
  
  private int findStringIndex(String paramString) {
    int i = Arrays.binarySearch((Object[])this.mStringPool, paramString);
    if (i >= 0)
      return i; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("String '");
    stringBuilder.append(paramString);
    stringBuilder.append("' is not in the string pool");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private HistoricalNotification readNotificationFromParcel(Parcel paramParcel) {
    HistoricalNotification.Builder builder = new HistoricalNotification.Builder();
    int i = paramParcel.readInt();
    if (i >= 0) {
      HistoricalNotification.Builder.access$1102(builder, this.mStringPool[i]);
    } else {
      HistoricalNotification.Builder.access$1102(builder, null);
    } 
    i = paramParcel.readInt();
    if (i >= 0) {
      builder.setChannelName(this.mStringPool[i]);
    } else {
      builder.setChannelName(null);
    } 
    i = paramParcel.readInt();
    if (i >= 0) {
      builder.setChannelId(this.mStringPool[i]);
    } else {
      builder.setChannelId(null);
    } 
    i = paramParcel.readInt();
    if (i >= 0) {
      builder.setConversationId(this.mStringPool[i]);
    } else {
      builder.setConversationId(null);
    } 
    builder.setUid(paramParcel.readInt());
    builder.setUserId(paramParcel.readInt());
    builder.setPostedTimeMs(paramParcel.readLong());
    builder.setTitle(paramParcel.readString());
    builder.setText(paramParcel.readString());
    builder.setIcon((Icon)Icon.CREATOR.createFromParcel(paramParcel));
    return builder.build();
  }
  
  private void writeNotificationToParcel(HistoricalNotification paramHistoricalNotification, Parcel paramParcel, int paramInt) {
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    if (paramHistoricalNotification.mPackage != null) {
      b1 = findStringIndex(paramHistoricalNotification.mPackage);
    } else {
      b1 = -1;
    } 
    if (paramHistoricalNotification.getChannelName() != null) {
      b2 = findStringIndex(paramHistoricalNotification.getChannelName());
    } else {
      b2 = -1;
    } 
    if (paramHistoricalNotification.getChannelId() != null) {
      b3 = findStringIndex(paramHistoricalNotification.getChannelId());
    } else {
      b3 = -1;
    } 
    if (!TextUtils.isEmpty(paramHistoricalNotification.getConversationId())) {
      b4 = findStringIndex(paramHistoricalNotification.getConversationId());
    } else {
      b4 = -1;
    } 
    paramParcel.writeInt(b1);
    paramParcel.writeInt(b2);
    paramParcel.writeInt(b3);
    paramParcel.writeInt(b4);
    paramParcel.writeInt(paramHistoricalNotification.getUid());
    paramParcel.writeInt(paramHistoricalNotification.getUserId());
    paramParcel.writeLong(paramHistoricalNotification.getPostedTimeMs());
    paramParcel.writeString(paramHistoricalNotification.getTitle());
    paramParcel.writeString(paramHistoricalNotification.getText());
    paramHistoricalNotification.getIcon().writeToParcel(paramParcel, paramInt);
  }
  
  public void addNewNotificationToWrite(HistoricalNotification paramHistoricalNotification) {
    if (paramHistoricalNotification == null)
      return; 
    this.mNotificationsToWrite.add(0, paramHistoricalNotification);
    this.mHistoryCount++;
  }
  
  public void addNotificationToWrite(HistoricalNotification paramHistoricalNotification) {
    if (paramHistoricalNotification == null)
      return; 
    this.mNotificationsToWrite.add(paramHistoricalNotification);
    this.mHistoryCount++;
  }
  
  public void addNotificationsToWrite(NotificationHistory paramNotificationHistory) {
    Iterator<HistoricalNotification> iterator = paramNotificationHistory.getNotificationsToWrite().iterator();
    while (iterator.hasNext())
      addNotificationToWrite(iterator.next()); 
    Collections.sort(this.mNotificationsToWrite, (Comparator<? super HistoricalNotification>)_$$Lambda$NotificationHistory$IawnrvjT3DfA_k_PePr01d9fBSQ.INSTANCE);
    poolStringsFromNotifications();
  }
  
  public void addPooledStrings(List<String> paramList) {
    this.mStringsToWrite.addAll(paramList);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getHistoryCount() {
    return this.mHistoryCount;
  }
  
  public HistoricalNotification getNextNotification() {
    if (!hasNextNotification())
      return null; 
    HistoricalNotification historicalNotification = readNotificationFromParcel(this.mParcel);
    this.mIndex++;
    if (!hasNextNotification()) {
      this.mParcel.recycle();
      this.mParcel = null;
    } 
    return historicalNotification;
  }
  
  public List<HistoricalNotification> getNotificationsToWrite() {
    return this.mNotificationsToWrite;
  }
  
  public String[] getPooledStringsToWrite() {
    String[] arrayOfString = this.mStringsToWrite.<String>toArray(new String[0]);
    Arrays.sort((Object[])arrayOfString);
    return arrayOfString;
  }
  
  public boolean hasNextNotification() {
    boolean bool;
    if (this.mIndex < this.mHistoryCount) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void poolStringsFromNotifications() {
    this.mStringsToWrite.clear();
    for (byte b = 0; b < this.mNotificationsToWrite.size(); b++) {
      HistoricalNotification historicalNotification = this.mNotificationsToWrite.get(b);
      this.mStringsToWrite.add(historicalNotification.getPackage());
      this.mStringsToWrite.add(historicalNotification.getChannelName());
      this.mStringsToWrite.add(historicalNotification.getChannelId());
      if (!TextUtils.isEmpty(historicalNotification.getConversationId()))
        this.mStringsToWrite.add(historicalNotification.getConversationId()); 
    } 
  }
  
  public boolean removeConversationFromWrite(String paramString1, String paramString2) {
    boolean bool = false;
    int i = this.mNotificationsToWrite.size() - 1;
    while (i >= 0) {
      HistoricalNotification historicalNotification = this.mNotificationsToWrite.get(i);
      boolean bool1 = bool;
      if (paramString1.equals(historicalNotification.getPackage())) {
        bool1 = bool;
        if (paramString2.equals(historicalNotification.getConversationId())) {
          bool1 = true;
          this.mNotificationsToWrite.remove(i);
        } 
      } 
      i--;
      bool = bool1;
    } 
    if (bool)
      poolStringsFromNotifications(); 
    return bool;
  }
  
  public boolean removeNotificationFromWrite(String paramString, long paramLong) {
    boolean bool = false;
    int i = this.mNotificationsToWrite.size() - 1;
    while (i >= 0) {
      HistoricalNotification historicalNotification = this.mNotificationsToWrite.get(i);
      boolean bool1 = bool;
      if (paramString.equals(historicalNotification.getPackage())) {
        bool1 = bool;
        if (paramLong == historicalNotification.getPostedTimeMs()) {
          bool1 = true;
          this.mNotificationsToWrite.remove(i);
        } 
      } 
      i--;
      bool = bool1;
    } 
    if (bool)
      poolStringsFromNotifications(); 
    return bool;
  }
  
  public void removeNotificationsFromWrite(String paramString) {
    for (int i = this.mNotificationsToWrite.size() - 1; i >= 0; i--) {
      if (paramString.equals(((HistoricalNotification)this.mNotificationsToWrite.get(i)).getPackage()))
        this.mNotificationsToWrite.remove(i); 
    } 
    poolStringsFromNotifications();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    Parcel parcel = Parcel.obtain();
    parcel.writeInt(this.mHistoryCount);
    parcel.writeInt(this.mIndex);
    if (this.mHistoryCount > 0) {
      String[] arrayOfString = getPooledStringsToWrite();
      this.mStringPool = arrayOfString;
      parcel.writeStringArray(arrayOfString);
      if (!this.mNotificationsToWrite.isEmpty()) {
        Parcel parcel1 = Parcel.obtain();
        try {
          parcel1.setDataPosition(0);
          for (byte b = 0; b < this.mHistoryCount; b++)
            writeNotificationToParcel(this.mNotificationsToWrite.get(b), parcel1, paramInt); 
          paramInt = parcel1.dataPosition();
          parcel.writeInt(paramInt);
          parcel.writeInt(0);
          parcel.appendFrom(parcel1, 0, paramInt);
        } finally {
          parcel1.recycle();
        } 
      } else {
        Parcel parcel1 = this.mParcel;
        if (parcel1 != null) {
          parcel.writeInt(parcel1.dataSize());
          parcel.writeInt(this.mParcel.dataPosition());
          parcel1 = this.mParcel;
          parcel.appendFrom(parcel1, 0, parcel1.dataSize());
        } else {
          throw new IllegalStateException("Either mParcel or mNotificationsToWrite must not be null");
        } 
      } 
    } 
    paramParcel.writeBlob(parcel.marshall());
  }
  
  public static final class HistoricalNotification {
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
    
    public boolean equals(Object param1Object) {
      boolean bool2;
      boolean bool1 = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if ((getIcon() == null && param1Object.getIcon() == null) || (getIcon() != null && param1Object.getIcon() != null && getIcon().sameAs(param1Object.getIcon()))) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (getUid() != param1Object.getUid() || getUserId() != param1Object.getUserId() || getPostedTimeMs() != param1Object.getPostedTimeMs() || !Objects.equals(getPackage(), param1Object.getPackage()) || !Objects.equals(getChannelName(), param1Object.getChannelName()) || !Objects.equals(getChannelId(), param1Object.getChannelId()) || !Objects.equals(getTitle(), param1Object.getTitle()) || !Objects.equals(getText(), param1Object.getText()) || !Objects.equals(getConversationId(), param1Object.getConversationId()) || !bool2)
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
    
    public Builder setChannelId(String param1String) {
      this.mChannelId = param1String;
      return this;
    }
    
    public Builder setChannelName(String param1String) {
      this.mChannelName = param1String;
      return this;
    }
    
    public Builder setConversationId(String param1String) {
      this.mConversationId = param1String;
      return this;
    }
    
    public Builder setIcon(Icon param1Icon) {
      this.mIcon = param1Icon;
      return this;
    }
    
    public Builder setPackage(String param1String) {
      this.mPackage = param1String;
      return this;
    }
    
    public Builder setPostedTimeMs(long param1Long) {
      this.mPostedTimeMs = param1Long;
      return this;
    }
    
    public Builder setText(String param1String) {
      this.mText = param1String;
      return this;
    }
    
    public Builder setTitle(String param1String) {
      this.mTitle = param1String;
      return this;
    }
    
    public Builder setUid(int param1Int) {
      this.mUid = param1Int;
      return this;
    }
    
    public Builder setUserId(int param1Int) {
      this.mUserId = param1Int;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationHistory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */