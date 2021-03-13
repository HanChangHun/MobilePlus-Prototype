package android.database;

public final class CharArrayBuffer {
  public char[] data;
  
  public int sizeCopied;
  
  public CharArrayBuffer(int paramInt) {
    this.data = new char[paramInt];
  }
  
  public CharArrayBuffer(char[] paramArrayOfchar) {
    this.data = paramArrayOfchar;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CharArrayBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */