package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Utilizador Convidado on 25/08/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    private int mColorActivity;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param words   A List of Word objects to display in a list
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.mColorActivity = color;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    //R.layout.list_item, parent, false);
                    R.layout.list_item_image_linear, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version number from the current Word object and
        // set this text on the number TextView
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_icon);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        //if (currentWord.getmImagemState() == View.VISIBLE) {
        //    // Get the image resource ID from the current Word object and
        //    // set the image to iconView
        //    iconView.setImageResource(currentWord.getmImageResourceId());
        //}

        //iconView.setVisibility(currentWord.getmImagemState());

        //set view color
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.text_container);
        linearLayout.setBackgroundResource(mColorActivity);

        //or
        /*
            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorActivity);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);
        */
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
