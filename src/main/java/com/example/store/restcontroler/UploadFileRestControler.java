package com.example.store.restcontroler;

import com.example.store.service.UploadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.websocket.server.PathParam;
import java.io.File;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/upload")
public class UploadFileRestControler {
    @Autowired
    UploadService uploadService;

    @PostMapping("/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable String folder) {
        File saveFile = uploadService.save(file, folder);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode nodes = objectMapper.createObjectNode();
        nodes.put("name", saveFile.getName().toString());
        nodes.put("size", saveFile.length());
        return nodes;
    }
}
