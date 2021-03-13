package android.drm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Deprecated
public class DrmRights {
  private String mAccountId;
  
  private byte[] mData;
  
  private String mMimeType;
  
  private String mSubscriptionId;
  
  public DrmRights(ProcessedData paramProcessedData, String paramString) {
    if (paramProcessedData != null) {
      this.mData = paramProcessedData.getData();
      this.mAccountId = paramProcessedData.getAccountId();
      this.mSubscriptionId = paramProcessedData.getSubscriptionId();
      this.mMimeType = paramString;
      if (isValid())
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("mimeType: ");
      stringBuilder.append(this.mMimeType);
      stringBuilder.append(",data: ");
      stringBuilder.append(Arrays.toString(this.mData));
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("data is null");
  }
  
  public DrmRights(File paramFile, String paramString) {
    instantiate(paramFile, paramString);
  }
  
  public DrmRights(String paramString1, String paramString2) {
    instantiate(new File(paramString1), paramString2);
  }
  
  public DrmRights(String paramString1, String paramString2, String paramString3) {
    this(paramString1, paramString2);
    this.mAccountId = paramString3;
  }
  
  public DrmRights(String paramString1, String paramString2, String paramString3, String paramString4) {
    this(paramString1, paramString2);
    this.mAccountId = paramString3;
    this.mSubscriptionId = paramString4;
  }
  
  private void instantiate(File paramFile, String paramString) {
    try {
      this.mData = DrmUtils.readBytes(paramFile);
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    this.mMimeType = paramString;
    if (isValid())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("mimeType: ");
    stringBuilder.append(this.mMimeType);
    stringBuilder.append(",data: ");
    stringBuilder.append(Arrays.toString(this.mData));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public String getAccountId() {
    return this.mAccountId;
  }
  
  public byte[] getData() {
    return this.mData;
  }
  
  public String getMimeType() {
    return this.mMimeType;
  }
  
  public String getSubscriptionId() {
    return this.mSubscriptionId;
  }
  
  boolean isValid() {
    String str = this.mMimeType;
    if (str != null && !str.equals("")) {
      byte[] arrayOfByte = this.mData;
      if (arrayOfByte != null && arrayOfByte.length > 0)
        return true; 
    } 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmRights.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */