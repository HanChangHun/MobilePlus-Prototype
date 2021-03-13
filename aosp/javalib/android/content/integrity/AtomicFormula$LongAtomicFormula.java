package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public final class LongAtomicFormula extends AtomicFormula implements Parcelable {
  public static final Parcelable.Creator<LongAtomicFormula> CREATOR = new Parcelable.Creator<LongAtomicFormula>() {
      public AtomicFormula.LongAtomicFormula createFromParcel(Parcel param2Parcel) {
        return new AtomicFormula.LongAtomicFormula(param2Parcel);
      }
      
      public AtomicFormula.LongAtomicFormula[] newArray(int param2Int) {
        return new AtomicFormula.LongAtomicFormula[param2Int];
      }
    };
  
  private final Integer mOperator;
  
  private final Long mValue;
  
  public LongAtomicFormula(int paramInt) {
    super(paramInt);
    boolean bool;
    if (paramInt == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, String.format("Key %s cannot be used with LongAtomicFormula", new Object[] { keyToString(paramInt) }));
    this.mValue = null;
    this.mOperator = null;
  }
  
  public LongAtomicFormula(int paramInt1, int paramInt2, long paramLong) {
    super(paramInt1);
    boolean bool;
    if (paramInt1 == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, String.format("Key %s cannot be used with LongAtomicFormula", new Object[] { keyToString(paramInt1) }));
    Preconditions.checkArgument(isValidOperator(paramInt2), String.format("Unknown operator: %d", new Object[] { Integer.valueOf(paramInt2) }));
    this.mOperator = Integer.valueOf(paramInt2);
    this.mValue = Long.valueOf(paramLong);
  }
  
  LongAtomicFormula(Parcel paramParcel) {
    super(paramParcel.readInt());
    this.mValue = Long.valueOf(paramParcel.readLong());
    this.mOperator = Integer.valueOf(paramParcel.readInt());
  }
  
  private static long getLongMetadataValue(AppInstallMetadata paramAppInstallMetadata, int paramInt) {
    if (paramInt == 4)
      return paramAppInstallMetadata.getVersionCode(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unexpected key in IntAtomicFormula");
    stringBuilder.append(paramInt);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private static boolean isValidOperator(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 0) {
      bool2 = bool1;
      if (paramInt != 1)
        if (paramInt == 2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
    } 
    return bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (getKey() != paramObject.getKey() || this.mValue != ((LongAtomicFormula)paramObject).mValue || this.mOperator != ((LongAtomicFormula)paramObject).mOperator)
      bool = false; 
    return bool;
  }
  
  public Integer getOperator() {
    return this.mOperator;
  }
  
  public int getTag() {
    return 2;
  }
  
  public Long getValue() {
    return this.mValue;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(getKey()), this.mOperator, this.mValue });
  }
  
  public boolean isAppCertificateFormula() {
    return false;
  }
  
  public boolean isInstallerFormula() {
    return false;
  }
  
  public boolean matches(AppInstallMetadata paramAppInstallMetadata) {
    Long long_ = this.mValue;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    if (long_ == null || this.mOperator == null)
      return false; 
    long l = getLongMetadataValue(paramAppInstallMetadata, getKey());
    int i = this.mOperator.intValue();
    if (i != 0) {
      if (i != 1) {
        if (i == 2) {
          if (l >= this.mValue.longValue())
            bool3 = true; 
          return bool3;
        } 
        throw new IllegalArgumentException(String.format("Unexpected operator %d", new Object[] { this.mOperator }));
      } 
      bool3 = bool1;
      if (l > this.mValue.longValue())
        bool3 = true; 
      return bool3;
    } 
    bool3 = bool2;
    if (l == this.mValue.longValue())
      bool3 = true; 
    return bool3;
  }
  
  public String toString() {
    return (this.mValue == null || this.mOperator == null) ? String.format("(%s)", new Object[] { keyToString(getKey()) }) : String.format("(%s %s %s)", new Object[] { keyToString(getKey()), operatorToString(this.mOperator.intValue()), this.mValue });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mValue != null && this.mOperator != null) {
      paramParcel.writeInt(getKey());
      paramParcel.writeLong(this.mValue.longValue());
      paramParcel.writeInt(this.mOperator.intValue());
      return;
    } 
    throw new IllegalStateException("Cannot write an empty LongAtomicFormula.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula$LongAtomicFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */