package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public  void beforeAdvice(JoinPoint jp)throws Exception{
        //获取当前访问时间
        visitTime = new Date();
        //获取当前访问的类
        clazz = jp.getTarget().getClass();
        //获取访问的方法名称
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        if(args==null||args.length==0){
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        }else{
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void afterAdvice(JoinPoint jp) throws Exception{
        //获取执行时间
        long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长

        //获取url
        String url="";
        if(clazz != null && method != null && clazz != LogAop.class){
            //获取类上的RequestMapping注解的值
            RequestMapping classAnnotation  = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            String[] classValue = classAnnotation.value();
            //获取方法上的RequestMapping注解的值
            RequestMapping methodAnnotation =  method.getAnnotation(RequestMapping.class);

            if(methodAnnotation!=null){
                String[] methodValue = methodAnnotation.value();
                url = classValue[0] + methodValue[0];

                //获取访问的ip地址
                String ip = request.getRemoteAddr();

                //获取当前的操作用户
                SecurityContext securityContext = SecurityContextHolder.getContext();
                User user = (User) securityContext.getAuthentication().getPrincipal();
                String userName = user.getUsername();

                //将数据封装在sysLog对象中
                //将日志相关信息封装到SysLog对象
                SysLog sysLog = new SysLog();
                sysLog.setExecutionTime(time); //执行时长
                sysLog.setIp(ip);
                sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(userName);
                sysLog.setVisitTime(visitTime);

                //调用Service完成操作
                sysLogService.save(sysLog);
            }




        }
    }
}
