package android.app;

class null extends IProcessObserver.Stub {
  public void onForegroundActivitiesChanged(int paramInt1, int paramInt2, boolean paramBoolean) {
    paramBoolean = HomeVisibilityObserver.access$000(HomeVisibilityObserver.this);
    if (HomeVisibilityObserver.this.mIsHomeActivityVisible != paramBoolean) {
      HomeVisibilityObserver.this.mIsHomeActivityVisible = paramBoolean;
      HomeVisibilityObserver homeVisibilityObserver = HomeVisibilityObserver.this;
      homeVisibilityObserver.onHomeVisibilityChanged(homeVisibilityObserver.mIsHomeActivityVisible);
    } 
  }
  
  public void onForegroundServicesChanged(int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onProcessDied(int paramInt1, int paramInt2) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/HomeVisibilityObserver$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */