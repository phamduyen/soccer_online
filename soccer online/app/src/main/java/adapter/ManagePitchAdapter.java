package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phamduyen.bongda.DetailMatchManage;
import com.example.phamduyen.bongda.Detail_PitchActivity;
import com.example.phamduyen.bongda.R;

import java.io.Serializable;
import java.util.ArrayList;

import bean.Pitch;
import bean.Rent;

/**
 * Created by Pham Duyen on 06/05/2016.
 */
public class ManagePitchAdapter extends ArrayAdapter<Rent> {
    Activity mContext = null;
    int idLayout;
    ArrayList<Rent> arrlistMatch = new ArrayList<>();
    public ManagePitchAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ManagePitchAdapter(Activity mContext,int idlayout,ArrayList<Rent> arrMatch)
    {
        super(mContext, idlayout, arrMatch);
        this.mContext=mContext;
        this.idLayout=idlayout;
        this.arrlistMatch=arrMatch;
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

            viewHolder.tvname= (TextView) view.findViewById(R.id.textNameM);
            viewHolder.tvdate=(TextView) view.findViewById(R.id.textDateM);
            viewHolder.tvtime=(TextView) view.findViewById(R.id.textTimeM);
            viewHolder.imaview=(ImageView) view.findViewById(R.id.image);
           // viewHolder.xem= (Button)view.findViewById(R.id.btnXoa);

            view.setTag(viewHolder);
        }
       view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, DetailMatchManage.class);
                Bundle bundle = new Bundle();
                Rent rent = new Rent();
                rent.setName(arrlistMatch.get(position).getName());
                rent.setPitch(arrlistMatch.get(position).getPitch());
                rent.setDay(arrlistMatch.get(position).getDay());
                rent.setIdRent(arrlistMatch.get(position).getIdRent());
                rent.setRentTime(arrlistMatch.get(position).getRentTime());
                rent.setSdt(arrlistMatch.get(position).getSdt());
                bundle.putSerializable("rent", (Serializable) rent);
                in.putExtras(bundle);
                mContext.startActivity(in);
            }
        });
        Rent contact= arrlistMatch.get(position);
        viewHolder.tvname.setText("\t\tName : " + contact.getPitch().getName());
        viewHolder.tvdate.setText("\t\tDate : " + contact.getDay());
//        viewHolder.tvtime.setText("\t\tTime : " + contact.getRentTime().getTime());
        notifyDataSetChanged();

        return view;
    }

    class ViewHolder{
        TextView tvname;
        TextView tvdate;
        ImageView imaview;
        TextView tvtime;
    }


}
