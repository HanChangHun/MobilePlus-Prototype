package android.app;

import android.content.Loader;
import android.os.Bundle;
import android.util.DebugUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

final class LoaderInfo implements Loader.OnLoadCompleteListener<Object>, Loader.OnLoadCanceledListener<Object> {
  final Bundle mArgs;
  
  LoaderManager.LoaderCallbacks<Object> mCallbacks;
  
  Object mData;
  
  boolean mDeliveredData;
  
  boolean mDestroyed;
  
  boolean mHaveData;
  
  final int mId;
  
  boolean mListenerRegistered;
  
  Loader<Object> mLoader;
  
  LoaderInfo mPendingLoader;
  
  boolean mReportNextStart;
  
  boolean mRetaining;
  
  boolean mRetainingStarted;
  
  boolean mStarted;
  
  public LoaderInfo(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks<Object> paramLoaderCallbacks) {
    this.mId = paramInt;
    this.mArgs = paramBundle;
    this.mCallbacks = paramLoaderCallbacks;
  }
  
  void callOnLoadFinished(Loader<Object> paramLoader, Object paramObject) {
    if (this.mCallbacks != null) {
      String str = null;
      if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null) {
        str = (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause;
        (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause = "onLoadFinished";
      } 
      try {
        if (LoaderManagerImpl.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("  onLoadFinished in ");
          stringBuilder.append(paramLoader);
          stringBuilder.append(": ");
          stringBuilder.append(paramLoader.dataToString(paramObject));
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        this.mCallbacks.onLoadFinished(paramLoader, paramObject);
        if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null)
          (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause = str; 
      } finally {
        if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null)
          (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause = str; 
      } 
    } 
  }
  
  boolean cancel() {
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  Canceling: ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (this.mStarted) {
      Loader<Object> loader = this.mLoader;
      if (loader != null && this.mListenerRegistered) {
        boolean bool = loader.cancelLoad();
        if (!bool)
          onLoadCanceled(this.mLoader); 
        return bool;
      } 
    } 
    return false;
  }
  
  void destroy() {
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  Destroying: ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    this.mDestroyed = true;
    boolean bool = this.mDeliveredData;
    this.mDeliveredData = false;
    if (this.mCallbacks != null && this.mLoader != null && this.mHaveData && bool) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Reseting: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      String str = null;
      if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null) {
        str = (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause;
        (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause = "onLoaderReset";
      } 
      try {
        this.mCallbacks.onLoaderReset(this.mLoader);
      } finally {
        if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null)
          (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.mNoTransactionsBecause = str; 
      } 
    } 
    this.mCallbacks = null;
    this.mData = null;
    this.mHaveData = false;
    Loader<Object> loader = this.mLoader;
    if (loader != null) {
      if (this.mListenerRegistered) {
        this.mListenerRegistered = false;
        loader.unregisterListener(this);
        this.mLoader.unregisterOnLoadCanceledListener(this);
      } 
      this.mLoader.reset();
    } 
    LoaderInfo loaderInfo = this.mPendingLoader;
    if (loaderInfo != null)
      loaderInfo.destroy(); 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mId=");
    paramPrintWriter.print(this.mId);
    paramPrintWriter.print(" mArgs=");
    paramPrintWriter.println(this.mArgs);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCallbacks=");
    paramPrintWriter.println(this.mCallbacks);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mLoader=");
    paramPrintWriter.println(this.mLoader);
    Loader<Object> loader = this.mLoader;
    if (loader != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      loader.dump(stringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
    if (this.mHaveData || this.mDeliveredData) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHaveData=");
      paramPrintWriter.print(this.mHaveData);
      paramPrintWriter.print("  mDeliveredData=");
      paramPrintWriter.println(this.mDeliveredData);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mData=");
      paramPrintWriter.println(this.mData);
    } 
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mStarted=");
    paramPrintWriter.print(this.mStarted);
    paramPrintWriter.print(" mReportNextStart=");
    paramPrintWriter.print(this.mReportNextStart);
    paramPrintWriter.print(" mDestroyed=");
    paramPrintWriter.println(this.mDestroyed);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetaining=");
    paramPrintWriter.print(this.mRetaining);
    paramPrintWriter.print(" mRetainingStarted=");
    paramPrintWriter.print(this.mRetainingStarted);
    paramPrintWriter.print(" mListenerRegistered=");
    paramPrintWriter.println(this.mListenerRegistered);
    if (this.mPendingLoader != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Pending Loader ");
      paramPrintWriter.print(this.mPendingLoader);
      paramPrintWriter.println(":");
      LoaderInfo loaderInfo = this.mPendingLoader;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      loaderInfo.dump(stringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  void finishRetain() {
    if (this.mRetaining) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Finished Retaining: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mRetaining = false;
      boolean bool = this.mStarted;
      if (bool != this.mRetainingStarted && !bool)
        stop(); 
    } 
    if (this.mStarted && this.mHaveData && !this.mReportNextStart)
      callOnLoadFinished(this.mLoader, this.mData); 
  }
  
  public void onLoadCanceled(Loader<Object> paramLoader) {
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onLoadCanceled: ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (this.mDestroyed) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Ignoring load canceled -- destroyed"); 
      return;
    } 
    if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Ignoring load canceled -- not active"); 
      return;
    } 
    LoaderInfo loaderInfo = this.mPendingLoader;
    if (loaderInfo != null) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Switching to pending loader: ");
        stringBuilder.append(loaderInfo);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mPendingLoader = null;
      LoaderManagerImpl.this.mLoaders.put(this.mId, null);
      destroy();
      LoaderManagerImpl.this.installLoader(loaderInfo);
    } 
  }
  
  public void onLoadComplete(Loader<Object> paramLoader, Object paramObject) {
    StringBuilder stringBuilder;
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("onLoadComplete: ");
      stringBuilder1.append(this);
      Log.v("LoaderManager", stringBuilder1.toString());
    } 
    if (this.mDestroyed) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Ignoring load complete -- destroyed"); 
      return;
    } 
    if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Ignoring load complete -- not active"); 
      return;
    } 
    LoaderInfo loaderInfo2 = this.mPendingLoader;
    if (loaderInfo2 != null) {
      if (LoaderManagerImpl.DEBUG) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("  Switching to pending loader: ");
        stringBuilder.append(loaderInfo2);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mPendingLoader = null;
      LoaderManagerImpl.this.mLoaders.put(this.mId, null);
      destroy();
      LoaderManagerImpl.this.installLoader(loaderInfo2);
      return;
    } 
    if (this.mData != paramObject || !this.mHaveData) {
      this.mData = paramObject;
      this.mHaveData = true;
      if (this.mStarted)
        callOnLoadFinished((Loader<Object>)stringBuilder, paramObject); 
    } 
    LoaderInfo loaderInfo1 = (LoaderInfo)LoaderManagerImpl.this.mInactiveLoaders.get(this.mId);
    if (loaderInfo1 != null && loaderInfo1 != this) {
      loaderInfo1.mDeliveredData = false;
      loaderInfo1.destroy();
      LoaderManagerImpl.this.mInactiveLoaders.remove(this.mId);
    } 
    if (LoaderManagerImpl.access$000(LoaderManagerImpl.this) != null && !LoaderManagerImpl.this.hasRunningLoaders())
      (LoaderManagerImpl.access$000(LoaderManagerImpl.this)).mFragmentManager.startPendingDeferredFragments(); 
  }
  
  void reportStart() {
    if (this.mStarted && this.mReportNextStart) {
      this.mReportNextStart = false;
      if (this.mHaveData && !this.mRetaining)
        callOnLoadFinished(this.mLoader, this.mData); 
    } 
  }
  
  void retain() {
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  Retaining: ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    this.mRetaining = true;
    this.mRetainingStarted = this.mStarted;
    this.mStarted = false;
    this.mCallbacks = null;
  }
  
  void start() {
    if (this.mRetaining && this.mRetainingStarted) {
      this.mStarted = true;
      return;
    } 
    if (this.mStarted)
      return; 
    this.mStarted = true;
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  Starting: ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (this.mLoader == null) {
      LoaderManager.LoaderCallbacks<Object> loaderCallbacks = this.mCallbacks;
      if (loaderCallbacks != null)
        this.mLoader = loaderCallbacks.onCreateLoader(this.mId, this.mArgs); 
    } 
    Loader<Object> loader = this.mLoader;
    if (loader != null) {
      if (!loader.getClass().isMemberClass() || Modifier.isStatic(this.mLoader.getClass().getModifiers())) {
        if (!this.mListenerRegistered) {
          this.mLoader.registerListener(this.mId, this);
          this.mLoader.registerOnLoadCanceledListener(this);
          this.mListenerRegistered = true;
        } 
        this.mLoader.startLoading();
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
      stringBuilder.append(this.mLoader);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  void stop() {
    if (LoaderManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  Stopping: ");
      stringBuilder.append(this);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    this.mStarted = false;
    if (!this.mRetaining) {
      Loader<Object> loader = this.mLoader;
      if (loader != null && this.mListenerRegistered) {
        this.mListenerRegistered = false;
        loader.unregisterListener(this);
        this.mLoader.unregisterOnLoadCanceledListener(this);
        this.mLoader.stopLoading();
      } 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(64);
    stringBuilder.append("LoaderInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" #");
    stringBuilder.append(this.mId);
    stringBuilder.append(" : ");
    DebugUtils.buildShortClassTag(this.mLoader, stringBuilder);
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoaderManagerImpl$LoaderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */