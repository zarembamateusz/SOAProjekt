package service;

import java.util.List;

public interface GenericService<DTO, ID> {

    void update(final DTO dto);

    void create(final DTO dto);

    void delete(final DTO entity);

    void deleteById(final ID id);

    DTO findById(final ID id);

    List<DTO> getAll();


}
