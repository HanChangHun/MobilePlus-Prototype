package android.database;

public abstract class AbstractWindowedCursor extends AbstractCursor {
  protected CursorWindow mWindow;
  
  protected void checkPosition() {
    super.checkPosition();
    if (this.mWindow != null)
      return; 
    throw new StaleDataException("Attempting to access a closed CursorWindow.Most probable cause: cursor is deactivated prior to calling this method.");
  }
  
  protected void clearOrCreateWindow(String paramString) {
    CursorWindow cursorWindow = this.mWindow;
    if (cursorWindow == null) {
      this.mWindow = new CursorWindow(paramString);
    } else {
      cursorWindow.clear();
    } 
  }
  
  protected void closeWindow() {
    CursorWindow cursorWindow = this.mWindow;
    if (cursorWindow != null) {
      cursorWindow.close();
      this.mWindow = null;
    } 
  }
  
  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer) {
    checkPosition();
    this.mWindow.copyStringToBuffer(this.mPos, paramInt, paramCharArrayBuffer);
  }
  
  public byte[] getBlob(int paramInt) {
    checkPosition();
    return this.mWindow.getBlob(this.mPos, paramInt);
  }
  
  public double getDouble(int paramInt) {
    checkPosition();
    return this.mWindow.getDouble(this.mPos, paramInt);
  }
  
  public float getFloat(int paramInt) {
    checkPosition();
    return this.mWindow.getFloat(this.mPos, paramInt);
  }
  
  public int getInt(int paramInt) {
    checkPosition();
    return this.mWindow.getInt(this.mPos, paramInt);
  }
  
  public long getLong(int paramInt) {
    checkPosition();
    return this.mWindow.getLong(this.mPos, paramInt);
  }
  
  public short getShort(int paramInt) {
    checkPosition();
    return this.mWindow.getShort(this.mPos, paramInt);
  }
  
  public String getString(int paramInt) {
    checkPosition();
    return this.mWindow.getString(this.mPos, paramInt);
  }
  
  public int getType(int paramInt) {
    checkPosition();
    return this.mWindow.getType(this.mPos, paramInt);
  }
  
  public CursorWindow getWindow() {
    return this.mWindow;
  }
  
  public boolean hasWindow() {
    boolean bool;
    if (this.mWindow != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isBlob(int paramInt) {
    boolean bool;
    if (getType(paramInt) == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isFloat(int paramInt) {
    boolean bool;
    if (getType(paramInt) == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isLong(int paramInt) {
    paramInt = getType(paramInt);
    boolean bool = true;
    if (paramInt != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isNull(int paramInt) {
    boolean bool;
    checkPosition();
    if (this.mWindow.getType(this.mPos, paramInt) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isString(int paramInt) {
    boolean bool;
    if (getType(paramInt) == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected void onDeactivateOrClose() {
    super.onDeactivateOrClose();
    closeWindow();
  }
  
  public void setWindow(CursorWindow paramCursorWindow) {
    if (paramCursorWindow != this.mWindow) {
      closeWindow();
      this.mWindow = paramCursorWindow;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/AbstractWindowedCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */