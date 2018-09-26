package br.com.app.api.establishments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.app.api.establishments.controller.EstablishmentFilter;
import br.com.app.api.establishments.domain.Establishment;
import br.com.app.api.establishments.repository.EstablishmentRepository;
import br.com.app.api.establishments.service.validator.EstablishmentValidator;

@Service
public class EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	@Autowired
	private EstablishmentValidator establishmentValidator;

	public Establishment save(Establishment establishment) {
		establishmentValidator.validate(establishment);
		return establishmentRepository.save(establishment);
	}

	public Establishment find(Long id) {
		return establishmentRepository.findOne(id);
	}
	
	public Page<Establishment> findAll(EstablishmentFilter filter, Pageable page) {

		return establishmentRepository.findAll(filter.build(),page);
	}
	
	
	public List<Establishment> findAll() {

		return establishmentRepository.findAll();
	}
	
	public void delete(Long id) {
		establishmentRepository.delete(id);
	}
}
