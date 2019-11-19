/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.advice;

import com.sg.vendingmachine2.dao.AuditDAO;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author acetip
 */
public class TimerAdvice {
    

    AuditDAO audit;

    public TimerAdvice(AuditDAO audit) {
        this.audit = audit;
    }

    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        audit.writeAuditEntry("Going to call the method.");
        Object output = pjp.proceed();
        audit.writeAuditEntry("Method execution completed.");
        long elapsedTime = System.currentTimeMillis() - start;
        audit.writeAuditEntry("Method execution time: " + elapsedTime + " milliseconds.");
        return output;
    }
    
}
