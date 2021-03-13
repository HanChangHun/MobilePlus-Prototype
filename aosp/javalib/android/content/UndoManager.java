package android.content;

import android.os.Parcel;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.util.ArrayList;

public class UndoManager {
  public static final int MERGE_MODE_ANY = 2;
  
  public static final int MERGE_MODE_NONE = 0;
  
  public static final int MERGE_MODE_UNIQUE = 1;
  
  private int mCommitId = 1;
  
  private int mHistorySize = 20;
  
  private boolean mInUndo;
  
  private boolean mMerged;
  
  private int mNextSavedIdx;
  
  private final ArrayMap<String, UndoOwner> mOwners = new ArrayMap(1);
  
  private final ArrayList<UndoState> mRedos = new ArrayList<>();
  
  private UndoOwner[] mStateOwners;
  
  private int mStateSeq;
  
  private final ArrayList<UndoState> mUndos = new ArrayList<>();
  
  private int mUpdateCount;
  
  private UndoState mWorking;
  
  private void createWorkingState() {
    int i = this.mCommitId;
    this.mCommitId = i + 1;
    this.mWorking = new UndoState(this, i);
    if (this.mCommitId < 0)
      this.mCommitId = 1; 
  }
  
  private void pushWorkingState() {
    int i = this.mUndos.size() + 1;
    if (this.mWorking.hasData()) {
      this.mUndos.add(this.mWorking);
      forgetRedos(null, -1);
      this.mWorking.commit();
      if (i >= 2)
        ((UndoState)this.mUndos.get(i - 2)).makeExecuted(); 
    } else {
      this.mWorking.destroy();
    } 
    this.mWorking = null;
    int j = this.mHistorySize;
    if (j >= 0 && i > j)
      forgetUndos(null, i - j); 
  }
  
  public void addOperation(UndoOperation<?> paramUndoOperation, int paramInt) {
    if (this.mWorking != null) {
      if ((paramUndoOperation.getOwner()).mManager == this) {
        if (paramInt != 0 && !this.mMerged && !this.mWorking.hasData()) {
          UndoState undoState = getTopUndo(null);
          if (undoState != null && (paramInt == 2 || !undoState.hasMultipleOwners()) && undoState.canMerge() && undoState.hasOperation(paramUndoOperation.getOwner())) {
            this.mWorking.destroy();
            this.mWorking = undoState;
            this.mUndos.remove(undoState);
            this.mMerged = true;
          } 
        } 
        this.mWorking.addOperation(paramUndoOperation);
        return;
      } 
      throw new IllegalArgumentException("Given operation's owner is not in this undo manager.");
    } 
    throw new IllegalStateException("Must be called during an update");
  }
  
  public void beginUpdate(CharSequence paramCharSequence) {
    if (!this.mInUndo) {
      if (this.mUpdateCount <= 0) {
        createWorkingState();
        this.mMerged = false;
        this.mUpdateCount = 0;
      } 
      this.mWorking.updateLabel(paramCharSequence);
      this.mUpdateCount++;
      return;
    } 
    throw new IllegalStateException("Can't being update while performing undo/redo");
  }
  
  public int commitState(UndoOwner paramUndoOwner) {
    UndoState undoState = this.mWorking;
    if (undoState != null && undoState.hasData()) {
      if (paramUndoOwner == null || this.mWorking.hasOperation(paramUndoOwner)) {
        this.mWorking.setCanMerge(false);
        int i = this.mWorking.getCommitId();
        pushWorkingState();
        createWorkingState();
        this.mMerged = true;
        return i;
      } 
    } else {
      undoState = getTopUndo(null);
      if (undoState != null && (paramUndoOwner == null || undoState.hasOperation(paramUndoOwner))) {
        undoState.setCanMerge(false);
        return undoState.getCommitId();
      } 
    } 
    return -1;
  }
  
  public int countRedos(UndoOwner[] paramArrayOfUndoOwner) {
    if (paramArrayOfUndoOwner == null)
      return this.mRedos.size(); 
    byte b = 0;
    int i = 0;
    while (true) {
      i = findNextState(this.mRedos, paramArrayOfUndoOwner, i);
      if (i >= 0) {
        b++;
        i++;
        continue;
      } 
      return b;
    } 
  }
  
  public int countUndos(UndoOwner[] paramArrayOfUndoOwner) {
    if (paramArrayOfUndoOwner == null)
      return this.mUndos.size(); 
    byte b = 0;
    int i = 0;
    while (true) {
      i = findNextState(this.mUndos, paramArrayOfUndoOwner, i);
      if (i >= 0) {
        b++;
        i++;
        continue;
      } 
      return b;
    } 
  }
  
  public void endUpdate() {
    if (this.mWorking != null) {
      int i = this.mUpdateCount - 1;
      this.mUpdateCount = i;
      if (i == 0)
        pushWorkingState(); 
      return;
    } 
    throw new IllegalStateException("Must be called during an update");
  }
  
  int findNextState(ArrayList<UndoState> paramArrayList, UndoOwner[] paramArrayOfUndoOwner, int paramInt) {
    int i = paramArrayList.size();
    int j = paramInt;
    if (paramInt < 0)
      j = 0; 
    if (j >= i)
      return -1; 
    paramInt = j;
    if (paramArrayOfUndoOwner == null)
      return j; 
    while (paramInt < i) {
      if (matchOwners(paramArrayList.get(paramInt), paramArrayOfUndoOwner))
        return paramInt; 
      paramInt++;
    } 
    return -1;
  }
  
  int findPrevState(ArrayList<UndoState> paramArrayList, UndoOwner[] paramArrayOfUndoOwner, int paramInt) {
    int i = paramArrayList.size();
    int j = paramInt;
    if (paramInt == -1)
      j = i - 1; 
    if (j >= i)
      return -1; 
    paramInt = j;
    if (paramArrayOfUndoOwner == null)
      return j; 
    while (paramInt >= 0) {
      if (matchOwners(paramArrayList.get(paramInt), paramArrayOfUndoOwner))
        return paramInt; 
      paramInt--;
    } 
    return -1;
  }
  
  public int forgetRedos(UndoOwner[] paramArrayOfUndoOwner, int paramInt) {
    int i = paramInt;
    if (paramInt < 0)
      i = this.mRedos.size(); 
    byte b = 0;
    for (paramInt = 0; paramInt < this.mRedos.size() && b < i; paramInt++) {
      UndoState undoState = this.mRedos.get(paramInt);
      if (i > 0 && matchOwners(undoState, paramArrayOfUndoOwner)) {
        undoState.destroy();
        this.mRedos.remove(paramInt);
        b++;
        continue;
      } 
    } 
    return b;
  }
  
  public int forgetUndos(UndoOwner[] paramArrayOfUndoOwner, int paramInt) {
    int i = paramInt;
    if (paramInt < 0)
      i = this.mUndos.size(); 
    byte b = 0;
    for (paramInt = 0; paramInt < this.mUndos.size() && b < i; paramInt++) {
      UndoState undoState = this.mUndos.get(paramInt);
      if (i > 0 && matchOwners(undoState, paramArrayOfUndoOwner)) {
        undoState.destroy();
        this.mUndos.remove(paramInt);
        b++;
        continue;
      } 
    } 
    return b;
  }
  
  public int getHistorySize() {
    return this.mHistorySize;
  }
  
  public UndoOperation<?> getLastOperation(int paramInt) {
    return getLastOperation(null, null, paramInt);
  }
  
  public UndoOperation<?> getLastOperation(UndoOwner paramUndoOwner, int paramInt) {
    return getLastOperation(null, paramUndoOwner, paramInt);
  }
  
  public <T extends UndoOperation> T getLastOperation(Class<T> paramClass, UndoOwner paramUndoOwner, int paramInt) {
    UndoState undoState = this.mWorking;
    if (undoState != null) {
      if (paramInt != 0 && !this.mMerged && !undoState.hasData()) {
        UndoState undoState1 = getTopUndo(null);
        if (undoState1 != null && (paramInt == 2 || !undoState1.hasMultipleOwners()) && undoState1.canMerge()) {
          undoState = undoState1.getLastOperation((Class)paramClass, paramUndoOwner);
          if (undoState != null && undoState.allowMerge()) {
            this.mWorking.destroy();
            this.mWorking = undoState1;
            this.mUndos.remove(undoState1);
            this.mMerged = true;
            return (T)undoState;
          } 
        } 
      } 
      return this.mWorking.getLastOperation(paramClass, paramUndoOwner);
    } 
    throw new IllegalStateException("Must be called during an update");
  }
  
  public UndoOwner getOwner(String paramString, Object paramObject) {
    if (paramString != null) {
      if (paramObject != null) {
        StringBuilder stringBuilder;
        UndoOwner undoOwner = (UndoOwner)this.mOwners.get(paramString);
        if (undoOwner != null) {
          if (undoOwner.mData != paramObject)
            if (undoOwner.mData == null) {
              undoOwner.mData = paramObject;
            } else {
              stringBuilder = new StringBuilder();
              stringBuilder.append("Owner ");
              stringBuilder.append(undoOwner);
              stringBuilder.append(" already exists with data ");
              stringBuilder.append(undoOwner.mData);
              stringBuilder.append(" but giving different data ");
              stringBuilder.append(paramObject);
              throw new IllegalStateException(stringBuilder.toString());
            }  
          return undoOwner;
        } 
        undoOwner = new UndoOwner((String)stringBuilder, this);
        undoOwner.mData = paramObject;
        this.mOwners.put(stringBuilder, undoOwner);
        return undoOwner;
      } 
      throw new NullPointerException("data can't be null");
    } 
    throw new NullPointerException("tag can't be null");
  }
  
  public CharSequence getRedoLabel(UndoOwner[] paramArrayOfUndoOwner) {
    UndoState undoState = getTopRedo(paramArrayOfUndoOwner);
    if (undoState != null) {
      CharSequence charSequence = undoState.getLabel();
    } else {
      undoState = null;
    } 
    return (CharSequence)undoState;
  }
  
  UndoState getTopRedo(UndoOwner[] paramArrayOfUndoOwner) {
    UndoState undoState;
    int i = this.mRedos.size();
    UndoOwner[] arrayOfUndoOwner = null;
    if (i <= 0)
      return null; 
    i = findPrevState(this.mRedos, paramArrayOfUndoOwner, -1);
    paramArrayOfUndoOwner = arrayOfUndoOwner;
    if (i >= 0)
      undoState = this.mRedos.get(i); 
    return undoState;
  }
  
  UndoState getTopUndo(UndoOwner[] paramArrayOfUndoOwner) {
    UndoState undoState;
    int i = this.mUndos.size();
    UndoOwner[] arrayOfUndoOwner = null;
    if (i <= 0)
      return null; 
    i = findPrevState(this.mUndos, paramArrayOfUndoOwner, -1);
    paramArrayOfUndoOwner = arrayOfUndoOwner;
    if (i >= 0)
      undoState = this.mUndos.get(i); 
    return undoState;
  }
  
  public CharSequence getUndoLabel(UndoOwner[] paramArrayOfUndoOwner) {
    UndoState undoState = getTopUndo(paramArrayOfUndoOwner);
    if (undoState != null) {
      CharSequence charSequence = undoState.getLabel();
    } else {
      undoState = null;
    } 
    return (CharSequence)undoState;
  }
  
  public int getUpdateNestingLevel() {
    return this.mUpdateCount;
  }
  
  public boolean hasOperation(UndoOwner paramUndoOwner) {
    UndoState undoState = this.mWorking;
    if (undoState != null)
      return undoState.hasOperation(paramUndoOwner); 
    throw new IllegalStateException("Must be called during an update");
  }
  
  public boolean isInUndo() {
    return this.mInUndo;
  }
  
  public boolean isInUpdate() {
    boolean bool;
    if (this.mUpdateCount > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean matchOwners(UndoState paramUndoState, UndoOwner[] paramArrayOfUndoOwner) {
    if (paramArrayOfUndoOwner == null)
      return true; 
    for (byte b = 0; b < paramArrayOfUndoOwner.length; b++) {
      if (paramUndoState.matchOwner(paramArrayOfUndoOwner[b]))
        return true; 
    } 
    return false;
  }
  
  public int redo(UndoOwner[] paramArrayOfUndoOwner, int paramInt) {
    if (this.mWorking == null) {
      byte b = 0;
      int i = -1;
      this.mInUndo = true;
      while (paramInt > 0) {
        int j = findPrevState(this.mRedos, paramArrayOfUndoOwner, i);
        i = j;
        if (j >= 0) {
          UndoState undoState = this.mRedos.remove(i);
          undoState.redo();
          this.mUndos.add(undoState);
          paramInt--;
          b++;
        } 
      } 
      this.mInUndo = false;
      return b;
    } 
    throw new IllegalStateException("Can't be called during an update");
  }
  
  void removeOwner(UndoOwner paramUndoOwner) {}
  
  public void restoreInstanceState(Parcel paramParcel, ClassLoader paramClassLoader) {
    if (this.mUpdateCount <= 0) {
      forgetUndos(null, -1);
      forgetRedos(null, -1);
      this.mHistorySize = paramParcel.readInt();
      this.mStateOwners = new UndoOwner[paramParcel.readInt()];
      while (true) {
        int i = paramParcel.readInt();
        if (i != 0) {
          UndoState undoState = new UndoState(this, paramParcel, paramClassLoader);
          if (i == 1) {
            this.mUndos.add(0, undoState);
            continue;
          } 
          this.mRedos.add(0, undoState);
          continue;
        } 
        break;
      } 
      return;
    } 
    throw new IllegalStateException("Can't save state while updating");
  }
  
  UndoOwner restoreOwner(Parcel paramParcel) {
    int i = paramParcel.readInt();
    UndoOwner undoOwner1 = this.mStateOwners[i];
    UndoOwner undoOwner2 = undoOwner1;
    if (undoOwner1 == null) {
      String str = paramParcel.readString();
      int j = paramParcel.readInt();
      undoOwner2 = new UndoOwner(str, this);
      undoOwner2.mOpCount = j;
      this.mStateOwners[i] = undoOwner2;
      this.mOwners.put(str, undoOwner2);
    } 
    return undoOwner2;
  }
  
  public void saveInstanceState(Parcel paramParcel) {
    if (this.mUpdateCount <= 0) {
      int i = this.mStateSeq + 1;
      this.mStateSeq = i;
      if (i <= 0)
        this.mStateSeq = 0; 
      this.mNextSavedIdx = 0;
      paramParcel.writeInt(this.mHistorySize);
      paramParcel.writeInt(this.mOwners.size());
      i = this.mUndos.size();
      while (i > 0) {
        paramParcel.writeInt(1);
        ((UndoState)this.mUndos.get(--i)).writeToParcel(paramParcel);
      } 
      i = this.mRedos.size();
      while (i > 0) {
        paramParcel.writeInt(2);
        ((UndoState)this.mRedos.get(--i)).writeToParcel(paramParcel);
      } 
      paramParcel.writeInt(0);
      return;
    } 
    throw new IllegalStateException("Can't save state while updating");
  }
  
  void saveOwner(UndoOwner paramUndoOwner, Parcel paramParcel) {
    int i = paramUndoOwner.mStateSeq;
    int j = this.mStateSeq;
    if (i == j) {
      paramParcel.writeInt(paramUndoOwner.mSavedIdx);
    } else {
      paramUndoOwner.mStateSeq = j;
      paramUndoOwner.mSavedIdx = this.mNextSavedIdx;
      paramParcel.writeInt(paramUndoOwner.mSavedIdx);
      paramParcel.writeString(paramUndoOwner.mTag);
      paramParcel.writeInt(paramUndoOwner.mOpCount);
      this.mNextSavedIdx++;
    } 
  }
  
  public void setHistorySize(int paramInt) {
    this.mHistorySize = paramInt;
    if (paramInt >= 0 && countUndos(null) > this.mHistorySize)
      forgetUndos(null, countUndos(null) - this.mHistorySize); 
  }
  
  public void setUndoLabel(CharSequence paramCharSequence) {
    UndoState undoState = this.mWorking;
    if (undoState != null) {
      undoState.setLabel(paramCharSequence);
      return;
    } 
    throw new IllegalStateException("Must be called during an update");
  }
  
  public void suggestUndoLabel(CharSequence paramCharSequence) {
    UndoState undoState = this.mWorking;
    if (undoState != null) {
      undoState.updateLabel(paramCharSequence);
      return;
    } 
    throw new IllegalStateException("Must be called during an update");
  }
  
  public boolean uncommitState(int paramInt, UndoOwner paramUndoOwner) {
    UndoState undoState = this.mWorking;
    if (undoState != null && undoState.getCommitId() == paramInt) {
      if (paramUndoOwner == null || this.mWorking.hasOperation(paramUndoOwner))
        return this.mWorking.setCanMerge(true); 
    } else {
      undoState = getTopUndo(null);
      if (undoState != null && (paramUndoOwner == null || undoState.hasOperation(paramUndoOwner)) && undoState.getCommitId() == paramInt)
        return undoState.setCanMerge(true); 
    } 
    return false;
  }
  
  public int undo(UndoOwner[] paramArrayOfUndoOwner, int paramInt) {
    if (this.mWorking == null) {
      byte b1 = 0;
      byte b = -1;
      this.mInUndo = true;
      UndoState undoState = getTopUndo(null);
      byte b2 = b1;
      int i = b;
      int j = paramInt;
      if (undoState != null) {
        undoState.makeExecuted();
        j = paramInt;
        i = b;
        b2 = b1;
      } 
      while (j > 0) {
        paramInt = findPrevState(this.mUndos, paramArrayOfUndoOwner, i);
        i = paramInt;
        if (paramInt >= 0) {
          undoState = this.mUndos.remove(i);
          undoState.undo();
          this.mRedos.add(undoState);
          j--;
          b2++;
        } 
      } 
      this.mInUndo = false;
      return b2;
    } 
    throw new IllegalStateException("Can't be called during an update");
  }
  
  static final class UndoState {
    private boolean mCanMerge;
    
    private final int mCommitId;
    
    private boolean mExecuted;
    
    private CharSequence mLabel;
    
    private final UndoManager mManager;
    
    private final ArrayList<UndoOperation<?>> mOperations;
    
    private ArrayList<UndoOperation<?>> mRecent;
    
    UndoState(UndoManager param1UndoManager, int param1Int) {
      this.mOperations = new ArrayList<>();
      this.mCanMerge = true;
      this.mManager = param1UndoManager;
      this.mCommitId = param1Int;
    }
    
    UndoState(UndoManager param1UndoManager, Parcel param1Parcel, ClassLoader param1ClassLoader) {
      boolean bool2;
      this.mOperations = new ArrayList<>();
      boolean bool1 = true;
      this.mCanMerge = true;
      this.mManager = param1UndoManager;
      this.mCommitId = param1Parcel.readInt();
      if (param1Parcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mCanMerge = bool2;
      if (param1Parcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.mExecuted = bool2;
      this.mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
      int i = param1Parcel.readInt();
      for (byte b = 0; b < i; b++) {
        UndoOwner undoOwner = this.mManager.restoreOwner(param1Parcel);
        UndoOperation<?> undoOperation = (UndoOperation)param1Parcel.readParcelable(param1ClassLoader);
        undoOperation.mOwner = undoOwner;
        this.mOperations.add(undoOperation);
      } 
    }
    
    void addOperation(UndoOperation<?> param1UndoOperation) {
      UndoOwner undoOwner;
      if (!this.mOperations.contains(param1UndoOperation)) {
        this.mOperations.add(param1UndoOperation);
        if (this.mRecent == null) {
          ArrayList<UndoOperation<?>> arrayList = new ArrayList();
          this.mRecent = arrayList;
          arrayList.add(param1UndoOperation);
        } 
        undoOwner = param1UndoOperation.mOwner;
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
    
    <T extends UndoOperation> T getLastOperation(Class<T> param1Class, UndoOwner param1UndoOwner) {
      UndoOperation undoOperation;
      int i = this.mOperations.size();
      Class clazz = null;
      if (param1Class == null && param1UndoOwner == null) {
        param1Class = clazz;
        if (i > 0)
          undoOperation = this.mOperations.get(i - 1); 
        return (T)undoOperation;
      } 
      while (--i >= 0) {
        UndoOperation undoOperation1 = this.mOperations.get(i);
        if (param1UndoOwner != null && undoOperation1.getOwner() != param1UndoOwner) {
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
    
    boolean hasOperation(UndoOwner param1UndoOwner) {
      int i = this.mOperations.size();
      boolean bool = false;
      if (param1UndoOwner == null) {
        if (i != 0)
          bool = true; 
        return bool;
      } 
      for (byte b = 0; b < i; b++) {
        if (((UndoOperation)this.mOperations.get(b)).getOwner() == param1UndoOwner)
          return true; 
      } 
      return false;
    }
    
    void makeExecuted() {
      this.mExecuted = true;
    }
    
    boolean matchOwner(UndoOwner param1UndoOwner) {
      for (int i = this.mOperations.size() - 1; i >= 0; i--) {
        if (((UndoOperation)this.mOperations.get(i)).matchOwner(param1UndoOwner))
          return true; 
      } 
      return false;
    }
    
    void redo() {
      int i = this.mOperations.size();
      for (byte b = 0; b < i; b++)
        ((UndoOperation)this.mOperations.get(b)).redo(); 
    }
    
    boolean setCanMerge(boolean param1Boolean) {
      if (param1Boolean && this.mExecuted)
        return false; 
      this.mCanMerge = param1Boolean;
      return true;
    }
    
    void setLabel(CharSequence param1CharSequence) {
      this.mLabel = param1CharSequence;
    }
    
    void undo() {
      for (int i = this.mOperations.size() - 1; i >= 0; i--)
        ((UndoOperation)this.mOperations.get(i)).undo(); 
    }
    
    void updateLabel(CharSequence param1CharSequence) {
      if (this.mLabel != null)
        this.mLabel = param1CharSequence; 
    }
    
    void writeToParcel(Parcel param1Parcel) {
      if (this.mRecent == null) {
        param1Parcel.writeInt(this.mCommitId);
        param1Parcel.writeInt(this.mCanMerge);
        param1Parcel.writeInt(this.mExecuted);
        TextUtils.writeToParcel(this.mLabel, param1Parcel, 0);
        int i = this.mOperations.size();
        param1Parcel.writeInt(i);
        for (byte b = 0; b < i; b++) {
          UndoOperation undoOperation = this.mOperations.get(b);
          this.mManager.saveOwner(undoOperation.mOwner, param1Parcel);
          param1Parcel.writeParcelable(undoOperation, 0);
        } 
        return;
      } 
      throw new IllegalStateException("Can't save state before committing");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/UndoManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */