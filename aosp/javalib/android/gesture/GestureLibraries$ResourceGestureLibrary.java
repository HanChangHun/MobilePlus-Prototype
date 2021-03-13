package android.gesture;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

class ResourceGestureLibrary extends GestureLibrary {
  private final WeakReference<Context> mContext;
  
  private final int mResourceId;
  
  public ResourceGestureLibrary(Context paramContext, int paramInt) {
    this.mContext = new WeakReference<>(paramContext);
    this.mResourceId = paramInt;
  }
  
  public boolean isReadOnly() {
    return true;
  }
  
  public boolean load() {
    boolean bool1 = false;
    Context context = this.mContext.get();
    boolean bool2 = bool1;
    if (context != null) {
      InputStream inputStream = context.getResources().openRawResource(this.mResourceId);
      try {
        this.mStore.load(inputStream, true);
        bool2 = true;
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not load the gesture library from raw resource ");
        stringBuilder.append(context.getResources().getResourceName(this.mResourceId));
        Log.d("Gestures", stringBuilder.toString(), iOException);
        bool2 = bool1;
      } 
    } 
    return bool2;
  }
  
  public boolean save() {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureLibraries$ResourceGestureLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */