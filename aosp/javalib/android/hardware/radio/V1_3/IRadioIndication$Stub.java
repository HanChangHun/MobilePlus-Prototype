package android.hardware.radio.V1_3;

import android.hardware.radio.V1_0.CdmaCallWaiting;
import android.hardware.radio.V1_0.CdmaInformationRecords;
import android.hardware.radio.V1_0.CdmaSignalInfoRecord;
import android.hardware.radio.V1_0.CdmaSmsMessage;
import android.hardware.radio.V1_0.CellInfo;
import android.hardware.radio.V1_0.HardwareConfig;
import android.hardware.radio.V1_0.LceDataInfo;
import android.hardware.radio.V1_0.PcoDataInfo;
import android.hardware.radio.V1_0.RadioCapability;
import android.hardware.radio.V1_0.SetupDataCallResult;
import android.hardware.radio.V1_0.SignalStrength;
import android.hardware.radio.V1_0.SimRefreshResult;
import android.hardware.radio.V1_0.StkCcUnsolSsResult;
import android.hardware.radio.V1_0.SuppSvcNotification;
import android.hardware.radio.V1_1.KeepaliveStatus;
import android.hardware.radio.V1_1.NetworkScanResult;
import android.hardware.radio.V1_2.CellInfo;
import android.hardware.radio.V1_2.LinkCapacityEstimate;
import android.hardware.radio.V1_2.NetworkScanResult;
import android.hardware.radio.V1_2.PhysicalChannelConfig;
import android.hardware.radio.V1_2.SignalStrength;
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

public abstract class Stub extends HwBinder implements IRadioIndication {
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
              -23, -48, -15, 26, 82, 113, 95, 90, 41, -40, 
              -98, 45, -114, 46, 33, -37, 30, 22, -92, 49, 
              116, -81, 107, -99, 81, -90, 45, 112, 92, -38, 
              20, 85 }, { 
              -51, -89, 82, -82, -85, -86, -68, 32, 72, 106, 
              -126, -84, 87, -93, -35, 16, 119, -123, -64, 6, 
              9, 74, 52, -101, -59, -30, 36, -24, -86, 34, 
              -95, 124 }, { 
              -4, -59, -56, -56, -117, -123, -87, -10, 63, -70, 
              103, -39, -26, 116, -38, 70, 108, 114, -87, -116, 
              -94, -121, -13, 67, -5, 87, 33, -48, -104, 113, 
              63, -122 }, { 
              92, -114, -5, -71, -60, 81, -91, -105, 55, -19, 
              44, 108, 32, 35, 10, -82, 71, 69, -125, -100, 
              -96, 29, Byte.MIN_VALUE, -120, -42, -36, -55, 2, 14, 82, 
              -46, -59 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.3::IRadioIndication", "android.hardware.radio@1.2::IRadioIndication", "android.hardware.radio@1.1::IRadioIndication", "android.hardware.radio@1.0::IRadioIndication", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.radio@1.3::IRadioIndication";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    DebugInfo debugInfo;
    byte[] arrayOfByte;
    String str;
    ArrayList<String> arrayList;
    SignalStrength signalStrength1;
    LinkCapacityEstimate linkCapacityEstimate;
    NetworkScanResult networkScanResult1;
    KeepaliveStatus keepaliveStatus;
    NetworkScanResult networkScanResult;
    PcoDataInfo pcoDataInfo;
    LceDataInfo lceDataInfo;
    StkCcUnsolSsResult stkCcUnsolSsResult;
    RadioCapability radioCapability;
    CdmaInformationRecords cdmaInformationRecords;
    CdmaCallWaiting cdmaCallWaiting;
    CdmaSmsMessage cdmaSmsMessage;
    CdmaSignalInfoRecord cdmaSignalInfoRecord;
    SimRefreshResult simRefreshResult;
    SuppSvcNotification suppSvcNotification;
    SignalStrength signalStrength;
    ArrayList<byte[]> arrayList1;
    HwBlob hwBlob1;
    HwBlob hwBlob2;
    boolean bool;
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
              arrayOfByte = arrayList1.get(paramInt1);
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
            arrayOfByte.enforceInterface("android.hidl.base@1.0::IBase");
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
      case 53:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        signalStrength1 = new SignalStrength();
        signalStrength1.readFromParcel((HwParcel)arrayList);
        currentSignalStrength_1_2(paramInt1, signalStrength1);
      case 52:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
        currentPhysicalChannelConfigs(arrayList.readInt32(), PhysicalChannelConfig.readVectorFromParcel((HwParcel)arrayList));
      case 51:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        linkCapacityEstimate = new LinkCapacityEstimate();
        linkCapacityEstimate.readFromParcel((HwParcel)arrayList);
        currentLinkCapacityEstimate(paramInt1, linkCapacityEstimate);
      case 50:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
        cellInfoList_1_2(arrayList.readInt32(), CellInfo.readVectorFromParcel((HwParcel)arrayList));
      case 49:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        networkScanResult1 = new NetworkScanResult();
        networkScanResult1.readFromParcel((HwParcel)arrayList);
        networkScanResult_1_2(paramInt1, networkScanResult1);
      case 48:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        keepaliveStatus = new KeepaliveStatus();
        keepaliveStatus.readFromParcel((HwParcel)arrayList);
        keepaliveStatus(paramInt1, keepaliveStatus);
      case 47:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        networkScanResult = new NetworkScanResult();
        networkScanResult.readFromParcel((HwParcel)arrayList);
        networkScanResult(paramInt1, networkScanResult);
      case 46:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioIndication");
        carrierInfoForImsiEncryption(arrayList.readInt32());
      case 45:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        modemReset(arrayList.readInt32(), arrayList.readString());
      case 44:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        pcoDataInfo = new PcoDataInfo();
        pcoDataInfo.readFromParcel((HwParcel)arrayList);
        pcoData(paramInt1, pcoDataInfo);
      case 43:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        lceDataInfo = new LceDataInfo();
        lceDataInfo.readFromParcel((HwParcel)arrayList);
        lceData(paramInt1, lceDataInfo);
      case 42:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        stkCallControlAlphaNotify(arrayList.readInt32(), arrayList.readString());
      case 41:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        stkCcUnsolSsResult = new StkCcUnsolSsResult();
        stkCcUnsolSsResult.readFromParcel((HwParcel)arrayList);
        onSupplementaryServiceIndication(paramInt1, stkCcUnsolSsResult);
      case 40:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        radioCapability = new RadioCapability();
        radioCapability.readFromParcel((HwParcel)arrayList);
        radioCapabilityIndication(paramInt1, radioCapability);
      case 39:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        hardwareConfigChanged(arrayList.readInt32(), HardwareConfig.readVectorFromParcel((HwParcel)arrayList));
      case 38:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        srvccStateNotify(arrayList.readInt32(), arrayList.readInt32());
      case 37:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        subscriptionStatusChanged(arrayList.readInt32(), arrayList.readBool());
      case 36:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        imsNetworkStateChanged(arrayList.readInt32());
      case 35:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        cellInfoList(arrayList.readInt32(), CellInfo.readVectorFromParcel((HwParcel)arrayList));
      case 34:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        voiceRadioTechChanged(arrayList.readInt32(), arrayList.readInt32());
      case 33:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        rilConnected(arrayList.readInt32());
      case 32:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        exitEmergencyCallbackMode(arrayList.readInt32());
      case 31:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        cdmaPrlChanged(arrayList.readInt32(), arrayList.readInt32());
      case 30:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        cdmaSubscriptionSourceChanged(arrayList.readInt32(), arrayList.readInt32());
      case 29:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        resendIncallMute(arrayList.readInt32());
      case 28:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        indicateRingbackTone(arrayList.readInt32(), arrayList.readBool());
      case 27:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        cdmaInformationRecords = new CdmaInformationRecords();
        cdmaInformationRecords.readFromParcel((HwParcel)arrayList);
        cdmaInfoRec(paramInt1, cdmaInformationRecords);
      case 26:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        cdmaOtaProvisionStatus(arrayList.readInt32(), arrayList.readInt32());
      case 25:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        cdmaCallWaiting = new CdmaCallWaiting();
        cdmaCallWaiting.readFromParcel((HwParcel)arrayList);
        cdmaCallWaiting(paramInt1, cdmaCallWaiting);
      case 24:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        enterEmergencyCallbackMode(arrayList.readInt32());
      case 23:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        restrictedStateChanged(arrayList.readInt32(), arrayList.readInt32());
      case 22:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        cdmaRuimSmsStorageFull(arrayList.readInt32());
      case 21:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        newBroadcastSms(arrayList.readInt32(), arrayList.readInt8Vector());
      case 20:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        cdmaSmsMessage = new CdmaSmsMessage();
        cdmaSmsMessage.readFromParcel((HwParcel)arrayList);
        cdmaNewSms(paramInt1, cdmaSmsMessage);
      case 19:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        simStatusChanged(arrayList.readInt32());
      case 18:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        bool = arrayList.readBool();
        cdmaSignalInfoRecord = new CdmaSignalInfoRecord();
        cdmaSignalInfoRecord.readFromParcel((HwParcel)arrayList);
        callRing(paramInt1, bool, cdmaSignalInfoRecord);
      case 17:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        simRefreshResult = new SimRefreshResult();
        simRefreshResult.readFromParcel((HwParcel)arrayList);
        simRefresh(paramInt1, simRefreshResult);
      case 16:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        simSmsStorageFull(arrayList.readInt32());
      case 15:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        stkCallSetup(arrayList.readInt32(), arrayList.readInt64());
      case 14:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        stkEventNotify(arrayList.readInt32(), arrayList.readString());
      case 13:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        stkProactiveCommand(arrayList.readInt32(), arrayList.readString());
      case 12:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        stkSessionEnd(arrayList.readInt32());
      case 11:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        suppSvcNotification = new SuppSvcNotification();
        suppSvcNotification.readFromParcel((HwParcel)arrayList);
        suppSvcNotify(paramInt1, suppSvcNotification);
      case 10:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        dataCallListChanged(arrayList.readInt32(), SetupDataCallResult.readVectorFromParcel((HwParcel)arrayList));
      case 9:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        paramInt1 = arrayList.readInt32();
        signalStrength = new SignalStrength();
        signalStrength.readFromParcel((HwParcel)arrayList);
        currentSignalStrength(paramInt1, signalStrength);
      case 8:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        nitzTimeReceived(arrayList.readInt32(), arrayList.readString(), arrayList.readInt64());
      case 7:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        onUssd(arrayList.readInt32(), arrayList.readInt32(), arrayList.readString());
      case 6:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        newSmsOnSim(arrayList.readInt32(), arrayList.readInt32());
      case 5:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        newSmsStatusReport(arrayList.readInt32(), arrayList.readInt8Vector());
      case 4:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        newSms(arrayList.readInt32(), arrayList.readInt8Vector());
      case 3:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        networkStateChanged(arrayList.readInt32());
      case 2:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
        callStateChanged(arrayList.readInt32());
      case 1:
        break;
    } 
    arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
    radioStateChanged(arrayList.readInt32(), arrayList.readInt32());
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.radio@1.3::IRadioIndication".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_3/IRadioIndication$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */