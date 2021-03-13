package android.hardware.gnss.V2_1;

import android.hardware.gnss.V1_0.GnssLocation;
import android.hardware.gnss.V1_0.IAGnss;
import android.hardware.gnss.V1_0.IAGnssRil;
import android.hardware.gnss.V1_0.IGnssBatching;
import android.hardware.gnss.V1_0.IGnssCallback;
import android.hardware.gnss.V1_0.IGnssConfiguration;
import android.hardware.gnss.V1_0.IGnssDebug;
import android.hardware.gnss.V1_0.IGnssGeofencing;
import android.hardware.gnss.V1_0.IGnssMeasurement;
import android.hardware.gnss.V1_0.IGnssNavigationMessage;
import android.hardware.gnss.V1_0.IGnssNi;
import android.hardware.gnss.V1_0.IGnssXtra;
import android.hardware.gnss.V1_1.IGnssCallback;
import android.hardware.gnss.V1_1.IGnssConfiguration;
import android.hardware.gnss.V1_1.IGnssMeasurement;
import android.hardware.gnss.V2_0.GnssLocation;
import android.hardware.gnss.V2_0.IAGnss;
import android.hardware.gnss.V2_0.IAGnssRil;
import android.hardware.gnss.V2_0.IGnssBatching;
import android.hardware.gnss.V2_0.IGnssCallback;
import android.hardware.gnss.V2_0.IGnssConfiguration;
import android.hardware.gnss.V2_0.IGnssDebug;
import android.hardware.gnss.V2_0.IGnssMeasurement;
import android.hardware.gnss.measurement_corrections.V1_0.IMeasurementCorrections;
import android.hardware.gnss.measurement_corrections.V1_1.IMeasurementCorrections;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControl;
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

public abstract class Stub extends HwBinder implements IGnss {
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
    byte[] arrayOfByte1 = { 
        -61, 25, -26, -117, 3, -126, -103, 88, 64, 68, 
        2, -62, -39, -58, -126, 1, -106, 120, 8, 125, 
        96, 73, 88, 7, -64, -89, 68, 78, 10, 106, 
        -7, -127 };
    byte[] arrayOfByte2 = { 
        -19, -26, -105, 16, -61, -87, 92, 44, -66, -127, 
        -114, 108, -117, -73, 44, 120, 22, -126, 63, -84, 
        -27, -4, 33, -63, 119, 49, -78, 111, 65, -39, 
        77, 101 };
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { arrayOfByte1, { 
              -11, 96, 95, 72, -62, -5, -97, 35, 22, 21, 
              -35, -109, 43, -9, 48, -82, -107, 64, -12, -7, 
              -117, 91, 122, -30, -78, 105, -105, 95, 69, 47, 
              109, 115 }, { 
              -75, -15, -12, -63, -67, 109, -25, 26, -114, 113, 
              -41, 15, 87, -51, -85, -112, 74, -64, 36, -95, 
              47, 61, -18, 62, 33, 115, 119, 10, 69, -125, 
              -68, -62 }, arrayOfByte2, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.1::IGnss", "android.hardware.gnss@2.0::IGnss", "android.hardware.gnss@1.1::IGnss", "android.hardware.gnss@1.0::IGnss", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.gnss@2.1::IGnss";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    DebugInfo debugInfo;
    ArrayList<byte[]> arrayList1;
    String str;
    ArrayList<String> arrayList;
    IGnssAntennaInfo iGnssAntennaInfo;
    HwBlob hwBlob2;
    IHwBinder iHwBinder24;
    IMeasurementCorrections iMeasurementCorrections;
    byte[] arrayOfByte1;
    IHwBinder iHwBinder23;
    IGnssConfiguration iGnssConfiguration;
    HwBlob hwBlob1;
    IHwBinder iHwBinder22;
    IGnssMeasurement iGnssMeasurement4;
    IHwBinder iHwBinder21;
    IGnssBatching iGnssBatching2;
    IHwBinder iHwBinder20;
    IGnssVisibilityControl iGnssVisibilityControl1;
    IHwBinder iHwBinder19;
    IMeasurementCorrections iMeasurementCorrections1;
    IHwBinder iHwBinder18;
    IGnssMeasurement iGnssMeasurement3;
    IHwBinder iHwBinder17;
    IAGnssRil iAGnssRil2;
    IHwBinder iHwBinder16;
    IAGnss iAGnss2;
    IHwBinder iHwBinder15;
    IGnssDebug iGnssDebug2;
    IHwBinder iHwBinder14;
    IGnssConfiguration iGnssConfiguration3;
    IHwBinder iHwBinder13;
    IGnssMeasurement iGnssMeasurement2;
    IHwBinder iHwBinder12;
    IGnssConfiguration iGnssConfiguration2;
    IHwBinder iHwBinder11;
    IGnssBatching iGnssBatching1;
    IHwBinder iHwBinder10;
    IGnssDebug iGnssDebug1;
    IHwBinder iHwBinder9;
    IGnssConfiguration iGnssConfiguration1;
    IHwBinder iHwBinder8;
    IGnssXtra iGnssXtra1;
    IHwBinder iHwBinder7;
    IGnssNavigationMessage iGnssNavigationMessage1;
    IHwBinder iHwBinder6;
    IGnssMeasurement iGnssMeasurement1;
    IHwBinder iHwBinder5;
    IGnssNi iGnssNi1;
    IHwBinder iHwBinder4;
    IAGnss iAGnss1;
    IHwBinder iHwBinder3;
    IGnssGeofencing iGnssGeofencing1;
    IHwBinder iHwBinder2;
    IAGnssRil iAGnssRil1;
    IHwBinder iHwBinder1;
    GnssLocation gnssLocation1;
    GnssLocation gnssLocation;
    byte[] arrayOfByte2 = null;
    HwBlob hwBlob3 = null;
    IGnssMeasurement iGnssMeasurement5 = null;
    IGnssBatching iGnssBatching3 = null;
    IGnssVisibilityControl iGnssVisibilityControl2 = null;
    IMeasurementCorrections iMeasurementCorrections2 = null;
    IGnssMeasurement iGnssMeasurement6 = null;
    IAGnssRil iAGnssRil3 = null;
    IAGnss iAGnss3 = null;
    IGnssDebug iGnssDebug3 = null;
    IGnssConfiguration iGnssConfiguration4 = null;
    IGnssMeasurement iGnssMeasurement7 = null;
    IGnssConfiguration iGnssConfiguration5 = null;
    IGnssBatching iGnssBatching4 = null;
    IGnssDebug iGnssDebug4 = null;
    IGnssConfiguration iGnssConfiguration6 = null;
    IGnssXtra iGnssXtra2 = null;
    IGnssNavigationMessage iGnssNavigationMessage2 = null;
    IGnssMeasurement iGnssMeasurement8 = null;
    IGnssNi iGnssNi2 = null;
    IAGnss iAGnss4 = null;
    IGnssGeofencing iGnssGeofencing2 = null;
    IAGnssRil iAGnssRil4 = null;
    HwBlob hwBlob4 = null;
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
            hwBlob3 = new HwBlob(16);
            paramInt2 = arrayList1.size();
            hwBlob3.putInt32(8L, paramInt2);
            hwBlob3.putBool(12L, false);
            hwBlob4 = new HwBlob(paramInt2 * 32);
            paramInt1 = 0;
            while (paramInt1 < paramInt2) {
              long l = (paramInt1 * 32);
              arrayOfByte2 = arrayList1.get(paramInt1);
              if (arrayOfByte2 != null && arrayOfByte2.length == 32) {
                hwBlob4.putInt8Array(l, arrayOfByte2);
                paramInt1++;
                continue;
              } 
              throw new IllegalArgumentException("Array element is not of the expected length");
            } 
            hwBlob3.putBlob(0L, hwBlob4);
            paramHwParcel2.writeBuffer(hwBlob3);
            paramHwParcel2.send();
          case 256136003:
            arrayList1.enforceInterface("android.hidl.base@1.0::IBase");
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
      case 38:
        arrayList.enforceInterface("android.hardware.gnss@2.1::IGnss");
        iGnssAntennaInfo = getExtensionGnssAntennaInfo();
        paramHwParcel2.writeStatus(0);
        if (iGnssAntennaInfo == null) {
          hwBlob2 = hwBlob4;
        } else {
          iHwBinder24 = hwBlob2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder24);
        paramHwParcel2.send();
      case 37:
        iHwBinder24.enforceInterface("android.hardware.gnss@2.1::IGnss");
        iMeasurementCorrections = getExtensionMeasurementCorrections_1_1();
        paramHwParcel2.writeStatus(0);
        if (iMeasurementCorrections == null) {
          arrayOfByte1 = arrayOfByte2;
        } else {
          iHwBinder23 = arrayOfByte1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder23);
        paramHwParcel2.send();
      case 36:
        iHwBinder23.enforceInterface("android.hardware.gnss@2.1::IGnss");
        iGnssConfiguration = getExtensionGnssConfiguration_2_1();
        paramHwParcel2.writeStatus(0);
        if (iGnssConfiguration == null) {
          hwBlob1 = hwBlob3;
        } else {
          iHwBinder22 = hwBlob1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder22);
        paramHwParcel2.send();
      case 35:
        iHwBinder22.enforceInterface("android.hardware.gnss@2.1::IGnss");
        iGnssMeasurement4 = getExtensionGnssMeasurement_2_1();
        paramHwParcel2.writeStatus(0);
        if (iGnssMeasurement4 == null) {
          iGnssMeasurement4 = iGnssMeasurement5;
        } else {
          iHwBinder21 = iGnssMeasurement4.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder21);
        paramHwParcel2.send();
      case 34:
        iHwBinder21.enforceInterface("android.hardware.gnss@2.1::IGnss");
        bool = setCallback_2_1(IGnssCallback.asInterface(iHwBinder21.readStrongBinder()));
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 33:
        iHwBinder21.enforceInterface("android.hardware.gnss@2.0::IGnss");
        gnssLocation1 = new GnssLocation();
        gnssLocation1.readFromParcel((HwParcel)iHwBinder21);
        bool = injectBestLocation_2_0(gnssLocation1);
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 32:
        iHwBinder21.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iGnssBatching2 = getExtensionGnssBatching_2_0();
        paramHwParcel2.writeStatus(0);
        if (iGnssBatching2 == null) {
          iGnssBatching2 = iGnssBatching3;
        } else {
          iHwBinder20 = iGnssBatching2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder20);
        paramHwParcel2.send();
      case 31:
        iHwBinder20.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iGnssVisibilityControl1 = getExtensionVisibilityControl();
        paramHwParcel2.writeStatus(0);
        if (iGnssVisibilityControl1 == null) {
          iGnssVisibilityControl1 = iGnssVisibilityControl2;
        } else {
          iHwBinder19 = iGnssVisibilityControl1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder19);
        paramHwParcel2.send();
      case 30:
        iHwBinder19.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iMeasurementCorrections1 = getExtensionMeasurementCorrections();
        paramHwParcel2.writeStatus(0);
        if (iMeasurementCorrections1 == null) {
          iMeasurementCorrections1 = iMeasurementCorrections2;
        } else {
          iHwBinder18 = iMeasurementCorrections1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder18);
        paramHwParcel2.send();
      case 29:
        iHwBinder18.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iGnssMeasurement3 = getExtensionGnssMeasurement_2_0();
        paramHwParcel2.writeStatus(0);
        if (iGnssMeasurement3 == null) {
          iGnssMeasurement3 = iGnssMeasurement6;
        } else {
          iHwBinder17 = iGnssMeasurement3.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder17);
        paramHwParcel2.send();
      case 28:
        iHwBinder17.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iAGnssRil2 = getExtensionAGnssRil_2_0();
        paramHwParcel2.writeStatus(0);
        if (iAGnssRil2 == null) {
          iAGnssRil2 = iAGnssRil3;
        } else {
          iHwBinder16 = iAGnssRil2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder16);
        paramHwParcel2.send();
      case 27:
        iHwBinder16.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iAGnss2 = getExtensionAGnss_2_0();
        paramHwParcel2.writeStatus(0);
        if (iAGnss2 == null) {
          iAGnss2 = iAGnss3;
        } else {
          iHwBinder15 = iAGnss2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder15);
        paramHwParcel2.send();
      case 26:
        iHwBinder15.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iGnssDebug2 = getExtensionGnssDebug_2_0();
        paramHwParcel2.writeStatus(0);
        if (iGnssDebug2 == null) {
          iGnssDebug2 = iGnssDebug3;
        } else {
          iHwBinder14 = iGnssDebug2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder14);
        paramHwParcel2.send();
      case 25:
        iHwBinder14.enforceInterface("android.hardware.gnss@2.0::IGnss");
        iGnssConfiguration3 = getExtensionGnssConfiguration_2_0();
        paramHwParcel2.writeStatus(0);
        if (iGnssConfiguration3 == null) {
          iGnssConfiguration3 = iGnssConfiguration4;
        } else {
          iHwBinder13 = iGnssConfiguration3.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder13);
        paramHwParcel2.send();
      case 24:
        iHwBinder13.enforceInterface("android.hardware.gnss@2.0::IGnss");
        bool = setCallback_2_0(IGnssCallback.asInterface(iHwBinder13.readStrongBinder()));
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 23:
        iHwBinder13.enforceInterface("android.hardware.gnss@1.1::IGnss");
        gnssLocation = new GnssLocation();
        gnssLocation.readFromParcel((HwParcel)iHwBinder13);
        bool = injectBestLocation(gnssLocation);
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 22:
        iHwBinder13.enforceInterface("android.hardware.gnss@1.1::IGnss");
        iGnssMeasurement2 = getExtensionGnssMeasurement_1_1();
        paramHwParcel2.writeStatus(0);
        if (iGnssMeasurement2 == null) {
          iGnssMeasurement2 = iGnssMeasurement7;
        } else {
          iHwBinder12 = iGnssMeasurement2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder12);
        paramHwParcel2.send();
      case 21:
        iHwBinder12.enforceInterface("android.hardware.gnss@1.1::IGnss");
        iGnssConfiguration2 = getExtensionGnssConfiguration_1_1();
        paramHwParcel2.writeStatus(0);
        if (iGnssConfiguration2 == null) {
          iGnssConfiguration2 = iGnssConfiguration5;
        } else {
          iHwBinder11 = iGnssConfiguration2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder11);
        paramHwParcel2.send();
      case 20:
        iHwBinder11.enforceInterface("android.hardware.gnss@1.1::IGnss");
        bool = setPositionMode_1_1(iHwBinder11.readInt8(), iHwBinder11.readInt32(), iHwBinder11.readInt32(), iHwBinder11.readInt32(), iHwBinder11.readInt32(), iHwBinder11.readBool());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 19:
        iHwBinder11.enforceInterface("android.hardware.gnss@1.1::IGnss");
        bool = setCallback_1_1(IGnssCallback.asInterface(iHwBinder11.readStrongBinder()));
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 18:
        iHwBinder11.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssBatching1 = getExtensionGnssBatching();
        paramHwParcel2.writeStatus(0);
        if (iGnssBatching1 == null) {
          iGnssBatching1 = iGnssBatching4;
        } else {
          iHwBinder10 = iGnssBatching1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder10);
        paramHwParcel2.send();
      case 17:
        iHwBinder10.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssDebug1 = getExtensionGnssDebug();
        paramHwParcel2.writeStatus(0);
        if (iGnssDebug1 == null) {
          iGnssDebug1 = iGnssDebug4;
        } else {
          iHwBinder9 = iGnssDebug1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder9);
        paramHwParcel2.send();
      case 16:
        iHwBinder9.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssConfiguration1 = getExtensionGnssConfiguration();
        paramHwParcel2.writeStatus(0);
        if (iGnssConfiguration1 == null) {
          iGnssConfiguration1 = iGnssConfiguration6;
        } else {
          iHwBinder8 = iGnssConfiguration1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder8);
        paramHwParcel2.send();
      case 15:
        iHwBinder8.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssXtra1 = getExtensionXtra();
        paramHwParcel2.writeStatus(0);
        if (iGnssXtra1 == null) {
          iGnssXtra1 = iGnssXtra2;
        } else {
          iHwBinder7 = iGnssXtra1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder7);
        paramHwParcel2.send();
      case 14:
        iHwBinder7.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssNavigationMessage1 = getExtensionGnssNavigationMessage();
        paramHwParcel2.writeStatus(0);
        if (iGnssNavigationMessage1 == null) {
          iGnssNavigationMessage1 = iGnssNavigationMessage2;
        } else {
          iHwBinder6 = iGnssNavigationMessage1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder6);
        paramHwParcel2.send();
      case 13:
        iHwBinder6.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssMeasurement1 = getExtensionGnssMeasurement();
        paramHwParcel2.writeStatus(0);
        if (iGnssMeasurement1 == null) {
          iGnssMeasurement1 = iGnssMeasurement8;
        } else {
          iHwBinder5 = iGnssMeasurement1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder5);
        paramHwParcel2.send();
      case 12:
        iHwBinder5.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssNi1 = getExtensionGnssNi();
        paramHwParcel2.writeStatus(0);
        if (iGnssNi1 == null) {
          iGnssNi1 = iGnssNi2;
        } else {
          iHwBinder4 = iGnssNi1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder4);
        paramHwParcel2.send();
      case 11:
        iHwBinder4.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iAGnss1 = getExtensionAGnss();
        paramHwParcel2.writeStatus(0);
        if (iAGnss1 == null) {
          iAGnss1 = iAGnss4;
        } else {
          iHwBinder3 = iAGnss1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder3);
        paramHwParcel2.send();
      case 10:
        iHwBinder3.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssGeofencing1 = getExtensionGnssGeofencing();
        paramHwParcel2.writeStatus(0);
        if (iGnssGeofencing1 == null) {
          iGnssGeofencing1 = iGnssGeofencing2;
        } else {
          iHwBinder2 = iGnssGeofencing1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder2);
        paramHwParcel2.send();
      case 9:
        iHwBinder2.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iAGnssRil1 = getExtensionAGnssRil();
        paramHwParcel2.writeStatus(0);
        if (iAGnssRil1 == null) {
          iAGnssRil1 = iAGnssRil4;
        } else {
          iHwBinder1 = iAGnssRil1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder1);
        paramHwParcel2.send();
      case 8:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = setPositionMode(iHwBinder1.readInt8(), iHwBinder1.readInt32(), iHwBinder1.readInt32(), iHwBinder1.readInt32(), iHwBinder1.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 7:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        deleteAidingData(iHwBinder1.readInt16());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 6:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = injectLocation(iHwBinder1.readDouble(), iHwBinder1.readDouble(), iHwBinder1.readFloat());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 5:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = injectTime(iHwBinder1.readInt64(), iHwBinder1.readInt64(), iHwBinder1.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 4:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        cleanup();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 3:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = stop();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 2:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = start();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 1:
        break;
    } 
    iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
    boolean bool = setCallback(IGnssCallback.asInterface(iHwBinder1.readStrongBinder()));
    paramHwParcel2.writeStatus(0);
    paramHwParcel2.writeBool(bool);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.gnss@2.1::IGnss".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnss$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */