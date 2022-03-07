package com.example.barodegeneratorscanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemLiscScreen extends AppCompatActivity {

    RecyclerView recyclerview;
    ArrayList<Model> list=new ArrayList<>();
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lisc_screen);

        list.add(new Model(getResources().getDrawable(R.drawable.tshirt),"T_SHIRT"));
        list.add(new Model(getResources().getDrawable(R.drawable.frok),"Baby Frok"));
        list.add(new Model(getResources().getDrawable(R.drawable.jense),"Jense Pent"));
        list.add(new Model(getResources().getDrawable(R.drawable.kot),"Upper"));
        list.add(new Model(getResources().getDrawable(R.drawable.laptop),"Laptop"));
        list.add(new Model(getResources().getDrawable(R.drawable.shoes),"Shoes"));
        list.add(new Model(getResources().getDrawable(R.drawable.iphone),"Smart Phone"));
        list.add(new Model(getResources().getDrawable(R.drawable.wallet),"Wallet"));
        list.add(new Model(getResources().getDrawable(R.drawable.watch),"Watche"));
        list.add(new Model(getResources().getDrawable(R.drawable.usb),"USB"));
        list.add(new Model(getResources().getDrawable(R.drawable.twitter),"Twitter"));
        list.add(new Model(getResources().getDrawable(R.drawable.facebook),"Facebook"));
        list.add(new Model(getResources().getDrawable(R.drawable.glases),"Glasses"));
        list.add(new Model(getResources().getDrawable(R.drawable.key_chain),"KeyChain"));
        list.add(new Model(getResources().getDrawable(R.drawable.keychain),"USB Keychain"));

        recyclerview=findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter=new Adapter(ItemLiscScreen.this,list);
        recyclerview.setAdapter(adapter);

    }

    private class Model
    {

        Drawable image;
        String name;

        public Model(Drawable image, String name) {
            this.image = image;
            this.name = name;
        }

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
    {
        ArrayList<Model> list=new ArrayList<>();
        Context context;

        public Adapter(Context context, ArrayList<Model> list) {
            this.context=context;
            this.list=list;
        }

        private void Update(ArrayList<Model> list)
        {
            this.list=list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.imgview.setImageDrawable(list.get(position).getImage());
            holder.name.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(context,Barcode_generated.class);
                    intent.putExtra("name",list.get(position).getName());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgview;
            TextView name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imgview=itemView.findViewById(R.id.imgview);
                name=itemView.findViewById(R.id.name);

            }
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}