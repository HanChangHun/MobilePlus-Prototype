package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.IRemoteCallback;
import android.util.Slog;
import com.android.internal.os.SomeArgs;

class null extends IInstantAppResolver.Stub {
  public void getInstantAppIntentFilterList(InstantAppRequestInfo paramInstantAppRequestInfo, IRemoteCallback paramIRemoteCallback) {
    if (InstantAppResolverService.access$000()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("[");
      stringBuilder.append(paramInstantAppRequestInfo.getToken());
      stringBuilder.append("] Phase2 called; posting");
      Slog.v("PackageManager", stringBuilder.toString());
    } 
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = paramInstantAppRequestInfo;
    someArgs.arg2 = paramIRemoteCallback;
    InstantAppResolverService.this.mHandler.obtainMessage(2, someArgs).sendToTarget();
  }
  
  public void getInstantAppResolveInfoList(InstantAppRequestInfo paramInstantAppRequestInfo, int paramInt, IRemoteCallback paramIRemoteCallback) {
    if (InstantAppResolverService.access$000()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("[");
      stringBuilder.append(paramInstantAppRequestInfo.getToken());
      stringBuilder.append("] Phase1 called; posting");
      Slog.v("PackageManager", stringBuilder.toString());
    } 
    SomeArgs someArgs = SomeArgs.obtain();
    someArgs.arg1 = paramInstantAppRequestInfo;
    someArgs.arg2 = paramIRemoteCallback;
    InstantAppResolverService.this.mHandler.obtainMessage(1, paramInt, 0, someArgs).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/InstantAppResolverService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */