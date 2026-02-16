package Models;

import java.time.LocalDateTime;

/*
 * This class represents a set of vital signs recorded for a senior.
 * It is part of the Model layer in MVC.
 *
 * It stores:
 * - Heart rate
 * - Blood pressure (systolic & diastolic)
 * - Glucose level
 * - Timestamp of when the vitals were taken
 * - Verification status (for nurse confirmation)
 */

public class Vitals {

    private int heartRate;
    private int systolic;
    private int diastolic;
    private double glucose;
    private LocalDateTime timestamp;
    private boolean verified;

    // Constructor automatically records the current date and time
    public Vitals(int heartRate, int systolic, int diastolic, double glucose) {
        this.heartRate = heartRate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.glucose = glucose;
        this.timestamp = LocalDateTime.now();
        this.verified = false;
    }

    // Getter methods for accessing values

    public int getHeartRate() { return heartRate; }
    public int getSystolic() { return systolic; }
    public int getDiastolic() { return diastolic; }
    public double getGlucose() { return glucose; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public boolean isVerified() { return verified; }

    // Used by nurse to confirm a self-reported vital entry
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}

