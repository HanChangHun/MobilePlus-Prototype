package android.content.pm;

import android.os.FileUtils;
import java.io.File;

public class SystemPartition {
  private final PackagePartitions.DeferredCanonicalFile mAppFolder;
  
  private final PackagePartitions.DeferredCanonicalFile mFolder;
  
  private final PackagePartitions.DeferredCanonicalFile mOverlayFolder;
  
  private final PackagePartitions.DeferredCanonicalFile mPrivAppFolder;
  
  public final int type;
  
  public SystemPartition(SystemPartition paramSystemPartition) {
    this.type = paramSystemPartition.type;
    this.mFolder = new PackagePartitions.DeferredCanonicalFile(PackagePartitions.DeferredCanonicalFile.access$300(paramSystemPartition.mFolder), null);
    this.mAppFolder = paramSystemPartition.mAppFolder;
    this.mPrivAppFolder = paramSystemPartition.mPrivAppFolder;
    this.mOverlayFolder = paramSystemPartition.mOverlayFolder;
  }
  
  private SystemPartition(File paramFile, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile;
    this.type = paramInt;
    File file = null;
    this.mFolder = new PackagePartitions.DeferredCanonicalFile(paramFile, null);
    this.mAppFolder = new PackagePartitions.DeferredCanonicalFile(paramFile, "app", null);
    if (paramBoolean1) {
      deferredCanonicalFile = new PackagePartitions.DeferredCanonicalFile(paramFile, "priv-app", null);
    } else {
      deferredCanonicalFile = null;
    } 
    this.mPrivAppFolder = deferredCanonicalFile;
    if (paramBoolean2) {
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile1 = new PackagePartitions.DeferredCanonicalFile(paramFile, "overlay", null);
    } else {
      paramFile = file;
    } 
    this.mOverlayFolder = (PackagePartitions.DeferredCanonicalFile)paramFile;
  }
  
  public SystemPartition(File paramFile, SystemPartition paramSystemPartition) {
    this(paramFile, i, bool2, bool1);
  }
  
  public boolean containsApp(File paramFile) {
    boolean bool;
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mAppFolder;
    if (deferredCanonicalFile != null && FileUtils.contains(PackagePartitions.DeferredCanonicalFile.access$300(deferredCanonicalFile), PackagePartitions.access$400(paramFile))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean containsFile(File paramFile) {
    return FileUtils.contains(PackagePartitions.DeferredCanonicalFile.access$300(this.mFolder), PackagePartitions.access$400(paramFile));
  }
  
  public boolean containsOverlay(File paramFile) {
    boolean bool;
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mOverlayFolder;
    if (deferredCanonicalFile != null && FileUtils.contains(PackagePartitions.DeferredCanonicalFile.access$300(deferredCanonicalFile), PackagePartitions.access$400(paramFile))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean containsPath(String paramString) {
    return containsFile(new File(paramString));
  }
  
  public boolean containsPrivApp(File paramFile) {
    boolean bool;
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mPrivAppFolder;
    if (deferredCanonicalFile != null && FileUtils.contains(PackagePartitions.DeferredCanonicalFile.access$300(deferredCanonicalFile), PackagePartitions.access$400(paramFile))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public File getAppFolder() {
    File file;
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mAppFolder;
    if (deferredCanonicalFile == null) {
      deferredCanonicalFile = null;
    } else {
      file = PackagePartitions.DeferredCanonicalFile.access$300(deferredCanonicalFile);
    } 
    return file;
  }
  
  public File getFolder() {
    return PackagePartitions.DeferredCanonicalFile.access$300(this.mFolder);
  }
  
  public File getOverlayFolder() {
    File file;
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mOverlayFolder;
    if (deferredCanonicalFile == null) {
      deferredCanonicalFile = null;
    } else {
      file = PackagePartitions.DeferredCanonicalFile.access$300(deferredCanonicalFile);
    } 
    return file;
  }
  
  public File getPrivAppFolder() {
    File file;
    PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mPrivAppFolder;
    if (deferredCanonicalFile == null) {
      deferredCanonicalFile = null;
    } else {
      file = PackagePartitions.DeferredCanonicalFile.access$300(deferredCanonicalFile);
    } 
    return file;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackagePartitions$SystemPartition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */