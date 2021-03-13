package android.app;

import android.os.ICancellationSignal;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.ref.WeakReference;
import java.util.function.Consumer;

final class KillCallback extends ICancellationSignal.Stub {
  private final WeakReference<VoiceInteractor> mInteractor;
  
  KillCallback(VoiceInteractor paramVoiceInteractor) {
    this.mInteractor = new WeakReference<>(paramVoiceInteractor);
  }
  
  public void cancel() {
    VoiceInteractor voiceInteractor = this.mInteractor.get();
    if (voiceInteractor != null)
      voiceInteractor.mHandlerCaller.getHandler().sendMessage(PooledLambda.obtainMessage((Consumer)_$$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k.INSTANCE, voiceInteractor)); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$KillCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */