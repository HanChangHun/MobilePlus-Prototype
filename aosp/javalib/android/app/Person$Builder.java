package android.app;

import android.graphics.drawable.Icon;

public class Builder {
  private Icon mIcon;
  
  private boolean mIsBot;
  
  private boolean mIsImportant;
  
  private String mKey;
  
  private CharSequence mName;
  
  private String mUri;
  
  public Builder() {}
  
  private Builder(Person paramPerson) {
    this.mName = Person.access$700(paramPerson);
    this.mIcon = Person.access$800(paramPerson);
    this.mUri = Person.access$900(paramPerson);
    this.mKey = Person.access$1000(paramPerson);
    this.mIsBot = Person.access$1100(paramPerson);
    this.mIsImportant = Person.access$1200(paramPerson);
  }
  
  public Person build() {
    return new Person(this, null);
  }
  
  public Builder setBot(boolean paramBoolean) {
    this.mIsBot = paramBoolean;
    return this;
  }
  
  public Builder setIcon(Icon paramIcon) {
    this.mIcon = paramIcon;
    return this;
  }
  
  public Builder setImportant(boolean paramBoolean) {
    this.mIsImportant = paramBoolean;
    return this;
  }
  
  public Builder setKey(String paramString) {
    this.mKey = paramString;
    return this;
  }
  
  public Builder setName(CharSequence paramCharSequence) {
    this.mName = paramCharSequence;
    return this;
  }
  
  public Builder setUri(String paramString) {
    this.mUri = paramString;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Person$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */