package fase4proposta.proposta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitante")
public class ControllerSolicitante {

    RepositorySolicitante repositorySolicitante;

    public ControllerSolicitante(RepositorySolicitante repositorySolicitante) {
        this.repositorySolicitante = repositorySolicitante;
    }


    @PostMapping
    public ResponseEntity<?> test (@RequestBody @Valid RequestSolicitante requestSolicitante){

        Boolean existeDocumento = repositorySolicitante.existsByDocumento(requestSolicitante.getDocumento());
        Boolean existeEmail = repositorySolicitante.existsByEmail(requestSolicitante.getEmail());


        if(existeDocumento) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestSolicitante);  // pas sur
        }

        if(existeEmail) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestSolicitante);  // pas sur
        }

            Solicitante solicitante = requestSolicitante.toSolicitante();

            repositorySolicitante.save(solicitante);

            return ResponseEntity.status(201).build();

    }

    
   }
