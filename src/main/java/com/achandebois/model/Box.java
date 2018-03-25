package com.achandebois.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Box {

    private int remainingSpace = 10;//TODO use variable

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        remainingSpace -= item.getSize();
        items.add(item);
    }

    public boolean hasEnoughSpaceToAddItem(int size) {
        if (remainingSpace - size >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasRemainingSpace() {
        return remainingSpace > 0;
    }
}
