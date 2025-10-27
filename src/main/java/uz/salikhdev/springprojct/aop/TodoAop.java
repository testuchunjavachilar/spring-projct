package uz.salikhdev.springprojct.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TodoAop {

    @Pointcut("execution(* uz.salikhdev.springprojct.repositroy.TodoRepository.*())")
    private void pointCut() {
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    private void afterThrowing(Exception e) {
        log.error("Xatolik : {}", e.getMessage());
        throw new RuntimeException("Xatolik :" + e.getMessage());
    }

}
