package android.hardware.radio.V1_1;

import android.hardware.radio.V1_0.CallForwardInfo;
import android.hardware.radio.V1_0.CarrierRestrictions;
import android.hardware.radio.V1_0.CdmaBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.CdmaSmsAck;
import android.hardware.radio.V1_0.CdmaSmsMessage;
import android.hardware.radio.V1_0.CdmaSmsWriteArgs;
import android.hardware.radio.V1_0.DataProfileInfo;
import android.hardware.radio.V1_0.Dial;
import android.hardware.radio.V1_0.GsmBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.GsmSmsMessage;
import android.hardware.radio.V1_0.IRadioIndication;
import android.hardware.radio.V1_0.IRadioResponse;
import android.hardware.radio.V1_0.IccIo;
import android.hardware.radio.V1_0.ImsSmsMessage;
import android.hardware.radio.V1_0.NvWriteItem;
import android.hardware.radio.V1_0.RadioCapability;
import android.hardware.radio.V1_0.SelectUiccSub;
import android.hardware.radio.V1_0.SimApdu;
import android.hardware.radio.V1_0.SmsWriteArgs;
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

public abstract class Stub extends HwBinder implements IRadio {
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
              -9, -98, -33, 80, -93, 120, -87, -55, -69, 115, 
              Byte.MAX_VALUE, -109, -14, 5, -38, -71, 27, 76, 99, -22, 
              73, 114, 58, -4, 111, -123, 108, 19, -126, 3, 
              -22, -127 }, { 
              -101, 90, -92, -103, -20, 59, 66, 38, -15, 95, 
              72, -11, -19, 8, -119, 110, 47, -64, 103, 111, 
              -105, -116, -98, 25, -100, 29, -94, 29, -86, -16, 
              2, -90 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.1::IRadio", "android.hardware.radio@1.0::IRadio", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.radio@1.1::IRadio";
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
    KeepaliveRequest keepaliveRequest;
    NetworkScanRequest networkScanRequest;
    ImsiEncryptionInfo imsiEncryptionInfo;
    CarrierRestrictions carrierRestrictions;
    RadioCapability radioCapability;
    SelectUiccSub selectUiccSub;
    NvWriteItem nvWriteItem;
    SimApdu simApdu;
    ImsSmsMessage imsSmsMessage;
    DataProfileInfo dataProfileInfo2;
    CdmaSmsWriteArgs cdmaSmsWriteArgs;
    CdmaSmsAck cdmaSmsAck;
    CdmaSmsMessage cdmaSmsMessage;
    SmsWriteArgs smsWriteArgs;
    CallForwardInfo callForwardInfo;
    IccIo iccIo;
    DataProfileInfo dataProfileInfo1;
    GsmSmsMessage gsmSmsMessage;
    Dial dial;
    ArrayList<byte[]> arrayList1;
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
            hwBlob2 = new HwBlob(16);
            paramInt2 = arrayList1.size();
            hwBlob2.putInt32(8L, paramInt2);
            hwBlob2.putBool(12L, false);
            hwBlob1 = new HwBlob(paramInt2 * 32);
            paramInt1 = 0;
            while (paramInt1 < paramInt2) {
              long l = (paramInt1 * 32);
              byte[] arrayOfByte = arrayList1.get(paramInt1);
              if (arrayOfByte != null && arrayOfByte.length == 32) {
                hwBlob1.putInt8Array(l, arrayOfByte);
                paramInt1++;
                continue;
              } 
              throw new IllegalArgumentException("Array element is not of the expected length");
            } 
            hwBlob2.putBlob(0L, hwBlob1);
            paramHwParcel2.writeBuffer(hwBlob2);
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
      case 136:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
        stopKeepalive(arrayList.readInt32(), arrayList.readInt32());
      case 135:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
        paramInt1 = arrayList.readInt32();
        keepaliveRequest = new KeepaliveRequest();
        keepaliveRequest.readFromParcel((HwParcel)arrayList);
        startKeepalive(paramInt1, keepaliveRequest);
      case 134:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
        stopNetworkScan(arrayList.readInt32());
      case 133:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
        paramInt1 = arrayList.readInt32();
        networkScanRequest = new NetworkScanRequest();
        networkScanRequest.readFromParcel((HwParcel)arrayList);
        startNetworkScan(paramInt1, networkScanRequest);
      case 132:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
        setSimCardPower_1_1(arrayList.readInt32(), arrayList.readInt32());
      case 131:
        arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
        paramInt1 = arrayList.readInt32();
        imsiEncryptionInfo = new ImsiEncryptionInfo();
        imsiEncryptionInfo.readFromParcel((HwParcel)arrayList);
        setCarrierInfoForImsiEncryption(paramInt1, imsiEncryptionInfo);
      case 130:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        responseAcknowledgement();
      case 129:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setSimCardPower(arrayList.readInt32(), arrayList.readBool());
      case 128:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setIndicationFilter(arrayList.readInt32(), arrayList.readInt32());
      case 127:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendDeviceState(arrayList.readInt32(), arrayList.readInt32(), arrayList.readBool());
      case 126:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getAllowedCarriers(arrayList.readInt32());
      case 125:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        bool = arrayList.readBool();
        carrierRestrictions = new CarrierRestrictions();
        carrierRestrictions.readFromParcel((HwParcel)arrayList);
        setAllowedCarriers(paramInt1, bool, carrierRestrictions);
      case 124:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getModemActivityInfo(arrayList.readInt32());
      case 123:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        pullLceData(arrayList.readInt32());
      case 122:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        stopLceService(arrayList.readInt32());
      case 121:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        startLceService(arrayList.readInt32(), arrayList.readInt32(), arrayList.readBool());
      case 120:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        radioCapability = new RadioCapability();
        radioCapability.readFromParcel((HwParcel)arrayList);
        setRadioCapability(paramInt1, radioCapability);
      case 119:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getRadioCapability(arrayList.readInt32());
      case 118:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        requestShutdown(arrayList.readInt32());
      case 117:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setDataProfile(arrayList.readInt32(), DataProfileInfo.readVectorFromParcel((HwParcel)arrayList), arrayList.readBool());
      case 116:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        requestIccSimAuthentication(arrayList.readInt32(), arrayList.readInt32(), arrayList.readString(), arrayList.readString());
      case 115:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getHardwareConfig(arrayList.readInt32());
      case 114:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setDataAllowed(arrayList.readInt32(), arrayList.readBool());
      case 113:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        selectUiccSub = new SelectUiccSub();
        selectUiccSub.readFromParcel((HwParcel)arrayList);
        setUiccSubscription(paramInt1, selectUiccSub);
      case 112:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        nvResetConfig(arrayList.readInt32(), arrayList.readInt32());
      case 111:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        nvWriteCdmaPrl(arrayList.readInt32(), arrayList.readInt8Vector());
      case 110:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        nvWriteItem = new NvWriteItem();
        nvWriteItem.readFromParcel((HwParcel)arrayList);
        nvWriteItem(paramInt1, nvWriteItem);
      case 109:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        nvReadItem(arrayList.readInt32(), arrayList.readInt32());
      case 108:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        simApdu = new SimApdu();
        simApdu.readFromParcel((HwParcel)arrayList);
        iccTransmitApduLogicalChannel(paramInt1, simApdu);
      case 107:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        iccCloseLogicalChannel(arrayList.readInt32(), arrayList.readInt32());
      case 106:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        iccOpenLogicalChannel(arrayList.readInt32(), arrayList.readString(), arrayList.readInt32());
      case 105:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        simApdu = new SimApdu();
        simApdu.readFromParcel((HwParcel)arrayList);
        iccTransmitApduBasicChannel(paramInt1, simApdu);
      case 104:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        imsSmsMessage = new ImsSmsMessage();
        imsSmsMessage.readFromParcel((HwParcel)arrayList);
        sendImsSms(paramInt1, imsSmsMessage);
      case 103:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getImsRegistrationState(arrayList.readInt32());
      case 102:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        dataProfileInfo2 = new DataProfileInfo();
        dataProfileInfo2.readFromParcel((HwParcel)arrayList);
        setInitialAttachApn(paramInt1, dataProfileInfo2, arrayList.readBool(), arrayList.readBool());
      case 101:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setCellInfoListRate(arrayList.readInt32(), arrayList.readInt32());
      case 100:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCellInfoList(arrayList.readInt32());
      case 99:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getVoiceRadioTechnology(arrayList.readInt32());
      case 98:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendEnvelopeWithStatus(arrayList.readInt32(), arrayList.readString());
      case 97:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        acknowledgeIncomingGsmSmsWithPdu(arrayList.readInt32(), arrayList.readBool(), arrayList.readString());
      case 96:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        requestIsimAuthentication(arrayList.readInt32(), arrayList.readString());
      case 95:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCdmaSubscriptionSource(arrayList.readInt32());
      case 94:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        reportStkServiceIsRunning(arrayList.readInt32());
      case 93:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        reportSmsMemoryStatus(arrayList.readInt32(), arrayList.readBool());
      case 92:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setSmscAddress(arrayList.readInt32(), arrayList.readString());
      case 91:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getSmscAddress(arrayList.readInt32());
      case 90:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        exitEmergencyCallbackMode(arrayList.readInt32());
      case 89:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getDeviceIdentity(arrayList.readInt32());
      case 88:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        deleteSmsOnRuim(arrayList.readInt32(), arrayList.readInt32());
      case 87:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        cdmaSmsWriteArgs = new CdmaSmsWriteArgs();
        cdmaSmsWriteArgs.readFromParcel((HwParcel)arrayList);
        writeSmsToRuim(paramInt1, cdmaSmsWriteArgs);
      case 86:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCDMASubscription(arrayList.readInt32());
      case 85:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setCdmaBroadcastActivation(arrayList.readInt32(), arrayList.readBool());
      case 84:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setCdmaBroadcastConfig(arrayList.readInt32(), CdmaBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
      case 83:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCdmaBroadcastConfig(arrayList.readInt32());
      case 82:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setGsmBroadcastActivation(arrayList.readInt32(), arrayList.readBool());
      case 81:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setGsmBroadcastConfig(arrayList.readInt32(), GsmBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
      case 80:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getGsmBroadcastConfig(arrayList.readInt32());
      case 79:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        cdmaSmsAck = new CdmaSmsAck();
        cdmaSmsAck.readFromParcel((HwParcel)arrayList);
        acknowledgeLastIncomingCdmaSms(paramInt1, cdmaSmsAck);
      case 78:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        cdmaSmsMessage = new CdmaSmsMessage();
        cdmaSmsMessage.readFromParcel((HwParcel)arrayList);
        sendCdmaSms(paramInt1, cdmaSmsMessage);
      case 77:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendBurstDtmf(arrayList.readInt32(), arrayList.readString(), arrayList.readInt32(), arrayList.readInt32());
      case 76:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendCDMAFeatureCode(arrayList.readInt32(), arrayList.readString());
      case 75:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getPreferredVoicePrivacy(arrayList.readInt32());
      case 74:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setPreferredVoicePrivacy(arrayList.readInt32(), arrayList.readBool());
      case 73:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getTTYMode(arrayList.readInt32());
      case 72:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setTTYMode(arrayList.readInt32(), arrayList.readInt32());
      case 71:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCdmaRoamingPreference(arrayList.readInt32());
      case 70:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setCdmaRoamingPreference(arrayList.readInt32(), arrayList.readInt32());
      case 69:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setCdmaSubscriptionSource(arrayList.readInt32(), arrayList.readInt32());
      case 68:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setLocationUpdates(arrayList.readInt32(), arrayList.readBool());
      case 67:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getNeighboringCids(arrayList.readInt32());
      case 66:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getPreferredNetworkType(arrayList.readInt32());
      case 65:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setPreferredNetworkType(arrayList.readInt32(), arrayList.readInt32());
      case 64:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        explicitCallTransfer(arrayList.readInt32());
      case 63:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        handleStkCallSetupRequestFromSim(arrayList.readInt32(), arrayList.readBool());
      case 62:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendTerminalResponseToSim(arrayList.readInt32(), arrayList.readString());
      case 61:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendEnvelope(arrayList.readInt32(), arrayList.readString());
      case 60:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getAvailableBandModes(arrayList.readInt32());
      case 59:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setBandMode(arrayList.readInt32(), arrayList.readInt32());
      case 58:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        deleteSmsOnSim(arrayList.readInt32(), arrayList.readInt32());
      case 57:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        smsWriteArgs = new SmsWriteArgs();
        smsWriteArgs.readFromParcel((HwParcel)arrayList);
        writeSmsToSim(paramInt1, smsWriteArgs);
      case 56:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setSuppServiceNotifications(arrayList.readInt32(), arrayList.readBool());
      case 55:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getDataCallList(arrayList.readInt32());
      case 54:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getClip(arrayList.readInt32());
      case 53:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getMute(arrayList.readInt32());
      case 52:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setMute(arrayList.readInt32(), arrayList.readBool());
      case 51:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        separateConnection(arrayList.readInt32(), arrayList.readInt32());
      case 50:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getBasebandVersion(arrayList.readInt32());
      case 49:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        stopDtmf(arrayList.readInt32());
      case 48:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        startDtmf(arrayList.readInt32(), arrayList.readString());
      case 47:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getAvailableNetworks(arrayList.readInt32());
      case 46:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setNetworkSelectionModeManual(arrayList.readInt32(), arrayList.readString());
      case 45:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setNetworkSelectionModeAutomatic(arrayList.readInt32());
      case 44:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getNetworkSelectionMode(arrayList.readInt32());
      case 43:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setBarringPassword(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 42:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setFacilityLockForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readBool(), arrayList.readString(), arrayList.readInt32(), arrayList.readString());
      case 41:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getFacilityLockForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readInt32(), arrayList.readString());
      case 40:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        deactivateDataCall(arrayList.readInt32(), arrayList.readInt32(), arrayList.readBool());
      case 39:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        acceptCall(arrayList.readInt32());
      case 38:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        acknowledgeLastIncomingGsmSms(arrayList.readInt32(), arrayList.readBool(), arrayList.readInt32());
      case 37:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setCallWaiting(arrayList.readInt32(), arrayList.readBool(), arrayList.readInt32());
      case 36:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCallWaiting(arrayList.readInt32(), arrayList.readInt32());
      case 35:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        callForwardInfo = new CallForwardInfo();
        callForwardInfo.readFromParcel((HwParcel)arrayList);
        setCallForward(paramInt1, callForwardInfo);
      case 34:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        callForwardInfo = new CallForwardInfo();
        callForwardInfo.readFromParcel((HwParcel)arrayList);
        getCallForwardStatus(paramInt1, callForwardInfo);
      case 33:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setClir(arrayList.readInt32(), arrayList.readInt32());
      case 32:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getClir(arrayList.readInt32());
      case 31:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        cancelPendingUssd(arrayList.readInt32());
      case 30:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendUssd(arrayList.readInt32(), arrayList.readString());
      case 29:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        iccIo = new IccIo();
        iccIo.readFromParcel((HwParcel)arrayList);
        iccIOForApp(paramInt1, iccIo);
      case 28:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt2 = arrayList.readInt32();
        paramInt1 = arrayList.readInt32();
        dataProfileInfo1 = new DataProfileInfo();
        dataProfileInfo1.readFromParcel((HwParcel)arrayList);
        setupDataCall(paramInt2, paramInt1, dataProfileInfo1, arrayList.readBool(), arrayList.readBool(), arrayList.readBool());
      case 27:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        gsmSmsMessage = new GsmSmsMessage();
        gsmSmsMessage.readFromParcel((HwParcel)arrayList);
        sendSMSExpectMore(paramInt1, gsmSmsMessage);
      case 26:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        gsmSmsMessage = new GsmSmsMessage();
        gsmSmsMessage.readFromParcel((HwParcel)arrayList);
        sendSms(paramInt1, gsmSmsMessage);
      case 25:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        sendDtmf(arrayList.readInt32(), arrayList.readString());
      case 24:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        setRadioPower(arrayList.readInt32(), arrayList.readBool());
      case 23:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getOperator(arrayList.readInt32());
      case 22:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getDataRegistrationState(arrayList.readInt32());
      case 21:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getVoiceRegistrationState(arrayList.readInt32());
      case 20:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getSignalStrength(arrayList.readInt32());
      case 19:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getLastCallFailCause(arrayList.readInt32());
      case 18:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        rejectCall(arrayList.readInt32());
      case 17:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        conference(arrayList.readInt32());
      case 16:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        switchWaitingOrHoldingAndActive(arrayList.readInt32());
      case 15:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        hangupForegroundResumeBackground(arrayList.readInt32());
      case 14:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        hangupWaitingOrBackground(arrayList.readInt32());
      case 13:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        hangup(arrayList.readInt32(), arrayList.readInt32());
      case 12:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getImsiForApp(arrayList.readInt32(), arrayList.readString());
      case 11:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        paramInt1 = arrayList.readInt32();
        dial = new Dial();
        dial.readFromParcel((HwParcel)arrayList);
        dial(paramInt1, dial);
      case 10:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getCurrentCalls(arrayList.readInt32());
      case 9:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        supplyNetworkDepersonalization(arrayList.readInt32(), arrayList.readString());
      case 8:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        changeIccPin2ForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 7:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        changeIccPinForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 6:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        supplyIccPuk2ForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 5:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        supplyIccPin2ForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString());
      case 4:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        supplyIccPukForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
      case 3:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        supplyIccPinForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString());
      case 2:
        arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
        getIccCardStatus(arrayList.readInt32());
      case 1:
        break;
    } 
    arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
    setResponseFunctions(IRadioResponse.asInterface(arrayList.readStrongBinder()), IRadioIndication.asInterface(arrayList.readStrongBinder()));
    dial.writeStatus(0);
    dial.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.radio@1.1::IRadio".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/IRadio$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */