package com.livevox.phonebook.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${logging-aspect.weave:true}")
public class LoggingAspect {

  private static final Marker METHOD_LOG_MARKER = MarkerFactory.getMarker("ML");

  @Around("@annotation(com.livevox.phonebook.logging.Logged)")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

    final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget()
        .getClass()
        .getName());

    if (!logger.isInfoEnabled()) {
      return joinPoint.proceed();
    }

    final Signature signature = joinPoint.getSignature();
    final String methodName = signature.getName();
    final Object[] args = joinPoint.getArgs();
    final long startExecutionTime = System.currentTimeMillis();

    Object returnValue;

    try {
      MethodLogger.logArgs(logger, methodName, args);
      returnValue = joinPoint.proceed();
      MethodLogger.logReturnValue(logger, methodName, returnValue);

      final Long executionTime = System.currentTimeMillis() - startExecutionTime;
      MethodLogger.logExecutionTime(logger, methodName, executionTime);

    } catch (Exception e) {
      final Long executionTime = System.currentTimeMillis() - startExecutionTime;
      MethodLogger.logException(logger, methodName, e);
      MethodLogger.logExecutionTime(logger, methodName, executionTime);
      throw e;
    }
    return returnValue;

  }

  static class MethodLogger {

    static void logArgs(Logger logger, String methodName, Object[] args) {
      for (int i = 0; i < args.length; i++) {
        logger.debug(METHOD_LOG_MARKER, "Method = {}, args[{}] = {}", methodName,
            i,
            args[i]);
      }
    }

    static void logReturnValue(Logger logger, String methodName, Object returnValue) {
      logger.debug(METHOD_LOG_MARKER, "Method = {}, retv = {}", new Object[]{methodName,
          returnValue});
    }

    static void logException(Logger logger, String methodName, Exception exception) {
      logger.debug(METHOD_LOG_MARKER, "Method = {}, expn = {}", new Object[]{methodName,
          exception});
    }

    static void logExecutionTime(Logger logger, String methodName, Long executionTime) {
      logger.debug(METHOD_LOG_MARKER, "Method = {}, exet = {} ms", new Object[]{methodName,
          executionTime});
    }
  }

}
