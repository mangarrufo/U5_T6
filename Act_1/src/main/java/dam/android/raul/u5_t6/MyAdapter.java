package dam.android.raul.u5_t6;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dam.android.raul.u5_t6.model.Item;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyContacts myContacts;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvID;
        TextView tvName;
        TextView tvNumber;
        ImageView ivPhoto;

        public MyViewHolder(View view) {
            super(view);
            this.view = view.findViewById(R.id.lyItem);
            this.tvID = view.findViewById(R.id.tvId);
            this.tvName = view.findViewById(R.id.tvName);
            this.tvNumber = view.findViewById(R.id.tvNumber);
            this.ivPhoto = view.findViewById(R.id.ivPhoto);
        }

        public void bind(Item contactData) {
            this.tvID.setText(contactData.getId());
            this.tvName.setText(contactData.getName());
            this.tvNumber.setText(contactData.getNumber());

            if (contactData.getPhoto() != null) {
                this.ivPhoto.setImageURI(Uri.parse(contactData.getPhoto()));
            }
        }
    }

    MyAdapter(MyContacts myContacts) {
        this.myContacts = myContacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(myContacts.getContactData(position));
    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }
}