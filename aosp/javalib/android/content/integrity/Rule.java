package android.content.integrity;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

@SystemApi
public final class Rule implements Parcelable {
  public static final Parcelable.Creator<Rule> CREATOR = new Parcelable.Creator<Rule>() {
      public Rule createFromParcel(Parcel param1Parcel) {
        return new Rule(param1Parcel);
      }
      
      public Rule[] newArray(int param1Int) {
        return new Rule[param1Int];
      }
    };
  
  public static final int DENY = 0;
  
  public static final int FORCE_ALLOW = 1;
  
  private final int mEffect;
  
  private final IntegrityFormula mFormula;
  
  public Rule(IntegrityFormula paramIntegrityFormula, int paramInt) {
    Preconditions.checkArgument(isValidEffect(paramInt), String.format("Unknown effect: %d", new Object[] { Integer.valueOf(paramInt) }));
    Objects.requireNonNull(paramIntegrityFormula);
    this.mFormula = paramIntegrityFormula;
    this.mEffect = paramInt;
  }
  
  Rule(Parcel paramParcel) {
    this.mFormula = IntegrityFormula.readFromParcel(paramParcel);
    this.mEffect = paramParcel.readInt();
  }
  
  private static String effectToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt == 1)
        return "FORCE_ALLOW"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown effect ");
      stringBuilder.append(paramInt);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return "DENY";
  }
  
  private static boolean isValidEffect(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 0)
      if (paramInt == 1) {
        bool2 = bool1;
      } else {
        bool2 = false;
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
    if (this.mEffect != ((Rule)paramObject).mEffect || !Objects.equals(this.mFormula, ((Rule)paramObject).mFormula))
      bool = false; 
    return bool;
  }
  
  public int getEffect() {
    return this.mEffect;
  }
  
  public IntegrityFormula getFormula() {
    return this.mFormula;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mFormula, Integer.valueOf(this.mEffect) });
  }
  
  public String toString() {
    return String.format("Rule: %s, %s", new Object[] { this.mFormula, effectToString(this.mEffect) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    IntegrityFormula.writeToParcel(this.mFormula, paramParcel, paramInt);
    paramParcel.writeInt(this.mEffect);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Effect {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/Rule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */