package android.app;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.FileUtils;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import libcore.io.ForwardingOs;
import libcore.io.IoUtils;
import libcore.io.Os;

class AndroidOs extends ForwardingOs {
  private AndroidOs(Os paramOs) {
    super(paramOs);
  }
  
  private void deleteDeprecatedDataPath(String paramString) throws ErrnoException {
    Uri uri = ContentResolver.translateDeprecatedDataPath(paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Redirecting ");
    stringBuilder.append(paramString);
    stringBuilder.append(" to ");
    stringBuilder.append(uri);
    Log.v("ActivityThread", stringBuilder.toString());
    ContentResolver contentResolver = ActivityThread.currentActivityThread().getApplication().getContentResolver();
    try {
      if (contentResolver.delete(uri, null, null) != 0)
        return; 
      FileNotFoundException fileNotFoundException = new FileNotFoundException();
      this();
      throw fileNotFoundException;
    } catch (SecurityException securityException) {
      throw new ErrnoException(securityException.getMessage(), OsConstants.EACCES);
    } catch (FileNotFoundException fileNotFoundException) {
      throw new ErrnoException(fileNotFoundException.getMessage(), OsConstants.ENOENT);
    } 
  }
  
  public static void install() {
    Os os;
    if (!ContentResolver.DEPRECATE_DATA_COLUMNS)
      return; 
    do {
      os = Os.getDefault();
    } while (!Os.compareAndSetDefault(os, (Os)new AndroidOs(os)));
  }
  
  private FileDescriptor openDeprecatedDataPath(String paramString, int paramInt) throws ErrnoException {
    Uri uri = ContentResolver.translateDeprecatedDataPath(paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Redirecting ");
    stringBuilder.append(paramString);
    stringBuilder.append(" to ");
    stringBuilder.append(uri);
    Log.v("ActivityThread", stringBuilder.toString());
    ContentResolver contentResolver = ActivityThread.currentActivityThread().getApplication().getContentResolver();
    try {
      FileDescriptor fileDescriptor = new FileDescriptor();
      this();
      fileDescriptor.setInt$(contentResolver.openFileDescriptor(uri, FileUtils.translateModePosixToString(paramInt)).detachFd());
      return fileDescriptor;
    } catch (SecurityException securityException) {
      throw new ErrnoException(securityException.getMessage(), OsConstants.EACCES);
    } catch (FileNotFoundException fileNotFoundException) {
      throw new ErrnoException(fileNotFoundException.getMessage(), OsConstants.ENOENT);
    } 
  }
  
  public boolean access(String paramString, int paramInt) throws ErrnoException {
    if (paramString != null && paramString.startsWith("/mnt/content/")) {
      IoUtils.closeQuietly(openDeprecatedDataPath(paramString, FileUtils.translateModeAccessToPosix(paramInt)));
      return true;
    } 
    return super.access(paramString, paramInt);
  }
  
  public FileDescriptor open(String paramString, int paramInt1, int paramInt2) throws ErrnoException {
    return (paramString != null && paramString.startsWith("/mnt/content/")) ? openDeprecatedDataPath(paramString, paramInt2) : super.open(paramString, paramInt1, paramInt2);
  }
  
  public void remove(String paramString) throws ErrnoException {
    if (paramString != null && paramString.startsWith("/mnt/content/")) {
      deleteDeprecatedDataPath(paramString);
    } else {
      super.remove(paramString);
    } 
  }
  
  public void rename(String paramString1, String paramString2) throws ErrnoException {
    try {
      super.rename(paramString1, paramString2);
    } catch (ErrnoException errnoException) {
      if (errnoException.errno == OsConstants.EXDEV && paramString1.startsWith("/storage/emulated") && paramString2.startsWith("/storage/emulated")) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Recovering failed rename ");
        stringBuilder.append(paramString1);
        stringBuilder.append(" to ");
        stringBuilder.append(paramString2);
        Log.v("ActivityThread", stringBuilder.toString());
        try {
          File file = new File();
          this(paramString1);
          Path path = file.toPath();
          file = new File();
          this(paramString2);
          Files.move(path, file.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
          return;
        } catch (IOException iOException) {
          Log.e("ActivityThread", "Rename recovery failed ", (Throwable)errnoException);
          throw errnoException;
        } 
      } 
    } 
  }
  
  public StructStat stat(String paramString) throws ErrnoException {
    FileDescriptor fileDescriptor;
    if (paramString != null && paramString.startsWith("/mnt/content/")) {
      fileDescriptor = openDeprecatedDataPath(paramString, OsConstants.O_RDONLY);
      try {
        return Os.fstat(fileDescriptor);
      } finally {
        IoUtils.closeQuietly(fileDescriptor);
      } 
    } 
    return super.stat((String)fileDescriptor);
  }
  
  public void unlink(String paramString) throws ErrnoException {
    if (paramString != null && paramString.startsWith("/mnt/content/")) {
      deleteDeprecatedDataPath(paramString);
    } else {
      super.unlink(paramString);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$AndroidOs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */