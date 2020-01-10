package com.pr.sepp.common.log;

import com.google.common.base.Stopwatch;
import com.pr.sepp.common.threadlocal.ParameterThreadLocal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isBlank;

public abstract class CommonAspect {
    private static Logger aspectLog = LoggerFactory.getLogger("sepp.aspect.log");
    private static final String HTTP_REQUEST_LOG_MESSAGE = "HttpId【{}】:{} time cost : {} ms";
    private static final String POIN_CLASS_METHOD = "%s.%s()";

    public Object printExecuteIntervalLog(ProceedingJoinPoint point) throws Throwable {
        String executeMethod = String.format(POIN_CLASS_METHOD, point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        Stopwatch started = Stopwatch.createStarted();
        Object obj = point.proceed();
        if (isBlank(ParameterThreadLocal.getHttpId())) {
            return obj;
        }
        aspectLog.info(HTTP_REQUEST_LOG_MESSAGE, ParameterThreadLocal.getHttpId(), executeMethod,
                started.stop().elapsed(TimeUnit.MILLISECONDS));
        return obj;
    }
}
