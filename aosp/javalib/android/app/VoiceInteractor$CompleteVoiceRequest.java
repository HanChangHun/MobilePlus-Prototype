package android.app;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.app.IVoiceInteractorCallback;
import com.android.internal.app.IVoiceInteractorRequest;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class CompleteVoiceRequest extends VoiceInteractor.Request {
  final Bundle mExtras;
  
  final VoiceInteractor.Prompt mPrompt;
  
  public CompleteVoiceRequest(VoiceInteractor.Prompt paramPrompt, Bundle paramBundle) {
    this.mPrompt = paramPrompt;
    this.mExtras = paramBundle;
  }
  
  public CompleteVoiceRequest(CharSequence paramCharSequence, Bundle paramBundle) {
    if (paramCharSequence != null) {
      VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(paramCharSequence);
    } else {
      paramCharSequence = null;
    } 
    this.mPrompt = (VoiceInteractor.Prompt)paramCharSequence;
    this.mExtras = paramBundle;
  }
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mPrompt=");
    paramPrintWriter.println(this.mPrompt);
    if (this.mExtras != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mExtras=");
      paramPrintWriter.println(this.mExtras);
    } 
  }
  
  String getRequestTypeName() {
    return "CompleteVoice";
  }
  
  public void onCompleteResult(Bundle paramBundle) {}
  
  IVoiceInteractorRequest submit(IVoiceInteractor paramIVoiceInteractor, String paramString, IVoiceInteractorCallback paramIVoiceInteractorCallback) throws RemoteException {
    return paramIVoiceInteractor.startCompleteVoice(paramString, paramIVoiceInteractorCallback, this.mPrompt, this.mExtras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$CompleteVoiceRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */