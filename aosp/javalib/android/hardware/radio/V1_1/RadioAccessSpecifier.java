package android.hardware.radio.V1_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class RadioAccessSpecifier {
  public ArrayList<Integer> channels = new ArrayList<>();
  
  public ArrayList<Integer> eutranBands = new ArrayList<>();
  
  public ArrayList<Integer> geranBands = new ArrayList<>();
  
  public int radioAccessNetwork = 0;
  
  public ArrayList<Integer> utranBands = new ArrayList<>();
  
  public static final ArrayList<RadioAccessSpecifier> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<RadioAccessSpecifier> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 72), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      RadioAccessSpecifier radioAccessSpecifier = new RadioAccessSpecifier();
      radioAccessSpecifier.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 72));
      arrayList.add(radioAccessSpecifier);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<RadioAccessSpecifier> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 72);
    for (byte b = 0; b < i; b++)
      ((RadioAccessSpecifier)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 72)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != RadioAccessSpecifier.class)
      return false; 
    paramObject = paramObject;
    return (this.radioAccessNetwork != ((RadioAccessSpecifier)paramObject).radioAccessNetwork) ? false : (!HidlSupport.deepEquals(this.geranBands, ((RadioAccessSpecifier)paramObject).geranBands) ? false : (!HidlSupport.deepEquals(this.utranBands, ((RadioAccessSpecifier)paramObject).utranBands) ? false : (!HidlSupport.deepEquals(this.eutranBands, ((RadioAccessSpecifier)paramObject).eutranBands) ? false : (!!HidlSupport.deepEquals(this.channels, ((RadioAccessSpecifier)paramObject).channels)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.radioAccessNetwork))), Integer.valueOf(HidlSupport.deepHashCode(this.geranBands)), Integer.valueOf(HidlSupport.deepHashCode(this.utranBands)), Integer.valueOf(HidlSupport.deepHashCode(this.eutranBands)), Integer.valueOf(HidlSupport.deepHashCode(this.channels)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.radioAccessNetwork = paramHwBlob.getInt32(paramLong + 0L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.geranBands.clear();
    byte b;
    for (b = 0; b < i; b++) {
      int j = hwBlob2.getInt32((b * 4));
      this.geranBands.add(Integer.valueOf(j));
    } 
    i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.utranBands.clear();
    for (b = 0; b < i; b++) {
      int j = hwBlob2.getInt32((b * 4));
      this.utranBands.add(Integer.valueOf(j));
    } 
    i = paramHwBlob.getInt32(paramLong + 40L + 8L);
    hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 40L + 0L, true);
    this.eutranBands.clear();
    for (b = 0; b < i; b++) {
      int j = hwBlob2.getInt32((b * 4));
      this.eutranBands.add(Integer.valueOf(j));
    } 
    i = paramHwBlob.getInt32(paramLong + 56L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 56L + 0L, true);
    this.channels.clear();
    for (b = 0; b < i; b++) {
      int j = hwBlob1.getInt32((b * 4));
      this.channels.add(Integer.valueOf(j));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(72L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".radioAccessNetwork = ");
    stringBuilder.append(RadioAccessNetworks.toString(this.radioAccessNetwork));
    stringBuilder.append(", .geranBands = ");
    stringBuilder.append(this.geranBands);
    stringBuilder.append(", .utranBands = ");
    stringBuilder.append(this.utranBands);
    stringBuilder.append(", .eutranBands = ");
    stringBuilder.append(this.eutranBands);
    stringBuilder.append(", .channels = ");
    stringBuilder.append(this.channels);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.radioAccessNetwork);
    int i = this.geranBands.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 4);
    byte b;
    for (b = 0; b < i; b++)
      hwBlob.putInt32((b * 4), ((Integer)this.geranBands.get(b)).intValue()); 
    paramHwBlob.putBlob(paramLong + 8L + 0L, hwBlob);
    i = this.utranBands.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    hwBlob = new HwBlob(i * 4);
    for (b = 0; b < i; b++)
      hwBlob.putInt32((b * 4), ((Integer)this.utranBands.get(b)).intValue()); 
    paramHwBlob.putBlob(paramLong + 24L + 0L, hwBlob);
    i = this.eutranBands.size();
    paramHwBlob.putInt32(paramLong + 40L + 8L, i);
    paramHwBlob.putBool(paramLong + 40L + 12L, false);
    hwBlob = new HwBlob(i * 4);
    for (b = 0; b < i; b++)
      hwBlob.putInt32((b * 4), ((Integer)this.eutranBands.get(b)).intValue()); 
    paramHwBlob.putBlob(paramLong + 40L + 0L, hwBlob);
    i = this.channels.size();
    paramHwBlob.putInt32(paramLong + 56L + 8L, i);
    paramHwBlob.putBool(paramLong + 56L + 12L, false);
    hwBlob = new HwBlob(i * 4);
    for (b = 0; b < i; b++)
      hwBlob.putInt32((b * 4), ((Integer)this.channels.get(b)).intValue()); 
    paramHwBlob.putBlob(paramLong + 56L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(72);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/RadioAccessSpecifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */