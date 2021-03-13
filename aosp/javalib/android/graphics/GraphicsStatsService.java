package android.graphics;

import android.annotation.SystemApi;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.os.Trace;
import android.os.UserHandle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.IGraphicsStats;
import android.view.IGraphicsStatsCallback;
import com.android.internal.util.DumpUtils;
import com.android.internal.util.FastPrintWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TimeZone;

public class GraphicsStatsService extends IGraphicsStats.Stub {
  private static final int AID_STATSD = 1066;
  
  private static final int DELETE_OLD = 2;
  
  public static final String GRAPHICS_STATS_SERVICE = "graphicsstats";
  
  private static final int SAVE_BUFFER = 1;
  
  private static final String TAG = "GraphicsStatsService";
  
  private ArrayList<ActiveBuffer> mActive;
  
  private final AlarmManager mAlarmManager;
  
  private final AppOpsManager mAppOps;
  
  private final int mAshmemSize;
  
  private final Context mContext;
  
  private final Object mFileAccessLock;
  
  private File mGraphicsStatsDir;
  
  private final Object mLock;
  
  private boolean mRotateIsScheduled;
  
  private Handler mWriteOutHandler;
  
  private final byte[] mZeroData;
  
  @SystemApi
  public GraphicsStatsService(Context paramContext) {
    int i = nGetAshmemSize();
    this.mAshmemSize = i;
    this.mZeroData = new byte[i];
    this.mLock = new Object();
    this.mActive = new ArrayList<>();
    this.mFileAccessLock = new Object();
    this.mRotateIsScheduled = false;
    this.mContext = paramContext;
    this.mAppOps = (AppOpsManager)paramContext.getSystemService(AppOpsManager.class);
    this.mAlarmManager = (AlarmManager)paramContext.getSystemService(AlarmManager.class);
    File file = new File(new File(Environment.getDataDirectory(), "system"), "graphicsstats");
    this.mGraphicsStatsDir = file;
    file.mkdirs();
    if (this.mGraphicsStatsDir.exists()) {
      HandlerThread handlerThread = new HandlerThread("GraphicsStats-disk", 10);
      handlerThread.start();
      this.mWriteOutHandler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            public boolean handleMessage(Message param1Message) {
              int i = param1Message.what;
              if (i != 1) {
                if (i == 2)
                  GraphicsStatsService.this.deleteOldBuffers(); 
              } else {
                GraphicsStatsService.this.saveBuffer((GraphicsStatsService.HistoricalBuffer)param1Message.obj);
              } 
              return true;
            }
          });
      nativeInit();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Graphics stats directory does not exist: ");
    stringBuilder.append(this.mGraphicsStatsDir.getAbsolutePath());
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void addToSaveQueue(ActiveBuffer paramActiveBuffer) {
    try {
      HistoricalBuffer historicalBuffer = new HistoricalBuffer();
      this(this, paramActiveBuffer);
      Message.obtain(this.mWriteOutHandler, 1, historicalBuffer).sendToTarget();
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to copy graphicsstats from ");
      stringBuilder.append(paramActiveBuffer.mInfo.mPackageName);
      Log.w("GraphicsStatsService", stringBuilder.toString(), iOException);
    } 
    paramActiveBuffer.closeAllBuffers();
  }
  
  private void deleteOldBuffers() {
    Trace.traceBegin(524288L, "deleting old graphicsstats buffers");
    synchronized (this.mFileAccessLock) {
      File[] arrayOfFile = this.mGraphicsStatsDir.listFiles();
      if (arrayOfFile == null || arrayOfFile.length <= 3)
        return; 
      long[] arrayOfLong = new long[arrayOfFile.length];
      byte b = 0;
      while (true) {
        int i = arrayOfFile.length;
        if (b < i) {
          try {
            arrayOfLong[b] = Long.parseLong(arrayOfFile[b].getName());
          } catch (NumberFormatException numberFormatException) {}
          b++;
          continue;
        } 
        if (arrayOfLong.length <= 3)
          return; 
        Arrays.sort(arrayOfLong);
        for (b = 0; b < arrayOfLong.length - 3; b++) {
          File file = new File();
          this(this.mGraphicsStatsDir, Long.toString(arrayOfLong[b]));
          deleteRecursiveLocked(file);
        } 
        Trace.traceEnd(524288L);
        return;
      } 
    } 
  }
  
  private void deleteRecursiveLocked(File paramFile) {
    if (paramFile.isDirectory()) {
      File[] arrayOfFile = paramFile.listFiles();
      int i = arrayOfFile.length;
      for (byte b = 0; b < i; b++)
        deleteRecursiveLocked(arrayOfFile[b]); 
    } 
    if (!paramFile.delete()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to delete '");
      stringBuilder.append(paramFile.getAbsolutePath());
      stringBuilder.append("'!");
      Log.w("GraphicsStatsService", stringBuilder.toString());
    } 
  }
  
  private HashSet<File> dumpActiveLocked(long paramLong, ArrayList<HistoricalBuffer> paramArrayList) {
    HashSet<File> hashSet = new HashSet(paramArrayList.size());
    for (byte b = 0; b < paramArrayList.size(); b++) {
      HistoricalBuffer historicalBuffer = paramArrayList.get(b);
      File file = pathForApp(historicalBuffer.mInfo);
      hashSet.add(file);
      nAddToDump(paramLong, file.getAbsolutePath(), historicalBuffer.mInfo.mPackageName, historicalBuffer.mInfo.mVersionCode, historicalBuffer.mInfo.mStartTime, historicalBuffer.mInfo.mEndTime, historicalBuffer.mData);
    } 
    return hashSet;
  }
  
  private void dumpHistoricalLocked(long paramLong, HashSet<File> paramHashSet) {
    File[] arrayOfFile = this.mGraphicsStatsDir.listFiles();
    int i = arrayOfFile.length;
    for (byte b = 0; b < i; b++) {
      File[] arrayOfFile1 = arrayOfFile[b].listFiles();
      int j = arrayOfFile1.length;
      for (byte b1 = 0; b1 < j; b1++) {
        File[] arrayOfFile2 = arrayOfFile1[b1].listFiles();
        int k = arrayOfFile2.length;
        for (byte b2 = 0; b2 < k; b2++) {
          File file = new File(arrayOfFile2[b2], "total");
          if (!paramHashSet.contains(file))
            nAddToDump(paramLong, file.getAbsolutePath()); 
        } 
      } 
    } 
  }
  
  private ActiveBuffer fetchActiveBuffersLocked(IGraphicsStatsCallback paramIGraphicsStatsCallback, int paramInt1, int paramInt2, String paramString, long paramLong) throws RemoteException {
    int i = this.mActive.size();
    long l = normalizeDate(System.currentTimeMillis()).getTimeInMillis();
    for (byte b = 0; b < i; b++) {
      ActiveBuffer activeBuffer = this.mActive.get(b);
      if (activeBuffer.mPid == paramInt2 && activeBuffer.mUid == paramInt1) {
        if (activeBuffer.mInfo.mStartTime < l) {
          activeBuffer.binderDied();
          break;
        } 
        return activeBuffer;
      } 
    } 
    try {
      ActiveBuffer activeBuffer = new ActiveBuffer();
      this(this, paramIGraphicsStatsCallback, paramInt1, paramInt2, paramString, paramLong);
      this.mActive.add(activeBuffer);
      return activeBuffer;
    } catch (IOException iOException) {
      throw new RemoteException("Failed to allocate space");
    } 
  }
  
  private static native void nAddToDump(long paramLong, String paramString);
  
  private static native void nAddToDump(long paramLong1, String paramString1, String paramString2, long paramLong2, long paramLong3, long paramLong4, byte[] paramArrayOfbyte);
  
  private static native long nCreateDump(int paramInt, boolean paramBoolean);
  
  private static native void nFinishDump(long paramLong);
  
  private static native void nFinishDumpInMemory(long paramLong1, long paramLong2, boolean paramBoolean);
  
  private static native int nGetAshmemSize();
  
  private static native void nSaveBuffer(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, byte[] paramArrayOfbyte);
  
  private static native void nativeDestructor();
  
  private native void nativeInit();
  
  private Calendar normalizeDate(long paramLong) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.setTimeInMillis(paramLong);
    calendar.set(11, 0);
    calendar.set(12, 0);
    calendar.set(13, 0);
    calendar.set(14, 0);
    return calendar;
  }
  
  private void onAlarm() {
    synchronized (this.mLock) {
      this.mRotateIsScheduled = false;
      scheduleRotateLocked();
      ActiveBuffer[] arrayOfActiveBuffer = this.mActive.<ActiveBuffer>toArray(new ActiveBuffer[0]);
      int i = arrayOfActiveBuffer.length;
      for (byte b = 0; b < i; b++) {
        null = arrayOfActiveBuffer[b];
        try {
          ((ActiveBuffer)null).mCallback.onRotateGraphicsStatsBuffer();
        } catch (RemoteException remoteException) {
          Log.w("GraphicsStatsService", String.format("Failed to notify '%s' (pid=%d) to rotate buffers", new Object[] { ((ActiveBuffer)null).mInfo.mPackageName, Integer.valueOf(((ActiveBuffer)null).mPid) }), (Throwable)remoteException);
        } 
      } 
      this.mWriteOutHandler.sendEmptyMessageDelayed(2, 10000L);
      return;
    } 
  }
  
  private File pathForApp(BufferInfo paramBufferInfo) {
    String str = String.format("%d/%s/%d/total", new Object[] { Long.valueOf(normalizeDate(paramBufferInfo.mStartTime).getTimeInMillis()), paramBufferInfo.mPackageName, Long.valueOf(paramBufferInfo.mVersionCode) });
    return new File(this.mGraphicsStatsDir, str);
  }
  
  private void processDied(ActiveBuffer paramActiveBuffer) {
    synchronized (this.mLock) {
      this.mActive.remove(paramActiveBuffer);
      addToSaveQueue(paramActiveBuffer);
      return;
    } 
  }
  
  private void pullGraphicsStats(boolean paramBoolean, long paramLong) throws RemoteException {
    if (Binder.getCallingUid() != 1066) {
      StringWriter stringWriter = new StringWriter();
      FastPrintWriter fastPrintWriter = new FastPrintWriter(stringWriter);
      if (!DumpUtils.checkDumpAndUsageStatsPermission(this.mContext, "GraphicsStatsService", (PrintWriter)fastPrintWriter)) {
        fastPrintWriter.flush();
        throw new RemoteException(stringWriter.toString());
      } 
    } 
    long l = Binder.clearCallingIdentity();
    try {
      pullGraphicsStatsImpl(paramBoolean, paramLong);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
  
  private void pullGraphicsStatsImpl(boolean paramBoolean, long paramLong) {
    // Byte code:
    //   0: iload_1
    //   1: ifeq -> 23
    //   4: aload_0
    //   5: invokestatic currentTimeMillis : ()J
    //   8: ldc2_w 86400000
    //   11: lsub
    //   12: invokespecial normalizeDate : (J)Ljava/util/Calendar;
    //   15: invokevirtual getTimeInMillis : ()J
    //   18: lstore #4
    //   20: goto -> 35
    //   23: aload_0
    //   24: invokestatic currentTimeMillis : ()J
    //   27: invokespecial normalizeDate : (J)Ljava/util/Calendar;
    //   30: invokevirtual getTimeInMillis : ()J
    //   33: lstore #4
    //   35: aload_0
    //   36: getfield mLock : Ljava/lang/Object;
    //   39: astore #6
    //   41: aload #6
    //   43: monitorenter
    //   44: new java/util/ArrayList
    //   47: astore #7
    //   49: aload #7
    //   51: aload_0
    //   52: getfield mActive : Ljava/util/ArrayList;
    //   55: invokevirtual size : ()I
    //   58: invokespecial <init> : (I)V
    //   61: iconst_0
    //   62: istore #8
    //   64: aload_0
    //   65: getfield mActive : Ljava/util/ArrayList;
    //   68: invokevirtual size : ()I
    //   71: istore #9
    //   73: iload #8
    //   75: iload #9
    //   77: if_icmpge -> 149
    //   80: aload_0
    //   81: getfield mActive : Ljava/util/ArrayList;
    //   84: iload #8
    //   86: invokevirtual get : (I)Ljava/lang/Object;
    //   89: checkcast android/graphics/GraphicsStatsService$ActiveBuffer
    //   92: astore #10
    //   94: aload #10
    //   96: getfield mInfo : Landroid/graphics/GraphicsStatsService$BufferInfo;
    //   99: getfield mStartTime : J
    //   102: lstore #11
    //   104: lload #11
    //   106: lload #4
    //   108: lcmp
    //   109: ifne -> 138
    //   112: new android/graphics/GraphicsStatsService$HistoricalBuffer
    //   115: astore #13
    //   117: aload #13
    //   119: aload_0
    //   120: aload #10
    //   122: invokespecial <init> : (Landroid/graphics/GraphicsStatsService;Landroid/graphics/GraphicsStatsService$ActiveBuffer;)V
    //   125: aload #7
    //   127: aload #13
    //   129: invokevirtual add : (Ljava/lang/Object;)Z
    //   132: pop
    //   133: goto -> 138
    //   136: astore #13
    //   138: iinc #8, 1
    //   141: goto -> 64
    //   144: astore #7
    //   146: goto -> 413
    //   149: aload #6
    //   151: monitorexit
    //   152: iconst_m1
    //   153: iconst_1
    //   154: invokestatic nCreateDump : (IZ)J
    //   157: lstore #14
    //   159: aload_0
    //   160: getfield mFileAccessLock : Ljava/lang/Object;
    //   163: astore #13
    //   165: aload #13
    //   167: monitorenter
    //   168: lload #4
    //   170: lstore #11
    //   172: aload_0
    //   173: lload #14
    //   175: aload #7
    //   177: invokespecial dumpActiveLocked : (JLjava/util/ArrayList;)Ljava/util/HashSet;
    //   180: astore #10
    //   182: lload #4
    //   184: lstore #11
    //   186: aload #7
    //   188: invokevirtual clear : ()V
    //   191: iconst_0
    //   192: istore #8
    //   194: lload #4
    //   196: lstore #11
    //   198: ldc_w '%d'
    //   201: iconst_1
    //   202: anewarray java/lang/Object
    //   205: dup
    //   206: iconst_0
    //   207: lload #4
    //   209: invokestatic valueOf : (J)Ljava/lang/Long;
    //   212: aastore
    //   213: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   216: astore #7
    //   218: lload #4
    //   220: lstore #11
    //   222: new java/io/File
    //   225: astore #6
    //   227: lload #4
    //   229: lstore #11
    //   231: aload #6
    //   233: aload_0
    //   234: getfield mGraphicsStatsDir : Ljava/io/File;
    //   237: aload #7
    //   239: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   242: lload #4
    //   244: lstore #11
    //   246: aload #6
    //   248: invokevirtual exists : ()Z
    //   251: ifeq -> 370
    //   254: lload #4
    //   256: lstore #11
    //   258: aload #6
    //   260: invokevirtual listFiles : ()[Ljava/io/File;
    //   263: astore #16
    //   265: lload #4
    //   267: lstore #11
    //   269: aload #16
    //   271: arraylength
    //   272: istore #17
    //   274: iload #8
    //   276: iload #17
    //   278: if_icmpge -> 367
    //   281: lload #4
    //   283: lstore #11
    //   285: aload #16
    //   287: iload #8
    //   289: aaload
    //   290: invokevirtual listFiles : ()[Ljava/io/File;
    //   293: astore #6
    //   295: aload #6
    //   297: arraylength
    //   298: istore #9
    //   300: iconst_0
    //   301: istore #18
    //   303: iload #18
    //   305: iload #9
    //   307: if_icmpge -> 361
    //   310: aload #6
    //   312: iload #18
    //   314: aaload
    //   315: astore #19
    //   317: new java/io/File
    //   320: astore #20
    //   322: aload #20
    //   324: aload #19
    //   326: ldc_w 'total'
    //   329: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   332: aload #10
    //   334: aload #20
    //   336: invokevirtual contains : (Ljava/lang/Object;)Z
    //   339: ifeq -> 345
    //   342: goto -> 355
    //   345: lload #14
    //   347: aload #20
    //   349: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   352: invokestatic nAddToDump : (JLjava/lang/String;)V
    //   355: iinc #18, 1
    //   358: goto -> 303
    //   361: iinc #8, 1
    //   364: goto -> 274
    //   367: goto -> 370
    //   370: aload #13
    //   372: monitorexit
    //   373: lload #14
    //   375: lload_2
    //   376: iload_1
    //   377: invokestatic nFinishDumpInMemory : (JJZ)V
    //   380: return
    //   381: astore #7
    //   383: aload #13
    //   385: monitorexit
    //   386: aload #7
    //   388: athrow
    //   389: astore #7
    //   391: goto -> 401
    //   394: astore #7
    //   396: goto -> 383
    //   399: astore #7
    //   401: lload #14
    //   403: lload_2
    //   404: iload_1
    //   405: invokestatic nFinishDumpInMemory : (JJZ)V
    //   408: aload #7
    //   410: athrow
    //   411: astore #7
    //   413: aload #6
    //   415: monitorexit
    //   416: aload #7
    //   418: athrow
    //   419: astore #7
    //   421: goto -> 413
    // Exception table:
    //   from	to	target	type
    //   44	61	411	finally
    //   64	73	411	finally
    //   80	104	144	finally
    //   112	133	136	java/io/IOException
    //   112	133	144	finally
    //   149	152	411	finally
    //   159	168	399	finally
    //   172	182	381	finally
    //   186	191	381	finally
    //   198	218	381	finally
    //   222	227	381	finally
    //   231	242	381	finally
    //   246	254	381	finally
    //   258	265	381	finally
    //   269	274	381	finally
    //   285	295	381	finally
    //   295	300	394	finally
    //   317	342	394	finally
    //   345	355	394	finally
    //   370	373	394	finally
    //   383	386	394	finally
    //   386	389	389	finally
    //   413	416	419	finally
  }
  
  private ParcelFileDescriptor requestBufferForProcessLocked(IGraphicsStatsCallback paramIGraphicsStatsCallback, int paramInt1, int paramInt2, String paramString, long paramLong) throws RemoteException {
    ActiveBuffer activeBuffer = fetchActiveBuffersLocked(paramIGraphicsStatsCallback, paramInt1, paramInt2, paramString, paramLong);
    scheduleRotateLocked();
    return activeBuffer.getPfd();
  }
  
  private void saveBuffer(HistoricalBuffer paramHistoricalBuffer) {
    if (Trace.isTagEnabled(524288L)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("saving graphicsstats for ");
      stringBuilder.append(paramHistoricalBuffer.mInfo.mPackageName);
      Trace.traceBegin(524288L, stringBuilder.toString());
    } 
    synchronized (this.mFileAccessLock) {
      StringBuilder stringBuilder;
      File file1 = pathForApp(paramHistoricalBuffer.mInfo);
      File file2 = file1.getParentFile();
      file2.mkdirs();
      if (!file2.exists()) {
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Unable to create path: '");
        stringBuilder.append(file2.getAbsolutePath());
        stringBuilder.append("'");
        Log.w("GraphicsStatsService", stringBuilder.toString());
        return;
      } 
      nSaveBuffer(file1.getAbsolutePath(), ((HistoricalBuffer)stringBuilder).mInfo.mPackageName, ((HistoricalBuffer)stringBuilder).mInfo.mVersionCode, ((HistoricalBuffer)stringBuilder).mInfo.mStartTime, ((HistoricalBuffer)stringBuilder).mInfo.mEndTime, ((HistoricalBuffer)stringBuilder).mData);
      Trace.traceEnd(524288L);
      return;
    } 
  }
  
  private void scheduleRotateLocked() {
    if (this.mRotateIsScheduled)
      return; 
    this.mRotateIsScheduled = true;
    Calendar calendar = normalizeDate(System.currentTimeMillis());
    calendar.add(5, 1);
    this.mAlarmManager.setExact(1, calendar.getTimeInMillis(), "GraphicsStatsService", new _$$Lambda$GraphicsStatsService$an_DvOX2nWltnD5OBOre5S9EpXs(this), this.mWriteOutHandler);
  }
  
  protected void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mContext : Landroid/content/Context;
    //   4: ldc 'GraphicsStatsService'
    //   6: aload_2
    //   7: invokestatic checkDumpAndUsageStatsPermission : (Landroid/content/Context;Ljava/lang/String;Ljava/io/PrintWriter;)Z
    //   10: ifne -> 14
    //   13: return
    //   14: iconst_0
    //   15: istore #4
    //   17: aload_3
    //   18: arraylength
    //   19: istore #5
    //   21: iconst_0
    //   22: istore #6
    //   24: iload #4
    //   26: istore #7
    //   28: iload #6
    //   30: iload #5
    //   32: if_icmpge -> 60
    //   35: ldc_w '--proto'
    //   38: aload_3
    //   39: iload #6
    //   41: aaload
    //   42: invokevirtual equals : (Ljava/lang/Object;)Z
    //   45: ifeq -> 54
    //   48: iconst_1
    //   49: istore #7
    //   51: goto -> 60
    //   54: iinc #6, 1
    //   57: goto -> 24
    //   60: aload_0
    //   61: getfield mLock : Ljava/lang/Object;
    //   64: astore_3
    //   65: aload_3
    //   66: monitorenter
    //   67: new java/util/ArrayList
    //   70: astore_2
    //   71: aload_2
    //   72: aload_0
    //   73: getfield mActive : Ljava/util/ArrayList;
    //   76: invokevirtual size : ()I
    //   79: invokespecial <init> : (I)V
    //   82: iconst_0
    //   83: istore #6
    //   85: aload_0
    //   86: getfield mActive : Ljava/util/ArrayList;
    //   89: invokevirtual size : ()I
    //   92: istore #5
    //   94: iload #6
    //   96: iload #5
    //   98: if_icmpge -> 142
    //   101: new android/graphics/GraphicsStatsService$HistoricalBuffer
    //   104: astore #8
    //   106: aload #8
    //   108: aload_0
    //   109: aload_0
    //   110: getfield mActive : Ljava/util/ArrayList;
    //   113: iload #6
    //   115: invokevirtual get : (I)Ljava/lang/Object;
    //   118: checkcast android/graphics/GraphicsStatsService$ActiveBuffer
    //   121: invokespecial <init> : (Landroid/graphics/GraphicsStatsService;Landroid/graphics/GraphicsStatsService$ActiveBuffer;)V
    //   124: aload_2
    //   125: aload #8
    //   127: invokevirtual add : (Ljava/lang/Object;)Z
    //   130: pop
    //   131: goto -> 136
    //   134: astore #8
    //   136: iinc #6, 1
    //   139: goto -> 85
    //   142: aload_3
    //   143: monitorexit
    //   144: aload_1
    //   145: invokevirtual getInt$ : ()I
    //   148: iload #7
    //   150: invokestatic nCreateDump : (IZ)J
    //   153: lstore #9
    //   155: aload_0
    //   156: getfield mFileAccessLock : Ljava/lang/Object;
    //   159: astore_1
    //   160: aload_1
    //   161: monitorenter
    //   162: aload_0
    //   163: lload #9
    //   165: aload_2
    //   166: invokespecial dumpActiveLocked : (JLjava/util/ArrayList;)Ljava/util/HashSet;
    //   169: astore_3
    //   170: aload_2
    //   171: invokevirtual clear : ()V
    //   174: aload_0
    //   175: lload #9
    //   177: aload_3
    //   178: invokespecial dumpHistoricalLocked : (JLjava/util/HashSet;)V
    //   181: aload_1
    //   182: monitorexit
    //   183: lload #9
    //   185: invokestatic nFinishDump : (J)V
    //   188: return
    //   189: astore_2
    //   190: aload_1
    //   191: monitorexit
    //   192: aload_2
    //   193: athrow
    //   194: astore_1
    //   195: lload #9
    //   197: invokestatic nFinishDump : (J)V
    //   200: aload_1
    //   201: athrow
    //   202: astore_1
    //   203: aload_3
    //   204: monitorexit
    //   205: aload_1
    //   206: athrow
    // Exception table:
    //   from	to	target	type
    //   67	82	202	finally
    //   85	94	202	finally
    //   101	131	134	java/io/IOException
    //   101	131	202	finally
    //   142	144	202	finally
    //   155	162	194	finally
    //   162	183	189	finally
    //   190	192	189	finally
    //   192	194	194	finally
    //   203	205	202	finally
  }
  
  protected void finalize() throws Throwable {
    nativeDestructor();
  }
  
  public ParcelFileDescriptor requestBufferForProcess(String paramString, IGraphicsStatsCallback paramIGraphicsStatsCallback) throws RemoteException {
    int i = Binder.getCallingUid();
    int j = Binder.getCallingPid();
    long l = Binder.clearCallingIdentity();
    try {
      this.mAppOps.checkPackage(i, paramString);
      Object object1 = this.mContext.getPackageManager().getPackageInfoAsUser(paramString, 0, UserHandle.getUserId(i));
      Object object2 = this.mLock;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      try {
        long l1 = object1.getLongVersionCode();
        Object object = object2;
        object2 = object;
      } finally {
        paramIGraphicsStatsCallback = null;
      } 
      object2 = object1;
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      throw paramIGraphicsStatsCallback;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      RemoteException remoteException = new RemoteException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Unable to find package: '");
      stringBuilder.append(paramString);
      stringBuilder.append("'");
      this(stringBuilder.toString());
      throw remoteException;
    } finally {}
    Binder.restoreCallingIdentity(l);
    throw paramString;
  }
  
  private final class ActiveBuffer implements IBinder.DeathRecipient {
    final IGraphicsStatsCallback mCallback;
    
    final GraphicsStatsService.BufferInfo mInfo;
    
    ByteBuffer mMapping;
    
    final int mPid;
    
    SharedMemory mProcessBuffer;
    
    final IBinder mToken;
    
    final int mUid;
    
    ActiveBuffer(IGraphicsStatsCallback param1IGraphicsStatsCallback, int param1Int1, int param1Int2, String param1String, long param1Long) throws RemoteException, IOException {
      this.mInfo = new GraphicsStatsService.BufferInfo(param1String, param1Long, System.currentTimeMillis());
      this.mUid = param1Int1;
      this.mPid = param1Int2;
      this.mCallback = param1IGraphicsStatsCallback;
      IBinder iBinder = param1IGraphicsStatsCallback.asBinder();
      this.mToken = iBinder;
      iBinder.linkToDeath(this, 0);
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("GFXStats-");
        stringBuilder.append(param1Int2);
        SharedMemory sharedMemory = SharedMemory.create(stringBuilder.toString(), GraphicsStatsService.this.mAshmemSize);
        this.mProcessBuffer = sharedMemory;
        this.mMapping = sharedMemory.mapReadWrite();
      } catch (ErrnoException errnoException) {
        errnoException.rethrowAsIOException();
      } 
      this.mMapping.position(0);
      this.mMapping.put(GraphicsStatsService.this.mZeroData, 0, GraphicsStatsService.this.mAshmemSize);
    }
    
    public void binderDied() {
      this.mToken.unlinkToDeath(this, 0);
      GraphicsStatsService.this.processDied(this);
    }
    
    void closeAllBuffers() {
      ByteBuffer byteBuffer = this.mMapping;
      if (byteBuffer != null) {
        SharedMemory.unmap(byteBuffer);
        this.mMapping = null;
      } 
      SharedMemory sharedMemory = this.mProcessBuffer;
      if (sharedMemory != null) {
        sharedMemory.close();
        this.mProcessBuffer = null;
      } 
    }
    
    ParcelFileDescriptor getPfd() {
      try {
        return this.mProcessBuffer.getFdDup();
      } catch (IOException iOException) {
        throw new IllegalStateException("Failed to get PFD from memory file", iOException);
      } 
    }
    
    void readBytes(byte[] param1ArrayOfbyte, int param1Int) throws IOException {
      ByteBuffer byteBuffer = this.mMapping;
      if (byteBuffer != null) {
        byteBuffer.position(0);
        this.mMapping.get(param1ArrayOfbyte, 0, param1Int);
        return;
      } 
      throw new IOException("SharedMemory has been deactivated");
    }
  }
  
  private final class BufferInfo {
    long mEndTime;
    
    final String mPackageName;
    
    long mStartTime;
    
    final long mVersionCode;
    
    BufferInfo(String param1String, long param1Long1, long param1Long2) {
      this.mPackageName = param1String;
      this.mVersionCode = param1Long1;
      this.mStartTime = param1Long2;
    }
  }
  
  private final class HistoricalBuffer {
    final byte[] mData = new byte[GraphicsStatsService.this.mAshmemSize];
    
    final GraphicsStatsService.BufferInfo mInfo;
    
    HistoricalBuffer(GraphicsStatsService.ActiveBuffer param1ActiveBuffer) throws IOException {
      GraphicsStatsService.BufferInfo bufferInfo = param1ActiveBuffer.mInfo;
      this.mInfo = bufferInfo;
      bufferInfo.mEndTime = System.currentTimeMillis();
      param1ActiveBuffer.readBytes(this.mData, GraphicsStatsService.this.mAshmemSize);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicsStatsService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */