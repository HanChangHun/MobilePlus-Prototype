package android.hardware.cas.V1_0;

import android.os.HwParcel;
import java.util.ArrayList;

class null implements ICas.openSessionCallback {
  public void onValues(int paramInt, ArrayList<Byte> paramArrayList) {
    _hidl_reply.writeStatus(0);
    _hidl_reply.writeInt32(paramInt);
    _hidl_reply.writeInt8Vector(paramArrayList);
    _hidl_reply.send();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_0/ICas$Stub$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */