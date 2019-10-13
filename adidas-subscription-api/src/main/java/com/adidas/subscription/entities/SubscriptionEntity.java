package com.adidas.subscription.entities;

import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_COL_CONSENT;
import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_COL_DATE_OF_BIRTH;
import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_COL_EMAIL;
import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_COL_FIRST_NAME;
import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_COL_GENDER;
import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_COL_NEWS_LETTER_ID;
import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_ENTITY_TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = SUBSCRIPTION_ENTITY_TABLE_NAME)
public class SubscriptionEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = SUBSCRIPTION_ENTITY_COL_FIRST_NAME)
	private String firstName;

	@Column(name = SUBSCRIPTION_ENTITY_COL_GENDER)
	private String gender;

	@Column(name = SUBSCRIPTION_ENTITY_COL_DATE_OF_BIRTH)
	private String dateOfBirth;

	@Column(name = SUBSCRIPTION_ENTITY_COL_CONSENT)
	private Boolean consent;

	@Column(name = SUBSCRIPTION_ENTITY_COL_NEWS_LETTER_ID)
	private Integer newsletterId;

	@Column(name = SUBSCRIPTION_ENTITY_COL_EMAIL, unique = true)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getConsent() {
		return consent;
	}

	public void setConsent(Boolean consent) {
		this.consent = consent;
	}

	public Integer getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(Integer newsletterId) {
		this.newsletterId = newsletterId;
	}

	@Override
	public String toString() {
		return "SubscriptionEntity [id=" + id + ", firstName=" + firstName + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", consent=" + consent + ", newsletterId=" + newsletterId + ", email=" + email + "]";
	}

}
