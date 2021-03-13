package android.telephony.data;

import android.annotation.SystemApi;
import android.net.LinkAddress;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SystemApi
public final class DataCallResponse implements Parcelable {
  public static final Parcelable.Creator<DataCallResponse> CREATOR = new Parcelable.Creator<DataCallResponse>() {
      public DataCallResponse createFromParcel(Parcel param1Parcel) {
        return new DataCallResponse(param1Parcel);
      }
      
      public DataCallResponse[] newArray(int param1Int) {
        return new DataCallResponse[param1Int];
      }
    };
  
  public static final int LINK_STATUS_ACTIVE = 2;
  
  public static final int LINK_STATUS_DORMANT = 1;
  
  public static final int LINK_STATUS_INACTIVE = 0;
  
  public static final int LINK_STATUS_UNKNOWN = -1;
  
  private final List<LinkAddress> mAddresses;
  
  private final int mCause;
  
  private final List<InetAddress> mDnsAddresses;
  
  private final List<InetAddress> mGatewayAddresses;
  
  private final int mId;
  
  private final String mInterfaceName;
  
  private final int mLinkStatus;
  
  private final int mMtu;
  
  private final int mMtuV4;
  
  private final int mMtuV6;
  
  private final List<InetAddress> mPcscfAddresses;
  
  private final int mProtocolType;
  
  private final int mSuggestedRetryTime;
  
  public DataCallResponse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, List<LinkAddress> paramList, List<InetAddress> paramList1, List<InetAddress> paramList2, List<InetAddress> paramList3, int paramInt6) {
    ArrayList<LinkAddress> arrayList;
    this.mCause = paramInt1;
    this.mSuggestedRetryTime = paramInt2;
    this.mId = paramInt3;
    this.mLinkStatus = paramInt4;
    this.mProtocolType = paramInt5;
    if (paramString == null)
      paramString = ""; 
    this.mInterfaceName = paramString;
    if (paramList == null) {
      arrayList = new ArrayList();
    } else {
      arrayList = new ArrayList<>(paramList);
    } 
    this.mAddresses = arrayList;
    if (paramList1 == null) {
      arrayList = new ArrayList<>();
    } else {
      arrayList = new ArrayList(paramList1);
    } 
    this.mDnsAddresses = (List)arrayList;
    if (paramList2 == null) {
      arrayList = new ArrayList<>();
    } else {
      arrayList = new ArrayList(paramList2);
    } 
    this.mGatewayAddresses = (List)arrayList;
    if (paramList3 == null) {
      arrayList = new ArrayList<>();
    } else {
      arrayList = new ArrayList(paramList3);
    } 
    this.mPcscfAddresses = (List)arrayList;
    this.mMtuV6 = paramInt6;
    this.mMtuV4 = paramInt6;
    this.mMtu = paramInt6;
  }
  
  private DataCallResponse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, List<LinkAddress> paramList, List<InetAddress> paramList1, List<InetAddress> paramList2, List<InetAddress> paramList3, int paramInt6, int paramInt7, int paramInt8) {
    ArrayList<LinkAddress> arrayList;
    this.mCause = paramInt1;
    this.mSuggestedRetryTime = paramInt2;
    this.mId = paramInt3;
    this.mLinkStatus = paramInt4;
    this.mProtocolType = paramInt5;
    if (paramString == null)
      paramString = ""; 
    this.mInterfaceName = paramString;
    if (paramList == null) {
      arrayList = new ArrayList();
    } else {
      arrayList = new ArrayList<>(paramList);
    } 
    this.mAddresses = arrayList;
    if (paramList1 == null) {
      arrayList = new ArrayList<>();
    } else {
      arrayList = new ArrayList(paramList1);
    } 
    this.mDnsAddresses = (List)arrayList;
    if (paramList2 == null) {
      arrayList = new ArrayList<>();
    } else {
      arrayList = new ArrayList(paramList2);
    } 
    this.mGatewayAddresses = (List)arrayList;
    if (paramList3 == null) {
      arrayList = new ArrayList<>();
    } else {
      arrayList = new ArrayList(paramList3);
    } 
    this.mPcscfAddresses = (List)arrayList;
    this.mMtu = paramInt6;
    this.mMtuV4 = paramInt7;
    this.mMtuV6 = paramInt8;
  }
  
  public DataCallResponse(Parcel paramParcel) {
    this.mCause = paramParcel.readInt();
    this.mSuggestedRetryTime = paramParcel.readInt();
    this.mId = paramParcel.readInt();
    this.mLinkStatus = paramParcel.readInt();
    this.mProtocolType = paramParcel.readInt();
    this.mInterfaceName = paramParcel.readString();
    ArrayList<LinkAddress> arrayList = new ArrayList();
    this.mAddresses = arrayList;
    paramParcel.readList(arrayList, LinkAddress.class.getClassLoader());
    arrayList = new ArrayList<>();
    this.mDnsAddresses = (List)arrayList;
    paramParcel.readList(arrayList, InetAddress.class.getClassLoader());
    arrayList = new ArrayList<>();
    this.mGatewayAddresses = (List)arrayList;
    paramParcel.readList(arrayList, InetAddress.class.getClassLoader());
    arrayList = new ArrayList<>();
    this.mPcscfAddresses = (List)arrayList;
    paramParcel.readList(arrayList, InetAddress.class.getClassLoader());
    this.mMtu = paramParcel.readInt();
    this.mMtuV4 = paramParcel.readInt();
    this.mMtuV6 = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof DataCallResponse))
      return false; 
    paramObject = paramObject;
    if (this.mCause != ((DataCallResponse)paramObject).mCause || this.mSuggestedRetryTime != ((DataCallResponse)paramObject).mSuggestedRetryTime || this.mId != ((DataCallResponse)paramObject).mId || this.mLinkStatus != ((DataCallResponse)paramObject).mLinkStatus || this.mProtocolType != ((DataCallResponse)paramObject).mProtocolType || !this.mInterfaceName.equals(((DataCallResponse)paramObject).mInterfaceName) || this.mAddresses.size() != ((DataCallResponse)paramObject).mAddresses.size() || !this.mAddresses.containsAll(((DataCallResponse)paramObject).mAddresses) || this.mDnsAddresses.size() != ((DataCallResponse)paramObject).mDnsAddresses.size() || !this.mDnsAddresses.containsAll(((DataCallResponse)paramObject).mDnsAddresses) || this.mGatewayAddresses.size() != ((DataCallResponse)paramObject).mGatewayAddresses.size() || !this.mGatewayAddresses.containsAll(((DataCallResponse)paramObject).mGatewayAddresses) || this.mPcscfAddresses.size() != ((DataCallResponse)paramObject).mPcscfAddresses.size() || !this.mPcscfAddresses.containsAll(((DataCallResponse)paramObject).mPcscfAddresses) || this.mMtu != ((DataCallResponse)paramObject).mMtu || this.mMtuV4 != ((DataCallResponse)paramObject).mMtuV4 || this.mMtuV6 != ((DataCallResponse)paramObject).mMtuV6)
      bool = false; 
    return bool;
  }
  
  public List<LinkAddress> getAddresses() {
    return this.mAddresses;
  }
  
  public int getCause() {
    return this.mCause;
  }
  
  public List<InetAddress> getDnsAddresses() {
    return this.mDnsAddresses;
  }
  
  public List<InetAddress> getGatewayAddresses() {
    return this.mGatewayAddresses;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public String getInterfaceName() {
    return this.mInterfaceName;
  }
  
  public int getLinkStatus() {
    return this.mLinkStatus;
  }
  
  @Deprecated
  public int getMtu() {
    return this.mMtu;
  }
  
  public int getMtuV4() {
    return this.mMtuV4;
  }
  
  public int getMtuV6() {
    return this.mMtuV6;
  }
  
  public List<InetAddress> getPcscfAddresses() {
    return this.mPcscfAddresses;
  }
  
  public int getProtocolType() {
    return this.mProtocolType;
  }
  
  public int getSuggestedRetryTime() {
    return this.mSuggestedRetryTime;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(this.mCause), Integer.valueOf(this.mSuggestedRetryTime), Integer.valueOf(this.mId), Integer.valueOf(this.mLinkStatus), Integer.valueOf(this.mProtocolType), this.mInterfaceName, this.mAddresses, this.mDnsAddresses, this.mGatewayAddresses, this.mPcscfAddresses, 
          Integer.valueOf(this.mMtu), Integer.valueOf(this.mMtuV4), Integer.valueOf(this.mMtuV6) });
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("DataCallResponse: {");
    stringBuffer.append(" cause=");
    stringBuffer.append(this.mCause);
    stringBuffer.append(" retry=");
    stringBuffer.append(this.mSuggestedRetryTime);
    stringBuffer.append(" cid=");
    stringBuffer.append(this.mId);
    stringBuffer.append(" linkStatus=");
    stringBuffer.append(this.mLinkStatus);
    stringBuffer.append(" protocolType=");
    stringBuffer.append(this.mProtocolType);
    stringBuffer.append(" ifname=");
    stringBuffer.append(this.mInterfaceName);
    stringBuffer.append(" addresses=");
    stringBuffer.append(this.mAddresses);
    stringBuffer.append(" dnses=");
    stringBuffer.append(this.mDnsAddresses);
    stringBuffer.append(" gateways=");
    stringBuffer.append(this.mGatewayAddresses);
    stringBuffer.append(" pcscf=");
    stringBuffer.append(this.mPcscfAddresses);
    stringBuffer.append(" mtu=");
    stringBuffer.append(getMtu());
    stringBuffer.append(" mtuV4=");
    stringBuffer.append(getMtuV4());
    stringBuffer.append(" mtuV6=");
    stringBuffer.append(getMtuV6());
    stringBuffer.append("}");
    return stringBuffer.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mCause);
    paramParcel.writeInt(this.mSuggestedRetryTime);
    paramParcel.writeInt(this.mId);
    paramParcel.writeInt(this.mLinkStatus);
    paramParcel.writeInt(this.mProtocolType);
    paramParcel.writeString(this.mInterfaceName);
    paramParcel.writeList(this.mAddresses);
    paramParcel.writeList(this.mDnsAddresses);
    paramParcel.writeList(this.mGatewayAddresses);
    paramParcel.writeList(this.mPcscfAddresses);
    paramParcel.writeInt(this.mMtu);
    paramParcel.writeInt(this.mMtuV4);
    paramParcel.writeInt(this.mMtuV6);
  }
  
  public static final class Builder {
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
      return new DataCallResponse(this.mCause, this.mSuggestedRetryTime, this.mId, this.mLinkStatus, this.mProtocolType, this.mInterfaceName, this.mAddresses, this.mDnsAddresses, this.mGatewayAddresses, this.mPcscfAddresses, this.mMtu, this.mMtuV4, this.mMtuV6);
    }
    
    public Builder setAddresses(List<LinkAddress> param1List) {
      this.mAddresses = param1List;
      return this;
    }
    
    public Builder setCause(int param1Int) {
      this.mCause = param1Int;
      return this;
    }
    
    public Builder setDnsAddresses(List<InetAddress> param1List) {
      this.mDnsAddresses = param1List;
      return this;
    }
    
    public Builder setGatewayAddresses(List<InetAddress> param1List) {
      this.mGatewayAddresses = param1List;
      return this;
    }
    
    public Builder setId(int param1Int) {
      this.mId = param1Int;
      return this;
    }
    
    public Builder setInterfaceName(String param1String) {
      this.mInterfaceName = param1String;
      return this;
    }
    
    public Builder setLinkStatus(int param1Int) {
      this.mLinkStatus = param1Int;
      return this;
    }
    
    public Builder setMtu(int param1Int) {
      this.mMtu = param1Int;
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
    
    public Builder setPcscfAddresses(List<InetAddress> param1List) {
      this.mPcscfAddresses = param1List;
      return this;
    }
    
    public Builder setProtocolType(int param1Int) {
      this.mProtocolType = param1Int;
      return this;
    }
    
    public Builder setSuggestedRetryTime(int param1Int) {
      this.mSuggestedRetryTime = param1Int;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LinkStatus {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataCallResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */