package usjobs.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	String street;
	String city;
	String state;
	String zip;
	
}
