package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ValidationFailedException extends IllegalArgumentException implements Parcelable {
  public static final Parcelable.Creator<ValidationFailedException> CREATOR = new Parcelable.Creator<ValidationFailedException>() {
      public SystemUpdatePolicy.ValidationFailedException createFromParcel(Parcel param2Parcel) {
        return new SystemUpdatePolicy.ValidationFailedException(param2Parcel.readInt(), param2Parcel.readString());
      }
      
      public SystemUpdatePolicy.ValidationFailedException[] newArray(int param2Int) {
        return new SystemUpdatePolicy.ValidationFailedException[param2Int];
      }
    };
  
  public static final int ERROR_COMBINED_FREEZE_PERIOD_TOO_CLOSE = 6;
  
  public static final int ERROR_COMBINED_FREEZE_PERIOD_TOO_LONG = 5;
  
  public static final int ERROR_DUPLICATE_OR_OVERLAP = 2;
  
  public static final int ERROR_NEW_FREEZE_PERIOD_TOO_CLOSE = 4;
  
  public static final int ERROR_NEW_FREEZE_PERIOD_TOO_LONG = 3;
  
  public static final int ERROR_NONE = 0;
  
  public static final int ERROR_UNKNOWN = 1;
  
  private final int mErrorCode;
  
  private ValidationFailedException(int paramInt, String paramString) {
    super(paramString);
    this.mErrorCode = paramInt;
  }
  
  public static ValidationFailedException combinedPeriodTooClose(String paramString) {
    return new ValidationFailedException(6, paramString);
  }
  
  public static ValidationFailedException combinedPeriodTooLong(String paramString) {
    return new ValidationFailedException(5, paramString);
  }
  
  public static ValidationFailedException duplicateOrOverlapPeriods() {
    return new ValidationFailedException(2, "Found duplicate or overlapping periods");
  }
  
  public static ValidationFailedException freezePeriodTooClose(String paramString) {
    return new ValidationFailedException(4, paramString);
  }
  
  public static ValidationFailedException freezePeriodTooLong(String paramString) {
    return new ValidationFailedException(3, paramString);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getErrorCode() {
    return this.mErrorCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mErrorCode);
    paramParcel.writeString(getMessage());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface ValidationFailureType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdatePolicy$ValidationFailedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */