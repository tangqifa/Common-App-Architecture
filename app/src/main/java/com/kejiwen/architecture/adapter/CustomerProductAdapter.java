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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shizhefei.view.listviewhelper.IDataAdapter;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.activity.CommonWebViewActivity;
import com.kejiwen.architecture.activity.NoteActivity;
import com.kejiwen.architecture.model.ProductItem;
import com.kejiwen.architecture.utils.DialogUtils;
import com.kejiwen.architecture.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerProductAdapter extends BaseAdapter implements IDataAdapter<List<ProductItem>> {
    private final static String TAG = "ProductHistoryAdapter";

    private List<ProductItem> mProductItems = new ArrayList<ProductItem>();
    private LayoutInflater mInflater;
    private Context mContext;

    public CustomerProductAdapter(Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ProductItem item = mProductItems.get(position);
        final ItemHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.customer_product_listview_item, parent, false);
            holder = new ItemHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.product_title);
            holder.daysTv = (TextView) convertView.findViewById(R.id.product_days);
            holder.stateTv = (TextView) convertView.findViewById(R.id.product_state);
            holder.rateTv = (TextView) convertView.findViewById(R.id.product_rate);
            holder.riskTv = (TextView) convertView.findViewById(R.id.product_risk);
            holder.positionTv = (TextView) convertView.findViewById(R.id.product_position_money);
            holder.startMoneyTv = (TextView) convertView.findViewById(R.id.product_start_money);
            holder.leftTv = (TextView) convertView.findViewById(R.id.product_left_total);
            holder.detailBt = (Button) convertView.findViewById(R.id.product_detail);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.product_progress);
            holder.interestBt = (Button) convertView.findViewById(R.id.product_no_interest);
            holder.inquiryBt = (Button) convertView.findViewById(R.id.product_inquiry);
            holder.appointmentBt = (Button) convertView.findViewById(R.id.product_appointment);
            holder.signBt = (Button) convertView.findViewById(R.id.product_signed);
            holder.noteBtn = (ImageButton) convertView.findViewById(R.id.product_note);
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
        holder.positionTv.setText(item.getPositionMoney());
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
        if (TextUtils.equals(item.getState(), "已购买")) {
            holder.interestBt.setVisibility(View.GONE);
            holder.inquiryBt.setVisibility(View.GONE);
            holder.appointmentBt.setVisibility(View.GONE);
            holder.signBt.setVisibility(View.GONE);
            holder.startMoneyTv.setVisibility(View.VISIBLE);
            if (item.isTipsVisibility()) {
                holder.tipsTv.setVisibility(View.VISIBLE);
            } else {
                holder.tipsTv.setVisibility(View.GONE);
            }
            holder.detailBt.setText("公告");
            holder.detailBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
            holder.detailBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUtils.getNoticeDialog(mContext, "公告", item.getNotice()).show();
                    item.setTipsVisibility(false);
                    notifyDataSetChanged();
                }
            });
        } else {
            holder.interestBt.setVisibility(View.VISIBLE);
            holder.inquiryBt.setVisibility(View.VISIBLE);
            holder.appointmentBt.setVisibility(View.VISIBLE);
            holder.signBt.setVisibility(View.VISIBLE);
            holder.positionTv.setText(item.getStartMoney());
            holder.startMoneyTv.setVisibility(View.GONE);
            holder.tipsTv.setVisibility(View.GONE);
            holder.detailBt.setText("详情");
            holder.detailBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
            holder.detailBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startDetailActivity(item);
                }
            });
        }
        switch (item.getRecommendState()) {
            case 1:
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                break;
            case 2:
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                break;
            case 3:
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                break;
            case 4:
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                break;
        }
        holder.noteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NoteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("info", item.getComment());
                intent.putExtra("position",position);
                intent.putExtra("type", 2);
                mContext.startActivity(intent);
            }
        });
        holder.interestBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setRecommendState(1);
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
            }
        });
        holder.inquiryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setRecommendState(2);
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
            }
        });
        holder.appointmentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setRecommendState(3);
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
            }
        });
        holder.signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setRecommendState(4);
                holder.interestBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.inquiryBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.appointmentBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_detail_button_selector));
                holder.signBt.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.product_notice_button_selector));
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
        Button detailBt;
        TextView riskTv;
        TextView startMoneyTv;
        TextView positionTv;
        TextView leftTv;
        ProgressBar progressBar;
        Button interestBt;
        Button inquiryBt;
        Button appointmentBt;
        Button signBt;
        ImageButton noteBtn;
        TextView tipsTv;
    }
}
