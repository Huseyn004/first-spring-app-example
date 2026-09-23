package com.example.firstspringappexample;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final List<String> items = new ArrayList<>();

    public List<String> getAllItems() {
        return items;
    }

    public void addItem(String newItem) {
        items.add(newItem);
    }

    public String deleteItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.remove(index);
        }
        return null;
    }

    public String updateItem(int index, String newItem) {
        if (index >= 0 && index < items.size()) {
            return items.set(index, newItem);
        }
        return null;
    }

    public String patchItem(int index, String updatedValue) {
        if (index >= 0 && index < items.size()) {
            if (updatedValue != null && !updatedValue.trim().isEmpty()) {
                items.set(index, updatedValue);
                return updatedValue;
            }
        }
        return null;
    }
}