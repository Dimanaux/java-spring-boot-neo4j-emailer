package com.example.emailer.aspects;

import com.example.emailer.security.AccountDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Aspect
@Component
public aspect AuthAspect {
    @Around(
            value = "execution(* com.example.emailer.controllers..*(..))" +
//                    " && @annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    " && args(accountDetails,modelMap)",
            argNames = "pjp,modelMap,accountDetails"
    )
    public Object addCurrentAccountToModelMap(ProceedingJoinPoint pjp,
                                              ModelMap modelMap,
                                              AccountDetails accountDetails) throws Throwable {
        modelMap.put("current_user", accountDetails.getAccount());
        return pjp.proceed(pjp.getArgs());
    }
}
