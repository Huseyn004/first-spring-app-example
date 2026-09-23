package com.example.firstspringappexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RestController
@RequestMapping("/api/items")
public class FirstSpringAppExampleApplication implements CommandLineRunner {

    private final ItemService itemService;
    public FirstSpringAppExampleApplication(ItemService itemService) {
        this.itemService = itemService;
    }
    public static void main(String[] args) {
        SpringApplication.run(FirstSpringAppExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- INITIALIZE LIST VIA SCANNER ---");
        System.out.print("How many initial items do you want to add? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.print("Enter item " + (i + 1) + ": ");
            itemService.addItem(scanner.nextLine());
        }

        System.out.println("Initial list populated successfully! Web server is ready for Postman requests.\n");
    }

    @GetMapping
    public List<String> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public String addItem(@RequestBody String newItem) {
        itemService.addItem(newItem);
        return "Item '" + newItem + "' added successfully!";
    }

    @DeleteMapping("/{index}")
    public String deleteItem(@PathVariable int index) {
        String removedItem = itemService.deleteItem(index);
        if (removedItem != null) {
            return "Successfully deleted: " + removedItem;
        }
        return "Error: element with number " + index + " does not exist!";
    }

    @PutMapping("/{index}")
    public String updateItem(@PathVariable int index, @RequestBody String newItem) {
        String oldItem = itemService.updateItem(index, newItem);
        if (oldItem != null) {
            return "Element '" + oldItem + "' changed successfully to '" + newItem + "'!";
        }
        return "Error: element with number " + index + " does not exist!";
    }

    @PatchMapping("/{index}")
    public String patchItem(@PathVariable int index, @RequestBody String updatedValue) {
        String updated = itemService.patchItem(index, updatedValue);
        if (updated != null) {
            return "Element with index " + index + " partially updated: '" + updated + "'";
        }
        return "Error: element with index " + index + " does not exist or invalid value passed!";
    }
}