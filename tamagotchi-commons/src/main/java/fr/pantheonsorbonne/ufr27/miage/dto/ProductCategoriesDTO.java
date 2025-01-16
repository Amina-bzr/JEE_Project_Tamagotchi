package fr.pantheonsorbonne.ufr27.miage.dto;

public enum ProductCategoriesDTO {
        VETEMENTS("Vetements"),
        ACCESSOIRES("Accessoires"),
        JOUETS("Jouets");

        private final String value;

        ProductCategoriesDTO(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
}
