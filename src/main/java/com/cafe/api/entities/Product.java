package com.cafe.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public Product() {
	}

	private Long id;
	private String name;
	private String description;
	private String keywords;
	private String imagePath;
	private Category category;
	private Date createdAt;
	private Date updatedAt;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Lob
	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public Optional<String> getDescriptionOpt() {
		return Optional.ofNullable(description);
	}

	@Column(name = "keywords", nullable = true)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Transient
	public Optional<String> getKeywordsOpt() {
		return Optional.ofNullable(keywords);
	}

	@Column(name = "image_path", nullable = true)
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Transient
	public Optional<String> getImagePathOpt() {
		return Optional.ofNullable(imagePath);
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "created_at", nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "update_at", nullable = false)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date now = new Date();
		this.createdAt = now;
		this.updatedAt = now;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", keywords=" + keywords
				+ ", imagePath=" + imagePath + ", category=" + category + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
}
