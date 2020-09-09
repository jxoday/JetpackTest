package com.jinxin.jetpacktest.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jinxin.jetpacktest.R;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.List;

/**
 * @author JinXin 2020/9/8
 */
public class StudentAdapter extends BaseAdapter {

    private List<Student> data;
    private Context context;

    public StudentAdapter(Context context, List<Student> data) {
        this.data = data;
        this.context = context;
    }

    class ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvAge;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_student, null);
            viewHolder = new ViewHolder();
            viewHolder.tvId = convertView.findViewById(R.id.tvId);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvAge = convertView.findViewById(R.id.tvAge);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvId.setText(String.valueOf(data.get(position).id));
        viewHolder.tvName.setText(data.get(position).name);
        viewHolder.tvAge.setText(String.valueOf(data.get(position).age));
        return convertView;
    }

    @Override
    public int getCount() {

        if (data == null) {
            return 0;
        }

        return data.size();
    }

    @Override
    public Student getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
