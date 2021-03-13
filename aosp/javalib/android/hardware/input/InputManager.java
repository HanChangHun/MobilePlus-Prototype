package android.hardware.input;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.PointerIcon;
import android.view.VerifiedInputEvent;
import com.android.internal.os.SomeArgs;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class InputManager {
  public static final String ACTION_QUERY_KEYBOARD_LAYOUTS = "android.hardware.input.action.QUERY_KEYBOARD_LAYOUTS";
  
  private static final boolean DEBUG = false;
  
  public static final int DEFAULT_POINTER_SPEED = 0;
  
  public static final int INJECT_INPUT_EVENT_MODE_ASYNC = 0;
  
  public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH = 2;
  
  public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_RESULT = 1;
  
  public static final int MAX_POINTER_SPEED = 7;
  
  public static final String META_DATA_KEYBOARD_LAYOUTS = "android.hardware.input.metadata.KEYBOARD_LAYOUTS";
  
  public static final int MIN_POINTER_SPEED = -7;
  
  private static final int MSG_DEVICE_ADDED = 1;
  
  private static final int MSG_DEVICE_CHANGED = 3;
  
  private static final int MSG_DEVICE_REMOVED = 2;
  
  public static final int SWITCH_STATE_OFF = 0;
  
  public static final int SWITCH_STATE_ON = 1;
  
  public static final int SWITCH_STATE_UNKNOWN = -1;
  
  private static final String TAG = "InputManager";
  
  private static InputManager sInstance;
  
  private final IInputManager mIm;
  
  private final ArrayList<InputDeviceListenerDelegate> mInputDeviceListeners = new ArrayList<>();
  
  private SparseArray<InputDevice> mInputDevices;
  
  private InputDevicesChangedListener mInputDevicesChangedListener;
  
  private final Object mInputDevicesLock = new Object();
  
  private List<OnTabletModeChangedListenerDelegate> mOnTabletModeChangedListeners;
  
  private TabletModeChangedListener mTabletModeChangedListener;
  
  private final Object mTabletModeLock = new Object();
  
  private InputManager(IInputManager paramIInputManager) {
    this.mIm = paramIInputManager;
  }
  
  private static boolean containsDeviceId(int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b += 2) {
      if (paramArrayOfint[b] == paramInt)
        return true; 
    } 
    return false;
  }
  
  private int findInputDeviceListenerLocked(InputDeviceListener paramInputDeviceListener) {
    int i = this.mInputDeviceListeners.size();
    for (byte b = 0; b < i; b++) {
      if (((InputDeviceListenerDelegate)this.mInputDeviceListeners.get(b)).mListener == paramInputDeviceListener)
        return b; 
    } 
    return -1;
  }
  
  private int findOnTabletModeChangedListenerLocked(OnTabletModeChangedListener paramOnTabletModeChangedListener) {
    int i = this.mOnTabletModeChangedListeners.size();
    for (byte b = 0; b < i; b++) {
      if (((OnTabletModeChangedListenerDelegate)this.mOnTabletModeChangedListeners.get(b)).mListener == paramOnTabletModeChangedListener)
        return b; 
    } 
    return -1;
  }
  
  public static InputManager getInstance() {
    // Byte code:
    //   0: ldc android/hardware/input/InputManager
    //   2: monitorenter
    //   3: getstatic android/hardware/input/InputManager.sInstance : Landroid/hardware/input/InputManager;
    //   6: astore_0
    //   7: aload_0
    //   8: ifnonnull -> 46
    //   11: new android/hardware/input/InputManager
    //   14: astore_0
    //   15: aload_0
    //   16: ldc 'input'
    //   18: invokestatic getServiceOrThrow : (Ljava/lang/String;)Landroid/os/IBinder;
    //   21: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/hardware/input/IInputManager;
    //   24: invokespecial <init> : (Landroid/hardware/input/IInputManager;)V
    //   27: aload_0
    //   28: putstatic android/hardware/input/InputManager.sInstance : Landroid/hardware/input/InputManager;
    //   31: goto -> 46
    //   34: astore_1
    //   35: new java/lang/IllegalStateException
    //   38: astore_0
    //   39: aload_0
    //   40: aload_1
    //   41: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   44: aload_0
    //   45: athrow
    //   46: getstatic android/hardware/input/InputManager.sInstance : Landroid/hardware/input/InputManager;
    //   49: astore_0
    //   50: ldc android/hardware/input/InputManager
    //   52: monitorexit
    //   53: aload_0
    //   54: areturn
    //   55: astore_0
    //   56: ldc android/hardware/input/InputManager
    //   58: monitorexit
    //   59: aload_0
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	55	finally
    //   11	31	34	android/os/ServiceManager$ServiceNotFoundException
    //   11	31	55	finally
    //   35	46	55	finally
    //   46	53	55	finally
    //   56	59	55	finally
  }
  
  private void initializeTabletModeListenerLocked() {
    TabletModeChangedListener tabletModeChangedListener = new TabletModeChangedListener();
    try {
      this.mIm.registerTabletModeChangedListener(tabletModeChangedListener);
      this.mTabletModeChangedListener = tabletModeChangedListener;
      this.mOnTabletModeChangedListeners = new ArrayList<>();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private void onInputDevicesChanged(int[] paramArrayOfint) {
    synchronized (this.mInputDevicesLock) {
      int i = this.mInputDevices.size();
      while (--i > 0) {
        int j = this.mInputDevices.keyAt(i);
        if (!containsDeviceId(paramArrayOfint, j)) {
          this.mInputDevices.removeAt(i);
          sendMessageToInputDeviceListenersLocked(2, j);
        } 
      } 
      for (i = 0; i < paramArrayOfint.length; i += 2) {
        int k = paramArrayOfint[i];
        int j = this.mInputDevices.indexOfKey(k);
        if (j >= 0) {
          InputDevice inputDevice = (InputDevice)this.mInputDevices.valueAt(j);
          if (inputDevice != null) {
            int m = paramArrayOfint[i + 1];
            if (inputDevice.getGeneration() != m) {
              this.mInputDevices.setValueAt(j, null);
              sendMessageToInputDeviceListenersLocked(3, k);
            } 
          } 
        } else {
          this.mInputDevices.put(k, null);
          sendMessageToInputDeviceListenersLocked(1, k);
        } 
      } 
      return;
    } 
  }
  
  private void onTabletModeChanged(long paramLong, boolean paramBoolean) {
    synchronized (this.mTabletModeLock) {
      int i = this.mOnTabletModeChangedListeners.size();
      for (byte b = 0; b < i; b++)
        ((OnTabletModeChangedListenerDelegate)this.mOnTabletModeChangedListeners.get(b)).sendTabletModeChanged(paramLong, paramBoolean); 
      return;
    } 
  }
  
  private void populateInputDevicesLocked() {
    if (this.mInputDevicesChangedListener == null) {
      InputDevicesChangedListener inputDevicesChangedListener = new InputDevicesChangedListener();
      try {
        this.mIm.registerInputDevicesChangedListener(inputDevicesChangedListener);
        this.mInputDevicesChangedListener = inputDevicesChangedListener;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
    if (this.mInputDevices == null)
      try {
        int[] arrayOfInt = this.mIm.getInputDeviceIds();
        this.mInputDevices = new SparseArray();
        for (byte b = 0; b < arrayOfInt.length; b++)
          this.mInputDevices.put(arrayOfInt[b], null); 
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  private void sendMessageToInputDeviceListenersLocked(int paramInt1, int paramInt2) {
    int i = this.mInputDeviceListeners.size();
    for (byte b = 0; b < i; b++) {
      InputDeviceListenerDelegate inputDeviceListenerDelegate = this.mInputDeviceListeners.get(b);
      inputDeviceListenerDelegate.sendMessage(inputDeviceListenerDelegate.obtainMessage(paramInt1, paramInt2, 0));
    } 
  }
  
  public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) {
    if (paramInputDeviceIdentifier != null) {
      if (paramString != null)
        try {
          this.mIm.addKeyboardLayoutForInputDevice(paramInputDeviceIdentifier, paramString);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
    } 
    throw new IllegalArgumentException("inputDeviceDescriptor must not be null");
  }
  
  public void addPortAssociation(String paramString, int paramInt) {
    try {
      this.mIm.addPortAssociation(paramString, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean[] deviceHasKeys(int paramInt, int[] paramArrayOfint) {
    boolean[] arrayOfBoolean = new boolean[paramArrayOfint.length];
    try {
      this.mIm.hasKeys(paramInt, -256, paramArrayOfint, arrayOfBoolean);
      return arrayOfBoolean;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean[] deviceHasKeys(int[] paramArrayOfint) {
    return deviceHasKeys(-1, paramArrayOfint);
  }
  
  public void disableInputDevice(int paramInt) {
    try {
      this.mIm.disableInputDevice(paramInt);
      return;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not disable input device with id = ");
      stringBuilder.append(paramInt);
      Log.w("InputManager", stringBuilder.toString());
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void enableInputDevice(int paramInt) {
    try {
      this.mIm.enableInputDevice(paramInt);
      return;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not enable input device with id = ");
      stringBuilder.append(paramInt);
      Log.w("InputManager", stringBuilder.toString());
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) {
    try {
      return this.mIm.getCurrentKeyboardLayoutForInputDevice(paramInputDeviceIdentifier);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) {
    if (paramInputDeviceIdentifier != null)
      try {
        return this.mIm.getEnabledKeyboardLayoutsForInputDevice(paramInputDeviceIdentifier);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("inputDeviceDescriptor must not be null");
  }
  
  public InputDevice getInputDevice(int paramInt) {
    synchronized (this.mInputDevicesLock) {
      populateInputDevicesLocked();
      int i = this.mInputDevices.indexOfKey(paramInt);
      if (i < 0)
        return null; 
      InputDevice inputDevice1 = (InputDevice)this.mInputDevices.valueAt(i);
      InputDevice inputDevice2 = inputDevice1;
      if (inputDevice1 == null)
        try {
          inputDevice1 = this.mIm.getInputDevice(paramInt);
          inputDevice2 = inputDevice1;
          if (inputDevice1 != null) {
            this.mInputDevices.setValueAt(i, inputDevice1);
            inputDevice2 = inputDevice1;
          } 
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return (InputDevice)remoteException;
    } 
  }
  
  public InputDevice getInputDeviceByDescriptor(String paramString) {
    if (paramString != null)
      synchronized (this.mInputDevicesLock) {
        populateInputDevicesLocked();
        int i = this.mInputDevices.size();
        for (byte b = 0; b < i; b++) {
          InputDevice inputDevice1 = (InputDevice)this.mInputDevices.valueAt(b);
          InputDevice inputDevice2 = inputDevice1;
          if (inputDevice1 == null) {
            int j = this.mInputDevices.keyAt(b);
            try {
              inputDevice2 = this.mIm.getInputDevice(j);
              if (inputDevice2 == null)
                continue; 
              this.mInputDevices.setValueAt(b, inputDevice2);
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            } 
          } 
          if (remoteException.equals(inputDevice2.getDescriptor()))
            return inputDevice2; 
          continue;
        } 
        return null;
      }  
    throw new IllegalArgumentException("descriptor must not be null.");
  }
  
  public int[] getInputDeviceIds() {
    synchronized (this.mInputDevicesLock) {
      populateInputDevicesLocked();
      int i = this.mInputDevices.size();
      int[] arrayOfInt = new int[i];
      for (byte b = 0; b < i; b++)
        arrayOfInt[b] = this.mInputDevices.keyAt(b); 
      return arrayOfInt;
    } 
  }
  
  public Vibrator getInputDeviceVibrator(int paramInt) {
    return new InputDeviceVibrator(paramInt);
  }
  
  public KeyboardLayout getKeyboardLayout(String paramString) {
    if (paramString != null)
      try {
        return this.mIm.getKeyboardLayout(paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
  }
  
  public KeyboardLayout[] getKeyboardLayouts() {
    try {
      return this.mIm.getKeyboardLayouts();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) {
    try {
      return this.mIm.getKeyboardLayoutsForInputDevice(paramInputDeviceIdentifier);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getPointerSpeed(Context paramContext) {
    int i = 0;
    try {
      int j = Settings.System.getInt(paramContext.getContentResolver(), "pointer_speed");
      i = j;
    } catch (android.provider.Settings.SettingNotFoundException settingNotFoundException) {}
    return i;
  }
  
  public TouchCalibration getTouchCalibration(String paramString, int paramInt) {
    try {
      return this.mIm.getTouchCalibrationForInputDevice(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean injectInputEvent(InputEvent paramInputEvent, int paramInt) {
    if (paramInputEvent != null) {
      if (paramInt == 0 || paramInt == 2 || paramInt == 1)
        try {
          return this.mIm.injectInputEvent(paramInputEvent, paramInt);
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("mode is invalid");
    } 
    throw new IllegalArgumentException("event must not be null");
  }
  
  public int isInTabletMode() {
    try {
      return this.mIm.isInTabletMode();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isInputDeviceEnabled(int paramInt) {
    try {
      return this.mIm.isInputDeviceEnabled(paramInt);
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not check enabled status of input device with id = ");
      stringBuilder.append(paramInt);
      Log.w("InputManager", stringBuilder.toString());
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int isMicMuted() {
    try {
      return this.mIm.isMicMuted();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public InputMonitor monitorGestureInput(String paramString, int paramInt) {
    try {
      return this.mIm.monitorGestureInput(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void registerInputDeviceListener(InputDeviceListener paramInputDeviceListener, Handler paramHandler) {
    if (paramInputDeviceListener != null)
      synchronized (this.mInputDevicesLock) {
        populateInputDevicesLocked();
        if (findInputDeviceListenerLocked(paramInputDeviceListener) < 0) {
          ArrayList<InputDeviceListenerDelegate> arrayList = this.mInputDeviceListeners;
          InputDeviceListenerDelegate inputDeviceListenerDelegate = new InputDeviceListenerDelegate();
          this(paramInputDeviceListener, paramHandler);
          arrayList.add(inputDeviceListenerDelegate);
        } 
        return;
      }  
    throw new IllegalArgumentException("listener must not be null");
  }
  
  public void registerOnTabletModeChangedListener(OnTabletModeChangedListener paramOnTabletModeChangedListener, Handler paramHandler) {
    if (paramOnTabletModeChangedListener != null)
      synchronized (this.mTabletModeLock) {
        if (this.mOnTabletModeChangedListeners == null)
          initializeTabletModeListenerLocked(); 
        if (findOnTabletModeChangedListenerLocked(paramOnTabletModeChangedListener) < 0) {
          OnTabletModeChangedListenerDelegate onTabletModeChangedListenerDelegate = new OnTabletModeChangedListenerDelegate();
          this(paramOnTabletModeChangedListener, paramHandler);
          this.mOnTabletModeChangedListeners.add(onTabletModeChangedListenerDelegate);
        } 
        return;
      }  
    throw new IllegalArgumentException("listener must not be null");
  }
  
  public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) {
    if (paramInputDeviceIdentifier != null) {
      if (paramString != null)
        try {
          this.mIm.removeKeyboardLayoutForInputDevice(paramInputDeviceIdentifier, paramString);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
    } 
    throw new IllegalArgumentException("inputDeviceDescriptor must not be null");
  }
  
  public void removePortAssociation(String paramString) {
    try {
      this.mIm.removePortAssociation(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void requestPointerCapture(IBinder paramIBinder, boolean paramBoolean) {
    try {
      this.mIm.requestPointerCapture(paramIBinder, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) {
    if (paramInputDeviceIdentifier != null) {
      if (paramString != null)
        try {
          this.mIm.setCurrentKeyboardLayoutForInputDevice(paramInputDeviceIdentifier, paramString);
          return;
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
    } 
    throw new IllegalArgumentException("identifier must not be null");
  }
  
  public void setCustomPointerIcon(PointerIcon paramPointerIcon) {
    try {
      this.mIm.setCustomPointerIcon(paramPointerIcon);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setPointerIconType(int paramInt) {
    try {
      this.mIm.setPointerIconType(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setPointerSpeed(Context paramContext, int paramInt) {
    if (paramInt >= -7 && paramInt <= 7) {
      Settings.System.putInt(paramContext.getContentResolver(), "pointer_speed", paramInt);
      return;
    } 
    throw new IllegalArgumentException("speed out of range");
  }
  
  public void setTouchCalibration(String paramString, int paramInt, TouchCalibration paramTouchCalibration) {
    try {
      this.mIm.setTouchCalibrationForInputDevice(paramString, paramInt, paramTouchCalibration);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void tryPointerSpeed(int paramInt) {
    if (paramInt >= -7 && paramInt <= 7)
      try {
        this.mIm.tryPointerSpeed(paramInt);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("speed out of range");
  }
  
  public void unregisterInputDeviceListener(InputDeviceListener paramInputDeviceListener) {
    if (paramInputDeviceListener != null)
      synchronized (this.mInputDevicesLock) {
        int i = findInputDeviceListenerLocked(paramInputDeviceListener);
        if (i >= 0) {
          ((InputDeviceListenerDelegate)this.mInputDeviceListeners.get(i)).removeCallbacksAndMessages(null);
          this.mInputDeviceListeners.remove(i);
        } 
        return;
      }  
    throw new IllegalArgumentException("listener must not be null");
  }
  
  public void unregisterOnTabletModeChangedListener(OnTabletModeChangedListener paramOnTabletModeChangedListener) {
    if (paramOnTabletModeChangedListener != null)
      synchronized (this.mTabletModeLock) {
        int i = findOnTabletModeChangedListenerLocked(paramOnTabletModeChangedListener);
        if (i >= 0)
          ((OnTabletModeChangedListenerDelegate)this.mOnTabletModeChangedListeners.remove(i)).removeCallbacksAndMessages(null); 
        return;
      }  
    throw new IllegalArgumentException("listener must not be null");
  }
  
  public VerifiedInputEvent verifyInputEvent(InputEvent paramInputEvent) {
    try {
      return this.mIm.verifyInputEvent(paramInputEvent);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static interface InputDeviceListener {
    void onInputDeviceAdded(int param1Int);
    
    void onInputDeviceChanged(int param1Int);
    
    void onInputDeviceRemoved(int param1Int);
  }
  
  private static final class InputDeviceListenerDelegate extends Handler {
    public final InputManager.InputDeviceListener mListener;
    
    public InputDeviceListenerDelegate(InputManager.InputDeviceListener param1InputDeviceListener, Handler param1Handler) {
      super(looper);
      Looper looper;
      this.mListener = param1InputDeviceListener;
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          if (i == 3)
            this.mListener.onInputDeviceChanged(param1Message.arg1); 
        } else {
          this.mListener.onInputDeviceRemoved(param1Message.arg1);
        } 
      } else {
        this.mListener.onInputDeviceAdded(param1Message.arg1);
      } 
    }
  }
  
  private final class InputDeviceVibrator extends Vibrator {
    private final int mDeviceId;
    
    private final Binder mToken;
    
    public InputDeviceVibrator(int param1Int) {
      this.mDeviceId = param1Int;
      this.mToken = new Binder();
    }
    
    public void addVibratorStateListener(Vibrator.OnVibratorStateChangedListener param1OnVibratorStateChangedListener) {
      throw new UnsupportedOperationException("addVibratorStateListener not supported in InputDeviceVibrator");
    }
    
    public void addVibratorStateListener(Executor param1Executor, Vibrator.OnVibratorStateChangedListener param1OnVibratorStateChangedListener) {
      throw new UnsupportedOperationException("addVibratorStateListener not supported in InputDeviceVibrator");
    }
    
    public void cancel() {
      try {
        InputManager.this.mIm.cancelVibrate(this.mDeviceId, (IBinder)this.mToken);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public boolean hasAmplitudeControl() {
      return false;
    }
    
    public boolean hasVibrator() {
      return true;
    }
    
    public boolean isVibrating() {
      throw new UnsupportedOperationException("isVibrating not supported in InputDeviceVibrator");
    }
    
    public void removeVibratorStateListener(Vibrator.OnVibratorStateChangedListener param1OnVibratorStateChangedListener) {
      throw new UnsupportedOperationException("removeVibratorStateListener not supported in InputDeviceVibrator");
    }
    
    public void vibrate(int param1Int, String param1String1, VibrationEffect param1VibrationEffect, String param1String2, AudioAttributes param1AudioAttributes) {
      long[] arrayOfLong;
      VibrationEffect.OneShot oneShot;
      if (param1VibrationEffect instanceof VibrationEffect.OneShot) {
        oneShot = (VibrationEffect.OneShot)param1VibrationEffect;
        arrayOfLong = new long[2];
        arrayOfLong[0] = 0L;
        arrayOfLong[1] = oneShot.getDuration();
        param1Int = -1;
      } else if (oneShot instanceof VibrationEffect.Waveform) {
        VibrationEffect.Waveform waveform = (VibrationEffect.Waveform)oneShot;
        arrayOfLong = waveform.getTimings();
        param1Int = waveform.getRepeatIndex();
      } else {
        Log.w("InputManager", "Pre-baked effects aren't supported on input devices");
        return;
      } 
      try {
        InputManager.this.mIm.vibrate(this.mDeviceId, arrayOfLong, param1Int, (IBinder)this.mToken);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
  }
  
  private final class InputDevicesChangedListener extends IInputDevicesChangedListener.Stub {
    private InputDevicesChangedListener() {}
    
    public void onInputDevicesChanged(int[] param1ArrayOfint) throws RemoteException {
      InputManager.this.onInputDevicesChanged(param1ArrayOfint);
    }
  }
  
  public static interface OnTabletModeChangedListener {
    void onTabletModeChanged(long param1Long, boolean param1Boolean);
  }
  
  private static final class OnTabletModeChangedListenerDelegate extends Handler {
    private static final int MSG_TABLET_MODE_CHANGED = 0;
    
    public final InputManager.OnTabletModeChangedListener mListener;
    
    public OnTabletModeChangedListenerDelegate(InputManager.OnTabletModeChangedListener param1OnTabletModeChangedListener, Handler param1Handler) {
      super(looper);
      Looper looper;
      this.mListener = param1OnTabletModeChangedListener;
    }
    
    public void handleMessage(Message param1Message) {
      if (param1Message.what == 0) {
        SomeArgs someArgs = (SomeArgs)param1Message.obj;
        long l1 = someArgs.argi1;
        long l2 = someArgs.argi2;
        boolean bool = ((Boolean)someArgs.arg1).booleanValue();
        this.mListener.onTabletModeChanged(l1 & 0xFFFFFFFFL | l2 << 32L, bool);
      } 
    }
    
    public void sendTabletModeChanged(long param1Long, boolean param1Boolean) {
      SomeArgs someArgs = SomeArgs.obtain();
      someArgs.argi1 = (int)(0xFFFFFFFFFFFFFFFFL & param1Long);
      someArgs.argi2 = (int)(param1Long >> 32L);
      someArgs.arg1 = Boolean.valueOf(param1Boolean);
      obtainMessage(0, someArgs).sendToTarget();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SwitchState {}
  
  private final class TabletModeChangedListener extends ITabletModeChangedListener.Stub {
    private TabletModeChangedListener() {}
    
    public void onTabletModeChanged(long param1Long, boolean param1Boolean) {
      InputManager.this.onTabletModeChanged(param1Long, param1Boolean);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */