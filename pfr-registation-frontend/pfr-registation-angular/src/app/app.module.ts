import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UtilisateurModule } from './modules/utilisateur/utilisateur.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SharedModule } from './modules/shared/shared.module';
import { MatDatepickerModule} from '@angular/material/datepicker';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { EvenementModule } from './modules/evenement/evenement.module';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {Document2Module} from './modules/document2/document2.module';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {MAT_MOMENT_DATE_FORMATS, MatMomentDateModule, MomentDateAdapter} from '@angular/material-moment-adapter';


import { JwtModule } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { LoginComponent } from './login/login.component';



import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { PresentationTeamComponent } from './presentation-team/presentation-team.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MaterielModule } from './modules/materiel/materiel.module';
import { AgmCoreModule } from '@agm/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatDialogRef, MAT_DIALOG_DATA, MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { UpdateMaterielComponent } from './modules/materiel/update-materiel/update-materiel.component';



export function jwtTokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PresentationTeamComponent,
    DashboardComponent,
  ],

  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    UtilisateurModule,
    MaterielModule,
    SharedModule,
    FormsModule,
    Document2Module,
    EvenementModule,
    MatDatepickerModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,

    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBEz6jxiWdqhyz79LyCjtgjG3ExiQVB0Nw'
    }),

    JwtModule.forRoot({
      config: {
        // pour injecter le token dans toutes les requetes
        tokenGetter: jwtTokenGetter,

        // inject le token pour tous ces chemin
        allowedDomains: [environment.backServer],

        // n'injecte pas le token pour ce chemin
        disallowedRoutes: [`${environment.backSchema}://${environment.backServer}/login`]
      }
    }),
    ReactiveFormsModule

  ],

  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    ],
  bootstrap: [AppComponent]
})
export class AppModule {}
