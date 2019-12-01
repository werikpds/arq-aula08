package aula08.rest.json.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aula08.rest.json.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	public Cidade findByLatitudeAndLongitude(Double latitude, Double longitude);
	
	public List<Cidade> findByNomeStartingWith(String nome);

}
