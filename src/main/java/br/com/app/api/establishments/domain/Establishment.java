package br.com.app.api.establishments.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "establishment")
public class Establishment {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@NotNull(message="establishment.name.cant.be.null")
	@Column(name = "name")
	private String name;
	
	@NotNull(message="establishment.responsible.cant.be.null")
	@Column(name = "responsible")
	private String responsible;
	
	@NotNull(message="establishment.cnpj.cant.be.null")
	@Column(name = "cnpj")
	private String cnpj;
	
	@NotNull(message="establishment.cpf.cant.be.null")
	@Column(name = "cpf")
	private String cpf;
	
	@NotNull(message="establishment.activity.cant.be.null")
	@Column(name = "activity")
	private String activity;
	
	@NotNull(message="establishment.category.cant.be.null")
	@Column(name = "category")
	private Long category;
	
	@NotNull(message="establishment.address.cant.be.null")
	@Column(name = "address")
	private String address;
	
	@NotNull(message="establishment.number.cant.be.null")
	@Column(name = "number")
	private String number;
	
	@NotNull(message="establishment.city.cant.be.null")
	@Column(name = "city")
	private String city;
	
	@NotNull(message="establishment.telephone.cant.be.null")
	@Column(name = "telephone")
	private String telephone;
	
	@NotNull(message="establishment.observation.cant.be.null")
	@Column(name = "observation")
	private String observation;

	@NotNull(message="establishment.cep.cant.be.null")
	@Column(name = "cep")
	private String cep;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "establishment_id")
	private Set<EstablishmentAttachments> establishmentAttachments = new HashSet<EstablishmentAttachments>();

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getResponsible() {
		return responsible;
	}

	
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	
	public String getCnpj() {
		return cnpj;
	}

	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
	public String getCpf() {
		return cpf;
	}

	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public String getActivity() {
		return activity;
	}

	
	public void setActivity(String activity) {
		this.activity = activity;
	}

	
	public Long getCategory() {
		return category;
	}

	
	public void setCategory(Long category) {
		this.category = category;
	}

	
	public String getAddress() {
		return address;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getNumber() {
		return number;
	}

	
	public void setNumber(String number) {
		this.number = number;
	}

	
	public String getCity() {
		return city;
	}

	
	public void setCity(String city) {
		this.city = city;
	}

	
	public String getTelephone() {
		return telephone;
	}

	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	public String getObservation() {
		return observation;
	}

	
	public void setObservation(String observation) {
		this.observation = observation;
	}
	

	public String getCep() {
		return cep;
	}

	
	public void setCep(String cep) {
		this.cep = cep;
	}


	public Set<EstablishmentAttachments> getEstablishmentAttachments() {
		return establishmentAttachments;
	}


	public void setEstablishmentAttachments(Set<EstablishmentAttachments> establishmentAttachments) {
		this.establishmentAttachments = establishmentAttachments;
	}
	

	
}
