package fase4proposta.proposta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/solicitante")
public class ControllerSolicitante {

    RepositorySolicitante repositorySolicitante;

    public ControllerSolicitante(RepositorySolicitante repositorySolicitante) {
        this.repositorySolicitante = repositorySolicitante;
    }


    @PostMapping
    public ResponseEntity<?> test (@RequestBody @Valid RequestSolicitante requestSolicitante){

        Solicitante solicitante = requestSolicitante.toSolicitante();

        repositorySolicitante.save(solicitante);

        return ResponseEntity.status(201).build();
    }


    /*
    @PostMapping
    public ResponseEntity<RequestSolicitante> novaProposta(@RequestBody @Valid RequestSolicitante requestSolicitante){
        Solicitante solicitante = requestSolicitante.toSolicitante();

        repositorySolicitante.save(solicitante);
        return ResponseEntity.created(uriComponentsBuilder.buildAndExpand("/resource/{id}", id).toUri()).body(body);
    }
    */

   }
