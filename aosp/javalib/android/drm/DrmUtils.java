package android.drm;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

@Deprecated
public class DrmUtils {
  public static ExtendedMetadataParser getExtendedMetadataParser(byte[] paramArrayOfbyte) {
    return new ExtendedMetadataParser(paramArrayOfbyte);
  }
  
  private static void quietlyDispose(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  static byte[] readBytes(File paramFile) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
    paramFile = null;
    try {
      byte[] arrayOfByte;
      int i = bufferedInputStream.available();
      if (i > 0) {
        arrayOfByte = new byte[i];
        bufferedInputStream.read(arrayOfByte);
      } 
      return arrayOfByte;
    } finally {
      quietlyDispose(bufferedInputStream);
      quietlyDispose(fileInputStream);
    } 
  }
  
  static byte[] readBytes(String paramString) throws IOException {
    return readBytes(new File(paramString));
  }
  
  static void removeFile(String paramString) throws IOException {
    (new File(paramString)).delete();
  }
  
  static void writeToFile(String paramString, byte[] paramArrayOfbyte) throws IOException {
    FileOutputStream fileOutputStream = null;
    if (paramString != null && paramArrayOfbyte != null) {
      FileOutputStream fileOutputStream1 = fileOutputStream;
      try {
        FileOutputStream fileOutputStream3 = new FileOutputStream();
        fileOutputStream1 = fileOutputStream;
        this(paramString);
        FileOutputStream fileOutputStream2 = fileOutputStream3;
        fileOutputStream1 = fileOutputStream2;
        fileOutputStream2.write(paramArrayOfbyte);
      } finally {
        quietlyDispose(fileOutputStream1);
      } 
    } 
  }
  
  public static class ExtendedMetadataParser {
    HashMap<String, String> mMap = new HashMap<>();
    
    private ExtendedMetadataParser(byte[] param1ArrayOfbyte) {
      int i = 0;
      while (i < param1ArrayOfbyte.length) {
        int j = readByte(param1ArrayOfbyte, i);
        int k = i + 1;
        i = readByte(param1ArrayOfbyte, k);
        String str1 = readMultipleBytes(param1ArrayOfbyte, j, ++k);
        j = k + j;
        String str2 = readMultipleBytes(param1ArrayOfbyte, i, j);
        String str3 = str2;
        if (str2.equals(" "))
          str3 = ""; 
        i = j + i;
        this.mMap.put(str1, str3);
      } 
    }
    
    private int readByte(byte[] param1ArrayOfbyte, int param1Int) {
      return param1ArrayOfbyte[param1Int];
    }
    
    private String readMultipleBytes(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      byte[] arrayOfByte = new byte[param1Int1];
      int i = param1Int2;
      for (byte b = 0; i < param1Int2 + param1Int1; b++) {
        arrayOfByte[b] = (byte)param1ArrayOfbyte[i];
        i++;
      } 
      return new String(arrayOfByte);
    }
    
    public String get(String param1String) {
      return this.mMap.get(param1String);
    }
    
    public Iterator<String> iterator() {
      return this.mMap.values().iterator();
    }
    
    public Iterator<String> keyIterator() {
      return this.mMap.keySet().iterator();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */