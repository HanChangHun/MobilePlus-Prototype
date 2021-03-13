package android.content.pm;

import android.os.Environment;
import android.os.FileUtils;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class PackagePartitions {
  public static final int PARTITION_ODM = 2;
  
  public static final int PARTITION_OEM = 3;
  
  public static final int PARTITION_PRODUCT = 4;
  
  public static final int PARTITION_SYSTEM = 0;
  
  public static final int PARTITION_SYSTEM_EXT = 5;
  
  public static final int PARTITION_VENDOR = 1;
  
  private static final ArrayList<SystemPartition> SYSTEM_PARTITIONS = new ArrayList<>(Arrays.asList(new SystemPartition[] { new SystemPartition(Environment.getRootDirectory(), 0, true, false), new SystemPartition(Environment.getVendorDirectory(), 1, true, true), new SystemPartition(Environment.getOdmDirectory(), 2, true, true), new SystemPartition(Environment.getOemDirectory(), 3, false, true), new SystemPartition(Environment.getProductDirectory(), 4, true, true), new SystemPartition(Environment.getSystemExtDirectory(), 5, true, true) }));
  
  private static File canonicalize(File paramFile) {
    try {
      return paramFile.getCanonicalFile();
    } catch (IOException iOException) {
      return paramFile;
    } 
  }
  
  public static <T> ArrayList<T> getOrderedPartitions(Function<SystemPartition, T> paramFunction) {
    ArrayList<T> arrayList = new ArrayList();
    byte b = 0;
    int i = SYSTEM_PARTITIONS.size();
    while (b < i) {
      T t = paramFunction.apply(SYSTEM_PARTITIONS.get(b));
      if (t != null)
        arrayList.add(t); 
      b++;
    } 
    return arrayList;
  }
  
  private static class DeferredCanonicalFile {
    private File mFile;
    
    private boolean mIsCanonical = false;
    
    private DeferredCanonicalFile(File param1File) {
      this.mFile = param1File;
    }
    
    private DeferredCanonicalFile(File param1File, String param1String) {
      this.mFile = new File(param1File, param1String);
    }
    
    private File getFile() {
      if (!this.mIsCanonical) {
        this.mFile = PackagePartitions.canonicalize(this.mFile);
        this.mIsCanonical = true;
      } 
      return this.mFile;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PartitionType {}
  
  public static class SystemPartition {
    private final PackagePartitions.DeferredCanonicalFile mAppFolder;
    
    private final PackagePartitions.DeferredCanonicalFile mFolder;
    
    private final PackagePartitions.DeferredCanonicalFile mOverlayFolder;
    
    private final PackagePartitions.DeferredCanonicalFile mPrivAppFolder;
    
    public final int type;
    
    public SystemPartition(SystemPartition param1SystemPartition) {
      this.type = param1SystemPartition.type;
      this.mFolder = new PackagePartitions.DeferredCanonicalFile(param1SystemPartition.mFolder.getFile());
      this.mAppFolder = param1SystemPartition.mAppFolder;
      this.mPrivAppFolder = param1SystemPartition.mPrivAppFolder;
      this.mOverlayFolder = param1SystemPartition.mOverlayFolder;
    }
    
    private SystemPartition(File param1File, int param1Int, boolean param1Boolean1, boolean param1Boolean2) {
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile;
      this.type = param1Int;
      File file = null;
      this.mFolder = new PackagePartitions.DeferredCanonicalFile(param1File);
      this.mAppFolder = new PackagePartitions.DeferredCanonicalFile(param1File, "app");
      if (param1Boolean1) {
        deferredCanonicalFile = new PackagePartitions.DeferredCanonicalFile(param1File, "priv-app");
      } else {
        deferredCanonicalFile = null;
      } 
      this.mPrivAppFolder = deferredCanonicalFile;
      if (param1Boolean2) {
        PackagePartitions.DeferredCanonicalFile deferredCanonicalFile1 = new PackagePartitions.DeferredCanonicalFile(param1File, "overlay");
      } else {
        param1File = file;
      } 
      this.mOverlayFolder = (PackagePartitions.DeferredCanonicalFile)param1File;
    }
    
    public SystemPartition(File param1File, SystemPartition param1SystemPartition) {
      this(param1File, i, bool2, bool1);
    }
    
    public boolean containsApp(File param1File) {
      boolean bool;
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mAppFolder;
      if (deferredCanonicalFile != null && FileUtils.contains(deferredCanonicalFile.getFile(), PackagePartitions.canonicalize(param1File))) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean containsFile(File param1File) {
      return FileUtils.contains(this.mFolder.getFile(), PackagePartitions.canonicalize(param1File));
    }
    
    public boolean containsOverlay(File param1File) {
      boolean bool;
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mOverlayFolder;
      if (deferredCanonicalFile != null && FileUtils.contains(deferredCanonicalFile.getFile(), PackagePartitions.canonicalize(param1File))) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean containsPath(String param1String) {
      return containsFile(new File(param1String));
    }
    
    public boolean containsPrivApp(File param1File) {
      boolean bool;
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mPrivAppFolder;
      if (deferredCanonicalFile != null && FileUtils.contains(deferredCanonicalFile.getFile(), PackagePartitions.canonicalize(param1File))) {
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
        file = deferredCanonicalFile.getFile();
      } 
      return file;
    }
    
    public File getFolder() {
      return this.mFolder.getFile();
    }
    
    public File getOverlayFolder() {
      File file;
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mOverlayFolder;
      if (deferredCanonicalFile == null) {
        deferredCanonicalFile = null;
      } else {
        file = deferredCanonicalFile.getFile();
      } 
      return file;
    }
    
    public File getPrivAppFolder() {
      File file;
      PackagePartitions.DeferredCanonicalFile deferredCanonicalFile = this.mPrivAppFolder;
      if (deferredCanonicalFile == null) {
        deferredCanonicalFile = null;
      } else {
        file = deferredCanonicalFile.getFile();
      } 
      return file;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackagePartitions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */