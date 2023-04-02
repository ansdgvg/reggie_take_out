package com.ljx.common;

/**
 * 基于 ThreadLocal 封装的工具类
 * <p>
 * ThreadLocalwe每个线程提供单独一份存储空间，具有线程隔离的效果
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
