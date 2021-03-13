package android.app.admin;

public class PolicyInfo {
  public final int description;
  
  public final int descriptionForSecondaryUsers;
  
  public final int ident;
  
  public final int label;
  
  public final int labelForSecondaryUsers;
  
  public final String tag;
  
  public PolicyInfo(int paramInt1, String paramString, int paramInt2, int paramInt3) {
    this(paramInt1, paramString, paramInt2, paramInt3, paramInt2, paramInt3);
  }
  
  public PolicyInfo(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.ident = paramInt1;
    this.tag = paramString;
    this.label = paramInt2;
    this.description = paramInt3;
    this.labelForSecondaryUsers = paramInt4;
    this.descriptionForSecondaryUsers = paramInt5;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DeviceAdminInfo$PolicyInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */