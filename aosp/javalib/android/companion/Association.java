package android.companion;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;

public class Association implements Parcelable {
  public static final Parcelable.Creator<Association> CREATOR = new Parcelable.Creator<Association>() {
      public Association createFromParcel(Parcel param1Parcel) {
        return new Association(param1Parcel);
      }
      
      public Association[] newArray(int param1Int) {
        return new Association[param1Int];
      }
    };
  
  public final String companionAppPackage;
  
  public final String deviceAddress;
  
  public final int userId;
  
  public Association(int paramInt, String paramString1, String paramString2) {
    this.userId = paramInt;
    this.deviceAddress = paramString1;
    AnnotationValidations.validate(NonNull.class, null, paramString1);
    this.companionAppPackage = paramString2;
    AnnotationValidations.validate(NonNull.class, null, paramString2);
  }
  
  protected Association(Parcel paramParcel) {
    int i = paramParcel.readInt();
    String str2 = paramParcel.readString();
    String str1 = paramParcel.readString();
    this.userId = i;
    this.deviceAddress = str2;
    AnnotationValidations.validate(NonNull.class, null, str2);
    this.companionAppPackage = str1;
    AnnotationValidations.validate(NonNull.class, null, str1);
  }
  
  @Deprecated
  private void __metadata() {}
  
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
    if (this.userId != ((Association)paramObject).userId || !Objects.equals(this.deviceAddress, ((Association)paramObject).deviceAddress) || !Objects.equals(this.companionAppPackage, ((Association)paramObject).companionAppPackage))
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return ((1 * 31 + this.userId) * 31 + Objects.hashCode(this.deviceAddress)) * 31 + Objects.hashCode(this.companionAppPackage);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Association { userId = ");
    stringBuilder.append(this.userId);
    stringBuilder.append(", deviceAddress = ");
    stringBuilder.append(this.deviceAddress);
    stringBuilder.append(", companionAppPackage = ");
    stringBuilder.append(this.companionAppPackage);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.userId);
    paramParcel.writeString(this.deviceAddress);
    paramParcel.writeString(this.companionAppPackage);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/Association.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */