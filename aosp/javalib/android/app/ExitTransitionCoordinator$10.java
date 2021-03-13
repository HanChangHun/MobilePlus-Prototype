package android.app;

import android.os.Bundle;
import android.os.ResultReceiver;

class null implements SharedElementCallback.OnSharedElementsReadyListener {
  public void onSharedElementsReady() {
    resultReceiver.send(103, sharedElementBundle);
    ExitTransitionCoordinator.access$1200(ExitTransitionCoordinator.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExitTransitionCoordinator$10.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */