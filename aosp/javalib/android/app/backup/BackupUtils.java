package android.app.backup;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class BackupUtils {
  public static boolean isFileSpecifiedInPathList(File paramFile, Collection<FullBackup.BackupScheme.PathWithRequiredFlags> paramCollection) throws IOException {
    Iterator<FullBackup.BackupScheme.PathWithRequiredFlags> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      String str = ((FullBackup.BackupScheme.PathWithRequiredFlags)iterator.next()).getPath();
      File file = new File(str);
      if (file.isDirectory()) {
        if (paramFile.isDirectory()) {
          if (paramFile.equals(file))
            return true; 
          continue;
        } 
        if (paramFile.toPath().startsWith(str))
          return true; 
        continue;
      } 
      if (paramFile.equals(file))
        return true; 
    } 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */