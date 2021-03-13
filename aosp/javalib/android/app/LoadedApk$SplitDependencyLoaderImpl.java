package android.app;

import android.content.pm.PackageManager;
import android.content.pm.split.SplitDependencyLoader;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class SplitDependencyLoaderImpl extends SplitDependencyLoader<PackageManager.NameNotFoundException> {
  private final ClassLoader[] mCachedClassLoaders;
  
  private final String[][] mCachedResourcePaths;
  
  SplitDependencyLoaderImpl(SparseArray<int[]> paramSparseArray) {
    super(paramSparseArray);
    this.mCachedResourcePaths = new String[(LoadedApk.access$000(paramLoadedApk)).length + 1][];
    this.mCachedClassLoaders = new ClassLoader[(LoadedApk.access$000(paramLoadedApk)).length + 1];
  }
  
  private int ensureSplitLoaded(String paramString) throws PackageManager.NameNotFoundException {
    int i = 0;
    if (paramString != null) {
      i = Arrays.binarySearch((Object[])LoadedApk.access$000(LoadedApk.this), paramString);
      if (i >= 0) {
        i++;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Split name '");
        stringBuilder.append(paramString);
        stringBuilder.append("' is not installed");
        throw new PackageManager.NameNotFoundException(stringBuilder.toString());
      } 
    } 
    loadDependenciesForSplit(i);
    return i;
  }
  
  protected void constructSplit(int paramInt1, int[] paramArrayOfint, int paramInt2) throws PackageManager.NameNotFoundException {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if (paramInt1 == 0) {
      LoadedApk.access$100(LoadedApk.this, null);
      this.mCachedClassLoaders[0] = LoadedApk.access$200(LoadedApk.this);
      paramInt2 = paramArrayOfint.length;
      for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++) {
        i = paramArrayOfint[paramInt1];
        arrayList.add(LoadedApk.access$300(LoadedApk.this)[i - 1]);
      } 
      this.mCachedResourcePaths[0] = arrayList.<String>toArray(new String[arrayList.size()]);
      return;
    } 
    ClassLoader[] arrayOfClassLoader = this.mCachedClassLoaders;
    ClassLoader classLoader = arrayOfClassLoader[paramInt2];
    arrayOfClassLoader[paramInt1] = ApplicationLoaders.getDefault().getClassLoader(LoadedApk.access$400(LoadedApk.this)[paramInt1 - 1], LoadedApk.this.getTargetSdkVersion(), false, null, null, classLoader, LoadedApk.access$500(LoadedApk.this)[paramInt1 - 1]);
    Collections.addAll(arrayList, this.mCachedResourcePaths[paramInt2]);
    arrayList.add(LoadedApk.access$300(LoadedApk.this)[paramInt1 - 1]);
    int j = paramArrayOfint.length;
    for (paramInt2 = i; paramInt2 < j; paramInt2++) {
      i = paramArrayOfint[paramInt2];
      arrayList.add(LoadedApk.access$300(LoadedApk.this)[i - 1]);
    } 
    this.mCachedResourcePaths[paramInt1] = arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  ClassLoader getClassLoaderForSplit(String paramString) throws PackageManager.NameNotFoundException {
    return this.mCachedClassLoaders[ensureSplitLoaded(paramString)];
  }
  
  String[] getSplitPathsForSplit(String paramString) throws PackageManager.NameNotFoundException {
    return this.mCachedResourcePaths[ensureSplitLoaded(paramString)];
  }
  
  protected boolean isSplitCached(int paramInt) {
    boolean bool;
    if (this.mCachedClassLoaders[paramInt] != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$SplitDependencyLoaderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */