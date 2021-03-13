package android.app;

import android.compat.Compatibility;
import android.content.SharedPreferences;
import android.os.FileUtils;
import android.os.Looper;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;
import android.system.StructTimespec;
import android.util.Log;
import com.android.internal.util.ExponentiallyBucketedHistogram;
import dalvik.system.BlockGuard;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;

final class SharedPreferencesImpl implements SharedPreferences {
  private static final long CALLBACK_ON_CLEAR_CHANGE = 119147584L;
  
  private static final Object CONTENT = new Object();
  
  private static final boolean DEBUG = false;
  
  private static final long MAX_FSYNC_DURATION_MILLIS = 256L;
  
  private static final String TAG = "SharedPreferencesImpl";
  
  private final File mBackupFile;
  
  private long mCurrentMemoryStateGeneration;
  
  private long mDiskStateGeneration;
  
  private int mDiskWritesInFlight = 0;
  
  private final File mFile;
  
  private final WeakHashMap<SharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners = new WeakHashMap<>();
  
  private boolean mLoaded = false;
  
  private final Object mLock = new Object();
  
  private Map<String, Object> mMap;
  
  private final int mMode;
  
  private int mNumSync = 0;
  
  private long mStatSize;
  
  private StructTimespec mStatTimestamp;
  
  private final ExponentiallyBucketedHistogram mSyncTimes = new ExponentiallyBucketedHistogram(16);
  
  private Throwable mThrowable;
  
  private final Object mWritingToDiskLock = new Object();
  
  SharedPreferencesImpl(File paramFile, int paramInt) {
    this.mFile = paramFile;
    this.mBackupFile = makeBackupFile(paramFile);
    this.mMode = paramInt;
    this.mLoaded = false;
    this.mMap = null;
    this.mThrowable = null;
    startLoadFromDisk();
  }
  
  private void awaitLoadedLocked() {
    if (!this.mLoaded)
      BlockGuard.getThreadPolicy().onReadFromDisk(); 
    while (!this.mLoaded) {
      try {
        this.mLock.wait();
      } catch (InterruptedException interruptedException) {}
    } 
    if (this.mThrowable == null)
      return; 
    throw new IllegalStateException(this.mThrowable);
  }
  
  private static FileOutputStream createFileOutputStream(File paramFile) {
    FileOutputStream fileOutputStream;
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2 = null;
    try {
      FileOutputStream fileOutputStream1 = new FileOutputStream();
      this(paramFile);
      fileOutputStream = fileOutputStream1;
    } catch (FileNotFoundException fileNotFoundException) {
      File file = fileOutputStream.getParentFile();
      if (!file.mkdir()) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Couldn't create directory for SharedPreferences file ");
        stringBuilder2.append(fileOutputStream);
        Log.e("SharedPreferencesImpl", stringBuilder2.toString());
        return null;
      } 
      FileUtils.setPermissions(file.getPath(), 505, -1, -1);
      try {
        FileOutputStream fileOutputStream1 = new FileOutputStream();
        this((File)fileOutputStream);
        fileOutputStream = fileOutputStream1;
      } catch (FileNotFoundException fileNotFoundException1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Couldn't create SharedPreferences file ");
        stringBuilder.append(fileOutputStream);
        Log.e("SharedPreferencesImpl", stringBuilder.toString(), fileNotFoundException1);
        stringBuilder1 = stringBuilder2;
      } 
    } 
    return (FileOutputStream)stringBuilder1;
  }
  
  private void enqueueDiskWrite(final MemoryCommitResult mcr, final Runnable postWriteRunnable) {
    final boolean isFromSyncCommit;
    boolean bool1 = false;
    if (postWriteRunnable == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    postWriteRunnable = new Runnable() {
        public void run() {
          synchronized (SharedPreferencesImpl.this.mWritingToDiskLock) {
            SharedPreferencesImpl.this.writeToFile(mcr, isFromSyncCommit);
            synchronized (SharedPreferencesImpl.this.mLock) {
              SharedPreferencesImpl.access$310(SharedPreferencesImpl.this);
              Runnable runnable = postWriteRunnable;
              if (runnable != null)
                runnable.run(); 
              return;
            } 
          } 
        }
      };
    if (bool2)
      synchronized (this.mLock) {
        boolean bool;
        if (this.mDiskWritesInFlight == 1) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool) {
          postWriteRunnable.run();
          return;
        } 
      }  
    if (!bool2)
      bool1 = true; 
    QueuedWork.queue(postWriteRunnable, bool1);
  }
  
  private boolean hasFileChangedUnexpectedly() {
    synchronized (this.mLock) {
      int i = this.mDiskWritesInFlight;
      boolean bool = false;
      if (i > 0)
        return false; 
      try {
        BlockGuard.getThreadPolicy().onReadFromDisk();
        null = Os.stat(this.mFile.getPath());
        synchronized (this.mLock) {
          if (!((StructStat)null).st_mtim.equals(this.mStatTimestamp) || this.mStatSize != ((StructStat)null).st_size)
            bool = true; 
          return bool;
        } 
      } catch (ErrnoException errnoException) {
        return true;
      } 
    } 
  }
  
  private void loadFromDisk() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLock : Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield mLoaded : Z
    //   11: ifeq -> 17
    //   14: aload_1
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: getfield mBackupFile : Ljava/io/File;
    //   21: invokevirtual exists : ()Z
    //   24: ifeq -> 47
    //   27: aload_0
    //   28: getfield mFile : Ljava/io/File;
    //   31: invokevirtual delete : ()Z
    //   34: pop
    //   35: aload_0
    //   36: getfield mBackupFile : Ljava/io/File;
    //   39: aload_0
    //   40: getfield mFile : Ljava/io/File;
    //   43: invokevirtual renameTo : (Ljava/io/File;)Z
    //   46: pop
    //   47: aload_1
    //   48: monitorexit
    //   49: aload_0
    //   50: getfield mFile : Ljava/io/File;
    //   53: invokevirtual exists : ()Z
    //   56: ifeq -> 112
    //   59: aload_0
    //   60: getfield mFile : Ljava/io/File;
    //   63: invokevirtual canRead : ()Z
    //   66: ifne -> 112
    //   69: new java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial <init> : ()V
    //   76: astore_1
    //   77: aload_1
    //   78: ldc_w 'Attempt to read preferences file '
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload_1
    //   86: aload_0
    //   87: getfield mFile : Ljava/io/File;
    //   90: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload_1
    //   95: ldc_w ' without permission'
    //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: ldc 'SharedPreferencesImpl'
    //   104: aload_1
    //   105: invokevirtual toString : ()Ljava/lang/String;
    //   108: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   111: pop
    //   112: aconst_null
    //   113: astore_2
    //   114: aconst_null
    //   115: astore_3
    //   116: aconst_null
    //   117: astore_1
    //   118: aconst_null
    //   119: astore #4
    //   121: aconst_null
    //   122: astore #5
    //   124: aconst_null
    //   125: astore #6
    //   127: aload_2
    //   128: astore #7
    //   130: aload_3
    //   131: astore #8
    //   133: aload_0
    //   134: getfield mFile : Ljava/io/File;
    //   137: invokevirtual getPath : ()Ljava/lang/String;
    //   140: invokestatic stat : (Ljava/lang/String;)Landroid/system/StructStat;
    //   143: astore #9
    //   145: aload_2
    //   146: astore #7
    //   148: aload #9
    //   150: astore #6
    //   152: aload_3
    //   153: astore #8
    //   155: aload #9
    //   157: astore #5
    //   159: aload_0
    //   160: getfield mFile : Ljava/io/File;
    //   163: invokevirtual canRead : ()Z
    //   166: istore #10
    //   168: aload #9
    //   170: astore #5
    //   172: iload #10
    //   174: ifeq -> 417
    //   177: aconst_null
    //   178: astore #6
    //   180: aconst_null
    //   181: astore_1
    //   182: aload_1
    //   183: astore #11
    //   185: aload #6
    //   187: astore #7
    //   189: new java/io/BufferedInputStream
    //   192: astore #8
    //   194: aload_1
    //   195: astore #11
    //   197: aload #6
    //   199: astore #7
    //   201: new java/io/FileInputStream
    //   204: astore #5
    //   206: aload_1
    //   207: astore #11
    //   209: aload #6
    //   211: astore #7
    //   213: aload #5
    //   215: aload_0
    //   216: getfield mFile : Ljava/io/File;
    //   219: invokespecial <init> : (Ljava/io/File;)V
    //   222: aload_1
    //   223: astore #11
    //   225: aload #6
    //   227: astore #7
    //   229: aload #8
    //   231: aload #5
    //   233: sipush #16384
    //   236: invokespecial <init> : (Ljava/io/InputStream;I)V
    //   239: aload #8
    //   241: astore_1
    //   242: aload_1
    //   243: astore #11
    //   245: aload_1
    //   246: astore #7
    //   248: aload_1
    //   249: invokestatic readMapXml : (Ljava/io/InputStream;)Ljava/util/HashMap;
    //   252: astore #8
    //   254: aload #8
    //   256: astore #7
    //   258: aload_1
    //   259: astore #11
    //   261: aload #7
    //   263: astore_1
    //   264: aload_1
    //   265: astore #7
    //   267: aload #9
    //   269: astore #6
    //   271: aload_1
    //   272: astore #8
    //   274: aload #9
    //   276: astore #5
    //   278: aload #11
    //   280: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   283: aload #9
    //   285: astore #5
    //   287: goto -> 417
    //   290: astore_1
    //   291: goto -> 366
    //   294: astore #8
    //   296: aload #7
    //   298: astore #11
    //   300: new java/lang/StringBuilder
    //   303: astore_1
    //   304: aload #7
    //   306: astore #11
    //   308: aload_1
    //   309: invokespecial <init> : ()V
    //   312: aload #7
    //   314: astore #11
    //   316: aload_1
    //   317: ldc_w 'Cannot read '
    //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: pop
    //   324: aload #7
    //   326: astore #11
    //   328: aload_1
    //   329: aload_0
    //   330: getfield mFile : Ljava/io/File;
    //   333: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   336: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: pop
    //   340: aload #7
    //   342: astore #11
    //   344: ldc 'SharedPreferencesImpl'
    //   346: aload_1
    //   347: invokevirtual toString : ()Ljava/lang/String;
    //   350: aload #8
    //   352: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   355: pop
    //   356: aload #4
    //   358: astore_1
    //   359: aload #7
    //   361: astore #11
    //   363: goto -> 264
    //   366: aload_2
    //   367: astore #7
    //   369: aload #9
    //   371: astore #6
    //   373: aload_3
    //   374: astore #8
    //   376: aload #9
    //   378: astore #5
    //   380: aload #11
    //   382: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   385: aload_2
    //   386: astore #7
    //   388: aload #9
    //   390: astore #6
    //   392: aload_3
    //   393: astore #8
    //   395: aload #9
    //   397: astore #5
    //   399: aload_1
    //   400: athrow
    //   401: astore_1
    //   402: aload #7
    //   404: astore #9
    //   406: aload #6
    //   408: astore #7
    //   410: goto -> 430
    //   413: astore_1
    //   414: aload #8
    //   416: astore_1
    //   417: aconst_null
    //   418: astore #11
    //   420: aload #5
    //   422: astore #7
    //   424: aload_1
    //   425: astore #9
    //   427: aload #11
    //   429: astore_1
    //   430: aload_0
    //   431: getfield mLock : Ljava/lang/Object;
    //   434: astore #11
    //   436: aload #11
    //   438: monitorenter
    //   439: aload_0
    //   440: iconst_1
    //   441: putfield mLoaded : Z
    //   444: aload_0
    //   445: aload_1
    //   446: putfield mThrowable : Ljava/lang/Throwable;
    //   449: aload_1
    //   450: ifnonnull -> 525
    //   453: aload #9
    //   455: ifnull -> 485
    //   458: aload_0
    //   459: aload #9
    //   461: putfield mMap : Ljava/util/Map;
    //   464: aload_0
    //   465: aload #7
    //   467: getfield st_mtim : Landroid/system/StructTimespec;
    //   470: putfield mStatTimestamp : Landroid/system/StructTimespec;
    //   473: aload_0
    //   474: aload #7
    //   476: getfield st_size : J
    //   479: putfield mStatSize : J
    //   482: goto -> 525
    //   485: new java/util/HashMap
    //   488: astore_1
    //   489: aload_1
    //   490: invokespecial <init> : ()V
    //   493: aload_0
    //   494: aload_1
    //   495: putfield mMap : Ljava/util/Map;
    //   498: goto -> 525
    //   501: astore_1
    //   502: aload_0
    //   503: aload_1
    //   504: putfield mThrowable : Ljava/lang/Throwable;
    //   507: aload_0
    //   508: getfield mLock : Ljava/lang/Object;
    //   511: astore_1
    //   512: goto -> 530
    //   515: astore_1
    //   516: aload_0
    //   517: getfield mLock : Ljava/lang/Object;
    //   520: invokevirtual notifyAll : ()V
    //   523: aload_1
    //   524: athrow
    //   525: aload_0
    //   526: getfield mLock : Ljava/lang/Object;
    //   529: astore_1
    //   530: aload_1
    //   531: invokevirtual notifyAll : ()V
    //   534: aload #11
    //   536: monitorexit
    //   537: return
    //   538: astore_1
    //   539: aload #11
    //   541: monitorexit
    //   542: aload_1
    //   543: athrow
    //   544: astore #7
    //   546: aload_1
    //   547: monitorexit
    //   548: aload #7
    //   550: athrow
    // Exception table:
    //   from	to	target	type
    //   7	16	544	finally
    //   17	47	544	finally
    //   47	49	544	finally
    //   133	145	413	android/system/ErrnoException
    //   133	145	401	finally
    //   159	168	413	android/system/ErrnoException
    //   159	168	401	finally
    //   189	194	294	java/lang/Exception
    //   189	194	290	finally
    //   201	206	294	java/lang/Exception
    //   201	206	290	finally
    //   213	222	294	java/lang/Exception
    //   213	222	290	finally
    //   229	239	294	java/lang/Exception
    //   229	239	290	finally
    //   248	254	294	java/lang/Exception
    //   248	254	290	finally
    //   278	283	413	android/system/ErrnoException
    //   278	283	401	finally
    //   300	304	290	finally
    //   308	312	290	finally
    //   316	324	290	finally
    //   328	340	290	finally
    //   344	356	290	finally
    //   380	385	413	android/system/ErrnoException
    //   380	385	401	finally
    //   399	401	413	android/system/ErrnoException
    //   399	401	401	finally
    //   439	449	538	finally
    //   458	482	501	finally
    //   485	498	501	finally
    //   502	507	515	finally
    //   507	512	538	finally
    //   516	523	538	finally
    //   523	525	538	finally
    //   525	530	538	finally
    //   530	534	538	finally
    //   534	537	538	finally
    //   539	542	538	finally
    //   546	548	544	finally
  }
  
  static File makeBackupFile(File paramFile) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramFile.getPath());
    stringBuilder.append(".bak");
    return new File(stringBuilder.toString());
  }
  
  private void startLoadFromDisk() {
    synchronized (this.mLock) {
      this.mLoaded = false;
      (new Thread("SharedPreferencesImpl-load") {
          public void run() {
            SharedPreferencesImpl.this.loadFromDisk();
          }
        }).start();
      return;
    } 
  }
  
  private void writeToFile(MemoryCommitResult paramMemoryCommitResult, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mFile : Ljava/io/File;
    //   4: invokevirtual exists : ()Z
    //   7: ifeq -> 196
    //   10: iconst_0
    //   11: istore_3
    //   12: iconst_0
    //   13: istore #4
    //   15: aload_0
    //   16: getfield mDiskStateGeneration : J
    //   19: aload_1
    //   20: getfield memoryStateGeneration : J
    //   23: lcmp
    //   24: ifge -> 81
    //   27: iload_2
    //   28: ifeq -> 37
    //   31: iconst_1
    //   32: istore #4
    //   34: goto -> 84
    //   37: aload_0
    //   38: getfield mLock : Ljava/lang/Object;
    //   41: astore #5
    //   43: aload #5
    //   45: monitorenter
    //   46: aload_0
    //   47: getfield mCurrentMemoryStateGeneration : J
    //   50: lstore #6
    //   52: lload #6
    //   54: aload_1
    //   55: getfield memoryStateGeneration : J
    //   58: lcmp
    //   59: ifne -> 65
    //   62: iconst_1
    //   63: istore #4
    //   65: aload #5
    //   67: monitorexit
    //   68: goto -> 84
    //   71: astore_1
    //   72: goto -> 76
    //   75: astore_1
    //   76: aload #5
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    //   81: iload_3
    //   82: istore #4
    //   84: iload #4
    //   86: ifne -> 96
    //   89: aload_1
    //   90: iconst_0
    //   91: iconst_1
    //   92: invokevirtual setDiskWriteResult : (ZZ)V
    //   95: return
    //   96: aload_0
    //   97: getfield mBackupFile : Ljava/io/File;
    //   100: invokevirtual exists : ()Z
    //   103: ifne -> 185
    //   106: aload_0
    //   107: getfield mFile : Ljava/io/File;
    //   110: aload_0
    //   111: getfield mBackupFile : Ljava/io/File;
    //   114: invokevirtual renameTo : (Ljava/io/File;)Z
    //   117: ifne -> 196
    //   120: new java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial <init> : ()V
    //   127: astore #5
    //   129: aload #5
    //   131: ldc_w 'Couldn't rename file '
    //   134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: aload #5
    //   140: aload_0
    //   141: getfield mFile : Ljava/io/File;
    //   144: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: aload #5
    //   150: ldc_w ' to backup file '
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: aload #5
    //   159: aload_0
    //   160: getfield mBackupFile : Ljava/io/File;
    //   163: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: ldc 'SharedPreferencesImpl'
    //   169: aload #5
    //   171: invokevirtual toString : ()Ljava/lang/String;
    //   174: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   177: pop
    //   178: aload_1
    //   179: iconst_0
    //   180: iconst_0
    //   181: invokevirtual setDiskWriteResult : (ZZ)V
    //   184: return
    //   185: aload_0
    //   186: getfield mFile : Ljava/io/File;
    //   189: invokevirtual delete : ()Z
    //   192: pop
    //   193: goto -> 196
    //   196: aload_0
    //   197: getfield mFile : Ljava/io/File;
    //   200: invokestatic createFileOutputStream : (Ljava/io/File;)Ljava/io/FileOutputStream;
    //   203: astore #5
    //   205: aload #5
    //   207: ifnonnull -> 217
    //   210: aload_1
    //   211: iconst_0
    //   212: iconst_0
    //   213: invokevirtual setDiskWriteResult : (ZZ)V
    //   216: return
    //   217: aload_1
    //   218: getfield mapToWriteToDisk : Ljava/util/Map;
    //   221: aload #5
    //   223: invokestatic writeMapXml : (Ljava/util/Map;Ljava/io/OutputStream;)V
    //   226: invokestatic currentTimeMillis : ()J
    //   229: lstore #6
    //   231: aload #5
    //   233: invokestatic sync : (Ljava/io/FileOutputStream;)Z
    //   236: pop
    //   237: invokestatic currentTimeMillis : ()J
    //   240: lstore #8
    //   242: aload #5
    //   244: invokevirtual close : ()V
    //   247: aload_0
    //   248: getfield mFile : Ljava/io/File;
    //   251: invokevirtual getPath : ()Ljava/lang/String;
    //   254: aload_0
    //   255: getfield mMode : I
    //   258: iconst_0
    //   259: invokestatic setFilePermissionsFromMode : (Ljava/lang/String;II)V
    //   262: aload_0
    //   263: getfield mFile : Ljava/io/File;
    //   266: invokevirtual getPath : ()Ljava/lang/String;
    //   269: invokestatic stat : (Ljava/lang/String;)Landroid/system/StructStat;
    //   272: astore #10
    //   274: aload_0
    //   275: getfield mLock : Ljava/lang/Object;
    //   278: astore #5
    //   280: aload #5
    //   282: monitorenter
    //   283: aload_0
    //   284: aload #10
    //   286: getfield st_mtim : Landroid/system/StructTimespec;
    //   289: putfield mStatTimestamp : Landroid/system/StructTimespec;
    //   292: aload_0
    //   293: aload #10
    //   295: getfield st_size : J
    //   298: putfield mStatSize : J
    //   301: aload #5
    //   303: monitorexit
    //   304: goto -> 317
    //   307: astore #10
    //   309: aload #5
    //   311: monitorexit
    //   312: aload #10
    //   314: athrow
    //   315: astore #5
    //   317: aload_0
    //   318: getfield mBackupFile : Ljava/io/File;
    //   321: invokevirtual delete : ()Z
    //   324: pop
    //   325: aload_0
    //   326: aload_1
    //   327: getfield memoryStateGeneration : J
    //   330: putfield mDiskStateGeneration : J
    //   333: aload_1
    //   334: iconst_1
    //   335: iconst_1
    //   336: invokevirtual setDiskWriteResult : (ZZ)V
    //   339: lload #8
    //   341: lload #6
    //   343: lsub
    //   344: lstore #6
    //   346: aload_0
    //   347: getfield mSyncTimes : Lcom/android/internal/util/ExponentiallyBucketedHistogram;
    //   350: lload #6
    //   352: l2i
    //   353: invokevirtual add : (I)V
    //   356: aload_0
    //   357: getfield mNumSync : I
    //   360: iconst_1
    //   361: iadd
    //   362: istore #4
    //   364: aload_0
    //   365: iload #4
    //   367: putfield mNumSync : I
    //   370: iload #4
    //   372: sipush #1024
    //   375: irem
    //   376: ifeq -> 388
    //   379: lload #6
    //   381: ldc2_w 256
    //   384: lcmp
    //   385: ifle -> 444
    //   388: aload_0
    //   389: getfield mSyncTimes : Lcom/android/internal/util/ExponentiallyBucketedHistogram;
    //   392: astore #5
    //   394: new java/lang/StringBuilder
    //   397: astore #10
    //   399: aload #10
    //   401: invokespecial <init> : ()V
    //   404: aload #10
    //   406: ldc_w 'Time required to fsync '
    //   409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: pop
    //   413: aload #10
    //   415: aload_0
    //   416: getfield mFile : Ljava/io/File;
    //   419: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   422: pop
    //   423: aload #10
    //   425: ldc_w ': '
    //   428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   431: pop
    //   432: aload #5
    //   434: ldc 'SharedPreferencesImpl'
    //   436: aload #10
    //   438: invokevirtual toString : ()Ljava/lang/String;
    //   441: invokevirtual log : (Ljava/lang/String;Ljava/lang/CharSequence;)V
    //   444: return
    //   445: astore #5
    //   447: ldc 'SharedPreferencesImpl'
    //   449: ldc_w 'writeToFile: Got exception:'
    //   452: aload #5
    //   454: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   457: pop
    //   458: goto -> 474
    //   461: astore #5
    //   463: ldc 'SharedPreferencesImpl'
    //   465: ldc_w 'writeToFile: Got exception:'
    //   468: aload #5
    //   470: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   473: pop
    //   474: aload_0
    //   475: getfield mFile : Ljava/io/File;
    //   478: invokevirtual exists : ()Z
    //   481: ifeq -> 533
    //   484: aload_0
    //   485: getfield mFile : Ljava/io/File;
    //   488: invokevirtual delete : ()Z
    //   491: ifne -> 533
    //   494: new java/lang/StringBuilder
    //   497: dup
    //   498: invokespecial <init> : ()V
    //   501: astore #5
    //   503: aload #5
    //   505: ldc_w 'Couldn't clean up partially-written file '
    //   508: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: pop
    //   512: aload #5
    //   514: aload_0
    //   515: getfield mFile : Ljava/io/File;
    //   518: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   521: pop
    //   522: ldc 'SharedPreferencesImpl'
    //   524: aload #5
    //   526: invokevirtual toString : ()Ljava/lang/String;
    //   529: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   532: pop
    //   533: aload_1
    //   534: iconst_0
    //   535: iconst_0
    //   536: invokevirtual setDiskWriteResult : (ZZ)V
    //   539: return
    // Exception table:
    //   from	to	target	type
    //   46	52	75	finally
    //   52	62	71	finally
    //   65	68	71	finally
    //   76	79	71	finally
    //   196	205	461	org/xmlpull/v1/XmlPullParserException
    //   196	205	445	java/io/IOException
    //   210	216	461	org/xmlpull/v1/XmlPullParserException
    //   210	216	445	java/io/IOException
    //   217	262	461	org/xmlpull/v1/XmlPullParserException
    //   217	262	445	java/io/IOException
    //   262	283	315	android/system/ErrnoException
    //   262	283	461	org/xmlpull/v1/XmlPullParserException
    //   262	283	445	java/io/IOException
    //   283	304	307	finally
    //   309	312	307	finally
    //   312	315	315	android/system/ErrnoException
    //   312	315	461	org/xmlpull/v1/XmlPullParserException
    //   312	315	445	java/io/IOException
    //   317	339	461	org/xmlpull/v1/XmlPullParserException
    //   317	339	445	java/io/IOException
    //   346	370	461	org/xmlpull/v1/XmlPullParserException
    //   346	370	445	java/io/IOException
    //   388	444	461	org/xmlpull/v1/XmlPullParserException
    //   388	444	445	java/io/IOException
  }
  
  public boolean contains(String paramString) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      return this.mMap.containsKey(paramString);
    } 
  }
  
  public SharedPreferences.Editor edit() {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      return new EditorImpl();
    } 
  }
  
  public Map<String, ?> getAll() {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      HashMap<Object, Object> hashMap = new HashMap<>();
      this((Map)this.mMap);
      return (Map)hashMap;
    } 
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      Boolean bool = (Boolean)this.mMap.get(paramString);
      if (bool != null)
        paramBoolean = bool.booleanValue(); 
      return paramBoolean;
    } 
  }
  
  public float getFloat(String paramString, float paramFloat) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      Float float_ = (Float)this.mMap.get(paramString);
      if (float_ != null)
        paramFloat = float_.floatValue(); 
      return paramFloat;
    } 
  }
  
  public int getInt(String paramString, int paramInt) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      Integer integer = (Integer)this.mMap.get(paramString);
      if (integer != null)
        paramInt = integer.intValue(); 
      return paramInt;
    } 
  }
  
  public long getLong(String paramString, long paramLong) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      Long long_ = (Long)this.mMap.get(paramString);
      if (long_ != null)
        paramLong = long_.longValue(); 
      return paramLong;
    } 
  }
  
  public String getString(String paramString1, String paramString2) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      paramString1 = (String)this.mMap.get(paramString1);
      if (paramString1 == null)
        paramString1 = paramString2; 
      return paramString1;
    } 
  }
  
  public Set<String> getStringSet(String paramString, Set<String> paramSet) {
    synchronized (this.mLock) {
      awaitLoadedLocked();
      Set<String> set = (Set)this.mMap.get(paramString);
      if (set == null)
        set = paramSet; 
      return set;
    } 
  }
  
  public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener) {
    synchronized (this.mLock) {
      this.mListeners.put(paramOnSharedPreferenceChangeListener, CONTENT);
      return;
    } 
  }
  
  void startReloadIfChangedUnexpectedly() {
    synchronized (this.mLock) {
      if (!hasFileChangedUnexpectedly())
        return; 
      startLoadFromDisk();
      return;
    } 
  }
  
  public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener) {
    synchronized (this.mLock) {
      this.mListeners.remove(paramOnSharedPreferenceChangeListener);
      return;
    } 
  }
  
  public final class EditorImpl implements SharedPreferences.Editor {
    private boolean mClear = false;
    
    private final Object mEditorLock = new Object();
    
    private final Map<String, Object> mModified = new HashMap<>();
    
    private SharedPreferencesImpl.MemoryCommitResult commitToMemory() {
      // Byte code:
      //   0: iconst_0
      //   1: istore_1
      //   2: aconst_null
      //   3: astore_2
      //   4: aconst_null
      //   5: astore_3
      //   6: aload_0
      //   7: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   10: invokestatic access$200 : (Landroid/app/SharedPreferencesImpl;)Ljava/lang/Object;
      //   13: astore #4
      //   15: aload #4
      //   17: monitorenter
      //   18: aload_0
      //   19: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   22: invokestatic access$300 : (Landroid/app/SharedPreferencesImpl;)I
      //   25: ifle -> 59
      //   28: aload_0
      //   29: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   32: astore #5
      //   34: new java/util/HashMap
      //   37: astore #6
      //   39: aload #6
      //   41: aload_0
      //   42: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   45: invokestatic access$400 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/Map;
      //   48: invokespecial <init> : (Ljava/util/Map;)V
      //   51: aload #5
      //   53: aload #6
      //   55: invokestatic access$402 : (Landroid/app/SharedPreferencesImpl;Ljava/util/Map;)Ljava/util/Map;
      //   58: pop
      //   59: aload_0
      //   60: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   63: invokestatic access$400 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/Map;
      //   66: astore #5
      //   68: aload_0
      //   69: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   72: invokestatic access$308 : (Landroid/app/SharedPreferencesImpl;)I
      //   75: pop
      //   76: aload_0
      //   77: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   80: invokestatic access$500 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/WeakHashMap;
      //   83: invokevirtual size : ()I
      //   86: ifle -> 95
      //   89: iconst_1
      //   90: istore #7
      //   92: goto -> 98
      //   95: iconst_0
      //   96: istore #7
      //   98: iload #7
      //   100: ifeq -> 129
      //   103: new java/util/ArrayList
      //   106: astore_2
      //   107: aload_2
      //   108: invokespecial <init> : ()V
      //   111: new java/util/HashSet
      //   114: astore_3
      //   115: aload_3
      //   116: aload_0
      //   117: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   120: invokestatic access$500 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/WeakHashMap;
      //   123: invokevirtual keySet : ()Ljava/util/Set;
      //   126: invokespecial <init> : (Ljava/util/Collection;)V
      //   129: aload_0
      //   130: getfield mEditorLock : Ljava/lang/Object;
      //   133: astore #6
      //   135: aload #6
      //   137: monitorenter
      //   138: iconst_0
      //   139: istore #8
      //   141: iconst_0
      //   142: istore #9
      //   144: aload_0
      //   145: getfield mClear : Z
      //   148: ifeq -> 182
      //   151: iload #9
      //   153: istore #8
      //   155: aload #5
      //   157: invokeinterface isEmpty : ()Z
      //   162: ifne -> 175
      //   165: iconst_1
      //   166: istore #8
      //   168: aload #5
      //   170: invokeinterface clear : ()V
      //   175: iconst_1
      //   176: istore_1
      //   177: aload_0
      //   178: iconst_0
      //   179: putfield mClear : Z
      //   182: aload_0
      //   183: getfield mModified : Ljava/util/Map;
      //   186: invokeinterface entrySet : ()Ljava/util/Set;
      //   191: invokeinterface iterator : ()Ljava/util/Iterator;
      //   196: astore #10
      //   198: aload #10
      //   200: invokeinterface hasNext : ()Z
      //   205: ifeq -> 356
      //   208: aload #10
      //   210: invokeinterface next : ()Ljava/lang/Object;
      //   215: checkcast java/util/Map$Entry
      //   218: astore #11
      //   220: aload #11
      //   222: invokeinterface getKey : ()Ljava/lang/Object;
      //   227: checkcast java/lang/String
      //   230: astore #12
      //   232: aload #11
      //   234: invokeinterface getValue : ()Ljava/lang/Object;
      //   239: astore #13
      //   241: aload #13
      //   243: aload_0
      //   244: if_acmpeq -> 311
      //   247: aload #13
      //   249: ifnonnull -> 255
      //   252: goto -> 311
      //   255: aload #5
      //   257: aload #12
      //   259: invokeinterface containsKey : (Ljava/lang/Object;)Z
      //   264: ifeq -> 296
      //   267: aload #5
      //   269: aload #12
      //   271: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   276: astore #11
      //   278: aload #11
      //   280: ifnull -> 296
      //   283: aload #11
      //   285: aload #13
      //   287: invokevirtual equals : (Ljava/lang/Object;)Z
      //   290: ifeq -> 296
      //   293: goto -> 198
      //   296: aload #5
      //   298: aload #12
      //   300: aload #13
      //   302: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   307: pop
      //   308: goto -> 336
      //   311: aload #5
      //   313: aload #12
      //   315: invokeinterface containsKey : (Ljava/lang/Object;)Z
      //   320: ifne -> 326
      //   323: goto -> 198
      //   326: aload #5
      //   328: aload #12
      //   330: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
      //   335: pop
      //   336: iconst_1
      //   337: istore #8
      //   339: iload #7
      //   341: ifeq -> 353
      //   344: aload_2
      //   345: aload #12
      //   347: invokeinterface add : (Ljava/lang/Object;)Z
      //   352: pop
      //   353: goto -> 198
      //   356: aload_0
      //   357: getfield mModified : Ljava/util/Map;
      //   360: invokeinterface clear : ()V
      //   365: iload #8
      //   367: ifeq -> 378
      //   370: aload_0
      //   371: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   374: invokestatic access$608 : (Landroid/app/SharedPreferencesImpl;)J
      //   377: pop2
      //   378: aload_0
      //   379: getfield this$0 : Landroid/app/SharedPreferencesImpl;
      //   382: invokestatic access$600 : (Landroid/app/SharedPreferencesImpl;)J
      //   385: lstore #14
      //   387: aload #6
      //   389: monitorexit
      //   390: aload #4
      //   392: monitorexit
      //   393: new android/app/SharedPreferencesImpl$MemoryCommitResult
      //   396: dup
      //   397: lload #14
      //   399: iload_1
      //   400: aload_2
      //   401: aload_3
      //   402: aload #5
      //   404: aconst_null
      //   405: invokespecial <init> : (JZLjava/util/List;Ljava/util/Set;Ljava/util/Map;Landroid/app/SharedPreferencesImpl$1;)V
      //   408: areturn
      //   409: astore_2
      //   410: aload #6
      //   412: monitorexit
      //   413: aload_2
      //   414: athrow
      //   415: astore_2
      //   416: aload #4
      //   418: monitorexit
      //   419: aload_2
      //   420: athrow
      // Exception table:
      //   from	to	target	type
      //   18	59	415	finally
      //   59	89	415	finally
      //   103	111	415	finally
      //   111	129	415	finally
      //   129	138	415	finally
      //   144	151	409	finally
      //   155	165	409	finally
      //   168	175	409	finally
      //   177	182	409	finally
      //   182	198	409	finally
      //   198	241	409	finally
      //   255	278	409	finally
      //   283	293	409	finally
      //   296	308	409	finally
      //   311	323	409	finally
      //   326	336	409	finally
      //   344	353	409	finally
      //   356	365	409	finally
      //   370	378	409	finally
      //   378	390	409	finally
      //   390	393	415	finally
      //   410	413	409	finally
      //   413	415	415	finally
      //   416	419	415	finally
    }
    
    private void notifyListeners(SharedPreferencesImpl.MemoryCommitResult param1MemoryCommitResult) {
      if (param1MemoryCommitResult.listeners == null || (param1MemoryCommitResult.keysModified == null && !param1MemoryCommitResult.keysCleared))
        return; 
      if (Looper.myLooper() == Looper.getMainLooper()) {
        if (param1MemoryCommitResult.keysCleared && Compatibility.isChangeEnabled(119147584L))
          for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : param1MemoryCommitResult.listeners) {
            if (onSharedPreferenceChangeListener != null)
              onSharedPreferenceChangeListener.onSharedPreferenceChanged(SharedPreferencesImpl.this, null); 
          }  
        for (int i = param1MemoryCommitResult.keysModified.size() - 1; i >= 0; i--) {
          String str = param1MemoryCommitResult.keysModified.get(i);
          for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : param1MemoryCommitResult.listeners) {
            if (onSharedPreferenceChangeListener != null)
              onSharedPreferenceChangeListener.onSharedPreferenceChanged(SharedPreferencesImpl.this, str); 
          } 
        } 
      } else {
        ActivityThread.sMainThreadHandler.post(new _$$Lambda$SharedPreferencesImpl$EditorImpl$3CAjkhzA131V3V_sLfP2uy0FWZ0(this, param1MemoryCommitResult));
      } 
    }
    
    public void apply() {
      final long startTime = System.currentTimeMillis();
      final SharedPreferencesImpl.MemoryCommitResult mcr = commitToMemory();
      final Runnable awaitCommit = new Runnable() {
          public void run() {
            try {
              mcr.writtenToDiskLatch.await();
            } catch (InterruptedException interruptedException) {}
          }
        };
      QueuedWork.addFinisher(runnable);
      runnable = new Runnable() {
          public void run() {
            awaitCommit.run();
            QueuedWork.removeFinisher(awaitCommit);
          }
        };
      SharedPreferencesImpl.this.enqueueDiskWrite(memoryCommitResult, runnable);
      notifyListeners(memoryCommitResult);
    }
    
    public SharedPreferences.Editor clear() {
      synchronized (this.mEditorLock) {
        this.mClear = true;
        return this;
      } 
    }
    
    public boolean commit() {
      null = commitToMemory();
      SharedPreferencesImpl.this.enqueueDiskWrite(null, null);
      try {
        null.writtenToDiskLatch.await();
        notifyListeners(null);
        return null.writeToDiskResult;
      } catch (InterruptedException interruptedException) {
        return false;
      } finally {}
    }
    
    public SharedPreferences.Editor putBoolean(String param1String, boolean param1Boolean) {
      synchronized (this.mEditorLock) {
        this.mModified.put(param1String, Boolean.valueOf(param1Boolean));
        return this;
      } 
    }
    
    public SharedPreferences.Editor putFloat(String param1String, float param1Float) {
      synchronized (this.mEditorLock) {
        this.mModified.put(param1String, Float.valueOf(param1Float));
        return this;
      } 
    }
    
    public SharedPreferences.Editor putInt(String param1String, int param1Int) {
      synchronized (this.mEditorLock) {
        this.mModified.put(param1String, Integer.valueOf(param1Int));
        return this;
      } 
    }
    
    public SharedPreferences.Editor putLong(String param1String, long param1Long) {
      synchronized (this.mEditorLock) {
        this.mModified.put(param1String, Long.valueOf(param1Long));
        return this;
      } 
    }
    
    public SharedPreferences.Editor putString(String param1String1, String param1String2) {
      synchronized (this.mEditorLock) {
        this.mModified.put(param1String1, param1String2);
        return this;
      } 
    }
    
    public SharedPreferences.Editor putStringSet(String param1String, Set<String> param1Set) {
      synchronized (this.mEditorLock) {
        Map<String, Object> map = this.mModified;
        if (param1Set == null) {
          param1Set = null;
        } else {
          param1Set = new HashSet<>(param1Set);
        } 
        map.put(param1String, param1Set);
        return this;
      } 
    }
    
    public SharedPreferences.Editor remove(String param1String) {
      synchronized (this.mEditorLock) {
        this.mModified.put(param1String, this);
        return this;
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      try {
        mcr.writtenToDiskLatch.await();
      } catch (InterruptedException interruptedException) {}
    }
  }
  
  class null implements Runnable {
    public void run() {
      awaitCommit.run();
      QueuedWork.removeFinisher(awaitCommit);
    }
  }
  
  private static class MemoryCommitResult {
    final boolean keysCleared;
    
    final List<String> keysModified;
    
    final Set<SharedPreferences.OnSharedPreferenceChangeListener> listeners;
    
    final Map<String, Object> mapToWriteToDisk;
    
    final long memoryStateGeneration;
    
    boolean wasWritten = false;
    
    volatile boolean writeToDiskResult = false;
    
    final CountDownLatch writtenToDiskLatch = new CountDownLatch(1);
    
    private MemoryCommitResult(long param1Long, boolean param1Boolean, List<String> param1List, Set<SharedPreferences.OnSharedPreferenceChangeListener> param1Set, Map<String, Object> param1Map) {
      this.memoryStateGeneration = param1Long;
      this.keysCleared = param1Boolean;
      this.keysModified = param1List;
      this.listeners = param1Set;
      this.mapToWriteToDisk = param1Map;
    }
    
    void setDiskWriteResult(boolean param1Boolean1, boolean param1Boolean2) {
      this.wasWritten = param1Boolean1;
      this.writeToDiskResult = param1Boolean2;
      this.writtenToDiskLatch.countDown();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedPreferencesImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */