package com.cafe.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoryDto {

	public CategoryDto() {
	}

	private Long id;
	private String name;
	private String imagePath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "O nome deve ser informado.")
	@Length(min = 3, max = 20, message = "O nome deve ter entre 3 e 20 caracteres.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "O caminho da imagem deve ser informado.")
	@Length(min = 3, max = 255, message = "O caminho da imagem deve ter entre 3 e 255 caracteres.")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", name=" + name + ", imagePath=" + imagePath + "]";
	}

}
