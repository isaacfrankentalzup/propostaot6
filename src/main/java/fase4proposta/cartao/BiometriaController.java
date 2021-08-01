package fase4proposta.cartao;

import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    private BiometriaRepository biometriaRepository;
    private CartaoRepository cartaoRepository;

    public BiometriaController(BiometriaRepository biometriaRepository, CartaoRepository cartaoRepository) {
        this.biometriaRepository = biometriaRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/{cartaoId}")
    //public void salvaBiometria(@PathVariable Long cartaoId)
    public ResponseEntity<?> salvaBiometria(@PathVariable Long cartaoId){
        Optional<Cartao> temCartao = cartaoRepository.findById(cartaoId);


        Long numeroAleatorio = new Random().nextLong();
        byte[] finger = numeroAleatorio.toString().getBytes();
        String fingerprint = Base64.getEncoder().encodeToString(finger);

        if (temCartao.isPresent()){
            biometriaRepository.save(new Biometria(fingerprint));
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
