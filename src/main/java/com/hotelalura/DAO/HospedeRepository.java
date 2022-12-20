package com.hotelalura.DAO;

import com.hotelalura.models.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, Integer> {
}
