package android.app;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class Message {
  static final String KEY_DATA_MIME_TYPE = "type";
  
  static final String KEY_DATA_URI = "uri";
  
  static final String KEY_EXTRAS_BUNDLE = "extras";
  
  static final String KEY_REMOTE_INPUT_HISTORY = "remote_input_history";
  
  static final String KEY_SENDER = "sender";
  
  static final String KEY_SENDER_PERSON = "sender_person";
  
  public static final String KEY_TEXT = "text";
  
  static final String KEY_TIMESTAMP = "time";
  
  private String mDataMimeType;
  
  private Uri mDataUri;
  
  private Bundle mExtras = new Bundle();
  
  private final boolean mRemoteInputHistory;
  
  private final Person mSender;
  
  private final CharSequence mText;
  
  private final long mTimestamp;
  
  public Message(CharSequence paramCharSequence, long paramLong, Person paramPerson) {
    this(paramCharSequence, paramLong, paramPerson, false);
  }
  
  public Message(CharSequence paramCharSequence, long paramLong, Person paramPerson, boolean paramBoolean) {
    this.mText = Notification.safeCharSequence(paramCharSequence);
    this.mTimestamp = paramLong;
    this.mSender = paramPerson;
    this.mRemoteInputHistory = paramBoolean;
  }
  
  public Message(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2) {
    this(paramCharSequence1, paramLong, person);
  }
  
  static Bundle[] getBundleArrayForMessages(List<Message> paramList) {
    Bundle[] arrayOfBundle = new Bundle[paramList.size()];
    int i = paramList.size();
    for (byte b = 0; b < i; b++)
      arrayOfBundle[b] = ((Message)paramList.get(b)).toBundle(); 
    return arrayOfBundle;
  }
  
  public static Message getMessageFromBundle(Bundle paramBundle) {
    try {
      if (!paramBundle.containsKey("text") || !paramBundle.containsKey("time"))
        return null; 
      Person person = (Person)paramBundle.getParcelable("sender_person");
      if (person == null) {
        CharSequence charSequence = paramBundle.getCharSequence("sender");
        if (charSequence != null) {
          Person.Builder builder = new Person.Builder();
          this();
          person = builder.setName(charSequence).build();
        } 
      } 
      Message message = new Message();
      this(paramBundle.getCharSequence("text"), paramBundle.getLong("time"), person, paramBundle.getBoolean("remote_input_history", false));
      if (paramBundle.containsKey("type") && paramBundle.containsKey("uri"))
        message.setData(paramBundle.getString("type"), (Uri)paramBundle.getParcelable("uri")); 
      if (paramBundle.containsKey("extras"))
        message.getExtras().putAll(paramBundle.getBundle("extras")); 
      return message;
    } catch (ClassCastException classCastException) {
      return null;
    } 
  }
  
  public static List<Message> getMessagesFromBundleArray(Parcelable[] paramArrayOfParcelable) {
    if (paramArrayOfParcelable == null)
      return new ArrayList<>(); 
    ArrayList<Message> arrayList = new ArrayList(paramArrayOfParcelable.length);
    for (byte b = 0; b < paramArrayOfParcelable.length; b++) {
      if (paramArrayOfParcelable[b] instanceof Bundle) {
        Message message = getMessageFromBundle((Bundle)paramArrayOfParcelable[b]);
        if (message != null)
          arrayList.add(message); 
      } 
    } 
    return arrayList;
  }
  
  public String getDataMimeType() {
    return this.mDataMimeType;
  }
  
  public Uri getDataUri() {
    return this.mDataUri;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public CharSequence getSender() {
    CharSequence charSequence;
    Person person = this.mSender;
    if (person == null) {
      person = null;
    } else {
      charSequence = person.getName();
    } 
    return charSequence;
  }
  
  public Person getSenderPerson() {
    return this.mSender;
  }
  
  public CharSequence getText() {
    return this.mText;
  }
  
  public long getTimestamp() {
    return this.mTimestamp;
  }
  
  public boolean isRemoteInputHistory() {
    return this.mRemoteInputHistory;
  }
  
  public Message setData(String paramString, Uri paramUri) {
    this.mDataMimeType = paramString;
    this.mDataUri = paramUri;
    return this;
  }
  
  public Bundle toBundle() {
    Bundle bundle1 = new Bundle();
    CharSequence charSequence = this.mText;
    if (charSequence != null)
      bundle1.putCharSequence("text", charSequence); 
    bundle1.putLong("time", this.mTimestamp);
    Person person = this.mSender;
    if (person != null) {
      bundle1.putCharSequence("sender", Notification.safeCharSequence(person.getName()));
      bundle1.putParcelable("sender_person", this.mSender);
    } 
    String str = this.mDataMimeType;
    if (str != null)
      bundle1.putString("type", str); 
    Uri uri = this.mDataUri;
    if (uri != null)
      bundle1.putParcelable("uri", (Parcelable)uri); 
    Bundle bundle2 = this.mExtras;
    if (bundle2 != null)
      bundle1.putBundle("extras", bundle2); 
    boolean bool = this.mRemoteInputHistory;
    if (bool)
      bundle1.putBoolean("remote_input_history", bool); 
    return bundle1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$MessagingStyle$Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */