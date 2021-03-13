package android.app.prediction;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class AppPredictionContext implements Parcelable {
  public static final Parcelable.Creator<AppPredictionContext> CREATOR = new Parcelable.Creator<AppPredictionContext>() {
      public AppPredictionContext createFromParcel(Parcel param1Parcel) {
        return new AppPredictionContext(param1Parcel);
      }
      
      public AppPredictionContext[] newArray(int param1Int) {
        return new AppPredictionContext[param1Int];
      }
    };
  
  private final Bundle mExtras;
  
  private final String mPackageName;
  
  private final int mPredictedTargetCount;
  
  private final String mUiSurface;
  
  private AppPredictionContext(Parcel paramParcel) {
    this.mUiSurface = paramParcel.readString();
    this.mPredictedTargetCount = paramParcel.readInt();
    this.mPackageName = paramParcel.readString();
    this.mExtras = paramParcel.readBundle();
  }
  
  private AppPredictionContext(String paramString1, int paramInt, String paramString2, Bundle paramBundle) {
    this.mUiSurface = paramString1;
    this.mPredictedTargetCount = paramInt;
    this.mPackageName = paramString2;
    this.mExtras = paramBundle;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    Object object;
    boolean bool = true;
    if (paramObject == this)
      return true; 
    Class<?> clazz = getClass();
    if (paramObject != null) {
      object = paramObject.getClass();
    } else {
      object = null;
    } 
    if (!clazz.equals(object))
      return false; 
    paramObject = paramObject;
    if (this.mPredictedTargetCount != ((AppPredictionContext)paramObject).mPredictedTargetCount || !this.mUiSurface.equals(((AppPredictionContext)paramObject).mUiSurface) || !this.mPackageName.equals(((AppPredictionContext)paramObject).mPackageName))
      bool = false; 
    return bool;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getPredictedTargetCount() {
    return this.mPredictedTargetCount;
  }
  
  public String getUiSurface() {
    return this.mUiSurface;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mUiSurface);
    paramParcel.writeInt(this.mPredictedTargetCount);
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeBundle(this.mExtras);
  }
  
  @SystemApi
  public static final class Builder {
    private Bundle mExtras;
    
    private final String mPackageName;
    
    private int mPredictedTargetCount;
    
    private String mUiSurface;
    
    @SystemApi
    public Builder(Context param1Context) {
      this.mPackageName = param1Context.getPackageName();
    }
    
    public AppPredictionContext build() {
      return new AppPredictionContext(this.mUiSurface, this.mPredictedTargetCount, this.mPackageName, this.mExtras);
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setPredictedTargetCount(int param1Int) {
      this.mPredictedTargetCount = param1Int;
      return this;
    }
    
    public Builder setUiSurface(String param1String) {
      this.mUiSurface = param1String;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */