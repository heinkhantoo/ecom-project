package project_oodd.ecom.service;

import java.util.List;
import java.util.UUID;

import project_oodd.ecom.dto.SizeDTO;
import project_oodd.ecom.model.Size;

public interface SizeService {

	List<SizeDTO> getSizes();
    SizeDTO getSizeById(UUID id);
    SizeDTO createSize(Size size);
    SizeDTO updateSize(UUID id, Size size);
    void deleteSize(UUID id);
}
