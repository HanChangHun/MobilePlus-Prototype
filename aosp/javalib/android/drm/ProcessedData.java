package android.drm;

@Deprecated
public class ProcessedData {
  private String mAccountId = "_NO_USER";
  
  private final byte[] mData;
  
  private String mSubscriptionId = "";
  
  ProcessedData(byte[] paramArrayOfbyte, String paramString) {
    this.mData = paramArrayOfbyte;
    this.mAccountId = paramString;
  }
  
  ProcessedData(byte[] paramArrayOfbyte, String paramString1, String paramString2) {
    this.mData = paramArrayOfbyte;
    this.mAccountId = paramString1;
    this.mSubscriptionId = paramString2;
  }
  
  public String getAccountId() {
    return this.mAccountId;
  }
  
  public byte[] getData() {
    return this.mData;
  }
  
  public String getSubscriptionId() {
    return this.mSubscriptionId;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/ProcessedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */