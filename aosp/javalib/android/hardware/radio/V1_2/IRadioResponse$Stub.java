package android.hardware.radio.V1_2;

import android.hardware.radio.V1_0.ActivityStatsInfo;
import android.hardware.radio.V1_0.Call;
import android.hardware.radio.V1_0.CallForwardInfo;
import android.hardware.radio.V1_0.CardStatus;
import android.hardware.radio.V1_0.CarrierRestrictions;
import android.hardware.radio.V1_0.CdmaBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.CellInfo;
import android.hardware.radio.V1_0.DataRegStateResult;
import android.hardware.radio.V1_0.GsmBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.HardwareConfig;
import android.hardware.radio.V1_0.IccIoResult;
import android.hardware.radio.V1_0.LastCallFailCauseInfo;
import android.hardware.radio.V1_0.LceDataInfo;
import android.hardware.radio.V1_0.LceStatusInfo;
import android.hardware.radio.V1_0.NeighboringCell;
import android.hardware.radio.V1_0.OperatorInfo;
import android.hardware.radio.V1_0.RadioCapability;
import android.hardware.radio.V1_0.RadioResponseInfo;
import android.hardware.radio.V1_0.SendSmsResult;
import android.hardware.radio.V1_0.SetupDataCallResult;
import android.hardware.radio.V1_0.SignalStrength;
import android.hardware.radio.V1_0.VoiceRegStateResult;
import android.hardware.radio.V1_1.KeepaliveStatus;
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

public abstract class Stub extends HwBinder implements IRadioResponse {
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
        5, -86, 61, -26, 19, 10, -105, -120, -3, -74, 
        -12, -45, -52, 87, -61, -22, -112, -16, 103, -25, 
        122, 94, 9, -42, -89, 114, -20, Byte.MAX_VALUE, 107, -54, 
        51, -46 };
    byte[] arrayOfByte2 = { 
        -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
        -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
        -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
        -54, 76 };
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
              -38, -116, 106, -23, -111, -58, -92, -78, -124, -52, 
              110, 68, 83, 50, -32, 100, -30, -114, -24, -96, 
              -108, -126, -19, 90, -1, -7, -47, 89, -20, 102, 
              -108, -73 }, arrayOfByte1, { 
              29, 74, 87, 118, 97, 76, 8, -75, -41, -108, 
              -91, -20, 90, -80, 70, -105, 38, 12, -67, 75, 
              52, 65, -43, -109, 92, -43, 62, -25, 29, 25, 
              -38, 2 }, arrayOfByte2 }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.2::IRadioResponse", "android.hardware.radio@1.1::IRadioResponse", "android.hardware.radio@1.0::IRadioResponse", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.radio@1.2::IRadioResponse";
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
    RadioResponseInfo radioResponseInfo11;
    CarrierRestrictions carrierRestrictions;
    RadioResponseInfo radioResponseInfo10;
    RadioCapability radioCapability1;
    RadioResponseInfo radioResponseInfo9;
    IccIoResult iccIoResult3;
    RadioResponseInfo radioResponseInfo8;
    IccIoResult iccIoResult2;
    RadioResponseInfo radioResponseInfo7;
    IccIoResult iccIoResult1;
    RadioResponseInfo radioResponseInfo6;
    SendSmsResult sendSmsResult2;
    RadioResponseInfo radioResponseInfo5;
    SetupDataCallResult setupDataCallResult;
    RadioResponseInfo radioResponseInfo4;
    SendSmsResult sendSmsResult1;
    RadioResponseInfo radioResponseInfo3;
    DataRegStateResult dataRegStateResult;
    RadioResponseInfo radioResponseInfo2;
    SignalStrength signalStrength;
    RadioResponseInfo radioResponseInfo1;
    ArrayList<byte[]> arrayList1;
    HwBlob hwBlob2;
    DataRegStateResult dataRegStateResult1;
    VoiceRegStateResult voiceRegStateResult1;
    SignalStrength signalStrength1;
    CardStatus cardStatus1;
    KeepaliveStatus keepaliveStatus;
    RadioResponseInfo radioResponseInfo19;
    ActivityStatsInfo activityStatsInfo;
    LceDataInfo lceDataInfo;
    LceStatusInfo lceStatusInfo;
    RadioResponseInfo radioResponseInfo18;
    RadioCapability radioCapability2;
    RadioResponseInfo radioResponseInfo17;
    SendSmsResult sendSmsResult4;
    IccIoResult iccIoResult5;
    RadioResponseInfo radioResponseInfo16;
    IccIoResult iccIoResult4;
    RadioResponseInfo radioResponseInfo15;
    SendSmsResult sendSmsResult3;
    RadioResponseInfo radioResponseInfo14;
    VoiceRegStateResult voiceRegStateResult;
    RadioResponseInfo radioResponseInfo13;
    LastCallFailCauseInfo lastCallFailCauseInfo;
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
      case 143:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        dataRegStateResult1 = new DataRegStateResult();
        dataRegStateResult1.readFromParcel((HwParcel)arrayList);
        getDataRegistrationStateResponse_1_2(radioResponseInfo11, dataRegStateResult1);
      case 142:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        voiceRegStateResult1 = new VoiceRegStateResult();
        voiceRegStateResult1.readFromParcel((HwParcel)arrayList);
        getVoiceRegistrationStateResponse_1_2(radioResponseInfo11, voiceRegStateResult1);
      case 141:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        signalStrength1 = new SignalStrength();
        signalStrength1.readFromParcel((HwParcel)arrayList);
        getSignalStrengthResponse_1_2(radioResponseInfo11, signalStrength1);
      case 140:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        getCurrentCallsResponse_1_2(radioResponseInfo11, Call.readVectorFromParcel((HwParcel)arrayList));
      case 139:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        setLinkCapacityReportingCriteriaResponse(radioResponseInfo11);
      case 138:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        setSignalStrengthReportingCriteriaResponse(radioResponseInfo11);
      case 137:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        cardStatus1 = new CardStatus();
        cardStatus1.readFromParcel((HwParcel)arrayList);
        getIccCardStatusResponse_1_2(radioResponseInfo11, cardStatus1);
      case 136:
        arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        getCellInfoListResponse_1_2(radioResponseInfo11, CellInfo.readVectorFromParcel((HwParcel)arrayList));
      case 135:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        stopKeepaliveResponse(radioResponseInfo11);
      case 134:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        keepaliveStatus = new KeepaliveStatus();
        keepaliveStatus.readFromParcel((HwParcel)arrayList);
        startKeepaliveResponse(radioResponseInfo11, keepaliveStatus);
      case 133:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        stopNetworkScanResponse(radioResponseInfo11);
      case 132:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        startNetworkScanResponse(radioResponseInfo11);
      case 131:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        setSimCardPowerResponse_1_1(radioResponseInfo11);
      case 130:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        setCarrierInfoForImsiEncryptionResponse(radioResponseInfo11);
      case 129:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        acknowledgeRequest(arrayList.readInt32());
      case 128:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        setSimCardPowerResponse(radioResponseInfo11);
      case 127:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        setIndicationFilterResponse(radioResponseInfo11);
      case 126:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo11 = new RadioResponseInfo();
        radioResponseInfo11.readFromParcel((HwParcel)arrayList);
        sendDeviceStateResponse(radioResponseInfo11);
      case 125:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo19 = new RadioResponseInfo();
        radioResponseInfo19.readFromParcel((HwParcel)arrayList);
        bool = arrayList.readBool();
        carrierRestrictions = new CarrierRestrictions();
        carrierRestrictions.readFromParcel((HwParcel)arrayList);
        getAllowedCarriersResponse(radioResponseInfo19, bool, carrierRestrictions);
      case 124:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo10 = new RadioResponseInfo();
        radioResponseInfo10.readFromParcel((HwParcel)arrayList);
        setAllowedCarriersResponse(radioResponseInfo10, arrayList.readInt32());
      case 123:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo10 = new RadioResponseInfo();
        radioResponseInfo10.readFromParcel((HwParcel)arrayList);
        activityStatsInfo = new ActivityStatsInfo();
        activityStatsInfo.readFromParcel((HwParcel)arrayList);
        getModemActivityInfoResponse(radioResponseInfo10, activityStatsInfo);
      case 122:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo10 = new RadioResponseInfo();
        radioResponseInfo10.readFromParcel((HwParcel)arrayList);
        lceDataInfo = new LceDataInfo();
        lceDataInfo.readFromParcel((HwParcel)arrayList);
        pullLceDataResponse(radioResponseInfo10, lceDataInfo);
      case 121:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo10 = new RadioResponseInfo();
        radioResponseInfo10.readFromParcel((HwParcel)arrayList);
        lceStatusInfo = new LceStatusInfo();
        lceStatusInfo.readFromParcel((HwParcel)arrayList);
        stopLceServiceResponse(radioResponseInfo10, lceStatusInfo);
      case 120:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo10 = new RadioResponseInfo();
        radioResponseInfo10.readFromParcel((HwParcel)arrayList);
        lceStatusInfo = new LceStatusInfo();
        lceStatusInfo.readFromParcel((HwParcel)arrayList);
        startLceServiceResponse(radioResponseInfo10, lceStatusInfo);
      case 119:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo18 = new RadioResponseInfo();
        radioResponseInfo18.readFromParcel((HwParcel)arrayList);
        radioCapability1 = new RadioCapability();
        radioCapability1.readFromParcel((HwParcel)arrayList);
        setRadioCapabilityResponse(radioResponseInfo18, radioCapability1);
      case 118:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo9 = new RadioResponseInfo();
        radioResponseInfo9.readFromParcel((HwParcel)arrayList);
        radioCapability2 = new RadioCapability();
        radioCapability2.readFromParcel((HwParcel)arrayList);
        getRadioCapabilityResponse(radioResponseInfo9, radioCapability2);
      case 117:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo9 = new RadioResponseInfo();
        radioResponseInfo9.readFromParcel((HwParcel)arrayList);
        requestShutdownResponse(radioResponseInfo9);
      case 116:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo9 = new RadioResponseInfo();
        radioResponseInfo9.readFromParcel((HwParcel)arrayList);
        setDataProfileResponse(radioResponseInfo9);
      case 115:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo17 = new RadioResponseInfo();
        radioResponseInfo17.readFromParcel((HwParcel)arrayList);
        iccIoResult3 = new IccIoResult();
        iccIoResult3.readFromParcel((HwParcel)arrayList);
        requestIccSimAuthenticationResponse(radioResponseInfo17, iccIoResult3);
      case 114:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        getHardwareConfigResponse(radioResponseInfo8, HardwareConfig.readVectorFromParcel((HwParcel)arrayList));
      case 113:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        setDataAllowedResponse(radioResponseInfo8);
      case 112:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        setUiccSubscriptionResponse(radioResponseInfo8);
      case 111:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        nvResetConfigResponse(radioResponseInfo8);
      case 110:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        nvWriteCdmaPrlResponse(radioResponseInfo8);
      case 109:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        nvWriteItemResponse(radioResponseInfo8);
      case 108:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        nvReadItemResponse(radioResponseInfo8, arrayList.readString());
      case 107:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo17 = new RadioResponseInfo();
        radioResponseInfo17.readFromParcel((HwParcel)arrayList);
        iccIoResult2 = new IccIoResult();
        iccIoResult2.readFromParcel((HwParcel)arrayList);
        iccTransmitApduLogicalChannelResponse(radioResponseInfo17, iccIoResult2);
      case 106:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo7 = new RadioResponseInfo();
        radioResponseInfo7.readFromParcel((HwParcel)arrayList);
        iccCloseLogicalChannelResponse(radioResponseInfo7);
      case 105:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo7 = new RadioResponseInfo();
        radioResponseInfo7.readFromParcel((HwParcel)arrayList);
        iccOpenLogicalChannelResponse(radioResponseInfo7, arrayList.readInt32(), arrayList.readInt8Vector());
      case 104:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo17 = new RadioResponseInfo();
        radioResponseInfo17.readFromParcel((HwParcel)arrayList);
        iccIoResult1 = new IccIoResult();
        iccIoResult1.readFromParcel((HwParcel)arrayList);
        iccTransmitApduBasicChannelResponse(radioResponseInfo17, iccIoResult1);
      case 103:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        sendSmsResult4 = new SendSmsResult();
        sendSmsResult4.readFromParcel((HwParcel)arrayList);
        sendImsSmsResponse(radioResponseInfo6, sendSmsResult4);
      case 102:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getImsRegistrationStateResponse(radioResponseInfo6, arrayList.readBool(), arrayList.readInt32());
      case 101:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setInitialAttachApnResponse(radioResponseInfo6);
      case 100:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setCellInfoListRateResponse(radioResponseInfo6);
      case 99:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getCellInfoListResponse(radioResponseInfo6, CellInfo.readVectorFromParcel((HwParcel)arrayList));
      case 98:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getVoiceRadioTechnologyResponse(radioResponseInfo6, arrayList.readInt32());
      case 97:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        iccIoResult5 = new IccIoResult();
        iccIoResult5.readFromParcel((HwParcel)arrayList);
        sendEnvelopeWithStatusResponse(radioResponseInfo6, iccIoResult5);
      case 96:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        acknowledgeIncomingGsmSmsWithPduResponse(radioResponseInfo6);
      case 95:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        requestIsimAuthenticationResponse(radioResponseInfo6, arrayList.readString());
      case 94:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getCdmaSubscriptionSourceResponse(radioResponseInfo6, arrayList.readInt32());
      case 93:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        reportStkServiceIsRunningResponse(radioResponseInfo6);
      case 92:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        reportSmsMemoryStatusResponse(radioResponseInfo6);
      case 91:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setSmscAddressResponse(radioResponseInfo6);
      case 90:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getSmscAddressResponse(radioResponseInfo6, arrayList.readString());
      case 89:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        exitEmergencyCallbackModeResponse(radioResponseInfo6);
      case 88:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getDeviceIdentityResponse(radioResponseInfo6, arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 87:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        deleteSmsOnRuimResponse(radioResponseInfo6);
      case 86:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        writeSmsToRuimResponse(radioResponseInfo6, arrayList.readInt32());
      case 85:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getCDMASubscriptionResponse(radioResponseInfo6, arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 84:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setCdmaBroadcastActivationResponse(radioResponseInfo6);
      case 83:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setCdmaBroadcastConfigResponse(radioResponseInfo6);
      case 82:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getCdmaBroadcastConfigResponse(radioResponseInfo6, CdmaBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
      case 81:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setGsmBroadcastActivationResponse(radioResponseInfo6);
      case 80:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setGsmBroadcastConfigResponse(radioResponseInfo6);
      case 79:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        getGsmBroadcastConfigResponse(radioResponseInfo6, GsmBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
      case 78:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        acknowledgeLastIncomingCdmaSmsResponse(radioResponseInfo6);
      case 77:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo16 = new RadioResponseInfo();
        radioResponseInfo16.readFromParcel((HwParcel)arrayList);
        sendSmsResult2 = new SendSmsResult();
        sendSmsResult2.readFromParcel((HwParcel)arrayList);
        sendCdmaSmsResponse(radioResponseInfo16, sendSmsResult2);
      case 76:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        sendBurstDtmfResponse(radioResponseInfo5);
      case 75:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        sendCDMAFeatureCodeResponse(radioResponseInfo5);
      case 74:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getPreferredVoicePrivacyResponse(radioResponseInfo5, arrayList.readBool());
      case 73:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setPreferredVoicePrivacyResponse(radioResponseInfo5);
      case 72:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getTTYModeResponse(radioResponseInfo5, arrayList.readInt32());
      case 71:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setTTYModeResponse(radioResponseInfo5);
      case 70:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getCdmaRoamingPreferenceResponse(radioResponseInfo5, arrayList.readInt32());
      case 69:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setCdmaRoamingPreferenceResponse(radioResponseInfo5);
      case 68:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setCdmaSubscriptionSourceResponse(radioResponseInfo5);
      case 67:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setLocationUpdatesResponse(radioResponseInfo5);
      case 66:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getNeighboringCidsResponse(radioResponseInfo5, NeighboringCell.readVectorFromParcel((HwParcel)arrayList));
      case 65:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getPreferredNetworkTypeResponse(radioResponseInfo5, arrayList.readInt32());
      case 64:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setPreferredNetworkTypeResponse(radioResponseInfo5);
      case 63:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        explicitCallTransferResponse(radioResponseInfo5);
      case 62:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        handleStkCallSetupRequestFromSimResponse(radioResponseInfo5);
      case 61:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        sendTerminalResponseToSimResponse(radioResponseInfo5);
      case 60:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        sendEnvelopeResponse(radioResponseInfo5, arrayList.readString());
      case 59:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getAvailableBandModesResponse(radioResponseInfo5, arrayList.readInt32Vector());
      case 58:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setBandModeResponse(radioResponseInfo5);
      case 57:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        deleteSmsOnSimResponse(radioResponseInfo5);
      case 56:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        writeSmsToSimResponse(radioResponseInfo5, arrayList.readInt32());
      case 55:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setSuppServiceNotificationsResponse(radioResponseInfo5);
      case 54:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getDataCallListResponse(radioResponseInfo5, SetupDataCallResult.readVectorFromParcel((HwParcel)arrayList));
      case 53:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getClipResponse(radioResponseInfo5, arrayList.readInt32());
      case 52:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getMuteResponse(radioResponseInfo5, arrayList.readBool());
      case 51:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setMuteResponse(radioResponseInfo5);
      case 50:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        separateConnectionResponse(radioResponseInfo5);
      case 49:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getBasebandVersionResponse(radioResponseInfo5, arrayList.readString());
      case 48:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        stopDtmfResponse(radioResponseInfo5);
      case 47:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        startDtmfResponse(radioResponseInfo5);
      case 46:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getAvailableNetworksResponse(radioResponseInfo5, OperatorInfo.readVectorFromParcel((HwParcel)arrayList));
      case 45:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setNetworkSelectionModeManualResponse(radioResponseInfo5);
      case 44:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setNetworkSelectionModeAutomaticResponse(radioResponseInfo5);
      case 43:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getNetworkSelectionModeResponse(radioResponseInfo5, arrayList.readBool());
      case 42:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setBarringPasswordResponse(radioResponseInfo5);
      case 41:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setFacilityLockForAppResponse(radioResponseInfo5, arrayList.readInt32());
      case 40:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getFacilityLockForAppResponse(radioResponseInfo5, arrayList.readInt32());
      case 39:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        deactivateDataCallResponse(radioResponseInfo5);
      case 38:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        acceptCallResponse(radioResponseInfo5);
      case 37:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        acknowledgeLastIncomingGsmSmsResponse(radioResponseInfo5);
      case 36:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setCallWaitingResponse(radioResponseInfo5);
      case 35:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getCallWaitingResponse(radioResponseInfo5, arrayList.readBool(), arrayList.readInt32());
      case 34:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setCallForwardResponse(radioResponseInfo5);
      case 33:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getCallForwardStatusResponse(radioResponseInfo5, CallForwardInfo.readVectorFromParcel((HwParcel)arrayList));
      case 32:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setClirResponse(radioResponseInfo5);
      case 31:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getClirResponse(radioResponseInfo5, arrayList.readInt32(), arrayList.readInt32());
      case 30:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        cancelPendingUssdResponse(radioResponseInfo5);
      case 29:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        sendUssdResponse(radioResponseInfo5);
      case 28:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        iccIoResult4 = new IccIoResult();
        iccIoResult4.readFromParcel((HwParcel)arrayList);
        iccIOForAppResponse(radioResponseInfo5, iccIoResult4);
      case 27:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo15 = new RadioResponseInfo();
        radioResponseInfo15.readFromParcel((HwParcel)arrayList);
        setupDataCallResult = new SetupDataCallResult();
        setupDataCallResult.readFromParcel((HwParcel)arrayList);
        setupDataCallResponse(radioResponseInfo15, setupDataCallResult);
      case 26:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendSmsResult3 = new SendSmsResult();
        sendSmsResult3.readFromParcel((HwParcel)arrayList);
        sendSMSExpectMoreResponse(radioResponseInfo4, sendSmsResult3);
      case 25:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo14 = new RadioResponseInfo();
        radioResponseInfo14.readFromParcel((HwParcel)arrayList);
        sendSmsResult1 = new SendSmsResult();
        sendSmsResult1.readFromParcel((HwParcel)arrayList);
        sendSmsResponse(radioResponseInfo14, sendSmsResult1);
      case 24:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo3 = new RadioResponseInfo();
        radioResponseInfo3.readFromParcel((HwParcel)arrayList);
        sendDtmfResponse(radioResponseInfo3);
      case 23:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo3 = new RadioResponseInfo();
        radioResponseInfo3.readFromParcel((HwParcel)arrayList);
        setRadioPowerResponse(radioResponseInfo3);
      case 22:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo3 = new RadioResponseInfo();
        radioResponseInfo3.readFromParcel((HwParcel)arrayList);
        getOperatorResponse(radioResponseInfo3, arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 21:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo14 = new RadioResponseInfo();
        radioResponseInfo14.readFromParcel((HwParcel)arrayList);
        dataRegStateResult = new DataRegStateResult();
        dataRegStateResult.readFromParcel((HwParcel)arrayList);
        getDataRegistrationStateResponse(radioResponseInfo14, dataRegStateResult);
      case 20:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo2 = new RadioResponseInfo();
        radioResponseInfo2.readFromParcel((HwParcel)arrayList);
        voiceRegStateResult = new VoiceRegStateResult();
        voiceRegStateResult.readFromParcel((HwParcel)arrayList);
        getVoiceRegistrationStateResponse(radioResponseInfo2, voiceRegStateResult);
      case 19:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo13 = new RadioResponseInfo();
        radioResponseInfo13.readFromParcel((HwParcel)arrayList);
        signalStrength = new SignalStrength();
        signalStrength.readFromParcel((HwParcel)arrayList);
        getSignalStrengthResponse(radioResponseInfo13, signalStrength);
      case 18:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        lastCallFailCauseInfo = new LastCallFailCauseInfo();
        lastCallFailCauseInfo.readFromParcel((HwParcel)arrayList);
        getLastCallFailCauseResponse(radioResponseInfo1, lastCallFailCauseInfo);
      case 17:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        rejectCallResponse(radioResponseInfo1);
      case 16:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        conferenceResponse(radioResponseInfo1);
      case 15:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        switchWaitingOrHoldingAndActiveResponse(radioResponseInfo1);
      case 14:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        hangupForegroundResumeBackgroundResponse(radioResponseInfo1);
      case 13:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        hangupWaitingOrBackgroundResponse(radioResponseInfo1);
      case 12:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        hangupConnectionResponse(radioResponseInfo1);
      case 11:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        getIMSIForAppResponse(radioResponseInfo1, arrayList.readString());
      case 10:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        dialResponse(radioResponseInfo1);
      case 9:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        getCurrentCallsResponse(radioResponseInfo1, Call.readVectorFromParcel((HwParcel)arrayList));
      case 8:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        supplyNetworkDepersonalizationResponse(radioResponseInfo1, arrayList.readInt32());
      case 7:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        changeIccPin2ForAppResponse(radioResponseInfo1, arrayList.readInt32());
      case 6:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        changeIccPinForAppResponse(radioResponseInfo1, arrayList.readInt32());
      case 5:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        supplyIccPuk2ForAppResponse(radioResponseInfo1, arrayList.readInt32());
      case 4:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        supplyIccPin2ForAppResponse(radioResponseInfo1, arrayList.readInt32());
      case 3:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        supplyIccPukForAppResponse(radioResponseInfo1, arrayList.readInt32());
      case 2:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo1 = new RadioResponseInfo();
        radioResponseInfo1.readFromParcel((HwParcel)arrayList);
        supplyIccPinForAppResponse(radioResponseInfo1, arrayList.readInt32());
      case 1:
        break;
    } 
    arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
    RadioResponseInfo radioResponseInfo12 = new RadioResponseInfo();
    radioResponseInfo12.readFromParcel((HwParcel)arrayList);
    CardStatus cardStatus = new CardStatus();
    cardStatus.readFromParcel((HwParcel)arrayList);
    getIccCardStatusResponse(radioResponseInfo12, cardStatus);
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.radio@1.2::IRadioResponse".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/IRadioResponse$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */