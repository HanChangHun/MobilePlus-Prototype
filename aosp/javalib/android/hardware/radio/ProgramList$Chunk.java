package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class Chunk implements Parcelable {
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
  
  private Chunk(Parcel paramParcel) {
    boolean bool2;
    byte b = paramParcel.readByte();
    boolean bool1 = true;
    if (b != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mPurge = bool2;
    if (paramParcel.readByte() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mComplete = bool2;
    this.mModified = Utils.createSet(paramParcel, RadioManager.ProgramInfo.CREATOR);
    this.mRemoved = Utils.createSet(paramParcel, ProgramSelector.Identifier.CREATOR);
  }
  
  public Chunk(boolean paramBoolean1, boolean paramBoolean2, Set<RadioManager.ProgramInfo> paramSet, Set<ProgramSelector.Identifier> paramSet1) {
    this.mPurge = paramBoolean1;
    this.mComplete = paramBoolean2;
    if (paramSet == null)
      paramSet = Collections.emptySet(); 
    this.mModified = paramSet;
    if (paramSet1 != null) {
      Set<ProgramSelector.Identifier> set = paramSet1;
    } else {
      paramSet = Collections.emptySet();
    } 
    this.mRemoved = (Set)paramSet;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof Chunk))
      return false; 
    paramObject = paramObject;
    return (this.mPurge != ((Chunk)paramObject).mPurge) ? false : ((this.mComplete != ((Chunk)paramObject).mComplete) ? false : (!Objects.equals(this.mModified, ((Chunk)paramObject).mModified) ? false : (!!Objects.equals(this.mRemoved, ((Chunk)paramObject).mRemoved))));
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
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeByte((byte)this.mPurge);
    paramParcel.writeByte((byte)this.mComplete);
    Utils.writeSet(paramParcel, this.mModified);
    Utils.writeSet(paramParcel, this.mRemoved);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramList$Chunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */