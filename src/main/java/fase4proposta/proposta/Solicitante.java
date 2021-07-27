package fase4proposta.proposta;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String nome;

    @Column (nullable = false)
    private String endereco;

    @Column (nullable = false)
    private BigDecimal salario;

    private String cartaoId;

    public Solicitante(){

    }

    public Solicitante(String documento, String email, String nome, String endereco,
                       BigDecimal salario, String cartaoId) {
        this.id = id;
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.cartaoId = cartaoId;
    }

    public String getCartaoId() {
        return cartaoId;
    }


    public void setCartaoId(String cartaoId) {
        this.cartaoId = cartaoId;
    }

    public Long getId() {
        return id;
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


}
