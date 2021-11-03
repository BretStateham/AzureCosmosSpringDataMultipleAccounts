package bs.sample.cdbma;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;

@DependsOn("expressionResolver")
@SpringBootApplication
public class CdbmaApplication {
    public static void main(String[] args){
        SpringApplication.run(CdbmaApplication.class,args);
    }
}
