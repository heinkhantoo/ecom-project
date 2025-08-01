package project_oodd.ecom.service;

import java.util.List;

import project_oodd.ecom.dto.ColorDTO;

public interface ColorService {

	List<ColorDTO> getColors();
    ColorDTO getColorById(String id);
    ColorDTO createColor(ColorDTO color);
    ColorDTO updateColor(String id, ColorDTO color);
    void deleteColor(String id);
}
