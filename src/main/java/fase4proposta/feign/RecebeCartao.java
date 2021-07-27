package fase4proposta.feign;

import fase4proposta.cartao.Cartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8888", name = "recebecartao")
public interface RecebeCartao {

    @PostMapping("/api/cartoes")
    public Cartao getIdCartao(SolicitacaoAnalise solicitacaoAnalise);


}
