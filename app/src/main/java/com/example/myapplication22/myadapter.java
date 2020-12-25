package com.example.myapplication22;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;


public class myadapter extends RecyclerView.Adapter <myadapter.myviewholder> {

    Context context;
    ArrayList<NewEvent> NewEvents;

    public myadapter(Context c , ArrayList<NewEvent> E)
    {
        context = c;
        NewEvents = E;
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(context).inflate(R.layout.singlerow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.name1.setText(NewEvents.get(position).getNeweventname());
        holder.date2.setText(NewEvents.get(position).getNeweventdate());
        holder.time2.setText(NewEvents.get(position).getNeweventtime());
        holder.description2.setText(NewEvents.get(position).getNeweventdescription());
        Picasso.get().load(NewEvents.get(position).getNeweventpic()).into(holder.im1);
        Glide.with(holder.im1.getContext()).load(NewEvents.get(position).getNeweventpic()).into(holder.im1);


    }
    @Override
    public int getItemCount() {
        return NewEvents.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView name1,time2,date2,description2;
        CircleImageView im1;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            name1=(TextView)itemView.findViewById(R.id.namevent);
            date2=(TextView)itemView.findViewById(R.id.datef);
            time2=(TextView)itemView.findViewById(R.id.timef);
            description2=(TextView)itemView.findViewById(R.id.despc);
            im1=( CircleImageView)itemView.findViewById((R.id.imevt));
        }
    }
}








