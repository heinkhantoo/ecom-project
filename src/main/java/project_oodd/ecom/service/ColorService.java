package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.ColorDTO;
import project_oodd.ecom.model.Color;

public interface ColorService {

	List<ColorDTO> getColors();
    ColorDTO getColorById(String id);
    ColorDTO createColor(Color color);
    ColorDTO updateColor(String id, Color color);
    void deleteColor(String id);
}
