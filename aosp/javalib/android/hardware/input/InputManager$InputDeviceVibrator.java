package android.hardware.input;

import android.media.AudioAttributes;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import java.util.concurrent.Executor;

final class InputDeviceVibrator extends Vibrator {
  private final int mDeviceId;
  
  private final Binder mToken;
  
  public InputDeviceVibrator(int paramInt) {
    this.mDeviceId = paramInt;
    this.mToken = new Binder();
  }
  
  public void addVibratorStateListener(Vibrator.OnVibratorStateChangedListener paramOnVibratorStateChangedListener) {
    throw new UnsupportedOperationException("addVibratorStateListener not supported in InputDeviceVibrator");
  }
  
  public void addVibratorStateListener(Executor paramExecutor, Vibrator.OnVibratorStateChangedListener paramOnVibratorStateChangedListener) {
    throw new UnsupportedOperationException("addVibratorStateListener not supported in InputDeviceVibrator");
  }
  
  public void cancel() {
    try {
      InputManager.access$400(InputManager.this).cancelVibrate(this.mDeviceId, (IBinder)this.mToken);
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
  
  public void removeVibratorStateListener(Vibrator.OnVibratorStateChangedListener paramOnVibratorStateChangedListener) {
    throw new UnsupportedOperationException("removeVibratorStateListener not supported in InputDeviceVibrator");
  }
  
  public void vibrate(int paramInt, String paramString1, VibrationEffect paramVibrationEffect, String paramString2, AudioAttributes paramAudioAttributes) {
    long[] arrayOfLong;
    VibrationEffect.OneShot oneShot;
    if (paramVibrationEffect instanceof VibrationEffect.OneShot) {
      oneShot = (VibrationEffect.OneShot)paramVibrationEffect;
      arrayOfLong = new long[2];
      arrayOfLong[0] = 0L;
      arrayOfLong[1] = oneShot.getDuration();
      paramInt = -1;
    } else if (oneShot instanceof VibrationEffect.Waveform) {
      VibrationEffect.Waveform waveform = (VibrationEffect.Waveform)oneShot;
      arrayOfLong = waveform.getTimings();
      paramInt = waveform.getRepeatIndex();
    } else {
      Log.w("InputManager", "Pre-baked effects aren't supported on input devices");
      return;
    } 
    try {
      InputManager.access$400(InputManager.this).vibrate(this.mDeviceId, arrayOfLong, paramInt, (IBinder)this.mToken);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManager$InputDeviceVibrator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */