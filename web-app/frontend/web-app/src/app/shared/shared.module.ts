import { NgModule, APP_INITIALIZER, Injector } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule, LOCATION_INITIALIZED } from '@angular/common';
import { MatDatepickerModule, MatNativeDateModule, MatSidenavModule, MatMenuModule, MatCheckboxModule,
  MatCardModule, MatIconModule, MatChipsModule, MatTooltipModule, MatSelectModule, MatButtonModule, MatDialogModule,
  MatRadioModule, MatAutocompleteModule, MatProgressBarModule, MatInputModule, MatProgressSpinnerModule, MatTabsModule,
  MatToolbarModule } from '@angular/material';
import { ActionBarComponent } from './action-bar/action-bar.component';
import { AgmCoreModule } from '@agm/core';
import { AgmJsMarkerClustererModule } from '@agm/js-marker-clusterer';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { LoadingSpinnerComponent } from './loading-spinner.component';
import { ServiceModule } from './services/service.module';
import { SuccessMessageComponent } from './success-message';
import { WelcomeRoutingModule } from '../welcome/welcome.routing.module';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

export function appInitializerFactory(translate: TranslateService, injector: Injector) {
  return () => new Promise<any>((resolve: any) => {
    const locationInitialized = injector.get(LOCATION_INITIALIZED, Promise.resolve(null));
    locationInitialized.then(() => {
      const langDefault = 'es';
      translate.setDefaultLang(langDefault);
      translate.use(langDefault).subscribe(() => {
      }, err => {
        console.error(err);
      }, () => {
        resolve(null);
      });
    });
  });
}

@NgModule({
  imports: [
    CommonModule, MatDatepickerModule, MatNativeDateModule, MatSidenavModule, MatMenuModule, MatCheckboxModule,
    MatCardModule, MatIconModule, MatChipsModule, MatTooltipModule, MatSelectModule, MatButtonModule, MatDialogModule,
    MatRadioModule, MatAutocompleteModule, MatProgressBarModule, MatInputModule, MatProgressSpinnerModule, MatToolbarModule,
    MatTabsModule, AgmJsMarkerClustererModule, FormsModule, ReactiveFormsModule,
    ServiceModule, WelcomeRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDdlb5QYlK76D6dwkb1cRgztQvdsiYnsbc'
    }),
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [ HttpClient ]
      }
    })
  ],
  exports: [
    MatDatepickerModule, MatNativeDateModule, MatSidenavModule, MatMenuModule, MatCheckboxModule,
    MatCardModule, MatIconModule, MatChipsModule, MatTooltipModule, MatSelectModule, MatButtonModule, MatDialogModule,
    MatRadioModule, MatAutocompleteModule, MatProgressBarModule, MatInputModule, MatProgressSpinnerModule, MatToolbarModule,
    MatTabsModule, ActionBarComponent, AgmCoreModule, AgmJsMarkerClustererModule,
    FormsModule, ReactiveFormsModule, HttpClientModule, TranslateModule, LoadingSpinnerComponent,
    SuccessMessageComponent, WelcomeRoutingModule,
  ],
  declarations: [ActionBarComponent, LoadingSpinnerComponent, SuccessMessageComponent],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: appInitializerFactory,
      deps: [TranslateService, Injector],
      multi: true
    }]
})
export class SharedModule { }
