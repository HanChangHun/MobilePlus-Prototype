package android.hardware.gnss.V2_1;

import android.hardware.gnss.V1_0.GnssLocation;
import android.hardware.gnss.V1_0.IGnssCallback;
import android.hardware.gnss.V2_0.GnssLocation;
import android.hardware.gnss.V2_0.IGnssCallback;
import android.internal.hidl.base.V1_0.DebugInfo;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Stub extends HwBinder implements IGnssCallback {
  public IHwBinder asBinder() {
    return (IHwBinder)this;
  }
  
  public void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) {}
  
  public final DebugInfo getDebugInfo() {
    DebugInfo debugInfo = new DebugInfo();
    debugInfo.pid = HidlSupport.getPidIfSharable();
    debugInfo.ptr = 0L;
    debugInfo.arch = 0;
    return debugInfo;
  }
  
  public final ArrayList<byte[]> getHashChain() {
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
              53, 65, -40, 58, -33, -22, -63, 110, -29, -28, 
              93, 24, 58, 88, -33, -2, 6, 1, 44, -53, 
              90, -91, -68, -46, -28, -10, -18, -82, 38, -97, 
              105, -51 }, { 
              100, 35, 32, 55, 16, -102, 94, 95, 83, -85, 
              3, 119, -25, 85, -20, 73, 74, -23, 63, -53, 
              82, 121, -26, -18, -89, 29, -20, 46, 122, -58, 
              -5, -4 }, { 
              -118, -43, 91, -61, 91, -77, -88, 62, 101, -64, 
              24, -67, -3, -25, -82, 94, -68, 116, -97, -14, 
              -65, 107, 121, 65, 45, -19, 11, -58, -56, -101, 
              -105, -40 }, { 
              -100, -77, -33, 43, -34, 44, 108, -43, -3, -106, 
              -73, -60, 21, 85, 66, 12, -84, -41, -30, 118, 
              -91, 86, -58, -124, -81, -111, -73, 70, 28, -122, 
              70, 15 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.1::IGnssCallback", "android.hardware.gnss@2.0::IGnssCallback", "android.hardware.gnss@1.1::IGnssCallback", "android.hardware.gnss@1.0::IGnssCallback", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.gnss@2.1::IGnssCallback";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    DebugInfo debugInfo;
    HwBlob hwBlob1;
    String str;
    ArrayList<String> arrayList;
    ArrayList<byte[]> arrayList1;
    GnssLocation gnssLocation1;
    IGnssCallback.GnssSystemInfo gnssSystemInfo;
    IGnssCallback.GnssSvStatus gnssSvStatus;
    HwBlob hwBlob2;
    switch (paramInt1) {
      default:
        switch (paramInt1) {
          default:
            return;
          case 257120595:
            paramHwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
            notifySyspropsChanged();
          case 257049926:
            paramHwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
            debugInfo = getDebugInfo();
            paramHwParcel2.writeStatus(0);
            debugInfo.writeToParcel(paramHwParcel2);
            paramHwParcel2.send();
          case 256921159:
            debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
            ping();
            paramHwParcel2.writeStatus(0);
            paramHwParcel2.send();
          case 256462420:
            debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
            setHALInstrumentation();
          case 256398152:
            debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
            arrayList1 = getHashChain();
            paramHwParcel2.writeStatus(0);
            hwBlob1 = new HwBlob(16);
            paramInt2 = arrayList1.size();
            hwBlob1.putInt32(8L, paramInt2);
            hwBlob1.putBool(12L, false);
            hwBlob2 = new HwBlob(paramInt2 * 32);
            paramInt1 = 0;
            while (paramInt1 < paramInt2) {
              long l = (paramInt1 * 32);
              byte[] arrayOfByte = arrayList1.get(paramInt1);
              if (arrayOfByte != null && arrayOfByte.length == 32) {
                hwBlob2.putInt8Array(l, arrayOfByte);
                paramInt1++;
                continue;
              } 
              throw new IllegalArgumentException("Array element is not of the expected length");
            } 
            hwBlob1.putBlob(0L, hwBlob2);
            paramHwParcel2.writeBuffer(hwBlob1);
            paramHwParcel2.send();
          case 256136003:
            hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
            str = interfaceDescriptor();
            paramHwParcel2.writeStatus(0);
            paramHwParcel2.writeString(str);
            paramHwParcel2.send();
          case 256131655:
            str.enforceInterface("android.hidl.base@1.0::IBase");
            debug(str.readNativeHandle(), str.readStringVector());
            paramHwParcel2.writeStatus(0);
            paramHwParcel2.send();
          case 256067662:
            break;
        } 
        str.enforceInterface("android.hidl.base@1.0::IBase");
        arrayList = interfaceChain();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeStringVector(arrayList);
        paramHwParcel2.send();
      case 17:
        arrayList.enforceInterface("android.hardware.gnss@2.1::IGnssCallback");
        gnssSvStatusCb_2_1(IGnssCallback.GnssSvInfo.readVectorFromParcel((HwParcel)arrayList));
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 16:
        arrayList.enforceInterface("android.hardware.gnss@2.1::IGnssCallback");
        gnssSetCapabilitiesCb_2_1(arrayList.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 15:
        arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
        gnssSvStatusCb_2_0(IGnssCallback.GnssSvInfo.readVectorFromParcel((HwParcel)arrayList));
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 14:
        arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
        gnssRequestLocationCb_2_0(arrayList.readBool(), arrayList.readBool());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 13:
        arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
        gnssLocation1 = new GnssLocation();
        gnssLocation1.readFromParcel((HwParcel)arrayList);
        gnssLocationCb_2_0(gnssLocation1);
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 12:
        arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
        gnssSetCapabilitiesCb_2_0(arrayList.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 11:
        arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssCallback");
        gnssRequestLocationCb(arrayList.readBool());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 10:
        arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssCallback");
        gnssNameCb(arrayList.readString());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 9:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssSystemInfo = new IGnssCallback.GnssSystemInfo();
        gnssSystemInfo.readFromParcel((HwParcel)arrayList);
        gnssSetSystemInfoCb(gnssSystemInfo);
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 8:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssRequestTimeCb();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 7:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssReleaseWakelockCb();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 6:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssAcquireWakelockCb();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 5:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssSetCapabilitesCb(arrayList.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 4:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssNmeaCb(arrayList.readInt64(), arrayList.readString());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 3:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssSvStatus = new IGnssCallback.GnssSvStatus();
        gnssSvStatus.readFromParcel((HwParcel)arrayList);
        gnssSvStatusCb(gnssSvStatus);
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 2:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
        gnssStatusCb(arrayList.readInt8());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 1:
        break;
    } 
    arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
    GnssLocation gnssLocation = new GnssLocation();
    gnssLocation.readFromParcel((HwParcel)arrayList);
    gnssLocationCb(gnssLocation);
    paramHwParcel2.writeStatus(0);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.gnss@2.1::IGnssCallback".equals(paramString) ? this : null);
  }
  
  public void registerAsService(String paramString) throws RemoteException {
    registerService(paramString);
  }
  
  public final void setHALInstrumentation() {}
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(interfaceDescriptor());
    stringBuilder.append("@Stub");
    return stringBuilder.toString();
  }
  
  public final boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) {
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */