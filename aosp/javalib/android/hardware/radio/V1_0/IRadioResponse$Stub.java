package android.hardware.radio.V1_0;

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
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
              29, 74, 87, 118, 97, 76, 8, -75, -41, -108, 
              -91, -20, 90, -80, 70, -105, 38, 12, -67, 75, 
              52, 65, -43, -109, 92, -43, 62, -25, 29, 25, 
              -38, 2 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.0::IRadioResponse", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.radio@1.0::IRadioResponse";
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
    RadioResponseInfo radioResponseInfo6;
    ActivityStatsInfo activityStatsInfo;
    LceDataInfo lceDataInfo;
    LceStatusInfo lceStatusInfo;
    RadioResponseInfo radioResponseInfo5;
    IccIoResult iccIoResult2;
    SendSmsResult sendSmsResult2;
    RadioResponseInfo radioResponseInfo4;
    IccIoResult iccIoResult1;
    SetupDataCallResult setupDataCallResult;
    SendSmsResult sendSmsResult1;
    RadioResponseInfo radioResponseInfo3;
    DataRegStateResult dataRegStateResult;
    VoiceRegStateResult voiceRegStateResult;
    RadioResponseInfo radioResponseInfo2;
    LastCallFailCauseInfo lastCallFailCauseInfo;
    RadioResponseInfo radioResponseInfo1;
    HwBlob hwBlob1;
    CarrierRestrictions carrierRestrictions;
    RadioResponseInfo radioResponseInfo10;
    RadioCapability radioCapability;
    IccIoResult iccIoResult4;
    RadioResponseInfo radioResponseInfo9;
    IccIoResult iccIoResult3;
    SendSmsResult sendSmsResult3;
    RadioResponseInfo radioResponseInfo8;
    SignalStrength signalStrength;
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
      case 129:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        acknowledgeRequest(arrayList.readInt32());
      case 128:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setSimCardPowerResponse(radioResponseInfo6);
      case 127:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setIndicationFilterResponse(radioResponseInfo6);
      case 126:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        sendDeviceStateResponse(radioResponseInfo6);
      case 125:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        bool = arrayList.readBool();
        carrierRestrictions = new CarrierRestrictions();
        carrierRestrictions.readFromParcel((HwParcel)arrayList);
        getAllowedCarriersResponse(radioResponseInfo6, bool, carrierRestrictions);
      case 124:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo6 = new RadioResponseInfo();
        radioResponseInfo6.readFromParcel((HwParcel)arrayList);
        setAllowedCarriersResponse(radioResponseInfo6, arrayList.readInt32());
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
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        radioCapability = new RadioCapability();
        radioCapability.readFromParcel((HwParcel)arrayList);
        setRadioCapabilityResponse(radioResponseInfo5, radioCapability);
      case 118:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        radioCapability = new RadioCapability();
        radioCapability.readFromParcel((HwParcel)arrayList);
        getRadioCapabilityResponse(radioResponseInfo5, radioCapability);
      case 117:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        requestShutdownResponse(radioResponseInfo5);
      case 116:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setDataProfileResponse(radioResponseInfo5);
      case 115:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        iccIoResult4 = new IccIoResult();
        iccIoResult4.readFromParcel((HwParcel)arrayList);
        requestIccSimAuthenticationResponse(radioResponseInfo5, iccIoResult4);
      case 114:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        getHardwareConfigResponse(radioResponseInfo5, HardwareConfig.readVectorFromParcel((HwParcel)arrayList));
      case 113:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setDataAllowedResponse(radioResponseInfo5);
      case 112:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        setUiccSubscriptionResponse(radioResponseInfo5);
      case 111:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        nvResetConfigResponse(radioResponseInfo5);
      case 110:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        nvWriteCdmaPrlResponse(radioResponseInfo5);
      case 109:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        nvWriteItemResponse(radioResponseInfo5);
      case 108:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        nvReadItemResponse(radioResponseInfo5, arrayList.readString());
      case 107:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        iccIoResult4 = new IccIoResult();
        iccIoResult4.readFromParcel((HwParcel)arrayList);
        iccTransmitApduLogicalChannelResponse(radioResponseInfo5, iccIoResult4);
      case 106:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        iccCloseLogicalChannelResponse(radioResponseInfo5);
      case 105:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo5 = new RadioResponseInfo();
        radioResponseInfo5.readFromParcel((HwParcel)arrayList);
        iccOpenLogicalChannelResponse(radioResponseInfo5, arrayList.readInt32(), arrayList.readInt8Vector());
      case 104:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo9 = new RadioResponseInfo();
        radioResponseInfo9.readFromParcel((HwParcel)arrayList);
        iccIoResult2 = new IccIoResult();
        iccIoResult2.readFromParcel((HwParcel)arrayList);
        iccTransmitApduBasicChannelResponse(radioResponseInfo9, iccIoResult2);
      case 103:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo9 = new RadioResponseInfo();
        radioResponseInfo9.readFromParcel((HwParcel)arrayList);
        sendSmsResult2 = new SendSmsResult();
        sendSmsResult2.readFromParcel((HwParcel)arrayList);
        sendImsSmsResponse(radioResponseInfo9, sendSmsResult2);
      case 102:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getImsRegistrationStateResponse(radioResponseInfo4, arrayList.readBool(), arrayList.readInt32());
      case 101:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setInitialAttachApnResponse(radioResponseInfo4);
      case 100:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCellInfoListRateResponse(radioResponseInfo4);
      case 99:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCellInfoListResponse(radioResponseInfo4, CellInfo.readVectorFromParcel((HwParcel)arrayList));
      case 98:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getVoiceRadioTechnologyResponse(radioResponseInfo4, arrayList.readInt32());
      case 97:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        iccIoResult3 = new IccIoResult();
        iccIoResult3.readFromParcel((HwParcel)arrayList);
        sendEnvelopeWithStatusResponse(radioResponseInfo4, iccIoResult3);
      case 96:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        acknowledgeIncomingGsmSmsWithPduResponse(radioResponseInfo4);
      case 95:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        requestIsimAuthenticationResponse(radioResponseInfo4, arrayList.readString());
      case 94:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCdmaSubscriptionSourceResponse(radioResponseInfo4, arrayList.readInt32());
      case 93:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        reportStkServiceIsRunningResponse(radioResponseInfo4);
      case 92:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        reportSmsMemoryStatusResponse(radioResponseInfo4);
      case 91:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setSmscAddressResponse(radioResponseInfo4);
      case 90:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getSmscAddressResponse(radioResponseInfo4, arrayList.readString());
      case 89:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        exitEmergencyCallbackModeResponse(radioResponseInfo4);
      case 88:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getDeviceIdentityResponse(radioResponseInfo4, arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 87:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        deleteSmsOnRuimResponse(radioResponseInfo4);
      case 86:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        writeSmsToRuimResponse(radioResponseInfo4, arrayList.readInt32());
      case 85:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCDMASubscriptionResponse(radioResponseInfo4, arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 84:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCdmaBroadcastActivationResponse(radioResponseInfo4);
      case 83:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCdmaBroadcastConfigResponse(radioResponseInfo4);
      case 82:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCdmaBroadcastConfigResponse(radioResponseInfo4, CdmaBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
      case 81:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setGsmBroadcastActivationResponse(radioResponseInfo4);
      case 80:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setGsmBroadcastConfigResponse(radioResponseInfo4);
      case 79:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getGsmBroadcastConfigResponse(radioResponseInfo4, GsmBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
      case 78:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        acknowledgeLastIncomingCdmaSmsResponse(radioResponseInfo4);
      case 77:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendSmsResult3 = new SendSmsResult();
        sendSmsResult3.readFromParcel((HwParcel)arrayList);
        sendCdmaSmsResponse(radioResponseInfo4, sendSmsResult3);
      case 76:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendBurstDtmfResponse(radioResponseInfo4);
      case 75:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendCDMAFeatureCodeResponse(radioResponseInfo4);
      case 74:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getPreferredVoicePrivacyResponse(radioResponseInfo4, arrayList.readBool());
      case 73:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setPreferredVoicePrivacyResponse(radioResponseInfo4);
      case 72:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getTTYModeResponse(radioResponseInfo4, arrayList.readInt32());
      case 71:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setTTYModeResponse(radioResponseInfo4);
      case 70:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCdmaRoamingPreferenceResponse(radioResponseInfo4, arrayList.readInt32());
      case 69:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCdmaRoamingPreferenceResponse(radioResponseInfo4);
      case 68:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCdmaSubscriptionSourceResponse(radioResponseInfo4);
      case 67:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setLocationUpdatesResponse(radioResponseInfo4);
      case 66:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getNeighboringCidsResponse(radioResponseInfo4, NeighboringCell.readVectorFromParcel((HwParcel)arrayList));
      case 65:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getPreferredNetworkTypeResponse(radioResponseInfo4, arrayList.readInt32());
      case 64:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setPreferredNetworkTypeResponse(radioResponseInfo4);
      case 63:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        explicitCallTransferResponse(radioResponseInfo4);
      case 62:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        handleStkCallSetupRequestFromSimResponse(radioResponseInfo4);
      case 61:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendTerminalResponseToSimResponse(radioResponseInfo4);
      case 60:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendEnvelopeResponse(radioResponseInfo4, arrayList.readString());
      case 59:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getAvailableBandModesResponse(radioResponseInfo4, arrayList.readInt32Vector());
      case 58:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setBandModeResponse(radioResponseInfo4);
      case 57:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        deleteSmsOnSimResponse(radioResponseInfo4);
      case 56:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        writeSmsToSimResponse(radioResponseInfo4, arrayList.readInt32());
      case 55:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setSuppServiceNotificationsResponse(radioResponseInfo4);
      case 54:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getDataCallListResponse(radioResponseInfo4, SetupDataCallResult.readVectorFromParcel((HwParcel)arrayList));
      case 53:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getClipResponse(radioResponseInfo4, arrayList.readInt32());
      case 52:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getMuteResponse(radioResponseInfo4, arrayList.readBool());
      case 51:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setMuteResponse(radioResponseInfo4);
      case 50:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        separateConnectionResponse(radioResponseInfo4);
      case 49:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getBasebandVersionResponse(radioResponseInfo4, arrayList.readString());
      case 48:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        stopDtmfResponse(radioResponseInfo4);
      case 47:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        startDtmfResponse(radioResponseInfo4);
      case 46:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getAvailableNetworksResponse(radioResponseInfo4, OperatorInfo.readVectorFromParcel((HwParcel)arrayList));
      case 45:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setNetworkSelectionModeManualResponse(radioResponseInfo4);
      case 44:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setNetworkSelectionModeAutomaticResponse(radioResponseInfo4);
      case 43:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getNetworkSelectionModeResponse(radioResponseInfo4, arrayList.readBool());
      case 42:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setBarringPasswordResponse(radioResponseInfo4);
      case 41:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setFacilityLockForAppResponse(radioResponseInfo4, arrayList.readInt32());
      case 40:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getFacilityLockForAppResponse(radioResponseInfo4, arrayList.readInt32());
      case 39:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        deactivateDataCallResponse(radioResponseInfo4);
      case 38:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        acceptCallResponse(radioResponseInfo4);
      case 37:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        acknowledgeLastIncomingGsmSmsResponse(radioResponseInfo4);
      case 36:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCallWaitingResponse(radioResponseInfo4);
      case 35:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCallWaitingResponse(radioResponseInfo4, arrayList.readBool(), arrayList.readInt32());
      case 34:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setCallForwardResponse(radioResponseInfo4);
      case 33:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getCallForwardStatusResponse(radioResponseInfo4, CallForwardInfo.readVectorFromParcel((HwParcel)arrayList));
      case 32:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        setClirResponse(radioResponseInfo4);
      case 31:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        getClirResponse(radioResponseInfo4, arrayList.readInt32(), arrayList.readInt32());
      case 30:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        cancelPendingUssdResponse(radioResponseInfo4);
      case 29:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo4 = new RadioResponseInfo();
        radioResponseInfo4.readFromParcel((HwParcel)arrayList);
        sendUssdResponse(radioResponseInfo4);
      case 28:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        iccIoResult1 = new IccIoResult();
        iccIoResult1.readFromParcel((HwParcel)arrayList);
        iccIOForAppResponse(radioResponseInfo8, iccIoResult1);
      case 27:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        setupDataCallResult = new SetupDataCallResult();
        setupDataCallResult.readFromParcel((HwParcel)arrayList);
        setupDataCallResponse(radioResponseInfo8, setupDataCallResult);
      case 26:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        sendSmsResult1 = new SendSmsResult();
        sendSmsResult1.readFromParcel((HwParcel)arrayList);
        sendSMSExpectMoreResponse(radioResponseInfo8, sendSmsResult1);
      case 25:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        sendSmsResult1 = new SendSmsResult();
        sendSmsResult1.readFromParcel((HwParcel)arrayList);
        sendSmsResponse(radioResponseInfo8, sendSmsResult1);
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
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        dataRegStateResult = new DataRegStateResult();
        dataRegStateResult.readFromParcel((HwParcel)arrayList);
        getDataRegistrationStateResponse(radioResponseInfo8, dataRegStateResult);
      case 20:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo8 = new RadioResponseInfo();
        radioResponseInfo8.readFromParcel((HwParcel)arrayList);
        voiceRegStateResult = new VoiceRegStateResult();
        voiceRegStateResult.readFromParcel((HwParcel)arrayList);
        getVoiceRegistrationStateResponse(radioResponseInfo8, voiceRegStateResult);
      case 19:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo2 = new RadioResponseInfo();
        radioResponseInfo2.readFromParcel((HwParcel)arrayList);
        signalStrength = new SignalStrength();
        signalStrength.readFromParcel((HwParcel)arrayList);
        getSignalStrengthResponse(radioResponseInfo2, signalStrength);
      case 18:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
        radioResponseInfo7 = new RadioResponseInfo();
        radioResponseInfo7.readFromParcel((HwParcel)arrayList);
        lastCallFailCauseInfo = new LastCallFailCauseInfo();
        lastCallFailCauseInfo.readFromParcel((HwParcel)arrayList);
        getLastCallFailCauseResponse(radioResponseInfo7, lastCallFailCauseInfo);
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
    RadioResponseInfo radioResponseInfo7 = new RadioResponseInfo();
    radioResponseInfo7.readFromParcel((HwParcel)arrayList);
    CardStatus cardStatus = new CardStatus();
    cardStatus.readFromParcel((HwParcel)arrayList);
    getIccCardStatusResponse(radioResponseInfo7, cardStatus);
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.radio@1.0::IRadioResponse".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/IRadioResponse$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */