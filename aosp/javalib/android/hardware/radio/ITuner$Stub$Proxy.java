package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

class Proxy implements ITuner {
  public static ITuner sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancel() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().cancel();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelAnnouncement() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().cancelAnnouncement();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void close() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().close();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public RadioManager.BandConfig getConfiguration() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      RadioManager.BandConfig bandConfig;
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bandConfig = ITuner.Stub.getDefaultImpl().getConfiguration();
        return bandConfig;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bandConfig = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(parcel2);
      } else {
        bandConfig = null;
      } 
      return bandConfig;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bitmap getImage(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Bitmap bitmap;
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bitmap = ITuner.Stub.getDefaultImpl().getImage(paramInt);
        return bitmap;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
      } else {
        bitmap = null;
      } 
      return bitmap;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.radio.ITuner";
  }
  
  public Map getParameters(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null)
        return ITuner.Stub.getDefaultImpl().getParameters(paramList); 
      parcel2.readException();
      return parcel2.readHashMap(getClass().getClassLoader());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isClosed() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bool = ITuner.Stub.getDefaultImpl().isClosed();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isConfigFlagSet(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bool = ITuner.Stub.getDefaultImpl().isConfigFlagSet(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isConfigFlagSupported(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(16, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bool = ITuner.Stub.getDefaultImpl().isConfigFlagSupported(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isMuted() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bool = ITuner.Stub.getDefaultImpl().isMuted();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void scan(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      boolean bool1 = true;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().scan(paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setConfigFlag(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().setConfigFlag(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setConfiguration(RadioManager.BandConfig paramBandConfig) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (paramBandConfig != null) {
        parcel1.writeInt(1);
        paramBandConfig.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().setConfiguration(paramBandConfig);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMuted(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().setMuted(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Map setParameters(Map paramMap) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      parcel1.writeMap(paramMap);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        paramMap = ITuner.Stub.getDefaultImpl().setParameters(paramMap);
        return paramMap;
      } 
      parcel2.readException();
      paramMap = parcel2.readHashMap(getClass().getClassLoader());
      return paramMap;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean startBackgroundScan() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(13, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        bool = ITuner.Stub.getDefaultImpl().startBackgroundScan();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startProgramListUpdates(ProgramList.Filter paramFilter) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (paramFilter != null) {
        parcel1.writeInt(1);
        paramFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().startProgramListUpdates(paramFilter);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void step(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      boolean bool1 = true;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().step(paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopProgramListUpdates() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().stopProgramListUpdates();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void tune(ProgramSelector paramProgramSelector) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
      if (paramProgramSelector != null) {
        parcel1.writeInt(1);
        paramProgramSelector.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
        ITuner.Stub.getDefaultImpl().tune(paramProgramSelector);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ITuner$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */