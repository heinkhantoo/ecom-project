package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.SizeDTO;

public interface SizeService {

	List<SizeDTO> getSizes();
    SizeDTO getSizeById(String id);
    SizeDTO createSize(SizeDTO size);
    SizeDTO updateSize(String id, SizeDTO size);
    void deleteSize(String id);
}
