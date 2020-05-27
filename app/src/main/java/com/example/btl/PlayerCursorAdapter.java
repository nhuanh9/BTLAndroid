package com.example.btl;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class PlayerCursorAdapter extends ResourceCursorAdapter {
    public PlayerCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
//        TextView textViewId = view.findViewById(R.id.textViewId);
//        textViewId.setText(cursor.getString(cursor.getColumnIndex(DBHelper.getID())));
        TextView textViewName = view.findViewById(R.id.textViewName);
        textViewName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.getNAME())));
        TextView textViewNamSinh = view.findViewById(R.id.textViewNamSinh);
        textViewNamSinh.setText(cursor.getString(cursor.getColumnIndex(DBHelper.getScore())));
    }
}
