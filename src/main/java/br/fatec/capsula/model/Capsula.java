package br.fatec.capsula.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_capsula")
@Entity
@Getter
@Setter
@ToString (callSuper = true)
@NoArgsConstructor
public class Capsula extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	

	@Column(name = "nm_title", length = 120)
	@NotBlank
	@Length(min = 4, max = 120)
	private String title;

	@NotNull
	@Column(name = "nr_ano")
	private Integer ano;

	@Column(name = "ds_descricao")
	private String descricao;

	@Column(name = "nm_imageUrl")
	private String imageUrl;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Integrante> integrantes;
	

}
