package cr.ac.una.aop;

import cr.ac.una.entity.Log;
import cr.ac.una.repository.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;

@Aspect// cualquier clase la convertimos en aspecto con esto
@Component //componente de Spring, componentes de servicio
public class PersonaAOP {

    @Autowired
    LogRepository logRepository;

    @Before("execution(* cr.ac.una.repository.PersonaRepository.findAll(..))")
    public void longBeforeV1(JoinPoint joinPoint){
        System.out.println("PersonaAOP.longBeforeV1()"+ joinPoint.getSignature().getName());
    }
//--------------------------
    @Before("execution(* cr.ac.una.repository.PersonaRepository.findAll(..))")
    public void longBeforeFindAll(JoinPoint joinPoint){
    logRepository.save(new Log(joinPoint.getSignature().getName(),new Date()));

    }

    @Before("execution(* cr.ac.una.repository.PersonaRepository.save(..))")
    public void longBeforeSave(JoinPoint joinPoint){
        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));

    }
    @Before("execution(* cr.ac.una.repository.PersonaRepository.findById(..))") // los ".." son para aceptar cualquier parametro
    public void longBeforeFindById(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }


    @Before("execution(* cr.ac.una.repository.PersonaRepository.delete(..))") // los ".." son para aceptar cualquier parametro
    public void longBeforeDelete(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }




}
