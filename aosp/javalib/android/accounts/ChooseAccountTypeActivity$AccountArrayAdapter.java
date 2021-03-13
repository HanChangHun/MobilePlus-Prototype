package android.accounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class AccountArrayAdapter extends ArrayAdapter<ChooseAccountTypeActivity.AuthInfo> {
  private ArrayList<ChooseAccountTypeActivity.AuthInfo> mInfos;
  
  private LayoutInflater mLayoutInflater;
  
  public AccountArrayAdapter(Context paramContext, int paramInt, ArrayList<ChooseAccountTypeActivity.AuthInfo> paramArrayList) {
    super(paramContext, paramInt, paramArrayList);
    this.mInfos = paramArrayList;
    this.mLayoutInflater = (LayoutInflater)paramContext.getSystemService("layout_inflater");
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    ChooseAccountTypeActivity.ViewHolder viewHolder;
    if (paramView == null) {
      paramView = this.mLayoutInflater.inflate(17367120, null);
      viewHolder = new ChooseAccountTypeActivity.ViewHolder(null);
      viewHolder.text = (TextView)paramView.findViewById(16908694);
      viewHolder.icon = (ImageView)paramView.findViewById(16908693);
      paramView.setTag(viewHolder);
    } else {
      viewHolder = (ChooseAccountTypeActivity.ViewHolder)paramView.getTag();
    } 
    viewHolder.text.setText(((ChooseAccountTypeActivity.AuthInfo)this.mInfos.get(paramInt)).name);
    viewHolder.icon.setImageDrawable(((ChooseAccountTypeActivity.AuthInfo)this.mInfos.get(paramInt)).drawable);
    return paramView;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseAccountTypeActivity$AccountArrayAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */