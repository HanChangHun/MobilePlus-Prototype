package android.filterfw.core;

import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.util.HashSet;

public class FilterFactory {
  private static final String TAG = "FilterFactory";
  
  private static Object mClassLoaderGuard;
  
  private static ClassLoader mCurrentClassLoader = Thread.currentThread().getContextClassLoader();
  
  private static HashSet<String> mLibraries = new HashSet<>();
  
  private static boolean mLogVerbose;
  
  private static FilterFactory mSharedFactory;
  
  private HashSet<String> mPackages = new HashSet<>();
  
  static {
    mClassLoaderGuard = new Object();
    mLogVerbose = Log.isLoggable("FilterFactory", 2);
  }
  
  public static void addFilterLibrary(String paramString) {
    if (mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Adding filter library ");
      stringBuilder.append(paramString);
      Log.v("FilterFactory", stringBuilder.toString());
    } 
    synchronized (mClassLoaderGuard) {
      if (mLibraries.contains(paramString)) {
        if (mLogVerbose)
          Log.v("FilterFactory", "Library already added"); 
        return;
      } 
      mLibraries.add(paramString);
      PathClassLoader pathClassLoader = new PathClassLoader();
      this(paramString, mCurrentClassLoader);
      mCurrentClassLoader = (ClassLoader)pathClassLoader;
      return;
    } 
  }
  
  public static FilterFactory sharedFactory() {
    if (mSharedFactory == null)
      mSharedFactory = new FilterFactory(); 
    return mSharedFactory;
  }
  
  public void addPackage(String paramString) {
    if (mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Adding package ");
      stringBuilder.append(paramString);
      Log.v("FilterFactory", stringBuilder.toString());
    } 
    this.mPackages.add(paramString);
  }
  
  public Filter createFilterByClass(Class paramClass, String paramString) {
    StringBuilder stringBuilder;
    try {
      paramClass.asSubclass(Filter.class);
      try {
        Filter filter;
        Constructor<Filter> constructor = paramClass.getConstructor(new Class[] { String.class });
        paramClass = null;
        try {
          Filter filter1 = constructor.newInstance(new Object[] { paramString });
          filter = filter1;
        } finally {}
        if (filter != null)
          return filter; 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Could not construct the filter '");
        stringBuilder.append(paramString);
        stringBuilder.append("'!");
        throw new IllegalArgumentException(stringBuilder.toString());
      } catch (NoSuchMethodException noSuchMethodException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("The filter class '");
        stringBuilder1.append(stringBuilder);
        stringBuilder1.append("' does not have a constructor of the form <init>(String name)!");
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
    } catch (ClassCastException classCastException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Attempting to allocate class '");
      stringBuilder1.append(stringBuilder);
      stringBuilder1.append("' which is not a subclass of Filter!");
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
  }
  
  public Filter createFilterByClassName(String paramString1, String paramString2) {
    // Byte code:
    //   0: getstatic android/filterfw/core/FilterFactory.mLogVerbose : Z
    //   3: ifeq -> 37
    //   6: new java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial <init> : ()V
    //   13: astore_3
    //   14: aload_3
    //   15: ldc 'Looking up class '
    //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: pop
    //   21: aload_3
    //   22: aload_1
    //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: pop
    //   27: ldc 'FilterFactory'
    //   29: aload_3
    //   30: invokevirtual toString : ()Ljava/lang/String;
    //   33: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: aconst_null
    //   38: astore_3
    //   39: aload_0
    //   40: getfield mPackages : Ljava/util/HashSet;
    //   43: invokevirtual iterator : ()Ljava/util/Iterator;
    //   46: astore #4
    //   48: aload_3
    //   49: astore #5
    //   51: aload #4
    //   53: invokeinterface hasNext : ()Z
    //   58: ifeq -> 271
    //   61: aload #4
    //   63: invokeinterface next : ()Ljava/lang/Object;
    //   68: checkcast java/lang/String
    //   71: astore #6
    //   73: aload_3
    //   74: astore #5
    //   76: getstatic android/filterfw/core/FilterFactory.mLogVerbose : Z
    //   79: ifeq -> 155
    //   82: aload_3
    //   83: astore #5
    //   85: new java/lang/StringBuilder
    //   88: astore #7
    //   90: aload_3
    //   91: astore #5
    //   93: aload #7
    //   95: invokespecial <init> : ()V
    //   98: aload_3
    //   99: astore #5
    //   101: aload #7
    //   103: ldc 'Trying '
    //   105: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: pop
    //   109: aload_3
    //   110: astore #5
    //   112: aload #7
    //   114: aload #6
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload_3
    //   121: astore #5
    //   123: aload #7
    //   125: ldc '.'
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload_3
    //   132: astore #5
    //   134: aload #7
    //   136: aload_1
    //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload_3
    //   142: astore #5
    //   144: ldc 'FilterFactory'
    //   146: aload #7
    //   148: invokevirtual toString : ()Ljava/lang/String;
    //   151: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   154: pop
    //   155: aload_3
    //   156: astore #5
    //   158: getstatic android/filterfw/core/FilterFactory.mClassLoaderGuard : Ljava/lang/Object;
    //   161: astore #7
    //   163: aload_3
    //   164: astore #5
    //   166: aload #7
    //   168: monitorenter
    //   169: aload_3
    //   170: astore #5
    //   172: getstatic android/filterfw/core/FilterFactory.mCurrentClassLoader : Ljava/lang/ClassLoader;
    //   175: astore #8
    //   177: aload_3
    //   178: astore #5
    //   180: new java/lang/StringBuilder
    //   183: astore #9
    //   185: aload_3
    //   186: astore #5
    //   188: aload #9
    //   190: invokespecial <init> : ()V
    //   193: aload_3
    //   194: astore #5
    //   196: aload #9
    //   198: aload #6
    //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: pop
    //   204: aload_3
    //   205: astore #5
    //   207: aload #9
    //   209: ldc '.'
    //   211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: pop
    //   215: aload_3
    //   216: astore #5
    //   218: aload #9
    //   220: aload_1
    //   221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload_3
    //   226: astore #5
    //   228: aload #8
    //   230: aload #9
    //   232: invokevirtual toString : ()Ljava/lang/String;
    //   235: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   238: astore_3
    //   239: aload_3
    //   240: astore #5
    //   242: aload #7
    //   244: monitorexit
    //   245: aload_3
    //   246: ifnull -> 255
    //   249: aload_3
    //   250: astore #5
    //   252: goto -> 271
    //   255: goto -> 48
    //   258: astore_3
    //   259: aload #7
    //   261: monitorexit
    //   262: aload_3
    //   263: athrow
    //   264: astore_3
    //   265: aload #5
    //   267: astore_3
    //   268: goto -> 48
    //   271: aload #5
    //   273: ifnull -> 284
    //   276: aload_0
    //   277: aload #5
    //   279: aload_2
    //   280: invokevirtual createFilterByClass : (Ljava/lang/Class;Ljava/lang/String;)Landroid/filterfw/core/Filter;
    //   283: areturn
    //   284: new java/lang/StringBuilder
    //   287: dup
    //   288: invokespecial <init> : ()V
    //   291: astore_2
    //   292: aload_2
    //   293: ldc 'Unknown filter class ''
    //   295: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: pop
    //   299: aload_2
    //   300: aload_1
    //   301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: aload_2
    //   306: ldc ''!'
    //   308: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: pop
    //   312: new java/lang/IllegalArgumentException
    //   315: dup
    //   316: aload_2
    //   317: invokevirtual toString : ()Ljava/lang/String;
    //   320: invokespecial <init> : (Ljava/lang/String;)V
    //   323: athrow
    // Exception table:
    //   from	to	target	type
    //   76	82	264	java/lang/ClassNotFoundException
    //   85	90	264	java/lang/ClassNotFoundException
    //   93	98	264	java/lang/ClassNotFoundException
    //   101	109	264	java/lang/ClassNotFoundException
    //   112	120	264	java/lang/ClassNotFoundException
    //   123	131	264	java/lang/ClassNotFoundException
    //   134	141	264	java/lang/ClassNotFoundException
    //   144	155	264	java/lang/ClassNotFoundException
    //   158	163	264	java/lang/ClassNotFoundException
    //   166	169	264	java/lang/ClassNotFoundException
    //   172	177	258	finally
    //   180	185	258	finally
    //   188	193	258	finally
    //   196	204	258	finally
    //   207	215	258	finally
    //   218	225	258	finally
    //   228	239	258	finally
    //   242	245	258	finally
    //   259	262	258	finally
    //   262	264	264	java/lang/ClassNotFoundException
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FilterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */