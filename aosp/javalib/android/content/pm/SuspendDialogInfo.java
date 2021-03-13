package android.content.pm;

import android.annotation.SystemApi;
import android.content.res.ResourceId;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Slog;
import com.android.internal.util.Preconditions;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

@SystemApi
public final class SuspendDialogInfo implements Parcelable {
  public static final int BUTTON_ACTION_MORE_DETAILS = 0;
  
  public static final int BUTTON_ACTION_UNSUSPEND = 1;
  
  public static final Parcelable.Creator<SuspendDialogInfo> CREATOR;
  
  private static final String TAG = SuspendDialogInfo.class.getSimpleName();
  
  private static final String XML_ATTR_BUTTON_ACTION = "buttonAction";
  
  private static final String XML_ATTR_BUTTON_TEXT_RES_ID = "buttonTextResId";
  
  private static final String XML_ATTR_DIALOG_MESSAGE = "dialogMessage";
  
  private static final String XML_ATTR_DIALOG_MESSAGE_RES_ID = "dialogMessageResId";
  
  private static final String XML_ATTR_ICON_RES_ID = "iconResId";
  
  private static final String XML_ATTR_TITLE_RES_ID = "titleResId";
  
  private final String mDialogMessage;
  
  private final int mDialogMessageResId;
  
  private final int mIconResId;
  
  private final int mNeutralButtonAction;
  
  private final int mNeutralButtonTextResId;
  
  private final int mTitleResId;
  
  static {
    CREATOR = new Parcelable.Creator<SuspendDialogInfo>() {
        public SuspendDialogInfo createFromParcel(Parcel param1Parcel) {
          return new SuspendDialogInfo(param1Parcel);
        }
        
        public SuspendDialogInfo[] newArray(int param1Int) {
          return new SuspendDialogInfo[param1Int];
        }
      };
  }
  
  SuspendDialogInfo(Builder paramBuilder) {
    String str;
    this.mIconResId = paramBuilder.mIconResId;
    this.mTitleResId = paramBuilder.mTitleResId;
    int i = paramBuilder.mDialogMessageResId;
    this.mDialogMessageResId = i;
    if (i == 0) {
      str = paramBuilder.mDialogMessage;
    } else {
      str = null;
    } 
    this.mDialogMessage = str;
    this.mNeutralButtonTextResId = paramBuilder.mNeutralButtonTextResId;
    this.mNeutralButtonAction = paramBuilder.mNeutralButtonAction;
  }
  
  private SuspendDialogInfo(Parcel paramParcel) {
    this.mIconResId = paramParcel.readInt();
    this.mTitleResId = paramParcel.readInt();
    this.mDialogMessageResId = paramParcel.readInt();
    this.mDialogMessage = paramParcel.readString();
    this.mNeutralButtonTextResId = paramParcel.readInt();
    this.mNeutralButtonAction = paramParcel.readInt();
  }
  
  public static SuspendDialogInfo restoreFromXml(XmlPullParser paramXmlPullParser) {
    Builder builder = new Builder();
    try {
      int i = XmlUtils.readIntAttribute(paramXmlPullParser, "iconResId", 0);
      int j = XmlUtils.readIntAttribute(paramXmlPullParser, "titleResId", 0);
      int k = XmlUtils.readIntAttribute(paramXmlPullParser, "buttonTextResId", 0);
      int m = XmlUtils.readIntAttribute(paramXmlPullParser, "buttonAction", 0);
      int n = XmlUtils.readIntAttribute(paramXmlPullParser, "dialogMessageResId", 0);
      String str = XmlUtils.readStringAttribute(paramXmlPullParser, "dialogMessage");
      if (i != 0)
        builder.setIcon(i); 
      if (j != 0)
        builder.setTitle(j); 
      if (k != 0)
        builder.setNeutralButtonText(k); 
      if (n != 0) {
        builder.setMessage(n);
      } else if (str != null) {
        builder.setMessage(str);
      } 
      builder.setNeutralButtonAction(m);
    } catch (Exception exception) {
      Slog.e(TAG, "Exception while parsing from xml. Some fields may default", exception);
    } 
    return builder.build();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof SuspendDialogInfo))
      return false; 
    paramObject = paramObject;
    if (this.mIconResId != ((SuspendDialogInfo)paramObject).mIconResId || this.mTitleResId != ((SuspendDialogInfo)paramObject).mTitleResId || this.mDialogMessageResId != ((SuspendDialogInfo)paramObject).mDialogMessageResId || this.mNeutralButtonTextResId != ((SuspendDialogInfo)paramObject).mNeutralButtonTextResId || this.mNeutralButtonAction != ((SuspendDialogInfo)paramObject).mNeutralButtonAction || !Objects.equals(this.mDialogMessage, ((SuspendDialogInfo)paramObject).mDialogMessage))
      bool = false; 
    return bool;
  }
  
  public String getDialogMessage() {
    return this.mDialogMessage;
  }
  
  public int getDialogMessageResId() {
    return this.mDialogMessageResId;
  }
  
  public int getIconResId() {
    return this.mIconResId;
  }
  
  public int getNeutralButtonAction() {
    return this.mNeutralButtonAction;
  }
  
  public int getNeutralButtonTextResId() {
    return this.mNeutralButtonTextResId;
  }
  
  public int getTitleResId() {
    return this.mTitleResId;
  }
  
  public int hashCode() {
    return ((((this.mIconResId * 31 + this.mTitleResId) * 31 + this.mNeutralButtonTextResId) * 31 + this.mDialogMessageResId) * 31 + Objects.hashCode(this.mDialogMessage)) * 31 + this.mNeutralButtonAction;
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    int i = this.mIconResId;
    if (i != 0)
      XmlUtils.writeIntAttribute(paramXmlSerializer, "iconResId", i); 
    i = this.mTitleResId;
    if (i != 0)
      XmlUtils.writeIntAttribute(paramXmlSerializer, "titleResId", i); 
    i = this.mDialogMessageResId;
    if (i != 0) {
      XmlUtils.writeIntAttribute(paramXmlSerializer, "dialogMessageResId", i);
    } else {
      XmlUtils.writeStringAttribute(paramXmlSerializer, "dialogMessage", this.mDialogMessage);
    } 
    i = this.mNeutralButtonTextResId;
    if (i != 0)
      XmlUtils.writeIntAttribute(paramXmlSerializer, "buttonTextResId", i); 
    XmlUtils.writeIntAttribute(paramXmlSerializer, "buttonAction", this.mNeutralButtonAction);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("SuspendDialogInfo: {");
    if (this.mIconResId != 0) {
      stringBuilder.append("mIconId = 0x");
      stringBuilder.append(Integer.toHexString(this.mIconResId));
      stringBuilder.append(" ");
    } 
    if (this.mTitleResId != 0) {
      stringBuilder.append("mTitleResId = 0x");
      stringBuilder.append(Integer.toHexString(this.mTitleResId));
      stringBuilder.append(" ");
    } 
    if (this.mNeutralButtonTextResId != 0) {
      stringBuilder.append("mNeutralButtonTextResId = 0x");
      stringBuilder.append(Integer.toHexString(this.mNeutralButtonTextResId));
      stringBuilder.append(" ");
    } 
    if (this.mDialogMessageResId != 0) {
      stringBuilder.append("mDialogMessageResId = 0x");
      stringBuilder.append(Integer.toHexString(this.mDialogMessageResId));
      stringBuilder.append(" ");
    } else if (this.mDialogMessage != null) {
      stringBuilder.append("mDialogMessage = \"");
      stringBuilder.append(this.mDialogMessage);
      stringBuilder.append("\" ");
    } 
    stringBuilder.append("mNeutralButtonAction = ");
    stringBuilder.append(this.mNeutralButtonAction);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mIconResId);
    paramParcel.writeInt(this.mTitleResId);
    paramParcel.writeInt(this.mDialogMessageResId);
    paramParcel.writeString(this.mDialogMessage);
    paramParcel.writeInt(this.mNeutralButtonTextResId);
    paramParcel.writeInt(this.mNeutralButtonAction);
  }
  
  public static final class Builder {
    private String mDialogMessage;
    
    private int mDialogMessageResId = 0;
    
    private int mIconResId = 0;
    
    private int mNeutralButtonAction = 0;
    
    private int mNeutralButtonTextResId = 0;
    
    private int mTitleResId = 0;
    
    public SuspendDialogInfo build() {
      return new SuspendDialogInfo(this);
    }
    
    public Builder setIcon(int param1Int) {
      Preconditions.checkArgument(ResourceId.isValid(param1Int), "Invalid resource id provided");
      this.mIconResId = param1Int;
      return this;
    }
    
    public Builder setMessage(int param1Int) {
      Preconditions.checkArgument(ResourceId.isValid(param1Int), "Invalid resource id provided");
      this.mDialogMessageResId = param1Int;
      return this;
    }
    
    public Builder setMessage(String param1String) {
      Preconditions.checkStringNotEmpty(param1String, "Message cannot be null or empty");
      this.mDialogMessage = param1String;
      return this;
    }
    
    public Builder setNeutralButtonAction(int param1Int) {
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (param1Int != 0)
        if (param1Int == 1) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      Preconditions.checkArgument(bool2, "Invalid button action");
      this.mNeutralButtonAction = param1Int;
      return this;
    }
    
    public Builder setNeutralButtonText(int param1Int) {
      Preconditions.checkArgument(ResourceId.isValid(param1Int), "Invalid resource id provided");
      this.mNeutralButtonTextResId = param1Int;
      return this;
    }
    
    public Builder setTitle(int param1Int) {
      Preconditions.checkArgument(ResourceId.isValid(param1Int), "Invalid resource id provided");
      this.mTitleResId = param1Int;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ButtonAction {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SuspendDialogInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */