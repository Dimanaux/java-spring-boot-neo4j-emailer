package com.example.emailer.aspects;

import com.example.emailer.security.AccountDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Aspect
@Configuration
public class ModelMapAspect {
    @Pointcut(value = "execution(* com.example.emailer.controllers..*(..))")
    public void getPage() {
    }

    @Around("getPage() && args(accountDetails,modelMap,..)")
    public Object putFolders(ProceedingJoinPoint pjp,
                             AccountDetails accountDetails,
                             ModelMap modelMap) throws Throwable {
        modelMap.put("folders", accountDetails.getAccount().getFolders());
        return pjp.proceed(pjp.getArgs());
    }

    @Around("getPage() && args(accountDetails,modelMap,..)")
    public Object putCurrentUser(ProceedingJoinPoint pjp,
                                 ModelMap modelMap,
                                 AccountDetails accountDetails) throws Throwable {
        modelMap.put("current_user", accountDetails.getAccount());
        return pjp.proceed(pjp.getArgs());
    }

    @Around("getPage() && args(accountDetails,modelMap,..)")
    public Object putGroups(ProceedingJoinPoint pjp,
                            ModelMap modelMap,
                            AccountDetails accountDetails) throws Throwable {
        modelMap.put("groups", accountDetails.getAccount().getGroups());
        return pjp.proceed(pjp.getArgs());
    }
}
