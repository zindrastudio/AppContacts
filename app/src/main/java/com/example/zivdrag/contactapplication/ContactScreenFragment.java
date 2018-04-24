package com.example.zivdrag.contactapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ContactScreenFragment extends Fragment {

    private ImageView mPhoto;
    private TextView mName;
    private TextView mPhone;
    private ContactPeople mItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.single_contact, container, false);

        initComponent(view);

        initComponent(view);
        Bundle bundle = getArguments();
        mItem = bundle.getParcelable("contact");
        mName.setText(mItem.getName());
        mPhone.setText(mItem.getPhone());
        loadImage(mItem);

        return view;
    }

    private void loadImage(ContactPeople item) {
        Picasso.get().load(item.getmPhotoURI()).into(mPhoto);
    }
    private void initComponent(View view) {
        mName = view.findViewById(R.id.textViewName);
        mPhone = view.findViewById(R.id.textViewPhone);
        mPhoto = view.findViewById(R.id.imageViewPhoto);
    }
}
