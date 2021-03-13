package android.app;

import android.view.View;

class null implements View.OnClickListener {
  public void onClick(View paramView) {
    if (TimePickerDialog.access$000(TimePickerDialog.this).validateInput()) {
      TimePickerDialog timePickerDialog = TimePickerDialog.this;
      timePickerDialog.onClick(timePickerDialog, -1);
      TimePickerDialog.access$000(TimePickerDialog.this).clearFocus();
      TimePickerDialog.this.dismiss();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/TimePickerDialog$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */