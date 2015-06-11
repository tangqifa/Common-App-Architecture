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

public class CustomerProductDataSource implements IDataSource<List<ProductItem>> {
    public static List<ProductItem> mLists;
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
        //数据0
        ProductItem item0 = new ProductItem();
        item0.setTitle("民生银行-非凡资产管理180天增利第345期");
        item0.setState("推荐");
        item0.setDays("180天");
        item0.setLeftMoney("剩3600万");
        item0.setTotalMoney("10000万");
        item0.setStartMoney("5万元起");
        item0.setRate("5.50%");
        item0.setTipsVisibility(false);
        item0.setRecommendState(1);
        item0.setRisk("一级风险");
        item0.setDetail("file:///android_asset/productDetail.html");
        items.add(item0);
        //数据1
        ProductItem item1 = new ProductItem();
        item1.setTitle("中国民生-银行非凡净值系列产品438号");
        item1.setState("推荐");
        item1.setDays("365天");
        item1.setLeftMoney("剩8000万");
        item1.setTotalMoney("10000万");
        item1.setStartMoney("10万元起");
        item1.setRate("5.90%");
        item1.setTipsVisibility(false);
        item1.setRecommendState(1);
        item1.setRisk("三级风险");
        item1.setDetail("file:///android_asset/product_04.html");
        items.add(item1);
        //数据2
        ProductItem item2 = new ProductItem();
        item2.setTitle("民生银行-非凡资产管理智赢1号");
        item2.setState("已购买");
        item2.setDays("280天");
        item2.setTipsVisibility(true);
        item2.setLeftMoney("剩4800万");
        item2.setTotalMoney("15000万");
        item2.setStartMoney("10万元起");
        item2.setRate("5.75%");
        item2.setRecommendState(0);
        item2.setPositionMoney("持有金额600万");
        item2.setRisk("一级风险");
        item2.setNotice("1.还有1个月到期");
        item2.setDetail("file:///android_asset/product_03.html");
        items.add(item2);
        //数据3
        ProductItem item3 = new ProductItem();
        item3.setTitle("北京银行-“心喜”系列2015年第301期人民币9个月信托债券非保本理财产品");
        item3.setState("已购买");
        item3.setDays("274天");
        item3.setLeftMoney("剩6000万");
        item3.setTotalMoney("30000万");
        item3.setStartMoney("5万元起");
        item3.setPositionMoney("持有金额400万");
        item3.setRate("5.65%");
        item3.setTipsVisibility(true);
        item3.setPositionMoney("持有金额100万");
        item3.setRecommendState(0);
        item3.setNotice("1.产品风险等级变化");
        item3.setRisk("三级风险");
        item3.setDetail("file:///android_asset/product_01.html");
        items.add(item3);
        //数据4
        ProductItem item4 = new ProductItem();
        item4.setTitle("北京银行-人民币“天天盈”562 号非保本浮动收益理财产品");
        item4.setDays("180天");
        item4.setLeftMoney("剩5000万");
        item4.setTipsVisibility(true);
        item4.setTotalMoney("20000万");
        item4.setPositionMoney("持有金额400万");
        item4.setStartMoney("20万元起");
        item4.setState("已购买");
        item4.setPositionMoney("持有金额100万");
        item4.setRate("4.70%");
        item4.setRecommendState(0);
        item4.setNotice("1.产品风险等级变化");
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
