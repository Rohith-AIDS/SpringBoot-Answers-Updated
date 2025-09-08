package com.hackerrank.tradingplatform.controller;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.TraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/trading/traders")
public class TraderController {
    @Autowired
    private TraderService traderService;

    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
   
    public ResponseEntity<Trader> registerTrader(@RequestBody @Valid Trader trader) {
        try{
         Trader t= traderService.registerTrader(trader);
         return new ResponseEntity<>(t,HttpStatus.CREATED);
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().build();

        }
        
    }

    //get by email
    @RequestMapping(method = RequestMethod.GET)
    
    public ResponseEntity<?> getTraderByEmail(@RequestParam("email") String email) {
        try{
        TraderDTO t=new TraderDTO(traderService.getTraderByEmail(email));
        return ResponseEntity.ok(t);
        }
        catch(RuntimeException e){
             return ResponseEntity.status(404).build();
        }
        
        
    }

    //get all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TraderDTO> getAllTraders() {

        return traderService.getAllTraders()
                .stream()
                .map(TraderDTO::new)
                .collect(Collectors.toList());
    }

    //update by email
    @RequestMapping(method = RequestMethod.PUT)
    
    public ResponseEntity<?> updateTrader(@RequestBody @Valid UpdateTraderDTO trader) {
        try{
        Trader t=traderService.updateTrader(trader);
        return ResponseEntity.ok(t);
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    //add money
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addMoney(@RequestBody @Valid AddMoneyTraderDTO trader) {
        traderService.addMoney(trader);
    }
}
