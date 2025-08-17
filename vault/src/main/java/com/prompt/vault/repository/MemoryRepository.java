package com.prompt.vault.repository;

import com.prompt.vault.models.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, String> {
}
