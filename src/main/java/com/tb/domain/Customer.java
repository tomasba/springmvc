package com.tb.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Customer implements DomainItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
    @OneToOne
    private User user;	
// commented out as spring.jpa.hibernate.naming.implicit-strategy property used instead	
//    @AttributeOverrides({
//        @AttributeOverride(name = "addressLineOne", column = @Column(name = "BILLING_ADDR1")),
//        @AttributeOverride(name = "addressLineTwo", column = @Column(name = "BILLING_ADDR2")),
//        @AttributeOverride(name = "city", column = @Column(name = "BILLING_CITY")),
//        @AttributeOverride(name = "state", column = @Column(name = "BILLING_STATE")),
//        @AttributeOverride(name = "zipCode", column = @Column(name = "BILLING_ZIPCODE"))
//    })
    @Embedded
    private Address billingAddress;    
    
// commented out as spring.jpa.hibernate.naming.implicit-strategy property used instead    
//    @AttributeOverrides({
//        @AttributeOverride(name = "addressLineOne", column = @Column(name = "SHIPPING_ADDR1")),
//        @AttributeOverride(name = "addressLineTwo", column = @Column(name = "SHIPPING_ADDR2")),
//        @AttributeOverride(name = "city", column = @Column(name = "SHIPPING_CITY")),
//        @AttributeOverride(name = "state", column = @Column(name = "SHIPPING_STATE")),
//        @AttributeOverride(name = "zipCode", column = @Column(name = "SHIPPING_ZIPCODE"))
//    })    
    @Embedded
    private Address shippingAddress;    
    
	@Version
	private Integer version;	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}    	

}
