package android.app;

import android.content.pm.ApplicationInfo;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.CompatResources;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.content.res.ResourcesKey;
import android.content.res.loader.ResourcesLoader;
import android.os.IBinder;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import android.view.Display;
import android.view.DisplayAdjustments;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Consumer;

public class ResourcesManager {
  private static final boolean DEBUG = false;
  
  static final String TAG = "ResourcesManager";
  
  private static ResourcesManager sResourcesManager;
  
  private final WeakHashMap<IBinder, ActivityResources> mActivityResourceReferences = new WeakHashMap<>();
  
  private final ArrayMap<Pair<Integer, DisplayAdjustments>, WeakReference<Display>> mAdjustedDisplays = new ArrayMap();
  
  private final ArrayMap<ApkKey, WeakReference<ApkAssets>> mCachedApkAssets = new ArrayMap();
  
  private CompatibilityInfo mResCompatibilityInfo;
  
  private final Configuration mResConfiguration = new Configuration();
  
  private final ArrayMap<ResourcesKey, WeakReference<ResourcesImpl>> mResourceImpls = new ArrayMap();
  
  private final ArrayList<WeakReference<Resources>> mResourceReferences = new ArrayList<>();
  
  private final ReferenceQueue<Resources> mResourcesReferencesQueue = new ReferenceQueue<>();
  
  private final UpdateHandler mUpdateCallbacks = new UpdateHandler();
  
  private void applyConfigurationToResourcesLocked(Configuration paramConfiguration1, CompatibilityInfo paramCompatibilityInfo, Configuration paramConfiguration2, ResourcesKey paramResourcesKey, ResourcesImpl paramResourcesImpl) {
    paramConfiguration2.setTo(paramConfiguration1);
    boolean bool = paramResourcesKey.hasOverrideConfiguration();
    if (bool)
      paramConfiguration2.updateFrom(paramResourcesKey.mOverrideConfiguration); 
    DisplayAdjustments displayAdjustments2 = paramResourcesImpl.getDisplayAdjustments();
    DisplayAdjustments displayAdjustments1 = displayAdjustments2;
    if (paramCompatibilityInfo != null) {
      displayAdjustments1 = new DisplayAdjustments(displayAdjustments2);
      displayAdjustments1.setCompatibilityInfo(paramCompatibilityInfo);
    } 
    int i = paramResourcesKey.mDisplayId;
    if (i == 0)
      displayAdjustments1.setConfiguration(paramConfiguration2); 
    DisplayMetrics displayMetrics = getDisplayMetrics(i, displayAdjustments1);
    if (i != 0) {
      applyNonDefaultDisplayMetricsToConfiguration(displayMetrics, paramConfiguration2);
      if (bool)
        paramConfiguration2.updateFrom(paramResourcesKey.mOverrideConfiguration); 
    } 
    paramResourcesImpl.updateConfiguration(paramConfiguration2, displayMetrics, paramCompatibilityInfo);
  }
  
  private static void applyNonDefaultDisplayMetricsToConfiguration(DisplayMetrics paramDisplayMetrics, Configuration paramConfiguration) {
    paramConfiguration.touchscreen = 1;
    paramConfiguration.densityDpi = paramDisplayMetrics.densityDpi;
    paramConfiguration.screenWidthDp = (int)(paramDisplayMetrics.widthPixels / paramDisplayMetrics.density);
    paramConfiguration.screenHeightDp = (int)(paramDisplayMetrics.heightPixels / paramDisplayMetrics.density);
    int i = Configuration.resetScreenLayout(paramConfiguration.screenLayout);
    if (paramDisplayMetrics.widthPixels > paramDisplayMetrics.heightPixels) {
      paramConfiguration.orientation = 2;
      paramConfiguration.screenLayout = Configuration.reduceScreenLayout(i, paramConfiguration.screenWidthDp, paramConfiguration.screenHeightDp);
    } else {
      paramConfiguration.orientation = 1;
      paramConfiguration.screenLayout = Configuration.reduceScreenLayout(i, paramConfiguration.screenHeightDp, paramConfiguration.screenWidthDp);
    } 
    paramConfiguration.smallestScreenWidthDp = Math.min(paramConfiguration.screenWidthDp, paramConfiguration.screenHeightDp);
    paramConfiguration.compatScreenWidthDp = paramConfiguration.screenWidthDp;
    paramConfiguration.compatScreenHeightDp = paramConfiguration.screenHeightDp;
    paramConfiguration.compatSmallestScreenWidthDp = paramConfiguration.smallestScreenWidthDp;
  }
  
  private static <T> void cleanupReferences(ArrayList<WeakReference<T>> paramArrayList, ReferenceQueue<T> paramReferenceQueue) {
    Reference<? extends T> reference = paramReferenceQueue.poll();
    if (reference == null)
      return; 
    HashSet<Reference<? extends T>> hashSet = new HashSet();
    while (reference != null) {
      hashSet.add(reference);
      reference = paramReferenceQueue.poll();
    } 
    ArrayUtils.unstableRemoveIf(paramArrayList, new _$$Lambda$ResourcesManager$JPMYJ3O5qlFN_c1356pr2ximEb0(hashSet));
  }
  
  private static <T> int countLiveReferences(Collection<WeakReference<T>> paramCollection) {
    int i = 0;
    for (WeakReference<WeakReference> weakReference : paramCollection) {
      if (weakReference != null) {
        weakReference = weakReference.get();
      } else {
        weakReference = null;
      } 
      int j = i;
      if (weakReference != null)
        j = i + 1; 
      i = j;
    } 
    return i;
  }
  
  private ApkAssetsSupplier createApkAssetsSupplierNotLocked(ResourcesKey paramResourcesKey) {
    Trace.traceBegin(8192L, "ResourcesManager#createApkAssetsSupplierNotLocked");
    try {
      ApkAssetsSupplier apkAssetsSupplier = new ApkAssetsSupplier();
      this(this);
      ArrayList<ApkKey> arrayList = extractApkKeys(paramResourcesKey);
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        ApkKey apkKey = arrayList.get(b);
        try {
          apkAssetsSupplier.load(apkKey);
        } catch (IOException iOException) {
          Log.w("ResourcesManager", String.format("failed to preload asset path '%s'", new Object[] { apkKey.path }), iOException);
        } 
        b++;
      } 
      return apkAssetsSupplier;
    } finally {
      Trace.traceEnd(8192L);
    } 
  }
  
  private AssetManager createAssetManager(ResourcesKey paramResourcesKey, ApkAssetsSupplier paramApkAssetsSupplier) {
    AssetManager.Builder builder = new AssetManager.Builder();
    ArrayList<ApkKey> arrayList = extractApkKeys(paramResourcesKey);
    byte b = 0;
    int i = arrayList.size();
    while (true) {
      boolean bool = false;
      if (b < i) {
        ApkKey apkKey = arrayList.get(b);
        if (paramApkAssetsSupplier != null) {
          try {
            ApkAssets apkAssets = paramApkAssetsSupplier.load(apkKey);
            builder.addApkAssets(apkAssets);
          } catch (IOException iOException) {
            if (apkKey.overlay) {
              Log.w("ResourcesManager", String.format("failed to add overlay path '%s'", new Object[] { apkKey.path }), iOException);
            } else if (apkKey.sharedLib) {
              Log.w("ResourcesManager", String.format("asset path '%s' does not exist or contains no resources", new Object[] { apkKey.path }), iOException);
            } else {
              Log.e("ResourcesManager", String.format("failed to add asset path '%s'", new Object[] { apkKey.path }), iOException);
              return null;
            } 
          } 
        } else {
          ApkAssets apkAssets = loadApkAssets(apkKey);
          builder.addApkAssets(apkAssets);
        } 
        b++;
        continue;
      } 
      if (paramResourcesKey.mLoaders != null) {
        ResourcesLoader[] arrayOfResourcesLoader = paramResourcesKey.mLoaders;
        i = arrayOfResourcesLoader.length;
        for (b = bool; b < i; b++)
          builder.addLoader(arrayOfResourcesLoader[b]); 
      } 
      return builder.build();
    } 
  }
  
  private Resources createResources(IBinder paramIBinder, ResourcesKey paramResourcesKey, ClassLoader paramClassLoader, ApkAssetsSupplier paramApkAssetsSupplier) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: aload #4
    //   6: invokespecial findOrCreateResourcesImplForKeyLocked : (Landroid/content/res/ResourcesKey;Landroid/app/ResourcesManager$ApkAssetsSupplier;)Landroid/content/res/ResourcesImpl;
    //   9: astore #4
    //   11: aload #4
    //   13: ifnonnull -> 20
    //   16: aload_0
    //   17: monitorexit
    //   18: aconst_null
    //   19: areturn
    //   20: aload_1
    //   21: ifnull -> 41
    //   24: aload_0
    //   25: aload_1
    //   26: aload_3
    //   27: aload #4
    //   29: aload_2
    //   30: getfield mCompatInfo : Landroid/content/res/CompatibilityInfo;
    //   33: invokespecial createResourcesForActivityLocked : (Landroid/os/IBinder;Ljava/lang/ClassLoader;Landroid/content/res/ResourcesImpl;Landroid/content/res/CompatibilityInfo;)Landroid/content/res/Resources;
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: areturn
    //   41: aload_0
    //   42: aload_3
    //   43: aload #4
    //   45: aload_2
    //   46: getfield mCompatInfo : Landroid/content/res/CompatibilityInfo;
    //   49: invokespecial createResourcesLocked : (Ljava/lang/ClassLoader;Landroid/content/res/ResourcesImpl;Landroid/content/res/CompatibilityInfo;)Landroid/content/res/Resources;
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: areturn
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	57	finally
    //   16	18	57	finally
    //   24	39	57	finally
    //   41	55	57	finally
    //   58	60	57	finally
  }
  
  private Resources createResourcesForActivityLocked(IBinder paramIBinder, ClassLoader paramClassLoader, ResourcesImpl paramResourcesImpl, CompatibilityInfo paramCompatibilityInfo) {
    Resources resources;
    ActivityResources activityResources = getOrCreateActivityResourcesStructLocked(paramIBinder);
    cleanupReferences(activityResources.activityResources, activityResources.activityResourcesQueue);
    if (paramCompatibilityInfo.needsCompatResources()) {
      CompatResources compatResources = new CompatResources(paramClassLoader);
    } else {
      resources = new Resources(paramClassLoader);
    } 
    resources.setImpl(paramResourcesImpl);
    resources.setCallbacks(this.mUpdateCallbacks);
    activityResources.activityResources.add(new WeakReference<>(resources, activityResources.activityResourcesQueue));
    return resources;
  }
  
  private ResourcesImpl createResourcesImpl(ResourcesKey paramResourcesKey, ApkAssetsSupplier paramApkAssetsSupplier) {
    DisplayAdjustments displayAdjustments = new DisplayAdjustments(paramResourcesKey.mOverrideConfiguration);
    displayAdjustments.setCompatibilityInfo(paramResourcesKey.mCompatInfo);
    AssetManager assetManager = createAssetManager(paramResourcesKey, paramApkAssetsSupplier);
    if (assetManager == null)
      return null; 
    DisplayMetrics displayMetrics = getDisplayMetrics(paramResourcesKey.mDisplayId, displayAdjustments);
    return new ResourcesImpl(assetManager, displayMetrics, generateConfig(paramResourcesKey, displayMetrics), displayAdjustments);
  }
  
  private Resources createResourcesLocked(ClassLoader paramClassLoader, ResourcesImpl paramResourcesImpl, CompatibilityInfo paramCompatibilityInfo) {
    CompatResources compatResources;
    Resources resources;
    cleanupReferences(this.mResourceReferences, this.mResourcesReferencesQueue);
    if (paramCompatibilityInfo.needsCompatResources()) {
      compatResources = new CompatResources(paramClassLoader);
    } else {
      resources = new Resources((ClassLoader)compatResources);
    } 
    resources.setImpl(paramResourcesImpl);
    resources.setCallbacks(this.mUpdateCallbacks);
    this.mResourceReferences.add(new WeakReference<>(resources, this.mResourcesReferencesQueue));
    return resources;
  }
  
  private static ArrayList<ApkKey> extractApkKeys(ResourcesKey paramResourcesKey) {
    ArrayList<ApkKey> arrayList = new ArrayList();
    if (paramResourcesKey.mResDir != null)
      arrayList.add(new ApkKey(paramResourcesKey.mResDir, false, false)); 
    if (paramResourcesKey.mSplitResDirs != null) {
      String[] arrayOfString = paramResourcesKey.mSplitResDirs;
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        arrayList.add(new ApkKey(arrayOfString[b], false, false)); 
    } 
    if (paramResourcesKey.mLibDirs != null)
      for (String str : paramResourcesKey.mLibDirs) {
        if (str.endsWith(".apk"))
          arrayList.add(new ApkKey(str, true, false)); 
      }  
    if (paramResourcesKey.mOverlayDirs != null) {
      String[] arrayOfString = paramResourcesKey.mOverlayDirs;
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        arrayList.add(new ApkKey(arrayOfString[b], false, true)); 
    } 
    return arrayList;
  }
  
  private ResourcesKey findKeyForResourceImplLocked(ResourcesImpl paramResourcesImpl) {
    int i = this.mResourceImpls.size();
    byte b = 0;
    while (true) {
      ResourcesImpl resourcesImpl = null;
      if (b < i) {
        WeakReference<ResourcesImpl> weakReference = (WeakReference)this.mResourceImpls.valueAt(b);
        if (weakReference != null)
          resourcesImpl = weakReference.get(); 
        if (paramResourcesImpl == resourcesImpl)
          return (ResourcesKey)this.mResourceImpls.keyAt(b); 
        b++;
        continue;
      } 
      return null;
    } 
  }
  
  private ResourcesImpl findOrCreateResourcesImplForKeyLocked(ResourcesKey paramResourcesKey) {
    return findOrCreateResourcesImplForKeyLocked(paramResourcesKey, null);
  }
  
  private ResourcesImpl findOrCreateResourcesImplForKeyLocked(ResourcesKey paramResourcesKey, ApkAssetsSupplier paramApkAssetsSupplier) {
    ResourcesImpl resourcesImpl1 = findResourcesImplForKeyLocked(paramResourcesKey);
    ResourcesImpl resourcesImpl2 = resourcesImpl1;
    if (resourcesImpl1 == null) {
      ResourcesImpl resourcesImpl = createResourcesImpl(paramResourcesKey, paramApkAssetsSupplier);
      resourcesImpl2 = resourcesImpl;
      if (resourcesImpl != null) {
        this.mResourceImpls.put(paramResourcesKey, new WeakReference<>(resourcesImpl));
        resourcesImpl2 = resourcesImpl;
      } 
    } 
    return resourcesImpl2;
  }
  
  private Resources findResourcesForActivityLocked(IBinder paramIBinder, ResourcesKey paramResourcesKey, ClassLoader paramClassLoader) {
    ActivityResources activityResources = getOrCreateActivityResourcesStructLocked(paramIBinder);
    int i = activityResources.activityResources.size();
    byte b = 0;
    while (true) {
      paramIBinder = null;
      if (b < i) {
        ResourcesKey resourcesKey;
        Resources resources = ((WeakReference<Resources>)activityResources.activityResources.get(b)).get();
        if (resources != null)
          resourcesKey = findKeyForResourceImplLocked(resources.getImpl()); 
        if (resourcesKey != null && Objects.equals(resources.getClassLoader(), paramClassLoader) && Objects.equals(resourcesKey, paramResourcesKey))
          return resources; 
        b++;
        continue;
      } 
      return null;
    } 
  }
  
  private ResourcesImpl findResourcesImplForKeyLocked(ResourcesKey paramResourcesKey) {
    WeakReference<ResourcesImpl> weakReference = (WeakReference)this.mResourceImpls.get(paramResourcesKey);
    if (weakReference != null) {
      ResourcesImpl resourcesImpl = weakReference.get();
    } else {
      weakReference = null;
    } 
    return (ResourcesImpl)((weakReference != null && weakReference.getAssets().isUpToDate()) ? weakReference : null);
  }
  
  private Configuration generateConfig(ResourcesKey paramResourcesKey, DisplayMetrics paramDisplayMetrics) {
    boolean bool;
    if (paramResourcesKey.mDisplayId == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool1 = paramResourcesKey.hasOverrideConfiguration();
    if (!bool || bool1) {
      Configuration configuration2 = new Configuration(getConfiguration());
      if (!bool)
        applyNonDefaultDisplayMetricsToConfiguration(paramDisplayMetrics, configuration2); 
      Configuration configuration1 = configuration2;
      if (bool1) {
        configuration2.updateFrom(paramResourcesKey.mOverrideConfiguration);
        configuration1 = configuration2;
      } 
      return configuration1;
    } 
    return getConfiguration();
  }
  
  private Display getAdjustedDisplay(int paramInt, DisplayAdjustments paramDisplayAdjustments) {
    // Byte code:
    //   0: aload_2
    //   1: ifnull -> 16
    //   4: new android/view/DisplayAdjustments
    //   7: dup
    //   8: aload_2
    //   9: invokespecial <init> : (Landroid/view/DisplayAdjustments;)V
    //   12: astore_2
    //   13: goto -> 24
    //   16: new android/view/DisplayAdjustments
    //   19: dup
    //   20: invokespecial <init> : ()V
    //   23: astore_2
    //   24: iload_1
    //   25: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   28: aload_2
    //   29: invokestatic create : (Ljava/lang/Object;Ljava/lang/Object;)Landroid/util/Pair;
    //   32: astore_2
    //   33: aload_0
    //   34: monitorenter
    //   35: aload_0
    //   36: getfield mAdjustedDisplays : Landroid/util/ArrayMap;
    //   39: aload_2
    //   40: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   43: checkcast java/lang/ref/WeakReference
    //   46: astore_3
    //   47: aload_3
    //   48: ifnull -> 67
    //   51: aload_3
    //   52: invokevirtual get : ()Ljava/lang/Object;
    //   55: checkcast android/view/Display
    //   58: astore_3
    //   59: aload_3
    //   60: ifnull -> 67
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_3
    //   66: areturn
    //   67: invokestatic getInstance : ()Landroid/hardware/display/DisplayManagerGlobal;
    //   70: astore_3
    //   71: aload_3
    //   72: ifnonnull -> 79
    //   75: aload_0
    //   76: monitorexit
    //   77: aconst_null
    //   78: areturn
    //   79: aload_3
    //   80: iload_1
    //   81: aload_2
    //   82: getfield second : Ljava/lang/Object;
    //   85: checkcast android/view/DisplayAdjustments
    //   88: invokevirtual getCompatibleDisplay : (ILandroid/view/DisplayAdjustments;)Landroid/view/Display;
    //   91: astore_3
    //   92: aload_3
    //   93: ifnull -> 122
    //   96: aload_0
    //   97: getfield mAdjustedDisplays : Landroid/util/ArrayMap;
    //   100: astore #4
    //   102: new java/lang/ref/WeakReference
    //   105: astore #5
    //   107: aload #5
    //   109: aload_3
    //   110: invokespecial <init> : (Ljava/lang/Object;)V
    //   113: aload #4
    //   115: aload_2
    //   116: aload #5
    //   118: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   121: pop
    //   122: aload_0
    //   123: monitorexit
    //   124: aload_3
    //   125: areturn
    //   126: astore_2
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_2
    //   130: athrow
    // Exception table:
    //   from	to	target	type
    //   35	47	126	finally
    //   51	59	126	finally
    //   63	65	126	finally
    //   67	71	126	finally
    //   75	77	126	finally
    //   79	92	126	finally
    //   96	122	126	finally
    //   122	124	126	finally
    //   127	129	126	finally
  }
  
  public static ResourcesManager getInstance() {
    // Byte code:
    //   0: ldc android/app/ResourcesManager
    //   2: monitorenter
    //   3: getstatic android/app/ResourcesManager.sResourcesManager : Landroid/app/ResourcesManager;
    //   6: ifnonnull -> 21
    //   9: new android/app/ResourcesManager
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic android/app/ResourcesManager.sResourcesManager : Landroid/app/ResourcesManager;
    //   21: getstatic android/app/ResourcesManager.sResourcesManager : Landroid/app/ResourcesManager;
    //   24: astore_0
    //   25: ldc android/app/ResourcesManager
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc android/app/ResourcesManager
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	28	30	finally
    //   31	34	30	finally
  }
  
  private ActivityResources getOrCreateActivityResourcesStructLocked(IBinder paramIBinder) {
    ActivityResources activityResources1 = this.mActivityResourceReferences.get(paramIBinder);
    ActivityResources activityResources2 = activityResources1;
    if (activityResources1 == null) {
      activityResources2 = new ActivityResources();
      this.mActivityResourceReferences.put(paramIBinder, activityResources2);
    } 
    return activityResources2;
  }
  
  private ApkAssets loadApkAssets(ApkKey paramApkKey) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast java/lang/ref/WeakReference
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull -> 50
    //   18: aload_2
    //   19: invokevirtual get : ()Ljava/lang/Object;
    //   22: checkcast android/content/res/ApkAssets
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull -> 41
    //   30: aload_2
    //   31: invokevirtual isUpToDate : ()Z
    //   34: ifeq -> 41
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_2
    //   40: areturn
    //   41: aload_0
    //   42: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   45: aload_1
    //   46: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   49: pop
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: getfield overlay : Z
    //   56: istore_3
    //   57: iconst_0
    //   58: istore #4
    //   60: iload_3
    //   61: ifeq -> 79
    //   64: aload_1
    //   65: getfield path : Ljava/lang/String;
    //   68: invokestatic overlayPathToIdmapPath : (Ljava/lang/String;)Ljava/lang/String;
    //   71: iconst_0
    //   72: invokestatic loadOverlayFromPath : (Ljava/lang/String;I)Landroid/content/res/ApkAssets;
    //   75: astore_2
    //   76: goto -> 101
    //   79: aload_1
    //   80: getfield path : Ljava/lang/String;
    //   83: astore_2
    //   84: aload_1
    //   85: getfield sharedLib : Z
    //   88: ifeq -> 94
    //   91: iconst_2
    //   92: istore #4
    //   94: aload_2
    //   95: iload #4
    //   97: invokestatic loadFromPath : (Ljava/lang/String;I)Landroid/content/res/ApkAssets;
    //   100: astore_2
    //   101: aload_0
    //   102: monitorenter
    //   103: aload_0
    //   104: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   107: astore #5
    //   109: new java/lang/ref/WeakReference
    //   112: astore #6
    //   114: aload #6
    //   116: aload_2
    //   117: invokespecial <init> : (Ljava/lang/Object;)V
    //   120: aload #5
    //   122: aload_1
    //   123: aload #6
    //   125: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   128: pop
    //   129: aload_0
    //   130: monitorexit
    //   131: aload_2
    //   132: areturn
    //   133: astore_1
    //   134: aload_0
    //   135: monitorexit
    //   136: aload_1
    //   137: athrow
    //   138: astore_1
    //   139: aload_0
    //   140: monitorexit
    //   141: aload_1
    //   142: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	138	finally
    //   18	26	138	finally
    //   30	39	138	finally
    //   41	50	138	finally
    //   50	52	138	finally
    //   103	131	133	finally
    //   134	136	133	finally
    //   139	141	138	finally
  }
  
  private static String overlayPathToIdmapPath(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("/data/resource-cache/");
    stringBuilder.append(paramString.substring(1).replace('/', '@'));
    stringBuilder.append("@idmap");
    return stringBuilder.toString();
  }
  
  private ResourcesKey rebaseActivityOverrideConfig(Resources paramResources, Configuration paramConfiguration1, Configuration paramConfiguration2, int paramInt) {
    StringBuilder stringBuilder;
    ResourcesKey resourcesKey = findKeyForResourceImplLocked(paramResources.getImpl());
    if (resourcesKey == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("can't find ResourcesKey for resources impl=");
      stringBuilder.append(paramResources.getImpl());
      Slog.e("ResourcesManager", stringBuilder.toString());
      return null;
    } 
    Configuration configuration = new Configuration();
    if (paramConfiguration2 != null)
      configuration.setTo(paramConfiguration2); 
    if ((stringBuilder.equals(Configuration.EMPTY) ^ true) != 0 && resourcesKey.hasOverrideConfiguration())
      configuration.updateFrom(Configuration.generateDelta((Configuration)stringBuilder, resourcesKey.mOverrideConfiguration)); 
    return new ResourcesKey(resourcesKey.mResDir, resourcesKey.mSplitResDirs, resourcesKey.mOverlayDirs, resourcesKey.mLibDirs, paramInt, configuration, resourcesKey.mCompatInfo, resourcesKey.mLoaders);
  }
  
  private void rebaseKeyForActivity(IBinder paramIBinder, ResourcesKey paramResourcesKey) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial getOrCreateActivityResourcesStructLocked : (Landroid/os/IBinder;)Landroid/app/ResourcesManager$ActivityResources;
    //   7: astore_1
    //   8: aload_2
    //   9: invokevirtual hasOverrideConfiguration : ()Z
    //   12: ifeq -> 57
    //   15: aload_1
    //   16: getfield overrideConfig : Landroid/content/res/Configuration;
    //   19: getstatic android/content/res/Configuration.EMPTY : Landroid/content/res/Configuration;
    //   22: invokevirtual equals : (Landroid/content/res/Configuration;)Z
    //   25: ifne -> 57
    //   28: new android/content/res/Configuration
    //   31: astore_3
    //   32: aload_3
    //   33: aload_1
    //   34: getfield overrideConfig : Landroid/content/res/Configuration;
    //   37: invokespecial <init> : (Landroid/content/res/Configuration;)V
    //   40: aload_3
    //   41: aload_2
    //   42: getfield mOverrideConfiguration : Landroid/content/res/Configuration;
    //   45: invokevirtual updateFrom : (Landroid/content/res/Configuration;)I
    //   48: pop
    //   49: aload_2
    //   50: getfield mOverrideConfiguration : Landroid/content/res/Configuration;
    //   53: aload_3
    //   54: invokevirtual setTo : (Landroid/content/res/Configuration;)V
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   2	57	60	finally
    //   57	59	60	finally
    //   61	63	60	finally
  }
  
  private void redirectResourcesToNewImplLocked(ArrayMap<ResourcesImpl, ResourcesKey> paramArrayMap) {
    if (paramArrayMap.isEmpty())
      return; 
    int i = this.mResourceReferences.size();
    byte b = 0;
    while (true) {
      Resources resources = null;
      if (b < i) {
        WeakReference<Resources> weakReference = this.mResourceReferences.get(b);
        if (weakReference != null)
          resources = weakReference.get(); 
        if (resources != null) {
          ResourcesKey resourcesKey = (ResourcesKey)paramArrayMap.get(resources.getImpl());
          if (resourcesKey != null) {
            ResourcesImpl resourcesImpl = findOrCreateResourcesImplForKeyLocked(resourcesKey);
            if (resourcesImpl != null) {
              resources.setImpl(resourcesImpl);
            } else {
              throw new Resources.NotFoundException("failed to redirect ResourcesImpl");
            } 
          } 
        } 
        b++;
        continue;
      } 
      for (ActivityResources activityResources : this.mActivityResourceReferences.values()) {
        i = activityResources.activityResources.size();
        for (b = 0; b < i; b++) {
          WeakReference<Resources> weakReference = activityResources.activityResources.get(b);
          if (weakReference != null) {
            Resources resources1 = weakReference.get();
          } else {
            weakReference = null;
          } 
          if (weakReference != null) {
            ResourcesKey resourcesKey = (ResourcesKey)paramArrayMap.get(weakReference.getImpl());
            if (resourcesKey != null) {
              ResourcesImpl resourcesImpl = findOrCreateResourcesImplForKeyLocked(resourcesKey);
              if (resourcesImpl != null) {
                weakReference.setImpl(resourcesImpl);
              } else {
                throw new Resources.NotFoundException("failed to redirect ResourcesImpl");
              } 
            } 
          } 
        } 
      } 
      return;
    } 
  }
  
  public void appendLibAssetForMainAssetPath(String paramString1, String paramString2) {
    appendLibAssetsForMainAssetPath(paramString1, new String[] { paramString2 });
  }
  
  public void appendLibAssetsForMainAssetPath(String paramString, String[] paramArrayOfString) {
    /* monitor enter ThisExpression{ObjectType{android/app/ResourcesManager}} */
    try {
      ArrayMap<ResourcesImpl, ResourcesKey> arrayMap = new ArrayMap();
      this();
      int i = this.mResourceImpls.size();
      for (byte b = 0;; b++) {
        String[] arrayOfString = paramArrayOfString;
        if (b < i) {
          ResourcesKey resourcesKey = (ResourcesKey)this.mResourceImpls.keyAt(b);
          WeakReference<ResourcesImpl> weakReference = (WeakReference)this.mResourceImpls.valueAt(b);
          if (weakReference != null) {
            ResourcesImpl resourcesImpl = weakReference.get();
          } else {
            weakReference = null;
          } 
          if (weakReference != null) {
            String str = resourcesKey.mResDir;
            try {
              if (Objects.equals(str, paramString)) {
                String[] arrayOfString1 = resourcesKey.mLibDirs;
                int j = arrayOfString.length;
                for (byte b1 = 0; b1 < j; b1++)
                  arrayOfString1 = (String[])ArrayUtils.appendElement(String.class, (Object[])arrayOfString1, arrayOfString[b1]); 
                if (!Arrays.equals((Object[])arrayOfString1, (Object[])resourcesKey.mLibDirs)) {
                  ResourcesKey resourcesKey1 = new ResourcesKey();
                  this(resourcesKey.mResDir, resourcesKey.mSplitResDirs, resourcesKey.mOverlayDirs, arrayOfString1, resourcesKey.mDisplayId, resourcesKey.mOverrideConfiguration, resourcesKey.mCompatInfo, resourcesKey.mLoaders);
                  arrayMap.put(weakReference, resourcesKey1);
                } 
              } 
              b++;
              continue;
            } finally {}
            /* monitor exit ThisExpression{ObjectType{android/app/ResourcesManager}} */
            throw paramString;
          } 
        } else {
          redirectResourcesToNewImplLocked(arrayMap);
          /* monitor exit ThisExpression{ObjectType{android/app/ResourcesManager}} */
          return;
        } 
      } 
    } finally {}
    /* monitor exit ThisExpression{ObjectType{android/app/ResourcesManager}} */
    throw paramString;
  }
  
  public boolean applyCompatConfigurationLocked(int paramInt, Configuration paramConfiguration) {
    CompatibilityInfo compatibilityInfo = this.mResCompatibilityInfo;
    if (compatibilityInfo != null && !compatibilityInfo.supportsScreen()) {
      this.mResCompatibilityInfo.applyToConfiguration(paramInt, paramConfiguration);
      return true;
    } 
    return false;
  }
  
  public final boolean applyConfigurationToResources(Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: aconst_null
    //   6: invokevirtual applyConfigurationToResourcesLocked : (Landroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;Landroid/view/DisplayAdjustments;)Z
    //   9: istore_3
    //   10: aload_0
    //   11: monitorexit
    //   12: iload_3
    //   13: ireturn
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	14	finally
    //   15	17	14	finally
  }
  
  public final boolean applyConfigurationToResourcesLocked(Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo) {
    return applyConfigurationToResourcesLocked(paramConfiguration, paramCompatibilityInfo, null);
  }
  
  public final boolean applyConfigurationToResourcesLocked(Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, DisplayAdjustments paramDisplayAdjustments) {
    // Byte code:
    //   0: ldc2_w 8192
    //   3: ldc_w 'ResourcesManager#applyConfigurationToResourcesLocked'
    //   6: invokestatic traceBegin : (JLjava/lang/String;)V
    //   9: aload_0
    //   10: getfield mResConfiguration : Landroid/content/res/Configuration;
    //   13: aload_1
    //   14: invokevirtual isOtherSeqNewer : (Landroid/content/res/Configuration;)Z
    //   17: istore #4
    //   19: iload #4
    //   21: ifne -> 36
    //   24: aload_2
    //   25: ifnonnull -> 36
    //   28: ldc2_w 8192
    //   31: invokestatic traceEnd : (J)V
    //   34: iconst_0
    //   35: ireturn
    //   36: aload_0
    //   37: getfield mResConfiguration : Landroid/content/res/Configuration;
    //   40: aload_1
    //   41: invokevirtual updateFrom : (Landroid/content/res/Configuration;)I
    //   44: istore #5
    //   46: aload_0
    //   47: getfield mAdjustedDisplays : Landroid/util/ArrayMap;
    //   50: invokevirtual clear : ()V
    //   53: aload_0
    //   54: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   57: astore #6
    //   59: iload #5
    //   61: istore #7
    //   63: aload_2
    //   64: ifnull -> 102
    //   67: aload_0
    //   68: getfield mResCompatibilityInfo : Landroid/content/res/CompatibilityInfo;
    //   71: ifnull -> 89
    //   74: iload #5
    //   76: istore #7
    //   78: aload_0
    //   79: getfield mResCompatibilityInfo : Landroid/content/res/CompatibilityInfo;
    //   82: aload_2
    //   83: invokevirtual equals : (Ljava/lang/Object;)Z
    //   86: ifne -> 102
    //   89: aload_0
    //   90: aload_2
    //   91: putfield mResCompatibilityInfo : Landroid/content/res/CompatibilityInfo;
    //   94: iload #5
    //   96: sipush #3328
    //   99: ior
    //   100: istore #7
    //   102: aload_3
    //   103: ifnull -> 112
    //   106: aload_3
    //   107: aload #6
    //   109: invokevirtual adjustGlobalAppMetrics : (Landroid/util/DisplayMetrics;)V
    //   112: aload_1
    //   113: aload #6
    //   115: aload_2
    //   116: invokestatic updateSystemConfiguration : (Landroid/content/res/Configuration;Landroid/util/DisplayMetrics;Landroid/content/res/CompatibilityInfo;)V
    //   119: invokestatic configurationChanged : ()V
    //   122: new android/content/res/Configuration
    //   125: astore #8
    //   127: aload #8
    //   129: invokespecial <init> : ()V
    //   132: aload_0
    //   133: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   136: invokevirtual size : ()I
    //   139: iconst_1
    //   140: isub
    //   141: istore #5
    //   143: iload #5
    //   145: iflt -> 226
    //   148: aload_0
    //   149: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   152: iload #5
    //   154: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   157: checkcast android/content/res/ResourcesKey
    //   160: astore #6
    //   162: aload_0
    //   163: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   166: iload #5
    //   168: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   171: checkcast java/lang/ref/WeakReference
    //   174: astore_3
    //   175: aload_3
    //   176: ifnull -> 190
    //   179: aload_3
    //   180: invokevirtual get : ()Ljava/lang/Object;
    //   183: checkcast android/content/res/ResourcesImpl
    //   186: astore_3
    //   187: goto -> 192
    //   190: aconst_null
    //   191: astore_3
    //   192: aload_3
    //   193: ifnull -> 210
    //   196: aload_0
    //   197: aload_1
    //   198: aload_2
    //   199: aload #8
    //   201: aload #6
    //   203: aload_3
    //   204: invokespecial applyConfigurationToResourcesLocked : (Landroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;Landroid/content/res/Configuration;Landroid/content/res/ResourcesKey;Landroid/content/res/ResourcesImpl;)V
    //   207: goto -> 220
    //   210: aload_0
    //   211: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   214: iload #5
    //   216: invokevirtual removeAt : (I)Ljava/lang/Object;
    //   219: pop
    //   220: iinc #5, -1
    //   223: goto -> 143
    //   226: iload #7
    //   228: ifeq -> 237
    //   231: iconst_1
    //   232: istore #4
    //   234: goto -> 240
    //   237: iconst_0
    //   238: istore #4
    //   240: ldc2_w 8192
    //   243: invokestatic traceEnd : (J)V
    //   246: iload #4
    //   248: ireturn
    //   249: astore_1
    //   250: ldc2_w 8192
    //   253: invokestatic traceEnd : (J)V
    //   256: aload_1
    //   257: athrow
    // Exception table:
    //   from	to	target	type
    //   0	19	249	finally
    //   36	59	249	finally
    //   67	74	249	finally
    //   78	89	249	finally
    //   89	94	249	finally
    //   106	112	249	finally
    //   112	143	249	finally
    //   148	175	249	finally
    //   179	187	249	finally
    //   196	207	249	finally
    //   210	220	249	finally
  }
  
  final void applyNewResourceDirsLocked(ApplicationInfo paramApplicationInfo, String[] paramArrayOfString) {
    // Byte code:
    //   0: ldc2_w 8192
    //   3: ldc_w 'ResourcesManager#applyNewResourceDirsLocked'
    //   6: invokestatic traceBegin : (JLjava/lang/String;)V
    //   9: aload_1
    //   10: invokevirtual getBaseCodePath : ()Ljava/lang/String;
    //   13: astore_3
    //   14: invokestatic myUid : ()I
    //   17: istore #4
    //   19: aload_1
    //   20: getfield uid : I
    //   23: iload #4
    //   25: if_icmpne -> 37
    //   28: aload_1
    //   29: getfield splitSourceDirs : [Ljava/lang/String;
    //   32: astore #5
    //   34: goto -> 43
    //   37: aload_1
    //   38: getfield splitPublicSourceDirs : [Ljava/lang/String;
    //   41: astore #5
    //   43: aload #5
    //   45: invokestatic cloneOrNull : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   48: checkcast [Ljava/lang/String;
    //   51: astore #6
    //   53: aload_1
    //   54: getfield resourceDirs : [Ljava/lang/String;
    //   57: invokestatic cloneOrNull : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   60: checkcast [Ljava/lang/String;
    //   63: astore #7
    //   65: new android/util/ArrayMap
    //   68: astore_1
    //   69: aload_1
    //   70: invokespecial <init> : ()V
    //   73: aload_0
    //   74: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   77: invokevirtual size : ()I
    //   80: istore #4
    //   82: iconst_0
    //   83: istore #8
    //   85: aload_3
    //   86: astore #5
    //   88: iload #8
    //   90: iload #4
    //   92: if_icmpge -> 255
    //   95: aload_0
    //   96: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   99: iload #8
    //   101: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   104: checkcast android/content/res/ResourcesKey
    //   107: astore #9
    //   109: aload_0
    //   110: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   113: iload #8
    //   115: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   118: checkcast java/lang/ref/WeakReference
    //   121: astore_3
    //   122: aload_3
    //   123: ifnull -> 137
    //   126: aload_3
    //   127: invokevirtual get : ()Ljava/lang/Object;
    //   130: checkcast android/content/res/ResourcesImpl
    //   133: astore_3
    //   134: goto -> 139
    //   137: aconst_null
    //   138: astore_3
    //   139: aload_3
    //   140: ifnonnull -> 146
    //   143: goto -> 249
    //   146: aload #9
    //   148: getfield mResDir : Ljava/lang/String;
    //   151: astore #10
    //   153: aload #10
    //   155: ifnull -> 200
    //   158: aload #9
    //   160: getfield mResDir : Ljava/lang/String;
    //   163: aload #5
    //   165: invokevirtual equals : (Ljava/lang/Object;)Z
    //   168: ifne -> 193
    //   171: aload #9
    //   173: getfield mResDir : Ljava/lang/String;
    //   176: astore #10
    //   178: aload_2
    //   179: aload #10
    //   181: invokestatic contains : ([Ljava/lang/Object;Ljava/lang/Object;)Z
    //   184: ifeq -> 190
    //   187: goto -> 200
    //   190: goto -> 249
    //   193: goto -> 200
    //   196: astore_1
    //   197: goto -> 268
    //   200: new android/content/res/ResourcesKey
    //   203: astore #10
    //   205: aload #10
    //   207: aload #5
    //   209: aload #6
    //   211: aload #7
    //   213: aload #9
    //   215: getfield mLibDirs : [Ljava/lang/String;
    //   218: aload #9
    //   220: getfield mDisplayId : I
    //   223: aload #9
    //   225: getfield mOverrideConfiguration : Landroid/content/res/Configuration;
    //   228: aload #9
    //   230: getfield mCompatInfo : Landroid/content/res/CompatibilityInfo;
    //   233: aload #9
    //   235: getfield mLoaders : [Landroid/content/res/loader/ResourcesLoader;
    //   238: invokespecial <init> : (Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;ILandroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;[Landroid/content/res/loader/ResourcesLoader;)V
    //   241: aload_1
    //   242: aload_3
    //   243: aload #10
    //   245: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   248: pop
    //   249: iinc #8, 1
    //   252: goto -> 88
    //   255: aload_0
    //   256: aload_1
    //   257: invokespecial redirectResourcesToNewImplLocked : (Landroid/util/ArrayMap;)V
    //   260: ldc2_w 8192
    //   263: invokestatic traceEnd : (J)V
    //   266: return
    //   267: astore_1
    //   268: ldc2_w 8192
    //   271: invokestatic traceEnd : (J)V
    //   274: aload_1
    //   275: athrow
    // Exception table:
    //   from	to	target	type
    //   0	34	267	finally
    //   37	43	267	finally
    //   43	73	267	finally
    //   73	82	267	finally
    //   95	122	267	finally
    //   126	134	267	finally
    //   146	153	267	finally
    //   158	178	196	finally
    //   178	187	267	finally
    //   200	249	267	finally
    //   255	260	267	finally
  }
  
  protected AssetManager createAssetManager(ResourcesKey paramResourcesKey) {
    return createAssetManager(paramResourcesKey, null);
  }
  
  public Resources createBaseTokenResources(IBinder paramIBinder, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, ClassLoader paramClassLoader, List<ResourcesLoader> paramList) {
    // Byte code:
    //   0: ldc2_w 8192
    //   3: ldc_w 'ResourcesManager#createBaseActivityResources'
    //   6: invokestatic traceBegin : (JLjava/lang/String;)V
    //   9: new android/content/res/ResourcesKey
    //   12: astore #11
    //   14: aload #7
    //   16: ifnull -> 34
    //   19: new android/content/res/Configuration
    //   22: astore #12
    //   24: aload #12
    //   26: aload #7
    //   28: invokespecial <init> : (Landroid/content/res/Configuration;)V
    //   31: goto -> 37
    //   34: aconst_null
    //   35: astore #12
    //   37: aload #10
    //   39: ifnonnull -> 48
    //   42: aconst_null
    //   43: astore #10
    //   45: goto -> 64
    //   48: aload #10
    //   50: iconst_0
    //   51: anewarray android/content/res/loader/ResourcesLoader
    //   54: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   59: checkcast [Landroid/content/res/loader/ResourcesLoader;
    //   62: astore #10
    //   64: aload #11
    //   66: aload_2
    //   67: aload_3
    //   68: aload #4
    //   70: aload #5
    //   72: iload #6
    //   74: aload #12
    //   76: aload #8
    //   78: aload #10
    //   80: invokespecial <init> : (Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;ILandroid/content/res/Configuration;Landroid/content/res/CompatibilityInfo;[Landroid/content/res/loader/ResourcesLoader;)V
    //   83: aload #9
    //   85: ifnull -> 94
    //   88: aload #9
    //   90: astore_2
    //   91: goto -> 98
    //   94: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   97: astore_2
    //   98: aload_0
    //   99: monitorenter
    //   100: aload_0
    //   101: aload_1
    //   102: invokespecial getOrCreateActivityResourcesStructLocked : (Landroid/os/IBinder;)Landroid/app/ResourcesManager$ActivityResources;
    //   105: pop
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_0
    //   109: aload_1
    //   110: aload #7
    //   112: iload #6
    //   114: iconst_0
    //   115: invokevirtual updateResourcesForActivity : (Landroid/os/IBinder;Landroid/content/res/Configuration;IZ)V
    //   118: aload_0
    //   119: aload_1
    //   120: aload #11
    //   122: invokespecial rebaseKeyForActivity : (Landroid/os/IBinder;Landroid/content/res/ResourcesKey;)V
    //   125: aload_0
    //   126: monitorenter
    //   127: aload_0
    //   128: aload_1
    //   129: aload #11
    //   131: aload_2
    //   132: invokespecial findResourcesForActivityLocked : (Landroid/os/IBinder;Landroid/content/res/ResourcesKey;Ljava/lang/ClassLoader;)Landroid/content/res/Resources;
    //   135: astore_3
    //   136: aload_3
    //   137: ifnull -> 150
    //   140: aload_0
    //   141: monitorexit
    //   142: ldc2_w 8192
    //   145: invokestatic traceEnd : (J)V
    //   148: aload_3
    //   149: areturn
    //   150: aload_0
    //   151: monitorexit
    //   152: aload_0
    //   153: aload_1
    //   154: aload #11
    //   156: aload_2
    //   157: aconst_null
    //   158: invokespecial createResources : (Landroid/os/IBinder;Landroid/content/res/ResourcesKey;Ljava/lang/ClassLoader;Landroid/app/ResourcesManager$ApkAssetsSupplier;)Landroid/content/res/Resources;
    //   161: astore_1
    //   162: ldc2_w 8192
    //   165: invokestatic traceEnd : (J)V
    //   168: aload_1
    //   169: areturn
    //   170: astore_1
    //   171: aload_0
    //   172: monitorexit
    //   173: aload_1
    //   174: athrow
    //   175: astore_1
    //   176: aload_0
    //   177: monitorexit
    //   178: aload_1
    //   179: athrow
    //   180: astore_1
    //   181: goto -> 193
    //   184: astore_1
    //   185: goto -> 176
    //   188: astore_1
    //   189: goto -> 193
    //   192: astore_1
    //   193: ldc2_w 8192
    //   196: invokestatic traceEnd : (J)V
    //   199: aload_1
    //   200: athrow
    // Exception table:
    //   from	to	target	type
    //   0	14	192	finally
    //   19	31	192	finally
    //   48	64	192	finally
    //   64	83	192	finally
    //   94	98	192	finally
    //   98	100	188	finally
    //   100	108	175	finally
    //   108	127	180	finally
    //   127	136	170	finally
    //   140	142	170	finally
    //   150	152	170	finally
    //   152	162	180	finally
    //   171	173	170	finally
    //   173	175	180	finally
    //   176	178	184	finally
    //   178	180	180	finally
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/android/internal/util/IndentingPrintWriter
    //   5: astore_3
    //   6: aload_3
    //   7: aload_2
    //   8: ldc_w '  '
    //   11: invokespecial <init> : (Ljava/io/Writer;Ljava/lang/String;)V
    //   14: iconst_0
    //   15: istore #4
    //   17: iload #4
    //   19: aload_1
    //   20: invokevirtual length : ()I
    //   23: iconst_2
    //   24: idiv
    //   25: if_icmpge -> 39
    //   28: aload_3
    //   29: invokevirtual increaseIndent : ()Lcom/android/internal/util/IndentingPrintWriter;
    //   32: pop
    //   33: iinc #4, 1
    //   36: goto -> 17
    //   39: aload_3
    //   40: ldc_w 'ResourcesManager:'
    //   43: invokevirtual println : (Ljava/lang/String;)V
    //   46: aload_3
    //   47: invokevirtual increaseIndent : ()Lcom/android/internal/util/IndentingPrintWriter;
    //   50: pop
    //   51: aload_3
    //   52: ldc_w 'total apks: '
    //   55: invokevirtual print : (Ljava/lang/String;)V
    //   58: aload_3
    //   59: aload_0
    //   60: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   63: invokevirtual values : ()Ljava/util/Collection;
    //   66: invokestatic countLiveReferences : (Ljava/util/Collection;)I
    //   69: invokevirtual println : (I)V
    //   72: aload_3
    //   73: ldc_w 'resources: '
    //   76: invokevirtual print : (Ljava/lang/String;)V
    //   79: aload_0
    //   80: getfield mResourceReferences : Ljava/util/ArrayList;
    //   83: invokestatic countLiveReferences : (Ljava/util/Collection;)I
    //   86: istore #4
    //   88: aload_0
    //   89: getfield mActivityResourceReferences : Ljava/util/WeakHashMap;
    //   92: invokevirtual values : ()Ljava/util/Collection;
    //   95: invokeinterface iterator : ()Ljava/util/Iterator;
    //   100: astore_1
    //   101: aload_1
    //   102: invokeinterface hasNext : ()Z
    //   107: ifeq -> 133
    //   110: iload #4
    //   112: aload_1
    //   113: invokeinterface next : ()Ljava/lang/Object;
    //   118: checkcast android/app/ResourcesManager$ActivityResources
    //   121: getfield activityResources : Ljava/util/ArrayList;
    //   124: invokestatic countLiveReferences : (Ljava/util/Collection;)I
    //   127: iadd
    //   128: istore #4
    //   130: goto -> 101
    //   133: aload_3
    //   134: iload #4
    //   136: invokevirtual println : (I)V
    //   139: aload_3
    //   140: ldc_w 'resource impls: '
    //   143: invokevirtual print : (Ljava/lang/String;)V
    //   146: aload_3
    //   147: aload_0
    //   148: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   151: invokevirtual values : ()Ljava/util/Collection;
    //   154: invokestatic countLiveReferences : (Ljava/util/Collection;)I
    //   157: invokevirtual println : (I)V
    //   160: aload_0
    //   161: monitorexit
    //   162: return
    //   163: astore_1
    //   164: aload_0
    //   165: monitorexit
    //   166: aload_1
    //   167: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	163	finally
    //   17	33	163	finally
    //   39	101	163	finally
    //   101	130	163	finally
    //   133	162	163	finally
    //   164	166	163	finally
  }
  
  public Display getAdjustedDisplay(int paramInt, Resources paramResources) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic getInstance : ()Landroid/hardware/display/DisplayManagerGlobal;
    //   5: astore_3
    //   6: aload_3
    //   7: ifnonnull -> 14
    //   10: aload_0
    //   11: monitorexit
    //   12: aconst_null
    //   13: areturn
    //   14: aload_3
    //   15: iload_1
    //   16: aload_2
    //   17: invokevirtual getCompatibleDisplay : (ILandroid/content/res/Resources;)Landroid/view/Display;
    //   20: astore_2
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_2
    //   24: areturn
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	25	finally
    //   10	12	25	finally
    //   14	23	25	finally
    //   26	28	25	finally
  }
  
  public Configuration getConfiguration() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mResConfiguration : Landroid/content/res/Configuration;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  DisplayMetrics getDisplayMetrics() {
    return getDisplayMetrics(0, DisplayAdjustments.DEFAULT_DISPLAY_ADJUSTMENTS);
  }
  
  protected DisplayMetrics getDisplayMetrics(int paramInt, DisplayAdjustments paramDisplayAdjustments) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    Display display = getAdjustedDisplay(paramInt, paramDisplayAdjustments);
    if (display != null) {
      display.getMetrics(displayMetrics);
    } else {
      displayMetrics.setToDefaults();
    } 
    return displayMetrics;
  }
  
  public Resources getResources(IBinder paramIBinder, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, ClassLoader paramClassLoader, List<ResourcesLoader> paramList) {
    try {
      ResourcesLoader[] arrayOfResourcesLoader;
      Trace.traceBegin(8192L, "ResourcesManager#getResources");
      ResourcesKey resourcesKey = new ResourcesKey();
      List list = null;
      if (paramConfiguration != null) {
        Configuration configuration = new Configuration();
        this(paramConfiguration);
        paramConfiguration = configuration;
      } else {
        paramConfiguration = null;
      } 
      if (paramList == null) {
        paramList = list;
      } else {
        arrayOfResourcesLoader = paramList.<ResourcesLoader>toArray(new ResourcesLoader[0]);
      } 
      this(paramString, paramArrayOfString1, paramArrayOfString2, paramArrayOfString3, paramInt, paramConfiguration, paramCompatibilityInfo, arrayOfResourcesLoader);
      if (paramClassLoader == null)
        paramClassLoader = ClassLoader.getSystemClassLoader(); 
      if (paramIBinder != null) {
        try {
          rebaseKeyForActivity(paramIBinder, resourcesKey);
          Resources resources = createResources(paramIBinder, resourcesKey, paramClassLoader, createApkAssetsSupplierNotLocked(resourcesKey));
          Trace.traceEnd(8192L);
          return resources;
        } finally {}
      } else {
        Resources resources = createResources(paramIBinder, resourcesKey, paramClassLoader, createApkAssetsSupplierNotLocked(resourcesKey));
        Trace.traceEnd(8192L);
        return resources;
      } 
    } finally {}
    Trace.traceEnd(8192L);
    throw paramIBinder;
  }
  
  public void invalidatePath(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_2
    //   4: aload_0
    //   5: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   8: invokevirtual size : ()I
    //   11: iconst_1
    //   12: isub
    //   13: istore_3
    //   14: iload_3
    //   15: iflt -> 82
    //   18: iload_2
    //   19: istore #4
    //   21: aload_0
    //   22: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   25: iload_3
    //   26: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   29: checkcast android/content/res/ResourcesKey
    //   32: aload_1
    //   33: invokevirtual isPathReferenced : (Ljava/lang/String;)Z
    //   36: ifeq -> 73
    //   39: aload_0
    //   40: getfield mResourceImpls : Landroid/util/ArrayMap;
    //   43: iload_3
    //   44: invokevirtual removeAt : (I)Ljava/lang/Object;
    //   47: checkcast java/lang/ref/WeakReference
    //   50: invokevirtual get : ()Ljava/lang/Object;
    //   53: checkcast android/content/res/ResourcesImpl
    //   56: astore #5
    //   58: aload #5
    //   60: ifnull -> 68
    //   63: aload #5
    //   65: invokevirtual flushLayoutCache : ()V
    //   68: iload_2
    //   69: iconst_1
    //   70: iadd
    //   71: istore #4
    //   73: iinc #3, -1
    //   76: iload #4
    //   78: istore_2
    //   79: goto -> 14
    //   82: new java/lang/StringBuilder
    //   85: astore #5
    //   87: aload #5
    //   89: invokespecial <init> : ()V
    //   92: aload #5
    //   94: ldc_w 'Invalidated '
    //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: aload #5
    //   103: iload_2
    //   104: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload #5
    //   110: ldc_w ' asset managers that referenced '
    //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload #5
    //   119: aload_1
    //   120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: ldc 'ResourcesManager'
    //   126: aload #5
    //   128: invokevirtual toString : ()Ljava/lang/String;
    //   131: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload_0
    //   136: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   139: invokevirtual size : ()I
    //   142: iconst_1
    //   143: isub
    //   144: istore_3
    //   145: iload_3
    //   146: iflt -> 213
    //   149: aload_0
    //   150: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   153: iload_3
    //   154: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   157: checkcast android/app/ResourcesManager$ApkKey
    //   160: getfield path : Ljava/lang/String;
    //   163: aload_1
    //   164: invokevirtual equals : (Ljava/lang/Object;)Z
    //   167: ifeq -> 207
    //   170: aload_0
    //   171: getfield mCachedApkAssets : Landroid/util/ArrayMap;
    //   174: iload_3
    //   175: invokevirtual removeAt : (I)Ljava/lang/Object;
    //   178: checkcast java/lang/ref/WeakReference
    //   181: astore #5
    //   183: aload #5
    //   185: ifnull -> 207
    //   188: aload #5
    //   190: invokevirtual get : ()Ljava/lang/Object;
    //   193: ifnull -> 207
    //   196: aload #5
    //   198: invokevirtual get : ()Ljava/lang/Object;
    //   201: checkcast android/content/res/ApkAssets
    //   204: invokevirtual close : ()V
    //   207: iinc #3, -1
    //   210: goto -> 145
    //   213: aload_0
    //   214: monitorexit
    //   215: return
    //   216: astore_1
    //   217: aload_0
    //   218: monitorexit
    //   219: aload_1
    //   220: athrow
    // Exception table:
    //   from	to	target	type
    //   4	14	216	finally
    //   21	58	216	finally
    //   63	68	216	finally
    //   82	145	216	finally
    //   149	183	216	finally
    //   188	207	216	finally
    //   213	215	216	finally
    //   217	219	216	finally
  }
  
  boolean isSameResourcesOverrideConfig(IBinder paramIBinder, Configuration paramConfiguration) {
    /* monitor enter ThisExpression{ObjectType{android/app/ResourcesManager}} */
    if (paramIBinder != null) {
      try {
        ActivityResources activityResources = this.mActivityResourceReferences.get(paramIBinder);
      } finally {}
    } else {
      paramIBinder = null;
    } 
    boolean bool1 = true;
    boolean bool2 = true;
    if (paramIBinder == null) {
      if (paramConfiguration != null)
        bool2 = false; 
      /* monitor exit ThisExpression{ObjectType{android/app/ResourcesManager}} */
      return bool2;
    } 
    if (Objects.equals(((ActivityResources)paramIBinder).overrideConfig, paramConfiguration) || (paramConfiguration != null && ((ActivityResources)paramIBinder).overrideConfig != null && paramConfiguration.diffPublicOnly(((ActivityResources)paramIBinder).overrideConfig) == 0)) {
      bool2 = bool1;
      /* monitor exit ThisExpression{ObjectType{android/app/ResourcesManager}} */
      return bool2;
    } 
    bool2 = false;
    /* monitor exit ThisExpression{ObjectType{android/app/ResourcesManager}} */
    return bool2;
  }
  
  public boolean overrideTokenDisplayAdjustments(IBinder paramIBinder, Consumer<DisplayAdjustments> paramConsumer) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield mActivityResourceReferences : Ljava/util/WeakHashMap;
    //   8: aload_1
    //   9: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast android/app/ResourcesManager$ActivityResources
    //   15: astore_1
    //   16: aload_1
    //   17: ifnonnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: iconst_0
    //   23: ireturn
    //   24: aload_1
    //   25: getfield activityResources : Ljava/util/ArrayList;
    //   28: astore #4
    //   30: aload #4
    //   32: invokevirtual size : ()I
    //   35: iconst_1
    //   36: isub
    //   37: istore #5
    //   39: iload #5
    //   41: iflt -> 78
    //   44: aload #4
    //   46: iload #5
    //   48: invokevirtual get : (I)Ljava/lang/Object;
    //   51: checkcast java/lang/ref/WeakReference
    //   54: invokevirtual get : ()Ljava/lang/Object;
    //   57: checkcast android/content/res/Resources
    //   60: astore_1
    //   61: aload_1
    //   62: ifnull -> 72
    //   65: aload_1
    //   66: aload_2
    //   67: invokevirtual overrideDisplayAdjustments : (Ljava/util/function/Consumer;)V
    //   70: iconst_1
    //   71: istore_3
    //   72: iinc #5, -1
    //   75: goto -> 39
    //   78: aload_0
    //   79: monitorexit
    //   80: iload_3
    //   81: ireturn
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Exception table:
    //   from	to	target	type
    //   4	16	82	finally
    //   20	22	82	finally
    //   24	39	82	finally
    //   44	61	82	finally
    //   65	70	82	finally
    //   78	80	82	finally
    //   83	85	82	finally
  }
  
  public void updateResourcesForActivity(IBinder paramIBinder, Configuration paramConfiguration, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: ldc2_w 8192
    //   3: ldc_w 'ResourcesManager#updateResourcesForActivity'
    //   6: invokestatic traceBegin : (JLjava/lang/String;)V
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: aload_1
    //   13: invokespecial getOrCreateActivityResourcesStructLocked : (Landroid/os/IBinder;)Landroid/app/ResourcesManager$ActivityResources;
    //   16: astore_1
    //   17: aload_1
    //   18: getfield overrideConfig : Landroid/content/res/Configuration;
    //   21: aload_2
    //   22: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   25: ifeq -> 42
    //   28: iload #4
    //   30: ifne -> 42
    //   33: aload_0
    //   34: monitorexit
    //   35: ldc2_w 8192
    //   38: invokestatic traceEnd : (J)V
    //   41: return
    //   42: new android/content/res/Configuration
    //   45: astore #5
    //   47: aload #5
    //   49: aload_1
    //   50: getfield overrideConfig : Landroid/content/res/Configuration;
    //   53: invokespecial <init> : (Landroid/content/res/Configuration;)V
    //   56: aload_2
    //   57: ifnull -> 71
    //   60: aload_1
    //   61: getfield overrideConfig : Landroid/content/res/Configuration;
    //   64: aload_2
    //   65: invokevirtual setTo : (Landroid/content/res/Configuration;)V
    //   68: goto -> 78
    //   71: aload_1
    //   72: getfield overrideConfig : Landroid/content/res/Configuration;
    //   75: invokevirtual unset : ()V
    //   78: aload_1
    //   79: getfield activityResources : Ljava/util/ArrayList;
    //   82: invokevirtual size : ()I
    //   85: istore #6
    //   87: iconst_0
    //   88: istore #7
    //   90: iload #7
    //   92: iload #6
    //   94: if_icmpge -> 178
    //   97: aload_1
    //   98: getfield activityResources : Ljava/util/ArrayList;
    //   101: iload #7
    //   103: invokevirtual get : (I)Ljava/lang/Object;
    //   106: checkcast java/lang/ref/WeakReference
    //   109: invokevirtual get : ()Ljava/lang/Object;
    //   112: checkcast android/content/res/Resources
    //   115: astore #8
    //   117: aload #8
    //   119: ifnonnull -> 125
    //   122: goto -> 172
    //   125: aload_0
    //   126: aload #8
    //   128: aload #5
    //   130: aload_2
    //   131: iload_3
    //   132: invokespecial rebaseActivityOverrideConfig : (Landroid/content/res/Resources;Landroid/content/res/Configuration;Landroid/content/res/Configuration;I)Landroid/content/res/ResourcesKey;
    //   135: astore #9
    //   137: aload #9
    //   139: ifnull -> 172
    //   142: aload_0
    //   143: aload #9
    //   145: invokespecial findOrCreateResourcesImplForKeyLocked : (Landroid/content/res/ResourcesKey;)Landroid/content/res/ResourcesImpl;
    //   148: astore #9
    //   150: aload #9
    //   152: ifnull -> 172
    //   155: aload #9
    //   157: aload #8
    //   159: invokevirtual getImpl : ()Landroid/content/res/ResourcesImpl;
    //   162: if_acmpeq -> 172
    //   165: aload #8
    //   167: aload #9
    //   169: invokevirtual setImpl : (Landroid/content/res/ResourcesImpl;)V
    //   172: iinc #7, 1
    //   175: goto -> 90
    //   178: aload_0
    //   179: monitorexit
    //   180: ldc2_w 8192
    //   183: invokestatic traceEnd : (J)V
    //   186: return
    //   187: astore_1
    //   188: aload_0
    //   189: monitorexit
    //   190: aload_1
    //   191: athrow
    //   192: astore_1
    //   193: ldc2_w 8192
    //   196: invokestatic traceEnd : (J)V
    //   199: aload_1
    //   200: athrow
    // Exception table:
    //   from	to	target	type
    //   0	11	192	finally
    //   11	28	187	finally
    //   33	35	187	finally
    //   42	56	187	finally
    //   60	68	187	finally
    //   71	78	187	finally
    //   78	87	187	finally
    //   97	117	187	finally
    //   125	137	187	finally
    //   142	150	187	finally
    //   155	172	187	finally
    //   178	180	187	finally
    //   188	190	187	finally
    //   190	192	192	finally
  }
  
  private static class ActivityResources {
    public final ArrayList<WeakReference<Resources>> activityResources = new ArrayList<>();
    
    final ReferenceQueue<Resources> activityResourcesQueue = new ReferenceQueue<>();
    
    public final Configuration overrideConfig = new Configuration();
    
    private ActivityResources() {}
  }
  
  private class ApkAssetsSupplier {
    final ArrayMap<ResourcesManager.ApkKey, ApkAssets> mLocalCache = new ArrayMap();
    
    private ApkAssetsSupplier() {}
    
    ApkAssets load(ResourcesManager.ApkKey param1ApkKey) throws IOException {
      ApkAssets apkAssets1 = (ApkAssets)this.mLocalCache.get(param1ApkKey);
      ApkAssets apkAssets2 = apkAssets1;
      if (apkAssets1 == null) {
        apkAssets2 = ResourcesManager.this.loadApkAssets(param1ApkKey);
        this.mLocalCache.put(param1ApkKey, apkAssets2);
      } 
      return apkAssets2;
    }
  }
  
  private static class ApkKey {
    public final boolean overlay;
    
    public final String path;
    
    public final boolean sharedLib;
    
    ApkKey(String param1String, boolean param1Boolean1, boolean param1Boolean2) {
      this.path = param1String;
      this.sharedLib = param1Boolean1;
      this.overlay = param1Boolean2;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof ApkKey;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      bool = bool1;
      if (this.path.equals(((ApkKey)param1Object).path)) {
        bool = bool1;
        if (this.sharedLib == ((ApkKey)param1Object).sharedLib) {
          bool = bool1;
          if (this.overlay == ((ApkKey)param1Object).overlay)
            bool = true; 
        } 
      } 
      return bool;
    }
    
    public int hashCode() {
      return ((1 * 31 + this.path.hashCode()) * 31 + Boolean.hashCode(this.sharedLib)) * 31 + Boolean.hashCode(this.overlay);
    }
  }
  
  private class UpdateHandler implements Resources.UpdateCallbacks {
    private UpdateHandler() {}
    
    public void onLoaderUpdated(ResourcesLoader param1ResourcesLoader) {
      synchronized (ResourcesManager.this) {
        ArrayMap arrayMap = new ArrayMap();
        this();
        for (int i = ResourcesManager.this.mResourceImpls.size() - 1; i >= 0; i--) {
          ResourcesKey resourcesKey = (ResourcesKey)ResourcesManager.this.mResourceImpls.keyAt(i);
          WeakReference<ResourcesImpl> weakReference = (WeakReference)ResourcesManager.this.mResourceImpls.valueAt(i);
          if (weakReference != null && weakReference.get() != null && ArrayUtils.contains((Object[])resourcesKey.mLoaders, param1ResourcesLoader)) {
            ResourcesManager.this.mResourceImpls.remove(resourcesKey);
            arrayMap.put(weakReference.get(), resourcesKey);
          } 
        } 
        ResourcesManager.this.redirectResourcesToNewImplLocked(arrayMap);
        return;
      } 
    }
    
    public void onLoadersChanged(Resources param1Resources, List<ResourcesLoader> param1List) {
      synchronized (ResourcesManager.this) {
        ResourcesKey resourcesKey = ResourcesManager.this.findKeyForResourceImplLocked(param1Resources.getImpl());
        if (resourcesKey != null) {
          ResourcesKey resourcesKey1 = new ResourcesKey();
          this(resourcesKey.mResDir, resourcesKey.mSplitResDirs, resourcesKey.mOverlayDirs, resourcesKey.mLibDirs, resourcesKey.mDisplayId, resourcesKey.mOverrideConfiguration, resourcesKey.mCompatInfo, param1List.<ResourcesLoader>toArray(new ResourcesLoader[0]));
          param1Resources.setImpl(ResourcesManager.this.findOrCreateResourcesImplForKeyLocked(resourcesKey1));
          return;
        } 
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("Cannot modify resource loaders of ResourcesImpl not registered with ResourcesManager");
        throw illegalArgumentException;
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResourcesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */