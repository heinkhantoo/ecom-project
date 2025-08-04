package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.SizeDTO;
import project_oodd.ecom.model.Size;

public interface SizeService {

	List<SizeDTO> getSizes();
    SizeDTO getSizeById(String id);
    SizeDTO createSize(Size size);
    SizeDTO updateSize(String id, Size size);
    void deleteSize(String id);
}
