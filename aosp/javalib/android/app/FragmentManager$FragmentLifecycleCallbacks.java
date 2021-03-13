package android.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

@Deprecated
public abstract class FragmentLifecycleCallbacks {
  public void onFragmentActivityCreated(FragmentManager paramFragmentManager, Fragment paramFragment, Bundle paramBundle) {}
  
  public void onFragmentAttached(FragmentManager paramFragmentManager, Fragment paramFragment, Context paramContext) {}
  
  public void onFragmentCreated(FragmentManager paramFragmentManager, Fragment paramFragment, Bundle paramBundle) {}
  
  public void onFragmentDestroyed(FragmentManager paramFragmentManager, Fragment paramFragment) {}
  
  public void onFragmentDetached(FragmentManager paramFragmentManager, Fragment paramFragment) {}
  
  public void onFragmentPaused(FragmentManager paramFragmentManager, Fragment paramFragment) {}
  
  public void onFragmentPreAttached(FragmentManager paramFragmentManager, Fragment paramFragment, Context paramContext) {}
  
  public void onFragmentPreCreated(FragmentManager paramFragmentManager, Fragment paramFragment, Bundle paramBundle) {}
  
  public void onFragmentResumed(FragmentManager paramFragmentManager, Fragment paramFragment) {}
  
  public void onFragmentSaveInstanceState(FragmentManager paramFragmentManager, Fragment paramFragment, Bundle paramBundle) {}
  
  public void onFragmentStarted(FragmentManager paramFragmentManager, Fragment paramFragment) {}
  
  public void onFragmentStopped(FragmentManager paramFragmentManager, Fragment paramFragment) {}
  
  public void onFragmentViewCreated(FragmentManager paramFragmentManager, Fragment paramFragment, View paramView, Bundle paramBundle) {}
  
  public void onFragmentViewDestroyed(FragmentManager paramFragmentManager, Fragment paramFragment) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManager$FragmentLifecycleCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */