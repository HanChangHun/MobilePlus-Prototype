package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class SelectionsRequest implements Parcelable {
  public static final Parcelable.Creator<SelectionsRequest> CREATOR = new Parcelable.Creator<SelectionsRequest>() {
      public SelectionsRequest createFromParcel(Parcel param1Parcel) {
        return new SelectionsRequest(param1Parcel.readInt(), (Point)param1Parcel.readTypedObject(Point.CREATOR), param1Parcel.readBundle());
      }
      
      public SelectionsRequest[] newArray(int param1Int) {
        return new SelectionsRequest[param1Int];
      }
    };
  
  private final Bundle mExtras;
  
  private final Point mInterestPoint;
  
  private final int mTaskId;
  
  private SelectionsRequest(int paramInt, Point paramPoint, Bundle paramBundle) {
    this.mTaskId = paramInt;
    this.mInterestPoint = paramPoint;
    this.mExtras = paramBundle;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Bundle getExtras() {
    Bundle bundle1 = this.mExtras;
    Bundle bundle2 = bundle1;
    if (bundle1 == null)
      bundle2 = new Bundle(); 
    return bundle2;
  }
  
  public Point getInterestPoint() {
    return this.mInterestPoint;
  }
  
  public int getTaskId() {
    return this.mTaskId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mTaskId);
    paramParcel.writeTypedObject((Parcelable)this.mInterestPoint, paramInt);
    paramParcel.writeBundle(this.mExtras);
  }
  
  @SystemApi
  public static final class Builder {
    private Bundle mExtras;
    
    private Point mInterestPoint;
    
    private final int mTaskId;
    
    public Builder(int param1Int) {
      this.mTaskId = param1Int;
    }
    
    public SelectionsRequest build() {
      return new SelectionsRequest(this.mTaskId, this.mInterestPoint, this.mExtras);
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setInterestPoint(Point param1Point) {
      this.mInterestPoint = param1Point;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/SelectionsRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */