package android.app;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.GraphicBuffer;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public class TaskSnapshot implements Parcelable {
  public static final Parcelable.Creator<TaskSnapshot> CREATOR = new Parcelable.Creator<TaskSnapshot>() {
      public ActivityManager.TaskSnapshot createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.TaskSnapshot(param2Parcel);
      }
      
      public ActivityManager.TaskSnapshot[] newArray(int param2Int) {
        return new ActivityManager.TaskSnapshot[param2Int];
      }
    };
  
  private final ColorSpace mColorSpace;
  
  private final Rect mContentInsets;
  
  private final long mId;
  
  private final boolean mIsLowResolution;
  
  private final boolean mIsRealSnapshot;
  
  private final boolean mIsTranslucent;
  
  private final int mOrientation;
  
  private int mRotation;
  
  private final GraphicBuffer mSnapshot;
  
  private final int mSystemUiVisibility;
  
  private final Point mTaskSize;
  
  private final ComponentName mTopActivityComponent;
  
  private final int mWindowingMode;
  
  public TaskSnapshot(long paramLong, ComponentName paramComponentName, GraphicBuffer paramGraphicBuffer, ColorSpace paramColorSpace, int paramInt1, int paramInt2, Point paramPoint, Rect paramRect, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, int paramInt4, boolean paramBoolean3) {
    ColorSpace colorSpace;
    this.mId = paramLong;
    this.mTopActivityComponent = paramComponentName;
    this.mSnapshot = paramGraphicBuffer;
    if (paramColorSpace.getId() < 0) {
      colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
    } else {
      colorSpace = paramColorSpace;
    } 
    this.mColorSpace = colorSpace;
    this.mOrientation = paramInt1;
    this.mRotation = paramInt2;
    this.mTaskSize = new Point(paramPoint);
    this.mContentInsets = new Rect(paramRect);
    this.mIsLowResolution = paramBoolean1;
    this.mIsRealSnapshot = paramBoolean2;
    this.mWindowingMode = paramInt3;
    this.mSystemUiVisibility = paramInt4;
    this.mIsTranslucent = paramBoolean3;
  }
  
  private TaskSnapshot(Parcel paramParcel) {
    ColorSpace colorSpace;
    this.mId = paramParcel.readLong();
    this.mTopActivityComponent = ComponentName.readFromParcel(paramParcel);
    this.mSnapshot = (GraphicBuffer)paramParcel.readParcelable(null);
    int i = paramParcel.readInt();
    if (i >= 0 && i < (ColorSpace.Named.values()).length) {
      colorSpace = ColorSpace.get(ColorSpace.Named.values()[i]);
    } else {
      colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
    } 
    this.mColorSpace = colorSpace;
    this.mOrientation = paramParcel.readInt();
    this.mRotation = paramParcel.readInt();
    this.mTaskSize = (Point)paramParcel.readParcelable(null);
    this.mContentInsets = (Rect)paramParcel.readParcelable(null);
    this.mIsLowResolution = paramParcel.readBoolean();
    this.mIsRealSnapshot = paramParcel.readBoolean();
    this.mWindowingMode = paramParcel.readInt();
    this.mSystemUiVisibility = paramParcel.readInt();
    this.mIsTranslucent = paramParcel.readBoolean();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public ColorSpace getColorSpace() {
    return this.mColorSpace;
  }
  
  public Rect getContentInsets() {
    return this.mContentInsets;
  }
  
  public long getId() {
    return this.mId;
  }
  
  public int getOrientation() {
    return this.mOrientation;
  }
  
  public int getRotation() {
    return this.mRotation;
  }
  
  public GraphicBuffer getSnapshot() {
    return this.mSnapshot;
  }
  
  public int getSystemUiVisibility() {
    return this.mSystemUiVisibility;
  }
  
  public Point getTaskSize() {
    return this.mTaskSize;
  }
  
  public ComponentName getTopActivityComponent() {
    return this.mTopActivityComponent;
  }
  
  public int getWindowingMode() {
    return this.mWindowingMode;
  }
  
  public boolean isLowResolution() {
    return this.mIsLowResolution;
  }
  
  public boolean isRealSnapshot() {
    return this.mIsRealSnapshot;
  }
  
  public boolean isTranslucent() {
    return this.mIsTranslucent;
  }
  
  public String toString() {
    boolean bool;
    GraphicBuffer graphicBuffer = this.mSnapshot;
    int i = 0;
    if (graphicBuffer != null) {
      bool = graphicBuffer.getWidth();
    } else {
      bool = false;
    } 
    graphicBuffer = this.mSnapshot;
    if (graphicBuffer != null)
      i = graphicBuffer.getHeight(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TaskSnapshot{ mId=");
    stringBuilder.append(this.mId);
    stringBuilder.append(" mTopActivityComponent=");
    stringBuilder.append(this.mTopActivityComponent.flattenToShortString());
    stringBuilder.append(" mSnapshot=");
    stringBuilder.append(this.mSnapshot);
    stringBuilder.append(" (");
    stringBuilder.append(bool);
    stringBuilder.append("x");
    stringBuilder.append(i);
    stringBuilder.append(") mColorSpace=");
    stringBuilder.append(this.mColorSpace.toString());
    stringBuilder.append(" mOrientation=");
    stringBuilder.append(this.mOrientation);
    stringBuilder.append(" mRotation=");
    stringBuilder.append(this.mRotation);
    stringBuilder.append(" mTaskSize=");
    stringBuilder.append(this.mTaskSize.toString());
    stringBuilder.append(" mContentInsets=");
    stringBuilder.append(this.mContentInsets.toShortString());
    stringBuilder.append(" mIsLowResolution=");
    stringBuilder.append(this.mIsLowResolution);
    stringBuilder.append(" mIsRealSnapshot=");
    stringBuilder.append(this.mIsRealSnapshot);
    stringBuilder.append(" mWindowingMode=");
    stringBuilder.append(this.mWindowingMode);
    stringBuilder.append(" mSystemUiVisibility=");
    stringBuilder.append(this.mSystemUiVisibility);
    stringBuilder.append(" mIsTranslucent=");
    stringBuilder.append(this.mIsTranslucent);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mId);
    ComponentName.writeToParcel(this.mTopActivityComponent, paramParcel);
    GraphicBuffer graphicBuffer = this.mSnapshot;
    if (graphicBuffer != null && !graphicBuffer.isDestroyed()) {
      graphicBuffer = this.mSnapshot;
    } else {
      graphicBuffer = null;
    } 
    paramParcel.writeParcelable((Parcelable)graphicBuffer, 0);
    paramParcel.writeInt(this.mColorSpace.getId());
    paramParcel.writeInt(this.mOrientation);
    paramParcel.writeInt(this.mRotation);
    paramParcel.writeParcelable((Parcelable)this.mTaskSize, 0);
    paramParcel.writeParcelable((Parcelable)this.mContentInsets, 0);
    paramParcel.writeBoolean(this.mIsLowResolution);
    paramParcel.writeBoolean(this.mIsRealSnapshot);
    paramParcel.writeInt(this.mWindowingMode);
    paramParcel.writeInt(this.mSystemUiVisibility);
    paramParcel.writeBoolean(this.mIsTranslucent);
  }
  
  public static final class Builder {
    private ColorSpace mColorSpace;
    
    private Rect mContentInsets;
    
    private long mId;
    
    private boolean mIsRealSnapshot;
    
    private boolean mIsTranslucent;
    
    private int mOrientation;
    
    private int mPixelFormat;
    
    private int mRotation;
    
    private GraphicBuffer mSnapshot;
    
    private int mSystemUiVisibility;
    
    private Point mTaskSize;
    
    private ComponentName mTopActivity;
    
    private int mWindowingMode;
    
    public ActivityManager.TaskSnapshot build() {
      return new ActivityManager.TaskSnapshot(this.mId, this.mTopActivity, this.mSnapshot, this.mColorSpace, this.mOrientation, this.mRotation, this.mTaskSize, this.mContentInsets, false, this.mIsRealSnapshot, this.mWindowingMode, this.mSystemUiVisibility, this.mIsTranslucent);
    }
    
    public int getPixelFormat() {
      return this.mPixelFormat;
    }
    
    public Builder setColorSpace(ColorSpace param2ColorSpace) {
      this.mColorSpace = param2ColorSpace;
      return this;
    }
    
    public Builder setContentInsets(Rect param2Rect) {
      this.mContentInsets = param2Rect;
      return this;
    }
    
    public Builder setId(long param2Long) {
      this.mId = param2Long;
      return this;
    }
    
    public Builder setIsRealSnapshot(boolean param2Boolean) {
      this.mIsRealSnapshot = param2Boolean;
      return this;
    }
    
    public Builder setIsTranslucent(boolean param2Boolean) {
      this.mIsTranslucent = param2Boolean;
      return this;
    }
    
    public Builder setOrientation(int param2Int) {
      this.mOrientation = param2Int;
      return this;
    }
    
    public Builder setPixelFormat(int param2Int) {
      this.mPixelFormat = param2Int;
      return this;
    }
    
    public Builder setRotation(int param2Int) {
      this.mRotation = param2Int;
      return this;
    }
    
    public Builder setSnapshot(GraphicBuffer param2GraphicBuffer) {
      this.mSnapshot = param2GraphicBuffer;
      return this;
    }
    
    public Builder setSystemUiVisibility(int param2Int) {
      this.mSystemUiVisibility = param2Int;
      return this;
    }
    
    public Builder setTaskSize(Point param2Point) {
      this.mTaskSize = param2Point;
      return this;
    }
    
    public Builder setTopActivityComponent(ComponentName param2ComponentName) {
      this.mTopActivity = param2ComponentName;
      return this;
    }
    
    public Builder setWindowingMode(int param2Int) {
      this.mWindowingMode = param2Int;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$TaskSnapshot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */