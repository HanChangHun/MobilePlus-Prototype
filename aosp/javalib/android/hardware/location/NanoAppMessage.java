package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import libcore.util.HexEncoding;

@SystemApi
public final class NanoAppMessage implements Parcelable {
  public static final Parcelable.Creator<NanoAppMessage> CREATOR = new Parcelable.Creator<NanoAppMessage>() {
      public NanoAppMessage createFromParcel(Parcel param1Parcel) {
        return new NanoAppMessage(param1Parcel);
      }
      
      public NanoAppMessage[] newArray(int param1Int) {
        return new NanoAppMessage[param1Int];
      }
    };
  
  private static final int DEBUG_LOG_NUM_BYTES = 16;
  
  private boolean mIsBroadcasted;
  
  private byte[] mMessageBody;
  
  private int mMessageType;
  
  private long mNanoAppId;
  
  private NanoAppMessage(long paramLong, int paramInt, byte[] paramArrayOfbyte, boolean paramBoolean) {
    this.mNanoAppId = paramLong;
    this.mMessageType = paramInt;
    this.mMessageBody = paramArrayOfbyte;
    this.mIsBroadcasted = paramBoolean;
  }
  
  private NanoAppMessage(Parcel paramParcel) {
    this.mNanoAppId = paramParcel.readLong();
    int i = paramParcel.readInt();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    this.mIsBroadcasted = bool;
    this.mMessageType = paramParcel.readInt();
    byte[] arrayOfByte = new byte[paramParcel.readInt()];
    this.mMessageBody = arrayOfByte;
    paramParcel.readByteArray(arrayOfByte);
  }
  
  public static NanoAppMessage createMessageFromNanoApp(long paramLong, int paramInt, byte[] paramArrayOfbyte, boolean paramBoolean) {
    return new NanoAppMessage(paramLong, paramInt, paramArrayOfbyte, paramBoolean);
  }
  
  public static NanoAppMessage createMessageToNanoApp(long paramLong, int paramInt, byte[] paramArrayOfbyte) {
    return new NanoAppMessage(paramLong, paramInt, paramArrayOfbyte, false);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1 = true;
    if (paramObject == this)
      return true; 
    boolean bool2 = false;
    if (paramObject instanceof NanoAppMessage) {
      paramObject = paramObject;
      if (paramObject.getNanoAppId() == this.mNanoAppId && paramObject.getMessageType() == this.mMessageType && paramObject.isBroadcastMessage() == this.mIsBroadcasted && Arrays.equals(paramObject.getMessageBody(), this.mMessageBody)) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
    } 
    return bool2;
  }
  
  public byte[] getMessageBody() {
    return this.mMessageBody;
  }
  
  public int getMessageType() {
    return this.mMessageType;
  }
  
  public long getNanoAppId() {
    return this.mNanoAppId;
  }
  
  public boolean isBroadcastMessage() {
    return this.mIsBroadcasted;
  }
  
  public String toString() {
    int i = this.mMessageBody.length;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("NanoAppMessage[type = ");
    stringBuilder1.append(this.mMessageType);
    stringBuilder1.append(", length = ");
    stringBuilder1.append(this.mMessageBody.length);
    stringBuilder1.append(" bytes, ");
    if (this.mIsBroadcasted) {
      str2 = "broadcast";
    } else {
      str2 = "unicast";
    } 
    stringBuilder1.append(str2);
    stringBuilder1.append(", nanoapp = 0x");
    stringBuilder1.append(Long.toHexString(this.mNanoAppId));
    stringBuilder1.append("](");
    String str1 = stringBuilder1.toString();
    String str2 = str1;
    if (i > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("data = 0x");
      str2 = stringBuilder.toString();
    } 
    for (byte b = 0; b < Math.min(i, 16); b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append(HexEncoding.encodeToString(this.mMessageBody[b], true));
      String str = stringBuilder.toString();
      str2 = str;
      if ((b + 1) % 4 == 0) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str);
        stringBuilder3.append(" ");
        str2 = stringBuilder3.toString();
      } 
    } 
    str1 = str2;
    if (i > 16) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("...");
      str1 = stringBuilder.toString();
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str1);
    stringBuilder2.append(")");
    return stringBuilder2.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mNanoAppId);
    paramParcel.writeInt(this.mIsBroadcasted);
    paramParcel.writeInt(this.mMessageType);
    paramParcel.writeInt(this.mMessageBody.length);
    paramParcel.writeByteArray(this.mMessageBody);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */