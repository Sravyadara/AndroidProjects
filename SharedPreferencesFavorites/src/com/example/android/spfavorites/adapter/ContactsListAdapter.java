package com.example.android.spfavorites.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.android.spfavorites.R;
import com.example.android.spfavorites.beans.Contact;
import com.example.android.spfavorites.utils.SharedPreference;

public class ContactsListAdapter extends ArrayAdapter<Contact> {

	private Context context;
	private List<Contact> contactsList;
	private SharedPreference sharedPreference;

	public ContactsListAdapter(Context context, List<Contact> contacts) {
		super(context, R.layout.contact_list_item, contacts);
		this.context = context;
		this.contactsList = contacts;
		sharedPreference = new SharedPreference();
	}

	private class ViewHolder {
        QuickContactBadge contactPhoto;
		TextView contactName;
		TextView contactNumber;
		ImageView favoriteImg;
	}

	@Override
	public int getCount() {
		return contactsList.size();
	}

	@Override
	public Contact getItem(int position) {
		return contactsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    	    //LayoutInflater inflater = LayoutInflater.from(getContext());

			convertView = inflater.inflate(R.layout.contact_list_item, null);
			holder = new ViewHolder();
			holder.contactPhoto = (QuickContactBadge) convertView.findViewById(R.id.badge);
			holder.contactName = (TextView) convertView.findViewById(R.id.name);
			holder.contactNumber = (TextView) convertView.findViewById(R.id.number);
			holder.favoriteImg = (ImageView) convertView.findViewById(R.id.imgbtn_favorite);			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Contact contact = (Contact) getItem(position);
		holder.contactName.setText(contact.getContactName());
		holder.contactNumber.setText(contact.getPhoneNo());
		
		/*If a contact exists in shared preferences then set heart_red drawable and set a tag*/
		if (checkFavoriteItem(contact)) {
			holder.favoriteImg.setImageResource(R.drawable.heart_red);
			holder.favoriteImg.setTag("red");
		} else {
			holder.favoriteImg.setImageResource(R.drawable.heart_grey);
			holder.favoriteImg.setTag("grey");
		}
		
		return convertView;
	}
	
	/*Checks whether a particular contact exists in SharedPreferences*/
	public boolean checkFavoriteItem(Contact checkContact) {
		boolean check = false;
		List<Contact> favorites = sharedPreference.getFavorites(context);
		if (favorites != null) {
			for (Contact contact : favorites) {
				if (contact.equals(checkContact)) {
					check = true;
					break;
				}
			}
		}
		return check;
	}

	@Override
	public void add(Contact contact) {
		super.add(contact);
		contactsList.add(contact);
		notifyDataSetChanged();
	}

	@Override
	public void remove(Contact contact) {
		super.remove(contact);
		contactsList.remove(contact);
		notifyDataSetChanged();
	}	
}

