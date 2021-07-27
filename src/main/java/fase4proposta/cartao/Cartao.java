package fase4proposta.cartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cartao {
    private String id;

    @JsonCreator
    public Cartao(@JsonProperty String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /*
    public Cartao(@JsonProperty("cartaoId") String cartaoId) {
        this.cartaoId = cartaoId;
    }

    public String getCartaoId() {
        return cartaoId;
    }

     */
}
