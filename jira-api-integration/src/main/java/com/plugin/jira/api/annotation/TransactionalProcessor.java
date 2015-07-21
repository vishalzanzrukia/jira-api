package com.plugin.jira.api.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;


@SupportedAnnotationTypes("com.plugin.jira.api.annotation.Transactional")
public class TransactionalProcessor extends AbstractProcessor {
  
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    System.out.println("Hello22");
    Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyAnnotation.class);
    for (Element e : elements) {

      if (e.getKind().equals(ElementKind.METHOD)) {
        System.out.println();
      }
      /*
       * if (!e.getClass().equals(String.class)) {
       * 
       * }
       */
    }
    return true;

  }



}
