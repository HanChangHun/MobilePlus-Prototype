package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.os.Build;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import libcore.io.IoUtils;

public class DefaultSplitAssetLoader implements SplitAssetLoader {
  private final String mBaseCodePath;
  
  private AssetManager mCachedAssetManager;
  
  private final int mFlags;
  
  private final String[] mSplitCodePaths;
  
  public DefaultSplitAssetLoader(PackageParser.PackageLite paramPackageLite, int paramInt) {
    this.mBaseCodePath = paramPackageLite.baseCodePath;
    this.mSplitCodePaths = paramPackageLite.splitCodePaths;
    this.mFlags = paramInt;
  }
  
  private static ApkAssets loadApkAssets(String paramString, int paramInt) throws PackageParser.PackageParserException {
    if ((paramInt & 0x1) == 0 || PackageParser.isApkPath(paramString))
      try {
        return ApkAssets.loadFromPath(paramString);
      } catch (IOException iOException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to load APK at path ");
        stringBuilder1.append(paramString);
        throw new PackageParser.PackageParserException(-2, stringBuilder1.toString(), iOException);
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid package file: ");
    stringBuilder.append(paramString);
    throw new PackageParser.PackageParserException(-100, stringBuilder.toString());
  }
  
  public void close() throws Exception {
    IoUtils.closeQuietly((AutoCloseable)this.mCachedAssetManager);
  }
  
  public AssetManager getBaseAssetManager() throws PackageParser.PackageParserException {
    AssetManager assetManager1 = this.mCachedAssetManager;
    if (assetManager1 != null)
      return assetManager1; 
    String[] arrayOfString = this.mSplitCodePaths;
    if (arrayOfString != null) {
      i = arrayOfString.length;
    } else {
      i = 0;
    } 
    ApkAssets[] arrayOfApkAssets = new ApkAssets[i + 1];
    int i = 0 + 1;
    arrayOfApkAssets[0] = loadApkAssets(this.mBaseCodePath, this.mFlags);
    if (!ArrayUtils.isEmpty((Object[])this.mSplitCodePaths)) {
      String[] arrayOfString1 = this.mSplitCodePaths;
      int j = arrayOfString1.length;
      byte b = 0;
      while (b < j) {
        arrayOfApkAssets[i] = loadApkAssets(arrayOfString1[b], this.mFlags);
        b++;
        i++;
      } 
    } 
    AssetManager assetManager2 = new AssetManager();
    assetManager2.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
    assetManager2.setApkAssets(arrayOfApkAssets, false);
    this.mCachedAssetManager = assetManager2;
    return assetManager2;
  }
  
  public AssetManager getSplitAssetManager(int paramInt) throws PackageParser.PackageParserException {
    return getBaseAssetManager();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/split/DefaultSplitAssetLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */