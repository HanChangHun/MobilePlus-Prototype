package android.hardware.radio.V1_2;

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
import android.hardware.radio.V1_1.IRadioIndication;
import android.hardware.radio.V1_1.KeepaliveStatus;
import android.hardware.radio.V1_1.NetworkScanResult;
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
import java.util.Iterator;
import java.util.Objects;

public interface IRadioIndication extends IRadioIndication {
  public static final String kInterfaceName = "android.hardware.radio@1.2::IRadioIndication";
  
  static IRadioIndication asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.radio@1.2::IRadioIndication");
    if (iHwInterface != null && iHwInterface instanceof IRadioIndication)
      return (IRadioIndication)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.radio@1.2::IRadioIndication");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IRadioIndication castFrom(IHwInterface paramIHwInterface) {
    IRadioIndication iRadioIndication;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iRadioIndication = asInterface(paramIHwInterface.asBinder());
    } 
    return iRadioIndication;
  }
  
  static IRadioIndication getService() throws RemoteException {
    return getService("default");
  }
  
  static IRadioIndication getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.2::IRadioIndication", paramString));
  }
  
  static IRadioIndication getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.2::IRadioIndication", paramString, paramBoolean));
  }
  
  static IRadioIndication getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void cellInfoList_1_2(int paramInt, ArrayList<CellInfo> paramArrayList) throws RemoteException;
  
  void currentLinkCapacityEstimate(int paramInt, LinkCapacityEstimate paramLinkCapacityEstimate) throws RemoteException;
  
  void currentPhysicalChannelConfigs(int paramInt, ArrayList<PhysicalChannelConfig> paramArrayList) throws RemoteException;
  
  void currentSignalStrength_1_2(int paramInt, SignalStrength paramSignalStrength) throws RemoteException;
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void networkScanResult_1_2(int paramInt, NetworkScanResult paramNetworkScanResult) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IRadioIndication {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public void callRing(int param1Int, boolean param1Boolean, CdmaSignalInfoRecord param1CdmaSignalInfoRecord) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      param1CdmaSignalInfoRecord.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(18, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void callStateChanged(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void carrierInfoForImsiEncryption(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(46, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaCallWaiting(int param1Int, CdmaCallWaiting param1CdmaCallWaiting) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1CdmaCallWaiting.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(25, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaInfoRec(int param1Int, CdmaInformationRecords param1CdmaInformationRecords) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1CdmaInformationRecords.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(27, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaNewSms(int param1Int, CdmaSmsMessage param1CdmaSmsMessage) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1CdmaSmsMessage.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(20, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaOtaProvisionStatus(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(26, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaPrlChanged(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(31, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaRuimSmsStorageFull(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(22, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cdmaSubscriptionSourceChanged(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(30, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cellInfoList(int param1Int, ArrayList<CellInfo> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      CellInfo.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(35, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void cellInfoList_1_2(int param1Int, ArrayList<CellInfo> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
      null.writeInt32(param1Int);
      CellInfo.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(50, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void currentLinkCapacityEstimate(int param1Int, LinkCapacityEstimate param1LinkCapacityEstimate) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
      null.writeInt32(param1Int);
      param1LinkCapacityEstimate.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(51, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void currentPhysicalChannelConfigs(int param1Int, ArrayList<PhysicalChannelConfig> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
      null.writeInt32(param1Int);
      PhysicalChannelConfig.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(52, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void currentSignalStrength(int param1Int, SignalStrength param1SignalStrength) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1SignalStrength.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void currentSignalStrength_1_2(int param1Int, SignalStrength param1SignalStrength) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
      null.writeInt32(param1Int);
      param1SignalStrength.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(53, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void dataCallListChanged(int param1Int, ArrayList<SetupDataCallResult> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      SetupDataCallResult.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hidl.base@1.0::IBase");
      hwParcel2.writeNativeHandle(param1NativeHandle);
      hwParcel2.writeStringVector(param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(256131655, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void enterEmergencyCallbackMode(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(24, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
    }
    
    public void exitEmergencyCallbackMode(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(32, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public DebugInfo getDebugInfo() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(257049926, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        DebugInfo debugInfo = new DebugInfo();
        this();
        debugInfo.readFromParcel(hwParcel);
        return debugInfo;
      } finally {
        hwParcel.release();
      } 
    }
    
    public ArrayList<byte[]> getHashChain() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256398152, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        ArrayList<byte[]> arrayList = new ArrayList();
        this();
        HwBlob hwBlob1 = hwParcel.readBuffer(16L);
        int i = hwBlob1.getInt32(8L);
        HwBlob hwBlob2 = hwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
        arrayList.clear();
        for (byte b = 0; b < i; b++) {
          byte[] arrayOfByte = new byte[32];
          hwBlob2.copyToInt8Array((b * 32), arrayOfByte, 32);
          arrayList.add(arrayOfByte);
        } 
        return arrayList;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hardwareConfigChanged(int param1Int, ArrayList<HardwareConfig> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HardwareConfig.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(39, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final int hashCode() {
      return asBinder().hashCode();
    }
    
    public void imsNetworkStateChanged(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(36, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void indicateRingbackTone(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(28, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public ArrayList<String> interfaceChain() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256067662, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readStringVector();
      } finally {
        hwParcel.release();
      } 
    }
    
    public String interfaceDescriptor() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256136003, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readString();
      } finally {
        hwParcel.release();
      } 
    }
    
    public void keepaliveStatus(int param1Int, KeepaliveStatus param1KeepaliveStatus) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioIndication");
      null.writeInt32(param1Int);
      param1KeepaliveStatus.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(48, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void lceData(int param1Int, LceDataInfo param1LceDataInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1LceDataInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(43, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) throws RemoteException {
      return this.mRemote.linkToDeath(param1DeathRecipient, param1Long);
    }
    
    public void modemReset(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(45, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void networkScanResult(int param1Int, NetworkScanResult param1NetworkScanResult) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioIndication");
      null.writeInt32(param1Int);
      param1NetworkScanResult.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(47, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void networkScanResult_1_2(int param1Int, NetworkScanResult param1NetworkScanResult) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
      null.writeInt32(param1Int);
      param1NetworkScanResult.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(49, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void networkStateChanged(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void newBroadcastSms(int param1Int, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(21, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void newSms(int param1Int, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void newSmsOnSim(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void newSmsStatusReport(int param1Int, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void nitzTimeReceived(int param1Int, String param1String, long param1Long) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      null.writeInt64(param1Long);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(8, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void notifySyspropsChanged() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(257120595, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void onSupplementaryServiceIndication(int param1Int, StkCcUnsolSsResult param1StkCcUnsolSsResult) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1StkCcUnsolSsResult.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(41, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void onUssd(int param1Int1, int param1Int2, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(7, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void pcoData(int param1Int, PcoDataInfo param1PcoDataInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1PcoDataInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(44, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void ping() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256921159, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void radioCapabilityIndication(int param1Int, RadioCapability param1RadioCapability) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1RadioCapability.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(40, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void radioStateChanged(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void resendIncallMute(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(29, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void restrictedStateChanged(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(23, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void rilConnected(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(33, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setHALInstrumentation() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256462420, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void simRefresh(int param1Int, SimRefreshResult param1SimRefreshResult) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1SimRefreshResult.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(17, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void simSmsStorageFull(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(16, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void simStatusChanged(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(19, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void srvccStateNotify(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(38, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stkCallControlAlphaNotify(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(42, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stkCallSetup(int param1Int, long param1Long) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeInt64(param1Long);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(15, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stkEventNotify(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(14, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stkProactiveCommand(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(13, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stkSessionEnd(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(12, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void subscriptionStatusChanged(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(37, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void suppSvcNotify(int param1Int, SuppSvcNotification param1SuppSvcNotification) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int);
      param1SuppSvcNotification.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(11, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public String toString() {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(interfaceDescriptor());
        stringBuilder.append("@Proxy");
        return stringBuilder.toString();
      } catch (RemoteException remoteException) {
        return "[class or subclass of android.hardware.radio@1.2::IRadioIndication]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
    
    public void voiceRadioTechChanged(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(34, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
  }
  
  public static abstract class Stub extends HwBinder implements IRadioIndication {
    public IHwBinder asBinder() {
      return (IHwBinder)this;
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) {}
    
    public final DebugInfo getDebugInfo() {
      DebugInfo debugInfo = new DebugInfo();
      debugInfo.pid = HidlSupport.getPidIfSharable();
      debugInfo.ptr = 0L;
      debugInfo.arch = 0;
      return debugInfo;
    }
    
    public final ArrayList<byte[]> getHashChain() {
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
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
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.2::IRadioIndication", "android.hardware.radio@1.1::IRadioIndication", "android.hardware.radio@1.0::IRadioIndication", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.radio@1.2::IRadioIndication";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      HwBlob hwBlob1;
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
      HwBlob hwBlob2;
      boolean bool;
      switch (param1Int1) {
        default:
          switch (param1Int1) {
            default:
              return;
            case 257120595:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              notifySyspropsChanged();
            case 257049926:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              debugInfo = getDebugInfo();
              param1HwParcel2.writeStatus(0);
              debugInfo.writeToParcel(param1HwParcel2);
              param1HwParcel2.send();
            case 256921159:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              ping();
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.send();
            case 256462420:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              setHALInstrumentation();
            case 256398152:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              arrayList1 = getHashChain();
              param1HwParcel2.writeStatus(0);
              hwBlob2 = new HwBlob(16);
              param1Int2 = arrayList1.size();
              hwBlob2.putInt32(8L, param1Int2);
              hwBlob2.putBool(12L, false);
              hwBlob1 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                byte[] arrayOfByte = arrayList1.get(param1Int1);
                if (arrayOfByte != null && arrayOfByte.length == 32) {
                  hwBlob1.putInt8Array(l, arrayOfByte);
                  param1Int1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob2.putBlob(0L, hwBlob1);
              param1HwParcel2.writeBuffer(hwBlob2);
              param1HwParcel2.send();
            case 256136003:
              hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
              str = interfaceDescriptor();
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.writeString(str);
              param1HwParcel2.send();
            case 256131655:
              str.enforceInterface("android.hidl.base@1.0::IBase");
              debug(str.readNativeHandle(), str.readStringVector());
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.send();
            case 256067662:
              break;
          } 
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 53:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          signalStrength1 = new SignalStrength();
          signalStrength1.readFromParcel((HwParcel)arrayList);
          currentSignalStrength_1_2(param1Int1, signalStrength1);
        case 52:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
          currentPhysicalChannelConfigs(arrayList.readInt32(), PhysicalChannelConfig.readVectorFromParcel((HwParcel)arrayList));
        case 51:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          linkCapacityEstimate = new LinkCapacityEstimate();
          linkCapacityEstimate.readFromParcel((HwParcel)arrayList);
          currentLinkCapacityEstimate(param1Int1, linkCapacityEstimate);
        case 50:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
          cellInfoList_1_2(arrayList.readInt32(), CellInfo.readVectorFromParcel((HwParcel)arrayList));
        case 49:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          networkScanResult1 = new NetworkScanResult();
          networkScanResult1.readFromParcel((HwParcel)arrayList);
          networkScanResult_1_2(param1Int1, networkScanResult1);
        case 48:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          keepaliveStatus = new KeepaliveStatus();
          keepaliveStatus.readFromParcel((HwParcel)arrayList);
          keepaliveStatus(param1Int1, keepaliveStatus);
        case 47:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          networkScanResult = new NetworkScanResult();
          networkScanResult.readFromParcel((HwParcel)arrayList);
          networkScanResult(param1Int1, networkScanResult);
        case 46:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioIndication");
          carrierInfoForImsiEncryption(arrayList.readInt32());
        case 45:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          modemReset(arrayList.readInt32(), arrayList.readString());
        case 44:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          pcoDataInfo = new PcoDataInfo();
          pcoDataInfo.readFromParcel((HwParcel)arrayList);
          pcoData(param1Int1, pcoDataInfo);
        case 43:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          lceDataInfo = new LceDataInfo();
          lceDataInfo.readFromParcel((HwParcel)arrayList);
          lceData(param1Int1, lceDataInfo);
        case 42:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          stkCallControlAlphaNotify(arrayList.readInt32(), arrayList.readString());
        case 41:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          stkCcUnsolSsResult = new StkCcUnsolSsResult();
          stkCcUnsolSsResult.readFromParcel((HwParcel)arrayList);
          onSupplementaryServiceIndication(param1Int1, stkCcUnsolSsResult);
        case 40:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          radioCapability = new RadioCapability();
          radioCapability.readFromParcel((HwParcel)arrayList);
          radioCapabilityIndication(param1Int1, radioCapability);
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
          param1Int1 = arrayList.readInt32();
          cdmaInformationRecords = new CdmaInformationRecords();
          cdmaInformationRecords.readFromParcel((HwParcel)arrayList);
          cdmaInfoRec(param1Int1, cdmaInformationRecords);
        case 26:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          cdmaOtaProvisionStatus(arrayList.readInt32(), arrayList.readInt32());
        case 25:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          cdmaCallWaiting = new CdmaCallWaiting();
          cdmaCallWaiting.readFromParcel((HwParcel)arrayList);
          cdmaCallWaiting(param1Int1, cdmaCallWaiting);
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
          param1Int1 = arrayList.readInt32();
          cdmaSmsMessage = new CdmaSmsMessage();
          cdmaSmsMessage.readFromParcel((HwParcel)arrayList);
          cdmaNewSms(param1Int1, cdmaSmsMessage);
        case 19:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          simStatusChanged(arrayList.readInt32());
        case 18:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          bool = arrayList.readBool();
          cdmaSignalInfoRecord = new CdmaSignalInfoRecord();
          cdmaSignalInfoRecord.readFromParcel((HwParcel)arrayList);
          callRing(param1Int1, bool, cdmaSignalInfoRecord);
        case 17:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          simRefreshResult = new SimRefreshResult();
          simRefreshResult.readFromParcel((HwParcel)arrayList);
          simRefresh(param1Int1, simRefreshResult);
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
          param1Int1 = arrayList.readInt32();
          suppSvcNotification = new SuppSvcNotification();
          suppSvcNotification.readFromParcel((HwParcel)arrayList);
          suppSvcNotify(param1Int1, suppSvcNotification);
        case 10:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          dataCallListChanged(arrayList.readInt32(), SetupDataCallResult.readVectorFromParcel((HwParcel)arrayList));
        case 9:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioIndication");
          param1Int1 = arrayList.readInt32();
          signalStrength = new SignalStrength();
          signalStrength.readFromParcel((HwParcel)arrayList);
          currentSignalStrength(param1Int1, signalStrength);
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
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.radio@1.2::IRadioIndication".equals(param1String) ? this : null);
    }
    
    public void registerAsService(String param1String) throws RemoteException {
      registerService(param1String);
    }
    
    public final void setHALInstrumentation() {}
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Stub");
      return stringBuilder.toString();
    }
    
    public final boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) {
      return true;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/IRadioIndication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */