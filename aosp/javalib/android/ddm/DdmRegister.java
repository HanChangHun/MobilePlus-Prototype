package android.ddm;

import org.apache.harmony.dalvik.ddmc.DdmServer;

public class DdmRegister {
  public static void registerHandlers() {
    DdmHandleHello.register();
    DdmHandleThread.register();
    DdmHandleHeap.register();
    DdmHandleNativeHeap.register();
    DdmHandleProfiling.register();
    DdmHandleExit.register();
    DdmHandleViewDebug.register();
    DdmServer.registrationComplete();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */