package android.app;

import android.os.Bundle;
import com.android.internal.app.IVoiceInteractorCallback;
import com.android.internal.app.IVoiceInteractorRequest;
import com.android.internal.os.HandlerCaller;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.function.Consumer;

class null extends IVoiceInteractorCallback.Stub {
  public void deliverAbortVoiceResult(IVoiceInteractorRequest paramIVoiceInteractorRequest, Bundle paramBundle) {
    VoiceInteractor.this.mHandlerCaller.sendMessage(VoiceInteractor.this.mHandlerCaller.obtainMessageOO(4, paramIVoiceInteractorRequest, paramBundle));
  }
  
  public void deliverCancel(IVoiceInteractorRequest paramIVoiceInteractorRequest) {
    VoiceInteractor.this.mHandlerCaller.sendMessage(VoiceInteractor.this.mHandlerCaller.obtainMessageOO(6, paramIVoiceInteractorRequest, null));
  }
  
  public void deliverCommandResult(IVoiceInteractorRequest paramIVoiceInteractorRequest, boolean paramBoolean, Bundle paramBundle) {
    HandlerCaller handlerCaller1 = VoiceInteractor.this.mHandlerCaller;
    HandlerCaller handlerCaller2 = VoiceInteractor.this.mHandlerCaller;
    handlerCaller1.sendMessage(handlerCaller2.obtainMessageIOO(5, paramBoolean, paramIVoiceInteractorRequest, paramBundle));
  }
  
  public void deliverCompleteVoiceResult(IVoiceInteractorRequest paramIVoiceInteractorRequest, Bundle paramBundle) {
    VoiceInteractor.this.mHandlerCaller.sendMessage(VoiceInteractor.this.mHandlerCaller.obtainMessageOO(3, paramIVoiceInteractorRequest, paramBundle));
  }
  
  public void deliverConfirmationResult(IVoiceInteractorRequest paramIVoiceInteractorRequest, boolean paramBoolean, Bundle paramBundle) {
    HandlerCaller handlerCaller1 = VoiceInteractor.this.mHandlerCaller;
    HandlerCaller handlerCaller2 = VoiceInteractor.this.mHandlerCaller;
    handlerCaller1.sendMessage(handlerCaller2.obtainMessageIOO(1, paramBoolean, paramIVoiceInteractorRequest, paramBundle));
  }
  
  public void deliverPickOptionResult(IVoiceInteractorRequest paramIVoiceInteractorRequest, boolean paramBoolean, VoiceInteractor.PickOptionRequest.Option[] paramArrayOfOption, Bundle paramBundle) {
    HandlerCaller handlerCaller1 = VoiceInteractor.this.mHandlerCaller;
    HandlerCaller handlerCaller2 = VoiceInteractor.this.mHandlerCaller;
    handlerCaller1.sendMessage(handlerCaller2.obtainMessageIOOO(2, paramBoolean, paramIVoiceInteractorRequest, paramArrayOfOption, paramBundle));
  }
  
  public void destroy() {
    VoiceInteractor.this.mHandlerCaller.getHandler().sendMessage(PooledLambda.obtainMessage((Consumer)_$$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k.INSTANCE, VoiceInteractor.this));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */