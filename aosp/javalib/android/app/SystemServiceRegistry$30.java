package android.app;

import android.view.inputmethod.InputMethodManager;

class null implements SystemServiceRegistry.ServiceFetcher<InputMethodManager> {
  public InputMethodManager getService(ContextImpl paramContextImpl) {
    return InputMethodManager.forContext(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$30.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */