package android.app;

import android.os.Bundle;
import android.os.Parcelable;

public class UnreadConversation {
  private static final String KEY_AUTHOR = "author";
  
  private static final String KEY_MESSAGES = "messages";
  
  private static final String KEY_ON_READ = "on_read";
  
  private static final String KEY_ON_REPLY = "on_reply";
  
  private static final String KEY_PARTICIPANTS = "participants";
  
  private static final String KEY_REMOTE_INPUT = "remote_input";
  
  private static final String KEY_TEXT = "text";
  
  private static final String KEY_TIMESTAMP = "timestamp";
  
  private final long mLatestTimestamp;
  
  private final String[] mMessages;
  
  private final String[] mParticipants;
  
  private final PendingIntent mReadPendingIntent;
  
  private final RemoteInput mRemoteInput;
  
  private final PendingIntent mReplyPendingIntent;
  
  UnreadConversation(String[] paramArrayOfString1, RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong) {
    this.mMessages = paramArrayOfString1;
    this.mRemoteInput = paramRemoteInput;
    this.mReadPendingIntent = paramPendingIntent2;
    this.mReplyPendingIntent = paramPendingIntent1;
    this.mParticipants = paramArrayOfString2;
    this.mLatestTimestamp = paramLong;
  }
  
  static UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle) {
    if (paramBundle == null)
      return null; 
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("messages");
    String[] arrayOfString1 = null;
    if (arrayOfParcelable != null) {
      boolean bool2;
      arrayOfString1 = new String[arrayOfParcelable.length];
      boolean bool1 = true;
      byte b = 0;
      while (true) {
        bool2 = bool1;
        if (b < arrayOfString1.length) {
          if (!(arrayOfParcelable[b] instanceof Bundle)) {
            bool2 = false;
            break;
          } 
          arrayOfString1[b] = ((Bundle)arrayOfParcelable[b]).getString("text");
          if (arrayOfString1[b] == null) {
            bool2 = false;
            break;
          } 
          b++;
          continue;
        } 
        break;
      } 
      if (!bool2)
        return null; 
    } 
    PendingIntent pendingIntent2 = (PendingIntent)paramBundle.getParcelable("on_read");
    PendingIntent pendingIntent1 = (PendingIntent)paramBundle.getParcelable("on_reply");
    RemoteInput remoteInput = (RemoteInput)paramBundle.getParcelable("remote_input");
    String[] arrayOfString2 = paramBundle.getStringArray("participants");
    return (arrayOfString2 == null || arrayOfString2.length != 1) ? null : new UnreadConversation(arrayOfString1, remoteInput, pendingIntent1, pendingIntent2, arrayOfString2, paramBundle.getLong("timestamp"));
  }
  
  Bundle getBundleForUnreadConversation() {
    Bundle bundle = new Bundle();
    String str1 = null;
    String[] arrayOfString = this.mParticipants;
    String str2 = str1;
    if (arrayOfString != null) {
      str2 = str1;
      if (arrayOfString.length > 1)
        str2 = arrayOfString[0]; 
    } 
    Parcelable[] arrayOfParcelable = new Parcelable[this.mMessages.length];
    for (byte b = 0; b < arrayOfParcelable.length; b++) {
      Bundle bundle1 = new Bundle();
      bundle1.putString("text", this.mMessages[b]);
      bundle1.putString("author", str2);
      arrayOfParcelable[b] = (Parcelable)bundle1;
    } 
    bundle.putParcelableArray("messages", arrayOfParcelable);
    RemoteInput remoteInput = this.mRemoteInput;
    if (remoteInput != null)
      bundle.putParcelable("remote_input", remoteInput); 
    bundle.putParcelable("on_reply", this.mReplyPendingIntent);
    bundle.putParcelable("on_read", this.mReadPendingIntent);
    bundle.putStringArray("participants", this.mParticipants);
    bundle.putLong("timestamp", this.mLatestTimestamp);
    return bundle;
  }
  
  public long getLatestTimestamp() {
    return this.mLatestTimestamp;
  }
  
  public String[] getMessages() {
    return this.mMessages;
  }
  
  public String getParticipant() {
    String[] arrayOfString = this.mParticipants;
    if (arrayOfString.length > 0) {
      String str = arrayOfString[0];
    } else {
      arrayOfString = null;
    } 
    return (String)arrayOfString;
  }
  
  public String[] getParticipants() {
    return this.mParticipants;
  }
  
  public PendingIntent getReadPendingIntent() {
    return this.mReadPendingIntent;
  }
  
  public RemoteInput getRemoteInput() {
    return this.mRemoteInput;
  }
  
  public PendingIntent getReplyPendingIntent() {
    return this.mReplyPendingIntent;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$CarExtender$UnreadConversation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */