package android.drm;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

@Deprecated
public class DrmInfo {
  private final HashMap<String, Object> mAttributes = new HashMap<>();
  
  private byte[] mData;
  
  private final int mInfoType;
  
  private final String mMimeType;
  
  public DrmInfo(int paramInt, String paramString1, String paramString2) {
    this.mInfoType = paramInt;
    this.mMimeType = paramString2;
    try {
      this.mData = DrmUtils.readBytes(paramString1);
    } catch (IOException iOException) {
      this.mData = null;
    } 
    if (isValid())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("infoType: ");
    stringBuilder.append(paramInt);
    stringBuilder.append(",mimeType: ");
    stringBuilder.append(paramString2);
    stringBuilder.append(",data: ");
    stringBuilder.append(Arrays.toString(this.mData));
    stringBuilder.toString();
    throw new IllegalArgumentException();
  }
  
  public DrmInfo(int paramInt, byte[] paramArrayOfbyte, String paramString) {
    this.mInfoType = paramInt;
    this.mMimeType = paramString;
    this.mData = paramArrayOfbyte;
    if (isValid())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("infoType: ");
    stringBuilder.append(paramInt);
    stringBuilder.append(",mimeType: ");
    stringBuilder.append(paramString);
    stringBuilder.append(",data: ");
    stringBuilder.append(Arrays.toString(paramArrayOfbyte));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Object get(String paramString) {
    return this.mAttributes.get(paramString);
  }
  
  public byte[] getData() {
    return this.mData;
  }
  
  public int getInfoType() {
    return this.mInfoType;
  }
  
  public String getMimeType() {
    return this.mMimeType;
  }
  
  boolean isValid() {
    String str = this.mMimeType;
    if (str != null && !str.equals("")) {
      byte[] arrayOfByte = this.mData;
      if (arrayOfByte != null && arrayOfByte.length > 0 && DrmInfoRequest.isValidType(this.mInfoType))
        return true; 
    } 
    return false;
  }
  
  public Iterator<Object> iterator() {
    return this.mAttributes.values().iterator();
  }
  
  public Iterator<String> keyIterator() {
    return this.mAttributes.keySet().iterator();
  }
  
  public void put(String paramString, Object paramObject) {
    this.mAttributes.put(paramString, paramObject);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */