package br.fatec.capsula.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_integrante")
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Integrante extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_nome", length = 80)
	@NotBlank
	@Length(min = 2, max = 80)
	private String nome;

	@Column(name = "nm_email", length = 40)
	@Email
	private String email;

	@Column(name = "dt_publicacao")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date publicacao;

	@ManyToOne
	private Regiao regiao;

	public Date getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_perfil")
	@Setter(AccessLevel.NONE)
	private Set<Integer> perfis = new HashSet<>();

	@Column(name = "nm_login", length = 80, unique = true)
	private String login;
	
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	@Column(name = "nm_senha")
	private String senha;

	public Set<TipoPerfil> getPerfis() {
		return perfis.stream().map(x -> TipoPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(TipoPerfil perfil) {
		this.perfis.add(perfil.getCod());
	}
	
	
}