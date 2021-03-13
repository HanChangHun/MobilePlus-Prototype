package android.database;

class null extends DataSetObserver {
  public void onChanged() {
    MergeCursor.this.mPos = -1;
  }
  
  public void onInvalidated() {
    MergeCursor.this.mPos = -1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/MergeCursor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */