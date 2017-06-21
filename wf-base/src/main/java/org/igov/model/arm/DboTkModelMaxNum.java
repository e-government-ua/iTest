package org.igov.model.arm;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@JsonRootName(value = "dboTkModelMaxNum")
public class DboTkModelMaxNum implements Serializable {
	private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	 @JsonProperty(value = "number_441")
     private Integer number_441;
	 

	public Integer getNumber_441() {
		return number_441;
	}





	public void setNumber_441(Integer number_441) {
		this.number_441 = number_441;
	}





		@Override
		public String toString() {
			try {
				return new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)
						.writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (JsonProcessingException e) {
				LOG.info(String.format("error [%s]", e.getMessage()));
			}
			return null;
		}
}