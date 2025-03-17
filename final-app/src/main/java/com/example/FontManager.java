package com.example;

public class FontManager {
    private static FontManager instance;
    private String selectedFont;

    private FontManager() {
        // Private constructor to prevent instantiation
    }

    public static FontManager getInstance() {
        if (instance == null) {
            instance = new FontManager();
        }
        return instance;
    }

    public String getSelectedFont() {
        return selectedFont;
    }

    public void setSelectedFont(String selectedFont) {
        this.selectedFont = selectedFont;
    }
}