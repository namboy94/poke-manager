package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;

public class SpeciesDescription {

    /**
     * The weight of the Pokemon in kg
     */
    private double metricWeight;

    /**
     * The weight of the Pokemon in lbs
     */
    private double imperialWeight;

    /**
     * The height of the Pokemon in m
     */
    private double metricHeight;

    /**
     * The height of the Pokemon in feet
     */
    private double imperialHeight;

    /**
     * A description of the Pokemon
     */
    private String classification;

    /**
     * Creates a new Species Description
     * @param metricWeight:   The weight in kg
     * @param imperialWeight: The weight in lbs
     * @param metricHeight:   The height in m
     * @param imperialHeight: The height in feet
     * @param classification: The description of the Pokemon
     */
    public SpeciesDescription(double metricWeight,
                              double imperialWeight,
                              double metricHeight,
                              double imperialHeight,
                              String classification) {

        this.metricWeight = metricWeight;
        this.imperialWeight = imperialWeight;
        this.metricHeight = metricHeight;
        this.imperialHeight = imperialHeight;
        this.classification = classification;

    }

    /**
     * @return The Pokemon's weight in kg
     */
    public double getMetricWeight() {
        return this.metricWeight;
    }

    /**
     * @return The Pokemon's height in m
     */
    public double getMetricHeight() {
        return this.metricHeight;
    }

    /**
     * @return The Pokemon's weight in lbs
     */
    public double getImperialWeight() {
        return this.imperialWeight;
    }

    /**
     * @return The Pokemon's height in feet/inches
     */
    public double getImperialHeight() {
        return this.imperialHeight;
    }

    /**
     * @return The Pokemon's classification text
     */
    public String getClassification() {
        return this.classification;
    }

    /**
     * Creates a new Species Description object from a parsed Serebii Parser
     * @param parser: The parser to use
     * @return        The generated SpeciesDescription
     */
    public static SpeciesDescription fromSerebiiPage(SerebiiParser parser) {

        double[] weights = parser.parseWeight();
        double[] heights = parser.parseHeight();
        String classification = parser.parseClassification();

        return new SpeciesDescription(weights[0], weights[1], heights[0], heights[1], classification);
    }
}
