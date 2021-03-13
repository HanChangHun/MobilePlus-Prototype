package android.app.blob;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public final class BlobHandle implements Parcelable {
  public static final String ALGO_SHA_256 = "SHA-256";
  
  public static final Parcelable.Creator<BlobHandle> CREATOR;
  
  private static final int LIMIT_BLOB_LABEL_LENGTH = 100;
  
  private static final int LIMIT_BLOB_TAG_LENGTH = 128;
  
  private static final String[] SUPPORTED_ALGOS = new String[] { "SHA-256" };
  
  public final String algorithm;
  
  public final byte[] digest;
  
  public final long expiryTimeMillis;
  
  public final CharSequence label;
  
  public final String tag;
  
  static {
    CREATOR = new Parcelable.Creator<BlobHandle>() {
        public BlobHandle createFromParcel(Parcel param1Parcel) {
          return new BlobHandle(param1Parcel);
        }
        
        public BlobHandle[] newArray(int param1Int) {
          return new BlobHandle[param1Int];
        }
      };
  }
  
  private BlobHandle(Parcel paramParcel) {
    this.algorithm = paramParcel.readString();
    this.digest = paramParcel.createByteArray();
    this.label = paramParcel.readCharSequence();
    this.expiryTimeMillis = paramParcel.readLong();
    this.tag = paramParcel.readString();
  }
  
  private BlobHandle(String paramString1, byte[] paramArrayOfbyte, CharSequence paramCharSequence, long paramLong, String paramString2) {
    this.algorithm = paramString1;
    this.digest = paramArrayOfbyte;
    this.label = paramCharSequence;
    this.expiryTimeMillis = paramLong;
    this.tag = paramString2;
  }
  
  public static BlobHandle create(String paramString1, byte[] paramArrayOfbyte, CharSequence paramCharSequence, long paramLong, String paramString2) {
    BlobHandle blobHandle = new BlobHandle(paramString1, paramArrayOfbyte, paramCharSequence, paramLong, paramString2);
    blobHandle.assertIsValid();
    return blobHandle;
  }
  
  public static BlobHandle createFromXml(XmlPullParser paramXmlPullParser) throws IOException {
    return create(XmlUtils.readStringAttribute(paramXmlPullParser, "al"), XmlUtils.readByteArrayAttribute(paramXmlPullParser, "dg"), XmlUtils.readStringAttribute(paramXmlPullParser, "lbl"), XmlUtils.readLongAttribute(paramXmlPullParser, "ex"), XmlUtils.readStringAttribute(paramXmlPullParser, "tg"));
  }
  
  public static BlobHandle createWithSha256(byte[] paramArrayOfbyte, CharSequence paramCharSequence, long paramLong, String paramString) {
    return create("SHA-256", paramArrayOfbyte, paramCharSequence, paramLong, paramString);
  }
  
  private static String encodeDigest(byte[] paramArrayOfbyte) {
    return Base64.encodeToString(paramArrayOfbyte, 2);
  }
  
  public static String safeDigest(byte[] paramArrayOfbyte) {
    String str = encodeDigest(paramArrayOfbyte);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str.substring(0, 2));
    stringBuilder.append("..");
    stringBuilder.append(str.substring(str.length() - 2));
    return stringBuilder.toString();
  }
  
  public void assertIsValid() {
    boolean bool2;
    Preconditions.checkArgumentIsSupported(SUPPORTED_ALGOS, this.algorithm);
    Preconditions.checkByteArrayNotEmpty(this.digest, "digest");
    Preconditions.checkStringNotEmpty(this.label, "label must not be null");
    int i = this.label.length();
    boolean bool1 = true;
    if (i <= 100) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "label too long");
    Preconditions.checkArgumentNonnegative(this.expiryTimeMillis, "expiryTimeMillis must not be negative");
    Preconditions.checkStringNotEmpty(this.tag, "tag must not be null");
    if (this.tag.length() <= 128) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "tag too long");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(IndentingPrintWriter paramIndentingPrintWriter, boolean paramBoolean) {
    if (paramBoolean) {
      String str;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("algo: ");
      stringBuilder2.append(this.algorithm);
      paramIndentingPrintWriter.println(stringBuilder2.toString());
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("digest: ");
      byte[] arrayOfByte = this.digest;
      if (paramBoolean) {
        str = encodeDigest(arrayOfByte);
      } else {
        str = safeDigest((byte[])str);
      } 
      stringBuilder3.append(str);
      paramIndentingPrintWriter.println(stringBuilder3.toString());
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("label: ");
      stringBuilder1.append(this.label);
      paramIndentingPrintWriter.println(stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("expiryMs: ");
      stringBuilder1.append(this.expiryTimeMillis);
      paramIndentingPrintWriter.println(stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("tag: ");
      stringBuilder1.append(this.tag);
      paramIndentingPrintWriter.println(stringBuilder1.toString());
    } else {
      paramIndentingPrintWriter.println(toString());
    } 
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || !(paramObject instanceof BlobHandle))
      return false; 
    paramObject = paramObject;
    if (!this.algorithm.equals(((BlobHandle)paramObject).algorithm) || !Arrays.equals(this.digest, ((BlobHandle)paramObject).digest) || !this.label.toString().equals(((BlobHandle)paramObject).label.toString()) || this.expiryTimeMillis != ((BlobHandle)paramObject).expiryTimeMillis || !this.tag.equals(((BlobHandle)paramObject).tag))
      bool = false; 
    return bool;
  }
  
  public long getExpiryTimeMillis() {
    return this.expiryTimeMillis;
  }
  
  public CharSequence getLabel() {
    return this.label;
  }
  
  public byte[] getSha256Digest() {
    return this.digest;
  }
  
  public String getTag() {
    return this.tag;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.algorithm, Integer.valueOf(Arrays.hashCode(this.digest)), this.label, Long.valueOf(this.expiryTimeMillis), this.tag });
  }
  
  public boolean isExpired() {
    boolean bool;
    long l = this.expiryTimeMillis;
    if (l != 0L && l < System.currentTimeMillis()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BlobHandle {algo:");
    stringBuilder.append(this.algorithm);
    stringBuilder.append(",digest:");
    stringBuilder.append(safeDigest(this.digest));
    stringBuilder.append(",label:");
    stringBuilder.append(this.label);
    stringBuilder.append(",expiryMs:");
    stringBuilder.append(this.expiryTimeMillis);
    stringBuilder.append(",tag:");
    stringBuilder.append(this.tag);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.algorithm);
    paramParcel.writeByteArray(this.digest);
    paramParcel.writeCharSequence(this.label);
    paramParcel.writeLong(this.expiryTimeMillis);
    paramParcel.writeString(this.tag);
  }
  
  public void writeToXml(XmlSerializer paramXmlSerializer) throws IOException {
    XmlUtils.writeStringAttribute(paramXmlSerializer, "al", this.algorithm);
    XmlUtils.writeByteArrayAttribute(paramXmlSerializer, "dg", this.digest);
    XmlUtils.writeStringAttribute(paramXmlSerializer, "lbl", this.label);
    XmlUtils.writeLongAttribute(paramXmlSerializer, "ex", this.expiryTimeMillis);
    XmlUtils.writeStringAttribute(paramXmlSerializer, "tg", this.tag);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */