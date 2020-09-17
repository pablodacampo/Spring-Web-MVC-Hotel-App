package dev.hotel.web.client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreerClientRequestDto {

	@NotBlank
	@Size(min = 2)
	private String nom;

	@NotBlank
	@Size(min = 3)
	private String prenoms;

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}

	/**
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

}