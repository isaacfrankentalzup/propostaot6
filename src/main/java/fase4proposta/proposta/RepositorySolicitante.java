package fase4proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySolicitante extends JpaRepository<Solicitante, Long> {
    Boolean existsByDocumento(String documento);
    Boolean existsByEmail(String email);
}
