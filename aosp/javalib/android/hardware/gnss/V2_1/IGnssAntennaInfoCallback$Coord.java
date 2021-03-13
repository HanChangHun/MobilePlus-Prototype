package android.hardware.gnss.V2_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class Coord {
  public double x = 0.0D;
  
  public double xUncertainty = 0.0D;
  
  public double y = 0.0D;
  
  public double yUncertainty = 0.0D;
  
  public double z = 0.0D;
  
  public double zUncertainty = 0.0D;
  
  public static final ArrayList<Coord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<Coord> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 48), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      Coord coord = new Coord();
      coord.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 48));
      arrayList.add(coord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<Coord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 48);
    for (byte b = 0; b < i; b++)
      ((Coord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 48)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != Coord.class)
      return false; 
    paramObject = paramObject;
    return (this.x != ((Coord)paramObject).x) ? false : ((this.xUncertainty != ((Coord)paramObject).xUncertainty) ? false : ((this.y != ((Coord)paramObject).y) ? false : ((this.yUncertainty != ((Coord)paramObject).yUncertainty) ? false : ((this.z != ((Coord)paramObject).z) ? false : (!(this.zUncertainty != ((Coord)paramObject).zUncertainty))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.x))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.xUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.y))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.yUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.z))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.zUncertainty))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.x = paramHwBlob.getDouble(0L + paramLong);
    this.xUncertainty = paramHwBlob.getDouble(8L + paramLong);
    this.y = paramHwBlob.getDouble(16L + paramLong);
    this.yUncertainty = paramHwBlob.getDouble(24L + paramLong);
    this.z = paramHwBlob.getDouble(32L + paramLong);
    this.zUncertainty = paramHwBlob.getDouble(40L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(48L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".x = ");
    stringBuilder.append(this.x);
    stringBuilder.append(", .xUncertainty = ");
    stringBuilder.append(this.xUncertainty);
    stringBuilder.append(", .y = ");
    stringBuilder.append(this.y);
    stringBuilder.append(", .yUncertainty = ");
    stringBuilder.append(this.yUncertainty);
    stringBuilder.append(", .z = ");
    stringBuilder.append(this.z);
    stringBuilder.append(", .zUncertainty = ");
    stringBuilder.append(this.zUncertainty);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putDouble(0L + paramLong, this.x);
    paramHwBlob.putDouble(8L + paramLong, this.xUncertainty);
    paramHwBlob.putDouble(16L + paramLong, this.y);
    paramHwBlob.putDouble(24L + paramLong, this.yUncertainty);
    paramHwBlob.putDouble(32L + paramLong, this.z);
    paramHwBlob.putDouble(40L + paramLong, this.zUncertainty);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(48);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssAntennaInfoCallback$Coord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */