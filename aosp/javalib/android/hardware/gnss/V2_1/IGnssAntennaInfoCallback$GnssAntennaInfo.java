package android.hardware.gnss.V2_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssAntennaInfo {
  public double carrierFrequencyMHz = 0.0D;
  
  public IGnssAntennaInfoCallback.Coord phaseCenterOffsetCoordinateMillimeters = new IGnssAntennaInfoCallback.Coord();
  
  public ArrayList<IGnssAntennaInfoCallback.Row> phaseCenterVariationCorrectionMillimeters = new ArrayList<>();
  
  public ArrayList<IGnssAntennaInfoCallback.Row> phaseCenterVariationCorrectionUncertaintyMillimeters = new ArrayList<>();
  
  public ArrayList<IGnssAntennaInfoCallback.Row> signalGainCorrectionDbi = new ArrayList<>();
  
  public ArrayList<IGnssAntennaInfoCallback.Row> signalGainCorrectionUncertaintyDbi = new ArrayList<>();
  
  public static final ArrayList<GnssAntennaInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssAntennaInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 120), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssAntennaInfo gnssAntennaInfo = new GnssAntennaInfo();
      gnssAntennaInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 120));
      arrayList.add(gnssAntennaInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssAntennaInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 120);
    for (byte b = 0; b < i; b++)
      ((GnssAntennaInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 120)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssAntennaInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.carrierFrequencyMHz != ((GnssAntennaInfo)paramObject).carrierFrequencyMHz) ? false : (!HidlSupport.deepEquals(this.phaseCenterOffsetCoordinateMillimeters, ((GnssAntennaInfo)paramObject).phaseCenterOffsetCoordinateMillimeters) ? false : (!HidlSupport.deepEquals(this.phaseCenterVariationCorrectionMillimeters, ((GnssAntennaInfo)paramObject).phaseCenterVariationCorrectionMillimeters) ? false : (!HidlSupport.deepEquals(this.phaseCenterVariationCorrectionUncertaintyMillimeters, ((GnssAntennaInfo)paramObject).phaseCenterVariationCorrectionUncertaintyMillimeters) ? false : (!HidlSupport.deepEquals(this.signalGainCorrectionDbi, ((GnssAntennaInfo)paramObject).signalGainCorrectionDbi) ? false : (!!HidlSupport.deepEquals(this.signalGainCorrectionUncertaintyDbi, ((GnssAntennaInfo)paramObject).signalGainCorrectionUncertaintyDbi))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierFrequencyMHz))), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterOffsetCoordinateMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterVariationCorrectionMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterVariationCorrectionUncertaintyMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.signalGainCorrectionDbi)), Integer.valueOf(HidlSupport.deepHashCode(this.signalGainCorrectionUncertaintyDbi)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.carrierFrequencyMHz = paramHwBlob.getDouble(paramLong + 0L);
    this.phaseCenterOffsetCoordinateMillimeters.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 8L);
    int i = paramHwBlob.getInt32(paramLong + 56L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 56L + 0L, true);
    this.phaseCenterVariationCorrectionMillimeters.clear();
    byte b;
    for (b = 0; b < i; b++) {
      IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
      row.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 16));
      this.phaseCenterVariationCorrectionMillimeters.add(row);
    } 
    i = paramHwBlob.getInt32(paramLong + 72L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 72L + 0L, true);
    this.phaseCenterVariationCorrectionUncertaintyMillimeters.clear();
    for (b = 0; b < i; b++) {
      IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
      row.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 16));
      this.phaseCenterVariationCorrectionUncertaintyMillimeters.add(row);
    } 
    i = paramHwBlob.getInt32(paramLong + 88L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 88L + 0L, true);
    this.signalGainCorrectionDbi.clear();
    for (b = 0; b < i; b++) {
      IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
      row.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 16));
      this.signalGainCorrectionDbi.add(row);
    } 
    i = paramHwBlob.getInt32(paramLong + 104L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 104L + 0L, true);
    this.signalGainCorrectionUncertaintyDbi.clear();
    for (b = 0; b < i; b++) {
      IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
      row.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 16));
      this.signalGainCorrectionUncertaintyDbi.add(row);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(120L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".carrierFrequencyMHz = ");
    stringBuilder.append(this.carrierFrequencyMHz);
    stringBuilder.append(", .phaseCenterOffsetCoordinateMillimeters = ");
    stringBuilder.append(this.phaseCenterOffsetCoordinateMillimeters);
    stringBuilder.append(", .phaseCenterVariationCorrectionMillimeters = ");
    stringBuilder.append(this.phaseCenterVariationCorrectionMillimeters);
    stringBuilder.append(", .phaseCenterVariationCorrectionUncertaintyMillimeters = ");
    stringBuilder.append(this.phaseCenterVariationCorrectionUncertaintyMillimeters);
    stringBuilder.append(", .signalGainCorrectionDbi = ");
    stringBuilder.append(this.signalGainCorrectionDbi);
    stringBuilder.append(", .signalGainCorrectionUncertaintyDbi = ");
    stringBuilder.append(this.signalGainCorrectionUncertaintyDbi);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putDouble(paramLong + 0L, this.carrierFrequencyMHz);
    this.phaseCenterOffsetCoordinateMillimeters.writeEmbeddedToBlob(paramHwBlob, paramLong + 8L);
    int i = this.phaseCenterVariationCorrectionMillimeters.size();
    paramHwBlob.putInt32(paramLong + 56L + 8L, i);
    paramHwBlob.putBool(paramLong + 56L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 16);
    byte b;
    for (b = 0; b < i; b++)
      ((IGnssAntennaInfoCallback.Row)this.phaseCenterVariationCorrectionMillimeters.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 56L + 0L, hwBlob);
    i = this.phaseCenterVariationCorrectionUncertaintyMillimeters.size();
    paramHwBlob.putInt32(paramLong + 72L + 8L, i);
    paramHwBlob.putBool(paramLong + 72L + 12L, false);
    hwBlob = new HwBlob(i * 16);
    for (b = 0; b < i; b++)
      ((IGnssAntennaInfoCallback.Row)this.phaseCenterVariationCorrectionUncertaintyMillimeters.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 72L + 0L, hwBlob);
    i = this.signalGainCorrectionDbi.size();
    paramHwBlob.putInt32(paramLong + 88L + 8L, i);
    paramHwBlob.putBool(paramLong + 88L + 12L, false);
    hwBlob = new HwBlob(i * 16);
    for (b = 0; b < i; b++)
      ((IGnssAntennaInfoCallback.Row)this.signalGainCorrectionDbi.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 88L + 0L, hwBlob);
    i = this.signalGainCorrectionUncertaintyDbi.size();
    paramHwBlob.putInt32(paramLong + 104L + 8L, i);
    paramHwBlob.putBool(paramLong + 104L + 12L, false);
    hwBlob = new HwBlob(i * 16);
    for (b = 0; b < i; b++)
      ((IGnssAntennaInfoCallback.Row)this.signalGainCorrectionUncertaintyDbi.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 104L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(120);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssAntennaInfoCallback$GnssAntennaInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */