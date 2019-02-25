package com.wgw.flux;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.Disposable;

/**
 * 存储并处理Action数据并向外部通知处理结果
 *
 * @author Created by 汪高皖 on 2019/2/21 0021 14:13
 */
@SuppressWarnings("WeakerAccess")
public abstract class Store extends ViewModel {
    private Disposable disposable;
    
    void setDisposable(Disposable disposable) {
        if (null != this.disposable && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
        this.disposable = disposable;
    }
    
    /**
     * 处理Action数据，使用完请调用{@link Action#recycleInstance(Action)}进行回收处理
     *
     * @param action 当前Store注册的Action
     */
    protected abstract void onAction(Action action);
    
    /**
     * @return 当前是否注册了Action监听
     */
    public boolean isRegistered() {
        return disposable != null;
    }
    
    @Override
    public void onCleared() {
        unRegister();
    }
    
    /**
     * 注册Action类型以监听{@link Dispatcher}分发的Action
     *
     * @param actionType action对应的类型
     */
    public void register(String... actionType) {
        Dispatcher.register(this, actionType);
    }
    
    /**
     * 取消注册
     */
    public void unRegister() {
        FluxLogger.logUnregisterStore(Store.class.getSimpleName());
        if (null != this.disposable && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
        this.disposable = null;
    }
}
