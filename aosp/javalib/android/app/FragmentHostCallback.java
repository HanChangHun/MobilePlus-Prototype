package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@Deprecated
public abstract class FragmentHostCallback<E> extends FragmentContainer {
  private final Activity mActivity;
  
  private ArrayMap<String, LoaderManager> mAllLoaderManagers;
  
  private boolean mCheckedForLoaderManager;
  
  final Context mContext;
  
  final FragmentManagerImpl mFragmentManager = new FragmentManagerImpl();
  
  private final Handler mHandler;
  
  private LoaderManagerImpl mLoaderManager;
  
  private boolean mLoadersStarted;
  
  private boolean mRetainLoaders;
  
  final int mWindowAnimations;
  
  FragmentHostCallback(Activity paramActivity) {
    this(paramActivity, (Context)paramActivity, paramActivity.mHandler, 0);
  }
  
  FragmentHostCallback(Activity paramActivity, Context paramContext, Handler paramHandler, int paramInt) {
    this.mActivity = paramActivity;
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mWindowAnimations = paramInt;
  }
  
  public FragmentHostCallback(Context paramContext, Handler paramHandler, int paramInt) {
    this(activity, paramContext, chooseHandler(paramContext, paramHandler), paramInt);
  }
  
  private static Handler chooseHandler(Context paramContext, Handler paramHandler) {
    return (paramHandler == null && paramContext instanceof Activity) ? ((Activity)paramContext).mHandler : paramHandler;
  }
  
  void doLoaderDestroy() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl == null)
      return; 
    loaderManagerImpl.doDestroy();
  }
  
  void doLoaderRetain() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl == null)
      return; 
    loaderManagerImpl.doRetain();
  }
  
  void doLoaderStart() {
    if (this.mLoadersStarted)
      return; 
    this.mLoadersStarted = true;
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl != null) {
      loaderManagerImpl.doStart();
    } else if (!this.mCheckedForLoaderManager) {
      this.mLoaderManager = getLoaderManager("(root)", true, false);
    } 
    this.mCheckedForLoaderManager = true;
  }
  
  void doLoaderStop(boolean paramBoolean) {
    this.mRetainLoaders = paramBoolean;
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl == null)
      return; 
    if (!this.mLoadersStarted)
      return; 
    this.mLoadersStarted = false;
    if (paramBoolean) {
      loaderManagerImpl.doRetain();
    } else {
      loaderManagerImpl.doStop();
    } 
  }
  
  void dumpLoaders(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mLoadersStarted=");
    paramPrintWriter.println(this.mLoadersStarted);
    if (this.mLoaderManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("Loader Manager ");
      paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
      paramPrintWriter.println(":");
      LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      loaderManagerImpl.dump(stringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  Activity getActivity() {
    return this.mActivity;
  }
  
  Context getContext() {
    return this.mContext;
  }
  
  FragmentManagerImpl getFragmentManagerImpl() {
    return this.mFragmentManager;
  }
  
  Handler getHandler() {
    return this.mHandler;
  }
  
  LoaderManagerImpl getLoaderManager(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    LoaderManagerImpl loaderManagerImpl1;
    if (this.mAllLoaderManagers == null)
      this.mAllLoaderManagers = new ArrayMap(); 
    LoaderManagerImpl loaderManagerImpl2 = (LoaderManagerImpl)this.mAllLoaderManagers.get(paramString);
    if (loaderManagerImpl2 == null && paramBoolean2) {
      loaderManagerImpl2 = new LoaderManagerImpl(paramString, this, paramBoolean1);
      this.mAllLoaderManagers.put(paramString, loaderManagerImpl2);
      loaderManagerImpl1 = loaderManagerImpl2;
    } else {
      loaderManagerImpl1 = loaderManagerImpl2;
      if (paramBoolean1) {
        loaderManagerImpl1 = loaderManagerImpl2;
        if (loaderManagerImpl2 != null) {
          loaderManagerImpl1 = loaderManagerImpl2;
          if (!loaderManagerImpl2.mStarted) {
            loaderManagerImpl2.doStart();
            loaderManagerImpl1 = loaderManagerImpl2;
          } 
        } 
      } 
    } 
    return loaderManagerImpl1;
  }
  
  LoaderManagerImpl getLoaderManagerImpl() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl != null)
      return loaderManagerImpl; 
    this.mCheckedForLoaderManager = true;
    loaderManagerImpl = getLoaderManager("(root)", this.mLoadersStarted, true);
    this.mLoaderManager = loaderManagerImpl;
    return loaderManagerImpl;
  }
  
  boolean getRetainLoaders() {
    return this.mRetainLoaders;
  }
  
  void inactivateFragment(String paramString) {
    ArrayMap<String, LoaderManager> arrayMap = this.mAllLoaderManagers;
    if (arrayMap != null) {
      LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl)arrayMap.get(paramString);
      if (loaderManagerImpl != null && !loaderManagerImpl.mRetaining) {
        loaderManagerImpl.doDestroy();
        this.mAllLoaderManagers.remove(paramString);
      } 
    } 
  }
  
  public void onAttachFragment(Fragment paramFragment) {}
  
  public void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public <T extends android.view.View> T onFindViewById(int paramInt) {
    return null;
  }
  
  public abstract E onGetHost();
  
  public LayoutInflater onGetLayoutInflater() {
    return (LayoutInflater)this.mContext.getSystemService("layout_inflater");
  }
  
  public int onGetWindowAnimations() {
    return this.mWindowAnimations;
  }
  
  public boolean onHasView() {
    return true;
  }
  
  public boolean onHasWindowAnimations() {
    return true;
  }
  
  public void onInvalidateOptionsMenu() {}
  
  public void onRequestPermissionsFromFragment(Fragment paramFragment, String[] paramArrayOfString, int paramInt) {}
  
  public boolean onShouldSaveFragmentState(Fragment paramFragment) {
    return true;
  }
  
  public void onStartActivityAsUserFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    if (paramInt == -1) {
      this.mContext.startActivityAsUser(paramIntent, paramUserHandle);
      return;
    } 
    throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
  }
  
  public void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle) {
    if (paramInt == -1) {
      this.mContext.startActivity(paramIntent);
      return;
    } 
    throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
  }
  
  public void onStartIntentSenderFromFragment(Fragment paramFragment, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    if (paramInt1 == -1) {
      this.mContext.startIntentSender(paramIntentSender, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
      return;
    } 
    throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
  }
  
  public boolean onUseFragmentManagerInflaterFactory() {
    return false;
  }
  
  void reportLoaderStart() {
    ArrayMap<String, LoaderManager> arrayMap = this.mAllLoaderManagers;
    if (arrayMap != null) {
      int i = arrayMap.size();
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[i];
      int j;
      for (j = i - 1; j >= 0; j--)
        arrayOfLoaderManagerImpl[j] = (LoaderManagerImpl)this.mAllLoaderManagers.valueAt(j); 
      for (j = 0; j < i; j++) {
        LoaderManagerImpl loaderManagerImpl = arrayOfLoaderManagerImpl[j];
        loaderManagerImpl.finishRetain();
        loaderManagerImpl.doReportStart();
      } 
    } 
  }
  
  void restoreLoaderNonConfig(ArrayMap<String, LoaderManager> paramArrayMap) {
    if (paramArrayMap != null) {
      byte b = 0;
      int i = paramArrayMap.size();
      while (b < i) {
        ((LoaderManagerImpl)paramArrayMap.valueAt(b)).updateHostController(this);
        b++;
      } 
    } 
    this.mAllLoaderManagers = paramArrayMap;
  }
  
  ArrayMap<String, LoaderManager> retainLoaderNonConfig() {
    int i = 0;
    boolean bool = false;
    ArrayMap<String, LoaderManager> arrayMap = this.mAllLoaderManagers;
    if (arrayMap != null) {
      int j = arrayMap.size();
      LoaderManagerImpl[] arrayOfLoaderManagerImpl = new LoaderManagerImpl[j];
      int k;
      for (k = j - 1; k >= 0; k--)
        arrayOfLoaderManagerImpl[k] = (LoaderManagerImpl)this.mAllLoaderManagers.valueAt(k); 
      boolean bool1 = getRetainLoaders();
      byte b = 0;
      k = bool;
      while (true) {
        i = k;
        if (b < j) {
          LoaderManagerImpl loaderManagerImpl = arrayOfLoaderManagerImpl[b];
          if (!loaderManagerImpl.mRetaining && bool1) {
            if (!loaderManagerImpl.mStarted)
              loaderManagerImpl.doStart(); 
            loaderManagerImpl.doRetain();
          } 
          if (loaderManagerImpl.mRetaining) {
            k = 1;
          } else {
            loaderManagerImpl.doDestroy();
            this.mAllLoaderManagers.remove(loaderManagerImpl.mWho);
          } 
          b++;
          continue;
        } 
        break;
      } 
    } 
    return (i != 0) ? this.mAllLoaderManagers : null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentHostCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */