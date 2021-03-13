package android.hardware.hdmi;

import android.os.Binder;
import java.util.concurrent.Executor;

class null extends IHdmiCecVolumeControlFeatureListener.Stub {
  public void onHdmiCecVolumeControlFeature(boolean paramBoolean) {
    Binder.clearCallingIdentity();
    executor.execute(new _$$Lambda$HdmiControlManager$3$nhJsWlQLW7H_zQCJ36JgrRbU5zI(listener, paramBoolean));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiControlManager$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */