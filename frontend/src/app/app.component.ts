import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { catchError, of } from 'rxjs';

type Shipment = {id:number;trackingCode:string;productName:string;minTempC:number;maxTempC:number;status:string};
type LoginResponse = {token:string;email:string;role:string};
const BASE_URL='http://localhost:8080/api';
@Component({selector:'app-root',standalone:true,imports:[CommonModule,FormsModule],template:`
 <div class="shell">
  <header><div class="brand">❄ Logi<span>Temp</span></div><span class="tag">Centre de supervision · Formation</span></header>
  <main>
   <div class="hero"><p class="eyebrow">SUPERVISION LOGISTIQUE</p><h1>Chaîne du froid en temps réel</h1>
    <p>Suivez les expéditions réfrigérées. Cette interface Angular de démonstration est fournie en lecture seule.</p></div>
   <section class="panel" *ngIf="!token">
    <h2>Accès aux expéditions</h2><p>Connectez-vous avec un compte de démonstration pour consulter le parc.</p>
    <form (ngSubmit)="login()"><label>E-mail <input name="email" type="email" [(ngModel)]="email" required></label>
     <label>Mot de passe <input name="password" type="password" [(ngModel)]="password" required></label>
     <button type="submit">Se connecter</button></form>
   </section>
   <section class="panel" *ngIf="token">
    <div class="row"><h2>Expéditions actives</h2><button class="outline" (click)="load()">Actualiser</button></div>
    <div class="grid"><article *ngFor="let x of shipments"><small>{{x.trackingCode}}</small><h3>{{x.productName}}</h3>
      <p>Plage : {{x.minTempC}} à {{x.maxTempC}} °C</p><b>{{x.status}}</b></article></div>
    <p *ngIf="!shipments.length">Aucune expédition trouvée.</p>
   </section><p class="error" *ngIf="error">{{error}}</p>
   <aside>API Spring Boot : localhost:8080 · UI Angular : localhost:4200 · Aucune modification du frontend n'est demandée.</aside>
  </main>
 </div>`})
export class AppComponent {
 private http=inject(HttpClient);
 email='operateur@logitemp.test';password='Demo1234!';token='';error='';shipments:Shipment[]=[];
 login(){this.error='';this.http.post<LoginResponse>(`${BASE_URL}/auth/login`,{email:this.email,password:this.password})
  .pipe(catchError(()=>{this.error='Connexion refusée ou requête bloquée (vérifiez le CORS).';return of(null);}))
  .subscribe(r=>{if(r){this.token=r.token;this.load();}});}
 load(){this.error='';this.http.get<Shipment[]>(`${BASE_URL}/shipments`,{
  headers:new HttpHeaders({Authorization:`Bearer ${this.token}`})
 }).pipe(catchError(()=>{this.error='Chargement impossible. Vérifiez l’API et les autorisations CORS.';return of([] as Shipment[]);})).subscribe(v=>this.shipments=v);}
}
