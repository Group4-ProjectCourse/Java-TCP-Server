package app.database;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Random;

public class CardDao {
    private final List<MagneticCard> cards = ImmutableList.of(
            new MagneticCard("ag34g-4g4g-gqr54", "4353"),
            new MagneticCard("35h5h-g1t4b-hh24b", "5312"),
            new MagneticCard("2h246-26n1fgb-13b135", "3467"),
            new MagneticCard("g35g-h426h-24t4g", "5551"),
            new MagneticCard("r14b531-g31-3g64j", "2456"),
            new MagneticCard("dg424-b24n1-b125n2", "6146")
    );

    public Iterable<MagneticCard> getAllCards() {
        return cards;
    }

    public MagneticCard getCardByUUID(String uuid) {
        return cards.stream()
                .filter(b -> b.getUUID().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    public MagneticCard getRandomCard() {
        return cards.get(new Random().nextInt(cards.size()));
    }
}
