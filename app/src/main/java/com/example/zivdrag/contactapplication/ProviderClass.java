package com.example.zivdrag.contactapplication;

import android.support.v4.content.CursorLoader;
import android.content.Context;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProviderClass {

    private final Context mContext;

    public ProviderClass(Context context) {

        this.mContext = context;
    }

   public List<ContactPeople> getContactsDisplay() {

       String[] projectionFields = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
               ContactsContract.CommonDataKinds.Phone.NUMBER,
               ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI};

       CursorLoader cursorLoader = new CursorLoader(mContext,
               ContactsContract.CommonDataKinds.Phone.CONTENT_URI, // URI
               projectionFields, // projection fields
               null, // the selection criteria
               null, // the selection args
               null // the sort order
       );

       Cursor cursor = cursorLoader.loadInBackground();

       Set<ContactPeople> contacts = new HashSet<>();

       // Check if cursor is NULL. If TRUE - Move the cursor to first.
       List<ContactPeople> list = null;
       if (cursor != null && cursor.moveToFirst()) {

           do {
               ContactPeople item = new ContactPeople();

               item.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
               item.setPhone(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
               item.setmPhotoURI(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI)));

               contacts.add(item);

           } while (cursor.moveToNext());

           list = new ArrayList<ContactPeople>();
           list.addAll(contacts);
           list = sortContacts(list);

           cursor.close();
       }
       return list;
   }

    private List<ContactPeople> sortContacts(List<ContactPeople> contacts) {

        if (contacts.size() > 0) {
                Collections.sort(contacts, new Comparator<ContactPeople>() {
                    @Override
                    public int compare(final ContactPeople object1, final ContactPeople object2) {
                        return object1.getName().compareTo(object2.getName());
                    }
                });
            }
        return contacts;
    }
}
