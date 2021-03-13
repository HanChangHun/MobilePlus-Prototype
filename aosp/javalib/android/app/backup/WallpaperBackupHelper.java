package android.app.backup;

import android.app.WallpaperManager;
import android.content.Context;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Slog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WallpaperBackupHelper extends FileBackupHelperBase implements BackupHelper {
  private static final boolean DEBUG = false;
  
  private static final String STAGE_FILE = (new File(Environment.getUserSystemDirectory(0), "wallpaper-tmp")).getAbsolutePath();
  
  private static final String TAG = "WallpaperBackupHelper";
  
  public static final String WALLPAPER_IMAGE_KEY = "/data/data/com.android.settings/files/wallpaper";
  
  public static final String WALLPAPER_INFO_KEY = "/data/system/wallpaper_info.xml";
  
  private final String[] mKeys;
  
  private final WallpaperManager mWpm;
  
  public WallpaperBackupHelper(Context paramContext, String[] paramArrayOfString) {
    super(paramContext);
    this.mContext = paramContext;
    this.mKeys = paramArrayOfString;
    this.mWpm = (WallpaperManager)paramContext.getSystemService("wallpaper");
  }
  
  public void performBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) {}
  
  public void restoreEntity(BackupDataInputStream paramBackupDataInputStream) {
    if (this.mWpm == null) {
      Slog.w("WallpaperBackupHelper", "restoreEntity(): no wallpaper service");
      return;
    } 
    String str = paramBackupDataInputStream.getKey();
    if (isKeyInList(str, this.mKeys) && str.equals("/data/data/com.android.settings/files/wallpaper")) {
      File file = new File(STAGE_FILE);
      try {
        boolean bool = writeFile(file, paramBackupDataInputStream);
        if (bool) {
          try {
            FileInputStream fileInputStream = new FileInputStream();
            this(file);
            try {
              this.mWpm.setStream(fileInputStream);
            } finally {
              try {
                fileInputStream.close();
              } finally {
                fileInputStream = null;
              } 
            } 
          } catch (IOException iOException) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("Unable to set restored wallpaper: ");
            stringBuilder.append(iOException.getMessage());
            Slog.e("WallpaperBackupHelper", stringBuilder.toString());
          } 
        } else {
          Slog.e("WallpaperBackupHelper", "Unable to save restored wallpaper");
        } 
      } finally {
        file.delete();
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/WallpaperBackupHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */