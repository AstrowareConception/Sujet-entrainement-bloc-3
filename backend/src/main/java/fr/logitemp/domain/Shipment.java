package fr.logitemp.domain;

import jakarta.persistence.*;

@Entity @Table(name="shipments", uniqueConstraints=@UniqueConstraint(columnNames="tracking_code"))
public class Shipment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="tracking_code", nullable=false, unique=true) private String trackingCode;
 @Column(name="product_name", nullable=false) private String productName;
 @Column(name="min_temp_c", nullable=false) private Double minTempC;
 @Column(name="max_temp_c", nullable=false) private Double maxTempC;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ShipmentStatus status=ShipmentStatus.CREATED;
 protected Shipment() {}
 public Shipment(String trackingCode,String productName,Double minTempC,Double maxTempC) {
  this.trackingCode=trackingCode; this.productName=productName;this.minTempC=minTempC;this.maxTempC=maxTempC;
 }
 public Long getId(){return id;}
 public String getTrackingCode(){return trackingCode;}
 public String getProductName(){return productName;}
 public Double getMinTempC(){return minTempC;}
 public Double getMaxTempC(){return maxTempC;}
 public ShipmentStatus getStatus(){return status;}
 public void release(){status=ShipmentStatus.RELEASED;}
}
