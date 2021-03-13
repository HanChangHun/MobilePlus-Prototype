package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import libcore.timezone.ZoneInfoDb;

public class AlarmManager {
  public static final String ACTION_NEXT_ALARM_CLOCK_CHANGED = "android.app.action.NEXT_ALARM_CLOCK_CHANGED";
  
  public static final int ELAPSED_REALTIME = 3;
  
  public static final int ELAPSED_REALTIME_WAKEUP = 2;
  
  public static final int FLAG_ALLOW_WHILE_IDLE = 4;
  
  public static final int FLAG_ALLOW_WHILE_IDLE_UNRESTRICTED = 8;
  
  public static final int FLAG_IDLE_UNTIL = 16;
  
  public static final int FLAG_STANDALONE = 1;
  
  public static final int FLAG_WAKE_FROM_IDLE = 2;
  
  public static final long INTERVAL_DAY = 86400000L;
  
  public static final long INTERVAL_FIFTEEN_MINUTES = 900000L;
  
  public static final long INTERVAL_HALF_DAY = 43200000L;
  
  public static final long INTERVAL_HALF_HOUR = 1800000L;
  
  public static final long INTERVAL_HOUR = 3600000L;
  
  public static final int RTC = 1;
  
  public static final int RTC_WAKEUP = 0;
  
  private static final String TAG = "AlarmManager";
  
  public static final long WINDOW_EXACT = 0L;
  
  public static final long WINDOW_HEURISTIC = -1L;
  
  private static WeakHashMap<OnAlarmListener, WeakReference<ListenerWrapper>> sWrappers;
  
  private final boolean mAlwaysExact;
  
  private final Context mContext;
  
  private final Handler mMainThreadHandler;
  
  private final String mPackageName;
  
  private final IAlarmManager mService;
  
  private final int mTargetSdkVersion;
  
  AlarmManager(IAlarmManager paramIAlarmManager, Context paramContext) {
    boolean bool;
    this.mService = paramIAlarmManager;
    this.mContext = paramContext;
    this.mPackageName = paramContext.getPackageName();
    int i = (paramContext.getApplicationInfo()).targetSdkVersion;
    this.mTargetSdkVersion = i;
    if (i < 19) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mAlwaysExact = bool;
    this.mMainThreadHandler = new Handler(paramContext.getMainLooper());
  }
  
  private long legacyExactLength() {
    long l;
    if (this.mAlwaysExact) {
      l = 0L;
    } else {
      l = -1L;
    } 
    return l;
  }
  
  private void setImpl(int paramInt1, long paramLong1, long paramLong2, long paramLong3, int paramInt2, PendingIntent paramPendingIntent, OnAlarmListener paramOnAlarmListener, String paramString, Handler paramHandler, WorkSource paramWorkSource, AlarmClockInfo paramAlarmClockInfo) {
    // Byte code:
    //   0: lload_2
    //   1: lconst_0
    //   2: lcmp
    //   3: ifge -> 11
    //   6: lconst_0
    //   7: lstore_2
    //   8: goto -> 11
    //   11: aconst_null
    //   12: astore #15
    //   14: aload #10
    //   16: ifnull -> 159
    //   19: ldc android/app/AlarmManager
    //   21: monitorenter
    //   22: getstatic android/app/AlarmManager.sWrappers : Ljava/util/WeakHashMap;
    //   25: ifnonnull -> 43
    //   28: new java/util/WeakHashMap
    //   31: astore #16
    //   33: aload #16
    //   35: invokespecial <init> : ()V
    //   38: aload #16
    //   40: putstatic android/app/AlarmManager.sWrappers : Ljava/util/WeakHashMap;
    //   43: getstatic android/app/AlarmManager.sWrappers : Ljava/util/WeakHashMap;
    //   46: aload #10
    //   48: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   51: checkcast java/lang/ref/WeakReference
    //   54: astore #16
    //   56: aload #16
    //   58: ifnull -> 71
    //   61: aload #16
    //   63: invokevirtual get : ()Ljava/lang/Object;
    //   66: checkcast android/app/AlarmManager$ListenerWrapper
    //   69: astore #15
    //   71: aload #15
    //   73: astore #16
    //   75: aload #15
    //   77: ifnonnull -> 120
    //   80: new android/app/AlarmManager$ListenerWrapper
    //   83: astore #16
    //   85: aload #16
    //   87: aload_0
    //   88: aload #10
    //   90: invokespecial <init> : (Landroid/app/AlarmManager;Landroid/app/AlarmManager$OnAlarmListener;)V
    //   93: getstatic android/app/AlarmManager.sWrappers : Ljava/util/WeakHashMap;
    //   96: astore #15
    //   98: new java/lang/ref/WeakReference
    //   101: astore #17
    //   103: aload #17
    //   105: aload #16
    //   107: invokespecial <init> : (Ljava/lang/Object;)V
    //   110: aload #15
    //   112: aload #10
    //   114: aload #17
    //   116: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   119: pop
    //   120: ldc android/app/AlarmManager
    //   122: monitorexit
    //   123: aload #12
    //   125: ifnull -> 135
    //   128: aload #12
    //   130: astore #10
    //   132: goto -> 141
    //   135: aload_0
    //   136: getfield mMainThreadHandler : Landroid/os/Handler;
    //   139: astore #10
    //   141: aload #16
    //   143: aload #10
    //   145: invokevirtual setHandler : (Landroid/os/Handler;)V
    //   148: goto -> 162
    //   151: astore #9
    //   153: ldc android/app/AlarmManager
    //   155: monitorexit
    //   156: aload #9
    //   158: athrow
    //   159: aconst_null
    //   160: astore #16
    //   162: aload_0
    //   163: getfield mService : Landroid/app/IAlarmManager;
    //   166: aload_0
    //   167: getfield mPackageName : Ljava/lang/String;
    //   170: iload_1
    //   171: lload_2
    //   172: lload #4
    //   174: lload #6
    //   176: iload #8
    //   178: aload #9
    //   180: aload #16
    //   182: aload #11
    //   184: aload #13
    //   186: aload #14
    //   188: invokeinterface set : (Ljava/lang/String;IJJJILandroid/app/PendingIntent;Landroid/app/IAlarmListener;Ljava/lang/String;Landroid/os/WorkSource;Landroid/app/AlarmManager$AlarmClockInfo;)V
    //   193: return
    //   194: astore #9
    //   196: aload #9
    //   198: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   201: athrow
    // Exception table:
    //   from	to	target	type
    //   22	43	151	finally
    //   43	56	151	finally
    //   61	71	151	finally
    //   80	93	151	finally
    //   93	120	151	finally
    //   120	123	151	finally
    //   153	156	151	finally
    //   162	193	194	android/os/RemoteException
  }
  
  public void cancel(OnAlarmListener paramOnAlarmListener) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 95
    //   4: aconst_null
    //   5: astore_2
    //   6: ldc android/app/AlarmManager
    //   8: monitorenter
    //   9: aload_2
    //   10: astore_3
    //   11: getstatic android/app/AlarmManager.sWrappers : Ljava/util/WeakHashMap;
    //   14: ifnull -> 45
    //   17: getstatic android/app/AlarmManager.sWrappers : Ljava/util/WeakHashMap;
    //   20: aload_1
    //   21: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   24: checkcast java/lang/ref/WeakReference
    //   27: astore #4
    //   29: aload_2
    //   30: astore_3
    //   31: aload #4
    //   33: ifnull -> 45
    //   36: aload #4
    //   38: invokevirtual get : ()Ljava/lang/Object;
    //   41: checkcast android/app/AlarmManager$ListenerWrapper
    //   44: astore_3
    //   45: ldc android/app/AlarmManager
    //   47: monitorexit
    //   48: aload_3
    //   49: ifnonnull -> 84
    //   52: new java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial <init> : ()V
    //   59: astore_3
    //   60: aload_3
    //   61: ldc 'Unrecognized alarm listener '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_3
    //   68: aload_1
    //   69: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: ldc 'AlarmManager'
    //   75: aload_3
    //   76: invokevirtual toString : ()Ljava/lang/String;
    //   79: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   82: pop
    //   83: return
    //   84: aload_3
    //   85: invokevirtual cancel : ()V
    //   88: return
    //   89: astore_1
    //   90: ldc android/app/AlarmManager
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    //   95: new java/lang/NullPointerException
    //   98: dup
    //   99: ldc 'cancel() called with a null OnAlarmListener'
    //   101: invokespecial <init> : (Ljava/lang/String;)V
    //   104: athrow
    // Exception table:
    //   from	to	target	type
    //   11	29	89	finally
    //   36	45	89	finally
    //   45	48	89	finally
    //   90	93	89	finally
  }
  
  public void cancel(PendingIntent paramPendingIntent) {
    if (paramPendingIntent == null) {
      if (this.mTargetSdkVersion < 24) {
        Log.e("AlarmManager", "cancel() called with a null PendingIntent");
        return;
      } 
      throw new NullPointerException("cancel() called with a null PendingIntent");
    } 
    try {
      this.mService.remove(paramPendingIntent, null);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AlarmClockInfo getNextAlarmClock() {
    return getNextAlarmClock(this.mContext.getUserId());
  }
  
  public AlarmClockInfo getNextAlarmClock(int paramInt) {
    try {
      return this.mService.getNextAlarmClock(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getNextWakeFromIdleTime() {
    try {
      return this.mService.getNextWakeFromIdleTime();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void set(int paramInt, long paramLong1, long paramLong2, long paramLong3, OnAlarmListener paramOnAlarmListener, Handler paramHandler, WorkSource paramWorkSource) {
    setImpl(paramInt, paramLong1, paramLong2, paramLong3, 0, null, paramOnAlarmListener, null, paramHandler, paramWorkSource, null);
  }
  
  @SystemApi
  public void set(int paramInt, long paramLong1, long paramLong2, long paramLong3, PendingIntent paramPendingIntent, WorkSource paramWorkSource) {
    setImpl(paramInt, paramLong1, paramLong2, paramLong3, 0, paramPendingIntent, null, null, null, paramWorkSource, null);
  }
  
  public void set(int paramInt, long paramLong1, long paramLong2, long paramLong3, String paramString, OnAlarmListener paramOnAlarmListener, Handler paramHandler, WorkSource paramWorkSource) {
    setImpl(paramInt, paramLong1, paramLong2, paramLong3, 0, null, paramOnAlarmListener, paramString, paramHandler, paramWorkSource, null);
  }
  
  public void set(int paramInt, long paramLong, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong, legacyExactLength(), 0L, 0, paramPendingIntent, null, null, null, null, null);
  }
  
  public void set(int paramInt, long paramLong, String paramString, OnAlarmListener paramOnAlarmListener, Handler paramHandler) {
    setImpl(paramInt, paramLong, legacyExactLength(), 0L, 0, null, paramOnAlarmListener, paramString, paramHandler, null, null);
  }
  
  public void setAlarmClock(AlarmClockInfo paramAlarmClockInfo, PendingIntent paramPendingIntent) {
    setImpl(0, paramAlarmClockInfo.getTriggerTime(), 0L, 0L, 0, paramPendingIntent, null, null, null, null, paramAlarmClockInfo);
  }
  
  public void setAndAllowWhileIdle(int paramInt, long paramLong, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong, -1L, 0L, 4, paramPendingIntent, null, null, null, null, null);
  }
  
  public void setExact(int paramInt, long paramLong, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong, 0L, 0L, 0, paramPendingIntent, null, null, null, null, null);
  }
  
  public void setExact(int paramInt, long paramLong, String paramString, OnAlarmListener paramOnAlarmListener, Handler paramHandler) {
    setImpl(paramInt, paramLong, 0L, 0L, 0, null, paramOnAlarmListener, paramString, paramHandler, null, null);
  }
  
  public void setExactAndAllowWhileIdle(int paramInt, long paramLong, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong, 0L, 0L, 4, paramPendingIntent, null, null, null, null, null);
  }
  
  public void setIdleUntil(int paramInt, long paramLong, String paramString, OnAlarmListener paramOnAlarmListener, Handler paramHandler) {
    setImpl(paramInt, paramLong, 0L, 0L, 16, null, paramOnAlarmListener, paramString, paramHandler, null, null);
  }
  
  public void setInexactRepeating(int paramInt, long paramLong1, long paramLong2, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong1, -1L, paramLong2, 0, paramPendingIntent, null, null, null, null, null);
  }
  
  public void setRepeating(int paramInt, long paramLong1, long paramLong2, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong1, legacyExactLength(), paramLong2, 0, paramPendingIntent, null, null, null, null, null);
  }
  
  public void setTime(long paramLong) {
    try {
      this.mService.setTime(paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setTimeZone(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return; 
    if (this.mTargetSdkVersion >= 23) {
      boolean bool = false;
      try {
        boolean bool1 = ZoneInfoDb.getInstance().hasTimeZone(paramString);
        bool = bool1;
      } catch (IOException iOException) {}
      if (!bool) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timezone: ");
        stringBuilder.append(paramString);
        stringBuilder.append(" is not an Olson ID");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } 
    try {
      this.mService.setTimeZone(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setWindow(int paramInt, long paramLong1, long paramLong2, PendingIntent paramPendingIntent) {
    setImpl(paramInt, paramLong1, paramLong2, 0L, 0, paramPendingIntent, null, null, null, null, null);
  }
  
  public void setWindow(int paramInt, long paramLong1, long paramLong2, String paramString, OnAlarmListener paramOnAlarmListener, Handler paramHandler) {
    setImpl(paramInt, paramLong1, paramLong2, 0L, 0, null, paramOnAlarmListener, paramString, paramHandler, null, null);
  }
  
  public static final class AlarmClockInfo implements Parcelable {
    public static final Parcelable.Creator<AlarmClockInfo> CREATOR = new Parcelable.Creator<AlarmClockInfo>() {
        public AlarmManager.AlarmClockInfo createFromParcel(Parcel param2Parcel) {
          return new AlarmManager.AlarmClockInfo(param2Parcel);
        }
        
        public AlarmManager.AlarmClockInfo[] newArray(int param2Int) {
          return new AlarmManager.AlarmClockInfo[param2Int];
        }
      };
    
    private final PendingIntent mShowIntent;
    
    private final long mTriggerTime;
    
    public AlarmClockInfo(long param1Long, PendingIntent param1PendingIntent) {
      this.mTriggerTime = param1Long;
      this.mShowIntent = param1PendingIntent;
    }
    
    AlarmClockInfo(Parcel param1Parcel) {
      this.mTriggerTime = param1Parcel.readLong();
      this.mShowIntent = (PendingIntent)param1Parcel.readParcelable(PendingIntent.class.getClassLoader());
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void dumpDebug(ProtoOutputStream param1ProtoOutputStream, long param1Long) {
      param1Long = param1ProtoOutputStream.start(param1Long);
      param1ProtoOutputStream.write(1112396529665L, this.mTriggerTime);
      PendingIntent pendingIntent = this.mShowIntent;
      if (pendingIntent != null)
        pendingIntent.dumpDebug(param1ProtoOutputStream, 1146756268034L); 
      param1ProtoOutputStream.end(param1Long);
    }
    
    public PendingIntent getShowIntent() {
      return this.mShowIntent;
    }
    
    public long getTriggerTime() {
      return this.mTriggerTime;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.mTriggerTime);
      param1Parcel.writeParcelable(this.mShowIntent, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<AlarmClockInfo> {
    public AlarmManager.AlarmClockInfo createFromParcel(Parcel param1Parcel) {
      return new AlarmManager.AlarmClockInfo(param1Parcel);
    }
    
    public AlarmManager.AlarmClockInfo[] newArray(int param1Int) {
      return new AlarmManager.AlarmClockInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AlarmType {}
  
  final class ListenerWrapper extends IAlarmListener.Stub implements Runnable {
    IAlarmCompleteListener mCompletion;
    
    Handler mHandler;
    
    final AlarmManager.OnAlarmListener mListener;
    
    public ListenerWrapper(AlarmManager.OnAlarmListener param1OnAlarmListener) {
      this.mListener = param1OnAlarmListener;
    }
    
    public void cancel() {
      try {
        AlarmManager.this.mService.remove(null, this);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void doAlarm(IAlarmCompleteListener param1IAlarmCompleteListener) {
      this.mCompletion = param1IAlarmCompleteListener;
      this.mHandler.post(this);
    }
    
    public void run() {
      try {
        this.mListener.onAlarm();
        return;
      } finally {
        try {
          this.mCompletion.alarmComplete((IBinder)this);
        } catch (Exception exception) {
          Log.e("AlarmManager", "Unable to report completion to Alarm Manager!", exception);
        } 
      } 
    }
    
    public void setHandler(Handler param1Handler) {
      this.mHandler = param1Handler;
    }
  }
  
  public static interface OnAlarmListener {
    void onAlarm();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AlarmManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */