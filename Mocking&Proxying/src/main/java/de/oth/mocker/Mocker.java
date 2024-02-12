package de.oth.mocker;

import java.lang.reflect.Proxy;

public class Mocker{

    static <T> T mock(Class<T> tClass){
        MyInvocationHandler handler = new MyInvocationHandler();
        T t = (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[] {tClass}, handler);
        handler.setSpy(false);
        return t;
    }

    static <T> T spy(T obj) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.isSpy = true;
        handler.spyObject = obj;
        Class objClass = obj.getClass();
        T t = (T) Proxy.newProxyInstance(objClass.getClassLoader(), objClass.getInterfaces(), handler);
        return t;
    }

}
