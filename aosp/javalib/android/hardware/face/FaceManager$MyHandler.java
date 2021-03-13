package android.hardware.face;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Trace;
import android.util.Log;
import com.android.internal.os.SomeArgs;

class MyHandler extends Handler {
  private MyHandler(Context paramContext) {
    super(paramContext.getMainLooper());
  }
  
  private MyHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    SomeArgs someArgs;
    FaceManager faceManager;
    Face face;
    int i;
    int j;
    boolean bool;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FaceManager#handleMessage: ");
    stringBuilder.append(Integer.toString(paramMessage.what));
    Trace.beginSection(stringBuilder.toString());
    switch (paramMessage.what) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown message: ");
        stringBuilder.append(paramMessage.what);
        Log.w("FaceManager", stringBuilder.toString());
        break;
      case 107:
        FaceManager.access$1200(FaceManager.this, ((Boolean)paramMessage.obj).booleanValue(), paramMessage.arg1);
        break;
      case 106:
        someArgs = (SomeArgs)paramMessage.obj;
        FaceManager.access$1300(FaceManager.this, ((Boolean)someArgs.arg1).booleanValue(), someArgs.argi1, ((Boolean)someArgs.arg2).booleanValue());
        someArgs.recycle();
        break;
      case 105:
        FaceManager.access$1100(FaceManager.this, (Face)((Message)someArgs).obj, ((Message)someArgs).arg1);
        break;
      case 104:
        FaceManager.access$1000(FaceManager.this, ((Long)((Message)someArgs).obj).longValue(), ((Message)someArgs).arg1, ((Message)someArgs).arg2);
        break;
      case 103:
        FaceManager.access$900(FaceManager.this);
        break;
      case 102:
        faceManager = FaceManager.this;
        face = (Face)((Message)someArgs).obj;
        i = ((Message)someArgs).arg1;
        j = ((Message)someArgs).arg2;
        bool = true;
        if (j != 1)
          bool = false; 
        FaceManager.access$800(faceManager, face, i, bool);
        break;
      case 101:
        FaceManager.access$700(FaceManager.this, ((Long)((Message)someArgs).obj).longValue(), ((Message)someArgs).arg1, ((Message)someArgs).arg2);
        break;
      case 100:
        FaceManager.access$600(FaceManager.this, (Face)((Message)someArgs).obj, ((Message)someArgs).arg1);
        break;
    } 
    Trace.endSection();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$MyHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */