package android.app;

import android.content.LocusId;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public final class DirectAction implements Parcelable {
  public static final Parcelable.Creator<DirectAction> CREATOR = new Parcelable.Creator<DirectAction>() {
      public DirectAction createFromParcel(Parcel param1Parcel) {
        return new DirectAction(param1Parcel);
      }
      
      public DirectAction[] newArray(int param1Int) {
        return new DirectAction[param1Int];
      }
    };
  
  public static final String KEY_ACTIONS_LIST = "actions_list";
  
  private IBinder mActivityId;
  
  private final Bundle mExtras;
  
  private final String mID;
  
  private final LocusId mLocusId;
  
  private int mTaskId;
  
  public DirectAction(DirectAction paramDirectAction) {
    this.mTaskId = paramDirectAction.mTaskId;
    this.mActivityId = paramDirectAction.mActivityId;
    this.mID = paramDirectAction.mID;
    this.mExtras = paramDirectAction.mExtras;
    this.mLocusId = paramDirectAction.mLocusId;
  }
  
  private DirectAction(Parcel paramParcel) {
    this.mTaskId = paramParcel.readInt();
    this.mActivityId = paramParcel.readStrongBinder();
    this.mID = paramParcel.readString();
    this.mExtras = paramParcel.readBundle();
    String str = paramParcel.readString();
    if (str != null) {
      LocusId locusId = new LocusId(str);
    } else {
      str = null;
    } 
    this.mLocusId = (LocusId)str;
  }
  
  public DirectAction(String paramString, Bundle paramBundle, LocusId paramLocusId) {
    this.mID = (String)Preconditions.checkStringNotEmpty(paramString);
    this.mExtras = paramBundle;
    this.mLocusId = paramLocusId;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == null) ? false : ((paramObject == this) ? true : ((getClass() != paramObject.getClass()) ? false : this.mID.equals(((DirectAction)paramObject).mID)));
  }
  
  public IBinder getActivityId() {
    return this.mActivityId;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public String getId() {
    return this.mID;
  }
  
  public LocusId getLocusId() {
    return this.mLocusId;
  }
  
  public int getTaskId() {
    return this.mTaskId;
  }
  
  public int hashCode() {
    return this.mID.hashCode();
  }
  
  public void setSource(int paramInt, IBinder paramIBinder) {
    this.mTaskId = paramInt;
    this.mActivityId = paramIBinder;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mTaskId);
    paramParcel.writeStrongBinder(this.mActivityId);
    paramParcel.writeString(this.mID);
    paramParcel.writeBundle(this.mExtras);
    paramParcel.writeString(this.mLocusId.getId());
  }
  
  public static final class Builder {
    private Bundle mExtras;
    
    private String mId;
    
    private LocusId mLocusId;
    
    public Builder(String param1String) {
      Objects.requireNonNull(param1String);
      this.mId = param1String;
    }
    
    public DirectAction build() {
      return new DirectAction(this.mId, this.mExtras, this.mLocusId);
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setLocusId(LocusId param1LocusId) {
      this.mLocusId = param1LocusId;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DirectAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */