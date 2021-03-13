package android.database.sqlite;

public class DatabaseObjectNotClosedException extends RuntimeException {
  private static final String s = "Application did not close the cursor or database object that was opened here";
  
  public DatabaseObjectNotClosedException() {
    super("Application did not close the cursor or database object that was opened here");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/DatabaseObjectNotClosedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */