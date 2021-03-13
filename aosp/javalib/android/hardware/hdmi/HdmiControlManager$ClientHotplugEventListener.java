package android.hardware.hdmi;

import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ClientHotplugEventListener implements HdmiControlManager.HotplugEventListener {
  private ClientHotplugEventListener() {}
  
  public void onReceived(HdmiHotplugEvent paramHdmiHotplugEvent) {
    new ArrayList();
    try {
      StringBuilder stringBuilder;
      List<HdmiPortInfo> list = HdmiControlManager.access$100(HdmiControlManager.this).getPortInfo();
      if (list.isEmpty()) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Can't find port info, not updating connected status. Hotplug event:");
        stringBuilder.append(paramHdmiHotplugEvent);
        Log.e("HdmiControlManager", stringBuilder.toString());
        return;
      } 
      Iterator<HdmiPortInfo> iterator = stringBuilder.iterator();
      while (iterator.hasNext()) {
        HdmiPortInfo hdmiPortInfo = iterator.next();
        if (hdmiPortInfo.getId() == paramHdmiHotplugEvent.getPort()) {
          if (hdmiPortInfo.getType() == 1) {
            char c;
            HdmiControlManager hdmiControlManager = HdmiControlManager.this;
            if (paramHdmiHotplugEvent.isConnected()) {
              c = hdmiPortInfo.getAddress();
            } else {
              c = '￿';
            } 
            HdmiControlManager.access$200(hdmiControlManager, c);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiControlManager$ClientHotplugEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */