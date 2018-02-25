package com.cafe.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class BannerDto {

	public BannerDto() {
	}

	private Long id;
	private String imagePath;
	private String position;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "O caminho da imagem deve ser informado.")
	@Length(min = 3, max = 255, message = "O caminho deve conter entre 3 e 255 caracteres.")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@NotEmpty(message = "A posição do banner deve ser informada.")
	@Length(min = 1, max = 255, message = "A posição do banner deve conter entre 1 e 255 caracteres.")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "BannerDto [id=" + id + ", imagePath=" + imagePath + ", position=" + position + "]";
	}

}
