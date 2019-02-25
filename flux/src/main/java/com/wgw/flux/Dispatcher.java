package com.wgw.flux;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Action分发器
 *
 * @author Created by 汪高皖 on 2019/2/21 0021 14:23
 */
@SuppressWarnings("WeakerAccess")
public class Dispatcher {
    private RxBus mRxBus;
    
    private Dispatcher() {
        mRxBus = new RxBus();
    }
    
    private static class Inner {
        private static final Dispatcher INSTANCE = new Dispatcher();
    }
    
    public static Dispatcher getInstance() {
        return Inner.INSTANCE;
    }
    
    private void registerInner(final Store store, final String... actionTypes) {
        FluxLogger.logRegisterStore(store.getClass().getSimpleName(), actionTypes);
        Disposable disposable = mRxBus.toObservable()
            .filter(action -> {
                if (action.getTarget() != null) {
                    return action.getTarget().equals(store);
                } else if (actionTypes == null || actionTypes.length == 0) {
                    return true;
                }
                
                for (String type : actionTypes) {
                    if (type.equals(action.getType())) {
                        return true;
                    }
                }
                return false;
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(store :: onAction, throwable ->
                FluxLogger.logHandleException(store.getClass().getSimpleName(), throwable));
        store.setDisposable(disposable);
    }
    
    private void postActionInner(Action action) {
        FluxLogger.logPostAction(action);
        mRxBus.post(action);
    }
    
    static void register(Store store, String... actionTypes) {
        getInstance().registerInner(store, actionTypes);
    }
    
    /**
     * 分发Action
     *
     * @param action 需要分发的Action
     */
    public static void postAction(Action action) {
        getInstance().postActionInner(action);
    }
    
    /**
     * 分发Action
     *
     * @param actionType action类型
     * @param data       携带的数据
     */
    public static void postAction(String actionType, Object data) {
        postAction(actionType, data, null);
    }
    
    /**
     * 分发Action
     *
     * @param actionType action类型
     * @param throwable  异常数据
     */
    public static void postAction(String actionType, Throwable throwable) {
        postAction(actionType, null, throwable);
    }
    
    /**
     * 分发Action
     *
     * @param actionType action类型
     * @param data       携带的数据
     * @param throwable  异常数据
     */
    public static void postAction(String actionType, Object data, Throwable throwable) {
        postAction(actionType, null, data, throwable);
    }
    
    /**
     * 分发Action
     *
     * @param actionType action类型
     * @param target     action分发指定唯一的Store接收
     * @param data       携带的数据
     * @param throwable  异常数据
     */
    public static void postAction(String actionType, Store target, Object data, Throwable throwable) {
        Action action = Action.getInstance(actionType);
        action.setTarget(target);
        action.setData(data);
        action.setThrowable(throwable);
        getInstance().postActionInner(action);
    }
}
