package android.app;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.DebugUtils;
import android.util.Log;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.app.IVoiceInteractorCallback;
import com.android.internal.app.IVoiceInteractorRequest;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public final class VoiceInteractor {
  static final boolean DEBUG = false;
  
  public static final String KEY_CANCELLATION_SIGNAL = "key_cancellation_signal";
  
  public static final String KEY_KILL_SIGNAL = "key_kill_signal";
  
  static final int MSG_ABORT_VOICE_RESULT = 4;
  
  static final int MSG_CANCEL_RESULT = 6;
  
  static final int MSG_COMMAND_RESULT = 5;
  
  static final int MSG_COMPLETE_VOICE_RESULT = 3;
  
  static final int MSG_CONFIRMATION_RESULT = 1;
  
  static final int MSG_PICK_OPTION_RESULT = 2;
  
  static final Request[] NO_REQUESTS = new Request[0];
  
  static final String TAG = "VoiceInteractor";
  
  final ArrayMap<IBinder, Request> mActiveRequests = new ArrayMap();
  
  Activity mActivity;
  
  final IVoiceInteractorCallback.Stub mCallback = new IVoiceInteractorCallback.Stub() {
      public void deliverAbortVoiceResult(IVoiceInteractorRequest param1IVoiceInteractorRequest, Bundle param1Bundle) {
        VoiceInteractor.this.mHandlerCaller.sendMessage(VoiceInteractor.this.mHandlerCaller.obtainMessageOO(4, param1IVoiceInteractorRequest, param1Bundle));
      }
      
      public void deliverCancel(IVoiceInteractorRequest param1IVoiceInteractorRequest) {
        VoiceInteractor.this.mHandlerCaller.sendMessage(VoiceInteractor.this.mHandlerCaller.obtainMessageOO(6, param1IVoiceInteractorRequest, null));
      }
      
      public void deliverCommandResult(IVoiceInteractorRequest param1IVoiceInteractorRequest, boolean param1Boolean, Bundle param1Bundle) {
        HandlerCaller handlerCaller1 = VoiceInteractor.this.mHandlerCaller;
        HandlerCaller handlerCaller2 = VoiceInteractor.this.mHandlerCaller;
        handlerCaller1.sendMessage(handlerCaller2.obtainMessageIOO(5, param1Boolean, param1IVoiceInteractorRequest, param1Bundle));
      }
      
      public void deliverCompleteVoiceResult(IVoiceInteractorRequest param1IVoiceInteractorRequest, Bundle param1Bundle) {
        VoiceInteractor.this.mHandlerCaller.sendMessage(VoiceInteractor.this.mHandlerCaller.obtainMessageOO(3, param1IVoiceInteractorRequest, param1Bundle));
      }
      
      public void deliverConfirmationResult(IVoiceInteractorRequest param1IVoiceInteractorRequest, boolean param1Boolean, Bundle param1Bundle) {
        HandlerCaller handlerCaller1 = VoiceInteractor.this.mHandlerCaller;
        HandlerCaller handlerCaller2 = VoiceInteractor.this.mHandlerCaller;
        handlerCaller1.sendMessage(handlerCaller2.obtainMessageIOO(1, param1Boolean, param1IVoiceInteractorRequest, param1Bundle));
      }
      
      public void deliverPickOptionResult(IVoiceInteractorRequest param1IVoiceInteractorRequest, boolean param1Boolean, VoiceInteractor.PickOptionRequest.Option[] param1ArrayOfOption, Bundle param1Bundle) {
        HandlerCaller handlerCaller1 = VoiceInteractor.this.mHandlerCaller;
        HandlerCaller handlerCaller2 = VoiceInteractor.this.mHandlerCaller;
        handlerCaller1.sendMessage(handlerCaller2.obtainMessageIOOO(2, param1Boolean, param1IVoiceInteractorRequest, param1ArrayOfOption, param1Bundle));
      }
      
      public void destroy() {
        VoiceInteractor.this.mHandlerCaller.getHandler().sendMessage(PooledLambda.obtainMessage((Consumer)_$$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k.INSTANCE, VoiceInteractor.this));
      }
    };
  
  Context mContext;
  
  final HandlerCaller mHandlerCaller;
  
  final HandlerCaller.Callback mHandlerCallerCallback = new HandlerCaller.Callback() {
      public void executeMessage(Message param1Message) {
        VoiceInteractor.Request request1;
        SomeArgs someArgs = (SomeArgs)param1Message.obj;
        int i = param1Message.what;
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
    };
  
  IVoiceInteractor mInteractor;
  
  final ArrayMap<Runnable, Executor> mOnDestroyCallbacks = new ArrayMap();
  
  boolean mRetaining;
  
  VoiceInteractor(IVoiceInteractor paramIVoiceInteractor, Context paramContext, Activity paramActivity, Looper paramLooper) {
    this.mInteractor = paramIVoiceInteractor;
    this.mContext = paramContext;
    this.mActivity = paramActivity;
    this.mHandlerCaller = new HandlerCaller(paramContext, paramLooper, this.mHandlerCallerCallback, true);
    try {
      IVoiceInteractor iVoiceInteractor = this.mInteractor;
      KillCallback killCallback = new KillCallback();
      this(this);
      iVoiceInteractor.setKillCallback((ICancellationSignal)killCallback);
    } catch (RemoteException remoteException) {}
  }
  
  private ArrayList<Request> makeRequestList() {
    int i = this.mActiveRequests.size();
    if (i < 1)
      return null; 
    ArrayList<Request> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add((Request)this.mActiveRequests.valueAt(b)); 
    return arrayList;
  }
  
  void attachActivity(Activity paramActivity) {
    this.mRetaining = false;
    if (this.mActivity == paramActivity)
      return; 
    this.mContext = (Context)paramActivity;
    this.mActivity = paramActivity;
    ArrayList<Request> arrayList = makeRequestList();
    if (arrayList != null)
      for (byte b = 0; b < arrayList.size(); b++) {
        Request request = arrayList.get(b);
        request.mContext = (Context)paramActivity;
        request.mActivity = paramActivity;
        request.onAttached(paramActivity);
      }  
  }
  
  void destroy() {
    int i;
    for (i = this.mActiveRequests.size() - 1; i >= 0; i--) {
      Request request = (Request)this.mActiveRequests.valueAt(i);
      this.mActiveRequests.removeAt(i);
      request.cancel();
    } 
    for (i = this.mOnDestroyCallbacks.size() - 1; i >= 0; i--) {
      Runnable runnable = (Runnable)this.mOnDestroyCallbacks.keyAt(i);
      ((Executor)this.mOnDestroyCallbacks.valueAt(i)).execute(runnable);
      this.mOnDestroyCallbacks.removeAt(i);
    } 
    this.mInteractor = null;
    Activity activity = this.mActivity;
    if (activity != null)
      activity.setVoiceInteractor(null); 
  }
  
  void detachActivity() {
    ArrayList<Request> arrayList = makeRequestList();
    if (arrayList != null)
      for (byte b = 0; b < arrayList.size(); b++) {
        Request request = arrayList.get(b);
        request.onDetached();
        request.mActivity = null;
        request.mContext = null;
      }  
    if (!this.mRetaining) {
      ArrayList<Request> arrayList1 = makeRequestList();
      if (arrayList1 != null)
        for (byte b = 0; b < arrayList1.size(); b++)
          ((Request)arrayList1.get(b)).cancel();  
      this.mActiveRequests.clear();
    } 
    this.mContext = null;
    this.mActivity = null;
  }
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("    ");
    String str = stringBuilder.toString();
    if (this.mActiveRequests.size() > 0) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Active voice requests:");
      for (byte b = 0; b < this.mActiveRequests.size(); b++) {
        Request request = (Request)this.mActiveRequests.valueAt(b);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(b);
        paramPrintWriter.print(": ");
        paramPrintWriter.println(request);
        request.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      } 
    } 
    paramPrintWriter.print(paramString);
    paramPrintWriter.println("VoiceInteractor misc state:");
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mInteractor=");
    paramPrintWriter.println(this.mInteractor.asBinder());
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mActivity=");
    paramPrintWriter.println(this.mActivity);
  }
  
  public Request getActiveRequest(String paramString) {
    if (isDestroyed()) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return null;
    } 
    synchronized (this.mActiveRequests) {
      int i = this.mActiveRequests.size();
      for (byte b = 0; b < i; b++) {
        Request request = (Request)this.mActiveRequests.valueAt(b);
        if (paramString == request.getName() || (paramString != null && paramString.equals(request.getName())))
          return request; 
      } 
      return null;
    } 
  }
  
  public Request[] getActiveRequests() {
    if (isDestroyed()) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return null;
    } 
    synchronized (this.mActiveRequests) {
      int i = this.mActiveRequests.size();
      if (i <= 0)
        return NO_REQUESTS; 
      Request[] arrayOfRequest = new Request[i];
      for (byte b = 0; b < i; b++)
        arrayOfRequest[b] = (Request)this.mActiveRequests.valueAt(b); 
      return arrayOfRequest;
    } 
  }
  
  public boolean isDestroyed() {
    boolean bool;
    if (this.mInteractor == null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void notifyDirectActionsChanged() {
    if (isDestroyed()) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return;
    } 
    try {
      this.mInteractor.notifyDirectActionsChanged(this.mActivity.getTaskId(), this.mActivity.getAssistToken());
    } catch (RemoteException remoteException) {
      Log.w("VoiceInteractor", "Voice interactor has died", (Throwable)remoteException);
    } 
  }
  
  Request pullRequest(IVoiceInteractorRequest paramIVoiceInteractorRequest, boolean paramBoolean) {
    synchronized (this.mActiveRequests) {
      Request request = (Request)this.mActiveRequests.get(paramIVoiceInteractorRequest.asBinder());
      if (request != null && paramBoolean)
        this.mActiveRequests.remove(paramIVoiceInteractorRequest.asBinder()); 
      return request;
    } 
  }
  
  public boolean registerOnDestroyedCallback(Executor paramExecutor, Runnable paramRunnable) {
    Objects.requireNonNull(paramExecutor);
    Objects.requireNonNull(paramRunnable);
    if (isDestroyed()) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return false;
    } 
    this.mOnDestroyCallbacks.put(paramRunnable, paramExecutor);
    return true;
  }
  
  void retainInstance() {
    this.mRetaining = true;
  }
  
  public boolean submitRequest(Request paramRequest) {
    return submitRequest(paramRequest, null);
  }
  
  public boolean submitRequest(Request paramRequest, String paramString) {
    if (isDestroyed()) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return false;
    } 
    try {
      if (paramRequest.mRequestInterface == null) {
        IVoiceInteractorRequest iVoiceInteractorRequest = paramRequest.submit(this.mInteractor, this.mContext.getOpPackageName(), (IVoiceInteractorCallback)this.mCallback);
        paramRequest.mRequestInterface = iVoiceInteractorRequest;
        paramRequest.mContext = this.mContext;
        paramRequest.mActivity = this.mActivity;
        paramRequest.mName = paramString;
        synchronized (this.mActiveRequests) {
          this.mActiveRequests.put(iVoiceInteractorRequest.asBinder(), paramRequest);
          return true;
        } 
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Given ");
      stringBuilder.append(paramRequest);
      stringBuilder.append(" is already active");
      this(stringBuilder.toString());
      throw illegalStateException;
    } catch (RemoteException remoteException) {
      Log.w("VoiceInteractor", "Remove voice interactor service died", (Throwable)remoteException);
      return false;
    } 
  }
  
  public boolean[] supportsCommands(String[] paramArrayOfString) {
    if (isDestroyed()) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return new boolean[paramArrayOfString.length];
    } 
    try {
      return this.mInteractor.supportsCommands(this.mContext.getOpPackageName(), paramArrayOfString);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("Voice interactor has died", remoteException);
    } 
  }
  
  public boolean unregisterOnDestroyedCallback(Runnable paramRunnable) {
    Objects.requireNonNull(paramRunnable);
    boolean bool = isDestroyed();
    boolean bool1 = false;
    if (bool) {
      Log.w("VoiceInteractor", "Cannot interact with a destroyed voice interactor");
      return false;
    } 
    if (this.mOnDestroyCallbacks.remove(paramRunnable) != null)
      bool1 = true; 
    return bool1;
  }
  
  public static class AbortVoiceRequest extends Request {
    final Bundle mExtras;
    
    final VoiceInteractor.Prompt mPrompt;
    
    public AbortVoiceRequest(VoiceInteractor.Prompt param1Prompt, Bundle param1Bundle) {
      this.mPrompt = param1Prompt;
      this.mExtras = param1Bundle;
    }
    
    public AbortVoiceRequest(CharSequence param1CharSequence, Bundle param1Bundle) {
      if (param1CharSequence != null) {
        VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(param1CharSequence);
      } else {
        param1CharSequence = null;
      } 
      this.mPrompt = (VoiceInteractor.Prompt)param1CharSequence;
      this.mExtras = param1Bundle;
    }
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      super.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mPrompt=");
      param1PrintWriter.println(this.mPrompt);
      if (this.mExtras != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mExtras=");
        param1PrintWriter.println(this.mExtras);
      } 
    }
    
    String getRequestTypeName() {
      return "AbortVoice";
    }
    
    public void onAbortResult(Bundle param1Bundle) {}
    
    IVoiceInteractorRequest submit(IVoiceInteractor param1IVoiceInteractor, String param1String, IVoiceInteractorCallback param1IVoiceInteractorCallback) throws RemoteException {
      return param1IVoiceInteractor.startAbortVoice(param1String, param1IVoiceInteractorCallback, this.mPrompt, this.mExtras);
    }
  }
  
  public static class CommandRequest extends Request {
    final Bundle mArgs;
    
    final String mCommand;
    
    public CommandRequest(String param1String, Bundle param1Bundle) {
      this.mCommand = param1String;
      this.mArgs = param1Bundle;
    }
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      super.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mCommand=");
      param1PrintWriter.println(this.mCommand);
      if (this.mArgs != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mArgs=");
        param1PrintWriter.println(this.mArgs);
      } 
    }
    
    String getRequestTypeName() {
      return "Command";
    }
    
    public void onCommandResult(boolean param1Boolean, Bundle param1Bundle) {}
    
    IVoiceInteractorRequest submit(IVoiceInteractor param1IVoiceInteractor, String param1String, IVoiceInteractorCallback param1IVoiceInteractorCallback) throws RemoteException {
      return param1IVoiceInteractor.startCommand(param1String, param1IVoiceInteractorCallback, this.mCommand, this.mArgs);
    }
  }
  
  public static class CompleteVoiceRequest extends Request {
    final Bundle mExtras;
    
    final VoiceInteractor.Prompt mPrompt;
    
    public CompleteVoiceRequest(VoiceInteractor.Prompt param1Prompt, Bundle param1Bundle) {
      this.mPrompt = param1Prompt;
      this.mExtras = param1Bundle;
    }
    
    public CompleteVoiceRequest(CharSequence param1CharSequence, Bundle param1Bundle) {
      if (param1CharSequence != null) {
        VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(param1CharSequence);
      } else {
        param1CharSequence = null;
      } 
      this.mPrompt = (VoiceInteractor.Prompt)param1CharSequence;
      this.mExtras = param1Bundle;
    }
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      super.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mPrompt=");
      param1PrintWriter.println(this.mPrompt);
      if (this.mExtras != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mExtras=");
        param1PrintWriter.println(this.mExtras);
      } 
    }
    
    String getRequestTypeName() {
      return "CompleteVoice";
    }
    
    public void onCompleteResult(Bundle param1Bundle) {}
    
    IVoiceInteractorRequest submit(IVoiceInteractor param1IVoiceInteractor, String param1String, IVoiceInteractorCallback param1IVoiceInteractorCallback) throws RemoteException {
      return param1IVoiceInteractor.startCompleteVoice(param1String, param1IVoiceInteractorCallback, this.mPrompt, this.mExtras);
    }
  }
  
  public static class ConfirmationRequest extends Request {
    final Bundle mExtras;
    
    final VoiceInteractor.Prompt mPrompt;
    
    public ConfirmationRequest(VoiceInteractor.Prompt param1Prompt, Bundle param1Bundle) {
      this.mPrompt = param1Prompt;
      this.mExtras = param1Bundle;
    }
    
    public ConfirmationRequest(CharSequence param1CharSequence, Bundle param1Bundle) {
      if (param1CharSequence != null) {
        VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(param1CharSequence);
      } else {
        param1CharSequence = null;
      } 
      this.mPrompt = (VoiceInteractor.Prompt)param1CharSequence;
      this.mExtras = param1Bundle;
    }
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      super.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mPrompt=");
      param1PrintWriter.println(this.mPrompt);
      if (this.mExtras != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mExtras=");
        param1PrintWriter.println(this.mExtras);
      } 
    }
    
    String getRequestTypeName() {
      return "Confirmation";
    }
    
    public void onConfirmationResult(boolean param1Boolean, Bundle param1Bundle) {}
    
    IVoiceInteractorRequest submit(IVoiceInteractor param1IVoiceInteractor, String param1String, IVoiceInteractorCallback param1IVoiceInteractorCallback) throws RemoteException {
      return param1IVoiceInteractor.startConfirmation(param1String, param1IVoiceInteractorCallback, this.mPrompt, this.mExtras);
    }
  }
  
  private static final class KillCallback extends ICancellationSignal.Stub {
    private final WeakReference<VoiceInteractor> mInteractor;
    
    KillCallback(VoiceInteractor param1VoiceInteractor) {
      this.mInteractor = new WeakReference<>(param1VoiceInteractor);
    }
    
    public void cancel() {
      VoiceInteractor voiceInteractor = this.mInteractor.get();
      if (voiceInteractor != null)
        voiceInteractor.mHandlerCaller.getHandler().sendMessage(PooledLambda.obtainMessage((Consumer)_$$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k.INSTANCE, voiceInteractor)); 
    }
  }
  
  public static class PickOptionRequest extends Request {
    final Bundle mExtras;
    
    final Option[] mOptions;
    
    final VoiceInteractor.Prompt mPrompt;
    
    public PickOptionRequest(VoiceInteractor.Prompt param1Prompt, Option[] param1ArrayOfOption, Bundle param1Bundle) {
      this.mPrompt = param1Prompt;
      this.mOptions = param1ArrayOfOption;
      this.mExtras = param1Bundle;
    }
    
    public PickOptionRequest(CharSequence param1CharSequence, Option[] param1ArrayOfOption, Bundle param1Bundle) {
      if (param1CharSequence != null) {
        VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(param1CharSequence);
      } else {
        param1CharSequence = null;
      } 
      this.mPrompt = (VoiceInteractor.Prompt)param1CharSequence;
      this.mOptions = param1ArrayOfOption;
      this.mExtras = param1Bundle;
    }
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      super.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mPrompt=");
      param1PrintWriter.println(this.mPrompt);
      if (this.mOptions != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.println("Options:");
        byte b = 0;
        while (true) {
          Option[] arrayOfOption = this.mOptions;
          if (b < arrayOfOption.length) {
            Option option = arrayOfOption[b];
            param1PrintWriter.print(param1String);
            param1PrintWriter.print("  #");
            param1PrintWriter.print(b);
            param1PrintWriter.println(":");
            param1PrintWriter.print(param1String);
            param1PrintWriter.print("    mLabel=");
            param1PrintWriter.println(option.mLabel);
            param1PrintWriter.print(param1String);
            param1PrintWriter.print("    mIndex=");
            param1PrintWriter.println(option.mIndex);
            if (option.mSynonyms != null && option.mSynonyms.size() > 0) {
              param1PrintWriter.print(param1String);
              param1PrintWriter.println("    Synonyms:");
              for (byte b1 = 0; b1 < option.mSynonyms.size(); b1++) {
                param1PrintWriter.print(param1String);
                param1PrintWriter.print("      #");
                param1PrintWriter.print(b1);
                param1PrintWriter.print(": ");
                param1PrintWriter.println(option.mSynonyms.get(b1));
              } 
            } 
            if (option.mExtras != null) {
              param1PrintWriter.print(param1String);
              param1PrintWriter.print("    mExtras=");
              param1PrintWriter.println(option.mExtras);
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
      if (this.mExtras != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mExtras=");
        param1PrintWriter.println(this.mExtras);
      } 
    }
    
    String getRequestTypeName() {
      return "PickOption";
    }
    
    public void onPickOptionResult(boolean param1Boolean, Option[] param1ArrayOfOption, Bundle param1Bundle) {}
    
    IVoiceInteractorRequest submit(IVoiceInteractor param1IVoiceInteractor, String param1String, IVoiceInteractorCallback param1IVoiceInteractorCallback) throws RemoteException {
      return param1IVoiceInteractor.startPickOption(param1String, param1IVoiceInteractorCallback, this.mPrompt, this.mOptions, this.mExtras);
    }
    
    public static final class Option implements Parcelable {
      public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
          public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel param3Parcel) {
            return new VoiceInteractor.PickOptionRequest.Option(param3Parcel);
          }
          
          public VoiceInteractor.PickOptionRequest.Option[] newArray(int param3Int) {
            return new VoiceInteractor.PickOptionRequest.Option[param3Int];
          }
        };
      
      Bundle mExtras;
      
      final int mIndex;
      
      final CharSequence mLabel;
      
      ArrayList<CharSequence> mSynonyms;
      
      Option(Parcel param2Parcel) {
        this.mLabel = param2Parcel.readCharSequence();
        this.mIndex = param2Parcel.readInt();
        this.mSynonyms = param2Parcel.readCharSequenceList();
        this.mExtras = param2Parcel.readBundle();
      }
      
      public Option(CharSequence param2CharSequence) {
        this.mLabel = param2CharSequence;
        this.mIndex = -1;
      }
      
      public Option(CharSequence param2CharSequence, int param2Int) {
        this.mLabel = param2CharSequence;
        this.mIndex = param2Int;
      }
      
      public Option addSynonym(CharSequence param2CharSequence) {
        if (this.mSynonyms == null)
          this.mSynonyms = new ArrayList<>(); 
        this.mSynonyms.add(param2CharSequence);
        return this;
      }
      
      public int countSynonyms() {
        boolean bool;
        ArrayList<CharSequence> arrayList = this.mSynonyms;
        if (arrayList != null) {
          bool = arrayList.size();
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public int describeContents() {
        return 0;
      }
      
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      public int getIndex() {
        return this.mIndex;
      }
      
      public CharSequence getLabel() {
        return this.mLabel;
      }
      
      public CharSequence getSynonymAt(int param2Int) {
        ArrayList<CharSequence> arrayList = this.mSynonyms;
        if (arrayList != null) {
          CharSequence charSequence = arrayList.get(param2Int);
        } else {
          arrayList = null;
        } 
        return (CharSequence)arrayList;
      }
      
      public void setExtras(Bundle param2Bundle) {
        this.mExtras = param2Bundle;
      }
      
      public void writeToParcel(Parcel param2Parcel, int param2Int) {
        param2Parcel.writeCharSequence(this.mLabel);
        param2Parcel.writeInt(this.mIndex);
        param2Parcel.writeCharSequenceList(this.mSynonyms);
        param2Parcel.writeBundle(this.mExtras);
      }
    }
    
    class null implements Parcelable.Creator<Option> {
      public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel param2Parcel) {
        return new VoiceInteractor.PickOptionRequest.Option(param2Parcel);
      }
      
      public VoiceInteractor.PickOptionRequest.Option[] newArray(int param2Int) {
        return new VoiceInteractor.PickOptionRequest.Option[param2Int];
      }
    }
  }
  
  public static final class Option implements Parcelable {
    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
        public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel param3Parcel) {
          return new VoiceInteractor.PickOptionRequest.Option(param3Parcel);
        }
        
        public VoiceInteractor.PickOptionRequest.Option[] newArray(int param3Int) {
          return new VoiceInteractor.PickOptionRequest.Option[param3Int];
        }
      };
    
    Bundle mExtras;
    
    final int mIndex;
    
    final CharSequence mLabel;
    
    ArrayList<CharSequence> mSynonyms;
    
    Option(Parcel param1Parcel) {
      this.mLabel = param1Parcel.readCharSequence();
      this.mIndex = param1Parcel.readInt();
      this.mSynonyms = param1Parcel.readCharSequenceList();
      this.mExtras = param1Parcel.readBundle();
    }
    
    public Option(CharSequence param1CharSequence) {
      this.mLabel = param1CharSequence;
      this.mIndex = -1;
    }
    
    public Option(CharSequence param1CharSequence, int param1Int) {
      this.mLabel = param1CharSequence;
      this.mIndex = param1Int;
    }
    
    public Option addSynonym(CharSequence param1CharSequence) {
      if (this.mSynonyms == null)
        this.mSynonyms = new ArrayList<>(); 
      this.mSynonyms.add(param1CharSequence);
      return this;
    }
    
    public int countSynonyms() {
      boolean bool;
      ArrayList<CharSequence> arrayList = this.mSynonyms;
      if (arrayList != null) {
        bool = arrayList.size();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public int getIndex() {
      return this.mIndex;
    }
    
    public CharSequence getLabel() {
      return this.mLabel;
    }
    
    public CharSequence getSynonymAt(int param1Int) {
      ArrayList<CharSequence> arrayList = this.mSynonyms;
      if (arrayList != null) {
        CharSequence charSequence = arrayList.get(param1Int);
      } else {
        arrayList = null;
      } 
      return (CharSequence)arrayList;
    }
    
    public void setExtras(Bundle param1Bundle) {
      this.mExtras = param1Bundle;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeCharSequence(this.mLabel);
      param1Parcel.writeInt(this.mIndex);
      param1Parcel.writeCharSequenceList(this.mSynonyms);
      param1Parcel.writeBundle(this.mExtras);
    }
  }
  
  class null implements Parcelable.Creator<PickOptionRequest.Option> {
    public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel param1Parcel) {
      return new VoiceInteractor.PickOptionRequest.Option(param1Parcel);
    }
    
    public VoiceInteractor.PickOptionRequest.Option[] newArray(int param1Int) {
      return new VoiceInteractor.PickOptionRequest.Option[param1Int];
    }
  }
  
  public static class Prompt implements Parcelable {
    public static final Parcelable.Creator<Prompt> CREATOR = new Parcelable.Creator<Prompt>() {
        public VoiceInteractor.Prompt createFromParcel(Parcel param2Parcel) {
          return new VoiceInteractor.Prompt(param2Parcel);
        }
        
        public VoiceInteractor.Prompt[] newArray(int param2Int) {
          return new VoiceInteractor.Prompt[param2Int];
        }
      };
    
    private final CharSequence mVisualPrompt;
    
    private final CharSequence[] mVoicePrompts;
    
    Prompt(Parcel param1Parcel) {
      this.mVoicePrompts = param1Parcel.readCharSequenceArray();
      this.mVisualPrompt = param1Parcel.readCharSequence();
    }
    
    public Prompt(CharSequence param1CharSequence) {
      this.mVoicePrompts = new CharSequence[] { param1CharSequence };
      this.mVisualPrompt = param1CharSequence;
    }
    
    public Prompt(CharSequence[] param1ArrayOfCharSequence, CharSequence param1CharSequence) {
      if (param1ArrayOfCharSequence != null) {
        if (param1ArrayOfCharSequence.length != 0) {
          if (param1CharSequence != null) {
            this.mVoicePrompts = param1ArrayOfCharSequence;
            this.mVisualPrompt = param1CharSequence;
            return;
          } 
          throw new NullPointerException("visualPrompt must not be null");
        } 
        throw new IllegalArgumentException("voicePrompts must not be empty");
      } 
      throw new NullPointerException("voicePrompts must not be null");
    }
    
    public int countVoicePrompts() {
      return this.mVoicePrompts.length;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public CharSequence getVisualPrompt() {
      return this.mVisualPrompt;
    }
    
    public CharSequence getVoicePromptAt(int param1Int) {
      return this.mVoicePrompts[param1Int];
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(128);
      DebugUtils.buildShortClassTag(this, stringBuilder);
      CharSequence charSequence = this.mVisualPrompt;
      if (charSequence != null) {
        CharSequence[] arrayOfCharSequence = this.mVoicePrompts;
        if (arrayOfCharSequence != null && arrayOfCharSequence.length == 1 && charSequence.equals(arrayOfCharSequence[0])) {
          stringBuilder.append(" ");
          stringBuilder.append(this.mVisualPrompt);
          stringBuilder.append('}');
          return stringBuilder.toString();
        } 
      } 
      if (this.mVisualPrompt != null) {
        stringBuilder.append(" visual=");
        stringBuilder.append(this.mVisualPrompt);
      } 
      if (this.mVoicePrompts != null) {
        stringBuilder.append(", voice=");
        for (byte b = 0; b < this.mVoicePrompts.length; b++) {
          if (b > 0)
            stringBuilder.append(" | "); 
          stringBuilder.append(this.mVoicePrompts[b]);
        } 
      } 
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeCharSequenceArray(this.mVoicePrompts);
      param1Parcel.writeCharSequence(this.mVisualPrompt);
    }
  }
  
  class null implements Parcelable.Creator<Prompt> {
    public VoiceInteractor.Prompt createFromParcel(Parcel param1Parcel) {
      return new VoiceInteractor.Prompt(param1Parcel);
    }
    
    public VoiceInteractor.Prompt[] newArray(int param1Int) {
      return new VoiceInteractor.Prompt[param1Int];
    }
  }
  
  public static abstract class Request {
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
    
    void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mRequestInterface=");
      param1PrintWriter.println(this.mRequestInterface.asBinder());
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mActivity=");
      param1PrintWriter.println(this.mActivity);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mName=");
      param1PrintWriter.println(this.mName);
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
    
    public void onAttached(Activity param1Activity) {}
    
    public void onCancel() {}
    
    public void onDetached() {}
    
    abstract IVoiceInteractorRequest submit(IVoiceInteractor param1IVoiceInteractor, String param1String, IVoiceInteractorCallback param1IVoiceInteractorCallback) throws RemoteException;
    
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */