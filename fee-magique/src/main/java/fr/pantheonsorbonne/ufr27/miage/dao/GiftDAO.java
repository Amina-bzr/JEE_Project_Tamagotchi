package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Gift;

import java.util.Collection;
import java.util.Optional;

public interface GiftDAO {
    void createGift(Gift gift);
    Collection<Gift> getAllGifts();
    Optional<Gift> getGiftById(Integer giftId);
}