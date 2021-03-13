package android.drm;

import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public class DrmSupportInfo {
  private String mDescription = "";
  
  private final ArrayList<String> mFileSuffixList = new ArrayList<>();
  
  private final ArrayList<String> mMimeTypeList = new ArrayList<>();
  
  public void addFileSuffix(String paramString) {
    if (paramString != "") {
      this.mFileSuffixList.add(paramString);
      return;
    } 
    throw new IllegalArgumentException("fileSuffix is an empty string");
  }
  
  public void addMimeType(String paramString) {
    if (paramString != null) {
      if (paramString != "") {
        this.mMimeTypeList.add(paramString);
        return;
      } 
      throw new IllegalArgumentException("mimeType is an empty string");
    } 
    throw new IllegalArgumentException("mimeType is null");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof DrmSupportInfo;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      if (this.mFileSuffixList.equals(((DrmSupportInfo)paramObject).mFileSuffixList) && this.mMimeTypeList.equals(((DrmSupportInfo)paramObject).mMimeTypeList) && this.mDescription.equals(((DrmSupportInfo)paramObject).mDescription))
        bool1 = true; 
      return bool1;
    } 
    return false;
  }
  
  public String getDescriprition() {
    return this.mDescription;
  }
  
  public String getDescription() {
    return this.mDescription;
  }
  
  public Iterator<String> getFileSuffixIterator() {
    return this.mFileSuffixList.iterator();
  }
  
  public Iterator<String> getMimeTypeIterator() {
    return this.mMimeTypeList.iterator();
  }
  
  public int hashCode() {
    return this.mFileSuffixList.hashCode() + this.mMimeTypeList.hashCode() + this.mDescription.hashCode();
  }
  
  boolean isSupportedFileSuffix(String paramString) {
    return this.mFileSuffixList.contains(paramString);
  }
  
  boolean isSupportedMimeType(String paramString) {
    if (paramString != null && !paramString.equals(""))
      for (byte b = 0; b < this.mMimeTypeList.size(); b++) {
        if (((String)this.mMimeTypeList.get(b)).startsWith(paramString))
          return true; 
      }  
    return false;
  }
  
  public void setDescription(String paramString) {
    if (paramString != null) {
      if (paramString != "") {
        this.mDescription = paramString;
        return;
      } 
      throw new IllegalArgumentException("description is an empty string");
    } 
    throw new IllegalArgumentException("description is null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmSupportInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */