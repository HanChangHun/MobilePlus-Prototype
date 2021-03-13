package android.app;

import android.content.pm.SharedLibraryInfo;
import android.os.Build;
import android.os.GraphicsEnvironment;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.os.ClassLoaderFactory;
import dalvik.system.PathClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApplicationLoaders {
  private static final String TAG = "ApplicationLoaders";
  
  private static final ApplicationLoaders gApplicationLoaders = new ApplicationLoaders();
  
  private final ArrayMap<String, ClassLoader> mLoaders = new ArrayMap();
  
  private Map<String, CachedClassLoader> mSystemLibsCacheMap = null;
  
  private void createAndCacheNonBootclasspathSystemClassLoader(SharedLibraryInfo paramSharedLibraryInfo) {
    String str = paramSharedLibraryInfo.getPath();
    List list = paramSharedLibraryInfo.getDependencies();
    if (list != null) {
      ArrayList<ClassLoader> arrayList = new ArrayList(list.size());
      Iterator<SharedLibraryInfo> iterator = list.iterator();
      while (iterator.hasNext()) {
        String str1 = ((SharedLibraryInfo)iterator.next()).getPath();
        CachedClassLoader cachedClassLoader = this.mSystemLibsCacheMap.get(str1);
        if (cachedClassLoader != null) {
          arrayList.add(cachedClassLoader.loader);
          continue;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to find dependency ");
        stringBuilder1.append(str1);
        stringBuilder1.append(" of cachedlibrary ");
        stringBuilder1.append(str);
        throw new IllegalStateException(stringBuilder1.toString());
      } 
    } else {
      paramSharedLibraryInfo = null;
    } 
    ClassLoader classLoader = getClassLoader(str, Build.VERSION.SDK_INT, true, null, null, null, null, null, (List<ClassLoader>)paramSharedLibraryInfo);
    if (classLoader != null) {
      CachedClassLoader cachedClassLoader = new CachedClassLoader();
      cachedClassLoader.loader = classLoader;
      cachedClassLoader.sharedLibraries = (List<ClassLoader>)paramSharedLibraryInfo;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Created zygote-cached class loader: ");
      stringBuilder1.append(str);
      Log.d("ApplicationLoaders", stringBuilder1.toString());
      this.mSystemLibsCacheMap.put(str, cachedClassLoader);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Failed to cache ");
    stringBuilder.append(str);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private ClassLoader getClassLoader(String paramString1, int paramInt, boolean paramBoolean, String paramString2, String paramString3, ClassLoader paramClassLoader, String paramString4, String paramString5, List<ClassLoader> paramList) {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader().getParent();
    ArrayMap<String, ClassLoader> arrayMap = this.mLoaders;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{java/lang/String}, ObjectType{java/lang/ClassLoader}]>}, name=null} */
    if (paramClassLoader == null)
      paramClassLoader = classLoader; 
    if (paramClassLoader == classLoader) {
      try {
        classLoader = (ClassLoader)this.mLoaders.get(paramString4);
        if (classLoader != null) {
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{java/lang/String}, ObjectType{java/lang/ClassLoader}]>}, name=null} */
          return classLoader;
        } 
        Trace.traceBegin(64L, paramString1);
        paramClassLoader = ClassLoaderFactory.createClassLoader(paramString1, paramString2, paramString3, paramClassLoader, paramInt, paramBoolean, paramString5, paramList);
        Trace.traceEnd(64L);
        Trace.traceBegin(64L, "setLayerPaths");
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getInstance();
        try {
          graphicsEnvironment.setLayerPaths(paramClassLoader, paramString2, paramString3);
          Trace.traceEnd(64L);
          if (paramString4 != null)
            this.mLoaders.put(paramString4, paramClassLoader); 
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{java/lang/String}, ObjectType{java/lang/ClassLoader}]>}, name=null} */
          return paramClassLoader;
        } finally {}
      } finally {}
    } else {
      Trace.traceBegin(64L, paramString1);
      try {
        ClassLoader classLoader1 = ClassLoaderFactory.createClassLoader(paramString1, null, paramClassLoader, paramString5, paramList);
        Trace.traceEnd(64L);
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{java/lang/String}, ObjectType{java/lang/ClassLoader}]>}, name=null} */
        return classLoader1;
      } finally {}
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{android/util/ArrayMap<[ObjectType{java/lang/String}, ObjectType{java/lang/ClassLoader}]>}, name=null} */
    throw paramString1;
  }
  
  public static ApplicationLoaders getDefault() {
    return gApplicationLoaders;
  }
  
  private static boolean sharedLibrariesEquals(List<ClassLoader> paramList1, List<ClassLoader> paramList2) {
    if (paramList1 == null) {
      boolean bool;
      if (paramList2 == null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return paramList1.equals(paramList2);
  }
  
  void addNative(ClassLoader paramClassLoader, Collection<String> paramCollection) {
    if (paramClassLoader instanceof PathClassLoader) {
      ((PathClassLoader)paramClassLoader).addNativePath(paramCollection);
      return;
    } 
    throw new IllegalStateException("class loader is not a PathClassLoader");
  }
  
  void addPath(ClassLoader paramClassLoader, String paramString) {
    if (paramClassLoader instanceof PathClassLoader) {
      ((PathClassLoader)paramClassLoader).addDexPath(paramString);
      return;
    } 
    throw new IllegalStateException("class loader is not a PathClassLoader");
  }
  
  public void createAndCacheNonBootclasspathSystemClassLoaders(SharedLibraryInfo[] paramArrayOfSharedLibraryInfo) {
    if (this.mSystemLibsCacheMap == null) {
      this.mSystemLibsCacheMap = new HashMap<>();
      int i = paramArrayOfSharedLibraryInfo.length;
      for (byte b = 0; b < i; b++)
        createAndCacheNonBootclasspathSystemClassLoader(paramArrayOfSharedLibraryInfo[b]); 
      return;
    } 
    throw new IllegalStateException("Already cached.");
  }
  
  public ClassLoader createAndCacheWebViewClassLoader(String paramString1, String paramString2, String paramString3) {
    return getClassLoader(paramString1, Build.VERSION.SDK_INT, false, paramString2, null, null, paramString3, null, null);
  }
  
  public ClassLoader getCachedNonBootclasspathSystemLib(String paramString1, ClassLoader paramClassLoader, String paramString2, List<ClassLoader> paramList) {
    StringBuilder stringBuilder1;
    Map<String, CachedClassLoader> map = this.mSystemLibsCacheMap;
    if (map == null)
      return null; 
    if (paramClassLoader != null || paramString2 != null)
      return null; 
    CachedClassLoader cachedClassLoader = map.get(paramString1);
    if (cachedClassLoader == null)
      return null; 
    if (!sharedLibrariesEquals(paramList, cachedClassLoader.sharedLibraries)) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unexpected environment for cached library: (");
      stringBuilder1.append(paramList);
      stringBuilder1.append("|");
      stringBuilder1.append(cachedClassLoader.sharedLibraries);
      stringBuilder1.append(")");
      Log.w("ApplicationLoaders", stringBuilder1.toString());
      return null;
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Returning zygote-cached class loader: ");
    stringBuilder2.append((String)stringBuilder1);
    Log.d("ApplicationLoaders", stringBuilder2.toString());
    return cachedClassLoader.loader;
  }
  
  ClassLoader getClassLoader(String paramString1, int paramInt, boolean paramBoolean, String paramString2, String paramString3, ClassLoader paramClassLoader, String paramString4) {
    return getClassLoaderWithSharedLibraries(paramString1, paramInt, paramBoolean, paramString2, paramString3, paramClassLoader, paramString4, null);
  }
  
  ClassLoader getClassLoaderWithSharedLibraries(String paramString1, int paramInt, boolean paramBoolean, String paramString2, String paramString3, ClassLoader paramClassLoader, String paramString4, List<ClassLoader> paramList) {
    return getClassLoader(paramString1, paramInt, paramBoolean, paramString2, paramString3, paramClassLoader, paramString1, paramString4, paramList);
  }
  
  ClassLoader getSharedLibraryClassLoaderWithSharedLibraries(String paramString1, int paramInt, boolean paramBoolean, String paramString2, String paramString3, ClassLoader paramClassLoader, String paramString4, List<ClassLoader> paramList) {
    ClassLoader classLoader = getCachedNonBootclasspathSystemLib(paramString1, paramClassLoader, paramString4, paramList);
    return (classLoader != null) ? classLoader : getClassLoaderWithSharedLibraries(paramString1, paramInt, paramBoolean, paramString2, paramString3, paramClassLoader, paramString4, paramList);
  }
  
  private static class CachedClassLoader {
    ClassLoader loader;
    
    List<ClassLoader> sharedLibraries;
    
    private CachedClassLoader() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationLoaders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */