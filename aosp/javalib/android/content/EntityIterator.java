package android.content;

import java.util.Iterator;

public interface EntityIterator extends Iterator<Entity> {
  void close();
  
  void reset();
}


/* Location:              /home/chun/Desktop/temp/!/android/content/EntityIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */