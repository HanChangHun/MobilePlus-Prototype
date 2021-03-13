package android.app.servertransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ObjectPool {
  private static final int MAX_POOL_SIZE = 50;
  
  private static final Map<Class, ArrayList<? extends ObjectPoolItem>> sPoolMap;
  
  private static final Object sPoolSync = new Object();
  
  static {
    sPoolMap = (Map)new HashMap<>();
  }
  
  public static <T extends ObjectPoolItem> T obtain(Class<T> paramClass) {
    synchronized (sPoolSync) {
      ArrayList<ObjectPoolItem> arrayList = (ArrayList)sPoolMap.get(paramClass);
      if (arrayList != null && !arrayList.isEmpty())
        return (T)arrayList.remove(arrayList.size() - 1); 
      return null;
    } 
  }
  
  public static <T extends ObjectPoolItem> void recycle(T paramT) {
    synchronized (sPoolSync) {
      IllegalStateException illegalStateException;
      ArrayList<? extends ObjectPoolItem> arrayList1 = sPoolMap.get(paramT.getClass());
      ArrayList<? extends ObjectPoolItem> arrayList2 = arrayList1;
      if (arrayList1 == null) {
        arrayList2 = new ArrayList();
        this();
        sPoolMap.put(paramT.getClass(), arrayList2);
      } 
      int i = arrayList2.size();
      byte b = 0;
      while (b < i) {
        if (arrayList2.get(b) != paramT) {
          b++;
          continue;
        } 
        illegalStateException = new IllegalStateException();
        this("Trying to recycle already recycled item");
        throw illegalStateException;
      } 
      if (i < 50)
        arrayList2.add(illegalStateException); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ObjectPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */