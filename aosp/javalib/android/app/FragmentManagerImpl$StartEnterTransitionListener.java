package android.app;

class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
  private final boolean mIsBack;
  
  private int mNumPostponed;
  
  private final BackStackRecord mRecord;
  
  public StartEnterTransitionListener(BackStackRecord paramBackStackRecord, boolean paramBoolean) {
    this.mIsBack = paramBoolean;
    this.mRecord = paramBackStackRecord;
  }
  
  public void cancelTransaction() {
    FragmentManagerImpl.access$300(this.mRecord.mManager, this.mRecord, this.mIsBack, false, false);
  }
  
  public void completeTransaction() {
    int i = this.mNumPostponed;
    boolean bool = false;
    if (i > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
    int j = fragmentManagerImpl.mAdded.size();
    for (byte b = 0; b < j; b++) {
      Fragment fragment = fragmentManagerImpl.mAdded.get(b);
      fragment.setOnStartEnterTransitionListener(null);
      if (i != 0 && fragment.isPostponed())
        fragment.startPostponedEnterTransition(); 
    } 
    fragmentManagerImpl = this.mRecord.mManager;
    BackStackRecord backStackRecord = this.mRecord;
    boolean bool1 = this.mIsBack;
    if (i == 0)
      bool = true; 
    FragmentManagerImpl.access$300(fragmentManagerImpl, backStackRecord, bool1, bool, true);
  }
  
  public boolean isReady() {
    boolean bool;
    if (this.mNumPostponed == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onStartEnterTransition() {
    int i = this.mNumPostponed - 1;
    this.mNumPostponed = i;
    if (i != 0)
      return; 
    FragmentManagerImpl.access$200(this.mRecord.mManager);
  }
  
  public void startListening() {
    this.mNumPostponed++;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerImpl$StartEnterTransitionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */