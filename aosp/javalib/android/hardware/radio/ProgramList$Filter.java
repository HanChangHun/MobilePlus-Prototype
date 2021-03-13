package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Filter implements Parcelable {
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
  
  private Filter(Parcel paramParcel) {
    boolean bool2;
    this.mIdentifierTypes = Utils.createIntSet(paramParcel);
    this.mIdentifiers = Utils.createSet(paramParcel, ProgramSelector.Identifier.CREATOR);
    byte b = paramParcel.readByte();
    boolean bool1 = true;
    if (b != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIncludeCategories = bool2;
    if (paramParcel.readByte() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mExcludeModifications = bool2;
    this.mVendorFilter = Utils.readStringMap(paramParcel);
  }
  
  public Filter(Map<String, String> paramMap) {
    this.mIdentifierTypes = Collections.emptySet();
    this.mIdentifiers = Collections.emptySet();
    this.mIncludeCategories = false;
    this.mExcludeModifications = false;
    this.mVendorFilter = paramMap;
  }
  
  public Filter(Set<Integer> paramSet, Set<ProgramSelector.Identifier> paramSet1, boolean paramBoolean1, boolean paramBoolean2) {
    Objects.requireNonNull(paramSet);
    this.mIdentifierTypes = paramSet;
    Objects.requireNonNull(paramSet1);
    this.mIdentifiers = paramSet1;
    this.mIncludeCategories = paramBoolean1;
    this.mExcludeModifications = paramBoolean2;
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
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof Filter))
      return false; 
    paramObject = paramObject;
    return (this.mIncludeCategories != ((Filter)paramObject).mIncludeCategories) ? false : ((this.mExcludeModifications != ((Filter)paramObject).mExcludeModifications) ? false : (!Objects.equals(this.mIdentifierTypes, ((Filter)paramObject).mIdentifierTypes) ? false : (!!Objects.equals(this.mIdentifiers, ((Filter)paramObject).mIdentifiers))));
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
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    Utils.writeIntSet(paramParcel, this.mIdentifierTypes);
    Utils.writeSet(paramParcel, this.mIdentifiers);
    paramParcel.writeByte((byte)this.mIncludeCategories);
    paramParcel.writeByte((byte)this.mExcludeModifications);
    Utils.writeStringMap(paramParcel, this.mVendorFilter);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramList$Filter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */