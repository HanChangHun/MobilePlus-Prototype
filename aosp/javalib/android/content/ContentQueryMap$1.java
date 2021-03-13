package android.content;

import android.database.ContentObserver;
import android.os.Handler;

class null extends ContentObserver {
  null(Handler paramHandler) {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean) {
    if (ContentQueryMap.this.countObservers() != 0) {
      ContentQueryMap.this.requery();
    } else {
      ContentQueryMap.access$002(ContentQueryMap.this, true);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentQueryMap$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */