package adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phamduyen.bongda.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import bean.menuItem;

/**
 * Created by Pham Duyen on 27/04/2016.
 */
public class Menu_Iteam_Adapter extends ArrayAdapter<menuItem>{
    Activity mContext = null;
    int idLayout;
    ArrayList<menuItem> menuItemArrayList= new ArrayList<>();
    public Menu_Iteam_Adapter(Context context, int resource) {
        super(context, resource);
    }

    public Menu_Iteam_Adapter(Activity mContext, int idLayout, ArrayList<menuItem> funtionList) {
        super(mContext, idLayout, funtionList);
        this.mContext = mContext;
        this.idLayout = idLayout;
        this.menuItemArrayList = funtionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView != null){
            viewHolder=(ViewHolder)convertView.getTag();
        }
        else {
            viewHolder = new ViewHolder();
            LayoutInflater layout = mContext.getLayoutInflater();
            convertView = layout.inflate(idLayout,null);
            viewHolder.menuItem = (ImageView)convertView.findViewById(R.id.imgFunction);
            viewHolder.nameFuntion = (TextView)convertView.findViewById(R.id.textNameFunction);
            convertView.setTag(viewHolder);
        }
        menuItem item = menuItemArrayList.get(position);
        int imageId = this.getMipmapResIdByName(item.getFuntion());
        viewHolder.menuItem.setImageResource(imageId);
        viewHolder.nameFuntion.setText(item.getName());
        notifyDataSetChanged();
        return convertView;
    }

    public int getMipmapResIdByName(String resName) {
        String pkgName = mContext.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = mContext.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("activiry_main_user", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
}
 class  ViewHolder{
     ImageView menuItem;
     TextView nameFuntion;
 }
}