package project_oodd.ecom.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project_oodd.ecom.dto.ColorDTO;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.Color;
import project_oodd.ecom.repository.ColorRepository;

@Service
public class ColorServiceImpl implements ColorService {
	@Autowired
    private ColorRepository colorRepository;
	
	public List<ColorDTO> getColors() {
        return convertToDTO(colorRepository.findAll());
    }

    public ColorDTO getColorById(String id) {
        ColorDTO color = convertToDTO(colorRepository.findByColorCodeIgnoreCase(id)
                .orElseThrow(() -> new AppException("Color not found with this id", 404)));
        
        return color;
    }

    public ColorDTO createColor(ColorDTO dto) {
    	Color color = new Color();
        color.setColorCode(dto.getColorCode());
        color.setColorDescription(dto.getColorDescription());
        return convertToDTO(colorRepository.save(color));
    }

    public ColorDTO updateColor(String id, ColorDTO dto) {

        Color color = colorRepository.findByColorCodeIgnoreCase(id)
                .orElseThrow(() -> new AppException("Color not found with this id", 404));
        
        if (dto.getColorCode() != null) color.setColorCode(dto.getColorCode());
        if (dto.getColorDescription() != null) color.setColorDescription(dto.getColorDescription());

        return convertToDTO(colorRepository.save(color));
    }

    public void deleteColor(String id) {
        Color color = colorRepository.findByColorCodeIgnoreCase(id)
                .orElseThrow(() -> new AppException("Color not found with this id", 404));
        colorRepository.delete(color);
    }
    
    public ColorDTO convertToDTO(Color color) {
        ColorDTO dto = new ColorDTO();
        dto.setColorCode(color.getColorCode());
        dto.setColorDescription(color.getColorDescription());

        return dto;
    }
    
    public List<ColorDTO> convertToDTO(List<Color> colors) {
        if (colors == null || colors.isEmpty()) {
            return Collections.emptyList();
        }

        return colors.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
}
