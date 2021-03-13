package android.app;

import android.widget.SearchView;

class null implements SearchView.OnQueryTextListener {
  public boolean onQueryTextChange(String paramString) {
    return false;
  }
  
  public boolean onQueryTextSubmit(String paramString) {
    SearchDialog.this.dismiss();
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchDialog$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */