package android.bluetooth;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class BluetoothHeadset implements BluetoothProfile {
  public static final String ACTION_ACTIVE_DEVICE_CHANGED = "android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED";
  
  public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED";
  
  public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED";
  
  public static final String ACTION_HF_INDICATORS_VALUE_CHANGED = "android.bluetooth.headset.action.HF_INDICATORS_VALUE_CHANGED";
  
  public static final String ACTION_VENDOR_SPECIFIC_HEADSET_EVENT = "android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT";
  
  public static final int AT_CMD_TYPE_ACTION = 4;
  
  public static final int AT_CMD_TYPE_BASIC = 3;
  
  public static final int AT_CMD_TYPE_READ = 0;
  
  public static final int AT_CMD_TYPE_SET = 2;
  
  public static final int AT_CMD_TYPE_TEST = 1;
  
  private static final boolean DBG = true;
  
  public static final String EXTRA_HF_INDICATORS_IND_ID = "android.bluetooth.headset.extra.HF_INDICATORS_IND_ID";
  
  public static final String EXTRA_HF_INDICATORS_IND_VALUE = "android.bluetooth.headset.extra.HF_INDICATORS_IND_VALUE";
  
  public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_ARGS = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS";
  
  public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD";
  
  public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE";
  
  private static final int MESSAGE_HEADSET_SERVICE_CONNECTED = 100;
  
  private static final int MESSAGE_HEADSET_SERVICE_DISCONNECTED = 101;
  
  public static final int STATE_AUDIO_CONNECTED = 12;
  
  public static final int STATE_AUDIO_CONNECTING = 11;
  
  public static final int STATE_AUDIO_DISCONNECTED = 10;
  
  private static final String TAG = "BluetoothHeadset";
  
  private static final boolean VDBG = false;
  
  public static final String VENDOR_RESULT_CODE_COMMAND_ANDROID = "+ANDROID";
  
  public static final String VENDOR_SPECIFIC_HEADSET_EVENT_COMPANY_ID_CATEGORY = "android.bluetooth.headset.intent.category.companyid";
  
  public static final String VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV = "+IPHONEACCEV";
  
  public static final int VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV_BATTERY_LEVEL = 1;
  
  public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XAPL = "+XAPL";
  
  public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT = "+XEVENT";
  
  public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL = "BATTERY";
  
  private BluetoothAdapter mAdapter;
  
  private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback = new IBluetoothStateChangeCallback.Stub() {
      public void onBluetoothStateChange(boolean param1Boolean) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onBluetoothStateChange: up=");
        stringBuilder.append(param1Boolean);
        Log.d("BluetoothHeadset", stringBuilder.toString());
        if (!param1Boolean) {
          BluetoothHeadset.this.doUnbind();
        } else {
          BluetoothHeadset.this.doBind();
        } 
      }
    };
  
  private final IBluetoothProfileServiceConnection mConnection = new IBluetoothProfileServiceConnection.Stub() {
      public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
        Log.d("BluetoothHeadset", "Proxy object connected");
        BluetoothHeadset.access$202(BluetoothHeadset.this, IBluetoothHeadset.Stub.asInterface(Binder.allowBlocking(param1IBinder)));
        BluetoothHeadset.this.mHandler.sendMessage(BluetoothHeadset.this.mHandler.obtainMessage(100));
      }
      
      public void onServiceDisconnected(ComponentName param1ComponentName) {
        Log.d("BluetoothHeadset", "Proxy object disconnected");
        BluetoothHeadset.this.doUnbind();
        BluetoothHeadset.this.mHandler.sendMessage(BluetoothHeadset.this.mHandler.obtainMessage(101));
      }
    };
  
  private Context mContext;
  
  private final Handler mHandler = new Handler(Looper.getMainLooper()) {
      public void handleMessage(Message param1Message) {
        int i = param1Message.what;
        if (i != 100) {
          if (i == 101 && BluetoothHeadset.this.mServiceListener != null)
            BluetoothHeadset.this.mServiceListener.onServiceDisconnected(1); 
        } else if (BluetoothHeadset.this.mServiceListener != null) {
          BluetoothHeadset.this.mServiceListener.onServiceConnected(1, BluetoothHeadset.this);
        } 
      }
    };
  
  private volatile IBluetoothHeadset mService;
  
  private BluetoothProfile.ServiceListener mServiceListener;
  
  BluetoothHeadset(Context paramContext, BluetoothProfile.ServiceListener paramServiceListener) {
    this.mContext = paramContext;
    this.mServiceListener = paramServiceListener;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    this.mAdapter = bluetoothAdapter;
    IBluetoothManager iBluetoothManager = bluetoothAdapter.getBluetoothManager();
    if (iBluetoothManager != null)
      try {
        iBluetoothManager.registerStateChangeCallback(this.mBluetoothStateChangeCallback);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", "", (Throwable)remoteException);
      }  
    doBind();
  }
  
  private boolean doBind() {
    synchronized (this.mConnection) {
      IBluetoothHeadset iBluetoothHeadset = this.mService;
      if (iBluetoothHeadset == null)
        try {
          return this.mAdapter.getBluetoothManager().bindBluetoothProfileService(1, this.mConnection);
        } catch (RemoteException remoteException) {
          Log.e("BluetoothHeadset", "Unable to bind HeadsetService", (Throwable)remoteException);
        }  
      return false;
    } 
  }
  
  private void doUnbind() {
    synchronized (this.mConnection) {
      IBluetoothHeadset iBluetoothHeadset = this.mService;
      if (iBluetoothHeadset != null)
        try {
          this.mAdapter.getBluetoothManager().unbindBluetoothProfileService(1, this.mConnection);
          this.mService = null;
        } catch (RemoteException remoteException) {
          Log.e("BluetoothHeadset", "Unable to unbind HeadsetService", (Throwable)remoteException);
          this.mService = null;
        } finally {} 
      return;
    } 
  }
  
  public static boolean isBluetoothVoiceDialingEnabled(Context paramContext) {
    return paramContext.getResources().getBoolean(17891384);
  }
  
  private boolean isDisabled() {
    boolean bool;
    if (this.mAdapter.getState() == 10) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
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
  
  public static boolean isInbandRingingSupported(Context paramContext) {
    return paramContext.getResources().getBoolean(17891380);
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
    Log.d("BluetoothHeadset", paramString);
  }
  
  public void clccResponse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, String paramString, int paramInt5) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        iBluetoothHeadset.clccResponse(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, paramString, paramInt5);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
  }
  
  void close() {
    IBluetoothManager iBluetoothManager = this.mAdapter.getBluetoothManager();
    if (iBluetoothManager != null)
      try {
        iBluetoothManager.unregisterStateChangeCallback(this.mBluetoothStateChangeCallback);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", "", (Throwable)remoteException);
      }  
    this.mServiceListener = null;
    doUnbind();
  }
  
  @SystemApi
  public boolean connect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("connect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.connect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean connectAudio() {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        return iBluetoothHeadset.connectAudio();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  @SystemApi
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("disconnect(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.disconnect(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return false;
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean disconnectAudio() {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        return iBluetoothHeadset.disconnectAudio();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public BluetoothDevice getActiveDevice() {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled())
      try {
        return iBluetoothHeadset.getActiveDevice();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return null;
  }
  
  public boolean getAudioRouteAllowed() {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        return iBluetoothHeadset.getAudioRouteAllowed();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public int getAudioState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && !isDisabled()) {
      try {
        return iBluetoothHeadset.getAudioState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
    return 10;
  }
  
  public List<BluetoothDevice> getConnectedDevices() {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled())
      try {
        return iBluetoothHeadset.getConnectedDevices();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (remoteException == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  @SystemApi
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.getConnectionPolicy(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.getConnectionState(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled())
      try {
        return iBluetoothHeadset.getDevicesMatchingConnectionStates(paramArrayOfint);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return new ArrayList<>();
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return new ArrayList<>();
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return BluetoothAdapter.connectionPolicyToPriority(iBluetoothHeadset.getPriority(paramBluetoothDevice));
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return 0;
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return 0;
  }
  
  public boolean isAudioConnected(BluetoothDevice paramBluetoothDevice) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.isAudioConnected(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean isAudioOn() {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled())
      try {
        return iBluetoothHeadset.isAudioOn();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean isInbandRingingEnabled() {
    log("isInbandRingingEnabled()");
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled())
      try {
        return iBluetoothHeadset.isInbandRingingEnabled();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public void phoneStateChanged(int paramInt1, int paramInt2, int paramInt3, String paramString1, int paramInt4, String paramString2) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        iBluetoothHeadset.phoneStateChanged(paramInt1, paramInt2, paramInt3, paramString1, paramInt4, paramString2);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
  }
  
  public boolean sendVendorSpecificResultCode(BluetoothDevice paramBluetoothDevice, String paramString1, String paramString2) {
    log("sendVendorSpecificResultCode()");
    if (paramString1 != null) {
      IBluetoothHeadset iBluetoothHeadset = this.mService;
      if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
        try {
          return iBluetoothHeadset.sendVendorSpecificResultCode(paramBluetoothDevice, paramString1, paramString2);
        } catch (RemoteException remoteException) {
          Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        }  
      if (iBluetoothHeadset == null)
        Log.w("BluetoothHeadset", "Proxy not attached to service"); 
      return false;
    } 
    throw new IllegalArgumentException("command is null");
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setActiveDevice: ");
    stringBuilder.append(paramBluetoothDevice);
    Log.d("BluetoothHeadset", stringBuilder.toString());
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && (paramBluetoothDevice == null || isValidDevice(paramBluetoothDevice)))
      try {
        return iBluetoothHeadset.setActiveDevice(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public void setAudioRouteAllowed(boolean paramBoolean) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        iBluetoothHeadset.setAudioRouteAllowed(paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
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
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothHeadset.setConnectionPolicy(paramBluetoothDevice, paramInt);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public void setForceScoAudio(boolean paramBoolean) {
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        iBluetoothHeadset.setForceScoAudio(paramBoolean);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
  }
  
  @SystemApi
  @Deprecated
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setPriority(");
    stringBuilder.append(paramBluetoothDevice);
    stringBuilder.append(", ");
    stringBuilder.append(paramInt);
    stringBuilder.append(")");
    log(stringBuilder.toString());
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice)) {
      if (paramInt != 0 && paramInt != 100)
        return false; 
      try {
        return iBluetoothHeadset.setPriority(paramBluetoothDevice, BluetoothAdapter.priorityToConnectionPolicy(paramInt));
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
        return false;
      } 
    } 
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean startScoUsingVirtualVoiceCall() {
    log("startScoUsingVirtualVoiceCall()");
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        return iBluetoothHeadset.startScoUsingVirtualVoiceCall();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) {
    log("startVoiceRecognition()");
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.startVoiceRecognition(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
  
  public boolean stopScoUsingVirtualVoiceCall() {
    log("stopScoUsingVirtualVoiceCall()");
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled()) {
      try {
        return iBluetoothHeadset.stopScoUsingVirtualVoiceCall();
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", remoteException.toString());
      } 
    } else {
      Log.w("BluetoothHeadset", "Proxy not attached to service");
      Log.d("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
    } 
    return false;
  }
  
  public boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) {
    log("stopVoiceRecognition()");
    IBluetoothHeadset iBluetoothHeadset = this.mService;
    if (iBluetoothHeadset != null && isEnabled() && isValidDevice(paramBluetoothDevice))
      try {
        return iBluetoothHeadset.stopVoiceRecognition(paramBluetoothDevice);
      } catch (RemoteException remoteException) {
        Log.e("BluetoothHeadset", Log.getStackTraceString(new Throwable()));
      }  
    if (iBluetoothHeadset == null)
      Log.w("BluetoothHeadset", "Proxy not attached to service"); 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHeadset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */