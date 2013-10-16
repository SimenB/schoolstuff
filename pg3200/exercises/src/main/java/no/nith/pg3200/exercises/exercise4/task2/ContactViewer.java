package no.nith.pg3200.exercises.exercise4.task2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import no.nith.pg3200.exercises.R;

import static android.provider.ContactsContract.CommonDataKinds.Email;
import static android.provider.ContactsContract.CommonDataKinds.Phone;
import static android.provider.ContactsContract.Contacts;

public class ContactViewer extends Activity {
    private ArrayList<Contact> theList;
    private ContentResolver contentResolver;
    private Context context;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theList = new ArrayList<Contact>();

        contentResolver = getContentResolver();

        context = this;

        new LoadContactsTask().execute(contentResolver.query(Phone.CONTENT_URI, null, null, null, null));

        this.listView = (ListView) findViewById(R.id.contactList);
    }

    private void populateView(final ArrayList<Contact> contacts) {
        this.theList = contacts;

        ContactAdapter adapter = new ContactAdapter(this.theList, this);

        this.listView.setAdapter(adapter);
    }

    private class LoadContactsTask extends AsyncTask<Cursor, Integer, ArrayList<Contact>> {
        ProgressDialog dialog;

        @Override
        protected ArrayList<Contact> doInBackground(Cursor... params) {
            ArrayList<Contact> contacts = new ArrayList<Contact>();
            Cursor cursor = params[0];
            if (cursor.getCount() > 0) {

                while (cursor.moveToNext()) {
                    String contact_id = cursor.getString(cursor.getColumnIndex(Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
                    List<String> phoneNumbers = null, emails = null;

                    int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Contacts.HAS_PHONE_NUMBER)));

                    if (hasPhoneNumber > 0) {

                        // Query and loop for every phone number of the contact
                        Cursor phoneCursor = contentResolver.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = ?", new String[]{contact_id}, null);

                        phoneNumbers = new ArrayList<String>();

                        while (phoneCursor.moveToNext()) {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
                            phoneNumbers.add(phoneNumber);
                        }

                        phoneCursor.close();

                        // Query and loop for every email of the contact
                        Cursor emailCursor = contentResolver.query(Email.CONTENT_URI, null, Email.CONTACT_ID + " = ?", new String[]{contact_id}, null);

                        emails = new ArrayList<String>();
                        while (emailCursor.moveToNext()) {

                            String email = emailCursor.getString(emailCursor.getColumnIndex(Email.DATA));

                            emails.add(email);
                        }

                        emailCursor.close();
                    }

                    contacts.add(new Contact(name, phoneNumbers, emails));
                }
            } else {
                Toast.makeText(context, "No data in cursor", Toast.LENGTH_SHORT).show();
            }

            return contacts;
        }

        @Override
        protected void onPreExecute() {
            setContentView(R.layout.exercise4task2);

            dialog = ProgressDialog.show(context, "Loading", "Loading contacts, please wait", true);

            dialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> arrayList) {
            dialog.dismiss();

            populateView(arrayList);
        }
    }
}

