package android.app.usage;

import android.util.LongSparseArray;
import android.util.Slog;

public class TimeSparseArray<E> extends LongSparseArray<E> {
  private static final String TAG = TimeSparseArray.class.getSimpleName();
  
  private boolean mWtfReported;
  
  public int closestIndexOnOrAfter(long paramLong) {
    int i = size();
    int j = 0;
    int k = i - 1;
    int m = -1;
    long l = -1L;
    while (j <= k) {
      m = j + (k - j) / 2;
      l = keyAt(m);
      if (paramLong > l) {
        j = m + 1;
        continue;
      } 
      if (paramLong < l) {
        k = m - 1;
        continue;
      } 
      return m;
    } 
    return (paramLong < l) ? m : ((paramLong > l && j < i) ? j : -1);
  }
  
  public int closestIndexOnOrBefore(long paramLong) {
    int i = closestIndexOnOrAfter(paramLong);
    return (i < 0) ? (size() - 1) : ((keyAt(i) == paramLong) ? i : (i - 1));
  }
  
  public void put(long paramLong, E paramE) {
    if (indexOfKey(paramLong) >= 0 && !this.mWtfReported) {
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Overwriting value ");
      stringBuilder.append(get(paramLong));
      stringBuilder.append(" by ");
      stringBuilder.append(paramE);
      Slog.wtf(str, stringBuilder.toString());
      this.mWtfReported = true;
    } 
    super.put(paramLong, paramE);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/TimeSparseArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */