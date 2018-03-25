package com.achandebois.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoxTest {

    @Test
    void should_addItem_withItemOfSize2() {
        //Given
        Box box = new Box();

        Item item = new Item(2);

        //When
        box.addItem(item);

        //Then
        Assertions.assertThat(box.getRemainingSpace()).isEqualTo(8);
    }

    @Test
    void should_hasRemainingSpace_returnTrue_whenAvailableSpaceInTheBox() {
        //Given
        Box box = new Box();

        Item item = new Item(2);

        //When
        box.addItem(item);

        //Then
        Assertions.assertThat(box.hasRemainingSpace()).isTrue();
    }

    @Test
    void should_hasRemainingSpace_returnFalse_whenNoAvailableSpaceInTheBox() {
        //Given
        Box box = new Box();

        Item item = new Item(10);

        //When
        box.addItem(item);

        //Then
        Assertions.assertThat(box.hasRemainingSpace()).isFalse();
    }

    @Test
    void should_hasEnoughSpaceToAddItem_returnTrue_whenEnoughSpaceInTheBox() {
        //Given
        Box box = new Box();

        box.addItem(new Item(8));

        Item item = new Item(2);

        //When
        boolean hasEnoughSpaceToAddNewItem = box.hasEnoughSpaceToAddItem(item.getSize());

        //Then
        Assertions.assertThat(hasEnoughSpaceToAddNewItem).isTrue();
    }

    @Test
    void should_hasEnoughSpaceToAddItem_returnFalse_whenNotEnoughSpaceInTheBox() {
        //Given
        Box box = new Box();

        box.addItem(new Item(8));

        Item item = new Item(3);

        //When
        boolean hasEnoughSpaceToAddNewItem = box.hasEnoughSpaceToAddItem(item.getSize());

        //Then
        Assertions.assertThat(hasEnoughSpaceToAddNewItem).isFalse();
    }
}
