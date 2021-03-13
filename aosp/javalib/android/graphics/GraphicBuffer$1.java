package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<GraphicBuffer> {
  public GraphicBuffer createFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    int m = paramParcel.readInt();
    long l = GraphicBuffer.access$000(paramParcel);
    return (l != 0L) ? new GraphicBuffer(i, j, k, m, l, null) : null;
  }
  
  public GraphicBuffer[] newArray(int paramInt) {
    return new GraphicBuffer[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicBuffer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */