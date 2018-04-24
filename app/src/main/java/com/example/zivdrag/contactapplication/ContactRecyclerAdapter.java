package com.example.zivdrag.contactapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactViewHolder>{

    private List<ContactPeople> mList;
    private Context mContext;
    private FragmentManager mManager;


    public ContactsRecyclerAdapter(List<ContactPeople> list, Context mContext, FragmentManager manager){
        this.mList = list;
        this.mContext = mContext;
        this.mManager = manager;


    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_recyclerview_item, null);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {

        final ContactPeople item = mList.get(position);

        holder.txtDisplay.setText(item.getName());
        holder.txtDisplay.setContentDescription(item.getName());

        holder.mLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Fragment fragment = new ContactScreenFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("contact", item);
                fragment.setArguments(bundle);

                mManager.beginTransaction().replace(R.id.main_screen,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        View mLayout;
        TextView txtDisplay;

        public ContactViewHolder(View itemView) {
            super(itemView);

            mLayout = itemView;
            txtDisplay = itemView.findViewById(R.id.nameInList);
        }
    }
}