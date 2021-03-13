package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class AuthenticationRequiredException extends SecurityException implements Parcelable {
  public static final Parcelable.Creator<AuthenticationRequiredException> CREATOR = new Parcelable.Creator<AuthenticationRequiredException>() {
      public AuthenticationRequiredException createFromParcel(Parcel param1Parcel) {
        return new AuthenticationRequiredException(param1Parcel);
      }
      
      public AuthenticationRequiredException[] newArray(int param1Int) {
        return new AuthenticationRequiredException[param1Int];
      }
    };
  
  private static final String TAG = "AuthenticationRequiredException";
  
  private final PendingIntent mUserAction;
  
  public AuthenticationRequiredException(Parcel paramParcel) {
    this(new SecurityException(paramParcel.readString()), (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel));
  }
  
  public AuthenticationRequiredException(Throwable paramThrowable, PendingIntent paramPendingIntent) {
    super(paramThrowable.getMessage());
    Objects.requireNonNull(paramPendingIntent);
    this.mUserAction = paramPendingIntent;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public PendingIntent getUserAction() {
    return this.mUserAction;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(getMessage());
    this.mUserAction.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AuthenticationRequiredException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */