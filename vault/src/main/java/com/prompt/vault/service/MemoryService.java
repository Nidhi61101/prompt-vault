package com.prompt.vault.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prompt.vault.models.Memory;
import com.prompt.vault.repository.MemoryRepository;
import com.prompt.vault.models.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryService {

    private static final Logger log = LoggerFactory.getLogger(MemoryService.class);
    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseDTO saveMemory(String userId, String text){
        log.info("Saving memory for user: {}",userId);
        float[] response = embeddingModel.embed(text);
        ResponseDTO responseDTO = new ResponseDTO();
        String embeddedJson;
        try {
            embeddedJson = objectMapper.writeValueAsString(response);
            Memory memory = new Memory();
            memory.setUserId(userId);
            memory.setText(text);
            memory.setEmbedding(embeddedJson);
            memoryRepository.save(memory);
            log.info("Memory saved successfully for user: {}", userId);


            responseDTO.setStatus("success");
            responseDTO.setMessage("Memory saved successfully for user:" + userId);
        } catch (Exception e ) {
            log.error("Error Converting embedding to JSON",e);
            responseDTO.setStatus("failed");
            responseDTO.setMessage("Memory save failed for user: "+ userId +"due to exception: "+ e.getMessage());
        }
        return responseDTO;
    }
}
