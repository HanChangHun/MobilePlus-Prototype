package android.hardware.cas.V1_2;

import android.os.HwParcel;
import java.util.ArrayList;

class null implements ICas.openSession_1_2Callback {
  public void onValues(int paramInt, ArrayList<Byte> paramArrayList) {
    _hidl_reply.writeStatus(0);
    _hidl_reply.writeInt32(paramInt);
    _hidl_reply.writeInt8Vector(paramArrayList);
    _hidl_reply.send();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/ICas$Stub$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */