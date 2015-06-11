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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.view.listviewhelper.IDataAdapter;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.activity.NoteActivity;
import com.kejiwen.architecture.model.CustomerItem;

import java.util.ArrayList;
import java.util.List;

public class ProductCustomerHistoryListAdapter extends BaseAdapter implements IDataAdapter<List<CustomerItem>> {
    private List<CustomerItem> mCustomerItems = new ArrayList<CustomerItem>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ProductCustomerHistoryListAdapter(Context context) {
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
        final ItemHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_customer_history_listview_item, parent, false);
            holder = new ItemHolder();
            holder.nameTv = (TextView)convertView.findViewById(R.id.product_customer_name);
            holder.editBt = (ImageButton)convertView.findViewById(R.id.product_customer_edit);
            holder.phoneBt = (ImageButton)convertView.findViewById(R.id.product_customer_phone);
            holder.typeTv = (TextView)convertView.findViewById(R.id.product_customer_type);
            holder.positionTv = (TextView)convertView.findViewById(R.id.product_customer_position);
            holder.aliasTv = (TextView)convertView.findViewById(R.id.product_customer_alias);
            holder.tipTv = (TextView)convertView.findViewById(R.id.product_customer_tips);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }

        holder.typeTv.setText(item.getType());
        holder.nameTv.setText(item.getName());
        holder.positionTv.setText(item.getPosition());
        holder.aliasTv.setText(item.getAlias());
        holder.phoneBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + item.getPhone().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                mContext.startActivity(intent);
            }
        });
        holder.editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("info", item.getNote());
                intent.putExtra("type",4);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });
        if (!TextUtils.isEmpty(item.getNote())) {
            holder.tipTv.setVisibility(View.VISIBLE);
        } else {
            holder.tipTv.setVisibility(View.GONE);
        }
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
        TextView aliasTv;
        TextView nameTv;
        ImageButton editBt;
        TextView typeTv;
        TextView positionTv;
        ImageButton phoneBt;
        TextView tipTv;
    }
}
