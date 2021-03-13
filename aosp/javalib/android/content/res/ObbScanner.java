package android.content.res;

import java.io.File;
import java.io.IOException;

public class ObbScanner {
  public static ObbInfo getObbInfo(String paramString) throws IOException {
    if (paramString != null) {
      ObbInfo obbInfo;
      File file = new File(paramString);
      if (file.exists()) {
        String str = file.getCanonicalPath();
        obbInfo = new ObbInfo();
        obbInfo.filename = str;
        getObbInfo_native(str, obbInfo);
        return obbInfo;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("OBB file does not exist: ");
      stringBuilder.append((String)obbInfo);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("file path cannot be null");
  }
  
  private static native void getObbInfo_native(String paramString, ObbInfo paramObbInfo) throws IOException;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ObbScanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */