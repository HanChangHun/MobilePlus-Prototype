package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

public final class WifiDisplaySessionInfo implements Parcelable {
  public static final Parcelable.Creator<WifiDisplaySessionInfo> CREATOR = new Parcelable.Creator<WifiDisplaySessionInfo>() {
      public WifiDisplaySessionInfo createFromParcel(Parcel param1Parcel) {
        boolean bool;
        if (param1Parcel.readInt() != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return new WifiDisplaySessionInfo(bool, param1Parcel.readInt(), param1Parcel.readString(), param1Parcel.readString(), param1Parcel.readString());
      }
      
      public WifiDisplaySessionInfo[] newArray(int param1Int) {
        return new WifiDisplaySessionInfo[param1Int];
      }
    };
  
  private final boolean mClient;
  
  private final String mGroupId;
  
  private final String mIP;
  
  private final String mPassphrase;
  
  private final int mSessionId;
  
  public WifiDisplaySessionInfo() {
    this(true, 0, "", "", "");
  }
  
  public WifiDisplaySessionInfo(boolean paramBoolean, int paramInt, String paramString1, String paramString2, String paramString3) {
    this.mClient = paramBoolean;
    this.mSessionId = paramInt;
    this.mGroupId = paramString1;
    this.mPassphrase = paramString2;
    this.mIP = paramString3;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getGroupId() {
    return this.mGroupId;
  }
  
  public String getIP() {
    return this.mIP;
  }
  
  public String getPassphrase() {
    return this.mPassphrase;
  }
  
  public int getSessionId() {
    return this.mSessionId;
  }
  
  public boolean isClient() {
    return this.mClient;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("WifiDisplaySessionInfo:\n    Client/Owner: ");
    if (this.mClient) {
      str = "Client";
    } else {
      str = "Owner";
    } 
    stringBuilder.append(str);
    stringBuilder.append("\n    GroupId: ");
    stringBuilder.append(this.mGroupId);
    stringBuilder.append("\n    Passphrase: ");
    stringBuilder.append(this.mPassphrase);
    stringBuilder.append("\n    SessionId: ");
    stringBuilder.append(this.mSessionId);
    stringBuilder.append("\n    IP Address: ");
    stringBuilder.append(this.mIP);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mClient);
    paramParcel.writeInt(this.mSessionId);
    paramParcel.writeString(this.mGroupId);
    paramParcel.writeString(this.mPassphrase);
    paramParcel.writeString(this.mIP);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/WifiDisplaySessionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */