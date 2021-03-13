package android.hardware.radio;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SystemApi
public final class ProgramList implements AutoCloseable {
  private boolean mIsClosed = false;
  
  private boolean mIsComplete = false;
  
  private final List<ListCallback> mListCallbacks = new ArrayList<>();
  
  private final Object mLock = new Object();
  
  private OnCloseListener mOnCloseListener;
  
  private final List<OnCompleteListener> mOnCompleteListeners = new ArrayList<>();
  
  private final Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> mPrograms = new HashMap<>();
  
  private void putLocked(RadioManager.ProgramInfo paramProgramInfo) {
    ProgramSelector.Identifier identifier2 = paramProgramInfo.getSelector().getPrimaryId();
    Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> map = this.mPrograms;
    Objects.requireNonNull(identifier2);
    map.put(identifier2, paramProgramInfo);
    ProgramSelector.Identifier identifier1 = paramProgramInfo.getSelector().getPrimaryId();
    this.mListCallbacks.forEach(new _$$Lambda$ProgramList$fDnoTVk5UB7qTfD9S7SYPcadYn0(identifier1));
  }
  
  private void removeLocked(ProgramSelector.Identifier paramIdentifier) {
    Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> map = this.mPrograms;
    Objects.requireNonNull(paramIdentifier);
    RadioManager.ProgramInfo programInfo = map.remove(paramIdentifier);
    if (programInfo == null)
      return; 
    ProgramSelector.Identifier identifier = programInfo.getSelector().getPrimaryId();
    this.mListCallbacks.forEach(new _$$Lambda$ProgramList$fHYelmhnUsVTYl6dFj75fMqCjGs(identifier));
  }
  
  public void addOnCompleteListener(OnCompleteListener paramOnCompleteListener) {
    synchronized (this.mLock) {
      if (this.mIsClosed)
        return; 
      List<OnCompleteListener> list = this.mOnCompleteListeners;
      Objects.requireNonNull(paramOnCompleteListener);
      list.add(paramOnCompleteListener);
      if (this.mIsComplete)
        paramOnCompleteListener.onComplete(); 
      return;
    } 
  }
  
  public void addOnCompleteListener(Executor paramExecutor, OnCompleteListener paramOnCompleteListener) {
    addOnCompleteListener(new _$$Lambda$ProgramList$aDYMynqVdAUqeKXIxfNtN1u67zs(paramExecutor, paramOnCompleteListener));
  }
  
  void apply(Chunk paramChunk) {
    synchronized (this.mLock) {
      if (this.mIsClosed)
        return; 
      this.mIsComplete = false;
      if (paramChunk.isPurge()) {
        HashSet hashSet = new HashSet();
        this((Collection)this.mPrograms.keySet());
        Stream stream2 = hashSet.stream();
        _$$Lambda$ProgramList$F_JpTj3vYguKIUQbnLbTePTuqUE _$$Lambda$ProgramList$F_JpTj3vYguKIUQbnLbTePTuqUE = new _$$Lambda$ProgramList$F_JpTj3vYguKIUQbnLbTePTuqUE();
        this(this);
        stream2.forEach(_$$Lambda$ProgramList$F_JpTj3vYguKIUQbnLbTePTuqUE);
      } 
      Stream<ProgramSelector.Identifier> stream = paramChunk.getRemoved().stream();
      _$$Lambda$ProgramList$pKu0Zp5jwjix619hfB_Imj8Ke_g _$$Lambda$ProgramList$pKu0Zp5jwjix619hfB_Imj8Ke_g = new _$$Lambda$ProgramList$pKu0Zp5jwjix619hfB_Imj8Ke_g();
      this(this);
      stream.forEach(_$$Lambda$ProgramList$pKu0Zp5jwjix619hfB_Imj8Ke_g);
      Stream<RadioManager.ProgramInfo> stream1 = paramChunk.getModified().stream();
      _$$Lambda$ProgramList$eY050tMTgAcGV9hiWR_UDxhkfhw _$$Lambda$ProgramList$eY050tMTgAcGV9hiWR_UDxhkfhw = new _$$Lambda$ProgramList$eY050tMTgAcGV9hiWR_UDxhkfhw();
      this(this);
      stream1.forEach(_$$Lambda$ProgramList$eY050tMTgAcGV9hiWR_UDxhkfhw);
      if (paramChunk.isComplete()) {
        this.mIsComplete = true;
        this.mOnCompleteListeners.forEach((Consumer<? super OnCompleteListener>)_$$Lambda$ProgramList$GfCj9jJ5znxw2TV4c2uykq35dgI.INSTANCE);
      } 
      return;
    } 
  }
  
  public void close() {
    synchronized (this.mLock) {
      if (this.mIsClosed)
        return; 
      this.mIsClosed = true;
      this.mPrograms.clear();
      this.mListCallbacks.clear();
      this.mOnCompleteListeners.clear();
      if (this.mOnCloseListener != null) {
        this.mOnCloseListener.onClose();
        this.mOnCloseListener = null;
      } 
      return;
    } 
  }
  
  public RadioManager.ProgramInfo get(ProgramSelector.Identifier paramIdentifier) {
    synchronized (this.mLock) {
      Map<ProgramSelector.Identifier, RadioManager.ProgramInfo> map = this.mPrograms;
      Objects.requireNonNull(paramIdentifier);
      return map.get(paramIdentifier);
    } 
  }
  
  public void registerListCallback(ListCallback paramListCallback) {
    synchronized (this.mLock) {
      if (this.mIsClosed)
        return; 
      List<ListCallback> list = this.mListCallbacks;
      Objects.requireNonNull(paramListCallback);
      list.add(paramListCallback);
      return;
    } 
  }
  
  public void registerListCallback(final Executor executor, final ListCallback callback) {
    registerListCallback(new ListCallback() {
          public void onItemChanged(ProgramSelector.Identifier param1Identifier) {
            executor.execute(new _$$Lambda$ProgramList$1$DVvry5MfhR6n8H2EZn67rvuhllI(callback, param1Identifier));
          }
          
          public void onItemRemoved(ProgramSelector.Identifier param1Identifier) {
            executor.execute(new _$$Lambda$ProgramList$1$a_xWqo5pESOZhcJIWvpiCd2AXmY(callback, param1Identifier));
          }
        });
  }
  
  public void removeOnCompleteListener(OnCompleteListener paramOnCompleteListener) {
    synchronized (this.mLock) {
      if (this.mIsClosed)
        return; 
      List<OnCompleteListener> list = this.mOnCompleteListeners;
      Objects.requireNonNull(paramOnCompleteListener);
      list.remove(paramOnCompleteListener);
      return;
    } 
  }
  
  void setOnCloseListener(OnCloseListener paramOnCloseListener) {
    synchronized (this.mLock) {
      if (this.mOnCloseListener == null) {
        this.mOnCloseListener = paramOnCloseListener;
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Close callback is already set");
      throw illegalStateException;
    } 
  }
  
  public List<RadioManager.ProgramInfo> toList() {
    synchronized (this.mLock) {
      return (List)this.mPrograms.values().stream().collect(Collectors.toList());
    } 
  }
  
  public void unregisterListCallback(ListCallback paramListCallback) {
    synchronized (this.mLock) {
      if (this.mIsClosed)
        return; 
      List<ListCallback> list = this.mListCallbacks;
      Objects.requireNonNull(paramListCallback);
      list.remove(paramListCallback);
      return;
    } 
  }
  
  public static final class Chunk implements Parcelable {
    public static final Parcelable.Creator<Chunk> CREATOR = new Parcelable.Creator<Chunk>() {
        public ProgramList.Chunk createFromParcel(Parcel param2Parcel) {
          return new ProgramList.Chunk(param2Parcel);
        }
        
        public ProgramList.Chunk[] newArray(int param2Int) {
          return new ProgramList.Chunk[param2Int];
        }
      };
    
    private final boolean mComplete;
    
    private final Set<RadioManager.ProgramInfo> mModified;
    
    private final boolean mPurge;
    
    private final Set<ProgramSelector.Identifier> mRemoved;
    
    private Chunk(Parcel param1Parcel) {
      boolean bool2;
      byte b = param1Parcel.readByte();
      boolean bool1 = true;
      if (b != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mPurge = bool2;
      if (param1Parcel.readByte() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.mComplete = bool2;
      this.mModified = Utils.createSet(param1Parcel, RadioManager.ProgramInfo.CREATOR);
      this.mRemoved = Utils.createSet(param1Parcel, ProgramSelector.Identifier.CREATOR);
    }
    
    public Chunk(boolean param1Boolean1, boolean param1Boolean2, Set<RadioManager.ProgramInfo> param1Set, Set<ProgramSelector.Identifier> param1Set1) {
      this.mPurge = param1Boolean1;
      this.mComplete = param1Boolean2;
      if (param1Set == null)
        param1Set = Collections.emptySet(); 
      this.mModified = param1Set;
      if (param1Set1 != null) {
        Set<ProgramSelector.Identifier> set = param1Set1;
      } else {
        param1Set = Collections.emptySet();
      } 
      this.mRemoved = (Set)param1Set;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof Chunk))
        return false; 
      param1Object = param1Object;
      return (this.mPurge != ((Chunk)param1Object).mPurge) ? false : ((this.mComplete != ((Chunk)param1Object).mComplete) ? false : (!Objects.equals(this.mModified, ((Chunk)param1Object).mModified) ? false : (!!Objects.equals(this.mRemoved, ((Chunk)param1Object).mRemoved))));
    }
    
    public Set<RadioManager.ProgramInfo> getModified() {
      return this.mModified;
    }
    
    public Set<ProgramSelector.Identifier> getRemoved() {
      return this.mRemoved;
    }
    
    public boolean isComplete() {
      return this.mComplete;
    }
    
    public boolean isPurge() {
      return this.mPurge;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Chunk [mPurge=");
      stringBuilder.append(this.mPurge);
      stringBuilder.append(", mComplete=");
      stringBuilder.append(this.mComplete);
      stringBuilder.append(", mModified=");
      stringBuilder.append(this.mModified);
      stringBuilder.append(", mRemoved=");
      stringBuilder.append(this.mRemoved);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeByte((byte)this.mPurge);
      param1Parcel.writeByte((byte)this.mComplete);
      Utils.writeSet(param1Parcel, this.mModified);
      Utils.writeSet(param1Parcel, this.mRemoved);
    }
  }
  
  class null implements Parcelable.Creator<Chunk> {
    public ProgramList.Chunk createFromParcel(Parcel param1Parcel) {
      return new ProgramList.Chunk(param1Parcel);
    }
    
    public ProgramList.Chunk[] newArray(int param1Int) {
      return new ProgramList.Chunk[param1Int];
    }
  }
  
  public static final class Filter implements Parcelable {
    public static final Parcelable.Creator<Filter> CREATOR = new Parcelable.Creator<Filter>() {
        public ProgramList.Filter createFromParcel(Parcel param2Parcel) {
          return new ProgramList.Filter(param2Parcel);
        }
        
        public ProgramList.Filter[] newArray(int param2Int) {
          return new ProgramList.Filter[param2Int];
        }
      };
    
    private final boolean mExcludeModifications;
    
    private final Set<Integer> mIdentifierTypes;
    
    private final Set<ProgramSelector.Identifier> mIdentifiers;
    
    private final boolean mIncludeCategories;
    
    private final Map<String, String> mVendorFilter;
    
    public Filter() {
      this.mIdentifierTypes = Collections.emptySet();
      this.mIdentifiers = Collections.emptySet();
      this.mIncludeCategories = false;
      this.mExcludeModifications = false;
      this.mVendorFilter = null;
    }
    
    private Filter(Parcel param1Parcel) {
      boolean bool2;
      this.mIdentifierTypes = Utils.createIntSet(param1Parcel);
      this.mIdentifiers = Utils.createSet(param1Parcel, ProgramSelector.Identifier.CREATOR);
      byte b = param1Parcel.readByte();
      boolean bool1 = true;
      if (b != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mIncludeCategories = bool2;
      if (param1Parcel.readByte() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.mExcludeModifications = bool2;
      this.mVendorFilter = Utils.readStringMap(param1Parcel);
    }
    
    public Filter(Map<String, String> param1Map) {
      this.mIdentifierTypes = Collections.emptySet();
      this.mIdentifiers = Collections.emptySet();
      this.mIncludeCategories = false;
      this.mExcludeModifications = false;
      this.mVendorFilter = param1Map;
    }
    
    public Filter(Set<Integer> param1Set, Set<ProgramSelector.Identifier> param1Set1, boolean param1Boolean1, boolean param1Boolean2) {
      Objects.requireNonNull(param1Set);
      this.mIdentifierTypes = param1Set;
      Objects.requireNonNull(param1Set1);
      this.mIdentifiers = param1Set1;
      this.mIncludeCategories = param1Boolean1;
      this.mExcludeModifications = param1Boolean2;
      this.mVendorFilter = null;
    }
    
    public boolean areCategoriesIncluded() {
      return this.mIncludeCategories;
    }
    
    public boolean areModificationsExcluded() {
      return this.mExcludeModifications;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof Filter))
        return false; 
      param1Object = param1Object;
      return (this.mIncludeCategories != ((Filter)param1Object).mIncludeCategories) ? false : ((this.mExcludeModifications != ((Filter)param1Object).mExcludeModifications) ? false : (!Objects.equals(this.mIdentifierTypes, ((Filter)param1Object).mIdentifierTypes) ? false : (!!Objects.equals(this.mIdentifiers, ((Filter)param1Object).mIdentifiers))));
    }
    
    public Set<Integer> getIdentifierTypes() {
      return this.mIdentifierTypes;
    }
    
    public Set<ProgramSelector.Identifier> getIdentifiers() {
      return this.mIdentifiers;
    }
    
    public Map<String, String> getVendorFilter() {
      return this.mVendorFilter;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.mIdentifierTypes, this.mIdentifiers, Boolean.valueOf(this.mIncludeCategories), Boolean.valueOf(this.mExcludeModifications) });
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Filter [mIdentifierTypes=");
      stringBuilder.append(this.mIdentifierTypes);
      stringBuilder.append(", mIdentifiers=");
      stringBuilder.append(this.mIdentifiers);
      stringBuilder.append(", mIncludeCategories=");
      stringBuilder.append(this.mIncludeCategories);
      stringBuilder.append(", mExcludeModifications=");
      stringBuilder.append(this.mExcludeModifications);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      Utils.writeIntSet(param1Parcel, this.mIdentifierTypes);
      Utils.writeSet(param1Parcel, this.mIdentifiers);
      param1Parcel.writeByte((byte)this.mIncludeCategories);
      param1Parcel.writeByte((byte)this.mExcludeModifications);
      Utils.writeStringMap(param1Parcel, this.mVendorFilter);
    }
  }
  
  class null implements Parcelable.Creator<Filter> {
    public ProgramList.Filter createFromParcel(Parcel param1Parcel) {
      return new ProgramList.Filter(param1Parcel);
    }
    
    public ProgramList.Filter[] newArray(int param1Int) {
      return new ProgramList.Filter[param1Int];
    }
  }
  
  public static abstract class ListCallback {
    public void onItemChanged(ProgramSelector.Identifier param1Identifier) {}
    
    public void onItemRemoved(ProgramSelector.Identifier param1Identifier) {}
  }
  
  static interface OnCloseListener {
    void onClose();
  }
  
  public static interface OnCompleteListener {
    void onComplete();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */