package android.app;

import android.content.Context;
import android.content.IIntentReceiver;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.AndroidException;
import android.util.ArraySet;
import android.util.proto.ProtoOutputStream;
import com.android.internal.os.IResultReceiver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PendingIntent implements Parcelable {
  public static final Parcelable.Creator<PendingIntent> CREATOR;
  
  public static final int FLAG_CANCEL_CURRENT = 268435456;
  
  public static final int FLAG_IMMUTABLE = 67108864;
  
  public static final int FLAG_NO_CREATE = 536870912;
  
  public static final int FLAG_ONE_SHOT = 1073741824;
  
  public static final int FLAG_UPDATE_CURRENT = 134217728;
  
  private static final ThreadLocal<OnMarshaledListener> sOnMarshaledListener = new ThreadLocal<>();
  
  private ArraySet<CancelListener> mCancelListeners;
  
  private IResultReceiver mCancelReceiver;
  
  private final IIntentSender mTarget;
  
  private IBinder mWhitelistToken;
  
  static {
    CREATOR = new Parcelable.Creator<PendingIntent>() {
        public PendingIntent createFromParcel(Parcel param1Parcel) {
          IBinder iBinder = param1Parcel.readStrongBinder();
          if (iBinder != null) {
            PendingIntent pendingIntent = new PendingIntent(iBinder, param1Parcel.getClassCookie(PendingIntent.class));
          } else {
            param1Parcel = null;
          } 
          return (PendingIntent)param1Parcel;
        }
        
        public PendingIntent[] newArray(int param1Int) {
          return new PendingIntent[param1Int];
        }
      };
  }
  
  public PendingIntent(IIntentSender paramIIntentSender) {
    this.mTarget = paramIIntentSender;
  }
  
  PendingIntent(IBinder paramIBinder, Object paramObject) {
    this.mTarget = IIntentSender.Stub.asInterface(paramIBinder);
    if (paramObject != null)
      this.mWhitelistToken = (IBinder)paramObject; 
  }
  
  private static PendingIntent buildServicePendingIntent(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3) {
    String str2;
    String str1 = paramContext.getPackageName();
    Context context = null;
    if (paramIntent != null) {
      str2 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
    } else {
      str2 = null;
    } 
    try {
      PendingIntent pendingIntent;
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityManager iActivityManager = ActivityManager.getService();
      String str = paramContext.getAttributionTag();
      if (str2 != null) {
        String[] arrayOfString = { str2 };
      } else {
        str2 = null;
      } 
      int i = paramContext.getUserId();
      IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(paramInt3, str1, str, null, null, paramInt1, new Intent[] { paramIntent }, (String[])str2, paramInt2, null, i);
      paramContext = context;
      if (iIntentSender != null)
        pendingIntent = new PendingIntent(iIntentSender); 
      return pendingIntent;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static PendingIntent getActivities(Context paramContext, int paramInt1, Intent[] paramArrayOfIntent, int paramInt2) {
    return getActivities(paramContext, paramInt1, paramArrayOfIntent, paramInt2, null);
  }
  
  public static PendingIntent getActivities(Context paramContext, int paramInt1, Intent[] paramArrayOfIntent, int paramInt2, Bundle paramBundle) {
    String str = paramContext.getPackageName();
    String[] arrayOfString = new String[paramArrayOfIntent.length];
    int i;
    for (i = 0; i < paramArrayOfIntent.length; i++) {
      paramArrayOfIntent[i].migrateExtraStreamToClipData(paramContext);
      paramArrayOfIntent[i].prepareToLeaveProcess(paramContext);
      arrayOfString[i] = paramArrayOfIntent[i].resolveTypeIfNeeded(paramContext.getContentResolver());
    } 
    try {
      IActivityManager iActivityManager = ActivityManager.getService();
      String str1 = paramContext.getAttributionTag();
      i = paramContext.getUserId();
      try {
        IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(2, str, str1, null, null, paramInt1, paramArrayOfIntent, arrayOfString, paramInt2, paramBundle, i);
        if (iIntentSender != null) {
          PendingIntent pendingIntent = new PendingIntent(iIntentSender);
        } else {
          iIntentSender = null;
        } 
        return (PendingIntent)iIntentSender;
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  public static PendingIntent getActivitiesAsUser(Context paramContext, int paramInt1, Intent[] paramArrayOfIntent, int paramInt2, Bundle paramBundle, UserHandle paramUserHandle) {
    String str = paramContext.getPackageName();
    String[] arrayOfString = new String[paramArrayOfIntent.length];
    int i;
    for (i = 0; i < paramArrayOfIntent.length; i++) {
      paramArrayOfIntent[i].migrateExtraStreamToClipData(paramContext);
      paramArrayOfIntent[i].prepareToLeaveProcess(paramContext);
      arrayOfString[i] = paramArrayOfIntent[i].resolveTypeIfNeeded(paramContext.getContentResolver());
    } 
    try {
      IActivityManager iActivityManager = ActivityManager.getService();
      String str1 = paramContext.getAttributionTag();
      i = paramUserHandle.getIdentifier();
      try {
        IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(2, str, str1, null, null, paramInt1, paramArrayOfIntent, arrayOfString, paramInt2, paramBundle, i);
        if (iIntentSender != null) {
          PendingIntent pendingIntent = new PendingIntent(iIntentSender);
        } else {
          iIntentSender = null;
        } 
        return (PendingIntent)iIntentSender;
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw remoteException.rethrowFromSystemServer();
  }
  
  public static PendingIntent getActivity(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2) {
    return getActivity(paramContext, paramInt1, paramIntent, paramInt2, null);
  }
  
  public static PendingIntent getActivity(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2, Bundle paramBundle) {
    String str2;
    String str1 = paramContext.getPackageName();
    Context context = null;
    if (paramIntent != null) {
      str2 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
    } else {
      str2 = null;
    } 
    try {
      PendingIntent pendingIntent;
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityManager iActivityManager = ActivityManager.getService();
      String str = paramContext.getAttributionTag();
      if (str2 != null) {
        String[] arrayOfString = { str2 };
      } else {
        str2 = null;
      } 
      int i = paramContext.getUserId();
      IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(2, str1, str, null, null, paramInt1, new Intent[] { paramIntent }, (String[])str2, paramInt2, paramBundle, i);
      paramContext = context;
      if (iIntentSender != null)
        pendingIntent = new PendingIntent(iIntentSender); 
      return pendingIntent;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static PendingIntent getActivityAsUser(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2, Bundle paramBundle, UserHandle paramUserHandle) {
    String str2;
    String str1 = paramContext.getPackageName();
    Context context = null;
    if (paramIntent != null) {
      str2 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
    } else {
      str2 = null;
    } 
    try {
      PendingIntent pendingIntent;
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityManager iActivityManager = ActivityManager.getService();
      String str = paramContext.getAttributionTag();
      if (str2 != null) {
        String[] arrayOfString = { str2 };
      } else {
        paramContext = null;
      } 
      int i = paramUserHandle.getIdentifier();
      IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(2, str1, str, null, null, paramInt1, new Intent[] { paramIntent }, (String[])paramContext, paramInt2, paramBundle, i);
      paramContext = context;
      if (iIntentSender != null)
        pendingIntent = new PendingIntent(iIntentSender); 
      return pendingIntent;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static PendingIntent getBroadcast(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2) {
    return getBroadcastAsUser(paramContext, paramInt1, paramIntent, paramInt2, paramContext.getUser());
  }
  
  public static PendingIntent getBroadcastAsUser(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2, UserHandle paramUserHandle) {
    String str2;
    String str1 = paramContext.getPackageName();
    Context context = null;
    if (paramIntent != null) {
      str2 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
    } else {
      str2 = null;
    } 
    try {
      PendingIntent pendingIntent;
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityManager iActivityManager = ActivityManager.getService();
      String str = paramContext.getAttributionTag();
      if (str2 != null) {
        String[] arrayOfString = { str2 };
      } else {
        paramContext = null;
      } 
      int i = paramUserHandle.getIdentifier();
      IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(1, str1, str, null, null, paramInt1, new Intent[] { paramIntent }, (String[])paramContext, paramInt2, null, i);
      paramContext = context;
      if (iIntentSender != null)
        pendingIntent = new PendingIntent(iIntentSender); 
      return pendingIntent;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static PendingIntent getForegroundService(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2) {
    return buildServicePendingIntent(paramContext, paramInt1, paramIntent, paramInt2, 5);
  }
  
  public static PendingIntent getService(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2) {
    return buildServicePendingIntent(paramContext, paramInt1, paramIntent, paramInt2, 4);
  }
  
  private void notifyCancelListeners() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new android/util/ArraySet
    //   5: astore_1
    //   6: aload_1
    //   7: aload_0
    //   8: getfield mCancelListeners : Landroid/util/ArraySet;
    //   11: invokespecial <init> : (Landroid/util/ArraySet;)V
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: invokevirtual size : ()I
    //   20: istore_2
    //   21: iconst_0
    //   22: istore_3
    //   23: iload_3
    //   24: iload_2
    //   25: if_icmpge -> 48
    //   28: aload_1
    //   29: iload_3
    //   30: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   33: checkcast android/app/PendingIntent$CancelListener
    //   36: aload_0
    //   37: invokeinterface onCancelled : (Landroid/app/PendingIntent;)V
    //   42: iinc #3, 1
    //   45: goto -> 23
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	49	finally
    //   50	52	49	finally
  }
  
  public static PendingIntent readPendingIntentOrNullFromParcel(Parcel paramParcel) {
    IBinder iBinder = paramParcel.readStrongBinder();
    if (iBinder != null) {
      PendingIntent pendingIntent = new PendingIntent(iBinder, paramParcel.getClassCookie(PendingIntent.class));
    } else {
      paramParcel = null;
    } 
    return (PendingIntent)paramParcel;
  }
  
  public static void setOnMarshaledListener(OnMarshaledListener paramOnMarshaledListener) {
    sOnMarshaledListener.set(paramOnMarshaledListener);
  }
  
  public static void writePendingIntentOrNullToParcel(PendingIntent paramPendingIntent, Parcel paramParcel) {
    IBinder iBinder;
    if (paramPendingIntent != null) {
      iBinder = paramPendingIntent.mTarget.asBinder();
    } else {
      iBinder = null;
    } 
    paramParcel.writeStrongBinder(iBinder);
    if (paramPendingIntent != null) {
      OnMarshaledListener onMarshaledListener = sOnMarshaledListener.get();
      if (onMarshaledListener != null)
        onMarshaledListener.onMarshaled(paramPendingIntent, paramParcel, 0); 
    } 
  }
  
  public void cancel() {
    try {
      ActivityManager.getService().cancelIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {}
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    IIntentSender iIntentSender = this.mTarget;
    if (iIntentSender != null)
      paramProtoOutputStream.write(1138166333441L, iIntentSender.asBinder().toString()); 
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof PendingIntent) ? this.mTarget.asBinder().equals(((PendingIntent)paramObject).mTarget.asBinder()) : false;
  }
  
  public String getCreatorPackage() {
    try {
      return ActivityManager.getService().getPackageForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getCreatorUid() {
    try {
      return ActivityManager.getService().getUidForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public UserHandle getCreatorUserHandle() {
    try {
      UserHandle userHandle;
      int i = ActivityManager.getService().getUidForIntentSender(this.mTarget);
      if (i > 0) {
        userHandle = new UserHandle(UserHandle.getUserId(i));
      } else {
        userHandle = null;
      } 
      return userHandle;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Intent getIntent() {
    try {
      return ActivityManager.getService().getIntentForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public IntentSender getIntentSender() {
    return new IntentSender(this.mTarget, this.mWhitelistToken);
  }
  
  public String getTag(String paramString) {
    try {
      return ActivityManager.getService().getTagForIntentSender(this.mTarget, paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public IIntentSender getTarget() {
    return this.mTarget;
  }
  
  @Deprecated
  public String getTargetPackage() {
    try {
      return ActivityManager.getService().getPackageForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public IBinder getWhitelistToken() {
    return this.mWhitelistToken;
  }
  
  public int hashCode() {
    return this.mTarget.asBinder().hashCode();
  }
  
  public boolean isActivity() {
    try {
      return ActivityManager.getService().isIntentSenderAnActivity(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isBroadcast() {
    try {
      return ActivityManager.getService().isIntentSenderABroadcast(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isForegroundService() {
    try {
      return ActivityManager.getService().isIntentSenderAForegroundService(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isTargetedToPackage() {
    try {
      return ActivityManager.getService().isIntentSenderTargetedToPackage(this.mTarget);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerCancelListener(CancelListener paramCancelListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCancelReceiver : Lcom/android/internal/os/IResultReceiver;
    //   6: ifnonnull -> 23
    //   9: new android/app/PendingIntent$1
    //   12: astore_2
    //   13: aload_2
    //   14: aload_0
    //   15: invokespecial <init> : (Landroid/app/PendingIntent;)V
    //   18: aload_0
    //   19: aload_2
    //   20: putfield mCancelReceiver : Lcom/android/internal/os/IResultReceiver;
    //   23: aload_0
    //   24: getfield mCancelListeners : Landroid/util/ArraySet;
    //   27: ifnonnull -> 43
    //   30: new android/util/ArraySet
    //   33: astore_2
    //   34: aload_2
    //   35: invokespecial <init> : ()V
    //   38: aload_0
    //   39: aload_2
    //   40: putfield mCancelListeners : Landroid/util/ArraySet;
    //   43: aload_0
    //   44: getfield mCancelListeners : Landroid/util/ArraySet;
    //   47: invokevirtual isEmpty : ()Z
    //   50: istore_3
    //   51: aload_0
    //   52: getfield mCancelListeners : Landroid/util/ArraySet;
    //   55: aload_1
    //   56: invokevirtual add : (Ljava/lang/Object;)Z
    //   59: pop
    //   60: iload_3
    //   61: ifeq -> 89
    //   64: invokestatic getService : ()Landroid/app/IActivityManager;
    //   67: aload_0
    //   68: getfield mTarget : Landroid/content/IIntentSender;
    //   71: aload_0
    //   72: getfield mCancelReceiver : Lcom/android/internal/os/IResultReceiver;
    //   75: invokeinterface registerIntentSenderCancelListener : (Landroid/content/IIntentSender;Lcom/android/internal/os/IResultReceiver;)V
    //   80: goto -> 89
    //   83: astore_1
    //   84: aload_1
    //   85: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   88: athrow
    //   89: aload_0
    //   90: monitorexit
    //   91: return
    //   92: astore_1
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_1
    //   96: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	92	finally
    //   23	43	92	finally
    //   43	60	92	finally
    //   64	80	83	android/os/RemoteException
    //   64	80	92	finally
    //   84	89	92	finally
    //   89	91	92	finally
    //   93	95	92	finally
  }
  
  public void send() throws CanceledException {
    send(null, 0, null, null, null, null, null);
  }
  
  public void send(int paramInt) throws CanceledException {
    send(null, paramInt, null, null, null, null, null);
  }
  
  public void send(int paramInt, OnFinished paramOnFinished, Handler paramHandler) throws CanceledException {
    send(null, paramInt, null, paramOnFinished, paramHandler, null, null);
  }
  
  public void send(Context paramContext, int paramInt, Intent paramIntent) throws CanceledException {
    send(paramContext, paramInt, paramIntent, null, null, null, null);
  }
  
  public void send(Context paramContext, int paramInt, Intent paramIntent, OnFinished paramOnFinished, Handler paramHandler) throws CanceledException {
    send(paramContext, paramInt, paramIntent, paramOnFinished, paramHandler, null, null);
  }
  
  public void send(Context paramContext, int paramInt, Intent paramIntent, OnFinished paramOnFinished, Handler paramHandler, String paramString) throws CanceledException {
    send(paramContext, paramInt, paramIntent, paramOnFinished, paramHandler, paramString, null);
  }
  
  public void send(Context paramContext, int paramInt, Intent paramIntent, OnFinished paramOnFinished, Handler paramHandler, String paramString, Bundle paramBundle) throws CanceledException {
    if (sendAndReturnResult(paramContext, paramInt, paramIntent, paramOnFinished, paramHandler, paramString, paramBundle) >= 0)
      return; 
    throw new CanceledException();
  }
  
  public int sendAndReturnResult(Context paramContext, int paramInt, Intent paramIntent, OnFinished paramOnFinished, Handler paramHandler, String paramString, Bundle paramBundle) throws CanceledException {
    ActivityOptions activityOptions1;
    ActivityOptions activityOptions2;
    String str;
    if (paramIntent != null) {
      try {
        str = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
      } catch (RemoteException remoteException2) {}
    } else {
      str = null;
    } 
    if (remoteException2 != null && isActivity()) {
      if (paramBundle != null) {
        ActivityOptions activityOptions = new ActivityOptions();
        this(paramBundle);
        activityOptions2 = activityOptions;
      } else {
        activityOptions2 = ActivityOptions.makeBasic();
      } 
      activityOptions2.setCallerDisplayId(remoteException2.getDisplayId());
      Bundle bundle = activityOptions2.toBundle();
    } else {
      activityOptions1 = activityOptions2;
    } 
    try {
      IActivityManager iActivityManager = ActivityManager.getService();
      IIntentSender iIntentSender = this.mTarget;
      IBinder iBinder = this.mWhitelistToken;
      if (paramOnFinished != null) {
        FinishedDispatcher finishedDispatcher = new FinishedDispatcher();
        try {
          this(this, paramOnFinished, paramHandler);
          FinishedDispatcher finishedDispatcher1 = finishedDispatcher;
          return iActivityManager.sendIntentSender(iIntentSender, iBinder, paramInt, paramIntent, str, (IIntentReceiver)finishedDispatcher1, paramString, (Bundle)activityOptions1);
        } catch (RemoteException null) {}
      } else {
        paramOnFinished = null;
        return iActivityManager.sendIntentSender(iIntentSender, iBinder, paramInt, paramIntent, str, (IIntentReceiver)paramOnFinished, paramString, (Bundle)remoteException1);
      } 
    } catch (RemoteException remoteException1) {}
    throw new CanceledException(remoteException1);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("PendingIntent{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(": ");
    IIntentSender iIntentSender = this.mTarget;
    if (iIntentSender != null) {
      IBinder iBinder = iIntentSender.asBinder();
    } else {
      iIntentSender = null;
    } 
    stringBuilder.append(iIntentSender);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void unregisterCancelListener(CancelListener paramCancelListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCancelListeners : Landroid/util/ArraySet;
    //   6: ifnonnull -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: getfield mCancelListeners : Landroid/util/ArraySet;
    //   16: invokevirtual isEmpty : ()Z
    //   19: istore_2
    //   20: aload_0
    //   21: getfield mCancelListeners : Landroid/util/ArraySet;
    //   24: aload_1
    //   25: invokevirtual remove : (Ljava/lang/Object;)Z
    //   28: pop
    //   29: aload_0
    //   30: getfield mCancelListeners : Landroid/util/ArraySet;
    //   33: invokevirtual isEmpty : ()Z
    //   36: istore_3
    //   37: iload_3
    //   38: ifeq -> 70
    //   41: iload_2
    //   42: ifne -> 70
    //   45: invokestatic getService : ()Landroid/app/IActivityManager;
    //   48: aload_0
    //   49: getfield mTarget : Landroid/content/IIntentSender;
    //   52: aload_0
    //   53: getfield mCancelReceiver : Lcom/android/internal/os/IResultReceiver;
    //   56: invokeinterface unregisterIntentSenderCancelListener : (Landroid/content/IIntentSender;Lcom/android/internal/os/IResultReceiver;)V
    //   61: goto -> 70
    //   64: astore_1
    //   65: aload_1
    //   66: invokevirtual rethrowFromSystemServer : ()Ljava/lang/RuntimeException;
    //   69: athrow
    //   70: aload_0
    //   71: monitorexit
    //   72: return
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	73	finally
    //   12	37	73	finally
    //   45	61	64	android/os/RemoteException
    //   45	61	73	finally
    //   65	70	73	finally
    //   70	72	73	finally
    //   74	76	73	finally
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.mTarget.asBinder());
    OnMarshaledListener onMarshaledListener = sOnMarshaledListener.get();
    if (onMarshaledListener != null)
      onMarshaledListener.onMarshaled(this, paramParcel, paramInt); 
  }
  
  public static interface CancelListener {
    void onCancelled(PendingIntent param1PendingIntent);
  }
  
  public static class CanceledException extends AndroidException {
    public CanceledException() {}
    
    public CanceledException(Exception param1Exception) {
      super(param1Exception);
    }
    
    public CanceledException(String param1String) {
      super(param1String);
    }
  }
  
  private static class FinishedDispatcher extends IIntentReceiver.Stub implements Runnable {
    private static Handler sDefaultSystemHandler;
    
    private final Handler mHandler;
    
    private Intent mIntent;
    
    private final PendingIntent mPendingIntent;
    
    private int mResultCode;
    
    private String mResultData;
    
    private Bundle mResultExtras;
    
    private final PendingIntent.OnFinished mWho;
    
    FinishedDispatcher(PendingIntent param1PendingIntent, PendingIntent.OnFinished param1OnFinished, Handler param1Handler) {
      this.mPendingIntent = param1PendingIntent;
      this.mWho = param1OnFinished;
      if (param1Handler == null && ActivityThread.isSystem()) {
        if (sDefaultSystemHandler == null)
          sDefaultSystemHandler = new Handler(Looper.getMainLooper()); 
        this.mHandler = sDefaultSystemHandler;
      } else {
        this.mHandler = param1Handler;
      } 
    }
    
    public void performReceive(Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2) {
      this.mIntent = param1Intent;
      this.mResultCode = param1Int1;
      this.mResultData = param1String;
      this.mResultExtras = param1Bundle;
      Handler handler = this.mHandler;
      if (handler == null) {
        run();
      } else {
        handler.post(this);
      } 
    }
    
    public void run() {
      this.mWho.onSendFinished(this.mPendingIntent, this.mIntent, this.mResultCode, this.mResultData, this.mResultExtras);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Flags {}
  
  public static interface OnFinished {
    void onSendFinished(PendingIntent param1PendingIntent, Intent param1Intent, int param1Int, String param1String, Bundle param1Bundle);
  }
  
  public static interface OnMarshaledListener {
    void onMarshaled(PendingIntent param1PendingIntent, Parcel param1Parcel, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PendingIntent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */