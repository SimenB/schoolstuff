package no.nith.pg3200.exercises.exercise4.task2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import no.nith.pg3200.exercises.R;

public class ContactAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Contact> data;

    public ContactAdapter(final ArrayList<Contact> data, final Activity activity) {
        this.data = data;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Way of doing it stolen from here: http://youtu.be/N6YdwzAvwOA
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_list_viewer, null);

            holder = new ViewHolder();

            holder.txtNameView = (TextView) convertView.findViewById(R.id.contactName);
            holder.txtNumberView = (TextView) convertView.findViewById(R.id.contactPhone);
            holder.txtEmailView = (TextView) convertView.findViewById(R.id.contackMail);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        String eøfn = "/%s/^/";


        Contact contact = this.data.get(position);

//        List<String> numbers = contact.getNumbers();
//        List<String> emails = contact.getEmails();
//        holder.txtNameView.setText(contact.getName());
//        holder.txtNumberView.setText(numbers.size() > 0 ? numbers.get(0) : "Nothing");
//        holder.txtEmailView.setText(emails.size() > 0 ? emails.get(0) : "Nothing");


        holder.txtNameView.setText(contact.toString());
        return convertView;
    }

    private static class ViewHolder {
        TextView txtNameView;
        TextView txtNumberView;
        TextView txtEmailView;
    }
}
