package android.content.res.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.ApkAssets;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.Closeable;
import java.io.IOException;

public class ResourcesProvider implements AutoCloseable, Closeable {
  private static final String TAG = "ResourcesProvider";
  
  private final ApkAssets mApkAssets;
  
  private final Object mLock = new Object();
  
  private boolean mOpen = true;
  
  private int mOpenCount = 0;
  
  private ResourcesProvider(ApkAssets paramApkAssets) {
    this.mApkAssets = paramApkAssets;
  }
  
  public static ResourcesProvider empty(AssetsProvider paramAssetsProvider) {
    return new ResourcesProvider(ApkAssets.loadEmptyForLoader(4, paramAssetsProvider));
  }
  
  public static ResourcesProvider loadFromApk(ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    return loadFromApk(paramParcelFileDescriptor, null);
  }
  
  public static ResourcesProvider loadFromApk(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, long paramLong2, AssetsProvider paramAssetsProvider) throws IOException {
    return new ResourcesProvider(ApkAssets.loadFromFd(paramParcelFileDescriptor.getFileDescriptor(), paramParcelFileDescriptor.toString(), paramLong1, paramLong2, 4, paramAssetsProvider));
  }
  
  public static ResourcesProvider loadFromApk(ParcelFileDescriptor paramParcelFileDescriptor, AssetsProvider paramAssetsProvider) throws IOException {
    return new ResourcesProvider(ApkAssets.loadFromFd(paramParcelFileDescriptor.getFileDescriptor(), paramParcelFileDescriptor.toString(), 4, paramAssetsProvider));
  }
  
  public static ResourcesProvider loadFromDirectory(String paramString, AssetsProvider paramAssetsProvider) throws IOException {
    return new ResourcesProvider(ApkAssets.loadFromDir(paramString, 4, paramAssetsProvider));
  }
  
  public static ResourcesProvider loadFromSplit(Context paramContext, String paramString) throws IOException {
    ApplicationInfo applicationInfo = paramContext.getApplicationInfo();
    int i = ArrayUtils.indexOf((Object[])applicationInfo.splitNames, paramString);
    if (i >= 0)
      return new ResourcesProvider(ApkAssets.loadFromPath(applicationInfo.getSplitCodePaths()[i], 4, null)); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Split ");
    stringBuilder.append(paramString);
    stringBuilder.append(" not found");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static ResourcesProvider loadFromTable(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, long paramLong2, AssetsProvider paramAssetsProvider) throws IOException {
    return new ResourcesProvider(ApkAssets.loadTableFromFd(paramParcelFileDescriptor.getFileDescriptor(), paramParcelFileDescriptor.toString(), paramLong1, paramLong2, 4, paramAssetsProvider));
  }
  
  public static ResourcesProvider loadFromTable(ParcelFileDescriptor paramParcelFileDescriptor, AssetsProvider paramAssetsProvider) throws IOException {
    return new ResourcesProvider(ApkAssets.loadTableFromFd(paramParcelFileDescriptor.getFileDescriptor(), paramParcelFileDescriptor.toString(), 4, paramAssetsProvider));
  }
  
  public void close() {
    synchronized (this.mLock) {
      if (!this.mOpen)
        return; 
      if (this.mOpenCount == 0) {
        this.mOpen = false;
        try {
          this.mApkAssets.close();
        } finally {}
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Failed to close provider used by ");
      stringBuilder.append(this.mOpenCount);
      stringBuilder.append(" ResourcesLoader instances");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  final void decrementRefCount() {
    synchronized (this.mLock) {
      this.mOpenCount--;
      return;
    } 
  }
  
  protected void finalize() throws Throwable {
    synchronized (this.mLock) {
      if (this.mOpenCount != 0) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("ResourcesProvider ");
        stringBuilder.append(this);
        stringBuilder.append(" finalized with non-zero refs: ");
        stringBuilder.append(this.mOpenCount);
        Log.w("ResourcesProvider", stringBuilder.toString());
      } 
      if (this.mOpen) {
        this.mOpen = false;
        this.mApkAssets.close();
      } 
      return;
    } 
  }
  
  public ApkAssets getApkAssets() {
    return this.mApkAssets;
  }
  
  final void incrementRefCount() {
    synchronized (this.mLock) {
      if (this.mOpen) {
        this.mOpenCount++;
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Operation failed: resources provider is closed");
      throw illegalStateException;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/loader/ResourcesProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */