package android.content;

import android.net.Uri;
import android.os.Parcel;
import android.util.proto.ProtoOutputStream;

public final class AuthorityEntry {
  private final String mHost;
  
  private final String mOrigHost;
  
  private final int mPort;
  
  private final boolean mWild;
  
  AuthorityEntry(Parcel paramParcel) {
    boolean bool;
    this.mOrigHost = paramParcel.readString();
    this.mHost = paramParcel.readString();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mWild = bool;
    this.mPort = paramParcel.readInt();
  }
  
  public AuthorityEntry(String paramString1, String paramString2) {
    this.mOrigHost = paramString1;
    int i = paramString1.length();
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (i > 0) {
      bool2 = bool1;
      if (paramString1.charAt(0) == '*')
        bool2 = true; 
    } 
    this.mWild = bool2;
    if (bool2)
      paramString1 = paramString1.substring(1).intern(); 
    this.mHost = paramString1;
    if (paramString2 != null) {
      i = Integer.parseInt(paramString2);
    } else {
      i = -1;
    } 
    this.mPort = i;
  }
  
  void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, this.mHost);
    paramProtoOutputStream.write(1133871366146L, this.mWild);
    paramProtoOutputStream.write(1120986464259L, this.mPort);
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof AuthorityEntry) ? match((AuthorityEntry)paramObject) : false;
  }
  
  public String getHost() {
    return this.mOrigHost;
  }
  
  public int getPort() {
    return this.mPort;
  }
  
  public int match(Uri paramUri) {
    return match(paramUri, false);
  }
  
  public int match(Uri paramUri, boolean paramBoolean) {
    String str = paramUri.getHost();
    if (str == null)
      return (paramBoolean && this.mWild) ? 3145728 : -2; 
    if (!paramBoolean || !"*".equals(str)) {
      String str1 = str;
      if (this.mWild) {
        if (str.length() < this.mHost.length())
          return -2; 
        str1 = str.substring(str.length() - this.mHost.length());
      } 
      if (str1.compareToIgnoreCase(this.mHost) != 0)
        return -2; 
    } 
    if (!paramBoolean) {
      int i = this.mPort;
      if (i >= 0)
        return (i != paramUri.getPort()) ? -2 : 4194304; 
    } 
    return 3145728;
  }
  
  public boolean match(AuthorityEntry paramAuthorityEntry) {
    return (this.mWild != paramAuthorityEntry.mWild) ? false : (!this.mHost.equals(paramAuthorityEntry.mHost) ? false : (!(this.mPort != paramAuthorityEntry.mPort)));
  }
  
  void writeToParcel(Parcel paramParcel) {
    paramParcel.writeString(this.mOrigHost);
    paramParcel.writeString(this.mHost);
    paramParcel.writeInt(this.mWild);
    paramParcel.writeInt(this.mPort);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentFilter$AuthorityEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */