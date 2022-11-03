package pl.waw.great.shop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.waw.great.shop.model.dto.MessageDto;
import pl.waw.great.shop.service.MessageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public MessageDto create(@RequestBody @Valid MessageDto messageDto) {
        return this.messageService.create(messageDto);
    }
}
