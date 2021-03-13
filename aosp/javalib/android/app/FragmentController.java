package android.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

@Deprecated
public class FragmentController {
  private final FragmentHostCallback<?> mHost;
  
  private FragmentController(FragmentHostCallback<?> paramFragmentHostCallback) {
    this.mHost = paramFragmentHostCallback;
  }
  
  public static final FragmentController createController(FragmentHostCallback<?> paramFragmentHostCallback) {
    return new FragmentController(paramFragmentHostCallback);
  }
  
  public void attachHost(Fragment paramFragment) {
    FragmentManagerImpl fragmentManagerImpl = this.mHost.mFragmentManager;
    FragmentHostCallback<?> fragmentHostCallback = this.mHost;
    fragmentManagerImpl.attachController(fragmentHostCallback, fragmentHostCallback, paramFragment);
  }
  
  public void dispatchActivityCreated() {
    this.mHost.mFragmentManager.dispatchActivityCreated();
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration) {
    this.mHost.mFragmentManager.dispatchConfigurationChanged(paramConfiguration);
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem) {
    return this.mHost.mFragmentManager.dispatchContextItemSelected(paramMenuItem);
  }
  
  public void dispatchCreate() {
    this.mHost.mFragmentManager.dispatchCreate();
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    return this.mHost.mFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public void dispatchDestroy() {
    this.mHost.mFragmentManager.dispatchDestroy();
  }
  
  public void dispatchDestroyView() {
    this.mHost.mFragmentManager.dispatchDestroyView();
  }
  
  public void dispatchLowMemory() {
    this.mHost.mFragmentManager.dispatchLowMemory();
  }
  
  @Deprecated
  public void dispatchMultiWindowModeChanged(boolean paramBoolean) {
    this.mHost.mFragmentManager.dispatchMultiWindowModeChanged(paramBoolean);
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    this.mHost.mFragmentManager.dispatchMultiWindowModeChanged(paramBoolean, paramConfiguration);
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem) {
    return this.mHost.mFragmentManager.dispatchOptionsItemSelected(paramMenuItem);
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu) {
    this.mHost.mFragmentManager.dispatchOptionsMenuClosed(paramMenu);
  }
  
  public void dispatchPause() {
    this.mHost.mFragmentManager.dispatchPause();
  }
  
  @Deprecated
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean) {
    this.mHost.mFragmentManager.dispatchPictureInPictureModeChanged(paramBoolean);
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    this.mHost.mFragmentManager.dispatchPictureInPictureModeChanged(paramBoolean, paramConfiguration);
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu) {
    return this.mHost.mFragmentManager.dispatchPrepareOptionsMenu(paramMenu);
  }
  
  public void dispatchResume() {
    this.mHost.mFragmentManager.dispatchResume();
  }
  
  public void dispatchStart() {
    this.mHost.mFragmentManager.dispatchStart();
  }
  
  public void dispatchStop() {
    this.mHost.mFragmentManager.dispatchStop();
  }
  
  public void dispatchTrimMemory(int paramInt) {
    this.mHost.mFragmentManager.dispatchTrimMemory(paramInt);
  }
  
  public void doLoaderDestroy() {
    this.mHost.doLoaderDestroy();
  }
  
  public void doLoaderStart() {
    this.mHost.doLoaderStart();
  }
  
  public void doLoaderStop(boolean paramBoolean) {
    this.mHost.doLoaderStop(paramBoolean);
  }
  
  public void dumpLoaders(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    this.mHost.dumpLoaders(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public boolean execPendingActions() {
    return this.mHost.mFragmentManager.execPendingActions();
  }
  
  public Fragment findFragmentByWho(String paramString) {
    return this.mHost.mFragmentManager.findFragmentByWho(paramString);
  }
  
  public FragmentManager getFragmentManager() {
    return this.mHost.getFragmentManagerImpl();
  }
  
  public LoaderManager getLoaderManager() {
    return this.mHost.getLoaderManagerImpl();
  }
  
  public void noteStateNotSaved() {
    this.mHost.mFragmentManager.noteStateNotSaved();
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return this.mHost.mFragmentManager.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public void reportLoaderStart() {
    this.mHost.reportLoaderStart();
  }
  
  public void restoreAllState(Parcelable paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    this.mHost.mFragmentManager.restoreAllState(paramParcelable, paramFragmentManagerNonConfig);
  }
  
  @Deprecated
  public void restoreAllState(Parcelable paramParcelable, List<Fragment> paramList) {
    this.mHost.mFragmentManager.restoreAllState(paramParcelable, new FragmentManagerNonConfig(paramList, null));
  }
  
  public void restoreLoaderNonConfig(ArrayMap<String, LoaderManager> paramArrayMap) {
    this.mHost.restoreLoaderNonConfig(paramArrayMap);
  }
  
  public ArrayMap<String, LoaderManager> retainLoaderNonConfig() {
    return this.mHost.retainLoaderNonConfig();
  }
  
  public FragmentManagerNonConfig retainNestedNonConfig() {
    return this.mHost.mFragmentManager.retainNonConfig();
  }
  
  @Deprecated
  public List<Fragment> retainNonConfig() {
    return this.mHost.mFragmentManager.retainNonConfig().getFragments();
  }
  
  public Parcelable saveAllState() {
    return this.mHost.mFragmentManager.saveAllState();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */