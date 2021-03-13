package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CardStatus {
  public ArrayList<AppStatus> applications = new ArrayList<>();
  
  public int cardState = 0;
  
  public int cdmaSubscriptionAppIndex = 0;
  
  public int gsmUmtsSubscriptionAppIndex = 0;
  
  public int imsSubscriptionAppIndex = 0;
  
  public int universalPinState = 0;
  
  public static final ArrayList<CardStatus> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CardStatus> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CardStatus cardStatus = new CardStatus();
      cardStatus.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 40));
      arrayList.add(cardStatus);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CardStatus> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((CardStatus)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CardStatus.class)
      return false; 
    paramObject = paramObject;
    return (this.cardState != ((CardStatus)paramObject).cardState) ? false : ((this.universalPinState != ((CardStatus)paramObject).universalPinState) ? false : ((this.gsmUmtsSubscriptionAppIndex != ((CardStatus)paramObject).gsmUmtsSubscriptionAppIndex) ? false : ((this.cdmaSubscriptionAppIndex != ((CardStatus)paramObject).cdmaSubscriptionAppIndex) ? false : ((this.imsSubscriptionAppIndex != ((CardStatus)paramObject).imsSubscriptionAppIndex) ? false : (!!HidlSupport.deepEquals(this.applications, ((CardStatus)paramObject).applications))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cardState))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.universalPinState))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.gsmUmtsSubscriptionAppIndex))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cdmaSubscriptionAppIndex))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.imsSubscriptionAppIndex))), Integer.valueOf(HidlSupport.deepHashCode(this.applications)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cardState = paramHwBlob.getInt32(paramLong + 0L);
    this.universalPinState = paramHwBlob.getInt32(paramLong + 4L);
    this.gsmUmtsSubscriptionAppIndex = paramHwBlob.getInt32(paramLong + 8L);
    this.cdmaSubscriptionAppIndex = paramHwBlob.getInt32(paramLong + 12L);
    this.imsSubscriptionAppIndex = paramHwBlob.getInt32(paramLong + 16L);
    int i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 64), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.applications.clear();
    for (byte b = 0; b < i; b++) {
      AppStatus appStatus = new AppStatus();
      appStatus.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 64));
      this.applications.add(appStatus);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cardState = ");
    stringBuilder.append(CardState.toString(this.cardState));
    stringBuilder.append(", .universalPinState = ");
    stringBuilder.append(PinState.toString(this.universalPinState));
    stringBuilder.append(", .gsmUmtsSubscriptionAppIndex = ");
    stringBuilder.append(this.gsmUmtsSubscriptionAppIndex);
    stringBuilder.append(", .cdmaSubscriptionAppIndex = ");
    stringBuilder.append(this.cdmaSubscriptionAppIndex);
    stringBuilder.append(", .imsSubscriptionAppIndex = ");
    stringBuilder.append(this.imsSubscriptionAppIndex);
    stringBuilder.append(", .applications = ");
    stringBuilder.append(this.applications);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.cardState);
    paramHwBlob.putInt32(4L + paramLong, this.universalPinState);
    paramHwBlob.putInt32(paramLong + 8L, this.gsmUmtsSubscriptionAppIndex);
    paramHwBlob.putInt32(paramLong + 12L, this.cdmaSubscriptionAppIndex);
    paramHwBlob.putInt32(16L + paramLong, this.imsSubscriptionAppIndex);
    int i = this.applications.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((AppStatus)this.applications.get(b)).writeEmbeddedToBlob(hwBlob, (b * 64)); 
    paramHwBlob.putBlob(24L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CardStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */