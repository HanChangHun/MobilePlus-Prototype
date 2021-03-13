package android.app.prediction;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SystemApi
public final class AppTargetEvent implements Parcelable {
  public static final int ACTION_DISMISS = 2;
  
  public static final int ACTION_LAUNCH = 1;
  
  public static final int ACTION_PIN = 3;
  
  public static final int ACTION_UNPIN = 4;
  
  public static final Parcelable.Creator<AppTargetEvent> CREATOR = new Parcelable.Creator<AppTargetEvent>() {
      public AppTargetEvent createFromParcel(Parcel param1Parcel) {
        return new AppTargetEvent(param1Parcel);
      }
      
      public AppTargetEvent[] newArray(int param1Int) {
        return new AppTargetEvent[param1Int];
      }
    };
  
  private final int mAction;
  
  private final String mLocation;
  
  private final AppTarget mTarget;
  
  private AppTargetEvent(AppTarget paramAppTarget, String paramString, int paramInt) {
    this.mTarget = paramAppTarget;
    this.mLocation = paramString;
    this.mAction = paramInt;
  }
  
  private AppTargetEvent(Parcel paramParcel) {
    this.mTarget = (AppTarget)paramParcel.readParcelable(null);
    this.mLocation = paramParcel.readString();
    this.mAction = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    Object object;
    Class<?> clazz = getClass();
    if (paramObject != null) {
      object = paramObject.getClass();
    } else {
      object = null;
    } 
    boolean bool = clazz.equals(object);
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (this.mTarget.equals(((AppTargetEvent)paramObject).mTarget) && this.mLocation.equals(((AppTargetEvent)paramObject).mLocation) && this.mAction == ((AppTargetEvent)paramObject).mAction)
      bool1 = true; 
    return bool1;
  }
  
  public int getAction() {
    return this.mAction;
  }
  
  public String getLaunchLocation() {
    return this.mLocation;
  }
  
  public AppTarget getTarget() {
    return this.mTarget;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable(this.mTarget, 0);
    paramParcel.writeString(this.mLocation);
    paramParcel.writeInt(this.mAction);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ActionType {}
  
  @SystemApi
  public static final class Builder {
    private int mAction;
    
    private String mLocation;
    
    private AppTarget mTarget;
    
    public Builder(AppTarget param1AppTarget, int param1Int) {
      this.mTarget = param1AppTarget;
      this.mAction = param1Int;
    }
    
    public AppTargetEvent build() {
      return new AppTargetEvent(this.mTarget, this.mLocation, this.mAction);
    }
    
    public Builder setLaunchLocation(String param1String) {
      this.mLocation = param1String;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTargetEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */