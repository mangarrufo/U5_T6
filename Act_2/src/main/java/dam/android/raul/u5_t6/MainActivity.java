package dam.android.raul.u5_t6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dam.android.raul.u5_t6.model.Item;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener, MyAdapter.OnItemLongClickListener {
    public MyContacts myContacts;
    public RecyclerView recyclerView;
    public MyAdapter adapter;
    public TextView tvInf;

    private static String[] PERMISSIONS_CONTACTS = {Manifest.permission.READ_CONTACTS};
    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();

        if (checkPermissions())
            setListAdapter();
    }

    private void setUI() {
        tvInf = findViewById(R.id.tvInformation);
        tvInf.setVisibility(View.INVISIBLE);
        recyclerView = findViewById(R.id.recycleViewContacts);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void setListAdapter() {
        myContacts = new MyContacts(this);

        adapter = new MyAdapter(myContacts, this, this);
        recyclerView.setAdapter(adapter);

        if (myContacts.getCount() > 0)
            findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, MainActivity.PERMISSIONS_CONTACTS, MainActivity.REQUEST_CONTACTS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setListAdapter();
            else
                Toast.makeText(this, getString(R.string.contacts_read_right_required), Toast.LENGTH_LONG).show();

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onItemClick(Item item) {
        tvInf.setVisibility(View.VISIBLE);
        tvInf.setText(item.toString());
    }
    
    @Override
    public boolean onItemLongClick(Item item) {
        tvInf.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(item.getId()));
        intent.setData(uri);
        startActivity(intent);
        return true;
    }
}
