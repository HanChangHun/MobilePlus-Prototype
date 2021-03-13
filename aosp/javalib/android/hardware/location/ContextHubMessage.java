package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import libcore.util.HexEncoding;

@SystemApi
@Deprecated
public class ContextHubMessage implements Parcelable {
  public static final Parcelable.Creator<ContextHubMessage> CREATOR = new Parcelable.Creator<ContextHubMessage>() {
      public ContextHubMessage createFromParcel(Parcel param1Parcel) {
        return new ContextHubMessage(param1Parcel);
      }
      
      public ContextHubMessage[] newArray(int param1Int) {
        return new ContextHubMessage[param1Int];
      }
    };
  
  private static final int DEBUG_LOG_NUM_BYTES = 16;
  
  private byte[] mData;
  
  private int mType;
  
  private int mVersion;
  
  public ContextHubMessage(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    this.mType = paramInt1;
    this.mVersion = paramInt2;
    this.mData = Arrays.copyOf(paramArrayOfbyte, paramArrayOfbyte.length);
  }
  
  private ContextHubMessage(Parcel paramParcel) {
    this.mType = paramParcel.readInt();
    this.mVersion = paramParcel.readInt();
    byte[] arrayOfByte = new byte[paramParcel.readInt()];
    this.mData = arrayOfByte;
    paramParcel.readByteArray(arrayOfByte);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getData() {
    byte[] arrayOfByte = this.mData;
    return Arrays.copyOf(arrayOfByte, arrayOfByte.length);
  }
  
  public int getMsgType() {
    return this.mType;
  }
  
  public int getVersion() {
    return this.mVersion;
  }
  
  public void setMsgData(byte[] paramArrayOfbyte) {
    this.mData = Arrays.copyOf(paramArrayOfbyte, paramArrayOfbyte.length);
  }
  
  public void setMsgType(int paramInt) {
    this.mType = paramInt;
  }
  
  public void setVersion(int paramInt) {
    this.mVersion = paramInt;
  }
  
  public String toString() {
    int i = this.mData.length;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("ContextHubMessage[type = ");
    stringBuilder2.append(this.mType);
    stringBuilder2.append(", length = ");
    stringBuilder2.append(this.mData.length);
    stringBuilder2.append(" bytes](");
    String str2 = stringBuilder2.toString();
    String str1 = str2;
    if (i > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("data = 0x");
      str1 = stringBuilder.toString();
    } 
    for (byte b = 0; b < Math.min(i, 16); b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(HexEncoding.encodeToString(this.mData[b], true));
      String str = stringBuilder.toString();
      str1 = str;
      if ((b + 1) % 4 == 0) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str);
        stringBuilder3.append(" ");
        str1 = stringBuilder3.toString();
      } 
    } 
    str2 = str1;
    if (i > 16) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("...");
      str2 = stringBuilder.toString();
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str2);
    stringBuilder1.append(")");
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mType);
    paramParcel.writeInt(this.mVersion);
    paramParcel.writeInt(this.mData.length);
    paramParcel.writeByteArray(this.mData);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */