package Test.Java;

import org.junit.Test;

import epf.model.Client;

public class ClientValidator {
   /**
    * Renvoie true si l’utilisateur passé en paramètre a un age >= 18 ans
    * @param client L'instance d’utilisateur à tester
    * @return Résultat du test (>= 18 ans)
    */

   public static boolean isLegal(Client client) {
       return
          client.getAge() >= 18;
   }
}
