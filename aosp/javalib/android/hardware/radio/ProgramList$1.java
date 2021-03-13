package android.hardware.radio;

import java.util.concurrent.Executor;

class null extends ProgramList.ListCallback {
  public void onItemChanged(ProgramSelector.Identifier paramIdentifier) {
    executor.execute(new _$$Lambda$ProgramList$1$DVvry5MfhR6n8H2EZn67rvuhllI(callback, paramIdentifier));
  }
  
  public void onItemRemoved(ProgramSelector.Identifier paramIdentifier) {
    executor.execute(new _$$Lambda$ProgramList$1$a_xWqo5pESOZhcJIWvpiCd2AXmY(callback, paramIdentifier));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramList$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */