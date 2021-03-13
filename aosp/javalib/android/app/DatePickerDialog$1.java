package android.app;

import android.widget.Button;
import android.widget.DatePicker;

class null implements DatePicker.ValidationCallback {
  public void onValidationChanged(boolean paramBoolean) {
    Button button = DatePickerDialog.this.getButton(-1);
    if (button != null)
      button.setEnabled(paramBoolean); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DatePickerDialog$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */