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
public class MocionAOP {

    @Autowired
    private LogRepository logRepository;

    @Before("execution(* cr.ac.una.repository.MocionRepository.findAll(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeFindAll(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

    @Before("execution(* cr.ac.una.repository.MocionRepository.save(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeSave(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

    @Before("execution(* cr.ac.una.repository.MocionRepository.delete(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeDelete(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

    @Before("execution(* cr.ac.una.repository.MocionRepository.findById(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeFindById(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }


}
