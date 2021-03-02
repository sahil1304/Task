package com.sj.video;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;


public class CustomView extends ArrayAdapter<String> {
    private String[] ID;
    private Activity context;
    private String[] col1;
    private String[] col2;
    private String[] col3;


    public CustomView(Activity context,String[] col1, String[] col2, String[] col3) {
        super(context, com.sj.video.R.layout.activity_custom_view,col1);
        this.context=context;
        this.ID=ID;
        this.col1=col1;
        this.col2=col2;
        this.col3=col3;



    }
    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;



        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(com.sj.video.R.layout.activity_custom_view,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
            String a;
        }
        else
        {
            viewHolder=(ViewHolder)r.getTag();
        }
        String Col1=(col1[position]);
        String Col2=(col2[position]);

        String Count= String.valueOf(position+1);
        viewHolder.col1.setText(Col1);
        viewHolder.col4.setText(Count);

        Glide.with(context) //1
                .load(Col2)
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .placeholder(R.drawable.bgreport)
                .apply(RequestOptions.signatureOf(new ObjectKey(String.valueOf(System.currentTimeMillis()))))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .into(viewHolder.imageView);



        viewHolder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(context,ViewVideo.class);
                context.startActivity(intent);
                variable.link=col3[position];*/
                playvideo(col3[position]);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.side : R.anim.side);
        r.startAnimation(animation);
        lastPosition = position;







        return r;  }
    static class  ViewHolder
    {
        TextView col1,col4;
        LinearLayout l;
        ImageView imageView;

        ViewHolder(View v)
        {
            col1=v.findViewById(R.id.col1);
            col4=v.findViewById(R.id.col4);
            imageView=v.findViewById(R.id.image);
            l=v.findViewById(R.id.layout);



        }
    }



    public void playvideo(String videoUrl)
    {
        Intent intent=new Intent(context,pip.class);
        intent.putExtra("video",videoUrl);
        context.startActivity(intent);
    }




}
