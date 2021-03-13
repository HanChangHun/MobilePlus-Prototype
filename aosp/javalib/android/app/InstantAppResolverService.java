package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.InstantAppRequestInfo;
import android.content.pm.InstantAppResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.util.Slog;
import com.android.internal.os.SomeArgs;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SystemApi
public abstract class InstantAppResolverService extends Service {
  private static final boolean DEBUG_INSTANT = Build.IS_DEBUGGABLE;
  
  public static final String EXTRA_RESOLVE_INFO = "android.app.extra.RESOLVE_INFO";
  
  public static final String EXTRA_SEQUENCE = "android.app.extra.SEQUENCE";
  
  private static final String TAG = "PackageManager";
  
  Handler mHandler;
  
  public final void attachBaseContext(Context paramContext) {
    super.attachBaseContext(paramContext);
    this.mHandler = new ServiceHandler(getLooper());
  }
  
  Looper getLooper() {
    return getBaseContext().getMainLooper();
  }
  
  public final IBinder onBind(Intent paramIntent) {
    return (IBinder)new IInstantAppResolver.Stub() {
        public void getInstantAppIntentFilterList(InstantAppRequestInfo param1InstantAppRequestInfo, IRemoteCallback param1IRemoteCallback) {
          if (InstantAppResolverService.DEBUG_INSTANT) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(param1InstantAppRequestInfo.getToken());
            stringBuilder.append("] Phase2 called; posting");
            Slog.v("PackageManager", stringBuilder.toString());
          } 
          SomeArgs someArgs = SomeArgs.obtain();
          someArgs.arg1 = param1InstantAppRequestInfo;
          someArgs.arg2 = param1IRemoteCallback;
          InstantAppResolverService.this.mHandler.obtainMessage(2, someArgs).sendToTarget();
        }
        
        public void getInstantAppResolveInfoList(InstantAppRequestInfo param1InstantAppRequestInfo, int param1Int, IRemoteCallback param1IRemoteCallback) {
          if (InstantAppResolverService.DEBUG_INSTANT) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(param1InstantAppRequestInfo.getToken());
            stringBuilder.append("] Phase1 called; posting");
            Slog.v("PackageManager", stringBuilder.toString());
          } 
          SomeArgs someArgs = SomeArgs.obtain();
          someArgs.arg1 = param1InstantAppRequestInfo;
          someArgs.arg2 = param1IRemoteCallback;
          InstantAppResolverService.this.mHandler.obtainMessage(1, param1Int, 0, someArgs).sendToTarget();
        }
      };
  }
  
  @Deprecated
  public void onGetInstantAppIntentFilter(Intent paramIntent, int[] paramArrayOfint, UserHandle paramUserHandle, String paramString, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    onGetInstantAppIntentFilter(paramIntent, paramArrayOfint, paramString, paramInstantAppResolutionCallback);
  }
  
  @Deprecated
  public void onGetInstantAppIntentFilter(Intent paramIntent, int[] paramArrayOfint, String paramString, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    Log.e("PackageManager", "New onGetInstantAppIntentFilter is not overridden");
    if (paramIntent.isWebIntent()) {
      onGetInstantAppIntentFilter(paramArrayOfint, paramString, paramInstantAppResolutionCallback);
    } else {
      paramInstantAppResolutionCallback.onInstantAppResolveInfo(Collections.emptyList());
    } 
  }
  
  public void onGetInstantAppIntentFilter(InstantAppRequestInfo paramInstantAppRequestInfo, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    onGetInstantAppIntentFilter(paramInstantAppRequestInfo.getIntent(), paramInstantAppRequestInfo.getHostDigestPrefix(), paramInstantAppRequestInfo.getUserHandle(), paramInstantAppRequestInfo.getToken(), paramInstantAppResolutionCallback);
  }
  
  @Deprecated
  public void onGetInstantAppIntentFilter(int[] paramArrayOfint, String paramString, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    throw new IllegalStateException("Must define onGetInstantAppIntentFilter");
  }
  
  @Deprecated
  public void onGetInstantAppResolveInfo(Intent paramIntent, int[] paramArrayOfint, UserHandle paramUserHandle, String paramString, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    onGetInstantAppResolveInfo(paramIntent, paramArrayOfint, paramString, paramInstantAppResolutionCallback);
  }
  
  @Deprecated
  public void onGetInstantAppResolveInfo(Intent paramIntent, int[] paramArrayOfint, String paramString, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    if (paramIntent.isWebIntent()) {
      onGetInstantAppResolveInfo(paramArrayOfint, paramString, paramInstantAppResolutionCallback);
    } else {
      paramInstantAppResolutionCallback.onInstantAppResolveInfo(Collections.emptyList());
    } 
  }
  
  public void onGetInstantAppResolveInfo(InstantAppRequestInfo paramInstantAppRequestInfo, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    onGetInstantAppResolveInfo(paramInstantAppRequestInfo.getIntent(), paramInstantAppRequestInfo.getHostDigestPrefix(), paramInstantAppRequestInfo.getUserHandle(), paramInstantAppRequestInfo.getToken(), paramInstantAppResolutionCallback);
  }
  
  @Deprecated
  public void onGetInstantAppResolveInfo(int[] paramArrayOfint, String paramString, InstantAppResolutionCallback paramInstantAppResolutionCallback) {
    throw new IllegalStateException("Must define onGetInstantAppResolveInfo");
  }
  
  public static final class InstantAppResolutionCallback {
    private final IRemoteCallback mCallback;
    
    private final int mSequence;
    
    public InstantAppResolutionCallback(int param1Int, IRemoteCallback param1IRemoteCallback) {
      this.mCallback = param1IRemoteCallback;
      this.mSequence = param1Int;
    }
    
    public void onInstantAppResolveInfo(List<InstantAppResolveInfo> param1List) {
      Bundle bundle = new Bundle();
      bundle.putParcelableList("android.app.extra.RESOLVE_INFO", param1List);
      bundle.putInt("android.app.extra.SEQUENCE", this.mSequence);
      try {
        this.mCallback.sendResult(bundle);
      } catch (RemoteException remoteException) {}
    }
  }
  
  private final class ServiceHandler extends Handler {
    public static final int MSG_GET_INSTANT_APP_INTENT_FILTER = 2;
    
    public static final int MSG_GET_INSTANT_APP_RESOLVE_INFO = 1;
    
    public ServiceHandler(Looper param1Looper) {
      super(param1Looper, null, true);
    }
    
    public void handleMessage(Message param1Message) {
      StringBuilder stringBuilder;
      int i = param1Message.what;
      if (i != 1) {
        if (i == 2) {
          SomeArgs someArgs = (SomeArgs)param1Message.obj;
          InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)someArgs.arg1;
          IRemoteCallback iRemoteCallback = (IRemoteCallback)someArgs.arg2;
          someArgs.recycle();
          if (InstantAppResolverService.DEBUG_INSTANT) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("[");
            stringBuilder1.append(instantAppRequestInfo.getToken());
            stringBuilder1.append("] Phase2 request; prefix: ");
            stringBuilder1.append(Arrays.toString(instantAppRequestInfo.getHostDigestPrefix()));
            stringBuilder1.append(", userId: ");
            stringBuilder1.append(instantAppRequestInfo.getUserHandle().getIdentifier());
            Slog.d("PackageManager", stringBuilder1.toString());
          } 
          InstantAppResolverService.this.onGetInstantAppIntentFilter(instantAppRequestInfo, new InstantAppResolverService.InstantAppResolutionCallback(-1, iRemoteCallback));
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown message: ");
          stringBuilder.append(i);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        SomeArgs someArgs = (SomeArgs)((Message)stringBuilder).obj;
        InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)someArgs.arg1;
        IRemoteCallback iRemoteCallback = (IRemoteCallback)someArgs.arg2;
        someArgs.recycle();
        i = ((Message)stringBuilder).arg1;
        if (InstantAppResolverService.DEBUG_INSTANT) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("[");
          stringBuilder.append(instantAppRequestInfo.getToken());
          stringBuilder.append("] Phase1 request; prefix: ");
          stringBuilder.append(Arrays.toString(instantAppRequestInfo.getHostDigestPrefix()));
          stringBuilder.append(", userId: ");
          stringBuilder.append(instantAppRequestInfo.getUserHandle().getIdentifier());
          Slog.d("PackageManager", stringBuilder.toString());
        } 
        InstantAppResolverService.this.onGetInstantAppResolveInfo(instantAppRequestInfo, new InstantAppResolverService.InstantAppResolutionCallback(i, iRemoteCallback));
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/InstantAppResolverService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */