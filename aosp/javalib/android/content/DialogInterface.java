package android.content;

import android.view.KeyEvent;

public interface DialogInterface {
  @Deprecated
  public static final int BUTTON1 = -1;
  
  @Deprecated
  public static final int BUTTON2 = -2;
  
  @Deprecated
  public static final int BUTTON3 = -3;
  
  public static final int BUTTON_NEGATIVE = -2;
  
  public static final int BUTTON_NEUTRAL = -3;
  
  public static final int BUTTON_POSITIVE = -1;
  
  void cancel();
  
  void dismiss();
  
  public static interface OnCancelListener {
    void onCancel(DialogInterface param1DialogInterface);
  }
  
  public static interface OnClickListener {
    void onClick(DialogInterface param1DialogInterface, int param1Int);
  }
  
  public static interface OnDismissListener {
    void onDismiss(DialogInterface param1DialogInterface);
  }
  
  public static interface OnKeyListener {
    boolean onKey(DialogInterface param1DialogInterface, int param1Int, KeyEvent param1KeyEvent);
  }
  
  public static interface OnMultiChoiceClickListener {
    void onClick(DialogInterface param1DialogInterface, int param1Int, boolean param1Boolean);
  }
  
  public static interface OnShowListener {
    void onShow(DialogInterface param1DialogInterface);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/DialogInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */