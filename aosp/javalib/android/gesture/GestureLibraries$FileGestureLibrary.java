package android.gesture;

import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class FileGestureLibrary extends GestureLibrary {
  private final FileDescriptor mFd;
  
  private final File mPath;
  
  public FileGestureLibrary(File paramFile) {
    this.mPath = paramFile;
    this.mFd = null;
  }
  
  public FileGestureLibrary(FileDescriptor paramFileDescriptor) {
    this.mPath = null;
    this.mFd = paramFileDescriptor;
  }
  
  public boolean isReadOnly() {
    File file = this.mPath;
    return (file != null) ? (file.canWrite() ^ true) : false;
  }
  
  public boolean load() {
    boolean bool1 = false;
    boolean bool2 = false;
    if (this.mPath != null) {
      File file = this.mPath;
      bool1 = bool2;
      if (file.exists()) {
        bool1 = bool2;
        if (file.canRead())
          try {
            GestureStore gestureStore = this.mStore;
            FileInputStream fileInputStream = new FileInputStream();
            this(file);
            gestureStore.load(fileInputStream, true);
            bool1 = true;
          } catch (IOException iOException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not load the gesture library from ");
            stringBuilder.append(this.mPath);
            Log.d("Gestures", stringBuilder.toString(), iOException);
            bool1 = bool2;
          }  
      } 
    } else {
      try {
        GestureStore gestureStore = this.mStore;
        FileInputStream fileInputStream = new FileInputStream();
        this(this.mFd);
        gestureStore.load(fileInputStream, true);
        bool1 = true;
      } catch (IOException iOException) {
        Log.d("Gestures", "Could not load the gesture library", iOException);
      } 
    } 
    return bool1;
  }
  
  public boolean save() {
    if (!this.mStore.hasChanged())
      return true; 
    boolean bool1 = false;
    boolean bool2 = false;
    if (this.mPath != null) {
      File file1 = this.mPath;
      File file2 = file1.getParentFile();
      if (!file2.exists() && !file2.mkdirs())
        return false; 
      try {
        file1.createNewFile();
        GestureStore gestureStore = this.mStore;
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(file1);
        gestureStore.save(fileOutputStream, true);
        bool2 = true;
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not save the gesture library in ");
        stringBuilder.append(this.mPath);
        Log.d("Gestures", stringBuilder.toString(), iOException);
      } 
    } else {
      try {
        GestureStore gestureStore = this.mStore;
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(this.mFd);
        gestureStore.save(fileOutputStream, true);
        bool2 = true;
      } catch (IOException iOException) {
        Log.d("Gestures", "Could not save the gesture library", iOException);
        bool2 = bool1;
      } 
    } 
    return bool2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureLibraries$FileGestureLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */