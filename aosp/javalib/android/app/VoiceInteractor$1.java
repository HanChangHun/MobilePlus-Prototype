package android.app;

import android.os.Bundle;
import android.os.Message;
import com.android.internal.app.IVoiceInteractorRequest;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;

class null implements HandlerCaller.Callback {
  public void executeMessage(Message paramMessage) {
    VoiceInteractor.Request request1;
    SomeArgs someArgs = (SomeArgs)paramMessage.obj;
    int i = paramMessage.what;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    switch (i) {
      default:
        return;
      case 6:
        request1 = VoiceInteractor.this.pullRequest((IVoiceInteractorRequest)someArgs.arg1, true);
        if (request1 != null) {
          request1.onCancel();
          request1.clear();
        } 
      case 5:
        if (((Message)request1).arg1 != 0) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        request2 = VoiceInteractor.this.pullRequest((IVoiceInteractorRequest)someArgs.arg1, bool1);
        if (request2 != null) {
          VoiceInteractor.CommandRequest commandRequest = (VoiceInteractor.CommandRequest)request2;
          if (((Message)request1).arg1 != 0)
            bool3 = true; 
          commandRequest.onCommandResult(bool3, (Bundle)someArgs.arg2);
          if (bool1)
            request2.clear(); 
        } 
      case 4:
        request1 = VoiceInteractor.this.pullRequest((IVoiceInteractorRequest)someArgs.arg1, true);
        if (request1 != null) {
          ((VoiceInteractor.AbortVoiceRequest)request1).onAbortResult((Bundle)someArgs.arg2);
          request1.clear();
        } 
      case 3:
        request1 = VoiceInteractor.this.pullRequest((IVoiceInteractorRequest)someArgs.arg1, true);
        if (request1 != null) {
          ((VoiceInteractor.CompleteVoiceRequest)request1).onCompleteResult((Bundle)someArgs.arg2);
          request1.clear();
        } 
      case 2:
        if (((Message)request1).arg1 != 0)
          bool1 = true; 
        request1 = VoiceInteractor.this.pullRequest((IVoiceInteractorRequest)someArgs.arg1, bool1);
        if (request1 != null) {
          ((VoiceInteractor.PickOptionRequest)request1).onPickOptionResult(bool1, (VoiceInteractor.PickOptionRequest.Option[])someArgs.arg2, (Bundle)someArgs.arg3);
          if (bool1)
            request1.clear(); 
        } 
      case 1:
        break;
    } 
    VoiceInteractor.Request request2 = VoiceInteractor.this.pullRequest((IVoiceInteractorRequest)someArgs.arg1, true);
    if (request2 != null) {
      VoiceInteractor.ConfirmationRequest confirmationRequest = (VoiceInteractor.ConfirmationRequest)request2;
      bool1 = bool2;
      if (((Message)request1).arg1 != 0)
        bool1 = true; 
      confirmationRequest.onConfirmationResult(bool1, (Bundle)someArgs.arg2);
      request2.clear();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */