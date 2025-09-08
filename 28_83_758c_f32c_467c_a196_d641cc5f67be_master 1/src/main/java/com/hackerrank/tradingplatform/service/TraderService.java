package com.hackerrank.tradingplatform.service;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TraderService {
    @Autowired
    private TraderRepository traderRepository;

    public Trader registerTrader(Trader trader) {
   if(traderRepository.findByEmail(trader.getEmail()).isPresent()){
    throw new  RuntimeException();
   }
        trader.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return traderRepository.save(trader);
    }

    public Trader getTraderById(Long id) {
        return traderRepository.findById(id).get();
    }

    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email).orElse(null);
    }

    public List<Trader> getAllTraders() {
        return traderRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Trader updateTrader(UpdateTraderDTO trader) {
        Trader existingTrader = traderRepository.findByEmail(trader.getEmail()).get();
        existingTrader.setName(trader.getName());
       // existingTrader.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return traderRepository.save(existingTrader);
    }

    public void addMoney(AddMoneyTraderDTO trader) {
        Trader existingTrader = traderRepository.findByEmail(trader.getEmail()).get();
        
        existingTrader.setBalance(existingTrader.getBalance()+trader.getAmount());

        traderRepository.save(existingTrader);
    }
}
