package org.entain.tests.retry;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransformer implements IAnnotationTransformer {
    /**
     * This method is invoked by TestNG before every test method execution.
     */
    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {
        Class<? extends IRetryAnalyzer> current = annotation.getRetryAnalyzerClass();
        if (current == null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}