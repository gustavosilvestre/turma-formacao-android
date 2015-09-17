package br.com.cast.turmaformacao.taskmanager.model.entities;

/**
 * Created by Administrador on 17/09/2015.
 */
public enum Color {

    RED("#F44336"),
    PINK("#E91E63"),
    PURPLE("#9C27B0"),
    BLUE("#2196F3"),
    DEEP_PURPLE("#673AB7"),
    INDIGO("#3F51B5"),
    LIGHT_BLUE("#03A9F4"),
    CYAN("#00BCD4"),
    TEAL("#009688"),
    GREEN("#4CAF50"),
    LIGHT_GREEN("#8BC34A"),
    LIME("#CDDC39"),
    YELLOW("#FFEB3B"),
    AMBER("#FFC107"),
    ORANGE("#FF9800"),
    DEEP_ORANGE("#FF5722"),
    BROWN("#795548"),
    GREY("#9E9E9E"),
    BLUE_GREY("#607D8B"),
    BLACK("#000000");

    private String hex;

    private Color(String hex) {
        this.hex = hex;
    }


    public String getHex() {
        return hex;
    }

    public static Color getInstance(String hex) {

        Color[] values = Color.values();
        for (Color c : values) {
            if (c.getHex().equals(hex)) {
                return c;
            }
        }
        return null;
    }
}
