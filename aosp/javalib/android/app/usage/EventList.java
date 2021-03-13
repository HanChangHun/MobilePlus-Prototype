package android.app.usage;

import java.util.ArrayList;

public class EventList {
  private final ArrayList<UsageEvents.Event> mEvents = new ArrayList<>();
  
  public void clear() {
    this.mEvents.clear();
  }
  
  public int firstIndexOnOrAfter(long paramLong) {
    int i = this.mEvents.size();
    int j = i;
    int k;
    for (k = 0; k <= --i; k = m + 1) {
      int m = k + i >>> 1;
      if (((UsageEvents.Event)this.mEvents.get(m)).mTimeStamp >= paramLong) {
        i = m - 1;
        j = m;
        continue;
      } 
    } 
    return j;
  }
  
  public UsageEvents.Event get(int paramInt) {
    return this.mEvents.get(paramInt);
  }
  
  public void insert(UsageEvents.Event paramEvent) {
    int i = this.mEvents.size();
    if (i == 0 || paramEvent.mTimeStamp >= ((UsageEvents.Event)this.mEvents.get(i - 1)).mTimeStamp) {
      this.mEvents.add(paramEvent);
      return;
    } 
    i = firstIndexOnOrAfter(paramEvent.mTimeStamp + 1L);
    this.mEvents.add(i, paramEvent);
  }
  
  public void merge(EventList paramEventList) {
    int i = paramEventList.size();
    for (byte b = 0; b < i; b++)
      insert(paramEventList.get(b)); 
  }
  
  public UsageEvents.Event remove(int paramInt) {
    try {
      return this.mEvents.remove(paramInt);
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      return null;
    } 
  }
  
  public int size() {
    return this.mEvents.size();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/EventList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */