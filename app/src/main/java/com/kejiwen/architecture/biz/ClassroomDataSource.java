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

import java.util.ArrayList;
import java.util.List;

import com.shizhefei.view.listviewhelper.IDataSource;
import com.kejiwen.architecture.model.ClassroomItem;
import com.kejiwen.architecture.utils.HttpUtils;

public class ClassroomDataSource implements IDataSource<List<ClassroomItem>> {
    private static List<ClassroomItem> mLists;
    private int page = 1;
    private int maxPage = 1;

    @Override
    public List<ClassroomItem> refresh() throws Exception {
        if (mLists == null) {
            mLists = loadData(1);
        }
        return mLists;
    }

    @Override
    public List<ClassroomItem> loadMore() throws Exception {
        if (mLists == null) {
            mLists = loadData(page + 1);
        }
        return mLists;
    }

    private List<ClassroomItem> loadData(int page) throws Exception {
        // 这里用百度首页模拟网络请求，如果网路出错的话，直接抛异常不会执行后面的获取books的语句
        HttpUtils.executeGet("http://www.baidu.com");

        List<ClassroomItem> ClassroomItems = new ArrayList<ClassroomItem>();

        ClassroomItem item1 = new ClassroomItem();
        item1.setTitle("关于银行理财的27个坏习惯");
        item1.setDate("2015.05.01");
        item1.setUrl("file:///android_asset/classDetails_01.html");
        ClassroomItems.add(item1);
        ClassroomItem item2 = new ClassroomItem();
        item2.setTitle("理财路上必须知道的六大定律 ");
        item2.setDate("2015.05.02");
        item2.setUrl("file:///android_asset/classDetails_02.html");
        ClassroomItems.add(item2);
        ClassroomItem item3 = new ClassroomItem();
        item3.setTitle("美国人如何靠攒钱成百万富翁");
        item3.setDate("2015.05.04");
        item3.setUrl("file:///android_asset/classDetails_03.html");
        ClassroomItems.add(item3);

        this.page = page;
        return ClassroomItems;
    }

    @Override
    public boolean hasMore() {
        return page < maxPage;
    }

}
