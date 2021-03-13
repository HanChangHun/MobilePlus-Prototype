package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@Deprecated
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
  private static final String SAVED_BACK_STACK_ID = "android:backStackId";
  
  private static final String SAVED_CANCELABLE = "android:cancelable";
  
  private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
  
  private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
  
  private static final String SAVED_STYLE = "android:style";
  
  private static final String SAVED_THEME = "android:theme";
  
  public static final int STYLE_NORMAL = 0;
  
  public static final int STYLE_NO_FRAME = 2;
  
  public static final int STYLE_NO_INPUT = 3;
  
  public static final int STYLE_NO_TITLE = 1;
  
  int mBackStackId = -1;
  
  boolean mCancelable = true;
  
  Dialog mDialog;
  
  boolean mDismissed;
  
  boolean mShownByMe;
  
  boolean mShowsDialog = true;
  
  int mStyle = 0;
  
  int mTheme = 0;
  
  boolean mViewDestroyed;
  
  public void dismiss() {
    dismissInternal(false);
  }
  
  public void dismissAllowingStateLoss() {
    dismissInternal(true);
  }
  
  void dismissInternal(boolean paramBoolean) {
    if (this.mDismissed)
      return; 
    this.mDismissed = true;
    this.mShownByMe = false;
    Dialog dialog = this.mDialog;
    if (dialog != null) {
      dialog.dismiss();
      this.mDialog = null;
    } 
    this.mViewDestroyed = true;
    if (this.mBackStackId >= 0) {
      getFragmentManager().popBackStack(this.mBackStackId, 1);
      this.mBackStackId = -1;
    } else {
      FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
      fragmentTransaction.remove(this);
      if (paramBoolean) {
        fragmentTransaction.commitAllowingStateLoss();
      } else {
        fragmentTransaction.commit();
      } 
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.println("DialogFragment:");
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mStyle=");
    paramPrintWriter.print(this.mStyle);
    paramPrintWriter.print(" mTheme=0x");
    paramPrintWriter.println(Integer.toHexString(this.mTheme));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mCancelable=");
    paramPrintWriter.print(this.mCancelable);
    paramPrintWriter.print(" mShowsDialog=");
    paramPrintWriter.print(this.mShowsDialog);
    paramPrintWriter.print(" mBackStackId=");
    paramPrintWriter.println(this.mBackStackId);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mDialog=");
    paramPrintWriter.println(this.mDialog);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mViewDestroyed=");
    paramPrintWriter.print(this.mViewDestroyed);
    paramPrintWriter.print(" mDismissed=");
    paramPrintWriter.print(this.mDismissed);
    paramPrintWriter.print(" mShownByMe=");
    paramPrintWriter.println(this.mShownByMe);
  }
  
  public Dialog getDialog() {
    return this.mDialog;
  }
  
  public boolean getShowsDialog() {
    return this.mShowsDialog;
  }
  
  public int getTheme() {
    return this.mTheme;
  }
  
  public boolean isCancelable() {
    return this.mCancelable;
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    super.onActivityCreated(paramBundle);
    if (!this.mShowsDialog)
      return; 
    View view = getView();
    if (view != null)
      if (view.getParent() == null) {
        this.mDialog.setContentView(view);
      } else {
        throw new IllegalStateException("DialogFragment can not be attached to a container view");
      }  
    Activity activity = getActivity();
    if (activity != null)
      this.mDialog.setOwnerActivity(activity); 
    this.mDialog.setCancelable(this.mCancelable);
    if (this.mDialog.takeCancelAndDismissListeners("DialogFragment", this, this)) {
      if (paramBundle != null) {
        paramBundle = paramBundle.getBundle("android:savedDialogState");
        if (paramBundle != null)
          this.mDialog.onRestoreInstanceState(paramBundle); 
      } 
      return;
    } 
    throw new IllegalStateException("You can not set Dialog's OnCancelListener or OnDismissListener");
  }
  
  public void onAttach(Context paramContext) {
    super.onAttach(paramContext);
    if (!this.mShownByMe)
      this.mDismissed = false; 
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {}
  
  public void onCreate(Bundle paramBundle) {
    boolean bool;
    super.onCreate(paramBundle);
    if (this.mContainerId == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mShowsDialog = bool;
    if (paramBundle != null) {
      this.mStyle = paramBundle.getInt("android:style", 0);
      this.mTheme = paramBundle.getInt("android:theme", 0);
      this.mCancelable = paramBundle.getBoolean("android:cancelable", true);
      this.mShowsDialog = paramBundle.getBoolean("android:showsDialog", this.mShowsDialog);
      this.mBackStackId = paramBundle.getInt("android:backStackId", -1);
    } 
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    return new Dialog((Context)getActivity(), getTheme());
  }
  
  public void onDestroyView() {
    super.onDestroyView();
    Dialog dialog = this.mDialog;
    if (dialog != null) {
      this.mViewDestroyed = true;
      dialog.dismiss();
      this.mDialog = null;
    } 
  }
  
  public void onDetach() {
    super.onDetach();
    if (!this.mShownByMe && !this.mDismissed)
      this.mDismissed = true; 
  }
  
  public void onDismiss(DialogInterface paramDialogInterface) {
    if (!this.mViewDestroyed)
      dismissInternal(true); 
  }
  
  public LayoutInflater onGetLayoutInflater(Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mShowsDialog : Z
    //   4: ifne -> 13
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial onGetLayoutInflater : (Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    //   12: areturn
    //   13: aload_0
    //   14: aload_1
    //   15: invokevirtual onCreateDialog : (Landroid/os/Bundle;)Landroid/app/Dialog;
    //   18: astore_1
    //   19: aload_0
    //   20: aload_1
    //   21: putfield mDialog : Landroid/app/Dialog;
    //   24: aload_0
    //   25: getfield mStyle : I
    //   28: istore_2
    //   29: iload_2
    //   30: iconst_1
    //   31: if_icmpeq -> 56
    //   34: iload_2
    //   35: iconst_2
    //   36: if_icmpeq -> 56
    //   39: iload_2
    //   40: iconst_3
    //   41: if_icmpeq -> 47
    //   44: goto -> 65
    //   47: aload_1
    //   48: invokevirtual getWindow : ()Landroid/view/Window;
    //   51: bipush #24
    //   53: invokevirtual addFlags : (I)V
    //   56: aload_0
    //   57: getfield mDialog : Landroid/app/Dialog;
    //   60: iconst_1
    //   61: invokevirtual requestWindowFeature : (I)Z
    //   64: pop
    //   65: aload_0
    //   66: getfield mDialog : Landroid/app/Dialog;
    //   69: astore_1
    //   70: aload_1
    //   71: ifnull -> 88
    //   74: aload_1
    //   75: invokevirtual getContext : ()Landroid/content/Context;
    //   78: ldc_w 'layout_inflater'
    //   81: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   84: checkcast android/view/LayoutInflater
    //   87: areturn
    //   88: aload_0
    //   89: getfield mHost : Landroid/app/FragmentHostCallback;
    //   92: invokevirtual getContext : ()Landroid/content/Context;
    //   95: ldc_w 'layout_inflater'
    //   98: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   101: checkcast android/view/LayoutInflater
    //   104: areturn
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    Dialog dialog = this.mDialog;
    if (dialog != null) {
      Bundle bundle = dialog.onSaveInstanceState();
      if (bundle != null)
        paramBundle.putBundle("android:savedDialogState", bundle); 
    } 
    int i = this.mStyle;
    if (i != 0)
      paramBundle.putInt("android:style", i); 
    i = this.mTheme;
    if (i != 0)
      paramBundle.putInt("android:theme", i); 
    boolean bool = this.mCancelable;
    if (!bool)
      paramBundle.putBoolean("android:cancelable", bool); 
    bool = this.mShowsDialog;
    if (!bool)
      paramBundle.putBoolean("android:showsDialog", bool); 
    i = this.mBackStackId;
    if (i != -1)
      paramBundle.putInt("android:backStackId", i); 
  }
  
  public void onStart() {
    super.onStart();
    Dialog dialog = this.mDialog;
    if (dialog != null) {
      this.mViewDestroyed = false;
      dialog.show();
    } 
  }
  
  public void onStop() {
    super.onStop();
    Dialog dialog = this.mDialog;
    if (dialog != null)
      dialog.hide(); 
  }
  
  public void setCancelable(boolean paramBoolean) {
    this.mCancelable = paramBoolean;
    Dialog dialog = this.mDialog;
    if (dialog != null)
      dialog.setCancelable(paramBoolean); 
  }
  
  public void setShowsDialog(boolean paramBoolean) {
    this.mShowsDialog = paramBoolean;
  }
  
  public void setStyle(int paramInt1, int paramInt2) {
    this.mStyle = paramInt1;
    if (paramInt1 == 2 || paramInt1 == 3)
      this.mTheme = 16974823; 
    if (paramInt2 != 0)
      this.mTheme = paramInt2; 
  }
  
  public int show(FragmentTransaction paramFragmentTransaction, String paramString) {
    this.mDismissed = false;
    this.mShownByMe = true;
    paramFragmentTransaction.add(this, paramString);
    this.mViewDestroyed = false;
    int i = paramFragmentTransaction.commit();
    this.mBackStackId = i;
    return i;
  }
  
  public void show(FragmentManager paramFragmentManager, String paramString) {
    this.mDismissed = false;
    this.mShownByMe = true;
    FragmentTransaction fragmentTransaction = paramFragmentManager.beginTransaction();
    fragmentTransaction.add(this, paramString);
    fragmentTransaction.commit();
  }
  
  public void showAllowingStateLoss(FragmentManager paramFragmentManager, String paramString) {
    this.mDismissed = false;
    this.mShownByMe = true;
    FragmentTransaction fragmentTransaction = paramFragmentManager.beginTransaction();
    fragmentTransaction.add(this, paramString);
    fragmentTransaction.commitAllowingStateLoss();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DialogFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */