package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

@SystemApi
public final class HdmiControlManager {
  public static final String ACTION_OSD_MESSAGE = "android.hardware.hdmi.action.OSD_MESSAGE";
  
  public static final int AVR_VOLUME_MUTED = 101;
  
  public static final int CLEAR_TIMER_STATUS_CEC_DISABLE = 162;
  
  public static final int CLEAR_TIMER_STATUS_CHECK_RECORDER_CONNECTION = 160;
  
  public static final int CLEAR_TIMER_STATUS_FAIL_TO_CLEAR_SELECTED_SOURCE = 161;
  
  public static final int CLEAR_TIMER_STATUS_TIMER_CLEARED = 128;
  
  public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_NO_INFO_AVAILABLE = 2;
  
  public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_NO_MATCHING = 1;
  
  public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_RECORDING = 0;
  
  public static final int CONTROL_STATE_CHANGED_REASON_SETTING = 1;
  
  public static final int CONTROL_STATE_CHANGED_REASON_STANDBY = 3;
  
  public static final int CONTROL_STATE_CHANGED_REASON_START = 0;
  
  public static final int CONTROL_STATE_CHANGED_REASON_WAKEUP = 2;
  
  public static final int DEVICE_EVENT_ADD_DEVICE = 1;
  
  public static final int DEVICE_EVENT_REMOVE_DEVICE = 2;
  
  public static final int DEVICE_EVENT_UPDATE_DEVICE = 3;
  
  public static final String EXTRA_MESSAGE_EXTRA_PARAM1 = "android.hardware.hdmi.extra.MESSAGE_EXTRA_PARAM1";
  
  public static final String EXTRA_MESSAGE_ID = "android.hardware.hdmi.extra.MESSAGE_ID";
  
  private static final int INVALID_PHYSICAL_ADDRESS = 65535;
  
  public static final int ONE_TOUCH_RECORD_ALREADY_RECORDING = 18;
  
  public static final int ONE_TOUCH_RECORD_CEC_DISABLED = 51;
  
  public static final int ONE_TOUCH_RECORD_CHECK_RECORDER_CONNECTION = 49;
  
  public static final int ONE_TOUCH_RECORD_DISALLOW_TO_COPY = 13;
  
  public static final int ONE_TOUCH_RECORD_DISALLOW_TO_FUTHER_COPIES = 14;
  
  public static final int ONE_TOUCH_RECORD_FAIL_TO_RECORD_DISPLAYED_SCREEN = 50;
  
  public static final int ONE_TOUCH_RECORD_INVALID_EXTERNAL_PHYSICAL_ADDRESS = 10;
  
  public static final int ONE_TOUCH_RECORD_INVALID_EXTERNAL_PLUG_NUMBER = 9;
  
  public static final int ONE_TOUCH_RECORD_MEDIA_PROBLEM = 21;
  
  public static final int ONE_TOUCH_RECORD_MEDIA_PROTECTED = 19;
  
  public static final int ONE_TOUCH_RECORD_NOT_ENOUGH_SPACE = 22;
  
  public static final int ONE_TOUCH_RECORD_NO_MEDIA = 16;
  
  public static final int ONE_TOUCH_RECORD_NO_OR_INSUFFICIENT_CA_ENTITLEMENTS = 12;
  
  public static final int ONE_TOUCH_RECORD_NO_SOURCE_SIGNAL = 20;
  
  public static final int ONE_TOUCH_RECORD_OTHER_REASON = 31;
  
  public static final int ONE_TOUCH_RECORD_PARENT_LOCK_ON = 23;
  
  public static final int ONE_TOUCH_RECORD_PLAYING = 17;
  
  public static final int ONE_TOUCH_RECORD_PREVIOUS_RECORDING_IN_PROGRESS = 48;
  
  public static final int ONE_TOUCH_RECORD_RECORDING_ALREADY_TERMINATED = 27;
  
  public static final int ONE_TOUCH_RECORD_RECORDING_ANALOGUE_SERVICE = 3;
  
  public static final int ONE_TOUCH_RECORD_RECORDING_CURRENTLY_SELECTED_SOURCE = 1;
  
  public static final int ONE_TOUCH_RECORD_RECORDING_DIGITAL_SERVICE = 2;
  
  public static final int ONE_TOUCH_RECORD_RECORDING_EXTERNAL_INPUT = 4;
  
  public static final int ONE_TOUCH_RECORD_RECORDING_TERMINATED_NORMALLY = 26;
  
  public static final int ONE_TOUCH_RECORD_UNABLE_ANALOGUE_SERVICE = 6;
  
  public static final int ONE_TOUCH_RECORD_UNABLE_DIGITAL_SERVICE = 5;
  
  public static final int ONE_TOUCH_RECORD_UNABLE_SELECTED_SERVICE = 7;
  
  public static final int ONE_TOUCH_RECORD_UNSUPPORTED_CA = 11;
  
  public static final int OSD_MESSAGE_ARC_CONNECTED_INVALID_PORT = 1;
  
  public static final int OSD_MESSAGE_AVR_VOLUME_CHANGED = 2;
  
  public static final int POWER_STATUS_ON = 0;
  
  public static final int POWER_STATUS_STANDBY = 1;
  
  public static final int POWER_STATUS_TRANSIENT_TO_ON = 2;
  
  public static final int POWER_STATUS_TRANSIENT_TO_STANDBY = 3;
  
  public static final int POWER_STATUS_UNKNOWN = -1;
  
  @Deprecated
  public static final int RESULT_ALREADY_IN_PROGRESS = 4;
  
  public static final int RESULT_COMMUNICATION_FAILED = 7;
  
  public static final int RESULT_EXCEPTION = 5;
  
  public static final int RESULT_INCORRECT_MODE = 6;
  
  public static final int RESULT_SOURCE_NOT_AVAILABLE = 2;
  
  public static final int RESULT_SUCCESS = 0;
  
  public static final int RESULT_TARGET_NOT_AVAILABLE = 3;
  
  public static final int RESULT_TIMEOUT = 1;
  
  private static final String TAG = "HdmiControlManager";
  
  public static final int TIMER_RECORDING_RESULT_EXTRA_CEC_DISABLED = 3;
  
  public static final int TIMER_RECORDING_RESULT_EXTRA_CHECK_RECORDER_CONNECTION = 1;
  
  public static final int TIMER_RECORDING_RESULT_EXTRA_FAIL_TO_RECORD_SELECTED_SOURCE = 2;
  
  public static final int TIMER_RECORDING_RESULT_EXTRA_NO_ERROR = 0;
  
  public static final int TIMER_RECORDING_TYPE_ANALOGUE = 2;
  
  public static final int TIMER_RECORDING_TYPE_DIGITAL = 1;
  
  public static final int TIMER_RECORDING_TYPE_EXTERNAL = 3;
  
  public static final int TIMER_STATUS_MEDIA_INFO_NOT_PRESENT = 2;
  
  public static final int TIMER_STATUS_MEDIA_INFO_PRESENT_NOT_PROTECTED = 0;
  
  public static final int TIMER_STATUS_MEDIA_INFO_PRESENT_PROTECTED = 1;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_CA_NOT_SUPPORTED = 6;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_CLOCK_FAILURE = 10;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_DATE_OUT_OF_RANGE = 2;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_DUPLICATED = 14;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_EXTERNAL_PHYSICAL_NUMBER = 5;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_EXTERNAL_PLUG_NUMBER = 4;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_SEQUENCE = 3;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_NO_CA_ENTITLEMENTS = 7;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_NO_FREE_TIME = 1;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_PARENTAL_LOCK_ON = 9;
  
  public static final int TIMER_STATUS_NOT_PROGRAMMED_UNSUPPORTED_RESOLUTION = 8;
  
  public static final int TIMER_STATUS_PROGRAMMED_INFO_ENOUGH_SPACE = 8;
  
  public static final int TIMER_STATUS_PROGRAMMED_INFO_MIGHT_NOT_ENOUGH_SPACE = 11;
  
  public static final int TIMER_STATUS_PROGRAMMED_INFO_NOT_ENOUGH_SPACE = 9;
  
  public static final int TIMER_STATUS_PROGRAMMED_INFO_NO_MEDIA_INFO = 10;
  
  private final boolean mHasAudioSystemDevice;
  
  private final boolean mHasPlaybackDevice;
  
  private final boolean mHasSwitchDevice;
  
  private final boolean mHasTvDevice;
  
  private final ArrayMap<HdmiCecVolumeControlFeatureListener, IHdmiCecVolumeControlFeatureListener> mHdmiCecVolumeControlFeatureListeners = new ArrayMap();
  
  private final ArrayMap<HdmiControlStatusChangeListener, IHdmiControlStatusChangeListener> mHdmiControlStatusChangeListeners = new ArrayMap();
  
  private final ArrayMap<HotplugEventListener, IHdmiHotplugEventListener> mHotplugEventListeners = new ArrayMap();
  
  private final boolean mIsSwitchDevice;
  
  private int mLocalPhysicalAddress = 65535;
  
  private final Object mLock = new Object();
  
  private final IHdmiControlService mService;
  
  public HdmiControlManager(IHdmiControlService paramIHdmiControlService) {
    this.mService = paramIHdmiControlService;
    int[] arrayOfInt = null;
    if (paramIHdmiControlService != null)
      try {
        arrayOfInt = paramIHdmiControlService.getSupportedTypes();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    this.mHasTvDevice = hasDeviceType(arrayOfInt, 0);
    this.mHasPlaybackDevice = hasDeviceType(arrayOfInt, 4);
    this.mHasAudioSystemDevice = hasDeviceType(arrayOfInt, 5);
    this.mHasSwitchDevice = hasDeviceType(arrayOfInt, 6);
    this.mIsSwitchDevice = SystemProperties.getBoolean("ro.hdmi.property_is_device_hdmi_cec_switch", false);
    addHotplugEventListener(new ClientHotplugEventListener());
  }
  
  private IHdmiCecVolumeControlFeatureListener createHdmiCecVolumeControlFeatureListenerWrapper(final Executor executor, final HdmiCecVolumeControlFeatureListener listener) {
    return new IHdmiCecVolumeControlFeatureListener.Stub() {
        public void onHdmiCecVolumeControlFeature(boolean param1Boolean) {
          Binder.clearCallingIdentity();
          executor.execute(new _$$Lambda$HdmiControlManager$3$nhJsWlQLW7H_zQCJ36JgrRbU5zI(listener, param1Boolean));
        }
      };
  }
  
  private IHdmiControlStatusChangeListener getHdmiControlStatusChangeListenerWrapper(final HdmiControlStatusChangeListener listener) {
    return new IHdmiControlStatusChangeListener.Stub() {
        public void onStatusChange(boolean param1Boolean1, boolean param1Boolean2) {
          listener.onStatusChange(param1Boolean1, param1Boolean2);
        }
      };
  }
  
  private IHdmiHotplugEventListener getHotplugEventListenerWrapper(final HotplugEventListener listener) {
    return new IHdmiHotplugEventListener.Stub() {
        public void onReceived(HdmiHotplugEvent param1HdmiHotplugEvent) {
          listener.onReceived(param1HdmiHotplugEvent);
        }
      };
  }
  
  private int getLocalPhysicalAddress() {
    synchronized (this.mLock) {
      return this.mLocalPhysicalAddress;
    } 
  }
  
  private static boolean hasDeviceType(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return false; 
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfint[b] == paramInt)
        return true; 
    } 
    return false;
  }
  
  private void setLocalPhysicalAddress(int paramInt) {
    synchronized (this.mLock) {
      this.mLocalPhysicalAddress = paramInt;
      return;
    } 
  }
  
  public void addHdmiCecVolumeControlFeatureListener(Executor paramExecutor, HdmiCecVolumeControlFeatureListener paramHdmiCecVolumeControlFeatureListener) {
    if (this.mService == null) {
      Log.e("HdmiControlManager", "HdmiControlService is not available");
      return;
    } 
    if (this.mHdmiCecVolumeControlFeatureListeners.containsKey(paramHdmiCecVolumeControlFeatureListener)) {
      Log.e("HdmiControlManager", "listener is already registered");
      return;
    } 
    IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener = createHdmiCecVolumeControlFeatureListenerWrapper(paramExecutor, paramHdmiCecVolumeControlFeatureListener);
    this.mHdmiCecVolumeControlFeatureListeners.put(paramHdmiCecVolumeControlFeatureListener, iHdmiCecVolumeControlFeatureListener);
    try {
      this.mService.addHdmiCecVolumeControlFeatureListener(iHdmiCecVolumeControlFeatureListener);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void addHdmiControlStatusChangeListener(HdmiControlStatusChangeListener paramHdmiControlStatusChangeListener) {
    if (this.mService == null) {
      Log.e("HdmiControlManager", "HdmiControlService is not available");
      return;
    } 
    if (this.mHdmiControlStatusChangeListeners.containsKey(paramHdmiControlStatusChangeListener)) {
      Log.e("HdmiControlManager", "listener is already registered");
      return;
    } 
    IHdmiControlStatusChangeListener iHdmiControlStatusChangeListener = getHdmiControlStatusChangeListenerWrapper(paramHdmiControlStatusChangeListener);
    this.mHdmiControlStatusChangeListeners.put(paramHdmiControlStatusChangeListener, iHdmiControlStatusChangeListener);
    try {
      this.mService.addHdmiControlStatusChangeListener(iHdmiControlStatusChangeListener);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void addHotplugEventListener(HotplugEventListener paramHotplugEventListener) {
    if (this.mService == null) {
      Log.e("HdmiControlManager", "HdmiControlService is not available");
      return;
    } 
    if (this.mHotplugEventListeners.containsKey(paramHotplugEventListener)) {
      Log.e("HdmiControlManager", "listener is already registered");
      return;
    } 
    IHdmiHotplugEventListener iHdmiHotplugEventListener = getHotplugEventListenerWrapper(paramHotplugEventListener);
    this.mHotplugEventListeners.put(paramHotplugEventListener, iHdmiHotplugEventListener);
    try {
      this.mService.addHotplugEventListener(iHdmiHotplugEventListener);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public HdmiAudioSystemClient getAudioSystemClient() {
    return (HdmiAudioSystemClient)getClient(5);
  }
  
  @SystemApi
  public HdmiClient getClient(int paramInt) {
    HdmiTvClient hdmiTvClient;
    IHdmiControlService iHdmiControlService = this.mService;
    HdmiSwitchClient hdmiSwitchClient1 = null;
    HdmiSwitchClient hdmiSwitchClient2 = null;
    HdmiSwitchClient hdmiSwitchClient3 = null;
    HdmiSwitchClient hdmiSwitchClient4 = null;
    if (iHdmiControlService == null)
      return null; 
    if (paramInt != 0) {
      HdmiPlaybackClient hdmiPlaybackClient;
      if (paramInt != 4) {
        HdmiAudioSystemClient hdmiAudioSystemClient;
        if (paramInt != 5) {
          if (paramInt != 6)
            return null; 
          if (this.mHasSwitchDevice || this.mIsSwitchDevice)
            hdmiSwitchClient4 = new HdmiSwitchClient(this.mService); 
          return hdmiSwitchClient4;
        } 
        hdmiSwitchClient4 = hdmiSwitchClient1;
        if (this.mHasAudioSystemDevice)
          hdmiAudioSystemClient = new HdmiAudioSystemClient(iHdmiControlService); 
        return hdmiAudioSystemClient;
      } 
      hdmiSwitchClient4 = hdmiSwitchClient2;
      if (this.mHasPlaybackDevice)
        hdmiPlaybackClient = new HdmiPlaybackClient(iHdmiControlService); 
      return hdmiPlaybackClient;
    } 
    hdmiSwitchClient4 = hdmiSwitchClient3;
    if (this.mHasTvDevice)
      hdmiTvClient = new HdmiTvClient(iHdmiControlService); 
    return hdmiTvClient;
  }
  
  @SystemApi
  public List<HdmiDeviceInfo> getConnectedDevices() {
    try {
      return this.mService.getDeviceList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public List<HdmiDeviceInfo> getConnectedDevicesList() {
    try {
      return this.mService.getDeviceList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public int getPhysicalAddress() {
    return getLocalPhysicalAddress();
  }
  
  @SystemApi
  public HdmiPlaybackClient getPlaybackClient() {
    return (HdmiPlaybackClient)getClient(4);
  }
  
  public HdmiSwitchClient getSwitchClient() {
    return (HdmiSwitchClient)getClient(6);
  }
  
  public boolean getSystemAudioMode() {
    try {
      return this.mService.getSystemAudioMode();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public HdmiTvClient getTvClient() {
    return (HdmiTvClient)getClient(0);
  }
  
  @SystemApi
  public boolean isDeviceConnected(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    int i = getLocalPhysicalAddress();
    boolean bool = false;
    if (i == 65535)
      return false; 
    int j = paramHdmiDeviceInfo.getPhysicalAddress();
    if (j == 65535)
      return false; 
    if (HdmiUtils.getLocalPortFromPhysicalAddress(j, i) != -1)
      bool = true; 
    return bool;
  }
  
  public boolean isHdmiCecVolumeControlEnabled() {
    try {
      return this.mService.isHdmiCecVolumeControlEnabled();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public boolean isRemoteDeviceConnected(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    int i = getLocalPhysicalAddress();
    boolean bool = false;
    if (i == 65535)
      return false; 
    int j = paramHdmiDeviceInfo.getPhysicalAddress();
    if (j == 65535)
      return false; 
    if (HdmiUtils.getLocalPortFromPhysicalAddress(j, i) != -1)
      bool = true; 
    return bool;
  }
  
  @SystemApi
  public void powerOffDevice(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    try {
      this.mService.powerOffRemoteDevice(paramHdmiDeviceInfo.getLogicalAddress(), paramHdmiDeviceInfo.getDevicePowerStatus());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public void powerOffRemoteDevice(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    try {
      this.mService.powerOffRemoteDevice(paramHdmiDeviceInfo.getLogicalAddress(), paramHdmiDeviceInfo.getDevicePowerStatus());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void powerOnDevice(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    try {
      this.mService.powerOnRemoteDevice(paramHdmiDeviceInfo.getLogicalAddress(), paramHdmiDeviceInfo.getDevicePowerStatus());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public void powerOnRemoteDevice(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    try {
      this.mService.powerOnRemoteDevice(paramHdmiDeviceInfo.getLogicalAddress(), paramHdmiDeviceInfo.getDevicePowerStatus());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeHdmiCecVolumeControlFeatureListener(HdmiCecVolumeControlFeatureListener paramHdmiCecVolumeControlFeatureListener) {
    if (this.mService == null) {
      Log.e("HdmiControlManager", "HdmiControlService is not available");
      return;
    } 
    IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener = (IHdmiCecVolumeControlFeatureListener)this.mHdmiCecVolumeControlFeatureListeners.remove(paramHdmiCecVolumeControlFeatureListener);
    if (iHdmiCecVolumeControlFeatureListener == null) {
      Log.e("HdmiControlManager", "tried to remove not-registered listener");
      return;
    } 
    try {
      this.mService.removeHdmiCecVolumeControlFeatureListener(iHdmiCecVolumeControlFeatureListener);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeHdmiControlStatusChangeListener(HdmiControlStatusChangeListener paramHdmiControlStatusChangeListener) {
    if (this.mService == null) {
      Log.e("HdmiControlManager", "HdmiControlService is not available");
      return;
    } 
    IHdmiControlStatusChangeListener iHdmiControlStatusChangeListener = (IHdmiControlStatusChangeListener)this.mHdmiControlStatusChangeListeners.remove(paramHdmiControlStatusChangeListener);
    if (iHdmiControlStatusChangeListener == null) {
      Log.e("HdmiControlManager", "tried to remove not-registered listener");
      return;
    } 
    try {
      this.mService.removeHdmiControlStatusChangeListener(iHdmiControlStatusChangeListener);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void removeHotplugEventListener(HotplugEventListener paramHotplugEventListener) {
    if (this.mService == null) {
      Log.e("HdmiControlManager", "HdmiControlService is not available");
      return;
    } 
    IHdmiHotplugEventListener iHdmiHotplugEventListener = (IHdmiHotplugEventListener)this.mHotplugEventListeners.remove(paramHotplugEventListener);
    if (iHdmiHotplugEventListener == null) {
      Log.e("HdmiControlManager", "tried to remove not-registered listener");
      return;
    } 
    try {
      this.mService.removeHotplugEventListener(iHdmiHotplugEventListener);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  @Deprecated
  public void requestRemoteDeviceToBecomeActiveSource(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    try {
      this.mService.askRemoteDeviceToBecomeActiveSource(paramHdmiDeviceInfo.getPhysicalAddress());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setActiveSource(HdmiDeviceInfo paramHdmiDeviceInfo) {
    Objects.requireNonNull(paramHdmiDeviceInfo);
    try {
      this.mService.askRemoteDeviceToBecomeActiveSource(paramHdmiDeviceInfo.getPhysicalAddress());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setHdmiCecVolumeControlEnabled(boolean paramBoolean) {
    try {
      this.mService.setHdmiCecVolumeControlEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setStandbyMode(boolean paramBoolean) {
    try {
      this.mService.setStandbyMode(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private final class ClientHotplugEventListener implements HotplugEventListener {
    private ClientHotplugEventListener() {}
    
    public void onReceived(HdmiHotplugEvent param1HdmiHotplugEvent) {
      new ArrayList();
      try {
        StringBuilder stringBuilder;
        List<HdmiPortInfo> list = HdmiControlManager.this.mService.getPortInfo();
        if (list.isEmpty()) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Can't find port info, not updating connected status. Hotplug event:");
          stringBuilder.append(param1HdmiHotplugEvent);
          Log.e("HdmiControlManager", stringBuilder.toString());
          return;
        } 
        Iterator<HdmiPortInfo> iterator = stringBuilder.iterator();
        while (iterator.hasNext()) {
          HdmiPortInfo hdmiPortInfo = iterator.next();
          if (hdmiPortInfo.getId() == param1HdmiHotplugEvent.getPort()) {
            if (hdmiPortInfo.getType() == 1) {
              char c;
              HdmiControlManager hdmiControlManager = HdmiControlManager.this;
              if (param1HdmiHotplugEvent.isConnected()) {
                c = hdmiPortInfo.getAddress();
              } else {
                c = '￿';
              } 
              hdmiControlManager.setLocalPhysicalAddress(c);
            } 
            break;
          } 
        } 
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
  }
  
  @SystemApi
  public static @interface ControlCallbackResult {}
  
  public static interface HdmiCecVolumeControlFeatureListener {
    void onHdmiCecVolumeControlFeature(boolean param1Boolean);
  }
  
  public static interface HdmiControlStatusChangeListener {
    void onStatusChange(boolean param1Boolean1, boolean param1Boolean2);
  }
  
  @SystemApi
  public static interface HotplugEventListener {
    void onReceived(HdmiHotplugEvent param1HdmiHotplugEvent);
  }
  
  @SystemApi
  public static interface VendorCommandListener {
    void onControlStateChanged(boolean param1Boolean, int param1Int);
    
    void onReceived(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte, boolean param1Boolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiControlManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */