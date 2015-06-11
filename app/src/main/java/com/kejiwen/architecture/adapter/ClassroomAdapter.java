/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.kejiwen.architecture.adapter;

import java.util.ArrayList;
import java.util.List;

import com.shizhefei.view.listviewhelper.IDataAdapter;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.activity.CommonWebViewActivity;
import com.kejiwen.architecture.model.ClassroomItem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassroomAdapter extends BaseAdapter implements IDataAdapter<List<ClassroomItem>> {
    private List<ClassroomItem> mClassroomItems = new ArrayList<ClassroomItem>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ClassroomAdapter(Context context) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mClassroomItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ClassroomItem item = mClassroomItems.get(position);
        ItemHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.classroom_listview_item, parent, false);
            holder = new ItemHolder();
            holder.dateTv = (TextView) convertView.findViewById(R.id.classroom_list_item_date);
            holder.titleTv = (TextView) convertView.findViewById(R.id.classroom_list_item_title);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }
        holder.titleTv.setText(mClassroomItems.get(position).getTitle());
        holder.dateTv.setText(mClassroomItems.get(position).getDate());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext, CommonWebViewActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                it.putExtra("url", item.getUrl());
                mContext.startActivity(it);
            }
        });
        return convertView;
    }

    @Override
    public void setData(List<ClassroomItem> data, boolean isRefresh) {
        if (isRefresh) {
            mClassroomItems.clear();
        }
        mClassroomItems.addAll(data);
    }

    @Override
    public List<ClassroomItem> getData() {
        return mClassroomItems;
    }

    public static class ItemHolder {
        TextView titleTv;
        TextView dateTv;
    }
}
