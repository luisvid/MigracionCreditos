/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import java.math.BigDecimal;

import java.math.BigInteger;

import java.util.Hashtable;
import oracle.toplink.essentials.internal.databaseaccess.FieldTypeDefinition;
import oracle.toplink.essentials.internal.databaseaccess.DatabasePlatform;
 



/**
 *
 * @author analian
 */
public class CustomFirebirdPlatform extends DatabasePlatform {
   @Override

               protected Hashtable buildFieldTypes() {

                              Hashtable fieldTypes = super.buildFieldTypes();

                              

                              fieldTypes.put(Long.class, new FieldTypeDefinition("INTEGER"));

                              fieldTypes.put(Integer.class, new FieldTypeDefinition("INTEGER"));

                              fieldTypes.put(BigInteger.class, new FieldTypeDefinition("INTEGER"));

                              fieldTypes.put(BigDecimal.class, new FieldTypeDefinition("DECIMAL").setLimits(9,0,9));

                              fieldTypes.put(Short.class, new FieldTypeDefinition("SMALLINT"));

                              

                              return fieldTypes;  
}
}
