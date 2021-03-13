package android.app;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Rational;
import java.util.ArrayList;
import java.util.List;

public final class PictureInPictureParams implements Parcelable {
  public static final Parcelable.Creator<PictureInPictureParams> CREATOR = new Parcelable.Creator<PictureInPictureParams>() {
      public PictureInPictureParams createFromParcel(Parcel param1Parcel) {
        return new PictureInPictureParams(param1Parcel);
      }
      
      public PictureInPictureParams[] newArray(int param1Int) {
        return new PictureInPictureParams[param1Int];
      }
    };
  
  private Rational mAspectRatio;
  
  private Rect mSourceRectHint;
  
  private List<RemoteAction> mUserActions;
  
  PictureInPictureParams() {}
  
  PictureInPictureParams(Parcel paramParcel) {
    if (paramParcel.readInt() != 0)
      this.mAspectRatio = new Rational(paramParcel.readInt(), paramParcel.readInt()); 
    if (paramParcel.readInt() != 0) {
      ArrayList<RemoteAction> arrayList = new ArrayList();
      this.mUserActions = arrayList;
      paramParcel.readParcelableList(arrayList, RemoteAction.class.getClassLoader());
    } 
    if (paramParcel.readInt() != 0)
      this.mSourceRectHint = (Rect)Rect.CREATOR.createFromParcel(paramParcel); 
  }
  
  PictureInPictureParams(Rational paramRational, List<RemoteAction> paramList, Rect paramRect) {
    this.mAspectRatio = paramRational;
    this.mUserActions = paramList;
    this.mSourceRectHint = paramRect;
  }
  
  public void copyOnlySet(PictureInPictureParams paramPictureInPictureParams) {
    if (paramPictureInPictureParams.hasSetAspectRatio())
      this.mAspectRatio = paramPictureInPictureParams.mAspectRatio; 
    if (paramPictureInPictureParams.hasSetActions())
      this.mUserActions = paramPictureInPictureParams.mUserActions; 
    if (paramPictureInPictureParams.hasSourceBoundsHint())
      this.mSourceRectHint = new Rect(paramPictureInPictureParams.getSourceRectHint()); 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean empty() {
    boolean bool;
    if (!hasSourceBoundsHint() && !hasSetActions() && !hasSetAspectRatio()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public List<RemoteAction> getActions() {
    return this.mUserActions;
  }
  
  public float getAspectRatio() {
    Rational rational = this.mAspectRatio;
    return (rational != null) ? rational.floatValue() : 0.0F;
  }
  
  public Rational getAspectRatioRational() {
    return this.mAspectRatio;
  }
  
  public Rect getSourceRectHint() {
    return this.mSourceRectHint;
  }
  
  public boolean hasSetActions() {
    boolean bool;
    if (this.mUserActions != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasSetAspectRatio() {
    boolean bool;
    if (this.mAspectRatio != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasSourceBoundsHint() {
    boolean bool;
    Rect rect = this.mSourceRectHint;
    if (rect != null && !rect.isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void truncateActions(int paramInt) {
    if (hasSetActions()) {
      List<RemoteAction> list = this.mUserActions;
      this.mUserActions = list.subList(0, Math.min(list.size(), paramInt));
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mAspectRatio != null) {
      paramParcel.writeInt(1);
      paramParcel.writeInt(this.mAspectRatio.getNumerator());
      paramParcel.writeInt(this.mAspectRatio.getDenominator());
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mUserActions != null) {
      paramParcel.writeInt(1);
      paramParcel.writeParcelableList(this.mUserActions, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mSourceRectHint != null) {
      paramParcel.writeInt(1);
      this.mSourceRectHint.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
  }
  
  public static class Builder {
    private Rational mAspectRatio;
    
    private Rect mSourceRectHint;
    
    private List<RemoteAction> mUserActions;
    
    public PictureInPictureParams build() {
      return new PictureInPictureParams(this.mAspectRatio, this.mUserActions, this.mSourceRectHint);
    }
    
    public Builder setActions(List<RemoteAction> param1List) {
      if (this.mUserActions != null)
        this.mUserActions = null; 
      if (param1List != null)
        this.mUserActions = new ArrayList<>(param1List); 
      return this;
    }
    
    public Builder setAspectRatio(Rational param1Rational) {
      this.mAspectRatio = param1Rational;
      return this;
    }
    
    public Builder setSourceRectHint(Rect param1Rect) {
      if (param1Rect == null) {
        this.mSourceRectHint = null;
      } else {
        this.mSourceRectHint = new Rect(param1Rect);
      } 
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PictureInPictureParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */