package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class CompoundFormula extends IntegrityFormula implements Parcelable {
  public static final int AND = 0;
  
  public static final Parcelable.Creator<CompoundFormula> CREATOR = new Parcelable.Creator<CompoundFormula>() {
      public CompoundFormula createFromParcel(Parcel param1Parcel) {
        return new CompoundFormula(param1Parcel);
      }
      
      public CompoundFormula[] newArray(int param1Int) {
        return new CompoundFormula[param1Int];
      }
    };
  
  public static final int NOT = 2;
  
  public static final int OR = 1;
  
  private final int mConnector;
  
  private final List<IntegrityFormula> mFormulas;
  
  public CompoundFormula(int paramInt, List<IntegrityFormula> paramList) {
    Preconditions.checkArgument(isValidConnector(paramInt), String.format("Unknown connector: %d", new Object[] { Integer.valueOf(paramInt) }));
    validateFormulas(paramInt, paramList);
    this.mConnector = paramInt;
    this.mFormulas = Collections.unmodifiableList(paramList);
  }
  
  CompoundFormula(Parcel paramParcel) {
    boolean bool;
    this.mConnector = paramParcel.readInt();
    int i = paramParcel.readInt();
    if (i >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Must have non-negative length. Got ");
    stringBuilder.append(i);
    Preconditions.checkArgument(bool, stringBuilder.toString());
    this.mFormulas = new ArrayList<>(i);
    for (byte b = 0; b < i; b++)
      this.mFormulas.add(IntegrityFormula.readFromParcel(paramParcel)); 
    validateFormulas(this.mConnector, this.mFormulas);
  }
  
  private static String connectorToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt == 2)
          return "NOT"; 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown connector ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      return "OR";
    } 
    return "AND";
  }
  
  private static boolean isValidConnector(int paramInt) {
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
  
  private static void validateFormulas(int paramInt, List<IntegrityFormula> paramList) {
    if (paramInt != 0 && paramInt != 1) {
      if (paramInt == 2) {
        boolean bool;
        if (paramList.size() == 1) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkArgument(bool, String.format("Connector %s must have 1 formula only", new Object[] { connectorToString(paramInt) }));
      } 
    } else {
      boolean bool;
      if (paramList.size() >= 2) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Connector %s must have at least 2 formulas", new Object[] { connectorToString(paramInt) }));
    } 
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
    if (this.mConnector != ((CompoundFormula)paramObject).mConnector || !this.mFormulas.equals(((CompoundFormula)paramObject).mFormulas))
      bool = false; 
    return bool;
  }
  
  public int getConnector() {
    return this.mConnector;
  }
  
  public List<IntegrityFormula> getFormulas() {
    return this.mFormulas;
  }
  
  public int getTag() {
    return 0;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mConnector), this.mFormulas });
  }
  
  public boolean isAppCertificateFormula() {
    return getFormulas().stream().anyMatch((Predicate)_$$Lambda$CompoundFormula$M_8xxsgv81_KQXrkqgoTu52hdSc.INSTANCE);
  }
  
  public boolean isInstallerFormula() {
    return getFormulas().stream().anyMatch((Predicate)_$$Lambda$CompoundFormula$uDdqwcHo8K9__Cad__RPzb_jKiw.INSTANCE);
  }
  
  public boolean matches(AppInstallMetadata paramAppInstallMetadata) {
    StringBuilder stringBuilder;
    int i = getConnector();
    if (i != 0) {
      if (i != 1) {
        if (i == 2)
          return ((IntegrityFormula)getFormulas().get(0)).matches(paramAppInstallMetadata) ^ true; 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown connector ");
        stringBuilder.append(getConnector());
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      return getFormulas().stream().anyMatch(new _$$Lambda$CompoundFormula$pW6rbPB_I2Vr7qv1hY_yfhAK2Fc((AppInstallMetadata)stringBuilder));
    } 
    return getFormulas().stream().allMatch(new _$$Lambda$CompoundFormula$eA9NEY6uQ5Etti30l5BCpddAf1g((AppInstallMetadata)stringBuilder));
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (this.mFormulas.size() == 1) {
      stringBuilder.append(String.format("%s ", new Object[] { connectorToString(this.mConnector) }));
      stringBuilder.append(((IntegrityFormula)this.mFormulas.get(0)).toString());
    } else {
      for (byte b = 0; b < this.mFormulas.size(); b++) {
        if (b > 0)
          stringBuilder.append(String.format(" %s ", new Object[] { connectorToString(this.mConnector) })); 
        stringBuilder.append(((IntegrityFormula)this.mFormulas.get(b)).toString());
      } 
    } 
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mConnector);
    paramParcel.writeInt(this.mFormulas.size());
    Iterator<IntegrityFormula> iterator = this.mFormulas.iterator();
    while (iterator.hasNext())
      IntegrityFormula.writeToParcel(iterator.next(), paramParcel, paramInt); 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Connector {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/CompoundFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */