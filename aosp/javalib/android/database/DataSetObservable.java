package android.database;

public class DataSetObservable extends Observable<DataSetObserver> {
  public void notifyChanged() {
    synchronized (this.mObservers) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((DataSetObserver)this.mObservers.get(i)).onChanged(); 
      return;
    } 
  }
  
  public void notifyInvalidated() {
    synchronized (this.mObservers) {
      for (int i = this.mObservers.size() - 1; i >= 0; i--)
        ((DataSetObserver)this.mObservers.get(i)).onInvalidated(); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/DataSetObservable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */