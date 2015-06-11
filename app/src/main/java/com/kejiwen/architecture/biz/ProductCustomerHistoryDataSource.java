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
import com.kejiwen.architecture.model.CustomerItem;
import com.kejiwen.architecture.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductCustomerHistoryDataSource implements IDataSource<List<CustomerItem>> {
    public static List<CustomerItem> mLists;
    private int page = 1;
    private int maxPage = 1;

    @Override
    public List<CustomerItem> refresh() throws Exception {
        if (mLists == null) {
            mLists = loadData(1);
        }
        return mLists;
    }

    @Override
    public List<CustomerItem> loadMore() throws Exception {
        if (mLists == null) {
            mLists = loadData(page + 1);
        }
        return mLists;
    }
    private List<CustomerItem> loadData(int page) throws Exception {
        // 这里用百度首页模拟网络请求，如果网路出错的话，直接抛异常不会执行后面的获取books的语句
        //HttpUtils.executeGet("http://www.baidu.com");

        List<CustomerItem> customerItems = new ArrayList<CustomerItem>();

        CustomerItem item0 = new CustomerItem();
        item0.setName("(张艳)");
        item0.setAlias("晓艳");
        item0.setPhone("13588425804");
        item0.setType("激进型");
        item0.setPosition("持仓100万");
        item0.setSex("女士");
        customerItems.add(item0);

        CustomerItem item1 = new CustomerItem();
        item1.setName("(李建国)");
        item1.setAlias("建国");
        item1.setPhone("13988425804");
        item1.setType("激进型");
        item1.setPosition("持仓100万");
        item1.setSex("先生");
        customerItems.add(item1);

        CustomerItem item2 = new CustomerItem();
        item2.setName("(唐飞艳)");
        item2.setAlias("飞艳");
        item2.setPhone("13688433404");
        item2.setType("激进型");
        item2.setPosition("持仓100万");
        item2.setSex("女士");
        customerItems.add(item2);

        CustomerItem item3 = new CustomerItem();
        item3.setName("(王超)");
        item3.setAlias("王总");
        item3.setPhone("13288427404");
        item3.setType("激进型");
        item3.setPosition("持仓100万");
        item3.setSex("女士");
        customerItems.add(item3);

        CustomerItem item4 = new CustomerItem();
        item4.setName("(唐石溪)");
        item4.setAlias("唐总");
        item4.setPhone("13988425604");
        item4.setType("激进型");
        item4.setPosition("持仓100万");
        item4.setSex("先生");
        customerItems.add(item4);

        this.page = page;
        return customerItems;
    }

    @Override
    public boolean hasMore() {
        return page < maxPage;
    }

}
