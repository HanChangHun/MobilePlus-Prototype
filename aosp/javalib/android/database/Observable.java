package android.database;

import java.util.ArrayList;

public abstract class Observable<T> {
  protected final ArrayList<T> mObservers = new ArrayList<>();
  
  public void registerObserver(T paramT) {
    if (paramT != null)
      synchronized (this.mObservers) {
        if (!this.mObservers.contains(paramT)) {
          this.mObservers.add(paramT);
          return;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Observer ");
        stringBuilder.append(paramT);
        stringBuilder.append(" is already registered.");
        this(stringBuilder.toString());
        throw illegalStateException;
      }  
    throw new IllegalArgumentException("The observer is null.");
  }
  
  public void unregisterAll() {
    synchronized (this.mObservers) {
      this.mObservers.clear();
      return;
    } 
  }
  
  public void unregisterObserver(T paramT) {
    if (paramT != null)
      synchronized (this.mObservers) {
        int i = this.mObservers.indexOf(paramT);
        if (i != -1) {
          this.mObservers.remove(i);
          return;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Observer ");
        stringBuilder.append(paramT);
        stringBuilder.append(" was not registered.");
        this(stringBuilder.toString());
        throw illegalStateException;
      }  
    throw new IllegalArgumentException("The observer is null.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/Observable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */