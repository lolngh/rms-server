package com.ration.ngh.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="rationshop")
@Data
public class RationModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String address;
	private String city;
	private String country;
	private String name;
	private String state;

	
	@Embedded
    private Revenue revenue;

	
}
