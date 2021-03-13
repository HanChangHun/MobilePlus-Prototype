package android.hardware.radio;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class TunerCallbackAdapter extends ITunerCallback.Stub {
  private static final String TAG = "BroadcastRadio.TunerCallbackAdapter";
  
  private final RadioTuner.Callback mCallback;
  
  RadioManager.ProgramInfo mCurrentProgramInfo;
  
  private boolean mDelayedCompleteCallback = false;
  
  private final Handler mHandler;
  
  boolean mIsAntennaConnected = true;
  
  List<RadioManager.ProgramInfo> mLastCompleteList;
  
  private final Object mLock = new Object();
  
  ProgramList mProgramList;
  
  TunerCallbackAdapter(RadioTuner.Callback paramCallback, Handler paramHandler) {
    this.mCallback = paramCallback;
    if (paramHandler == null) {
      this.mHandler = new Handler(Looper.getMainLooper());
    } else {
      this.mHandler = paramHandler;
    } 
  }
  
  private void sendBackgroundScanCompleteLocked() {
    this.mDelayedCompleteCallback = false;
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$xIUT1Qu5TkA83V8ttYy1zv_JuFo(this));
  }
  
  void clearLastCompleteList() {
    synchronized (this.mLock) {
      this.mLastCompleteList = null;
      return;
    } 
  }
  
  void close() {
    synchronized (this.mLock) {
      if (this.mProgramList != null)
        this.mProgramList.close(); 
      return;
    } 
  }
  
  RadioManager.ProgramInfo getCurrentProgramInformation() {
    synchronized (this.mLock) {
      return this.mCurrentProgramInfo;
    } 
  }
  
  List<RadioManager.ProgramInfo> getLastCompleteList() {
    synchronized (this.mLock) {
      return this.mLastCompleteList;
    } 
  }
  
  boolean isAntennaConnected() {
    return this.mIsAntennaConnected;
  }
  
  public void onAntennaState(boolean paramBoolean) {
    this.mIsAntennaConnected = paramBoolean;
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$dR_VQmFrL_tBD2wpNvborTd8W08(this, paramBoolean));
  }
  
  public void onBackgroundScanAvailabilityChange(boolean paramBoolean) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$4zf9n0sz_rU8z6a9GJmRInWrYkQ(this, paramBoolean));
  }
  
  public void onBackgroundScanComplete() {
    synchronized (this.mLock) {
      if (this.mLastCompleteList == null) {
        Log.i("BroadcastRadio.TunerCallbackAdapter", "Got onBackgroundScanComplete callback, but the program list didn't get through yet. Delaying it...");
        this.mDelayedCompleteCallback = true;
        return;
      } 
      sendBackgroundScanCompleteLocked();
      return;
    } 
  }
  
  public void onConfigurationChanged(RadioManager.BandConfig paramBandConfig) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$B4BuskgdSatf_Xt5wzgLniEltQk(this, paramBandConfig));
  }
  
  public void onCurrentProgramInfoChanged(RadioManager.ProgramInfo paramProgramInfo) {
    if (paramProgramInfo == null) {
      Log.e("BroadcastRadio.TunerCallbackAdapter", "ProgramInfo must not be null");
      return;
    } 
    synchronized (this.mLock) {
      this.mCurrentProgramInfo = paramProgramInfo;
      this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$RSNrzX5_O3nayC2_jg0kAR6KkKY(this, paramProgramInfo));
      return;
    } 
  }
  
  public void onEmergencyAnnouncement(boolean paramBoolean) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$ZwPm3xxjeLvbP12KweyzqFJVnj4(this, paramBoolean));
  }
  
  public void onError(int paramInt) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$jl29exheqPoYrltfLs9fLsjsI1A(this, paramInt));
  }
  
  public void onParametersUpdated(Map paramMap) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$VuQIK9G4xFXWn3HBWuEltoSS_BE(this, paramMap));
  }
  
  public void onProgramListChanged() {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$UsmGhKordXy4lhCylRP0mm2NcYc(this));
  }
  
  public void onProgramListUpdated(ProgramList.Chunk paramChunk) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$rNDBw_02jSdL0O9fv_r929EVVzo(this, paramChunk));
  }
  
  public void onTrafficAnnouncement(boolean paramBoolean) {
    this.mHandler.post(new _$$Lambda$TunerCallbackAdapter$tiaoLZrR2K56rYeqHvSRh5lRdBI(this, paramBoolean));
  }
  
  public void onTuneFailed(int paramInt, ProgramSelector paramProgramSelector) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mHandler : Landroid/os/Handler;
    //   4: new android/hardware/radio/_$$Lambda$TunerCallbackAdapter$Hj_P___HTEx_8p7qvYVPXmhwu7w
    //   7: dup
    //   8: aload_0
    //   9: iload_1
    //   10: aload_2
    //   11: invokespecial <init> : (Landroid/hardware/radio/TunerCallbackAdapter;ILandroid/hardware/radio/ProgramSelector;)V
    //   14: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   17: pop
    //   18: iload_1
    //   19: ldc -2147483648
    //   21: if_icmpeq -> 61
    //   24: iload_1
    //   25: bipush #-38
    //   27: if_icmpeq -> 61
    //   30: iload_1
    //   31: bipush #-32
    //   33: if_icmpeq -> 56
    //   36: iload_1
    //   37: bipush #-22
    //   39: if_icmpeq -> 61
    //   42: iload_1
    //   43: bipush #-19
    //   45: if_icmpeq -> 61
    //   48: iload_1
    //   49: iconst_m1
    //   50: if_icmpeq -> 56
    //   53: goto -> 100
    //   56: iconst_1
    //   57: istore_1
    //   58: goto -> 102
    //   61: new java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial <init> : ()V
    //   68: astore_2
    //   69: aload_2
    //   70: ldc 'Got an error with no mapping to the legacy API ('
    //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: aload_2
    //   77: iload_1
    //   78: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload_2
    //   83: ldc_w '), doing a best-effort conversion to ERROR_SCAN_TIMEOUT'
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: ldc 'BroadcastRadio.TunerCallbackAdapter'
    //   92: aload_2
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   99: pop
    //   100: iconst_3
    //   101: istore_1
    //   102: aload_0
    //   103: getfield mHandler : Landroid/os/Handler;
    //   106: new android/hardware/radio/_$$Lambda$TunerCallbackAdapter$HcS5_voI1xju970_jCP6Iz0LgPE
    //   109: dup
    //   110: aload_0
    //   111: iload_1
    //   112: invokespecial <init> : (Landroid/hardware/radio/TunerCallbackAdapter;I)V
    //   115: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   118: pop
    //   119: return
  }
  
  void setProgramListObserver(ProgramList paramProgramList, ProgramList.OnCloseListener paramOnCloseListener) {
    Objects.requireNonNull(paramOnCloseListener);
    synchronized (this.mLock) {
      if (this.mProgramList != null) {
        Log.w("BroadcastRadio.TunerCallbackAdapter", "Previous program list observer wasn't properly closed, closing it...");
        this.mProgramList.close();
      } 
      this.mProgramList = paramProgramList;
      if (paramProgramList == null)
        return; 
      _$$Lambda$TunerCallbackAdapter$Hl80_0ppQ17uTjZuGamwBQMrO6Y _$$Lambda$TunerCallbackAdapter$Hl80_0ppQ17uTjZuGamwBQMrO6Y = new _$$Lambda$TunerCallbackAdapter$Hl80_0ppQ17uTjZuGamwBQMrO6Y();
      this(this, paramProgramList, paramOnCloseListener);
      paramProgramList.setOnCloseListener(_$$Lambda$TunerCallbackAdapter$Hl80_0ppQ17uTjZuGamwBQMrO6Y);
      _$$Lambda$TunerCallbackAdapter$V_mJUy8dIlOVjsZ1ckkgn490jFI _$$Lambda$TunerCallbackAdapter$V_mJUy8dIlOVjsZ1ckkgn490jFI = new _$$Lambda$TunerCallbackAdapter$V_mJUy8dIlOVjsZ1ckkgn490jFI();
      this(this, paramProgramList);
      paramProgramList.addOnCompleteListener(_$$Lambda$TunerCallbackAdapter$V_mJUy8dIlOVjsZ1ckkgn490jFI);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/TunerCallbackAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */