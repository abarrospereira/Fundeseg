package br.com.app.api.establishments.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.api.establishments.domain.Establishment;
import br.com.app.api.establishments.repository.EstablishmentRepository;
import br.com.app.api.establishments.service.EstablishmentService;


@RestController
@RequestMapping("/rest/establishment")
public class EstablishmentController {

	@Autowired
	private EstablishmentService establishmentService;
	
	@Autowired
	private EstablishmentRepository establishmentRepository;
	

	@RequestMapping(method = POST)
	@PreAuthorize("hasRole('ROLE_USER')")
	public Establishment add(@RequestBody Establishment establishment) {
		return establishmentService.save(establishment);
		
	}
	
	@RequestMapping(method = PUT)
	@Transactional
	public void edit(@RequestBody Establishment establishment)  {
		establishmentRepository.save(establishment);
	}
	
	@RequestMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	public Page<Establishment> findAll(EstablishmentFilter filter, Pageable pageable) {
		return establishmentService.findAll(filter,pageable);
	}
	
	@RequestMapping(method = GET,path="/{id}")
	@PreAuthorize("hasRole('USER_VIEW')")
	public Establishment get(@PathVariable Long id) throws NotFoundException {
		Establishment user = establishmentService.find(id);
		if(user==null) {
			throw new NotFoundException();
		}
		return user;
	}
	
	@RequestMapping(method = DELETE,path="/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public void delete(@PathVariable Long id) {
		establishmentService.delete(id);	
	}
	

}
