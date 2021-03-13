package android.content.res;

import android.content.res.loader.ResourcesLoader;
import android.os.ParcelFileDescriptor;
import android.util.ArraySet;
import android.util.SparseArray;
import android.util.TypedValue;
import com.android.internal.content.om.OverlayConfig;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class AssetManager implements AutoCloseable {
  public static final int ACCESS_BUFFER = 3;
  
  public static final int ACCESS_RANDOM = 1;
  
  public static final int ACCESS_STREAMING = 2;
  
  public static final int ACCESS_UNKNOWN = 0;
  
  private static final boolean DEBUG_REFS = false;
  
  private static final String FRAMEWORK_APK_PATH = "/system/framework/framework-res.apk";
  
  private static final String TAG = "AssetManager";
  
  private static final ApkAssets[] sEmptyApkAssets;
  
  private static final Object sSync = new Object();
  
  static AssetManager sSystem;
  
  private static ApkAssets[] sSystemApkAssets;
  
  private static ArraySet<ApkAssets> sSystemApkAssetsSet;
  
  private ApkAssets[] mApkAssets;
  
  private ResourcesLoader[] mLoaders;
  
  private int mNumRefs = 1;
  
  private long mObject;
  
  private final long[] mOffsets = new long[2];
  
  private boolean mOpen = true;
  
  private HashMap<Long, RuntimeException> mRefStacks;
  
  private final TypedValue mValue = new TypedValue();
  
  static {
    sEmptyApkAssets = new ApkAssets[0];
    sSystem = null;
    sSystemApkAssets = new ApkAssets[0];
  }
  
  public AssetManager() {
    synchronized (sSync) {
      createSystemAssetsInZygoteLocked(false, "/system/framework/framework-res.apk");
      ApkAssets[] arrayOfApkAssets = sSystemApkAssets;
      this.mObject = nativeCreate();
      setApkAssets(arrayOfApkAssets, false);
      return;
    } 
  }
  
  private AssetManager(boolean paramBoolean) {
    this.mObject = nativeCreate();
  }
  
  private int addAssetPathInternal(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'path'
    //   3: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: invokespecial ensureOpenLocked : ()V
    //   13: aload_0
    //   14: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   17: arraylength
    //   18: istore #4
    //   20: iconst_0
    //   21: istore #5
    //   23: iload #5
    //   25: iload #4
    //   27: if_icmpge -> 60
    //   30: aload_0
    //   31: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   34: iload #5
    //   36: aaload
    //   37: invokevirtual getAssetPath : ()Ljava/lang/String;
    //   40: aload_1
    //   41: invokevirtual equals : (Ljava/lang/Object;)Z
    //   44: ifeq -> 54
    //   47: aload_0
    //   48: monitorexit
    //   49: iload #5
    //   51: iconst_1
    //   52: iadd
    //   53: ireturn
    //   54: iinc #5, 1
    //   57: goto -> 23
    //   60: iload_2
    //   61: ifeq -> 125
    //   64: new java/lang/StringBuilder
    //   67: astore #6
    //   69: aload #6
    //   71: invokespecial <init> : ()V
    //   74: aload #6
    //   76: ldc '/data/resource-cache/'
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload #6
    //   84: aload_1
    //   85: iconst_1
    //   86: invokevirtual substring : (I)Ljava/lang/String;
    //   89: bipush #47
    //   91: bipush #64
    //   93: invokevirtual replace : (CC)Ljava/lang/String;
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload #6
    //   102: ldc '@idmap'
    //   104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload #6
    //   110: invokevirtual toString : ()Ljava/lang/String;
    //   113: iconst_0
    //   114: invokestatic loadOverlayFromPath : (Ljava/lang/String;I)Landroid/content/res/ApkAssets;
    //   117: astore_1
    //   118: goto -> 145
    //   121: astore_1
    //   122: goto -> 195
    //   125: iload_3
    //   126: ifeq -> 135
    //   129: iconst_2
    //   130: istore #5
    //   132: goto -> 138
    //   135: iconst_0
    //   136: istore #5
    //   138: aload_1
    //   139: iload #5
    //   141: invokestatic loadFromPath : (Ljava/lang/String;I)Landroid/content/res/ApkAssets;
    //   144: astore_1
    //   145: aload_0
    //   146: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   149: iload #4
    //   151: iconst_1
    //   152: iadd
    //   153: invokestatic copyOf : ([Ljava/lang/Object;I)[Ljava/lang/Object;
    //   156: checkcast [Landroid/content/res/ApkAssets;
    //   159: astore #6
    //   161: aload_0
    //   162: aload #6
    //   164: putfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   167: aload #6
    //   169: iload #4
    //   171: aload_1
    //   172: aastore
    //   173: aload_0
    //   174: getfield mObject : J
    //   177: aload #6
    //   179: iconst_1
    //   180: invokestatic nativeSetApkAssets : (J[Landroid/content/res/ApkAssets;Z)V
    //   183: aload_0
    //   184: iconst_m1
    //   185: invokespecial invalidateCachesLocked : (I)V
    //   188: aload_0
    //   189: monitorexit
    //   190: iload #4
    //   192: iconst_1
    //   193: iadd
    //   194: ireturn
    //   195: aload_0
    //   196: monitorexit
    //   197: iconst_0
    //   198: ireturn
    //   199: astore_1
    //   200: aload_0
    //   201: monitorexit
    //   202: aload_1
    //   203: athrow
    // Exception table:
    //   from	to	target	type
    //   9	20	199	finally
    //   30	49	199	finally
    //   64	118	121	java/io/IOException
    //   64	118	199	finally
    //   138	145	121	java/io/IOException
    //   138	145	199	finally
    //   145	167	199	finally
    //   173	190	199	finally
    //   195	197	199	finally
    //   200	202	199	finally
  }
  
  public static void createSystemAssetsInZygoteLocked(boolean paramBoolean, String paramString) {
    if (sSystem != null && !paramBoolean)
      return; 
    try {
      ArrayList<ApkAssets> arrayList = new ArrayList();
      this();
      arrayList.add(ApkAssets.loadFromPath(paramString, 1));
      String[] arrayOfString = OverlayConfig.getZygoteInstance().createImmutableFrameworkIdmapsInZygote();
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        arrayList.add(ApkAssets.loadOverlayFromPath(arrayOfString[b], 1)); 
      ArraySet<ApkAssets> arraySet = new ArraySet();
      this(arrayList);
      sSystemApkAssetsSet = arraySet;
      sSystemApkAssets = arrayList.<ApkAssets>toArray(new ApkAssets[arrayList.size()]);
      if (sSystem == null) {
        AssetManager assetManager = new AssetManager();
        this(true);
        sSystem = assetManager;
      } 
      sSystem.setApkAssets(sSystemApkAssets, false);
      return;
    } catch (IOException iOException) {
      throw new IllegalStateException("Failed to create system AssetManager", iOException);
    } 
  }
  
  private void decRefsLocked(long paramLong) {
    int i = this.mNumRefs - 1;
    this.mNumRefs = i;
    if (i == 0) {
      paramLong = this.mObject;
      if (paramLong != 0L) {
        nativeDestroy(paramLong);
        this.mObject = 0L;
        this.mApkAssets = sEmptyApkAssets;
      } 
    } 
  }
  
  private void ensureOpenLocked() {
    if (this.mOpen)
      return; 
    throw new RuntimeException("AssetManager has been closed");
  }
  
  private void ensureValidLocked() {
    if (this.mObject != 0L)
      return; 
    throw new RuntimeException("AssetManager has been destroyed");
  }
  
  public static native String getAssetAllocations();
  
  public static native int getGlobalAssetCount();
  
  public static native int getGlobalAssetManagerCount();
  
  public static AssetManager getSystem() {
    synchronized (sSync) {
      createSystemAssetsInZygoteLocked(false, "/system/framework/framework-res.apk");
      return sSystem;
    } 
  }
  
  private void incRefsLocked(long paramLong) {
    this.mNumRefs++;
  }
  
  private void invalidateCachesLocked(int paramInt) {}
  
  private static native void nativeApplyStyle(long paramLong1, long paramLong2, int paramInt1, int paramInt2, long paramLong3, int[] paramArrayOfint, long paramLong4, long paramLong5);
  
  private static native void nativeAssetDestroy(long paramLong);
  
  private static native long nativeAssetGetLength(long paramLong);
  
  private static native long nativeAssetGetRemainingLength(long paramLong);
  
  private static native int nativeAssetRead(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private static native int nativeAssetReadChar(long paramLong);
  
  private static native long nativeAssetSeek(long paramLong1, long paramLong2, int paramInt);
  
  private static native int[] nativeAttributeResolutionStack(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3);
  
  private static native boolean nativeContainsAllocatedTable(long paramLong);
  
  private static native long nativeCreate();
  
  private static native String[] nativeCreateIdmapsForStaticOverlaysTargetingAndroid();
  
  private static native void nativeDestroy(long paramLong);
  
  private static native SparseArray<String> nativeGetAssignedPackageIdentifiers(long paramLong, boolean paramBoolean1, boolean paramBoolean2);
  
  private static native String nativeGetLastResourceResolution(long paramLong);
  
  private static native String[] nativeGetLocales(long paramLong, boolean paramBoolean);
  
  private static native Map nativeGetOverlayableMap(long paramLong, String paramString);
  
  private static native String nativeGetOverlayablesToString(long paramLong, String paramString);
  
  private static native int nativeGetResourceArray(long paramLong, int paramInt, int[] paramArrayOfint);
  
  private static native int nativeGetResourceArraySize(long paramLong, int paramInt);
  
  private static native int nativeGetResourceBagValue(long paramLong, int paramInt1, int paramInt2, TypedValue paramTypedValue);
  
  private static native String nativeGetResourceEntryName(long paramLong, int paramInt);
  
  private static native int nativeGetResourceIdentifier(long paramLong, String paramString1, String paramString2, String paramString3);
  
  private static native int[] nativeGetResourceIntArray(long paramLong, int paramInt);
  
  private static native String nativeGetResourceName(long paramLong, int paramInt);
  
  private static native String nativeGetResourcePackageName(long paramLong, int paramInt);
  
  private static native String[] nativeGetResourceStringArray(long paramLong, int paramInt);
  
  private static native int[] nativeGetResourceStringArrayInfo(long paramLong, int paramInt);
  
  private static native String nativeGetResourceTypeName(long paramLong, int paramInt);
  
  private static native int nativeGetResourceValue(long paramLong, int paramInt, short paramShort, TypedValue paramTypedValue, boolean paramBoolean);
  
  private static native Configuration[] nativeGetSizeConfigurations(long paramLong);
  
  private static native int[] nativeGetStyleAttributes(long paramLong, int paramInt);
  
  private static native String[] nativeList(long paramLong, String paramString) throws IOException;
  
  private static native long nativeOpenAsset(long paramLong, String paramString, int paramInt);
  
  private static native ParcelFileDescriptor nativeOpenAssetFd(long paramLong, String paramString, long[] paramArrayOflong) throws IOException;
  
  private static native long nativeOpenNonAsset(long paramLong, int paramInt1, String paramString, int paramInt2);
  
  private static native ParcelFileDescriptor nativeOpenNonAssetFd(long paramLong, int paramInt, String paramString, long[] paramArrayOflong) throws IOException;
  
  private static native long nativeOpenXmlAsset(long paramLong, int paramInt, String paramString);
  
  private static native long nativeOpenXmlAssetFd(long paramLong, int paramInt, FileDescriptor paramFileDescriptor);
  
  private static native boolean nativeResolveAttrs(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3, int[] paramArrayOfint4);
  
  private static native boolean nativeRetrieveAttributes(long paramLong1, long paramLong2, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3);
  
  private static native void nativeSetApkAssets(long paramLong, ApkAssets[] paramArrayOfApkAssets, boolean paramBoolean);
  
  private static native void nativeSetConfiguration(long paramLong, int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, int paramInt16, int paramInt17);
  
  private static native void nativeSetResourceResolutionLoggingEnabled(long paramLong, boolean paramBoolean);
  
  private static native void nativeThemeApplyStyle(long paramLong1, long paramLong2, int paramInt, boolean paramBoolean);
  
  static native void nativeThemeClear(long paramLong);
  
  private static native void nativeThemeCopy(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
  
  private static native long nativeThemeCreate(long paramLong);
  
  private static native void nativeThemeDestroy(long paramLong);
  
  private static native void nativeThemeDump(long paramLong1, long paramLong2, int paramInt, String paramString1, String paramString2);
  
  private static native int nativeThemeGetAttributeValue(long paramLong1, long paramLong2, int paramInt, TypedValue paramTypedValue, boolean paramBoolean);
  
  static native int nativeThemeGetChangingConfigurations(long paramLong);
  
  @Deprecated
  public int addAssetPath(String paramString) {
    return addAssetPathInternal(paramString, false, false);
  }
  
  @Deprecated
  public int addAssetPathAsSharedLibrary(String paramString) {
    return addAssetPathInternal(paramString, false, true);
  }
  
  @Deprecated
  public int addOverlayPath(String paramString) {
    return addAssetPathInternal(paramString, true, false);
  }
  
  void applyStyle(long paramLong1, int paramInt1, int paramInt2, XmlBlock.Parser paramParser, int[] paramArrayOfint, long paramLong2, long paramLong3) {
    // Byte code:
    //   0: aload #6
    //   2: ldc_w 'inAttrs'
    //   5: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   8: pop
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: invokespecial ensureValidLocked : ()V
    //   15: aload_0
    //   16: getfield mObject : J
    //   19: lstore #11
    //   21: aload #5
    //   23: ifnull -> 36
    //   26: aload #5
    //   28: getfield mParseState : J
    //   31: lstore #13
    //   33: goto -> 39
    //   36: lconst_0
    //   37: lstore #13
    //   39: lload #11
    //   41: lload_1
    //   42: iload_3
    //   43: iload #4
    //   45: lload #13
    //   47: aload #6
    //   49: lload #7
    //   51: lload #9
    //   53: invokestatic nativeApplyStyle : (JJIIJ[IJJ)V
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: astore #5
    //   61: aload_0
    //   62: monitorexit
    //   63: aload #5
    //   65: athrow
    // Exception table:
    //   from	to	target	type
    //   11	21	59	finally
    //   26	33	59	finally
    //   39	58	59	finally
    //   61	63	59	finally
  }
  
  void applyStyleToTheme(long paramLong, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: lload_1
    //   11: iload_3
    //   12: iload #4
    //   14: invokestatic nativeThemeApplyStyle : (JJIZ)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore #5
    //   22: aload_0
    //   23: monitorexit
    //   24: aload #5
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	20	finally
    //   22	24	20	finally
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifne -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_0
    //   14: putfield mOpen : Z
    //   17: aload_0
    //   18: aload_0
    //   19: invokevirtual hashCode : ()I
    //   22: i2l
    //   23: invokespecial decRefsLocked : (J)V
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	29	finally
    //   12	28	29	finally
    //   30	32	29	finally
  }
  
  public boolean containsAllocatedTable() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: invokestatic nativeContainsAllocatedTable : (J)Z
    //   13: istore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_1
    //   17: ireturn
    //   18: astore_2
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_2
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	18	finally
    //   19	21	18	finally
  }
  
  long createTheme() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: invokestatic nativeThemeCreate : (J)J
    //   13: lstore_1
    //   14: aload_0
    //   15: lload_1
    //   16: invokespecial incRefsLocked : (J)V
    //   19: aload_0
    //   20: monitorexit
    //   21: lload_1
    //   22: lreturn
    //   23: astore_3
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_3
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	23	finally
    //   24	26	23	finally
  }
  
  void dumpTheme(long paramLong, int paramInt, String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: lload_1
    //   11: iload_3
    //   12: aload #4
    //   14: aload #5
    //   16: invokestatic nativeThemeDump : (JJILjava/lang/String;Ljava/lang/String;)V
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: astore #4
    //   24: aload_0
    //   25: monitorexit
    //   26: aload #4
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	22	finally
    //   24	26	22	finally
  }
  
  protected void finalize() throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mObject : J
    //   6: lconst_0
    //   7: lcmp
    //   8: ifeq -> 23
    //   11: aload_0
    //   12: getfield mObject : J
    //   15: invokestatic nativeDestroy : (J)V
    //   18: aload_0
    //   19: lconst_0
    //   20: putfield mObject : J
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	26	finally
    //   23	25	26	finally
    //   27	29	26	finally
  }
  
  public int findCookieForPath(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'path'
    //   3: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: invokespecial ensureValidLocked : ()V
    //   13: aload_0
    //   14: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   17: arraylength
    //   18: istore_2
    //   19: iconst_0
    //   20: istore_3
    //   21: iload_3
    //   22: iload_2
    //   23: if_icmpge -> 54
    //   26: aload_1
    //   27: aload_0
    //   28: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   31: iload_3
    //   32: aaload
    //   33: invokevirtual getAssetPath : ()Ljava/lang/String;
    //   36: invokevirtual equals : (Ljava/lang/Object;)Z
    //   39: ifeq -> 48
    //   42: aload_0
    //   43: monitorexit
    //   44: iload_3
    //   45: iconst_1
    //   46: iadd
    //   47: ireturn
    //   48: iinc #3, 1
    //   51: goto -> 21
    //   54: aload_0
    //   55: monitorexit
    //   56: iconst_0
    //   57: ireturn
    //   58: astore_1
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_1
    //   62: athrow
    // Exception table:
    //   from	to	target	type
    //   9	19	58	finally
    //   26	44	58	finally
    //   54	56	58	finally
    //   59	61	58	finally
  }
  
  public ApkAssets[] getApkAssets() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifeq -> 18
    //   9: aload_0
    //   10: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: monitorexit
    //   20: getstatic android/content/res/AssetManager.sEmptyApkAssets : [Landroid/content/res/ApkAssets;
    //   23: areturn
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	24	finally
    //   18	20	24	finally
    //   25	27	24	finally
  }
  
  public String[] getApkPaths() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifeq -> 53
    //   9: aload_0
    //   10: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   13: arraylength
    //   14: anewarray java/lang/String
    //   17: astore_1
    //   18: aload_0
    //   19: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   22: arraylength
    //   23: istore_2
    //   24: iconst_0
    //   25: istore_3
    //   26: iload_3
    //   27: iload_2
    //   28: if_icmpge -> 49
    //   31: aload_1
    //   32: iload_3
    //   33: aload_0
    //   34: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   37: iload_3
    //   38: aaload
    //   39: invokevirtual getAssetPath : ()Ljava/lang/String;
    //   42: aastore
    //   43: iinc #3, 1
    //   46: goto -> 26
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: areturn
    //   53: aload_0
    //   54: monitorexit
    //   55: iconst_0
    //   56: anewarray java/lang/String
    //   59: areturn
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	60	finally
    //   31	43	60	finally
    //   49	51	60	finally
    //   53	55	60	finally
    //   61	63	60	finally
  }
  
  public SparseArray<String> getAssignedPackageIdentifiers() {
    return getAssignedPackageIdentifiers(true, true);
  }
  
  public SparseArray<String> getAssignedPackageIdentifiers(boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: iload_2
    //   12: invokestatic nativeGetAssignedPackageIdentifiers : (JZZ)Landroid/util/SparseArray;
    //   15: astore_3
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_3
    //   19: areturn
    //   20: astore_3
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_3
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	20	finally
    //   21	23	20	finally
  }
  
  int[] getAttributeResolutionStack(long paramLong, int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mObject : J
    //   6: lload_1
    //   7: iload #5
    //   9: iload_3
    //   10: iload #4
    //   12: invokestatic nativeAttributeResolutionStack : (JJIII)[I
    //   15: astore #6
    //   17: aload_0
    //   18: monitorexit
    //   19: aload #6
    //   21: areturn
    //   22: astore #6
    //   24: aload_0
    //   25: monitorexit
    //   26: aload #6
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	22	finally
    //   24	26	22	finally
  }
  
  public String getLastResourceResolution() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: invokestatic nativeGetLastResourceResolution : (J)Ljava/lang/String;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	18	finally
    //   19	21	18	finally
  }
  
  public List<ResourcesLoader> getLoaders() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLoaders : [Landroid/content/res/loader/ResourcesLoader;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull -> 16
    //   9: invokestatic emptyList : ()Ljava/util/List;
    //   12: astore_1
    //   13: goto -> 21
    //   16: aload_1
    //   17: invokestatic asList : ([Ljava/lang/Object;)Ljava/util/List;
    //   20: astore_1
    //   21: aload_1
    //   22: areturn
  }
  
  public String[] getLocales() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iconst_0
    //   11: invokestatic nativeGetLocales : (JZ)[Ljava/lang/String;
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: areturn
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  public String[] getNonSystemLocales() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iconst_1
    //   11: invokestatic nativeGetLocales : (JZ)[Ljava/lang/String;
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: areturn
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  public Map<String, String> getOverlayableMap(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: aload_1
    //   11: invokestatic nativeGetOverlayableMap : (JLjava/lang/String;)Ljava/util/Map;
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: areturn
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  public String getOverlayablesToString(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: aload_1
    //   11: invokestatic nativeGetOverlayablesToString : (JLjava/lang/String;)Ljava/lang/String;
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: areturn
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  CharSequence getPooledStringForCookie(int paramInt1, int paramInt2) {
    return getApkAssets()[paramInt1 - 1].getStringFromPool(paramInt2);
  }
  
  int getResourceArray(int paramInt, int[] paramArrayOfint) {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 'outData'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureValidLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: iload_1
    //   19: aload_2
    //   20: invokestatic nativeGetResourceArray : (JI[I)I
    //   23: istore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_1
    //   27: ireturn
    //   28: astore_2
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   10	26	28	finally
    //   29	31	28	finally
  }
  
  int getResourceArraySize(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceArraySize : (JI)I
    //   14: istore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_1
    //   18: ireturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  CharSequence getResourceBagText(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mValue : Landroid/util/TypedValue;
    //   10: astore_3
    //   11: aload_0
    //   12: getfield mObject : J
    //   15: iload_1
    //   16: iload_2
    //   17: aload_3
    //   18: invokestatic nativeGetResourceBagValue : (JIILandroid/util/TypedValue;)I
    //   21: istore_1
    //   22: iload_1
    //   23: ifgt -> 30
    //   26: aload_0
    //   27: monitorexit
    //   28: aconst_null
    //   29: areturn
    //   30: aload_3
    //   31: aload_3
    //   32: getfield changingConfigurations : I
    //   35: invokestatic activityInfoConfigNativeToJava : (I)I
    //   38: putfield changingConfigurations : I
    //   41: aload_3
    //   42: getfield type : I
    //   45: iconst_3
    //   46: if_icmpne -> 63
    //   49: aload_0
    //   50: iload_1
    //   51: aload_3
    //   52: getfield data : I
    //   55: invokevirtual getPooledStringForCookie : (II)Ljava/lang/CharSequence;
    //   58: astore_3
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_3
    //   62: areturn
    //   63: aload_3
    //   64: invokevirtual coerceToString : ()Ljava/lang/CharSequence;
    //   67: astore_3
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_3
    //   71: areturn
    //   72: astore_3
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_3
    //   76: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	72	finally
    //   26	28	72	finally
    //   30	61	72	finally
    //   63	70	72	finally
    //   73	75	72	finally
  }
  
  String getResourceEntryName(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceEntryName : (JI)Ljava/lang/String;
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  int getResourceIdentifier(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: aload_1
    //   11: aload_2
    //   12: aload_3
    //   13: invokestatic nativeGetResourceIdentifier : (JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   16: istore #4
    //   18: aload_0
    //   19: monitorexit
    //   20: iload #4
    //   22: ireturn
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	23	finally
    //   24	26	23	finally
  }
  
  int[] getResourceIntArray(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceIntArray : (JI)[I
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  String getResourceName(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceName : (JI)Ljava/lang/String;
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  String getResourcePackageName(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourcePackageName : (JI)Ljava/lang/String;
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  String[] getResourceStringArray(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceStringArray : (JI)[Ljava/lang/String;
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  CharSequence getResourceText(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mValue : Landroid/util/TypedValue;
    //   6: astore_2
    //   7: aload_0
    //   8: iload_1
    //   9: iconst_0
    //   10: aload_2
    //   11: iconst_1
    //   12: invokevirtual getResourceValue : (IILandroid/util/TypedValue;Z)Z
    //   15: ifeq -> 27
    //   18: aload_2
    //   19: invokevirtual coerceToString : ()Ljava/lang/CharSequence;
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: areturn
    //   27: aload_0
    //   28: monitorexit
    //   29: aconst_null
    //   30: areturn
    //   31: astore_2
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	31	finally
    //   27	29	31	finally
    //   32	34	31	finally
  }
  
  CharSequence[] getResourceTextArray(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceStringArrayInfo : (JI)[I
    //   14: astore_2
    //   15: aload_2
    //   16: ifnonnull -> 23
    //   19: aload_0
    //   20: monitorexit
    //   21: aconst_null
    //   22: areturn
    //   23: aload_2
    //   24: arraylength
    //   25: istore_3
    //   26: iload_3
    //   27: iconst_2
    //   28: idiv
    //   29: anewarray java/lang/CharSequence
    //   32: astore #4
    //   34: iconst_0
    //   35: istore #5
    //   37: iconst_0
    //   38: istore_1
    //   39: iload #5
    //   41: iload_3
    //   42: if_icmpge -> 100
    //   45: aload_2
    //   46: iload #5
    //   48: iaload
    //   49: istore #6
    //   51: aload_2
    //   52: iload #5
    //   54: iconst_1
    //   55: iadd
    //   56: iaload
    //   57: istore #7
    //   59: iload #7
    //   61: iflt -> 82
    //   64: iload #6
    //   66: ifle -> 82
    //   69: aload_0
    //   70: iload #6
    //   72: iload #7
    //   74: invokevirtual getPooledStringForCookie : (II)Ljava/lang/CharSequence;
    //   77: astore #8
    //   79: goto -> 85
    //   82: aconst_null
    //   83: astore #8
    //   85: aload #4
    //   87: iload_1
    //   88: aload #8
    //   90: aastore
    //   91: iinc #5, 2
    //   94: iinc #1, 1
    //   97: goto -> 39
    //   100: aload_0
    //   101: monitorexit
    //   102: aload #4
    //   104: areturn
    //   105: astore #8
    //   107: aload_0
    //   108: monitorexit
    //   109: aload #8
    //   111: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	105	finally
    //   19	21	105	finally
    //   23	34	105	finally
    //   69	79	105	finally
    //   100	102	105	finally
    //   107	109	105	finally
  }
  
  String getResourceTypeName(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetResourceTypeName : (JI)Ljava/lang/String;
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  boolean getResourceValue(int paramInt1, int paramInt2, TypedValue paramTypedValue, boolean paramBoolean) {
    // Byte code:
    //   0: aload_3
    //   1: ldc_w 'outValue'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureValidLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: iload_1
    //   19: iload_2
    //   20: i2s
    //   21: aload_3
    //   22: iload #4
    //   24: invokestatic nativeGetResourceValue : (JISLandroid/util/TypedValue;Z)I
    //   27: istore_1
    //   28: iload_1
    //   29: ifgt -> 36
    //   32: aload_0
    //   33: monitorexit
    //   34: iconst_0
    //   35: ireturn
    //   36: aload_3
    //   37: aload_3
    //   38: getfield changingConfigurations : I
    //   41: invokestatic activityInfoConfigNativeToJava : (I)I
    //   44: putfield changingConfigurations : I
    //   47: aload_3
    //   48: getfield type : I
    //   51: iconst_3
    //   52: if_icmpne -> 68
    //   55: aload_3
    //   56: aload_0
    //   57: iload_1
    //   58: aload_3
    //   59: getfield data : I
    //   62: invokevirtual getPooledStringForCookie : (II)Ljava/lang/CharSequence;
    //   65: putfield string : Ljava/lang/CharSequence;
    //   68: aload_0
    //   69: monitorexit
    //   70: iconst_1
    //   71: ireturn
    //   72: astore_3
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_3
    //   76: athrow
    // Exception table:
    //   from	to	target	type
    //   10	28	72	finally
    //   32	34	72	finally
    //   36	68	72	finally
    //   68	70	72	finally
    //   73	75	72	finally
  }
  
  Configuration[] getSizeConfigurations() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: invokestatic nativeGetSizeConfigurations : (J)[Landroid/content/res/Configuration;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	18	finally
    //   19	21	18	finally
  }
  
  int[] getStyleAttributes(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeGetStyleAttributes : (JI)[I
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: areturn
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	19	finally
    //   20	22	19	finally
  }
  
  boolean getThemeValue(long paramLong, int paramInt, TypedValue paramTypedValue, boolean paramBoolean) {
    // Byte code:
    //   0: aload #4
    //   2: ldc_w 'outValue'
    //   5: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   8: pop
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: invokespecial ensureValidLocked : ()V
    //   15: aload_0
    //   16: getfield mObject : J
    //   19: lload_1
    //   20: iload_3
    //   21: aload #4
    //   23: iload #5
    //   25: invokestatic nativeThemeGetAttributeValue : (JJILandroid/util/TypedValue;Z)I
    //   28: istore_3
    //   29: iload_3
    //   30: ifgt -> 37
    //   33: aload_0
    //   34: monitorexit
    //   35: iconst_0
    //   36: ireturn
    //   37: aload #4
    //   39: aload #4
    //   41: getfield changingConfigurations : I
    //   44: invokestatic activityInfoConfigNativeToJava : (I)I
    //   47: putfield changingConfigurations : I
    //   50: aload #4
    //   52: getfield type : I
    //   55: iconst_3
    //   56: if_icmpne -> 74
    //   59: aload #4
    //   61: aload_0
    //   62: iload_3
    //   63: aload #4
    //   65: getfield data : I
    //   68: invokevirtual getPooledStringForCookie : (II)Ljava/lang/CharSequence;
    //   71: putfield string : Ljava/lang/CharSequence;
    //   74: aload_0
    //   75: monitorexit
    //   76: iconst_1
    //   77: ireturn
    //   78: astore #4
    //   80: aload_0
    //   81: monitorexit
    //   82: aload #4
    //   84: athrow
    // Exception table:
    //   from	to	target	type
    //   11	29	78	finally
    //   33	35	78	finally
    //   37	74	78	finally
    //   74	76	78	finally
    //   80	82	78	finally
  }
  
  public boolean isUpToDate() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifne -> 13
    //   9: aload_0
    //   10: monitorexit
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_0
    //   14: getfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   17: astore_1
    //   18: aload_1
    //   19: arraylength
    //   20: istore_2
    //   21: iconst_0
    //   22: istore_3
    //   23: iload_3
    //   24: iload_2
    //   25: if_icmpge -> 47
    //   28: aload_1
    //   29: iload_3
    //   30: aaload
    //   31: invokevirtual isUpToDate : ()Z
    //   34: ifne -> 41
    //   37: aload_0
    //   38: monitorexit
    //   39: iconst_0
    //   40: ireturn
    //   41: iinc #3, 1
    //   44: goto -> 23
    //   47: aload_0
    //   48: monitorexit
    //   49: iconst_1
    //   50: ireturn
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	51	finally
    //   13	21	51	finally
    //   28	39	51	finally
    //   47	49	51	finally
    //   52	54	51	finally
  }
  
  public String[] list(String paramString) throws IOException {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'path'
    //   3: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: invokespecial ensureValidLocked : ()V
    //   13: aload_0
    //   14: getfield mObject : J
    //   17: aload_1
    //   18: invokestatic nativeList : (JLjava/lang/String;)[Ljava/lang/String;
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   9	24	26	finally
    //   27	29	26	finally
  }
  
  public InputStream open(String paramString) throws IOException {
    return open(paramString, 2);
  }
  
  public InputStream open(String paramString, int paramInt) throws IOException {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'fileName'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureOpenLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: aload_1
    //   19: iload_2
    //   20: invokestatic nativeOpenAsset : (JLjava/lang/String;I)J
    //   23: lstore_3
    //   24: lload_3
    //   25: lconst_0
    //   26: lcmp
    //   27: ifeq -> 54
    //   30: new android/content/res/AssetManager$AssetInputStream
    //   33: astore_1
    //   34: aload_1
    //   35: aload_0
    //   36: lload_3
    //   37: aconst_null
    //   38: invokespecial <init> : (Landroid/content/res/AssetManager;JLandroid/content/res/AssetManager$1;)V
    //   41: aload_0
    //   42: aload_1
    //   43: invokevirtual hashCode : ()I
    //   46: i2l
    //   47: invokespecial incRefsLocked : (J)V
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: areturn
    //   54: new java/io/FileNotFoundException
    //   57: astore #5
    //   59: new java/lang/StringBuilder
    //   62: astore #6
    //   64: aload #6
    //   66: invokespecial <init> : ()V
    //   69: aload #6
    //   71: ldc_w 'Asset file: '
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload #6
    //   80: aload_1
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload #5
    //   87: aload #6
    //   89: invokevirtual toString : ()Ljava/lang/String;
    //   92: invokespecial <init> : (Ljava/lang/String;)V
    //   95: aload #5
    //   97: athrow
    //   98: astore_1
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    // Exception table:
    //   from	to	target	type
    //   10	24	98	finally
    //   30	52	98	finally
    //   54	98	98	finally
    //   99	101	98	finally
  }
  
  public AssetFileDescriptor openFd(String paramString) throws IOException {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'fileName'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureOpenLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: aload_1
    //   19: aload_0
    //   20: getfield mOffsets : [J
    //   23: invokestatic nativeOpenAssetFd : (JLjava/lang/String;[J)Landroid/os/ParcelFileDescriptor;
    //   26: astore_2
    //   27: aload_2
    //   28: ifnull -> 56
    //   31: new android/content/res/AssetFileDescriptor
    //   34: astore_1
    //   35: aload_1
    //   36: aload_2
    //   37: aload_0
    //   38: getfield mOffsets : [J
    //   41: iconst_0
    //   42: laload
    //   43: aload_0
    //   44: getfield mOffsets : [J
    //   47: iconst_1
    //   48: laload
    //   49: invokespecial <init> : (Landroid/os/ParcelFileDescriptor;JJ)V
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: areturn
    //   56: new java/io/FileNotFoundException
    //   59: astore_2
    //   60: new java/lang/StringBuilder
    //   63: astore_3
    //   64: aload_3
    //   65: invokespecial <init> : ()V
    //   68: aload_3
    //   69: ldc_w 'Asset file: '
    //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: aload_3
    //   77: aload_1
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload_2
    //   83: aload_3
    //   84: invokevirtual toString : ()Ljava/lang/String;
    //   87: invokespecial <init> : (Ljava/lang/String;)V
    //   90: aload_2
    //   91: athrow
    //   92: astore_1
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_1
    //   96: athrow
    // Exception table:
    //   from	to	target	type
    //   10	27	92	finally
    //   31	54	92	finally
    //   56	92	92	finally
    //   93	95	92	finally
  }
  
  public InputStream openNonAsset(int paramInt, String paramString) throws IOException {
    return openNonAsset(paramInt, paramString, 2);
  }
  
  public InputStream openNonAsset(int paramInt1, String paramString, int paramInt2) throws IOException {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 'fileName'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureOpenLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: iload_1
    //   19: aload_2
    //   20: iload_3
    //   21: invokestatic nativeOpenNonAsset : (JILjava/lang/String;I)J
    //   24: lstore #4
    //   26: lload #4
    //   28: lconst_0
    //   29: lcmp
    //   30: ifeq -> 58
    //   33: new android/content/res/AssetManager$AssetInputStream
    //   36: astore_2
    //   37: aload_2
    //   38: aload_0
    //   39: lload #4
    //   41: aconst_null
    //   42: invokespecial <init> : (Landroid/content/res/AssetManager;JLandroid/content/res/AssetManager$1;)V
    //   45: aload_0
    //   46: aload_2
    //   47: invokevirtual hashCode : ()I
    //   50: i2l
    //   51: invokespecial incRefsLocked : (J)V
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_2
    //   57: areturn
    //   58: new java/io/FileNotFoundException
    //   61: astore #6
    //   63: new java/lang/StringBuilder
    //   66: astore #7
    //   68: aload #7
    //   70: invokespecial <init> : ()V
    //   73: aload #7
    //   75: ldc_w 'Asset absolute file: '
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload #7
    //   84: aload_2
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload #6
    //   91: aload #7
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokespecial <init> : (Ljava/lang/String;)V
    //   99: aload #6
    //   101: athrow
    //   102: astore_2
    //   103: aload_0
    //   104: monitorexit
    //   105: aload_2
    //   106: athrow
    // Exception table:
    //   from	to	target	type
    //   10	26	102	finally
    //   33	56	102	finally
    //   58	102	102	finally
    //   103	105	102	finally
  }
  
  public InputStream openNonAsset(String paramString) throws IOException {
    return openNonAsset(0, paramString, 2);
  }
  
  public InputStream openNonAsset(String paramString, int paramInt) throws IOException {
    return openNonAsset(0, paramString, paramInt);
  }
  
  public AssetFileDescriptor openNonAssetFd(int paramInt, String paramString) throws IOException {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 'fileName'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureOpenLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: iload_1
    //   19: aload_2
    //   20: aload_0
    //   21: getfield mOffsets : [J
    //   24: invokestatic nativeOpenNonAssetFd : (JILjava/lang/String;[J)Landroid/os/ParcelFileDescriptor;
    //   27: astore_3
    //   28: aload_3
    //   29: ifnull -> 57
    //   32: new android/content/res/AssetFileDescriptor
    //   35: astore_2
    //   36: aload_2
    //   37: aload_3
    //   38: aload_0
    //   39: getfield mOffsets : [J
    //   42: iconst_0
    //   43: laload
    //   44: aload_0
    //   45: getfield mOffsets : [J
    //   48: iconst_1
    //   49: laload
    //   50: invokespecial <init> : (Landroid/os/ParcelFileDescriptor;JJ)V
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_2
    //   56: areturn
    //   57: new java/io/FileNotFoundException
    //   60: astore_3
    //   61: new java/lang/StringBuilder
    //   64: astore #4
    //   66: aload #4
    //   68: invokespecial <init> : ()V
    //   71: aload #4
    //   73: ldc_w 'Asset absolute file: '
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload #4
    //   82: aload_2
    //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload_3
    //   88: aload #4
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: invokespecial <init> : (Ljava/lang/String;)V
    //   96: aload_3
    //   97: athrow
    //   98: astore_2
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_2
    //   102: athrow
    // Exception table:
    //   from	to	target	type
    //   10	28	98	finally
    //   32	55	98	finally
    //   57	98	98	finally
    //   99	101	98	finally
  }
  
  public AssetFileDescriptor openNonAssetFd(String paramString) throws IOException {
    return openNonAssetFd(0, paramString);
  }
  
  XmlBlock openXmlBlockAsset(int paramInt, String paramString) throws IOException {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 'fileName'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokespecial ensureOpenLocked : ()V
    //   14: aload_0
    //   15: getfield mObject : J
    //   18: iload_1
    //   19: aload_2
    //   20: invokestatic nativeOpenXmlAsset : (JILjava/lang/String;)J
    //   23: lstore_3
    //   24: lload_3
    //   25: lconst_0
    //   26: lcmp
    //   27: ifeq -> 53
    //   30: new android/content/res/XmlBlock
    //   33: astore_2
    //   34: aload_2
    //   35: aload_0
    //   36: lload_3
    //   37: invokespecial <init> : (Landroid/content/res/AssetManager;J)V
    //   40: aload_0
    //   41: aload_2
    //   42: invokevirtual hashCode : ()I
    //   45: i2l
    //   46: invokespecial incRefsLocked : (J)V
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_2
    //   52: areturn
    //   53: new java/io/FileNotFoundException
    //   56: astore #5
    //   58: new java/lang/StringBuilder
    //   61: astore #6
    //   63: aload #6
    //   65: invokespecial <init> : ()V
    //   68: aload #6
    //   70: ldc_w 'Asset XML file: '
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload #6
    //   79: aload_2
    //   80: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload #5
    //   86: aload #6
    //   88: invokevirtual toString : ()Ljava/lang/String;
    //   91: invokespecial <init> : (Ljava/lang/String;)V
    //   94: aload #5
    //   96: athrow
    //   97: astore_2
    //   98: aload_0
    //   99: monitorexit
    //   100: aload_2
    //   101: athrow
    // Exception table:
    //   from	to	target	type
    //   10	24	97	finally
    //   30	51	97	finally
    //   53	97	97	finally
    //   98	100	97	finally
  }
  
  XmlBlock openXmlBlockAsset(String paramString) throws IOException {
    return openXmlBlockAsset(0, paramString);
  }
  
  public XmlResourceParser openXmlResourceParser(int paramInt, String paramString) throws IOException {
    XmlBlock xmlBlock = openXmlBlockAsset(paramInt, paramString);
    try {
      XmlResourceParser xmlResourceParser = xmlBlock.newParser();
      if (xmlResourceParser != null)
        return xmlResourceParser; 
      AssertionError assertionError = new AssertionError();
      this("block.newParser() returned a null parser");
      throw assertionError;
    } finally {
      if (xmlBlock != null)
        try {
          xmlBlock.close();
        } finally {
          xmlBlock = null;
        }  
    } 
  }
  
  public XmlResourceParser openXmlResourceParser(String paramString) throws IOException {
    return openXmlResourceParser(0, paramString);
  }
  
  void releaseTheme(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lload_1
    //   3: invokestatic nativeThemeDestroy : (J)V
    //   6: aload_0
    //   7: lload_1
    //   8: invokespecial decRefsLocked : (J)V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_3
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	14	finally
    //   15	17	14	finally
  }
  
  boolean resolveAttrs(long paramLong, int paramInt1, int paramInt2, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3, int[] paramArrayOfint4) {
    // Byte code:
    //   0: aload #6
    //   2: ldc_w 'inAttrs'
    //   5: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   8: pop
    //   9: aload #7
    //   11: ldc_w 'outValues'
    //   14: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   17: pop
    //   18: aload #8
    //   20: ldc_w 'outIndices'
    //   23: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   26: pop
    //   27: aload_0
    //   28: monitorenter
    //   29: aload_0
    //   30: invokespecial ensureValidLocked : ()V
    //   33: aload_0
    //   34: getfield mObject : J
    //   37: lload_1
    //   38: iload_3
    //   39: iload #4
    //   41: aload #5
    //   43: aload #6
    //   45: aload #7
    //   47: aload #8
    //   49: invokestatic nativeResolveAttrs : (JJII[I[I[I[I)Z
    //   52: istore #9
    //   54: aload_0
    //   55: monitorexit
    //   56: iload #9
    //   58: ireturn
    //   59: astore #5
    //   61: aload_0
    //   62: monitorexit
    //   63: aload #5
    //   65: athrow
    // Exception table:
    //   from	to	target	type
    //   29	56	59	finally
    //   61	63	59	finally
  }
  
  boolean retrieveAttributes(XmlBlock.Parser paramParser, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'parser'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: aload_2
    //   9: ldc_w 'inAttrs'
    //   12: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   15: pop
    //   16: aload_3
    //   17: ldc_w 'outValues'
    //   20: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   23: pop
    //   24: aload #4
    //   26: ldc_w 'outIndices'
    //   29: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   32: pop
    //   33: aload_0
    //   34: monitorenter
    //   35: aload_0
    //   36: invokespecial ensureValidLocked : ()V
    //   39: aload_0
    //   40: getfield mObject : J
    //   43: aload_1
    //   44: getfield mParseState : J
    //   47: aload_2
    //   48: aload_3
    //   49: aload #4
    //   51: invokestatic nativeRetrieveAttributes : (JJ[I[I[I)Z
    //   54: istore #5
    //   56: aload_0
    //   57: monitorexit
    //   58: iload #5
    //   60: ireturn
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Exception table:
    //   from	to	target	type
    //   35	58	61	finally
    //   62	64	61	finally
  }
  
  public void setApkAssets(ApkAssets[] paramArrayOfApkAssets, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'apkAssets'
    //   4: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   7: pop
    //   8: getstatic android/content/res/AssetManager.sSystemApkAssets : [Landroid/content/res/ApkAssets;
    //   11: astore_3
    //   12: aload_3
    //   13: arraylength
    //   14: aload_1
    //   15: arraylength
    //   16: iadd
    //   17: anewarray android/content/res/ApkAssets
    //   20: astore #4
    //   22: aload_3
    //   23: arraylength
    //   24: istore #5
    //   26: iconst_0
    //   27: istore #6
    //   29: aload_3
    //   30: iconst_0
    //   31: aload #4
    //   33: iconst_0
    //   34: iload #5
    //   36: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   39: getstatic android/content/res/AssetManager.sSystemApkAssets : [Landroid/content/res/ApkAssets;
    //   42: arraylength
    //   43: istore #5
    //   45: aload_1
    //   46: arraylength
    //   47: istore #7
    //   49: iload #6
    //   51: iload #7
    //   53: if_icmpge -> 97
    //   56: aload_1
    //   57: iload #6
    //   59: aaload
    //   60: astore_3
    //   61: iload #5
    //   63: istore #8
    //   65: getstatic android/content/res/AssetManager.sSystemApkAssetsSet : Landroid/util/ArraySet;
    //   68: aload_3
    //   69: invokevirtual contains : (Ljava/lang/Object;)Z
    //   72: ifne -> 87
    //   75: aload #4
    //   77: iload #5
    //   79: aload_3
    //   80: aastore
    //   81: iload #5
    //   83: iconst_1
    //   84: iadd
    //   85: istore #8
    //   87: iinc #6, 1
    //   90: iload #8
    //   92: istore #5
    //   94: goto -> 49
    //   97: aload #4
    //   99: astore_1
    //   100: iload #5
    //   102: aload #4
    //   104: arraylength
    //   105: if_icmpeq -> 119
    //   108: aload #4
    //   110: iload #5
    //   112: invokestatic copyOf : ([Ljava/lang/Object;I)[Ljava/lang/Object;
    //   115: checkcast [Landroid/content/res/ApkAssets;
    //   118: astore_1
    //   119: aload_0
    //   120: monitorenter
    //   121: aload_0
    //   122: invokespecial ensureOpenLocked : ()V
    //   125: aload_0
    //   126: aload_1
    //   127: putfield mApkAssets : [Landroid/content/res/ApkAssets;
    //   130: aload_0
    //   131: getfield mObject : J
    //   134: aload_1
    //   135: iload_2
    //   136: invokestatic nativeSetApkAssets : (J[Landroid/content/res/ApkAssets;Z)V
    //   139: iload_2
    //   140: ifeq -> 148
    //   143: aload_0
    //   144: iconst_m1
    //   145: invokespecial invalidateCachesLocked : (I)V
    //   148: aload_0
    //   149: monitorexit
    //   150: return
    //   151: astore_1
    //   152: aload_0
    //   153: monitorexit
    //   154: aload_1
    //   155: athrow
    // Exception table:
    //   from	to	target	type
    //   121	139	151	finally
    //   143	148	151	finally
    //   148	150	151	finally
    //   152	154	151	finally
  }
  
  public void setConfiguration(int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, int paramInt16, int paramInt17) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: iload_2
    //   12: aload_3
    //   13: iload #4
    //   15: iload #5
    //   17: iload #6
    //   19: iload #7
    //   21: iload #8
    //   23: iload #9
    //   25: iload #10
    //   27: iload #11
    //   29: iload #12
    //   31: iload #13
    //   33: iload #14
    //   35: iload #15
    //   37: iload #16
    //   39: iload #17
    //   41: iload #18
    //   43: invokestatic nativeSetConfiguration : (JIILjava/lang/String;IIIIIIIIIIIIIII)V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_3
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_3
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	48	49	finally
    //   50	52	49	finally
  }
  
  void setLoaders(List<ResourcesLoader> paramList) {
    Objects.requireNonNull(paramList, "newLoaders");
    ArrayList<ApkAssets> arrayList = new ArrayList();
    int i = 0;
    while (true) {
      ApkAssets[] arrayOfApkAssets = this.mApkAssets;
      if (i < arrayOfApkAssets.length) {
        if (!arrayOfApkAssets[i].isForLoader())
          arrayList.add(this.mApkAssets[i]); 
        i++;
        continue;
      } 
      if (!paramList.isEmpty()) {
        int j = arrayList.size();
        ArraySet arraySet = new ArraySet();
        for (i = paramList.size() - 1; i >= 0; i--) {
          List<ApkAssets> list = ((ResourcesLoader)paramList.get(i)).getApkAssets();
          for (int k = list.size() - 1; k >= 0; k--) {
            ApkAssets apkAssets = list.get(k);
            if (arraySet.add(apkAssets))
              arrayList.add(j, apkAssets); 
          } 
        } 
      } 
      this.mLoaders = paramList.<ResourcesLoader>toArray(new ResourcesLoader[0]);
      setApkAssets(arrayList.<ApkAssets>toArray(new ApkAssets[0]), true);
      return;
    } 
  }
  
  public void setResourceResolutionLoggingEnabled(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_0
    //   7: getfield mObject : J
    //   10: iload_1
    //   11: invokestatic nativeSetResourceResolutionLoggingEnabled : (JZ)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: astore_2
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_2
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	17	finally
    //   18	20	17	finally
  }
  
  void setThemeTo(long paramLong1, AssetManager paramAssetManager, long paramLong2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureValidLocked : ()V
    //   6: aload_3
    //   7: monitorenter
    //   8: aload_3
    //   9: invokespecial ensureValidLocked : ()V
    //   12: aload_0
    //   13: getfield mObject : J
    //   16: lload_1
    //   17: aload_3
    //   18: getfield mObject : J
    //   21: lload #4
    //   23: invokestatic nativeThemeCopy : (JJJJ)V
    //   26: aload_3
    //   27: monitorexit
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore #6
    //   33: aload_3
    //   34: monitorexit
    //   35: aload #6
    //   37: athrow
    //   38: astore_3
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_3
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	38	finally
    //   8	28	31	finally
    //   28	30	38	finally
    //   33	35	31	finally
    //   35	38	38	finally
    //   39	41	38	finally
  }
  
  void xmlBlockGone(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: i2l
    //   4: lstore_2
    //   5: aload_0
    //   6: lload_2
    //   7: invokespecial decRefsLocked : (J)V
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore #4
    //   15: aload_0
    //   16: monitorexit
    //   17: aload #4
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   5	12	13	finally
    //   15	17	13	finally
  }
  
  public final class AssetInputStream extends InputStream {
    private long mAssetNativePtr;
    
    private long mLength;
    
    private long mMarkPos;
    
    private AssetInputStream(long param1Long) {
      this.mAssetNativePtr = param1Long;
      this.mLength = AssetManager.nativeAssetGetLength(param1Long);
    }
    
    private void ensureOpen() {
      if (this.mAssetNativePtr != 0L)
        return; 
      throw new IllegalStateException("AssetInputStream is closed");
    }
    
    public final int available() throws IOException {
      int i;
      ensureOpen();
      long l = AssetManager.nativeAssetGetRemainingLength(this.mAssetNativePtr);
      if (l > 2147483647L) {
        i = Integer.MAX_VALUE;
      } else {
        i = (int)l;
      } 
      return i;
    }
    
    public final void close() throws IOException {
      long l = this.mAssetNativePtr;
      if (l != 0L) {
        AssetManager.nativeAssetDestroy(l);
        this.mAssetNativePtr = 0L;
        synchronized (AssetManager.this) {
          AssetManager.this.decRefsLocked(hashCode());
        } 
      } 
    }
    
    protected void finalize() throws Throwable {
      close();
    }
    
    public final int getAssetInt() {
      throw new UnsupportedOperationException();
    }
    
    public final long getNativeAsset() {
      return this.mAssetNativePtr;
    }
    
    public final void mark(int param1Int) {
      ensureOpen();
      this.mMarkPos = AssetManager.nativeAssetSeek(this.mAssetNativePtr, 0L, 0);
    }
    
    public final boolean markSupported() {
      return true;
    }
    
    public final int read() throws IOException {
      ensureOpen();
      return AssetManager.nativeAssetReadChar(this.mAssetNativePtr);
    }
    
    public final int read(byte[] param1ArrayOfbyte) throws IOException {
      ensureOpen();
      Objects.requireNonNull(param1ArrayOfbyte, "b");
      return AssetManager.nativeAssetRead(this.mAssetNativePtr, param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public final int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      ensureOpen();
      Objects.requireNonNull(param1ArrayOfbyte, "b");
      return AssetManager.nativeAssetRead(this.mAssetNativePtr, param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void reset() throws IOException {
      ensureOpen();
      AssetManager.nativeAssetSeek(this.mAssetNativePtr, this.mMarkPos, -1);
    }
    
    public final long skip(long param1Long) throws IOException {
      ensureOpen();
      long l1 = AssetManager.nativeAssetSeek(this.mAssetNativePtr, 0L, 0);
      long l2 = this.mLength;
      long l3 = param1Long;
      if (l1 + param1Long > l2)
        l3 = l2 - l1; 
      if (l3 > 0L)
        AssetManager.nativeAssetSeek(this.mAssetNativePtr, l3, 0); 
      return l3;
    }
  }
  
  public static class Builder {
    private ArrayList<ResourcesLoader> mLoaders = new ArrayList<>();
    
    private ArrayList<ApkAssets> mUserApkAssets = new ArrayList<>();
    
    public Builder addApkAssets(ApkAssets param1ApkAssets) {
      this.mUserApkAssets.add(param1ApkAssets);
      return this;
    }
    
    public Builder addLoader(ResourcesLoader param1ResourcesLoader) {
      this.mLoaders.add(param1ResourcesLoader);
      return this;
    }
    
    public AssetManager build() {
      ResourcesLoader[] arrayOfResourcesLoader;
      ApkAssets[] arrayOfApkAssets1 = AssetManager.getSystem().getApkAssets();
      ArrayList<ApkAssets> arrayList = new ArrayList();
      ArraySet arraySet = new ArraySet();
      int i;
      for (i = this.mLoaders.size() - 1; i >= 0; i--) {
        List<ApkAssets> list = ((ResourcesLoader)this.mLoaders.get(i)).getApkAssets();
        for (int k = list.size() - 1; k >= 0; k--) {
          ApkAssets apkAssets = list.get(k);
          if (arraySet.add(apkAssets))
            arrayList.add(0, apkAssets); 
        } 
      } 
      ApkAssets[] arrayOfApkAssets2 = new ApkAssets[arrayOfApkAssets1.length + this.mUserApkAssets.size() + arrayList.size()];
      System.arraycopy(arrayOfApkAssets1, 0, arrayOfApkAssets2, 0, arrayOfApkAssets1.length);
      i = 0;
      int j = this.mUserApkAssets.size();
      while (i < j) {
        arrayOfApkAssets2[arrayOfApkAssets1.length + i] = this.mUserApkAssets.get(i);
        i++;
      } 
      i = 0;
      j = arrayList.size();
      while (i < j) {
        arrayOfApkAssets2[arrayOfApkAssets1.length + i + this.mUserApkAssets.size()] = arrayList.get(i);
        i++;
      } 
      arrayList = null;
      AssetManager assetManager = new AssetManager(false);
      AssetManager.access$102(assetManager, arrayOfApkAssets2);
      AssetManager.nativeSetApkAssets(assetManager.mObject, arrayOfApkAssets2, false);
      if (!this.mLoaders.isEmpty())
        arrayOfResourcesLoader = this.mLoaders.<ResourcesLoader>toArray(new ResourcesLoader[0]); 
      AssetManager.access$402(assetManager, arrayOfResourcesLoader);
      return assetManager;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */