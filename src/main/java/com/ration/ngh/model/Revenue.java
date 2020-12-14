package com.ration.ngh.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Revenue {
	
	private int revenue;
    
    private String owner;
    
}
