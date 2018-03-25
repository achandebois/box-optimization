package com.achandebois.service;


import com.achandebois.model.Box;
import com.achandebois.model.Item;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Factory {

    private static final Pattern ITEMS_PATTERN = Pattern.compile("[1-9]+");

    private List<Box> boxes = new ArrayList<>();

    private Map<Integer, Long> ItemStockBySize = new HashMap<>();

    public void optimize(String items) {

        if (StringUtils.isNotEmpty(items)) {

            if (!ITEMS_PATTERN.matcher(items).matches()) {
                throw new IllegalArgumentException("Invalid input items");
            }

            initItemStock(items);

            for (int itemSize = 1; itemSize < 10; itemSize++) {

                while (hasItemInStock(itemSize)) {

                    Box box = new Box();

                    fillBox(box, itemSize);

                    boxes.add(box);
                }
            }
        }
    }

    public int countBoxes() {
        return boxes.size();
    }

    private void fillBox(Box box, int itemSize) {

        while (box.hasRemainingSpace() && itemSize > 0) {

            if (box.hasEnoughSpaceToAddItem(itemSize) && hasItemInStock(itemSize)) {

                box.addItem(new Item(itemSize));

                withdrawItemFromStock(itemSize);

                itemSize = box.getRemainingSpace();
            } else {

                itemSize--;
            }

            fillBox(box, itemSize);
        }
    }

    private void withdrawItemFromStock(int itemSize) {
        ItemStockBySize.put(itemSize, ItemStockBySize.get(itemSize) - 1);
    }

    private boolean hasItemInStock(int i) {
        return ItemStockBySize.containsKey(i) && ItemStockBySize.get(i) > 0;
    }

    private void initItemStock(String items) {
        ItemStockBySize = items.chars()
                .mapToObj(Character::getNumericValue)
                .collect(Collectors.groupingBy(itemSize -> itemSize, Collectors.counting()));
    }
}