package org.example.completeapp.services;

import org.example.completeapp.entities.Publisher;
import org.example.completeapp.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Se encarga de la lógica de negocio
// Aquí pondremos todas las operaciones que se pueden hacer con los editores
@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }

}
