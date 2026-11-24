package fr.logitemp.domain;

import java.time.Instant;
import jakarta.persistence.*;

@Entity @Table(name="telemetry_readings")
public class TelemetryReading {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="shipment_id") private Shipment shipment;
 @Column(name="temperature_c",nullable=false) private Double temperatureC;
 @Column(name="humidity_pct",nullable=false) private Double humidityPct;
 @Column(name="measured_at",nullable=false,columnDefinition="timestamptz") private Instant measuredAt;
 protected TelemetryReading() {}
 public TelemetryReading(Shipment shipment,Double temperatureC,Double humidityPct,Instant measuredAt) {
  this.shipment=shipment;this.temperatureC=temperatureC;this.humidityPct=humidityPct;this.measuredAt=measuredAt;
 }
 public Long getId(){return id;}
 public Long getShipmentId(){return shipment.getId();}
 public Double getTemperatureC(){return temperatureC;}
 public Double getHumidityPct(){return humidityPct;}
 public Instant getMeasuredAt(){return measuredAt;}
}
