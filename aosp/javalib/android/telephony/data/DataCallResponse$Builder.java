package android.telephony.data;

import android.net.LinkAddress;
import java.net.InetAddress;
import java.util.List;

public final class Builder {
  private List<LinkAddress> mAddresses;
  
  private int mCause;
  
  private List<InetAddress> mDnsAddresses;
  
  private List<InetAddress> mGatewayAddresses;
  
  private int mId;
  
  private String mInterfaceName;
  
  private int mLinkStatus;
  
  private int mMtu;
  
  private int mMtuV4;
  
  private int mMtuV6;
  
  private List<InetAddress> mPcscfAddresses;
  
  private int mProtocolType;
  
  private int mSuggestedRetryTime;
  
  public DataCallResponse build() {
    return new DataCallResponse(this.mCause, this.mSuggestedRetryTime, this.mId, this.mLinkStatus, this.mProtocolType, this.mInterfaceName, this.mAddresses, this.mDnsAddresses, this.mGatewayAddresses, this.mPcscfAddresses, this.mMtu, this.mMtuV4, this.mMtuV6, null);
  }
  
  public Builder setAddresses(List<LinkAddress> paramList) {
    this.mAddresses = paramList;
    return this;
  }
  
  public Builder setCause(int paramInt) {
    this.mCause = paramInt;
    return this;
  }
  
  public Builder setDnsAddresses(List<InetAddress> paramList) {
    this.mDnsAddresses = paramList;
    return this;
  }
  
  public Builder setGatewayAddresses(List<InetAddress> paramList) {
    this.mGatewayAddresses = paramList;
    return this;
  }
  
  public Builder setId(int paramInt) {
    this.mId = paramInt;
    return this;
  }
  
  public Builder setInterfaceName(String paramString) {
    this.mInterfaceName = paramString;
    return this;
  }
  
  public Builder setLinkStatus(int paramInt) {
    this.mLinkStatus = paramInt;
    return this;
  }
  
  public Builder setMtu(int paramInt) {
    this.mMtu = paramInt;
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
  
  public Builder setPcscfAddresses(List<InetAddress> paramList) {
    this.mPcscfAddresses = paramList;
    return this;
  }
  
  public Builder setProtocolType(int paramInt) {
    this.mProtocolType = paramInt;
    return this;
  }
  
  public Builder setSuggestedRetryTime(int paramInt) {
    this.mSuggestedRetryTime = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataCallResponse$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */