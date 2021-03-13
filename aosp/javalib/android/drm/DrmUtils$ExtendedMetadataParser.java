package android.drm;

import java.util.HashMap;
import java.util.Iterator;

public class ExtendedMetadataParser {
  HashMap<String, String> mMap = new HashMap<>();
  
  private ExtendedMetadataParser(byte[] paramArrayOfbyte) {
    int i = 0;
    while (i < paramArrayOfbyte.length) {
      int j = readByte(paramArrayOfbyte, i);
      int k = i + 1;
      i = readByte(paramArrayOfbyte, k);
      String str1 = readMultipleBytes(paramArrayOfbyte, j, ++k);
      j = k + j;
      String str2 = readMultipleBytes(paramArrayOfbyte, i, j);
      String str3 = str2;
      if (str2.equals(" "))
        str3 = ""; 
      i = j + i;
      this.mMap.put(str1, str3);
    } 
  }
  
  private int readByte(byte[] paramArrayOfbyte, int paramInt) {
    return paramArrayOfbyte[paramInt];
  }
  
  private String readMultipleBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte[] arrayOfByte = new byte[paramInt1];
    int i = paramInt2;
    for (byte b = 0; i < paramInt2 + paramInt1; b++) {
      arrayOfByte[b] = (byte)paramArrayOfbyte[i];
      i++;
    } 
    return new String(arrayOfByte);
  }
  
  public String get(String paramString) {
    return this.mMap.get(paramString);
  }
  
  public Iterator<String> iterator() {
    return this.mMap.values().iterator();
  }
  
  public Iterator<String> keyIterator() {
    return this.mMap.keySet().iterator();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmUtils$ExtendedMetadataParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */