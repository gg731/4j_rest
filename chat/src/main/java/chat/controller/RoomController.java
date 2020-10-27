package chat.controller;

import chat.domain.Message;
import chat.domain.Person;
import chat.domain.Room;
import chat.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomRepository rooms;

    public RoomController(RoomRepository rooms) {
        this.rooms = rooms;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return StreamSupport.stream(
                rooms.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<Message> roomMessages(@PathVariable int id) {
        return this.rooms.findById(id).get().getMessages();
    }

    @GetMapping("/{id}/persons")
    public List<Person> roomPersons(@PathVariable int id) {
        return this.rooms.findById(id).get().getPersons();
    }

    @PostMapping("/")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return new ResponseEntity<>(
                this.rooms.save(room), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/message")
    public ResponseEntity<Void> newMessage(@PathVariable int id, @RequestBody Message message) {
        Room room = rooms.findById(id).get();
        room.addMessage(message);
        rooms.save(room);

        return ResponseEntity.ok().build();
    }
}
