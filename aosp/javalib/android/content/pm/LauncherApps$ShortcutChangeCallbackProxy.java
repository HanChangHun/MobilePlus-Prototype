package android.content.pm;

import android.os.UserHandle;
import android.util.Pair;
import com.android.internal.util.function.QuadConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Executor;

class ShortcutChangeCallbackProxy extends IShortcutChangeCallback.Stub {
  private final WeakReference<Pair<Executor, LauncherApps.ShortcutChangeCallback>> mRemoteReferences;
  
  ShortcutChangeCallbackProxy(Executor paramExecutor, LauncherApps.ShortcutChangeCallback paramShortcutChangeCallback) {
    this.mRemoteReferences = new WeakReference<>(new Pair(paramExecutor, paramShortcutChangeCallback));
  }
  
  public void onShortcutsAddedOrUpdated(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) {
    Pair pair = this.mRemoteReferences.get();
    if (pair == null)
      return; 
    Executor executor = (Executor)pair.first;
    LauncherApps.ShortcutChangeCallback shortcutChangeCallback = (LauncherApps.ShortcutChangeCallback)pair.second;
    executor.execute((Runnable)PooledLambda.obtainRunnable((QuadConsumer)_$$Lambda$ixVkOBzuX9ZQHchageObICICvzs.INSTANCE, shortcutChangeCallback, paramString, paramList, paramUserHandle).recycleOnUse());
  }
  
  public void onShortcutsRemoved(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) {
    Pair pair = this.mRemoteReferences.get();
    if (pair == null)
      return; 
    Executor executor = (Executor)pair.first;
    LauncherApps.ShortcutChangeCallback shortcutChangeCallback = (LauncherApps.ShortcutChangeCallback)pair.second;
    executor.execute((Runnable)PooledLambda.obtainRunnable((QuadConsumer)_$$Lambda$us1_9lD180TeidUshXKquRrDKoU.INSTANCE, shortcutChangeCallback, paramString, paramList, paramUserHandle).recycleOnUse());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$ShortcutChangeCallbackProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */