package Models;

/*
 * This class represents a medication prescribed to a senior.
 * It is part of the Model layer.
 *
 * It stores basic prescription information that will be sent to the server.
 */

public class Medication {

    private String name;
    private String dosage;
    private String frequency;
    private String instructions;

    public Medication(String name, String dosage, String frequency, String instructions) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.instructions = instructions;
    }

    public String getName() { return name; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public String getInstructions() { return instructions; }
}

