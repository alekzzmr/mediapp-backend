package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del paciente")
@Entity
@Table(name = "signos")
public class Signo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSigno;	
	
	@ManyToOne // FK
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
	private Paciente paciente;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	
	@Column(name = "temperatura", length = 5, nullable = true)
	private String temperatura;
	
	@Column(name = "pulso", length = 3, nullable = true)
	private String pulso;
	
	@Column(name = "ritmo", length = 3, nullable = true)
	private String ritmo;

	public Integer getIdSigno() {
		return idSigno;
	}

	public void setIdSigno(Integer idSigno) {
		this.idSigno = idSigno;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getRitmo() {
		return ritmo;
	}

	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}

	@Override
	public String toString() {
		return "Signos [idSignos=" + idSigno + ", paciente=" + paciente + ", fecha=" + fecha + ", temperatura="
				+ temperatura + ", pulso=" + pulso + ", ritmo=" + ritmo + ", getIdSignos()=" + getIdSigno()
				+ ", getPaciente()=" + getPaciente() + ", getFecha()=" + getFecha() + ", getTemperatura()="
				+ getTemperatura() + ", getPulso()=" + getPulso() + ", getRitmo()=" + getRitmo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}	
	
}
