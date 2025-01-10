package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Gift;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class GiftDAOImpl implements GiftDAO {

    private final Collection<Gift> gifts = new ArrayList<>();

    @Override
    public void createGift(Gift gift) {
        gifts.add(gift);
    }

    @Override
    public Collection<Gift> getAllGifts() {
        return new ArrayList<>(gifts);
    }

    @Override
    public Optional<Gift> getGiftById(Integer giftId) {
        return gifts.stream()
                .filter(gift -> gift.getId().equals(giftId))
                .findFirst();
    }
}
