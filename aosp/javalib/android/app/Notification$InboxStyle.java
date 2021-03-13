package android.app;

import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class InboxStyle extends Notification.Style {
  private static final int NUMBER_OF_HISTORY_ALLOWED_UNTIL_REDUCTION = 1;
  
  private ArrayList<CharSequence> mTexts = new ArrayList<>(5);
  
  public InboxStyle() {}
  
  @Deprecated
  public InboxStyle(Notification.Builder paramBuilder) {
    setBuilder(paramBuilder);
  }
  
  private void handleInboxImageMargin(RemoteViews paramRemoteViews, int paramInt1, boolean paramBoolean, int paramInt2) {
    byte b = 0;
    int i = b;
    if (paramBoolean) {
      Bundle bundle = (Notification.Builder.access$400(this.mBuilder)).extras;
      boolean bool = false;
      i = bundle.getInt("android.progressMax", 0);
      paramBoolean = (Notification.Builder.access$400(this.mBuilder)).extras.getBoolean("android.progressIndeterminate");
      if (i != 0 || paramBoolean)
        bool = true; 
      i = b;
      if (!bool)
        i = paramInt2; 
    } 
    paramRemoteViews.setViewLayoutMarginEnd(paramInt1, i);
  }
  
  public void addExtras(Bundle paramBundle) {
    super.addExtras(paramBundle);
    CharSequence[] arrayOfCharSequence = new CharSequence[this.mTexts.size()];
    paramBundle.putCharSequenceArray("android.textLines", this.mTexts.<CharSequence>toArray(arrayOfCharSequence));
  }
  
  public InboxStyle addLine(CharSequence paramCharSequence) {
    this.mTexts.add(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    if (paramStyle == null || getClass() != paramStyle.getClass())
      return true; 
    InboxStyle inboxStyle = (InboxStyle)paramStyle;
    ArrayList<CharSequence> arrayList1 = getLines();
    ArrayList<CharSequence> arrayList2 = inboxStyle.getLines();
    int i = arrayList1.size();
    if (i != arrayList2.size())
      return true; 
    for (byte b = 0; b < i; b++) {
      if (!Objects.equals(String.valueOf(arrayList1.get(b)), String.valueOf(arrayList2.get(b))))
        return true; 
    } 
    return false;
  }
  
  public ArrayList<CharSequence> getLines() {
    return this.mTexts;
  }
  
  public RemoteViews makeBigContentView() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mBuilder : Landroid/app/Notification$Builder;
    //   4: getfield mParams : Landroid/app/Notification$StandardTemplateParams;
    //   7: invokevirtual reset : ()Landroid/app/Notification$StandardTemplateParams;
    //   10: aload_0
    //   11: getfield mBuilder : Landroid/app/Notification$Builder;
    //   14: invokevirtual fillTextsFrom : (Landroid/app/Notification$Builder;)Landroid/app/Notification$StandardTemplateParams;
    //   17: aconst_null
    //   18: invokevirtual text : (Ljava/lang/CharSequence;)Landroid/app/Notification$StandardTemplateParams;
    //   21: astore_1
    //   22: new android/app/Notification$TemplateBindResult
    //   25: dup
    //   26: aconst_null
    //   27: invokespecial <init> : (Landroid/app/Notification$1;)V
    //   30: astore_2
    //   31: aload_0
    //   32: aload_0
    //   33: getfield mBuilder : Landroid/app/Notification$Builder;
    //   36: invokestatic access$4200 : (Landroid/app/Notification$Builder;)I
    //   39: aload_1
    //   40: aload_2
    //   41: invokevirtual getStandardView : (ILandroid/app/Notification$StandardTemplateParams;Landroid/app/Notification$TemplateBindResult;)Landroid/widget/RemoteViews;
    //   44: astore_3
    //   45: bipush #7
    //   47: newarray int
    //   49: astore #4
    //   51: aload #4
    //   53: dup
    //   54: iconst_0
    //   55: ldc 16909063
    //   57: iastore
    //   58: dup
    //   59: iconst_1
    //   60: ldc 16909064
    //   62: iastore
    //   63: dup
    //   64: iconst_2
    //   65: ldc 16909065
    //   67: iastore
    //   68: dup
    //   69: iconst_3
    //   70: ldc 16909066
    //   72: iastore
    //   73: dup
    //   74: iconst_4
    //   75: ldc 16909067
    //   77: iastore
    //   78: dup
    //   79: iconst_5
    //   80: ldc 16909068
    //   82: iastore
    //   83: dup
    //   84: bipush #6
    //   86: ldc 16909069
    //   88: iastore
    //   89: pop
    //   90: aload #4
    //   92: arraylength
    //   93: istore #5
    //   95: iconst_0
    //   96: istore #6
    //   98: iload #6
    //   100: iload #5
    //   102: if_icmpge -> 122
    //   105: aload_3
    //   106: aload #4
    //   108: iload #6
    //   110: iaload
    //   111: bipush #8
    //   113: invokevirtual setViewVisibility : (II)V
    //   116: iinc #6, 1
    //   119: goto -> 98
    //   122: aload_0
    //   123: getfield mBuilder : Landroid/app/Notification$Builder;
    //   126: invokestatic access$3500 : (Landroid/app/Notification$Builder;)Landroid/content/Context;
    //   129: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   132: ldc 17105366
    //   134: invokevirtual getDimensionPixelSize : (I)I
    //   137: istore #7
    //   139: aload #4
    //   141: arraylength
    //   142: istore #5
    //   144: iload #5
    //   146: istore #6
    //   148: aload_0
    //   149: getfield mBuilder : Landroid/app/Notification$Builder;
    //   152: invokestatic access$3000 : (Landroid/app/Notification$Builder;)Ljava/util/ArrayList;
    //   155: invokevirtual size : ()I
    //   158: ifle -> 167
    //   161: iload #5
    //   163: iconst_1
    //   164: isub
    //   165: istore #6
    //   167: aload_0
    //   168: getfield mBuilder : Landroid/app/Notification$Builder;
    //   171: invokestatic access$400 : (Landroid/app/Notification$Builder;)Landroid/app/Notification;
    //   174: getfield extras : Landroid/os/Bundle;
    //   177: ldc 'android.remoteInputHistoryItems'
    //   179: ldc android/app/RemoteInputHistoryItem
    //   181: invokestatic access$000 : (Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/Class;)[Landroid/os/Parcelable;
    //   184: checkcast [Landroid/app/RemoteInputHistoryItem;
    //   187: astore #8
    //   189: aload #8
    //   191: ifnull -> 282
    //   194: aload #8
    //   196: arraylength
    //   197: iconst_1
    //   198: if_icmple -> 282
    //   201: aload #8
    //   203: arraylength
    //   204: iconst_3
    //   205: invokestatic min : (II)I
    //   208: istore #5
    //   210: aload_0
    //   211: getfield mTexts : Ljava/util/ArrayList;
    //   214: invokevirtual size : ()I
    //   217: iload #5
    //   219: iadd
    //   220: iconst_1
    //   221: isub
    //   222: istore #5
    //   224: iload #5
    //   226: iload #6
    //   228: if_icmple -> 282
    //   231: iload #5
    //   233: iload #6
    //   235: isub
    //   236: istore #9
    //   238: aload_0
    //   239: getfield mTexts : Ljava/util/ArrayList;
    //   242: invokevirtual size : ()I
    //   245: iload #6
    //   247: if_icmple -> 269
    //   250: iconst_0
    //   251: istore #5
    //   253: iconst_1
    //   254: istore #10
    //   256: iconst_0
    //   257: istore #11
    //   259: iload #6
    //   261: iload #9
    //   263: isub
    //   264: istore #6
    //   266: goto -> 291
    //   269: iload #9
    //   271: istore #5
    //   273: iconst_1
    //   274: istore #10
    //   276: iconst_0
    //   277: istore #11
    //   279: goto -> 291
    //   282: iconst_0
    //   283: istore #5
    //   285: iconst_1
    //   286: istore #10
    //   288: iconst_0
    //   289: istore #11
    //   291: iload #5
    //   293: aload_0
    //   294: getfield mTexts : Ljava/util/ArrayList;
    //   297: invokevirtual size : ()I
    //   300: if_icmpge -> 441
    //   303: iload #5
    //   305: iload #6
    //   307: if_icmpge -> 441
    //   310: aload_0
    //   311: getfield mTexts : Ljava/util/ArrayList;
    //   314: iload #5
    //   316: invokevirtual get : (I)Ljava/lang/Object;
    //   319: checkcast java/lang/CharSequence
    //   322: astore #8
    //   324: aload #8
    //   326: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   329: ifne -> 435
    //   332: aload_3
    //   333: aload #4
    //   335: iload #5
    //   337: iaload
    //   338: iconst_0
    //   339: invokevirtual setViewVisibility : (II)V
    //   342: aload_3
    //   343: aload #4
    //   345: iload #5
    //   347: iaload
    //   348: aload_0
    //   349: getfield mBuilder : Landroid/app/Notification$Builder;
    //   352: aload_0
    //   353: getfield mBuilder : Landroid/app/Notification$Builder;
    //   356: aload #8
    //   358: invokestatic access$2600 : (Landroid/app/Notification$Builder;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    //   361: invokestatic access$2700 : (Landroid/app/Notification$Builder;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    //   364: invokevirtual setTextViewText : (ILjava/lang/CharSequence;)V
    //   367: aload_0
    //   368: getfield mBuilder : Landroid/app/Notification$Builder;
    //   371: aload_3
    //   372: aload #4
    //   374: iload #5
    //   376: iaload
    //   377: aload_1
    //   378: invokestatic access$2800 : (Landroid/app/Notification$Builder;Landroid/widget/RemoteViews;ILandroid/app/Notification$StandardTemplateParams;)V
    //   381: aload_3
    //   382: aload #4
    //   384: iload #5
    //   386: iaload
    //   387: iconst_0
    //   388: iload #7
    //   390: iconst_0
    //   391: iconst_0
    //   392: invokevirtual setViewPadding : (IIIII)V
    //   395: aload_0
    //   396: aload_3
    //   397: aload #4
    //   399: iload #5
    //   401: iaload
    //   402: iload #10
    //   404: aload_2
    //   405: invokevirtual getIconMarginEnd : ()I
    //   408: invokespecial handleInboxImageMargin : (Landroid/widget/RemoteViews;IZI)V
    //   411: iload #10
    //   413: ifeq -> 426
    //   416: aload #4
    //   418: iload #5
    //   420: iaload
    //   421: istore #11
    //   423: goto -> 429
    //   426: iconst_0
    //   427: istore #11
    //   429: iconst_0
    //   430: istore #10
    //   432: goto -> 435
    //   435: iinc #5, 1
    //   438: goto -> 291
    //   441: iload #11
    //   443: ifeq -> 470
    //   446: aload_3
    //   447: iload #11
    //   449: iconst_0
    //   450: aload_0
    //   451: getfield mBuilder : Landroid/app/Notification$Builder;
    //   454: invokestatic access$3500 : (Landroid/app/Notification$Builder;)Landroid/content/Context;
    //   457: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   460: ldc 17105383
    //   462: invokevirtual getDimensionPixelSize : (I)I
    //   465: iconst_0
    //   466: iconst_0
    //   467: invokevirtual setViewPadding : (IIIII)V
    //   470: aload_3
    //   471: areturn
  }
  
  protected void restoreFromExtras(Bundle paramBundle) {
    super.restoreFromExtras(paramBundle);
    this.mTexts.clear();
    if (paramBundle.containsKey("android.textLines"))
      Collections.addAll(this.mTexts, paramBundle.getCharSequenceArray("android.textLines")); 
  }
  
  public InboxStyle setBigContentTitle(CharSequence paramCharSequence) {
    internalSetBigContentTitle(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public InboxStyle setSummaryText(CharSequence paramCharSequence) {
    internalSetSummaryText(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$InboxStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */