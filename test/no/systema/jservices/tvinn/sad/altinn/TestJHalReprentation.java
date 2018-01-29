package no.systema.jservices.tvinn.sad.altinn;

import static de.otto.edison.hal.EmbeddedTypeInfo.withEmbedded;
//import static de.otto.edison.hal.EmbeddedTypeInfo.withEmbedded;
import static de.otto.edison.hal.HalParser.parse;
//import static de.otto.edison.hal.HalParser.parse;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.otto.edison.hal.HalRepresentation;


//https://github.com/otto-de/edison-hal/blob/master/src/test/java/de/otto/edison/hal/HalParserTest.java

/*
{
	   "_links":{
	      "find":{
	         "href":"https://tt02.altinn.no/api/810514442/messages/{messageId}",
	         "isTemplated":true
	      },
	      "portalview":{
	         "href":"https://tt02.altinn.no/Pages/ServiceEngine/MyMainPage/MyMainPage.aspx"
	      },
	      "self":{
	         "href":"https://tt02.altinn.no/api/810514442/messages"
	      }
	   },
	   "_embedded":{
	      "messages":[
	         {
	            "MessageId":"a5308314",
	            "Subject":"Title",
	            "Status":"Lest",
	            "LastChangedDateTime":"2017-12-14T15:27:03.627",
	            "CreatedDate":"2017-12-14T15:26:24.81",
	            "LastChangedBy":"AutoRegTest",
	            "ServiceOwner":"Samlesider",
	            "Type":"Correspondence",
	            "MessageSender":"Samlesider",
	            "ServiceCode":"3996",
	            "ServiceEdition":201503,
	            "DueDate":"2017-12-16T00:00:00",
	            "_links":{
	               "self":{
	                  "href":"https://tt02.altinn.no/api/810514442/messages/a5308314"
	               },
	               "metadata":{
	                  "href":"https://tt02.altinn.no/api/metadata/correspondence/3996/201503"
	               },
	               "portalview":{
	                  "href":"https://tt02.altinn.no/Pages/ServiceEngine/Correspondence/Correspondences.aspx?ReporteeElementID=5308314&ESC=3996&ESEC=201503"
	               }
	            }
	         }
	      ]
	   }
	}
*/

public class TestJHalReprentation {

	@Before
	public void setUp() throws Exception {
	}

	    @Test
	    public void shouldParseEmbeddedItemsWithSpecificType() throws IOException {
	        // given
	        final String json =
	                "{" +
	                        "\"_embedded\":{\"bar\":[" +
	                        "   {" +
	                        "       \"value\":\"3\"," +
	                        "       \"_links\":{\"self\":[{\"href\":\"http://example.org/test/bar/01\"}]}" +
	                        "   }" +
	                        "]}" +
	                        "}";

	        
	        
	        // when
	        final SimpleHalRepresentation result = parse(json)
	                .as(SimpleHalRepresentation.class, withEmbedded("bar", EmbeddedHalRepresentation.class));
	        // then
	        final List<EmbeddedHalRepresentation> embeddedItems = result.getEmbedded().getItemsBy("bar", EmbeddedHalRepresentation.class);
	        
	 
	    }
	 
	 

     static class SimpleHalRepresentation extends HalRepresentation {
         @JsonProperty("someProperty")
         private String someProperty = "foo";
         @JsonProperty("someOtherProperty")
         private String someOtherProperty = "bar";
     } 	 
	 

     static class EmbeddedHalRepresentation extends HalRepresentation {
         @JsonProperty("value")
         String value = "foobar";
     }    
     
     
     
	 
}
