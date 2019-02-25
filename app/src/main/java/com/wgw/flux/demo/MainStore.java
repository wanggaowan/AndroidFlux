package com.wgw.flux.demo;

import android.arch.lifecycle.MutableLiveData;

import com.wgw.flux.Action;
import com.wgw.flux.Store;

/**
 * @author Created by 汪高皖 on 2019/2/25 0025 09:26
 */
public class MainStore extends Store {
    // 可自行实现LiveData，而不使用MutableLiveData，来达到只有Store可以修改值，而外部只能获取或监听
    // 原则是不建议Store外部直接修改内容
    public MutableLiveData<String> text = new MutableLiveData<>();
    
    @Override
    protected void onAction(Action action) {
        text.setValue((String) action.getData());
    }
}
