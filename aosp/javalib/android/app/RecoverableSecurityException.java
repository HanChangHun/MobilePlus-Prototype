package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class RecoverableSecurityException extends SecurityException implements Parcelable {
  public static final Parcelable.Creator<RecoverableSecurityException> CREATOR = new Parcelable.Creator<RecoverableSecurityException>() {
      public RecoverableSecurityException createFromParcel(Parcel param1Parcel) {
        return new RecoverableSecurityException(param1Parcel);
      }
      
      public RecoverableSecurityException[] newArray(int param1Int) {
        return new RecoverableSecurityException[param1Int];
      }
    };
  
  private static final String TAG = "RecoverableSecurityException";
  
  private final RemoteAction mUserAction;
  
  private final CharSequence mUserMessage;
  
  public RecoverableSecurityException(Parcel paramParcel) {
    this(new SecurityException(paramParcel.readString()), paramParcel.readCharSequence(), (RemoteAction)RemoteAction.CREATOR.createFromParcel(paramParcel));
  }
  
  public RecoverableSecurityException(Throwable paramThrowable, CharSequence paramCharSequence, RemoteAction paramRemoteAction) {
    super(paramThrowable.getMessage());
    Objects.requireNonNull(paramCharSequence);
    this.mUserMessage = paramCharSequence;
    Objects.requireNonNull(paramRemoteAction);
    this.mUserAction = paramRemoteAction;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public RemoteAction getUserAction() {
    return this.mUserAction;
  }
  
  public CharSequence getUserMessage() {
    return this.mUserMessage;
  }
  
  public void showAsDialog(Activity paramActivity) {
    LocalDialog localDialog = new LocalDialog();
    Bundle bundle = new Bundle();
    bundle.putParcelable("RecoverableSecurityException", this);
    localDialog.setArguments(bundle);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RecoverableSecurityException_");
    stringBuilder.append(this.mUserAction.getActionIntent().getCreatorUid());
    String str = stringBuilder.toString();
    FragmentManager fragmentManager = paramActivity.getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Fragment fragment = fragmentManager.findFragmentByTag(str);
    if (fragment != null)
      fragmentTransaction.remove(fragment); 
    fragmentTransaction.add(localDialog, str);
    fragmentTransaction.commitAllowingStateLoss();
  }
  
  public void showAsNotification(Context paramContext, String paramString) {
    NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService(NotificationManager.class);
    Notification.Builder builder = (new Notification.Builder(paramContext, paramString)).setSmallIcon(17302799).setContentTitle(this.mUserAction.getTitle()).setContentText(this.mUserMessage).setContentIntent(this.mUserAction.getActionIntent()).setCategory("err");
    notificationManager.notify("RecoverableSecurityException", this.mUserAction.getActionIntent().getCreatorUid(), builder.build());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(getMessage());
    paramParcel.writeCharSequence(this.mUserMessage);
    this.mUserAction.writeToParcel(paramParcel, paramInt);
  }
  
  public static class LocalDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle param1Bundle) {
      RecoverableSecurityException recoverableSecurityException = (RecoverableSecurityException)getArguments().getParcelable("RecoverableSecurityException");
      return (new AlertDialog.Builder((Context)getActivity())).setMessage(recoverableSecurityException.mUserMessage).setPositiveButton(recoverableSecurityException.mUserAction.getTitle(), new _$$Lambda$RecoverableSecurityException$LocalDialog$r8YNkpjWIZllJsQ_8eA0q51FU5Q(recoverableSecurityException)).setNegativeButton(17039360, (DialogInterface.OnClickListener)null).create();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RecoverableSecurityException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */