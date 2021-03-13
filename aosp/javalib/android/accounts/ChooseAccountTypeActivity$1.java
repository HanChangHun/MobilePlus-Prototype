package android.accounts;

import android.view.View;
import android.widget.AdapterView;

class null implements AdapterView.OnItemClickListener {
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    ChooseAccountTypeActivity chooseAccountTypeActivity = ChooseAccountTypeActivity.this;
    ChooseAccountTypeActivity.access$100(chooseAccountTypeActivity, ((ChooseAccountTypeActivity.AuthInfo)ChooseAccountTypeActivity.access$000(chooseAccountTypeActivity).get(paramInt)).desc.type);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseAccountTypeActivity$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */