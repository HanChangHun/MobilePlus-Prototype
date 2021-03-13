package android.app;

import java.io.File;
import java.io.FilenameFilter;

class null implements FilenameFilter {
  public boolean accept(File paramFile, String paramString) {
    return paramString.startsWith(prefix);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ContextImpl$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */