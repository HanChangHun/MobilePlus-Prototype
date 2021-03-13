package android.drm;

@Deprecated
public class DrmConvertedStatus {
  public static final int STATUS_ERROR = 3;
  
  public static final int STATUS_INPUTDATA_ERROR = 2;
  
  public static final int STATUS_OK = 1;
  
  public final byte[] convertedData;
  
  public final int offset;
  
  public final int statusCode;
  
  public DrmConvertedStatus(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    if (isValidStatusCode(paramInt1)) {
      this.statusCode = paramInt1;
      this.convertedData = paramArrayOfbyte;
      this.offset = paramInt2;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unsupported status code: ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private boolean isValidStatusCode(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 1) {
      bool2 = bool1;
      if (paramInt != 2)
        if (paramInt == 3) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
    } 
    return bool2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmConvertedStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */