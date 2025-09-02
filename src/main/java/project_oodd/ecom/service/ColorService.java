package project_oodd.ecom.service;

import java.util.List;
import java.util.UUID;

import project_oodd.ecom.dto.ColorDTO;
import project_oodd.ecom.model.Color;

public interface ColorService {

	List<ColorDTO> getColors();
    ColorDTO getColorById(UUID id);
    ColorDTO createColor(Color color);
    ColorDTO updateColor(UUID id, Color color);
    void deleteColor(UUID id);
}
