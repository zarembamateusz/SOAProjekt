package models.service;

import org.eclipse.persistence.jpa.config.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface GenericService<DTO extends Serializable, ID extends Serializable> {

    void update(final DTO dto);

    void create(final DTO dto);

    void delete(final DTO entity);

    void deleteById(final ID id);

    DTO findById(final ID id);

    List<DTO> getAll();



}
