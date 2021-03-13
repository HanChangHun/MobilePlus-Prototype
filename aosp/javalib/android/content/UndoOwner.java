package android.content;

public class UndoOwner {
  Object mData;
  
  final UndoManager mManager;
  
  int mOpCount;
  
  int mSavedIdx;
  
  int mStateSeq;
  
  final String mTag;
  
  UndoOwner(String paramString, UndoManager paramUndoManager) {
    if (paramString != null) {
      if (paramUndoManager != null) {
        this.mTag = paramString;
        this.mManager = paramUndoManager;
        return;
      } 
      throw new NullPointerException("manager can't be null");
    } 
    throw new NullPointerException("tag can't be null");
  }
  
  public Object getData() {
    return this.mData;
  }
  
  public String getTag() {
    return this.mTag;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UndoOwner:[mTag=");
    stringBuilder.append(this.mTag);
    stringBuilder.append(" mManager=");
    stringBuilder.append(this.mManager);
    stringBuilder.append(" mData=");
    stringBuilder.append(this.mData);
    stringBuilder.append(" mData=");
    stringBuilder.append(this.mData);
    stringBuilder.append(" mOpCount=");
    stringBuilder.append(this.mOpCount);
    stringBuilder.append(" mStateSeq=");
    stringBuilder.append(this.mStateSeq);
    stringBuilder.append(" mSavedIdx=");
    stringBuilder.append(this.mSavedIdx);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/UndoOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */