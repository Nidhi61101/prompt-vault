package com.prompt.vault.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="memory_entry")
public class Memory {

    @Column(name="user_id", unique=true, nullable=false)
    private String userId;

    @Column(name="text", columnDefinition = "TEXT")
    private String text;

    @Column(name="embedding",columnDefinition = "JSONB")
    private String embedding;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        text = text;
    }

    public String getEmbedding() {
        return embedding;
    }

    public void setEmbedding(String embedding) {
        this.embedding = embedding;
    }
}
