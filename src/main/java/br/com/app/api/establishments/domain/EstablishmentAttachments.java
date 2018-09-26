package br.com.app.api.establishments.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class EstablishmentAttachments {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@NotNull(message="establishment.attachment.name.cant.be.null")
	@Column(name = "attachment_id")
	private String attachment_id;

	@NotNull(message="establishment.attachment.file.name.cant.be.null")
	@Column(name = "fileName")
	private String fileName;
	
	@NotNull(message="establishment.attachment.description.cant.be.null")
	@Column(name = "description")
	private String description;
	
	@NotNull(message="establishment.attachment.link.cant.be.null")
	@Column(name = "link")
	private String link;
	
	@NotNull(message="establishment.attachment.title.cant.be.null")
	@Column(name = "title")
	private String title;
	
	@NotNull(message="establishment.attachment.size.cant.be.null")
	@Column(name = "size")
	private Number size;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "establishment_id", referencedColumnName = "id")
	@JsonIgnore
	private Establishment establishment;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public Number getSize() {
		return size;
	}

	public void setSize(Number size) {
		this.size = size;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	
}
