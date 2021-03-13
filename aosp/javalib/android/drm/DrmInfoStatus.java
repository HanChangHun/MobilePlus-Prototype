package android.drm;

@Deprecated
public class DrmInfoStatus {
  public static final int STATUS_ERROR = 2;
  
  public static final int STATUS_OK = 1;
  
  public final ProcessedData data;
  
  public final int infoType;
  
  public final String mimeType;
  
  public final int statusCode;
  
  public DrmInfoStatus(int paramInt1, int paramInt2, ProcessedData paramProcessedData, String paramString) {
    if (DrmInfoRequest.isValidType(paramInt2)) {
      if (isValidStatusCode(paramInt1)) {
        if (paramString != null && paramString != "") {
          this.statusCode = paramInt1;
          this.infoType = paramInt2;
          this.data = paramProcessedData;
          this.mimeType = paramString;
          return;
        } 
        throw new IllegalArgumentException("mimeType is null or an empty string");
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unsupported status code: ");
      stringBuilder1.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("infoType: ");
    stringBuilder.append(paramInt2);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private boolean isValidStatusCode(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 1)
      if (paramInt == 2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmInfoStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */