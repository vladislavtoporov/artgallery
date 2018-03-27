package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Exhibit;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long>{
}
