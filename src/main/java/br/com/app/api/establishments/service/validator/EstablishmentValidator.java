package br.com.app.api.establishments.service.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.app.api.establishments.domain.Establishment;
import br.com.app.api.establishments.repository.EstablishmentRepository;
import br.com.app.api.establishments.repository.specifications.QEstablishment;
import br.com.app.api.exception.ValidationException;
import br.com.app.api.util.messages.ErrorCode;
import br.com.app.api.util.messages.Message;

@Service
public class EstablishmentValidator {

	@Autowired
	private EstablishmentRepository establishmentRepository;

	public void validate(Establishment establishment) {
		List<Message> errors = new ArrayList<>();
		Establishment saved = establishmentRepository.findOne(Specifications.where(QEstablishment.equalsCnpj(establishment.getCnpj())));
		isEstablishmentAlreadyExist(errors, saved);
		throwsErrors(errors);
	}

	protected void isEstablishmentAlreadyExist(List<Message> errors, Establishment saved) {
		if (!Objects.isNull(saved)) {
			errors.add(ErrorCode.ESTABLISHMENT_ALREADY_EXISTS.getfundesegError());
			return;
		}
	}

	protected void throwsErrors(List<Message> errors) {
		if (!CollectionUtils.isEmpty(errors)) {
			  throw new ValidationException(errors);
		}
	}
	
	
}
