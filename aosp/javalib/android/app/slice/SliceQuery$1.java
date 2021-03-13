package android.app.slice;

import java.util.Iterator;
import java.util.Queue;

class null implements Iterator<SliceItem> {
  public boolean hasNext() {
    boolean bool;
    if (items.size() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public SliceItem next() {
    SliceItem sliceItem = items.poll();
    if (SliceQuery.compareTypes(sliceItem, "slice") || SliceQuery.compareTypes(sliceItem, "action"))
      items.addAll(sliceItem.getSlice().getItems()); 
    return sliceItem;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceQuery$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */