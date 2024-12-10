package org.example.completeapp.repositories;

import org.example.completeapp.entities.Biography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiographyRepository extends JpaRepository<Biography, Long> {

}