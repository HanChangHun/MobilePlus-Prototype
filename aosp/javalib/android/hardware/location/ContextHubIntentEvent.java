package android.hardware.location;

import android.annotation.SystemApi;
import android.content.Intent;
import java.util.Objects;

@SystemApi
public class ContextHubIntentEvent {
  private final ContextHubInfo mContextHubInfo;
  
  private final int mEventType;
  
  private final int mNanoAppAbortCode;
  
  private final long mNanoAppId;
  
  private final NanoAppMessage mNanoAppMessage;
  
  private ContextHubIntentEvent(ContextHubInfo paramContextHubInfo, int paramInt) {
    this(paramContextHubInfo, paramInt, -1L, null, -1);
  }
  
  private ContextHubIntentEvent(ContextHubInfo paramContextHubInfo, int paramInt, long paramLong) {
    this(paramContextHubInfo, paramInt, paramLong, null, -1);
  }
  
  private ContextHubIntentEvent(ContextHubInfo paramContextHubInfo, int paramInt1, long paramLong, int paramInt2) {
    this(paramContextHubInfo, paramInt1, paramLong, null, paramInt2);
  }
  
  private ContextHubIntentEvent(ContextHubInfo paramContextHubInfo, int paramInt, long paramLong, NanoAppMessage paramNanoAppMessage) {
    this(paramContextHubInfo, paramInt, paramLong, paramNanoAppMessage, -1);
  }
  
  private ContextHubIntentEvent(ContextHubInfo paramContextHubInfo, int paramInt1, long paramLong, NanoAppMessage paramNanoAppMessage, int paramInt2) {
    this.mContextHubInfo = paramContextHubInfo;
    this.mEventType = paramInt1;
    this.mNanoAppId = paramLong;
    this.mNanoAppMessage = paramNanoAppMessage;
    this.mNanoAppAbortCode = paramInt2;
  }
  
  public static ContextHubIntentEvent fromIntent(Intent paramIntent) {
    Objects.requireNonNull(paramIntent, "Intent cannot be null");
    hasExtraOrThrow(paramIntent, "android.hardware.location.extra.CONTEXT_HUB_INFO");
    ContextHubInfo contextHubInfo = (ContextHubInfo)paramIntent.getParcelableExtra("android.hardware.location.extra.CONTEXT_HUB_INFO");
    if (contextHubInfo != null) {
      StringBuilder stringBuilder;
      ContextHubIntentEvent contextHubIntentEvent;
      int i = getIntExtraOrThrow(paramIntent, "android.hardware.location.extra.EVENT_TYPE");
      switch (i) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown intent event type ");
          stringBuilder.append(i);
          throw new IllegalArgumentException(stringBuilder.toString());
        case 6:
          return new ContextHubIntentEvent(contextHubInfo, i);
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
          break;
      } 
      long l = getLongExtraOrThrow((Intent)stringBuilder, "android.hardware.location.extra.NANOAPP_ID");
      if (i == 5) {
        hasExtraOrThrow((Intent)stringBuilder, "android.hardware.location.extra.MESSAGE");
        NanoAppMessage nanoAppMessage = (NanoAppMessage)stringBuilder.getParcelableExtra("android.hardware.location.extra.MESSAGE");
        if (nanoAppMessage != null) {
          contextHubIntentEvent = new ContextHubIntentEvent(contextHubInfo, i, l, nanoAppMessage);
        } else {
          throw new IllegalArgumentException("NanoAppMessage extra was null");
        } 
      } else if (i == 4) {
        contextHubIntentEvent = new ContextHubIntentEvent(contextHubInfo, i, l, getIntExtraOrThrow((Intent)contextHubIntentEvent, "android.hardware.location.extra.NANOAPP_ABORT_CODE"));
      } else {
        contextHubIntentEvent = new ContextHubIntentEvent(contextHubInfo, i, l);
      } 
      return contextHubIntentEvent;
    } 
    throw new IllegalArgumentException("ContextHubInfo extra was null");
  }
  
  private static int getIntExtraOrThrow(Intent paramIntent, String paramString) {
    hasExtraOrThrow(paramIntent, paramString);
    return paramIntent.getIntExtra(paramString, -1);
  }
  
  private static long getLongExtraOrThrow(Intent paramIntent, String paramString) {
    hasExtraOrThrow(paramIntent, paramString);
    return paramIntent.getLongExtra(paramString, -1L);
  }
  
  private static void hasExtraOrThrow(Intent paramIntent, String paramString) {
    if (paramIntent.hasExtra(paramString))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Intent did not have extra: ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2;
    boolean bool1 = true;
    if (paramObject == this)
      return true; 
    int i = 0;
    int j = i;
    if (paramObject instanceof ContextHubIntentEvent) {
      paramObject = paramObject;
      j = i;
      if (paramObject.getEventType() == this.mEventType) {
        j = i;
        if (paramObject.getContextHubInfo().equals(this.mContextHubInfo)) {
          j = 1;
          try {
            if (this.mEventType != 6) {
              boolean bool;
              if (paramObject.getNanoAppId() == this.mNanoAppId) {
                bool = true;
              } else {
                bool = false;
              } 
              j = true & bool;
            } 
            i = j;
            if (this.mEventType == 4) {
              int k;
              if (paramObject.getNanoAppAbortCode() == this.mNanoAppAbortCode) {
                k = bool1;
              } else {
                k = 0;
              } 
              i = k & j;
            } 
            j = i;
            if (this.mEventType == 5) {
              int k = paramObject.getNanoAppMessage().equals(this.mNanoAppMessage);
              bool2 = k & i;
            } 
          } catch (UnsupportedOperationException unsupportedOperationException) {
            bool2 = false;
          } 
        } 
      } 
    } 
    return bool2;
  }
  
  public ContextHubInfo getContextHubInfo() {
    return this.mContextHubInfo;
  }
  
  public int getEventType() {
    return this.mEventType;
  }
  
  public int getNanoAppAbortCode() {
    if (this.mEventType == 4)
      return this.mNanoAppAbortCode; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Cannot invoke getNanoAppAbortCode() on non-abort event: ");
    stringBuilder.append(this.mEventType);
    throw new UnsupportedOperationException(stringBuilder.toString());
  }
  
  public long getNanoAppId() {
    if (this.mEventType != 6)
      return this.mNanoAppId; 
    throw new UnsupportedOperationException("Cannot invoke getNanoAppId() on Context Hub reset event");
  }
  
  public NanoAppMessage getNanoAppMessage() {
    if (this.mEventType == 5)
      return this.mNanoAppMessage; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Cannot invoke getNanoAppMessage() on non-message event: ");
    stringBuilder.append(this.mEventType);
    throw new UnsupportedOperationException(stringBuilder.toString());
  }
  
  public String toString() {
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("ContextHubIntentEvent[eventType = ");
    stringBuilder2.append(this.mEventType);
    stringBuilder2.append(", contextHubId = ");
    stringBuilder2.append(this.mContextHubInfo.getId());
    String str1 = stringBuilder2.toString();
    String str2 = str1;
    if (this.mEventType != 6) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(", nanoAppId = 0x");
      stringBuilder.append(Long.toHexString(this.mNanoAppId));
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if (this.mEventType == 4) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append(", nanoAppAbortCode = ");
      stringBuilder.append(this.mNanoAppAbortCode);
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if (this.mEventType == 5) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(", nanoAppMessage = ");
      stringBuilder.append(this.mNanoAppMessage);
      str2 = stringBuilder.toString();
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str2);
    stringBuilder1.append("]");
    return stringBuilder1.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubIntentEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */