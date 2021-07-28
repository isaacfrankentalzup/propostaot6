package fase4proposta.proposta;

import fase4proposta.arrechandle.PropostaNaoEncontrada;
import fase4proposta.cartao.Cartao;
import fase4proposta.feign.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/propostas")   //solicitante
public class ControllerSolicitante {

    private RepositorySolicitante repositorySolicitante; // 35:22
    private ValidacaoProposta validacaoProposta;
    private RecebeCartao recebeCartao;

    public ControllerSolicitante(RepositorySolicitante repositorySolicitante,
                                 ValidacaoProposta validacaoProposta, RecebeCartao recebeCartao) {
        this.repositorySolicitante = repositorySolicitante;
        this.validacaoProposta = validacaoProposta;
        this.recebeCartao = recebeCartao;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Object> test (@RequestBody @Valid RequestSolicitante requestSolicitante,
                                        UriComponentsBuilder uriComponentsBuilder){


        Boolean existeDocumento = repositorySolicitante.existsByDocumento(requestSolicitante.getDocumento());
        Boolean existeEmail = repositorySolicitante.existsByEmail(requestSolicitante.getEmail());


        if(existeDocumento) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestSolicitante);  // pas sur
        }

        if(existeEmail) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestSolicitante);  // pas sur
        }

        Solicitante solicitanteSalva = repositorySolicitante.save(requestSolicitante.toSolicitante());

        SolicitacaoAnalise solicitacao = new SolicitacaoAnalise(
                solicitanteSalva.getDocumento(),
                solicitanteSalva.getNome(),
                solicitanteSalva.getId().toString());  // retestar

        // validação pelo feign
        ResultadoAnalise resultadoAnalise = validacaoProposta.validaProposta(solicitacao);

        // SE O RESUTADO FOR SEM_RESTRIÇÃO DEVO SOLICITAR UM CARTÃO.
        ResultadoSolicitacao resultado = resultadoAnalise.getResultadoSolicitacao();

        if(resultado == ResultadoSolicitacao.SEM_RESTRICAO){
            String idProposta = solicitanteSalva.getId().toString(); // pego para uma variavel o idproposta
            Cartao cartaoId = recebeCartao.getIdCartao(solicitacao); // estou passando idproposta par o feign
            solicitanteSalva.setCartaoId(cartaoId.getId());

            System.out.println(solicitanteSalva.getCartaoId());
        }

        //return ResponseEntity.status(201).build();
        return  ResponseEntity.ok(resultadoAnalise);

        }

    @GetMapping("/{propostaId}")
    public ResponseEntity<Solicitante> pegaProposta(@PathVariable Long propostaId){
        Optional<Solicitante> temProposta = repositorySolicitante.findById(propostaId);

        //if(temProposta.isEmpty()) throw new PropostaNaoEncontrada("Proposta de número " +
          //     propostaId + " não encontrada");

        if(temProposta.isPresent()){
            return ResponseEntity.ok(temProposta.get());
        }
            return ResponseEntity.notFound().build();
    }
    
   }
