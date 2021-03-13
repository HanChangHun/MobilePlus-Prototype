package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

@SystemApi
public final class NanoAppBinary implements Parcelable {
  public static final Parcelable.Creator<NanoAppBinary> CREATOR;
  
  private static final int EXPECTED_HEADER_VERSION = 1;
  
  private static final int EXPECTED_MAGIC_VALUE = 1330528590;
  
  private static final ByteOrder HEADER_ORDER = ByteOrder.LITTLE_ENDIAN;
  
  private static final int HEADER_SIZE_BYTES = 40;
  
  private static final int NANOAPP_ENCRYPTED_FLAG_BIT = 2;
  
  private static final int NANOAPP_SIGNED_FLAG_BIT = 1;
  
  private static final String TAG = "NanoAppBinary";
  
  private int mFlags;
  
  private boolean mHasValidHeader = false;
  
  private int mHeaderVersion;
  
  private long mHwHubType;
  
  private int mMagic;
  
  private byte[] mNanoAppBinary;
  
  private long mNanoAppId;
  
  private int mNanoAppVersion;
  
  private byte mTargetChreApiMajorVersion;
  
  private byte mTargetChreApiMinorVersion;
  
  static {
    CREATOR = new Parcelable.Creator<NanoAppBinary>() {
        public NanoAppBinary createFromParcel(Parcel param1Parcel) {
          return new NanoAppBinary(param1Parcel);
        }
        
        public NanoAppBinary[] newArray(int param1Int) {
          return new NanoAppBinary[param1Int];
        }
      };
  }
  
  private NanoAppBinary(Parcel paramParcel) {
    byte[] arrayOfByte = new byte[paramParcel.readInt()];
    this.mNanoAppBinary = arrayOfByte;
    paramParcel.readByteArray(arrayOfByte);
    parseBinaryHeader();
  }
  
  public NanoAppBinary(byte[] paramArrayOfbyte) {
    this.mNanoAppBinary = paramArrayOfbyte;
    parseBinaryHeader();
  }
  
  private void parseBinaryHeader() {
    ByteBuffer byteBuffer = ByteBuffer.wrap(this.mNanoAppBinary).order(HEADER_ORDER);
    this.mHasValidHeader = false;
    try {
      StringBuilder stringBuilder;
      int i = byteBuffer.getInt();
      this.mHeaderVersion = i;
      if (i != 1) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Unexpected header version ");
        stringBuilder.append(this.mHeaderVersion);
        stringBuilder.append(" while parsing header (expected ");
        stringBuilder.append(1);
        stringBuilder.append(")");
        Log.e("NanoAppBinary", stringBuilder.toString());
        return;
      } 
      this.mMagic = stringBuilder.getInt();
      this.mNanoAppId = stringBuilder.getLong();
      this.mNanoAppVersion = stringBuilder.getInt();
      this.mFlags = stringBuilder.getInt();
      this.mHwHubType = stringBuilder.getLong();
      this.mTargetChreApiMajorVersion = stringBuilder.get();
      this.mTargetChreApiMinorVersion = stringBuilder.get();
      if (this.mMagic != 1330528590) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected magic value ");
        stringBuilder.append(String.format("0x%08X", new Object[] { Integer.valueOf(this.mMagic) }));
        stringBuilder.append("while parsing header (expected ");
        stringBuilder.append(String.format("0x%08X", new Object[] { Integer.valueOf(1330528590) }));
        stringBuilder.append(")");
        Log.e("NanoAppBinary", stringBuilder.toString());
      } else {
        this.mHasValidHeader = true;
      } 
      return;
    } catch (BufferUnderflowException bufferUnderflowException) {
      Log.e("NanoAppBinary", "Not enough contents in nanoapp header");
      return;
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getBinary() {
    return this.mNanoAppBinary;
  }
  
  public byte[] getBinaryNoHeader() {
    byte[] arrayOfByte = this.mNanoAppBinary;
    if (arrayOfByte.length >= 40)
      return Arrays.copyOfRange(arrayOfByte, 40, arrayOfByte.length); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NanoAppBinary binary byte size (");
    stringBuilder.append(this.mNanoAppBinary.length);
    stringBuilder.append(") is less than header size (");
    stringBuilder.append(40);
    stringBuilder.append(")");
    throw new IndexOutOfBoundsException(stringBuilder.toString());
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public int getHeaderVersion() {
    return this.mHeaderVersion;
  }
  
  public long getHwHubType() {
    return this.mHwHubType;
  }
  
  public long getNanoAppId() {
    return this.mNanoAppId;
  }
  
  public int getNanoAppVersion() {
    return this.mNanoAppVersion;
  }
  
  public byte getTargetChreApiMajorVersion() {
    return this.mTargetChreApiMajorVersion;
  }
  
  public byte getTargetChreApiMinorVersion() {
    return this.mTargetChreApiMinorVersion;
  }
  
  public boolean hasValidHeader() {
    return this.mHasValidHeader;
  }
  
  public boolean isEncrypted() {
    boolean bool;
    if ((this.mFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isSigned() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mNanoAppBinary.length);
    paramParcel.writeByteArray(this.mNanoAppBinary);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppBinary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */