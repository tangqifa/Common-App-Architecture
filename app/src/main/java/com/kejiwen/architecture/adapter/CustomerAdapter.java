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

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.view.listviewhelper.IDataAdapter;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.activity.CustomerProductListActivity;
import com.kejiwen.architecture.activity.NoteActivity;
import com.kejiwen.architecture.model.CustomerItem;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends BaseAdapter implements IDataAdapter<List<CustomerItem>> {
    private List<CustomerItem> mCustomerItems = new ArrayList<CustomerItem>();
    private LayoutInflater mInflater;
    private Activity mContext;

    public CustomerAdapter(Activity context) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mCustomerItems.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CustomerItem item = mCustomerItems.get(position);
        ItemHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.customer_listview_item, parent, false);
            holder = new ItemHolder();
            holder.aliasTv = (TextView) convertView.findViewById(R.id.customer_alias);
            holder.nameTv = (TextView) convertView.findViewById(R.id.customer_name);
            holder.editBt = (ImageButton) convertView.findViewById(R.id.customer_edit);
            holder.phoneBt = (ImageButton) convertView.findViewById(R.id.customer_phone);
            holder.sexTv = (TextView) convertView.findViewById(R.id.customer_sex);
            holder.riskTv = (TextView) convertView.findViewById(R.id.customer_risk);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }
        holder.aliasTv.setText(item.getAlias());
        holder.sexTv.setText(item.getSex());
        holder.aliasTv.setText(item.getAlias());
        holder.riskTv.setText(item.getRisk());
        holder.nameTv.setText(item.getName());
        holder.phoneBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + item.getPhone().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                mContext.startActivity(intent);
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CustomerProductListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", item.getAlias() + item.getName());
                intent.putExtra("phone", item.getPhone());
                mContext.startActivity(intent);
            }
        });
        holder.editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("info", item.getAlias());
                intent.putExtra("position", position);
                intent.putExtra("type", 1);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public void setData(List<CustomerItem> data, boolean isRefresh) {
        if (isRefresh) {
            mCustomerItems.clear();
        }
        mCustomerItems.addAll(data);
    }

    @Override
    public List<CustomerItem> getData() {
        return mCustomerItems;
    }

    public static class ItemHolder {
        TextView nameTv;
        TextView aliasTv;
        ImageButton editBt;
        TextView riskTv;
        TextView sexTv;
        ImageButton phoneBt;
    }
}
