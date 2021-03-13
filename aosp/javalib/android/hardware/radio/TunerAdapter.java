package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class TunerAdapter extends RadioTuner {
  private static final String TAG = "BroadcastRadio.TunerAdapter";
  
  private int mBand;
  
  private final TunerCallbackAdapter mCallback;
  
  private boolean mIsClosed = false;
  
  private Map<String, String> mLegacyListFilter;
  
  private ProgramList mLegacyListProxy;
  
  private final ITuner mTuner;
  
  TunerAdapter(ITuner paramITuner, TunerCallbackAdapter paramTunerCallbackAdapter, int paramInt) {
    Objects.requireNonNull(paramITuner);
    this.mTuner = paramITuner;
    Objects.requireNonNull(paramTunerCallbackAdapter);
    this.mCallback = paramTunerCallbackAdapter;
    this.mBand = paramInt;
  }
  
  public int cancel() {
    try {
      this.mTuner.cancel();
      return 0;
    } catch (IllegalStateException illegalStateException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't cancel", illegalStateException);
      return -38;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public void cancelAnnouncement() {
    try {
      this.mTuner.cancelAnnouncement();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public void close() {
    synchronized (this.mTuner) {
      if (this.mIsClosed) {
        Log.v("BroadcastRadio.TunerAdapter", "Tuner is already closed");
        return;
      } 
      this.mIsClosed = true;
      if (this.mLegacyListProxy != null) {
        this.mLegacyListProxy.close();
        this.mLegacyListProxy = null;
      } 
      this.mCallback.close();
      try {
        this.mTuner.close();
      } catch (RemoteException remoteException) {
        Log.e("BroadcastRadio.TunerAdapter", "Exception trying to close tuner", (Throwable)remoteException);
      } 
      return;
    } 
  }
  
  public int getConfiguration(RadioManager.BandConfig[] paramArrayOfBandConfig) {
    if (paramArrayOfBandConfig != null && paramArrayOfBandConfig.length == 1)
      try {
        paramArrayOfBandConfig[0] = this.mTuner.getConfiguration();
        return 0;
      } catch (RemoteException remoteException) {
        Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
        return -32;
      }  
    throw new IllegalArgumentException("The argument must be an array of length 1");
  }
  
  public ProgramList getDynamicProgramList(ProgramList.Filter paramFilter) {
    synchronized (this.mTuner) {
      if (this.mLegacyListProxy != null) {
        this.mLegacyListProxy.close();
        this.mLegacyListProxy = null;
      } 
      this.mLegacyListFilter = null;
      ProgramList programList = new ProgramList();
      this();
      TunerCallbackAdapter tunerCallbackAdapter = this.mCallback;
      _$$Lambda$TunerAdapter$ytmKJEaNVVp6n7nE6SVU6pZ9g7c _$$Lambda$TunerAdapter$ytmKJEaNVVp6n7nE6SVU6pZ9g7c = new _$$Lambda$TunerAdapter$ytmKJEaNVVp6n7nE6SVU6pZ9g7c();
      this(this);
      tunerCallbackAdapter.setProgramListObserver(programList, _$$Lambda$TunerAdapter$ytmKJEaNVVp6n7nE6SVU6pZ9g7c);
      try {
        this.mTuner.startProgramListUpdates(paramFilter);
        return programList;
      } catch (UnsupportedOperationException unsupportedOperationException) {
        Log.i("BroadcastRadio.TunerAdapter", "Program list is not supported with this hardware");
        return null;
      } catch (RemoteException remoteException) {
        this.mCallback.setProgramListObserver(null, (ProgramList.OnCloseListener)_$$Lambda$TunerAdapter$St9hluCzvLWs9wyE7kDX24NpwJQ.INSTANCE);
        RuntimeException runtimeException = new RuntimeException();
        this("service died", (Throwable)remoteException);
        throw runtimeException;
      } 
    } 
  }
  
  public Bitmap getMetadataImage(int paramInt) {
    try {
      return this.mTuner.getImage(paramInt);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public boolean getMute() {
    try {
      return this.mTuner.isMuted();
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return true;
    } 
  }
  
  public Map<String, String> getParameters(List<String> paramList) {
    try {
      ITuner iTuner = this.mTuner;
      Objects.requireNonNull(paramList);
      return iTuner.getParameters(paramList);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public int getProgramInformation(RadioManager.ProgramInfo[] paramArrayOfProgramInfo) {
    if (paramArrayOfProgramInfo == null || paramArrayOfProgramInfo.length != 1) {
      Log.e("BroadcastRadio.TunerAdapter", "The argument must be an array of length 1");
      return -22;
    } 
    RadioManager.ProgramInfo programInfo = this.mCallback.getCurrentProgramInformation();
    if (programInfo == null) {
      Log.w("BroadcastRadio.TunerAdapter", "Didn't get program info yet");
      return -38;
    } 
    paramArrayOfProgramInfo[0] = programInfo;
    return 0;
  }
  
  public List<RadioManager.ProgramInfo> getProgramList(Map<String, String> paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mTuner : Landroid/hardware/radio/ITuner;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield mLegacyListProxy : Landroid/hardware/radio/ProgramList;
    //   11: ifnull -> 25
    //   14: aload_0
    //   15: getfield mLegacyListFilter : Ljava/util/Map;
    //   18: aload_1
    //   19: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   22: ifne -> 95
    //   25: ldc 'BroadcastRadio.TunerAdapter'
    //   27: ldc 'Program list filter has changed, requesting new list'
    //   29: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   32: pop
    //   33: new android/hardware/radio/ProgramList
    //   36: astore_3
    //   37: aload_3
    //   38: invokespecial <init> : ()V
    //   41: aload_0
    //   42: aload_3
    //   43: putfield mLegacyListProxy : Landroid/hardware/radio/ProgramList;
    //   46: aload_0
    //   47: aload_1
    //   48: putfield mLegacyListFilter : Ljava/util/Map;
    //   51: aload_0
    //   52: getfield mCallback : Landroid/hardware/radio/TunerCallbackAdapter;
    //   55: invokevirtual clearLastCompleteList : ()V
    //   58: aload_0
    //   59: getfield mCallback : Landroid/hardware/radio/TunerCallbackAdapter;
    //   62: aload_0
    //   63: getfield mLegacyListProxy : Landroid/hardware/radio/ProgramList;
    //   66: getstatic android/hardware/radio/_$$Lambda$TunerAdapter$xm27iP_3PUgByOaDoK2KJcP5fnA.INSTANCE : Landroid/hardware/radio/-$$Lambda$TunerAdapter$xm27iP_3PUgByOaDoK2KJcP5fnA;
    //   69: invokevirtual setProgramListObserver : (Landroid/hardware/radio/ProgramList;Landroid/hardware/radio/ProgramList$OnCloseListener;)V
    //   72: aload_0
    //   73: getfield mTuner : Landroid/hardware/radio/ITuner;
    //   76: astore #4
    //   78: new android/hardware/radio/ProgramList$Filter
    //   81: astore_3
    //   82: aload_3
    //   83: aload_1
    //   84: invokespecial <init> : (Ljava/util/Map;)V
    //   87: aload #4
    //   89: aload_3
    //   90: invokeinterface startProgramListUpdates : (Landroid/hardware/radio/ProgramList$Filter;)V
    //   95: aload_0
    //   96: getfield mCallback : Landroid/hardware/radio/TunerCallbackAdapter;
    //   99: invokevirtual getLastCompleteList : ()Ljava/util/List;
    //   102: astore_1
    //   103: aload_1
    //   104: ifnull -> 111
    //   107: aload_2
    //   108: monitorexit
    //   109: aload_1
    //   110: areturn
    //   111: new java/lang/IllegalStateException
    //   114: astore_1
    //   115: aload_1
    //   116: ldc 'Program list is not ready yet'
    //   118: invokespecial <init> : (Ljava/lang/String;)V
    //   121: aload_1
    //   122: athrow
    //   123: astore_3
    //   124: new java/lang/RuntimeException
    //   127: astore_1
    //   128: aload_1
    //   129: ldc 'service died'
    //   131: aload_3
    //   132: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   135: aload_1
    //   136: athrow
    //   137: astore_1
    //   138: aload_2
    //   139: monitorexit
    //   140: aload_1
    //   141: athrow
    // Exception table:
    //   from	to	target	type
    //   7	25	137	finally
    //   25	72	137	finally
    //   72	95	123	android/os/RemoteException
    //   72	95	137	finally
    //   95	103	137	finally
    //   107	109	137	finally
    //   111	123	137	finally
    //   124	137	137	finally
    //   138	140	137	finally
  }
  
  public boolean hasControl() {
    try {
      boolean bool = this.mTuner.isClosed();
      return bool ^ true;
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isAnalogForced() {
    try {
      return isConfigFlagSet(2);
    } catch (UnsupportedOperationException unsupportedOperationException) {
      throw new IllegalStateException(unsupportedOperationException);
    } 
  }
  
  public boolean isAntennaConnected() {
    return this.mCallback.isAntennaConnected();
  }
  
  public boolean isConfigFlagSet(int paramInt) {
    try {
      return this.mTuner.isConfigFlagSet(paramInt);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public boolean isConfigFlagSupported(int paramInt) {
    try {
      return this.mTuner.isConfigFlagSupported(paramInt);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public int scan(int paramInt, boolean paramBoolean) {
    try {
      ITuner iTuner = this.mTuner;
      boolean bool = true;
      if (paramInt != 1)
        bool = false; 
      iTuner.scan(bool, paramBoolean);
      return 0;
    } catch (IllegalStateException illegalStateException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't scan", illegalStateException);
      return -38;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public void setAnalogForced(boolean paramBoolean) {
    try {
      setConfigFlag(2, paramBoolean);
      return;
    } catch (UnsupportedOperationException unsupportedOperationException) {
      throw new IllegalStateException(unsupportedOperationException);
    } 
  }
  
  public void setConfigFlag(int paramInt, boolean paramBoolean) {
    try {
      this.mTuner.setConfigFlag(paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public int setConfiguration(RadioManager.BandConfig paramBandConfig) {
    if (paramBandConfig == null)
      return -22; 
    try {
      this.mTuner.setConfiguration(paramBandConfig);
      this.mBand = paramBandConfig.getType();
      return 0;
    } catch (IllegalArgumentException illegalArgumentException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't set configuration", illegalArgumentException);
      return -22;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public int setMute(boolean paramBoolean) {
    try {
      this.mTuner.setMuted(paramBoolean);
      return 0;
    } catch (IllegalStateException illegalStateException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't set muted", illegalStateException);
      return Integer.MIN_VALUE;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public Map<String, String> setParameters(Map<String, String> paramMap) {
    try {
      ITuner iTuner = this.mTuner;
      Objects.requireNonNull(paramMap);
      return iTuner.setParameters(paramMap);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public boolean startBackgroundScan() {
    try {
      return this.mTuner.startBackgroundScan();
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
  
  public int step(int paramInt, boolean paramBoolean) {
    try {
      ITuner iTuner = this.mTuner;
      boolean bool = true;
      if (paramInt != 1)
        bool = false; 
      iTuner.step(bool, paramBoolean);
      return 0;
    } catch (IllegalStateException illegalStateException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't step", illegalStateException);
      return -38;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public int tune(int paramInt1, int paramInt2) {
    try {
      this.mTuner.tune(ProgramSelector.createAmFmSelector(this.mBand, paramInt1, paramInt2));
      return 0;
    } catch (IllegalStateException illegalStateException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't tune", illegalStateException);
      return -38;
    } catch (IllegalArgumentException illegalArgumentException) {
      Log.e("BroadcastRadio.TunerAdapter", "Can't tune", illegalArgumentException);
      return -22;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.TunerAdapter", "service died", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public void tune(ProgramSelector paramProgramSelector) {
    try {
      this.mTuner.tune(paramProgramSelector);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("service died", remoteException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/TunerAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */