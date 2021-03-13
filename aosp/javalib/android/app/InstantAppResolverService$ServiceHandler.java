package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.Handler;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Message;
import android.util.Slog;
import com.android.internal.os.SomeArgs;
import java.util.Arrays;

final class ServiceHandler extends Handler {
  public static final int MSG_GET_INSTANT_APP_INTENT_FILTER = 2;
  
  public static final int MSG_GET_INSTANT_APP_RESOLVE_INFO = 1;
  
  public ServiceHandler(Looper paramLooper) {
    super(paramLooper, null, true);
  }
  
  public void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    int i = paramMessage.what;
    if (i != 1) {
      if (i == 2) {
        SomeArgs someArgs = (SomeArgs)paramMessage.obj;
        InstantAppRequestInfo instantAppRequestInfo = (InstantAppRequestInfo)someArgs.arg1;
        IRemoteCallback iRemoteCallback = (IRemoteCallback)someArgs.arg2;
        someArgs.recycle();
        if (InstantAppResolverService.access$000()) {
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
      if (InstantAppResolverService.access$000()) {
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


/* Location:              /home/chun/Desktop/temp/!/android/app/InstantAppResolverService$ServiceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */