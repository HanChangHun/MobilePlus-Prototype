package android.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class CarExtender implements Notification.Extender {
  private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
  
  private static final String EXTRA_COLOR = "app_color";
  
  private static final String EXTRA_CONVERSATION = "car_conversation";
  
  private static final String EXTRA_LARGE_ICON = "large_icon";
  
  private static final String TAG = "CarExtender";
  
  private int mColor;
  
  private Bitmap mLargeIcon;
  
  private UnreadConversation mUnreadConversation;
  
  public CarExtender() {
    this.mColor = 0;
  }
  
  public CarExtender(Notification paramNotification) {
    Bundle bundle;
    this.mColor = 0;
    if (paramNotification.extras == null) {
      paramNotification = null;
    } else {
      bundle = paramNotification.extras.getBundle("android.car.EXTENSIONS");
    } 
    if (bundle != null) {
      this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
      this.mColor = bundle.getInt("app_color", 0);
      this.mUnreadConversation = UnreadConversation.getUnreadConversationFromBundle(bundle.getBundle("car_conversation"));
    } 
  }
  
  public Notification.Builder extend(Notification.Builder paramBuilder) {
    Bundle bundle = new Bundle();
    Bitmap bitmap = this.mLargeIcon;
    if (bitmap != null)
      bundle.putParcelable("large_icon", (Parcelable)bitmap); 
    int i = this.mColor;
    if (i != 0)
      bundle.putInt("app_color", i); 
    UnreadConversation unreadConversation = this.mUnreadConversation;
    if (unreadConversation != null)
      bundle.putBundle("car_conversation", unreadConversation.getBundleForUnreadConversation()); 
    paramBuilder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
    return paramBuilder;
  }
  
  public int getColor() {
    return this.mColor;
  }
  
  public Bitmap getLargeIcon() {
    return this.mLargeIcon;
  }
  
  public UnreadConversation getUnreadConversation() {
    return this.mUnreadConversation;
  }
  
  public CarExtender setColor(int paramInt) {
    this.mColor = paramInt;
    return this;
  }
  
  public CarExtender setLargeIcon(Bitmap paramBitmap) {
    this.mLargeIcon = paramBitmap;
    return this;
  }
  
  public CarExtender setUnreadConversation(UnreadConversation paramUnreadConversation) {
    this.mUnreadConversation = paramUnreadConversation;
    return this;
  }
  
  public static class Builder {
    private long mLatestTimestamp;
    
    private final List<String> mMessages = new ArrayList<>();
    
    private final String mParticipant;
    
    private PendingIntent mReadPendingIntent;
    
    private RemoteInput mRemoteInput;
    
    private PendingIntent mReplyPendingIntent;
    
    public Builder(String param2String) {
      this.mParticipant = param2String;
    }
    
    public Builder addMessage(String param2String) {
      this.mMessages.add(param2String);
      return this;
    }
    
    public Notification.CarExtender.UnreadConversation build() {
      List<String> list = this.mMessages;
      String[] arrayOfString = list.<String>toArray(new String[list.size()]);
      String str = this.mParticipant;
      RemoteInput remoteInput = this.mRemoteInput;
      PendingIntent pendingIntent1 = this.mReplyPendingIntent;
      PendingIntent pendingIntent2 = this.mReadPendingIntent;
      long l = this.mLatestTimestamp;
      return new Notification.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
    }
    
    public Builder setLatestTimestamp(long param2Long) {
      this.mLatestTimestamp = param2Long;
      return this;
    }
    
    public Builder setReadPendingIntent(PendingIntent param2PendingIntent) {
      this.mReadPendingIntent = param2PendingIntent;
      return this;
    }
    
    public Builder setReplyAction(PendingIntent param2PendingIntent, RemoteInput param2RemoteInput) {
      this.mRemoteInput = param2RemoteInput;
      this.mReplyPendingIntent = param2PendingIntent;
      return this;
    }
  }
  
  public static class UnreadConversation {
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
    
    UnreadConversation(String[] param2ArrayOfString1, RemoteInput param2RemoteInput, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, String[] param2ArrayOfString2, long param2Long) {
      this.mMessages = param2ArrayOfString1;
      this.mRemoteInput = param2RemoteInput;
      this.mReadPendingIntent = param2PendingIntent2;
      this.mReplyPendingIntent = param2PendingIntent1;
      this.mParticipants = param2ArrayOfString2;
      this.mLatestTimestamp = param2Long;
    }
    
    static UnreadConversation getUnreadConversationFromBundle(Bundle param2Bundle) {
      if (param2Bundle == null)
        return null; 
      Parcelable[] arrayOfParcelable = param2Bundle.getParcelableArray("messages");
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
      PendingIntent pendingIntent2 = (PendingIntent)param2Bundle.getParcelable("on_read");
      PendingIntent pendingIntent1 = (PendingIntent)param2Bundle.getParcelable("on_reply");
      RemoteInput remoteInput = (RemoteInput)param2Bundle.getParcelable("remote_input");
      String[] arrayOfString2 = param2Bundle.getStringArray("participants");
      return (arrayOfString2 == null || arrayOfString2.length != 1) ? null : new UnreadConversation(arrayOfString1, remoteInput, pendingIntent1, pendingIntent2, arrayOfString2, param2Bundle.getLong("timestamp"));
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$CarExtender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */