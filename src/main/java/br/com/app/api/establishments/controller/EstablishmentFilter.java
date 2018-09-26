package br.com.app.api.establishments.controller;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import br.com.app.api.establishments.domain.Establishment;
import br.com.app.api.establishments.repository.specifications.QEstablishment;

public class EstablishmentFilter {

	private String term;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Specification<Establishment> build() {
		return Specifications.where(QEstablishment.likeName(term))
				             .and(QEstablishment.equalsCpf(term))
				             .and(QEstablishment.equalsPhone(term));
	}
}
