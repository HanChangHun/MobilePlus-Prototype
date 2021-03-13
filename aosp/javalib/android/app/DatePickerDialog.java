package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;

public class DatePickerDialog extends AlertDialog implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener {
  private static final String DAY = "day";
  
  private static final String MONTH = "month";
  
  private static final String YEAR = "year";
  
  private final DatePicker mDatePicker;
  
  private OnDateSetListener mDateSetListener;
  
  private final DatePicker.ValidationCallback mValidationCallback = new DatePicker.ValidationCallback() {
      public void onValidationChanged(boolean param1Boolean) {
        Button button = DatePickerDialog.this.getButton(-1);
        if (button != null)
          button.setEnabled(param1Boolean); 
      }
    };
  
  public DatePickerDialog(Context paramContext) {
    this(paramContext, 0, (OnDateSetListener)null, Calendar.getInstance(), -1, -1, -1);
  }
  
  public DatePickerDialog(Context paramContext, int paramInt) {
    this(paramContext, paramInt, (OnDateSetListener)null, Calendar.getInstance(), -1, -1, -1);
  }
  
  public DatePickerDialog(Context paramContext, int paramInt1, OnDateSetListener paramOnDateSetListener, int paramInt2, int paramInt3, int paramInt4) {
    this(paramContext, paramInt1, paramOnDateSetListener, (Calendar)null, paramInt2, paramInt3, paramInt4);
  }
  
  private DatePickerDialog(Context paramContext, int paramInt1, OnDateSetListener paramOnDateSetListener, Calendar paramCalendar, int paramInt2, int paramInt3, int paramInt4) {
    super(paramContext, resolveDialogTheme(paramContext, paramInt1));
    Context context = getContext();
    View view = LayoutInflater.from(context).inflate(17367138, null);
    setView(view);
    setButton(-1, context.getString(17039370), this);
    setButton(-2, context.getString(17039360), this);
    setButtonPanelLayoutHint(1);
    if (paramCalendar != null) {
      paramInt2 = paramCalendar.get(1);
      paramInt3 = paramCalendar.get(2);
      paramInt4 = paramCalendar.get(5);
    } 
    DatePicker datePicker = (DatePicker)view.findViewById(16908906);
    this.mDatePicker = datePicker;
    datePicker.init(paramInt2, paramInt3, paramInt4, this);
    this.mDatePicker.setValidationCallback(this.mValidationCallback);
    this.mDateSetListener = paramOnDateSetListener;
  }
  
  public DatePickerDialog(Context paramContext, OnDateSetListener paramOnDateSetListener, int paramInt1, int paramInt2, int paramInt3) {
    this(paramContext, 0, paramOnDateSetListener, (Calendar)null, paramInt1, paramInt2, paramInt3);
  }
  
  static int resolveDialogTheme(Context paramContext, int paramInt) {
    if (paramInt == 0) {
      TypedValue typedValue = new TypedValue();
      paramContext.getTheme().resolveAttribute(16843948, typedValue, true);
      return typedValue.resourceId;
    } 
    return paramInt;
  }
  
  public DatePicker getDatePicker() {
    return this.mDatePicker;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    if (paramInt != -2) {
      if (paramInt == -1 && this.mDateSetListener != null) {
        this.mDatePicker.clearFocus();
        OnDateSetListener onDateSetListener = this.mDateSetListener;
        DatePicker datePicker = this.mDatePicker;
        onDateSetListener.onDateSet(datePicker, datePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth());
      } 
    } else {
      cancel();
    } 
  }
  
  public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3) {
    this.mDatePicker.init(paramInt1, paramInt2, paramInt3, this);
  }
  
  public void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    int i = paramBundle.getInt("year");
    int j = paramBundle.getInt("month");
    int k = paramBundle.getInt("day");
    this.mDatePicker.init(i, j, k, this);
  }
  
  public Bundle onSaveInstanceState() {
    Bundle bundle = super.onSaveInstanceState();
    bundle.putInt("year", this.mDatePicker.getYear());
    bundle.putInt("month", this.mDatePicker.getMonth());
    bundle.putInt("day", this.mDatePicker.getDayOfMonth());
    return bundle;
  }
  
  public void setOnDateSetListener(OnDateSetListener paramOnDateSetListener) {
    this.mDateSetListener = paramOnDateSetListener;
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3) {
    this.mDatePicker.updateDate(paramInt1, paramInt2, paramInt3);
  }
  
  public static interface OnDateSetListener {
    void onDateSet(DatePicker param1DatePicker, int param1Int1, int param1Int2, int param1Int3);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DatePickerDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */