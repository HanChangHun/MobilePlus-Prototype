package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.android.internal.app.AlertController;

public class Builder {
  private final AlertController.AlertParams P;
  
  public Builder(Context paramContext) {
    this(paramContext, AlertDialog.resolveDialogTheme(paramContext, 0));
  }
  
  public Builder(Context paramContext, int paramInt) {
    this.P = new AlertController.AlertParams((Context)new ContextThemeWrapper(paramContext, AlertDialog.resolveDialogTheme(paramContext, paramInt)));
  }
  
  public AlertDialog create() {
    AlertDialog alertDialog = new AlertDialog(this.P.mContext, 0, false);
    this.P.apply(AlertDialog.access$000(alertDialog));
    alertDialog.setCancelable(this.P.mCancelable);
    if (this.P.mCancelable)
      alertDialog.setCanceledOnTouchOutside(true); 
    alertDialog.setOnCancelListener(this.P.mOnCancelListener);
    alertDialog.setOnDismissListener(this.P.mOnDismissListener);
    if (this.P.mOnKeyListener != null)
      alertDialog.setOnKeyListener(this.P.mOnKeyListener); 
    return alertDialog;
  }
  
  public Context getContext() {
    return this.P.mContext;
  }
  
  public Builder setAdapter(ListAdapter paramListAdapter, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mAdapter = paramListAdapter;
    this.P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setCancelable(boolean paramBoolean) {
    this.P.mCancelable = paramBoolean;
    return this;
  }
  
  public Builder setCursor(Cursor paramCursor, DialogInterface.OnClickListener paramOnClickListener, String paramString) {
    this.P.mCursor = paramCursor;
    this.P.mLabelColumn = paramString;
    this.P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setCustomTitle(View paramView) {
    this.P.mCustomTitleView = paramView;
    return this;
  }
  
  public Builder setIcon(int paramInt) {
    this.P.mIconId = paramInt;
    return this;
  }
  
  public Builder setIcon(Drawable paramDrawable) {
    this.P.mIcon = paramDrawable;
    return this;
  }
  
  public Builder setIconAttribute(int paramInt) {
    TypedValue typedValue = new TypedValue();
    this.P.mContext.getTheme().resolveAttribute(paramInt, typedValue, true);
    this.P.mIconId = typedValue.resourceId;
    return this;
  }
  
  @Deprecated
  public Builder setInverseBackgroundForced(boolean paramBoolean) {
    this.P.mForceInverseBackground = paramBoolean;
    return this;
  }
  
  public Builder setItems(int paramInt, DialogInterface.OnClickListener paramOnClickListener) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mItems = alertParams.mContext.getResources().getTextArray(paramInt);
    this.P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setItems(CharSequence[] paramArrayOfCharSequence, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mItems = paramArrayOfCharSequence;
    this.P.mOnClickListener = paramOnClickListener;
    return this;
  }
  
  public Builder setMessage(int paramInt) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mMessage = alertParams.mContext.getText(paramInt);
    return this;
  }
  
  public Builder setMessage(CharSequence paramCharSequence) {
    this.P.mMessage = paramCharSequence;
    return this;
  }
  
  public Builder setMultiChoiceItems(int paramInt, boolean[] paramArrayOfboolean, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mItems = alertParams.mContext.getResources().getTextArray(paramInt);
    this.P.mOnCheckboxClickListener = paramOnMultiChoiceClickListener;
    this.P.mCheckedItems = paramArrayOfboolean;
    this.P.mIsMultiChoice = true;
    return this;
  }
  
  public Builder setMultiChoiceItems(Cursor paramCursor, String paramString1, String paramString2, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener) {
    this.P.mCursor = paramCursor;
    this.P.mOnCheckboxClickListener = paramOnMultiChoiceClickListener;
    this.P.mIsCheckedColumn = paramString1;
    this.P.mLabelColumn = paramString2;
    this.P.mIsMultiChoice = true;
    return this;
  }
  
  public Builder setMultiChoiceItems(CharSequence[] paramArrayOfCharSequence, boolean[] paramArrayOfboolean, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener) {
    this.P.mItems = paramArrayOfCharSequence;
    this.P.mOnCheckboxClickListener = paramOnMultiChoiceClickListener;
    this.P.mCheckedItems = paramArrayOfboolean;
    this.P.mIsMultiChoice = true;
    return this;
  }
  
  public Builder setNegativeButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mNegativeButtonText = alertParams.mContext.getText(paramInt);
    this.P.mNegativeButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setNegativeButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mNegativeButtonText = paramCharSequence;
    this.P.mNegativeButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setNeutralButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mNeutralButtonText = alertParams.mContext.getText(paramInt);
    this.P.mNeutralButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setNeutralButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mNeutralButtonText = paramCharSequence;
    this.P.mNeutralButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setOnCancelListener(DialogInterface.OnCancelListener paramOnCancelListener) {
    this.P.mOnCancelListener = paramOnCancelListener;
    return this;
  }
  
  public Builder setOnDismissListener(DialogInterface.OnDismissListener paramOnDismissListener) {
    this.P.mOnDismissListener = paramOnDismissListener;
    return this;
  }
  
  public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener) {
    this.P.mOnItemSelectedListener = paramOnItemSelectedListener;
    return this;
  }
  
  public Builder setOnKeyListener(DialogInterface.OnKeyListener paramOnKeyListener) {
    this.P.mOnKeyListener = paramOnKeyListener;
    return this;
  }
  
  public Builder setPositiveButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mPositiveButtonText = alertParams.mContext.getText(paramInt);
    this.P.mPositiveButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setPositiveButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mPositiveButtonText = paramCharSequence;
    this.P.mPositiveButtonListener = paramOnClickListener;
    return this;
  }
  
  public Builder setRecycleOnMeasureEnabled(boolean paramBoolean) {
    this.P.mRecycleOnMeasure = paramBoolean;
    return this;
  }
  
  public Builder setSingleChoiceItems(int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mItems = alertParams.mContext.getResources().getTextArray(paramInt1);
    this.P.mOnClickListener = paramOnClickListener;
    this.P.mCheckedItem = paramInt2;
    this.P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setSingleChoiceItems(Cursor paramCursor, int paramInt, String paramString, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mCursor = paramCursor;
    this.P.mOnClickListener = paramOnClickListener;
    this.P.mCheckedItem = paramInt;
    this.P.mLabelColumn = paramString;
    this.P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setSingleChoiceItems(ListAdapter paramListAdapter, int paramInt, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mAdapter = paramListAdapter;
    this.P.mOnClickListener = paramOnClickListener;
    this.P.mCheckedItem = paramInt;
    this.P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setSingleChoiceItems(CharSequence[] paramArrayOfCharSequence, int paramInt, DialogInterface.OnClickListener paramOnClickListener) {
    this.P.mItems = paramArrayOfCharSequence;
    this.P.mOnClickListener = paramOnClickListener;
    this.P.mCheckedItem = paramInt;
    this.P.mIsSingleChoice = true;
    return this;
  }
  
  public Builder setTitle(int paramInt) {
    AlertController.AlertParams alertParams = this.P;
    alertParams.mTitle = alertParams.mContext.getText(paramInt);
    return this;
  }
  
  public Builder setTitle(CharSequence paramCharSequence) {
    this.P.mTitle = paramCharSequence;
    return this;
  }
  
  public Builder setView(int paramInt) {
    this.P.mView = null;
    this.P.mViewLayoutResId = paramInt;
    this.P.mViewSpacingSpecified = false;
    return this;
  }
  
  public Builder setView(View paramView) {
    this.P.mView = paramView;
    this.P.mViewLayoutResId = 0;
    this.P.mViewSpacingSpecified = false;
    return this;
  }
  
  @Deprecated
  public Builder setView(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.P.mView = paramView;
    this.P.mViewLayoutResId = 0;
    this.P.mViewSpacingSpecified = true;
    this.P.mViewSpacingLeft = paramInt1;
    this.P.mViewSpacingTop = paramInt2;
    this.P.mViewSpacingRight = paramInt3;
    this.P.mViewSpacingBottom = paramInt4;
    return this;
  }
  
  public AlertDialog show() {
    AlertDialog alertDialog = create();
    alertDialog.show();
    return alertDialog;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AlertDialog$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */