package com.plugin.jira.api.annotation;

import java.lang.reflect.Proxy;

public class MyClass implements MyInterface {
  
  private static MyClass INSTANCE = null;
  
  private MyClass(){
  }

  public static MyClass getInstance(){
    System.out.println("creating instance");
    if(INSTANCE == null){
      INSTANCE = new MyClass();
    }
    return INSTANCE;
  }
  
  @MyAnnotation
  public void test() {
    System.out.println("Inside test");
  }

  public static void main(String[] args) throws IllegalArgumentException, InstantiationException, IllegalAccessException {
    MyInterface test = (MyInterface) getProxyInstance(MyClass.class, MyInterface.class);
    test.test();
  }

  @SuppressWarnings("rawtypes")
  public static Object getProxyInstance(Class clazz, Class interfaze) throws IllegalArgumentException,
      InstantiationException, IllegalAccessException {
    Object proxy =
        Proxy.newProxyInstance(MethodInvocationHandler.class.getClassLoader(),
            new Class[] {interfaze}, new MethodInvocationHandler(clazz.newInstance()));
    return proxy;
  }
}
