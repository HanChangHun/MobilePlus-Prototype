package android.hardware.gnss.V2_1;

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

public interface IGnssAntennaInfoCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@2.1::IGnssAntennaInfoCallback";
  
  static IGnssAntennaInfoCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.1::IGnssAntennaInfoCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssAntennaInfoCallback)
      return (IGnssAntennaInfoCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.1::IGnssAntennaInfoCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssAntennaInfoCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssAntennaInfoCallback iGnssAntennaInfoCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssAntennaInfoCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssAntennaInfoCallback;
  }
  
  static IGnssAntennaInfoCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssAntennaInfoCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.1::IGnssAntennaInfoCallback", paramString));
  }
  
  static IGnssAntennaInfoCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.1::IGnssAntennaInfoCallback", paramString, paramBoolean));
  }
  
  static IGnssAntennaInfoCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  void gnssAntennaInfoCb(ArrayList<GnssAntennaInfo> paramArrayList) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Coord {
    public double x = 0.0D;
    
    public double xUncertainty = 0.0D;
    
    public double y = 0.0D;
    
    public double yUncertainty = 0.0D;
    
    public double z = 0.0D;
    
    public double zUncertainty = 0.0D;
    
    public static final ArrayList<Coord> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<Coord> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 48), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        Coord coord = new Coord();
        coord.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 48));
        arrayList.add(coord);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<Coord> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 48);
      for (byte b = 0; b < i; b++)
        ((Coord)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 48)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != Coord.class)
        return false; 
      param1Object = param1Object;
      return (this.x != ((Coord)param1Object).x) ? false : ((this.xUncertainty != ((Coord)param1Object).xUncertainty) ? false : ((this.y != ((Coord)param1Object).y) ? false : ((this.yUncertainty != ((Coord)param1Object).yUncertainty) ? false : ((this.z != ((Coord)param1Object).z) ? false : (!(this.zUncertainty != ((Coord)param1Object).zUncertainty))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.x))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.xUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.y))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.yUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.z))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.zUncertainty))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.x = param1HwBlob.getDouble(0L + param1Long);
      this.xUncertainty = param1HwBlob.getDouble(8L + param1Long);
      this.y = param1HwBlob.getDouble(16L + param1Long);
      this.yUncertainty = param1HwBlob.getDouble(24L + param1Long);
      this.z = param1HwBlob.getDouble(32L + param1Long);
      this.zUncertainty = param1HwBlob.getDouble(40L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(48L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".x = ");
      stringBuilder.append(this.x);
      stringBuilder.append(", .xUncertainty = ");
      stringBuilder.append(this.xUncertainty);
      stringBuilder.append(", .y = ");
      stringBuilder.append(this.y);
      stringBuilder.append(", .yUncertainty = ");
      stringBuilder.append(this.yUncertainty);
      stringBuilder.append(", .z = ");
      stringBuilder.append(this.z);
      stringBuilder.append(", .zUncertainty = ");
      stringBuilder.append(this.zUncertainty);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putDouble(0L + param1Long, this.x);
      param1HwBlob.putDouble(8L + param1Long, this.xUncertainty);
      param1HwBlob.putDouble(16L + param1Long, this.y);
      param1HwBlob.putDouble(24L + param1Long, this.yUncertainty);
      param1HwBlob.putDouble(32L + param1Long, this.z);
      param1HwBlob.putDouble(40L + param1Long, this.zUncertainty);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(48);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssAntennaInfo {
    public double carrierFrequencyMHz = 0.0D;
    
    public IGnssAntennaInfoCallback.Coord phaseCenterOffsetCoordinateMillimeters = new IGnssAntennaInfoCallback.Coord();
    
    public ArrayList<IGnssAntennaInfoCallback.Row> phaseCenterVariationCorrectionMillimeters = new ArrayList<>();
    
    public ArrayList<IGnssAntennaInfoCallback.Row> phaseCenterVariationCorrectionUncertaintyMillimeters = new ArrayList<>();
    
    public ArrayList<IGnssAntennaInfoCallback.Row> signalGainCorrectionDbi = new ArrayList<>();
    
    public ArrayList<IGnssAntennaInfoCallback.Row> signalGainCorrectionUncertaintyDbi = new ArrayList<>();
    
    public static final ArrayList<GnssAntennaInfo> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssAntennaInfo> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 120), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssAntennaInfo gnssAntennaInfo = new GnssAntennaInfo();
        gnssAntennaInfo.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 120));
        arrayList.add(gnssAntennaInfo);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssAntennaInfo> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 120);
      for (byte b = 0; b < i; b++)
        ((GnssAntennaInfo)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 120)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssAntennaInfo.class)
        return false; 
      param1Object = param1Object;
      return (this.carrierFrequencyMHz != ((GnssAntennaInfo)param1Object).carrierFrequencyMHz) ? false : (!HidlSupport.deepEquals(this.phaseCenterOffsetCoordinateMillimeters, ((GnssAntennaInfo)param1Object).phaseCenterOffsetCoordinateMillimeters) ? false : (!HidlSupport.deepEquals(this.phaseCenterVariationCorrectionMillimeters, ((GnssAntennaInfo)param1Object).phaseCenterVariationCorrectionMillimeters) ? false : (!HidlSupport.deepEquals(this.phaseCenterVariationCorrectionUncertaintyMillimeters, ((GnssAntennaInfo)param1Object).phaseCenterVariationCorrectionUncertaintyMillimeters) ? false : (!HidlSupport.deepEquals(this.signalGainCorrectionDbi, ((GnssAntennaInfo)param1Object).signalGainCorrectionDbi) ? false : (!!HidlSupport.deepEquals(this.signalGainCorrectionUncertaintyDbi, ((GnssAntennaInfo)param1Object).signalGainCorrectionUncertaintyDbi))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierFrequencyMHz))), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterOffsetCoordinateMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterVariationCorrectionMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterVariationCorrectionUncertaintyMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.signalGainCorrectionDbi)), Integer.valueOf(HidlSupport.deepHashCode(this.signalGainCorrectionUncertaintyDbi)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.carrierFrequencyMHz = param1HwBlob.getDouble(param1Long + 0L);
      this.phaseCenterOffsetCoordinateMillimeters.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, param1Long + 8L);
      int i = param1HwBlob.getInt32(param1Long + 56L + 8L);
      HwBlob hwBlob1 = param1HwParcel.readEmbeddedBuffer((i * 16), param1HwBlob.handle(), param1Long + 56L + 0L, true);
      this.phaseCenterVariationCorrectionMillimeters.clear();
      byte b;
      for (b = 0; b < i; b++) {
        IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
        row.readEmbeddedFromParcel(param1HwParcel, hwBlob1, (b * 16));
        this.phaseCenterVariationCorrectionMillimeters.add(row);
      } 
      i = param1HwBlob.getInt32(param1Long + 72L + 8L);
      hwBlob1 = param1HwParcel.readEmbeddedBuffer((i * 16), param1HwBlob.handle(), param1Long + 72L + 0L, true);
      this.phaseCenterVariationCorrectionUncertaintyMillimeters.clear();
      for (b = 0; b < i; b++) {
        IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
        row.readEmbeddedFromParcel(param1HwParcel, hwBlob1, (b * 16));
        this.phaseCenterVariationCorrectionUncertaintyMillimeters.add(row);
      } 
      i = param1HwBlob.getInt32(param1Long + 88L + 8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 16), param1HwBlob.handle(), param1Long + 88L + 0L, true);
      this.signalGainCorrectionDbi.clear();
      for (b = 0; b < i; b++) {
        IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
        row.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 16));
        this.signalGainCorrectionDbi.add(row);
      } 
      i = param1HwBlob.getInt32(param1Long + 104L + 8L);
      hwBlob1 = param1HwParcel.readEmbeddedBuffer((i * 16), param1HwBlob.handle(), param1Long + 104L + 0L, true);
      this.signalGainCorrectionUncertaintyDbi.clear();
      for (b = 0; b < i; b++) {
        IGnssAntennaInfoCallback.Row row = new IGnssAntennaInfoCallback.Row();
        row.readEmbeddedFromParcel(param1HwParcel, hwBlob1, (b * 16));
        this.signalGainCorrectionUncertaintyDbi.add(row);
      } 
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(120L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".carrierFrequencyMHz = ");
      stringBuilder.append(this.carrierFrequencyMHz);
      stringBuilder.append(", .phaseCenterOffsetCoordinateMillimeters = ");
      stringBuilder.append(this.phaseCenterOffsetCoordinateMillimeters);
      stringBuilder.append(", .phaseCenterVariationCorrectionMillimeters = ");
      stringBuilder.append(this.phaseCenterVariationCorrectionMillimeters);
      stringBuilder.append(", .phaseCenterVariationCorrectionUncertaintyMillimeters = ");
      stringBuilder.append(this.phaseCenterVariationCorrectionUncertaintyMillimeters);
      stringBuilder.append(", .signalGainCorrectionDbi = ");
      stringBuilder.append(this.signalGainCorrectionDbi);
      stringBuilder.append(", .signalGainCorrectionUncertaintyDbi = ");
      stringBuilder.append(this.signalGainCorrectionUncertaintyDbi);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putDouble(param1Long + 0L, this.carrierFrequencyMHz);
      this.phaseCenterOffsetCoordinateMillimeters.writeEmbeddedToBlob(param1HwBlob, param1Long + 8L);
      int i = this.phaseCenterVariationCorrectionMillimeters.size();
      param1HwBlob.putInt32(param1Long + 56L + 8L, i);
      param1HwBlob.putBool(param1Long + 56L + 12L, false);
      HwBlob hwBlob = new HwBlob(i * 16);
      byte b;
      for (b = 0; b < i; b++)
        ((IGnssAntennaInfoCallback.Row)this.phaseCenterVariationCorrectionMillimeters.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
      param1HwBlob.putBlob(param1Long + 56L + 0L, hwBlob);
      i = this.phaseCenterVariationCorrectionUncertaintyMillimeters.size();
      param1HwBlob.putInt32(param1Long + 72L + 8L, i);
      param1HwBlob.putBool(param1Long + 72L + 12L, false);
      hwBlob = new HwBlob(i * 16);
      for (b = 0; b < i; b++)
        ((IGnssAntennaInfoCallback.Row)this.phaseCenterVariationCorrectionUncertaintyMillimeters.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
      param1HwBlob.putBlob(param1Long + 72L + 0L, hwBlob);
      i = this.signalGainCorrectionDbi.size();
      param1HwBlob.putInt32(param1Long + 88L + 8L, i);
      param1HwBlob.putBool(param1Long + 88L + 12L, false);
      hwBlob = new HwBlob(i * 16);
      for (b = 0; b < i; b++)
        ((IGnssAntennaInfoCallback.Row)this.signalGainCorrectionDbi.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
      param1HwBlob.putBlob(param1Long + 88L + 0L, hwBlob);
      i = this.signalGainCorrectionUncertaintyDbi.size();
      param1HwBlob.putInt32(param1Long + 104L + 8L, i);
      param1HwBlob.putBool(param1Long + 104L + 12L, false);
      hwBlob = new HwBlob(i * 16);
      for (b = 0; b < i; b++)
        ((IGnssAntennaInfoCallback.Row)this.signalGainCorrectionUncertaintyDbi.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
      param1HwBlob.putBlob(param1Long + 104L + 0L, hwBlob);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(120);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class Proxy implements IGnssAntennaInfoCallback {
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
    
    public void gnssAntennaInfoCb(ArrayList<IGnssAntennaInfoCallback.GnssAntennaInfo> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.1::IGnssAntennaInfoCallback");
      IGnssAntennaInfoCallback.GnssAntennaInfo.writeVectorToParcel(null, param1ArrayList);
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
    
    public String toString() {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(interfaceDescriptor());
        stringBuilder.append("@Proxy");
        return stringBuilder.toString();
      } catch (RemoteException remoteException) {
        return "[class or subclass of android.hardware.gnss@2.1::IGnssAntennaInfoCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static final class Row {
    public ArrayList<Double> row = new ArrayList<>();
    
    public static final ArrayList<Row> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<Row> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 16), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        Row row = new Row();
        row.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 16));
        arrayList.add(row);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<Row> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 16);
      for (byte b = 0; b < i; b++)
        ((Row)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != Row.class)
        return false; 
      param1Object = param1Object;
      return !!HidlSupport.deepEquals(this.row, ((Row)param1Object).row);
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.row)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      int i = param1HwBlob.getInt32(param1Long + 0L + 8L);
      HwBlob hwBlob = param1HwParcel.readEmbeddedBuffer((i * 8), param1HwBlob.handle(), param1Long + 0L + 0L, true);
      this.row.clear();
      for (byte b = 0; b < i; b++) {
        double d = hwBlob.getDouble((b * 8));
        this.row.add(Double.valueOf(d));
      } 
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(16L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".row = ");
      stringBuilder.append(this.row);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      int i = this.row.size();
      param1HwBlob.putInt32(param1Long + 0L + 8L, i);
      param1HwBlob.putBool(param1Long + 0L + 12L, false);
      HwBlob hwBlob = new HwBlob(i * 8);
      for (byte b = 0; b < i; b++)
        hwBlob.putDouble((b * 8), ((Double)this.row.get(b)).doubleValue()); 
      param1HwBlob.putBlob(param1Long + 0L + 0L, hwBlob);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(16);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssAntennaInfoCallback {
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
                11, -61, -19, -105, -53, -61, -10, -85, -56, -100, 
                104, -12, -23, -12, -47, 36, -7, -9, 35, 67, 
                25, -105, -36, -120, -62, 24, 108, -12, -46, -83, 
                71, -18 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.1::IGnssAntennaInfoCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@2.1::IGnssAntennaInfoCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      ArrayList<byte[]> arrayList1;
      String str;
      ArrayList<String> arrayList;
      HwBlob hwBlob1;
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
          arrayList1.enforceInterface("android.hidl.base@1.0::IBase");
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
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@2.1::IGnssAntennaInfoCallback");
      gnssAntennaInfoCb(IGnssAntennaInfoCallback.GnssAntennaInfo.readVectorFromParcel((HwParcel)arrayList));
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.1::IGnssAntennaInfoCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssAntennaInfoCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */