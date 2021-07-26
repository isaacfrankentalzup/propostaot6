package fase4proposta.proposta;

import fase4proposta.feign.ResultadoAnalise;
import fase4proposta.feign.SolicitacaoAnalise;
import fase4proposta.feign.ValidacaoProposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/solicitante")
public class ControllerSolicitante {

    private RepositorySolicitante repositorySolicitante; // 35:22

    private ValidacaoProposta validacaoProposta;

    public ControllerSolicitante(RepositorySolicitante repositorySolicitante,
                                 ValidacaoProposta validacaoProposta) {
        this.repositorySolicitante = repositorySolicitante;
        this.validacaoProposta = validacaoProposta;
    }


    @PostMapping
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

        System.out.println(resultadoAnalise.getIdProposta());
        System.out.println(resultadoAnalise.getDocumento());
        System.out.println(resultadoAnalise.getDocumento());
        System.out.println(resultadoAnalise.getResultadoSolicitacao());

        //return ResponseEntity.status(201).build();
        return  ResponseEntity.ok(resultadoAnalise) ;


        /*

            Solicitante solicitante = requestSolicitante.toSolicitante();

            repositorySolicitante.save(solicitante);

            return ResponseEntity.status(201).build();

         */

    }

    
   }
