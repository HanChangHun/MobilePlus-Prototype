package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.SparseArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import libcore.io.IoUtils;

public class SplitAssetDependencyLoader extends SplitDependencyLoader<PackageParser.PackageParserException> implements SplitAssetLoader {
  private final AssetManager[] mCachedAssetManagers;
  
  private final ApkAssets[][] mCachedSplitApks;
  
  private final int mFlags;
  
  private final String[] mSplitPaths;
  
  public SplitAssetDependencyLoader(PackageParser.PackageLite paramPackageLite, SparseArray<int[]> paramSparseArray, int paramInt) {
    super(paramSparseArray);
    String[] arrayOfString2 = new String[paramPackageLite.splitCodePaths.length + 1];
    this.mSplitPaths = arrayOfString2;
    arrayOfString2[0] = paramPackageLite.baseCodePath;
    System.arraycopy(paramPackageLite.splitCodePaths, 0, this.mSplitPaths, 1, paramPackageLite.splitCodePaths.length);
    this.mFlags = paramInt;
    String[] arrayOfString1 = this.mSplitPaths;
    this.mCachedSplitApks = new ApkAssets[arrayOfString1.length][];
    this.mCachedAssetManagers = new AssetManager[arrayOfString1.length];
  }
  
  private static AssetManager createAssetManagerWithAssets(ApkAssets[] paramArrayOfApkAssets) {
    AssetManager assetManager = new AssetManager();
    assetManager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
    assetManager.setApkAssets(paramArrayOfApkAssets, false);
    return assetManager;
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
    AssetManager[] arrayOfAssetManager = this.mCachedAssetManagers;
    int i = arrayOfAssetManager.length;
    for (byte b = 0; b < i; b++)
      IoUtils.closeQuietly((AutoCloseable)arrayOfAssetManager[b]); 
  }
  
  protected void constructSplit(int paramInt1, int[] paramArrayOfint, int paramInt2) throws PackageParser.PackageParserException {
    ArrayList<? super ApkAssets> arrayList = new ArrayList();
    if (paramInt2 >= 0)
      Collections.addAll(arrayList, this.mCachedSplitApks[paramInt2]); 
    arrayList.add(loadApkAssets(this.mSplitPaths[paramInt1], this.mFlags));
    int i = paramArrayOfint.length;
    for (paramInt2 = 0; paramInt2 < i; paramInt2++) {
      int j = paramArrayOfint[paramInt2];
      arrayList.add(loadApkAssets(this.mSplitPaths[j], this.mFlags));
    } 
    this.mCachedSplitApks[paramInt1] = arrayList.<ApkAssets>toArray(new ApkAssets[arrayList.size()]);
    this.mCachedAssetManagers[paramInt1] = createAssetManagerWithAssets(this.mCachedSplitApks[paramInt1]);
  }
  
  public AssetManager getBaseAssetManager() throws PackageParser.PackageParserException {
    loadDependenciesForSplit(0);
    return this.mCachedAssetManagers[0];
  }
  
  public AssetManager getSplitAssetManager(int paramInt) throws PackageParser.PackageParserException {
    loadDependenciesForSplit(paramInt + 1);
    return this.mCachedAssetManagers[paramInt + 1];
  }
  
  protected boolean isSplitCached(int paramInt) {
    boolean bool;
    if (this.mCachedAssetManagers[paramInt] != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/split/SplitAssetDependencyLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */