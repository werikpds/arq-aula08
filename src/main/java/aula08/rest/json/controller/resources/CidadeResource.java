package aula08.rest.json.controller.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import aula08.rest.json.model.beans.Cidade;
import aula08.rest.json.model.repository.CidadeRepository;

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {
	
	@Autowired 
	private CidadeRepository cidadeRepo;

	@GetMapping ("/lista") 
	public List <Cidade> todasAsCidades (){ 
		return cidadeRepo.findAll(); 
	}
	
	
	@PostMapping ("/salvar")
	// agora desnecessário
	// @ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Cidade> salvar (@RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().
				path("/{id}").buildAndExpand(c.getId()).toUri();
		// desnecessário também
		// response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(cidade);
	}
	
	@GetMapping ("findById/{id}") 
	public Cidade buscarPeloId (@PathVariable Long id) { 
		return cidadeRepo.getOne(id); 
	}
	
	@GetMapping ("findByCoordinate/{latitude}/{longitude}")
	public Cidade buscarPorLatELong (@PathVariable Double latitude, @PathVariable Double longitude) {
		return cidadeRepo.findByLatitudeAndLongitude(latitude, longitude);
	}
	
	@GetMapping ("findByName/{nome}")
	public List<Cidade> buscarPorCidadeComecaCom (@PathVariable String nome) {
		return cidadeRepo.findByNomeStartingWith(nome);
	}
	
}
