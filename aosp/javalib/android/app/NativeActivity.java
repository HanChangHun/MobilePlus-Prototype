package android.app;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.AttributeSet;
import android.view.InputQueue;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import dalvik.system.BaseDexClassLoader;
import java.io.File;

public class NativeActivity extends Activity implements SurfaceHolder.Callback2, InputQueue.Callback, ViewTreeObserver.OnGlobalLayoutListener {
  private static final String KEY_NATIVE_SAVED_STATE = "android:native_state";
  
  public static final String META_DATA_FUNC_NAME = "android.app.func_name";
  
  public static final String META_DATA_LIB_NAME = "android.app.lib_name";
  
  private InputQueue mCurInputQueue;
  
  private SurfaceHolder mCurSurfaceHolder;
  
  private boolean mDestroyed;
  
  private boolean mDispatchingUnhandledKey;
  
  private InputMethodManager mIMM;
  
  int mLastContentHeight;
  
  int mLastContentWidth;
  
  int mLastContentX;
  
  int mLastContentY;
  
  final int[] mLocation = new int[2];
  
  private NativeContentView mNativeContentView;
  
  private long mNativeHandle;
  
  private static String getAbsolutePath(File paramFile) {
    if (paramFile != null) {
      String str = paramFile.getAbsolutePath();
    } else {
      paramFile = null;
    } 
    return (String)paramFile;
  }
  
  private native String getDlError();
  
  private native long loadNativeCode(String paramString1, String paramString2, MessageQueue paramMessageQueue, String paramString3, String paramString4, String paramString5, int paramInt, AssetManager paramAssetManager, byte[] paramArrayOfbyte, ClassLoader paramClassLoader, String paramString6);
  
  private native void onConfigurationChangedNative(long paramLong);
  
  private native void onContentRectChangedNative(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  private native void onInputQueueCreatedNative(long paramLong1, long paramLong2);
  
  private native void onInputQueueDestroyedNative(long paramLong1, long paramLong2);
  
  private native void onLowMemoryNative(long paramLong);
  
  private native void onPauseNative(long paramLong);
  
  private native void onResumeNative(long paramLong);
  
  private native byte[] onSaveInstanceStateNative(long paramLong);
  
  private native void onStartNative(long paramLong);
  
  private native void onStopNative(long paramLong);
  
  private native void onSurfaceChangedNative(long paramLong, Surface paramSurface, int paramInt1, int paramInt2, int paramInt3);
  
  private native void onSurfaceCreatedNative(long paramLong, Surface paramSurface);
  
  private native void onSurfaceDestroyedNative(long paramLong);
  
  private native void onSurfaceRedrawNeededNative(long paramLong, Surface paramSurface);
  
  private native void onWindowFocusChangedNative(long paramLong, boolean paramBoolean);
  
  private native void unloadNativeCode(long paramLong);
  
  void hideIme(int paramInt) {
    this.mIMM.hideSoftInputFromWindow(this.mNativeContentView.getWindowToken(), paramInt);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (!this.mDestroyed)
      onConfigurationChangedNative(this.mNativeHandle); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    String str1 = "main";
    String str2 = "ANativeActivity_onCreate";
    this.mIMM = (InputMethodManager)getSystemService(InputMethodManager.class);
    getWindow().takeSurface(this);
    getWindow().takeInputQueue(this);
    getWindow().setFormat(4);
    getWindow().setSoftInputMode(16);
    NativeContentView nativeContentView = new NativeContentView((Context)this);
    this.mNativeContentView = nativeContentView;
    nativeContentView.mActivity = this;
    setContentView(this.mNativeContentView);
    this.mNativeContentView.requestFocus();
    this.mNativeContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    try {
      ActivityInfo activityInfo = getPackageManager().getActivityInfo(getIntent().getComponent(), 128);
      if (activityInfo.metaData != null) {
        String str3 = activityInfo.metaData.getString("android.app.lib_name");
        if (str3 != null)
          str1 = str3; 
        str3 = activityInfo.metaData.getString("android.app.func_name");
        if (str3 != null)
          str2 = str3; 
        str3 = str1;
        str1 = str2;
        str2 = str3;
      } else {
        str2 = "main";
        str1 = "ANativeActivity_onCreate";
      } 
      BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader)getClassLoader();
      String str = baseDexClassLoader.findLibrary(str2);
      if (str != null) {
        if (paramBundle != null) {
          byte[] arrayOfByte = paramBundle.getByteArray("android:native_state");
        } else {
          str2 = null;
        } 
        long l = loadNativeCode(str, str1, Looper.myQueue(), getAbsolutePath(getFilesDir()), getAbsolutePath(getObbDir()), getAbsolutePath(getExternalFilesDir(null)), Build.VERSION.SDK_INT, getAssets(), (byte[])str2, (ClassLoader)baseDexClassLoader, baseDexClassLoader.getLdLibraryPath());
        this.mNativeHandle = l;
        if (l != 0L) {
          super.onCreate(paramBundle);
          return;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Unable to load native library \"");
        stringBuilder1.append(str);
        stringBuilder1.append("\": ");
        stringBuilder1.append(getDlError());
        throw new UnsatisfiedLinkError(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to find native library ");
      stringBuilder.append(str2);
      stringBuilder.append(" using classloader: ");
      stringBuilder.append(baseDexClassLoader.toString());
      throw new IllegalArgumentException(stringBuilder.toString());
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new RuntimeException("Error getting activity info", nameNotFoundException);
    } 
  }
  
  protected void onDestroy() {
    this.mDestroyed = true;
    if (this.mCurSurfaceHolder != null) {
      onSurfaceDestroyedNative(this.mNativeHandle);
      this.mCurSurfaceHolder = null;
    } 
    InputQueue inputQueue = this.mCurInputQueue;
    if (inputQueue != null) {
      onInputQueueDestroyedNative(this.mNativeHandle, inputQueue.getNativePtr());
      this.mCurInputQueue = null;
    } 
    unloadNativeCode(this.mNativeHandle);
    super.onDestroy();
  }
  
  public void onGlobalLayout() {
    this.mNativeContentView.getLocationInWindow(this.mLocation);
    int i = this.mNativeContentView.getWidth();
    int j = this.mNativeContentView.getHeight();
    int[] arrayOfInt = this.mLocation;
    if (arrayOfInt[0] != this.mLastContentX || arrayOfInt[1] != this.mLastContentY || i != this.mLastContentWidth || j != this.mLastContentHeight) {
      arrayOfInt = this.mLocation;
      int k = arrayOfInt[0];
      this.mLastContentX = k;
      int m = arrayOfInt[1];
      this.mLastContentY = m;
      this.mLastContentWidth = i;
      this.mLastContentHeight = j;
      if (!this.mDestroyed)
        onContentRectChangedNative(this.mNativeHandle, k, m, i, j); 
    } 
  }
  
  public void onInputQueueCreated(InputQueue paramInputQueue) {
    if (!this.mDestroyed) {
      this.mCurInputQueue = paramInputQueue;
      onInputQueueCreatedNative(this.mNativeHandle, paramInputQueue.getNativePtr());
    } 
  }
  
  public void onInputQueueDestroyed(InputQueue paramInputQueue) {
    if (!this.mDestroyed) {
      onInputQueueDestroyedNative(this.mNativeHandle, paramInputQueue.getNativePtr());
      this.mCurInputQueue = null;
    } 
  }
  
  public void onLowMemory() {
    super.onLowMemory();
    if (!this.mDestroyed)
      onLowMemoryNative(this.mNativeHandle); 
  }
  
  protected void onPause() {
    super.onPause();
    onPauseNative(this.mNativeHandle);
  }
  
  protected void onResume() {
    super.onResume();
    onResumeNative(this.mNativeHandle);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    byte[] arrayOfByte = onSaveInstanceStateNative(this.mNativeHandle);
    if (arrayOfByte != null)
      paramBundle.putByteArray("android:native_state", arrayOfByte); 
  }
  
  protected void onStart() {
    super.onStart();
    onStartNative(this.mNativeHandle);
  }
  
  protected void onStop() {
    super.onStop();
    onStopNative(this.mNativeHandle);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {
    super.onWindowFocusChanged(paramBoolean);
    if (!this.mDestroyed)
      onWindowFocusChangedNative(this.mNativeHandle, paramBoolean); 
  }
  
  void setWindowFlags(int paramInt1, int paramInt2) {
    getWindow().setFlags(paramInt1, paramInt2);
  }
  
  void setWindowFormat(int paramInt) {
    getWindow().setFormat(paramInt);
  }
  
  void showIme(int paramInt) {
    this.mIMM.showSoftInput(this.mNativeContentView, paramInt);
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {
    if (!this.mDestroyed) {
      this.mCurSurfaceHolder = paramSurfaceHolder;
      onSurfaceChangedNative(this.mNativeHandle, paramSurfaceHolder.getSurface(), paramInt1, paramInt2, paramInt3);
    } 
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
    if (!this.mDestroyed) {
      this.mCurSurfaceHolder = paramSurfaceHolder;
      onSurfaceCreatedNative(this.mNativeHandle, paramSurfaceHolder.getSurface());
    } 
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
    this.mCurSurfaceHolder = null;
    if (!this.mDestroyed)
      onSurfaceDestroyedNative(this.mNativeHandle); 
  }
  
  public void surfaceRedrawNeeded(SurfaceHolder paramSurfaceHolder) {
    if (!this.mDestroyed) {
      this.mCurSurfaceHolder = paramSurfaceHolder;
      onSurfaceRedrawNeededNative(this.mNativeHandle, paramSurfaceHolder.getSurface());
    } 
  }
  
  static class NativeContentView extends View {
    NativeActivity mActivity;
    
    public NativeContentView(Context param1Context) {
      super(param1Context);
    }
    
    public NativeContentView(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NativeActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */