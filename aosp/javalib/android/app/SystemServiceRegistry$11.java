package android.app;

import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.IHdmiControlService;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<HdmiControlManager> {
  public HdmiControlManager createService() throws ServiceManager.ServiceNotFoundException {
    return new HdmiControlManager(IHdmiControlService.Stub.asInterface(ServiceManager.getServiceOrThrow("hdmi_control")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */