package android.content.pm;

import android.os.Parcel;

public final class WindowLayout {
  public final int gravity;
  
  public final int height;
  
  public final float heightFraction;
  
  public final int minHeight;
  
  public final int minWidth;
  
  public final int width;
  
  public final float widthFraction;
  
  public String windowLayoutAffinity;
  
  public WindowLayout(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, int paramInt3, int paramInt4, int paramInt5) {
    this.width = paramInt1;
    this.widthFraction = paramFloat1;
    this.height = paramInt2;
    this.heightFraction = paramFloat2;
    this.gravity = paramInt3;
    this.minWidth = paramInt4;
    this.minHeight = paramInt5;
  }
  
  public WindowLayout(Parcel paramParcel) {
    this.width = paramParcel.readInt();
    this.widthFraction = paramParcel.readFloat();
    this.height = paramParcel.readInt();
    this.heightFraction = paramParcel.readFloat();
    this.gravity = paramParcel.readInt();
    this.minWidth = paramParcel.readInt();
    this.minHeight = paramParcel.readInt();
    this.windowLayoutAffinity = paramParcel.readString8();
  }
  
  public boolean hasSpecifiedSize() {
    return (this.width >= 0 || this.height >= 0 || this.widthFraction >= 0.0F || this.heightFraction >= 0.0F);
  }
  
  public void writeToParcel(Parcel paramParcel) {
    paramParcel.writeInt(this.width);
    paramParcel.writeFloat(this.widthFraction);
    paramParcel.writeInt(this.height);
    paramParcel.writeFloat(this.heightFraction);
    paramParcel.writeInt(this.gravity);
    paramParcel.writeInt(this.minWidth);
    paramParcel.writeInt(this.minHeight);
    paramParcel.writeString8(this.windowLayoutAffinity);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ActivityInfo$WindowLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */