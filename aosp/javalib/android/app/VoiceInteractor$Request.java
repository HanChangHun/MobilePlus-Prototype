package android.app;

import android.content.Context;
import android.os.RemoteException;
import android.util.DebugUtils;
import android.util.Log;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.app.IVoiceInteractorCallback;
import com.android.internal.app.IVoiceInteractorRequest;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class Request {
  Activity mActivity;
  
  Context mContext;
  
  String mName;
  
  IVoiceInteractorRequest mRequestInterface;
  
  public void cancel() {
    IVoiceInteractorRequest iVoiceInteractorRequest = this.mRequestInterface;
    if (iVoiceInteractorRequest != null) {
      try {
        iVoiceInteractorRequest.cancel();
      } catch (RemoteException remoteException) {
        Log.w("VoiceInteractor", "Voice interactor has died", (Throwable)remoteException);
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Request ");
    stringBuilder.append(this);
    stringBuilder.append(" is no longer active");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  void clear() {
    this.mRequestInterface = null;
    this.mContext = null;
    this.mActivity = null;
    this.mName = null;
  }
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRequestInterface=");
    paramPrintWriter.println(this.mRequestInterface.asBinder());
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mActivity=");
    paramPrintWriter.println(this.mActivity);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mName=");
    paramPrintWriter.println(this.mName);
  }
  
  public Activity getActivity() {
    return this.mActivity;
  }
  
  public Context getContext() {
    return this.mContext;
  }
  
  public String getName() {
    return this.mName;
  }
  
  String getRequestTypeName() {
    return "Request";
  }
  
  public void onAttached(Activity paramActivity) {}
  
  public void onCancel() {}
  
  public void onDetached() {}
  
  abstract IVoiceInteractorRequest submit(IVoiceInteractor paramIVoiceInteractor, String paramString, IVoiceInteractorCallback paramIVoiceInteractorCallback) throws RemoteException;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, stringBuilder);
    stringBuilder.append(" ");
    stringBuilder.append(getRequestTypeName());
    stringBuilder.append(" name=");
    stringBuilder.append(this.mName);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$Request.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */