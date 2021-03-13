package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class LocalDialog extends DialogFragment {
  public Dialog onCreateDialog(Bundle paramBundle) {
    RecoverableSecurityException recoverableSecurityException = (RecoverableSecurityException)getArguments().getParcelable("RecoverableSecurityException");
    return (new AlertDialog.Builder((Context)getActivity())).setMessage(RecoverableSecurityException.access$100(recoverableSecurityException)).setPositiveButton(RecoverableSecurityException.access$000(recoverableSecurityException).getTitle(), new _$$Lambda$RecoverableSecurityException$LocalDialog$r8YNkpjWIZllJsQ_8eA0q51FU5Q(recoverableSecurityException)).setNegativeButton(17039360, (DialogInterface.OnClickListener)null).create();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RecoverableSecurityException$LocalDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */