package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.app.ProfilerInfo;
import android.app.ResultInfo;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.Trace;
import android.view.DisplayAdjustments;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Objects;

public class LaunchActivityItem extends ClientTransactionItem {
  public static final Parcelable.Creator<LaunchActivityItem> CREATOR = new Parcelable.Creator<LaunchActivityItem>() {
      public LaunchActivityItem createFromParcel(Parcel param1Parcel) {
        return new LaunchActivityItem(param1Parcel);
      }
      
      public LaunchActivityItem[] newArray(int param1Int) {
        return new LaunchActivityItem[param1Int];
      }
    };
  
  private IBinder mAssistToken;
  
  private CompatibilityInfo mCompatInfo;
  
  private Configuration mCurConfig;
  
  private DisplayAdjustments.FixedRotationAdjustments mFixedRotationAdjustments;
  
  private int mIdent;
  
  private ActivityInfo mInfo;
  
  private Intent mIntent;
  
  private boolean mIsForward;
  
  private Configuration mOverrideConfig;
  
  private List<ReferrerIntent> mPendingNewIntents;
  
  private List<ResultInfo> mPendingResults;
  
  private PersistableBundle mPersistentState;
  
  private int mProcState;
  
  private ProfilerInfo mProfilerInfo;
  
  private String mReferrer;
  
  private Bundle mState;
  
  private IVoiceInteractor mVoiceInteractor;
  
  private LaunchActivityItem() {}
  
  private LaunchActivityItem(Parcel paramParcel) {
    setValues(this, (Intent)paramParcel.readTypedObject(Intent.CREATOR), paramParcel.readInt(), (ActivityInfo)paramParcel.readTypedObject(ActivityInfo.CREATOR), (Configuration)paramParcel.readTypedObject(Configuration.CREATOR), (Configuration)paramParcel.readTypedObject(Configuration.CREATOR), (CompatibilityInfo)paramParcel.readTypedObject(CompatibilityInfo.CREATOR), paramParcel.readString(), IVoiceInteractor.Stub.asInterface(paramParcel.readStrongBinder()), paramParcel.readInt(), paramParcel.readBundle(getClass().getClassLoader()), paramParcel.readPersistableBundle(getClass().getClassLoader()), paramParcel.createTypedArrayList(ResultInfo.CREATOR), paramParcel.createTypedArrayList(ReferrerIntent.CREATOR), paramParcel.readBoolean(), (ProfilerInfo)paramParcel.readTypedObject(ProfilerInfo.CREATOR), paramParcel.readStrongBinder(), (DisplayAdjustments.FixedRotationAdjustments)paramParcel.readTypedObject(DisplayAdjustments.FixedRotationAdjustments.CREATOR));
  }
  
  private boolean activityInfoEqual(ActivityInfo paramActivityInfo) {
    ActivityInfo activityInfo = this.mInfo;
    boolean bool1 = true;
    boolean bool2 = true;
    if (activityInfo == null) {
      if (paramActivityInfo != null)
        bool2 = false; 
      return bool2;
    } 
    if (paramActivityInfo != null && activityInfo.flags == paramActivityInfo.flags && this.mInfo.maxAspectRatio == paramActivityInfo.maxAspectRatio && Objects.equals(this.mInfo.launchToken, paramActivityInfo.launchToken) && Objects.equals(this.mInfo.getComponentName(), paramActivityInfo.getComponentName())) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    return bool2;
  }
  
  private static boolean areBundlesEqualRoughly(BaseBundle paramBaseBundle1, BaseBundle paramBaseBundle2) {
    boolean bool;
    if (getRoughBundleHashCode(paramBaseBundle1) == getRoughBundleHashCode(paramBaseBundle2)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static int getRoughBundleHashCode(BaseBundle paramBaseBundle) {
    return (paramBaseBundle == null || paramBaseBundle.isDefinitelyEmpty()) ? 0 : 1;
  }
  
  public static LaunchActivityItem obtain(Intent paramIntent, int paramInt1, ActivityInfo paramActivityInfo, Configuration paramConfiguration1, Configuration paramConfiguration2, CompatibilityInfo paramCompatibilityInfo, String paramString, IVoiceInteractor paramIVoiceInteractor, int paramInt2, Bundle paramBundle, PersistableBundle paramPersistableBundle, List<ResultInfo> paramList, List<ReferrerIntent> paramList1, boolean paramBoolean, ProfilerInfo paramProfilerInfo, IBinder paramIBinder, DisplayAdjustments.FixedRotationAdjustments paramFixedRotationAdjustments) {
    LaunchActivityItem launchActivityItem1 = ObjectPool.<LaunchActivityItem>obtain(LaunchActivityItem.class);
    LaunchActivityItem launchActivityItem2 = launchActivityItem1;
    if (launchActivityItem1 == null)
      launchActivityItem2 = new LaunchActivityItem(); 
    setValues(launchActivityItem2, paramIntent, paramInt1, paramActivityInfo, paramConfiguration1, paramConfiguration2, paramCompatibilityInfo, paramString, paramIVoiceInteractor, paramInt2, paramBundle, paramPersistableBundle, paramList, paramList1, paramBoolean, paramProfilerInfo, paramIBinder, paramFixedRotationAdjustments);
    return launchActivityItem2;
  }
  
  private static void setValues(LaunchActivityItem paramLaunchActivityItem, Intent paramIntent, int paramInt1, ActivityInfo paramActivityInfo, Configuration paramConfiguration1, Configuration paramConfiguration2, CompatibilityInfo paramCompatibilityInfo, String paramString, IVoiceInteractor paramIVoiceInteractor, int paramInt2, Bundle paramBundle, PersistableBundle paramPersistableBundle, List<ResultInfo> paramList, List<ReferrerIntent> paramList1, boolean paramBoolean, ProfilerInfo paramProfilerInfo, IBinder paramIBinder, DisplayAdjustments.FixedRotationAdjustments paramFixedRotationAdjustments) {
    paramLaunchActivityItem.mIntent = paramIntent;
    paramLaunchActivityItem.mIdent = paramInt1;
    paramLaunchActivityItem.mInfo = paramActivityInfo;
    paramLaunchActivityItem.mCurConfig = paramConfiguration1;
    paramLaunchActivityItem.mOverrideConfig = paramConfiguration2;
    paramLaunchActivityItem.mCompatInfo = paramCompatibilityInfo;
    paramLaunchActivityItem.mReferrer = paramString;
    paramLaunchActivityItem.mVoiceInteractor = paramIVoiceInteractor;
    paramLaunchActivityItem.mProcState = paramInt2;
    paramLaunchActivityItem.mState = paramBundle;
    paramLaunchActivityItem.mPersistentState = paramPersistableBundle;
    paramLaunchActivityItem.mPendingResults = paramList;
    paramLaunchActivityItem.mPendingNewIntents = paramList1;
    paramLaunchActivityItem.mIsForward = paramBoolean;
    paramLaunchActivityItem.mProfilerInfo = paramProfilerInfo;
    paramLaunchActivityItem.mAssistToken = paramIBinder;
    paramLaunchActivityItem.mFixedRotationAdjustments = paramFixedRotationAdjustments;
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: aload_1
    //   4: if_acmpne -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: aload_1
    //   10: ifnull -> 285
    //   13: aload_0
    //   14: invokevirtual getClass : ()Ljava/lang/Class;
    //   17: aload_1
    //   18: invokevirtual getClass : ()Ljava/lang/Class;
    //   21: if_acmpeq -> 27
    //   24: goto -> 285
    //   27: aload_1
    //   28: checkcast android/app/servertransaction/LaunchActivityItem
    //   31: astore_1
    //   32: aload_0
    //   33: getfield mIntent : Landroid/content/Intent;
    //   36: ifnonnull -> 46
    //   39: aload_1
    //   40: getfield mIntent : Landroid/content/Intent;
    //   43: ifnull -> 66
    //   46: aload_0
    //   47: getfield mIntent : Landroid/content/Intent;
    //   50: astore_3
    //   51: aload_3
    //   52: ifnull -> 72
    //   55: aload_3
    //   56: aload_1
    //   57: getfield mIntent : Landroid/content/Intent;
    //   60: invokevirtual filterEquals : (Landroid/content/Intent;)Z
    //   63: ifeq -> 72
    //   66: iconst_1
    //   67: istore #4
    //   69: goto -> 75
    //   72: iconst_0
    //   73: istore #4
    //   75: iload #4
    //   77: ifeq -> 281
    //   80: aload_0
    //   81: getfield mIdent : I
    //   84: aload_1
    //   85: getfield mIdent : I
    //   88: if_icmpne -> 281
    //   91: aload_0
    //   92: aload_1
    //   93: getfield mInfo : Landroid/content/pm/ActivityInfo;
    //   96: invokespecial activityInfoEqual : (Landroid/content/pm/ActivityInfo;)Z
    //   99: ifeq -> 281
    //   102: aload_0
    //   103: getfield mCurConfig : Landroid/content/res/Configuration;
    //   106: aload_1
    //   107: getfield mCurConfig : Landroid/content/res/Configuration;
    //   110: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   113: ifeq -> 281
    //   116: aload_0
    //   117: getfield mOverrideConfig : Landroid/content/res/Configuration;
    //   120: aload_1
    //   121: getfield mOverrideConfig : Landroid/content/res/Configuration;
    //   124: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   127: ifeq -> 281
    //   130: aload_0
    //   131: getfield mCompatInfo : Landroid/content/res/CompatibilityInfo;
    //   134: aload_1
    //   135: getfield mCompatInfo : Landroid/content/res/CompatibilityInfo;
    //   138: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   141: ifeq -> 281
    //   144: aload_0
    //   145: getfield mReferrer : Ljava/lang/String;
    //   148: aload_1
    //   149: getfield mReferrer : Ljava/lang/String;
    //   152: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   155: ifeq -> 281
    //   158: aload_0
    //   159: getfield mProcState : I
    //   162: aload_1
    //   163: getfield mProcState : I
    //   166: if_icmpne -> 281
    //   169: aload_0
    //   170: getfield mState : Landroid/os/Bundle;
    //   173: aload_1
    //   174: getfield mState : Landroid/os/Bundle;
    //   177: invokestatic areBundlesEqualRoughly : (Landroid/os/BaseBundle;Landroid/os/BaseBundle;)Z
    //   180: ifeq -> 281
    //   183: aload_0
    //   184: getfield mPersistentState : Landroid/os/PersistableBundle;
    //   187: aload_1
    //   188: getfield mPersistentState : Landroid/os/PersistableBundle;
    //   191: invokestatic areBundlesEqualRoughly : (Landroid/os/BaseBundle;Landroid/os/BaseBundle;)Z
    //   194: ifeq -> 281
    //   197: aload_0
    //   198: getfield mPendingResults : Ljava/util/List;
    //   201: aload_1
    //   202: getfield mPendingResults : Ljava/util/List;
    //   205: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   208: ifeq -> 281
    //   211: aload_0
    //   212: getfield mPendingNewIntents : Ljava/util/List;
    //   215: aload_1
    //   216: getfield mPendingNewIntents : Ljava/util/List;
    //   219: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   222: ifeq -> 281
    //   225: aload_0
    //   226: getfield mIsForward : Z
    //   229: aload_1
    //   230: getfield mIsForward : Z
    //   233: if_icmpne -> 281
    //   236: aload_0
    //   237: getfield mProfilerInfo : Landroid/app/ProfilerInfo;
    //   240: aload_1
    //   241: getfield mProfilerInfo : Landroid/app/ProfilerInfo;
    //   244: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   247: ifeq -> 281
    //   250: aload_0
    //   251: getfield mAssistToken : Landroid/os/IBinder;
    //   254: aload_1
    //   255: getfield mAssistToken : Landroid/os/IBinder;
    //   258: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   261: ifeq -> 281
    //   264: aload_0
    //   265: getfield mFixedRotationAdjustments : Landroid/view/DisplayAdjustments$FixedRotationAdjustments;
    //   268: aload_1
    //   269: getfield mFixedRotationAdjustments : Landroid/view/DisplayAdjustments$FixedRotationAdjustments;
    //   272: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   275: ifeq -> 281
    //   278: goto -> 283
    //   281: iconst_0
    //   282: istore_2
    //   283: iload_2
    //   284: ireturn
    //   285: iconst_0
    //   286: ireturn
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityStart");
    paramClientTransactionHandler.handleLaunchActivity(new ActivityThread.ActivityClientRecord(paramIBinder, this.mIntent, this.mIdent, this.mInfo, this.mOverrideConfig, this.mCompatInfo, this.mReferrer, this.mVoiceInteractor, this.mState, this.mPersistentState, this.mPendingResults, this.mPendingNewIntents, this.mIsForward, this.mProfilerInfo, paramClientTransactionHandler, this.mAssistToken, this.mFixedRotationAdjustments), paramPendingTransactionActions, null);
    Trace.traceEnd(64L);
  }
  
  public int hashCode() {
    return ((((((((((((((17 * 31 + this.mIntent.filterHashCode()) * 31 + this.mIdent) * 31 + Objects.hashCode(this.mCurConfig)) * 31 + Objects.hashCode(this.mOverrideConfig)) * 31 + Objects.hashCode(this.mCompatInfo)) * 31 + Objects.hashCode(this.mReferrer)) * 31 + Objects.hashCode(Integer.valueOf(this.mProcState))) * 31 + getRoughBundleHashCode((BaseBundle)this.mState)) * 31 + getRoughBundleHashCode((BaseBundle)this.mPersistentState)) * 31 + Objects.hashCode(this.mPendingResults)) * 31 + Objects.hashCode(this.mPendingNewIntents)) * 31 + this.mIsForward) * 31 + Objects.hashCode(this.mProfilerInfo)) * 31 + Objects.hashCode(this.mAssistToken)) * 31 + Objects.hashCode(this.mFixedRotationAdjustments);
  }
  
  public void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    paramClientTransactionHandler.countLaunchingActivities(-1);
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    paramClientTransactionHandler.countLaunchingActivities(1);
    paramClientTransactionHandler.updateProcessState(this.mProcState, false);
    paramClientTransactionHandler.updatePendingConfiguration(this.mCurConfig);
  }
  
  public void recycle() {
    setValues(this, null, 0, null, null, null, null, null, null, 0, null, null, null, null, false, null, null, null);
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LaunchActivityItem{intent=");
    stringBuilder.append(this.mIntent);
    stringBuilder.append(",ident=");
    stringBuilder.append(this.mIdent);
    stringBuilder.append(",info=");
    stringBuilder.append(this.mInfo);
    stringBuilder.append(",curConfig=");
    stringBuilder.append(this.mCurConfig);
    stringBuilder.append(",overrideConfig=");
    stringBuilder.append(this.mOverrideConfig);
    stringBuilder.append(",referrer=");
    stringBuilder.append(this.mReferrer);
    stringBuilder.append(",procState=");
    stringBuilder.append(this.mProcState);
    stringBuilder.append(",state=");
    stringBuilder.append(this.mState);
    stringBuilder.append(",persistentState=");
    stringBuilder.append(this.mPersistentState);
    stringBuilder.append(",pendingResults=");
    stringBuilder.append(this.mPendingResults);
    stringBuilder.append(",pendingNewIntents=");
    stringBuilder.append(this.mPendingNewIntents);
    stringBuilder.append(",profilerInfo=");
    stringBuilder.append(this.mProfilerInfo);
    stringBuilder.append(",assistToken=");
    stringBuilder.append(this.mAssistToken);
    stringBuilder.append(",rotationAdj=");
    stringBuilder.append(this.mFixedRotationAdjustments);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject((Parcelable)this.mIntent, paramInt);
    paramParcel.writeInt(this.mIdent);
    paramParcel.writeTypedObject((Parcelable)this.mInfo, paramInt);
    paramParcel.writeTypedObject((Parcelable)this.mCurConfig, paramInt);
    paramParcel.writeTypedObject((Parcelable)this.mOverrideConfig, paramInt);
    paramParcel.writeTypedObject((Parcelable)this.mCompatInfo, paramInt);
    paramParcel.writeString(this.mReferrer);
    paramParcel.writeStrongInterface((IInterface)this.mVoiceInteractor);
    paramParcel.writeInt(this.mProcState);
    paramParcel.writeBundle(this.mState);
    paramParcel.writePersistableBundle(this.mPersistentState);
    paramParcel.writeTypedList(this.mPendingResults, paramInt);
    paramParcel.writeTypedList(this.mPendingNewIntents, paramInt);
    paramParcel.writeBoolean(this.mIsForward);
    paramParcel.writeTypedObject((Parcelable)this.mProfilerInfo, paramInt);
    paramParcel.writeStrongBinder(this.mAssistToken);
    paramParcel.writeTypedObject((Parcelable)this.mFixedRotationAdjustments, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/LaunchActivityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */