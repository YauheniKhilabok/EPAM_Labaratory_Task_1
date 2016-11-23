package com.epam.onlineshop.enums;

/**
 * The TypeEnum enum announces a series of sports nutrition types,
 * which are in the range of sports nutrition store.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public enum TypeEnum {
    PROTEINS("Proteins"),
    AMINOACIDS("AminoAcids"),
    BCAA("BCAA"),
    GAINERS("Gainers"),
    CREATINE("Creatine"),
    TESTOSTERONE("Testosterone"),
    COMPLEXES("Complexes"),
    WEIGHTLOSS("WeightLoss"),
    CHONDROPROTECTORS("Chondroprotectors"),
    VITAMINS("Vitamins"),
    ENERGY("Energy"),
    SPECIALPREPARATIONS("SpecialPreparations"),
    BARS("Bars");
    /**
     * Property - enumeration value.
     */
    private String value;

    /**
     * Create new enumeration.
     */
    private TypeEnum(String value) {
        this.value = value;
    }

    /**
     * Method for get value of field {@link TypeEnum#value}
     *
     * @return return enumeration value.
     */
    public String getValue() {
        return value;
    }
}
