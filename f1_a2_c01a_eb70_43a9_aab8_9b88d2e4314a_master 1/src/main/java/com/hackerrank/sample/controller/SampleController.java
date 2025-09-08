package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {

	
	   final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	   RestTemplate restTemplate = new RestTemplate();
	   String result = restTemplate.getForObject(uri, String.class);			
	   JSONObject root = new JSONObject(result);
	   
	   JSONArray data = root.getJSONArray("data");
	   
	   
		
		@CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ? > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{
             

   List<FilteredProducts> list=new ArrayList<>();
	 for(int i=0;i<data.length();i++){
		JSONObject json=data.getJSONObject(i);
		int price=json.getInt("price");
	

		if(price>init_price && price<final_price){
				String barcode=json.getString("barcode");
			 list.add(new FilteredProducts(barcode));
		}

	 }

	 return  list.size()>0?ResponseEntity.status(200).body(list):ResponseEntity.notFound().build();
		}

	 
	 /*List<FilteredProducts> arr = new ArrayList<>();
	 
	 for(int i=0;i<data.length();i++) {
		 JSONObject json = data.getJSONObject(i);
		 int price = json.getInt("price");
		 
		 if(price>=init_price && price<=final_price) {
			 String barcode = json.getString("barcode");
			 arr.add(new FilteredProducts(barcode));
		 }
	 }
	 return arr.size()>0 ? ResponseEntity.status(200).body(arr): ResponseEntity.notFound().build();		
	}*/
			
@CrossOrigin
    @GetMapping("/sort/price")
private ResponseEntity<?> sorted_books() {
    List<SortedProducts> list = new ArrayList<>();

    // Build product objects
    for (int i = 0; i < data.length(); i++) {
        JSONObject j = data.getJSONObject(i);
        String barcode = j.getString("barcode");
        int price = j.getInt("price");

        list.add(new SortedProducts(barcode, price));
    }

    // Sort by price ascending and map to FilteredProducts
    List<FilteredProducts> filteredProductses =
        list.stream()
            .sorted(Comparator.comparing(SortedProducts::getPrice))
            .map(p -> new FilteredProducts(p.getBarcode()))
						.collect(Collectors.toList());

    return new ResponseEntity<>(filteredProductses, HttpStatus.OK);
}
}

		

            // Sort by price ascending
		// 	try {
				
			
		// 			ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
			    
		// 		    return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);

			   
			    
		// 	}catch(Exception E)
		// 		{
	  //  	System.out.println("Error encountered : "+E.getMessage());
	  //   return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
		// 		}
			
		// }  
		
		
// 		@CrossOrigin
// 		@GetMapping("/sort/price")  
// 		private ResponseEntity<SortedProducts[]> sorted_books()   
// 		{  
			
// 			try {
				
// 		         SortedProducts[] ans=new SortedProducts[data.length()];

			
		         
	
// 			    return new ResponseEntity<SortedProducts[]>(ans, HttpStatus.OK);
			    
// 			}catch(Exception E)
// 				{
// 	   	System.out.println("Error encountered : "+E.getMessage());
// 	    return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
// 				}
			
// 		}  
		
		
	
// }
