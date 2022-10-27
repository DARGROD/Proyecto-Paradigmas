package cr.ac.una.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cr.ac.una.entity.Log;
import cr.ac.una.repository.LogRepository;

import java.util.Date;

@Aspect// cualquier clase la convertimos en aspecto con esto
@Component //componente de Spring, componentes de servicio
public class Tipo_MocionAOP {

    @Autowired
    private LogRepository logRepository;

    @Before("execution(* cr.ac.una.repository.TipoMocionRepository.findAll(..))") // los ".." son para aceptar cualquier
    public void logBeforeFindAll(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

    @Before("execution(* cr.ac.una.repository.TipoMocionRepository.save(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeSave(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

    @Before("execution(* cr.ac.una.repository.TipoMocionRepository.delete(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeDelete(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

    @Before("execution(* cr.ac.una.repository.TipoMocionRepository.findById(..))") // los ".." son para aceptar cualquier parametro
    public void logBeforeFindById(JoinPoint joinPoint){

        logRepository.save(new Log(joinPoint.getSignature().getName(), new Date()));
    }

}
