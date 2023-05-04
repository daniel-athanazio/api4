package br.com.edusync.desafio.controllerPlayer;

import br.com.edusync.desafio.service.Serviceitens;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/items")
public class ItemController {

    private final Serviceitens itemService;

    public ItemController(Serviceitens itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/new")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item createdItem = itemService.createItem(item);
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        } catch (MissingArgumentException e) {
            return new ResponseEntity<>("Error: Missing arguments to create item", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> listAllItems() {
        List<Item> items = itemService.listAllItems();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        try {
            Item item = itemService.findItemById(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>("Error: Item not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item newItem) {
        try {
            Item updatedItem = itemService.updateItem(id, newItem);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>("Error: Item not found", HttpStatus.NOT_FOUND);
        } catch (MissingArgumentException e) {
            return new ResponseEntity<>("Error: Missing arguments to update item", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>("Item deleted", HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>("Error: Item not found", HttpStatus.NOT_FOUND);
        }
    }

