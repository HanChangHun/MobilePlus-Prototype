package android.app;

final class ProviderRefCount {
  public final ActivityThread.ProviderClientRecord client;
  
  public final ContentProviderHolder holder;
  
  public boolean removePending;
  
  public int stableCount;
  
  public int unstableCount;
  
  ProviderRefCount(ContentProviderHolder paramContentProviderHolder, ActivityThread.ProviderClientRecord paramProviderClientRecord, int paramInt1, int paramInt2) {
    this.holder = paramContentProviderHolder;
    this.client = paramProviderClientRecord;
    this.stableCount = paramInt1;
    this.unstableCount = paramInt2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ProviderRefCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */