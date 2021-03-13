package android.hardware.display;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@SystemApi
public final class AmbientBrightnessDayStats implements Parcelable {
  public static final Parcelable.Creator<AmbientBrightnessDayStats> CREATOR = new Parcelable.Creator<AmbientBrightnessDayStats>() {
      public AmbientBrightnessDayStats createFromParcel(Parcel param1Parcel) {
        return new AmbientBrightnessDayStats(param1Parcel);
      }
      
      public AmbientBrightnessDayStats[] newArray(int param1Int) {
        return new AmbientBrightnessDayStats[param1Int];
      }
    };
  
  private final float[] mBucketBoundaries;
  
  private final LocalDate mLocalDate;
  
  private final float[] mStats;
  
  private AmbientBrightnessDayStats(Parcel paramParcel) {
    this.mLocalDate = LocalDate.parse(paramParcel.readString());
    this.mBucketBoundaries = paramParcel.createFloatArray();
    this.mStats = paramParcel.createFloatArray();
  }
  
  public AmbientBrightnessDayStats(LocalDate paramLocalDate, float[] paramArrayOffloat) {
    this(paramLocalDate, paramArrayOffloat, null);
  }
  
  public AmbientBrightnessDayStats(LocalDate paramLocalDate, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    Objects.requireNonNull(paramLocalDate);
    Objects.requireNonNull(paramArrayOffloat1);
    Preconditions.checkArrayElementsInRange(paramArrayOffloat1, 0.0F, Float.MAX_VALUE, "bucketBoundaries");
    if (paramArrayOffloat1.length >= 1) {
      checkSorted(paramArrayOffloat1);
      if (paramArrayOffloat2 == null) {
        paramArrayOffloat2 = new float[paramArrayOffloat1.length];
      } else {
        Preconditions.checkArrayElementsInRange(paramArrayOffloat2, 0.0F, Float.MAX_VALUE, "stats");
        if (paramArrayOffloat1.length != paramArrayOffloat2.length)
          throw new IllegalArgumentException("Bucket boundaries and stats must be of same size."); 
      } 
      this.mLocalDate = paramLocalDate;
      this.mBucketBoundaries = paramArrayOffloat1;
      this.mStats = paramArrayOffloat2;
      return;
    } 
    throw new IllegalArgumentException("Bucket boundaries must contain at least 1 value");
  }
  
  private static void checkSorted(float[] paramArrayOffloat) {
    if (paramArrayOffloat.length <= 1)
      return; 
    float f = paramArrayOffloat[0];
    for (byte b = 1; b < paramArrayOffloat.length; b++) {
      boolean bool;
      if (f < paramArrayOffloat[b]) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool);
      f = paramArrayOffloat[b];
    } 
  }
  
  private int getBucketIndex(float paramFloat) {
    float[] arrayOfFloat = this.mBucketBoundaries;
    if (paramFloat < arrayOfFloat[0])
      return -1; 
    byte b = 0;
    int i = arrayOfFloat.length - 1;
    while (b < i) {
      byte b1;
      int j = (b + i) / 2;
      arrayOfFloat = this.mBucketBoundaries;
      if (arrayOfFloat[j] <= paramFloat && paramFloat < arrayOfFloat[j + 1])
        return j; 
      arrayOfFloat = this.mBucketBoundaries;
      if (arrayOfFloat[j] < paramFloat) {
        b1 = j + 1;
      } else {
        b1 = b;
        if (arrayOfFloat[j] > paramFloat) {
          i = j - 1;
          b1 = b;
        } 
      } 
      b = b1;
    } 
    return b;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!this.mLocalDate.equals(((AmbientBrightnessDayStats)paramObject).mLocalDate) || !Arrays.equals(this.mBucketBoundaries, ((AmbientBrightnessDayStats)paramObject).mBucketBoundaries) || !Arrays.equals(this.mStats, ((AmbientBrightnessDayStats)paramObject).mStats))
      bool = false; 
    return bool;
  }
  
  public float[] getBucketBoundaries() {
    return this.mBucketBoundaries;
  }
  
  public LocalDate getLocalDate() {
    return this.mLocalDate;
  }
  
  public float[] getStats() {
    return this.mStats;
  }
  
  public int hashCode() {
    return ((1 * 31 + this.mLocalDate.hashCode()) * 31 + Arrays.hashCode(this.mBucketBoundaries)) * 31 + Arrays.hashCode(this.mStats);
  }
  
  public void log(float paramFloat1, float paramFloat2) {
    int i = getBucketIndex(paramFloat1);
    if (i >= 0) {
      float[] arrayOfFloat = this.mStats;
      arrayOfFloat[i] = arrayOfFloat[i] + paramFloat2;
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();
    for (byte b = 0; b < this.mBucketBoundaries.length; b++) {
      if (b != 0) {
        stringBuilder1.append(", ");
        stringBuilder2.append(", ");
      } 
      stringBuilder1.append(this.mBucketBoundaries[b]);
      stringBuilder2.append(this.mStats[b]);
    } 
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(this.mLocalDate);
    stringBuilder3.append(" ");
    stringBuilder3.append("{");
    stringBuilder3.append(stringBuilder1);
    stringBuilder3.append("} ");
    stringBuilder3.append("{");
    stringBuilder3.append(stringBuilder2);
    stringBuilder3.append("}");
    return stringBuilder3.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mLocalDate.toString());
    paramParcel.writeFloatArray(this.mBucketBoundaries);
    paramParcel.writeFloatArray(this.mStats);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/AmbientBrightnessDayStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */