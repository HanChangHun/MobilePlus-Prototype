package android.database.sqlite;

import java.io.File;
import java.io.FileFilter;

class null implements FileFilter {
  public boolean accept(File paramFile) {
    return paramFile.getName().startsWith(prefix);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDatabase$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */