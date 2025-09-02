package project_oodd.ecom.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import project_oodd.ecom.dto.SizeDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.Size;
import project_oodd.ecom.repository.SizeRepository;

@Service
public class SizeServiceImpl implements SizeService {
	
	@Autowired
    private SizeRepository sizeRepository;
	
	public List<SizeDTO> getSizes() {
        return convertToDTO(sizeRepository.findAll());
    }

    public SizeDTO getSizeById(UUID id) {
        SizeDTO Size = convertToDTO(sizeRepository.findById(id)
                .orElseThrow(() -> new AppException("Size not found with this id", 404)));
        
        return Size;
    }

    public SizeDTO createSize(Size data) {
    	Size Size = new Size();
        Size.setValue(data.getValue());
        return convertToDTO(sizeRepository.save(Size));
    }

    public SizeDTO updateSize(UUID id, Size data) {

        Size Size = sizeRepository.findById(id)
                .orElseThrow(() -> new AppException("Size not found with this id", 404));

        if (data.getValue() != null) Size.setValue(data.getValue());

        return convertToDTO(sizeRepository.save(Size));
    }

    public void deleteSize(UUID id) {
        Size Size = sizeRepository.findById(id)
                .orElseThrow(() -> new AppException("Size not found with this id", 404));
        sizeRepository.delete(Size);
    }
    
    public SizeDTO convertToDTO(Size size) {
        SizeDTO dto = new SizeDTO();
        dto.setSid(size.getSid());
        dto.setValue(size.getValue());

        return dto;
    }
    
    public List<SizeDTO> convertToDTO(List<Size> Sizes) {
        if (Sizes == null || Sizes.isEmpty()) {
            return Collections.emptyList();
        }

        return Sizes.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

}
