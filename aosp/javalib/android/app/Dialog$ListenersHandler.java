package android.app;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

final class ListenersHandler extends Handler {
  private final WeakReference<DialogInterface> mDialog;
  
  public ListenersHandler(Dialog paramDialog) {
    this.mDialog = new WeakReference<>(paramDialog);
  }
  
  public void handleMessage(Message paramMessage) {
    switch (paramMessage.what) {
      default:
        return;
      case 69:
        ((DialogInterface.OnShowListener)paramMessage.obj).onShow(this.mDialog.get());
      case 68:
        ((DialogInterface.OnCancelListener)paramMessage.obj).onCancel(this.mDialog.get());
      case 67:
        break;
    } 
    ((DialogInterface.OnDismissListener)paramMessage.obj).onDismiss(this.mDialog.get());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Dialog$ListenersHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */