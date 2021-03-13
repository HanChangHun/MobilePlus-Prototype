package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public final class BooleanAtomicFormula extends AtomicFormula implements Parcelable {
  public static final Parcelable.Creator<BooleanAtomicFormula> CREATOR = new Parcelable.Creator<BooleanAtomicFormula>() {
      public AtomicFormula.BooleanAtomicFormula createFromParcel(Parcel param2Parcel) {
        return new AtomicFormula.BooleanAtomicFormula(param2Parcel);
      }
      
      public AtomicFormula.BooleanAtomicFormula[] newArray(int param2Int) {
        return new AtomicFormula.BooleanAtomicFormula[param2Int];
      }
    };
  
  private final Boolean mValue;
  
  public BooleanAtomicFormula(int paramInt) {
    super(paramInt);
    boolean bool;
    if (paramInt == 5 || paramInt == 6) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, String.format("Key %s cannot be used with BooleanAtomicFormula", new Object[] { keyToString(paramInt) }));
    this.mValue = null;
  }
  
  public BooleanAtomicFormula(int paramInt, boolean paramBoolean) {
    super(paramInt);
    boolean bool;
    if (paramInt == 5 || paramInt == 6) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, String.format("Key %s cannot be used with BooleanAtomicFormula", new Object[] { keyToString(paramInt) }));
    this.mValue = Boolean.valueOf(paramBoolean);
  }
  
  BooleanAtomicFormula(Parcel paramParcel) {
    super(paramParcel.readInt());
    boolean bool;
    if (paramParcel.readByte() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mValue = Boolean.valueOf(bool);
  }
  
  private static boolean getBooleanMetadataValue(AppInstallMetadata paramAppInstallMetadata, int paramInt) {
    StringBuilder stringBuilder;
    if (paramInt != 5) {
      if (paramInt == 6)
        return paramAppInstallMetadata.isStampTrusted(); 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected key in BooleanAtomicFormula: ");
      stringBuilder.append(paramInt);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    return stringBuilder.isPreInstalled();
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
    if (getKey() != paramObject.getKey() || this.mValue != ((BooleanAtomicFormula)paramObject).mValue)
      bool = false; 
    return bool;
  }
  
  public int getTag() {
    return 3;
  }
  
  public Boolean getValue() {
    return this.mValue;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(getKey()), this.mValue });
  }
  
  public boolean isAppCertificateFormula() {
    return false;
  }
  
  public boolean isInstallerFormula() {
    return false;
  }
  
  public boolean matches(AppInstallMetadata paramAppInstallMetadata) {
    Boolean bool = this.mValue;
    boolean bool1 = false;
    if (bool == null)
      return false; 
    if (getBooleanMetadataValue(paramAppInstallMetadata, getKey()) == this.mValue.booleanValue())
      bool1 = true; 
    return bool1;
  }
  
  public String toString() {
    return (this.mValue == null) ? String.format("(%s)", new Object[] { keyToString(getKey()) }) : String.format("(%s %s %s)", new Object[] { keyToString(getKey()), operatorToString(0), this.mValue });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mValue != null) {
      paramParcel.writeInt(getKey());
      paramParcel.writeByte((byte)this.mValue.booleanValue());
      return;
    } 
    throw new IllegalStateException("Cannot write an empty BooleanAtomicFormula.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula$BooleanAtomicFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */