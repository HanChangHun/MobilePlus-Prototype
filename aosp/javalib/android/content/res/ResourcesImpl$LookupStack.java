package android.content.res;

import com.android.internal.util.GrowingArrayUtils;

class LookupStack {
  private int[] mIds = new int[4];
  
  private int mSize = 0;
  
  private LookupStack() {}
  
  public boolean contains(int paramInt) {
    for (byte b = 0; b < this.mSize; b++) {
      if (this.mIds[b] == paramInt)
        return true; 
    } 
    return false;
  }
  
  public void pop() {
    this.mSize--;
  }
  
  public void push(int paramInt) {
    this.mIds = GrowingArrayUtils.append(this.mIds, this.mSize, paramInt);
    this.mSize++;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ResourcesImpl$LookupStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */