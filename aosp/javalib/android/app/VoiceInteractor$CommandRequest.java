package android.app;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.app.IVoiceInteractorCallback;
import com.android.internal.app.IVoiceInteractorRequest;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class CommandRequest extends VoiceInteractor.Request {
  final Bundle mArgs;
  
  final String mCommand;
  
  public CommandRequest(String paramString, Bundle paramBundle) {
    this.mCommand = paramString;
    this.mArgs = paramBundle;
  }
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCommand=");
    paramPrintWriter.println(this.mCommand);
    if (this.mArgs != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArgs=");
      paramPrintWriter.println(this.mArgs);
    } 
  }
  
  String getRequestTypeName() {
    return "Command";
  }
  
  public void onCommandResult(boolean paramBoolean, Bundle paramBundle) {}
  
  IVoiceInteractorRequest submit(IVoiceInteractor paramIVoiceInteractor, String paramString, IVoiceInteractorCallback paramIVoiceInteractorCallback) throws RemoteException {
    return paramIVoiceInteractor.startCommand(paramString, paramIVoiceInteractorCallback, this.mCommand, this.mArgs);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$CommandRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */