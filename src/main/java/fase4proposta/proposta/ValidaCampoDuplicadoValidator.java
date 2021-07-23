package fase4proposta.proposta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaCampoDuplicadoValidator  implements ConstraintValidator<ValidaCampoDuplicado, Object> {
    @PersistenceContext
    private EntityManager manager;

    private String campo;
    private Class<?> aClass;

    @Override
    public void initialize(ValidaCampoDuplicado dadosDaClasse) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        this.campo = dadosDaClasse.atributo();
        this.aClass = dadosDaClasse.aClass();

    }

    @Override
    public boolean isValid(Object valorRecebido, ConstraintValidatorContext constraintValidatorContext) {
        //consultar o banco para ver se realmente é duplicado
        Query resultado = manager.createQuery(
                "SELECT c FROM " + aClass.getName() + " c WHERE c." + campo + " =: valor");
        resultado.setParameter("valor", valorRecebido);

        if (resultado.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}
