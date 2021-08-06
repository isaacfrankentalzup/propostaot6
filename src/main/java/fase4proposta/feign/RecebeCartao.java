package fase4proposta.feign;

import fase4proposta.cartao.AvisoResponse;
import fase4proposta.cartao.Cartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// 055 & 060
@FeignClient(url = "http://localhost:8888", name = "recebecartao")
public interface RecebeCartao {

    @PostMapping("/api/cartoes")
    public Cartao getIdCartao(SolicitacaoAnalise solicitacaoAnalise);

    @PostMapping(value = "/api/cartoes/{cartaoId}/bloqueios", consumes = "application/json", produces = "application/json")
    public String cartaoBloqueio(@PathVariable String cartaoId, String sistemaResponsavel);

    @PostMapping(value = "/api/cartoes/{cartaoId}/avisos", consumes = "application/json", produces = "application/json")
    public String cartaoAviso(@PathVariable String cartaoId, AvisoResponse avisoResponse);



}
