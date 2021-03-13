package android.app;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.WindowManagerImpl;

class null extends ContextThemeWrapper {
  null(Context paramContext, int paramInt) {
    super(paramContext, paramInt);
  }
  
  public Object getSystemService(String paramString) {
    return "window".equals(paramString) ? displayWindowManager : super.getSystemService(paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Presentation$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */