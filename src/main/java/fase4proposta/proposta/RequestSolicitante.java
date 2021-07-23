package fase4proposta.proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RequestSolicitante {

    @CpfCnpj
    @NotBlank
    @ValidaCampoDuplicado(atributo = "documento", aClass = Solicitante.class)
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

   @NotBlank
    private String endereco;

    @NotNull @Positive
    private BigDecimal salario;

    public RequestSolicitante(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Solicitante toSolicitante(){
        return new Solicitante(this.documento, this.email, this.nome, this.endereco,
                this.salario);
    };


}
