package android.drm;

import java.util.HashMap;
import java.util.Iterator;

@Deprecated
public class DrmInfoRequest {
  public static final String ACCOUNT_ID = "account_id";
  
  public static final String SUBSCRIPTION_ID = "subscription_id";
  
  public static final int TYPE_REGISTRATION_INFO = 1;
  
  public static final int TYPE_RIGHTS_ACQUISITION_INFO = 3;
  
  public static final int TYPE_RIGHTS_ACQUISITION_PROGRESS_INFO = 4;
  
  public static final int TYPE_UNREGISTRATION_INFO = 2;
  
  private final int mInfoType;
  
  private final String mMimeType;
  
  private final HashMap<String, Object> mRequestInformation = new HashMap<>();
  
  public DrmInfoRequest(int paramInt, String paramString) {
    this.mInfoType = paramInt;
    this.mMimeType = paramString;
    if (isValid())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("infoType: ");
    stringBuilder.append(paramInt);
    stringBuilder.append(",mimeType: ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  static boolean isValidType(int paramInt) {
    boolean bool = false;
    if (paramInt == 1 || paramInt == 2 || paramInt == 3 || paramInt == 4)
      bool = true; 
    return bool;
  }
  
  public Object get(String paramString) {
    return this.mRequestInformation.get(paramString);
  }
  
  public int getInfoType() {
    return this.mInfoType;
  }
  
  public String getMimeType() {
    return this.mMimeType;
  }
  
  boolean isValid() {
    boolean bool;
    String str = this.mMimeType;
    if (str != null && !str.equals("") && this.mRequestInformation != null && isValidType(this.mInfoType)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Iterator<Object> iterator() {
    return this.mRequestInformation.values().iterator();
  }
  
  public Iterator<String> keyIterator() {
    return this.mRequestInformation.keySet().iterator();
  }
  
  public void put(String paramString, Object paramObject) {
    this.mRequestInformation.put(paramString, paramObject);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmInfoRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */