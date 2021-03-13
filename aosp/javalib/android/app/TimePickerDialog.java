package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener, TimePicker.OnTimeChangedListener {
  private static final String HOUR = "hour";
  
  private static final String IS_24_HOUR = "is24hour";
  
  private static final String MINUTE = "minute";
  
  private final int mInitialHourOfDay;
  
  private final int mInitialMinute;
  
  private final boolean mIs24HourView;
  
  private final TimePicker mTimePicker;
  
  private final OnTimeSetListener mTimeSetListener;
  
  public TimePickerDialog(Context paramContext, int paramInt1, OnTimeSetListener paramOnTimeSetListener, int paramInt2, int paramInt3, boolean paramBoolean) {
    super(paramContext, resolveDialogTheme(paramContext, paramInt1));
    this.mTimeSetListener = paramOnTimeSetListener;
    this.mInitialHourOfDay = paramInt2;
    this.mInitialMinute = paramInt3;
    this.mIs24HourView = paramBoolean;
    paramContext = getContext();
    View view = LayoutInflater.from(paramContext).inflate(17367335, null);
    setView(view);
    setButton(-1, paramContext.getString(17039370), this);
    setButton(-2, paramContext.getString(17039360), this);
    setButtonPanelLayoutHint(1);
    TimePicker timePicker = (TimePicker)view.findViewById(16909534);
    this.mTimePicker = timePicker;
    timePicker.setIs24HourView(Boolean.valueOf(this.mIs24HourView));
    this.mTimePicker.setCurrentHour(Integer.valueOf(this.mInitialHourOfDay));
    this.mTimePicker.setCurrentMinute(Integer.valueOf(this.mInitialMinute));
    this.mTimePicker.setOnTimeChangedListener(this);
  }
  
  public TimePickerDialog(Context paramContext, OnTimeSetListener paramOnTimeSetListener, int paramInt1, int paramInt2, boolean paramBoolean) {
    this(paramContext, 0, paramOnTimeSetListener, paramInt1, paramInt2, paramBoolean);
  }
  
  static int resolveDialogTheme(Context paramContext, int paramInt) {
    if (paramInt == 0) {
      TypedValue typedValue = new TypedValue();
      paramContext.getTheme().resolveAttribute(16843934, typedValue, true);
      return typedValue.resourceId;
    } 
    return paramInt;
  }
  
  public TimePicker getTimePicker() {
    return this.mTimePicker;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    if (paramInt != -2) {
      if (paramInt == -1) {
        OnTimeSetListener onTimeSetListener = this.mTimeSetListener;
        if (onTimeSetListener != null) {
          TimePicker timePicker = this.mTimePicker;
          onTimeSetListener.onTimeSet(timePicker, timePicker.getCurrentHour().intValue(), this.mTimePicker.getCurrentMinute().intValue());
        } 
      } 
    } else {
      cancel();
    } 
  }
  
  public void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    int i = paramBundle.getInt("hour");
    int j = paramBundle.getInt("minute");
    this.mTimePicker.setIs24HourView(Boolean.valueOf(paramBundle.getBoolean("is24hour")));
    this.mTimePicker.setCurrentHour(Integer.valueOf(i));
    this.mTimePicker.setCurrentMinute(Integer.valueOf(j));
  }
  
  public Bundle onSaveInstanceState() {
    Bundle bundle = super.onSaveInstanceState();
    bundle.putInt("hour", this.mTimePicker.getCurrentHour().intValue());
    bundle.putInt("minute", this.mTimePicker.getCurrentMinute().intValue());
    bundle.putBoolean("is24hour", this.mTimePicker.is24HourView());
    return bundle;
  }
  
  public void onTimeChanged(TimePicker paramTimePicker, int paramInt1, int paramInt2) {}
  
  public void show() {
    super.show();
    getButton(-1).setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            if (TimePickerDialog.this.mTimePicker.validateInput()) {
              TimePickerDialog timePickerDialog = TimePickerDialog.this;
              timePickerDialog.onClick(timePickerDialog, -1);
              TimePickerDialog.this.mTimePicker.clearFocus();
              TimePickerDialog.this.dismiss();
            } 
          }
        });
  }
  
  public void updateTime(int paramInt1, int paramInt2) {
    this.mTimePicker.setCurrentHour(Integer.valueOf(paramInt1));
    this.mTimePicker.setCurrentMinute(Integer.valueOf(paramInt2));
  }
  
  public static interface OnTimeSetListener {
    void onTimeSet(TimePicker param1TimePicker, int param1Int1, int param1Int2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/TimePickerDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */