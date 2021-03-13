package android.content;

import android.os.Parcel;
import android.text.TextUtils;
import java.util.ArrayList;

final class UndoState {
  private boolean mCanMerge;
  
  private final int mCommitId;
  
  private boolean mExecuted;
  
  private CharSequence mLabel;
  
  private final UndoManager mManager;
  
  private final ArrayList<UndoOperation<?>> mOperations;
  
  private ArrayList<UndoOperation<?>> mRecent;
  
  UndoState(UndoManager paramUndoManager, int paramInt) {
    this.mOperations = new ArrayList<>();
    this.mCanMerge = true;
    this.mManager = paramUndoManager;
    this.mCommitId = paramInt;
  }
  
  UndoState(UndoManager paramUndoManager, Parcel paramParcel, ClassLoader paramClassLoader) {
    boolean bool2;
    this.mOperations = new ArrayList<>();
    boolean bool1 = true;
    this.mCanMerge = true;
    this.mManager = paramUndoManager;
    this.mCommitId = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mCanMerge = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mExecuted = bool2;
    this.mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    int i = paramParcel.readInt();
    for (byte b = 0; b < i; b++) {
      UndoOwner undoOwner = this.mManager.restoreOwner(paramParcel);
      UndoOperation<?> undoOperation = (UndoOperation)paramParcel.readParcelable(paramClassLoader);
      undoOperation.mOwner = undoOwner;
      this.mOperations.add(undoOperation);
    } 
  }
  
  void addOperation(UndoOperation<?> paramUndoOperation) {
    UndoOwner undoOwner;
    if (!this.mOperations.contains(paramUndoOperation)) {
      this.mOperations.add(paramUndoOperation);
      if (this.mRecent == null) {
        ArrayList<UndoOperation<?>> arrayList = new ArrayList();
        this.mRecent = arrayList;
        arrayList.add(paramUndoOperation);
      } 
      undoOwner = paramUndoOperation.mOwner;
      undoOwner.mOpCount++;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Already holds ");
    stringBuilder.append(undoOwner);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  boolean canMerge() {
    boolean bool;
    if (this.mCanMerge && !this.mExecuted) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void commit() {
    byte b1;
    ArrayList<UndoOperation<?>> arrayList = this.mRecent;
    if (arrayList != null) {
      b1 = arrayList.size();
    } else {
      b1 = 0;
    } 
    for (byte b2 = 0; b2 < b1; b2++)
      ((UndoOperation)this.mRecent.get(b2)).commit(); 
    this.mRecent = null;
  }
  
  int countOperations() {
    return this.mOperations.size();
  }
  
  void destroy() {
    for (int i = this.mOperations.size() - 1; i >= 0; i--) {
      UndoOwner undoOwner = ((UndoOperation)this.mOperations.get(i)).mOwner;
      undoOwner.mOpCount--;
      if (undoOwner.mOpCount <= 0)
        if (undoOwner.mOpCount >= 0) {
          this.mManager.removeOwner(undoOwner);
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Underflow of op count on owner ");
          stringBuilder.append(undoOwner);
          stringBuilder.append(" in op ");
          stringBuilder.append(this.mOperations.get(i));
          throw new IllegalStateException(stringBuilder.toString());
        }  
    } 
  }
  
  int getCommitId() {
    return this.mCommitId;
  }
  
  CharSequence getLabel() {
    return this.mLabel;
  }
  
  <T extends UndoOperation> T getLastOperation(Class<T> paramClass, UndoOwner paramUndoOwner) {
    UndoOperation undoOperation;
    int i = this.mOperations.size();
    Class clazz = null;
    if (paramClass == null && paramUndoOwner == null) {
      paramClass = clazz;
      if (i > 0)
        undoOperation = this.mOperations.get(i - 1); 
      return (T)undoOperation;
    } 
    while (--i >= 0) {
      UndoOperation undoOperation1 = this.mOperations.get(i);
      if (paramUndoOwner != null && undoOperation1.getOwner() != paramUndoOwner) {
        i--;
        continue;
      } 
      return (T)((undoOperation != null && undoOperation1.getClass() != undoOperation) ? null : undoOperation1);
    } 
    return null;
  }
  
  boolean hasData() {
    for (int i = this.mOperations.size() - 1; i >= 0; i--) {
      if (((UndoOperation)this.mOperations.get(i)).hasData())
        return true; 
    } 
    return false;
  }
  
  boolean hasMultipleOwners() {
    int i = this.mOperations.size();
    if (i <= 1)
      return false; 
    UndoOwner undoOwner = ((UndoOperation)this.mOperations.get(0)).getOwner();
    for (byte b = 1; b < i; b++) {
      if (((UndoOperation)this.mOperations.get(b)).getOwner() != undoOwner)
        return true; 
    } 
    return false;
  }
  
  boolean hasOperation(UndoOwner paramUndoOwner) {
    int i = this.mOperations.size();
    boolean bool = false;
    if (paramUndoOwner == null) {
      if (i != 0)
        bool = true; 
      return bool;
    } 
    for (byte b = 0; b < i; b++) {
      if (((UndoOperation)this.mOperations.get(b)).getOwner() == paramUndoOwner)
        return true; 
    } 
    return false;
  }
  
  void makeExecuted() {
    this.mExecuted = true;
  }
  
  boolean matchOwner(UndoOwner paramUndoOwner) {
    for (int i = this.mOperations.size() - 1; i >= 0; i--) {
      if (((UndoOperation)this.mOperations.get(i)).matchOwner(paramUndoOwner))
        return true; 
    } 
    return false;
  }
  
  void redo() {
    int i = this.mOperations.size();
    for (byte b = 0; b < i; b++)
      ((UndoOperation)this.mOperations.get(b)).redo(); 
  }
  
  boolean setCanMerge(boolean paramBoolean) {
    if (paramBoolean && this.mExecuted)
      return false; 
    this.mCanMerge = paramBoolean;
    return true;
  }
  
  void setLabel(CharSequence paramCharSequence) {
    this.mLabel = paramCharSequence;
  }
  
  void undo() {
    for (int i = this.mOperations.size() - 1; i >= 0; i--)
      ((UndoOperation)this.mOperations.get(i)).undo(); 
  }
  
  void updateLabel(CharSequence paramCharSequence) {
    if (this.mLabel != null)
      this.mLabel = paramCharSequence; 
  }
  
  void writeToParcel(Parcel paramParcel) {
    if (this.mRecent == null) {
      paramParcel.writeInt(this.mCommitId);
      paramParcel.writeInt(this.mCanMerge);
      paramParcel.writeInt(this.mExecuted);
      TextUtils.writeToParcel(this.mLabel, paramParcel, 0);
      int i = this.mOperations.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        UndoOperation undoOperation = this.mOperations.get(b);
        this.mManager.saveOwner(undoOperation.mOwner, paramParcel);
        paramParcel.writeParcelable(undoOperation, 0);
      } 
      return;
    } 
    throw new IllegalStateException("Can't save state before committing");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/UndoManager$UndoState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */