package android.bluetooth;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothHeadsetClient implements BluetoothProfile {
  public static final String ACTION_AG_EVENT = "android.bluetooth.headsetclient.profile.action.AG_EVENT";
  
  public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED";
  
  public static final String ACTION_CALL_CHANGED = "android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED";
  
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_LAST_VTAG = "android.bluetooth.headsetclient.profile.action.LAST_VTAG";
  
  public static final String ACTION_RESULT = "android.bluetooth.headsetclient.profile.action.RESULT";
  
  public static final int ACTION_RESULT_ERROR = 1;
  
  public static final int ACTION_RESULT_ERROR_BLACKLISTED = 6;
  
  public static final int ACTION_RESULT_ERROR_BUSY = 3;
  
  public static final int ACTION_RESULT_ERROR_CME = 7;
  
  public static final int ACTION_RESULT_ERROR_DELAYED = 5;
  
  public static final int ACTION_RESULT_ERROR_NO_ANSWER = 4;
  
  public static final int ACTION_RESULT_ERROR_NO_CARRIER = 2;
  
  public static final int ACTION_RESULT_OK = 0;
  
  public static final String ACTION_VENDOR_SPECIFIC_HEADSETCLIENT_EVENT = "android.bluetooth.headsetclient.profile.action.VENDOR_SPECIFIC_EVENT";
  
  public static final int CALL_ACCEPT_HOLD = 1;
  
  public static final int CALL_ACCEPT_NONE = 0;
  
  public static final int CALL_ACCEPT_TERMINATE = 2;
  
  public static final int CME_CORPORATE_PERSONALIZATION_PIN_REQUIRED = 46;
  
  public static final int CME_CORPORATE_PERSONALIZATION_PUK_REQUIRED = 47;
  
  public static final int CME_DIAL_STRING_TOO_LONG = 26;
  
  public static final int CME_EAP_NOT_SUPPORTED = 49;
  
  public static final int CME_EMERGENCY_SERVICE_ONLY = 32;
  
  public static final int CME_HIDDEN_KEY_REQUIRED = 48;
  
  public static final int CME_INCORRECT_PARAMETERS = 50;
  
  public static final int CME_INCORRECT_PASSWORD = 16;
  
  public static final int CME_INVALID_CHARACTER_IN_DIAL_STRING = 27;
  
  public static final int CME_INVALID_CHARACTER_IN_TEXT_STRING = 25;
  
  public static final int CME_INVALID_INDEX = 21;
  
  public static final int CME_MEMORY_FAILURE = 23;
  
  public static final int CME_MEMORY_FULL = 20;
  
  public static final int CME_NETWORK_PERSONALIZATION_PIN_REQUIRED = 40;
  
  public static final int CME_NETWORK_PERSONALIZATION_PUK_REQUIRED = 41;
  
  public static final int CME_NETWORK_SUBSET_PERSONALIZATION_PIN_REQUIRED = 42;
  
  public static final int CME_NETWORK_SUBSET_PERSONALIZATION_PUK_REQUIRED = 43;
  
  public static final int CME_NETWORK_TIMEOUT = 31;
  
  public static final int CME_NOT_FOUND = 22;
  
  public static final int CME_NOT_SUPPORTED_FOR_VOIP = 34;
  
  public static final int CME_NO_CONNECTION_TO_PHONE = 1;
  
  public static final int CME_NO_NETWORK_SERVICE = 30;
  
  public static final int CME_NO_SIMULTANOUS_VOIP_CS_CALLS = 33;
  
  public static final int CME_OPERATION_NOT_ALLOWED = 3;
  
  public static final int CME_OPERATION_NOT_SUPPORTED = 4;
  
  public static final int CME_PHFSIM_PIN_REQUIRED = 6;
  
  public static final int CME_PHFSIM_PUK_REQUIRED = 7;
  
  public static final int CME_PHONE_FAILURE = 0;
  
  public static final int CME_PHSIM_PIN_REQUIRED = 5;
  
  public static final int CME_SERVICE_PROVIDER_PERSONALIZATION_PIN_REQUIRED = 44;
  
  public static final int CME_SERVICE_PROVIDER_PERSONALIZATION_PUK_REQUIRED = 45;
  
  public static final int CME_SIM_BUSY = 14;
  
  public static final int CME_SIM_FAILURE = 13;
  
  public static final int CME_SIM_NOT_INSERTED = 10;
  
  public static final int CME_SIM_PIN2_REQUIRED = 17;
  
  public static final int CME_SIM_PIN_REQUIRED = 11;
  
  public static final int CME_SIM_PUK2_REQUIRED = 18;
  
  public static final int CME_SIM_PUK_REQUIRED = 12;
  
  public static final int CME_SIM_WRONG = 15;
  
  public static final int CME_SIP_RESPONSE_CODE = 35;
  
  public static final int CME_TEXT_STRING_TOO_LONG = 24;
  
  private static final boolean DBG = true;
  
  public static final String EXTRA_AG_FEATURE_3WAY_CALLING = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_3WAY_CALLING";
  
  public static final String EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL";
  
  public static final String EXTRA_AG_FEATURE_ATTACH_NUMBER_TO_VT = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ATTACH_NUMBER_TO_VT";
  
  public static final String EXTRA_AG_FEATURE_ECC = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ECC";
  
  public static final String EXTRA_AG_FEATURE_MERGE = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE";
  
  public static final String EXTRA_AG_FEATURE_MERGE_AND_DETACH = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE_AND_DETACH";
  
  public static final String EXTRA_AG_FEATURE_REJECT_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_REJECT_CALL";
  
  public static final String EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT";
  
  public static final String EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL";
  
  public static final String EXTRA_AG_FEATURE_RESPONSE_AND_HOLD = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RESPONSE_AND_HOLD";
  
  public static final String EXTRA_AG_FEATURE_VOICE_RECOGNITION = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_VOICE_RECOGNITION";
  
  public static final String EXTRA_AUDIO_WBS = "android.bluetooth.headsetclient.extra.AUDIO_WBS";
  
  public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.headsetclient.extra.BATTERY_LEVEL";
  
  public static final String EXTRA_CALL = "android.bluetooth.headsetclient.extra.CALL";
  
  public static final String EXTRA_CME_CODE = "android.bluetooth.headsetclient.extra.CME_CODE";
  
  public static final String EXTRA_IN_BAND_RING = "android.bluetooth.headsetclient.extra.IN_BAND_RING";
  
  public static final String EXTRA_NETWORK_ROAMING = "android.bluetooth.headsetclient.extra.NETWORK_ROAMING";
  
  public static final String EXTRA_NETWORK_SIGNAL_STRENGTH = "android.bluetooth.headsetclient.extra.NETWORK_SIGNAL_STRENGTH";
  
  public static final String EXTRA_NETWORK_STATUS = "android.bluetooth.headsetclient.extra.NETWORK_STATUS";
  
  public static final String EXTRA_NUMBER = "android.bluetooth.headsetclient.extra.NUMBER";
  
  public static final String EXTRA_OPERATOR_NAME = "android.bluetooth.headsetclient.extra.OPERATOR_NAME";
  
  public static final String EXTRA_RESULT_CODE = "android.bluetooth.headsetclient.extra.RESULT_CODE";
  
  public static final String EXTRA_SUBSCRIBER_INFO = "android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO";
  
  public static final String EXTRA_VENDOR_EVENT_CODE = "android.bluetooth.headsetclient.extra.VENDOR_EVENT_CODE";
  
  public static final String EXTRA_VENDOR_EVENT_FULL_ARGS = "android.bluetooth.headsetclient.extra.VENDOR_EVENT_FULL_ARGS";
  
  public static final String EXTRA_VENDOR_ID = "android.bluetooth.headsetclient.extra.VENDOR_ID";
  
  public static final String EXTRA_VOICE_RECOGNITION = "android.bluetooth.headsetclient.extra.VOICE_RECOGNITION";
  
  public static final int STATE_AUDIO_CONNECTED = 2;
  
  public static final int STATE_AUDIO_CONNECTING = 1;
  
  public static final int STATE_AUDIO_DISCONNECTED = 0;
  
  private static final String TAG = "BluetoothHeadsetClient";
  
  private static final boolean VDBG = false;
  
  private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private final BluetoothProfileConnector<IBluetoothHeadsetClient> mProfileConnector = new BluetoothProfileConnector<IBluetoothHeadsetClient>(this, 16, "BluetoothHeadsetClient", IBluetoothHeadsetClient.class.getName()) {
      public IBluetoothHeadsetClient getServiceInterface(IBinder param1IBinder) {
        return IBluetoothHeadsetClient.Stub.asInterface(Binder.allowBlocking(param1IBinder));
      }
    };
  
  BluetoothHeadsetClient(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mProfileConnector.connect(paramContext, paramServiceListener);
  }
  
  private IBluetoothHeadsetClient getService() {
    return this.mProfileConnector.getService();
  }
  
  private boolean isEnabled() {
    boolean bool;
    if (this.mAdapter.getState() == 12) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    boolean bool;
    if (paramBluetoothDevice != null && BluetoothAdapter.checkBluetoothAddress(paramBluetoothDevice.getAddress())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static void log(String paramString) {
    Log.d("BluetoothHeadsetClient", paramString);
  }
  
  public boolean acceptCall(BluetoothDevice paramBluetoothDevice, int paramInt) {
    log("acceptCall()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.acceptCall(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  void close() {
    this.mProfileConnector.disconnect();
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean connectAudio(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled()) {
      try {
        return iBluetoothHeadsetClient.connectAudio(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
      Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public BluetoothHeadsetClientCall dial(BluetoothDevice paramBluetoothDevice, String paramString) {
    log("dial()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.dial(paramBluetoothDevice, paramString);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return null;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnectAudio(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled()) {
      try {
        return iBluetoothHeadsetClient.disconnectAudio(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
      Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public boolean enterPrivateMode(BluetoothDevice paramBluetoothDevice, int paramInt) {
    log("enterPrivateMode()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.enterPrivateMode(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean explicitCallTransfer(BluetoothDevice paramBluetoothDevice) {
    log("explicitCallTransfer()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.explicitCallTransfer(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean getAudioRouteAllowed(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled()) {
      try {
        return iBluetoothHeadsetClient.getAudioRouteAllowed(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
      Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public int getAudioState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled()) {
      try {
        return iBluetoothHeadsetClient.getAudioState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
      Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
    } 
    return 0;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled())
      try {
        return iBluetoothHeadsetClient.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (remoteException == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return 0;
  }
  
  public Bundle getCurrentAgEvents(BluetoothDevice paramBluetoothDevice) {
    log("getCurrentCalls()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.getCurrentAgEvents(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return null;
  }
  
  public Bundle getCurrentAgFeatures(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled()) {
      try {
        return iBluetoothHeadsetClient.getCurrentAgFeatures(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
      Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
    } 
    return null;
  }
  
  public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice paramBluetoothDevice) {
    log("getCurrentCalls()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.getCurrentCalls(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return null;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled())
      try {
        return iBluetoothHeadsetClient.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public boolean getLastVoiceTagNumber(BluetoothDevice paramBluetoothDevice) {
    log("getLastVoiceTagNumber()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.getLastVoiceTagNumber(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(paramBluetoothDevice));
  }
  
  public boolean holdCall(BluetoothDevice paramBluetoothDevice) {
    log("holdCall()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.holdCall(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean rejectCall(BluetoothDevice paramBluetoothDevice) {
    log("rejectCall()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.rejectCall(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean sendDTMF(BluetoothDevice paramBluetoothDevice, byte paramByte) {
    log("sendDTMF()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.sendDTMF(paramBluetoothDevice, paramByte);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean sendVendorAtCommand(BluetoothDevice paramBluetoothDevice, int paramInt, String paramString) {
    log("sendVendorSpecificCommand()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.sendVendorAtCommand(paramBluetoothDevice, paramInt, paramString);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public void setAudioRouteAllowed(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) {
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled()) {
      try {
        iBluetoothHeadsetClient.setAudioRouteAllowed(paramBluetoothDevice, paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service");
      Log.d("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
    } 
  }
  
  @SystemApi
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setConnectionPolicy(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(", ");
    stringBuilder.append(paramInt);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothHeadsetClient.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setPriority(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(", ");
    stringBuilder.append(paramInt);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    return setConnectionPolicy(paramBluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(paramInt));
  }
  
  public boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) {
    log("startVoiceRecognition()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.startVoiceRecognition(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) {
    log("stopVoiceRecognition()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.stopVoiceRecognition(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean terminateCall(BluetoothDevice paramBluetoothDevice, BluetoothHeadsetClientCall paramBluetoothHeadsetClientCall) {
    log("terminateCall()");
    IBluetoothHeadsetClient iBluetoothHeadsetClient = getService();
    if (iBluetoothHeadsetClient != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadsetClient.terminateCall(paramBluetoothDevice, paramBluetoothHeadsetClientCall);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadsetClient", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadsetClient == null)
      Log.w("BluetoothHeadsetClient", "Proxy not attached to service"); 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadsetClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */