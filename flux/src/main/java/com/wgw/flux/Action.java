package com.wgw.flux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * 保存动作类型以及携带数据
 *
 * @author Created by 汪高皖 on 2019/2/22 0021 16:27
 */
@SuppressWarnings("WeakerAccess")
public class Action extends ObjectPool.Poolable {
    /**
     * 初始池大小，只能在未调用{@link #getInstance(String)}、
     * {@link #recycleInstance(Action)}、{@link #recycleInstances(List)}方法之前设置
     */
    public static int INIT_POOL_SIZE = 20;
    
    /**
     * 当池容量不够时池每次增长比例，只能在未调用{@link #getInstance(String)}、
     * {@link #recycleInstance(Action)}、{@link #recycleInstances(List)}方法之前设置
     */
    public static float REPLENISH_PERCENTAGE = 0.5f;
    
    private static class Inner {
        private static ObjectPool<Action> pool;
        
        static {
            pool = ObjectPool.create(INIT_POOL_SIZE, new Action());
            pool.setReplenishPercentage(REPLENISH_PERCENTAGE);
        }
    }
    
    /**
     * 获取实例，无需使用请调用{@link #recycleInstance(Action)}或{@link #recycleInstances(List)}
     */
    public static Action getInstance(@NonNull String type) {
        Action result = Inner.pool.get();
        result.setType(type);
        return result;
    }
    
    /**
     * 回收实例
     */
    public static void recycleInstance(Action instance) {
        Inner.pool.recycle(instance);
    }
    
    /**
     * 回收实例集合
     */
    public static void recycleInstances(List<Action> instances) {
        Inner.pool.recycle(instances);
    }
    
    
    private String type;
    private Object data;
    private Throwable throwable;
    private Store target;
    
    private Action() {
        this.type = "";
    }
    
    /**
     * @return 动作所要分发到的目标类型。
     */
    @NonNull
    public String getType() {
        return type;
    }
    
    /**
     * @param type 动作所要分发到的目标类型。
     *             当{@link #target}值为null时，通过此值来分发动作到对应{@link Store}
     */
    public void setType(@NonNull String type) {
        this.type = type;
    }
    
    @Nullable
    public Object getData() {
        return data;
    }
    
    public void setData(@Nullable Object data) {
        this.data = data;
    }
    
    public void setThrowable(@Nullable Throwable throwable) {
        this.throwable = throwable;
    }
    
    @Nullable
    public Throwable getThrowable() {
        return throwable;
    }
    
    /**
     * @return 动作所要分发到的目标 {@link Store}
     */
    @Nullable
    public Store getTarget() {
        return target;
    }
    
    /**
     * @param target 动作所要分发到的目标{@link Store}。
     *               如果该值不为空，则该动作只会分发到对应的{@link Store},
     *               如果没有{@link Store}注册到{@link Dispatcher}，则该动作流逝
     */
    public void setTarget(@Nullable Store target) {
        this.target = target;
    }
    
    @Override
    protected ObjectPool.Poolable instantiate() {
        return new Action();
    }
    
    @Override
    protected void recycle() {
        type = "";
        throwable = null;
        data = null;
        target = null;
    }
}
