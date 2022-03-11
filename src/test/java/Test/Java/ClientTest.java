package Test.Java;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.h2.engine.User;
import org.junit.Test;

import epf.model.Client;

public class ClientTest{
	 @Test
	   public void isLegal_should_return_true_when_age_is_over_18() {
	       // Given
	       Client legalClient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2000, 07, 06));
	        
	       // Then
	       assertTrue(ClientValidator.isLegal(legalClient));
	   }

	   @Test
	  public void isLegal_should_return_false_when_age_is_under_18() {
	        // Given   
	        Client illegaClient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2010, 06, 10));
	        
	       // Then
	       assertFalse(ClientValidator.isLegal(illegaClient));
	   }
	}

