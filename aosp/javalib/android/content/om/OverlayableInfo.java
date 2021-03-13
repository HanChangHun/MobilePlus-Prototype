package android.content.om;

import android.annotation.NonNull;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;

public final class OverlayableInfo {
  public final String actor;
  
  public final String name;
  
  public OverlayableInfo(String paramString1, String paramString2) {
    this.name = paramString1;
    AnnotationValidations.validate(NonNull.class, null, paramString1);
    this.actor = paramString2;
  }
  
  @Deprecated
  private void __metadata() {}
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.name, ((OverlayableInfo)paramObject).name) || !Objects.equals(this.actor, ((OverlayableInfo)paramObject).actor))
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return (1 * 31 + Objects.hashCode(this.name)) * 31 + Objects.hashCode(this.actor);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/OverlayableInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */