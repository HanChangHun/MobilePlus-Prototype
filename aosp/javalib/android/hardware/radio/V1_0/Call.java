package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class Call {
  public byte als = (byte)0;
  
  public int index = 0;
  
  public boolean isMT = false;
  
  public boolean isMpty = false;
  
  public boolean isVoice = false;
  
  public boolean isVoicePrivacy = false;
  
  public String name = new String();
  
  public int namePresentation = 0;
  
  public String number = new String();
  
  public int numberPresentation = 0;
  
  public int state = 0;
  
  public int toa = 0;
  
  public ArrayList<UusInfo> uusInfo = new ArrayList<>();
  
  public static final ArrayList<Call> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<Call> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      Call call = new Call();
      call.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 88));
      arrayList.add(call);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<Call> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((Call)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != Call.class)
      return false; 
    paramObject = paramObject;
    return (this.state != ((Call)paramObject).state) ? false : ((this.index != ((Call)paramObject).index) ? false : ((this.toa != ((Call)paramObject).toa) ? false : ((this.isMpty != ((Call)paramObject).isMpty) ? false : ((this.isMT != ((Call)paramObject).isMT) ? false : ((this.als != ((Call)paramObject).als) ? false : ((this.isVoice != ((Call)paramObject).isVoice) ? false : ((this.isVoicePrivacy != ((Call)paramObject).isVoicePrivacy) ? false : (!HidlSupport.deepEquals(this.number, ((Call)paramObject).number) ? false : ((this.numberPresentation != ((Call)paramObject).numberPresentation) ? false : (!HidlSupport.deepEquals(this.name, ((Call)paramObject).name) ? false : ((this.namePresentation != ((Call)paramObject).namePresentation) ? false : (!!HidlSupport.deepEquals(this.uusInfo, ((Call)paramObject).uusInfo)))))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.index))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.toa))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isMpty))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isMT))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.als))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isVoice))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isVoicePrivacy))), Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberPresentation))), 
          Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.namePresentation))), Integer.valueOf(HidlSupport.deepHashCode(this.uusInfo)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.state = paramHwBlob.getInt32(paramLong + 0L);
    this.index = paramHwBlob.getInt32(paramLong + 4L);
    this.toa = paramHwBlob.getInt32(paramLong + 8L);
    this.isMpty = paramHwBlob.getBool(paramLong + 12L);
    this.isMT = paramHwBlob.getBool(paramLong + 13L);
    this.als = paramHwBlob.getInt8(paramLong + 14L);
    this.isVoice = paramHwBlob.getBool(paramLong + 15L);
    this.isVoicePrivacy = paramHwBlob.getBool(paramLong + 16L);
    String str = paramHwBlob.getString(paramLong + 24L);
    this.number = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
    this.numberPresentation = paramHwBlob.getInt32(paramLong + 40L);
    str = paramHwBlob.getString(paramLong + 48L);
    this.name = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 48L + 0L, false);
    this.namePresentation = paramHwBlob.getInt32(paramLong + 64L);
    int i = paramHwBlob.getInt32(paramLong + 72L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), paramHwBlob.handle(), paramLong + 72L + 0L, true);
    this.uusInfo.clear();
    for (byte b = 0; b < i; b++) {
      UusInfo uusInfo = new UusInfo();
      uusInfo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 24));
      this.uusInfo.add(uusInfo);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".state = ");
    stringBuilder.append(CallState.toString(this.state));
    stringBuilder.append(", .index = ");
    stringBuilder.append(this.index);
    stringBuilder.append(", .toa = ");
    stringBuilder.append(this.toa);
    stringBuilder.append(", .isMpty = ");
    stringBuilder.append(this.isMpty);
    stringBuilder.append(", .isMT = ");
    stringBuilder.append(this.isMT);
    stringBuilder.append(", .als = ");
    stringBuilder.append(this.als);
    stringBuilder.append(", .isVoice = ");
    stringBuilder.append(this.isVoice);
    stringBuilder.append(", .isVoicePrivacy = ");
    stringBuilder.append(this.isVoicePrivacy);
    stringBuilder.append(", .number = ");
    stringBuilder.append(this.number);
    stringBuilder.append(", .numberPresentation = ");
    stringBuilder.append(CallPresentation.toString(this.numberPresentation));
    stringBuilder.append(", .name = ");
    stringBuilder.append(this.name);
    stringBuilder.append(", .namePresentation = ");
    stringBuilder.append(CallPresentation.toString(this.namePresentation));
    stringBuilder.append(", .uusInfo = ");
    stringBuilder.append(this.uusInfo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.state);
    paramHwBlob.putInt32(4L + paramLong, this.index);
    paramHwBlob.putInt32(paramLong + 8L, this.toa);
    paramHwBlob.putBool(paramLong + 12L, this.isMpty);
    paramHwBlob.putBool(13L + paramLong, this.isMT);
    paramHwBlob.putInt8(14L + paramLong, this.als);
    paramHwBlob.putBool(15L + paramLong, this.isVoice);
    paramHwBlob.putBool(16L + paramLong, this.isVoicePrivacy);
    paramHwBlob.putString(24L + paramLong, this.number);
    paramHwBlob.putInt32(40L + paramLong, this.numberPresentation);
    paramHwBlob.putString(48L + paramLong, this.name);
    paramHwBlob.putInt32(64L + paramLong, this.namePresentation);
    int i = this.uusInfo.size();
    paramHwBlob.putInt32(paramLong + 72L + 8L, i);
    paramHwBlob.putBool(paramLong + 72L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((UusInfo)this.uusInfo.get(b)).writeEmbeddedToBlob(hwBlob, (b * 24)); 
    paramHwBlob.putBlob(72L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/Call.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */