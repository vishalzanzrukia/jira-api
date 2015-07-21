package com.plugin.jira.api.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodInvocationHandler implements InvocationHandler {
  private Object proxied;

  public MethodInvocationHandler(Object proxied) {
    this.proxied = proxied;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Method m = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
    if (m.isAnnotationPresent(MyAnnotation.class)) {

      // do your work here
      System.out.println("Before " + m.getName() + " call..!");

      /** also you can get annotation and access it's properties..! */
      MyAnnotation annotation = m.getAnnotation(MyAnnotation.class);
      System.out.println("name: " + annotation.name());

    }

    /** also you can get all the annotations if you want */
    Annotation[] annotations = method.getDeclaredAnnotations();
    for (Annotation annotation : annotations) {
      // do your annotation specific work here like this,
      if (annotation instanceof MyAnnotation) {
        // do your work here.
      }
    }
    return method.invoke(proxied, args);
  }
}
