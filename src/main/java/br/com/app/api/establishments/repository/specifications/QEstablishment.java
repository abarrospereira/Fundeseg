package br.com.app.api.establishments.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.app.api.establishments.domain.Establishment;

public class QEstablishment {

	public static Specification<Establishment> likeName(String name) {
		if (name == null) {
			return null;
		}

		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
	}

	public static Specification<Establishment> equalsPhone(String phone) {
		if (phone == null) {
			return null;
		}
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("telephone"), phone);
	}

	public static Specification<Establishment> equalsCpf(String cpf) {
		if (cpf == null) {
			return null;
		}
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("cpf"), cpf);
	}

	public static Specification<Establishment> equalsCnpj(String cnpj) {
		if (cnpj == null) {
			return null;
		}
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("cnpj"), cnpj);
	}

}
