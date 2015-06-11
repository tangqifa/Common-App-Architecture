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
package com.kejiwen.architecture.biz;

import com.shizhefei.view.listviewhelper.IDataSource;
import com.kejiwen.architecture.model.ProductItem;
import com.kejiwen.architecture.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductOnSellDataSource implements IDataSource<List<ProductItem>> {
    private static List<ProductItem> mLists;
    private int page = 1;
    private int maxPage = 1;

    @Override
    public List<ProductItem> refresh() throws Exception {
        if (mLists == null) {
            mLists = loadData(1);
        }
        return mLists;
    }

    @Override
    public List<ProductItem> loadMore() throws Exception {
        if (mLists == null) {
            mLists = loadData(page + 1);
        }
        return mLists;
    }

    private List<ProductItem> loadData(int page) throws Exception {
        // 这里用百度首页模拟网络请求，如果网路出错的话，直接抛异常不会执行后面的获取books的语句
        //HttpUtils.executeGet("http://www.baidu.com");

        List<ProductItem> items = new ArrayList<ProductItem>();
        //数据3
        ProductItem item3 = new ProductItem();
        item3.setTitle("北京银行-“心喜”系列2015年第301期人民币9个月信托债券非保本理财产品");
        item3.setState("热销中");
        item3.setDays("274天");
        item3.setLeftMoney("剩6000万");
        item3.setTotalMoney("30000万");
        item3.setStartMoney("5万元");
        item3.setRate("5.65%");
        item3.setRisk("二级风险");
        item3.setDetail("file:///android_asset/product_01.html");
        items.add(item3);
        //数据4
        ProductItem item4 = new ProductItem();
        item4.setTitle("北京银行-人民币“天天盈”562 号非保本浮动收益理财产品");
        item4.setState("热销中");
        item4.setDays("180天");
        item4.setLeftMoney("剩5000万");
        item4.setTotalMoney("20000万");
        item4.setStartMoney("20万元");
        item4.setRate("4.70%");
        item4.setRisk("二级风险");
        item4.setDetail("file:///android_asset/product_02.html");
        items.add(item4);
        this.page = page;
        return items;
    }

    @Override
    public boolean hasMore() {
        return page < maxPage;
    }

}
