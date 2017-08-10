package com.salvavidas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPO_PESSOA", discriminatorType=DiscriminatorType.STRING)
public abstract class Pessoa {

	//private static final long serialversionUID = 1L;

	private long codigo;
	private String nome;
	private String endereco;
	private String cidade;
	private Provincia provincia;
	private String telefone;
	private Sexo sexo;
	private String bi;
	private String gsanguinio;
	private Date dataCadastro;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	@NotBlank @Size(max=100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@NotBlank @Size(max=100)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	//@NotBlank @Size(max=100)
	@Enumerated(EnumType.STRING)
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	@NotBlank @Size(max=100)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	//@NotBlank @Size(max=100)
	@Enumerated(EnumType.STRING)
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	@NotBlank @Size(max=100)
	@Column(name="grupo_sanguinio")
	public String getGsanguinio() {
		return gsanguinio;
	}

	public void setGsanguinio(String gsanguinio) {
		this.gsanguinio = gsanguinio;
	}
	@Temporal(TemporalType.DATE)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
		
}
