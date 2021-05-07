

package com.mgcloud.common.aspect;

import com.google.gson.Gson;


import com.mgcloud.common.utils.DateUtils;
import com.mgcloud.common.utils.HttpContextUtils;
import com.mgcloud.common.utils.IPUtils;
import com.mgcloud.modules.sys.entity.SysUserEntity;
import com.mgcloud.modules.sys.service.SysLogService;
import com.mgcloud.common.annotation.SysLog;
import com.mgcloud.modules.sys.entity.SysLogEntity;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 * <p>
 * tzen@e-veb.com
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

//    @Pointcut("@annotation(com.mgcloud.common.annotation.SysLog)")
//    public void logPointCut() {
//
//    }
    //修改拦截器
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)" +
            "&& (execution(public * com.mgcloud.modules..*.add*(..))" +
            "|| execution(public * com.mgcloud.modules..*.update*(..))" +
            "|| execution(public * com.mgcloud.modules..*.save*(..))" +
            "|| execution(public * com.mgcloud.modules..*.delete*(..))" +
            "|| execution(public * com.mgcloud.modules..*.insert*(..)))")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity sysLog = new SysLogEntity();
//        SysLog syslog = method.getAnnotation(SysLog.class);
//        if (syslog != null) {
//            //注解上的描述
//            sysLog.setOperation(syslog.value());
//        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        sysLog.setOperation(methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用户名
        String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateDate(DateUtils.LOCAL_DATETIME);
        //保存系统日志
        sysLogService.save(sysLog);
    }
}
