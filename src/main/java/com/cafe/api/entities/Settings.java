package com.cafe.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "settings")
public class Settings implements Serializable {

	private static final long serialVersionUID = 1L;

	public Settings() {
	}
	
	private Long id;
	private String aboutUs;
	private Boolean showPrice;
	private String contactUsText;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Lob
	@Column(name = "about_us", nullable = false)
	public String getAboutUs() {
		return aboutUs;
	}


	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	@Column(name = "show_price", nullable = false)
	public Boolean getShowPrice() {
		return showPrice;
	}


	public void setShowPrice(Boolean showPrice) {
		this.showPrice = showPrice;
	}

	@Lob
	@Column(name = "contact_us_text", nullable = false)
	public String getContactUsText() {
		return contactUsText;
	}


	public void setContactUsText(String contactUsText) {
		this.contactUsText = contactUsText;
	}

	@Override
	public String toString() {
		return "Settings [id=" + id + ", aboutUs=" + aboutUs + ", showPrice=" + showPrice + ", contactUsText="
				+ contactUsText + "]";
	}
}
