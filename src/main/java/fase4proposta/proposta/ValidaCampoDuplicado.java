package fase4proposta.proposta;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidaCampoDuplicadoValidator.class)

public @interface ValidaCampoDuplicado {
    String message() default "Campo Duplicado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String atributo();
    Class<?> aClass();

}
