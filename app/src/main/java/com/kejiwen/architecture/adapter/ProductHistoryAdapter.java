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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shizhefei.view.listviewhelper.IDataAdapter;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.activity.CommonWebViewActivity;
import com.kejiwen.architecture.activity.ProductCustomerListActivity;
import com.kejiwen.architecture.model.ProductItem;
import com.kejiwen.architecture.utils.DialogUtils;
import com.kejiwen.architecture.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductHistoryAdapter extends BaseAdapter implements IDataAdapter<List<ProductItem>> {
    private final static String TAG = "ProductHistoryAdapter";

    private List<ProductItem> mProductItems = new ArrayList<ProductItem>();
    private LayoutInflater mInflater;
    private Context mContext;

    public ProductHistoryAdapter(Context context) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mProductItems.size();
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
        final ProductItem item = mProductItems.get(position);
        final ItemHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_history_listview_item, parent, false);
            holder = new ItemHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.product_title);
            holder.daysTv = (TextView) convertView.findViewById(R.id.product_days);
            holder.stateTv = (TextView) convertView.findViewById(R.id.product_state);
            holder.rateTv = (TextView) convertView.findViewById(R.id.product_rate);
            holder.riskTv = (TextView) convertView.findViewById(R.id.product_risk);
            holder.listTv = (Button) convertView.findViewById(R.id.product_list);
            holder.startMoneyTv = (TextView) convertView.findViewById(R.id.product_start_money);
            holder.leftTv = (TextView) convertView.findViewById(R.id.product_left_total);
            holder.detailTv = (Button) convertView.findViewById(R.id.product_detail);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.product_progress);
            holder.tipsTv = (TextView) convertView.findViewById(R.id.product_tips);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }
        holder.daysTv.setText(item.getDays());
        holder.leftTv.setText(item.getLeftMoney() + "/" + item.getTotalMoney());
        holder.rateTv.setText(item.getRate());
        holder.riskTv.setText(item.getRisk());
        holder.startMoneyTv.setText(item.getStartMoney());
        holder.titleTv.setText(item.getTitle());
        holder.stateTv.setText(item.getState());
        int totalMoney = StringUtils.parseInt(StringUtils.getNumbers(item.getTotalMoney()), 0);
        int leftMoney = StringUtils.parseInt(StringUtils.getNumbers(item.getLeftMoney()), 0);
        holder.progressBar.setMax(totalMoney);
        holder.progressBar.setProgress(totalMoney - leftMoney);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailActivity(item);
            }
        });
        holder.detailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.getNoticeDialog(mContext, "公告", item.getNotice()).show();
                holder.tipsTv.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        });
        holder.listTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCustomerListActivity();
            }
        });
        return convertView;
    }

    private void startDetailActivity(final ProductItem item) {
        Intent it = new Intent(mContext, CommonWebViewActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra("url", item.getDetail());
        mContext.startActivity(it);
    }

    private void startCustomerListActivity() {
        Intent it = new Intent(mContext, ProductCustomerListActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra("type", 1);
        mContext.startActivity(it);
    }

    @Override
    public void setData(List<ProductItem> data, boolean isRefresh) {
        if (isRefresh) {
            mProductItems.clear();
        }
        mProductItems.addAll(data);
    }

    @Override
    public List<ProductItem> getData() {
        return mProductItems;
    }

    public static class ItemHolder {
        TextView titleTv;
        TextView daysTv;
        TextView stateTv;
        TextView rateTv;
        Button detailTv;
        TextView riskTv;
        Button listTv;
        TextView tipsTv;
        TextView startMoneyTv;
        TextView leftTv;
        ProgressBar progressBar;
    }
}
