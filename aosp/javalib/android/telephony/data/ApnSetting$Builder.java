package android.telephony.data;

import android.net.Uri;
import android.text.TextUtils;
import java.net.InetAddress;

public class Builder {
  private String mApnName;
  
  private int mApnSetId;
  
  private int mApnTypeBitmask;
  
  private int mAuthType;
  
  private boolean mCarrierEnabled;
  
  private int mCarrierId = -1;
  
  private String mEntryName;
  
  private int mId;
  
  private int mMaxConns;
  
  private int mMaxConnsTime;
  
  private String mMmsProxyAddress;
  
  private int mMmsProxyPort = -1;
  
  private Uri mMmsc;
  
  private boolean mModemCognitive;
  
  private int mMtu;
  
  private String mMvnoMatchData;
  
  private int mMvnoType = -1;
  
  private int mNetworkTypeBitmask;
  
  private String mOperatorNumeric;
  
  private String mPassword;
  
  private int mProfileId;
  
  private int mProtocol = -1;
  
  private String mProxyAddress;
  
  private int mProxyPort = -1;
  
  private int mRoamingProtocol = -1;
  
  private int mSkip464Xlat = -1;
  
  private String mUser;
  
  private int mWaitTime;
  
  private Builder setId(int paramInt) {
    this.mId = paramInt;
    return this;
  }
  
  public ApnSetting build() {
    return ((this.mApnTypeBitmask & 0xFFF) == 0 || TextUtils.isEmpty(this.mApnName) || TextUtils.isEmpty(this.mEntryName)) ? null : new ApnSetting(this, null);
  }
  
  public ApnSetting buildWithoutCheck() {
    return new ApnSetting(this, null);
  }
  
  public Builder setApnName(String paramString) {
    this.mApnName = paramString;
    return this;
  }
  
  public Builder setApnSetId(int paramInt) {
    this.mApnSetId = paramInt;
    return this;
  }
  
  public Builder setApnTypeBitmask(int paramInt) {
    this.mApnTypeBitmask = paramInt;
    return this;
  }
  
  public Builder setAuthType(int paramInt) {
    this.mAuthType = paramInt;
    return this;
  }
  
  public Builder setCarrierEnabled(boolean paramBoolean) {
    this.mCarrierEnabled = paramBoolean;
    return this;
  }
  
  public Builder setCarrierId(int paramInt) {
    this.mCarrierId = paramInt;
    return this;
  }
  
  public Builder setEntryName(String paramString) {
    this.mEntryName = paramString;
    return this;
  }
  
  public Builder setMaxConns(int paramInt) {
    this.mMaxConns = paramInt;
    return this;
  }
  
  public Builder setMaxConnsTime(int paramInt) {
    this.mMaxConnsTime = paramInt;
    return this;
  }
  
  public Builder setMmsProxyAddress(String paramString) {
    this.mMmsProxyAddress = paramString;
    return this;
  }
  
  @Deprecated
  public Builder setMmsProxyAddress(InetAddress paramInetAddress) {
    this.mMmsProxyAddress = ApnSetting.inetAddressToString(paramInetAddress);
    return this;
  }
  
  public Builder setMmsProxyPort(int paramInt) {
    this.mMmsProxyPort = paramInt;
    return this;
  }
  
  public Builder setMmsc(Uri paramUri) {
    this.mMmsc = paramUri;
    return this;
  }
  
  public Builder setModemCognitive(boolean paramBoolean) {
    this.mModemCognitive = paramBoolean;
    return this;
  }
  
  public Builder setMtu(int paramInt) {
    this.mMtu = paramInt;
    return this;
  }
  
  public Builder setMvnoMatchData(String paramString) {
    this.mMvnoMatchData = paramString;
    return this;
  }
  
  public Builder setMvnoType(int paramInt) {
    this.mMvnoType = paramInt;
    return this;
  }
  
  public Builder setNetworkTypeBitmask(int paramInt) {
    this.mNetworkTypeBitmask = paramInt;
    return this;
  }
  
  public Builder setOperatorNumeric(String paramString) {
    this.mOperatorNumeric = paramString;
    return this;
  }
  
  public Builder setPassword(String paramString) {
    this.mPassword = paramString;
    return this;
  }
  
  public Builder setProfileId(int paramInt) {
    this.mProfileId = paramInt;
    return this;
  }
  
  public Builder setProtocol(int paramInt) {
    this.mProtocol = paramInt;
    return this;
  }
  
  public Builder setProxyAddress(String paramString) {
    this.mProxyAddress = paramString;
    return this;
  }
  
  @Deprecated
  public Builder setProxyAddress(InetAddress paramInetAddress) {
    this.mProxyAddress = ApnSetting.inetAddressToString(paramInetAddress);
    return this;
  }
  
  public Builder setProxyPort(int paramInt) {
    this.mProxyPort = paramInt;
    return this;
  }
  
  public Builder setRoamingProtocol(int paramInt) {
    this.mRoamingProtocol = paramInt;
    return this;
  }
  
  public Builder setSkip464Xlat(int paramInt) {
    this.mSkip464Xlat = paramInt;
    return this;
  }
  
  public Builder setUser(String paramString) {
    this.mUser = paramString;
    return this;
  }
  
  public Builder setWaitTime(int paramInt) {
    this.mWaitTime = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/ApnSetting$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */