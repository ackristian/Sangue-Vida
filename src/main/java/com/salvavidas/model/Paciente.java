package com.salvavidas.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("paciente")
public class Paciente extends Pessoa {
	
	private byte[] foto;

	@Lob
	@Column(name="foto_paciente")
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Boolean hasFoto(){
		return this.foto != null && this.foto.length > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(foto);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (!Arrays.equals(foto, other.foto))
			return false;
		return true;
	}
}
