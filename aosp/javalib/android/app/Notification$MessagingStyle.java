package android.app;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.ArraySet;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class MessagingStyle extends Notification.Style {
  public static final int CONVERSATION_TYPE_IMPORTANT = 2;
  
  public static final int CONVERSATION_TYPE_LEGACY = 0;
  
  public static final int CONVERSATION_TYPE_NORMAL = 1;
  
  public static final int MAXIMUM_RETAINED_MESSAGES = 25;
  
  CharSequence mConversationTitle;
  
  int mConversationType = 0;
  
  List<Message> mHistoricMessages = new ArrayList<>();
  
  boolean mIsGroupConversation;
  
  List<Message> mMessages = new ArrayList<>();
  
  Icon mShortcutIcon;
  
  int mUnreadMessageCount;
  
  Person mUser;
  
  MessagingStyle() {}
  
  public MessagingStyle(Person paramPerson) {
    this.mUser = paramPerson;
  }
  
  public MessagingStyle(CharSequence paramCharSequence) {
    this((new Person.Builder()).setName(paramCharSequence).build());
  }
  
  private CharSequence createConversationTitleFromMessages() {
    ArraySet arraySet = new ArraySet();
    byte b;
    for (b = 0; b < this.mMessages.size(); b++) {
      Person person = ((Message)this.mMessages.get(b)).getSenderPerson();
      if (person != null)
        arraySet.add(person.getName()); 
    } 
    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    int i = arraySet.size();
    for (b = 0; b < i; b++) {
      CharSequence charSequence = (CharSequence)arraySet.valueAt(b);
      if (!TextUtils.isEmpty((CharSequence)spannableStringBuilder))
        spannableStringBuilder.append(", "); 
      spannableStringBuilder.append(BidiFormatter.getInstance().unicodeWrap(charSequence));
    } 
    return (CharSequence)spannableStringBuilder;
  }
  
  private Message findLatestIncomingMessage() {
    return findLatestIncomingMessage(this.mMessages);
  }
  
  public static Message findLatestIncomingMessage(List<Message> paramList) {
    for (int i = paramList.size() - 1; i >= 0; i--) {
      Message message = paramList.get(i);
      if (message.mSender != null && !TextUtils.isEmpty(message.mSender.getName()))
        return message; 
    } 
    return !paramList.isEmpty() ? paramList.get(paramList.size() - 1) : null;
  }
  
  private void fixTitleAndTextExtras(Bundle paramBundle) {
    CharSequence charSequence1;
    CharSequence charSequence2;
    Message message = findLatestIncomingMessage();
    Person person = null;
    if (message == null) {
      charSequence2 = null;
    } else {
      charSequence2 = message.mText;
    } 
    if (message != null) {
      if (message.mSender == null || TextUtils.isEmpty(message.mSender.getName())) {
        person = this.mUser;
      } else {
        person = message.mSender;
      } 
      charSequence1 = person.getName();
    } 
    if (!TextUtils.isEmpty(this.mConversationTitle))
      if (!TextUtils.isEmpty(charSequence1)) {
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        charSequence1 = Notification.Builder.access$3500(this.mBuilder).getString(17040713, new Object[] { bidiFormatter.unicodeWrap(this.mConversationTitle), bidiFormatter.unicodeWrap(charSequence1) });
      } else {
        charSequence1 = this.mConversationTitle;
      }  
    if (charSequence1 != null)
      paramBundle.putCharSequence("android.title", charSequence1); 
    if (charSequence2 != null)
      paramBundle.putCharSequence("android.text", charSequence2); 
  }
  
  private boolean hasOnlyWhiteSpaceSenders() {
    for (byte b = 0; b < this.mMessages.size(); b++) {
      Person person = ((Message)this.mMessages.get(b)).getSenderPerson();
      if (person != null && !isWhiteSpace(person.getName()))
        return false; 
    } 
    return true;
  }
  
  private boolean isWhiteSpace(CharSequence paramCharSequence) {
    if (TextUtils.isEmpty(paramCharSequence))
      return true; 
    if (paramCharSequence.toString().matches("^\\s*$"))
      return true; 
    for (byte b = 0; b < paramCharSequence.length(); b++) {
      if (paramCharSequence.charAt(b) != '​')
        return false; 
    } 
    return true;
  }
  
  private static TextAppearanceSpan makeFontColorSpan(int paramInt) {
    return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(paramInt), null);
  }
  
  private RemoteViews makeMessagingView(boolean paramBoolean1, boolean paramBoolean2) {
    CharSequence charSequence1;
    int j;
    CharSequence charSequence3;
    boolean bool2;
    int k;
    if (!TextUtils.isEmpty(Notification.Style.access$3400(this))) {
      charSequence1 = Notification.Style.access$3400(this);
    } else {
      charSequence1 = this.mConversationTitle;
    } 
    int i = (Notification.Builder.access$3500(this.mBuilder).getApplicationInfo()).targetSdkVersion;
    boolean bool1 = true;
    if (i >= 28) {
      i = 1;
    } else {
      i = 0;
    } 
    CharSequence charSequence2 = null;
    if (i == 0) {
      boolean bool = TextUtils.isEmpty(charSequence1);
      charSequence3 = charSequence1;
      if (hasOnlyWhiteSpaceSenders()) {
        bool = true;
        charSequence3 = null;
        charSequence2 = charSequence1;
      } 
    } else {
      j = isGroupConversation() ^ true;
      charSequence3 = charSequence1;
    } 
    if (this.mConversationType != 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (this.mConversationType == 2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Icon icon = Notification.access$1400(Notification.Builder.access$400(this.mBuilder));
    Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult(null);
    Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().hasProgress(false).title(charSequence3).text(null);
    boolean bool3 = bool1;
    if (!paramBoolean2)
      if (j != 0) {
        bool3 = bool1;
      } else {
        bool3 = false;
      }  
    standardTemplateParams = standardTemplateParams.hideLargeIcon(bool3).hideReplyIcon(paramBoolean2).headerTextSecondary(charSequence3);
    Notification.Builder builder = this.mBuilder;
    if (i != 0) {
      k = Notification.Builder.access$3800(this.mBuilder);
    } else {
      k = Notification.Builder.access$3900(this.mBuilder);
    } 
    RemoteViews remoteViews = Notification.Builder.access$2400(builder, k, standardTemplateParams, templateBindResult);
    addExtras((Notification.Builder.access$400(this.mBuilder)).extras);
    if (i == 0)
      remoteViews.setViewLayoutMarginEnd(16909239, templateBindResult.getIconMarginEnd()); 
    if (Notification.Builder.access$4000(this.mBuilder, standardTemplateParams)) {
      k = this.mBuilder.getPrimaryTextColor(standardTemplateParams);
    } else {
      k = this.mBuilder.resolveContrastColor(standardTemplateParams);
    } 
    remoteViews.setInt(16909478, "setLayoutColor", k);
    remoteViews.setInt(16909478, "setSenderTextColor", this.mBuilder.getPrimaryTextColor(standardTemplateParams));
    remoteViews.setInt(16909478, "setMessageTextColor", this.mBuilder.getSecondaryTextColor(standardTemplateParams));
    remoteViews.setInt(16909478, "setNotificationBackgroundColor", Notification.Builder.access$4100(this.mBuilder, standardTemplateParams));
    remoteViews.setBoolean(16909478, "setIsCollapsed", paramBoolean1);
    remoteViews.setIcon(16909478, "setAvatarReplacement", Notification.access$1400(Notification.Builder.access$400(this.mBuilder)));
    remoteViews.setCharSequence(16909478, "setNameReplacement", charSequence2);
    remoteViews.setBoolean(16909478, "setIsOneToOne", j);
    remoteViews.setCharSequence(16909478, "setConversationTitle", charSequence3);
    if (i != 0) {
      remoteViews.setIcon(16909478, "setShortcutIcon", this.mShortcutIcon);
      remoteViews.setBoolean(16909478, "setIsImportantConversation", bool2);
    } 
    remoteViews.setIcon(16909478, "setLargeIcon", icon);
    remoteViews.setBundle(16909478, "setData", (Notification.Builder.access$400(this.mBuilder)).extras);
    return remoteViews;
  }
  
  public void addExtras(Bundle paramBundle) {
    super.addExtras(paramBundle);
    Person person = this.mUser;
    if (person != null) {
      paramBundle.putCharSequence("android.selfDisplayName", person.getName());
      paramBundle.putParcelable("android.messagingUser", this.mUser);
    } 
    CharSequence charSequence = this.mConversationTitle;
    if (charSequence != null)
      paramBundle.putCharSequence("android.conversationTitle", charSequence); 
    if (!this.mMessages.isEmpty())
      paramBundle.putParcelableArray("android.messages", (Parcelable[])Message.getBundleArrayForMessages(this.mMessages)); 
    if (!this.mHistoricMessages.isEmpty())
      paramBundle.putParcelableArray("android.messages.historic", (Parcelable[])Message.getBundleArrayForMessages(this.mHistoricMessages)); 
    Icon icon = this.mShortcutIcon;
    if (icon != null)
      paramBundle.putParcelable("android.conversationIcon", (Parcelable)icon); 
    paramBundle.putInt("android.conversationUnreadMessageCount", this.mUnreadMessageCount);
    fixTitleAndTextExtras(paramBundle);
    paramBundle.putBoolean("android.isGroupConversation", this.mIsGroupConversation);
  }
  
  public MessagingStyle addHistoricMessage(Message paramMessage) {
    this.mHistoricMessages.add(paramMessage);
    if (this.mHistoricMessages.size() > 25)
      this.mHistoricMessages.remove(0); 
    return this;
  }
  
  public MessagingStyle addMessage(Message paramMessage) {
    this.mMessages.add(paramMessage);
    if (this.mMessages.size() > 25)
      this.mMessages.remove(0); 
    return this;
  }
  
  public MessagingStyle addMessage(CharSequence paramCharSequence, long paramLong, Person paramPerson) {
    return addMessage(new Message(paramCharSequence, paramLong, paramPerson));
  }
  
  public MessagingStyle addMessage(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2) {
    Person person;
    if (paramCharSequence2 == null) {
      paramCharSequence2 = null;
    } else {
      person = (new Person.Builder()).setName(paramCharSequence2).build();
    } 
    return addMessage(paramCharSequence1, paramLong, person);
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 289
    //   4: aload_0
    //   5: invokevirtual getClass : ()Ljava/lang/Class;
    //   8: aload_1
    //   9: invokevirtual getClass : ()Ljava/lang/Class;
    //   12: if_acmpeq -> 18
    //   15: goto -> 289
    //   18: aload_1
    //   19: checkcast android/app/Notification$MessagingStyle
    //   22: astore_1
    //   23: aload_0
    //   24: invokevirtual getMessages : ()Ljava/util/List;
    //   27: astore_2
    //   28: aload_1
    //   29: invokevirtual getMessages : ()Ljava/util/List;
    //   32: astore_3
    //   33: aload_2
    //   34: ifnull -> 43
    //   37: aload_3
    //   38: astore_1
    //   39: aload_3
    //   40: ifnonnull -> 51
    //   43: new java/util/ArrayList
    //   46: dup
    //   47: invokespecial <init> : ()V
    //   50: astore_1
    //   51: aload_2
    //   52: invokeinterface size : ()I
    //   57: istore #4
    //   59: iload #4
    //   61: aload_1
    //   62: invokeinterface size : ()I
    //   67: if_icmpeq -> 72
    //   70: iconst_1
    //   71: ireturn
    //   72: iconst_0
    //   73: istore #5
    //   75: iload #5
    //   77: iload #4
    //   79: if_icmpge -> 287
    //   82: aload_2
    //   83: iload #5
    //   85: invokeinterface get : (I)Ljava/lang/Object;
    //   90: checkcast android/app/Notification$MessagingStyle$Message
    //   93: astore #6
    //   95: aload_1
    //   96: iload #5
    //   98: invokeinterface get : (I)Ljava/lang/Object;
    //   103: checkcast android/app/Notification$MessagingStyle$Message
    //   106: astore #7
    //   108: aload #6
    //   110: invokevirtual getText : ()Ljava/lang/CharSequence;
    //   113: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   116: aload #7
    //   118: invokevirtual getText : ()Ljava/lang/CharSequence;
    //   121: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   124: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   127: ifne -> 132
    //   130: iconst_1
    //   131: ireturn
    //   132: aload #6
    //   134: invokevirtual getDataUri : ()Landroid/net/Uri;
    //   137: aload #7
    //   139: invokevirtual getDataUri : ()Landroid/net/Uri;
    //   142: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   145: ifne -> 150
    //   148: iconst_1
    //   149: ireturn
    //   150: aload #6
    //   152: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   155: ifnonnull -> 167
    //   158: aload #6
    //   160: invokevirtual getSender : ()Ljava/lang/CharSequence;
    //   163: astore_3
    //   164: goto -> 176
    //   167: aload #6
    //   169: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   172: invokevirtual getName : ()Ljava/lang/CharSequence;
    //   175: astore_3
    //   176: aload_3
    //   177: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   180: astore #8
    //   182: aload #7
    //   184: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   187: ifnonnull -> 199
    //   190: aload #7
    //   192: invokevirtual getSender : ()Ljava/lang/CharSequence;
    //   195: astore_3
    //   196: goto -> 208
    //   199: aload #7
    //   201: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   204: invokevirtual getName : ()Ljava/lang/CharSequence;
    //   207: astore_3
    //   208: aload #8
    //   210: aload_3
    //   211: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   214: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   217: ifne -> 222
    //   220: iconst_1
    //   221: ireturn
    //   222: aload #6
    //   224: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   227: astore_3
    //   228: aconst_null
    //   229: astore #8
    //   231: aload_3
    //   232: ifnonnull -> 240
    //   235: aconst_null
    //   236: astore_3
    //   237: goto -> 249
    //   240: aload #6
    //   242: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   245: invokevirtual getKey : ()Ljava/lang/String;
    //   248: astore_3
    //   249: aload #7
    //   251: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   254: ifnonnull -> 260
    //   257: goto -> 270
    //   260: aload #7
    //   262: invokevirtual getSenderPerson : ()Landroid/app/Person;
    //   265: invokevirtual getKey : ()Ljava/lang/String;
    //   268: astore #8
    //   270: aload_3
    //   271: aload #8
    //   273: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   276: ifne -> 281
    //   279: iconst_1
    //   280: ireturn
    //   281: iinc #5, 1
    //   284: goto -> 75
    //   287: iconst_0
    //   288: ireturn
    //   289: iconst_1
    //   290: ireturn
  }
  
  public CharSequence getConversationTitle() {
    return this.mConversationTitle;
  }
  
  public int getConversationType() {
    return this.mConversationType;
  }
  
  public CharSequence getHeadsUpStatusBarText() {
    CharSequence charSequence;
    if (!TextUtils.isEmpty(Notification.Style.access$3400(this))) {
      charSequence = Notification.Style.access$3400(this);
    } else {
      charSequence = this.mConversationTitle;
    } 
    return (this.mConversationType == 0 && !TextUtils.isEmpty(charSequence) && !hasOnlyWhiteSpaceSenders()) ? charSequence : null;
  }
  
  public List<Message> getHistoricMessages() {
    return this.mHistoricMessages;
  }
  
  public List<Message> getMessages() {
    return this.mMessages;
  }
  
  public Icon getShortcutIcon() {
    return this.mShortcutIcon;
  }
  
  public int getUnreadMessageCount() {
    return this.mUnreadMessageCount;
  }
  
  public Person getUser() {
    return this.mUser;
  }
  
  public CharSequence getUserDisplayName() {
    return this.mUser.getName();
  }
  
  public boolean isGroupConversation() {
    if (this.mBuilder != null && (Notification.Builder.access$3500(this.mBuilder).getApplicationInfo()).targetSdkVersion < 28) {
      boolean bool;
      if (this.mConversationTitle != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return this.mIsGroupConversation;
  }
  
  public RemoteViews makeBigContentView() {
    return makeMessagingView(false, true);
  }
  
  public RemoteViews makeContentView(boolean paramBoolean) {
    Notification.Builder.access$2902(this.mBuilder, Notification.Builder.access$3000(this.mBuilder));
    Notification.Builder.access$3002(this.mBuilder, new ArrayList());
    RemoteViews remoteViews = makeMessagingView(true, false);
    Notification.Builder.access$3002(this.mBuilder, Notification.Builder.access$2900(this.mBuilder));
    Notification.Builder.access$2902(this.mBuilder, null);
    return remoteViews;
  }
  
  public RemoteViews makeHeadsUpContentView(boolean paramBoolean) {
    RemoteViews remoteViews = makeMessagingView(true, true);
    if (this.mConversationType == 0)
      remoteViews.setInt(16909239, "setMaxDisplayedLines", 1); 
    return remoteViews;
  }
  
  protected void restoreFromExtras(Bundle paramBundle) {
    super.restoreFromExtras(paramBundle);
    Person person = (Person)paramBundle.getParcelable("android.messagingUser");
    this.mUser = person;
    if (person == null) {
      CharSequence charSequence = paramBundle.getCharSequence("android.selfDisplayName");
      this.mUser = (new Person.Builder()).setName(charSequence).build();
    } 
    this.mConversationTitle = paramBundle.getCharSequence("android.conversationTitle");
    this.mMessages = Message.getMessagesFromBundleArray(paramBundle.getParcelableArray("android.messages"));
    this.mHistoricMessages = Message.getMessagesFromBundleArray(paramBundle.getParcelableArray("android.messages.historic"));
    this.mIsGroupConversation = paramBundle.getBoolean("android.isGroupConversation");
    this.mUnreadMessageCount = paramBundle.getInt("android.conversationUnreadMessageCount");
    this.mShortcutIcon = (Icon)paramBundle.getParcelable("android.conversationIcon");
  }
  
  public MessagingStyle setConversationTitle(CharSequence paramCharSequence) {
    this.mConversationTitle = paramCharSequence;
    return this;
  }
  
  public MessagingStyle setConversationType(int paramInt) {
    this.mConversationType = paramInt;
    return this;
  }
  
  public MessagingStyle setGroupConversation(boolean paramBoolean) {
    this.mIsGroupConversation = paramBoolean;
    return this;
  }
  
  public MessagingStyle setShortcutIcon(Icon paramIcon) {
    this.mShortcutIcon = paramIcon;
    return this;
  }
  
  public MessagingStyle setUnreadMessageCount(int paramInt) {
    this.mUnreadMessageCount = paramInt;
    return this;
  }
  
  public void validate(Context paramContext) {
    super.validate(paramContext);
    if ((paramContext.getApplicationInfo()).targetSdkVersion >= 28) {
      Person person = this.mUser;
      if (person == null || person.getName() == null)
        throw new RuntimeException("User must be valid and have a name."); 
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ConversationType {}
  
  public static final class Message {
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
    
    public Message(CharSequence param2CharSequence, long param2Long, Person param2Person) {
      this(param2CharSequence, param2Long, param2Person, false);
    }
    
    public Message(CharSequence param2CharSequence, long param2Long, Person param2Person, boolean param2Boolean) {
      this.mText = Notification.safeCharSequence(param2CharSequence);
      this.mTimestamp = param2Long;
      this.mSender = param2Person;
      this.mRemoteInputHistory = param2Boolean;
    }
    
    public Message(CharSequence param2CharSequence1, long param2Long, CharSequence param2CharSequence2) {
      this(param2CharSequence1, param2Long, person);
    }
    
    static Bundle[] getBundleArrayForMessages(List<Message> param2List) {
      Bundle[] arrayOfBundle = new Bundle[param2List.size()];
      int i = param2List.size();
      for (byte b = 0; b < i; b++)
        arrayOfBundle[b] = ((Message)param2List.get(b)).toBundle(); 
      return arrayOfBundle;
    }
    
    public static Message getMessageFromBundle(Bundle param2Bundle) {
      try {
        if (!param2Bundle.containsKey("text") || !param2Bundle.containsKey("time"))
          return null; 
        Person person = (Person)param2Bundle.getParcelable("sender_person");
        if (person == null) {
          CharSequence charSequence = param2Bundle.getCharSequence("sender");
          if (charSequence != null) {
            Person.Builder builder = new Person.Builder();
            this();
            person = builder.setName(charSequence).build();
          } 
        } 
        Message message = new Message();
        this(param2Bundle.getCharSequence("text"), param2Bundle.getLong("time"), person, param2Bundle.getBoolean("remote_input_history", false));
        if (param2Bundle.containsKey("type") && param2Bundle.containsKey("uri"))
          message.setData(param2Bundle.getString("type"), (Uri)param2Bundle.getParcelable("uri")); 
        if (param2Bundle.containsKey("extras"))
          message.getExtras().putAll(param2Bundle.getBundle("extras")); 
        return message;
      } catch (ClassCastException classCastException) {
        return null;
      } 
    }
    
    public static List<Message> getMessagesFromBundleArray(Parcelable[] param2ArrayOfParcelable) {
      if (param2ArrayOfParcelable == null)
        return new ArrayList<>(); 
      ArrayList<Message> arrayList = new ArrayList(param2ArrayOfParcelable.length);
      for (byte b = 0; b < param2ArrayOfParcelable.length; b++) {
        if (param2ArrayOfParcelable[b] instanceof Bundle) {
          Message message = getMessageFromBundle((Bundle)param2ArrayOfParcelable[b]);
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
    
    public Message setData(String param2String, Uri param2Uri) {
      this.mDataMimeType = param2String;
      this.mDataUri = param2Uri;
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$MessagingStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */