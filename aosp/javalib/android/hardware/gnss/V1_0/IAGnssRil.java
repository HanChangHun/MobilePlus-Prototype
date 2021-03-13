package android.hardware.gnss.V1_0;

import android.internal.hidl.base.V1_0.DebugInfo;
import android.internal.hidl.base.V1_0.IBase;
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

public interface IAGnssRil extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IAGnssRil";
  
  static IAGnssRil asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IAGnssRil");
    if (iHwInterface != null && iHwInterface instanceof IAGnssRil)
      return (IAGnssRil)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IAGnssRil");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IAGnssRil castFrom(IHwInterface paramIHwInterface) {
    IAGnssRil iAGnssRil;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iAGnssRil = asInterface(paramIHwInterface.asBinder());
    } 
    return iAGnssRil;
  }
  
  static IAGnssRil getService() throws RemoteException {
    return getService("default");
  }
  
  static IAGnssRil getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IAGnssRil", paramString));
  }
  
  static IAGnssRil getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IAGnssRil", paramString, paramBoolean));
  }
  
  static IAGnssRil getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setCallback(IAGnssRilCallback paramIAGnssRilCallback) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  void setRefLocation(AGnssRefLocation paramAGnssRefLocation) throws RemoteException;
  
  boolean setSetId(byte paramByte, String paramString) throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  boolean updateNetworkAvailability(boolean paramBoolean, String paramString) throws RemoteException;
  
  boolean updateNetworkState(boolean paramBoolean1, byte paramByte, boolean paramBoolean2) throws RemoteException;
  
  public static final class AGnssRefLocation {
    public IAGnssRil.AGnssRefLocationCellID cellID = new IAGnssRil.AGnssRefLocationCellID();
    
    public byte type = (byte)0;
    
    public static final ArrayList<AGnssRefLocation> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<AGnssRefLocation> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 20), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        AGnssRefLocation aGnssRefLocation = new AGnssRefLocation();
        aGnssRefLocation.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 20));
        arrayList.add(aGnssRefLocation);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<AGnssRefLocation> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 20);
      for (byte b = 0; b < i; b++)
        ((AGnssRefLocation)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != AGnssRefLocation.class)
        return false; 
      param1Object = param1Object;
      return (this.type != ((AGnssRefLocation)param1Object).type) ? false : (!!HidlSupport.deepEquals(this.cellID, ((AGnssRefLocation)param1Object).cellID));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.cellID)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.type = param1HwBlob.getInt8(0L + param1Long);
      this.cellID.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 4L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(20L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".type = ");
      stringBuilder.append(IAGnssRil.AGnssRefLocationType.toString(this.type));
      stringBuilder.append(", .cellID = ");
      stringBuilder.append(this.cellID);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt8(0L + param1Long, this.type);
      this.cellID.writeEmbeddedToBlob(param1HwBlob, 4L + param1Long);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(20);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class AGnssRefLocationCellID {
    public int cid = 0;
    
    public short lac = (short)0;
    
    public short mcc = (short)0;
    
    public short mnc = (short)0;
    
    public short pcid = (short)0;
    
    public short tac = (short)0;
    
    public byte type = (byte)0;
    
    public static final ArrayList<AGnssRefLocationCellID> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<AGnssRefLocationCellID> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 16), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        AGnssRefLocationCellID aGnssRefLocationCellID = new AGnssRefLocationCellID();
        aGnssRefLocationCellID.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 16));
        arrayList.add(aGnssRefLocationCellID);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<AGnssRefLocationCellID> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 16);
      for (byte b = 0; b < i; b++)
        ((AGnssRefLocationCellID)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != AGnssRefLocationCellID.class)
        return false; 
      param1Object = param1Object;
      return (this.type != ((AGnssRefLocationCellID)param1Object).type) ? false : ((this.mcc != ((AGnssRefLocationCellID)param1Object).mcc) ? false : ((this.mnc != ((AGnssRefLocationCellID)param1Object).mnc) ? false : ((this.lac != ((AGnssRefLocationCellID)param1Object).lac) ? false : ((this.cid != ((AGnssRefLocationCellID)param1Object).cid) ? false : ((this.tac != ((AGnssRefLocationCellID)param1Object).tac) ? false : (!(this.pcid != ((AGnssRefLocationCellID)param1Object).pcid)))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.mcc))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.mnc))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.lac))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.tac))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.pcid))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.type = param1HwBlob.getInt8(0L + param1Long);
      this.mcc = param1HwBlob.getInt16(2L + param1Long);
      this.mnc = param1HwBlob.getInt16(4L + param1Long);
      this.lac = param1HwBlob.getInt16(6L + param1Long);
      this.cid = param1HwBlob.getInt32(8L + param1Long);
      this.tac = param1HwBlob.getInt16(12L + param1Long);
      this.pcid = param1HwBlob.getInt16(14L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(16L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".type = ");
      stringBuilder.append(IAGnssRil.AGnssRefLocationType.toString(this.type));
      stringBuilder.append(", .mcc = ");
      stringBuilder.append(this.mcc);
      stringBuilder.append(", .mnc = ");
      stringBuilder.append(this.mnc);
      stringBuilder.append(", .lac = ");
      stringBuilder.append(this.lac);
      stringBuilder.append(", .cid = ");
      stringBuilder.append(this.cid);
      stringBuilder.append(", .tac = ");
      stringBuilder.append(this.tac);
      stringBuilder.append(", .pcid = ");
      stringBuilder.append(this.pcid);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt8(0L + param1Long, this.type);
      param1HwBlob.putInt16(2L + param1Long, this.mcc);
      param1HwBlob.putInt16(4L + param1Long, this.mnc);
      param1HwBlob.putInt16(6L + param1Long, this.lac);
      param1HwBlob.putInt32(8L + param1Long, this.cid);
      param1HwBlob.putInt16(12L + param1Long, this.tac);
      param1HwBlob.putInt16(14L + param1Long, this.pcid);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(16);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class AGnssRefLocationType {
    public static final byte GSM_CELLID = 1;
    
    public static final byte LTE_CELLID = 4;
    
    public static final byte UMTS_CELLID = 2;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("GSM_CELLID");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("UMTS_CELLID");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x4) == 4) {
        arrayList.add("LTE_CELLID");
        b1 = (byte)(b2 | 0x4);
      } 
      if (param1Byte != b1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b1 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 1)
        return "GSM_CELLID"; 
      if (param1Byte == 2)
        return "UMTS_CELLID"; 
      if (param1Byte == 4)
        return "LTE_CELLID"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class NetworkType {
    public static final byte DUN = 4;
    
    public static final byte HIPRI = 5;
    
    public static final byte MMS = 2;
    
    public static final byte MOBILE = 0;
    
    public static final byte SUPL = 3;
    
    public static final byte WIFI = 1;
    
    public static final byte WIMAX = 6;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("MOBILE");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("WIFI");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("MMS");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("SUPL");
        b1 = (byte)(b2 | 0x3);
      } 
      b2 = b1;
      if ((param1Byte & 0x4) == 4) {
        arrayList.add("DUN");
        b2 = (byte)(b1 | 0x4);
      } 
      b1 = b2;
      if ((param1Byte & 0x5) == 5) {
        arrayList.add("HIPRI");
        b1 = (byte)(b2 | 0x5);
      } 
      b2 = b1;
      if ((param1Byte & 0x6) == 6) {
        arrayList.add("WIMAX");
        b2 = (byte)(b1 | 0x6);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 0)
        return "MOBILE"; 
      if (param1Byte == 1)
        return "WIFI"; 
      if (param1Byte == 2)
        return "MMS"; 
      if (param1Byte == 3)
        return "SUPL"; 
      if (param1Byte == 4)
        return "DUN"; 
      if (param1Byte == 5)
        return "HIPRI"; 
      if (param1Byte == 6)
        return "WIMAX"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IAGnssRil {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
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
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
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
        HwBlob hwBlob = hwParcel.readBuffer(16L);
        int i = hwBlob.getInt32(8L);
        hwBlob = hwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
        arrayList.clear();
        for (byte b = 0; b < i; b++) {
          byte[] arrayOfByte = new byte[32];
          hwBlob.copyToInt8Array((b * 32), arrayOfByte, 32);
          arrayList.add(arrayOfByte);
        } 
        return arrayList;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final int hashCode() {
      return asBinder().hashCode();
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
    
    public boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) throws RemoteException {
      return this.mRemote.linkToDeath(param1DeathRecipient, param1Long);
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
    
    public void setCallback(IAGnssRilCallback param1IAGnssRilCallback) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssRil");
      if (param1IAGnssRilCallback == null) {
        param1IAGnssRilCallback = null;
      } else {
        iHwBinder = param1IAGnssRilCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
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
    
    public void setRefLocation(IAGnssRil.AGnssRefLocation param1AGnssRefLocation) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssRil");
      param1AGnssRefLocation.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setSetId(byte param1Byte, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssRil");
      null.writeInt8(param1Byte);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
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
        return "[class or subclass of android.hardware.gnss@1.0::IAGnssRil]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
    
    public boolean updateNetworkAvailability(boolean param1Boolean, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssRil");
      null.writeBool(param1Boolean);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Boolean = hwParcel.readBool();
        return param1Boolean;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean updateNetworkState(boolean param1Boolean1, byte param1Byte, boolean param1Boolean2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssRil");
      null.writeBool(param1Boolean1);
      null.writeInt8(param1Byte);
      null.writeBool(param1Boolean2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Boolean1 = hwParcel.readBool();
        return param1Boolean1;
      } finally {
        hwParcel.release();
      } 
    }
  }
  
  public static final class SetIDType {
    public static final byte IMSI = 1;
    
    public static final byte MSISDM = 2;
    
    public static final byte NONE = 0;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("NONE");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("IMSI");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("MSISDM");
        b2 = (byte)(b1 | 0x2);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 0)
        return "NONE"; 
      if (param1Byte == 1)
        return "IMSI"; 
      if (param1Byte == 2)
        return "MSISDM"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static abstract class Stub extends HwBinder implements IAGnssRil {
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
      byte[] arrayOfByte = { 
          -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
          -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
          -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
          -54, 76 };
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                -47, 110, 106, 53, -101, -26, -106, 62, -89, 83, 
                -41, 19, -114, -124, -20, -14, -71, 48, 82, 9, 
                121, 56, -109, -116, 77, 54, -41, -92, 126, -94, 
                -30, -82 }, arrayOfByte }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IAGnssRil", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IAGnssRil";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      ArrayList<String> arrayList;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                DebugInfo debugInfo;
                HwBlob hwBlob1;
                String str;
                ArrayList<byte[]> arrayList1;
                HwBlob hwBlob2;
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
                    hwBlob1 = new HwBlob(16);
                    param1Int2 = arrayList1.size();
                    hwBlob1.putInt32(8L, param1Int2);
                    hwBlob1.putBool(12L, false);
                    hwBlob2 = new HwBlob(param1Int2 * 32);
                    param1Int1 = 0;
                    while (param1Int1 < param1Int2) {
                      long l = (param1Int1 * 32);
                      byte[] arrayOfByte = arrayList1.get(param1Int1);
                      if (arrayOfByte != null && arrayOfByte.length == 32) {
                        hwBlob2.putInt8Array(l, arrayOfByte);
                        param1Int1++;
                        continue;
                      } 
                      throw new IllegalArgumentException("Array element is not of the expected length");
                    } 
                    hwBlob1.putBlob(0L, hwBlob2);
                    param1HwParcel2.writeBuffer(hwBlob1);
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
              } 
              arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssRil");
              boolean bool2 = updateNetworkAvailability(arrayList.readBool(), arrayList.readString());
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.writeBool(bool2);
              param1HwParcel2.send();
            } 
            arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssRil");
            boolean bool1 = updateNetworkState(arrayList.readBool(), arrayList.readInt8(), arrayList.readBool());
            param1HwParcel2.writeStatus(0);
            param1HwParcel2.writeBool(bool1);
            param1HwParcel2.send();
          } 
          arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssRil");
          boolean bool = setSetId(arrayList.readInt8(), arrayList.readString());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssRil");
        IAGnssRil.AGnssRefLocation aGnssRefLocation = new IAGnssRil.AGnssRefLocation();
        aGnssRefLocation.readFromParcel((HwParcel)arrayList);
        setRefLocation(aGnssRefLocation);
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssRil");
      setCallback(IAGnssRilCallback.asInterface(arrayList.readStrongBinder()));
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IAGnssRil".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssRil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */