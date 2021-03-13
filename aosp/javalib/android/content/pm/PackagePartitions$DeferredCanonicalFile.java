package android.content.pm;

import java.io.File;

class DeferredCanonicalFile {
  private File mFile;
  
  private boolean mIsCanonical = false;
  
  private DeferredCanonicalFile(File paramFile) {
    this.mFile = paramFile;
  }
  
  private DeferredCanonicalFile(File paramFile, String paramString) {
    this.mFile = new File(paramFile, paramString);
  }
  
  private File getFile() {
    if (!this.mIsCanonical) {
      this.mFile = PackagePartitions.access$400(this.mFile);
      this.mIsCanonical = true;
    } 
    return this.mFile;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackagePartitions$DeferredCanonicalFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */