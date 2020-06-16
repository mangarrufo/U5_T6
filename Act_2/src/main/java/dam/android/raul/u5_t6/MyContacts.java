package dam.android.raul.u5_t6;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

import dam.android.raul.u5_t6.model.Item;

public class MyContacts {
    private ArrayList<Item> myDataSet;
    private Context context;

    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();
    }

    private ArrayList<Item> getContacts() {

        ArrayList<Item> contactsList = new ArrayList<Item>(1);

        ContentResolver contentResolver = context.getContentResolver();


        String[] projection = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY,
                ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID,
                ContactsContract.Data.PHOTO_THUMBNAIL_URI,
                ContactsContract.CommonDataKinds.Phone.TYPE};

        String selectionFilter = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT  NULL ";

        Cursor contactCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionFilter, null, ContactsContract.Data.DISPLAY_NAME + " ASC");


        if (contactCursor != null) {
            int nameIndex = contactCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int numberIndex = contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int contactId = contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
            int lookupKey = contactCursor.getColumnIndexOrThrow(ContactsContract.Data.LOOKUP_KEY);
            int rawContactID = contactCursor.getColumnIndexOrThrow(ContactsContract.Data.RAW_CONTACT_ID);
            int photoProfile = contactCursor.getColumnIndexOrThrow(ContactsContract.Data.PHOTO_THUMBNAIL_URI);
            int type = contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE);


            while (contactCursor.moveToNext()) {
                String name = contactCursor.getString(nameIndex);
                String number = contactCursor.getString(numberIndex);
                String contactID = contactCursor.getString(contactId);
                String lookup = contactCursor.getString(lookupKey);
                String rawContact = contactCursor.getString(rawContactID);
                String photo = contactCursor.getString(photoProfile);
                String tye = contactCursor.getString(type);

                contactsList.add(new Item(contactID, name, number, lookup, rawContact, tye, photo));
            }
            contactCursor.close();
        }

        return contactsList;
    }

    public Item getContactData(int position) {
        return myDataSet.get(position);
    }

    public int getCount() {
        return myDataSet.size();
    }
}
