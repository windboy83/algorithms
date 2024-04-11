package com.charles.test;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

interface TestInterface {
    int calculate(int a, int b);

    String calculate(String a, String b);

    String getValue();
}

public class MockLibrary implements InvocationHandler {
    private Map<String, MockLibrary> expected = new LinkedHashMap<>();
    @Getter
    @Setter
    private Object value;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        for (Object o : args) {
            sb.append("_").append(o.toString());
        }
        return expected.containsKey(sb.toString()) ? expected.get(sb.toString()).getValue() : "";
    }

    public MockLibrary when(String method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        for (Object o : args) {
            sb.append("_").append(o.toString());
        }
        MockLibrary mockLibrary = new MockLibrary();
        expected.put(sb.toString(), mockLibrary);

        return mockLibrary;
    }

    public void thenReturn(Object val) {
        this.setValue(val);
    }

    public static void main(String[] args) throws Throwable {
        MockLibrary handler = new MockLibrary();
        TestInterface ref = (TestInterface) Proxy.newProxyInstance(
                MockLibrary.class.getClassLoader(),
                new Class[]{TestInterface.class}, handler);

        handler.when("calculate", new Object[]{1, 2}).thenReturn(3);
        handler.when("calculate", new Object[]{"a", "b"}).thenReturn("ab");

        System.out.println(ref.calculate(1, 2)); // prints 3
        System.out.println(ref.calculate("a", "b")); // prints "ab"
    }
}
