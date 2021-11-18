package br.fatec.capsula.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_regiao")
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Regiao extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	public Regiao() {}

	@Column(name = "nm_cidade", length = 50)
	@NotBlank
	@Length(min = 2, max = 50)
	private String cidade;

	@Column(name = "nm_estado", length = 50)
	@NotBlank
	@Length(min = 2, max = 50)
	private String estado;
	
	@Builder
	public Regiao(Long id, @NotBlank @Length(min = 2, max = 50) String cidade,
			@NotBlank @Length(min = 2, max = 50) String estado) {
		super(id);
		this.cidade = cidade;
		this.estado = estado;
	}
	
	


}
