package com.prompt.vault.controller;

import com.prompt.vault.models.MemoryDTO;
import com.prompt.vault.models.ResponseDTO;
import com.prompt.vault.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PromptVaultController {
    @Autowired
    private MemoryService memoryService;

    @PostMapping("/memory")
    public ResponseEntity<ResponseDTO> saveMemory(@RequestBody MemoryDTO memoryDTO) {
        if (null == memoryDTO || null == memoryDTO.getUserId() || null == memoryDTO.getText()) {
            return ResponseEntity.status(500).body(new ResponseDTO());
        } else {
            ResponseDTO responseDTO = memoryService.saveMemory(memoryDTO.getUserId(), memoryDTO.getText());
            if ("success".equalsIgnoreCase(responseDTO.getStatus())) {
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.status(500).body(responseDTO);
            }

        }
    }

}
