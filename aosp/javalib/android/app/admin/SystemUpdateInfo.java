package android.app.admin;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public final class SystemUpdateInfo implements Parcelable {
  private static final String ATTR_ORIGINAL_BUILD = "original-build";
  
  private static final String ATTR_RECEIVED_TIME = "received-time";
  
  private static final String ATTR_SECURITY_PATCH_STATE = "security-patch-state";
  
  public static final Parcelable.Creator<SystemUpdateInfo> CREATOR = new Parcelable.Creator<SystemUpdateInfo>() {
      public SystemUpdateInfo createFromParcel(Parcel param1Parcel) {
        return new SystemUpdateInfo(param1Parcel);
      }
      
      public SystemUpdateInfo[] newArray(int param1Int) {
        return new SystemUpdateInfo[param1Int];
      }
    };
  
  public static final int SECURITY_PATCH_STATE_FALSE = 1;
  
  public static final int SECURITY_PATCH_STATE_TRUE = 2;
  
  public static final int SECURITY_PATCH_STATE_UNKNOWN = 0;
  
  private final long mReceivedTime;
  
  private final int mSecurityPatchState;
  
  private SystemUpdateInfo(long paramLong, int paramInt) {
    this.mReceivedTime = paramLong;
    this.mSecurityPatchState = paramInt;
  }
  
  private SystemUpdateInfo(Parcel paramParcel) {
    this.mReceivedTime = paramParcel.readLong();
    this.mSecurityPatchState = paramParcel.readInt();
  }
  
  public static SystemUpdateInfo of(long paramLong) {
    SystemUpdateInfo systemUpdateInfo;
    if (paramLong == -1L) {
      systemUpdateInfo = null;
    } else {
      systemUpdateInfo = new SystemUpdateInfo(paramLong, 0);
    } 
    return systemUpdateInfo;
  }
  
  public static SystemUpdateInfo of(long paramLong, boolean paramBoolean) {
    SystemUpdateInfo systemUpdateInfo;
    if (paramLong == -1L) {
      systemUpdateInfo = null;
    } else {
      boolean bool;
      if (paramBoolean) {
        bool = true;
      } else {
        bool = true;
      } 
      systemUpdateInfo = new SystemUpdateInfo(paramLong, bool);
    } 
    return systemUpdateInfo;
  }
  
  public static SystemUpdateInfo readFromXml(XmlPullParser paramXmlPullParser) {
    String str = paramXmlPullParser.getAttributeValue(null, "original-build");
    if (!Build.FINGERPRINT.equals(str))
      return null; 
    long l = Long.parseLong(paramXmlPullParser.getAttributeValue(null, "received-time"));
    return new SystemUpdateInfo(l, Integer.parseInt(paramXmlPullParser.getAttributeValue(null, "security-patch-state")));
  }
  
  private static String securityPatchStateToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt == 2)
          return "true"; 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unrecognized security patch state: ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      return "false";
    } 
    return "unknown";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mReceivedTime != ((SystemUpdateInfo)paramObject).mReceivedTime || this.mSecurityPatchState != ((SystemUpdateInfo)paramObject).mSecurityPatchState)
      bool = false; 
    return bool;
  }
  
  public long getReceivedTime() {
    return this.mReceivedTime;
  }
  
  public int getSecurityPatchState() {
    return this.mSecurityPatchState;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Long.valueOf(this.mReceivedTime), Integer.valueOf(this.mSecurityPatchState) });
  }
  
  public String toString() {
    return String.format("SystemUpdateInfo (receivedTime = %d, securityPatchState = %s)", new Object[] { Long.valueOf(this.mReceivedTime), securityPatchStateToString(this.mSecurityPatchState) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(getReceivedTime());
    paramParcel.writeInt(getSecurityPatchState());
  }
  
  public void writeToXml(XmlSerializer paramXmlSerializer, String paramString) throws IOException {
    paramXmlSerializer.startTag(null, paramString);
    paramXmlSerializer.attribute(null, "received-time", String.valueOf(this.mReceivedTime));
    paramXmlSerializer.attribute(null, "security-patch-state", String.valueOf(this.mSecurityPatchState));
    paramXmlSerializer.attribute(null, "original-build", Build.FINGERPRINT);
    paramXmlSerializer.endTag(null, paramString);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SecurityPatchState {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdateInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */