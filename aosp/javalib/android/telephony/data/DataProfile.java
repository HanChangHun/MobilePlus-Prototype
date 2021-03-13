package android.telephony.data;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.telephony.util.TelephonyUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

@SystemApi
public final class DataProfile implements Parcelable {
  public static final Parcelable.Creator<DataProfile> CREATOR = new Parcelable.Creator<DataProfile>() {
      public DataProfile createFromParcel(Parcel param1Parcel) {
        return new DataProfile(param1Parcel);
      }
      
      public DataProfile[] newArray(int param1Int) {
        return new DataProfile[param1Int];
      }
    };
  
  public static final int TYPE_3GPP = 1;
  
  public static final int TYPE_3GPP2 = 2;
  
  public static final int TYPE_COMMON = 0;
  
  private final String mApn;
  
  private final int mAuthType;
  
  private final int mBearerBitmask;
  
  private final boolean mEnabled;
  
  private final int mMaxConnections;
  
  private final int mMaxConnectionsTime;
  
  private final int mMtuV4;
  
  private final int mMtuV6;
  
  private final String mPassword;
  
  private final boolean mPersistent;
  
  private final boolean mPreferred;
  
  private final int mProfileId;
  
  private final int mProtocolType;
  
  private final int mRoamingProtocolType;
  
  private final int mSupportedApnTypesBitmask;
  
  private final int mType;
  
  private final String mUserName;
  
  private final int mWaitTime;
  
  private DataProfile(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2, String paramString3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean1, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, boolean paramBoolean2, boolean paramBoolean3) {
    this.mProfileId = paramInt1;
    this.mApn = paramString1;
    this.mProtocolType = paramInt2;
    if (paramInt3 == -1) {
      if (TextUtils.isEmpty(paramString2)) {
        paramInt1 = 0;
      } else {
        paramInt1 = 3;
      } 
    } else {
      paramInt1 = paramInt3;
    } 
    this.mAuthType = paramInt1;
    this.mUserName = paramString2;
    this.mPassword = paramString3;
    this.mType = paramInt4;
    this.mMaxConnectionsTime = paramInt5;
    this.mMaxConnections = paramInt6;
    this.mWaitTime = paramInt7;
    this.mEnabled = paramBoolean1;
    this.mSupportedApnTypesBitmask = paramInt8;
    this.mRoamingProtocolType = paramInt9;
    this.mBearerBitmask = paramInt10;
    this.mMtuV4 = paramInt11;
    this.mMtuV6 = paramInt12;
    this.mPersistent = paramBoolean2;
    this.mPreferred = paramBoolean3;
  }
  
  private DataProfile(Parcel paramParcel) {
    this.mProfileId = paramParcel.readInt();
    this.mApn = paramParcel.readString();
    this.mProtocolType = paramParcel.readInt();
    this.mAuthType = paramParcel.readInt();
    this.mUserName = paramParcel.readString();
    this.mPassword = paramParcel.readString();
    this.mType = paramParcel.readInt();
    this.mMaxConnectionsTime = paramParcel.readInt();
    this.mMaxConnections = paramParcel.readInt();
    this.mWaitTime = paramParcel.readInt();
    this.mEnabled = paramParcel.readBoolean();
    this.mSupportedApnTypesBitmask = paramParcel.readInt();
    this.mRoamingProtocolType = paramParcel.readInt();
    this.mBearerBitmask = paramParcel.readInt();
    this.mMtuV4 = paramParcel.readInt();
    this.mMtuV6 = paramParcel.readInt();
    this.mPersistent = paramParcel.readBoolean();
    this.mPreferred = paramParcel.readBoolean();
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
    if (this.mProfileId != ((DataProfile)paramObject).mProfileId || this.mProtocolType != ((DataProfile)paramObject).mProtocolType || this.mAuthType != ((DataProfile)paramObject).mAuthType || this.mType != ((DataProfile)paramObject).mType || this.mMaxConnectionsTime != ((DataProfile)paramObject).mMaxConnectionsTime || this.mMaxConnections != ((DataProfile)paramObject).mMaxConnections || this.mWaitTime != ((DataProfile)paramObject).mWaitTime || this.mEnabled != ((DataProfile)paramObject).mEnabled || this.mSupportedApnTypesBitmask != ((DataProfile)paramObject).mSupportedApnTypesBitmask || this.mRoamingProtocolType != ((DataProfile)paramObject).mRoamingProtocolType || this.mBearerBitmask != ((DataProfile)paramObject).mBearerBitmask || this.mMtuV4 != ((DataProfile)paramObject).mMtuV4 || this.mMtuV6 != ((DataProfile)paramObject).mMtuV6 || this.mPersistent != ((DataProfile)paramObject).mPersistent || this.mPreferred != ((DataProfile)paramObject).mPreferred || !Objects.equals(this.mApn, ((DataProfile)paramObject).mApn) || !Objects.equals(this.mUserName, ((DataProfile)paramObject).mUserName) || !Objects.equals(this.mPassword, ((DataProfile)paramObject).mPassword))
      bool = false; 
    return bool;
  }
  
  public String getApn() {
    return this.mApn;
  }
  
  public int getAuthType() {
    return this.mAuthType;
  }
  
  public int getBearerBitmask() {
    return this.mBearerBitmask;
  }
  
  public int getMaxConnections() {
    return this.mMaxConnections;
  }
  
  public int getMaxConnectionsTime() {
    return this.mMaxConnectionsTime;
  }
  
  @Deprecated
  public int getMtu() {
    return this.mMtuV4;
  }
  
  public int getMtuV4() {
    return this.mMtuV4;
  }
  
  public int getMtuV6() {
    return this.mMtuV6;
  }
  
  public String getPassword() {
    return this.mPassword;
  }
  
  public int getProfileId() {
    return this.mProfileId;
  }
  
  public int getProtocolType() {
    return this.mProtocolType;
  }
  
  public int getRoamingProtocolType() {
    return this.mRoamingProtocolType;
  }
  
  public int getSupportedApnTypesBitmask() {
    return this.mSupportedApnTypesBitmask;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public String getUserName() {
    return this.mUserName;
  }
  
  public int getWaitTime() {
    return this.mWaitTime;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(this.mProfileId), this.mApn, Integer.valueOf(this.mProtocolType), Integer.valueOf(this.mAuthType), this.mUserName, this.mPassword, Integer.valueOf(this.mType), Integer.valueOf(this.mMaxConnectionsTime), Integer.valueOf(this.mMaxConnections), Integer.valueOf(this.mWaitTime), 
          Boolean.valueOf(this.mEnabled), Integer.valueOf(this.mSupportedApnTypesBitmask), Integer.valueOf(this.mRoamingProtocolType), Integer.valueOf(this.mBearerBitmask), Integer.valueOf(this.mMtuV4), Integer.valueOf(this.mMtuV6), Boolean.valueOf(this.mPersistent), Boolean.valueOf(this.mPreferred) });
  }
  
  public boolean isEnabled() {
    return this.mEnabled;
  }
  
  public boolean isPersistent() {
    return this.mPersistent;
  }
  
  public boolean isPreferred() {
    return this.mPreferred;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DataProfile=");
    stringBuilder.append(this.mProfileId);
    stringBuilder.append("/");
    stringBuilder.append(this.mProtocolType);
    stringBuilder.append("/");
    stringBuilder.append(this.mAuthType);
    stringBuilder.append("/");
    if (TelephonyUtils.IS_USER) {
      str = "***/***/***";
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(this.mApn);
      stringBuilder1.append("/");
      stringBuilder1.append(this.mUserName);
      stringBuilder1.append("/");
      stringBuilder1.append(this.mPassword);
      str = stringBuilder1.toString();
    } 
    stringBuilder.append(str);
    stringBuilder.append("/");
    stringBuilder.append(this.mType);
    stringBuilder.append("/");
    stringBuilder.append(this.mMaxConnectionsTime);
    stringBuilder.append("/");
    stringBuilder.append(this.mMaxConnections);
    stringBuilder.append("/");
    stringBuilder.append(this.mWaitTime);
    stringBuilder.append("/");
    stringBuilder.append(this.mEnabled);
    stringBuilder.append("/");
    stringBuilder.append(this.mSupportedApnTypesBitmask);
    stringBuilder.append("/");
    stringBuilder.append(this.mRoamingProtocolType);
    stringBuilder.append("/");
    stringBuilder.append(this.mBearerBitmask);
    stringBuilder.append("/");
    stringBuilder.append(this.mMtuV4);
    stringBuilder.append("/");
    stringBuilder.append(this.mMtuV6);
    stringBuilder.append("/");
    stringBuilder.append(this.mPersistent);
    stringBuilder.append("/");
    stringBuilder.append(this.mPreferred);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mProfileId);
    paramParcel.writeString(this.mApn);
    paramParcel.writeInt(this.mProtocolType);
    paramParcel.writeInt(this.mAuthType);
    paramParcel.writeString(this.mUserName);
    paramParcel.writeString(this.mPassword);
    paramParcel.writeInt(this.mType);
    paramParcel.writeInt(this.mMaxConnectionsTime);
    paramParcel.writeInt(this.mMaxConnections);
    paramParcel.writeInt(this.mWaitTime);
    paramParcel.writeBoolean(this.mEnabled);
    paramParcel.writeInt(this.mSupportedApnTypesBitmask);
    paramParcel.writeInt(this.mRoamingProtocolType);
    paramParcel.writeInt(this.mBearerBitmask);
    paramParcel.writeInt(this.mMtuV4);
    paramParcel.writeInt(this.mMtuV6);
    paramParcel.writeBoolean(this.mPersistent);
    paramParcel.writeBoolean(this.mPreferred);
  }
  
  public static final class Builder {
    private String mApn;
    
    private int mAuthType;
    
    private int mBearerBitmask;
    
    private boolean mEnabled;
    
    private int mMaxConnections;
    
    private int mMaxConnectionsTime;
    
    private int mMtuV4;
    
    private int mMtuV6;
    
    private String mPassword;
    
    private boolean mPersistent;
    
    private boolean mPreferred;
    
    private int mProfileId;
    
    private int mProtocolType;
    
    private int mRoamingProtocolType;
    
    private int mSupportedApnTypesBitmask;
    
    private int mType;
    
    private String mUserName;
    
    private int mWaitTime;
    
    public DataProfile build() {
      return new DataProfile(this.mProfileId, this.mApn, this.mProtocolType, this.mAuthType, this.mUserName, this.mPassword, this.mType, this.mMaxConnectionsTime, this.mMaxConnections, this.mWaitTime, this.mEnabled, this.mSupportedApnTypesBitmask, this.mRoamingProtocolType, this.mBearerBitmask, this.mMtuV4, this.mMtuV6, this.mPersistent, this.mPreferred);
    }
    
    public Builder enable(boolean param1Boolean) {
      this.mEnabled = param1Boolean;
      return this;
    }
    
    public Builder setApn(String param1String) {
      this.mApn = param1String;
      return this;
    }
    
    public Builder setAuthType(int param1Int) {
      this.mAuthType = param1Int;
      return this;
    }
    
    public Builder setBearerBitmask(int param1Int) {
      this.mBearerBitmask = param1Int;
      return this;
    }
    
    public Builder setMaxConnections(int param1Int) {
      this.mMaxConnections = param1Int;
      return this;
    }
    
    public Builder setMaxConnectionsTime(int param1Int) {
      this.mMaxConnectionsTime = param1Int;
      return this;
    }
    
    public Builder setMtu(int param1Int) {
      this.mMtuV6 = param1Int;
      this.mMtuV4 = param1Int;
      return this;
    }
    
    public Builder setMtuV4(int param1Int) {
      this.mMtuV4 = param1Int;
      return this;
    }
    
    public Builder setMtuV6(int param1Int) {
      this.mMtuV6 = param1Int;
      return this;
    }
    
    public Builder setPassword(String param1String) {
      this.mPassword = param1String;
      return this;
    }
    
    public Builder setPersistent(boolean param1Boolean) {
      this.mPersistent = param1Boolean;
      return this;
    }
    
    public Builder setPreferred(boolean param1Boolean) {
      this.mPreferred = param1Boolean;
      return this;
    }
    
    public Builder setProfileId(int param1Int) {
      this.mProfileId = param1Int;
      return this;
    }
    
    public Builder setProtocolType(int param1Int) {
      this.mProtocolType = param1Int;
      return this;
    }
    
    public Builder setRoamingProtocolType(int param1Int) {
      this.mRoamingProtocolType = param1Int;
      return this;
    }
    
    public Builder setSupportedApnTypesBitmask(int param1Int) {
      this.mSupportedApnTypesBitmask = param1Int;
      return this;
    }
    
    public Builder setType(int param1Int) {
      this.mType = param1Int;
      return this;
    }
    
    public Builder setUserName(String param1String) {
      this.mUserName = param1String;
      return this;
    }
    
    public Builder setWaitTime(int param1Int) {
      this.mWaitTime = param1Int;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Type {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */