package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.internal.R;
import java.text.NumberFormat;

@Deprecated
public class ProgressDialog extends AlertDialog {
  public static final int STYLE_HORIZONTAL = 1;
  
  public static final int STYLE_SPINNER = 0;
  
  private boolean mHasStarted;
  
  private int mIncrementBy;
  
  private int mIncrementSecondaryBy;
  
  private boolean mIndeterminate;
  
  private Drawable mIndeterminateDrawable;
  
  private int mMax;
  
  private CharSequence mMessage;
  
  private TextView mMessageView;
  
  private ProgressBar mProgress;
  
  private Drawable mProgressDrawable;
  
  private TextView mProgressNumber;
  
  private String mProgressNumberFormat;
  
  private TextView mProgressPercent;
  
  private NumberFormat mProgressPercentFormat;
  
  private int mProgressStyle = 0;
  
  private int mProgressVal;
  
  private int mSecondaryProgressVal;
  
  private Handler mViewUpdateHandler;
  
  public ProgressDialog(Context paramContext) {
    super(paramContext);
    initFormats();
  }
  
  public ProgressDialog(Context paramContext, int paramInt) {
    super(paramContext, paramInt);
    initFormats();
  }
  
  private void initFormats() {
    this.mProgressNumberFormat = "%1d/%2d";
    NumberFormat numberFormat = NumberFormat.getPercentInstance();
    this.mProgressPercentFormat = numberFormat;
    numberFormat.setMaximumFractionDigits(0);
  }
  
  private void onProgressChanged() {
    if (this.mProgressStyle == 1) {
      Handler handler = this.mViewUpdateHandler;
      if (handler != null && !handler.hasMessages(0))
        this.mViewUpdateHandler.sendEmptyMessage(0); 
    } 
  }
  
  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return show(paramContext, paramCharSequence1, paramCharSequence2, false);
  }
  
  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean) {
    return show(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean, false, (DialogInterface.OnCancelListener)null);
  }
  
  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2) {
    return show(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean1, paramBoolean2, (DialogInterface.OnCancelListener)null);
  }
  
  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2, DialogInterface.OnCancelListener paramOnCancelListener) {
    ProgressDialog progressDialog = new ProgressDialog(paramContext);
    progressDialog.setTitle(paramCharSequence1);
    progressDialog.setMessage(paramCharSequence2);
    progressDialog.setIndeterminate(paramBoolean1);
    progressDialog.setCancelable(paramBoolean2);
    progressDialog.setOnCancelListener(paramOnCancelListener);
    progressDialog.show();
    return progressDialog;
  }
  
  public int getMax() {
    ProgressBar progressBar = this.mProgress;
    return (progressBar != null) ? progressBar.getMax() : this.mMax;
  }
  
  public int getProgress() {
    ProgressBar progressBar = this.mProgress;
    return (progressBar != null) ? progressBar.getProgress() : this.mProgressVal;
  }
  
  public int getSecondaryProgress() {
    ProgressBar progressBar = this.mProgress;
    return (progressBar != null) ? progressBar.getSecondaryProgress() : this.mSecondaryProgressVal;
  }
  
  public void incrementProgressBy(int paramInt) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.incrementProgressBy(paramInt);
      onProgressChanged();
    } else {
      this.mIncrementBy += paramInt;
    } 
  }
  
  public void incrementSecondaryProgressBy(int paramInt) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.incrementSecondaryProgressBy(paramInt);
      onProgressChanged();
    } else {
      this.mIncrementSecondaryBy += paramInt;
    } 
  }
  
  public boolean isIndeterminate() {
    ProgressBar progressBar = this.mProgress;
    return (progressBar != null) ? progressBar.isIndeterminate() : this.mIndeterminate;
  }
  
  protected void onCreate(Bundle paramBundle) {
    View view;
    LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
    TypedArray typedArray = this.mContext.obtainStyledAttributes(null, R.styleable.AlertDialog, 16842845, 0);
    if (this.mProgressStyle == 1) {
      this.mViewUpdateHandler = new Handler() {
          public void handleMessage(Message param1Message) {
            super.handleMessage(param1Message);
            int i = ProgressDialog.this.mProgress.getProgress();
            int j = ProgressDialog.this.mProgress.getMax();
            if (ProgressDialog.this.mProgressNumberFormat != null) {
              String str = ProgressDialog.this.mProgressNumberFormat;
              ProgressDialog.this.mProgressNumber.setText(String.format(str, new Object[] { Integer.valueOf(i), Integer.valueOf(j) }));
            } else {
              ProgressDialog.this.mProgressNumber.setText("");
            } 
            if (ProgressDialog.this.mProgressPercentFormat != null) {
              double d = i / j;
              SpannableString spannableString = new SpannableString(ProgressDialog.this.mProgressPercentFormat.format(d));
              spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
              ProgressDialog.this.mProgressPercent.setText((CharSequence)spannableString);
            } else {
              ProgressDialog.this.mProgressPercent.setText("");
            } 
          }
        };
      view = layoutInflater.inflate(typedArray.getResourceId(13, 17367088), null);
      this.mProgress = (ProgressBar)view.findViewById(16908301);
      this.mProgressNumber = (TextView)view.findViewById(16909324);
      this.mProgressPercent = (TextView)view.findViewById(16909325);
      setView(view);
    } else {
      view = view.inflate(typedArray.getResourceId(18, 17367264), null);
      this.mProgress = (ProgressBar)view.findViewById(16908301);
      this.mMessageView = (TextView)view.findViewById(16908299);
      setView(view);
    } 
    typedArray.recycle();
    int i = this.mMax;
    if (i > 0)
      setMax(i); 
    i = this.mProgressVal;
    if (i > 0)
      setProgress(i); 
    i = this.mSecondaryProgressVal;
    if (i > 0)
      setSecondaryProgress(i); 
    i = this.mIncrementBy;
    if (i > 0)
      incrementProgressBy(i); 
    i = this.mIncrementSecondaryBy;
    if (i > 0)
      incrementSecondaryProgressBy(i); 
    Drawable drawable = this.mProgressDrawable;
    if (drawable != null)
      setProgressDrawable(drawable); 
    drawable = this.mIndeterminateDrawable;
    if (drawable != null)
      setIndeterminateDrawable(drawable); 
    CharSequence charSequence = this.mMessage;
    if (charSequence != null)
      setMessage(charSequence); 
    setIndeterminate(this.mIndeterminate);
    onProgressChanged();
    super.onCreate(paramBundle);
  }
  
  public void onStart() {
    super.onStart();
    this.mHasStarted = true;
  }
  
  protected void onStop() {
    super.onStop();
    this.mHasStarted = false;
  }
  
  public void setIndeterminate(boolean paramBoolean) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.setIndeterminate(paramBoolean);
    } else {
      this.mIndeterminate = paramBoolean;
    } 
  }
  
  public void setIndeterminateDrawable(Drawable paramDrawable) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.setIndeterminateDrawable(paramDrawable);
    } else {
      this.mIndeterminateDrawable = paramDrawable;
    } 
  }
  
  public void setMax(int paramInt) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.setMax(paramInt);
      onProgressChanged();
    } else {
      this.mMax = paramInt;
    } 
  }
  
  public void setMessage(CharSequence paramCharSequence) {
    if (this.mProgress != null) {
      if (this.mProgressStyle == 1) {
        super.setMessage(paramCharSequence);
      } else {
        this.mMessageView.setText(paramCharSequence);
      } 
    } else {
      this.mMessage = paramCharSequence;
    } 
  }
  
  public void setProgress(int paramInt) {
    if (this.mHasStarted) {
      this.mProgress.setProgress(paramInt);
      onProgressChanged();
    } else {
      this.mProgressVal = paramInt;
    } 
  }
  
  public void setProgressDrawable(Drawable paramDrawable) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.setProgressDrawable(paramDrawable);
    } else {
      this.mProgressDrawable = paramDrawable;
    } 
  }
  
  public void setProgressNumberFormat(String paramString) {
    this.mProgressNumberFormat = paramString;
    onProgressChanged();
  }
  
  public void setProgressPercentFormat(NumberFormat paramNumberFormat) {
    this.mProgressPercentFormat = paramNumberFormat;
    onProgressChanged();
  }
  
  public void setProgressStyle(int paramInt) {
    this.mProgressStyle = paramInt;
  }
  
  public void setSecondaryProgress(int paramInt) {
    ProgressBar progressBar = this.mProgress;
    if (progressBar != null) {
      progressBar.setSecondaryProgress(paramInt);
      onProgressChanged();
    } else {
      this.mSecondaryProgressVal = paramInt;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ProgressDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */