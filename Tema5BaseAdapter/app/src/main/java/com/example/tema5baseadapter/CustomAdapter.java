package com.example.tema5baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> items;
    private int selectedPosition=-1;
    public CustomAdapter(Context context, List<ListItem> items){
        this.context=context;
        this.items=items;
    }
    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemdId(int position){
        return position;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertVuew==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item, parent, );
        }
        ImageView imageView=convertView.findViewById(R.id.itemImage);
        TextView titleTextView=convertView.findViewById(R.id.itemTitle);
        TextView contentTextView=convertView.findViewById(R.id.itemContent);
        RadioButton radioButton= convertView.findViewById(R.id.itemRadioButton);
        ListItem item= items.get(position);
        imageView.setImageResource(item.getImageResId());
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getContent());

        radioButton.setChecked(position==selectedPosition);
        radioButton.setOnClickListener(v->{
            selectedPosition=position;
            notifyDataSetChanged();
        });
        return convertView;
    }
}
