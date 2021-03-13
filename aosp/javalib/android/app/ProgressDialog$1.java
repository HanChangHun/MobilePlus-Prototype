package android.app;

import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.StyleSpan;

class null extends Handler {
  public void handleMessage(Message paramMessage) {
    super.handleMessage(paramMessage);
    int i = ProgressDialog.access$000(ProgressDialog.this).getProgress();
    int j = ProgressDialog.access$000(ProgressDialog.this).getMax();
    if (ProgressDialog.access$100(ProgressDialog.this) != null) {
      String str = ProgressDialog.access$100(ProgressDialog.this);
      ProgressDialog.access$200(ProgressDialog.this).setText(String.format(str, new Object[] { Integer.valueOf(i), Integer.valueOf(j) }));
    } else {
      ProgressDialog.access$200(ProgressDialog.this).setText("");
    } 
    if (ProgressDialog.access$300(ProgressDialog.this) != null) {
      double d = i / j;
      SpannableString spannableString = new SpannableString(ProgressDialog.access$300(ProgressDialog.this).format(d));
      spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
      ProgressDialog.access$400(ProgressDialog.this).setText((CharSequence)spannableString);
    } else {
      ProgressDialog.access$400(ProgressDialog.this).setText("");
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ProgressDialog$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */