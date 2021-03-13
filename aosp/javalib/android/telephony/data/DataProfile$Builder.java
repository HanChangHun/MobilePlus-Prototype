package android.telephony.data;

public final class Builder {
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
    return new DataProfile(this.mProfileId, this.mApn, this.mProtocolType, this.mAuthType, this.mUserName, this.mPassword, this.mType, this.mMaxConnectionsTime, this.mMaxConnections, this.mWaitTime, this.mEnabled, this.mSupportedApnTypesBitmask, this.mRoamingProtocolType, this.mBearerBitmask, this.mMtuV4, this.mMtuV6, this.mPersistent, this.mPreferred, null);
  }
  
  public Builder enable(boolean paramBoolean) {
    this.mEnabled = paramBoolean;
    return this;
  }
  
  public Builder setApn(String paramString) {
    this.mApn = paramString;
    return this;
  }
  
  public Builder setAuthType(int paramInt) {
    this.mAuthType = paramInt;
    return this;
  }
  
  public Builder setBearerBitmask(int paramInt) {
    this.mBearerBitmask = paramInt;
    return this;
  }
  
  public Builder setMaxConnections(int paramInt) {
    this.mMaxConnections = paramInt;
    return this;
  }
  
  public Builder setMaxConnectionsTime(int paramInt) {
    this.mMaxConnectionsTime = paramInt;
    return this;
  }
  
  public Builder setMtu(int paramInt) {
    this.mMtuV6 = paramInt;
    this.mMtuV4 = paramInt;
    return this;
  }
  
  public Builder setMtuV4(int paramInt) {
    this.mMtuV4 = paramInt;
    return this;
  }
  
  public Builder setMtuV6(int paramInt) {
    this.mMtuV6 = paramInt;
    return this;
  }
  
  public Builder setPassword(String paramString) {
    this.mPassword = paramString;
    return this;
  }
  
  public Builder setPersistent(boolean paramBoolean) {
    this.mPersistent = paramBoolean;
    return this;
  }
  
  public Builder setPreferred(boolean paramBoolean) {
    this.mPreferred = paramBoolean;
    return this;
  }
  
  public Builder setProfileId(int paramInt) {
    this.mProfileId = paramInt;
    return this;
  }
  
  public Builder setProtocolType(int paramInt) {
    this.mProtocolType = paramInt;
    return this;
  }
  
  public Builder setRoamingProtocolType(int paramInt) {
    this.mRoamingProtocolType = paramInt;
    return this;
  }
  
  public Builder setSupportedApnTypesBitmask(int paramInt) {
    this.mSupportedApnTypesBitmask = paramInt;
    return this;
  }
  
  public Builder setType(int paramInt) {
    this.mType = paramInt;
    return this;
  }
  
  public Builder setUserName(String paramString) {
    this.mUserName = paramString;
    return this;
  }
  
  public Builder setWaitTime(int paramInt) {
    this.mWaitTime = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataProfile$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */