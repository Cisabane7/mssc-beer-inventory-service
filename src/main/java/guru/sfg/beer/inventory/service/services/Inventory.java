package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.brewery.model.events.BeerDto;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Inventory {
    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        BeerDto beerDto = event.getBeerDto();
        BeerInventory saved = beerInventoryRepository.save(BeerInventory.
                builder().
                beerId(beerDto.getId()).
                upc(beerDto.getUpc()).
                quantityOnHand(beerDto.getQuantityOnHand()).
                build());
        log.debug("New inventory done with quantity = {}",saved.getQuantityOnHand());
    }
}
