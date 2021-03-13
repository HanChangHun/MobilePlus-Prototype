package android.hardware.radio;

import java.util.List;
import java.util.concurrent.Executor;

class null extends IAnnouncementListener.Stub {
  public void onListUpdated(List<Announcement> paramList) {
    executor.execute(new _$$Lambda$RadioManager$1$yOwq8CG0kiZcgKFclFSIrjag008(listener, paramList));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */