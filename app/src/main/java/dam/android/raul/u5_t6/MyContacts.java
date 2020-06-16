package dam.android.raul.u5_t6;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class MyContacts {
    private ArrayList<String> myDataSet;
    private Context context;

    public MyContacts(Context context) {
        this.context = context;
        this.myDataSet = getContacts();
    }

    private ArrayList<String> getContacts() {
        ArrayList<String> contactsList = new ArrayList<String>(1);

        ContentResolver contentResolver = context.getContentResolver();

        String[] projection = new String[]{ContactsContract.Contacts.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        String selectionFilter = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT  NULL ";

        Cursor contactCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection, selectionFilter, null, ContactsContract.Data.DISPLAY_NAME + " ASC");

        if (contactCursor != null) {
            int nameIndex = contactCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int numberIndex = contactCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (contactCursor.moveToNext()) {
                String name = contactCursor.getString(nameIndex);
                String number = contactCursor.getString(numberIndex);

                contactsList.add(name + ": " + number);
            }
            contactCursor.close();
        }

        return contactsList;
    }

    public String getContactData(int position) {
        return myDataSet.get(position);
    }

    public int getCount() {
        return myDataSet.size();
    }
}
