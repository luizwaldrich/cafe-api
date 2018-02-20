package com.cafe.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	public Category() {
	}
	
	private Long id;
	private String name;
	private String imagePath;
	private Date createdAt;	
	private Date updatedAt;	
	private List<Product> products;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	@OneToMany(mappedBy="category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
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
		final Date now =  new Date();
		this.createdAt = now;
		this.updatedAt = now;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", imagePath=" + imagePath + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", products=" + products + "]";
	}
}
