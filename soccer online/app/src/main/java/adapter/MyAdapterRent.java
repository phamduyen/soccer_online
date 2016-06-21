package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.phamduyen.bongda.R;
import com.example.phamduyen.bongda.RentPitchActivity;

import java.io.Serializable;
import java.util.ArrayList;

import bean.Pitch;
import bean.User;

/**
 * Created by Pham Duyen on 23/04/2016.
 */
public class MyAdapterRent extends ArrayAdapter<Pitch> {
    Activity mContext = null;
    int idLayout;
    ArrayList<Pitch> arrlistPitch = new ArrayList<>();
    String day, time,idTime;
    Bundle extras;
    public MyAdapterRent(Context context, int resource) {
        super(context, resource);
    }

    public MyAdapterRent(Activity mContext, int idlayout, ArrayList<Pitch> arrpitch, String day, String time, String idTime)
    {
        super(mContext, idlayout, arrpitch);
        this.mContext=mContext;
        this.idLayout=idlayout;
        this.arrlistPitch=arrpitch;
        this.day = day;
        this.time = time;
        this.idTime = idTime;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null ;
        if (view!= null){
            viewHolder=(ViewHolder) view.getTag();
        }
        else {
            viewHolder =  new ViewHolder();
            LayoutInflater layout = mContext.getLayoutInflater();
            view=layout.inflate(idLayout,null);

            viewHolder.tvname= (TextView) view.findViewById(R.id.textName);
            viewHolder.tvaddress=(TextView) view.findViewById(R.id.textaddress);
            viewHolder.imaview=(ImageView) view.findViewById(R.id.image);
            /*viewHolder.xem= (Button)view.findViewById(R.id.btnXoa);*/

            view.setTag(viewHolder);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext,RentPitchActivity.class);
                Bundle bundle = new Bundle();
                Pitch p = new Pitch();
                User user = new User();
                user = arrlistPitch.get(position).getUser();
               // p.setUser(user);
                p.setName(arrlistPitch.get(position).getName());
                p.setCost(arrlistPitch.get(position).getCost());
                p.setIDPitch(arrlistPitch.get(position).getIDPitch());
                bundle.putSerializable("pitch", (Serializable) p);
                in.putExtra("day", day);
                in.putExtra("time",time);
                in.putExtra("address", user.getAddress());
                in.putExtra("idTime",idTime);
                in.putExtras(bundle);
                mContext.startActivity(in);
            }
        });

        Pitch contact= arrlistPitch.get(position);
        viewHolder.tvname.setText("\t\tName : " + contact.getName());
        viewHolder.tvaddress.setText("\t\tAddress : " + contact.getUser().getAddress());
        notifyDataSetChanged();

        return view;
    }

    class ViewHolder{
        TextView tvname;
       // Button xem;
        ImageView imaview;
        TextView tvaddress;
    }


}
