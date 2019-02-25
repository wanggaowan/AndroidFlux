package com.wgw.flux;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 发送Action，增加订阅
 *
 * @author Created by 汪高皖 on 2019/2/21 0021 13:52
 */
class RxBus {
    private Subject<Object> bus = PublishSubject.create().toSerialized();
    
    /**
     * 发射Action
     *
     * @param action 需要发射的Action
     */
    void post(Action action) {
        bus.onNext(action);
    }
    
    /**
     * @return an Observable that emits items from the source ObservableSource of type {@link Action}.class
     */
    Observable<Action> toObservable() {
        return bus.ofType(Action.class);
    }
}
