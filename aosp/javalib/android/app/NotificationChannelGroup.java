package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.proto.ProtoOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public final class NotificationChannelGroup implements Parcelable {
  private static final String ATT_BLOCKED = "blocked";
  
  private static final String ATT_DESC = "desc";
  
  private static final String ATT_ID = "id";
  
  private static final String ATT_NAME = "name";
  
  private static final String ATT_USER_LOCKED = "locked";
  
  public static final Parcelable.Creator<NotificationChannelGroup> CREATOR = new Parcelable.Creator<NotificationChannelGroup>() {
      public NotificationChannelGroup createFromParcel(Parcel param1Parcel) {
        return new NotificationChannelGroup(param1Parcel);
      }
      
      public NotificationChannelGroup[] newArray(int param1Int) {
        return new NotificationChannelGroup[param1Int];
      }
    };
  
  private static final int MAX_TEXT_LENGTH = 1000;
  
  private static final String TAG_GROUP = "channelGroup";
  
  public static final int USER_LOCKED_BLOCKED_STATE = 1;
  
  private boolean mBlocked;
  
  private List<NotificationChannel> mChannels = new ArrayList<>();
  
  private String mDescription;
  
  private final String mId;
  
  private CharSequence mName;
  
  private int mUserLockedFields;
  
  protected NotificationChannelGroup(Parcel paramParcel) {
    if (paramParcel.readByte() != 0) {
      this.mId = paramParcel.readString();
    } else {
      this.mId = null;
    } 
    this.mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    if (paramParcel.readByte() != 0) {
      this.mDescription = paramParcel.readString();
    } else {
      this.mDescription = null;
    } 
    paramParcel.readParcelableList(this.mChannels, NotificationChannel.class.getClassLoader());
    this.mBlocked = paramParcel.readBoolean();
    this.mUserLockedFields = paramParcel.readInt();
  }
  
  public NotificationChannelGroup(String paramString, CharSequence paramCharSequence) {
    this.mId = getTrimmedString(paramString);
    if (paramCharSequence != null) {
      paramString = getTrimmedString(paramCharSequence.toString());
    } else {
      paramString = null;
    } 
    this.mName = paramString;
  }
  
  private String getTrimmedString(String paramString) {
    return (paramString != null && paramString.length() > 1000) ? paramString.substring(0, 1000) : paramString;
  }
  
  private static boolean safeBool(XmlPullParser paramXmlPullParser, String paramString, boolean paramBoolean) {
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    return TextUtils.isEmpty(str) ? paramBoolean : Boolean.parseBoolean(str);
  }
  
  public void addChannel(NotificationChannel paramNotificationChannel) {
    this.mChannels.add(paramNotificationChannel);
  }
  
  public NotificationChannelGroup clone() {
    NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(getId(), getName());
    notificationChannelGroup.setDescription(getDescription());
    notificationChannelGroup.setBlocked(isBlocked());
    notificationChannelGroup.setChannels(getChannels());
    notificationChannelGroup.lockFields(this.mUserLockedFields);
    return notificationChannelGroup;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, this.mId);
    paramProtoOutputStream.write(1138166333442L, this.mName.toString());
    paramProtoOutputStream.write(1138166333443L, this.mDescription);
    paramProtoOutputStream.write(1133871366148L, this.mBlocked);
    Iterator<NotificationChannel> iterator = this.mChannels.iterator();
    while (iterator.hasNext())
      ((NotificationChannel)iterator.next()).dumpDebug(paramProtoOutputStream, 2246267895813L); 
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (isBlocked() != paramObject.isBlocked() || this.mUserLockedFields != ((NotificationChannelGroup)paramObject).mUserLockedFields || !Objects.equals(getId(), paramObject.getId()) || !Objects.equals(getName(), paramObject.getName()) || !Objects.equals(getDescription(), paramObject.getDescription()) || !Objects.equals(getChannels(), paramObject.getChannels()))
      bool = false; 
    return bool;
  }
  
  public List<NotificationChannel> getChannels() {
    return this.mChannels;
  }
  
  public String getDescription() {
    return this.mDescription;
  }
  
  public String getId() {
    return this.mId;
  }
  
  public CharSequence getName() {
    return this.mName;
  }
  
  public int getUserLockedFields() {
    return this.mUserLockedFields;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { getId(), getName(), getDescription(), Boolean.valueOf(isBlocked()), getChannels(), Integer.valueOf(this.mUserLockedFields) });
  }
  
  public boolean isBlocked() {
    return this.mBlocked;
  }
  
  public void lockFields(int paramInt) {
    this.mUserLockedFields |= paramInt;
  }
  
  public void populateFromXml(XmlPullParser paramXmlPullParser) {
    setDescription(paramXmlPullParser.getAttributeValue(null, "desc"));
    setBlocked(safeBool(paramXmlPullParser, "blocked", false));
  }
  
  public void setBlocked(boolean paramBoolean) {
    this.mBlocked = paramBoolean;
  }
  
  public void setChannels(List<NotificationChannel> paramList) {
    this.mChannels = paramList;
  }
  
  public void setDescription(String paramString) {
    this.mDescription = getTrimmedString(paramString);
  }
  
  @SystemApi
  public JSONObject toJson() throws JSONException {
    JSONObject jSONObject = new JSONObject();
    jSONObject.put("id", getId());
    jSONObject.put("name", getName());
    jSONObject.put("desc", getDescription());
    jSONObject.put("blocked", isBlocked());
    jSONObject.put("locked", this.mUserLockedFields);
    return jSONObject;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NotificationChannelGroup{mId='");
    stringBuilder.append(this.mId);
    stringBuilder.append('\'');
    stringBuilder.append(", mName=");
    stringBuilder.append(this.mName);
    stringBuilder.append(", mDescription=");
    if (!TextUtils.isEmpty(this.mDescription)) {
      str = "hasDescription ";
    } else {
      str = "";
    } 
    stringBuilder.append(str);
    stringBuilder.append(", mBlocked=");
    stringBuilder.append(this.mBlocked);
    stringBuilder.append(", mChannels=");
    stringBuilder.append(this.mChannels);
    stringBuilder.append(", mUserLockedFields=");
    stringBuilder.append(this.mUserLockedFields);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void unlockFields(int paramInt) {
    this.mUserLockedFields &= paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mId != null) {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.mId);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    TextUtils.writeToParcel(this.mName, paramParcel, paramInt);
    if (this.mDescription != null) {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.mDescription);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    paramParcel.writeParcelableList(this.mChannels, paramInt);
    paramParcel.writeBoolean(this.mBlocked);
    paramParcel.writeInt(this.mUserLockedFields);
  }
  
  public void writeXml(XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.startTag(null, "channelGroup");
    paramXmlSerializer.attribute(null, "id", getId());
    if (getName() != null)
      paramXmlSerializer.attribute(null, "name", getName().toString()); 
    if (getDescription() != null)
      paramXmlSerializer.attribute(null, "desc", getDescription().toString()); 
    paramXmlSerializer.attribute(null, "blocked", Boolean.toString(isBlocked()));
    paramXmlSerializer.attribute(null, "locked", Integer.toString(this.mUserLockedFields));
    paramXmlSerializer.endTag(null, "channelGroup");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationChannelGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */